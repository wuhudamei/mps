<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>业务催促表管理</title>
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
		<li class="active"><a href="${ctx}/bizbusinessurge/bizBusinessUrge/">业务催促表列表</a></li>
		<shiro:hasPermission name="bizbusinessurge:bizBusinessUrge:edit"><li><a href="${ctx}/bizbusinessurge/bizBusinessUrge/form">业务催促表添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="bizBusinessUrge" action="${ctx}/bizbusinessurge/bizBusinessUrge/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>操作人员工id：</label>
				<form:input path="operatorEmployeeId" htmlEscape="false" maxlength="11" class="input-medium"/>
			</li>
			<li><label>操作日期时间：</label>
				<input name="operateDatetime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${bizBusinessUrge.operateDatetime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>备注</th>
				<th>更新时间</th>
				<shiro:hasPermission name="bizbusinessurge:bizBusinessUrge:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="bizBusinessUrge">
			<tr>
				<td><a href="${ctx}/bizbusinessurge/bizBusinessUrge/form?id=${bizBusinessUrge.id}">
					${bizBusinessUrge.remarks}
				</a></td>
				<td>
					<fmt:formatDate value="${bizBusinessUrge.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="bizbusinessurge:bizBusinessUrge:edit"><td>
    				<a href="${ctx}/bizbusinessurge/bizBusinessUrge/form?id=${bizBusinessUrge.id}">修改</a>
					<a href="${ctx}/bizbusinessurge/bizBusinessUrge/delete?id=${bizBusinessUrge.id}" onclick="return confirmx('确认要删除该业务催促表吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>