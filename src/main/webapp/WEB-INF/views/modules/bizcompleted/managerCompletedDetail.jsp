<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>项目经理竣工提成</title>
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
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="javascript:void(0)">项目经理竣工提成明细</a></li>
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
						<center>项目经理竣工提成明细</center>
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
						<td width="50%">订单编号：${bizPmStarCommissionCnfgSnap.orderNumber}</td>
						<td width="50%">小区名称：${bizPmStarCommissionCnfgSnap.communityName}</td>
					</tr>
					<tr>
						<td width="50%">客户：${bizPmStarCommissionCnfgSnap.customerName}-${bizPmStarCommissionCnfgSnap.customerPhone}</td>
						<td width="50%">项目经理：${bizPmStarCommissionCnfgSnap.itemManager}</td>
					</tr>
					<tr>
						<td width="50%">派单时间：<fmt:formatDate
								value="${bizPmStarCommissionCnfgSnap.sendOrderDate}"
								pattern="yyyy-MM-dd HH:mm:ss" /></td>
						<td width="50%">派单时项目经理星级：${bizPmStarCommissionCnfgSnap.starLever}</td>
					</tr>
				</table>
			</div>

			<div>
				<h3>
					<center>竣工提成金额明细</center>
				</h3>
				<table class="table table-striped table-bordered table-condensed">
					<tr>
						<td colspan="2" width="50%">竣工星级提成实发金额：<input type="text"
							id="allAmount" readonly="readonly"
							value="${bizPmStarCommissionCnfgSnap.commissionAmount * bizPmStarCommissionCnfgSnap.commissionRateComplete}" /></td>
					</tr>
					<tr>
						<td width="50%">新/老房： <input type="text" readonly="readonly"
							value="${fns:getDictLabel(bizPmStarCommissionCnfgSnap.isOldNew,'biz_is_new_house', '')}" />
						</td>
						<td width="50%">派单时项目经理星级： <input type="text"
							readonly="readonly"
							value="${bizPmStarCommissionCnfgSnap.starLever}" />
						</td>
					</tr>
					<tr>
						<td width="50%">提成总金额： <input type="text" readonly="readonly"
							value="${bizPmStarCommissionCnfgSnap.commissionAmount}" />
						</td>
						<td width="50%">竣工提成比例： <input type="text"
							readonly="readonly"
							value="${bizPmStarCommissionCnfgSnap.commissionRateComplete*100}%" />
						</td>
					</tr>
				</table>
			</div>
			
			<div>
				<h3>
					<center>竣工质保金明细</center>
				</h3>
				<table class="table table-striped table-bordered table-condensed">
					<tr>
						<td colspan="2" width="50%">竣工上缴质保金金额：<input type="text"
							id="allAmount" readonly="readonly"
							value="${pmGuaranteeMoney}" /></td>
					</tr>
					<tr>
						<td width="50%">质保金上限定额： <input type="text" readonly="readonly"
							value="${gmcs.guaranteeMoneyMax}" />
						</td>
						<td width="50%">每个订单扣除额度： <input type="text"
							readonly="readonly"
							value="${gmcs.guaranteeMoneyPerOrder}" />
						</td>
					</tr>
				</table>
			</div>
			
			<div class="col-md-12 column">
				<div>
					<h3>
						<center>竣工罚款明细</center>
					</h3>
					竣工扣除罚款金额：<input type="text" value="${managerPenalty}"
						readonly="readonly" />
				</div>
				<table class="table table-striped table-bordered table-condensed">
					<thead>
						<tr>
							<th>质检报告编号</th>
							<th>检查项分类</th>
							<th>检查项</th>
							<th>罚款项目经理</th>
							<th>罚款金额</th>
							<th>提交报告时间</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${pmPunishList}" var="item">
							   <tr>
							     <td>${item.qcBillCode}</td>
							     <td>${item.qcCheckKindName}</td>
							     <td>${item.qcCheckItemName}</td>
							     <td>${item.pmEmpName}</td>
							     <td>-${item.punishMoneyAmountReal}</td>
							     <td><fmt:formatDate value="${item.subDate}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
							   </tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			
			
			<div class="col-md-12 column">
				<div>
					<h3>
						<center>竣工自采材料报销明细</center>
					</h3>
					自采材料报销金额：<input type="text" value="${sinceMaterials}"
						readonly="readonly" />
				</div>
				<table class="table table-striped table-bordered table-condensed">
					<thead>
						<tr>
							<th>项目经理</th>
							<th>小区名称</th>
							<th>客户</th>
							<th>自采材料名称</th>
							<th>客户交费金额</th>
							<th>结算比例</th>
							<th>实际结算金额</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${materialSelfbuyList}" var="item">
						<tr>
						  <td>${item.itemManager}-${item.itemPhone}</td>
						  <td>${item.communityName}-${item.buildNumber}-${item.buildUnit}-${item.buildRoom}</td>
						  <td>${item.customerName}-${item.customerPhone}</td>
						  <td>${item.materialName}</td>
						  <td>${item.customerPayAmount}</td>
						  <td>${item.settleRate*100}%</td>
						  <td>${item.settleAmount}</td>
						</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>

			<div class="col-md-12 column">
				<div>
					<h3>
						<center>任务包材料结算明细</center>
					</h3>
					竣工任务包材料结算总金额：<input type="text" value="${pmMaterialsSettleAmount}"
						readonly="readonly" />
				</div>
				<table class="table table-striped table-bordered table-condensed">
					<thead>
						<tr>
							<th>任务包编号</th>
							<th>任务包名称</th>
							<th>辅料费结算总金额</th>
							<th>辅料扣款金额</th>
							<th>沙子水泥扣款金额</th>
							<th>项目经理材料结算金额</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${pmMaterials}" var="pmMaterial">
							<tr>
								<td>${pmMaterial.taskPackageNo}</td>
								<td>${pmMaterial.taskPackageName}</td>
								<td>${pmMaterial.auxiliaryMaterialsSettleAmount}</td>
								<td>${pmMaterial.auxiliaryMaterialsAmount}</td>
								<td>${pmMaterial.sandCementAmount}</td>
								<td>${pmMaterial.pmMaterialsSettleAmount}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>

			<div>
				<h3>
					<center>结算汇总</center>
				</h3>
				竣工提成合计金额：<input type="text" id="allAmount"
							readonly="readonly" value="${settleAmount}" />
				<table class="table table-striped table-bordered table-condensed">
					<tr>
						<td colspan="2">竣工星级提成金额：<input type="text"
							id="guaranteeMoneyAmount" readonly="readonly"
							name="guaranteeMoneyAmount" value="${commissionAmount}" /></td>
					</tr>
					<tr>
						<td width="50%">质保金扣款金额：<input type="text"
							name="qcPunishMoneyAmount" readonly="readonly"
							value="${pmGuaranteeMoney}" />
						</td>
						<td width="50%">竣工罚款金额：<input type="text"
							name="auxiliaryMaterialsAmount" readonly="readonly"
							value="${managerPenalty}" /></td>
					</tr>
					<tr>
						<td width="50%">自采材料报销金额：<input type="text"
							name="sandMaterialsAmount" readonly="readonly"
							value="${sinceMaterials}" /></td>
						<td width="50%">竣工任务包材料结算总金额：<input type="text"
							name="pmMaterialsSettleAmount" readonly="readonly"
							value="${pmMaterialsSettleAmount}" /></td>
					</tr>
					<tr>
					  <td width="50%">竣工奖励金额：<input type="text"
							name="pmRewardAmount" readonly="readonly"
							value="${pmRewardAmount}" /></td>
					  <td width="50%">竣工扣款金额：<input type="text"
							name="pmPunishAmount" readonly="readonly"
							value="${pmPunishAmount}" /></td>
					</tr>
					<tr>
						<td width="50%">竣工巡检奖励金额：<input type="text"
													  name="pmInspectionRewardAmount" readonly="readonly"
													  value="${pmInspectionRewardAmount}" /></td>
						<td width="50%">竣工巡检罚款金额：<input type="text"
													  name="pmInspectionPunishAmount" readonly="readonly"
													  value="${pmInspectionPunishAmount}" /></td>
					</tr>
				</table>
			</div>
		</div>
	</div>
</body>
</html>