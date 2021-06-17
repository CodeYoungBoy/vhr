package com.wbj.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author 王兵杰
 * @date 2021/5/23
 */


@ApiModel("相应数据")
public class Result {
    @ApiModelProperty(value = "响应状态码")
    private Integer code;
    private String msg;
    private Object data;
    @ApiModelProperty(value = "过期时间")
    private Integer expiration;

    public static Result build() {
        return new Result();
    }

    public static Result ok(String msg) {
        return new Result(200, msg, null);
    }

    public static Result ok(String msg, Object data) {
        return new Result(200, msg, data);
    }
    public static Result ok(String msg, Object data,Integer expiration) {
        return new Result(200, msg, data,expiration);
    }
    public static Result error(String msg) {
        return new Result(500, msg, null);
    }

    public static Result error(String msg, Object data) {
        return new Result(500, msg, data);
    }

    private Result() {
    }

    private Result(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
    private Result(Integer code, String msg, Object data,Integer expiration) {
        this.code = code;
        this.msg = msg;
        this.data = data;
        this.expiration=expiration;
    }
    public Integer getCode() {
        return code;
    }

    public Result setCode(Integer code) {
        this.code = code;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public Result setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public Object getData() {
        return data;
    }

    public Result setData(Object data) {
        this.data = data;
        return this;
    }
    public Integer getExpiration(){
        return expiration;
    }
    public Result setExpiration(){
        this.expiration=expiration;
        return this;
    }
}
