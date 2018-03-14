<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>主材管理</title>
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
		<li class="active"><a href="${ctx}/mainmaterials/bizMainMaterials/mainMaterialsList">主材管理列表</a></li>
		<shiro:hasPermission name="mainmaterials:bizMainMaterials:edit"><li><a href="${ctx}/mainmaterials/bizMainMaterials/form">主材管理添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="bizMainMaterials" action="${ctx}/mainmaterials/bizMainMaterials/mainMaterialsList" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>门店：</label>
				<c:if test="${empty storeDropEnable}">
					<form:select path="storeId" class="input-medium" disabled="true">
						<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
				<c:if test="${!empty storeDropEnable}">
					<form:select path="storeId" class="input-medium needClear">
						<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
					
			</li>
			<li><label>材料编号：</label>
				<form:input path="mainMaterialsNo" htmlEscape="false" maxlength="100" class="input-medium needClear"/>
			</li>
			<li><label>材料名称：</label>
				<form:input path="mainMaterialsName" htmlEscape="false" maxlength="100" class="input-medium needClear"/>
			</li>
			<li><label>材料类别：</label>
				<form:select path="categoryId" class="input-medium needClear">
					<form:option value="" label=""/>
					<form:options items="${fns:getMainMaterialCategoryList()}" itemLabel="categoryName" itemValue="id" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>状态：</label>
				<form:select path="status" class="input-medium needClear">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('biz_enable_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
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
				<th>门店</th>
				<th>材料编号</th>
				<th>材料名称</th>
				<th>材料类别</th>
				<th>规格</th>
				<th>单位</th>
				<th>状态</th>
				<th>是否计数</th>
				<th>排序</th>
				<th>备注</th>
				<shiro:hasPermission name="mainmaterials:bizMainMaterials:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="bizMainMaterials">
			<tr>
				<td><a href="${ctx}/mainmaterials/bizMainMaterials/form?id=${bizMainMaterials.id}">
					${fns:getStoreLabel(bizMainMaterials.storeId, '')}
				</a></td>
				<td>
					${bizMainMaterials.mainMaterialsNo}
				</td>
				<td>
					${bizMainMaterials.mainMaterialsName}
				</td>
				<td>
					${fns:getMainMaterialCategoryLabel(bizMainMaterials.categoryId, '')}
				</td>
				<td>
					${bizMainMaterials.specifications}
				</td>
				<td>
					${fns:getDictLabel(bizMainMaterials.measurementUnit, 'biz_material_unit', '')}
				</td>
				<td>
					${fns:getDictLabel(bizMainMaterials.status, 'biz_enable_status', '')}
				</td>
				<td>
					${fns:getDictLabel(bizMainMaterials.isCounted, 'yes_no', '')}
					
				</td>
				<td>
					${bizMainMaterials.sortIndex}
				</td>
				<td>
					${bizMainMaterials.remarks}
				</td>
<%-- 				<shiro:hasPermission name="mainmaterials:bizMainMaterials:edit"><td>
    				<a href="${ctx}/mainmaterials/bizMainMaterials/form?id=${bizAuxiliaryMaterials.id}">修改</a>
					<a href="${ctx}/mainmaterials/bizMainMaterials/delete?id=${bizAuxiliaryMaterials.id}" onclick="return confirmx('确认要删除该辅料管理吗？', this.href)">删除</a>
				</td></shiro:hasPermission> --%>
				<shiro:hasPermission name="mainmaterials:bizMainMaterials:edit"><td>
    				<a href="${ctx}/mainmaterials/bizMainMaterials/form?id=${bizMainMaterials.id}">修改</a>
    				<a href="${ctx}/mainmaterialssupplierrel/bizMainMaterialsSupplierRel/list?mainMaterialsId=${bizMainMaterials.id}">供应商</a>
					<a href="${ctx}/mainmaterials/bizMainMaterials/enable?id=${bizMainMaterials.id}" onclick="return confirmx('确认要修改状态吗？', this.href)">
                        <c:if test="${bizMainMaterials.status==1}">
							停用
						</c:if>
						<c:if test="${bizMainMaterials.status==0}">
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