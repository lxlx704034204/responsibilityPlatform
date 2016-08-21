package com.orbit.entity;


import com.orbit.entity.permission.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 全生命周期任务
 */
@Entity
@Table(name = "LIFETIME_TASK")
public class LifeTimeTask extends BaseEntity {
  protected LifeTimeTask() {
  }

  public LifeTimeTask(Satellite satellite) {
    this.setSatellite(satellite);
  }

  public LifeTimeTask(Satellite satellite, String name) {
    this.setSatellite(satellite);
    this.setName(name);
    this.stage = Stage.交付后;//默认是"交付后",因为交付后的任务最多
  }

  @ManyToOne
  @JoinColumn(name = "SATELLITEID", referencedColumnName = "ID")
  private Satellite satellite;

  @Enumerated(EnumType.ORDINAL)
  @Column(name = "stage", nullable = false, columnDefinition = "SMALLINT")
  private Stage stage;

  @Column(nullable = false)
  private String name;

  @ManyToOne
  @JoinColumn(name = "USER_RESPONSIBLE", referencedColumnName = "ID")
  private User userResponsible;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "DEADLINE_TIME")
  private Date deadLineTime;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "SUBMIT_TIME")
  private Date submitTime;

  @Column(length = 1000)
  private String taskResult;

  @Column(length = 1000)
  private String detail;

  @ManyToOne
  @JoinColumn(name = "CONFIRM_USER_ID", referencedColumnName = "ID")
  private User confirmUser;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "confirmTime")
  private Date confirmTime;

  @OneToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "LIFETIME_TASK_USER", joinColumns = @JoinColumn(name = "LIFETIME_TASK_ID"), foreignKey = @ForeignKey(name = "FK_LIFETIME_TASK_2_USER"), inverseJoinColumns = @JoinColumn(name = "USER_ID"), inverseForeignKey = @ForeignKey(name = "FK_USER_2_LIFETIME_TASK"))
  private List<User> involvedUsers;

  @OneToMany(fetch = FetchType.EAGER)
  @JoinTable(
          name = "LIFETIME_TASK_ATTACHMENT",
          joinColumns = @JoinColumn(name = "LIFETIME_TASK_ID"))
  private List<Attachment> attachments;

  public Satellite getSatellite() {
    return satellite;
  }

  public void setSatellite(Satellite satellite) {
    this.satellite = satellite;
  }

  /**
   * 任务阶段
   */
  public Stage getStage() {
    return stage;
  }

  public void setStage(Stage stage) {
    this.stage = stage;
  }

  /**
   * 任务名称
   */
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  /**
   * 责任人
   */
  public User getUserResponsible() {
    return userResponsible;
  }

  public void setUserResponsible(User userResponsible) {
    this.userResponsible = userResponsible;
  }

  /**
   * 截止时间
   */
  public Date getDeadLineTime() {
    return deadLineTime;
  }

  public void setDeadLineTime(Date deadLineTime) {
    this.deadLineTime = deadLineTime;
  }

  /**
   * 提交时间
   */
  public Date getSubmitTime() {
    return submitTime;
  }

  public void setSubmitTime(Date submitTime) {
    this.submitTime = submitTime;
  }

  /**
   * 任务成果
   */
  public String getTaskResult() {
    return taskResult;
  }

  public void setTaskResult(String taskResult) {
    this.taskResult = taskResult;
  }

  /**
   * 详细信息
   */
  public String getDetail() {
    return detail;
  }

  public void setDetail(String detail) {
    this.detail = detail;
  }

  public User getConfirmUser() {
    return confirmUser;
  }

  public void setConfirmUser(User confirmUser) {
    this.confirmUser = confirmUser;
  }

  public Date getConfirmTime() {
    return confirmTime;
  }

  public void setConfirmTime(Date confirmTime) {
    this.confirmTime = confirmTime;
  }

  /**
   * 任务附件
   */
  public List<Attachment> getAttachments() {
    return attachments;
  }

  public void setAttachments(List<Attachment> attachments) {
    this.attachments = attachments;
  }

  /**
   * 添加一个附件
   *
   * @param attachment 待添加的附件
   * @return 所有附件列表
   */
  public List<Attachment> addAttachment(Attachment attachment) {
    if (this.attachments == null) {
      this.attachments = new ArrayList<>();
    }
    this.attachments.add(attachment);

    return this.attachments;

  }

  /**
   * 其他相关人员
   */
  public List<User> getInvolvedUsers() {
    return involvedUsers;
  }

  public void setInvolvedUsers(List<User> involvedUsers) {
    this.involvedUsers = involvedUsers;
  }

  /**
   * 生命周期阶段
   */
  public enum Stage {
    发生及保障阶段,//Launch
    交付前,//PreDeliver
    交付后,//PostDeliver
    寿命终结// EndOfLife
  }
}
