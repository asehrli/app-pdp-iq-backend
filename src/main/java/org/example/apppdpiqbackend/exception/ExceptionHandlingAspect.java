package org.example.apppdpiqbackend.exception;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ExceptionHandlingAspect {
    @AfterThrowing(pointcut = "execution(* org.example.apppdpiqbackend.*.*.delete(..))")
    public void deleteHandleException() {
        throw new MyNotFoundException("Delete item bo'lmadi");
    }

    @AfterThrowing(pointcut = "execution(* org.example.apppdpiqbackend.*.*.add(..))")
    public void addHandleException() {
        throw new MyConflictException("Bunday nomli item allaqachon bor");
    }

    @AfterThrowing(pointcut = "execution(* org.example.apppdpiqbackend.*.*.one(..))")
    public void oneHandleException() {
        throw new MyNotFoundException("Bunday nomli item topilmadi");
    }
}
