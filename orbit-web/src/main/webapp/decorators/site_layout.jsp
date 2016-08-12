<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator"
	prefix="decorator"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html lang="zh-CN">
    <head>
        <base href="<%=basePath%>" />
        <meta charset="utf-8"/>
        <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
        <meta name="viewport" content="width=device-width, initial-scale=1"/>
        <title>
            <decorator:title default="欢迎"></decorator:title>
        </title>
        <link type="text/css" rel="stylesheet" href="<s:url value="/bower_components/bootstrap/dist/css/bootstrap.min.css"></s:url>" />
        <script src="<s:url value="/bower_components/jquery/dist/jquery.min.js"></s:url>"></script>
    <script src="<s:url value="/bower_components/bootstrap/dist/js/bootstrap.min.js"></s:url>"></script>
<script src="<s:url value="/scripts/jsless.js"></s:url>"></script>
<link type="text/css" rel="stylesheet" href="<s:url value="/style/stage.css"></s:url>" />
<decorator:head></decorator:head>
</head>
<body>
<nav class="navbar navbar-fixed-top">
<div class="container-fluid">
    <div class="navbar-header">
        <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </button>
        <a class="navbar-brand" href="#">平台</a>
    </div>
    <div id="navbar" class="navbar-collapse collapse">
        <ul class="nav navbar-nav navbar-right">
            <li>
                <a href="#">修改密码</a>
            </li>
            <li>
                <a href="#">设置</a>
            </li>
            <li>
                <a href="#">帮助</a>
            </li>
        </ul>
        <form class="navbar-form navbar-right">
            <input type="text" class="form-control" placeholder="关键字"></form>
        </div>
    </div>
    <div class="currinfo">

        <form class="form-inline">
            <div class="form-group">
                欢迎：
                <label for="exampleInputEmail2">张三</label>！
            </div>
            <div class="form-group">
                已选型号：
                <div class="dropdown mode-selector">
                    <button class="btn btn-default dropdown-toggle" type="button" id="ddm_main_selectmodels" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                        <span class="btn-text">型号1,型号2</span>
                        <span class="caret"></span>
                    </button>
                    <ul id="main_modelselector" class="dropdown-menu" aria-labelledby="ddm_main_selectmodels">
                        <li>
                            <div class="dropdown-menuitem">
                                <label><input type="checkbox" value="型号1"/>型号1</label>
                            </div>
                        </li>
                        <li>
                            <div class="dropdown-menuitem">
                                <label><input type="checkbox" value="型号1"/>型号2</label>
                            </div>
                        </li>
                        <li>
                            <div class="dropdown-menuitem">
                                <label><input type="checkbox" value="型号1"/>型号3</label>
                            </div>
                        </li>
                        <li>
                            <div class="dropdown-menuitem">
                                <label><input type="checkbox" value="型号1"/>型号4</label>
                            </div>
                        </li>
                    </ul>
                </div>
            </div>
            <%-- <button type="submit" class="btn btn-default">型号选择</button> --%>
            <a href="<s:url namespace="/taskdist" action="indexgraph"></s:url>" class="btn btn-primary" role="button">任务分发</a>
        <div class="form-group">
            目前未处理报警信息数：
            <label>XX</label>
        </div>
        <div class="form-group">
            今日测控事件：
            <label>XX</label>
        </div>
        <div class="form-group">
            今日备忘：
            <label>XX</label>
        </div>
    </form>
</div>
</nav>

<div class="container-fluid">
<div class="row">
    <div class="col-sm-3 col-md-2 sidebar">
        <div class="main-nav-container">
            <ul id="main-nav-tabs" class="nav nav-tabs" role="tablist">
                <li role="presentation">
                    <a href="#history" id="history-tab" role="tab" data-toggle="tab" aria-controls="history" aria-expanded="true">历史</a>
                </li>
                <li class="active" role="presentation">
                    <a href="#today" id="today-tab" role="tab" data-toggle="tab" aria-controls="today" aria-expanded="true">本日</a>
                </li>
                <li role="presentation">
                    <a href="#future" id="future-tab" role="tab" data-toggle="tab" aria-controls="future" aria-expanded="true">未来</a>
                </li>
            </ul>
            <div id="main-nav-contents">
                <div role="tabpanel" class="tab-pane fade" id="history" aria-labelledby="history-tab">历史...</div>
                <div role="tabpanel" class="tab-pane fade active in" id="today" aria-labelledby="today-tab">
                    <dl>
                        <dt>日常工作</dt>
                        <dd>
                            <ul class="nav nav-sidebar">
                                <li class="active">
                                    <a href="<s:url namespace="/regular/levelthree" action="index"></s:url>">三级门限报警分析
                                    <span id="tip_level3limit" class="badge">--</span>
                                </a>
                            </li>
                            <li>
                                <a href="#">二代平台报警分析<span class="badge">120/200</span>
                                </a>
                            </li>
                            <li>
                                <a href="<s:url namespace="/regular/cekongevents" action="index"></s:url>">测控事件<span class="badge">120/200</span>
                            </a>
                        </li>
                        <li>
                            <a href="#">备忘录<span class="badge">120/200</span>
                            </a>
                        </li>
                        <li>
                            <a href="<s:url namespace="/regular/yaoceparams" action="index"></s:url>">重点遥测参数趋势图<span class="badge">120/200</span>
                        </a>
                    </li>
                </ul>
            </dd>
            <dt>在轨管理工作</dt>
            <dd>
                <ul class="nav nav-sidebar">
                    <li>
                        <a href="">型号1<span class="badge">120/200</span>
                        </a>
                    </li>
                    <li>
                        <a href="">型号2<span class="badge">120/200</span>
                        </a>
                    </li>
                </ul>
            </dd>
            <dt>
                工作成果
            </dt>
            <dd>
                <div class="work-result container-fluid">
                    <div class="row">
                        <div class="col-xs-6 col-md-6">待办：120</div>
                        <div class="col-xs-6 col-md-6">待确认：20</div>
                    </div>
                    <div class="row">
                        <div class="col-xs-6 col-md-6">被退回：300</div>
                        <div class="col-xs-6 col-md-6">已完成：300</div>
                    </div>
                    <div class="row">
                        <div class="col-xs-6 col-md-6">已延期：0</div>
                        <div class="col-xs-6 col-md-6"></div>
                    </div>
                </div>
            </dd>
            <dt>常用信息及软件</dt>
            <dd>
                <ul class="nav nav-sidebar">
                    <li>
                        <a href="">报警分析软件</a>
                    </li>
                    <li>
                        <a href="">测控事件记录软件</a>
                    </li>
                    <li>
                        <a href="">遥测数据监视软件</a>
                    </li>
                    <li>
                        <a href="">型号联系人电话表</a>
                    </li>
                </ul>
            </dd>
        </dl>

    </div>
    <div role="tabpanel" class="tab-pane fade" id="future" aria-labelledby="future-tab">未来...</div>
</div>
</div>

</div>
<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
<decorator:body></decorator:body>
</div>
</div>
</div>

<script>
$('#main-nav-tabs a').click(function (e) {
    $("#main-nav-contents .tab-pane").hide();
    var tab_title_btn = $(this);
    var a_href = tab_title_btn.attr("href");
    var targetid = a_href.substr(1, a_href.length - 1)
    $("#" + targetid).show();
});

/**
 * update the tips of sidebar.
 **/
var updateTips = function () {
    jsless.ajax({
        url: "<s:url namespace='/json/tips' action='getSummary'></s:url>",
        data: {},
        success: function (rep) {
            if (rep.statusCode == 200) {
                var tips = rep.content;
                $("#tip_level3limit").html(tips['level3limit']);
            } else {}
        }
    });
};
/**
 * update the button of model selector
 **/
var updateBtnMainModelSelector = function(){
    var selectedModelNames = [];
    $("#main_modelselector").find("input:checkbox:checked").each(function(){
        var modelName = $(this).attr("modelname");
        selectedModelNames.push(modelName);
    });
    $("#ddm_main_selectmodels").find(".btn-text").text(selectedModelNames.join(','));
};

/**
 *build the model selector(the pop window)
 **/
var buildModelSelector = function(){
    jsless.ajax({
        url: "<s:url namespace='/json/models' action='getAdminModels'></s:url>",
        data: {},
        success: function (rep) {
            if (rep.statusCode == 200) {
                var models = rep.content;
                if(models){
                    $("#main_modelselector").empty();
                    for(var i = 0; i< models.length; i++){
                        var model = models[i];
                        var li = $("<li></li>");
                        var div = $("<div></div>").addClass("dropdown-menuitem")
                        var label = $("<label></label>")
                        var checkbox = $("<input type='checkbox' class='modelcheckor' />")
                            .val(model.id).attr("code", model.code).attr("modelname", model.name)
                            .prop("checked", model.selected);
                        var span = $("<span></span>").text(model.name);
                        $("#main_modelselector").append(li.append(div.append(label.append(checkbox).append(span))));
                    }
                    updateBtnMainModelSelector();
                }
            } else {
                // error
            }
        }
    });
};

/**
 * bind the change event to the checkbox in model selector.
 **/
var bindChangeEventToModelSelector = function(){
    $("#main_modelselector").delegate(".modelcheckor", "change", function(){
        var selectedModelIds = [];
        $("#main_modelselector").find("input:checkbox:checked").each(function(){
            var modelid = $(this).val();
            selectedModelIds.push(parseInt(modelid));
        });
        var params = {selectedmodels: selectedModelIds};
        jsless.ajax({
            url: "<s:url namespace='/json/models' action='updateSelectedModels'></s:url>",
            data: params,
            success: function(rep){
                if(rep.statusCode == 200){
                    updateBtnMainModelSelector();
                } else {
                    // error:
                }
            }
        });
    });
}

$(function () {
    buildModelSelector();
    bindChangeEventToModelSelector();
    updateTips();
    window.setInterval(updateTips, 3000);
    $(".dropdown-menu").delegate(".dropdown-menuitem", "click", function(e){
        e.stopPropagation();
    });
});
</script>
</body>
</html>
