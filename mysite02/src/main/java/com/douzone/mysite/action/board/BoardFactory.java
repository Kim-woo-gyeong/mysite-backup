package com.douzone.mysite.action.board;

import com.douzone.web.action.Action;
import com.douzone.web.action.ActionFactory;

public class BoardFactory extends ActionFactory {

	@Override
	public Action getAction(String actionName) {
		if(actionName == null) {
			return new ListAction();
		} else {
			switch(actionName) {
			case "view" : return new ViewAction();
			case "write" : return new WriteFormAction();
			case "write_check" : return new WriteAction();
			case "delete" : return new DeleteAction();
			case "modify" : return new UpdateFormAction();
			case "update" : return new UpdateAction();
			case "reply" : return new ReplyFormAction();
			case "reply_check" : return new ReplyAction();
			default : return new ListAction();
			}
		}
	}

}
