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
 * 三级门限报警
 */
@Entity
@Table(name = "alarminfo")
public class ThresholdAlert extends BaseEntity {

  protected ThresholdAlert() {
  }

  public ThresholdAlert(Satellite satellite) {
    this.satellite = satellite;
  }

  public ThresholdAlert(Satellite satellite, String message) {
    this.satellite = satellite;
    this.message = message;
    this.severityLevel = SeverityLevel.轻度异常;
    Date now = new Date();
    this.beginTime = now;
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
  public Date getBeginTime() {
    return beginTime;
  }

  public void setBeginTime(Date beginTime) {
    this.beginTime = beginTime;
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
   * 事件类别(严重程度)
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
   * 情况说明
   */
  public String getSituation() {
    return situation;
  }

  public void setSituation(String situation) {
    this.situation = situation;
  }

  /**
   * 确认类别
   */
  public ConfirmCategroy getConfirmCategroy() {
    return confirmCategroy;
  }

  public void setConfirmCategroy(ConfirmCategroy confirmCategroy) {
    this.confirmCategroy = confirmCategroy;
  }

  /**
   * 值班人确认时间
   */
  public Date getResponseTime() {
    return responseTime;
  }

  public void setResponseTime(Date responseTime) {
    this.responseTime = responseTime;
  }

  /**
   * 值班人姓名
   */
  public String getResponseUserName() {
    return responseUserName;
  }

  public void setResponseUserName(String responseUserName) {
    this.responseUserName = responseUserName;
  }

  /**
   * 报警值
   */
  public String getAlarmValue() {
    return alarmValue;
  }

  public void setAlarmValue(String alarmValue) {
    this.alarmValue = alarmValue;
  }

  /**
   * 遥测参数代号,通过regex从alarmMessage中截取
   */
  public String getTelementCode() {
    return telementCode;
  }

  public void setTelementCode(String telementCode) {
    this.telementCode = telementCode;
  }

  /**
   * 报警严重等级
   */
  public enum SeverityLevel {
    中间变量,
    状态跳变,
    提示信息,
    轻度异常,
    中度异常,
    重度异常
  }

  /**
   * 确认类别,一二代定义在一起
   */
  public enum ConfirmCategroy {
    星上异常报警,
    地面异常报警,
    测控事件,
    误码和野值,
    门限设置不当,
    知识错误,
    其他
  }

  @ManyToOne
  @JoinColumn(name = "satid", referencedColumnName = "ID", nullable = false)
  private Satellite satellite;

  @Column(name = "begintime", nullable = false)
  private Date beginTime;

  @Column(name = "endtime")
  private Date endTime;

  @Column(nullable = false, length = 1000)
  private String message;

  @Enumerated(EnumType.STRING)
  @Column(name = "severitylevel", nullable = false)
  private SeverityLevel severityLevel;

  @Enumerated(EnumType.STRING)
  @Column(name = "confirmcategroy")
  private ConfirmCategroy confirmCategroy;

  @ManyToOne
  @JoinColumn(name = "CONFIRM_USER_ID", referencedColumnName = "ID")
  private User confirmUser;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "confirmtime")
  private Date confirmTime;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "responsetime")
  private Date responseTime;

  @Column(name = "responseusername", length = 100)
  private String responseUserName;

  @Column(name = "alarmvalue", length = 512)
  private String alarmValue;

  @Column(name = "telementcode",length = 100)
  private String telementCode;

  /**
   * 情况说明
   */
  @Column(length = 1000)
  private String situation;

  @Override
  public String toString() {
    return String.format(
            "ThresholdAlert[id=%d, 型号名称='%s',报警信息=%s,开始时间=%s]",
            getId(), satellite == null ? "" : satellite.getName(), message == null ? "" : message, beginTime == null ? "" : dateFormat.format(this.beginTime));
  }
}
