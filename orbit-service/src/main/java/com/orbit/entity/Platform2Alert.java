package com.orbit.entity;

import com.orbit.entity.permission.User;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 2代平台
 */
@Entity
@Table(name="PLATFORM2_ALERT")
public class Platform2Alert extends BaseEntity {

  protected Platform2Alert() {
  }

  /**
   * 构造函数
   *
   * @param name    型号代号
   * @param message 报警信息
   */
  public Platform2Alert(String name, String message) {
    this.name = name;
    this.message = message;
    this.severityLevel = ThresholdAlert.SeverityLevel.MINOR;
    Date now = new Date();
    this.startTime = now;
    this.confirmTime = now;
  }

  /**
   * 报警开始时间
   */
  public Date getStartTime() {
    return startTime;
  }

  public void setStartTime(Date startTime) {
    this.startTime = startTime;
  }

  /**
   * 报警结束时间
   */
  public Date getEndTime() {
    return endTime;
  }

  public void setEndTime(Date endTime) {
    this.endTime = endTime;
  }

  /**
   * 报警信息
   */
  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  /**
   * TODO: 事件类别(严重程度)?
   */
  public ThresholdAlert.SeverityLevel getSeverityLevel() {
    return severityLevel;
  }

  public void setSeverityLevel(ThresholdAlert.SeverityLevel severityLevel) {
    this.severityLevel = severityLevel;
  }

  /**
   * 确认人id
   */
  public User getConfirmUser() {
    return confirmUser;
  }

  public void setConfirmUser(User confirmUser) {
    this.confirmUser = confirmUser;
  }

  /**
   * 确认时间
   */
  public Date getConfirmTime() {
    return confirmTime;
  }

  public void setConfirmTime(Date confirmTime) {
    this.confirmTime = confirmTime;
  }

  @Column(nullable = false, length = 100)
  private String name;

  @Column(name = "START_TIME", nullable = false)
  private Date startTime;

  @Column(name = "END_TIME")
  private Date endTime;

  @Column(nullable = false, length = 1000)
  private String message;

  @Enumerated(EnumType.ORDINAL)
  @Column(name = "SEVERITY_LEVEL", nullable = false)
  private ThresholdAlert.SeverityLevel severityLevel;

  @ManyToOne
  @JoinColumn(name = "CONFIRM_USER_ID", referencedColumnName = "ID")
  private User confirmUser;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "CONFIRM_TIME", nullable = false)
  private Date confirmTime;

  @Override
  public String toString() {
    return String.format(
            "Platform2Alert[id=%d, 型号代号='%s',报警信息=%s,开始时间=%s]",
            getId(), getName() == null ? "" : getName(), message == null ? "" : message, startTime == null ? "" : dateFormat.format(this.startTime));
  }

  /**
   * 型号代号
   */
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
