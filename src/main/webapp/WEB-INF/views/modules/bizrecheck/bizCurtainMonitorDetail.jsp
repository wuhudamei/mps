<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta name="decorator" content="default"/>
	<title>窗帘复尺</title>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/bizorderrecheckmonitor/bizOrderRecheckMonitor/list">复尺未转单监控列表</a></li>
		<li class="active"><a href="#">窗帘复尺详情</a></li>
	</ul>

	<div class="container">
		<div class="col-md-12 column">
			<div >
				<h3>
					<center>窗帘复尺详情</center>
				</h3>
			</div>
		</div>

		<table style="width:100%" align="center" valign="center" class="table table-striped table-bordered table-condensed">
			<tr>
				<td colspan="2" style="width:100%"><label>项目信息：</label>
				${order.communityName}-${order.buildNumber}-${order.buildUnit}-${order.buildRoom}-${order.customerName}  </td>
			</tr>
			<tr>
				<td style="width:50%"><label>预计安装日期：</label><fmt:formatDate type="date" value="${scaleBill.planInstallDate }"/></td>
				<td style="width:50%"><label>复尺内容：</label>${fns:getDictLabel(scaleBill.type, 'complex_content', '')}</td>
			</tr>
			<tr>
				<td style="width:50%"><label>复尺人：</label>${order.employeeName } ${order.employeePhone }</td>
				<td style="width:50%"><label>申请复尺日期：</label><fmt:formatDate type="both" value="${scaleBill.createDate }"/></td>
			</tr>
		</table>
		
		<table class="table table-striped table-bordered table-condensed">
			<thead><tr>
				<th>复尺位置</th>
				<th>窗帘杆类型</th>
				<th>窗帘杆长</th>
				<th>帘布高度</th></tr>
			</thead>
			<tbody>
			<c:forEach items="${listCurtain }" var="curtain">
				<tr><td>${fns:getDictLabel(curtain.position, 'recheck_curtain_position', '')}</td>
				<td>${fns:getDictLabel(curtain.poleType, 'recheck_curtain_poletype', '')}</td>
				<td><fmt:formatNumber value="${curtain.poleLength }" pattern="#"/>mm</td>
				<td><fmt:formatNumber value="${curtain.clothHeight }" pattern="#"/>mm</td></tr>
			</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>