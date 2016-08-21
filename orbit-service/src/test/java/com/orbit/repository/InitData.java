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
	
	User adminUser;
	Satellite sl1, sl2, sl3;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	SatelliteRepository satelliteRepository;
	
	@Test
	public void createUser(){
		adminUser = new User("adminuser");
		adminUser.setFullName("管理员");
		userRepository.save(adminUser);
	}
	
	@Test
	public void createSatelites(){
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
		
	}

}
