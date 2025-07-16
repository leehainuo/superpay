package com.superpay.sso.service.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.superpay.sso.model.dto.AdminLoginDTO;
import com.superpay.sso.model.entity.Admin;
import com.superpay.sso.model.vo.AdminLoginVO;


/**
 * <p>
 * 管理员表 服务类
 * </p>
 *
 * @author lihainuo
 * @since 2024-10-27
 */
public interface AdminService extends IService<Admin> {

    AdminLoginVO login(AdminLoginDTO adminLoginDTO);

    String getAdminIdByToken();
}
