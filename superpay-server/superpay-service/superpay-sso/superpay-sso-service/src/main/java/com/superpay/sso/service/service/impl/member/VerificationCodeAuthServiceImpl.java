package com.superpay.sso.service.service.impl.member;

import com.superpay.common.exceptions.BusinessException;
import com.superpay.common.exceptions.enums.ResponseEnum;
import com.superpay.member.api.MemberInfoClient;
import com.superpay.member.model.entity.MemberInfo;
import com.superpay.sso.model.dto.LoginDTO;
import com.superpay.sso.model.entity.Member;
import com.superpay.sso.service.service.MemberAuthService;
import com.superpay.sso.service.service.MemberService;
import com.superpay.sso.service.utils.JwtUtil;
import com.superpay.sso.model.vo.MemberLoginVO;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author lihainuo
 */
@Slf4j
@Service("verificationCodeAuthService")
public class VerificationCodeAuthServiceImpl implements MemberAuthService {
    @Resource
    private RedisTemplate<String, String> redisTemplate;
    @Resource
    private JwtUtil jwtUtil;

    @Resource
    private RabbitTemplate rabbitTemplate;

    @Resource
    private MemberService memberService;

    @Resource
    private MemberInfoClient memberInfoClient;

    @Override
    public void sendCode(String phone) {
        Random random = new Random();
        String code = random.nextInt(8888) + 1000 + "";
        //redis编排方式： 项目名:模块名:场景名:特征码
        //判断手机号格式
        if(phone.length()!=11){
            throw new BusinessException(ResponseEnum.INVALID_PHONE);
        }
        String key = "superpay:sso:login:" + phone+":code";
        String count="superpay:sso:login:"+phone+":count";
        //判断是否超过5次
        if(Boolean.TRUE.equals(redisTemplate.hasKey(count))){
            String s = redisTemplate.opsForValue().get(count);
            if(s==null){
                throw new BusinessException(ResponseEnum.ERROR);
            }
            if(Integer.parseInt(s)>5){
                throw new BusinessException(ResponseEnum.TOO_MANY_REQUESTS);
            }
        }

        redisTemplate.opsForValue().set(key, code, 360, TimeUnit.SECONDS);
        //记一次发送次数
        if(Boolean.FALSE.equals(redisTemplate.hasKey(count))){
            redisTemplate.opsForValue().set(count,"1",5,TimeUnit.MINUTES);
        }else{
            int countInt = Integer.parseInt(redisTemplate.opsForValue().get(count))+1;
            redisTemplate.opsForValue().set(count,countInt+"",5,TimeUnit.MINUTES);
        }
        //往MQ中发送短信
        rabbitTemplate.convertAndSend("test.direct","sms","您的登录短信验证码："+code);
        log.info("验证码是：{}", code);
    }

    @Override
    public MemberLoginVO login(LoginDTO loginDTO) {
        log.info("短信验证码方式...");
        Member member = memberService.lambdaQuery()
                .eq(Member::getPhone, loginDTO.getPhone())
                .one();

        //redis取值对比
        String key = "superpay:sso:login:" + loginDTO.getPhone()+":code";
        String codeInRedis = redisTemplate.opsForValue().get(key);
        if(codeInRedis==null){
            throw new RuntimeException("短信验证码丢失或者失效");
        }
        if (!codeInRedis.equals(loginDTO.getCode())) {
            throw new RuntimeException("验证码错误");
        }

        if(member==null){
            //新用户
            member=new Member();
            member.setPhone(loginDTO.getPhone());
            //存入新用户
            memberService.save(member);
            //推入队列
            MemberInfo memberInfo=new MemberInfo();
            memberInfo.setId(member.getId());
            memberInfo.setPhone(member.getPhone());
            memberInfoClient.save(memberInfo);
        }

        //签发token
        HashMap<String,Object> map=new HashMap<>();
        map.put("id",member.getId());
        map.put("role","member");
        String token = jwtUtil.createToken(map);

        MemberLoginVO memberLoginVO=new MemberLoginVO();
        memberLoginVO.setId(member.getId());
        memberLoginVO.setToken(token);
        return memberLoginVO;
    }
}
