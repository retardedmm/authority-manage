package com.my.system.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.my.common.result.Result;
import com.my.common.utils.MD5;
import com.my.model.system.SysRole;
import com.my.model.system.SysUser;
import com.my.model.vo.SysRoleQueryVo;
import com.my.model.vo.SysUserQueryVo;
import com.my.system.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author ztl
 * @since 2023-05-05
 */
@Api(tags = "用户管理接口")
@RestController
@RequestMapping("/admin/system/sysUser")
public class SysUserController {

    @Autowired
    SysUserService sysUserService;

    @ApiOperation("通过分页查询用户信息")
    @GetMapping("{page}/{limit}")
    public Result queryRoleByPage(@PathVariable long page, @PathVariable long limit, SysUserQueryVo sysUserQueryVo){
        Page<SysUser> page1=new Page<>(page,limit);
        IPage<SysUser> page2=sysUserService.selectPage(page1,sysUserQueryVo);
        return Result.ok(page2);
    }

    @ApiOperation("添加用户")
    @PostMapping("addUser")
    public Result addUser(@RequestBody SysUser sysUser){
        sysUser.setPassword(MD5.encrypt(sysUser.getPassword()));

        boolean save = sysUserService.save(sysUser);
        if(save){
            return Result.ok();
        }else{
            return Result.fail();
        }
    }

    @ApiOperation("根据id删除用户")
    @DeleteMapping("delete/{id}")
    public Result deleteUser(@PathVariable("id") Long id){
        boolean b = sysUserService.removeById(id);
        if(b){
            return Result.ok();
        }else{
            return Result.fail();
        }
    }

    @ApiOperation("根据id修改用户")
    @PutMapping("update")
    public Result updateUser(@RequestBody SysUser sysUser){
        boolean b = sysUserService.updateById(sysUser);
        if(b){
            return Result.ok();
        }else{
            return Result.fail();
        }
    }
    @ApiOperation("根据id查询用户")
    @GetMapping("findOne/{id}")
    public Result<SysUser> findUser(@PathVariable("id") Long id){
        SysUser byId = sysUserService.getById(id);
        return Result.ok(byId);
    }

    @ApiOperation("根据id批量删除用户")
    @DeleteMapping("deleteMore")
    public Result deleteMoreUser(@RequestBody List<Long> ids){
        boolean b = sysUserService.removeByIds(ids);
        if (b){
            return Result.ok();
        }else{
            return Result.fail();
        }
    }

    @ApiOperation("更改用户状态值")
    @PutMapping("/updateStatus/{id}/{status}")
    public Result updateStatus(@PathVariable Long id , @PathVariable Integer status){
        int i = sysUserService.updateStatus(id, status);
        if (i==1){
            return Result.ok();
        }else{
            return Result.fail();
        }

    }

}









