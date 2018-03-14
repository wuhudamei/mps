<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>主材对应供应商管理</title>
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
		<li><a href="${ctx}/mainmaterials/bizMainMaterials/mainMaterialsList">主材管理列表</a></li>
		<li class="active"><a href="${ctx}/mainmaterialssupplierrel/bizMainMaterialsSupplierRel/list?mainMaterialsId=${bizMainMaterialsSupplierRel.mainMaterialsId}">对应供应商列表</a></li>
		<li><a href="${ctx}/mainmaterialssupplierrel/bizMainMaterialsSupplierRel/form?mainMaterialsId=${bizMainMaterialsSupplierRel.mainMaterialsId}">对应供应商添加</a></li>
		<shiro:hasPermission name="mainmaterialssupplierrel:bizMainMaterialsSupplierRel:edit"><li><a href="${ctx}/mainmaterialssupplierrel/bizMainMaterialsSupplierRel/form">主材对应供应商添加</a></li></shiro:hasPermission>
	
		<%-- <li class="active"><a href="${ctx}/mainmaterialssupplier/bizMainMaterialsSupplierRel/">辅料对应供应商列表</a></li>
		<shiro:hasPermission name="mainmaterialssupplier:bizMainMaterialsSupplierRel:edit"><li><a href="${ctx}/mainmaterialssupplier/bizMainMaterialsSupplierRel/form">辅料对应供应商添加</a></li></shiro:hasPermission> --%>
	</ul>
<%-- 	<form:form id="searchForm" modelAttribute="bizMainMaterialsSupplierRel" action="${ctx}/mainmaterialssupplier/bizMainMaterialsSupplierRel/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form> --%>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>供应商编号</th>
				<th>供应商名称</th>
				<th>供货周期（天）</th>
				<th>供应商供货价（元）</th>
				<th>工人结算价（元）</th>
				<th>版本号</th>
				<th>生效日期</th>
				<shiro:hasPermission name="mainmaterialssupplierrel:bizMainMaterialsSupplierRel:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="bizMainMaterialsSupplierRel">
			<tr>
				<td>
					${bizMainMaterialsSupplierRel.supplierNo}
				</td>
				<td>
					${fns:getSupplierLabel(bizMainMaterialsSupplierRel.supplierId, '')}
				</td>
				<td>
					${bizMainMaterialsSupplierRel.supplierCycle}
				</td>
				<td>
					${bizMainMaterialsSupplierRel.supplierPrice}
				</td>
				<td>
					${bizMainMaterialsSupplierRel.laborPrice}
				</td>
				<td>
					${bizMainMaterialsSupplierRel.version}
				</td>
				<td>
					<fmt:formatDate value="${bizMainMaterialsSupplierRel.effectiveDate}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
				<a href="${ctx}/mainmaterialssupplierrel/bizMainMaterialsSupplierRel/delete?id=${bizMainMaterialsSupplierRel.id}" onclick="return confirmx('确认要删除该主材对应供应商吗？', this.href)">删除</a>
				</td>
				<%-- <shiro:hasPermission name="mainmaterialssupplier:bizMainMaterialsSupplierRel:edit"><td>
    				<a href="${ctx}/mainmaterialssupplier/bizMainMaterialsSupplierRel/form?id=${bizMainMaterialsSupplierRel.id}">修改</a>
					<a href="${ctx}/mainmaterialssupplier/bizMainMaterialsSupplierRel/delete?id=${bizMainMaterialsSupplierRel.id}" onclick="return confirmx('确认要删除该辅料对应供应商吗？', this.href)">删除</a>
				</td></shiro:hasPermission> --%>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>