package com.my.system.test;

import com.my.model.system.SysRole;
import com.my.system.mapper.SysRoleMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class test {
    @Autowired
    SysRoleMapper sysRoleMapper;
    @Test
    void testSelect(){
        List<SysRole> sysRoles = sysRoleMapper.selectList(null);
        sysRoles.forEach(list->System.out.println(list));
    }
}