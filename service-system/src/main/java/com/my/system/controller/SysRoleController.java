package com.my.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.my.common.result.Result;
import com.my.model.system.SysRole;
import com.my.model.vo.AssginRoleVo;
import com.my.model.vo.SysRoleQueryVo;
import com.my.system.service.SysRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Api(tags = "角色管理接口")
@RestController
@RequestMapping("/admin/system/sysRole")
public class SysRoleController {
    @Autowired
    SysRoleService sysRoleService;

    @ApiOperation("根据id批量删除权限用户")
    @DeleteMapping("deleteMore")
    public Result deleteMoreRole(@RequestBody List<Long> ids){
        boolean b = sysRoleService.removeByIds(ids);
        if (b){
            return Result.ok();
        }else{
            return Result.fail();
        }
    }

    @ApiOperation("修改权限用户")
    @PutMapping("update")
    public Result updateRole(@RequestBody SysRole sysRole){
        boolean b = sysRoleService.updateById(sysRole);
        if (b){
            return Result.ok();
        }else{
            return Result.fail();
        }
    }


    @ApiOperation("添加角色")
    @PostMapping("addRole")
    //加上RequestBody则表示前端使用json格式传递数据
    public Result addRole(@RequestBody SysRole sysRole){
        boolean save = sysRoleService.save(sysRole);
        if(save){
            return Result.ok();
        }else {
            return Result.fail();
        }
    }


    @ApiOperation("通过分页查询权限角色信息")
    @GetMapping("{page}/{limit}")
    public Result queryRoleByPage(@PathVariable long page, @PathVariable long limit, SysRoleQueryVo sysRoleQueryVo){
        Page<SysRole> page1=new Page<>(page,limit);
        IPage<SysRole> page2=sysRoleService.selectPage(page1,sysRoleQueryVo);
        return Result.ok(page2);
    }


    //删除权限角色信息
    @ApiOperation("删除权限角色信息")
    @DeleteMapping("remove/{id}")
    public Result removeOne(@PathVariable("id") String id){
        boolean b = sysRoleService.removeById(id);
        if(b){
           return Result.ok();
        }
        return Result.fail();
    }


    //查询所有权限角色信息
    @ApiOperation("查询所有权限角色信息")
    @GetMapping("findAll")
    public Result<List<SysRole>> findAllRole(){

        List<SysRole> list = sysRoleService.list();
        return Result.ok(list);
    }

    //根据id查询用户
    @ApiOperation("根据id查询用户数据")
    @GetMapping("findOne/{id}")
    public Result<SysRole> findOneRole(@PathVariable("id") long id){
        SysRole byId = sysRoleService.getById(id);
        return Result.ok(byId);
    }

    @ApiOperation("根据用户id查询角色信息")
    @GetMapping("getRoleByUserId/{userId}")
    public Result getRoleByUserId(@PathVariable Long userId ){
        //map集合存放两个信息：
        // 1.所有权限角色信息
        // 2.此id的用户拥有的权限角色信息
        Map<String ,Object> map=sysRoleService.getRoleByUserId(userId);
        return Result.ok(map);
    }

    @ApiOperation(value = "根据用户分配角色")
    @PostMapping("/doAssign")
    public Result doAssign(@RequestBody AssginRoleVo assginRoleVo) {
        sysRoleService.doAssign(assginRoleVo);
        return Result.ok();
    }

}






