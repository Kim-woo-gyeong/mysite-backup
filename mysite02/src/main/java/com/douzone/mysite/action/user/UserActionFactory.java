package com.douzone.mysite.action.user;

import com.douzone.mysite.action.main.MainAction;
import com.douzone.web.action.Action;
import com.douzone.web.action.ActionFactory;

public class UserActionFactory extends ActionFactory {

	@Override
	public Action getAction(String actionName) {
		if("joinform".equals(actionName)) {
			return new JoinFormAction();
			
		} else if("join".equals(actionName)) {
			return new JoinAction();
			
		} else if("joinsuccess".equals(actionName)) {
			return new JoinSuccessAction();
			
		} else if("loginform".equals(actionName)){
			return new LoginFormAction();
			
		} else if("login".equals(actionName)) {
			return new LoginAction();
			
		} else if("logout".equals(actionName)) {
			return new LogoutAction();
			
		} else if("updateform".equals(actionName)) {
			return new UpdateFormAction();
		} else if("update".equals(actionName)){
			return new UpdateAction();
		}else {
			return new MainAction();
		}
	}

}
