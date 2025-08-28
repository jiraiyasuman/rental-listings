package com.rental_listing_landlord.landlord_post.aop;

import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class PropertyPostAspect {

	private Logger LOGGER = Logger.getLogger(getClass().getName());
	@Pointcut("execution(* com.rental_listing_landlord.landlord_post.*.*(...))")
	private void forControllerPackage() {
		LOGGER.info("PropertyPostController class is being executed....");
	}
	@Pointcut("execution(* com.rental_listing_landlord.landlord_post.*.*(...))")
	private void forServicePckage() {
		LOGGER.info("PropertyPostService class is being executed");
	}
	@Pointcut("execution(* com.rental_listing_landlord.landlord_post.*.*(..))")
	private void forRepositoryPackage() {
		LOGGER.info("PropertyPostRepository class is being executed...");
	}
	@Pointcut("forControllerPackage()||forServicePackage()||forRepositoryPackage()")
	private void forAppFlow() {
		LOGGER.info("---------------------------------");
	}
	@Before("forAppFlow()")
	public void before(JoinPoint joinPoint) {
		String method = joinPoint.getSignature().toShortString();
		LOGGER.info("In Before: calling method:"+method);
		Object[]  args = joinPoint.getArgs();
		for(Object tempArg: args) {
			LOGGER.info("argument"+args);
		}
	}
	@After("execution(* com.rental_listing_landlord.landlord_post.*.*(..)))")
	public void aftermethodExecution() {
	     LOGGER.info("After Advice: Method execution completed");
	}
	@AfterReturning(pointcut ="forAppFlow()", returning="theResult")
	public void afterReturning(JoinPoint joinPoint, Object theResult) {
		// display method we are returning from 
				String method = joinPoint.getSignature().toShortString();
				LOGGER.info("====> In AfterReturning: from method:"+method);
				// display data returned
				LOGGER.info("========> Result:"+theResult);
	}
	@Around("execution(* com.rental_listing_landlord.landlord_post.service.*.*(..))))")
	public void measureExecutionTime(ProceedingJoinPoint joinPoint)throws Throwable{
		long start = System.currentTimeMillis();
		Object result = joinPoint.proceed();
		long executionTime = System.currentTimeMillis();
		System.out.println(joinPoint.getSignature()+"executed in "+executionTime+"ms");
		
	}
	
}
