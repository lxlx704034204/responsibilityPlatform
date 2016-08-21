package com.orbit.entity;

import com.orbit.entity.permission.User;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 附件,关联到生命周期全阶段任务
 */
@Entity
public class Attachment extends BaseEntity {
  @Column(name = "FILE_NAME")
  private String fileName;

  @Lob
  @Basic(fetch = FetchType.LAZY)
  private byte[] content;

  @OneToOne
  @JoinColumn(name = "uploaduser")
  private User uploadBy;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "uploadTime")
  private Date uploadTime;

  protected Attachment() {
  }

  public Attachment(String fileName) {
    this.setFileName(fileName);
  }

  /**
   * 附件内容
   */
  public byte[] getContent() {
    return content;
  }

  public void setContent(byte[] content) {
    this.content = content;
  }

  public User getUploadBy() {
    return uploadBy;
  }

  public void setUploadBy(User uploadBy) {
    this.uploadBy = uploadBy;
  }

  public Date getUploadTime() {
    return uploadTime;
  }

  public void setUploadTime(Date uploadTime) {
    this.uploadTime = uploadTime;
  }

  @Override
  public String toString() {
    return String.format(
            "Attachment[fileName=%d, uploadBy='%s',uploadTime=%s]",
            getId(), this.getFileName(), this.uploadBy == null ? "" : uploadBy.getUserName(), uploadTime == null ? "" : dateFormat.format(uploadTime));
  }

  /**
   * 附件名称
   */
  public String getFileName() {
    return fileName;
  }

  public void setFileName(String fileName) {
    this.fileName = fileName;
  }
}
