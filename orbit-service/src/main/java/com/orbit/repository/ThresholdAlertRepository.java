package com.orbit.repository;

import com.orbit.entity.ThresholdAlert;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Date;

/**
 * 三级门限报警repository
 */
public interface ThresholdAlertRepository extends JpaRepository<ThresholdAlert, Long> {

  /**
   * 根据型号列表,分页查询三级门限报警异常列表,型号列表大小写不敏感,报警开始时间在指定的区间范围之内
   *
   * @param satelliteIds 型号id列表
   * @param beginTime    报警开始时间范围起始位置
   * @param endTime      报警开始时间范围终止位置
   * @param pageable     分页查询参数
   * @return 属于这些型号的三级报警列表
   */
  Page<ThresholdAlert> findBySatelliteIdInAndBeginTimeBetween(Collection<Long> satelliteIds, Date beginTime, Date endTime, Pageable pageable);

  /**
   * 批量更新"确认类别"和"情况说明"
   *
   * @param confirmCategroy 严重程度
   * @param situation       情况说明
   * @param ids           id列表
   * @return 被更新的行数
   */
  @Modifying
  @Transactional
  @Query("update #{#entityName} a set a.confirmCategroy=?1 , a.situation=?2 where a.id in (?3)")
  Integer batchAddSituationComment(ThresholdAlert.ConfirmCategroy confirmCategroy, String situation, Long... ids);


  /**
   * 批量确认
   *
   * @param confirmUserId 确认人id
   * @param confirmTime   确认时间
   * @param ids           id列表
   */
  @Modifying
  @Transactional
  @Query("update #{#entityName} a set  a.confirmUser.id=?1, a.confirmTime=?2  where a.id in (?3)")
  Integer batchConfirm(Long confirmUserId, Date confirmTime, Long... ids);
}
