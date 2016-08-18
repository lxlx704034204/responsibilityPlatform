<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>测控事件</title>
<link type="text/css" rel="stylesheet" href="<s:url value="/bower_components/smalot-bootstrap-datetimepicker/css/bootstrap-datetimepicker.min.css"></s:url>" />
</head>
<body>
          <h2 class="page-header">测控事件</h2>

<form class="form-inline">
  <div class="form-group">
    <label for="exampleInputEmail2">开始时间：</label>
    <input type="text" class="form-control" id="txt_starttime" placeholder="开始时间" style="width:200px;"/>
  </div>
	<div class="form-group">
    <label for="exampleInputEmail2">结束时间：</label>
    <input type="text" class="form-control" id="txt_endtime" placeholder="结束时间" style="width:200px;"/>
  </div>
  <a type="submit" class="btn btn-default" id="btn-search">查询</a>
</form>
          <div class="table-responsive">
            <table id="list-table" class="table table-striped">
            </table>
          </div>
          
<script src="<s:url value="/bower_components/bootstrap-table/dist/bootstrap-table.min.js"></s:url>"></script>
<script src="<s:url value="/bower_components/bootstrap-table/dist/locale/bootstrap-table-zh-CN.min.js"></s:url>"></script>
<script src="<s:url value="/bower_components/smalot-bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js"></s:url>"></script>
<script src="<s:url value="/bower_components/smalot-bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></s:url>"></script>
<script src="<s:url value="/scripts/datatable.js"></s:url>"></script>
<script>
var getPagerData = function (pageIndex, callback) {
    var selectedModelIds = [];
    $("#main_modelselector").find("input:checkbox:checked").each(function(){
        var modelid = $(this).val();
        selectedModelIds.push(parseInt(modelid));
    });

    var params = {
        searcher: {
            keyword: '',
            models: selectedModelIds,
            starttime: $("#txt_starttime").val() + ":00",
            endtime: $("#txt_endtime").val() + ":00"
        },
        pager: {
            pageIndex: pageIndex
        }
    };

    jsless.ajax({
        url: "<s:url namespace='/json/regular/cekongevents' action='getListByPager'></s:url>",
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

var table = null;

var buildtable = function (pageIndex, pageSize, recordCount, listdata) {

	table = new datatable({
		tableSelector: "#list-table",
		columns: [{
            	headFormatter: function(table){
            		var checkbox = $("<input type='checkbox' />").val("toggleall");
            		checkbox.change(function(){
            			table.find("tbody tr").each(function(){
            				var row = $(this);
            				row.find(".itemcheck").prop("checked", checkbox.prop("checked"));
            				if(checkbox.prop("checked")){
            					row.addClass("warning");
            				} else {
            					row.removeClass("warning");
            				}
            			});

            		});
                	return checkbox;
            	},
            	bodyFormatter: function(row, rowdata){
                	var checkbox = $("<input type='checkbox' class='itemcheck' />").val(rowdata.id);
                	checkbox.change(function(){
                		if(checkbox.prop("checked")){
                			row.addClass("warning");
                		} else {
                			row.removeClass("warning");
                		}

                	});
                	return checkbox;
                }
            },{
                //field: 'id',
                checkbox: true
            }, {
                field: 'modecode',
                title: '型号代号'
            }, {
            	field: 'subsystemcode',
            	title: '分系统'
            }, {
                field: 'eventtype',
                title: '事件类型'
            },{
                field: 'startdt',
                title: '开始时间'
            }, {
                field: 'enddt',
                title: '结束时间'
            }, {
                field: 'desc',
                title: '说明'
            }, {
                field: 'conformperson',
                title: '确认人'
            }, {
                field: 'locationdesc',
                title: '位保情况描述'
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

$(function () {
    doSearch(0);

    $("#btn-search").click(function(e){
        doSearch(0);
    });

    $("#txt_starttime").datetimepicker();
    $("#txt_endtime").datetimepicker();
});
</script>
</body>
</html>
