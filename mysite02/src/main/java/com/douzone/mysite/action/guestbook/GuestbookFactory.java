package com.douzone.mysite.action.guestbook;

import com.douzone.web.action.Action;
import com.douzone.web.action.ActionFactory;

public class GuestbookFactory extends ActionFactory {

	@Override
	public Action getAction(String actionName) {
		
		if(actionName == null) {
			return new ListAction();
		} else {
			switch(actionName) {
			case "insert": return new InsertAction();
			case "deleteform": return new DeleteFormAction();
			case "delete": return new DeleteAction();
			default:return new ListAction();
			}	
		}
	}

}
