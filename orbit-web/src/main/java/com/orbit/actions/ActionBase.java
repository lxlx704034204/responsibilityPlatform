package com.orbit.actions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.orbit.AppContext;
import com.orbit.models.User;
import com.orbit.utils.NumericUtils;
import com.orbit.utils.StrUtils;
import com.opensymphony.xwork2.ActionSupport;

/**
 * the base class of action
 * */
public class ActionBase extends ActionSupport {	
	
	protected final String HTTP_METHOD_GET = "GET";
	protected final String HTTP_METHOD_POST = "POST";
	
	public ActionBase(){
//		this.getRequest().setAttribute("returnurl", this.getRequest().getRequestURL());
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8647185033056627255L;
	
	private static Log log = LogFactory.getLog(ActionBase.class);
	
	/**
	 * get the request post data
	 * */
	protected String getRequestPostText(){
		StringBuilder strBuilder = new StringBuilder();
		try {
			InputStream inStream = ServletActionContext.getRequest().getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(inStream,"UTF-8"));
			String line = null;
			while((line = reader.readLine()) != null){
				strBuilder.append(line);
			}
			//reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		log.debug("Request Post Text: \n" + strBuilder.toString());
		return strBuilder.toString();
	}
	
	protected JSONObject getRequestJsonObject(){
		return JSONObject.fromObject(this.getRequestPostText());
	}
	
	protected JSONArray getRequestJsonArray(){
		return JSONArray.fromObject(this.getRequestPostText());
	}
	
	protected HttpServletRequest getRequest(){
		return ServletActionContext.getRequest();
	}
	
	/**
	 * send the text to client
	 * */
	protected void sendToClient(JsonResult jsonResult){
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		try {
			if(jsonResult == null){
				jsonResult = new JsonResult();
			}
			PrintWriter writer = response.getWriter();
			writer.print(jsonResult.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	protected void sendToClient(JsonResult jsonResult,JsonConfig config){
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		try {
			if(jsonResult == null){
				jsonResult = new JsonResult();
			}
			PrintWriter writer = response.getWriter();
			writer.print(jsonResult.toString(config));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取Request中参数名为pageindex的值
	 * */
	protected Integer getPageIndex(){
		Integer pageIndex = 0;
		String s_pageIndex = this.getRequest().getParameter("pageindex");
		if(!StrUtils.isNullOrEmpty(s_pageIndex)){
			pageIndex = NumericUtils.parseInteger(s_pageIndex);
		}
		return pageIndex;
	}
	
	/**
	 * 获取Request中参数名为searchkey的参数值
	 * */
	protected String getSearchKey(){
		String searchKey = this.getRequest().getParameter("keyword");		
		return StrUtils.isNullOrEmpty(searchKey) ? null : searchKey;
	}
	
	/**
	 * 设置已验证的用户
	 * */
	public void setAuthenticatedUser(User user){
		this.getRequest().getSession().setAttribute(AppContext.USER_KEY, user);
	}
	
	/**
	 * 获取已验证的用户
	 * */
	protected User getAuthenticatedUser(){
		User user = null;
		Object obj = this.getRequest() == null ? null : this.getRequest().getSession().getAttribute(AppContext.USER_KEY);
		if(obj != null && obj instanceof User){
			user = (User)obj;
		}
		return user;
	}
	
	/**
	 * 给Request对象设置Error属性
	 * */
	protected void setErrorAttribute(Exception e){
		ErrorMessage error = new ErrorMessage(e);
		this.getRequest().setAttribute("error", error);
	}
}