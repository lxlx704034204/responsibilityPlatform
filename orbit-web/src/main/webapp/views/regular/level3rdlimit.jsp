<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <title>三级门限报警分析</title>
        <link type="text/css" rel="stylesheet" href="<s:url value="/bower_components/bootstrap-table/dist/bootstrap-table.min.css"></s:url>" />
    </head>
    <body>
        <h2 class="page-header">三级门限报警分析
            <button id="btn_batchset" class="btn btn-default pull-right" data-target="#modal_batchset" data-toggle="modal">批量分析</button>
        </h2>

        <form class="form-inline">
            <div class="form-group">
                <label for="exampleInputName2">卫星型号：</label>
                <div class="dropdown mode-selector">
                    <button class="btn btn-default dropdown-toggle" type="button" id="btn_child_modelselector" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                        <span class="btn-text">型号1,型号2</span>
                        <span class="caret"></span>
                    </button>
                    <ul id="child_modelselector" class="dropdown-menu" aria-labelledby="btn_child_modelselector">
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
                <input type="text" class="form-control" id="exampleInputEmail2" placeholder="报警开始时间" style="width:200px;"/></div>
            <div class="form-group">
                <label for="exampleInputEmail2">报警结束时间：</label>
                <input type="text" class="form-control" id="exampleInputEmail3" placeholder="报警结束时间" style="width:200px;"/></div>
            <button id="btn-search" type="submit" class="btn btn-default">查询</button>
        </form>

        <div class="table-responsive" style="margin-top:10px;">
            <table id="list-table"></table>
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
                                <select class="form-control">
                                    <option>XXXX</option>
                                </select>
                            </div>
                            <div class="form-group">
                                <label for="exampleInputPassword1">情况说明：</label>
                                <input type="text" class="form-control" id="exampleInputPassword1" placeholder="说明"/>
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                        <button type="button" class="btn btn-primary" id="btn_batchconform">保存</button>
                    </div>
                </div>
            </div>
        </div>

        <script src="<s:url value="/bower_components/bootstrap-table/dist/bootstrap-table.min.js"></s:url>"></script>
    <script src="<s:url value="/bower_components/bootstrap-table/dist/locale/bootstrap-table-zh-CN.min.js"></s:url>"></script>
<script>

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
                    $("#child_modelselector").empty();
                    for(var model in models){
                        var li = $("<li></li>");
                        var div = $("<div></div>").addClass("dropdown-menuitem")
                        var label = $("<label></label>")
                        var checkbox = $("<input type='checkbox' class='modelcheckor' />")
                            .val(model.id).attr("code", model.code).attr("modelname", model.name);
                        var span = $("<span></span>").text(model.name);
                        $("#child_modelselector").append(li.append(div.append(label.append(checkbox).append(span))));
                    }
                    updateBtnMainModelSelector();
                }
            } else {
                // error
            }
        }
    });
};



var doSearch = function () {
    var params = {
        searcher: {
            keyword: '',
            models: []
        },
        pager: {
            pageIndex: 0
        }
    };

    jsless.ajax({
        url: "<s:url namespace='/json/regular/levelthree' action='getListByPager'></s:url>",
        data: params,
        success: function (rep) {
            if (rep.statusCode == 200) {
                var ctn = rep.content;
                var records = ctn.records;
                var pageInfo = ctn.pageInfo;

                buildtable(pageInfo.pageCount, pageInfo.pageSize, pageInfo.recordCount, records);
            } else {}
        }
    });
};

var buildtable = function (pageCount, pageSize, recordCount, listdata) {
    $('#list-table').bootstrapTable({
        locale: 'zh-CN',
        pagination: true,
        pageNumber: pageCount,
        pageSize: pageSize,
        sidePagination: 'server',
        totalRows: recordCount,
        columns: [
            {
                field: 'id',
                checkbox: true,
                title: 'Item ID'
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
        data: listdata
    });
};

var bindBtnBatchConfromClick = function(){
    $("#btn_batchconform").click(function(){

    });
};

$(function () {
    doSearch();
    
    $("#btn-search").click(function(e){
        doSearch();
    });
});
</script>
</body>
</html>
