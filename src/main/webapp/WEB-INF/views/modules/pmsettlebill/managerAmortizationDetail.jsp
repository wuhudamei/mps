<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>项目经理中期提成</title>
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
		<li class="active"><a href="javascript:void(0)">项目经理中期提成明细</a></li>
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
						<center>项目经理中期提成明细</center>
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
						<center>中期提成金额明细</center>
					</h3>
					<table class="table table-striped table-bordered table-condensed">
						<tr>
							<td colspan="2" width="50%">中期星级提成实发金额：<input type="text" id="allAmount"
								readonly="readonly" value="${bizPmStarCommissionCnfgSnap.commissionAmount* bizPmStarCommissionCnfgSnap.commissionRateMidway}" /></td>
						</tr>
						<tr>
							<td width="50%">新/老房：
							  <input type="text"  readonly="readonly" value="${fns:getDictLabel(bizPmStarCommissionCnfgSnap.isOldNew,'biz_is_new_house', '')}" />
							</td>
							<td width="50%">派单时项目经理星级：
							  <input type="text" readonly="readonly" value="${bizPmStarCommissionCnfgSnap.starLever}" />
							</td>
						</tr>
						<tr>
							<td width="50%">提成总金额：
							   <input type="text" readonly="readonly" value="${bizPmStarCommissionCnfgSnap.commissionAmount}" />
							</td>
							<td width="50%">中期提成比例：
							     <input type="text" readonly="readonly" value="${bizPmStarCommissionCnfgSnap.commissionRateMidway*100}%" />
							</td>
						</tr>
					</table>
				</div>
				
				<div class="col-md-12 column">
					<div>
						<h3>
							<center>标化材料明细</center>
						</h3>
						标化材料扣款金额：<input type="text" value="${details3.receiveBillAmount}"
							readonly="readonly" />
					</div>
					<table class="table table-striped table-bordered table-condensed">
						<thead>
							<tr>
								<th>申请单号</th>
								<th>物料类别</th>
								<th>物料名称</th>
								<th>单价</th>
								<th>领取数量</th>
								<th>总价</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${materialsStandardList}" var="item">
							   <tr>
							      <td>${item.materialsStandardReceiveBillCode}</td>
							      <td>${item.materialsType}</td>
							      <td>${item.materialsName}</td>
							      <td>${item.materialsPrice}</td>
							      <td>${item.receiveNumberTotal}</td>
							      <td>${item.materialsAmount}</td>
							   </tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				
				<div class="col-md-12 column">
					<div>
						<h3>
							<center>中期自主支配明细</center>
						</h3>
						自主支配金额：<input type="text" value="${managerOwnpay}"
							readonly="readonly" />
					</div>
					<table class="table table-striped table-bordered table-condensed">
						<thead>
							<tr>
								<th>支配项目</th>
								<th>单位</th>
								<th>金额</th>
								<th>备注</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${pmOwnpayCnfgSnapList}" var="item">
							 <tr>
							   <td>${item.ownpayName}</td>
							   <td>${item.unit}</td>
							   <td>${item.amount}</td>
							   <td>${item.remarks}</td>
							 </tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				
				<div class="col-md-12 column">
					<div>
						<h3>
							<center>中期罚款明细</center>
						</h3>
						中期扣除罚款金额：<input type="text" value="-${managerPenalty}"
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
							<center>任务包材料结算明细</center>
						</h3>
						中期任务包材料结算总金额：<input type="text" value="${pmMaterialsSettleAmount}"
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
					<table class="table table-striped table-bordered table-condensed">
						<tr>
							<td width="50%">中期提成合计金额：<input type="text" id="allAmount"
								readonly="readonly" value="${settleAmount}" /></td>
							<td width="50%">中期星级提成金额：<input type="text"
								id="guaranteeMoneyAmount" readonly="readonly"
								name="guaranteeMoneyAmount" value="${commissionAmount}" /></td>
						</tr>
						<tr>
							<td width="50%">标化材料扣款金额：<input type="text"
								name="qcPunishMoneyAmount" readonly="readonly"
								value="${details3.receiveBillAmount}" />
							</td>
							<td width="50%">自主支配金额：<input type="text"
								name="auxiliaryMaterialsAmount" readonly="readonly"
								value="${managerOwnpay}" /></td>
						</tr>
						<tr>
							<td width="50%">中期罚款金额：<input type="text"
								name="sandMaterialsAmount" readonly="readonly"
								value="${managerPenalty}" /></td>
							<td width="50%">中期任务包材料结算总金额：<input type="text"
								name="pmMaterialsSettleAmount" readonly="readonly"
								value="${pmMaterialsSettleAmount}" /></td>
						</tr>
					</table>
				</div>
		</div>
	</div>
</body>
</html>