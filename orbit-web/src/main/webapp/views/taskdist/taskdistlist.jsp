<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>在轨管理工作全阶段任务管理</title>
</head>
<body>
          <h2 class="page-header">在轨管理工作全阶段任务管理</h2>
		  
		  <h3>一、发射及保障工作 <button id="btn_setnewtask" class="btn btn-default pull-right" data-target="#modal_setnewtask" data-toggle="modal">设置新任务</button></h3>
          <div class="table-responsive">
            <table id="table_lanuch" class="table table-striped table-hover">
            <thead>
                <tr>
                  <th>序号</th>
                  <th>任务名称</th>
                  <th>责任人</th>
                  <th>截止时间</th>
                  <th>任务结果</th>
				  <th>当前状态</th>
				  <th>详细信息</th>
				  <th>调整任务</th>
                </tr>
              </thead>
              <tbody></tbody>
            </table>
          </div>
		  
		  <h3>二、交付前工作 <button class="btn btn-default pull-right">设置新任务</button></h3>
          <div class="table-responsive">
            <table id="table_predeliver" class="table table-striped table-hover">
            	<thead>
                <tr>
                  <th>序号</th>
                  <th>任务名称</th>
                  <th>责任人</th>
                  <th>截止时间</th>
                  <th>任务结果</th>
				  <th>当前状态</th>
				  <th>详细信息</th>
				  <th>调整任务</th>
                </tr>
              </thead>
              <tbody></tbody>
            </table>
          </div>
		  
		  <h3>三、交付后工作 <button class="btn btn-default pull-right">设置新任务</button></h3>
          <div class="table-responsive">
            <table id="table_postdeliver" class="table table-striped table-hover">
              <thead>
                <tr>
                  <th>序号</th>
                  <th>任务名称</th>
                  <th>责任人</th>
                  <th>截止时间</th>
                  <th>任务结果</th>
				  <th>当前状态</th>
				  <th>详细信息</th>
				  <th>调整任务</th>
                </tr>
              </thead>
              <tbody></tbody>
            </table>
          </div>
		  <h3>四、寿命终结工作 <button class="btn btn-default pull-right">设置新任务</button></h3>
          <div class="table-responsive">
            <table id="table_endoflife" class="table table-striped table-hover">
              <thead>
                <tr>
                  <th>序号</th>
                  <th>任务名称</th>
                  <th>责任人</th>
                  <th>截止时间</th>
                  <th>任务结果</th>
				  <th>当前状态</th>
				  <th>详细信息</th>
				  <th>调整任务</th>
                </tr>
              </thead>
              <tbody></tbody>
            </table>
          </div>
		  <div class="modal fade" id="modal_setnewtask" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
			  <div class="modal-dialog" role="document">
				<div class="modal-content">
				  <div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					<h4 class="modal-title" id="myModalLabel">任务建立与分配</h4>
				  </div>
				  <div class="modal-body">
<form>
  <div class="form-group">
    <label for="exampleInputEmail1">任务名称：</label>
	<input type="text" class="form-control" id="exampleInputText1" placeholder="任务名称">
  </div>
  <div class="form-group">
    <label for="exampleInputPassword1">确认人：</label>
    <input type="text" class="form-control" id="exampleInputText2" placeholder="确认人">
  </div>
<div class="form-group">
    <label for="exampleInputEmail1">责任人：</label>
	<input type="text" class="form-control" id="exampleInputText3" placeholder="责任人">
  </div>
  <div class="form-group">
    <label for="exampleInputPassword1">截止时间：</label>
    <input type="text" class="form-control" id="exampleInputText4" placeholder="截止时间">
  </div>
</form>
				  </div>
				  <div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary">保存</button>
				  </div>
				</div>
			  </div>
			</div>
<script>

var createTaskRow = function(index ,taskdata){
	var tr = $("<tr></tr>");
	var serialno = $("<td class='serialno'></td>").html(index);
	var taskname = $("<td class='name'></td>").html(taskdata.name);
	var responsibleUser = $("<td class='responsibleuser'></td>").html(taskdata.userResponsibleName);
	var deadlineTime = $("<td class='deadlinetime'></td>").html(taskdata.deadlineTime);
	var taskresult = $("<td class='result'></td>").html(taskdata.taskResult);
	var status = $("<td class='status'></td>").html(" ");
	var detail = $("<td class='detail'></td>").html(taskdata.detail);
	var operate = $("<td class='operate'></td>").html(" ");
	tr.append(serialno).append(taskname).append(responsibleUser).append(deadlineTime).append(taskresult).append(status).append(detail).append(operate);
	return tr;
};

var getModelTask = function(){
	var params = {
		modelid: ${ modelid }
	};
	return jsless.ajax({
        url: "<s:url namespace='/json/taskdist' action='getModelTasks'></s:url>",
        data: params,
        success: function (rep) {
            if (rep.statusCode == 200) {
                var tasks = rep.content.records;
                for(var i = 0; i < tasks.length; i++){
                    var task = tasks[i];
                    
                    if(task.stage == "发生及保障阶段"){
                    	var size = $("#table_lanuch tbody").find("tr").size();
                    	var taskRow = createTaskRow(size + 1, task);
                        $("#table_lanuch tbody").append(taskRow);
                    } else if(task.stage == "交付前"){
                    	var size = $("#table_predeliver tbody").find("tr").size();
                    	var taskRow = createTaskRow(size + 1, task);
                        $("#table_predeliver tbody").append(taskRow);
                    } else if(task.stage == "交付后"){
                    	var size = $("#table_postdeliver tbody").find("tr").size();
                    	var taskRow = createTaskRow(size + 1, task);
                        $("#table_postdeliver tbody").append(taskRow);
                    } else if(task.stage == "寿命终结") {
                    	var size = $("#table_endoflife tbody").find("tr").size();
                    	var taskRow = createTaskRow(size + 1, task);
                        $("#table_endoflife tbody").append(taskRow);
                    }
                }
            } else {
                // error
            }
        }
    });
};



$(function(){
	getModelTask();
});
$("#btn_setnewtask").click(function(){
	
});
 </script>
</body>
</html>
