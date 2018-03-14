<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>生成任务包->查看详情</title>
	<meta name="decorator" content="default"/>
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
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});
		
		function updateValue(index){
			var budgetNumber = $("#budgetNumber"+index).val();//预算员确认数量
			var accessoriesPrice = $("#accessoriesPrice"+index).val();//定额综合价
			var total = $("#total"+index).val(budgetNumber*accessoriesPrice);//总价
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/ordertaskpack/orderTaskpack/list">任务包列表</a></li>
		<li class="active"><a href="javascript:void(0)">查看详情</a></li>
	</ul><br/>
	<ul class="nav nav-tabs">
		<li><a>${order.customerName }</a></li>
	</ul>
	<div class="order-total">
		<div class="order-row row-title">
			<div class="order-item">
				订单任务包工料费预算总金额 : <span class="item-price _total" id="totalAmount"><fmt:formatNumber type="number" value="${totalAmount}" pattern="0.00" maxFractionDigits="2"/></span>
				元
			</div>
		</div>
		<ul class="nav nav-tabs">
			<c:forEach items="${orderTaskpackagekList}" var="taskpackage">
				<li><label
					style="width: 300px; margin-left: 40px; margin-top: 7px;">
						${taskpackage.packageName}工料费预算总金额：<span class="item-price _total">${taskpackage.laborAuxiliaryMaterialsBudgetAmount}</span>
						元
				</label></li>
			</c:forEach>
		</ul>
	</div>
	<sys:message content="${message}"/>
	<c:if test="${fn:length(orderTaskpackList) > 0}">
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>工序归属任务包</th>
				<th>工序编号</th>
				<th>工序名称</th>
				<th>预算员确认数量</th>
				<th>单位</th>
				<th>人工结算价</th>
				<th>辅料结算价</th>
				<th>工料结算价</th><!-- 定额综合价 -->
				<th>人工费预算金额</th>
				<th>辅料费预算金额</th>
				<th>工料费预算金额</th><!-- 总价 -->
				<th>备注</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${orderTaskpackList}" var="p" varStatus="status">
			<tr>
				<td>
					${p.packageName}
				</td>
				<td>
					${p.procedureNo}
				</td>
				<td>
					${p.procedureName}
				</td>
				<td>
					<input id="budgetNumber${status.index }" readonly="readonly" value="${p.budgetNumber}" maxLength="9" size="10" onchange="updateValue('${status.index }');"/>
				</td>
				<td>
					${p.label}
				</td>
				<td>
					<input id="laborPrice${status.index }" value="${p.laborPrice}" readonly="readonly" style="border:0;font-size: medium;color:-webkit-text;"/>
				</td>
				<td>
					<input id="accessoriesPrice${status.index }" value="${p.accessoriesPrice}" readonly="readonly" style="border:0;font-size: medium;color:-webkit-text;"/>
				</td>
				<td>
					<input id="synthesizePrice${status.index }" value="${p.synthesizePrice}" readonly="readonly" style="border:0;font-size: medium;color:-webkit-text;"/>
				</td>
				<td>
					<input id="laborBudgetAmount${status.index }" value="${p.laborBudgetAmount}" readonly="readonly" style="border:0;font-size: medium;color:-webkit-text;"/>
				</td>
				<td>
					<input id="auxiliaryMaterialsBudgetAmount${status.index }" value="${p.auxiliaryMaterialsBudgetAmount}" readonly="readonly" style="border:0;font-size: medium;color:-webkit-text;"/>
				</td>
				<td>
					<!-- 工料费预算金额 -->
					<input id="total${status.index }" value="${p.laborAuxiliaryMaterialsBudgetAmount}" readonly="readonly" style="border:0;font-size: medium;color:-webkit-text;"/>
				</td>
				<td>
					<textarea>${p.remarks}</textarea>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	</c:if>
	<c:if test="${fn:length(orderTaskpackList) < 1}">
		&nbsp;&nbsp;&nbsp;数据为空!
	</c:if>
</body>
</html>