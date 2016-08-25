package com.orbit.actions;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import com.orbit.OrbitServiceApplication;
import com.orbit.configs.SystemConfig;
import com.orbit.entity.Satellite;
import com.orbit.entity.ThresholdAlert;
import com.orbit.repository.SatelliteRepository;
import com.orbit.repository.ThresholdAlertRepository;
import com.orbit.repository.permission.UserRepository;
import com.orbit.utils.DateTimeUtils;
import com.orbit.utils.StrUtils;

@SpringApplicationConfiguration(classes = OrbitServiceApplication.class)
public class YaoceParamsActions extends AppAction {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private static Log log = LogFactory.getLog(YaoceParamsActions.class);
	
	@Autowired
    SatelliteRepository slRepo;

	@Autowired
    UserRepository userRepo;

	@Autowired
    ThresholdAlertRepository thRepo;

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
			JSONArray models = searcherJson.getJSONArray("models");
			String alertstarttime = searcherJson.getString("alertstarttime");
			String alertendtime = searcherJson.getString("alertendtime");
			alertstarttime = StrUtils.isNullOrEmpty(alertstarttime) ? null : alertstarttime + ":00";
			alertendtime = StrUtils.isNullOrEmpty(alertendtime) ? null : alertendtime + ":00";

			List<Long> selectedModelIds =  JSONArray.toList(models, Long.class);
			Date startDate = DateTimeUtils.parseISODatetime(alertstarttime);
			Date endDate = DateTimeUtils.parseISODatetime(alertendtime);
			
			Integer pageIndex = pagerJson.getInt("pageIndex");
			Integer pageSize = SystemConfig.getSystemCommonListPageSize();
			PageRequest pageRequest = new PageRequest(pageIndex, pageSize, new Sort(new Sort.Order(Sort.Direction.DESC, "startTime")));
			
//			Page<ThresholdAlert> pageResult = thRepo.findBySatelliteIdAndStartTimeBetween(selectedModelIds, startDate, endDate, pageRequest);
//			List<ThresholdAlert> alerts = pageResult.getContent();
//			Long recordCount = pageResult.getTotalElements();
			
			Long recordCount = 388l;
//			List<ThresholdAlert> alerts = new ArrayList<ThresholdAlert>();
//			Satellite sl = new Satellite("型号1");
//			sl.setCode("sl1");
//			for(int i = (pageSize * pageIndex); i<(pageSize*(pageIndex+1)); i++){
//				System.out.println("i: " + i);
//				ThresholdAlert alert = new ThresholdAlert(sl, "异常描述信息" + i);
//				alert.setId((long) i);
//				if(i > recordCount){
//					break;
//				}
//				alerts.add(alert);
//			}
			
			
			JSONArray list = new JSONArray();
//			for (ThresholdAlert alert : alerts) {
			for(int i = 0; i < 30; i++){
				JSONObject item = new JSONObject();
				item.put("serialno", 0);
				item.put("id", i);
				item.put("modecode", "xxx");
				item.put("paramid", "param-" + i);
				item.put("paramname", "xyz");
				item.put("result", "");
				item.put("exceptiondesc", "def");
				item.put("trendpic", "");
				list.add(item);
		    }

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
