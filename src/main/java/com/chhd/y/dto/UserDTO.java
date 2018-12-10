package com.chhd.y.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

public class UserDTO implements Serializable {

    /**
     * 用户id
     */
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 身份验证
     */
    private String token;

    /**
     * 头像
     */
    private String icon;

    /**
     * 手机号码
     */
    private Long tel;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 用户角色，0=管理员，1=普通用户
     */
    private Integer role;

    private String roleName;

    /**
     * 状态，0=开启，1=禁用
     */
    private Integer disable;

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

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Integer getDisable() {
        return disable;
    }

    public void setDisable(Integer disable) {
        this.disable = disable;
    }
}
