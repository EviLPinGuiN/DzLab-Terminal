package com.dz.tele2.aspect;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;


/**
 * Created by Alex on 27.09.15.
 */
@Component
@Aspect
public class MethodExecutionLogger {

    /**
     * This AspectJ class used to log method calls and returned values.
     */

    private final Logger LOG = Logger.getLogger(getClass());

    @Before("execution(* com.dz.tele2.*..*(..))")
    public void logMethodCalls(final JoinPoint joinPoint) {
        final Object[] args = joinPoint.getArgs();
        final String className = joinPoint.getSignature().getDeclaringTypeName();
        final String methodName = joinPoint.getSignature().getName();
        String argumentsString = "";
        for (Object arg : args) {
            argumentsString += arg.toString() + "; ";
        }
        if (LOG.isDebugEnabled()) {
            LOG.debug(String.format("Called method \"%s()\" from class \"%s\" with arguments: %s",
                    methodName, className, argumentsString));
        }
    }

    @AfterReturning(pointcut = "execution(* com.dz.tele2.*..*(..))", returning = "returnValue")
    public void logReturnValues(final JoinPoint joinPoint, final Object returnValue) {
        final String className = joinPoint.getSignature().getDeclaringTypeName();
        final String methodName = joinPoint.getSignature().getName();
        if (LOG.isDebugEnabled()) {
            LOG.debug(String.format("Method \"%s()\" from class \"%s\" returned: %s",
                    methodName, className, returnValue.toString()));
        }
    }


}
