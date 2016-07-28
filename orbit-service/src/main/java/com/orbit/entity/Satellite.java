package com.orbit.entity;

import javax.persistence.Entity;

/**
 * 型号
 */
@Entity
public class Satellite extends BaseEntity {

  private String name;

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
            id, name, "");
  }
}
