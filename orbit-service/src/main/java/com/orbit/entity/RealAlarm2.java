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
 * 2代平台报警,RealAlarm2,以后如果有1代,RealAlarm1
 */
@Entity
@Table(name = "realalarm2")
public class RealAlarm2 extends BaseEntity {

  protected RealAlarm2() {
  }

  /**
   * 构造函数
   *
   * @param satelliteCode 型号代号
   * @param message       报警信息
   */
  public RealAlarm2(String satelliteCode, String message) {
    this.satelliteCode = satelliteCode;
    this.message = message;
    this.severityLevel = ThresholdAlert.SeverityLevel.轻度异常;
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
   * 件类别(严重程度)
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

  @Column(name = "START_TIME", nullable = false)
  private Date startTime;

  @Column(name = "END_TIME")
  private Date endTime;

  @Column(nullable = false, length = 1000)
  private String message;

  @Enumerated(EnumType.ORDINAL)
  @Column(name = "severityLevel", nullable = false)
  private ThresholdAlert.SeverityLevel severityLevel;

  @ManyToOne
  @JoinColumn(name = "confirmUser", referencedColumnName = "ID")
  private User confirmUser;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "confirmTime", nullable = false)
  private Date confirmTime;

  @Column(name = "satcode")
  private String satelliteCode;

  @Column(name = "alarmExplain", length = 2048)
  private String alarmExplain;

  @Column(length = 1024)
  private String situation;

  @Enumerated(EnumType.ORDINAL)
  @Column(name = "confirmCategroy", nullable = false, columnDefinition = "SMALLINT")
  private ThresholdAlert.ConfirmCategroy confirmCategroy;

  @Override
  public String toString() {
    return String.format(
            "RealAlarm2[id=%d, 型号代号='%s',报警信息=%s,开始时间=%s]",
            getId(), getSatelliteCode() == null ? "" : getSatelliteCode(),
            message == null ? "" : message, startTime == null ? "" : dateFormat.format(this.startTime));
  }

  /**
   * 关联的型号代号,这里没有做对象关联
   */
  public String getSatelliteCode() {
    return satelliteCode;
  }

  public void setSatelliteCode(String satelliteCode) {
    this.satelliteCode = satelliteCode;
  }

  /**
   * 情况说明
   */
  public String getSituation() {
    return situation;
  }

  public void setSituation(String situation) {
    this.situation = situation;
  }

  /**
   * 报警解释信息
   */
  public String getAlarmExplain() {
    return alarmExplain;
  }

  public void setAlarmExplain(String alarmExplain) {
    this.alarmExplain = alarmExplain;
  }

  /**
   * 确认类别
   */
  public ThresholdAlert.ConfirmCategroy getConfirmCategroy() {
    return confirmCategroy;
  }

  public void setConfirmCategroy(ThresholdAlert.ConfirmCategroy confirmCategroy) {
    this.confirmCategroy = confirmCategroy;
  }
}
