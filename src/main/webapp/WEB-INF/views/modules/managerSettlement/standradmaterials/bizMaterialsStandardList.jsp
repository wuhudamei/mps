<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>标化辅材管理</title>
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
		<li class="active"><a href="${ctx}/standradmaterials/bizMaterialsStandard/listPage">标化辅材列表</a></li>
		<shiro:hasPermission name="standradmaterials:bizMaterialsStandard:edit"><li><a href="${ctx}/standradmaterials/bizMaterialsStandard/form">标化辅材添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="bizMaterialsStandard" action="${ctx}/standradmaterials/bizMaterialsStandard/" method="post" class="breadcrumb form-search">
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
				<th>物料类别 </th>
				<th>物料名称</th>
				<th>物料单位</th>
				<th>物料单价 </th>
				<th>申请上限</th>
				<th>是否启用</th>
				<th>备注</th>
				<shiro:hasPermission name="standradmaterials:bizMaterialsStandard:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="bizMaterialsStandard">
			<tr>
				<td>
					${fns:getStoreLabel(bizMaterialsStandard.storeId, '')}
				</td>
				<td>
					${bizMaterialsStandard.materialsType}
				</td>
				<td>
					${bizMaterialsStandard.materialsName}
				</td>
				<td>
					${bizMaterialsStandard.materialsUnit}
				</td>
				<td>
					${bizMaterialsStandard.materialsPrice}
				</td>
				<td>
					${bizMaterialsStandard.maxReceiveNumber}
				</td>
				<td>
					<%-- ${bizMaterialsStandard.isEnabled} --%>${fns:getDictLabel(bizMaterialsStandard.isEnabled, 'biz_enable_status', '')}
				</td>
				<td>
					${bizMaterialsStandard.remarks}
				</td>
				<shiro:hasPermission name="standradmaterials:bizMaterialsStandard:edit"><td>
    				<a href="${ctx}/standradmaterials/bizMaterialsStandard/form?id=${bizMaterialsStandard.id}">修改</a>
					<a href="${ctx}/standradmaterials/bizMaterialsStandard/enable?id=${bizMaterialsStandard.id}">
						<c:if test="${bizMaterialsStandard.isEnabled=='1'}">
							停用
						</c:if>
						<c:if test="${bizMaterialsStandard.isEnabled=='0'}">
							启用
						</c:if>
					</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>