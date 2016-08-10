package com.orbit.actions;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.orbit.entity.Satellite;

import com.orbit.AppContext;

/**
 * the base class of action
 * */
public class AppAction extends ActionBase {


	public AppAction(){
	}

	/**
	 *
	 */
	private static final long serialVersionUID = 8647185033056627255L;

	private static Log log = LogFactory.getLog(AppAction.class);

	/**
	 * 设置登陆用户授权的型号
	 **/
	protected void setAdminModels(List<Satellite> satelites){
		this.getSession().setAttribute(AppContext.ADMIN_MODELS_KEY, satelites);
	}

	/**
	 * 获取登陆用户授权的型号
	 **/
	protected List<Satellite> getAdminModels(){
		List<Satellite> sls = null;
		Object obj = this.getSession().getAttribute(AppContext.ADMIN_MODELS_KEY);
		if(obj != null && obj instanceof List){
			sls = (List<Satellite>)obj;
		}
		return sls;
	}

    protected void setSelectedModelIds(List<Long> ids){
        this.getSession().setAttribute(AppContext.SELECTED_MODELIDS_KEY, ids);
    }

    protected List<Long> getSelectedModelIds(){
        List<Long> ids = null;
        Object obj = this.getSession().getAttribute(AppContext.SELECTED_MODELIDS_KEY);
        if(obj != null && obj instanceof List){
            ids = (List<Long>)obj;
        }
        return ids;
    }

}
