<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>节点管理</title>
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
		<li class="active"><a href="${ctx}/bizmaterialsstandardchecknode/bizMaterialsStandardQcCheckNode/list">节点列表</a></li>
		<shiro:hasPermission name="bizmaterialsstandardchecknode:bizMaterialsStandardQcCheckNode:edit"><li><a href="${ctx}/bizmaterialsstandardchecknode/bizMaterialsStandardQcCheckNode/form">节点添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="bizMaterialsStandardQcCheckNode" action="${ctx}/bizmaterialsstandardchecknode/bizMaterialsStandardQcCheckNode/findList" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>门店：</label>
				<form:select path="storeId" class="input-medium" >
						<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
			</li>
			<li><label>工程模式：</label>
					<form:select path="projectMode" class="input-medium" >
						<form:option value="" label=""/>
						<form:options items="${fns:getDictList('project_mode')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
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
				<th>材料类型</th>
				<th>约检节点</th>
				<th>备注</th>
				<shiro:hasPermission name="bizmaterialsstandardchecknode:bizMaterialsStandardQcCheckNode:edit"><th>操作</th></shiro:hasPermission> 
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="bizMaterialsStandardQcCheckNode">
			<tr>
				<td>
					${bizMaterialsStandardQcCheckNode.storeName}
				</td>
				<td>
					${bizMaterialsStandardQcCheckNode.projectModeName}
				</td>
				<td>
					${bizMaterialsStandardQcCheckNode.materialName}
				</td>
				<td>
					${bizMaterialsStandardQcCheckNode.qcCheckNodeIdName}
				</td>
				<td>
					${bizMaterialsStandardQcCheckNode.remarks}
				</td>
				<shiro:hasPermission name="bizmaterialsstandardchecknode:bizMaterialsStandardQcCheckNode:edit"><td>
    				<%--修改 备用 涉及历史节点问题 <a href="${ctx}/bizmaterialsstandardchecknode/bizMaterialsStandardQcCheckNode/form?id=${bizMaterialsStandardQcCheckNode.id}">修改</a> --%>
					<a href="${ctx}/bizmaterialsstandardchecknode/bizMaterialsStandardQcCheckNode/delete?id=${bizMaterialsStandardQcCheckNode.id}" onclick="return confirmx('确认要删除该节点吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>