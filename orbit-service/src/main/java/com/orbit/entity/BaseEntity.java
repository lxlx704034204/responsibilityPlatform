package com.orbit.entity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
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
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "ORIGIN_ID")
    private Long originId;

    /**
     * 上次修改时间
     */
    @Column(name = "LAST_MODIFIED_TIME", nullable = false)
    private Date lastModifedTime;

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
     * 由于涉及到ETL,本系统中保存原有系统中model的id,便于在数据不一致的时候比较数据一致性
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
