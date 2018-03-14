<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>承包总价</title>
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

	function initMoney(type) {
		var packagedSquare = $("#packagedSquare").val();
		var contractSubsidySquare = $("#contractSubsidySquare").val();
		if (type == 1) {
			if (packagedSquare == null || packagedSquare == '') {
				packagedSquare = 0;
			}
			contractSubsidySquare = packagedSquare;
			$("#contractSubsidySquare").val(contractSubsidySquare);
			
		} else if (type == 2) {
			if (contractSubsidySquare == null || contractSubsidySquare == '') {
				contractSubsidySquare = 0;
			}
			packagedSquare = contractSubsidySquare;
			$("#packagedSquare").val(packagedSquare);
			
		}

		var packagedPrice = $("#packagedPrice").val();
		var packagedAmount = (parseFloat(packagedSquare) * parseFloat(packagedPrice)).toFixed(2);
		$("#packagedAmount").val(packagedAmount);

		var contractSubsidyPrice = $("#contractSubsidyPrice").val();
		var contractSubsidyAmount = (parseFloat(contractSubsidySquare) * parseFloat(contractSubsidyPrice))
				.toFixed(2);
		$("#contractSubsidyAmount").val(contractSubsidyAmount);

		$("#contractAmount")
				.val(
						(parseFloat(contractSubsidyAmount) + parseFloat(packagedAmount))
								.toFixed(2));
	}
	
	function submitForm() {
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
		<li class="active"><a href="javascript:void(0)">承包总价</a></li>
	</ul>
	<ul class="ul-form breadcrumb">
		<li class="btns"><input class="btn" type="button" value="返回"
			onclick="history.go(-1)" /></li>
	</ul>
	<div class="container">
		<div class="row clearfix">
			<div class="col-md-12 column">
				<div class="nav nav-tabs">
					<h2>
						<center>承包总价</center>
					</h2>
				</div>
			</div>
		</div>
		<div class="row clearfix">
			<div class="col-md-12 column">
				<div>
					<h3>
						<center>基本信息</center>
					</h3>
				</div>
				<table class="table table-striped table-bordered table-condensed">
					<tr>
						<td>订单编号：${order2.orderNumber}</td>
					</tr>
					<tr>
						<td>顾客：${order2.communityName}-${order2.buildNumber}-${order2.buildUnit}-${order2.buildRoom}-${order2.customerName}</td>
					</tr>
					<tr>
						<td>合同面积：${order2.contractArea}m²</td>
					</tr>
				</table>
			</div>
			<div class="col-md-12 column">
				<div>
					<h3>
						<center>承包结算汇总</center>
					</h3>
				</div>
				<form id="inputSubmit" action="${ctx}/proIndustryPmSettle/proIndustryPmSettle/saveContractTotal">
				    <input type="hidden" name="id" value="${contractSettle.id}" />
				     <input type="hidden" name="orderId" value="${contractSettle.orderId}" />
					<table class="table table-striped table-bordered table-condensed">
						<tr>
							<td>计价面积：<input type="text" id="packagedSquare" readonly="readonly"
								name="packagedSquare" value="${contractSettle.packagedSquare}"
								oninput="this.value=this.value.replace(/[^\d\.]/g,'');initMoney(1)">
								m²
							</td>
						</tr>

						<tr>
							<td>结算单价：<input type="text" name="packagedPrice"
								id="packagedPrice" readonly="readonly"
								value="${contractSettle.packagedPrice}"> 元/m²
							</td>
						</tr>

						<tr>
							<td>套餐总价：<input type="text" name="packagedAmount"
								id="packagedAmount" readonly="readonly"
								value="${contractSettle.packagedAmount}"> 元
							</td>
						</tr>

						<tr>
							<td>补助面积：<input type="text" name="contractSubsidySquare"
								id="contractSubsidySquare" readonly="readonly"
								value="${contractSettle.contractSubsidySquare}"
								oninput="this.value=this.value.replace(/[^\d\.]/g,'');initMoney(2)">
								m²
							</td>
						</tr>

						<tr>
							<td>补助单价：<input type="text" name="contractSubsidyPrice"
								id="contractSubsidyPrice" readonly="readonly"
								value="${contractSettle.contractSubsidyPrice}"> 元/m²
							</td>
						</tr>

						<tr>
							<td>补助总价：<input type="text" name="contractSubsidyAmount"
								id="contractSubsidyAmount" readonly="readonly"
								value="${contractSettle.contractSubsidyAmount}"> 元
							</td>
						</tr>

						<tr>
							<td>承包总价：<input type="text" name="contractAmount"
								id="contractAmount" readonly="readonly"
								value="${contractSettle.contractAmount}"> 元
							</td>
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