package com.my.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.my.model.system.SysRole;
import com.my.model.vo.AssginRoleVo;
import com.my.model.vo.SysRoleQueryVo;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface SysRoleService extends IService<SysRole> {
    IPage<SysRole> selectPage(Page<SysRole> page1, SysRoleQueryVo sysRoleQueryVo);

    Map<String, Object> getRoleByUserId(Long userId);

    void doAssign(AssginRoleVo assginRoleVo);
}
