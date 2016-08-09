package com.orbit.entity;

import com.orbit.entity.permission.User;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 * 型号
 */
@Entity
public class Satellite extends BaseEntity {

    /**
     * 名称
     */
    @Column(unique = true, length = 100)
    private String name;

    /**
     * 代号
     */
    @Column(unique = true, length = 100)
    private String code;

    /**
     * 责任人
     */
    @ManyToOne
    @JoinColumn(name = "ADMIN_ID", referencedColumnName = "ID")
    private User adminUser;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "SATELLITE_ID", referencedColumnName = "ID")
    private List<ThresholdAlert> alerts;

    protected Satellite() {
    }

    public Satellite(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取型号的责任人
     */
    public User getAdminUser() {
        return adminUser;
    }

    public void setAdminUser(User adminUser) {
        this.adminUser = adminUser;
    }

    @Override
    public String toString() {
        return String.format(
                "Satellite[id=%d, name='%s',责任人=%s]",
                getId(), name, adminUser);
    }

    /**
     * 门限报警
     */
    public List<ThresholdAlert> getAlerts() {
        return alerts;
    }

    public void setAlerts(List<ThresholdAlert> alerts) {
        this.alerts = alerts;
    }

    /**
     * 代号
     *
     * @return 型号代号
     */
    public String getCode() {
        return code;
    }

    /**
     * 型号代号
     *
     * @param code 代号
     */
    public void setCode(String code) {
        this.code = code;
    }
}
