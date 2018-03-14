<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>任务包审核->查看详情</title>
<meta name="decorator" content="default" />
<style>
.none {
	display: none;
}

.order-total {
	width: 100%;
	font-size: 12px;
	color: #5a5858;
}

.order-row {
	border: 1px solid #ccc;
	display: flex;
	border-top: 0;
	line-height: 2;
}

.order-row.row-title {
	border-width: 0 0 1px 0;
	text-align: center;
}

.order-item {
	flex: 1;
	display: inline-block;
}

.order-total .item-price {
	font-weight: bold;
	color: #3b3939;
	font-size: 14px;
}

.order-total .item-price._total {
	font-size: 18px;
	color: #d80101;
}
</style>
<script type="text/javascript">
	$(document).ready(
			function() {
				//$("#name").focus();
				$("#inputForm")
						.validate(
								{
									submitHandler : function(form) {
										loading('正在提交，请稍等...');
										form.submit();
									},
									errorContainer : "#messageBox",
									errorPlacement : function(error, element) {
										$("#messageBox").text("输入有误，请先更正。");
										if (element.is(":checkbox")
												|| element.is(":radio")
												|| element.parent().is(
														".input-append")) {
											error.appendTo(element.parent()
													.parent());
										} else {
											error.insertAfter(element);
										}
									}
								});
			});

	function updateValue(index) {
		var budgetNumber = $("#budgetNumber" + index).val();//预算员确认数量
		var laborPrice = $("#laborPrice" + index).val();// 人工结算价
		var accessoriesPrice = $("#accessoriesPrice" + index).val();// 辅料结算价
		var synthesizePrice = $("#synthesizePrice" + index).val();//工料结算价 
		var laborBudgetAmount = $("#laborBudgetAmount" + index).val(
				Number(budgetNumber * laborPrice)) //人工费预算金额 = 预算员确认数量 * 人工结算价    
		var auxiliaryMaterialsBudgetAmount = $(
				"#auxiliaryMaterialsBudgetAmount" + index).val(
				Number(budgetNumber * accessoriesPrice)) //辅料费预算金额 = 预算员确认数量 * 辅料结算价
		var total = $("#total" + index).val(budgetNumber * synthesizePrice);//工料费预算金额 = 预算员确认数量 * 工料结算价
	}
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<c:if test="${flag == '0'}">
			<li>
				<c:if test="${isSpecial == '0' }"><a href="${ctx}/ordertaskpackageaudit/orderTaskpackageAudit/stayList">待审核任务包列表</a></c:if>
				<c:if test="${isSpecial == '1' }"><a href="${ctx}/ordertaskpackageaudit/orderTaskpackageAudit/staySpectialList">待审核特殊任务包列表</a></c:if>
			</li>
			<li class="active"><a>查看详情</a></li>
		</c:if>
		<c:if test="${flag == '1'}">
			<li><a
				href="${ctx}/ordertaskpackageaudit/orderTaskpackageAudit/list">已审核任务包列表</a></li>
			<li class="active"><a>查看详情</a></li>
		</c:if>
	</ul>
	<ul class="nav nav-tabs">
		<li><a>${orderTaskpackage.packageName }</a></li>
        <div class="order-total">
		<li><label
			style="width: 300px; margin-left: 40px; margin-top: 7px;">
				${orderTaskpackage.packageName}工料费预算总金额：<span class="item-price _total">${orderTaskpackage.total}</span> 元
		</label></li>
		</div>
	</ul>
	<table id="contentTable"
		class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>工序归属任务包</th>
				<th>工序编号</th>
				<th>工序名称</th>
				<th>预算员确认数量</th>
				<th>单位</th>
				<th>人工结算价</th>
				<th>辅料结算价</th>
				<th>工料结算价</th>
				<!-- 定额综合价 -->
				<th>人工费预算金额</th>
				<th>辅料费预算金额</th>
				<th>工料费预算金额</th>
				<!-- 总价 -->
				<th>备注</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${orderTaskpackageAuditList}" var="p"
				varStatus="status">
				<tr>
					<td>${p.packageName}</td>
					<td>${p.procedureNo}</td>
					<td>${p.procedureName}</td>
					<td><input id="budgetNumber${status.index }"
						readonly="readonly" value="${p.budgetNumber}" maxLength="9"
						size="10" onchange="updateValue('${status.index }');" /></td>
					<td>${p.label}</td>
					<td><input id="laborPrice${status.index }"
						value="${p.laborPrice}" readonly="readonly"
						style="border: 0; font-size: medium; color: -webkit-text;" /></td>
					<td><input id="accessoriesPrice${status.index }"
						value="${p.accessoriesPrice}" readonly="readonly"
						style="border: 0; font-size: medium; color: -webkit-text;" /></td>
					<td>
						<!-- 工料结算价 --> <input id="synthesizePrice${status.index }"
						value="${p.synthesizePrice}" readonly="readonly"
						style="border: 0; font-size: medium; color: -webkit-text;" />
					</td>
					<td><input id="laborBudgetAmount${status.index }"
						value="${p.laborBudgetAmount}" readonly="readonly"
						style="border: 0; font-size: medium; color: -webkit-text;" /></td>
					<td><input id="auxiliaryMaterialsBudgetAmount${status.index }"
						value="${p.auxiliaryMaterialsBudgetAmount}" readonly="readonly"
						style="border: 0; font-size: medium; color: -webkit-text;" /></td>
					<td>
						<!-- 工料费预算金额 --> <input id="total${status.index }"
						value="${p.laborAuxiliaryMaterialsBudgetAmount}"
						readonly="readonly"
						style="border: 0; font-size: medium; color: -webkit-text;" />
					</td>
					<td><textarea>${p.remarks}</textarea></td>
				</tr>
			</c:forEach>

			<tr>
				<td>
					<div class="control-group"
						style="font-size: 10px; text-align: center;">
						<a href="javascript:" onclick="history.go(-1);" class="btn"
							style="font-size: 15px">返回上一页</a> &nbsp;
					</div>
				</td>

			</tr>

		</tbody>

	</table>
	<%-- </c:if>
	<c:if test="${fn:length(orderTaskpackageAuditList) <= 1}">
		&nbsp;&nbsp;&nbsp;数据为空!
	</c:if> --%>
</body>
</html>