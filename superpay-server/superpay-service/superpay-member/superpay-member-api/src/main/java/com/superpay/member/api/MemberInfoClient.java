package com.superpay.member.api;

import com.superpay.member.model.entity.MemberInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author lihainuo
 */
@Component
@FeignClient(
        name = "superpay-member-service",
        contextId = "superpay-member-service"
)
public interface MemberInfoClient {
    @PostMapping("memberInfo")
    MemberInfo save(@RequestBody MemberInfo memberInfo);

    @GetMapping("memberInfoApi/{id}")
    MemberInfo getInfoById(@PathVariable("id") String id);
}
