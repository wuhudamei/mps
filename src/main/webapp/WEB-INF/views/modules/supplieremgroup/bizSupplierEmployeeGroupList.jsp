<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>主材安装供应商和工人组管理</title>
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
        function clearCondition(){
			 document.getElementById("searchForm").reset();
			
			 var inputObjs=jQuery("#searchForm input[type='text']"); 
			 for(var i=0;i<inputObjs.length;i++){ 
			 var inputObj = inputObjs[i]; 
			 inputObj.value=""; 
			 } 
			
			 var selectObjs = jQuery("#searchForm input[class='input-medium']"); 
			 for(var i=0;i<selectObjs.length;i++){ 
			 var selectObj = selectObjs[i]; 
			 selectObj.value=""; 
			 } 
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/supplieremgroup/bizSupplierEmployeeGroup/">主材安装供应商和工人组列表</a></li>
		<shiro:hasPermission name="supplieremgroup:bizSupplierEmployeeGroup:edit"><li><a href="${ctx}/supplieremgroup/bizSupplierEmployeeGroup/form">主材安装供应商和工人组添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="bizSupplierEmployeeGroup" action="${ctx}/supplieremgroup/bizSupplierEmployeeGroup/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>供应商名称：</label>
				<form:input path="supplierName" htmlEscape="false" maxlength="11" class="input-medium needClear"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="btns"><input class="btn btn-primary clearBtn" type="button" value="清空" onclick="clearCondition()"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>序号</th>
				<th>供应商名称</th>
				<th>工人组</th>
				<shiro:hasPermission name="supplieremgroup:bizSupplierEmployeeGroup:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="bizSupplierEmployeeGroup">
			<tr>
				<td>
					${bizSupplierEmployeeGroup.index}
				</td>
				<td>
					${bizSupplierEmployeeGroup.supplierName}
				</td>
				<td>
				<a href="${ctx}/supplieremgroup/bizSupplierEmployeeGroup/queryEmployeeGroupList?supplierId=${bizSupplierEmployeeGroup.id}">
					查看
				</a>
				</td>
				<shiro:hasPermission name="supplieremgroup:bizSupplierEmployeeGroup:edit"><td>
    				<a href="${ctx}/supplieremgroup/bizSupplierEmployeeGroup/formUpdate?id=${bizSupplierEmployeeGroup.id}">修改</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>