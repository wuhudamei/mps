<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>选材变更单材料表管理</title>
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
		<li class="active"><a href="${ctx}/bizmaterialschoicechange/bizMaterialsChoiceChangeBillItem/">选材变更单材料表列表</a></li>
		<shiro:hasPermission name="bizmaterialschoicechange:bizMaterialsChoiceChangeBillItem:edit"><li><a href="${ctx}/bizmaterialschoicechange/bizMaterialsChoiceChangeBillItem/form">选材变更单材料表添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="bizMaterialsChoiceChangeBillItem" action="${ctx}/bizmaterialschoicechange/bizMaterialsChoiceChangeBillItem/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>remarks</th>
				<th>update_date</th>
				<shiro:hasPermission name="bizmaterialschoicechange:bizMaterialsChoiceChangeBillItem:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="bizMaterialsChoiceChangeBillItem">
			<tr>
				<td><a href="${ctx}/bizmaterialschoicechange/bizMaterialsChoiceChangeBillItem/form?id=${bizMaterialsChoiceChangeBillItem.id}">
					${bizMaterialsChoiceChangeBillItem.remarks}
				</a></td>
				<td>
					<fmt:formatDate value="${bizMaterialsChoiceChangeBillItem.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="bizmaterialschoicechange:bizMaterialsChoiceChangeBillItem:edit"><td>
    				<a href="${ctx}/bizmaterialschoicechange/bizMaterialsChoiceChangeBillItem/form?id=${bizMaterialsChoiceChangeBillItem.id}">修改</a>
					<a href="${ctx}/bizmaterialschoicechange/bizMaterialsChoiceChangeBillItem/delete?id=${bizMaterialsChoiceChangeBillItem.id}" onclick="return confirmx('确认要删除该选材变更单材料表吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>