package com.ks.member.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * <p>
 * 
 * </p>
 *
 * @author dCoder
 * @since 2022-03-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="User对象", description="")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户唯一id")
    @TableId(value = "user_id", type = IdType.ID_WORKER_STR)
    private String userId;

    @NotBlank(message = "手机号不能为空")
    @ApiModelProperty(value = "用户手机号")
    private String userMobile;

    @NotBlank(message = "邮箱不能为空")
    @Email(message = "邮箱格式不正确")
    @ApiModelProperty(value = "用户邮箱")
    private String email;

    @ApiModelProperty(value = "用户密码")
    private String userPassword;

    @ApiModelProperty(value = "随机生成盐值")
    private String salt;

    @ApiModelProperty(value = "用户昵称")
    private String userNickname;

    @ApiModelProperty(value = "性别")
    private String userSex;

    @ApiModelProperty(value = "头像")
    private String userAvatar;

    @ApiModelProperty(value = "个性签名")
    private String userSign;

    @ApiModelProperty(value = "粉丝数")
    private Integer fans;

    @ApiModelProperty(value = "是否被禁用(0为正常，1为禁用)")
    private Integer isDisabled;

    @ApiModelProperty(value = "是否被删除(0为正常，1为删除)")
    private Integer isDeleted;

    @TableField(exist = false)
    List<String> labelIds;


}
