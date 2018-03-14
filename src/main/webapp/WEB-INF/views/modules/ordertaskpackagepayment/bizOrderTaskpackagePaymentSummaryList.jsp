<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>批次对应付款单工人明细</title>
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
			$("input[name='summaryStatus']").removeAttr("checked");
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/ordertaskpackagepayment/bizOrderTaskpackagePayment/paymentSummaryLoadList">批次对应付款单工人明细列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="bizOrderTaskpackagePaymentSummaryVo" action="${ctx}/ordertaskpackagepayment/bizOrderTaskpackagePayment/paymentSummaryLoadList" method="post" class="breadcrumb form-search">
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
			<li><label>付款批次编号：</label>
				<form:input path="orderTaskpackagePaymentSummaryCode" htmlEscape="false" maxlength="64" class="input-medium needClear"/>
			</li>
			<li><label style="width: 120px">生成付款批次日期：</label>
				<input name="beginSummaryGeneratedDatetime" id="beginSummaryGeneratedDatetime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate needClear"
					   value="<fmt:formatDate value="${bizOrderTaskpackagePaymentSummaryVo.beginSummaryGeneratedDatetime}" pattern="yyyy-MM-dd"/>"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'endSummaryGeneratedDatetime\')}',isShowClear:true});"/> -
				<input name="endSummaryGeneratedDatetime" id="endSummaryGeneratedDatetime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate needClear"
					   value="<fmt:formatDate value="${bizOrderTaskpackagePaymentSummaryVo.endSummaryGeneratedDatetime}" pattern="yyyy-MM-dd"/>"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'beginSummaryGeneratedDatetime\')}',isShowClear:true});"/>
			</li>
			<li><label>付款批次状态：</label>
				<c:forEach items="${fns:getSummaryList('summary_status')}" var="dict">
					<input type="checkbox" name="summaryStatus" value="${dict.value}" <c:if test="${fns:checkIsExits(bizOrderTaskpackagePaymentSummaryVo.summaryStatus,dict.value)}"> checked="checked"</c:if>/>${dict.label}&nbsp;
				</c:forEach>
			</li>
			<li><label>付款单日期：</label>
				<input name="beginPaymentGeneratedDatetime" id="beginPaymentGeneratedDatetime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate needClear"
					   value="<fmt:formatDate value="${bizOrderTaskpackagePaymentSummaryVo.beginPaymentGeneratedDatetime}" pattern="yyyy-MM-dd"/>"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'endPaymentGeneratedDatetime\')}',isShowClear:true});"/> -
				<input name="endPaymentGeneratedDatetime" id="endPaymentGeneratedDatetime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate needClear"
					   value="<fmt:formatDate value="${bizOrderTaskpackagePaymentSummaryVo.endPaymentGeneratedDatetime}" pattern="yyyy-MM-dd"/>"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'beginPaymentGeneratedDatetime\')}',isShowClear:true});"/>
			</li>
			<li><label>付款单编号：</label>
				<form:input path="orderTaskpackagePaymentCode" htmlEscape="false" maxlength="64" class="input-medium needClear"/>
			</li>
			<li><label>付款单状态：</label>
				<c:forEach items="${fns:getPaymentList('payment_status')}" var="dict">
					<input type="checkbox" name="paymentStatus" value="${dict.value}" <c:if test="${fns:checkIsExits(bizOrderTaskpackagePaymentSummaryVo.paymentStatus,dict.value)}"> checked="checked"</c:if>/>${dict.label}&nbsp;
				</c:forEach>
			</li>
			<li><label>工人姓名：</label>
				<form:input path="realName" htmlEscape="false" maxlength="64" class="input-medium needClear"/>
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
				<th>批次编号</th>
				<th>付款批次状态</th>
				<th>批次生成日期</th>
				<th>付款单编号</th>
				<th>付款单状态</th>
				<th>付款单生成日期</th>
				<th>付款单金额</th>
				<th>付款类型</th>
				<th>对应任务包</th>
				<th>对应工人</th>
				<shiro:hasPermission name="ordertaskpackagepayment:bizOrderTaskpackagePayment:view"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="payment">
			<tr>
				<td>${fns:getStoreLabel(payment.storeId, '')}</td>
				<td>${payment.orderTaskpackagePaymentSummaryCode}</td>
				<td>${fns:getSummaryStatus(payment.summaryStatus)}</td>
				<td><fmt:formatDate value="${payment.summaryGeneratedDatetime}" pattern="yyyy-MM-dd"/></td>
				<td>${payment.orderTaskpackagePaymentCode}</td>
				<td>${fns:getPaymentStatus(payment.paymentStatus)}</td>
				<td><fmt:formatDate value="${payment.paymentGeneratedDatetime}" pattern="yyyy-MM-dd"/></td>
				<td>${payment.amount}</td>
				<td><c:if test="${payment.orderTaskpackagePaymentType eq '0'}">首款</c:if><c:if test="${payment.orderTaskpackagePaymentType eq '1'}">尾款</c:if></td>
				<td>${payment.packageName}</td>
				<td>${payment.realName}</td>
				<shiro:hasPermission name="ordertaskpackagepayment:bizOrderTaskpackagePayment:view"><td>
					<a href="${ctx}/ordertaskpackagepayment/bizOrderTaskpackagePayment/paymentListView?id=${payment.id}">付款明细查看</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>