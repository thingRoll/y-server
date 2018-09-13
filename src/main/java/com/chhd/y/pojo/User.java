package com.chhd.y.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * user
 * @author 
 */
public class User implements Serializable {
    /**
     * 用户id
     */
    private Long id;

    /**
     * 登录账户，用户名，邮箱，手机
     */
    private String account;

    /**
     * 用户名
     */
    private String username;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 身份验证
     */
    private String token;

    /**
     * 身份验证id
     */
    private String tokenUid;

    /**
     * 头像
     */
    private String icon;

    /**
     * 用户角色，0=管理员，1=普通用户，2=会员
     */
    private Integer role;

    /**
     * 手机号码
     */
    private Long tel;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 绑定邮箱的验证码
     */
    private Integer emailCode;

    /**
     * 邮箱状态，0=未激活，1=激活
     */
    private Integer emailState;

    /**
     * 绑定手机的验证码
     */
    private Integer telCode;

    /**
     * 手机状态，0=未激活，1=激活
     */
    private Integer telState;

    /**
     * 状态，0=开启，1=禁用
     */
    private Integer disable;

    /**
     * 浏览平台，0=网页，1=安卓
     */
    private Integer os;

    /**
     * 网页，记录浏览器名称，安卓，记录设备名称
     */
    private String device;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTokenUid() {
        return tokenUid;
    }

    public void setTokenUid(String tokenUid) {
        this.tokenUid = tokenUid;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
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

    public Integer getEmailCode() {
        return emailCode;
    }

    public void setEmailCode(Integer emailCode) {
        this.emailCode = emailCode;
    }

    public Integer getEmailState() {
        return emailState;
    }

    public void setEmailState(Integer emailState) {
        this.emailState = emailState;
    }

    public Integer getTelCode() {
        return telCode;
    }

    public void setTelCode(Integer telCode) {
        this.telCode = telCode;
    }

    public Integer getTelState() {
        return telState;
    }

    public void setTelState(Integer telState) {
        this.telState = telState;
    }

    public Integer getDisable() {
        return disable;
    }

    public void setDisable(Integer disable) {
        this.disable = disable;
    }

    public Integer getOs() {
        return os;
    }

    public void setOs(Integer os) {
        this.os = os;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        User other = (User) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getAccount() == null ? other.getAccount() == null : this.getAccount().equals(other.getAccount()))
            && (this.getUsername() == null ? other.getUsername() == null : this.getUsername().equals(other.getUsername()))
            && (this.getPassword() == null ? other.getPassword() == null : this.getPassword().equals(other.getPassword()))
            && (this.getToken() == null ? other.getToken() == null : this.getToken().equals(other.getToken()))
            && (this.getTokenUid() == null ? other.getTokenUid() == null : this.getTokenUid().equals(other.getTokenUid()))
            && (this.getIcon() == null ? other.getIcon() == null : this.getIcon().equals(other.getIcon()))
            && (this.getRole() == null ? other.getRole() == null : this.getRole().equals(other.getRole()))
            && (this.getTel() == null ? other.getTel() == null : this.getTel().equals(other.getTel()))
            && (this.getEmail() == null ? other.getEmail() == null : this.getEmail().equals(other.getEmail()))
            && (this.getEmailCode() == null ? other.getEmailCode() == null : this.getEmailCode().equals(other.getEmailCode()))
            && (this.getEmailState() == null ? other.getEmailState() == null : this.getEmailState().equals(other.getEmailState()))
            && (this.getTelCode() == null ? other.getTelCode() == null : this.getTelCode().equals(other.getTelCode()))
            && (this.getTelState() == null ? other.getTelState() == null : this.getTelState().equals(other.getTelState()))
            && (this.getDisable() == null ? other.getDisable() == null : this.getDisable().equals(other.getDisable()))
            && (this.getOs() == null ? other.getOs() == null : this.getOs().equals(other.getOs()))
            && (this.getDevice() == null ? other.getDevice() == null : this.getDevice().equals(other.getDevice()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getAccount() == null) ? 0 : getAccount().hashCode());
        result = prime * result + ((getUsername() == null) ? 0 : getUsername().hashCode());
        result = prime * result + ((getPassword() == null) ? 0 : getPassword().hashCode());
        result = prime * result + ((getToken() == null) ? 0 : getToken().hashCode());
        result = prime * result + ((getTokenUid() == null) ? 0 : getTokenUid().hashCode());
        result = prime * result + ((getIcon() == null) ? 0 : getIcon().hashCode());
        result = prime * result + ((getRole() == null) ? 0 : getRole().hashCode());
        result = prime * result + ((getTel() == null) ? 0 : getTel().hashCode());
        result = prime * result + ((getEmail() == null) ? 0 : getEmail().hashCode());
        result = prime * result + ((getEmailCode() == null) ? 0 : getEmailCode().hashCode());
        result = prime * result + ((getEmailState() == null) ? 0 : getEmailState().hashCode());
        result = prime * result + ((getTelCode() == null) ? 0 : getTelCode().hashCode());
        result = prime * result + ((getTelState() == null) ? 0 : getTelState().hashCode());
        result = prime * result + ((getDisable() == null) ? 0 : getDisable().hashCode());
        result = prime * result + ((getOs() == null) ? 0 : getOs().hashCode());
        result = prime * result + ((getDevice() == null) ? 0 : getDevice().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", account=").append(account);
        sb.append(", username=").append(username);
        sb.append(", password=").append(password);
        sb.append(", token=").append(token);
        sb.append(", tokenUid=").append(tokenUid);
        sb.append(", icon=").append(icon);
        sb.append(", role=").append(role);
        sb.append(", tel=").append(tel);
        sb.append(", email=").append(email);
        sb.append(", emailCode=").append(emailCode);
        sb.append(", emailState=").append(emailState);
        sb.append(", telCode=").append(telCode);
        sb.append(", telState=").append(telState);
        sb.append(", disable=").append(disable);
        sb.append(", os=").append(os);
        sb.append(", device=").append(device);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}