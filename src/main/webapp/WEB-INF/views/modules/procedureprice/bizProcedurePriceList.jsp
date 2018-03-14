<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>工序价格管理</title>
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
	    <li><a href="${ctx}/procedure/bizProcedure/">工序列表</a></li>
		<li class="active"><a href="${ctx}/procedureprice/bizProcedurePrice/list?procedureNo=${bizProcedurePrice.procedureNo}">工序价格列表</a></li>
		<!--li><a href="${ctx}/procedureprice/bizProcedurePrice/form?procedureNo=${bizProcedurePrice.procedureNo}">工序价格添加</a></li-->
		<shiro:hasPermission name="procedureprice:bizProcedurePrice:edit"><li><a href="${ctx}/procedureprice/bizProcedurePrice/form?procedureNo=${bizProcedurePrice.procedureNo}">工序价格添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="bizProcedurePrice" action="${ctx}/procedureprice/bizProcedurePrice/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<!--ul class="ul-form">
			<li><label>门店：</label>
				<sys:treeselect id="storeId" name="storeId" value="${bizProcedurePrice.storeId}" labelName="" labelValue="${bizProcedurePrice.storeId}"
					title="部门" url="/sys/office/treeData?type=2" cssClass="input-small" allowClear="true" notAllowSelectParent="true"/>
			</li>
			<li><label>门店：</label>
				<form:select path="storeId" class="input-medium">
					<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>工序编号：</label>
				<form:input path="procedureNo" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul-->
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>门店</th>
				<th>工程模式</th>
				<th>人工结算价</th>
				<th>辅料结算价</th>
				<th>综合价</th>
				<th>当前版本号</th>
				<th>生效日期</th>
				<th>操作</th>
				<shiro:hasPermission name="procedureprice:bizProcedurePrice:edit"></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="bizProcedurePrice">
			<tr>
				<td><%-- <a href="${ctx}/procedureprice/bizProcedurePrice/form?id=${bizProcedurePrice.id}">
					  ${fns:getStoreLabel(bizProcedurePrice.storeId, '')}
				</a> --%>
				${fns:getStoreLabel(bizProcedurePrice.storeId, '')}
				</td>
				<td>${fns:getDictLabel(bizProcedurePrice.projectMode, 'package_project_mode', '')}</td>
				<td>
					${bizProcedurePrice.laborPrice}
				</td>
				<td>
					${bizProcedurePrice.accessoriesPrice}
				</td>
				<td>
					${bizProcedurePrice.synthesizePrice}
				</td>
				<td>
					${bizProcedurePrice.version}
				</td>
				<td>
					<fmt:formatDate value="${bizProcedurePrice.effectiveDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
    				
					<a href="${ctx}/procedureprice/bizProcedurePrice/delete?id=${bizProcedurePrice.id}" onclick="return confirmx('确认要删除该工序价格吗？', this.href)">删除</a>
				</td><shiro:hasPermission name="procedureprice:bizProcedurePrice:edit"></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>