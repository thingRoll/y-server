package com.chhd.y.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "请求结果")
public class Response<T> {

    @ApiModelProperty(value = "状态码，0：成功，非0：失败")
    private int status;
    @ApiModelProperty(value = "信息")
    private String message;
    @ApiModelProperty(value = "数据")
    private T data;

    private Response(int status, String message) {
        this.status = status;
        this.message = message;
    }

    private Response(int status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }

    @JsonIgnore
    public boolean isSuccess() {
        return this.status == ResponseCode.SUCCESS.getCode();
    }

    public static Response createBySuccess() {
        return new Response(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getDesc());
    }

    public static <T> Response createBySuccess(T data) {
        return new Response<T>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getDesc(), data);
    }

    public static Response createByError() {
        return new Response(ResponseCode.ERROR.getCode(), ResponseCode.ERROR.getDesc());
    }

    public static Response createByError(String msg) {
        return new Response(ResponseCode.ERROR.getCode(), msg);
    }

    public static Response createByInvalidArgument() {
        return new Response(ResponseCode.INVALID_ARGUMENT.getCode(), ResponseCode.INVALID_ARGUMENT.getDesc());
    }

    public static Response createByInvalidArgument(String msg) {
        return new Response(ResponseCode.INVALID_ARGUMENT.getCode(), msg);
    }

    public static Response createByInvalidHeader() {
        return new Response(ResponseCode.INVALID_HEADER.getCode(), ResponseCode.INVALID_HEADER.getDesc());
    }

    public static Response createByInvalidHeader(String msg) {
        return new Response(ResponseCode.INVALID_HEADER.getCode(), msg);
    }

    public static Response createByInvalidToken() {
        return new Response(ResponseCode.INVALID_TOKEN.getCode(), ResponseCode.INVALID_TOKEN.getDesc());
    }

    public static Response createByInvalidPermission() {
        return new Response(ResponseCode.INVALID_PERMISSION.getCode(), ResponseCode.INVALID_PERMISSION.getDesc());
    }
}
