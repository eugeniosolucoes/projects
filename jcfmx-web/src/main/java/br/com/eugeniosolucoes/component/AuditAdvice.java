/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.eugeniosolucoes.component;

import br.com.eugeniosolucoes.service.impl.AuditService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author eugenio
 */
@Aspect
@Component
public class AuditAdvice {

    @Autowired
    private AuditService auditService;

    @AfterReturning(
             pointcut = "execution(* br.com.eugeniosolucoes.controller.ClienteController.listar(..))",
            returning = "result" )
    public void logAfterReturning( JoinPoint joinPoint, Object result ) {

        System.out.println( "logAfterReturning() is running!" );
        System.out.println( "hijacked : " + joinPoint.getSignature().getName() );
        System.out.println( "Method returned value is : " + result );
        System.out.println( "******" );

    }
}
