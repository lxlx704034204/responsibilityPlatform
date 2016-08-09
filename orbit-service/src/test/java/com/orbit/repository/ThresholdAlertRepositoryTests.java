package com.orbit.repository;

import com.orbit.OrbitServiceApplication;
import com.orbit.entity.Satellite;
import com.orbit.entity.ThresholdAlert;
import com.orbit.entity.permission.User;
import com.orbit.repsitory.SatelliteRepository;
import com.orbit.repsitory.ThresholdAlertRepository;
import com.orbit.repsitory.permission.UserRepository;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = OrbitServiceApplication.class)
public class ThresholdAlertRepositoryTests {

  @Autowired
  ThresholdAlertRepository repository;

  @Autowired
  SatelliteRepository satelliteRepository;

  @Autowired
  UserRepository userRepository;

  Satellite s1;
  ThresholdAlert alert1, alert2, alert3, alert4, alert5, alert6;
  User confirmUser;

  @Before
  public void setUp() {
    s1 = new Satellite("型号1");
    satelliteRepository.save(s1);

    confirmUser = new User("责任人1");
    userRepository.save(confirmUser);

    alert1 = new ThresholdAlert(s1, "测试异常现象描述1");
    alert1.setConfirmUser(confirmUser);
    alert1.setConfirmTime(new Date());
    repository.save(alert1);

    alert2 = new ThresholdAlert(s1, "测试异常现象描述2");
    alert2.setConfirmUser(confirmUser);
    alert2.setConfirmTime(new Date());
    repository.save(alert2);

    alert3 = new ThresholdAlert(s1, "测试异常现象描述3");
    alert3.setConfirmUser(confirmUser);
    alert3.setConfirmTime(new Date());
    repository.save(alert3);

    alert4 = new ThresholdAlert(s1, "测试异常现象描述4");
    alert4.setConfirmUser(confirmUser);
    alert4.setConfirmTime(new Date());
    repository.save(alert4);

    alert5 = new ThresholdAlert(s1, "测试异常现象描述5");
    alert5.setConfirmUser(confirmUser);
    alert5.setConfirmTime(new Date());
    repository.save(alert5);

    alert6 = new ThresholdAlert(s1, "测试异常6");
    alert6.setConfirmUser(confirmUser);
    alert6.setConfirmTime(new Date());
    repository.save(alert6);

  }

  @After
  public void destroy() {
    repository.delete(alert1.getId());
    repository.delete(alert2.getId());
    repository.delete(alert3.getId());
    repository.delete(alert4.getId());
    repository.delete(alert5.getId());
    repository.delete(alert6.getId());

    satelliteRepository.delete(s1);

    userRepository.delete(confirmUser);

  }

  @Test
  public void testFindBySatelliteNameInIgnoreCaseAndStartTimeBetween() {
    //这里可以作为前台UI调用的demo,分页查询如何向后台传递参数,页码从0开始
    PageRequest pageRequest = new PageRequest(0, 5, new Sort(new Sort.Order(Sort.Direction.DESC, "startTime")));
    Calendar yesterday = Calendar.getInstance();
    yesterday.add(Calendar.DAY_OF_MONTH, -1);

    Calendar tomorrow = Calendar.getInstance();
    tomorrow.add(Calendar.DAY_OF_MONTH, 1);

    Page<ThresholdAlert> pageResult = repository.findBySatelliteNameInIgnoreCaseAndStartTimeBetween(Arrays.asList("型号1", "型号2"), yesterday.getTime(), tomorrow.getTime()
            , pageRequest);
    Assert.assertTrue(pageResult != null && pageResult.getNumberOfElements() >= 0);
    System.out.println("总行数=" + pageResult.getTotalElements());
    System.out.println("总页数=" + pageResult.getTotalPages());
    System.out.println("记录编号到=" + pageResult.getNumberOfElements());
    System.out.println("getNumber()=" + pageResult.getNumber());
    System.out.println("getSize()当前页内记录数=" + pageResult.getSize());

    System.out.println("三级门限报警信息如下:");
    //如下查看排序效果
    for (ThresholdAlert alert :
            pageResult.getContent()) {
      System.out.println(alert);
    }
  }

  /**
   * 测试添加批量分析
   */
  @Test
  public void testBatchAddSituationComment() {
    int affectedSize = repository.batchAddSituationComment(ThresholdAlert.SeverityLevel.MAJOR, "测试情况说明测试情况说明", alert1.getId(), alert2.getId(), alert3.getId(), alert4.getId(), alert5.getId());
    Assert.assertEquals(5, affectedSize);
  }

  /**
   * 测试批量确认
   */
  @Test
  public void testBatchConfirm() throws Exception {
    int affectedSize = repository.batchConfirm(confirmUser.getId(), new Date(), alert1.getId(), alert2.getId(), alert3.getId(), alert4.getId(), alert5.getId());
    Assert.assertEquals(5, affectedSize);
  }
}
