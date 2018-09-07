package com.chhd.y.common;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Response<T> {

    private int status;
    private String msg;
    private T data;

    private Response(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    private Response(int status, String msg, T data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public String getMsg() {
        return msg;
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

    public static Response createByInvalidToken() {
        return new Response(ResponseCode.INVALID_TOKEN.getCode(), ResponseCode.INVALID_TOKEN.getDesc());
    }
}
