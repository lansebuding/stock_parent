package com.itheima.stock.controller;

import com.itheima.stock.pojo.entity.SysUser;
import com.itheima.stock.service.impl.UserServiceImpl;
import com.itheima.stock.vo.req.LoginReqVo;
import com.itheima.stock.vo.resp.LoginRespVo;
import com.itheima.stock.vo.resp.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/user/{name}")
    SysUser getSysuserByName(@PathVariable String name){
        return userService.getSysUserByName(name);
    }

    @PostMapping("/login")
    R<LoginRespVo> getUser(@RequestBody LoginReqVo reqVo){
        return userService.getUser(reqVo);
    }
}
