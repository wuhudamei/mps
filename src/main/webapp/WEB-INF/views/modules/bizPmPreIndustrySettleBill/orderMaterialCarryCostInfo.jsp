<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>材料搬运及运输费</title>
<meta name="decorator" content="default" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script type="text/javascript">
	$(document).ready(function() {
	});
	function page(n, s) {
		$("#pageNo").val(n);
		$("#pageSize").val(s);
		$("#searchForm").submit();
		return false;
	}

	function initMoney() {
		var carryCostAmount = $("#carryCostAmount").val();
		if(carryCostAmount == null || carryCostAmount == ""){
			carryCostAmount = 0;
		}
		var accountRate = $("#accountRate").val();
		if(accountRate == null || accountRate == ""){
			accountRate = 0;
		}
		var accountAmount = (parseFloat(carryCostAmount) * parseFloat(accountRate))/100;
		$("#accountAmount").val(accountAmount.toFixed(2));
	}
	
	function submitForm() {
		var carryCostAmount = $("#carryCostAmount").val();
		if(carryCostAmount == null || carryCostAmount == ''){
			alertx("材料搬运及运输费不能为空！");
			return;
		}
		var accountRate = $("#accountRate").val();
		if(accountRate == null || accountRate == ''){
			alertx("结算比例不能为空！");
			return;
		}
		top.$.jBox.confirm("您确认要保存吗？", "系统提示", function(v, h, f) {
			if (v == "ok") {
				$(obj).attr({
					"disabled" : "disabled"
				});
				$("#inputSubmit").submit();
			}
		}, {
			buttonsFocus : 1
		});
		top.$('.jbox-body .jbox-icon').css('top', '55px');
	}
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="javascript:void(0)">材料搬运及运输费</a></li>
	</ul>
	<ul class="ul-form breadcrumb">
		<li class="btns"><input class="btn" type="button" value="返回"
			onclick="history.go(-1)" /></li>
	</ul>
	<div class="container">
		<div class="row clearfix">
			<div class="col-md-12 column">
				<div>
					<h3>
						<center>材料搬运及运输费</center>
					</h3>
				</div>
				<table class="table table-striped table-bordered table-condensed">
					<tr>
						<td>订单编号：${order2.orderNumber}</td>
					</tr>
					<tr>
						<td>顾客：${order2.communityName}-${order2.buildNumber}-${order2.buildUnit}-${order2.buildRoom}-${order2.customerName}</td>
					</tr>
				</table>
			</div>
			<div class="col-md-12 column">
				<form id="inputSubmit" action="${ctx}/proIndustryPmSettle/proIndustryPmSettle/saveOrderMaterialCarryCost">
				    <input type="hidden" name="id" value="${bizOrderMaterialCarryCost.id}" />
				    <input type="hidden" name="orderId" value="${bizOrderMaterialCarryCost.orderId}" />
				    <input type="hidden" name="isEditSettleBill" value="${isEditSettleBill}" />
					<table class="table table-striped table-bordered table-condensed">
						<tr>
							<td>材料搬运及运输费：<input type="text" id="carryCostAmount" readonly="readonly"
								name="carryCostAmount" value="${bizOrderMaterialCarryCost.carryCostAmount}"
								oninput="this.value=this.value.replace(/[^\d\.]/g,'');initMoney()">
								元
							</td>
						</tr>

						<tr>
							<td>结算比例：<input type="text" name="accountRate"
								id="accountRate"
								value="${bizOrderMaterialCarryCost.accountRate}" readonly="readonly" oninput="this.value=this.value.replace(/[^\d\.]/g,'');initMoney()"> %
							</td>
						</tr>

						<tr>
							<td>材料搬运及运输结算金额：<input type="text" name="accountAmount"
								id="accountAmount" readonly="readonly"
								value="${bizOrderMaterialCarryCost.accountAmount}"> 元
							</td>
						</tr>

						<tr>
						  <td>材料搬运及运输费说明：<textarea name="carryCostRemarks" readonly="readonly">${bizOrderMaterialCarryCost.carryCostRemarks}</textarea></td>
						</tr>
					</table>
				</form>
			</div>
			<div class="form-actions" style="text-align: center">
					<input class="btn" type="button" value="返 回"
					onclick="history.go(-1)" />
			</div>
		</div>
	</div>
</body>
</html>