<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>测控事件</title>
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
            <table id="list-table">
            </table>
          </div>
          
<script src="<s:url value="/bower_components/bootstrap-table/dist/bootstrap-table.min.js"></s:url>"></script>
<script src="<s:url value="/bower_components/bootstrap-table/dist/locale/bootstrap-table-zh-CN.min.js"></s:url>"></script>
<script src="<s:url value="/bower_components/smalot-bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js"></s:url>"></script>
<script src="<s:url value="/bower_components/smalot-bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></s:url>"></script>
<script>
var doSearch = function (pageIndex) {
    var params = {
        searcher: {
            keyword: '',
            alertstarttime: $("#txt_starttime").val(),
            alertendtime: $("#txt_endtime").val()
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
                var ctn = rep.content; 
                var records = ctn.records;
                var pageInfo = ctn.pageInfo;

                buildtable(pageInfo.pageCount, pageInfo.pageSize, pageInfo.recordCount, records);
            } else {
                // error
            }
        }
    });
};

var buildtable = function (pageCount, pageSize, recordCount, listdata) {
	table = $('#list-table').bootstrapTable({
        locale: 'zh-CN',
        pagination: true,
        pageNumber: pageCount,
        pageSize: pageSize,
        sidePagination: 'server',
        totalRows: recordCount,
        columns: [
            {
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
        data: listdata,
        onPageChange: function(number, size){
        	doSearch(number -1);
        }
    });
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
