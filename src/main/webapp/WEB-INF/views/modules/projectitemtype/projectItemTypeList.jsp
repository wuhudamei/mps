	<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>施工项管理</title>
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
		function clearCondition(){
			 document.getElementById("searchForm").reset();
			
			 var inputObjs=jQuery("#searchForm input[type='text']"); 
			 for(var i=0;i<inputObjs.length;i++){ 
			 var inputObj = inputObjs[i]; 
			 inputObj.value=""; 
			 } 
			
			 var selectObjs = jQuery("#searchForm input[class='input-medium']"); 
			 for(var i=0;i<selectObjs.length;i++){ 
			 var selectObj = selectObjs[i]; 
			 selectObj.value=""; 
			 } 
		}

	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/projectitemtype/projectItemType/">施工项列表</a></li>
		<shiro:hasPermission name="projectitemtype:projectItemType:edit"><li><a href="${ctx}/projectitemtype/projectItemType/form">施工项添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="projectItemType" action="${ctx}/projectitemtype/projectItemType/list2" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>名称 ：</label>
				<form:input path="projectIntemTypeName" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="btns"><input class="btn btn-primary clearBtn" type="button" value="清空" onclick="clearCondition()"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>分类名称</th>
				<th>最后一次更新操作人</th>
				<th>最后一次更新操作时间</th>
				<th>状态</th>
				<shiro:hasPermission name="projectitemtype:projectItemType:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="projectItemType">
			<tr>
				<td><a href="${ctx}/projectitemtype/projectItemType/form?id=${projectItemType.id}">
					${projectItemType.projectIntemTypeName}
				</a></td>
				
				<td>
					${fns:getDictLabel(projectItemType.updateMan, 'sys_user_type','')}
				</td>
				<td>
					<fmt:formatDate value="${projectItemType.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<c:if test="${projectItemType.status=='1' }"><span style="color:#00EC00;">启用</span></c:if>
					<c:if test="${projectItemType.status=='0' }"><span style="color:red;">停用</span></c:if>
				</td>
				<shiro:hasPermission name="projectitemtype:projectItemType:edit"><td>
    				<a href="${ctx}/projectitemtype/projectItemType/form?id=${projectItemType.projectItemTypeId}">修改</a>
    				<c:if test="${projectItemType.status=='0' }">
    				<a href="${ctx}/projectitemtype/projectItemType/delete?id=${projectItemType.projectItemTypeId}&status=1" onclick="return confirmx('确认要启用该施工类型吗？', this.href)">
				<span style="color:#00EC00;">启用</span></a>
					</c:if>
					
					
					
					<c:if test="${projectItemType.status=='1' }">
					<a href="${ctx}/projectitemtype/projectItemType/delete?id=${projectItemType.projectItemTypeId}&status=0" onclick="return confirmx('确认要停用该施工类型吗？', this.href)">
					<span style="color:red;">停用</span>
					</a>
					</c:if>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>