<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<script type="text/javascript">
var type = "${ type }";
if(type == "uploadTaskAttachment"){
	var callback = "${callback}";
	var taskId = "${taskid}";
	var attachmentId = 1;//"${attachmentId}";
	var attachmentName = "${attachmentName}";
	var js = "window.parent." + callback + "(" + taskId + ", " + attachmentId + ",'" + attachmentName + "')";
	jsless.eval(js);
}
</script>
</body>
</html>