<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>材料类别管理</title>
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
		<li class="active"><a href="${ctx}/materialcategory/bizMaterialCategory/">材料类别列表</a></li>
		<shiro:hasPermission name="materialcategory:bizMaterialCategory:edit"><li><a href="${ctx}/materialcategory/bizMaterialCategory/form">材料类别添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="bizMaterialCategory" action="${ctx}/materialcategory/bizMaterialCategory/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>材料类别名称：</label>
				<form:input path="categoryName" htmlEscape="false" maxlength="255" class="input-medium needClear"/>
			</li>
			<li><label>主材/辅材：</label>
				<form:select path="materialTypeId" class="input-medium needClear">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('biz_material_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
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
				<th>材料类别编号</th>
				<th>材料类别名称</th>
				<th>主材/辅材</th>
				<th>状态</th>
				<shiro:hasPermission name="materialcategory:bizMaterialCategory:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="bizMaterialCategory">
			<tr>
				<td><a href="${ctx}/materialcategory/bizMaterialCategory/form?id=${bizMaterialCategory.id}">
					${bizMaterialCategory.categoryNo}
				</a></td>
				<td>
					${bizMaterialCategory.categoryName}
				</td>
				<td>
					${fns:getDictLabel(bizMaterialCategory.materialTypeId, 'biz_material_type', '未知')}
				</td>
				<td>
					${fns:getDictLabel(bizMaterialCategory.status, 'biz_enable_status', '未知')}
				</td>
				<shiro:hasPermission name="materialcategory:bizMaterialCategory:edit"><td>
    				<a href="${ctx}/materialcategory/bizMaterialCategory/form?id=${bizMaterialCategory.id}">修改</a>
    				<c:if test="${bizMaterialCategory.status==1}">
							<a href="${ctx}/materialcategory/bizMaterialCategory/enable?id=${bizMaterialCategory.id}" onclick="return confirmx('确认要停用吗？', this.href)"><span style="color:red;">停用</span></a>
						</c:if>
						<c:if test="${bizMaterialCategory.status==0}">
							<a href="${ctx}/materialcategory/bizMaterialCategory/enable?id=${bizMaterialCategory.id}" onclick="return confirmx('确认要启用吗？', this.href)"><span style="color:#00EC00;">启用</span></a>
						</c:if>
					<!--  <a href="${ctx}/materialcategory/bizMaterialCategory/delete?id=${bizMaterialCategory.id}" onclick="return confirmx('确认要删除该材料类别吗？', this.href)">删除</a>-->
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>