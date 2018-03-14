<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>工人使用质保金详情</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
function page(n,s){
	$("#pageNo").val(n);
	$("#pageSize").val(s);
	$("#searchForm").submit();
	return false;
}
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/guarantee/guaranteeWorker3/queryWorkerGuarantee">工人质保金查询</a></li>
		<li class="active"><a href="#">使用质保金详情</a></li>
	</ul>
	<br />
	<form:form id="inputForm" modelAttribute="bizGuaranteeMoneyBalance"
		action="${ctx}/guarantee/guaranteeManager/savePaidGuarantee"
		method="post" class="form-horizontal">
		<form:hidden path="id" />
		<sys:message content="${message}" />

		<table>
			<tr>
				<td>
					<div class="control-group">
						<label class="control-label">工人组长：</label>
						<div class="controls">
							<form:input path="empName" id="empName" htmlEscape="false"
								class="input-medium required" readonly="true" />
						</div>
					</div>
				</td>
				<td><div class="control-group">
						<label class="control-label">质保金余额：</label>
						<div class="controls">
							<form:input path="guaranteeMoneyBalance"
								id="guaranteeMoneyBalance" htmlEscape="false"
								class="input-medium required" readonly="true" />
						</div>
					</div></td>
			</tr>

			<tr>
				<td>
					<div class="control-group">
						<label class="control-label">结算上缴质保金总额：</label>
						<div class="controls">
							<form:input path="guaranteeMoneyAmountPaidSettle"
								id="guaranteeMoneyAmountPaidSettle" htmlEscape="false"
								class="input-medium required" readonly="true" />
						</div>
					</div>
				</td>
				<td><div class="control-group">
						<label class="control-label">线下上缴质保金总额：</label>
						<div class="controls">
							<form:input path="guaranteMoneyAmountPaidOffline"
								id="guaranteMoneyAmountPaidOffline" htmlEscape="false"
								class="input-medium required" readonly="true" />
						</div>
					</div></td>
			</tr>

			<tr>
				<td colspan="2">
					<div class="control-group">
						<label class="control-label">使用质保金总额：</label>
						<div class="controls">
							<form:input path="guaranteeMoneyAmountPaidUsed"
								id="guaranteeMoneyAmountPaidUsed" htmlEscape="false"
								class="input-medium required" readonly="true" />
						</div>
					</div>
				</td>
			</tr>
		</table>
	</form:form>
<form:form id="searchForm" modelAttribute="bizGuaranteeMoneyPaidUsed"
		action="${ctx}/guarantee/guaranteeWorker3/queryWorkerGuaranteeUsedDetail"
		method="post" class="breadcrumb form-search">
		<input id="usedEmployeeId" name="usedEmployeeId" type="hidden" value="${bizGuaranteeMoneyPaidUsed.usedEmployeeId}"/>
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden"
			value="${page.pageSize}" />
    </form:form>
	<table id="contentTable"
		class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>使用时间</th>
				<th>金额</th>
				<th>客户</th>
				<th>使用类型</th>
				<th>使用原因</th>
			</tr>
		</thead>
		<tbody>
		   <c:forEach items="${page.list}" var="bizGuaranteeMoneyPaidUsed">
		   <tr>
				<td><fmt:formatDate value="${bizGuaranteeMoneyPaidUsed.guaranteeMoneyDateTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td>${bizGuaranteeMoneyPaidUsed.guaranteeMoneyAmount}
				</td>
				<td>${bizGuaranteeMoneyPaidUsed.communityName}${bizGuaranteeMoneyPaidUsed.buildNumber}-${bizGuaranteeMoneyPaidUsed.buildUnit}-${bizGuaranteeMoneyPaidUsed.buildRoom}-${bizGuaranteeMoneyPaidUsed.customerName}</td>
			    <td>
			    ${fns:getDictLabel(bizGuaranteeMoneyPaidUsed.guaranteeMoneyFor,'use_guarantee_type', '')}
			    </td>
			     <td>
			    ${bizGuaranteeMoneyPaidUsed.guaranteeMoneyReason}
			    </td>
			</tr>
		   </c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>