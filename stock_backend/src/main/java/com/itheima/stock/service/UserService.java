package com.itheima.stock.service;

import com.itheima.stock.pojo.entity.SysUser;
import com.itheima.stock.vo.req.LoginReqVo;
import com.itheima.stock.vo.resp.LoginRespVo;
import com.itheima.stock.vo.resp.R;

public interface UserService {
    SysUser getSysUserByName(String userName);
    R<LoginRespVo> getUser(LoginReqVo reqVo);
}
