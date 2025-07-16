package com.superpay.merchant.service.service.impl;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.superpay.base.api.BaseClient;
import com.superpay.common.exceptions.BusinessException;
import com.superpay.common.exceptions.enums.ResponseEnum;
import com.superpay.merchant.model.dto.StorePageQueryDTO;
import com.superpay.merchant.model.entity.Store;
import com.superpay.merchant.service.mapper.StoreMapper;
import com.superpay.merchant.service.service.StoreService;
import com.superpay.merchant.model.vo.StorePageVO;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.data.geo.*;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

/**
 * <p>
 * 店铺表 服务实现类
 * </p>
 *
 * @author lihainuo
 * @since 2024-11-10
 */
@Service
public class StoreServiceImpl extends ServiceImpl<StoreMapper, Store> implements StoreService {

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    @Resource
    private BaseClient baseClient;

    @Override
    public void saveStore(Store store) {
        this.save(store);
        //加入到redis的list中
        StorePageVO storePageVO = new StorePageVO();
        BeanUtils.copyProperties(store, storePageVO);
        redisTemplate.opsForGeo().add("stories", new Point(store.getLongitude(), store.getLatitude()), JSON.toJSONString(storePageVO));
    }

    @Override
    public IPage<Store> getPage(StorePageQueryDTO storePageQueryDTO) {
        IPage<Store> pageInfo = new Page<>(storePageQueryDTO.getCurrent(), storePageQueryDTO.getPageSize());
        IPage<Store> page = this.page(pageInfo);
        return page;
    }

    @Override
    public IPage<StorePageVO> getNearbyPage(StorePageQueryDTO storePageQueryDTO) {
        //构建一个page，用在做返回值
        IPage<StorePageVO> pageInfo = new Page<>(storePageQueryDTO.getCurrent(), storePageQueryDTO.getPageSize());

        //判断redis里面有没有数据,没有数据就从数据库查并加入到redis
        if (!redisTemplate.hasKey("stories")) {
            List<Store> stories = this.lambdaQuery().eq(Store::getStatus, 1).list();
            for (Store store : stories) {
                //加入到redis的list中
                StorePageVO storePageVO = new StorePageVO();
                BeanUtils.copyProperties(store, storePageVO);
                redisTemplate.opsForGeo().add("stories", new Point(store.getLongitude(), store.getLatitude()), JSON.toJSONString(storePageVO));
            }
        }

        Circle circle = new Circle(
                new Point(storePageQueryDTO.getLongitude(), storePageQueryDTO.getLatitude()),
                new Distance(storePageQueryDTO.getDistance(), Metrics.KILOMETERS)
        );
        RedisGeoCommands.GeoRadiusCommandArgs args = RedisGeoCommands.GeoRadiusCommandArgs
                .newGeoRadiusArgs().includeDistance()
                .includeCoordinates().sortAscending().limit(100);
        GeoResults<RedisGeoCommands.GeoLocation<String>> results = redisTemplate.opsForGeo().radius("stories", circle, args);

        if (results == null) {
            //todo 附近没有门店的处理方案
        }

        //构建list用来填充page对象的records
        List<StorePageVO> storeList = new ArrayList<>();
        for (GeoResult<RedisGeoCommands.GeoLocation<String>> result : results) {
            RedisGeoCommands.GeoLocation<String> location = result.getContent();
            String name = location.getName();
            StorePageVO storePageVO = JSON.parseObject(name, StorePageVO.class);
            storePageVO.setDistance(result.getDistance().getValue());
            storeList.add(storePageVO);
        }
        pageInfo.setRecords(storeList);
        pageInfo.setTotal(storeList.size());
        pageInfo.setPages(storeList.size() / storePageQueryDTO.getPageSize());
        return pageInfo;
    }

    @Override
    public String generateQrCodeByStoreApplyId(String storeApplyId) {
        Store store = this.lambdaQuery().eq(Store::getStoreApplyId, storeApplyId).one();
        if (store == null) {
            throw new BusinessException(ResponseEnum.INVALID_PARAMETERS);
        }

        String url = "/pages/pay/index/index?merchantId=${MID}&storeId=${SID}";
        url = url.replace("${MID}", store.getMerchantId())
                .replace("${SID}", store.getId());
        url = Base64.getEncoder().encodeToString(url.getBytes());

        String base64Image = baseClient.generateQRCode(url);

        return base64Image;
    }

    public static void main(String[] args) {
        String url = "/pages/pay/index/index?merchantId=${MID}&storeId=${SID}";
//        String encode = URLEncoder.encode(url, StandardCharsets.UTF_8);
//
//        String decode = URLDecoder.decode(encode, StandardCharsets.UTF_8);

        String encode = Base64.getEncoder().encodeToString(url.getBytes());
        String decode = new String(Base64.getDecoder().decode(encode));
        System.out.println(encode);
        System.out.println(decode);
    }

}
