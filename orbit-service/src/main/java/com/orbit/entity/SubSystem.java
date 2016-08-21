package com.orbit.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 分系统
 */
@Entity
@Table(name = "subsystem")
public class SubSystem extends BaseEntity {

  @Column(nullable = false, length = 100)
  private String name;

  /**
   * 分系统名称
   */
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
