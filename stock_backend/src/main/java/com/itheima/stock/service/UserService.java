package com.itheima.stock.service;

import com.itheima.stock.pojo.entity.SysUser;
import com.itheima.stock.vo.req.LoginReqVo;
import com.itheima.stock.vo.resp.LoginRespVo;
import com.itheima.stock.vo.resp.R;

import java.util.Map;

public interface UserService {
    SysUser getSysUserByName(String userName);
    R<LoginRespVo> getUser(LoginReqVo reqVo);

    /**
     * 前端获取验证码图片
     * @return
     */
    R<Map<String, String>> getCaptcha();
}
