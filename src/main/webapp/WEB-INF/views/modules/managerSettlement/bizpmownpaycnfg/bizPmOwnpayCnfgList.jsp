<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>自主支配项定义管理</title>
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
		<li class="active"><a href="${ctx}/managerSettlement/bizpmownpaycnfg/bizPmOwnpayCnfg/list">自主支配项定义列表</a></li>
		<shiro:hasPermission name="bizpmownpaycnfg:bizPmOwnpayCnfg:edit"><li><a href="${ctx}/managerSettlement/bizpmownpaycnfg/bizPmOwnpayCnfg/form">自主支配项定义添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="bizPmOwnpayCnfg" action="${ctx}/managerSettlement/bizpmownpaycnfg/bizPmOwnpayCnfg/list2" method="post" class="breadcrumb form-search">
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
			<li><label>新老房：</label>
				<form:select path="isOldNew" class="input-medium needClear">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('biz_is_new_house')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
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
				<th>门店</th>
				<th>新老房</th>
				<th>工程模式</th>
				<th>支配项名称</th>
				<th>单位</th>
				<th>金额</th>
				<th>备注</th>
				<shiro:hasPermission name="bizpmownpaycnfg:bizPmOwnpayCnfg:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="bizPmOwnpayCnfg">
			<tr>
				<td>
					${fns:getStoreLabel(bizPmOwnpayCnfg.storeId, '')}
				</td>
				<td>
					${fns:getDictLabel(bizPmOwnpayCnfg.isOldNew, 'biz_is_new_house', '')}
				</td>
				<td>
					${fns:getDictLabel(bizPmOwnpayCnfg.projectMode, 'project_mode', '')}
				</td>
				<td>
					${bizPmOwnpayCnfg.ownpayName}
				</td>
				<td>
					${bizPmOwnpayCnfg.unit}
				</td>
				<td>
					${bizPmOwnpayCnfg.amount}
				</td>
				<td>
					${bizPmOwnpayCnfg.remarks}
				</td>
				<shiro:hasPermission name="bizpmownpaycnfg:bizPmOwnpayCnfg:edit"><td>
    				<a href="${ctx}/managerSettlement/bizpmownpaycnfg/bizPmOwnpayCnfg/form?id=${bizPmOwnpayCnfg.id}">修改</a>
					<a href="${ctx}/managerSettlement/bizpmownpaycnfg/bizPmOwnpayCnfg/delete?id=${bizPmOwnpayCnfg.id}">
						<c:if test="${bizPmOwnpayCnfg.isEnabled=='0' }"><span style="color:#00EC00;">启用</span></c:if>
						<c:if test="${bizPmOwnpayCnfg.isEnabled=='1' }"><span style="color:red;">停用</span></c:if>
					</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>