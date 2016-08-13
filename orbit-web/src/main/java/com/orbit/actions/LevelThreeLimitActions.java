package com.orbit.actions;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.orbit.configs.SystemConfig;
import com.orbit.AppContext;
import com.orbit.OrbitServiceApplication;
import com.orbit.entity.Satellite;
import com.orbit.entity.ThresholdAlert;
import com.orbit.entity.permission.User;
import com.orbit.repository.SatelliteRepository;
import com.orbit.repository.permission.UserRepository;
import com.orbit.repository.ThresholdAlertRepository;
import com.orbit.utils.DateTimeUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Date;

@SpringApplicationConfiguration(classes = OrbitServiceApplication.class)
public class LevelThreeLimitActions extends AppAction {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private static Log log = LogFactory.getLog(LevelThreeLimitActions.class);

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

			List<Long> selectedModelIds =  JSONArray.toList(models, Long.class);
			Integer pageIndex = pagerJson.getInt("pageIndex");
			Integer pageSize = SystemConfig.getSystemCommonListPageSize();

			PageRequest pageRequest = new PageRequest(pageIndex, pageSize, new Sort(new Sort.Order(Sort.Direction.DESC, "startTime")));
			Date startDate = DateTimeUtils.parseISODatetime(alertstarttime);
			Date endDate = DateTimeUtils.parseISODatetime(alertendtime);
			Page<ThresholdAlert> pageResult = thRepo.findBySatelliteIdAndStartTimeBetween(selectedModelIds, startDate, endDate, pageRequest);

			JSONArray list = new JSONArray();
			for (ThresholdAlert alert :
		            pageResult.getContent()) {
				JSONObject item = new JSONObject();
				item.put("serialno", 0);
				item.put("id", alert.getId());
				item.put("modecode", alert.getSatellite().getCode());
				item.put("alertstartdt", DateTimeUtils.datetimeFormat.format(alert.getStartTime()));
				item.put("alertenddt", DateTimeUtils.datetimeFormat.format(alert.getEndTime()));
				item.put("alertmsg", alert.getMessage());
				item.put("eventtype", alert.getSeverityLevel().name());
				item.put("desc", "");
				item.put("conformperson", alert.getConfirmUser().getFullName());
				item.put("conformdt", DateTimeUtils.datetimeFormat.format(alert.getConfirmTime()));
				list.add(item);
		    }

			PageInfo pageInfo = new PageInfo();
			pageInfo.setPageIndex(pageIndex);
			pageInfo.setPageSize(pageSize);
			pageInfo.setRecordCount(pageResult.getTotalElements());

			JSONObject listingData = new JSONObject();
			listingData.put("records", list);
			listingData.put("pageInfo", pageInfo);

			jsonResult = new JsonResultSuccess(listingData);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			jsonResult = new JsonResultError(e.getMessage());
		} finally {
			this.sendToClient(jsonResult);
		}
	}

}
