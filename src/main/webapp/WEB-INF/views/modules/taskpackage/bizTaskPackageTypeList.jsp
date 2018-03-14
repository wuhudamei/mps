<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>任务包类型管理</title>
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
		<li class="active"><a href="${ctx}/taskpackage/bizTaskPackageType/">任务包类型列表</a></li>
		<shiro:hasPermission name="taskpackage:bizTaskPackageType:edit"><li><a href="${ctx}/taskpackage/bizTaskPackageType/form">任务包类型添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="bizTaskPackageType" action="${ctx}/taskpackage/bizTaskPackageType/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="64" class="input-medium needClear"/>
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
				<th>任务包类型名称</th>
				<th>最后一次更新操作人</th>
				<th>更新时间</th>
				<th>状态</th>
				<shiro:hasPermission name="taskpackage:bizTaskPackageType:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="bizTaskPackageType">
			<tr>
				<td><a href="${ctx}/taskpackage/bizTaskPackageType/form?id=${bizTaskPackageType.id}">
					${bizTaskPackageType.name}
				</a></td>
				<td>
					${bizTaskPackageType.updateBy.loginName}
				</td>
				<td>
					<fmt:formatDate value="${bizTaskPackageType.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${fns:getDictLabel(bizTaskPackageType.status, 'biz_enable_status', '未知')}
				</td>
				<shiro:hasPermission name="taskpackage:bizTaskPackageType:edit"><td>
					<a href="${ctx}/taskpackage/bizTaskPackageType/enable?id=${bizTaskPackageType.id}">
						<c:if test="${bizTaskPackageType.status==1}">
							停用
						</c:if>
						<c:if test="${bizTaskPackageType.status==0}">
							启用
						</c:if>
					</a>
    				<a href="${ctx}/taskpackage/bizTaskPackageType/form?id=${bizTaskPackageType.id}">修改</a>
<%-- 					<a href="${ctx}/taskpackage/bizTaskPackageType/delete?id=${bizTaskPackageType.id}" onclick="return confirmx('确认要删除该任务包类型吗？', this.href)">删除</a> --%>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>