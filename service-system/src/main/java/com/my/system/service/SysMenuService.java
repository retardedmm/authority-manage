package com.my.system.service;

import com.my.model.system.SysMenu;
import com.baomidou.mybatisplus.extension.service.IService;
import com.my.model.vo.AssginMenuVo;
import com.my.model.vo.RouterVo;

import java.util.List;

/**
 * <p>
 * 菜单表 服务类
 * </p>
 *
 * @author ztl
 * @since 2023-05-08
 */
public interface SysMenuService extends IService<SysMenu> {

    List<SysMenu> findMenu();

    void removeMenu(Long id);

    List<SysMenu> findSysMenuByRoleId(Long roleId);

    void doAssign(AssginMenuVo assginMenuVo);

    List<RouterVo> getUserMenuById(Long id);

    List<String> getUserButtonById(Long id);
}
