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
import com.orbit.utils.StrUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class LevelThreeLimitActions extends AppAction {

  private static final long serialVersionUID = 1L;

  private static Log log = LogFactory.getLog(LevelThreeLimitActions.class);

  @Autowired
  SatelliteRepository slRepo;

  @Autowired
  UserRepository userRepo;

  @Autowired
  ThresholdAlertRepository thRepo;

  public String pageIndex() {
    return SUCCESS;
  }

  public void jsonGetListByPager() {
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
      
      List<Long> selectedModelIds = JSONArray.toList(models, Long.class);
      Date startDate = DateTimeUtils.parseISODatetime(alertstarttime);
      Date endDate = DateTimeUtils.parseISODatetime(alertendtime);
      
      // TODO:for test
      if(startDate == null && endDate == null){
    	  Calendar yesterday = Calendar.getInstance();
          yesterday.add(Calendar.DAY_OF_MONTH, -1);
          startDate = yesterday.getTime();
          Calendar tomorrow = Calendar.getInstance();
          tomorrow.add(Calendar.DAY_OF_MONTH, 1);
          endDate = tomorrow.getTime();
      }

      Integer pageIndex = pagerJson.getInt("pageIndex");
      Integer pageSize = SystemConfig.getSystemCommonListPageSize();
      PageRequest pageRequest = new PageRequest(pageIndex, pageSize, new Sort(new Sort.Order(Sort.Direction.DESC, "startTime")));

//      Page<ThresholdAlert> pageResult = thRepo.findBySatelliteIdAndStartTimeBetween(selectedModelIds, startDate, endDate, pageRequest);
//      List<ThresholdAlert> alerts = pageResult.getContent();
//      Long recordCount = pageResult.getTotalElements();

      Long recordCount = 70l;
      List<ThresholdAlert> alerts = new ArrayList<ThresholdAlert>();
      Satellite sl = new Satellite("型号1");
      sl.setCode("sl1");
      for (int i = (pageSize * pageIndex); i < (pageSize * (pageIndex + 1)); i++) {
        System.out.println("i: " + i);
        ThresholdAlert alert = new ThresholdAlert(sl, "异常描述信息" + i);
        alert.setId((long) i);
        if (i > recordCount) {
          break;
        }
        alerts.add(alert);
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
        item.put("conformperson", alert.getConfirmUser() != null ? alert.getConfirmUser().getFullName() : null);
        item.put("conformdt", DateTimeUtils.formatToISODatetime(alert.getConfirmTime()));
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

  public void jsonBatchUpdate() {
    JsonResult jsonResult = null;
    try {
      JSONObject json = this.getRequestJsonObject();
      JSONArray selectedIds_jsonarr = json.getJSONArray("selectedids");
      String eventtype = json.getString("eventtype");
      String eventdesc = json.getString("eventdesc");
      List<Long> selectedIds = JSONArray.toList(selectedIds_jsonarr, Long.class);

      ThresholdAlert.SeverityLevel level = Enum.valueOf(ThresholdAlert.SeverityLevel.class, eventtype);
      Long[] arr = new Long[selectedIds.size()];
      for (int i = 0; i < selectedIds.size(); i++) {
        arr[i] = selectedIds.get(i);
      }
      // 联调代码：start
      //Integer count = thRepo.batchAddSituationComment(level, eventdesc, arr);
      // 联调代码：end

      // 测试代码：start
      Integer count = 1;
      // 测试代码：end
      jsonResult = new JsonResultSuccess(count);
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      jsonResult = new JsonResultError(e.getMessage());
    } finally {
      this.sendToClient(jsonResult);
    }
  }

}
