package com.orbit.entity;

import com.orbit.entity.permission.User;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 * 备忘录
 */
@Entity
@Table(name = "MEMO")
public class Memo extends BaseEntity {

  protected Memo() {
  }

  public Memo(Satellite satellite) {
    this.satellite = satellite;
  }

  public Memo(Satellite satellite, String event) {
    this.satellite = satellite;
    this.event = event;
    Date now = new Date();
    this.setEventTime(now);
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

  @ManyToOne
  @JoinColumn(name = "SATELLITE_ID", referencedColumnName = "ID")
  private Satellite satellite;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "EVENT_TIME", nullable = false)
  private Date eventTime;

  @Column(nullable = false, length = 1000)
  private String event;

  @ManyToOne
  @JoinColumn(name = "CONFIRM_USER_ID", referencedColumnName = "ID")
  private User confirmUser;

  /**
   * 确认时间,可以为空,为空时表示为被确认
   */
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "CONFIRM_TIME")
  private Date confirmTime;

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
            "Memo[id=%d, 型号名称='%s',事件=%s,时间=%s]",
            getId(), satellite == null ? "" : satellite.getName(), event == null ? "" : event, eventTime == null ? "" : dateFormat.format(this.eventTime));
  }

  public Date getEventTime() {
    return eventTime;
  }

  public void setEventTime(Date eventTime) {
    this.eventTime = eventTime;
  }
}
