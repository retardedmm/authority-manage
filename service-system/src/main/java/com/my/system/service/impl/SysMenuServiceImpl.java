package com.my.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.my.model.system.SysMenu;
import com.my.model.system.SysRoleMenu;
import com.my.model.vo.AssginMenuVo;
import com.my.model.vo.RouterVo;
import com.my.system.excpetion.SpecificException;
import com.my.system.mapper.SysMenuMapper;
import com.my.system.mapper.SysRoleMenuMapper;
import com.my.system.service.SysMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.my.system.utils.DateForMenu;
import com.my.system.utils.RouterHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 菜单表 服务实现类
 * </p>
 *
 * @author ztl
 * @since 2023-05-08
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {


    @Autowired
    SysRoleMenuMapper sysRoleMenuMapper;


    /**
     * 因为前端封装的组件采用树形结构
     * 所以需要返回固定的数据格式
     * @return
     */
    @Override
    public List<SysMenu> findMenu() {
        //先查询出所有的菜单
        List<SysMenu> sysMenus = baseMapper.selectList(null);

        //封装一个工具类,返回指定类型的数据格式
        List<SysMenu> resultList=DateForMenu.toTree(sysMenus);

        return resultList;
    }

    @Override
    public void removeMenu(Long id) {
        QueryWrapper<SysMenu> wrapper=new QueryWrapper<>();
        wrapper.eq("parent_id",id);
        Integer integer = baseMapper.selectCount(wrapper);
        if(integer>1){
            throw new SpecificException(201,"请先删除子菜单");
        }
        baseMapper.deleteById(id);
    }


    //获取已经给角色分配后的菜单
    @Override
    public List<SysMenu> findSysMenuByRoleId(Long roleId) {
        //获取所有状态码为1的菜单，1为可用，0为不可用
        QueryWrapper<SysMenu> wrapperMenu=new QueryWrapper<>();
        wrapperMenu.eq("status",1);
        List<SysMenu> sysMenus = baseMapper.selectList(wrapperMenu);

        //查询该角色id拥有的菜单
        QueryWrapper<SysRoleMenu> wrapperRoleMenu=new QueryWrapper<>();
        wrapperRoleMenu.eq("role_id",roleId);
        List<SysRoleMenu> sysRoleMenus = sysRoleMenuMapper.selectList(wrapperRoleMenu);

        //获取已经分配的所有id集合
        List<Long> ids=new ArrayList<>();
        for (SysRoleMenu sysRoleMenu : sysRoleMenus) {
            ids.add(sysRoleMenu.getMenuId());
        }

        //将已分配的菜单的select设置为true
        for(SysMenu sysMenu: sysMenus){
            if(ids.contains(sysMenu.getId())){
                sysMenu.setSelect(true);
            }else {
                sysMenu.setSelect(false);
            }
        }

        //将权限列表转换为权限树
        List<SysMenu> sysMenusNew = DateForMenu.toTree(sysMenus);


        return sysMenusNew;
    }

    @Override
    public void doAssign(AssginMenuVo assginMenuVo) {
        //删除已经分配的菜单
        sysRoleMenuMapper.delete(new QueryWrapper<SysRoleMenu>().eq("role_id",assginMenuVo.getRoleId()));

        //添加新的分配菜单
        List<Long> ids=assginMenuVo.getMenuIdList();
        for(Long id : ids){
            SysRoleMenu sysRoleMenu = new SysRoleMenu();
            sysRoleMenu.setRoleId(assginMenuVo.getRoleId());
            sysRoleMenu.setMenuId(id);
            sysRoleMenuMapper.insert(sysRoleMenu);
        }

    }

    @Override
    public List<RouterVo> getUserMenuById(Long id) {
        List<SysMenu> sysMenuList=null;

        if(id==1){
            sysMenuList=baseMapper.selectList(new QueryWrapper<SysMenu>().eq("status",1).orderByAsc("sort_value"));
        }else{
            sysMenuList=baseMapper.findMenuListUserId(id);
        }
        List<SysMenu> sysMenuListTree = DateForMenu.toTree(sysMenuList);
        List<RouterVo> routerVos = RouterHelper.buildRouters(sysMenuListTree);

        return routerVos;
    }

    @Override
    public List<String> getUserButtonById(Long id) {
        List<SysMenu> sysMenuList=null;
        if(id==1){
            sysMenuList=baseMapper.selectList(new QueryWrapper<SysMenu>().eq("status",1).orderByAsc("sort_value"));
        }else{
            sysMenuList=baseMapper.findMenuListUserId(id);
        }
        List<String> list=new ArrayList<>();
        for(SysMenu sysMenu:sysMenuList){
            if(sysMenu.getType()==2){
                list.add(sysMenu.getPerms());
            }
        }


        return list;
    }
}





