package com.orbit.repository;

import com.orbit.entity.RealAlarm2;
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
public interface RealAlarm2Repository extends JpaRepository<RealAlarm2, Long> {

  /**
   * 根据型号代号列表和开始时间范围查询二代平台报警列表
   *
   * @param satelliteCodes 型号代号列表
   * @param beginTime      开始报警时间
   * @param endTime        结束报警时间
   * @param pageable       分页和排序条件
   * @return 满足条件的二代平台报警信息一页数据
   */
  Page<RealAlarm2> findBySatelliteCodeInAndBeginTimeBetween(Collection<String> satelliteCodes, Date beginTime, Date endTime, Pageable pageable);

  /**
   * 批量更新"事件类别"和"情况说明"
   *
   * @param severityLevel 严重程度
   * @param situation     情况说明
   * @param ids           id列表
   * @return 被更新的行数
   */
  @Modifying
  @Transactional
  @Query("update #{#entityName} a set a.severityLevel=?1 , a.situation=?2 where a.id in (?3)")
  Integer batchAddSituationComment(ThresholdAlert.SeverityLevel severityLevel, String situation, Long... ids);


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
