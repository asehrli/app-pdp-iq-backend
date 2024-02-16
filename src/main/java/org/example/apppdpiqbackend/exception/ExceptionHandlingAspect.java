package org.example.apppdpiqbackend.exception;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ExceptionHandlingAspect {
    @AfterThrowing(pointcut = "execution(* org.example.apppdpiqbackend.*.*.delete(..))")
    public void handleException() {
        throw new MyNotFoundException("Delete item bo'lmadi");
    }
}
