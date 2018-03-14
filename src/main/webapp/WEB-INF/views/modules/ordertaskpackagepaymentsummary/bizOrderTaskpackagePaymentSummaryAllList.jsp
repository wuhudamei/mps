<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>付款单管理</title>
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
		<li class="active"><a href="${ctx}/ordertaskpackagepaymentsummary/bizOrderTaskpackagePaymentSummary/paymentSummaryLoadList">付款单明细列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="bizOrderTaskpackagePaymentSummary" action="${ctx}/ordertaskpackagepaymentsummary/bizOrderTaskpackagePaymentSummary/paymentSummaryLoadList" method="post" class="breadcrumb form-search">
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
			<li><label>工人姓名：</label>
				<form:input path="realName" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>付款批次编号：</label>
				<form:input path="orderTaskpackagePaymentSummaryCode" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label style="width: 105px">批次生成日期：</label>
				<input name="beginGeneratedDatetime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					   value="<fmt:formatDate value="${bizOrderTaskpackagePaymentSummary.beginGeneratedDatetime}" pattern="yyyy-MM-dd"/>"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/> -
				<input name="endGeneratedDatetime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					   value="<fmt:formatDate value="${bizOrderTaskpackagePaymentSummary.endGeneratedDatetime}" pattern="yyyy-MM-dd"/>"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</li>
			<li><label>付款批次状态：</label>
				<c:forEach items="${fns:getPaymentSummaryList('summary_status')}" var="dict">
					<input type="checkbox" name="status" value="${dict.value}" <c:if test="${fns:checkIsExits(bizOrderTaskpackagePaymentSummary.status,dict.value)}"> checked="checked"</c:if>/>${dict.label}&nbsp;
				</c:forEach>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" /></li>
			<li class="btns"><input class="btn btn-primary clearBtn" type="button" value="清空" onclick="clearCondition()"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>门店</th>
				<th>付款批次编号</th>
				<th>付款批次状态</th>
				<th>批次生成日期</th>
				<th>工人姓名</th>
				<th>工人手机号</th>
				<th>批次付款总金额</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="summary">
			<tr>
				<td>${fns:getStoreLabel(summary.storeId, '')}</td>
				<td>${summary.orderTaskpackagePaymentSummaryCode}</td>
				<td>${fns:getPaymentSummaryStatus(summary.status)}</td>
				<td><fmt:formatDate value="${summary.generatedDatetime}" pattern="yyyy-MM-dd"/></td>
				<td>${summary.realName}</td>
				<td>${summary.phone}</td>
				<td>${summary.amount}</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>