package com.orbit.repsitory;

import com.orbit.entity.Satellite;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 型号repository
 */
public interface SatelliteRepository extends JpaRepository<Satellite, Long> {

  /**
   * 根据"负责人"查询该负责人负责的型号列表
   *
   * @param user 负责人
   * @return 型号列表
   */
  //List<Satellite> findByUser(User user);
}
