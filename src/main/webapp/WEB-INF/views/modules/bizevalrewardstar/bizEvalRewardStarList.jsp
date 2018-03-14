<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>评价奖励星级管理</title>
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
		<li class="active"><a href="${ctx}/bizevalrewardstar/bizEvalRewardStar/">评价奖励星级列表</a></li>
		<shiro:hasPermission name="bizevalrewardstar:bizEvalRewardStar:edit"><li><a href="${ctx}/bizevalrewardstar/bizEvalRewardStar/form">评价奖励星级添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="bizEvalRewardStar" action="${ctx}/bizevalrewardstar/bizEvalRewardStar/" method="post" class="breadcrumb form-search">
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
				<th>&plusmn;&cedil;&times;&cent; -- '</th>
				<th>&cedil;&uuml;&ETH;&Acirc;&Egrave;&Otilde;&AElig;&Uacute;&Ecirc;&plusmn;&frac14;&auml; -- '</th>
				<shiro:hasPermission name="bizevalrewardstar:bizEvalRewardStar:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="bizEvalRewardStar">
			<tr>
				<td><a href="${ctx}/bizevalrewardstar/bizEvalRewardStar/form?id=${bizEvalRewardStar.id}">
					${bizEvalRewardStar.remarks}
				</a></td>
				<td>
					<fmt:formatDate value="${bizEvalRewardStar.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="bizevalrewardstar:bizEvalRewardStar:edit"><td>
    				<a href="${ctx}/bizevalrewardstar/bizEvalRewardStar/form?id=${bizEvalRewardStar.id}">修改</a>
					<a href="${ctx}/bizevalrewardstar/bizEvalRewardStar/delete?id=${bizEvalRewardStar.id}" onclick="return confirmx('确认要删除该评价奖励星级吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>