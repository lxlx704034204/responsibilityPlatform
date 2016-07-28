package com.orbit.repository;

import com.orbit.OrbitServiceApplication;
import com.orbit.entity.Satellite;
import com.orbit.entity.ThresholdAlert;
import com.orbit.repsitory.SatelliteRepository;
import com.orbit.repsitory.ThresholdAlertRepository;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = OrbitServiceApplication.class)
public class ThresholdAlertRepositoryTests {

  @Autowired
  ThresholdAlertRepository repository;

  @Autowired
  SatelliteRepository satelliteRepository;

  @Test
  public void testCreateAndDelete() {
    Satellite s1 = new Satellite("型号1");
    satelliteRepository.save(s1);

    ThresholdAlert alert = new ThresholdAlert(s1, "测试异常1");
    repository.save(alert);

    repository.delete(alert);

    satelliteRepository.delete(s1);
  }

  @Test
  public void testFindBySatelliteName() {
    Satellite s1 = new Satellite("型号1");
    satelliteRepository.save(s1);

    ThresholdAlert alert1 = new ThresholdAlert(s1, "测试异常1");
    repository.save(alert1);

    ThresholdAlert alert2 = new ThresholdAlert(s1, "测试异常2");
    repository.save(alert2);

    ThresholdAlert alert3 = new ThresholdAlert(s1, "测试异常3");
    repository.save(alert3);

    ThresholdAlert alert4 = new ThresholdAlert(s1, "测试异常4");
    repository.save(alert4);

    ThresholdAlert alert5 = new ThresholdAlert(s1, "测试异常5");
    repository.save(alert5);

    ThresholdAlert alert6 = new ThresholdAlert(s1, "测试异常6");
    repository.save(alert6);

    //这里可以作为前台UI调用的demo,分页查询如何向后台传递参数,页码从0开始
    Page<ThresholdAlert> pageResult = repository.findBySatelliteName("型号1", new PageRequest(0, 5));

    Assert.assertTrue(pageResult != null && pageResult.getTotalElements() > 0);

    repository.delete(alert1);
    repository.delete(alert2);
    repository.delete(alert3);
    repository.delete(alert4);
    repository.delete(alert5);
    repository.delete(alert6);

    satelliteRepository.delete(s1);

  }
}
