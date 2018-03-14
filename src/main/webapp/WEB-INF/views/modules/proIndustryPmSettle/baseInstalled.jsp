<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>基装增项</title>
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
		var changeAmount = $("#changeAmount").val();
		if(changeAmount == null || changeAmount == ""){
			changeAmount = 0;
		}
		var changeAccountRate = $("#changeAccountRate").val();
		if(changeAccountRate == null || changeAccountRate == ""){
			changeAccountRate = 0;
		}
		var changeAccountAmount = (parseFloat(changeAmount) * parseFloat(changeAccountRate))/100;
		$("#changeAccountAmount").val(changeAccountAmount.toFixed(2));
	}
	
	function submitForm() {
		var changeAmount = $("#changeAmount").val();
		if(changeAmount == null || changeAmount == ''){
			alertx("基装增项合计金额不能为空！");
			return;
		}
		var changeAccountRate = $("#changeAccountRate").val();
		if(changeAccountRate == null || changeAccountRate == ''){
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
		<li class="active"><a href="javascript:void(0)">基装增项</a></li>
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
						<center>基装增项</center>
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
				<form id="inputSubmit" action="${ctx}/proIndustryPmSettle/proIndustryPmSettle/saveBaseInstalled">
				    <input type="hidden" name="id" value="${bizOrderChange.id}" />
				    <input type="hidden" name="orderId" value="${bizOrderChange.orderId}" />
				    <input type="hidden" name="changeType" value="${bizOrderChange.changeType}" />
				    <input type="hidden" name="isEditSettleBill" value="${isEditSettleBill}"/>
                    <input type="hidden" name="isNewSettleBill" value="${isNewSettleBill}"/>
					<table class="table table-striped table-bordered table-condensed">
						<tr>
							<td>基装增项合计金额：<input type="text" id="changeAmount"
								name="changeAmount" value="${bizOrderChange.changeAmount}"
								oninput="this.value=this.value.replace(/[^\d\.]/g,'');initMoney()">
								元
							</td>
						</tr>

						<tr>
							<td>结算比例：<input type="text" name="changeAccountRate"
								id="changeAccountRate"
								value="${bizOrderChange.changeAccountRate}" oninput="this.value=this.value.replace(/[^\d\.]/g,'');initMoney()"> %
							</td>
						</tr>

						<tr>
							<td>基装增项结算金额：<input type="text" name="changeAccountAmount"
								id="changeAccountAmount" readonly="readonly"
								value="${bizOrderChange.changeAccountAmount}"> 元
							</td>
						</tr>

						<tr>
						  <td>基装增项结算说明：<textarea name="changeRemarks">${bizOrderChange.changeRemarks}</textarea></td>
						</tr>
					</table>
				</form>
			</div>
			<div class="form-actions" style="text-align: center">
					<input class="btn btn-primary" type="button" value="保 存"
						onclick="submitForm()" />
				&nbsp; <input class="btn" type="button" value="返 回"
					onclick="history.go(-1)" />
			</div>
		</div>
	</div>
</body>
</html>