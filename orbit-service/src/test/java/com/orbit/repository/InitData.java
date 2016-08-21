package com.orbit.repository;


import com.orbit.OrbitServiceApplication;
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
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	SatelliteRepository satelliteRepository;
	
	@Autowired
	ThresholdAlertRepository thresholdAlertRepository;

	@Test
	public void initAll(){
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
	}

}
