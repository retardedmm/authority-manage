package com.my.system.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.my.model.system.SysUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.my.model.vo.SysUserQueryVo;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author ztl
 * @since 2023-05-05
 */
public interface SysUserMapper extends BaseMapper<SysUser> {
    IPage<SysUser> selectByPage(Page<SysUser> page1, @Param("vo") SysUserQueryVo sysUserQueryVo);
}
