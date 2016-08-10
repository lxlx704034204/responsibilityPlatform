package com.orbit.actions;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class TaskDistActions extends AppAction {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private static Log log = LogFactory.getLog(TaskDistActions.class);

	public String pageIndexList(){
		return SUCCESS;
	}

	public String pageIndexGraph(){
		return SUCCESS;
	}

}
