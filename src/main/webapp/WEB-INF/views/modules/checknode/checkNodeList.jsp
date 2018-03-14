<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>约检节点管理</title>
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
		<li class="active"><a href="${ctx}/checknode/checkNode/list">约检节点列表</a></li>
		<shiro:hasPermission name="checknode:checkNode:edit"><li><a href="${ctx}/checknode/checkNode/form">约检节点添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="checkNode" action="${ctx}/checknode/checkNode/checknodeList" method="post" class="breadcrumb form-search">
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
						<form:options items="${fns:getDictList('project_mode')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
				<c:if test="${!empty gongcheng}">
					<form:select path="projectMode" class="input-medium needClear">
						<form:option value="" label=""/>
						<form:options items="${fns:getDictList('project_mode')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
					
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
				<th>序号</th>
				<th>门店</th>
				<th>工程模式</th>
				<th>节点名称</th>
				<th>节点顺序</th>
				<th>开工后第几天申请 </th>
				<th>开工后第几天检查 </th>
				<th>所属进度节点模板</th>
				<th>是否缴催</th>
				<th>是否基装约检节点</th>
				<th>状态</th>
				<shiro:hasPermission name="checknode:checkNode:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="checkNode" varStatus="status">
			<tr>
				<td>
					${status.index+1}
				</td>
				<td><a href="${ctx}/checknode/checkNode/form?id=${checkNode.id}">
					${fns:getStoreLabel(checkNode.storeId, '')}
				</a></td>
				<td>
					${fns:getDictLabel(checkNode.projectMode, 'project_mode', '')}
				</td>
				<td>
					${checkNode.qcCheckNodeName}
				</td>
				<td>
					${checkNode.qcCheckNodeIndex}
				</td>
				<td>
					${checkNode.daysToApply}
				</td><td>
					${checkNode.daysToCheck}
				</td>
				<td>
					${checkNode.constructionScheduleName}
				</td>
				<td>
				<c:if test="${checkNode.isUrgePay == 1}">是</c:if>
				<c:if test="${checkNode.isUrgePay ==0}">否</c:if>
				
				</td>
				<td>
				<c:if test="${checkNode.isForBasicWork==1 }"><span>是</span></c:if>
				<c:if test="${checkNode.isForBasicWork==0 }"><span>否</span></c:if>

				</td>

				<td>
				<c:if test="${checkNode.status=='1' }"><span style="color:#00EC00;">启用</span></c:if>
					<c:if test="${checkNode.status=='0' }"><span style="color:red;">停用</span></c:if>
					
				</td>



				
				<shiro:hasPermission name="checknode:checkNode:edit"><td>
    				<a href="${ctx}/checknode/checkNode/form?id=${checkNode.id}">修改</a>
					<c:if test="${checkNode.status=='0' }">
						<a href="${ctx}/checknode/checkNode/delete?id=${checkNode.id}&status=1" onclick="return confirmx('确认要启用该约检节点项吗？', this.href)">
							<span style="color:#00EC00;">启用</span>
						</a>
					</c:if>
					
					
					
					<c:if test="${checkNode.status=='1' }">
						<a href="${ctx}/checknode/checkNode/delete?id=${checkNode.id}&status=0" onclick="return confirmx('确认要停用该约检节点项吗？', this.href)">
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