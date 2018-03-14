<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>短信组管理</title>
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
		<li class="active"><a href="${ctx}/messagegroup/bizMessagegroup/">短信组列表</a></li>
		<shiro:hasPermission name="messagegroup:bizMessagegroup:edit"><li><a href="${ctx}/messagegroup/bizMessagegroup/form">短信组添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="bizMessagegroup" action="${ctx}/messagegroup/bizMessagegroup/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>门店：</label>
				<form:select path="storeId" class="input-medium">
					<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
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
				<th>短信组类型</th>
				<th>员工</th>
				<th>启用标记</th>
				<shiro:hasPermission name="messagegroup:bizMessagegroup:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="bizMessagegroup" varStatus="status">
			<tr>
				<td>
					${status.count }
				</td>
				<td><a href="${ctx}/messagegroup/bizMessagegroup/form?id=${bizMessagegroup.id}">
					${fns:getStoreLabel(bizMessagegroup.storeId, '')}
				</a></td>
				<td>
					${fns:getDictLabel(bizMessagegroup.messageGroupType, 'message_group_type', '')}
				</td>
				<td>
					 ${fns:getEmployeeNameByIds(bizEngineeringDepartment.jobDispatcher)}
					 ${fns:getEmployeeNameByIds(bizMessagegroup.employees)}
				</td>
					<td>
					${fns:getDictLabel(bizMessagegroup.isEnable, 'biz_enable_status', '')}
				</td>
				<shiro:hasPermission name="messagegroup:bizMessagegroup:edit">
					<td>
						<a href="${ctx}/messagegroup/bizMessagegroup/enable?id=${bizMessagegroup.id}">
							<c:if test="${bizMessagegroup.isEnable==1}">
								停用
							</c:if>
							<c:if test="${bizMessagegroup.isEnable==0}">
								启用
							</c:if>
						</a>
	    				<a href="${ctx}/messagegroup/bizMessagegroup/form?id=${bizMessagegroup.id}">修改</a>
						<!-- <a href="${ctx}/taskpackage/bizTaskPackageTemplat/delete?id=${bizTaskPackageTemplat.id}" onclick="return confirmx('确认要删除该任务包类型吗？', this.href)">删除</a> -->
					</td>
				</shiro:hasPermission> 
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>