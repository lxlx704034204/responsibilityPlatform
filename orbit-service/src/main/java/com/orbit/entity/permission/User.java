package com.orbit.entity.permission;

import com.orbit.entity.BaseEntity;
import com.orbit.entity.Satellite;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Converter;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.PreRemove;

/**
 * 用户
 */
@Entity
public class User extends BaseEntity implements UserDetails {

  /**
   * 用户姓名
   */
  @Column(name = "fullname")
  private String fullName;

  /**
   * 登录系统的账号
   */
  @Column(name = "username", nullable = false)
  private String userName;

  @Column(name = "isadmin", nullable = false)
  private boolean isAdmin = false;

  @Column(name = "isactive", nullable = false)
  private boolean isActive = true;

  @Column(name = "telePhoneNum", length = 50)
  private String telePhoneNum;

  @Column(name = "workPhoneNum", length = 50)
  private String workPhoneNum;

  @Column(length = 512)
  private String remark;


  /**
   * 密码,使用PasswordConverter解决透明加解密问题
   */
  @Basic(fetch = FetchType.EAGER)
  @Convert(converter = PasswordConverter.class)
  private String password = "";

  /**
   * 所负责的型号
   */
  @OneToMany(mappedBy = "adminUser", fetch = FetchType.LAZY)
  private List<Satellite> satellites;

  protected User() {
  }

  public User(String userName) {
    this.userName = userName;
  }

  //以下是UserDetails接口中规定的方法
  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return null;
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return userName;
  }

  @Override
  public boolean isAccountNonExpired() {
    return false;
  }

  @Override
  public boolean isAccountNonLocked() {
    return false;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return false;
  }

  @Override
  public boolean isEnabled() {
    return false;
  }

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  /**
   * 获取该责任者所负责的型号列表
   */
  public List<Satellite> getSatellites() {
    return satellites;
  }

  public void setSatellites(List<Satellite> satellites) {
    this.satellites = satellites;
  }

  /**
   * 添加负责的型号
   *
   * @param satellite 型号
   */
  public List<Satellite> addSatellite(Satellite satellite) {
    if (this.satellites == null) {
      this.satellites = new ArrayList<Satellite>(10);
    }
    this.satellites.add(satellite);

    return this.satellites;
  }

  /**
   * 删除user时,避免satellite的外键约束造成删除失败
   */
  @PreRemove
  private void preRemove() {
    if (satellites != null && satellites.size() > 0) {
      for (Satellite s : satellites) {
        s.setAdminUser(null);
      }
    }
  }

  public void setPassword(String password) {
    this.password = password;
  }

  @Override
  public String toString() {
    return String.format(
            "User[userName=%d,fullName=%s]",
            getId(), this.userName, fullName == null ? "" : fullName);
  }

  /**
   * 是否是管理员
   */
  public boolean isAdmin() {
    return isAdmin;
  }

  public void setAdmin(boolean admin) {
    isAdmin = admin;
  }

  /**
   * 是否活跃,已离职的为false
   */
  public boolean isActive() {
    return isActive;
  }

  public void setActive(boolean active) {
    isActive = active;
  }

  /**
   * 手机
   */
  public String getTelePhoneNum() {
    return telePhoneNum;
  }

  public void setTelePhoneNum(String telePhoneNum) {
    this.telePhoneNum = telePhoneNum;
  }

  /**
   * 工作电话
   */
  public String getWorkPhoneNum() {
    return workPhoneNum;
  }

  public void setWorkPhoneNum(String workPhoneNum) {
    this.workPhoneNum = workPhoneNum;
  }

  /**
   * 备注,紧急电话等
   */
  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }
}
