<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>结算单详情</title>
<meta name="decorator" content="default" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script type="text/javascript">
	$(document).ready(function() {
		initMoney();
	});

	function initMoney() {
		//工人组结算总金额
		var workerGroupSettleAmount = 0;
		//项目经理材料结算总金额
		var pmMaterialsSettleAmount = 0;
		//工料费结算总金额
		var laborAuxiliaryMaterialsSettleAmount = 0;
		//人工费结算总金额
		var laborSettleAmount = 0;
		//辅料费结算总金额
		var auxiliaryMaterialsSettleAmount = 0;
		
		var length = ${fn:length(procedures)};
		for(var i=0;i<length;i++){
			laborAuxiliaryMaterialsSettleAmount = parseFloat(laborAuxiliaryMaterialsSettleAmount) + parseFloat(($("#laborAuxiliaryMaterialsSettleAmount"+i).val() == null || $("#laborAuxiliaryMaterialsSettleAmount"+i).val() == "")?0:$("#laborAuxiliaryMaterialsSettleAmount"+i).val());
			laborSettleAmount = parseFloat(laborSettleAmount) + parseFloat(($("#laborSettleAmount"+i).val() == null || $("#laborSettleAmount"+i).val() == "")?0:$("#laborSettleAmount"+i).val());
			auxiliaryMaterialsSettleAmount = parseFloat(auxiliaryMaterialsSettleAmount) + parseFloat(($("#auxiliaryMaterialsSettleAmount"+i).val() == null || $("#auxiliaryMaterialsSettleAmount"+i).val() == "")?0:$("#auxiliaryMaterialsSettleAmount"+i).val());
		}
		$("#laborAuxiliaryMaterialsSettleAmount").val(parseFloat(laborAuxiliaryMaterialsSettleAmount).toFixed(2));
		$("#laborSettleAmount").val(parseFloat(laborSettleAmount).toFixed(2));
		$("#auxiliaryMaterialsSettleAmount").val(parseFloat(auxiliaryMaterialsSettleAmount).toFixed(2));
		
		// 辅材金额
		var auxiliaryAmount = ("${bizOrderTaskpackageSettlementVo.auxiliaryMaterialsAmount}" == null || "${bizOrderTaskpackageSettlementVo.auxiliaryMaterialsAmount}" == "") ? 0
				: "${bizOrderTaskpackageSettlementVo.auxiliaryMaterialsAmount}";
	    
	    //沙子水泥金额
        var sandAmount = ("${bizOrderTaskpackageSettlementVo.sandMaterialsAmount}" == null || "${bizOrderTaskpackageSettlementVo.sandMaterialsAmount}" == "") ? 0
				: "${bizOrderTaskpackageSettlementVo.sandMaterialsAmount}";
	    
		// 延期扣款
		var delayAmerce = ("${bizOrderTaskpackageSettlementVo.delayAmerce}" == null || "${bizOrderTaskpackageSettlementVo.delayAmerce}" == "") ? 0
				: "${bizOrderTaskpackageSettlementVo.delayAmerce}";

		// 管理处罚金额
		var punishAmerce = ("${bizOrderTaskpackageSettlementVo.punishAmerce}" == null || "${bizOrderTaskpackageSettlementVo.punishAmerce}" == "") ? 0
				: "${bizOrderTaskpackageSettlementVo.punishAmerce}";

		// 质检罚款
		var qcAmount = ("${qcAmount}" == null || "${qcAmount}" == "") ? 0
				: "${qcAmount}";
				
	    //公司扣款
	    var companyDeductAmount = ($("#companyDeductAmount").val() == null || $("#companyDeductAmount").val() == "") ? 0 : $("#companyDeductAmount").val();

	    var settleStyle = $("#settleStyle").val();

		if(settleStyle == "1"){//包工包料
        	workerGroupSettleAmount = parseFloat(laborAuxiliaryMaterialsSettleAmount)-parseFloat(auxiliaryAmount) - parseFloat(sandAmount) -
			parseFloat(delayAmerce) - parseFloat(punishAmerce) - parseFloat(qcAmount) - parseFloat(companyDeductAmount);
        }else if(settleStyle == "2"){//包工
        	workerGroupSettleAmount = parseFloat(laborSettleAmount) - parseFloat(delayAmerce) - parseFloat(punishAmerce) - parseFloat(qcAmount) - parseFloat(companyDeductAmount);
        	pmMaterialsSettleAmount = parseFloat(auxiliaryMaterialsSettleAmount) - parseFloat(auxiliaryAmount) - parseFloat(sandAmount);
        }
	    
		// 质保金
		var guaranteeMoneyAmount = 0;
		if ('${bizOrderTaskpackageSettlementVo.gualityGuaranteeType}' == '3') {
			var guaranteeMoneyAmountTotal = "${bizOrderTaskpackageSettlementVo.guaranteeMoneyAmountTotal}";
			var qualityGuaranteeRate = "${bizOrderTaskpackageSettlementVo.qualityGuaranteeRate}";
			var guaranteeMoneyBalance = "${bizOrderTaskpackageSettlementVo.guaranteeMoneyBalance}";
			if (parseFloat(guaranteeMoneyBalance) >= 2000) { // 累计金额大于2000，质保金为0
				guaranteeMoneyAmount = 0;
			} else {
				var amount = (workerGroupSettleAmount * parseFloat(qualityGuaranteeRate))/100;
				if ((parseFloat(guaranteeMoneyBalance) + amount) > 2000) {
					guaranteeMoneyAmount = (amount
									- ((parseFloat(guaranteeMoneyBalance) + amount) - parseFloat(2000)));
				} else {
					guaranteeMoneyAmount = amount;
				}
			}
		}
		workerGroupSettleAmount = workerGroupSettleAmount - guaranteeMoneyAmount;
		$("#guaranteeMoneyAmount").val(guaranteeMoneyAmount.toFixed(2));

		var settlementAmount = parseFloat(workerGroupSettleAmount) + parseFloat(pmMaterialsSettleAmount);
		$("#settlementAmount").val(parseFloat(settlementAmount).toFixed(2));
		$("#workerGroupSettleAmount").val(parseFloat(workerGroupSettleAmount).toFixed(2));
		$("#pmMaterialsSettleAmount").val(parseFloat(pmMaterialsSettleAmount).toFixed(2));
		
		
	}

	function checkSettlementNumber(index, obj) {
		//先把非数字的都替换掉，除了数字和. 
		obj.value = obj.value.replace(/[^\d.]/g, "");
		//必须保证第一个为数字而不是. 
		obj.value = obj.value.replace(/^\./g, "");
		//保证.只出现一次，而不能出现两次以上 
		obj.value = obj.value.replace(".", "$#$").replace(/\./g, "").replace(
				"$#$", ".");
		//只能输入两个小数 
		obj.value = obj.value.replace(/^(\-)*(\d+)\.(\d\d).*$/, '$1$2.$3');

		var synthesizePrice = parseFloat(($("#synthesizePriceSpan" + index)
				.html() == null || $("#synthesizePriceSpan" + index).html() == "") ? 0
				: $("#synthesizePriceSpan" + index).html());
		var laborPrice = parseFloat(($("#laborPriceSpan" + index)
				.html() == null || $("#laborPriceSpan" + index).html() == "") ? 0
				: $("#laborPriceSpan" + index).html());
		var accessoriesPrice = parseFloat(($("#accessoriesPriceSpan" + index)
				.html() == null || $("#accessoriesPriceSpan" + index).html() == "") ? 0
				: $("#accessoriesPriceSpan" + index).html());
		
		var amount = parseFloat((obj.value == null || obj.value == "") ? 0
				: obj.value);
		var laborAuxiliaryMaterialsSettleAmount = (synthesizePrice * amount).toFixed(2);
		var laborSettleAmount = (laborPrice * amount).toFixed(2);
		var auxiliaryMaterialsSettleAmount = (accessoriesPrice * amount).toFixed(2);

		$("#laborAuxiliaryMaterialsSettleAmountSpan" + index).html(laborAuxiliaryMaterialsSettleAmount);
		$("#laborAuxiliaryMaterialsSettleAmount" + index).val(laborAuxiliaryMaterialsSettleAmount);
		
		$("#laborSettleAmountSpan" + index).html(laborSettleAmount);
		$("#laborSettleAmount" + index).val(laborSettleAmount);
		
		$("#auxiliaryMaterialsSettleAmountSpan" + index).html(auxiliaryMaterialsSettleAmount);
		$("#auxiliaryMaterialsSettleAmount" + index).val(auxiliaryMaterialsSettleAmount);

		initMoney();
	}

	function checkRecheckNumber(obj) {
		//先把非数字的都替换掉，除了数字和. 
		obj.value = obj.value.replace(/[^\d.]/g, "");
		//必须保证第一个为数字而不是. 
		obj.value = obj.value.replace(/^\./g, "");
		//保证.只出现一次，而不能出现两次以上 
		obj.value = obj.value.replace(".", "$#$").replace(/\./g, "").replace(
				"$#$", ".");
		//只能输入两个小数 
		obj.value = obj.value.replace(/^(\-)*(\d+)\.(\d\d).*$/, '$1$2.$3');
	}
	
	//验证文本框只能输入数字和小数点
	function check(e,mark) {
		if(e.value == $("#budgetNumber"+mark).val()){
		    var re = /^\d+(?=\.{0,1}\d+$|$)/;
		    if (e.value != "") { 
		        if (!re.test(e.value)) { 
		            alert("请输入正确的数字和小数!"); 
		            $("#budgetNumber"+mark).val(0.00); 
		            $("#total"+mark).val("0.00")//如果输入正则验证不通过，则恢复默认值
		            e.focus(); 
		        } 
		    } 
		}
	} 

	function submitForm(obj) {
		var length = ${fn:length(procedures)};
		var bool = false;
		for (var i = 0; i < length; i++) {
			var settlementNumber = $("#settlementNumber" + i).val();
			if (settlementNumber == null || settlementNumber == "") {
				alertx("任务包工序清单第" + (i + 1) + "行的结算工程量不能为空!");
				bool = true;
				break;
			}
            var oldsettlementNumber = $("#oldsettlementNumber" + i).val();
			var recheckNumber = $("#recheckNumber" + i).val();
			var oldrecheckNumber = $("#oldrecheckNumber" + i).val();
			var modifyNumberReason = $("#modifyNumberReason"+i).val();
			if(oldsettlementNumber != settlementNumber || oldrecheckNumber != recheckNumber ){
				if(modifyNumberReason ==null || modifyNumberReason == ""){
					alertx("任务包工序清单第" + (i + 1) + "行的更改工程量原因!");
					bool = true;
					break;
				}
			}
			var realNumber = $("#realNumber" + i).html();
			var count = 0;
			if (realNumber != null && realNumber != "") {
				if (parseFloat(settlementNumber).toFixed(2) == parseFloat(
						realNumber).toFixed(2)) {
					count = count + 1;
				}
			}
			if (recheckNumber != null && recheckNumber != "") {
				if (parseFloat(settlementNumber).toFixed(2) == parseFloat(
						recheckNumber).toFixed(2)) {
					count = count + 1;
				}
			}
			if (count == 0) {
				alertx("任务包工序清单第" + (i + 1) + "行的结算工程量必须与实际工程量或复核工程量中的一个数值相同!");
				bool = true;
				break;
			}
		}
		
		 var companyDeductAmount = parseFloat($("#companyDeductAmount").val());
		 if(companyDeductAmount != null && companyDeductAmount>0){
			 var companyDeductReason = $("#companyDeductReason").val();
			 if(companyDeductReason == null ||companyDeductReason　== ""){
				 alertx("公司扣款原因必须填写！");
				 bool = true;
			 }
		 }
		 
		 if (bool) {
				return;
			}

		top.$.jBox.confirm("您确认要保存吗？", "系统提示", function(v, h, f) {
			if (v == "ok") {
				$(obj).attr({
					"disabled" : "disabled"
				});
				loading('正在提交，请稍等...');
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
		<li class="active"><a href="javascript:void(0)">结算单修改</a></li>
		<li><a
			href="${ctx}/ordertaskpackagesettlement/bizOrderTaskpackageSettlementVo/settleOrderList">结算单列表</a></li>
	</ul>
	<div class="container">
		<div class="row clearfix">
			<div class="col-md-12 column">
				<div class="nav nav-tabs">
					<h2>
						<center>任务包结算单修改</center>
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
							结算单号：${bizOrderTaskpackageSettlementVo.settlementNo}</td>
						<td width="50%">
							项目名称：${bizOrderTaskpackageSettlementVo.customerMessage}-${bizOrderTaskpackageSettlementVo.customerName}
						</td>
					</tr>
					<tr>
						<td width="50%">
							订单编号：${bizOrderTaskpackageSettlementVo.orderNo}</td>
						<td width="50%">
							任务包名：${bizOrderTaskpackageSettlementVo.orderTaskpackageName}</td>
					</tr>
					<tr>
						<td width="50%">
							任务包号：${bizOrderTaskpackageSettlementVo.taskPackageNo}</td>
						<td width="50%">
							工人组长：${bizOrderTaskpackageSettlementVo.groupRealname}</td>
					</tr>
					<tr>
						<td width="50%">
							项目经理：${bizOrderTaskpackageSettlementVo.itemManager}</td>
						<td width="50%">
							户型/面积：${fns:getDictLabel(bizOrderTaskpackageSettlementVo.houseType, 'home_type', '')}/${bizOrderTaskpackageSettlementVo.coveredArea}
						</td>
					</tr>
					<tr>
						<td width="50%">设 计
							师：${bizOrderTaskpackageSettlementVo.designerName}</td>
						<td width="50%"></td>
					</tr>
				</table>
			</div>

			<form id="inputSubmit"
				action="${ctx}/ordertaskpackagesettlement/bizOrderTaskpackageSettlementVo/updateSettlement">
				<div class="col-md-12 column">
					<div>
						<h3>
							<center>任务包工序清单</center>
						</h3>
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
									<th>后台更改工程量原因</th>
									<th>复核说明</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${procedures}" var="procedure"
									varStatus="status">
									<tr>
										<td>${procedure.procedureName}</td>
										<td>${procedure.measurementUnitLabel}</td>
										<td>${procedure.budgetNumber}</td>
										<td><span id="realNumber${status.index}">${procedure.realNumber}</span>
										</td>
										<td>
										   <input type="hidden" id="oldrecheckNumber${status.index}" value="${procedure.recheckNumber}" /> 
										   <input type="text" id="recheckNumber${status.index}" name="orderTaskProcedure[${status.index}].recheckNumber" value="${procedure.recheckNumber}" style="width: 100px; color: blue" onblur="check(this,${status.index })" onkeyup="this.value=this.value.replace(/[^0-9.]/g,'')" />
										</td>
										<td>
										  <input type="hidden" name="orderTaskProcedure[${status.index}].id" value="${procedure.id}" /> 
										  <input type="hidden" id="oldsettlementNumber${status.index}" value="${procedure.settlementNumber}" /> 
										  <input type="text" id="settlementNumber${status.index}" name="orderTaskProcedure[${status.index}].settlementNumber" value="${procedure.settlementNumber}" style="width: 100px; color: red" onblur="check(this,${status.index })" onkeyup="this.value=this.value.replace(/[^0-9.]/g,'')" oninput="checkSettlementNumber(${status.index},this)" />
										</td>
										<td>
										  <span id="synthesizePriceSpan${status.index}">${procedure.synthesizePrice}</span>
										</td>
										
                                        <td><span id="laborPriceSpan${status.index}">${procedure.laborPrice}</span></td>
								        <td><span id="accessoriesPriceSpan${status.index}">${procedure.accessoriesPrice}</span></td>
                                         
										<%-- <td><input type="hidden" class="amount" id="amount${status.index}" name="orderTaskProcedure[${status.index}].total" value="${procedure.total}" /> 
										<span id="amountSpan${status.index}">${procedure.total}</span>
										</td> --%>
										
										<td>
										  <input type="hidden" class="amount" id="laborAuxiliaryMaterialsSettleAmount${status.index}" name="orderTaskProcedure[${status.index}].laborAuxiliaryMaterialsSettleAmount" value="${procedure.laborAuxiliaryMaterialsSettleAmount}" /> 
										  <span id="laborAuxiliaryMaterialsSettleAmountSpan${status.index}">${procedure.laborAuxiliaryMaterialsSettleAmount}</span>
										</td>
								        <td>
								          <input type="hidden" class="amount" id="laborSettleAmount${status.index}" name="orderTaskProcedure[${status.index}].laborSettleAmount" value="${procedure.laborSettleAmount}" /> 
										  <span id="laborSettleAmountSpan${status.index}">${procedure.laborSettleAmount}</span>
								        </td>
								        <td>
								          <input type="hidden" class="amount" id="auxiliaryMaterialsSettleAmount${status.index}" name="orderTaskProcedure[${status.index}].auxiliaryMaterialsSettleAmount" value="${procedure.auxiliaryMaterialsSettleAmount}" /> 
										  <span id="auxiliaryMaterialsSettleAmountSpan${status.index}">${procedure.auxiliaryMaterialsSettleAmount}</span>
								        </td>
										
										<td><input type="text"
											id="modifyNumberReason${status.index}"
											name="orderTaskProcedure[${status.index}].modifyNumberReason"
											value="${procedure.modifyNumberReason}" /></td>
										<td>${procedure.recheckRemarks}</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>

					</div>
					<%-- <div>
						<h3>
							<center>结算汇总</center>
						</h3>
						<input type="hidden" name="id"
							value="${bizOrderTaskpackageSettlementVo.id}" /> <input
							type="hidden" name="guaranteeMoneyAmountTotal"
							value="${bizOrderTaskpackageSettlementVo.guaranteeMoneyAmountTotal}" />
						结算总金额：<input type="text" id="allAmount" readonly="readonly">&nbsp;
						质保金：<input type="text" id="guaranteeMoneyAmount"
							readonly="readonly" name="guaranteeMoneyAmount">&nbsp;
						质检罚款：<input type="text" name="qcPunishMoneyAmount"
							readonly="readonly" value="${qcAmount}"> <br> 辅料扣款：<input
							type="text" name="auxiliaryMaterialsAmount" readonly="readonly"
							value="${bizOrderTaskpackageSettlementVo.auxiliaryMaterialsAmount}">
						沙子水泥扣款：<input type="text" name="sandMaterialsAmount"
							readonly="readonly"
							value="${bizOrderTaskpackageSettlementVo.sandMaterialsAmount}">
						<br> 公司扣款：<input type="text" id="companyDeductAmount"
							name="companyDeductAmount"
							value="${bizOrderTaskpackageSettlementVo.companyDeductAmount}"
							onblur="initMoney()"> 公司扣款原因：<input type="text"
							id="companyDeductReason" name="companyDeductReason"
							value="${bizOrderTaskpackageSettlementVo.companyDeductReason}">
						<br> 结算金额：<input type="text" id="settlementAmount"
							name="settlementAmount" readonly="readonly">
					</div>
				</div> --%>
				<div class="col-md-12 column">
				<div>
					<h3>
						<center>结算汇总</center>
					</h3>
				</div>
				<input type="hidden" name="id" value="${bizOrderTaskpackageSettlementVo.id}" />
				<input type="hidden" name="guaranteeMoneyAmountTotal" value="${bizOrderTaskpackageSettlementVo.guaranteeMoneyAmountTotal}" />
				<input type="hidden" id="settleStyle" name="settleStyle" value="${bizOrderTaskpackageSettlementVo.settleStyle}"/>
				<table class="table table-striped table-bordered table-condensed" style="width:1200px">
				    <tr>
				      <td colspan="3">结算方式：${fns:getDictLabel(orderTaskpackageVo.settleStyle, 'settle_style', '')}</td>
				    </tr>
				    <tr>
						<td>结算总金额：<input type="text" id="settlementAmount" name="settlementAmount" readonly="readonly" value="${bizOrderTaskpackageSettlementVo.settlementAmount}" /></td>
						<td>工人组结算金额：<input type="text" id="workerGroupSettleAmount" name="workerGroupSettleAmount" readonly="readonly" value="${bizOrderTaskpackageSettlementVo.workerGroupSettleAmount}" /></td>
						<td>项目经理材料结算金额：<input type="text" id="pmMaterialsSettleAmount" name="pmMaterialsSettleAmount" readonly="readonly" value="${bizOrderTaskpackageSettlementVo.pmMaterialsSettleAmount}" /></td>
					</tr>
					<tr>
						<td>工料费结算总金额：<input type="text" id="laborAuxiliaryMaterialsSettleAmount" name="laborAuxiliaryMaterialsSettleAmount" readonly="readonly" value="${bizOrderTaskpackageSettlementVo.laborAuxiliaryMaterialsSettleAmount}" /></td>
						<td>人工费结算总金额：<input type="text" id="laborSettleAmount" name="laborSettleAmount" readonly="readonly" value="${bizOrderTaskpackageSettlementVo.laborSettleAmount}" /></td>
						<td>辅料费结算总金额：<input type="text" id="auxiliaryMaterialsSettleAmount" name="auxiliaryMaterialsSettleAmount" readonly="readonly" value="${bizOrderTaskpackageSettlementVo.auxiliaryMaterialsSettleAmount}" /></td>
					</tr>
					<tr>
						<td>
						   工期扣款：<input type="text"  readonly="readonly" value="${empty bizOrderTaskpackageSettlementVo.delayAmerce ? 0.0 : bizOrderTaskpackageSettlementVo.delayAmerce}" />
						 <input type="hidden" name="delayAmerce" value="${bizOrderTaskpackageSettlementVo.delayAmerce}" />
						</td>
					    <td>
					               管理扣款：<input type="text"  readonly="readonly" value="${empty bizOrderTaskpackageSettlementVo.punishAmerce ? 0.0 : bizOrderTaskpackageSettlementVo.punishAmerce}" />
					      <input type="hidden" name=punishAmerce value="${bizOrderTaskpackageSettlementVo.punishAmerce}" />
					    </td>
					    <td>
					               质检罚款：<input type="text"  readonly="readonly" value="${empty bizOrderTaskpackageSettlementVo.qcPunishMoneyAmount ? 0.0 : bizOrderTaskpackageSettlementVo.qcPunishMoneyAmount}" />
					      <input type="hidden" name="qcPunishMoneyAmount" value="${bizOrderTaskpackageSettlementVo.qcPunishMoneyAmount}" />
					    </td>
					</tr>
					<tr>
					  <td>辅料扣款：<input type="text" name="auxiliaryMaterialsAmount" readonly="readonly" value="${bizOrderTaskpackageSettlementVo.auxiliaryMaterialsAmount}"></td>
					  <td>沙子水泥扣款：<input type="text" name="auxiliaryMaterialsAmount" readonly="readonly" value="${bizOrderTaskpackageSettlementVo.sandMaterialsAmount}"></td>
					  <td>质保金：<input type="text" id="guaranteeMoneyAmount" readonly="readonly" name="guaranteeMoneyAmount" value="${bizOrderTaskpackageSettlementVo.guaranteeMoneyAmount}"/></td>
					</tr>
					<tr>
						<td colspan="3">奖励金额：<input type="text" name="auxiliaryMaterialsAmount" readonly="readonly" value="${employeeRewardDetail.rewardAmount}"></td>
					</tr>
					<tr>
						<td>
						  公司扣款：<input type="text" id="companyDeductAmount" name="companyDeductAmount" value="${bizOrderTaskpackageSettlementVo.companyDeductAmount}" onblur="initMoney()"> 
						</td>
						<td colspan="2">
						 公司扣款原因：<input type="text" id="companyDeductReason" name="companyDeductReason" value="${bizOrderTaskpackageSettlementVo.companyDeductReason}">
						</td>
					</tr>
					
					<tr>
						<td colspan="3"><label>备注：
结算方式为“包工包料”：把人工费+材料费都发给工人组<br />
工人组结算金额 =（工料费结算总金额 - 工期扣款 - 管理处罚款 - 质检罚款  - 公司扣款- 辅料扣款 - 沙子水泥扣款 - 质保金扣除 +奖励金额）<br />
项目经理结算金额 = 0<br />
结算方式为“包工”时，把人工费发给工人组，把材料费发给项目经理。<br />
工人组结算金额 =（人工费结算总金额 - 工期扣款 - 管理处罚款 - 质检罚款  - 公司扣款款 - 质保金扣除 + 奖励金额）<br />
项目经理结算金额 =（辅料费结算总金额 - 辅料扣款 - 沙子水泥扣款 ）
						</label></td>
					</tr>
				</table>
			</div>
			</form>

			<div class="form-actions" style="text-align: center">
				<shiro:hasPermission
					name="ordertaskpackagesettlement:bizOrderTaskpackageSettlementVo:edit">
					<input class="btn btn-primary" type="button" value="保 存"
						onclick="submitForm(this)" />
				</shiro:hasPermission>
				&nbsp; <input class="btn" type="button" value="返 回"
					onclick="history.go(-1)" />
			</div>
		</div>
	</div>
</body>
</html>