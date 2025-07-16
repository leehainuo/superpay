package com.superpay.sso.service.api;

import com.superpay.sso.service.service.AdminService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/adminApi")
public class AdminApi {
    @Resource
    private AdminService adminService;

    @PostMapping("getAdminIdByToken")
    public String getAdminIdByToken(){
        return adminService.getAdminIdByToken();
    }
}
