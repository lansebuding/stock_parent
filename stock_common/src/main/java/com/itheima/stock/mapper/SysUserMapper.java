package com.itheima.stock.mapper;

import com.itheima.stock.pojo.entity.SysUser;

/**
* @author Admin
* @description 针对表【sys_user(用户表)】的数据库操作Mapper
* @createDate 2025-10-10 21:42:40
* @Entity com.itheima.stock.pojo.entity.SysUser
*/
public interface SysUserMapper {

    int deleteByPrimaryKey(Long id);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    SysUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);

    SysUser getByUsername(String userName);
}
