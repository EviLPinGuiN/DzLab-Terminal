package com.dz.tele2.aspect;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * Created by Alex on 28.09.15.
 */
@Component
@Aspect
public class ExceptionLogger {

    /**
     * This AspectJ class used to log all exception in readable form.
     */

    private final Logger LOG = Logger.getLogger(getClass());

    @AfterThrowing(pointcut = "execution (* com.dz.tele2.*..*(..))", throwing = "exception")
    public void logException(final JoinPoint joinPoint, final Exception exception) {
        final String className = joinPoint.getSignature().getDeclaringTypeName();
        final String methodName = joinPoint.getSignature().getName();
        LOG.error(String.format("Exception occurred in %s class at %s method.\nMessage: %s \nCause: %s\nStacktrace: %s",
                className, methodName, exception.getMessage(), exception.getCause(), ExceptionUtils.getStackTrace(exception)));
    }
}
