package com.orbit.entity.permission;

import com.orbit.entity.BaseEntity;
import com.orbit.entity.Satellite;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

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
    return null;
  }

  @Override
  public String getUsername() {
    return null;
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

  public List<Satellite> getSatellites() {
    return satellites;
  }

  public void setSatellites(List<Satellite> satellites) {
    this.satellites = satellites;
  }

  @Override
  public String toString() {
    return String.format(
            "Satellite[id=%d, loginName='%s',fullName=%s]",
            id, loginName, fullName);
  }
}
