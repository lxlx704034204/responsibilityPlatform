package com.orbit.actions;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class HomeActions extends ActionBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static Log log = LogFactory.getLog(HomeActions.class);
	
	public String pageIndex(){
		return SUCCESS;
	}

}
