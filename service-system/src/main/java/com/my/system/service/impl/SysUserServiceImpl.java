package com.my.system.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.my.model.system.SysUser;
import com.my.model.vo.RouterVo;
import com.my.model.vo.SysUserQueryVo;
import com.my.system.mapper.SysUserMapper;
import com.my.system.service.SysMenuService;
import com.my.system.service.SysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author ztl
 * @since 2023-05-05
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Autowired
    SysMenuService sysMenuService;

    @Override
    public IPage<SysUser> selectPage(Page<SysUser> page1, SysUserQueryVo sysUserQueryVo) {
        IPage<SysUser> e = baseMapper.selectByPage(page1, sysUserQueryVo);
        return e;
    }

    @Override
    public int updateStatus(Long id , Integer status) {
        SysUser sysUser = baseMapper.selectById(id);
        sysUser.setStatus(status);
        int i = baseMapper.updateById(sysUser);
        return i;
    }


    //username查询
    @Override
    public SysUser getUserInfoByUserName(String username) {
        QueryWrapper<SysUser> wrapper = new QueryWrapper<>();
        wrapper.eq("username",username);
        return baseMapper.selectOne(wrapper);
    }

    @Override
    public Map<String, Object> getUserInfo(String username) {
        //获取用户基本信息
        SysUser sysUser = getUserInfoByUserName(username);

        //根据userid查询菜单权限值
        List<RouterVo> listMenu=sysMenuService.getUserMenuById(sysUser.getId());

        //根据userid查询按钮权限值
        List<String> listButton=sysMenuService.getUserButtonById(sysUser.getId());

        Map<String,Object> map =new HashMap<>();
        map.put("name",username);
        map.put("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        map.put("roles","[\"admin\"]");
        //菜单权限数据
        map.put("routers",listMenu);
        //按钮权限数据
        map.put("buttons",listButton);
        return map;
    }


}
