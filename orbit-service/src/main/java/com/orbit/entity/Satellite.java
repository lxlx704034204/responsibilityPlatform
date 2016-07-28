package com.orbit.entity;

import com.orbit.entity.permission.User;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * 型号
 */
@Entity
public class Satellite extends BaseEntity {

  /**
   * 名称
   */
  private String name;

  /**
   * 责任人
   */
  @ManyToOne
  @JoinColumn(name = "ADMIN_ID", referencedColumnName = "ID")
  private User adminUser;

  protected Satellite() {
  }

  public Satellite(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public User getAdminUser() {
    return adminUser;
  }

  public void setAdminUser(User adminUser) {
    this.adminUser = adminUser;
  }

  @Override
  public String toString() {
    return String.format(
            "Satellite[id=%d, name='%s',责任人=%s]",
            id, name, adminUser);
  }
}
