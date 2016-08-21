package com.orbit.entity;

import com.orbit.entity.permission.User;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 * 测控事件
 */
@Entity
@Table(name = "TELE_CONTROL")
public class TeleControl extends BaseEntity {

  protected TeleControl() {
  }

  public TeleControl(Satellite satellite) {
    this.satellite = satellite;
  }

  public TeleControl(Satellite satellite, String message) {
    this.satellite = satellite;
    this.message = message;
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

  public EventType getEventType() {
    return eventType;
  }

  public void setEventType(EventType eventType) {
    this.eventType = eventType;
  }

  /**
   * 附件
   */
  public Attachment getAttachment() {
    return attachment;
  }

  public void setAttachment(Attachment attachment) {
    this.attachment = attachment;
  }

  /**
   * 测控事件类型
   */
  public enum EventType {
    PositionMaitain,
    SlopChange,//倾角调整
    JamProtection,//干扰保护
    ChangeTrack//变轨
  }

  @Enumerated(EnumType.ORDINAL)
  @Column(name = "EVENT_TYPE", columnDefinition = "SMALLINT")
  private EventType eventType;

  @ManyToOne
  @JoinColumn(name = "SATELLITE_ID", referencedColumnName = "ID")
  private Satellite satellite;

  @Column(name = "START_TIME", nullable = false)
  private Date startTime;

  @Column(name = "END_TIME")
  private Date endTime;

  @Column(nullable = false, length = 1000)
  private String message;

  @ManyToOne
  @JoinColumn(name = "CONFIRM_USER_ID", referencedColumnName = "ID")
  private User confirmUser;

  /**
   * 确认时间,可以为空,为空时表示为被确认
   * TODO: 三级门限和二代平台都有确认人和确认时间,为什么测控事件没有呢?是否因为,能确认的都是该型号的责任人?
   */
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "CONFIRM_TIME")
  private Date confirmTime;

  @Basic(fetch = FetchType.LAZY)
  @OneToOne (cascade= CascadeType.ALL)
  @JoinColumn(name="ATTACHMENT_ID")
  private Attachment attachment;

  /**
   * 是否被确认,如果确认时间和确认人都存在,则认为已经被确认
   *
   * @return 是否被确认
   */
  @Transient
  public boolean isConfirmed() {
    return confirmUser != null && confirmTime != null;
  }

  @Override
  public String toString() {
    return String.format(
            "TeleControl[id=%d, 型号名称='%s',报警信息=%s,开始时间=%s]",
            getId(), satellite == null ? "" : satellite.getName(), message == null ? "" : message, startTime == null ? "" : dateFormat.format(this.startTime));
  }
}
