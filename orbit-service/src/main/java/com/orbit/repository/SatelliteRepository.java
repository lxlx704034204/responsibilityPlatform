package com.orbit.repository;

import com.orbit.entity.Satellite;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 型号repository
 */
public interface SatelliteRepository extends JpaRepository<Satellite, Long> {

  /**
   * 根据"负责人"查询该负责人所有负责的型号列表,按照名字
   *
   * @param userName 负责人的登录用户名
   * @return 型号列表
   */
  List<Satellite> findAllByAdminUserUserNameOrderByNameAsc(String userName);


  /**
   * 分页查询"负责人"负责的型号列表
   *
   * @param userName 负责人的登录用户名
   * @param pageable 分页查询条件,包括排序条件
   */
  Page<Satellite> findByAdminUserUserName(String userName, Pageable pageable);
}
