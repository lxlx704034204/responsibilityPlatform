<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>三级门限报警分析</title>
    <link type="text/css" rel="stylesheet"
          href="<s:url value="/bower_components/bootstrap-table/dist/bootstrap-table.min.css"></s:url>"/>
    <link type="text/css" rel="stylesheet"
          href="<s:url value="/bower_components/smalot-bootstrap-datetimepicker/css/bootstrap-datetimepicker.min.css"></s:url>"/>
</head>
<body>
<h2 class="page-header">三级门限报警分析
    <button id="btn_batchset" class="btn btn-default pull-right" data-target="#modal_batchset"
            data-toggle="modal">批量分析
    </button>
</h2>

<form class="form-inline">
    <div class="form-group">
        <label for="exampleInputName2">卫星型号：</label>
        <div class="dropdown mode-selector">
            <button class="btn btn-default dropdown-toggle" type="button"
                    id="ddm_child_selectmodels" data-toggle="dropdown" aria-haspopup="true"
                    aria-expanded="true">
                <span class="btn-text">型号1,型号2</span>
                <span class="caret"></span>
            </button>
            <ul id="child_modelselector" class="dropdown-menu"
                aria-labelledby="ddm_child_selectmodels"></ul>
        </div>

    </div>
    <div class="form-group">
        <label for="exampleInputEmail2">报警开始时间：</label>
        <input type="text" class="form-control" id="txt_alertstarttime" placeholder="报警开始时间"
               style="width:200px;" data-date-format="yyyy-mm-dd hh:ii"/></div>
    <div class="form-group">
        <label for="exampleInputEmail2">报警结束时间：</label>
        <input type="text" class="form-control" id="txt_alertendtime" placeholder="报警结束时间"
               style="width:200px;" data-date-format="yyyy-mm-dd hh:ii"/></div>
    <a id="btn-search" type="submit" class="btn btn-default">查询</a>
</form>

<div class="table-responsive" style="margin-top:10px;">
    <table id="list-table" class="table table-striped"></table>
</div>

<div class="modal fade" id="modal_batchset" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel">
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
                        <label for="exampleInputEmail1">确认类别：</label>
                        <select class="form-control" id="slt_batch_eventtype">
                            <option value="星上异常报警">星上异常报警</option>
                            <option value="地面异常报警">地面异常报警</option>
                            <option value="测控事件">测控事件</option>
                            <option value="误码和野值">误码和野值</option>
                            <option value="门限设置不当">门限设置不当</option>
                            <option value="知识错误">知识错误</option>
                            <option value="其他">其他</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="exampleInputPassword1">情况说明：</label>
                        <textarea type="text" class="form-control" id="txt_batch_eventdesc"
                                  placeholder="说明" rows="4"></textarea>
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

<div class="modal fade" id="modal_updatesingledesc" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel">情况说明</h4>
            </div>
            <div class="modal-body">
                <form>
                    <div class="form-group">
                        <textarea type="text" class="form-control" id="txt_single_eventdesc"
                                  placeholder="说明" rows="4"></textarea>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" id="btn_savesingledesc">保存</button>
            </div>
        </div>
    </div>
</div>

<script src="<s:url value="/bower_components/smalot-bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js"></s:url>"></script>
<script src="<s:url value="/bower_components/smalot-bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></s:url>"></script>
<script src="<s:url value="/scripts/datatable.js"></s:url>"></script>
<script>

    /**
     * update the button of model selector
     **/
    var updateBtnChildModelSelector = function () {
        var selectedModelNames = [];
        $("#child_modelselector").find("input:checkbox:checked").each(function () {
            var modelName = $(this).attr("modelname");
            selectedModelNames.push(modelName);
        });
        var btntext = null;
        if(selectedModelNames.length == 0){
        	btntext = "请选择型号";
        } else if(selectedModelNames.length > 2){
        	btntext = "多个型号";
        } else {
        	btntext = selectedModelNames.join(',');
        }
        $("#ddm_child_selectmodels").find(".btn-text").text(btntext);
    };

    /**
     *build the model selector(the pop window)
     **/
    var buildChildModelSelector = function () {
        return jsless.ajax({
                               url: "<s:url namespace='/json/models' action='getAdminModels'></s:url>",
                               data: {},
                               success: function (rep) {
                                   if (rep.statusCode == 200) {
                                       var models = rep.content;
                                       if (models) {
                                           $("#child_modelselector").empty();
                                           for (var i = 0; i < models.length; i++) {
                                               var model = models[i];
                                               var li = $("<li></li>");
                                               var div = $("<div></div>")
                                                       .addClass("dropdown-menuitem")
                                               var label = $("<label></label>")
                                               var checkbox = $(
                                                       "<input type='checkbox' class='modelcheckor' />")
                                                       .val(model.id).attr("code", model.code)
                                                       .attr("modelname", model.name)
                                                       .prop("checked", model.selected);
                                               var span = $("<span></span>").text(model.name);
                                               $("#child_modelselector").append(li.append(
                                                       div.append(label.append(checkbox)
                                                                          .append(span))));
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
    var bindChangeEventToChildModelSelector = function () {
        $("#child_modelselector").delegate(".modelcheckor", "change", function () {
            var selectedModelIds = [];
            $("#child_modelselector").find("input:checkbox:checked").each(function () {
                var modelid = $(this).val();
                selectedModelIds.push(parseInt(modelid));
            });
            var params = {selectedmodels: selectedModelIds};
            return jsless.ajax({
                                   url: "<s:url namespace='/json/models' action='updateSelectedModels'></s:url>",
                                   data: params,
                                   success: function (rep) {
                                       if (rep.statusCode == 200) {
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
        $("#child_modelselector").find("input:checkbox:checked").each(function () {
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
                                if (callback) {
                                    callback(rep.content);
                                }

                            } else {
                                // error
                            }
                        }
                    });
    };

    var doSearch = function (pageIndex) {
        getPagerData(
                pageIndex,
                function (result) {
                    var records = result.records;
                    var pageInfo = result.pageInfo;

                    buildtable(pageInfo.pageIndex, pageInfo.pageSize, pageInfo.recordCount,
                               records);
                }
        );
    };

    var table = null;

    var buildtable = function (pageIndex, pageSize, recordCount, listdata) {

        table = new datatable({
            tableSelector: "#list-table",
            columns: [{
                headFormatter: function (table) {
                    var checkbox = $("<input type='checkbox' />").val("toggleall");
                    checkbox.change(function () {
                        table.find("tbody tr").each(function () {
                            var row = $(this);
                            row.find(".itemcheck").prop("checked", checkbox.prop("checked"));
                            if (checkbox.prop("checked")) {
                                row.addClass("warning");
                            } else {
                                row.removeClass("warning");
                            }
                        });

                    });
                    return checkbox;
                },
                bodyFormatter: function (row, rowdata) {
                    var checkbox = $("<input type='checkbox' class='itemcheck' />").val(rowdata.id);
                    checkbox.change(function () {
                        if (checkbox.prop("checked")) {
                            row.addClass("warning");
                        } else {
                            row.removeClass("warning");
                        }

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
                title: '确认类别',
                bodyFormatter: function (row, rowdata) {
                	var displaytext = rowdata.eventtype ? rowdata.eventtype : null;
                	var select = $("<select></select>");
                    select.append($("<option value='星上异常报警'>星上异常报警</option>"));
                    select.append($("<option value='地面异常报警'>地面异常报警</option>"));
                    select.append($("<option value='测控事件'>测控事件</option>"));
                    select.append($("<option value='误码和野值'>误码和野值</option>"));
                    select.append($("<option value='门限设置不当'>门限设置不当</option>"));
                    select.append($("<option value='知识错误'>知识错误</option>"));
                    select.append($("<option value='其他'>其他</option>"));
                    select.val(rowdata.eventtype);
                    select.change(function(){
                    	$(this).next().text($(this).val()).show();
                    	$(this).hide();
                    });
                    select.hide();
                    var span = $("<span></span>").text(rowdata.eventtype);
                	var wrapper = $("<div title=双击以进行编辑 style='height:100%;min-height:30px;min-width:50px;line-height:30px;'></div>");
                	wrapper.attr("eventtype", rowdata.eventtype);
                	wrapper.append(select);
                	wrapper.append(span);
                	wrapper.dblclick(function(){
                		$(this).find("select").show();
                		$(this).find("span").hide();
                	});
                	wrapper.focusout(function(){
                		$(this).find("select").hide();
                		$(this).find("span").show();
                	});
                	wrapper.mouseover(function(){
                		$(this).css("background-color","#ffffcc");
                	});
                	wrapper.mouseout(function(){
                		$(this).css("background-color", "inherit")
                	});
                	return wrapper;
                }
            }, {
                field: 'desc',
                title: '情况说明',
                bodyFormatter: function (row, rowdata) {
                	var displaytext = null;
                	if(rowdata.desc){
                		displaytext = rowdata.desc.length > 50 ? rowdata.desc.substr(0, 50) + "..." : rowdata.desc;
                	}
                	var wrapper = $("<div title=双击以进行编辑 style='height:100%;min-height:30px;min-width:50px;line-height:30px;'></div>");
                	wrapper.attr("desc", rowdata.desc);
                	wrapper.html(displaytext);
                	wrapper.dblclick(function(){
                		var desc = $(this).attr("desc");
                		$("#txt_single_eventdesc").val(desc);
                        $("#modal_updatesingledesc").modal("show");
                	});
                	wrapper.mouseover(function(){
                		$(this).css("background-color","#ffffcc");
                	});
                	wrapper.mouseout(function(){
                		$(this).css("background-color", "inherit")
                	});
                	return wrapper;
                }
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
            getPagerRows: function (pageIndex, pageSize) {
                getPagerData(
                        pageIndex,
                        function (result) {
                            var records = result.records;
                            var pageInfo = result.pageInfo;
                            table.reload(pageIndex, records);
                        }
                );
                var txt = $("<textarea></textarea>");
                return txt;
            }
        });

        table.render();

        return table;
    };

    var bindBtnBatchUpdateClick = function () {
        $("#btn_batchupdate").click(function () {
            var selectedIds = [];
            $("#list-table").find("tbody tr.warning").each(function () {
                selectedIds.push(parseInt($(this).find(".itemcheck:first").val()));
            });
            if (selectedIds.length == 0) {
                alert("请选择要处理的报警!");
                return;
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
                                    getPagerData(
                                            table.getPageIndex(),
                                            function (result) {
                                                var records = result.records;
                                                var pageInfo = result.pageInfo;
                                                table.reload(table.getPageIndex(), records);
                                            }
                                    );
                                } else {
                                    // error
                                }
                            }
                        });
        });
    };

    $(function () {
        bindChangeEventToChildModelSelector();
        $.when(buildChildModelSelector()).done(function () {
            doSearch(0);
        });

        $("#btn-search").click(function (e) {
            doSearch(0);
        });

        $("#txt_alertstarttime").datetimepicker();
        $("#txt_alertendtime").datetimepicker();
        bindBtnBatchUpdateClick();
    });
</script>
</body>
</html>
