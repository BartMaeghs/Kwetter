/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yolo.sjwek.kwetter.interceptor;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import yolo.sjwek.kwetter.model.Kweet;

/**
 *
 * @author Bart
 */
/**
@Interceptor
public class FollowTrendInterceptor {
    
    @AroundInvoke
	public Object vetCoolToHardDik(InvocationContext invocationContext) throws Exception {
		Object[] parameters = invocationContext.getParameters();
		if (parameters.length > 0 && parameters[0] instanceof Kweet) {
			Kweet kweet = (Kweet) parameters[0];
			String content = kweet.getContent();
			content = content.replace("vet", "dik");
			content = content.replace("cool", "hard");
                        kweet.setContent(content);
		}		
		return invocationContext.proceed();
	}
    
}*/
