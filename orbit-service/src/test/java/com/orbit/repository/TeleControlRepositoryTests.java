package com.orbit.repository;

import com.orbit.OrbitServiceApplication;
import com.orbit.entity.Platform2Alert;
import com.orbit.entity.Satellite;
import com.orbit.entity.TeleControl;
import com.orbit.entity.ThresholdAlert;
import com.orbit.entity.permission.User;
import com.orbit.repository.permission.UserRepository;

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

/**
 * 测控事件的repository测试
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = OrbitServiceApplication.class)
public class TeleControlRepositoryTests {

  @Autowired
  TeleControlRepository repository;

  @Autowired
  UserRepository userRepository;

  @Autowired
  SatelliteRepository satelliteRepository;

  TeleControl alert1, alert2, alert3, alert4, alert5, alert6;
  User confirmUser;

  Satellite s1, s2;

  @Before
  public void setUp() {
    confirmUser = new User("责任人1");
    userRepository.save(confirmUser);

    s1 = new Satellite("型号1");
    s1.setAdminUser(confirmUser);
    satelliteRepository.save(s1);

    s2 = new Satellite("型号2");
    s2.setAdminUser(confirmUser);
    satelliteRepository.save(s2);

    alert1 = new TeleControl(s1, "测试异常现象描述1");
    alert1.setConfirmUser(confirmUser);
    alert1.setConfirmTime(new Date());
    repository.save(alert1);

    alert2 = new TeleControl(s1, "测试异常现象描述2");
    alert2.setConfirmUser(confirmUser);
    alert2.setConfirmTime(new Date());
    repository.save(alert2);

    alert3 = new TeleControl(s1, "测试异常现象描述3");
    alert3.setConfirmUser(confirmUser);
    alert3.setConfirmTime(new Date());
    repository.save(alert3);

    alert4 = new TeleControl(s1, "测试异常现象描述4");
    alert4.setConfirmUser(confirmUser);
    alert4.setConfirmTime(new Date());
    repository.save(alert4);

    alert5 = new TeleControl(s2, "型号2_测试异常现象描述1");
    alert5.setConfirmUser(confirmUser);
    alert5.setConfirmTime(new Date());
    repository.save(alert5);

    alert6 = new TeleControl(s2, "型号2_测试异常现象描述2");
    alert6.setConfirmUser(confirmUser);
    alert6.setConfirmTime(new Date());
    repository.save(alert6);

  }

  @After
  public void destroy() {
    satelliteRepository.delete(s1.getId());
    satelliteRepository.delete(s2.getId());

    repository.delete(alert1.getId());
    repository.delete(alert2.getId());
    repository.delete(alert3.getId());
    repository.delete(alert4.getId());
    repository.delete(alert5.getId());
    repository.delete(alert6.getId());

    userRepository.delete(confirmUser);

  }

  @Test
  public void testFindBySatelliteIdAndStartTimeBetween() {
    //这里可以作为前台UI调用的demo,分页查询如何向后台传递参数,页码从0开始,默认按照"报警开始"降序排序
    PageRequest pageRequest = new PageRequest(0, 5, new Sort(new Sort.Order(Sort.Direction.DESC, "startTime")));
    Calendar yesterday = Calendar.getInstance();
    yesterday.add(Calendar.DAY_OF_MONTH, -1);

    Calendar tomorrow = Calendar.getInstance();
    tomorrow.add(Calendar.DAY_OF_MONTH, 1);

    Page<TeleControl> pageResult = repository.findBySatelliteIdAndStartTimeBetween(Arrays.asList(s1.getId()), yesterday.getTime(), tomorrow.getTime()
            , pageRequest);
    Assert.assertTrue(pageResult != null && pageResult.getNumberOfElements() >= 0);
    System.out.println("总行数=" + pageResult.getTotalElements());
    System.out.println("总页数=" + pageResult.getTotalPages());
    System.out.println("记录编号到=" + pageResult.getNumberOfElements());
    System.out.println("getNumber()=" + pageResult.getNumber());
    System.out.println("getSize()当前页内记录数=" + pageResult.getSize());

    System.out.println("测控事件息如下:");
    //如下查看排序效果
    for (TeleControl alert :
            pageResult.getContent()) {
      System.out.println(alert);
    }
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
