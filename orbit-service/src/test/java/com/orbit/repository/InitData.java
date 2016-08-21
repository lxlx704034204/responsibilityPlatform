package com.orbit.repository;


import com.orbit.OrbitServiceApplication;
import com.orbit.entity.LifeTimeTask;
import com.orbit.entity.Satellite;
import com.orbit.entity.ThresholdAlert;
import com.orbit.entity.permission.User;
import com.orbit.repository.SatelliteRepository;
import com.orbit.repository.ThresholdAlertRepository;
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

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = OrbitServiceApplication.class)
public class InitData {
	
	User adminUser, confirmUser;
	Satellite sl1, sl2, sl3;
	ThresholdAlert alert1, alert2, alert3, alert4, alert5, alert6;
	LifeTimeTask task1, task2, task3, task4, task5, task6, task7, task8, task9, task10;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	SatelliteRepository satelliteRepository;
	
	@Autowired
	ThresholdAlertRepository thresholdAlertRepository;
	
	@Autowired
	LifeTimeTaskRepository lifeTimeTaskRepository;

	@Test
	public void initAll(){
		
		
		
		thresholdAlertRepository.deleteAll();
		lifeTimeTaskRepository.deleteAll();
		satelliteRepository.deleteAll();
		userRepository.deleteAll();
		
		// users
		adminUser = new User("adminuser");
		adminUser.setFullName("管理员");
		userRepository.save(adminUser);
		
		confirmUser = new User("confirmuser");
		confirmUser.setFullName("张三");
		userRepository.save(confirmUser);
		
		// satellites
		sl1 = new Satellite("型号1");
		sl1.setAdminUser(adminUser);
		sl1.setCode("sl1");
		
		sl2 = new Satellite("型号2");
		sl2.setAdminUser(adminUser);
		sl2.setCode("sl2");
		
		sl3 = new Satellite("型号3");
		sl3.setAdminUser(adminUser);
		sl3.setCode("sl3");
		
		satelliteRepository.save(sl1);
		satelliteRepository.save(sl2);
		satelliteRepository.save(sl3);
		
		// threshold alerts
		alert1 = new ThresholdAlert(sl1, "测试异常现象描述1");
	    alert1.setConfirmUser(confirmUser);
	    alert1.setConfirmTime(new Date());
	    thresholdAlertRepository.save(alert1);

	    alert2 = new ThresholdAlert(sl2, "测试异常现象描述2");
	    alert2.setConfirmUser(confirmUser);
	    alert2.setConfirmTime(new Date());
	    thresholdAlertRepository.save(alert2);

	    alert3 = new ThresholdAlert(sl1, "测试异常现象描述3");
	    alert3.setConfirmUser(confirmUser);
	    alert3.setConfirmTime(new Date());
	    thresholdAlertRepository.save(alert3);

	    alert4 = new ThresholdAlert(sl2, "测试异常现象描述4");
	    alert4.setConfirmUser(confirmUser);
	    alert4.setConfirmTime(new Date());
	    thresholdAlertRepository.save(alert4);

	    alert5 = new ThresholdAlert(sl1, "测试异常现象描述5");
	    alert5.setConfirmUser(confirmUser);
	    alert5.setConfirmTime(new Date());
	    thresholdAlertRepository.save(alert5);

	    alert6 = new ThresholdAlert(sl3, "测试异常现象描述6");
	    alert6.setConfirmUser(confirmUser);
	    alert6.setConfirmTime(new Date());
	    thresholdAlertRepository.save(alert6);
	    
	    // life time tasks
	    task1 = new LifeTimeTask(sl1, "发射保障策划_LifeTimeTaskRepositoryTest");
	    task1.setConfirmUser(confirmUser);
	    task1.setConfirmTime(new Date());
	    task1.setStage(LifeTimeTask.Stage.Launch);
	    lifeTimeTaskRepository.save(task1);

	    task2 = new LifeTimeTask(sl1, "发射保障软硬件配置_LifeTimeTaskRepositoryTest");
	    task2.setConfirmUser(confirmUser);
	    task2.setConfirmTime(new Date());
	    task2.setStage(LifeTimeTask.Stage.Launch);
	    lifeTimeTaskRepository.save(task2);

	    task3 = new LifeTimeTask(sl1, "在轨管理工作要点_LifeTimeTaskRepositoryTest");
	    task3.setConfirmUser(confirmUser);
	    task3.setConfirmTime(new Date());
	    task3.setStage(LifeTimeTask.Stage.PreDeliver);
	    lifeTimeTaskRepository.save(task3);

	    task4 = new LifeTimeTask(sl1, "建立履历书_LifeTimeTaskRepositoryTest");
	    task4.setConfirmUser(confirmUser);
	    task4.setConfirmTime(new Date());
	    task4.setStage(LifeTimeTask.Stage.PreDeliver);
	    lifeTimeTaskRepository.save(task4);

	    task5 = new LifeTimeTask(sl1, "设置报警门限_LifeTimeTaskRepositoryTest");
	    task5.setConfirmUser(confirmUser);
	    task5.setConfirmTime(new Date());
	    task5.setStage(LifeTimeTask.Stage.PreDeliver);
	    lifeTimeTaskRepository.save(task5);

	    task6 = new LifeTimeTask(sl1, "在轨管理手册_LifeTimeTaskRepositoryTest");
	    task6.setConfirmUser(confirmUser);
	    task6.setConfirmTime(new Date());
	    task6.setStage(LifeTimeTask.Stage.PostDeliver);
	    lifeTimeTaskRepository.save(task6);

	    task7 = new LifeTimeTask(sl1, "门限设置文件提交_LifeTimeTaskRepositoryTest");
	    task7.setConfirmUser(confirmUser);
	    task7.setConfirmTime(new Date());
	    task7.setStage(LifeTimeTask.Stage.PostDeliver);
	    lifeTimeTaskRepository.save(task7);

	    task8 = new LifeTimeTask(sl1, "更新履历书信息_LifeTimeTaskRepositoryTest");
	    task8.setConfirmUser(confirmUser);
	    task8.setConfirmTime(new Date());
	    task8.setStage(LifeTimeTask.Stage.PostDeliver);
	    lifeTimeTaskRepository.save(task8);

	    task9 = new LifeTimeTask(sl1, "航天器在轨全寿命运行情况总结报告_LifeTimeTaskRepositoryTest");
	    task9.setConfirmUser(confirmUser);
	    task9.setConfirmTime(new Date());
	    task9.setStage(LifeTimeTask.Stage.EndOfLife);
	    lifeTimeTaskRepository.save(task9);

	    task10 = new LifeTimeTask(sl1, "航天器在轨运行履历书_LifeTimeTaskRepositoryTest");
	    task10.setConfirmUser(confirmUser);
	    task10.setConfirmTime(new Date());
	    task10.setStage(LifeTimeTask.Stage.EndOfLife);
	    lifeTimeTaskRepository.save(task10);
	}

}
