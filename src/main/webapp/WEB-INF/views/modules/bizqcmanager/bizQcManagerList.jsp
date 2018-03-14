<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>质检管理人员管理</title>
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
		<li class="active"><a href="${ctx}/bizqcmanager/bizQcManager/list">质检管理人员列表</a></li>
		<shiro:hasPermission name="bizqcmanager:bizQcManager:edit"><li><a href="${ctx}/bizqcmanager/bizQcManager/form">质检管理人员添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="bizQcManager" action="${ctx}/bizqcmanager/bizQcManager/managerList" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>门店：</label>
				<form:select path="storeId" class="input-medium">
					<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>门店</th>
				<th>工程模式</th>
				<th>人员编号</th>
				<th>人员姓名</th>
				<th>手机号</th>
				<shiro:hasPermission name="bizqcmanager:bizQcManager:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="bizQcManager">
			<tr>
				<td>
					${fns:getStoreLabel(bizQcManager.storeId, '')}
				</td>
				<td>
					${fns:getDictLabel(bizQcManager.projectMode, 'project_mode', '')}
				</td>
				<td>
					${bizQcManager.no}
				</td>
				<td>
					${bizQcManager.realName}
				</td>
				<td>
					${bizQcManager.phone}
				</td>
				<shiro:hasPermission name="bizqcmanager:bizQcManager:edit"><td>
					<a href="${ctx}/bizqcmanager/bizQcManager/delete?id=${bizQcManager.id}" onclick="return confirmx('确认要删除该质检管理人员吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>