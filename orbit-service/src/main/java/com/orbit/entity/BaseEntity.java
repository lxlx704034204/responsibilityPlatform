package com.orbit.entity;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * 所有entity基类,封装基本信息
 */
@MappedSuperclass
public abstract class BaseEntity {

  @Id
  protected Long id;
}
