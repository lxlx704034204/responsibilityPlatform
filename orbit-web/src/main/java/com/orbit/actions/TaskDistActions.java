package com.orbit.actions;

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
import com.orbit.entity.LifeTimeTask;
import com.orbit.entity.LifeTimeTask.Stage;
import com.orbit.entity.Satellite;
import com.orbit.entity.ThresholdAlert;
import com.orbit.repository.LifeTimeTaskRepository;
import com.orbit.repository.SatelliteRepository;
import com.orbit.repository.ThresholdAlertRepository;
import com.orbit.repository.permission.UserRepository;
import com.orbit.utils.DateTimeUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@SpringApplicationConfiguration(classes = OrbitServiceApplication.class)
public class TaskDistActions extends AppAction {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private static Log log = LogFactory.getLog(TaskDistActions.class);
	
	@Autowired
    SatelliteRepository slRepo;

	@Autowired
    UserRepository userRepo;

	@Autowired
    ThresholdAlertRepository thRepo;
	
	@Autowired
	LifeTimeTaskRepository lttRepo;

	public String pageIndexList(){
		return SUCCESS;
	}

	public String pageIndexGraph(){
		return SUCCESS;
	}
	
	public void jsonGetListByPager(){
		JsonResult jsonResult = null;
		try {
			JSONObject json = this.getRequestJsonObject();
			Long modelid = json.getLong("modelid");
			
			//List<LifeTimeTask> tasks = lttRepo.findAllBySatelliteIdOrderByStageAscDeadLineTimeAsc(modelid);		
			
			Long recordCount = 70l;
			List<LifeTimeTask> tasks = new ArrayList<LifeTimeTask>();
			Satellite sl = new Satellite("型号1");
			sl.setCode("sl1");
			for(int i = 0; i<16; i++){
				LifeTimeTask task = new LifeTimeTask(sl);
				task.setDeadLineTime(new Date());
				task.setDetail("详细" + i);
				task.setId(Long.valueOf(i+""));
				task.setName("任务" + i);
				if(i < 4){
					task.setStage(Stage.Launch);
				} else if(4 <= i && i < 8){
					task.setStage(Stage.PreDeliver);
				} else if(8 <= i && i < 12){
					task.setStage(Stage.PostDeliver);
				} else if(12 <= i && i < 16){
					task.setStage(Stage.EndOfLife);
				}
				tasks.add(task);
			}
			
			
			JSONArray list = new JSONArray();
			for (ThresholdAlert alert : alerts) {
				JSONObject item = new JSONObject();
				item.put("serialno", 0);
				item.put("id", alert.getId());
				item.put("modecode", alert.getSatellite() != null ? alert.getSatellite().getCode() : null);
				item.put("alertstartdt", DateTimeUtils.formatToISODatetime(alert.getStartTime()));
				item.put("alertenddt", DateTimeUtils.formatToISODatetime(alert.getEndTime()));
				item.put("alertmsg", alert.getMessage());
				item.put("eventtype", alert.getSeverityLevel().name());
				item.put("desc", "");
				item.put("conformperson", alert.getConfirmUser() != null ?  alert.getConfirmUser().getFullName() : null);
				item.put("conformdt", DateTimeUtils.formatToISODatetime(alert.getConfirmTime()));
				list.add(item);
		    }

			JSONObject listingData = new JSONObject();
			listingData.put("records", list);

			jsonResult = new JsonResultSuccess(listingData);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			jsonResult = new JsonResultError(e.getMessage());
		} finally {
			this.sendToClient(jsonResult);
		}
	}

}
