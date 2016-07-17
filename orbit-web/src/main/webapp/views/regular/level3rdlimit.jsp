<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>三级门限报警分析</title>
</head>
<body>
          <h2 class="page-header">三级门限报警分析 <button id="btn_batchset" class="btn btn-default pull-right" data-target="#modal_batchset" data-toggle="modal">批量分析</button> </h2>

          <div class="row placeholders">
            <div class="col-xs-6 col-sm-3 placeholder">
              卫星型号：<select><option>型号1</option></select>
            </div>
            <div class="col-xs-6 col-sm-3 placeholder">
              报警开始时间：<input type="text" />
            </div>
            <div class="col-xs-6 col-sm-3 placeholder">
              报警结束时间：<input type="text" />
            </div>
            <div class="col-xs-6 col-sm-3 placeholder">
              <button type="button" class="btn btn-primary">查询</button>
            </div>
          </div>

          
		
          <div class="table-responsive">
            <table class="table table-striped table-hover">
              <thead>
                <tr>
                  <th><input type="checkbox" /></th>
                  <th>型号代号</th>
                  <th>报警开始时间</th>
                  <th>报警结束时间</th>
                  <th>报警信息</th>
				  <th>事件类别</th>
				  <th>情况说明</th>
				  <th>确认人</th>
				  <th>确认时间</th>
                </tr>
              </thead>
              <tbody>
                <tr>
                  <td><input type="checkbox" /></td>
                  <td>A211</td>
                  <td>2016-01-01 08:30:00</td>
				  <td>2016-01-01 08:38:00</td>
                  <td>STOP</td>
                  <td>ClassA</td>
				  <td></td>
				  <td></td>
				  <td></td>
                </tr>
                <tr>
                  <td><input type="checkbox" /></td>
                  <td>A211</td>
                  <td>2016-01-01 08:30:00</td>
				  <td>2016-01-01 08:38:00</td>
                  <td>STOP</td>
                  <td>ClassA</td>
				  <td></td>
				  <td></td>
				  <td></td>
                </tr>
                <tr>
                  <td><input type="checkbox" /></td>
                  <td>A211</td>
                  <td>2016-01-01 08:30:00</td>
				  <td>2016-01-01 08:38:00</td>
                  <td>STOP</td>
                  <td>ClassA</td>
				  <td></td>
				  <td></td>
				  <td></td>
                </tr>
                <tr>
                  <td><input type="checkbox" /></td>
                  <td>A211</td>
                  <td>2016-01-01 08:30:00</td>
				  <td>2016-01-01 08:38:00</td>
                  <td>STOP</td>
                  <td>ClassA</td>
				  <td></td>
				  <td></td>
				  <td></td>
                </tr>
                <tr>
                  <td><input type="checkbox" /></td>
                  <td>A211</td>
                  <td>2016-01-01 08:30:00</td>
				  <td>2016-01-01 08:38:00</td>
                  <td>STOP</td>
                  <td>ClassA</td>
				  <td></td>
				  <td></td>
				  <td></td>
                </tr>
                <tr>
                  <td><input type="checkbox" /></td>
                  <td>A211</td>
                  <td>2016-01-01 08:30:00</td>
				  <td>2016-01-01 08:38:00</td>
                  <td>STOP</td>
                  <td>ClassA</td>
				  <td></td>
				  <td></td>
				  <td></td>
                </tr>
                <tr>
                  <td><input type="checkbox" /></td>
                  <td>A211</td>
                  <td>2016-01-01 08:30:00</td>
				  <td>2016-01-01 08:38:00</td>
                  <td>STOP</td>
                  <td>ClassA</td>
				  <td></td>
				  <td></td>
				  <td></td>
                </tr>
                <tr>
                  <td><input type="checkbox" /></td>
                  <td>A211</td>
                  <td>2016-01-01 08:30:00</td>
				  <td>2016-01-01 08:38:00</td>
                  <td>STOP</td>
                  <td>ClassA</td>
				  <td></td>
				  <td></td>
				  <td></td>
                </tr>
                <tr>
                  <td><input type="checkbox" /></td>
                  <td>A211</td>
                  <td>2016-01-01 08:30:00</td>
				  <td>2016-01-01 08:38:00</td>
                  <td>STOP</td>
                  <td>ClassA</td>
				  <td></td>
				  <td></td>
				  <td></td>
                </tr>
                <tr>
                  <td><input type="checkbox" /></td>
                  <td>A211</td>
                  <td>2016-01-01 08:30:00</td>
				  <td>2016-01-01 08:38:00</td>
                  <td>STOP</td>
                  <td>ClassA</td>
				  <td></td>
				  <td></td>
				  <td></td>
                </tr>
              </tbody>
            </table>
          </div>
		  
		  <div class="modal fade" id="modal_batchset" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
			  <div class="modal-dialog" role="document">
				<div class="modal-content">
				  <div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					<h4 class="modal-title" id="myModalLabel">批量分类分析</h4>
				  </div>
				  <div class="modal-body">
					<div class="container-fluid">
					<div class="row">
						<div class="col-sm-4">事件类别：</div>
						<div class="col-sm-6"><select><option>XXXX</option></select></div>
					</div>
					<div class="row">
						<div class="col-sm-4">情况说明：</div>
						<div class="col-sm-6"><textarea ></textarea></div>
					</div>
					</div>
				  </div>
				  <div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary">保存</button>
				  </div>
				</div>
			  </div>
			</div>
</body>
</html>