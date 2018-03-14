<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>数据权限配置列表</title>
	<meta name="decorator" content="default"/>
</head>

<body>
<ul class="ul-form">
	<form:form id="searchForm" modelAttribute="dataAuthority" action="${ctx}/employee/bizEmployee/" method="post" class="breadcrumb form-search">
		<%-- <li><label>业务数据:</label>
				${transaction} --%>
		 <%-- <form:select path="transactionData" class="input-medium" >
						<form:option value="" label=""/>
						<form:options items="${fns:getDictList('transaction_data')}" itemLabel="label" itemValue="value" htmlEscape="false"/> --%>
		<%-- </form:select> --%>
		</li>
		
		<!-- <li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li> -->
		
	</form:form>
</ul>
	<sys:message content="${message}"/>
	<%-- <div style="text-align: center;font-size: 20px;height: 50px">
		 <span style="font-style: normal;font-size:normal">${roleName }</span> 
	</div> --%>
	<h3 style="text-align: center;color: black;">
		${roleName }
	</h3>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<input type="hidden" value = "${roleName }"/>
		<input type="hidden" value = "${roleId }"/>
		
		
		<tr><th>业务数据</th><th>最后修改时间</th><th>操作</th></tr>
		<c:forEach items="${list}" var = "role">
			<tr>
			<td>${role.transactionData }</td>
			<td>
			<fmt:formatDate value="${role.updateDate }" pattern="yyyy-MM-dd HH:mm:ss"/>
			</td>
			<td><a href="${ctx}/dataAuthority/configure/details?roleName=${roleName }&data=${role.transactionData }&ids=${role.id }&roleId=${roleId}">配置</a></td>
		</tr>
		</c:forEach>
		
	
	</table>
	<div class="form-actions">
			<a class="btn" href="${ctx}/sys/role">返 回上一页</a>
		</div>
</body>
</html>