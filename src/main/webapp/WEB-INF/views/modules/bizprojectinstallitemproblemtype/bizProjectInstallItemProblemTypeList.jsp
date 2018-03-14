<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>上报问题分类设置管理</title>
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
		<li class="active"><a href="${ctx}/bizProjectInstallItemProblemType/bizProjectInstallItemProblemType/list">上报问题分类列表</a></li>
		<shiro:hasPermission name="bizProjectInstallItemProblemType:bizProjectInstallItemProblemType:edit"><li><a href="${ctx}/bizProjectInstallItemProblemType/bizProjectInstallItemProblemType/form">工程安装问题分类添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="bizProjectInstallItemProblemType" action="${ctx}/bizProjectInstallItemProblemType/bizProjectInstallItemProblemType/list2" method="post" class="breadcrumb form-search">
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
			<li><label>所属业务：</label>
				<form:select path="businessType" class="input-medium needClear">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('problem_business_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
					
			</li>
			<li><label>检查分类：</label>
				<form:input path="typeName" htmlEscape="false" maxlength="64" class="input-medium"/>
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
				<th>门店</th>
				<th>工程模式</th>
				<th>所属业务</th>
				<th>问题分类</th>
				<th>扣除分数</th>
				<th>罚款金额</th>
				<th>状态</th>
				<shiro:hasPermission name="bizProjectInstallItemProblemType:bizProjectInstallItemProblemType:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="bizProjectInstallItemProblemType">
			<tr>
				<td>
					${fns:getStoreLabel(bizProjectInstallItemProblemType.storeId, '')}
				</td>
				<td>
					${fns:getDictLabel(bizProjectInstallItemProblemType.projectMode, 'project_mode', '')}
				</td>
				<td>
					${fns:getDictLabel(bizProjectInstallItemProblemType.businessType, 'problem_business_type', '')}
				</td>
				<td>
					${bizProjectInstallItemProblemType.typeName}
				</td>
				<td>
					${bizProjectInstallItemProblemType.punishScore}
				</td>
				<td>
					${bizProjectInstallItemProblemType.punishMoney}
				</td>
				
				<td>
					<c:if test="${bizProjectInstallItemProblemType.isEnabled=='1' }"><span style="color:#00EC00;">启用</span></c:if>
					<c:if test="${bizProjectInstallItemProblemType.isEnabled=='0' }"><span style="color:red;">停用</span></c:if>
				</td>
				<shiro:hasPermission name="bizProjectInstallItemProblemType:bizProjectInstallItemProblemType:edit"><td>
    				<a href="${ctx}/bizProjectInstallItemProblemType/bizProjectInstallItemProblemType/form?id=${bizProjectInstallItemProblemType.id}">修改</a>
					<a href="${ctx}/bizProjectInstallItemProblemType/bizProjectInstallItemProblemType/delete?id=${bizProjectInstallItemProblemType.id}">
						<c:if test="${bizProjectInstallItemProblemType.isEnabled=='0' }"><span style="color:#00EC00;">启用</span></c:if>
						<c:if test="${bizProjectInstallItemProblemType.isEnabled=='1' }"><span style="color:red;">停用</span></c:if>
					</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>