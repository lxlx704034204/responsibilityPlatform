package com.orbit.repository;

import com.orbit.OrbitServiceApplication;
import com.orbit.entity.permission.User;
import com.orbit.repsitory.UserRepository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = OrbitServiceApplication.class)
public class ThresholdAlertRepositoryTests {

  @Autowired
  UserRepository repository;

  @Test
  public void contextLoads() {
  }

  @Test
  public void testCreate() {
    // save a couple of customers
    repository.save(new User("张三"));
    repository.save(new User("李四"));
    repository.save(new User("王五"));
  }
}
