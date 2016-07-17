package com.orbit.actions;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Level3rdLimitActions extends ActionBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static Log log = LogFactory.getLog(Level3rdLimitActions.class);
	
	public String pageIndex(){
		return SUCCESS;
	}
	
	public void jsonGetListByPager(){
		JsonResult jsonResult = null;
		try {
			JSONObject json = this.getRequestJsonObject();
			//JSONObject searcherJson = json.getJSONObject("searcher");
			//JSONObject pagerJson = json.getJSONObject("pager");

			//String searchKey = searcherJson.getString("keyword");
			//Integer pageIndex = pagerJson.getInt("pageIndex");
			

			//
			JSONArray list = new JSONArray();
			for(int i =0; i < 10; i++){
				JSONObject item = new JSONObject();
				item.put("serialno", i + 1);
				item.put("id", i + 1);
				item.put("modecode", "XXX" + i);
				item.put("alertstartdt", "2016-06-06 00:09:30");
				item.put("alertenddt", "2016-06-06 00:09:50");
				item.put("alertmsg", "Warn...");
				item.put("eventtype", "类别" + (i + 1));
				item.put("desc", "");
				item.put("conformperson", "");
				item.put("conformdt", "");
				list.add(item);
			}

			jsonResult = new JsonResultSuccess(list);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			jsonResult = new JsonResultError(e.getMessage());
		} finally {
			this.sendToClient(jsonResult);
		}
	}

}
