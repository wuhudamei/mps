<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>任务包数量</title>
<meta name="decorator" content="default"/>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="javascript:goToHistory()">返回</a></li>
	</ul>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<tr colspan="2">任务包数量：${fn:length(taskpackageList) }</h3>个</tr>
				<th>任务包名称</th>
				<th>任务包状态</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${taskpackageList}" var="taskpackageList">
			<tr>
				<td>
					${taskpackageList.packageName}
				</td>
				<td>
					${fns:getDictLabel(taskpackageList.packageStateId, 'taskpackage_status', '')}<br/>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
</body>
</html>