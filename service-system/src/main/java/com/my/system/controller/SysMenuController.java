package com.my.system.controller;


import com.my.common.result.Result;
import com.my.model.system.SysMenu;
import com.my.model.vo.AssginMenuVo;
import com.my.system.service.SysMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 菜单表 前端控制器
 * </p>
 *
 * @author ztl
 * @since 2023-05-08
 */
@Api(tags = "菜单管理")
@RestController
@RequestMapping("/admin/system/sysMenu")
public class SysMenuController {
    @Autowired
    SysMenuService sysMenuService;

    @ApiOperation("通过id查找菜单")
    @GetMapping("findMenuById/{id}")
    public Result findMenuById(@PathVariable Long id){
        SysMenu byId = sysMenuService.getById(id);
        return Result.ok(byId);
    }


    @ApiOperation("添加菜单")
    @PostMapping("addMenu")
    public Result addMenu(@RequestBody SysMenu sysMenu){
        sysMenuService.save(sysMenu);
        return Result.ok();
    }

    @ApiOperation("删除菜单")
    @DeleteMapping("deleteMenu/{id}")
    public Result deleteMenu(@PathVariable Long id){
        sysMenuService.removeMenu(id);
        return Result.ok();
    }

    @ApiOperation("修改菜单")
    @PutMapping("updateMenu")
    public Result updateMenu(@RequestBody SysMenu sysMenu){
        sysMenuService.updateById(sysMenu);
        return Result.ok();
    }

    @ApiOperation("查找菜单")
    @GetMapping("findMenu")
    public Result findMenu(){
        List<SysMenu> list=sysMenuService.findMenu();
        return Result.ok(list);
    }

    @ApiOperation(value = "根据角色获取菜单")
    @GetMapping("/toAssign/{roleId}")
    public Result toAssign(@PathVariable Long roleId) {
        List<SysMenu> list = sysMenuService.findSysMenuByRoleId(roleId);
        return Result.ok(list);
    }

    @ApiOperation(value = "给角色分配权限")
    @PostMapping("/doAssign")
    public Result doAssign(@RequestBody AssginMenuVo assginMenuVo) {
        sysMenuService.doAssign(assginMenuVo);
        return Result.ok();
    }

}






