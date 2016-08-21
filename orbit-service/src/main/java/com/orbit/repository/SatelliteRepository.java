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
   * 根据"负责人"查询该负责人所有负责的型号列表,按照名字升序
   *
   * @param loginName 负责人的登录用户名
   * @return 型号列表
   */
  List<Satellite> findAllByAdminUserLoginNameOrderByNameAsc(String loginName);


  /**
   * 分页查询"负责人"负责的型号列表
   * @param loginName
   * @param pageable
   * @return
   */
  Page<Satellite> findByAdminUserLoginName(String loginName, Pageable pageable);
}
