package com.wbj.entity;

import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 *
 * </p>
 *
 * @author wbj
 * @since 2021-06-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="MailSendLog对象", description="")
public class MailSendLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("msgId")
    private String msgId;

    @TableField("empId")
    private Integer empId;

    @ApiModelProperty(value = "0发送中，1发送成功，2发送失败")
    private Integer status;

    @TableField("routeKey")
    private String routeKey;

    private String exchange;

    @ApiModelProperty(value = "重试次数")
    private Integer count;

    @ApiModelProperty(value = "第一次重试时间")
    @TableField("tryTime")
    private LocalDateTime tryTime;

    @TableField("createTime")
    private LocalDateTime createTime;

    @TableField("updateTime")
    private LocalDateTime updateTime;


}
