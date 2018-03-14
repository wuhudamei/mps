<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>主材安装供应商设置管理</title>
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
		<li class="active"><a href="${ctx}/installandsupplier/projectInstallAndSupplier/">主材安装供应商设置列表</a></li>
		<shiro:hasPermission name="installandsupplier:projectInstallAndSupplier:edit"><li><a href="${ctx}/installandsupplier/projectInstallAndSupplier/form">主材安装供应商设置添加</a></li></shiro:hasPermission>
	</ul>

	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>供应商编号</th>
				<th>供应商名称</th>
				<th>联系人</th>
				<th>手机号</th>
			</tr>
		</thead>
		<tbody>
					<c:forEach items="${bizSupplierList}" var="bizSupplierList">
				<tr>
				<td>
					${bizSupplierList.supplierNo}
				</td>
				<td>
					${bizSupplierList.supplierName}
				</td>
				<td>
					${bizSupplierList.contacts}
				</td>
				<td>
					${bizSupplierList.contactsPhone}
		
				</td>

			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
	
	

</body>
</html>