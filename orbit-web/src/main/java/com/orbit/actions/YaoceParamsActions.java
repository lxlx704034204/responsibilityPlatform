package com.orbit.actions;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.orbit.configs.SystemConfig;

public class YaoceParamsActions extends ActionBase {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private static Log log = LogFactory.getLog(YaoceParamsActions.class);

	public String pageIndex(){
		return SUCCESS;
	}

	public void jsonGetListByPager(){
		JsonResult jsonResult = null;
		try {
			JSONObject json = this.getRequestJsonObject();
			JSONObject searcherJson = json.getJSONObject("searcher");
			JSONObject pagerJson = json.getJSONObject("pager");

			String searchKey = searcherJson.getString("keyword");
			Integer pageIndex = pagerJson.getInt("pageIndex");
			Integer pageSize = SystemConfig.getSystemCommonListPageSize();

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

			Integer recordCount = 23;
			PageInfo pageInfo = new PageInfo();
			pageInfo.setPageIndex(pageIndex);
			pageInfo.setPageSize(pageSize);
			pageInfo.setRecordCount(recordCount);

			JSONObject listingData = new JSONObject();
			listingData.put("records", list);
			listingData.put("pageInfo", pageInfo);

			jsonResult = new JsonResultSuccess(listingData);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			log.error(e.getMessage(), e);
			jsonResult = new JsonResultError(e.getMessage());
		} finally {
			this.sendToClient(jsonResult);
		}
	}

}
