package com.orbit.repository;

import com.orbit.entity.Memo;
import com.orbit.entity.TeleControl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Date;

/**
 * 备忘录repository
 */
public interface MemoRepository extends JpaRepository<Memo, Long> {

  /**
   * "备忘录菜单点击之后,默认分页查询页面"调用的方法
   * 根据型号列表,分页查询备忘录,型号列表大小写不敏感,报警开始时间在指定的区间范围之内
   *
   * @param satelliteIds 型号id列表
   * @param startTime    报警开始时间范围起始位置
   * @param endTime      报警开始时间范围终止位置
   * @param pageable     分页查询参数
   * @return 属于这些型号的备忘录列表
   */
  Page<Memo> findBySatelliteIdAndEventTimeBetween(Collection<Long> satelliteIds, Date startTime, Date endTime, Pageable pageable);

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
