package com.orbit.actions;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.orbit.configs.SystemConfig;
import com.orbit.AppContext;

public class ModelsActions extends ActionBase {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private static Log log = LogFactory.getLog(ModelsActions.class);

	public void jsonGetAllModels(){
		JsonResult jsonResult = null;
		try {
            JSONArray list = new JSONArray();
			for(int i =0; i < 5; i++){
				JSONObject item = new JSONObject();
				item.put("name", "型号" + i);
                item.put("id", i);
				list.add(item);
			}

			jsonResult = new JsonResultSuccess(list);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			log.error(e.getMessage(), e);
			jsonResult = new JsonResultError(e.getMessage());
		} finally {
			this.sendToClient(jsonResult);
		}
	}

    public void jsonUpdateSelectedModels(){
        JsonResult jsonResult = null;
		try {
            JSONObject json = this.getRequestJsonObject();
			String selectedModels = json.getString("selectedmodels");
            this.getSession().setAttribute(AppContext.SELECTED_MODELS_KEY, selectedModels);

			jsonResult = new JsonResultSuccess();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			log.error(e.getMessage(), e);
			jsonResult = new JsonResultError(e.getMessage());
		} finally {
			this.sendToClient(jsonResult);
		}
    }

    public void jsonGetSelectedModels(){
        JsonResult jsonResult = null;
		try {
            String selectedModels =  (String)this.getSession().getAttribute(AppContext.SELECTED_MODELS_KEY);
			// selected models
			JSONArray list = new JSONArray();

            JSONObject model1 = new JSONObject();
            model1.put("name", "型号1");

			jsonResult = new JsonResultSuccess(list);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			log.error(e.getMessage(), e);
			jsonResult = new JsonResultError(e.getMessage());
		} finally {
			this.sendToClient(jsonResult);
		}
    }

}
