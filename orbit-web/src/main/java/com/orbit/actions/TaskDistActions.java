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
import com.orbit.entity.Attachment;
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
		String modelid = this.getRequest().getParameter("id");
		this.getRequest().setAttribute("modelid", modelid);
		return SUCCESS;
	}
	
	public void jsonGetModelTasks(){
		JsonResult jsonResult = null;
		try {
			JSONObject json = this.getRequestJsonObject();
			Long modelid = json.getLong("modelid");
			
			List<LifeTimeTask> tasks = lttRepo.findAllBySatelliteIdOrderByStageAscDeadLineTimeAsc(modelid);		
			
//			Long recordCount = 70l;
//			List<LifeTimeTask> tasks = new ArrayList<LifeTimeTask>();
//			Satellite sl = new Satellite("型号1");
//			sl.setCode("sl1");
//			for(int i = 0; i<16; i++){
//				LifeTimeTask task = new LifeTimeTask(sl);
//				task.setDeadLineTime(new Date());
//				task.setDetail("详细" + i);
//				task.setId(Long.valueOf(i+""));
//				task.setName("任务" + i);
//				if(i < 4){
//					task.setStage(Stage.Launch);
//				} else if(4 <= i && i < 8){
//					task.setStage(Stage.PreDeliver);
//				} else if(8 <= i && i < 12){
//					task.setStage(Stage.PostDeliver);
//				} else if(12 <= i && i < 16){
//					task.setStage(Stage.EndOfLife);
//				}
//				tasks.add(task);
//			}
			
			
			JSONArray list = new JSONArray();
			for (LifeTimeTask task : tasks) {
				JSONObject item = new JSONObject();
				item.put("id", task.getId());
				item.put("modecode", task.getSatellite() != null ? task.getSatellite().getCode() : null);
				item.put("stage", task.getStage() != null ? task.getStage().name() : null);
				item.put("name", task.getName());
				item.put("userResponsibleName", task.getUserResponsible() != null ? task.getUserResponsible().getFullName() : null);
				item.put("deadlineTime", task.getDeadLineTime() != null ? DateTimeUtils.formatToISODate(task.getDeadLineTime()) : null);
				item.put("submitTime", task.getSubmitTime() != null ? DateTimeUtils.formatToISODate(task.getSubmitTime()) : null);
				item.put("taskResult", task.getTaskResult());
				item.put("detail", task.getDetail());
				item.put("confirmUser", task.getConfirmUser() != null ? task.getConfirmUser().getFullName() : null);
				item.put("confirmTime", task.getConfirmTime() != null ? DateTimeUtils.formatToISODate(task.getConfirmTime()) : null);
				
				JSONArray attachmentArr = new JSONArray();
				List<Attachment> attachments = task.getAttachments();
				if(attachments != null && attachments.size() > 0){
					for(Attachment attachment : attachments){
						JSONObject attachmentJSON = new JSONObject();
						attachmentJSON.put("id", attachment.getId());
						attachmentJSON.put("fileName", attachment.getFileName());
						attachmentJSON.put("uploadByUserName", attachment.getUploadBy() != null ? attachment.getUploadBy().getFullName() : null);
						attachmentJSON.put("uploadTime", attachment.getUploadTime() != null ? DateTimeUtils.formatToISODate(attachment.getUploadTime()) : null);
						attachmentArr.add(attachmentJSON);
					}
				}
				item.put("attachments", attachmentArr);
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
