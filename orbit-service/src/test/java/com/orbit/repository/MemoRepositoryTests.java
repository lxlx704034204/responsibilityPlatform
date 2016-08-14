package com.orbit.repository;

import com.orbit.OrbitServiceApplication;
import com.orbit.entity.Memo;
import com.orbit.entity.Satellite;
import com.orbit.entity.TeleControl;
import com.orbit.entity.permission.User;
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

/**
 * 测控事件的repository测试
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = OrbitServiceApplication.class)
public class MemoRepositoryTests {

  @Autowired
  MemoRepository repository;

  @Autowired
  UserRepository userRepository;

  @Autowired
  SatelliteRepository satelliteRepository;

  Memo memo1, memo2, memo3, memo4, memo5, memo6;
  User confirmUser;

  Satellite s1, s2;

  @Before
  public void setUp() {
    confirmUser = new User("责任人MemoRepositoryTests_1");
    userRepository.save(confirmUser);

    s1 = new Satellite("型号MemoRepositoryTests_1");
    s1.setAdminUser(confirmUser);
    satelliteRepository.save(s1);

    s2 = new Satellite("型号MemoRepositoryTests_2");
    s2.setAdminUser(confirmUser);
    satelliteRepository.save(s2);

    confirmUser.addSatellite(s1);
    confirmUser.addSatellite(s2);

    memo1 = new Memo(s1, "测试异常现象描述1_MemoRepositoryTests");
    memo1.setConfirmUser(confirmUser);
    memo1.setConfirmTime(new Date());
    repository.save(memo1);

    memo2 = new Memo(s1, "测试异常现象描述2_MemoRepositoryTests");
    memo2.setConfirmUser(confirmUser);
    memo2.setConfirmTime(new Date());
    repository.save(memo2);

    memo3 = new Memo(s1, "测试异常现象描述3_MemoRepositoryTests");
    memo3.setConfirmUser(confirmUser);
    memo3.setConfirmTime(new Date());
    repository.save(memo3);

    memo4 = new Memo(s1, "测试异常现象描述4_MemoRepositoryTests");
    memo4.setConfirmUser(confirmUser);
    memo4.setConfirmTime(new Date());
    repository.save(memo4);

    memo5 = new Memo(s2, "型号2_测试异常现象描述1_MemoRepositoryTests");
    memo5.setConfirmUser(confirmUser);
    memo5.setConfirmTime(new Date());
    repository.save(memo5);

    memo6 = new Memo(s2, "型号2_测试异常现象描述2_MemoRepositoryTests");
    memo6.setConfirmUser(confirmUser);
    memo6.setConfirmTime(new Date());
    repository.save(memo6);

  }

  @After
  public void destroy() {
    repository.delete(memo1.getId());
    repository.delete(memo2.getId());
    repository.delete(memo3.getId());
    repository.delete(memo4.getId());
    repository.delete(memo5.getId());
    repository.delete(memo6.getId());

    //TODO:先删除satellite再删除user会报错,id=x的satellite找不着
    userRepository.delete(confirmUser);
    satelliteRepository.delete(s1.getId());
    satelliteRepository.delete(s2.getId());
  }

  @Test
  public void testFindBySatelliteIdAndStartTimeBetween() {
    //这里可以作为前台UI调用的demo,分页查询如何向后台传递参数,页码从0开始,默认按照"事件"降序排序
    PageRequest pageRequest = new PageRequest(0, 5, new Sort(new Sort.Order(Sort.Direction.DESC, "eventTime")));
    Calendar yesterday = Calendar.getInstance();
    yesterday.add(Calendar.DAY_OF_MONTH, -1);

    Calendar tomorrow = Calendar.getInstance();
    tomorrow.add(Calendar.DAY_OF_MONTH, 1);

    Page<Memo> pageResult = repository.findBySatelliteIdAndEventTimeBetween(Arrays.asList(s1.getId()), yesterday.getTime(), tomorrow.getTime()
            , pageRequest);
    Assert.assertTrue(pageResult != null && pageResult.getNumberOfElements() >= 0);
    System.out.println("总行数=" + pageResult.getTotalElements());
    System.out.println("总页数=" + pageResult.getTotalPages());
    System.out.println("记录编号到=" + pageResult.getNumberOfElements());
    System.out.println("getNumber()=" + pageResult.getNumber());
    System.out.println("getSize()当前页内记录数=" + pageResult.getSize());

    System.out.println("备忘录如下:");
    //如下查看排序效果
    for (Memo memo :
            pageResult.getContent()) {
      System.out.println(memo);
    }
  }

  /**
   * 测试批量确认
   */
  @Test
  public void testBatchConfirm() throws Exception {
    int affectedSize = repository.batchConfirm(confirmUser.getId(), new Date(), memo1.getId(), memo2.getId(), memo3.getId(), memo4.getId(), memo5.getId());
    Assert.assertEquals(5, affectedSize);
  }
}
