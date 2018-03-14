<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>消息公告查看日志管理</title>
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
		<li class="active"><a href="${ctx}/noticeviewlog/bizNoticeViewLog/">消息公告查看日志列表</a></li>
		<shiro:hasPermission name="noticeviewlog:bizNoticeViewLog:edit"><li><a href="${ctx}/noticeviewlog/bizNoticeViewLog/form">消息公告查看日志添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="bizNoticeViewLog" action="${ctx}/noticeviewlog/bizNoticeViewLog/" method="post" class="breadcrumb form-search">
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
				<th>&aring;&curren;&Dagger;&aelig;&sup3;&uml; -- '</th>
				<th>&aelig;&rsaquo;&acute;&aelig;&ndash;&deg;&aelig;&mdash;&yen;&aelig;&oelig;&Yuml;&aelig;&mdash;&para;&eacute;&mdash;&acute; -- '</th>
				<shiro:hasPermission name="noticeviewlog:bizNoticeViewLog:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="bizNoticeViewLog">
			<tr>
				<td><a href="${ctx}/noticeviewlog/bizNoticeViewLog/form?id=${bizNoticeViewLog.id}">
					${bizNoticeViewLog.remarks}
				</a></td>
				<td>
					<fmt:formatDate value="${bizNoticeViewLog.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="noticeviewlog:bizNoticeViewLog:edit"><td>
    				<a href="${ctx}/noticeviewlog/bizNoticeViewLog/form?id=${bizNoticeViewLog.id}">修改</a>
					<a href="${ctx}/noticeviewlog/bizNoticeViewLog/delete?id=${bizNoticeViewLog.id}" onclick="return confirmx('确认要删除该消息公告查看日志吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>