package com.orbit.repository;

import com.orbit.OrbitServiceApplication;
import com.orbit.entity.LifeTimeTask;
import com.orbit.entity.Satellite;
import com.orbit.entity.permission.User;
import com.orbit.repository.permission.UserRepository;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = OrbitServiceApplication.class)
public class LifeTimeTaskRepositoryTest {
  @Autowired
  SatelliteRepository satelliteRepository;

  @Autowired
  UserRepository userRepository;

  @Autowired
  LifeTimeTaskRepository repository;

  LifeTimeTask task1, task2, task3, task4, task5, task6, task7, task8, task9, task10;
  User confirmUser;
  Satellite s1;

  @Before
  public void setUp() {
    confirmUser = new User("责任人1_LifeTimeTaskRepositoryTest");
    userRepository.save(confirmUser);

    s1 = new Satellite("型号1");
    s1.setAdminUser(confirmUser);
    //confirmUser.addSatellite(s1);//不设置会造成FK reference异常
    satelliteRepository.save(s1);

    task1 = new LifeTimeTask(s1, "发射保障策划_LifeTimeTaskRepositoryTest");
    task1.setConfirmUser(confirmUser);
    task1.setConfirmTime(new Date());
    task1.setStage(LifeTimeTask.Stage.Launch);
    repository.save(task1);

    task2 = new LifeTimeTask(s1, "发射保障软硬件配置_LifeTimeTaskRepositoryTest");
    task2.setConfirmUser(confirmUser);
    task2.setConfirmTime(new Date());
    task2.setStage(LifeTimeTask.Stage.Launch);
    repository.save(task2);

    task3 = new LifeTimeTask(s1, "在轨管理工作要点_LifeTimeTaskRepositoryTest");
    task3.setConfirmUser(confirmUser);
    task3.setConfirmTime(new Date());
    task3.setStage(LifeTimeTask.Stage.PreDeliver);
    repository.save(task3);

    task4 = new LifeTimeTask(s1, "建立履历书_LifeTimeTaskRepositoryTest");
    task4.setConfirmUser(confirmUser);
    task4.setConfirmTime(new Date());
    task4.setStage(LifeTimeTask.Stage.PreDeliver);
    repository.save(task4);

    task5 = new LifeTimeTask(s1, "设置报警门限_LifeTimeTaskRepositoryTest");
    task5.setConfirmUser(confirmUser);
    task5.setConfirmTime(new Date());
    task5.setStage(LifeTimeTask.Stage.PreDeliver);
    repository.save(task5);

    task6 = new LifeTimeTask(s1, "在轨管理手册_LifeTimeTaskRepositoryTest");
    task6.setConfirmUser(confirmUser);
    task6.setConfirmTime(new Date());
    task6.setStage(LifeTimeTask.Stage.PostDeliver);
    repository.save(task6);

    task7 = new LifeTimeTask(s1, "门限设置文件提交_LifeTimeTaskRepositoryTest");
    task7.setConfirmUser(confirmUser);
    task7.setConfirmTime(new Date());
    task7.setStage(LifeTimeTask.Stage.PostDeliver);
    repository.save(task7);

    task8 = new LifeTimeTask(s1, "更新履历书信息_LifeTimeTaskRepositoryTest");
    task8.setConfirmUser(confirmUser);
    task8.setConfirmTime(new Date());
    task8.setStage(LifeTimeTask.Stage.PostDeliver);
    repository.save(task8);

    task9 = new LifeTimeTask(s1, "航天器在轨全寿命运行情况总结报告_LifeTimeTaskRepositoryTest");
    task9.setConfirmUser(confirmUser);
    task9.setConfirmTime(new Date());
    task9.setStage(LifeTimeTask.Stage.EndOfLife);
    repository.save(task9);

    task10 = new LifeTimeTask(s1, "航天器在轨运行履历书_LifeTimeTaskRepositoryTest");
    task10.setConfirmUser(confirmUser);
    task10.setConfirmTime(new Date());
    task10.setStage(LifeTimeTask.Stage.EndOfLife);
    repository.save(task10);

  }

  @After
  public void destroy() {
    repository.delete(task1.getId());
    repository.delete(task2.getId());
    repository.delete(task3.getId());
    repository.delete(task4.getId());
    repository.delete(task5.getId());
    repository.delete(task6.getId());
    repository.delete(task7.getId());
    repository.delete(task8.getId());
    repository.delete(task9.getId());
    repository.delete(task10.getId());

    satelliteRepository.delete(s1);
    userRepository.delete(confirmUser);

  }


  @Test
  public void findAllBySatelliteIdOrderByStage() throws Exception {
    List<LifeTimeTask> tasks = repository.findAllBySatelliteIdOrderByStage(s1.getId());
    Assert.assertEquals(10, tasks.size());
  }

}