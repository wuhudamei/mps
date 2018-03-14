<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>结算单管理</title>
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
		<li class="active"><a href="${ctx}/ordertaskpackagesettlement/bizOrderTaskpackageSettlementVo/settlementLoadList">结算单列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="bizOrderTaskpackageSettlementVo" action="${ctx}/ordertaskpackagesettlement/bizOrderTaskpackageSettlementVo/settlementLoadList" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
		
			<li><label>门店：</label>
				<c:if test="${empty storeDropEnable}">
					<form:select path="storeid" class="input-medium" disabled="true" id="storeid" >
						<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
				<c:if test="${!empty storeDropEnable}">
					<form:select path="storeid" class="input-medium" id="storeid">
						<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
			</li>
			
			<li><label>工程模式：</label>
				<c:if test="${empty gongcheng}">
					<form:select path="projectMode" id="projectMode" class="input-medium" disabled="true" >
						<form:option value="" label=""/>
						<form:options items="${fns:getDictList('package_project_mode')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
				<c:if test="${!empty gongcheng}">
					<form:select path="projectMode" id="projectMode" class="input-medium needClear">
						<form:option value="" label=""/>
						<form:options items="${fns:getDictList('package_project_mode')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
			</li>
			
			<li><label>订单编号：</label>
				<form:input path="orderNo" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>结算单编号：</label>
				<form:input path="settlementNo" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>客户姓名：</label>
				<form:input path="customerName" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>项目经理：</label>
				<form:input path="itemManager" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>任务包状态：</label>
				<form:select path="packageStateid" class="input-medium needClear">
					<form:option value="" label="" />
					<form:options items="${fns:getOrderTaskPackageStatusList('taskpackage_status')}" itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
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
				<th>结算单编号</th>
				<th>订单编号</th>
				<th>客户信息</th>
				<th>项目经理</th>
				<th>任务包名称</th>
				<th>任务包状态</th>
				<th>结算金额</th>
				<th>工人组结算金额</th>
				<th>项目经理结算金额</th>
				<shiro:hasPermission name="ordertaskpackagesettlement:bizOrderTaskpackageSettlementVo:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="bizOrderTaskpackageSettlementVo">
			<tr>
			    <td>${fns:getStoreLabel(bizOrderTaskpackageSettlementVo.storeid, '')}</td>
				<td>${fns:getDictLabel(bizOrderTaskpackageSettlementVo.projectMode, 'package_project_mode', '')}</td>
				<td>
					${bizOrderTaskpackageSettlementVo.settlementNo}
				</td>
				<td>
					${bizOrderTaskpackageSettlementVo.orderNo}
				</td>
				<td>
					${bizOrderTaskpackageSettlementVo.customerMessage}-${bizOrderTaskpackageSettlementVo.customerName}
				</td>
				<td>
					${bizOrderTaskpackageSettlementVo.itemManager}
				</td>
				<td>
					${bizOrderTaskpackageSettlementVo.orderTaskpackageName}
				</td>
				<td>
					${fns:getDictLabel(bizOrderTaskpackageSettlementVo.packageStateid, 'taskpackage_status', '')}
				</td>
				<td>
					${bizOrderTaskpackageSettlementVo.settlementAmount}
				</td>
				
				<td>
					${bizOrderTaskpackageSettlementVo.workerGroupSettleAmount}
				</td>
				
				<td>
					${bizOrderTaskpackageSettlementVo.pmMaterialsSettleAmount}
				</td>
				<shiro:hasPermission name="ordertaskpackagesettlement:bizOrderTaskpackageSettlementVo:view"><td>
					<a href="${ctx}/ordertaskpackagesettlement/bizOrderTaskpackageSettlementVo/toUpdateSettlement?id=${bizOrderTaskpackageSettlementVo.id}">修改</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>