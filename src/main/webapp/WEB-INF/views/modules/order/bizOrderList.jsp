<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>订单付款综合查询</title>
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

			$("input[name='orderStatusNumber']").removeAttr("checked");
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/order2/order2/orderLoadList">订单付款综合查询列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="order" action="${ctx}/order2/order2/orderLoadList" method="post" class="breadcrumb form-search">
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
			<li><label>订单编号：</label>
				<form:input path="orderNumber" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>客户名称：</label>
				<form:input path="customerName" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>客户手机号：</label>
				<form:input path="customerPhone" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>项目经理：</label>
				<form:input path="itemManager" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label style="width: 120px">合同开工时间：</label>
				<input name="beginContractStartDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					   value="<fmt:formatDate value="${order.beginContractStartDate}" pattern="yyyy-MM-dd"/>"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/> -
				<input name="endContractStartDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					   value="<fmt:formatDate value="${order.endContractStartDate}" pattern="yyyy-MM-dd"/>"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</li>
			<li style="height:auto"><label>订单状态：</label>
				<c:forEach items="${fns:getDictListByType('order_status')}" var="dict">
					<input type="checkbox" name="orderStatusNumber" value="${dict.value}" <c:if test="${fns:checkIsExits(order.orderStatusNumber,dict.value)}"> checked="checked"</c:if>/>${dict.label}&nbsp;
				</c:forEach>&nbsp;&nbsp;
				<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" />&nbsp;&nbsp;&nbsp;
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
				<th>订单编号</th>
				<th>客户名称</th>
				<th>客户电话</th>
				<th>客户地址</th>
				<th>项目经理</th>
				<th>质检员</th>
				<th>合同开工时间</th>
				<th>RT000012<br/>铲墙皮金额</th>
				<th>RT000023<br/>营销保护金额</th>
				<th>RT000011<br/>水电任务包首款</th>
				<th>RT000011<br/>水电任务包尾款</th>
				<th>RT000013<br/>瓦工首款</th>
				<th>RT000013<br/>瓦工尾款</th>
				<th>RT000015<br/>木工首款</th>
				<th>RT000015<br/>木工尾款</th>
				<th>RT000014<br/>油工首款</th>
				<th>RT000014<br/>油工尾款</th>
				<th>订单状态</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="order2">
			<tr>
				<td>${fns:getStoreLabel(order2.storeId, '')}</td>
				<td>${order2.orderNumber}</td>
				<td>${order2.customerName}</td>
				<td>${order2.customerPhone}</td>
				<td>${order2.customerAddress}</td>
				<td>${order2.itemManager}</td>
				<td>${order2.orderInspector}</td>
				<td><fmt:formatDate value="${order2.contractStartDate}" pattern="yyyy-MM-dd"/></td>
				<td>${order2.amount1}</td>
				<td>${order2.amount2}</td>
				<td>${order2.amount3}</td>
				<td>${order2.amount4}</td>
				<td>${order2.amount5}</td>
				<td>${order2.amount6}</td>
				<td>${order2.amount7}</td>
				<td>${order2.amount8}</td>
				<td>${order2.amount9}</td>
				<td>${order2.amount10}</td>
				<td>${fns:getOrderStatus(order2.orderStatusNumber)}</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>