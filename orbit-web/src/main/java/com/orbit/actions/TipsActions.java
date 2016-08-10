package com.orbit.actions;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class TipsActions extends AppAction {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private static int tip_level3limit = 90;

	private static Log log = LogFactory.getLog(TipsActions.class);

	public void jsonGetSummary(){
		JsonResult jsonResult = null;
		try {
			JSONObject sum = new JSONObject();
			sum.put("level3limit", tip_level3limit + "/200");
			tip_level3limit += 5;
			tip_level3limit = tip_level3limit >= 200 ? 50 : tip_level3limit;

			jsonResult = new JsonResultSuccess(sum);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			jsonResult = new JsonResultError(e.getMessage());
		} finally {
			this.sendToClient(jsonResult);
		}
	}

}
