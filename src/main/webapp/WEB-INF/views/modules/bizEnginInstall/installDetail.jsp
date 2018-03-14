<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="decorator" content="default"/>
<title>申请详情</title>
</head>
<body>
	<ul class="nav nav-tabs">
			<li><a href="${ctx}/bizengininstall/bizEnginInstall/list">安装项列表</a></li>
			<li class="active"><a href="">申请详情</a></li>
		</ul>
			<form:form class="breadcrumb form-search">
			<table id="contentTable" class="table table-striped table-bordered table-condensed">
				<tr>
					<td style="text-align:center" >
						<font><h3>${order.communityName}-${order.buildNumber}-${order.buildUnit}-${order.buildRoom}-${order.customerName}</h3></font>
					</td>
				</tr>
				<%-- <tr>
					<td colspan="2" style="width:100%"><label>申请详情：</label>
						${order.communityName} - ${order.buildNumber} - ${order.buildUnit} - ${order.buildRoom} - ${order.customerName}
					</td>
				</tr> --%>
				<tr>
					<td style="width:50%"><label>计划安装日期:</label><fmt:formatDate type="date" value="${InstallPlan.applyIntoDate }"/></td>
				</tr>
				<tr>
					<td style="width:50%"><label>安装内容:</label>${InstallPlan.installItemName }</td>
				</tr>
				<tr>
					<td style="width:50%"><label>备注:</label>${InstallPlan.remarks }</td>
				</tr>
				<tr>
					<td style="width:50%"><label>申请日期:</label>
					<fmt:formatDate value="${InstallPlan.applyIntoCreateDatetime }" pattern="yyyy-MM-dd"/></td>
				</tr>
				<tr>
					<td style="width:50%"><label>供应商确认日期:</label>
					<fmt:formatDate value="${InstallPlan.supplierConfirmIntoDate }" pattern="yyyy-MM-dd"/></td>
				</tr>
			</table>
			</form:form>
</body>
</html>