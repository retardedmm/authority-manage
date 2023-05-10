package com.my.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.my.common.result.Result;
import com.my.common.utils.JwtHelper;
import com.my.common.utils.MD5;
import com.my.model.system.SysUser;
import com.my.model.vo.LoginVo;
import com.my.system.excpetion.SpecificException;
import com.my.system.service.SysUserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Api(tags = "用户登录")
@RestController
@RequestMapping("/admin/system/index")
public class IndexController {

    @Autowired
    SysUserService sysUserService;

    //{"code":20000,"data":{"token":"admin-token"}}
    @PostMapping("login")
    public Result login(){

        Map<String, Object> map = new HashMap<>();
        map.put("token","admin");
        return Result.ok(map);




        /*//查询数据库，用户名是否存在
        SysUser sysUser = sysUserService.getOne(new QueryWrapper<SysUser>().eq("username", loginVo.getUsername()));
        if(sysUser==null){
            throw new SpecificException(20001,"用户名不存在");
        }
        //验证密码是否正确
        if(!sysUser.getPassword().equals(MD5.encrypt(loginVo.getPassword()))){
            throw new SpecificException(20001,"密码错误");
        }

        //加密并返回给前端token
        Map<String,Object> map =new HashMap<>();
        map.put("token",JwtHelper.createToken(sysUser.getId(),sysUser.getName()));
        return Result.ok(map);*/
    }

    //{"code":20000,
    // "data":{"roles":["admin"],
    // "introduction":"I am a super administrator",
    // "avatar":"https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif",
    // "name":"Super Admin"}}
    @GetMapping("info")
    public Result info(){
        /*Map<String,Object> map=new HashMap<>();
        map.put("roles","[admin]");
        map.put("introduction","I am a super administrator");
        map.put("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        map.put("name","Super Admin");
        return Result.ok(map);*/

        Map<String, Object> map = new HashMap<>();
        map.put("roles","[admin]");
        map.put("name","admin");
        map.put("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        return Result.ok(map);


       /*//获取请求头
        String token = request.getHeader("token");
        //从token字符串解密得到用户名称
        String username = JwtHelper.getUsername(token);
        Map<String,Object> map=sysUserService.getUserInfo(username);
        return Result.ok(map);
*/
    }

}




