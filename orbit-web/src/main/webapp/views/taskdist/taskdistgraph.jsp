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
                    <div class="startcycle"></div>
                    <div class="blankholder"></div>
                    <div class="flowtitlecontainer">
                        <div class="title">发射及保障</div>
                        <div class="daterange">(2016-02-11)</div>
                    </div>
                </td>
                <td>
                    <div style="height:300px;">
                    </div>
                </td>
            </tr>
            <tr class="stage">
                <td class="flowtitlearea">
                    <div class="flowtitlecontainer">
                        <div class="title">交付前</div>
                        <div class="daterange">(2016-03-31)</div>
                    </div>
                </td>
                <td>
                    <div style="height:300px;">
                    </div>
                </td>
            </tr>
            <tr class="stage">
                <td class="flowtitlearea">
                    <div class="flowtitlecontainer">
                        <div class="title">交付后</div>
                        <div class="daterange">(日期未指定)</div>
                    </div>
                </td>
                <td>
                    <div style="height:300px;">
                    </div>
                </td>
            </tr>
            <tr class="stage">
                <td class="flowtitlearea bottomstage">
                    <div class="flowtitlecontainer">
                        <div class="title">寿命终结</div>
                        <div class="daterange">(日期未指定)</div>
                    </div>
                    <div class="blankholder"></div>
                    <div class="stopcycle"></div>
                </td>
                <td>
                    <div style="height:300px;">
                    </div>
                </td>
            </tr>
        </table>
        <div class="row">
            <div class="col-sm-12">
                <a href="<s:url namespace='/taskdist' action='indexlist'></s:url>" class="btn btn-primary text-center">全部任务节点信息</a>
            </div>
        </div>
    </body>
</html>
