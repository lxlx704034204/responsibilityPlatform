package com.orbit.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * 所有entity基类,封装基本信息
 */
@MappedSuperclass
public abstract class BaseEntity {

  /**
   * 本系统的model id都是自动生成的序列值
   */
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "ID", nullable = false)
  protected Long id;

  /**
   * 由于涉及到ETL,本系统中保存原有系统中model的id,便于在数据不一致的时候比较数据一致性
   */
  @Column(name = "ORIGIN_ID")
  protected Long originId;
}
