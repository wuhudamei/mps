<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>付款单付款尾款节点设置管理</title>
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
		<li class="active"><a href="${ctx}/taskpackagetemplatchecknoderel/bizTaskPackageTemplatCheckNodeRel/">付款单付款尾款节点设置列表</a></li>
	<%-- 	<shiro:hasPermission name="taskpackagetemplatchecknoderel:bizTaskPackageTemplatCheckNodeRel:edit"><li><a href="${ctx}/taskpackagetemplatchecknoderel/bizTaskPackageTemplatCheckNodeRel/addTaskpackageNodeRel">付款单付款尾款节点设置添加</a></li></shiro:hasPermission> --%>
	</ul>
	<form:form id="searchForm" modelAttribute="bizTaskPackageTemplatCheckNodeRel" action="${ctx}/taskpackagetemplatchecknoderel/bizTaskPackageTemplatCheckNodeRel/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>门店：</label>
				<c:if test="${empty storeDropEnable}">
					<form:select path="storeId" class="input-medium" disabled="true">
						<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
				<c:if test="${!empty storeDropEnable}">
					<form:select path="storeId" class="input-medium needClear">
						<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
			</li>
			
			<li><label>工程模式：</label>
				<c:if test="${empty gongcheng}">
					<form:select path="projectMode" class="input-medium" disabled="true">
						<form:option value="" label=""/>
						<form:options items="${fns:getDictList('package_project_mode')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
				<c:if test="${!empty gongcheng}">
					<form:select path="projectMode" class="input-medium needClear">
						<form:option value="" label=""/>
						<form:options items="${fns:getDictList('package_project_mode')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
			</li>
			
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="btns"><input id="btnAdd" class="btn btn-primary" type="button" value="添加尾款付款约检节点" onclick="add()"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>序号</th>
				<th>门店</th>
				<th>工程模式</th>
				<th>任务包名称</th>
				<th>尾款付款约检节点</th>
				<th>状态</th>
				<shiro:hasPermission name="taskpackagetemplatchecknoderel:bizTaskPackageTemplatCheckNodeRel:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="bizTaskPackageTemplatCheckNodeRel" varStatus="status">
			<tr>
				<td>
					${ status.count }
				</td>
				<td>
					${fns:getStoreLabel(bizTaskPackageTemplatCheckNodeRel.storeId, '')}
				</td>
				<td>${fns:getDictLabel(bizTaskPackageTemplatCheckNodeRel.projectMode, 'package_project_mode', '')}</td>
				<td>
					${bizTaskPackageTemplatCheckNodeRel.packageName}
				</td>
				<td>
					${bizTaskPackageTemplatCheckNodeRel.qcCheckNodeName}
				</td>
				<td>
					<c:if test="${bizTaskPackageTemplatCheckNodeRel.status == '1' }">
						启用
					</c:if>
					<c:if test="${bizTaskPackageTemplatCheckNodeRel.status == '0' }">
						停用
					</c:if>
				</td>
				<shiro:hasPermission name="taskpackagetemplatchecknoderel:bizTaskPackageTemplatCheckNodeRel:edit"><td>
    				<a href="${ctx}/taskpackagetemplatchecknoderel/bizTaskPackageTemplatCheckNodeRel/form?id=${bizTaskPackageTemplatCheckNodeRel.id}">修改</a>
					<c:if test="${bizTaskPackageTemplatCheckNodeRel.status == '1' }">
						<a href="${ctx}/taskpackagetemplatchecknoderel/bizTaskPackageTemplatCheckNodeRel/updateStatus?id=${bizTaskPackageTemplatCheckNodeRel.id}&status=0" onclick="return confirmx('确认要停用该付款单尾款约检节点吗？', this.href)">停用</a>
					</c:if>
					<c:if test="${bizTaskPackageTemplatCheckNodeRel.status == '0' }">
						<a href="${ctx}/taskpackagetemplatchecknoderel/bizTaskPackageTemplatCheckNodeRel/updateStatus?id=${bizTaskPackageTemplatCheckNodeRel.id}&status=1" onclick="return confirmx('确认要启用该付款单尾款约检节点吗？', this.href)">启用</a>
					</c:if>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
	<script type="text/javascript">
		function add(){
			window.location.href ="${ctx}/taskpackagetemplatchecknoderel/bizTaskPackageTemplatCheckNodeRel/addTaskpackageNodeRel";
		}
	
	</script>
</body>
</html>