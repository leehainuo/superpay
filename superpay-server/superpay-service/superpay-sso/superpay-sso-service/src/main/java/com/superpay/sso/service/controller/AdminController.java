package com.superpay.sso.service.controller;

import com.superpay.common.Response;
import com.superpay.sso.model.dto.AdminLoginDTO;
import com.superpay.sso.service.service.AdminService;
import com.superpay.sso.model.vo.AdminLoginVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 管理员表 前端控制器
 * </p>
 *
 * @author lihainuo
 * @since 2024-10-27
 */
@Slf4j
@Tag(name = "管理员表")
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Resource
    private AdminService adminService;

    @Operation(summary = "开发者测试接口")
    @PostMapping("/test")
    public Response<String> test(){
        return Response.success("hello sso");
    }

    @Operation(summary = "管理员登录")
    @PostMapping("/login")
    public Response<AdminLoginVO> login(@RequestBody AdminLoginDTO adminLoginDTO){
        log.info("管理员登录:{}",adminLoginDTO);
        AdminLoginVO adminLoginVO= adminService.login(adminLoginDTO);
        return Response.success(adminLoginVO);
    }
}
