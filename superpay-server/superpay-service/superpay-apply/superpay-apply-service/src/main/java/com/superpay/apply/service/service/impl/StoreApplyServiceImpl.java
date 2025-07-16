package com.superpay.apply.service.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.superpay.apply.model.dto.ApplyPageQueryDTO;
import com.superpay.apply.model.dto.StoreApplyDTO;
import com.superpay.apply.model.entity.StoreApply;
import com.superpay.apply.service.mapper.StoreApplyMapper;
import com.superpay.apply.service.service.StoreApplyService;
import com.superpay.merchant.api.MerchantClient;
import com.superpay.merchant.model.entity.Store;
import com.superpay.sso.api.SSOClient;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 店铺入驻申请表 服务实现类
 * </p>
 *
 * @author lihainuo
 * @since 2024-11-10
 */
@Slf4j
@Service
public class StoreApplyServiceImpl extends ServiceImpl<StoreApplyMapper, StoreApply> implements StoreApplyService {
    @Resource
    private HttpServletRequest request;
    @Resource
    private SSOClient ssoClient;

    @Resource
    private MerchantClient merchantClient;

    //    @Resource
//    private StoreClient storeClient;
    @Override
    public void apply(StoreApply storeApply) {
        String token = request.getHeader("Authorization");
        log.info("token:{}", token);
        //merchantId和memberId一致
        String memberId = ssoClient.getMemberIdByToken();
        log.info("memberId:{}", memberId);
        storeApply.setMerchantId(memberId);
        this.save(storeApply);
    }

    @Override
    public void approval(StoreApplyDTO storeApplyDTO) {
        //为什么不能new，要从数据库读？
        StoreApply storeApply = this.getById(storeApplyDTO.getId());
        //判断  待审核
        if (!"0".equals(storeApply.getStatus())) {
            throw new RuntimeException("申请单状态异常");
        }
        storeApply.setStatus(storeApplyDTO.getStatus());
        storeApply.setRemark(storeApplyDTO.getRemark());
        this.updateById(storeApply);

        //生成对应的店铺信息
        Store store = new Store();
        BeanUtils.copyProperties(storeApply, store);
        //对拷的过程中会把申请单的id拷到店铺id里
        store.setStoreApplyId(storeApply.getId());
        store.setStoreName(storeApply.getStoreName());
        store.setId(null);
        merchantClient.addStore(store);
    }

    @Override
    public IPage<StoreApply> getPage(ApplyPageQueryDTO applyPageQueryDTO) {
        IPage<StoreApply> pageInfo = new Page<>(applyPageQueryDTO.getCurrent(), applyPageQueryDTO.getPageSize());
         LambdaQueryWrapper<StoreApply> wrapper=new LambdaQueryWrapper<>();
        String token = request.getHeader("Authorization");
        log.info("token:{}", token);
        String memberId = ssoClient.getMemberIdByToken();
        wrapper.eq(StoreApply::getMerchantId,memberId);
        IPage<StoreApply> page = this.page(pageInfo,wrapper);
        return page;
    }
}
