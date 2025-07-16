package com.superpay.sso.service.controller;

import com.superpay.common.Response;
import com.superpay.member.model.entity.MemberInfo;
import com.superpay.sso.model.dto.LoginDTO;
import com.superpay.sso.model.dto.SendSmsDTO;
import com.superpay.sso.service.enums.AuthType;
import com.superpay.sso.service.service.MemberAuthService;
import com.superpay.sso.service.service.MemberService;
import com.superpay.sso.model.vo.MemberLoginVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author lihainuo
 * @since 2024-10-27
 */
@Tag(name = "会员管理")
@Slf4j
@RestController
@RequestMapping("/member")
public class MemberController {
    @Resource
    private MemberService memberService;

    private final Map<String, MemberAuthService> memberAuthServiceHashMap;

    public MemberController(Map<String, MemberAuthService> memberAuthServiceHashMap){
        this.memberAuthServiceHashMap =memberAuthServiceHashMap;
    }

    @Operation(summary = "会员登录")
    @PostMapping("login")
    public Response<MemberLoginVO> login(@RequestBody LoginDTO loginDTO) {
        log.info("loginDTO:{}", loginDTO);

        MemberAuthService memberService = memberAuthServiceHashMap.get(loginDTO.getType());
        MemberLoginVO memberLoginVO = memberService.login(loginDTO);

        return Response.success(memberLoginVO);
    }

    @Operation(summary = "获取验证码")
    @PostMapping("getCode")
    public Response<Object> getCode(@RequestBody SendSmsDTO sendSmsDTO){

        MemberAuthService memberAuthService = memberAuthServiceHashMap.get(AuthType.VERIFICATION_CODE.getClassName());
        memberAuthService.sendCode(sendSmsDTO.getPhone());
        //todo 记录场景使用次数
        return Response.success();
    }

    @Operation(summary = "根据token获取会员信息")
    @GetMapping("findByToken")
    public Response<MemberInfo> findByToken(){
        MemberInfo memberInfo =memberService.findByToken();
        return Response.success(memberInfo);
    }

    @Operation(summary = "根据token获取会员id")
    @GetMapping("getMemberIdByToken")
    public Response<String> getMemberIdByToken(){
        log.info("1");
        return Response.success(memberService.getMemberIdByToken()) ;
    }
}
