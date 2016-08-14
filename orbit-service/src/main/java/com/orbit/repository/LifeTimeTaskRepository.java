package com.orbit.repository;

import com.orbit.entity.LifeTimeTask;
import com.orbit.entity.Satellite;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 全生命周期任务repository
 */
public interface LifeTimeTaskRepository extends JpaRepository<LifeTimeTask, Long> {

  /**
   * 根据"型号"查询该型号全生命周期的任务列表,并且按照"发射保障、交付前、交付后、寿命终结"的默认顺序排序
   *
   * @param satelliteId 型号id
   * @return 该型号全生命周期的任务列表, 并且按照"发射保障、交付前、交付后、寿命终结"的默认顺序排序
   */
  List<LifeTimeTask> findAllBySatelliteIdOrderByStageAsc(Long satelliteId);

}
