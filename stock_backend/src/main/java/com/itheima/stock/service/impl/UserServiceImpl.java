package com.itheima.stock.service.impl;

import com.itheima.stock.mapper.SysUserMapper;
import com.itheima.stock.pojo.entity.SysUser;
import com.itheima.stock.service.UserService;
import com.itheima.stock.vo.req.LoginReqVo;
import com.itheima.stock.vo.resp.LoginRespVo;
import com.itheima.stock.vo.resp.R;
import com.itheima.stock.vo.resp.ResponseCode;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private SysUserMapper userMapper;

    @Override
    public SysUser getSysUserByName(String userName) {
        return userMapper.getByUsername(userName);
    }

    @Override
    public R<LoginRespVo> getUser(LoginReqVo reqVo) {
        if (Objects.isNull(reqVo)) {
            return R.error(ResponseCode.DATA_ERROR);
        }
        String username = reqVo.getUsername();
        String password = reqVo.getPassword();
        String code = reqVo.getCode();
        if (StringUtils.isBlank(username) || StringUtils.isBlank(password) || StringUtils.isBlank(code)) {
            return R.error(ResponseCode.DATA_ERROR);
        }
        SysUser user = userMapper.getByUsername(username);
        if (Objects.isNull(user)) {
            return R.error(ResponseCode.ACCOUNT_NOT_EXISTS);
        }
        if (!passwordEncoder.matches(password, user.getPassword())) {
            return R.error(ResponseCode.USERNAME_OR_PASSWORD_ERROR);
        }
        LoginRespVo respVo = new LoginRespVo();
        BeanUtils.copyProperties(user, respVo);
        return R.ok(respVo);
    }
}
