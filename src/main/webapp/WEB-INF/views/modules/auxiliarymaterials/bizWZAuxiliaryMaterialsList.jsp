<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>辅料管理管理</title>
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
		function onclickFrom(){
			$('#myModal').modal('hide');
		}
		function exportExcel(date){
			if(date==''){
				alertx('请选择门店！')
			}else{
				$("#searchForm").attr("action", "${ctx}/auxiliarymaterials/bizAuxiliaryMaterials/exportExcel");
				$("#searchForm").submit();
	            $("#searchForm").attr("action", "${ctx}/auxiliarymaterials/bizAuxiliaryMaterials/WZlist");
			}
			
		}
		
		function searchButton(){
			$("#searchForm").attr("action", "${ctx}/auxiliarymaterials/bizAuxiliaryMaterials/WZlist");
			$("#searchForm").submit();
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/auxiliarymaterials/bizAuxiliaryMaterials/WZlist">辅料管理列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="bizAuxiliaryMaterials" action="${ctx}/auxiliarymaterials/bizAuxiliaryMaterials/WZlist" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>门店：</label>
				<c:if test="${empty storeDropEnable}">
					<form:select id = "storeId" path="storeId" class="input-medium" disabled="true">
						<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
				<c:if test="${!empty storeDropEnable}">
					<form:select id = "storeId" path="storeId" class="input-medium needClear">
						<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
					
			</li>
			<li><label>材料编号：</label>
				<form:input path="auxiliaryMaterialsNo" htmlEscape="false" maxlength="100" class="input-medium needClear"/>
			</li>
			<li><label>材料名称：</label>
				<form:input path="auxiliaryMaterialsName" htmlEscape="false" maxlength="100" class="input-medium needClear"/>
			</li>
			<li><label>材料类别：</label>
				<form:select path="categoryId" class="input-medium needClear">
					<form:option value="" label=""/>
					<form:options items="${fns:getMaterialCategoryList()}" itemLabel="categoryName" itemValue="id" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>状态：</label>
				<form:select path="status" class="input-medium needClear">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('biz_enable_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>是否沙子水泥：</label>
				<form:select path="isSandCement" class="input-medium needClear">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="button" value="查询"  onclick="searchButton()"/></li>
			<li class="btns"><input class="btn btn-primary clearBtn" type="button" value="清空"/></li>
			<li class="btns"><input class="btn btn-primary" type="button" value="导出" onclick="exportExcel($('#storeId').val())"/></li>
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
				<th>沙子水泥</th>
				<th>状态</th>
				<th>品牌</th>
				<shiro:hasPermission name="auxiliarymaterials:bizWZAuxiliaryMaterials:edit">
				<th>操作</th>
				
				</shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="bizAuxiliaryMaterials">
			<tr>
				<td><a href="${ctx}/auxiliarymaterials/bizAuxiliaryMaterials/form?id=${bizAuxiliaryMaterials.id}">
					${fns:getStoreLabel(bizAuxiliaryMaterials.storeId, '')}
				</a></td>
				<td>
					${bizAuxiliaryMaterials.auxiliaryMaterialsNo}
				</td>
				<td>
					${bizAuxiliaryMaterials.auxiliaryMaterialsName}
				</td>
				<td>
					${fns:getMaterialCategoryLabel(bizAuxiliaryMaterials.categoryId, '')}
				</td>
				<td>
					${bizAuxiliaryMaterials.specifications}
				</td>
				<td>
					${fns:getDictLabel(bizAuxiliaryMaterials.measurementUnit, 'biz_material_unit', '')}
				</td>
				<td style="text-align: center;">
					${fns:getDictLabel(bizAuxiliaryMaterials.isSandCement, 'yes_no', '')}
				</td>
				<td>
					${fns:getDictLabel(bizAuxiliaryMaterials.status, 'biz_enable_status', '')}
				</td>
				<td>
					${bizAuxiliaryMaterials.brands}
				</td>

				<shiro:hasPermission name="auxiliarymaterials:bizWZAuxiliaryMaterials:edit">
				<td> 
    				<a href="${ctx}/auxiliarymaterials/bizAuxiliaryMaterials/wZform?id=${bizAuxiliaryMaterials.id}">修改基本信息</a>
    				<a href="${ctx}/auxiliarymaterialssupplier/bizAuxiliaryMaterialsSupplierRel/WZlist?auxiliaryMaterialsId=${bizAuxiliaryMaterials.id}">修改门店价</a>
    			</td>
    			
				</shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
				
</body>
</html>
