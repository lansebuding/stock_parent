package com.itheima.stock.service.impl;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.GifCaptcha;
import cn.hutool.captcha.LineCaptcha;
import com.itheima.stock.constant.StockConstant;
import com.itheima.stock.mapper.SysUserMapper;
import com.itheima.stock.pojo.entity.SysUser;
import com.itheima.stock.service.UserService;
import com.itheima.stock.utils.IdWorker;
import com.itheima.stock.vo.req.LoginReqVo;
import com.itheima.stock.vo.resp.LoginRespVo;
import com.itheima.stock.vo.resp.R;
import com.itheima.stock.vo.resp.ResponseCode;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private IdWorker idWorker;

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
        String sessionId = reqVo.getSessionId();
        // 根据session查询redis
        Object o = redisTemplate.opsForValue().get(StockConstant.CHECK_PREFIX + sessionId);
        log.info("当前传入的会话ID是：{}，传入的验证码是：{}", sessionId, code);
        if (Objects.isNull(o)) {
            return R.error(ResponseCode.CHECK_CODE_TIMEOUT);
        }
        // 比较时忽略大小写
        if (!Objects.equals(o.toString().toLowerCase(), code.toLowerCase())) {
            return R.error(ResponseCode.CHECK_CODE_ERROR);
        }
        // 查询数据库
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

    /**
     * 实现前端获取验证码功能
     */
    @Override
    public R<Map<String, String>> getCaptcha() {
        LineCaptcha captcha = CaptchaUtil.createLineCaptcha(250, 40, 4, 5);
        // 设置详细参数
        captcha.setBackground(Color.LIGHT_GRAY);
        String code = captcha.getCode();
        String sessionId = String.valueOf(idWorker.nextId());
        redisTemplate.opsForValue().set(StockConstant.CHECK_PREFIX + sessionId, code, 3, TimeUnit.MINUTES);
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("imageData", captcha.getImageBase64());
        hashMap.put("sessionId", sessionId);
        log.info("当前会话ID是：{}，生成的验证码是：{}", sessionId, code);
        return R.ok(hashMap);
    }
}
