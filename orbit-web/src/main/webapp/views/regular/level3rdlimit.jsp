<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <title>三级门限报警分析</title>
        <link type="text/css" rel="stylesheet" href="<s:url value="/bower_components/bootstrap-table/dist/bootstrap-table.min.css"></s:url>" />
        <link type="text/css" rel="stylesheet" href="<s:url value="/bower_components/smalot-bootstrap-datetimepicker/css/bootstrap-datetimepicker.min.css"></s:url>" />
    </head>
    <body>
        <h2 class="page-header">三级门限报警分析
            <button id="btn_batchset" class="btn btn-default pull-right" data-target="#modal_batchset" data-toggle="modal">批量分析</button>
        </h2>

        <form class="form-inline">
            <div class="form-group">
                <label for="exampleInputName2">卫星型号：</label>
                <div class="dropdown mode-selector">
                    <button class="btn btn-default dropdown-toggle" type="button" id="ddm_child_selectmodels" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                        <span class="btn-text">型号1,型号2</span>
                        <span class="caret"></span>
                    </button>
                    <ul id="child_modelselector" class="dropdown-menu" aria-labelledby="ddm_child_selectmodels">
                        <li>
                            <div class="dropdown-menuitem"><label><input type="checkbox" value="型号1" />型号1</label></div>
                        </li>
                        <li>
                            <div class="dropdown-menuitem"><label><input type="checkbox" value="型号1" />型号2</label></div>
                        </li>
                        <li>
                            <div class="dropdown-menuitem"><label><input type="checkbox" value="型号1" />型号3</label></div>
                        </li>
                        <li>
                            <div class="dropdown-menuitem"><label><input type="checkbox" value="型号1" />型号4</label></div>
                        </li>
                    </ul>
                </div>

            </div>
            <div class="form-group">
                <label for="exampleInputEmail2">报警开始时间：</label>
                <input type="text" class="form-control" id="txt_alertstarttime" placeholder="报警开始时间" style="width:200px;" data-date-format="yyyy-mm-dd hh:ii"/></div>
            <div class="form-group">
                <label for="exampleInputEmail2">报警结束时间：</label>
                <input type="text" class="form-control" id="txt_alertendtime" placeholder="报警结束时间" style="width:200px;" data-date-format="yyyy-mm-dd hh:ii"/></div>
            <a id="btn-search" type="submit" class="btn btn-default">查询</a>
        </form>

        <div class="table-responsive" style="margin-top:10px;">
            <table id="list-table" class="table table-striped"></table>
        </div>

        <div class="modal fade" id="modal_batchset" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                        <h4 class="modal-title" id="myModalLabel">批量分类分析</h4>
                    </div>
                    <div class="modal-body">
                        <form>
                            <div class="form-group">
                                <label for="exampleInputEmail1">事件类别：</label>
                                <select class="form-control" id="slt_batch_eventtype">
                                    <option value="MINOR">MINOR</option>
                                    <option value="MAJOR">MAJOR</option>
                                    <option value="SERVE">SERVE</option>
                                    <option value="FATAL">FATAL</option>

                                </select>
                            </div>
                            <div class="form-group">
                                <label for="exampleInputPassword1">情况说明：</label>
                                <input type="text" class="form-control" id="txt_batch_eventdesc" placeholder="说明"/>
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                        <button type="button" class="btn btn-primary" id="btn_batchupdate">保存</button>
                    </div>
                </div>
            </div>
        </div>

<script src="<s:url value="/bower_components/bootstrap-table/dist/bootstrap-table.min.js"></s:url>"></script>
<script src="<s:url value="/bower_components/bootstrap-table/dist/locale/bootstrap-table-zh-CN.min.js"></s:url>"></script>
<script src="<s:url value="/bower_components/smalot-bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js"></s:url>"></script>
<script src="<s:url value="/bower_components/smalot-bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></s:url>"></script>
<script src="<s:url value="/scripts/datatable.js"></s:url>"></script>
<script>

/**
 * update the button of model selector
 **/
var updateBtnChildModelSelector = function(){
    var selectedModelNames = [];
    $("#child_modelselector").find("input:checkbox:checked").each(function(){
        var modelName = $(this).attr("modelname");
        selectedModelNames.push(modelName);
    });
    $("#ddm_child_selectmodels").find(".btn-text").text(selectedModelNames.join(','));
};

/**
 *build the model selector(the pop window)
 **/
var buildChildModelSelector = function(){
    return jsless.ajax({
        url: "<s:url namespace='/json/models' action='getAdminModels'></s:url>",
        data: {},
        success: function (rep) {
            if (rep.statusCode == 200) {
                var models = rep.content;
                if(models){
                    $("#child_modelselector").empty();
                    for(var i = 0; i< models.length; i++){
                        var model = models[i];
                        var li = $("<li></li>");
                        var div = $("<div></div>").addClass("dropdown-menuitem")
                        var label = $("<label></label>")
                        var checkbox = $("<input type='checkbox' class='modelcheckor' />")
                            .val(model.id).attr("code", model.code).attr("modelname", model.name)
                            .prop("checked", model.selected);
                        var span = $("<span></span>").text(model.name);
                        $("#child_modelselector").append(li.append(div.append(label.append(checkbox).append(span))));
                    }
                    updateBtnChildModelSelector();
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
var bindChangeEventToChildModelSelector = function(){
    $("#child_modelselector").delegate(".modelcheckor", "change", function(){
        var selectedModelIds = [];
        $("#child_modelselector").find("input:checkbox:checked").each(function(){
            var modelid = $(this).val();
            selectedModelIds.push(parseInt(modelid));
        });
        var params = {selectedmodels: selectedModelIds};
        return jsless.ajax({
            url: "<s:url namespace='/json/models' action='updateSelectedModels'></s:url>",
            data: params,
            success: function(rep){
                if(rep.statusCode == 200){
                    updateBtnChildModelSelector();
                } else {
                    // error:
                }
            }
        });
    });
}



var getPagerData = function (pageIndex, callback) {
    var selectedModelIds = [];
    $("#child_modelselector").find("input:checkbox:checked").each(function(){
        var modelid = $(this).val();
        selectedModelIds.push(parseInt(modelid));
    });

    var params = {
        searcher: {
            keyword: '',
            models: selectedModelIds,
            alertstarttime: $("#txt_alertstarttime").val(),
            alertendtime: $("#txt_alertendtime").val()
        },
        pager: {
            pageIndex: pageIndex
        }
    };

    jsless.ajax({
        url: "<s:url namespace='/json/regular/levelthree' action='getListByPager'></s:url>",
        data: params,
        success: function (rep) {
            if (rep.statusCode == 200) {
                if(callback){
                    callback(rep.content);
                }

            } else {
                // error
            }
        }
    });
};

var doSearch = function(pageIndex){
    getPagerData(
        pageIndex,
        function(result){
            var records = result.records;
            var pageInfo = result.pageInfo;

            buildtable(pageInfo.pageIndex, pageInfo.pageSize, pageInfo.recordCount, records);
        }
    );
};

var buildtable = function (pageIndex, pageSize, recordCount, listdata) {

	var table = new datatable({
		tableSelector: "#list-table",
		columns: [{
            	columnFormatter: function(){
            		var checkbox = $("<input type='checkbox' />").val("toggleall");
            		checkbox.change(function(table){

            		});
                	return checkbox;
            	},
            	rowFormatter: function(row, rowdata){
                	var checkbox = $("<input type='checkbox' />").val(rowdata.id);
                	checkbox.change(function(){

                	});
                	return checkbox;
                }
            }, {
                field: 'modecode',
                title: '型号代号'
            }, {
                field: 'alertstartdt',
                title: '报警开始时间'
            }, {
                field: 'alertenddt',
                title: '报警结束时间'
            }, {
                field: 'alertmsg',
                title: '报警信息'
            }, {
                field: 'eventtype',
                title: '事件类别'
            }, {
                field: 'desc',
                title: '情况说明'
            }, {
                field: 'conformperson',
                title: '确认人'
            }, {
                field: 'conformdt',
                title: '确认时间'
            }
        ],
        rows: listdata,
        recordCount: recordCount,
        pageSize: pageSize,
        pageIndex: pageIndex,
        getPagerRows: function(pageIndex, pageSize){
            getPagerData(
                pageIndex,
                function(result){
                    var records = result.records;
                    var pageInfo = result.pageInfo;
                    table.reload(pageIndex, records);
                }
            );
        }
	});

	table.render();

    return table;
};

var bindBtnBatchUpdateClick = function(){
    $("#btn_batchupdate").click(function(){
		var selectedItems = table.bootstrapTable('getSelections');
		var selectedIds = [];
		for(var item in selectedItems){
			selectedIds.push(item.id);
		}
		var eventtype = $("#slt_batch_eventtype").val();
		var eventdesc = $("#txt_batch_eventdesc").val();
		var params = {selectedids: selectedIds, eventtype: eventtype, eventdesc: eventdesc};
		jsless.ajax({
	        url: "<s:url namespace='/json/regular/levelthree' action='batchUpdate'></s:url>",
	        data: params,
	        success: function (rep) {
	            if (rep.statusCode == 200) {
	                var ctn = rep.content;
	                $("#modal_batchset").modal('hide');
	                doSearch(0);
	            } else {
	                // error
	            }
	        }
	    });
    });
};

$(function () {
    bindChangeEventToChildModelSelector();
    $.when(buildChildModelSelector()).done(function(){
        doSearch(0);
    });


    $("#btn-search").click(function(e){
        doSearch(0);
    });

    $("#txt_alertstarttime").datetimepicker();
    $("#txt_alertendtime").datetimepicker();
    bindBtnBatchUpdateClick();
});
</script>
</body>
</html>
