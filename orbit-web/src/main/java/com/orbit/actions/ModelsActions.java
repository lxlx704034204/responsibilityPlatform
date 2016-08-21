package com.orbit.actions;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.orbit.configs.SystemConfig;
import com.orbit.AppContext;
import com.orbit.OrbitServiceApplication;
import com.orbit.entity.Satellite;
import com.orbit.entity.permission.User;
import com.orbit.repository.SatelliteRepository;
import com.orbit.repository.permission.UserRepository;
import com.orbit.repository.ThresholdAlertRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.ArrayList;

@SpringApplicationConfiguration(classes = OrbitServiceApplication.class)
public class ModelsActions extends AppAction {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private static Log log = LogFactory.getLog(ModelsActions.class);

	@Autowired
    SatelliteRepository slRepo;

	@Autowired
    UserRepository userRepo;

	public void jsonGetAdminModels(){
		JsonResult jsonResult = null;
		try {
			// cancel comment in production environment.
			String userName = "adminuser";//this.getAuthenticatedUser().getName();
			List<Satellite> satellitesAdmins = slRepo.findAllByAdminUserLoginName(userName);

			// mock data.
//			List<Satellite> satellitesAdmins = new ArrayList<Satellite>();
//			Satellite sl1 = new Satellite("型号1");
//			sl1.setCode("sl1");
//			sl1.setId(1l);
//			Satellite sl2 = new Satellite("型号2");
//			sl2.setCode("sl2");
//			sl2.setId(2l);
//			Satellite sl3 = new Satellite("型号3");
//			sl3.setCode("sl3");
//			sl3.setId(3l);
//			satellitesAdmins.add(sl1);
//			satellitesAdmins.add(sl2);
//			satellitesAdmins.add(sl3);

			this.setAdminModels(satellitesAdmins);

            JSONArray list = new JSONArray();
			if(satellitesAdmins != null && satellitesAdmins.size() > 0){
				for(Satellite sl : satellitesAdmins){
					JSONObject item = new JSONObject();
					item.put("id", sl.getId());
					item.put("name", sl.getName());
	        		item.put("code", sl.getCode());
					item.put("selected", true);
					list.add(item);
				}
			}

			jsonResult = new JsonResultSuccess(list);
		} catch (Exception e) {
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
			JSONArray selectedModelIds = json.getJSONArray("selectedmodels");
            this.setSelectedModelIds(JSONArray.toList(selectedModelIds, Long.class));

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
            List<Long> modelIds = this.getSelectedModelIds();
			jsonResult = new JsonResultSuccess(modelIds);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			log.error(e.getMessage(), e);
			jsonResult = new JsonResultError(e.getMessage());
		} finally {
			this.sendToClient(jsonResult);
		}
    }

}
