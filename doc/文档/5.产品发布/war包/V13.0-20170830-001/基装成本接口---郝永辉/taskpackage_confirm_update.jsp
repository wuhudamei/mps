<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1,maximum-scale=1, user-scalable=no">
<meta content="yes" name="apple-mobile-web-app-capable">
<meta content="black" name="apple-mobile-web-app-status-bar-style">
<meta content="telephone=no" name="format-detection">
<meta content="email=no" name="format-detection">
<title>确认验收</title>
<link rel="stylesheet" type="text/css"
	href="${ctxStatic}/mobile/modules/Manager/css/reset.css" />
<link rel="stylesheet" type="text/css"
	href="${ctxStatic}/mobile/modules/Manager/css/lib/lCalendar.css" />
<link rel="stylesheet" type="text/css"
	href="${ctxStatic}/mobile/modules/Manager/css/globalUtil.css" />
<link rel="stylesheet" type="text/css"
	href="${ctxStatic}/mobile/modules/Manager/css/header.css" />
<link rel="stylesheet" type="text/css"
	href="${ctxStatic}/mobile/modules/Manager/css/common.css" />
<link rel="stylesheet" type="text/css"
	href="${ctxStatic}/mobile/modules/Manager/css/auxiliary_choose1.css" />
<link rel="stylesheet" type="text/css"
	href="${ctxStatic}/mobile/modules/Manager/css/check_confirm.css" />
<link rel="stylesheet" type="text/css"
	href="${ctxStatic}/mobile/modules/Manager/css/metri/metriGet.css" />
<link rel="stylesheet" type="text/css"
	href="${ctxStatic}/mobile/modules/Manager/css/metri/sandGet.css" />
<link rel="stylesheet" type="text/css"
	href="${ctxStatic}/mobile/modules/Manager/css/waitMe.css">
</head>
<body>
	<div class="g-check" id="aboveId">
		<header>
			<a class="back_btn" href="${ctx}/app/manager/taskBudgetManage"></a>
			<h2 class="title">确认验收</h2>
		</header>
		<!-- /header -->
		<form id="submitForm">
			<input type="hidden" value="${orderTaskpackageVo.id}"
				name="orderTaskpackageId" /> <input type="hidden"
				value="${orderTaskpackageVo.orderId}" name="orderId" /> <input
				type="hidden" value="${taskSettlement.id}" name="id" /> <input
				type="hidden" value="${taskSettlement.status}" name="status" /> <input
				type="hidden" value="${taskSettlement.isNeedRecheck}"
				name="isNeedRecheck" />
			<section class="check_section padtop8">
				<h3 class="dot">基本信息</h3>
				<ul class="lists shadow">
					<li class="line_hgt6 clearfix"><span class="span_left">顾客信息：</span>
						<p class="p_right">${orderTaskpackageVo.customerMessage}-${orderTaskpackageVo.customerName}</p>
					</li>
					<li class="line_hgt6 clearfix"><span class="span_left">任务包名称：</span>
						<p class="p_right">${orderTaskpackageVo.packageName}</p></li>
					<li class="line_hgt6 clearfix">
					<span class="span_left">任务包状态：</span>
					<p class="p_right">${fns:getDictLabel(orderTaskpackageVo.packageStateId, 'taskpackage_status', '')}</p>
				    </li>
					<li class="line_hgt6 clearfix"><span class="span_left">工人组长：</span>
						<p class="p_right">${orderTaskpackageVo.realName}-${orderTaskpackageVo.phone}</p>
					</li>
					<li class="line_hgt6 clearfix"><span class="span_left">户
							型：</span>
						<p class="p_right">
							<c:choose>
								<c:when test="${orderTaskpackageVo.houseType == '1'}">一居室</c:when>
								<c:when test="${orderTaskpackageVo.houseType == '2'}">二居室</c:when>
								<c:when test="${orderTaskpackageVo.houseType == '3'}">三居室</c:when>
								<c:when test="${orderTaskpackageVo.houseType == '4'}">四居室</c:when>
								<c:when test="${orderTaskpackageVo.houseType == '5'}">五居室</c:when>
								<c:otherwise>其它居室</c:otherwise>
							</c:choose>
						</p></li>
					<li class="line_hgt6 clearfix"><span class="span_left">建筑面积：</span>
						<p class="p_right">
							<c:if test="${not empty orderTaskpackageVo.coveredArea}">${orderTaskpackageVo.coveredArea}平米</c:if>
						</p></li>
				</ul>
				<h3 class="dot">项目评定</h3>
				<div class="box-bottom">
					<div class="mask_top undis"></div>
					<ul class="lists shadow">
						<li class="line_hgt6 clearfix"><span class="span_left">验收日期：</span>
							<p class="p_right">
								<input id="txtBeginDate" class="date" type="text"
									name="checkedDate"
									value="<fmt:formatDate value='${taskSettlement.checkDate}' pattern='yyyy-MM-dd'/>"
									placeholder="请输入日期" data-lcalendar="2000-01-01,2000-01-01" />
							</p></li>
						<li class="line_hgt6 clearfix"><span class="span_left">质量检验：</span>
							<p class="p_right">
								<span class="radio_span marrgt1"> <input type="radio"
									id="yes" name="isQualified" value="1"> <label
									data-name="quality" for="yes"
									<c:if test="${taskSettlement.isQualified eq '1'}"> class="checked"</c:if>>合格</label>
								</span> <span class="radio_span"> <input type="radio" id="no"
									name="isQualified" value="0"> <label
									data-name="quality" for="no" id="qualifiedNo"
									<c:if test="${taskSettlement.isQualified eq '0'}"> class="checked"</c:if>>不合格</label>
								</span>
							</p></li>
						<li class="line_hgt6 clearfix"><span class="span_left">实际工期：</span>
							<p class="p_right">
								<span class="radio_span marrgt04"> <input type="radio"
									id="on" name="isDelay" value="0"> <label
									data-name="ontime" for="on" id="delayYes"
									<c:if test="${taskSettlement.isDelay eq '0'}"> class="checked"</c:if>>按时完成</label>
								</span> <span class="radio_span"> <input type="hidden"
									id="delayAmerce" name="delayAmerce"
									value="${taskSettlement.delayAmerce}" /> <input type="radio"
									id="out" name="isDelay" value="1"> <label
									data-name="ontime" for="out" id="delayNo"
									<c:if test="${taskSettlement.isDelay eq '1'}"> class="checked"</c:if>>延期<input
										class="days wid100" type="text" name="delayDays"
										id="delayDays" value="${taskSettlement.delayDays}"
										oninput="this.value=this.value.replace(/[^\d\.]/g,'');initMoney();">天
								</label>
								</span>
							</p></li>
						<li class="line_hgt6 clearfix"><span class="span_left">管理处罚：</span>
							<p class="p_right">
								<span class="radio_span blk"> <input type="radio"
									id="one" name="isManagePunish" value="0"> <label
									data-name="punish" for="one" id="punishYes"
									<c:if test="${taskSettlement.isManagePunish eq '0'}"> class="checked"</c:if>>无</label>
								</span> <span class="radio_span"> <input type="radio" id="two"
									name="isManagePunish" value="1"> <label
									data-name="punish" for="two" id="punishNo"
									<c:if test="${taskSettlement.isManagePunish eq '1'}"> class="checked"</c:if>>有<input
										class="days wid100 margin0" type="text" maxlength="10"
										id="punishAmerce" name="punishAmerce"
										value="${taskSettlement.punishAmerce}"
										oninput="this.value=this.value.replace(/[^\d\.]/g,'');initMoney()">元
								</label> <input class="days wid284 font24 col_b4" type="text"
									id="punishReason" name="punishReason"
									value="${taskSettlement.punishReason}"
									placeholder="若有处罚，请说明处罚原因" />
								</span>
							</p></li>
						<li class="line_hgt6 clearfix"><span class="span_left">质检罚款：</span>
							<p class="p_right">
								${qcAmount}元<input type="hidden" id="qcAmount"
									name="qcPunishMoneyAmount" value="${qcAmount}" />
							</p>
						</li>
						<li class="line_hgt6 clearfix">
						<span class="span_left">公司扣款：</span>
						<p class="p_right">
						  <c:if test="${empty taskSettlement.companyDeductAmount}">
						  -0.0元
						  </c:if>
						  <c:if test="${!empty taskSettlement.companyDeductAmount}">
						  -${taskSettlement.companyDeductAmount}元
						  </c:if>
						  <input type="hidden" id="companyDeductAmount"
									name="companyDeductAmount" value="${taskSettlement.companyDeductAmount}" />
						</p>
					</li>
					<c:if test="${taskSettlement.companyDeductAmount != null}">
					<li class="line_hgt6 clearfix">
						<span class="span_left">公司扣款原因：</span>
						<p class="p_right" style="overflow:hidden;">
							${taskSettlement.companyDeductReason}
						</p>
					</li>
					</c:if>
					</ul>
				</div>

				<c:if test="${fn:length(evalStoreList) > 0}">
					<h3 class="dot">评价工人</h3>
					<div class="box-bottom">
						<ul class="lists shadow font0">
							<c:forEach items="${evalStoreList}" var="evalStore"
								varStatus="status">
								<li class="mar_btm24 clearfix a"><input type="hidden"
									id="selectCount${status.index}"
									value="${evalStore.selectCount}" /> <span
									class="col_3 font28 mar_ght32 left-small">${evalStore.indexName}</span>
									<p class="flow star-p" id="starP${status.index}">
										<span class="star1"></span>
										<span class="star1"></span>
										<span class="star1"></span>
										<span class="star1"></span>
										<span class="star1"></span>
									</p>
							</c:forEach>
							<div class="advise-area font0 col_9">
								<textarea class="advise" readonly name="evalFeedback"
									placeholder="请输入您的反馈意见">${evalFeedback}</textarea>
							</div>
						</ul>
					</div>
				</c:if>

				<h3 class="dot pos_ret">
					工程清单 <a class="showAll" href="javascript:void(0)">展开</a>
				</h3>
				<p class="caution">备注：实际工程量超过预算3%，需要质检员复核</p>
				<p class="caution indent mar_btm3">质检员复核过之后，不允许再修改工程量</p>
				<ul class="lists shadow project_bars">
				    
				    <div class="lists font0 shadow">
						<span class="font28 col6"> <span class="col6">工料费结算总金额：</span>
							<input id="laborAuxiliaryMaterialsSettleAmount"
							name="laborAuxiliaryMaterialsSettleAmount" value=""
							class="days wid200 text_grey" readonly type="text">元
						</span> <br /> <span class="font28 col6"> <span class="col6">人工费结算总金额：</span>
							<input id="laborSettleAmount" name="laborSettleAmount" value=""
							class="days wid200 text_grey" readonly type="text">元
						</span> <br /> <span class="font28 col6"> <span class="col6">辅料费结算总金额：</span>
							<input id="auxiliaryMaterialsSettleAmount"
							name="auxiliaryMaterialsSettleAmount" value=""
							class="days wid200 text_grey" readonly type="text">元
						</span>
					</div>
				    
					<c:forEach items="${taskProcedureList}" var="taskProcedure"
						varStatus="status">
						<li class="project_list salary btm_border mar_top20 undis">
							<ul>
								<p class="line_hgt6 font28 col3 clearfix">${status.index+1}、${taskProcedure.procedureName}-
									单位：${taskProcedure.measurementUnitLabel}</p>
								<li class="line_hgt6 clearfix"><span
									class="pro_span wid250 font0"> <span class="col6 font28">预算工程量：</span>
										<input type="hidden" value="${taskProcedure.id}"
										name="orderTaskProcedure[${status.index}].id" /> <span
										class="col3 font28">${taskProcedure.budgetNumber}</span> <input
										type="hidden" id="budgetNumber${status.index}"
										value="${taskProcedure.budgetNumber}" />
								</span> <span class="pro_span font0"> <span class="col6 font28">实际工程量：<input
											class="days wid100 <c:if test="${taskSettlement.isNeedRecheck eq '1'}"> text_grey</c:if>"
											type="text" id="realNumber${status.index}"
											name="orderTaskProcedure[${status.index}].realNumber"
											value="${taskProcedure.realNumber}"
											oninput="this.value=this.value.replace(/[^\d\.]/g,'');getTotal(${status.index},this)"
											<c:if test="${taskSettlement.isNeedRecheck eq '1'}"> readonly="true"</c:if>></span>
								</span></li>
								<li class="line_hgt6 clearfix"><span class="pro_span font0">
										<span class="col6 font28">工料费单价：</span> <span
										class="col3 font28">${taskProcedure.synthesizePrice}元</span> <input
										type="hidden" id="synthesizePrice${status.index}"
										value="${taskProcedure.synthesizePrice}" /> <input
										type="hidden" id="procedureName${status.index}"
										value="${taskProcedure.procedureName}" />
								</span> <span class="pro_span font0"> <span class="col6 font28">
								                    工料费金额：</span> <span class="col3 font28" id="laborAuxiliaryMaterialsSettleAmount${status.index}">${taskProcedure.laborAuxiliaryMaterialsSettleAmount}元</span>
										<input type="hidden" id="laborAuxiliaryMaterialsSettleAmountPrice${status.index}"
										name="orderTaskProcedure[${status.index}].laborAuxiliaryMaterialsSettleAmount"
										value="${taskProcedure.laborAuxiliaryMaterialsSettleAmount}" />
								</span></li>
								
								<li class="line_hgt6 clearfix"><span class="pro_span font0">
										<span class="col6 font28">人工费单价：</span> <span
										class="col3 font28">${taskProcedure.laborPrice}元</span> <input
										type="hidden" id="laborPrice${status.index}"
										value="${taskProcedure.laborPrice}" /> <input type="hidden"
										id="procedureName${status.index}"
										value="${taskProcedure.procedureName}" />
								</span> <span class="pro_span font0"> <span class="col6 font28">人工费金额：</span>
										<span class="col3 font28"
										id="laborSettleAmount${status.index}">${taskProcedure.laborSettleAmount}元</span>
										<input type="hidden"
										id="laborSettleAmountPrice${status.index}"
										name="orderTaskProcedure[${status.index}].laborSettleAmount"
										value="${taskProcedure.laborSettleAmount}" />
								</span></li>

								<li class="line_hgt6 clearfix"><span class="pro_span font0">
										<span class="col6 font28">辅料费单价：</span> <span
										class="col3 font28">${taskProcedure.accessoriesPrice}元</span>
										<input type="hidden" id="accessoriesPrice${status.index}"
										value="${taskProcedure.accessoriesPrice}" /> <input
										type="hidden" id="procedureName${status.index}"
										value="${taskProcedure.procedureName}" />
								</span> <span class="pro_span font0"> <span class="col6 font28">辅料费金额：</span>
										<span class="col3 font28"
										id="auxiliaryMaterialsSettleAmount${status.index}">${taskProcedure.auxiliaryMaterialsSettleAmount}元</span>
										<input type="hidden"
										id="auxiliaryMaterialsSettleAmountPrice${status.index}"
										name="orderTaskProcedure[${status.index}].auxiliaryMaterialsSettleAmount"
										value="${taskProcedure.auxiliaryMaterialsSettleAmount}" />
								</span></li>
								
								<li class="line_hgt6 mar_btm20 clearfix"><span
									class="span_left col6">备 注 信 息 ：</span>
									<p class="">
										<input
											class="days wid370 font28 <c:if test="${taskSettlement.isNeedRecheck eq '1'}"> text_grey</c:if>"
											type="text" id="remark${status.index}"
											name="orderTaskProcedure[${status.index}].remarks"
											value="${taskProcedure.remarks}"
											<c:if test="${taskSettlement.isNeedRecheck eq '1'}"> readonly="true"</c:if> />
									</p></li>
								<li class="line_hgt6 mar_btm20 clearfix"
									<c:if test="${taskSettlement.isNeedRecheck ne '1'}"> style="display:none"</c:if>>
									<span class="span_left col6">复核工程量：</span> <input type="hidden"
									id="settlementNumber${status.index}"
									name="orderTaskProcedure[${status.index}].settlementNumber"
									value="${taskProcedure.settlementNumber}" />
									<p class="">
										<input
											class="days wid100 font28 <c:if test="${taskSettlement.isNeedRecheck eq '1'}"> text_grey</c:if>"
											type="text" id="recheckNumber${status.index}"
											name="orderTaskProcedure[${status.index}].recheckNumber"
											value="${taskProcedure.recheckNumber}"
											<c:if test="${taskSettlement.isNeedRecheck eq '1'}"> readonly="true"</c:if> />
									</p>
								</li>
								<li class="line_hgt6 mar_btm20 clearfix"
									<c:if test="${taskSettlement.isNeedRecheck ne '1'}"> style="display:none"</c:if>>
									<span class="span_left col6">复 核 说 明 ：</span>
									<p class="">
										<input
											class="days wid370 font28 <c:if test="${taskSettlement.isNeedRecheck eq '1'}"> text_grey</c:if>"
											type="text"
											<c:if test="${taskSettlement.isNeedRecheck eq '1'}"> readonly="true"</c:if> />
									</p>
								</li>
							</ul>
						</li>
					</c:forEach>
				</ul>
				<a class="sub_btn blue_bg undis" id="gongchengCheck">提交工程量</a>

				<div class="box-bottom mar_top100 pad_btm2">
					<div class="mask_btm undis" id="mengchengCheck"></div>

					<h3 class="dot">辅料核算</h3>
					<div class="lists font0 shadow">
						<span class="font28 col6"> <span class="col6">辅料金额：</span>
						   <input type="hidden" id="auxiliaryMaterialsDeductAmountSupplierPrice" name="auxiliaryMaterialsDeductAmountSupplierPrice" />
					       <input type="hidden" id="auxiliaryMaterialsDeductAmountWangzhenPrice" name="auxiliaryMaterialsDeductAmountWangzhenPrice" />
							<input class="days wid200 text_grey" type="text" readonly="true"
							id="auxiliaryAmount" name="auxiliaryMaterialsAmount"
							value="${taskSettlement.auxiliaryMaterialsAmount}">元
						</span> <a class="auxi" id="showAuxiliary">获取辅料</a>
					</div>
					<c:if test="${checkTaskPack=='1'}">
						<input type="hidden" value="1" id="checkSand" />
						<h3 class="dot">沙子水泥核算</h3>
						<div class="lists font0 shadow">
							<span class="font28 col6"> <span class="col6">沙子水泥材料金额：</span>
							    <input type="hidden" id="sandCementDeductAmountSupplierPrice" name="sandCementDeductAmountSupplierPrice" />
					            <input type="hidden" id="sandCementDeductAmountWangzhenPrice" name="sandCementDeductAmountWangzhenPrice" />
								<input style="width: 1.3rem;" class="days wid200 text_grey"
								type="text" readonly="true" id="sandAmount"
								name="sandCementAmount"
								value="${taskSettlement.sandCementAmount}" />元
							</span> <a class="auxi" href="javascript:void(0)" id="showSand">获取沙子水泥</a>
						</div>
					</c:if>
					<h3 class="dot">上缴质保金</h3>
					<div class="lists font0 shadow">
						<span class="font0 col6 marrgt09"> <span
							class="font28 col6">工人姓名：</span> <span class="font28 col3">${orderTaskpackageVo.realName}</span>
							<input type="hidden" value="${orderTaskpackageVo.groupId}"
							name="groupId" /> <input type="hidden"
							value="${orderTaskpackageVo.empGroupid}" name="empGroupid" /> <input
							type="hidden" value="${orderTaskpackageVo.taskPackageTemplatId}"
							name="taskPackageTemplatId" /> <input type="hidden"
							value="${orderTaskpackageVo.guaranteeMoneyAmountTotal}"
							name="guaranteeMoneyAmountTotal" /> <input type="hidden"
							value="${orderTaskpackageVo.gualityGuaranteeType}"
							name="gualityGuaranteeType" />
						</span> <span class="font0 col6"> <span class="font28 col6">上缴金额：</span>
							<span class="font28 col6"><input
								class="days wid126 text_grey" readonly="true" type="text"
								id="guaranteeMoneyAmount" name="guaranteeMoneyAmount">元</span>
						</span>
						<p class="col9 font24 mar_top20">备注：一次上缴${orderTaskpackageVo.qualityGuaranteeRate}%的质保金，2000元封顶</p>
					</div>

					<h3 class="dot">奖励金额</h3>
					<div class="lists font0 shadow">
						<span class="font28 col6"> <span class="col6">奖励金额：</span>
							<input class="days wid200 text_grey" type="text" readonly="true"
							<c:if test="${empty rewardAmount}">placeholder="待核算"</c:if>
							<c:if test="${not empty rewardAmount}">value="${rewardAmount}"</c:if>>元
						</span>
					</div>

					<h3 class="dot">结算金额</h3>
					<div class="lists font0 shadow">
						<span class="font28 col6">
					<span class="col6">结算方式：${fns:getDictLabel(orderTaskpackageVo.settleStyle, 'settle_style', '')}</span>
					 <input type="hidden" id="settleStyle" name="settleStyle" value="${orderTaskpackageVo.settleStyle}" />
				</span>
				<br />
				<span class="font28 col6">
					<span class="col6">结算总金额：</span>
					<input class="days wid200 text_grey" type="text" readonly="true" id="settlementAmount" name="settlementAmount">元
				</span>
				<br />
				<span class="font28 col6">
					<span class="col6">工人组结算金额：</span>
					<input class="days wid200 text_grey" type="text" readonly="true" id="workerGroupSettleAmount" name="workerGroupSettleAmount">元
				</span>
				<br />
				<span class="font28 col6">
					<span class="col6">项目经理材料结算金额：</span>
					<input class="days wid200 text_grey" type="text" readonly="true" id="pmMaterialsSettleAmount" name="pmMaterialsSettleAmount">元
				</span>
						<!-- <p class="col9 font24 mar_top20 line_hgt4">备注：结算金额=（结算总金额-辅材-工期扣款-管理扣款-质检罚款-质保金）</p> -->
					</div>
					<h3 class="dot pos_ret">分配薪酬</h3>
					<ul class="lists shadow">
						<c:forEach items="${empList}" var="emp" varStatus="status">
							<li class="salary mar_top20 btm_border">
								<ul>
									<li class="line_hgt6 clearfix"><span
										class="pro_span font0"> <span class="col6 font28">工人姓名：</span>
											<span class="col3 font28" id="realName${status.index}">${emp.realName}</span>
											<input type="hidden" value="${emp.settlementDetailId}"
											name="settlementDetail[${status.index}].id" />
									</span> <span class="font28 col6"> <span class="col6">分配金额：</span>
											<input class="days wid126 font28" type="text"
											id="paymentAmount${status.index}"
											name="settlementDetail[${status.index}].paymentAmount"
											oninput="this.value=this.value.replace(/[^\d\.]/g,'')"
											value="${emp.paymentAmount}">元
									</span></li>
									<li class="line_hgt6 mar_btm20 clearfix"><span
										class="span_left col6">备 注 信 息 ：</span>
										<p class="">
											<input class="days wid370 font28" type="text"
												name="settlementDetail[${status.index}].remarks"
												value="${emp.remarks}" />
										</p></li>
								</ul>
							</li>
						</c:forEach>
					</ul>
				</div>
			</section>
			<div class="footer_mask">
				<footer class="footer1">
					<a class="watch_btn_a"><span class="btn watch_btn font28">查看施工图</span></a>
					<a class="check_btn_a"><span class="btn check_btn font28">确认验收</span></a>
				</footer>
				<input type="hidden" value="0" id="checkSub" />
			</div>
			<!-- 沙子水泥用料弹框 -->
			<section class="g-auxiliary_choose undis" id="sandModel">
				<!-- <h3 class="mask_tit">辅料用量</h3> -->
				<div class="show_sec">
					<ul>
						<c:forEach items="${sandList}" var="auxiliary" varStatus="status">
							<li class="shadow">
								<div class="img_container">
									<img src="${root}${auxiliary.picUrl}" alt="goods"
										onerror="nofind()">
								</div>
								<p class="brand">${auxiliary.auxiliaryMaterialsName}</p> <input
								type="hidden" id="sandMaterialsName${status.index}"
								value="${auxiliary.auxiliaryMaterialsName}"> <input
								type="hidden" value="${auxiliary.orderAuxiMateId}"
								name="sandMaterials[${status.index}].id" />
								<p class="format">
									规格：${auxiliary.specifications} <span class="price">送货数量：${auxiliary.totalAuxiMateCount}${auxiliary.measurementUnitLabel}</span>
									<input type="hidden" id="sandtotalAuxiMateCount${status.index}"
										value="${auxiliary.totalAuxiMateCount}"> <input
										type="hidden" value="${auxiliary.auxiliaryMaterialsNo}"
										name="sandMaterials[${status.index}].auxiliaryMaterialsNo" />
								</p>
								<p class="labeler">使用数量：</p> <input type="hidden"
								id="sanduserCountTotal${status.index}"
								value="${auxiliary.totalAuxiMateCount}" />
								<div class="clearfix shopcount">
									<a class="reduce" href="javascript:;"></a> <input type="text"
										id="sanduseCount${status.index}"
										name="sandMaterials[${status.index}].usedCount" readonly
										value='${auxiliary.totalAuxiMateCount}' maxlength="11" /> <a
										class="plus" href="javascript:;"></a>
								</div> <span class="price_sin col_red">¥${auxiliary.laborPrice}</span>
								<input type="hidden" id="sandlaborPrice${status.index}"
								value="${auxiliary.laborPrice}"
								name="sandMaterials[${status.index}].price" /> <input
								type="hidden" id="sandsupplierPrice${status.index}"
								value="${auxiliary.supplierPrice}"
								name="sandMaterials[${status.index}].supplierPrice" /> <input
								type="hidden" id="sandwangzhenPrice${status.index}"
								value="${auxiliary.wangzhenPrice}"
								name="sandMaterials[${status.index}].wangzhenPrice" /> <input
								type="hidden" value="1"
								name="sandMaterials[${status.index}].isSandCement" />
							</li>
						</c:forEach>
					</ul>
				</div>
				<footer>
					<div>
						<p class="col_red">
							合计：¥<span id="sandTotal">0</span>
						</p>
					    <input type="hidden" id="sandSupplierTotal" />
				    	<input type="hidden" id="sandWangzhenTotal" />
						<p class="goods">
							共使用<span id="sandCount">0</span>种商品
						</p>
					</div>
					<a class="choose_btn" href="javascript:void(0)" id="sandChoose">确认用量</a>
				</footer>
			</section>


			<!-- 辅料用料弹框 -->
			<section class="g-auxiliary_choose undis" id="auxiliaryModel">
				<!-- <h3 class="mask_tit">辅料用量</h3> -->
				<div class="show_sec">
					<ul>
						<c:forEach items="${auxiliaryList}" var="auxiliary"
							varStatus="status">
							<li class="shadow">
								<div class="img_container">
									<img src="${root}${auxiliary.picUrl}" alt="goods"
										onerror="nofind()">
								</div>
								<p class="brand">${auxiliary.auxiliaryMaterialsName}</p> <input
								type="hidden" id="auxiliaryMaterialsName${status.index}"
								value="${auxiliary.auxiliaryMaterialsName}"> <input
								type="hidden" value="${auxiliary.orderAuxiMateId}"
								name="auxiliaryMaterials[${status.index}].id" />
								<p class="format">
									规格：${auxiliary.specifications} <span class="price">送货数量：${auxiliary.totalAuxiMateCount}${auxiliary.measurementUnitLabel}</span>
									<input type="hidden" id="totalAuxiMateCount${status.index}"
										value="${auxiliary.totalAuxiMateCount}"> <input
										type="hidden" value="${auxiliary.auxiliaryMaterialsNo}"
										name="auxiliaryMaterials[${status.index}].auxiliaryMaterialsNo" />
								</p>
								<p class="labeler">使用数量：</p> <input type="hidden"
								id="userCountTotal${status.index}"
								value="${auxiliary.totalAuxiMateCount}" />
								<div class="clearfix shopcount">
									<a class="reduce" href="javascript:;"></a> <input type="text"
										id="useCount${status.index}"
										name="auxiliaryMaterials[${status.index}].usedCount" readonly
										value='${auxiliary.totalAuxiMateCount}' maxlength="11" /> <a
										class="plus" href="javascript:;"></a>
								</div> <span class="price_sin col_red">¥${auxiliary.laborPrice}</span>
								<input type="hidden" id="auxiliarylaborPrice${status.index}"
								value="${auxiliary.laborPrice}"
								name="auxiliaryMaterials[${status.index}].price" /> <input
								type="hidden" id="supplierPrice${status.index}"
								value="${auxiliary.supplierPrice}"
								name="auxiliaryMaterials[${status.index}].supplierPrice" /> <input
								type="hidden" id="wangzhenPrice${status.index}"
								value="${auxiliary.wangzhenPrice}"
								name="auxiliaryMaterials[${status.index}].wangzhenPrice" /> <input
								type="hidden" value="0"
								name="auxiliaryMaterials[${status.index}].isSandCement" />
							</li>
						</c:forEach>
					</ul>
				</div>
				<footer>
					<div>
						<p class="col_red">
							合计：¥<span id="total">0</span>
						</p>
						<input type="hidden" id="auxiliarySupplierTotal" />
					    <input type="hidden" id="auxiliaryWangzhenTotal" />
						<p class="goods">
							共使用<span id="auxiliaryCount">0</span>种商品
						</p>
					</div>
					<a class="choose_btn" href="javascript:void(0)"
						id="auxiliaryChoose">确认用量</a>
				</footer>
			</section>
		</form>
	</div>
	<div class="alertDialog undis">
		<div class="tip-wrap">
			<p class="tip-tit">提示</p>
			<p class="tip-content">错误提示</p>
			<a class="tip-btn" href="javascript:;">确认</a>
		</div>
	</div>
	<script type="text/javascript"
		src="${ctxStatic}/mobile/modules/Manager/js/lib/jquery-2.1.1.min.js"></script>
	<script type="text/javascript"
		src="${ctxStatic}/mobile/modules/Manager/js/utils/global.js"></script>
	<script type="text/javascript"
		src="${ctxStatic}/mobile/modules/Manager/js/utils/calcRootFontSize.js"></script>
	<script type="text/javascript"
		src="${ctxStatic}/mobile/modules/Manager/js/lib/lCalendar.js"></script>
	<script type="text/javascript"
		src="${ctxStatic}/mobile/modules/Manager/js/utils/history.js"></script>
	<script type="text/javascript"
		src="${ctxStatic}/mobile/modules/Manager/js/utils/waitMe.js"></script>
	<%--<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/shopcount.js"></script>--%>
	<script>
	function run_waitMe(effect,text){
		$('#aboveId').waitMe({
			effect: effect,
			text: text,
			bg: 'rgba(255,255,255,0.7)',
			color:'#000',
			sizeW:'',
			sizeH:'',
			source: 'img.svg'
		});
	}
		$(function(){
			$(document).on('click','.tip-btn',function(){
				$('.alertDialog').addClass('undis');
			});
			// checkbox
			$('label').click(function(){
			    var thisName = $(this).attr('data-name');
			    $('label[data-name="'+thisName+'"]').removeAttr('class') && $(this).attr('class', 'checked');
			  });
	       	// 获取当前日期，结束日期
	    	var now = new Date(),
	    		start = globalUtil.fn.formatDate(now, 'yyyy-MM-dd'),
	    		end = new Date(now.setFullYear(now.getFullYear()+1)),
	    		end = globalUtil.fn.formatDate(end, 'yyyy-MM-dd');

	    	var lcalendar = start+','+end;
	    	// 将时间限制加到input标签上。
	    	$('#txtBeginDate').attr('data-lcalendar', lcalendar);
			var calendar = new lCalendar();
			calendar.init({
			    'trigger': '#txtBeginDate',//标签id
			    'type': 'date'//date 调出日期选择 datetime 调出日期时间选择 time 调出时间选择
			});
			
			//以事件委托的形式，绑定元素点击事件
			$(document).on('click', '.showAll', function(){
				$('.project_bars .project_list').show();
				$(this).text('折叠').addClass('showSome').removeClass('showAll');
			});
			$(document).on('click', '.showSome', function(){
				$('.project_bars .project_list').hide();
				$(this).text('展开').addClass('showAll').removeClass('showSome');
			});
				
			// 获取辅料弹框
			$('#showAuxiliary').click(function(){
				$('#auxiliaryModel').removeClass('undis');
				$('.check_section').addClass('undis');
			});
			
			// 获取沙子水泥料弹框
			$('#showSand').click(function(){
				$('#sandModel').removeClass('undis');
				$('.check_section').addClass('undis');
			});
			
			// add mask
			$(document).on('click', '.sub_btn', function(){
				$('.box-bottom .mask_btm').removeClass('undis');
			});
			
			// add footer_mask
			$('.footer_mask .footer1 a').on('click', function(){
				var bool = $('#mengchengCheck').hasClass('undis');
				if (bool) {
					if($(this).hasClass('watch_btn_a')){
						window.location.href = '${ctx}/app/manager/viewPhone?orderTaskpackageId=${orderTaskpackageVo.id}';
					}
					if($(this).hasClass('check_btn_a')){
						// 用于验证数字和小数
						var re = /^(([1-9][0-9]*\.[0-9][0-9]*)|([0]\.[0-9][0-9]*)|([1-9][0-9]*)|([0]{1}))$/;
						
						// 验证合格
						var qualifiedNo = $("#qualifiedNo").attr("class");
						if(qualifiedNo == "checked"){
							globalUtil.fn.alert('请要求工人组整改合格后再提交工程量!',2.0);
							return;
						}else{
							$("#yes").attr("checked",true);
						}
						
						//验证延期
						var delayNo = $("#delayNo").attr("class");
						if(delayNo == "checked"){
							var delayDays = $("#delayDays").val();
							if(!re.test(delayDays)){
								globalUtil.fn.alert('实际工期为延期时，延误天数必填，整数或小数，且小数部分只能为0或5，填写格式如：2,2.0或2.5',2.0);
								return;
							}
							var array = delayDays.split(".");
							if(array.length > 1){
								if(array[1] != 5 && array[1] != 0){
									globalUtil.fn.alert('实际工期为延期时，延误天数必填，整数或小数，且小数部分只能为0或5，填写格式如：2,2.0或2.5',2.0);
									return;
								}
							}
							$("#out").attr("checked",true);
							// 延期扣款金额
							$("#delayAmerce").val(parseFloat($("#delayDays").val()) * 100);
						}else{
							$("#on").attr("checked",true);
						}
						
						// 验证管理处罚
						var punishNo = $("#punishNo").attr("class");
						if(punishNo == "checked"){
							var punishAmerce = $("#punishAmerce").val();
							var punishReason = $("#punishReason").val();
							if(punishAmerce == null || punishAmerce == ""){
								globalUtil.fn.alert('有管理处罚时，处罚金额必填!',2.0);
								return;
							}
							if(punishReason == null || punishReason == ""){
								globalUtil.fn.alert('有管理处罚时，处罚理由必填!',2.0);
								return;
							}
							$("#two").attr("checked",true);
						}else{
							$("#one").attr("checked",true);
						}
						
						// 验证实际工程量
						var length = '${fn:length(taskProcedureList)}';
						var bool = false;
						for(var i=0;i<length;i++){
							var realNumber = $("#realNumber"+i).val();
							var budgetNumber = $("#budgetNumber"+i).val();
							var procedureName = $("#procedureName"+i).val();
							var budgetNumber = $("#budgetNumber"+i).val();
							if(!re.test(realNumber)){
								globalUtil.fn.alert('工程清单第'+(i+1)+'个工序【'+procedureName+'】实际工程量不能为空，且填写正确整数或小数!',2.0);
								bool = true;
								break;
							}
							if(parseFloat(realNumber) > parseFloat(budgetNumber)){
								var remark = $("#remark"+i).val();
								if($.trim(remark) == null || $.trim(remark) == ""){
									globalUtil.fn.alert('工程清单第'+(i+1)+'个工序【'+procedureName+'】实际工程量大于预算工程量时，备注信息必填!',2.0);
									bool = true;
									break;	
								}
							}
						}
						if(bool){
							return;
						}
						
						
						// 验证辅料金额不为空并且等于辅料弹窗中的总额
						var auxiliaryAmount = $("#auxiliaryAmount").val();
						var total = $("#total").html();
						if(auxiliaryAmount == undefined || auxiliaryAmount == null || auxiliaryAmount == ""||auxiliaryAmount != total){
							$(".tip-content").html("请先点击“获取辅料”后，再确认验收。（拆除、营销保护没有用到辅料也要点击获取辅料）");
							$('.alertDialog').removeClass('undis');
							return;
						}
						
						var checkSand = $("#checkSand").val();
						if(checkSand != undefined || checkSand != null || checkSand == "1"){
							
						   var sandAmount = $("#sandAmount").val();
						   var sandTotal = $("#sandTotal").html();
						   if(sandAmount == undefined || sandAmount == null || sandAmount == "" || sandAmount != sandTotal){
								$(".tip-content").html("请先点击“获取沙子水泥”后，再确认验收。");
								$('.alertDialog').removeClass('undis');
								return;
							}
						}
						
						// 验证工人分配薪酬
						var paymentAmountSum = 0;
						var empLength = '${fn:length(empList)}';
						var boo = false;
						for(var i=0;i<empLength;i++){
							var realName = $("#realName"+i).html();
							var paymentAmount = $("#paymentAmount"+i).val();
							if(!re.test(paymentAmount)){
								globalUtil.fn.alert('分配薪酬【'+realName+'】的分配金额请输入正确的数字!',2.0);
								boo = true;
								break;
							}
							paymentAmountSum = parseFloat(paymentAmountSum) + parseFloat(paymentAmount);
						}
						if(boo){
							return;
						}else{
							var settlementAmount = $("#workerGroupSettleAmount").val();
							if(parseFloat(settlementAmount) != parseFloat(paymentAmountSum).toFixed(2)){
								globalUtil.fn.alert('工人分配金额的总和必须与工人组结算金额一致!',2.0);
								return;
							}
						}
						var checkSub = $("#checkSub").val();
						if(checkSub != "0"){
							return;
						}else{
							$("#checkSub").val("1");
						}
						$(this).unbind();
						// 提交form表单
						run_waitMe("win8",'请稍等 ,拼命提交中....');
						$.ajax({
				            type: "post",  
				            url: "${ctx}/app/manager/orderTaskpackageSettlementAgain",       
				            data: $("#submitForm").serialize(),      
				            success: function(data) {
				            	$('#aboveId').waitMe('hide');
				            	if(data == "success"){
				            		globalUtil.fn.alert('结算单提交成功!',2.0);
				            		window.location.href="${ctx}/app/manager/taskBudgetManage";
				            	}else if(data == "repeat"){
									globalUtil.fn.alert('结算单已生成，不可重复提交!',2.0);
								}else{
									globalUtil.fn.alert('结算单生成失败!',2.0);
								}
				            },  
				            error: function(data) {  
				            	globalUtil.fn.alert('结算单提交失败!',2.0);
				            }  
				        });
					}
				}
			});

			// 点击获取辅料弹框的确认用量按钮
			$('#auxiliaryChoose').click(function(){
				var length = '${fn:length(auxiliaryList)}';
		  		var bool = false;
		  		for(var i=0;i<length;i++){
		  			var userCount = ($("#useCount"+i).val() == null || $("#useCount"+i).val() == "") ? 0 : $("#useCount"+i).val();
		  			var auxiliaryMaterialsName = $("#auxiliaryMaterialsName"+i).val();
		  			var totalAuxiMateCount = $("#totalAuxiMateCount"+i).val();
		  			// 判断使用数量小于送货数量
		  			/* if(parseFloat(userCount) > parseFloat(totalAuxiMateCount)){
		  				//globalUtil.fn.alert('【'+auxiliaryMaterialsName+'】的使用数量不允许超过送货数量',2.0);
						$(".tip-content").html("【"+auxiliaryMaterialsName+"】的使用数量不允许超过送货数量");
						$('.alertDialog').removeClass('undis');
		  				bool = true;
		  				break;
		  			} */
		  			// 判断所有使用数量小于所有送货数量
		  			var userCountTotal = $("#userCountTotal"+i).val();
		  			/* if((parseFloat(userCountTotal)+parseFloat(userCount)) > parseFloat(totalAuxiMateCount)){
		  				//globalUtil.fn.alert('【'+auxiliaryMaterialsName+'】的所有使用数量不允许超过所有送货数量',2.0);
						var shengyu = parseFloat(totalAuxiMateCount) - parseFloat(userCountTotal);
						$(".tip-content").html("【"+auxiliaryMaterialsName+"】已经使用了"+userCountTotal+"，还剩余的使用数量为"+parseFloat(shengyu).toFixed(0));
						$('.alertDialog').removeClass('undis');
		  				bool = true;
		  				break;
		  			} */
		  		}
		  		if(!bool){
		  			$('#auxiliaryModel').addClass('undis');
					$('.check_section').removeClass('undis');
					$("#auxiliaryAmount").val($("#total").html());
					$("#auxiliaryMaterialsDeductAmountSupplierPrice").val($("#auxiliarySupplierTotal").val());
					$("#auxiliaryMaterialsDeductAmountWangzhenPrice").val($("#auxiliaryWangzhenTotal").val());
					initMoney();
		  		}
			});
			
			// 点击获取沙子水泥弹框的确认用量按钮
			$('#sandChoose').click(function(){
				var length = '${fn:length(sandList)}';
		  		var bool = false;
		  		for(var i=0;i<length;i++){
		  			var userCount = ($("#sanduseCount"+i).val() == null || $("#sanduseCount"+i).val() == "") ? 0 : $("#sanduseCount"+i).val();
		  			var auxiliaryMaterialsName = $("#sandMaterialsName"+i).val();
		  			var totalAuxiMateCount = $("#sandtotalAuxiMateCount"+i).val();
		  			// 判断使用数量小于送货数量
		  			/* if(parseFloat(userCount) > parseFloat(totalAuxiMateCount)){
		  				//globalUtil.fn.alert('【'+auxiliaryMaterialsName+'】的使用数量不允许超过送货数量',2.0);
						$(".tip-content").html("【"+auxiliaryMaterialsName+"】的使用数量不允许超过送货数量");
						$('.alertDialog').removeClass('undis');
		  				bool = true;
		  				break;
		  			} */
		  			// 判断所有使用数量小于所有送货数量
		  			var userCountTotal = $("#sanduserCountTotal"+i).val();
		  			/* if((parseFloat(userCountTotal)+parseFloat(userCount)) > parseFloat(totalAuxiMateCount)){
		  				//globalUtil.fn.alert('【'+auxiliaryMaterialsName+'】的所有使用数量不允许超过所有送货数量',2.0);
						var shengyu = parseFloat(totalAuxiMateCount) - parseFloat(userCountTotal);
						$(".tip-content").html("【"+auxiliaryMaterialsName+"】已经使用了"+userCountTotal+"，还剩余的使用数量为"+parseFloat(shengyu).toFixed(0));
						$('.alertDialog').removeClass('undis');
		  				bool = true;
		  				break;
		  			} */
		  		}
		  		if(!bool){
		  			$('#sandModel').addClass('undis');
					$('.check_section').removeClass('undis');
					$("#sandAmount").val($("#sandTotal").html());
					$("#sandCementDeductAmountSupplierPrice").val($("#sandSupplierTotal").val());
					$("#sandCementDeductAmountWangzhenPrice").val($("#sandWangzhenTotal").val());
					initMoney();
		  		}
			});

			// 点击提交工程量按钮
			$("#gongchengCheck").click(function(){
				// 用于验证数字和小数
				var re = /^(([1-9][0-9]*\.[0-9][0-9]*)|([0]\.[0-9][0-9]*)|([1-9][0-9]*)|([0]{1}))$/;
				
				// 验证合格
				var qualifiedNo = $("#qualifiedNo").attr("class");
				if(qualifiedNo == "checked"){
					globalUtil.fn.alert('请要求工人组整改合格后再提交工程量!',2.0);
					return;
				}else{
					$("#yes").attr("checked",true);
				}
				
				//验证延期
				var delayNo = $("#delayNo").attr("class");
				if(delayNo == "checked"){
					var delayDays = $("#delayDays").val();
					if(!re.test(delayDays)){
						globalUtil.fn.alert('实际工期为延期时，延误天数必填，整数或小数，且小数部分只能为0或5，填写格式如：2,2.0或2.5',2.0);
						return;
					}
					var array = delayDays.split(".");
					if(array.length > 1){
						if(array[1] != 5 && array[1] != 0){
							globalUtil.fn.alert('实际工期为延期时，延误天数必填，整数或小数，且小数部分只能为0或5，填写格式如：2,2.0或2.5',2.0);
							return;
						}
					}
					$("#out").attr("checked",true);
					// 延期扣款金额
					$("#delayAmerce").val(parseFloat($("#delayDays").val()) * 100);
				}else{
					$("#on").attr("checked",true);
				}
				
				// 验证管理处罚
				var punishNo = $("#punishNo").attr("class");
				if(punishNo == "checked"){
					var punishAmerce = $("#punishAmerce").val();
					var punishReason = $("#punishReason").val();
					if(punishAmerce == null || punishAmerce == ""){
						globalUtil.fn.alert('有管理处罚时，处罚金额必填!',2.0);
						return;
					}
					if(punishReason == null || punishReason == ""){
						globalUtil.fn.alert('有管理处罚时，处罚理由必填!',2.0);
						return;
					}
					$("#two").attr("checked",true);
				}else{
					$("#one").attr("checked",true);
				}
				
				// 验证实际工程量
				var length = '${fn:length(taskProcedureList)}';
				var bool = false;
				for(var i=0;i<length;i++){
					var realNumber = $("#realNumber"+i).val();
					var budgetNumber = $("#budgetNumber"+i).val();
					var procedureName = $("#procedureName"+i).val();
					var budgetNumber = $("#budgetNumber"+i).val();
					if(!re.test(realNumber)){
						globalUtil.fn.alert('工程清单第'+(i+1)+'个工序【'+procedureName+'】实际工程量不能为空，且填写正确整数或小数!',2.0);
						bool = true;
						break;
					}
					if(parseFloat(realNumber) > parseFloat(budgetNumber)){
						var remark = $("#remark"+i).val();
						if($.trim(remark) == null || $.trim(remark) == ""){
							globalUtil.fn.alert('工程清单第'+(i+1)+'个工序【'+procedureName+'】实际工程量大于预算工程量时，备注信息必填!',2.0);
							bool = true;
							break;	
						}
					}
				}
				if(bool){
					return;
				}
				
				$(this).unbind();
				$.post("${ctx}/app/manager/submitGongchengUpdate",$("#submitForm").serialize(),function(data){
					if(data == "success"){
						globalUtil.fn.alert("提交工程量成功!",2.0);
						window.location.href="${ctx}/app/manager/taskBudgetManage";
					}else{
						globalUtil.fn.alert("提交工程量失败!",2.0);
					}
				});
			});
						
			// 初始化时计算金额
			initMoney();
			getTotalPrice();
			getSandTotalPrice();
			showStore();
	    });

		function showStore(){
			var length = '${fn:length(evalStoreList)}';
			for(var i=0;i<length;i++){
				var selectCount = $("#selectCount"+i).val();
				$("#starP"+i).find(".star1:lt("+selectCount+")").addClass("star2");
			}
		}
		
		// 实际工期点按时完成
		$("#delayYes").click(function(){
			$("#delayDays").val("");
			$("#delayAmerce").val("");
			initMoney();
		});
		
		// 管理处罚点无
		$("#punishYes").click(function(){
			$("#punishAmerce").val("");
			$("#punishReason").val("");
			initMoney();
		});
		
		//图片没有 就使用默认图片
    	function nofind(){
			var img=event.srcElement;
    		img.src="/mdn/static/mobile/modules/Manager/css/photo/auxiliaryPhotoForNothing.png";
			img.onerror=null; 
    	}
		
		// 辅料计算价格、个数
    	function getTotalPrice(){
	  		var length = '${fn:length(auxiliaryList)}';
	  		var totalPrice = 0;
	  		var auxiliarySupplierTotal = 0;
	  		var auxiliaryWangzhenTotal = 0;
	  		var auxiliaryCount = 0;
	  		for(var i=0;i<length;i++){
	  			var userCount = ($("#useCount"+i).val() == null || $("#useCount"+i).val() == "") ? 0 : $("#useCount"+i).val();
	  			var laborPrice = ($("#auxiliarylaborPrice"+i).val() == null || $("#auxiliarylaborPrice"+i).val() == "") ? 0 : $("#auxiliarylaborPrice"+i).val() ;
	  			var supplierPrice = ($("#supplierPrice"+i).val() == null || $("#supplierPrice"+i).val() == "") ? 0 : $("#supplierPrice"+i).val() ;
	  			var wangzhenPrice = ($("#wangzhenPrice"+i).val() == null || $("#wangzhenPrice"+i).val() == "") ? 0 : $("#wangzhenPrice"+i).val() ;
	  			totalPrice = parseFloat(totalPrice) + parseFloat(userCount) * parseFloat(laborPrice);
	  			auxiliarySupplierTotal =  parseFloat(auxiliarySupplierTotal) + parseFloat(userCount) * parseFloat(supplierPrice);
	  			auxiliaryWangzhenTotal =  parseFloat(auxiliaryWangzhenTotal) + parseFloat(userCount) * parseFloat(wangzhenPrice);
	  			if(userCount > 0){
	  				auxiliaryCount = auxiliaryCount + 1;
	  			}
	  		}
	  		$("#total").html(parseFloat(totalPrice).toFixed(2));
	  		$("#auxiliaryCount").html(auxiliaryCount);
	  		$("#auxiliarySupplierTotal").val(auxiliarySupplierTotal.toFixed(2));
	  		$("#auxiliaryWangzhenTotal").val(auxiliaryWangzhenTotal.toFixed(2));
	  	}
		
    	// 沙子水泥计算价格、个数
    	function getSandTotalPrice(){
	  		var length = '${fn:length(sandList)}';
	  		var totalPrice = 0;
	  		var sandSupplierTotal = 0;
	  		var sandWangzhenTotal = 0;
	  		var auxiliaryCount = 0;
	  		for(var i=0;i<length;i++){
	  			var userCount = ($("#sanduseCount"+i).val() == null || $("#sanduseCount"+i).val() == "") ? 0 : $("#sanduseCount"+i).val();
	  			var laborPrice = ($("#sandlaborPrice"+i).val() == null || $("#sandlaborPrice"+i).val() == "") ? 0 : $("#sandlaborPrice"+i).val() ;
	  			var supplierPrice = ($("#sandsupplierPrice"+i).val() == null || $("#sandsupplierPrice"+i).val() == "") ? 0 : $("#sandsupplierPrice"+i).val() ;
	  			var wangzhenPrice = ($("#sandwangzhenPrice"+i).val() == null || $("#sandwangzhenPrice"+i).val() == "") ? 0 : $("#sandwangzhenPrice"+i).val() ;
	  			totalPrice = parseFloat(totalPrice) + parseFloat(userCount) * parseFloat(laborPrice);
	  			sandSupplierTotal =  parseFloat(sandSupplierTotal) + parseFloat(userCount) * parseFloat(supplierPrice);
	  			sandWangzhenTotal =  parseFloat(sandWangzhenTotal) + parseFloat(userCount) * parseFloat(wangzhenPrice);
	  			if(userCount > 0){
	  				auxiliaryCount = auxiliaryCount + 1;
	  			}
	  		}
	  		$("#sandTotal").html(parseFloat(totalPrice).toFixed(2));
	  		$("#sandCount").html(auxiliaryCount);
	  		$("#sandSupplierTotal").val(sandSupplierTotal.toFixed(2));
	  		$("#sandWangzhenTotal").val(sandWangzhenTotal.toFixed(2));
	  	}
		
    	/*// 点加号时事件
		function addUseCount(index){
			var length = '${fn:length(auxiliaryList)}';
	  		var totalPrice = 0;
	  		var auxiliaryCount = 0;
	  		for(var i=0;i<length;i++){
	  			var userCount = ($("#useCount"+i).val() == null || $("#useCount"+i).val() == "") ? 0 : $("#useCount"+i).val();
	  			if(i == index){
	  				userCount = parseFloat(userCount) + 1;
	  			}
	  			var laborPrice = ($("#laborPrice"+i).val() == null || $("#laborPrice"+i).val() == "") ? 0 : $("#laborPrice"+i).val();
	  			totalPrice = parseFloat(totalPrice) + parseFloat(userCount) * parseFloat(laborPrice);
	  			if(userCount > 0){
	  				auxiliaryCount = auxiliaryCount + 1;
	  			}
	  		}
	  		$("#total").html(parseFloat(totalPrice).toFixed(2));
	  		$("#auxiliaryCount").html(auxiliaryCount);
		}*/
    	
		/*// 点减号时事件
		function jianUseCount(index){
			var length = '${fn:length(auxiliaryList)}';
	  		var totalPrice = 0;
	  		var auxiliaryCount = 0;
	  		for(var i=0;i<length;i++){
	  			var userCount = ($("#useCount"+i).val() == null || $("#useCount"+i).val() == "") ? 0 : $("#useCount"+i).val();
	  			if(i == index){
	  				if(userCount > 0){
	  					userCount = parseFloat(userCount) - 1;
	  				}
	  			}
	  			var laborPrice = ($("#laborPrice"+i).val() == null || $("#laborPrice"+i).val() == "") ? 0 : $("#laborPrice"+i).val();
	  			totalPrice = parseFloat(totalPrice) + parseFloat(userCount) * parseFloat(laborPrice);
	  			if(userCount > 0){
	  				auxiliaryCount = auxiliaryCount + 1;
	  			}
	  		}
	  		$("#total").html(parseFloat(totalPrice).toFixed(2));
	  		$("#auxiliaryCount").html(auxiliaryCount);
		}*/
		
		// 计算总价（结算价）---所有工序的总价
		function getTotal(index,obj){
			var length = '${fn:length(taskProcedureList)}';
			var num = 0;
			for(var i=0;i<length;i++){
				var realNumber = ($("#realNumber"+i).val() == null || $("#realNumber"+i).val() == "") ? 0 : $("#realNumber"+i).val();
				var budgetNumber = ($("#budgetNumber"+i).val() == null || $("#budgetNumber"+i).val() == "") ? 0 : $("#budgetNumber"+i).val();
				if((parseFloat(budgetNumber) * 1.03) < parseFloat(realNumber)){
					num = num + 1;
				}
			}

			if(num > 0){
				$("#mengchengCheck").removeClass("undis");
				$("#gongchengCheck").removeClass("undis");
			}else{
				$("#mengchengCheck").addClass("undis");
				$("#gongchengCheck").addClass("undis");
			}
			var synthesizePrice = ($("#synthesizePrice"+index).val() == null || $("#synthesizePrice"+index).val() == "") ? 0 : $("#synthesizePrice"+index).val();
			var laborPrice = ($("#laborPrice"+index).val() == null || $("#laborPrice"+index).val() == "") ? 0 : $("#laborPrice"+index).val();
			var accessoriesPrice = ($("#accessoriesPrice"+index).val() == null || $("#accessoriesPrice"+index).val() == "") ? 0 : $("#accessoriesPrice"+index).val();
			var currentRealNumber = ($(obj).val() == null || $(obj).val() == "") ? 0 : $(obj).val();
			$("#laborAuxiliaryMaterialsSettleAmount"+index).html((parseFloat(synthesizePrice) * parseFloat(currentRealNumber)).toFixed(2) + "元");
			$("#laborAuxiliaryMaterialsSettleAmountPrice"+index).val((parseFloat(synthesizePrice) * parseFloat(currentRealNumber)).toFixed(2));
			
			$("#auxiliaryMaterialsSettleAmount"+index).html((parseFloat(accessoriesPrice) * parseFloat(currentRealNumber)).toFixed(2) + "元");
			$("#auxiliaryMaterialsSettleAmountPrice"+index).val((parseFloat(accessoriesPrice) * parseFloat(currentRealNumber)).toFixed(2));
			
			$("#laborSettleAmount"+index).html((parseFloat(laborPrice) * parseFloat(currentRealNumber)).toFixed(2) + "元");
			$("#laborSettleAmountPrice"+index).val((parseFloat(laborPrice) * parseFloat(currentRealNumber)).toFixed(2));
			
			initMoney();
		}
		
		// 初始化时显示质保金(工人组结算金额 * 质保金比例)
		function initMoney(){
			var length = ${fn:length(taskProcedureList)};
			//工料费结算总金额
			var laborAuxiliaryMaterialsSettleAmount = 0;
			//人工费结算总金额
			var laborSettleAmount = 0;
			//辅料费结算总金额
			var auxiliaryMaterialsSettleAmount = 0;
			
			for(var i=0;i<length;i++){
				laborAuxiliaryMaterialsSettleAmount = parseFloat(laborAuxiliaryMaterialsSettleAmount) + parseFloat($("#laborAuxiliaryMaterialsSettleAmountPrice"+i).val());
				laborSettleAmount = parseFloat(laborSettleAmount) + parseFloat($("#laborSettleAmountPrice"+i).val());
				auxiliaryMaterialsSettleAmount = parseFloat(auxiliaryMaterialsSettleAmount) + parseFloat($("#auxiliaryMaterialsSettleAmountPrice"+i).val());
			}
			$("#laborAuxiliaryMaterialsSettleAmount").val(parseFloat(laborAuxiliaryMaterialsSettleAmount).toFixed(2));
			$("#laborSettleAmount").val(parseFloat(laborSettleAmount).toFixed(2));
			$("#auxiliaryMaterialsSettleAmount").val(parseFloat(auxiliaryMaterialsSettleAmount).toFixed(2));

			// 辅材金额
			var auxiliaryAmount = ($("#auxiliaryAmount").val() == null || $("#auxiliaryAmount").val() == "") ? 0 : $("#auxiliaryAmount").val();

			//沙子水泥金额
			var sandAmount = ($("#sandAmount").val() == null || $("#sandAmount").val() == "") ? 0 : $("#sandAmount").val();
			// 延期扣款
			var delayAmerce = 0;
			var delayNo = $("#delayNo").attr("class");
			if(delayNo == "checked"){
				delayAmerce = parseFloat(($("#delayDays").val() == null || $("#delayDays").val() == "") ? 0 : $("#delayDays").val()) * 100;
			}

			// 管理处罚金额
			var punishAmerce = 0;
			var punishNo = $("#punishNo").attr("class");
			if(punishNo == "checked"){
				punishAmerce = ($("#punishAmerce").val() == null || $("#punishAmerce").val() == "") ? 0 : $("#punishAmerce").val();
			}

			// 质检罚款
			var qcAmount = ($("#qcAmount").val() == null || $("#qcAmount").val() == "") ? 0 : $("#qcAmount").val();

			//公司扣款
			var companyDeductAmount = ($("#companyDeductAmount").val() == null || $("#companyDeductAmount").val() == "") ? 0 : $("#companyDeductAmount").val();
			
			var settleStyle = $("#settleStyle").val();
			// 结算总金额
			var allAmount = 0;
			//工人组结算金额
			var workerGroupSettleAmount = 0;
			//项目经理材料结算金额
			var pmMaterialsSettleAmount = 0;
			
			if(settleStyle == "1"){//包工包料
            	workerGroupSettleAmount = parseFloat(laborAuxiliaryMaterialsSettleAmount)-parseFloat(auxiliaryAmount) - parseFloat(sandAmount) -
				parseFloat(delayAmerce) - parseFloat(punishAmerce) - parseFloat(qcAmount) - parseFloat(companyDeductAmount);
            }else if(settleStyle == "2"){//包工
            	workerGroupSettleAmount = parseFloat(laborSettleAmount) - parseFloat(delayAmerce) - parseFloat(punishAmerce) - parseFloat(qcAmount) - parseFloat(companyDeductAmount);
            	pmMaterialsSettleAmount = parseFloat(auxiliaryMaterialsSettleAmount) - parseFloat(auxiliaryAmount) - parseFloat(sandAmount);
            }
			
			// 质保金
			var guaranteeMoneyAmount = 0;
			if('${orderTaskpackageVo.gualityGuaranteeType}' == 3){
				var guaranteeMoneyAmountTotal = '${orderTaskpackageVo.guaranteeMoneyAmountTotal}';
				var guaranteeMoneyBalance = '${orderTaskpackageVo.guaranteeMoneyBalance}';
				var qualityGuaranteeRate = '${orderTaskpackageVo.qualityGuaranteeRate}';
				var amount = (workerGroupSettleAmount * parseFloat(qualityGuaranteeRate))/100;
				if(parseFloat(guaranteeMoneyBalance) >= 2000){ // 累计金额大于2000，质保金为0
					guaranteeMoneyAmount = 0;
				}else{
					if((parseFloat(guaranteeMoneyBalance) + amount) > 2000){
						guaranteeMoneyAmount = (amount - ((parseFloat(guaranteeMoneyBalance) + amount) - parseFloat(2000))).toFixed(2);
					}else{
						guaranteeMoneyAmount = amount.toFixed(2);
					}
				}
			}
			workerGroupSettleAmount = workerGroupSettleAmount - guaranteeMoneyAmount;
			$("#guaranteeMoneyAmount").val(guaranteeMoneyAmount);

			
			
			// 结算总金额
			var settlementAmount = parseFloat(workerGroupSettleAmount) + parseFloat(pmMaterialsSettleAmount);
			$("#settlementAmount").val(parseFloat(settlementAmount).toFixed(2));
			$("#workerGroupSettleAmount").val(parseFloat(workerGroupSettleAmount).toFixed(2));
			$("#pmMaterialsSettleAmount").val(parseFloat(pmMaterialsSettleAmount).toFixed(2));
		}
	</script>
</body>
</html>