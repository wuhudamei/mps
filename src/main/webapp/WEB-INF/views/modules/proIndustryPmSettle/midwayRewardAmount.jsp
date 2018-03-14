<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>中期奖励</title>
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

	
	function submitForm() {
		var rewardPunishAmount = $("#rewardPunishAmount").val();
		if(rewardPunishAmount == null || rewardPunishAmount == ''){
			alertx("中期奖励不能为空！");
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
		<li class="active"><a href="javascript:void(0)">中期奖励</a></li>
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
						<center>中期奖励</center>
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
				<form id="inputSubmit" action="${ctx}/proIndustryPmSettle/proIndustryPmSettle/savePmRewardPunish">
				    <input type="hidden" name="id" value="${bizBusinessRewardPunish.id}" />
				    <input type="hidden" name="relatedBusinessIdInt" value="${bizBusinessRewardPunish.relatedBusinessIdInt}" />
				    <input type="hidden" name="rewardPunishType" value="${bizBusinessRewardPunish.rewardPunishType}" />
				    <input type="hidden" name="relatedBusinessType" value="${bizBusinessRewardPunish.relatedBusinessType}" />
				    <input type="hidden" name="employeeId" value="${order2.itemManagerId}"/>
				    <input type="hidden" name="isEditSettleBill" value="${isEditSettleBill}" />
                    <input type="hidden" name="isNewSettleBill" value="${isNewSettleBill}"/>
					<table class="table table-striped table-bordered table-condensed">
						<tr>
							<td>中期奖励：<input type="text" id="rewardPunishAmount"
								name="rewardPunishAmount" value="${bizBusinessRewardPunish.rewardPunishAmount}">元
							</td>
						</tr>

						<tr>
						  <td>中期奖励说明：<textarea name="rewardPunishRemarks">${bizBusinessRewardPunish.rewardPunishRemarks}</textarea></td>
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