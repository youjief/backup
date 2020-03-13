package com.demo.cn.common.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 2019/11/17
 * Time: 13:54
 * Description: No Description
 */
@Data
public class ContactVo implements Serializable {

    @ApiModelProperty(value = "联系人名字", required = true)
    private String cname;
    @ApiModelProperty(value = "手机号")
    private String mobile;
    @ApiModelProperty(value = "QQ")
    private String qq;
    @ApiModelProperty(value = "邮箱")
    private String email;
    @ApiModelProperty(value = "地址")
    private String address;
}
