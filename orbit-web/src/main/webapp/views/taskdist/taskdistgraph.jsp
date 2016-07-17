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
            <table class="table table-striped table-hover">
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
              <tbody>
                <tr>
                  <td>1</td>
                  <td>发射保障策划</td>
                  <td>XXXX</td>
				  <td>XXXX</td>
                  <td><a href="#">发射保障策划</a></td>
                  <td><select><option selected>待确认</option><option>待提交</option></select></td>
				  <td><a href="#">点击查看</a></td>
				  <td><button class="btn btn-default btn-xs" type="button">修改</button></td>
                </tr>
                <tr>
                  <td>2</td>
                  <td>软硬件配置</td>
                  <td>XXXX</td>
				  <td>XXXX</td>
                  <td><button class="btn btn-default btn-xs" type="button">提交</button></td>
                  <td><select><option>待确认</option><option selected>待提交</option></select></td>
				  <td><a href="#">点击查看</a></td>
				  <td><button class="btn btn-default btn-xs" type="button">修改</button></td>
                </tr>
              </tbody>
            </table>
          </div>
		  
		  <h3>二、交付前工作 <button class="btn btn-default pull-right">设置新任务</button></h3>
          <div class="table-responsive">
            <table class="table table-striped table-hover">
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
              <tbody>
                <tr>
                  <td>1</td>
                  <td>XXXX</td>
                  <td>XXXX</td>
				  <td></td>
                  <td></td>
                  <td></td>
				  <td></td>
				  <td></td>
                </tr>
                <tr>
                  <td>2</td>
                  <td>XXXX</td>
                  <td>XXXX</td>
				  <td></td>
                  <td></td>
                  <td></td>
				  <td></td>
				  <td></td>
                </tr>
				<tr>
                  <td>3</td>
                  <td>XXXX</td>
                  <td>XXXX</td>
				  <td></td>
                  <td></td>
                  <td></td>
				  <td></td>
				  <td></td>
                </tr>
				<tr>
                  <td>4</td>
                  <td>XXXX</td>
                  <td>XXXX</td>
				  <td></td>
                  <td></td>
                  <td></td>
				  <td></td>
				  <td></td>
                </tr>
				<tr>
                  <td>5</td>
                  <td>XXXX</td>
                  <td>XXXX</td>
				  <td></td>
                  <td></td>
                  <td></td>
				  <td></td>
				  <td></td>
                </tr>
              </tbody>
            </table>
          </div>
		  
		  <h3>三、交付后工作 <button class="btn btn-default pull-right">设置新任务</button></h3>
          <div class="table-responsive">
            <table class="table table-striped table-hover">
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
              <tbody>
                <tr>
                  <td>1</td>
                  <td>XXXX</td>
                  <td>XXXX</td>
				  <td></td>
                  <td></td>
                  <td></td>
				  <td></td>
				  <td></td>
                </tr>
                <tr>
                  <td>2</td>
                  <td>XXXX</td>
                  <td>XXXX</td>
				  <td></td>
                  <td></td>
                  <td></td>
				  <td></td>
				  <td></td>
                </tr>
				<tr>
                  <td>3</td>
                  <td>XXXX</td>
                  <td>XXXX</td>
				  <td></td>
                  <td></td>
                  <td></td>
				  <td></td>
				  <td></td>
                </tr>
				<tr>
                  <td>4</td>
                  <td>XXXX</td>
                  <td>XXXX</td>
				  <td></td>
                  <td></td>
                  <td></td>
				  <td></td>
				  <td></td>
                </tr>
				<tr>
                  <td>5</td>
                  <td>XXXX</td>
                  <td>XXXX</td>
				  <td></td>
                  <td></td>
                  <td></td>
				  <td></td>
				  <td></td>
                </tr>
              </tbody>
            </table>
          </div>
		  <h3>四、寿命终结工作 <button class="btn btn-default pull-right">设置新任务</button></h3>
          <div class="table-responsive">
            <table class="table table-striped table-hover">
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
              <tbody>
                <tr>
                  <tr>
                  <td>1</td>
                  <td>XXXX</td>
                  <td>XXXX</td>
				  <td></td>
                  <td></td>
                  <td></td>
				  <td></td>
				  <td></td>
                </tr>
                <tr>
                  <td>2</td>
                  <td>XXXX</td>
                  <td>XXXX</td>
				  <td></td>
                  <td></td>
                  <td></td>
				  <td></td>
				  <td></td>
                </tr>
				<tr>
                  <td>3</td>
                  <td>XXXX</td>
                  <td>XXXX</td>
				  <td></td>
                  <td></td>
                  <td></td>
				  <td></td>
				  <td></td>
                </tr>
				<tr>
                  <td>4</td>
                  <td>XXXX</td>
                  <td>XXXX</td>
				  <td></td>
                  <td></td>
                  <td></td>
				  <td></td>
				  <td></td>
                </tr>
				<tr>
                  <td>5</td>
                  <td>XXXX</td>
                  <td>XXXX</td>
				  <td></td>
                  <td></td>
                  <td></td>
				  <td></td>
				  <td></td>
                </tr>
              </tbody>
            </table>
          </div>
		  <div class="row">
			<div class="col-sm-12">
				<a href="<s:url namespace='/taskdist' action='indexlist'></s:url>" class="btn btn-primary text-center">全部任务节点信息</a>
			</div>
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
			$("#btn_setnewtask").click(function(){
				
			});
		  </script>
</body>
</html>
