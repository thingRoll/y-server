package com.chhd.y.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

public class UserDto implements Serializable {

    /**
     * 用户id
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long id;

    /**
     * 用户名
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String username;

    /**
     * 身份验证
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String token;

    /**
     * 头像
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String icon;

    /**
     * 手机号码
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long tel;

    /**
     * 邮箱
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String email;

    /**
     * 用户角色，0=管理员，1=普通用户
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer role;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Long getTel() {
        return tel;
    }

    public void setTel(Long tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

}
