package com.orbit.repository.permission;

import com.orbit.OrbitServiceApplication;
import com.orbit.entity.permission.User;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = OrbitServiceApplication.class)
public class UserRepositoryTests {

  @Autowired
  UserRepository repository;

  @Test
  public void contextLoads() {
  }

  @Test
  public void testCreateAndDelete() {
    // save a couple of customers
    User user1 = new User("张三");
    User user2 = new User("李四");
    User user3 = new User("王五");
    repository.save(user1);
    repository.save(user2);
    repository.save(user3);

    repository.delete(user1);
    repository.delete(user2);
    repository.delete(user3);
  }
}
