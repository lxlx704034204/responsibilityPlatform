package com.orbit.repsitory;

import com.orbit.entity.ThresholdAlert;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 门限报警repository
 */
public interface ThresholdAlertRepository extends JpaRepository<ThresholdAlert, Long> {

  /**
   * 根据型号分页查询报警信息
   */
  Page<ThresholdAlert> findBySatelliteName(String satelliteName, Pageable pageable);//Pageable参数能被Spring JPA自动识别,并创建参数

}
