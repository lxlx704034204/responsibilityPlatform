package com.orbit.aop;

import com.orbit.entity.permission.User;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * 使用spring-aop解决用户日志记录和工作效率审计问题
 */
@Aspect
public class AuditAspect {

  private static final Logger log = LoggerFactory.getLogger(AuditAspect.class);

  /**
   * 记录用户某项从事某项活动多长时间
   */
  private Map<User, Long> userActivity;

  @Pointcut("execution(* com.orbit.service.*) && @within() && args(user)") //TODO:传入业务annotation
  public void audit(User user) {
  }


  @Around("audit(user)")
  public void auditTime(ProceedingJoinPoint jp, User user) {
    try {
      long startTime = System.currentTimeMillis();
      jp.proceed();
      long endTime = System.currentTimeMillis();

      long elapse = endTime - startTime;
      log.debug("用户:{},操作:{},时长:{}", user, elapse / 1000);
    } catch (Throwable e) {
      log.error("{}进行了{}时间");
    }
  }
}
