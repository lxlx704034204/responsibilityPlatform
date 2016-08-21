package com.orbit.entity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 * 所有entity基类,封装基本信息
 */
@MappedSuperclass
public abstract class BaseEntity {
  @Transient
  protected static final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(nullable = false)
  private Long id;

  @Column(name = "originid")
  private Long originId;

  /**
   * 上次修改时间
   */
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "lastModifedTime", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
  private Date lastModifedTime = new Date();

  /**
   * 本系统的model id都是自动生成的序列值
   */
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  /**
   * 由于涉及到ETL,本系统中保存原有系统中model的id,便于在数据不一致的时候比较哪些数据缺失,建议本系统在ETL时,遵循如下规范:如果源表的id字段在本系统中需要使用,则ETL过程中,所有的源表id字段都叫originid
   */
  public Long getOriginId() {
    return originId;
  }

  public void setOriginId(Long originId) {
    this.originId = originId;
  }

  public Date getLastModifedTime() {
    return lastModifedTime;
  }

  public void setLastModifedTime(Date lastModifedTime) {
    this.lastModifedTime = lastModifedTime;
  }
}
