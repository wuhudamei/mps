<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>项目经理质保金设置管理</title>
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
		<li class="active"><a href="${ctx}/managerSettlement/bizpmguaranteemoneycnfg/bizPmGuaranteeMoneyCnfg/list">项目经理质保金设置列表</a></li>
		<shiro:hasPermission name="bizpmguaranteemoneycnfg:bizPmGuaranteeMoneyCnfg:edit"><li><a href="${ctx}/managerSettlement/bizpmguaranteemoneycnfg/bizPmGuaranteeMoneyCnfg/form">项目经理质保金设置添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="bizPmGuaranteeMoneyCnfg" action="${ctx}/managerSettlement/bizpmguaranteemoneycnfg/bizPmGuaranteeMoneyCnfg/list2" method="post" class="breadcrumb form-search">
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
				<th>门店</th>
				<th>工程模式</th>
				<th>质保金上限定额</th>
				<th>每个订单扣除额度</th>
				<shiro:hasPermission name="bizpmguaranteemoneycnfg:bizPmGuaranteeMoneyCnfg:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="bizPmGuaranteeMoneyCnfg">
			<tr>
				<td>
					${fns:getStoreLabel(bizPmGuaranteeMoneyCnfg.storeId, '')}
				</td>
				<td>
					${fns:getDictLabel(bizPmGuaranteeMoneyCnfg.projectMode, 'project_mode', '')}
				</td>
				<td>
					${bizPmGuaranteeMoneyCnfg.guaranteeMoneyMax}
				</td>
				<td>
					${bizPmGuaranteeMoneyCnfg.guaranteeMoneyPerOrder}
				</td>
				<shiro:hasPermission name="bizpmguaranteemoneycnfg:bizPmGuaranteeMoneyCnfg:edit"><td>
    				<a href="${ctx}/managerSettlement/bizpmguaranteemoneycnfg/bizPmGuaranteeMoneyCnfg/form?id=${bizPmGuaranteeMoneyCnfg.id}">修改</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>