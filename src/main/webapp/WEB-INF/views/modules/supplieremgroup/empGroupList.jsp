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
		<li class="active"><a href="${ctx}/supplieremgroup/bizSupplierEmployeeGroup/">主材安装供应商和工人组列表</a></li>
		<shiro:hasPermission name="installandsupplier:projectInstallAndSupplier:edit"><li><a href="${ctx}/installandsupplier/projectInstallAndSupplier/form">主材安装供应商和工人组添加</a></li></shiro:hasPermission>
	</ul>

	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>星级</th>
				<th>组长姓名</th>
				<th>手机号</th>
				<th>是否停单</th>
				<th>是否删除</th>
			</tr>
		</thead>
		<tbody>
					<c:forEach items="${bizEmgrouprelationList}" var="bizEmgrouprelationList">
				<tr>
				<td>
					${bizEmgrouprelationList.star}
				</td>
				<td>
					${bizEmgrouprelationList.leaderRealName}
				</td>
				<td>
					${bizEmgrouprelationList.leaderPhone}
				</td>
				<td>
			<c:if test="${bizEmgrouprelationList.orderstop==0}"><span style="color:#00EC00;">否</span></c:if> 
					<c:if test="${bizEmgrouprelationList.orderstop==1}"><span style="color:red">是</span></c:if> </td>
				</td>
				<td>
					<c:if test="${bizEmgrouprelationList.state==0}"><span style="color:#00EC00;">否</span></c:if> 
					<c:if test="${bizEmgrouprelationList.state==1}"><span style="color:red">是</span></c:if> 
		
				</td>

			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
	
	

</body>
</html>