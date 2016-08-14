package com.orbit.entity;

import com.orbit.entity.permission.User;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 三级门限报警
 */
@Entity
@Table(name = "THRESHOLD_ALERT")
public class ThresholdAlert extends BaseEntity {

  protected ThresholdAlert() {
  }

  public ThresholdAlert(Satellite satellite) {
    this.satellite = satellite;
  }

  public ThresholdAlert(Satellite satellite, String message) {
    this.satellite = satellite;
    this.message = message;
    this.severityLevel = SeverityLevel.MINOR;
    Date now = new Date();
    this.startTime = now;
    this.confirmTime = now;
  }

  /**
   * 相关型号
   */
  public Satellite getSatellite() {
    return satellite;
  }

  public void setSatellite(Satellite satellite) {
    this.satellite = satellite;
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
  public SeverityLevel getSeverityLevel() {
    return severityLevel;
  }

  public void setSeverityLevel(SeverityLevel severityLevel) {
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

  /**
   * 报警严重等级
   */
  public enum SeverityLevel {
    MINOR,
    MAJOR,
    SERVE,
    FATAL
  }

  @ManyToOne
  @JoinColumn(name = "SATELLITE_ID", referencedColumnName = "ID")
  private Satellite satellite;

  @Column(name = "START_TIME", nullable = false)
  private Date startTime;

  @Column(name = "END_TIME")
  private Date endTime;

  @Column(nullable = false, length = 1000)
  private String message;

  @Enumerated(EnumType.ORDINAL)
  @Column(name = "SEVERITY_LEVEL", nullable = false, columnDefinition = "SMALLINT")
  private SeverityLevel severityLevel;

  @ManyToOne
  @JoinColumn(name = "CONFIRM_USER_ID", referencedColumnName = "ID")
  private User confirmUser;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "CONFIRM_TIME", nullable = false)
  private Date confirmTime;

  @Override
  public String toString() {
    return String.format(
            "ThresholdAlert[id=%d, 型号名称='%s',报警信息=%s,开始时间=%s]",
            getId(), satellite == null ? "" : satellite.getName(), message == null ? "" : message, startTime == null ? "" : dateFormat.format(this.startTime));
  }
}
