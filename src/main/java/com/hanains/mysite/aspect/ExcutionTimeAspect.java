package com.hanains.mysite.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class ExcutionTimeAspect {

	@Around("execution(* *..dao.*.*(..)) " )
	public Object afround(ProceedingJoinPoint pjp) throws Throwable{
		
//		Object[] params = {"Camera"};
//		Object result =  pjp.proceed(params);
		
		String taskName= pjp.getTarget().getClass() + "." + pjp.getSignature().getName();
		StopWatch stopWatch = new StopWatch();
		stopWatch.start(taskName);
		
		Object result =  pjp.proceed();
		
		
		stopWatch.stop();
		System.out.println("Excution time : " + taskName + " " + stopWatch.getTotalTimeMillis());
		
		
		return result;
	}
}
