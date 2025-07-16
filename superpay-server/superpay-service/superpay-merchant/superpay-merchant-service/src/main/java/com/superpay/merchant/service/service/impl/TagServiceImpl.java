package com.superpay.merchant.service.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.superpay.merchant.model.entity.Tag;
import com.superpay.merchant.service.mapper.TagMapper;
import com.superpay.merchant.service.service.TagService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 标签表 服务实现类
 * </p>
 *
 * @author lihainuo
 * @since 2024-11-10
 */
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {

}
