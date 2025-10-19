package com.itheima.stock.vo.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author by itheima
 * @Date 2021/12/30
 * @Description 登录请求vo
 */
@Data
@ApiModel(description = "登录请求信息")
public class LoginReqVo {
    /**
     * 用户名
     */
    @ApiModelProperty("用户名")
    private String username;
    /**
     * 密码
     */
    @ApiModelProperty("密码")
    private String password;
    /**
     * sessionId
     */
    @ApiModelProperty("sessionId")
    private String sessionId;
    /**
     * 验证码
     */
    @ApiModelProperty("验证码")
    private String code;
}