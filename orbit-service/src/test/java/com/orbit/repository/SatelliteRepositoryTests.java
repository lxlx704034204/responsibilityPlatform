package com.orbit.repository;

import com.orbit.OrbitServiceApplication;
import com.orbit.entity.Satellite;
import com.orbit.entity.permission.User;
import com.orbit.repsitory.SatelliteRepository;
import com.orbit.repsitory.permission.UserRepository;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

    List<Satellite> satellitesAdmins = repository.findAllByAdminUserLoginName("张三");
    Assert.assertTrue(satellitesAdmins.size() >= 1);

    repository.delete(s1);
  }

  @Test
  public void testfindByAdminUserLoginName() {
    User user1 = new User("张三");
    userRepository.save(user1);

    Satellite s1 = new Satellite("型号1");
    s1.setAdminUser(user1);
    Satellite s2 = new Satellite("型号2");
    s2.setAdminUser(user1);
    Satellite s3 = new Satellite("型号3");
    s3.setAdminUser(user1);
    Satellite s4 = new Satellite("型号4");
    s4.setAdminUser(user1);

    s1 = repository.save(s1);
    s2 = repository.save(s2);
    s3 = repository.save(s3);
    s4 = repository.save(s4);

    Page<Satellite> satellitesAdmins = repository.findByAdminUserLoginName("张三", new PageRequest(0, 3));
    Assert.assertTrue(satellitesAdmins.getTotalElements() >= 1);

    repository.delete(s1);
    repository.delete(s2);
    repository.delete(s3);
    repository.delete(s4);
  }
}
