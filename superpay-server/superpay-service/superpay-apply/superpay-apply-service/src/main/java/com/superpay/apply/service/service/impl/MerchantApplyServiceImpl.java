package com.superpay.apply.service.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.superpay.apply.service.context.BaseContext;
import com.superpay.apply.model.dto.ApplyPageQueryDTO;
import com.superpay.apply.model.dto.MerchantApplyDTO;
import com.superpay.apply.model.entity.MerchantApply;
import com.superpay.apply.service.mapper.MerchantApplyMapper;
import com.superpay.apply.service.service.MerchantApplyService;
import com.superpay.merchant.api.MerchantClient;
import com.superpay.merchant.model.entity.MerchantInfo;
import com.superpay.sso.api.SSOClient;
import com.superpay.sso.model.entity.Merchant;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商家入驻申请表 服务实现类
 * </p>
 *
 * @author lihainuo
 * @since 2024-11-10
 */
@Slf4j
@Service
public class MerchantApplyServiceImpl extends ServiceImpl<MerchantApplyMapper, MerchantApply> implements MerchantApplyService {

    @Resource
    private SSOClient ssoClient;

    @Resource
    private MerchantClient merchantClient;

    @Override
    public void apply(MerchantApply merchantApply) {
        String memberId = BaseContext.get();
        merchantApply.setMemberId(memberId);

        this.save(merchantApply);
    }

    @Override
    public void approval(MerchantApplyDTO merchantApplyDTO) {
        MerchantApply merchantApply = this.getById(merchantApplyDTO.getId());
        merchantApply.setId(merchantApplyDTO.getId());

        //判断  待审核
        if(!"0".equals(merchantApply.getStatus())){
            throw new RuntimeException("申请单状态异常");
        }
        merchantApply.setStatus(merchantApplyDTO.getStatus());
        merchantApply.setRemark(merchantApplyDTO.getRemark());
        this.updateById(merchantApply);
        //生成对应的商户登录信息 sso

        Merchant merchant=new Merchant();
        merchant.setId(merchantApply.getMemberId());
        merchant.setPhone(merchantApply.getPhone());
        //截取身份证的后6位或者手机号后6位，推荐用身份证
        merchant.setPassword(merchantApply.getIdCard().substring(merchantApply.getIdCard().length()-6));

        ssoClient.save(merchant);
        //生成对应的商户信息非登录信息 merchant_info表
        MerchantInfo merchantInfo=new MerchantInfo();
        //todo 对拷
        BeanUtils.copyProperties(merchantApply,merchantInfo);
        merchantInfo.setId(merchant.getId());
        merchantClient.save(merchantInfo);
    }

    @Override
    public IPage<MerchantApply> getPage(ApplyPageQueryDTO applyPageQueryDTO) {
        IPage<MerchantApply> pageInfo=new Page<>(applyPageQueryDTO.getCurrent(), applyPageQueryDTO.getPageSize());
        LambdaQueryWrapper<MerchantApply> wrapper=new LambdaQueryWrapper<>();
        String memberId = BaseContext.get();
        wrapper.eq(MerchantApply::getMemberId,memberId);
        IPage<MerchantApply> page = this.page(pageInfo,wrapper);
        return page;
    }
}
