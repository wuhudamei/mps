<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html>
<head>
	<title>客户回访节点管理</title>
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
		<li class="active"><a href="${ctx}/customerreturnvisit/bizCustomerReturnVisit/">客户回访节点列表</a></li>
		<shiro:hasPermission name="customerreturnvisit:bizCustomerReturnVisit:edit">
		<li><a href="${ctx}/customerreturnvisit/bizCustomerReturnVisit/form">客户回访节点添加</a></li>
		</shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="bizCustomerReturnVisit" action="${ctx}/customerreturnvisit/bizCustomerReturnVisit/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>门店：</label>
				<form:select path="storeId" class="input-medium needClear" >
					<form:option value="" label=""/>
					<form:options items="${fns:getStoreList()}" itemLabel="label" selectIndex="1" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>工程模式：</label>
				<form:select path="projectMode" class="input-medium needClear" >
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('project_mode')}" itemLabel="label" selectIndex="1" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>序号</th>
				<th>门店</th>
				<th>回访节点</th>
				<th>工程节点</th>
				<th>工程模式</th>
				<c:forEach var="i" begin="1" end="${15}">
					<th>回访问题${i}</th>   
				</c:forEach>
				<shiro:hasPermission name="customerreturnvisit:bizCustomerReturnVisit:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="bizCustomerReturnVisit" varStatus="status">
			<tr>
				<td>${status.index + 1}</td>
				<td>${fns:getStoreLabel(bizCustomerReturnVisit.storeId, '')}</td>
				<td>${bizCustomerReturnVisit.returnVisitNode}</td>
				<td>${bizCustomerReturnVisit.projectNodeCn}</td>
				<td>${fns:getDictLabel(bizCustomerReturnVisit.projectMode, 'employee_project_mode', '')}</td>
					
					<c:forEach items="${bizCustomerReturnVisit.questions}" var="BizCustomerReturnVisitContent" >
						<td style="width:150px;">${BizCustomerReturnVisitContent.questionContent}</td>
					</c:forEach>
					<c:forEach var="i" begin="${fn:length(bizCustomerReturnVisit.questions)}" end="${14}">
						<td style="width:150px;"></td>
					</c:forEach>	  
				
				<shiro:hasPermission name="customerreturnvisit:bizCustomerReturnVisit:edit"><td>
    				<a href="${ctx}/customerreturnvisit/bizCustomerReturnVisit/form?id=${bizCustomerReturnVisit.id}">修改</a>
					<a href="${ctx}/customerreturnvisit/bizCustomerReturnVisit/delete?id=${bizCustomerReturnVisit.id}" onclick="return confirmx('确认要删除该客户回访节点吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>