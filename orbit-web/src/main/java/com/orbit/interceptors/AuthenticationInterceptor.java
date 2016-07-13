package com.orbit.interceptors;

import java.util.Map;

import com.orbit.AppContext;
import com.orbit.models.User;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

public class AuthenticationInterceptor implements Interceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4066981065162057297L;

	@Override
	public void destroy() {
		
	}

	@Override
	public void init() {
		
	}

	@Override
	public String intercept(ActionInvocation actionInvocation) throws Exception {
		Map<String, Object> session = actionInvocation.getInvocationContext().getSession();
        User user = (User) session.get(AppContext.USER_KEY);

        boolean isAuthenticated = (user != null) && (user.getName().trim().length() > 0);

        if (!isAuthenticated) {
            return Action.LOGIN;
        }
        else {
            return actionInvocation.invoke();
        }
	}

}
