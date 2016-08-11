package com.orbit.repository;

import com.orbit.entity.Platform2Alert;
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
 * 2代平台报警repository
 */
public interface Platform2Repository extends JpaRepository<Platform2Alert, Long> {

  /**
   * 根据型号列表,分页查询2代平台报警异常列表,报警开始时间在指定的区间范围之内
   *
   * @param startTime    报警开始时间范围起始位置
   * @param endTime      报警开始时间范围终止位置
   * @param pageable     分页查询参数
   * @return 属于这些型号的三级报警列表
   */
  Page<Platform2Alert> findByStartTimeBetween(Date startTime, Date endTime, Pageable pageable);

  /**
   * 批量更新"事件类别"和"情况说明"
   *
   * @param severityLevel 严重程度
   * @param message       情况说明
   * @param ids           id列表
   * @return 被更新的行数
   */
  @Modifying
  @Transactional
  @Query("update #{#entityName} a set a.severityLevel=?1 , a.message=?2 where a.id in (?3)")
  Integer batchAddSituationComment(ThresholdAlert.SeverityLevel severityLevel, String message, Long... ids);


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