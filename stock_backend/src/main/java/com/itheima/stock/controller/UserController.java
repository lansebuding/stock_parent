package com.itheima.stock.controller;

import com.itheima.stock.pojo.entity.SysUser;
import com.itheima.stock.service.impl.UserServiceImpl;
import com.itheima.stock.vo.req.LoginReqVo;
import com.itheima.stock.vo.resp.LoginRespVo;
import com.itheima.stock.vo.resp.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
@Api(tags = "用户登录控制器")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/user/{name}")
    @ApiOperation("查询用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "用户名", required = true, paramType = "path")
    })
    SysUser getSysuserByName(@PathVariable String name) {
        return userService.getSysUserByName(name);
    }

    @PostMapping("/login")
    @ApiOperation("用户登录")
    R<LoginRespVo> getUser(@RequestBody LoginReqVo reqVo) {
        return userService.getUser(reqVo);
    }

    @GetMapping("/captcha")
    @ApiOperation("获取验证码")
    R<Map<String, String>> getCaptcha() {
        return userService.getCaptcha();
    }
}
