package com.chhd.y.common;

public enum ResponseCode {

    SUCCESS(0, "操作成功"),
    ERROR(1, "操作失败"),
    INVALID_ARGUMENT(2, "请求参数错误"),
    INVALID_HEADER(3, "os/device/token任一个header参数不能为空"),
    INVALID_TOKEN(4, "token过期，请重新登录"),
    INVALID_PERMISSION(5, "当前角色暂无权限操作");

    private final int code;
    private final String desc;

    ResponseCode(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
