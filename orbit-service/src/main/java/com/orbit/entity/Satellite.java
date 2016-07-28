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

  private String name;

  @ManyToOne
  @JoinColumn(name = "ADMIN_ID", referencedColumnName = "ID")
  private User adminUser;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return String.format(
            "Satellite[id=%d, name='%s',责任人=%s]",
            id, name, adminUser);
  }

  public User getAdminUser() {
    return adminUser;
  }

  public void setAdminUser(User adminUser) {
    this.adminUser = adminUser;
  }
}
