package com.orbit.repository.permission;

import com.orbit.entity.permission.User;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 用户repository
 */
public interface UserRepository extends JpaRepository<User, Long> {

}
