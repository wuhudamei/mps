<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>手机app版本管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/sysappversion/sysAppVersion/list">手机app版本列表</a></li>
		<shiro:hasPermission name="sysappversion:sysAppVersion:edit"><li><a href="${ctx}/sysappversion/sysAppVersion/form">手机app版本添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="sysAppVersion" action="${ctx}/sysappversion/sysAppVersion/list" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>APP名称：</label>
				<form:select path="appType" class="input-medium needClear">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictListByType('app_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>是否使用：</label>
				<form:select path="isEnabled" class="input-medium needClear">
					<form:option value="" label=""/>
					<form:option value="1" label="是"/>
					<form:option value="0" label="否"/>
				</form:select>
			</li>
			<%--<li><label>APP版本：</label>
				<form:select path="phoneType" class="input-medium needClear">
					<form:option value="" label=""/>
					<form:option value="1" label="Android"/>
					<form:option value="2" label="IOS"/>
				</form:select>
			</li>--%>
			<li><label>版本号：</label>
				<form:input path="version" htmlEscape="false" maxlength="20" class="input-medium needClear"/>
			</li>
			<li><label style="width: 120px">上传时间：</label>
				<input name="beginUploadDatetime" type="text" readonly="readonly" maxlength="20" class="input-medium needClear Wdate"
					   value="<fmt:formatDate value="${sysAppVersion.beginUploadDatetime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/> -
				<input name="endUploadDatetime" type="text" readonly="readonly" maxlength="20" class="input-medium needClear Wdate"
					   value="<fmt:formatDate value="${sysAppVersion.endUploadDatetime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="btns"><input class="btn btn-primary clearBtn" type="button" value="清空"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>APP名称</th>
				<th>版本号</th>
				<th>下载URL地址</th>
				<th>上传人</th>
				<th>上传时间</th>
				<th>是否使用</th>
				<shiro:hasPermission name="sysappversion:sysAppVersion:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="sysAppVersion">
			<tr>
				<td>${sysAppVersion.appName}</td>
				<td>${sysAppVersion.version}</td>
				<td>${sysAppVersion.downloadUrl}</td>
				<td>${sysAppVersion.uploadEmployeeName}</td>
				<td><fmt:formatDate value="${sysAppVersion.uploadDatetime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td><c:if test="${sysAppVersion.isEnabled eq '0'}">否</c:if><c:if test="${sysAppVersion.isEnabled eq '1'}">是</c:if></td>
				<shiro:hasPermission name="sysappversion:sysAppVersion:edit"><td>
    				<a href="${ctx}/sysappversion/sysAppVersion/form?id=${sysAppVersion.id}">修改</a>
					<%--<a href="${ctx}/sysappversion/sysAppVersion/delete?id=${sysAppVersion.id}" onclick="return confirmx('确认要删除该手机app版本吗？', this.href)">删除</a>--%>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>