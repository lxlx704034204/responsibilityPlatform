package com.orbit.repository;

import com.orbit.OrbitServiceApplication;
import com.orbit.entity.Satellite;
import com.orbit.entity.permission.User;
import com.orbit.repsitory.SatelliteRepository;
import com.orbit.repsitory.UserRepository;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = OrbitServiceApplication.class)
public class SatelliteRepositoryTests {

  @Autowired
  SatelliteRepository repository;

  @Autowired
  UserRepository userRepository;

  @Test
  public void testCreateAndDelete() {
    User user1 = new User("张三");
    userRepository.save(user1);

    Satellite s1 = new Satellite("型号1");
    s1.setAdminUser(user1);

    s1 = repository.save(s1);

    List<Satellite> satellitesAdmins = repository.findByAdminUserLoginName("张三");
    Assert.assertTrue(satellitesAdmins.size() >= 1);

    repository.delete(s1);
  }
}
