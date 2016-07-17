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
    <input type="text" class="form-control" id="exampleInputEmail2" placeholder="开始时间">
  </div>
<div class="form-group">
    <label for="exampleInputEmail2">结束时间：</label>
    <input type="text" class="form-control" id="exampleInputEmail3" placeholder="结束时间">
  </div>
  <button type="submit" class="btn btn-default">查询</button>
</form>

          
		
          <div class="table-responsive">
            <table class="table table-striped table-hover">
              <thead>
                <tr>
                  <th>型号</th>
                  <th>分系统</th>
                  <th>事件类型</th>
                  <th>开始时间</th>
                  <th>结束时间</th>
				  <th>说明信息</th>
				  <th>结果确认</th>
				  <th>位保情况简述</th>
                </tr>
              </thead>
              <tbody>
                <tr>
                  <td>XXXX</td>
                  <td>XXXX</td>
                  <td><select><option>位置保持</option></select></td>
				  <td>XXXX</td>
                  <td>XXXX</td>
                  <td>XXXX</td>
				  <td>已确认</td>
				  <td></td>
                </tr>
                <tr>
                  <td>XXXX</td>
                  <td>XXXX</td>
                  <td><select><option>位置保持</option></select></td>
				  <td>XXXX</td>
                  <td>XXXX</td>
                  <td>XXXX</td>
				  <td><button class="btn btn-default btn-xs">确认</button></td>
				  <td></td>
                </tr>
                
              </tbody>
            </table>
          </div>
</body>
</html>
