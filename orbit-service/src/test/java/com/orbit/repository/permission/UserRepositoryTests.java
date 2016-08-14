package com.orbit.repository.permission;

import com.orbit.OrbitServiceApplication;
import com.orbit.entity.permission.User;

import org.junit.Assert;
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
    String password = "123";
    // save a couple of customers
    User user1 = new User("张三");
    User user2 = new User("李四");
    User user3 = new User("王五");

    user1.setPassword(password);
    user2.setPassword(password);
    user3.setPassword(password);
    repository.save(user1);
    repository.save(user2);
    repository.save(user2);

    User user1Return = repository.getOne(user1.getId());
    User user2Return = repository.getOne(user2.getId());
    User user3Return = repository.getOne(user2.getId());

//    Assert.assertEquals("123", user1Return.getPassword());
//    Assert.assertEquals("123", user2Return.getPassword());
//    Assert.assertEquals("123", user3Return.getPassword());

    repository.delete(user1);
    repository.delete(user2);
    repository.delete(user3);
  }
}
