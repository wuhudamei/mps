<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>辅料对应供应商管理</title>
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
		<li><a href="${ctx}/auxiliarymaterials/bizAuxiliaryMaterials/supplierList">辅料管理列表</a></li>
		<li class="active"><a href="${ctx}/auxiliarymaterialssupplier/bizAuxiliaryMaterialsSupplierRel/supplierList?auxiliaryMaterialsId=${bizAuxiliaryMaterialsSupplierRel.auxiliaryMaterialsId}">对应供应商列表</a></li>
	</ul>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>供应商编号</th>
				<th>供应商名称</th>
				<th>材料名称</th>
				<th>规格</th>
				<th>供货周期（天）</th>
				<th>供应商供货价（元）</th>
				<th>版本号</th>
				<th>生效日期</th>
				<th>是否删除</th>
				<th>操作</th>
				<shiro:hasPermission name="auxiliarymaterialssupplier:bizAuxiliaryMaterialsSupplierRel:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="bizAuxiliaryMaterialsSupplierRel">
			<tr>
				<td><a href="${ctx}/auxiliarymaterialssupplier/bizAuxiliaryMaterialsSupplierRel/supplierform?id=${bizAuxiliaryMaterialsSupplierRel.id}">
					${bizAuxiliaryMaterialsSupplierRel.supplierNo}
				</a></td> 
				<td>
					${fns:getSupplierLabel(bizAuxiliaryMaterialsSupplierRel.supplierId, '')}
				</td>
				<td>
					${bizAuxiliaryMaterials.auxiliaryMaterialsName }
				</td>
				<td>
					${bizAuxiliaryMaterials.specifications }
				</td>
				<td>
					${bizAuxiliaryMaterialsSupplierRel.supplierCycle}
				</td>
				<td>
					${bizAuxiliaryMaterialsSupplierRel.supplierPrice}
				</td>
				<td>
					${bizAuxiliaryMaterialsSupplierRel.version}
				</td>
				<td>
					<fmt:formatDate value="${bizAuxiliaryMaterialsSupplierRel.effectiveDate}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					${fns:getDictLabel(bizAuxiliaryMaterialsSupplierRel.delFlag, 'yes_no', '')}
				</td>
				<td>
					<c:if test = "${bizAuxiliaryMaterialsSupplierRel.delFlag == '1'}">
						已删除
					</c:if>
					<c:if test = "${bizAuxiliaryMaterialsSupplierRel.delFlag == '0'}">
						<c:set var="nowDate" value="<%=System.currentTimeMillis()%>"></c:set>
						<c:choose>
							<c:when test="${bizAuxiliaryMaterialsSupplierRel.effectiveDateMsec-nowDate gt 0}">
							<a href="${ctx}/auxiliarymaterialssupplier/bizAuxiliaryMaterialsSupplierRel/delete?id=${bizAuxiliaryMaterialsSupplierRel.id}" onclick="return confirmx('确认要删除该辅料对应供应商吗？', this.href)">删除</a>
						</c:when>
						</c:choose>
					</c:if>
					
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>