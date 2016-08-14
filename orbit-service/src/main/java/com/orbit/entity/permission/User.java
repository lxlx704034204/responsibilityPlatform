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
  @Column(name = "FULL_NAME")
  private String fullName;

  /**
   * 登录系统的账号
   */
  @Column(name = "LOGIN_NAME", nullable = false)
  private String loginName;

  /**
   * 邮箱
   */
  private String email;

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

  public User(String loginName) {
    this.loginName = loginName;
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
    return loginName;
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

  public String getLoginName() {
    return loginName;
  }

  public void setLoginName(String loginName) {
    this.loginName = loginName;
  }

  /**
   * email
   */
  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
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
            "User[loginName=%d, email='%s',fullName=%s]",
            getId(), this.loginName, this.email == null ? "" : email, fullName == null ? "" : fullName);
  }
}
