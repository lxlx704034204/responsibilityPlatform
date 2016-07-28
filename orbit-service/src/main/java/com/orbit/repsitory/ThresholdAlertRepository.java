package com.orbit.repsitory;

import com.orbit.entity.permission.User;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 用户repository
 */
public interface ThresholdAlertRepository extends JpaRepository<User, Long> {

}
