<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>结算单详情</title>
<meta name="decorator" content="default" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script type="text/javascript">
			$(document).ready(function() {
			});
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
		<li class="active"><a href="javascript:void(0)">结算单详情</a></li>
	</ul>
	<div class="container">
		<div class="row clearfix">
			<div class="col-md-12 column">
				<div class="nav nav-tabs">
					<h2>
						<center>任务包结算单</center>
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
						<td width="50%">
							结算单号：${bizOrderTaskpackageSettlementVo.settlementNo }</td>
						<td width="50%">
							项目名称：${bizOrderTaskpackageSettlementVo.customerMessage }-${bizOrderTaskpackageSettlementVo.customerName }
						</td>
					</tr>
					<tr>
						<td width="50%">
							订单编号：${bizOrderTaskpackageSettlementVo.orderNo }</td>
						<td width="50%">
							任务包名：${bizOrderTaskpackageSettlementVo.orderTaskpackageName }</td>
					</tr>
					<tr>
						<td width="50%">
							任务包号：${bizOrderTaskpackageSettlementVo.taskPackageNo }</td>
						<td width="50%">
							工人组长：${bizOrderTaskpackageSettlementVo.groupRealname }</td>
					</tr>
					<tr>
						<td width="50%">
							项目经理：${bizOrderTaskpackageSettlementVo.itemManager }</td>
						<td width="50%">
							户型/面积：${fns:getDictLabel(bizOrderTaskpackageSettlementVo.houseType, 'home_type', '')}/${bizOrderTaskpackageSettlementVo.coveredArea }
						</td>
					</tr>
					<tr>
						<td width="50%">设 计
							师：${bizOrderTaskpackageSettlementVo.designerName }</td>
						<td width="50%"></td>
					</tr>
				</table>
			</div>

			<div class="col-md-12 column">
				<div>
					<h3>
						<center>项目评定</center>
					</h3>
				</div>
				<table class="table table-striped table-bordered table-condensed">

					<tr>
						<td width="33%">验收日期</td>
						<td width="67%"><fmt:formatDate
								value="${bizOrderTaskpackageSettlementVo.checkDate }"
								pattern="yyyy-MM-dd" /></td>
					</tr>
					<tr>
						<td width="33%">质量</td>
						<td width="67%"><input type="radio" name="quality"
							<c:if test="${bizOrderTaskpackageSettlementVo.isQualified == '1'}">checked="checked"</c:if>
							disabled>合格 <input type="radio" name="quality"
							<c:if test="${bizOrderTaskpackageSettlementVo.isQualified == '0'}">checked="checked"</c:if>
							disabled>不合格</td>
					</tr>
					<tr>
						<td width="33%">工期</td>
						<td width="67%">
						    <input type="radio" name="time"
							<c:if test="${bizOrderTaskpackageSettlementVo.isDelay == '0'}">checked="checked"</c:if>
							disabled />按时完成 
							<input type="radio" name="time"
							<c:if test="${bizOrderTaskpackageSettlementVo.isDelay == '1'}">checked="checked"</c:if>
							disabled />延期 
							<input type="text" name="days"
							value="${bizOrderTaskpackageSettlementVo.delayDays}"
							readonly="readonly" />天</td>
					</tr>
					<tr>
						<td width="33%">管理处罚</td>
						<td width="67%"><input type="radio" name="managePunishment"
							<c:if test="${bizOrderTaskpackageSettlementVo.isManagePunish == '1'}">checked="checked"</c:if>
							disabled>有 <input type="radio" name="managePunishment"
							<c:if test="${bizOrderTaskpackageSettlementVo.isManagePunish == '0'}">checked="checked"</c:if>
							disabled>无 <input type="text" name="money"
							value="${bizOrderTaskpackageSettlementVo.punishAmerce}"
							readonly="readonly">元 理由：<input type="text" name="reason"
							value="${bizOrderTaskpackageSettlementVo.punishReason}"
							readonly="readonly"></td>
					</tr>
				</table>
			</div>

			<div class="col-md-12 column">
				<div>
					<h3>
						<center>任务包工序清单</center>
					</h3>
					工料费结算总金额：<input type="text" value="${bizOrderTaskpackageSettlementVo.laborAuxiliaryMaterialsSettleAmount}" readonly="readonly" />
					<br />
					人工费结算总金额：<input type="text" value="${bizOrderTaskpackageSettlementVo.laborSettleAmount}" readonly="readonly" />
					辅料费结算总金额：<input type="text" value="${bizOrderTaskpackageSettlementVo.auxiliaryMaterialsSettleAmount}" readonly="readonly" />
				</div>
				<table class="table table-striped table-bordered table-condensed">
					<thead>
						<tr>
							<th>施工项目</th>
							<th>单位</th>
							<th>预算工程量</th>
							<th>实际工程量</th>
							<th style="color: blue">复核工程量</th>
							<th style="color: red">结算工程量</th>
							<th>工料结算价</th>
							<th>人工结算价</th>
							<th>辅料结算价</th>
							<th>工料费结算金额</th>
							<th>人工费结算金额</th>
							<th>辅料费结算金额</th>
							<th>备注</th>
							<th>复核说明</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${procedures}" var="procedure">
							<tr>
								<td>${procedure.procedureName }</td>
								<td>${procedure.measurementUnitLabel }</td>
								<td>${procedure.budgetNumber }</td>
								<td>${procedure.realNumber } 
								</td>
								<td style="color: blue">${procedure.recheckNumber }</td>
								<td style="color: red">${procedure.settlementNumber }</td>
								<td>${procedure.synthesizePrice}</td>
								<td>${procedure.laborPrice}</td>
								<td>${procedure.accessoriesPrice}</td>
								<td>${procedure.laborAuxiliaryMaterialsSettleAmount}</td>
								<td>${procedure.laborSettleAmount}</td>
								<td>${procedure.auxiliaryMaterialsSettleAmount}</td>
								<td>${procedure.remarks }</td>
								<td>${procedure.recheckRemarks }</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>

			<div class="col-md-12 column">
				<div>
					<h3>
						<center>辅料核算清单</center>
					</h3>
					辅料总金额：<input type="text"
						value="${bizOrderTaskpackageSettlementVo.auxiliaryMaterialsAmount}"
						readonly="readonly">
				</div>


				<table class="table table-striped table-bordered table-condensed">
					<thead>
						<tr>
							<th>序号</th>
							<th>辅料商品编号</th>
							<th>辅料商品名称</th>
							<th>单位</th>
							<th>结算价</th>
							<th>使用数量</th>
							<th>总价</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${auxiliarys}" var="auxiliary"
							varStatus="status">
							<tr>
								<td>${status.index +1 }</td>
								<td>${auxiliary.auxiliaryMaterialsNo }</td>
								<td>${auxiliary.auxiliaryMaterialsName }</td>
								<td>${fns:getDictLabel(auxiliary.measurementUnit, 'biz_material_unit', '')}
								</td>
								<td>${auxiliary.laborPrice }</td>
								<td>${auxiliary.usedCount }</td>
								<td>${auxiliary.price }</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>

			<div class="col-md-12 column">
				<div>
					<h3>
						<center>沙子水泥核算清单</center>
					</h3>
					沙子水泥总金额：<input type="text"
					  <c:if test="${bizOrderTaskpackageSettlementVo.sandMaterialsAmount == null}">value="0.0"</c:if>
					  <c:if test="${bizOrderTaskpackageSettlementVo.sandMaterialsAmount != null}">value="${bizOrderTaskpackageSettlementVo.sandMaterialsAmount}"</c:if>
						
						readonly="readonly">
				</div>


				<table class="table table-striped table-bordered table-condensed">
					<thead>
						<tr>
							<th>序号</th>
							<th>商品编号</th>
							<th>商品名称</th>
							<th>单位</th>
							<th>结算价</th>
							<th>使用数量</th>
							<th>总价</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${sands}" var="auxiliary" varStatus="status">
							<tr>
								<td>${status.index +1 }</td>
								<td>${auxiliary.auxiliaryMaterialsNo }</td>
								<td>${auxiliary.auxiliaryMaterialsName }</td>
								<td>${fns:getDictLabel(auxiliary.measurementUnit, 'biz_material_unit', '')}
								</td>
								<td>${auxiliary.laborPrice }</td>
								<td>${auxiliary.usedCount }</td>
								<td>${auxiliary.price }</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>


			<div class="col-md-12 column">
				<div>
					<h3>
						<center>上缴质保金</center>
					</h3>
				</div>
				<table class="table table-striped table-bordered table-condensed">
					<tr>
						<td>工人组长：${guarantee.groupRealname }</td>
						<td>本次上缴质保金：${guarantee.guaranteeMoneyAmount }</td>
						<td>累次上缴质保金:${guarantee.guaranteeMoneyAmountTotal }</td>
					</tr>
				</table>
			</div>

			<div class="col-md-12 column">
				<div>
					<h3>
						<center>奖励金额</center>
					</h3>
				</div>
				<table class="table table-striped table-bordered table-condensed">
					<thead>
						<tr>
							<td width="50%" style="text-align: center">任务包星级：${fns:getDictLabel(employeeRewardDetail.starLevel, 'eval_reward_star', '')}</td>
							<td width="50%" style="text-align: center">分数：${bizEvalTaskpackScore.gotScore}</td>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td colspan="2" style="text-align: center">奖励金额：${employeeRewardDetail.rewardAmount}<span
								style="color: #f00">&nbsp;&nbsp;(奖励金额会在结算员审核通过后分配给工人组长)</span></td>
						</tr>
					</tbody>
				</table>
			</div>

			<div class="col-md-12 column">
				<div>
					<h3>
						<center>结算汇总</center>
					</h3>
				</div>
				<table class="table table-striped table-bordered table-condensed" style="width:1200px">
				    <tr>
				      <td colspan="3">结算方式：${fns:getDictLabel(bizOrderTaskpackageSettlementVo.settleStyle, 'settle_style', '')}</td>
				    </tr>
				    <tr>
						<td>结算总金额：${bizOrderTaskpackageSettlementVo.settlementAmount}</td>
						<td>工人组结算金额：${bizOrderTaskpackageSettlementVo.workerGroupSettleAmount}</td>
						<td>项目经理材料结算金额：${bizOrderTaskpackageSettlementVo.pmMaterialsSettleAmount}</td>
					</tr>
					<tr>
						<td>工料费结算总金额：${bizOrderTaskpackageSettlementVo.laborAuxiliaryMaterialsSettleAmount}</td>
						<td>人工费结算总金额：${bizOrderTaskpackageSettlementVo.laborSettleAmount}</td>
						<td>辅料费结算总金额：${bizOrderTaskpackageSettlementVo.auxiliaryMaterialsSettleAmount}</td>
					</tr>
					<tr>
						<td>工期扣款：${empty bizOrderTaskpackageSettlementVo.delayAmerce ? 0.0 : bizOrderTaskpackageSettlementVo.delayAmerce}</td>
					    <td>管理扣款：${empty bizOrderTaskpackageSettlementVo.punishAmerce ? 0.0 : bizOrderTaskpackageSettlementVo.punishAmerce}</td>
					    <td>质检罚款：${ bizOrderTaskpackageSettlementVo.qcPunishMoneyAmount}</td>
					</tr>
					<tr>
					  <td>公司扣款：${empty bizOrderTaskpackageSettlementVo.companyDeductAmount ? 0.0 : bizOrderTaskpackageSettlementVo.companyDeductAmount}</td>
					  <td>辅料扣款：${totalPrice}</td>
					  <td>沙子水泥扣款：${sandTotalPrice}</td>
					</tr>
					<tr>
						<td>质保金：${bizOrderTaskpackageSettlementVo.guaranteeMoneyAmount}</td>
						<td>奖励金额：${employeeRewardDetail.rewardAmount}</td>
					</tr>
					<tr>
						
					</tr>
					<%-- <tr>
						<td colspan="2">结算金额：${bizOrderTaskpackageSettlementVo.settlementAmount}</td>
					</tr>
					<tr>
						<td colspan="2" style="text-align: center;"><font size="5">
								${empty totalMoney ? 0.0 : totalMoney} 
								-${empty bizOrderTaskpackageSettlementVo.auxiliaryMaterialsAmount ? 0.0 : bizOrderTaskpackageSettlementVo.auxiliaryMaterialsAmount}
								- ${empty bizOrderTaskpackageSettlementVo.sandMaterialsAmount ? 0.0 : bizOrderTaskpackageSettlementVo.sandMaterialsAmount}-
								${empty bizOrderTaskpackageSettlementVo.delayAmerce ? 0.0 : bizOrderTaskpackageSettlementVo.delayAmerce}-
								${empty bizOrderTaskpackageSettlementVo.punishAmerce ? 0.0 : bizOrderTaskpackageSettlementVo.punishAmerce}-
								${empty bizOrderTaskpackageSettlementVo.qcPunishMoneyAmount ? 0.0 : bizOrderTaskpackageSettlementVo.qcPunishMoneyAmount}-
								${empty bizOrderTaskpackageSettlementVo.guaranteeMoneyAmount ? 0.0 : bizOrderTaskpackageSettlementVo.guaranteeMoneyAmount}
								= ${empty bizOrderTaskpackageSettlementVo.settlementAmount ? 0.0 : bizOrderTaskpackageSettlementVo.settlementAmount}
						</font></td>
					</tr> --%>
					<tr>
						<td colspan="3"><label>备注：
结算方式为“包工包料”：把人工费+材料费都发给工人组<br />
工人组结算金额 =（工料费结算总金额 - 工期扣款 - 管理处罚款 - 质检罚款  - 公司扣款- 辅料扣款 - 沙子水泥扣款 - 质保金扣除 +奖励金额）<br />
项目经理结算金额 = 0<br />
结算方式为“包工”时，把人工费发给工人组，把材料费发给项目经理。<br />
工人组结算金额 =（人工费结算总金额 - 工期扣款 - 管理处罚款 - 质检罚款  - 公司扣款款 - 质保金扣除 + 奖励金额）<br />
项目经理结算金额 =（辅料费结算总金额 - 辅料扣款 - 沙子水泥扣款 ）<br />
						</label></td>
					</tr>
				</table>
			</div>

			<div class="col-md-12 column">
				<div>
					<h3>
						<center>薪酬分配</center>
					</h3>
				</div>

				<table class="table table-striped table-bordered table-condensed">
					<thead>
						<tr>
							<th>工人姓名</th>
							<th>角色</th>
							<th>分配金额（元）</th>
							<th>备注</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${payments}" var="payment">
							<tr>
								<td>${payment.employeeName }</td>
								<td><c:if test="${payment.isLeader == '1'}">组长</c:if> <c:if
										test="${payment.isLeader == '0'}">组员</c:if></td>
								<td>${payment.paymentAmount}</td>
								<td>${payment.paymentRemarks}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		<div class="errorMessage">
			<a class="btn" href="javascript:" onclick="history.go(-1);">返回上一页</a>
		</div>
	</div>
</body>
</html>