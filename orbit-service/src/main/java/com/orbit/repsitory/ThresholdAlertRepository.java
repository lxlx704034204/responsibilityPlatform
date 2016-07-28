package com.orbit.repsitory;

import com.orbit.entity.ThresholdAlert;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.Date;

/**
 * 门限报警repository
 */
public interface ThresholdAlertRepository extends JpaRepository<ThresholdAlert, Long> {

  /**
   * 根据型号列表,分页查询三级门限报警异常列表,型号列表大小写不敏感,报警开始时间在指定的区间范围之内
   *
   * @param startTime      报警开始时间范围起始位置
   * @param endTime        报警开始时间范围终止位置
   * @param pageable       分页查询参数
   * @param satelliteNames 型号列表,变长参数
   * @return 属于这些型号的三级报警列表
   */
  Page<ThresholdAlert> findBySatelliteNameInIgnoreCaseAndStartTimeBetween(Collection<String> satelliteNames, Date startTime, Date endTime, Pageable pageable);

}
