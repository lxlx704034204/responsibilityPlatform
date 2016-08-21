<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <title>在轨管理工作全阶段任务管理</title>
    </head>
    <body>
        <h2 class="page-header">在轨管理工作全阶段任务管理</h2>
        <table class="flowgraphtable">
            <tr class="stage">
                <td class="flowtitlearea topstage">
                	<div class="whiteholder"></div>
                    <div class="startcycle"></div>
                    <div class="blankholder"></div>
                    <div class="flowtitlecontainer">
                        <div class="title">发射及保障</div>
                        <div class="daterange">(2016-02-11)</div>
                    </div>
                    <div class="blankholder"></div>
                </td>
                <td>
                	<div id="wrapper_launch"></div>
                </td>
            </tr>
            <tr class="stage">
                <td class="flowtitlearea">
                	<div class="blankholder"></div>
                    <div class="flowtitlecontainer">
                        <div class="title">交付前</div>
                        <div class="daterange">(2016-02-11)</div>
                    </div>
                    <div class="blankholder"></div>
                </td>
                <td>
                	<div id="wrapper_predeliver"></div>
                </td>
            </tr>
            <tr class="stage">
                <td class="flowtitlearea">
                	<div class="blankholder"></div>
                    <div class="flowtitlecontainer">
                        <div class="title">交付后</div>
                        <div class="daterange">(日期未指定)</div>
                    </div>
                    <div class="blankholder"></div>
                </td>
                <td>
                	<div id="wrapper_postdeliver"></div>
                </td>
            </tr>
            <tr class="stage">
                <td class="flowtitlearea bottomstage">
                	<div class="blankholder"></div>
                    <div class="flowtitlecontainer">
                        <div class="title">寿命终结</div>
                        <div class="daterange">(日期未指定)</div>
                    </div>
                    <div class="blankholder"></div>
                    <div class="stopcycle"></div>
                    <div class="whiteholder"></div>
                </td>
                <td>
                	<div id="wrapper_endoflife"></div>
                </td>
            </tr>
        </table>
        <div class="container-fluid" style="margin-top:30px;">
            <div class="row">
                <div class="col-sm-12 " style="text-align:center;">
                    <a href="<s:url namespace='/taskdist' action='indexlist'></s:url>" class="btn btn-primary text-center" style="margin:0 auto;">全部任务节点信息</a>
                </div>
            </div>
        </div>
        <iframe id="iframe_upload" name="iframe_upload" style="display:none;"></iframe>
<script type="text/javascript">
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
                    var taskwrapper = createTaskInfoWrapper(task);
                    if(task.stage == "Launch"){
                        $("#wrapper_launch").append(taskwrapper);
                    } else if(task.stage == "PreDeliver"){
                        $("#wrapper_predeliver").append(taskwrapper)
                    } else if(task.stage == "PostDeliver"){
                        $("#wrapper_postdeliver").append(taskwrapper);
                    } else if(task.stage == "EndOfLife") {
                        $("#wrapper_endoflife").append(taskwrapper);
                    }
                }
            } else {
                // error
            }
        }
    });
};

var createAttachmentWrapper = function(taskid, attachmentId, attachmentFileName){
    var attachmentWrapper = $("<div></div>");
    var attachmentLink = $("<a></a>").text(attachmentFileName);
    var delBtn = $("<a><span class='glyphicon glyphicon-remove' aria-hidden='true'></span></a>");
    attachmentWrapper.append(attachmentLink).append(delBtn);
    return attachmentWrapper;
};

// upload callback
window.appendAttachmentInfoToWrapper = function(taskid, attachmentId, attachmentFileName){
    var attachmentsUploadedWrapper = $("#wrapper_uploaded_taskid_" + taskid);
    var attachmentWrapper = createAttachmentWrapper(taskid, attachmentId, attachmentFileName);
    console.log(attachmentWrapper);
    attachmentsUploadedWrapper.append(attachmentWrapper);
};

var createTaskInfoWrapper = function(taskdata){
	var wrapper = $("<div class='container-fluid task-info'></div>");

	var taskNameRow = $("<div class='row'></div>");
	var taskNameCol = $("<div class='col-lg-12'></div>");
	var taskNameLink = $("<a href='#'></a>").text(taskdata.name);
	taskNameRow.append(taskNameCol.append(taskNameLink));

	var taskInfoRow = $("<div class='row'>");
	var responsibleUserCol = $("<div class='col-lg-3'>");
	var responsibleUserName = $("<div></div>").html("责任人：" + taskdata.userResponsibleName);
	var deadlineTime = $("<div></div>").html("截止时间：" + taskdata.deadlineTime);
	responsibleUserCol.append(responsibleUserName).append(deadlineTime);
	taskInfoRow.append(responsibleUserCol);

	var attachmentsCol = $("<div class='col-lg-6'>");
    var attachmentsUploaded = $("<div class='wrapper-uploaded'></div>").attr("id","wrapper_uploaded_taskid_" + taskdata.id);
    var attachmentToUpload = $("<div class='wrapper-toupload'></div>");
	if(taskdata.attachments.length > 0){
		for(var i = 0; i < taskdata.attachments.length; i++){
			var attachment = taskdata.attachments[i];
			var attachmentWrapper =createAttachmentWrapper(taskdata.id, attachment.id, attachment.fileName);
			attachmentsUploaded.append(attachmentWrapper);
		}
	}
    attachmentsCol.append(attachmentsUploaded);
    var uploadAction = "<s:url namespace='/upload' action='upload'></s:url>" 
    	+ "?type=uploadTaskAttachment&callback=appendAttachmentInfoToWrapper&taskid=" + taskdata.id;
    var uploadForm = $("<form class='form-inline'></form>").attr("target", "iframe_upload").attr("enctype","multipart/form-data")
        .attr("method","post").attr("action",uploadAction);
    var fileInput = $("<input type='file' name='file' style='display:inline-block;' />");
    var uploadBtn = $("<button type='submit' class='btn btn-default btn-sm' >提交</button>");
    uploadForm.append(fileInput).append(uploadBtn);
    attachmentToUpload.append(uploadForm);
    attachmentsCol.append(attachmentsUploaded).append(attachmentToUpload);
	taskInfoRow.append(attachmentsCol);

	// var changeBtnCol = $("<div class='col-lg-3'>");
	// var changeBtn = $("<button type='button' class='btn btn-default btn-sm'>修改</button>");
	// changeBtnCol.append(changeBtn);

	var confirmCol = $("<div class='col-lg-3'>");
	if(taskdata.confirmUser && taskdata.confirmUser != ""){
        var confirmTime = $("<span></span>").text("确认时间：" + taskdata.confirmTime);
        confirmCol.append(confirmTime);
	} else {
        var confirmBtn = $("<button type='button' class='btn btn-default btn-sm'>确认</button>");
        var rejectBtn = $("<button type='button' class='btn btn-default btn-sm'>退回</button>");
        confirmCol.append(confirmBtn).append(rejectBtn);
	}
    taskInfoRow.append(confirmCol);

    wrapper.append(taskNameRow).append(taskInfoRow);
    return wrapper;
};

$(function(){
	getModelTask();
});
</script>
    </body>
</html>
