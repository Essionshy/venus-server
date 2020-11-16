package com.tingyu.venus.aspect;

import com.tingyu.venus.exception.ResultException;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 日志切面
 * @Author essionshy
 * @Create 2020/11/11 10:03
 * @Version venus-server
 */
@Slf4j
@Component
@Aspect
public class LogAspect {

    //定义切点
    @Pointcut("@annotation(io.swagger.annotations.ApiOperation)")
    public void logAspect(){

    }


    //环绕通知

    @Around("logAspect()")
    public Object around(ProceedingJoinPoint pjp){

        Object result=null;
        Signature signature = pjp.getSignature();
        MethodSignature methodSignature= (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        ApiOperation apiOperation = method.getAnnotation(ApiOperation.class);
        long startTime = System.currentTimeMillis();
        String apiOperationName="";
        if(null !=apiOperation){
            apiOperationName= apiOperation.value();
        }

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        Object[] args = pjp.getArgs();
        List<Object> argsList=Arrays.asList(args);
        List<Object> logArgs = argsList.stream()
                .filter(arg -> (!(arg instanceof HttpServletRequest) && !(arg instanceof HttpServletResponse))||arg instanceof MultipartFile)
                .collect(Collectors.toList());

        log.info("请求链接：{}", request.getRequestURL().toString());
        log.info("请求SessionID:{}", request.getSession().getId());
        log.info("接口描述信息：{}", apiOperationName);
        log.info("请求类型:{}", request.getMethod());
        log.info("请求方法:{}.{}", signature.getDeclaringTypeName(), signature.getName());
        log.info("请求IP:{}", request.getRemoteAddr());
       // log.info("请求入参：{}", JSON.toJSONString(logArgs));

        try {
            result = pjp.proceed();
        } catch (Throwable throwable) {

            if(throwable instanceof ResultException){
                ResultException ex= (ResultException) throwable;
                throw  ex;
            }

            throwable.printStackTrace();
        }finally {
            long endTime=System.currentTimeMillis();
            log.info("请求时长：{}ms",endTime-startTime);

        }
        return result;
    }


}
