package com.orbit.repository;

import com.orbit.entity.EventType;
import com.orbit.entity.permission.User;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 测控事件种类repository
 */
public interface EventTypeRepository extends JpaRepository<EventType, Long> {

}
