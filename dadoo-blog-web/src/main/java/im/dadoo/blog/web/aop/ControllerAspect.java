/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package java.im.dadoo.blog.web.aop;

import java.util.HashMap;
import java.util.Map;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 *
 * @author shuwen.zsw
 */
@Aspect
@Component
public class ControllerAspect {
  
  private static final Logger logger = LoggerFactory.getLogger("im.dadoo.blog.web.controller");
  
  @Around("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
  public Object stat(ProceedingJoinPoint pjp) throws Throwable {
    long t1 = System.currentTimeMillis();
    Object ret = pjp.proceed();
    long t2 = System.currentTimeMillis();
    String sig = pjp.getSignature().toLongString();
    Map<String, Object> map = new HashMap<>();
    map.put("sig", sig);
    map.put("runtime", t2 - t1);
    logger.info(map.toString());
    return ret;
  }
}
