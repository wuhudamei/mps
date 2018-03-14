<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>供应商安装施工单表管理</title>
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
		<li class="active"><a href="${ctx}/bizsupplierinstallcontructbill/bizSupplierInstallConstructBill/">供应商安装施工单表列表</a></li>
		<shiro:hasPermission name="bizsupplierinstallcontructbill:bizSupplierInstallConstructBill:edit"><li><a href="${ctx}/bizsupplierinstallcontructbill/bizSupplierInstallConstructBill/form">供应商安装施工单表添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="bizSupplierInstallConstructBill" action="${ctx}/bizsupplierinstallcontructbill/bizSupplierInstallConstructBill/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>供应商安装单id：</label>
				<form:input path="supplierInstallBillId" htmlEscape="false" maxlength="11" class="input-medium"/>
			</li>
			<li><label>工人组id：</label>
				<form:input path="employeeGroupId" htmlEscape="false" maxlength="11" class="input-medium"/>
			</li>
			<li><label>状态：</label>
				<form:input path="status" htmlEscape="false" maxlength="10" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>施工单号</th>
				<th>供应商安装单id</th>
				<th>工人组id</th>
				<th>状态</th>
				<th>remarks</th>
				<th>update_date</th>
				<shiro:hasPermission name="bizsupplierinstallcontructbill:bizSupplierInstallConstructBill:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="bizSupplierInstallConstructBill">
			<tr>
				<td><a href="${ctx}/bizsupplierinstallcontructbill/bizSupplierInstallConstructBill/form?id=${bizSupplierInstallConstructBill.id}">
					${bizSupplierInstallConstructBill.constructBillCode}
				</a></td>
				<td>
					${bizSupplierInstallConstructBill.supplierInstallBillId}
				</td>
				<td>
					${bizSupplierInstallConstructBill.employeeGroupId}
				</td>
				<td>
					${bizSupplierInstallConstructBill.status}
				</td>
				<td>
					${bizSupplierInstallConstructBill.remarks}
				</td>
				<td>
					<fmt:formatDate value="${bizSupplierInstallConstructBill.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="bizsupplierinstallcontructbill:bizSupplierInstallConstructBill:edit"><td>
    				<a href="${ctx}/bizsupplierinstallcontructbill/bizSupplierInstallConstructBill/form?id=${bizSupplierInstallConstructBill.id}">修改</a>
					<a href="${ctx}/bizsupplierinstallcontructbill/bizSupplierInstallConstructBill/delete?id=${bizSupplierInstallConstructBill.id}" onclick="return confirmx('确认要删除该供应商安装施工单表吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>