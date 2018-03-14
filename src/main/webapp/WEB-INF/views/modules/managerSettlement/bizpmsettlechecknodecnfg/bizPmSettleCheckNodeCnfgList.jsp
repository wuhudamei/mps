<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>项目经理结算关联约检节点设置管理</title>
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
		<li class="active"><a href="${ctx}/managerSettlement/bizpmsettlechecknodecnfg/bizPmSettleCheckNodeCnfg/list">项目经理结算关联约检节点设置列表</a></li>
		<shiro:hasPermission name="bizpmsettlechecknodecnfg:bizPmSettleCheckNodeCnfg:edit"><li><a href="${ctx}/managerSettlement/bizpmsettlechecknodecnfg/bizPmSettleCheckNodeCnfg/form">项目经理结算关联约检节点设置添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="bizPmSettleCheckNodeCnfg" action="${ctx}/managerSettlement/bizpmsettlechecknodecnfg/bizPmSettleCheckNodeCnfg/list2" method="post" class="breadcrumb form-search">
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
					<form:select path="storeId" class="input-medium needClear" onchange="checkKind()">
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
					<form:select path="projectMode" class="input-medium needClear" onchange="checkKind()">
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
				<th>所属结算人员</th>
				<th>结算阶段</th>
				<th>关联的约检节点</th>
				<th>状态</th>
				<shiro:hasPermission name="bizpmsettlechecknodecnfg:bizPmSettleCheckNodeCnfg:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="bizPmSettleCheckNodeCnfg" varStatus="status">
			<tr>
				<td>
					${status.index+1}
				</td>
				<td>
					${fns:getStoreLabel(bizPmSettleCheckNodeCnfg.storeId, '')}
				</td>
				<td>
					${fns:getDictLabel(bizPmSettleCheckNodeCnfg.projectMode, 'project_mode', '')}
				</td>
				<td>
					${fns:getDictLabel(bizPmSettleCheckNodeCnfg.settleRole, 'settle_role', '')}
				</td>
				<td>
					${fns:getDictLabel(bizPmSettleCheckNodeCnfg.settleNode, 'settle_node', '')}
				</td>
				<td>
					${bizPmSettleCheckNodeCnfg.qcCheckNodeName}
				</td>
				<td>
					<c:if test="${bizPmSettleCheckNodeCnfg.isEnabled=='1' }"><span style="color:#00EC00;">启用</span></c:if>
					<c:if test="${bizPmSettleCheckNodeCnfg.isEnabled=='0' }"><span style="color:red;">停用</span></c:if>
				</td>
				<shiro:hasPermission name="bizpmsettlechecknodecnfg:bizPmSettleCheckNodeCnfg:edit"><td>
    				<a href="${ctx}/managerSettlement/bizpmsettlechecknodecnfg/bizPmSettleCheckNodeCnfg/form?id=${bizPmSettleCheckNodeCnfg.id}">修改</a>
					<a href="${ctx}/managerSettlement/bizpmsettlechecknodecnfg/bizPmSettleCheckNodeCnfg/delete?id=${bizPmSettleCheckNodeCnfg.id}">
						<c:if test="${bizPmSettleCheckNodeCnfg.isEnabled=='0' }"><span style="color:#00EC00;">启用</span></c:if>
						<c:if test="${bizPmSettleCheckNodeCnfg.isEnabled=='1' }"><span style="color:red;">停用</span></c:if>
					</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>