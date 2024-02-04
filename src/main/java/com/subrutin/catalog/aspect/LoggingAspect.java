package com.subrutin.catalog.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import com.subrutin.catalog.dto.BookDetailResponseDTO;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@Aspect
public class LoggingAspect {
	
	@Pointcut("execution(* com.subrutin.catalog.web.*.*(..))")
	private void restAPI() {}
	
	@Pointcut("within(com.subrutin.catalog.web.*)")
	private void withinPointcutExample() {}
	
	@Pointcut("args(com.subrutin.catalog.dto.PublisherCreateRequestDTO)")
	private void argsPointcutExample() {}
	
	@Pointcut("@args(com.subrutin.catalog.annotation.LogThisArg)")
	private void argsAnnotationPointcutExample() {}
	
	@Pointcut("@annotation(com.subrutin.catalog.annotation.LogThisMethod)")
	private void annotationPointcutExample() {}
	
	@Before("annotationPointcutExample()")
	public void beforeExecutedLogging() {
		log.info("this is log from aspect before method executed");
	}
	
	@After("annotationPointcutExample()")
	public void afterExecutedLogging() {
		log.info("this is log from aspect after method executed");
	}

	
	@AfterReturning("annotationPointcutExample()")
	public void afterReturnExecutedLogging() {
		log.info("this is log from aspect after returning method executed");
	}
	
	
	@AfterThrowing("annotationPointcutExample()")
	public void afterThrowingExecutedLogging() {
		log.info("this is log from aspect after throwing method executed");
	}
	
	@Around("restAPI()")
	public Object processingTimeLogging(ProceedingJoinPoint jointPoint) throws Throwable {
		StopWatch stopWatch = new StopWatch();

		
		try {
			log.info("start {}.{} ",jointPoint.getTarget().getClass().getName(),jointPoint.getSignature().getName());
			stopWatch.start();
			return jointPoint.proceed();
		} finally {
			stopWatch.stop();
			log.info("finish {}.{}  execution time = {}",jointPoint.getTarget().getClass().getName(),
					jointPoint.getSignature().getName(),
					stopWatch.getTotalTimeMillis());
		}
		


	}

}
