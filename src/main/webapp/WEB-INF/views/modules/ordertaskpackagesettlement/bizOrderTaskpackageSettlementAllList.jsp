<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>产业工人结算明细</title>
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

			$("input[name='status']").removeAttr("checked");
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/ordertaskpackagesettlement/bizOrderTaskpackageSettlementVo/settlementDetailLoadList">产业工人结算明细列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="bizOrderTaskpackageSettlementVo" action="${ctx}/ordertaskpackagesettlement/bizOrderTaskpackageSettlementVo/settlementDetailLoadList" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>门店：</label>
				<c:if test="${empty storeDropEnable}">
					<form:select path="storeid" class="input-medium" disabled="true">
						<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
				<c:if test="${!empty storeDropEnable}">
					<form:select path="storeid" class="input-medium needClear">
						<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
			</li>
			<li><label>结算单编号：</label>
				<form:input path="settlementNo" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>客户名称：</label>
				<form:input path="customerName" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>客户电话：</label>
				<form:input path="customerPhone" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>客户地址：</label>
				<form:input path="customerAddress" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>项目经理：</label>
				<form:input path="itemCustomer" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>任务包类型：</label>
				<form:select path="taskPackageTemplatId" class="input-medium needClear">
					<form:option value="" label=""/>
					<form:options items="${fns:getTaskPackageTemplate()}" itemLabel="templatName" itemValue="id" htmlEscape="false"/>
				</form:select>
			</li>
			<li style="height:auto"><label>结算单状态：</label>
				<c:forEach items="${fns:getDictListByType('settlement_status')}" var="dict">
					<input type="checkbox" name="status" value="${dict.value}" <c:if test="${fns:checkIsExits(bizOrderTaskpackageSettlementVo.status,dict.value)}"> checked="checked"</c:if>/>${dict.label}&nbsp;
				</c:forEach>
				<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" />
				<input class="btn btn-primary clearBtn" type="button" value="清空" onclick="clearCondition()"/>
			</li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>门店</th>
				<th>结算单编号</th>
				<th>客户名称</th>
				<th>客户电话</th>
				<th>客户地址</th>
				<th>项目经理</th>
				<th>任务包类型</th>
				<th>对应工人</th>
				<th>结算总金额</th>
				<th>首款结算比例</th>
				<th>首款金额</th>
				<th>尾款结算比例</th>
				<th>尾款金额</th>
				<th>结算单状态</th>
				<shiro:hasPermission name="ordertaskpackagesettlement:bizOrderTaskpackageSettlementVo:view"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="settlement">
			<tr>
				<td>${fns:getStoreLabel(settlement.storeid, '')}</td>
				<td>${settlement.settlementNo}</td>
				<td>${settlement.customerName}</td>
				<td>${settlement.customerPhone}</td>
				<td>${settlement.customerAddress}</td>
				<td>${settlement.itemCustomer}</td>
				<td>${settlement.orderTaskpackageName}</td>
				<td>${settlement.realName}</td>
				<td>${settlement.settlementAmount}</td>
				<td>${settlement.advancePaymentRates}</td>
				<td>${settlement.advanceAmount}</td>
				<td>${settlement.restPaymentRates}</td>
				<td>${settlement.restAmount}</td>
				<td>${fns:getSettlementStatus(settlement.status)}</td>
				<shiro:hasPermission name="ordertaskpackagesettlement:bizOrderTaskpackageSettlementVo:view"><td>
					<a href="${ctx}/ordertaskpackagesettlement/bizOrderTaskpackageSettlementVo/details?id=${settlement.id}">详情</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>