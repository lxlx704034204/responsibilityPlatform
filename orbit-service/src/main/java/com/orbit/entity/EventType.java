package com.orbit.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 测控事件类型
 */
@Entity
@Table(name = "eventtype")
public class EventType extends BaseEntity {

  private String name;

  protected EventType() {
  }

  public EventType(String name) {
    this.name = name;
  }

  /**
   * 测控事件名称
   */
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
