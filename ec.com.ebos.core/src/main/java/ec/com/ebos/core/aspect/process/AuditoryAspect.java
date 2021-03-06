package ec.com.ebos.core.aspect.process;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import ec.com.ebos.core.aspect.annotation.Auditable;
import ec.com.ebos.core.root.model.Entidad;
import ec.com.ebos.core.security.model.Usuario;
import ec.com.ebos.core.security.service.SecurityS;
import ec.com.ebos.core.util.EntityUtils;

/**
 * Auditory aspects
 * 
 * @author Eduardo Plua Alay
 * @since 2013/02/13
 */
@Aspect
@Component
public class AuditoryAspect {

	@Getter @Setter
    @Autowired
    @Qualifier(SecurityS.BEAN_NAME)
    private SecurityS securityS;

	
	/*@Before("execution(* com.mkyong.customer.bo.CustomerBo.addCustomer(..))")
	public void logBefore(JoinPoint joinPoint) {

		System.out.println("logBefore() is running!");
		System.out.println("hijacked : " + joinPoint.getSignature().getName());
		System.out.println("******");
	}*/

	/*@After("execution(* com.mkyong.customer.bo.CustomerBo.addCustomer(..))")
	public void logAfter(JoinPoint joinPoint) {

		System.out.println("logAfter() is running!");
		System.out.println("hijacked : " + joinPoint.getSignature().getName());
		System.out.println("******");

	}*/
	
	/*@AfterReturning(
			pointcut = "execution(* com.mkyong.customer.bo.CustomerBo.addCustomerReturnValue(..))",
			returning= "result")
	public void logAfterReturning(JoinPoint joinPoint, Object result) {

		System.out.println("logAfterReturning() is running!");
		System.out.println("hijacked : " + joinPoint.getSignature().getName());
		System.out.println("Method returned value is : " + result);
		System.out.println("******");

	}*/
	
	/*@AfterThrowing(
			pointcut = "execution(* com.mkyong.customer.bo.CustomerBo.addCustomerThrowException(..))",
			throwing= "error")
	public void logAfterThrowing(JoinPoint joinPoint, Throwable error) {

		System.out.println("logAfterThrowing() is running!");
		System.out.println("hijacked : " + joinPoint.getSignature().getName());
		System.out.println("Exception : " + error);
		System.out.println("******");

	}*/
	
//	@Around("execution(* ec.com.ebos.security.core.process.SecurityP.iniciarSesion(..))")
//	public void loginAround(ProceedingJoinPoint joinPoint) throws Throwable {
//
//		System.out.println("logAround() is running!");
//		System.out.println("hijacked method : " + joinPoint.getSignature().getName());
//		System.out.println("hijacked arguments : " + Arrays.toString(joinPoint.getArgs()));
//		
//		System.out.println("Around before is running!");
//		joinPoint.proceed();
//		System.out.println("Around after is running!");
//		
//		System.out.println("******");
//
//	}
	
	@AfterReturning(pointcut = "execution(* ec.com.ebos.*.core.service.*S.create*(..))",
			returning= "entity")
	public void createEntity(JoinPoint joinPoint, Entidad entity){
		
		if(entity.getClass().getAnnotation(Auditable.class) != null){
			entity.setAuditoria(securityS.getSessionBean().getAuditoria());
			Usuario usuario = securityS.getSessionBean().getUsuario();
			entity.setCreador(usuario);
			entity.setCreado(new Date());
		}
	}
	
	
	@Before("execution(* ec.com.ebos.*.core.service.*S.save*(..))")	
	public void saveEntity(JoinPoint joinPoint){
		Entidad entity = (Entidad) joinPoint.getArgs()[0];
		
		if(entity.getClass().getAnnotation(Auditable.class) != null && entity.getAuditoria() != null){
			Usuario usuario = securityS.getSessionBean().getUsuario();			
			Date date = new Date();
	        if (EntityUtils.isPersistent(entity)) {
	        	entity.setModificador(usuario);
	        	entity.setModificado(date);
	        } else {
	        	entity.setCreador(usuario);
	            entity.setCreado(date);                       
	        }
		}
	}

}