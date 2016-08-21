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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import com.orbit.OrbitServiceApplication;
import com.orbit.configs.SystemConfig;
import com.orbit.entity.Satellite;
import com.orbit.entity.TeleControl;
import com.orbit.entity.ThresholdAlert;
import com.orbit.repository.SatelliteRepository;
import com.orbit.repository.TeleControlRepository;
import com.orbit.repository.ThresholdAlertRepository;
import com.orbit.repository.permission.UserRepository;
import com.orbit.utils.DateTimeUtils;
import com.orbit.utils.StrUtils;

@SpringApplicationConfiguration(classes = OrbitServiceApplication.class)
public class CekongEventsActions extends AppAction {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private static Log log = LogFactory.getLog(CekongEventsActions.class);
	
	@Autowired
    SatelliteRepository slRepo;

	@Autowired
    UserRepository userRepo;

	@Autowired
    ThresholdAlertRepository thRepo;
	
//	@Autowired
//	TeleControlRepository tcRepo;

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
			String starttime = searcherJson.getString("starttime");
			String endtime = searcherJson.getString("endtime");
			starttime = StrUtils.isNullOrEmpty(starttime) ? null : starttime + ":00";
			endtime = StrUtils.isNullOrEmpty(endtime) ? null : endtime + ":00";

			List<Long> selectedModelIds =  JSONArray.toList(models, Long.class);
			Date startDate = DateTimeUtils.parseISODatetime(starttime);
			Date endDate = DateTimeUtils.parseISODatetime(endtime);
			
			Integer pageIndex = pagerJson.getInt("pageIndex");
			Integer pageSize = SystemConfig.getSystemCommonListPageSize();
			PageRequest pageRequest = new PageRequest(pageIndex, pageSize, new Sort(new Sort.Order(Sort.Direction.DESC, "startTime")));
			
//			Page<TeleControl> pageResult = tcRepo.findBySatelliteIdAndStartTimeBetween(selectedModelIds, startDate, endDate, pageRequest);
//			List<TeleControl> alerts = pageResult.getContent();
//			Long recordCount = pageResult.getTotalElements();
			
			Long recordCount = 88l;
			List<TeleControl> ctrls = new ArrayList<TeleControl>();
			Satellite sl = new Satellite("型号1");
			sl.setCode("sl1");
			for(int i = (pageSize * pageIndex); i<(pageSize*(pageIndex+1)); i++){
				TeleControl ctrl = new TeleControl(sl, "异常描述信息" + i);
				ctrl.setId((long) i);
				if(i > recordCount){
					break;
				}
				ctrls.add(ctrl);
			}
			
			
			JSONArray list = new JSONArray();
			for (TeleControl ctrl : ctrls) {
				JSONObject item = new JSONObject();
				item.put("serialno", 0);
				item.put("id", ctrl.getId());
				item.put("modecode", ctrl.getSatellite() != null ? ctrl.getSatellite().getCode() : null);
				item.put("subsystemcode", "");
				item.put("startdt", DateTimeUtils.formatToISODatetime(ctrl.getStartTime()));
				item.put("enddt", DateTimeUtils.formatToISODatetime(ctrl.getEndTime()));
				item.put("alertmsg", ctrl.getMessage());
				item.put("eventtype", ctrl.getEventType() != null ? ctrl.getEventType().name() : null);
				item.put("desc", "");
				item.put("conformperson", ctrl.getConfirmUser() != null ?  ctrl.getConfirmUser().getFullName() : null);
				item.put("conformdt", DateTimeUtils.formatToISODatetime(ctrl.getConfirmTime()));
				item.put("locationdesc", "");
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
			log.error(e.getMessage(), e);
			jsonResult = new JsonResultError(e.getMessage());
		} finally {
			this.sendToClient(jsonResult);
		}
	}

}
