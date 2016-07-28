package com.orbit.repository;

import com.orbit.OrbitServiceApplication;
import com.orbit.entity.Satellite;
import com.orbit.entity.ThresholdAlert;
import com.orbit.repsitory.SatelliteRepository;
import com.orbit.repsitory.ThresholdAlertRepository;

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

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = OrbitServiceApplication.class)
public class ThresholdAlertRepositoryTests {

  @Autowired
  ThresholdAlertRepository repository;

  @Autowired
  SatelliteRepository satelliteRepository;


  Satellite s1;
  ThresholdAlert alert1, alert2, alert3, alert4, alert5, alert6;

  @Before
  public void setUp() {
    s1 = new Satellite("型号1");
    satelliteRepository.save(s1);

    alert1 = new ThresholdAlert(s1, "测试异常1");
    repository.save(alert1);

    alert2 = new ThresholdAlert(s1, "测试异常2");
    repository.save(alert2);

    alert3 = new ThresholdAlert(s1, "测试异常3");
    repository.save(alert3);

    alert4 = new ThresholdAlert(s1, "测试异常4");
    repository.save(alert4);

    alert5 = new ThresholdAlert(s1, "测试异常5");
    repository.save(alert5);

    alert6 = new ThresholdAlert(s1, "测试异常6");
    repository.save(alert6);

  }

  @After
  public void destroy() {
    repository.delete(alert1);
    repository.delete(alert2);
    repository.delete(alert3);
    repository.delete(alert4);
    repository.delete(alert5);
    repository.delete(alert6);

    satelliteRepository.delete(s1);

  }

  @Test
  public void testfindBySatelliteNameIn() {
    //这里可以作为前台UI调用的demo,分页查询如何向后台传递参数,页码从0开始
    PageRequest pageRequest = new PageRequest(0, 5, new Sort(new Sort.Order(Sort.Direction.DESC, "startTime")));
    Calendar yesterday = Calendar.getInstance();
    yesterday.add(Calendar.DAY_OF_MONTH, -1);

    Calendar tommorow = Calendar.getInstance();
    tommorow.add(Calendar.DAY_OF_MONTH, 1);


    Page<ThresholdAlert> pageResult = repository.findBySatelliteNameInIgnoreCaseAndStartTimeBetween(Arrays.asList("型号1", "型号2"), yesterday.getTime(), tommorow.getTime()
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
}
