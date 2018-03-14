<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>标化辅材领取详情管理</title>
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
		<li class="active"><a href="${ctx}/standradmaterialsdetail/bizMaterialsStandardReceiveDetail/">标化辅材领取详情列表</a></li>
		<shiro:hasPermission name="standradmaterialsdetail:bizMaterialsStandardReceiveDetail:edit"><li><a href="${ctx}/standradmaterialsdetail/bizMaterialsStandardReceiveDetail/form">标化辅材领取详情添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="bizMaterialsStandardReceiveDetail" action="${ctx}/standradmaterialsdetail/bizMaterialsStandardReceiveDetail/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<shiro:hasPermission name="standradmaterialsdetail:bizMaterialsStandardReceiveDetail:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="bizMaterialsStandardReceiveDetail">
			<tr>
				<shiro:hasPermission name="standradmaterialsdetail:bizMaterialsStandardReceiveDetail:edit"><td>
    				<a href="${ctx}/standradmaterialsdetail/bizMaterialsStandardReceiveDetail/form?id=${bizMaterialsStandardReceiveDetail.id}">修改</a>
					<a href="${ctx}/standradmaterialsdetail/bizMaterialsStandardReceiveDetail/delete?id=${bizMaterialsStandardReceiveDetail.id}" onclick="return confirmx('确认要删除该标化辅材领取详情吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>