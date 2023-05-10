package com.my.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.my.model.system.SysRole;
import com.my.model.system.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;
import com.my.model.vo.SysUserQueryVo;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author ztl
 * @since 2023-05-05
 */
@Service
public interface SysUserService extends IService<SysUser> {
    IPage<SysUser> selectPage(Page<SysUser> page1, SysUserQueryVo sysUserQueryVo);

    int updateStatus(Long id , Integer status);

    SysUser getUserInfoByUserName(String username);

    Map<String, Object> getUserInfo(String username);
}
