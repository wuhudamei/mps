<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>违规形式管理</title>
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
		<li class="active"><a href="${ctx}/illegalmodality/illegalModality/list?checkItemId=${checkItemId}&projectMode=${projectMode}">违规形式列表</a></li>
		<shiro:hasPermission name="illegalmodality:illegalModality:edit"><li><a href="${ctx}/illegalmodality/illegalModality/form/?checkItemId=${checkItemId}&projectMode=${projectMode}">违规形式添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="illegalModality" action="${ctx}/illegalmodality/illegalModality/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li class="clearfix"><a href="${ctx}//bizqccheckitem/bizQcCheckItem/itemList">返回检查项列表</a>
			</li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>门店</th>
				<th>工程模式</th>
				<th>检查分类</th>
				<th>检查项</th>
				<th>违规形式描述</th>
				<th>是否填写备注</th>
				<th>状态</th>
				<shiro:hasPermission name="illegalmodality:illegalModality:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="illegalModality">
			<tr>
				<td>
					${fns:getStoreLabel(illegalModality.storeId, '')}
					
				</td>
				<td>
					${fns:getDictLabel(illegalModality.projectMode, 'project_mode','')}
					
				</td>
				<td>
					${illegalModality.checkKindName}
				</td>
				<td>
					${illegalModality.checkItemName}
				</td>
				<td>
					${illegalModality.breakDescribe}
				</td>
				<td>
				<c:if test="${illegalModality.isRequiredRemarks=='1' }">是</c:if>
					<c:if test="${illegalModality.isRequiredRemarks=='0' }">否</c:if>
					
				</td>
				<td>
				<c:if test="${illegalModality.status=='1' }"><span style="color:#00EC00;">启用</span></c:if>
					<c:if test="${illegalModality.status=='0' }"><span style="color:red;">停用</span></c:if>
					
				</td>
				<shiro:hasPermission name="illegalmodality:illegalModality:edit"><td>
    				<a href="${ctx}/illegalmodality/illegalModality/form1?id=${illegalModality.id}">修改</a>
					
					<a href="${ctx}/illegalmodality/illegalModality/delete?id=${illegalModality.id}&status=1" onclick="return confirmx('确认要启用该违规形式吗？', this.href)">
					<c:if test="${illegalModality.status=='0' }"><span style="color:#00EC00;">启用</span></a>
					</c:if>
					
					
					
					<c:if test="${illegalModality.status=='1' }">
					<a href="${ctx}/illegalmodality/illegalModality/delete?id=${illegalModality.id}&status=0" onclick="return confirmx('确认要停用该违规形式吗？', this.href)">
					<span style="color:red;">停用</span>
					
					</a>
					</c:if>
					
					
					
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>