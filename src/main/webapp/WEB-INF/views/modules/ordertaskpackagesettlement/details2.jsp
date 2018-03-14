<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>

<head>
    <title>结算单详情</title>
    <meta name="decorator" content="default"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet"
          href="${ctxStatic}/modules/orderTaskpackageSettle/css/reset.css">
    <link rel="stylesheet"
          href="${ctxStatic}/modules/orderTaskpackageSettle/css/style.css">
    <link rel="stylesheet"
          href="${ctxStatic}/modules/orderTaskpackageSettle/css/modal.css">

</head>

<body>
<div class="wrap">
    <div class="header">
        <span>任务包结算单</span>
    </div>
    <div class="content clearfix">
        <div class="content-block">
            <div class="content-block-title">基本信息：</div>
            <div class="content-block-item">
                <div class="item clearfix">
                    <div class="col-md-6 pull-left">
                        <span class="item-name">结 算 单 号：</span>
                        ${bizOrderTaskpackageSettlementVo.settlementNo }
                    </div>
                    <div class="col-md-6 pull-left">
                        <span class="item-name">订 单 编 号：</span>
                        ${bizOrderTaskpackageSettlementVo.orderNo }
                    </div>
                </div>
                <div class="item clearfix">
                    <div class="col-md-6 pull-left">
                        <span class="item-name">小 区 名 称：</span>
                        ${bizOrderTaskpackageSettlementVo.customerMessage }
                    </div>
                    <div class="col-md-6 pull-left">
                        <span class="item-name">客 户 姓 名：</span>
                        ${bizOrderTaskpackageSettlementVo.customerName }-${bizOrderTaskpackageSettlementVo.customerPhone}
                    </div>
                </div>
                <div class="item clearfix">
                    <div class="col-md-6 pull-left">
                        <span class="item-name">任务包编号：</span>
                        ${bizOrderTaskpackageSettlementVo.taskPackageNo }
                    </div>
                    <div class="col-md-6 pull-left">
                        <span class="item-name">任务包名称：</span>
                        ${bizOrderTaskpackageSettlementVo.orderTaskpackageName }
                    </div>
                </div>
                <div class="item clearfix">
                    <div class="col-md-6 pull-left">
                        <span class="item-name">项 目 经 理：</span>
                        ${bizOrderTaskpackageSettlementVo.itemManager }-${bizOrderTaskpackageSettlementVo.itemPhone}
                    </div>
                    <div class="col-md-6 pull-left">
                        <span class="item-name">工 人 组 长：</span>
                        ${bizOrderTaskpackageSettlementVo.groupRealname }
                        -${bizOrderTaskpackageSettlementVo.groupPhone}
                    </div>
                </div>
                <div class="item clearfix">
                    <div class="col-md-6 pull-left">
                        <span class="item-name">设 计 师：</span>
                        ${bizOrderTaskpackageSettlementVo.designerName }
                    </div>
                    <div class="col-md-6 pull-left">
                        <span class="item-name">户 型/面 积：</span>
                        ${fns:getDictLabel(bizOrderTaskpackageSettlementVo.houseType, 'home_type', '')}/${bizOrderTaskpackageSettlementVo.coveredArea }
                    </div>
                </div>
            </div>
        </div>
        <div class="content-block">
            <div class="content-block-title">项目评定：</div>
            <div class="content-block-item clearfix">
                <div class="item clearfix">
                    <div class="col-md-6 pull-left">
                        <span class="item-name">验收日期： </span>
                        <fmt:formatDate
                                value="${bizOrderTaskpackageSettlementVo.checkDate }"
                                pattern="yyyy-MM-dd"/>
                    </div>
                    <div class="col-md-6 pull-left">
                        <span class="item-name">质量：</span>
                        <c:if
                                test="${bizOrderTaskpackageSettlementVo.isQualified == '1'}">合格</c:if>
                        <c:if
                                test="${bizOrderTaskpackageSettlementVo.isQualified == '0'}">不合格</c:if>
                    </div>
                </div>
                <div class="item clearfix">
                    <p class="pull-left">
                        <span class="item-name">工 期：</span> <span class="item-tips"><i
                            class="text-blue"> <c:if
                            test="${bizOrderTaskpackageSettlementVo.isDelay == '0'}">按时完成</c:if>
									<c:if test="${bizOrderTaskpackageSettlementVo.isDelay == '1'}">延期"5天"，</c:if></i></span>
                    </p>
                    <p class="pull-left">
                        <span class="item-name">管理处罚：</span> <span class="item-tips"><i
                            class="text-blue"> <c:if
                            test="${bizOrderTaskpackageSettlementVo.isManagePunish == '0'}">无</c:if>
									<c:if
                                            test="${bizOrderTaskpackageSettlementVo.isManagePunish == '1'}">有，共处罚"-${bizOrderTaskpackageSettlementVo.punishAmerce}元"</c:if></i>
								<c:if
                                        test="${bizOrderTaskpackageSettlementVo.isManagePunish == '1'}">${bizOrderTaskpackageSettlementVo.punishReason}</c:if>
							</span>
                    </p>
                </div>
            </div>
        </div>
        <div class="content-block">
            <div class="content-block-title">任务包工序清单：</div>
            <div class="content-block-item clearfix">
                <div class="item clearfix">
                    <div class="col-md-4 pull-left">
                        <span class="item-name">（人工+辅料）预算总金额：</span>+${bizOrderTaskpackageSettlementVo.laborAuxiliaryMaterialsBudgetAmount}元
                    </div>
                    <div class="col-md-4 pull-left">
                        <span class="item-name">人工费预算总金额：</span>+${bizOrderTaskpackageSettlementVo.laborBudgetAmount}元
                    </div>
                    <div class="col-md-4 pull-left">
                        <span class="item-name">辅料费预算总金额：</span>+${bizOrderTaskpackageSettlementVo.auxiliaryMaterialsBudgetAmount}元
                    </div>
                </div>
                <div class="item clearfix">
                    <div class="col-md-4 pull-left">
                        <span class="item-name">（人工+辅料）结算总金额：</span>+${bizOrderTaskpackageSettlementVo.laborAuxiliaryMaterialsSettleAmount}元
                    </div>
                    <div class="col-md-4 pull-left">
                        <span class="item-name">人工费结算总金额：</span>+${bizOrderTaskpackageSettlementVo.laborSettleAmount}元
                    </div>
                    <div class="col-md-4 pull-left">
                        <span class="item-name">辅料费结算总金额：</span>+${bizOrderTaskpackageSettlementVo.auxiliaryMaterialsSettleAmount}元
                    </div>
                </div>
            </div>
            <div class="table-scroll">
                <table class="item-table _long">
                    <thead>
                    <tr class="bg-blue">
                        <th>工序名称</th>
                        <th>单位</th>
                        <th><p>预算</p>
                            <p>工程量</p></th>
                        <th><p>实际</p>
                            <p>工程量</p></th>
                        <th><p>复核</p>
                            <p>工程量</p></th>
                        <th><p>结算</p>
                            <p>工程量</p></th>
                        <th>
                            <p>(人工+辅料)</p>
                            <p>结算单价</p>
                        </th>
                        <th>
                            <p>人工费</p>
                            <p>结算单价</p>
                        </th>
                        <th>
                            <p>辅料费</p>
                            <p>结算单价</p>
                        </th>
                        <th>
                            <p>(人工+辅料)</p>
                            <p>结算金额</p>
                        </th>
                        <th>
                            <p>人工费</p>
                            <p>结算金额</p>
                        </th>
                        <th>
                            <p>辅料费</p>
                            <p>结算金额</p>
                        </th>
                        <th>备注</th>
                        <th>复核说明</th>
                    </tr>
                    </thead>
                    <tbody id="tblMain">
                    <c:forEach items="${procedures}" var="procedure"
                               varStatus="status">
                        <tr id="tr${status.index}" onclick="TrOnClick(${status.index})">
                            <td>${procedure.procedureName }</td>
                            <td>${procedure.measurementUnitLabel }</td>
                            <td
                                    <c:if test="${procedure.budgetNumber != procedure.settlementNumber || (procedure.recheckNumber != null && procedure.budgetNumber != procedure.recheckNumber)}">class="text-red"</c:if>>${procedure.budgetNumber }</td>
                            <td
                                    <c:if test="${procedure.budgetNumber != procedure.settlementNumber || (procedure.recheckNumber != null && procedure.budgetNumber != procedure.recheckNumber)}">class="text-red"</c:if>>${procedure.realNumber }
                            </td>
                            <td
                                    <c:if test="${procedure.budgetNumber != procedure.settlementNumber || (procedure.recheckNumber != null && procedure.budgetNumber != procedure.recheckNumber)}">class="text-red"</c:if>>${procedure.recheckNumber }</td>
                            <td>${procedure.settlementNumber }</td>
                            <td>${procedure.synthesizePrice}</td>
                            <td>${procedure.laborPrice}</td>
                            <td>${procedure.accessoriesPrice}</td>
                            <td>${procedure.laborAuxiliaryMaterialsSettleAmount}</td>
                            <td>${procedure.laborSettleAmount}</td>
                            <td>${procedure.auxiliaryMaterialsSettleAmount}</td>
                            <td><a href="javascript:void(0)" class="btn btn-primary"
                                   onclick="showRemarks('${procedure.remarks}')">查看详情</a></td>
                            <td><a href="javascript:void(0)"
                                   class="btn btn-primary bg-blue"
                                   onclick="showRemarks('${procedure.recheckRemarks}')">查看详情</a></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
        <div class="content-block">
            <div class="content-block-title">辅料核算清单：</div>
            <div class="content-block-item clearfix">
                <div class="item clearfix">
                    <div>
                        辅料扣款金额：<span
                            class="text-red">-${bizOrderTaskpackageSettlementVo.auxiliaryMaterialsAmount}元</span>
                    </div>
                </div>
            </div>
            <table class="item-table">
                <thead>
                <tr class="bg-grey">
                    <th>工序名称</th>
                    <th>辅料商品编号</th>
                    <th>辅料商品名称</th>
                    <th>单位</th>
                    <th>使用数量</th>
                    <th>结算单价（元）</th>
                    <th>商品总价（元）</th>
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
                        <td>${auxiliary.usedCount}</td>
                        <td>${auxiliary.laborPrice}</td>
                        <td>${auxiliary.price }</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <div class="content-block">
            <div class="content-block-title">沙子水泥核算清单 :</div>
            <div class="content-block-item clearfix">
                <div class="item clearfix">
                    <div>
                        沙子水泥扣款金额：<span class="text-red">-<c:if
                            test="${bizOrderTaskpackageSettlementVo.sandMaterialsAmount == null}">0.00</c:if>
								<c:if
                                        test="${bizOrderTaskpackageSettlementVo.sandMaterialsAmount != null}">${bizOrderTaskpackageSettlementVo.sandMaterialsAmount}</c:if>元
							</span>
                    </div>
                </div>
            </div>
            <table class="item-table">
                <thead>
                <tr class="bg-grey">
                    <th>工序名称</th>
                    <th>辅料商品编号</th>
                    <th>辅料商品名称</th>
                    <th>单位</th>
                    <th>使用数量</th>
                    <th>结算单价（元）</th>
                    <th>商品总价（元）</th>
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
                        <td>${auxiliary.usedCount}</td>
                        <td>${auxiliary.laborPrice}</td>
                        <td>${auxiliary.price}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <div class="content-block">
            <div class="content-block-title">上缴质保金：</div>
            <div class="content-block-item _spacing">
                <div class="item clearfix">
                    <div>
                        <span class="item-name">工人组长：${guarantee.groupRealname }</span>
                    </div>
                </div>
                <div class="item clearfix">
                    <div class="col-md-4 pull-left">
                        <span class="item-name">本次上缴质保金金额：</span>
                        -${guarantee.guaranteeMoneyAmount }元
                    </div>
                    <div class="col-md-4 pull-left">
                        <span class="item-name">累计上缴质保金金额：</span>
                        -${guarantee.guaranteeMoneyAmountTotal}元
                    </div>
                </div>
            </div>
        </div>
        <c:if test="${bizOrderTaskpackageSettlementVo.settleStyle == 1}">
            <div class="content-block">
                <div class="content-block-title">结算金额：</div>
                <div class="content-block-item clearfix">
                    <div class="item clearfix">
                        <div>
                            <span class="item-name">结算方式：</span>包工包料
                        </div>
                    </div>
                </div>
                <div class="content-block-item _spacing clearfix">
                    <div class="item clearfix">
                        <div class="col-md-4 pull-left">
                            <span class="item-name text-right">(人工+辅料)结算总金额：</span>+${bizOrderTaskpackageSettlementVo.laborAuxiliaryMaterialsSettleAmount}元
                        </div>
                    </div>
                    <div class="item clearfix">
                        <div class="col-md-4 pull-left">
                            <span class="item-name text-right">工期扣款：</span>-${empty bizOrderTaskpackageSettlementVo.delayAmerce ? 0.00 : bizOrderTaskpackageSettlementVo.delayAmerce}元
                        </div>
                        <div class="col-md-4 pull-left">
                            <span class="item-name text-right">管理处罚：</span>-${empty bizOrderTaskpackageSettlementVo.punishAmerce ? 0.00 : bizOrderTaskpackageSettlementVo.punishAmerce}元
                        </div>
                        <div class="col-md-4 pull-left">
                            <span class="item-name text-right">质检罚款：</span>-${empty bizOrderTaskpackageSettlementVo.qcPunishMoneyAmount ? 0.00 : bizOrderTaskpackageSettlementVo.qcPunishMoneyAmount}元
                        </div>
                    </div>
                    <div class="item clearfix">

                        <div class="col-md-4 pull-left">
                            <span class="item-name text-right">奖励金额：</span>+${empty employeeRewardDetail.rewardAmount ? 0.00 : employeeRewardDetail.rewardAmount}元
                        </div>

                        <div class="col-md-4 pull-left">
                            <span class="item-name text-right">辅料扣款：</span>-${totalPrice}元
                        </div>
                        <div class="col-md-4 pull-left">
                            <span class="item-name text-right">沙子水泥扣款：</span>-${sandTotalPrice}元
                        </div>
                    </div>
                    <div class="item clearfix">
                        <div class="col-md-4 pull-left">
                            <span class="item-name text-right">质保金扣款：</span>-${bizOrderTaskpackageSettlementVo.guaranteeMoneyAmount}元
                        </div>
                        <div class="col-md-4 pull-left">
                            <span class="item-name text-right">公司罚款：</span>-${empty bizOrderTaskpackageSettlementVo.companyDeductAmount ? 0.00 : bizOrderTaskpackageSettlementVo.companyDeductAmount}元
                        </div>
                        <%--<div class="col-md-4 pull-left clearfix">--%>
                            <%--<span class="item-name text-right pull-left disIB">罚款原因：</span><span--%>
                                <%--class="pull-left disIB _ellipsis3 _w200 _h90">${ bizOrderTaskpackageSettlementVo.companyDeductReason}</span>--%>
                        <%--</div>--%>
                    </div>
                    <div class="item">
                        <div class="clearfix">
                            <span class="item-name text-right pull-left">罚款原因：</span><span class="pull-left col-md-10">${ bizOrderTaskpackageSettlementVo.companyDeductReason}</span>
                        </div>
                    </div>
                    <div class="item">
                        <div class="text-right">
                            工人组结算金额：<span
                                class="text-red">+${bizOrderTaskpackageSettlementVo.workerGroupSettleAmount}元</span>
                        </div>
                    </div>
                        <%-- <div class="item">
                            <div class="text-right">
                                (人工+辅料)结算总金额：<span class="text-red">+${bizOrderTaskpackageSettlementVo.settlementAmount}元</span>
                            </div>
                        </div> --%>
                </div>
                <div class="content-block-item _demo-txt">
                    <p>说明：</p>
                    <p>结算方式为“包工包料”表示把任务包的（人工+辅料）结算总金额都发给工人组</p>
                    <p>工人组结算金额 =【(人工+辅料)结算总金额 - 工期扣款 - 管理处罚款 - 质检罚款 - 公司扣款 - 辅料扣款
                        - 沙子水泥扣款 - 质保金扣除 + 奖励金额】</p>
                </div>
            </div>
        </c:if>
        <c:if test="${bizOrderTaskpackageSettlementVo.settleStyle == 2}">

            <div class="content-block">
                <div class="content-block-title">结算金额：</div>
                <div class="content-block-item clearfix">
                    <div class="item clearfix">
                        <div>
                            <span class="item-name">结算方式：</span>包工
                        </div>
                    </div>
                </div>
                <div class="content-block-item _spacing clearfix">
                    <div class="item border-bottom">工人组结算明细：</div>
                    <div class="item clearfix">
                        <div class="col-md-4 pull-left">
                            <span class="item-name text-right">人工费结算总金额：</span>+${bizOrderTaskpackageSettlementVo.laborSettleAmount}元
                        </div>
                        <div class="col-md-4 pull-left">
                            <span class="item-name text-right">工期扣款：</span>-${empty bizOrderTaskpackageSettlementVo.delayAmerce ? 0.0 : bizOrderTaskpackageSettlementVo.delayAmerce}元
                        </div>
                        <div class="col-md-4 pull-left">
                            <span class="item-name text-right">管理处罚：</span>-${empty bizOrderTaskpackageSettlementVo.punishAmerce ? 0.0 : bizOrderTaskpackageSettlementVo.punishAmerce}元
                        </div>
                    </div>
                    <div class="item clearfix">

                        <div class="col-md-4 pull-left">
                            <span class="item-name text-right">质检罚款：</span>-${empty bizOrderTaskpackageSettlementVo.qcPunishMoneyAmount ? 0.0 : bizOrderTaskpackageSettlementVo.qcPunishMoneyAmount}元
                        </div>
                        <div class="col-md-4 pull-left">
                            <span class="item-name text-right">奖励金额：</span>+${empty employeeRewardDetail.rewardAmount ? 0.0 : employeeRewardDetail.rewardAmount}元
                        </div>
                        <div class="col-md-4 pull-left">
                            <span class="item-name text-right">质保金扣款：</span>-${bizOrderTaskpackageSettlementVo.guaranteeMoneyAmount}元
                        </div>
                    </div>
                    <div class="item clearfix">
                        <div class="col-md-4 pull-left">
                            <span class="item-name text-right">公司罚款：</span>-${empty bizOrderTaskpackageSettlementVo.companyDeductAmount ? 0.0 : bizOrderTaskpackageSettlementVo.companyDeductAmount}元
                        </div>
               <%--         <div class="col-md-4 pull-left clearfix">
                            <span class="item-name text-right pull-left disIB">罚款原因：</span><span
                                class="pull-left disIB _ellipsis3 _w200 _h90">${ bizOrderTaskpackageSettlementVo.companyDeductReason}</span>
                        </div>--%>
                    </div>
                    <div class="item">
                        <div class="clearfix">
                            <span class="item-name text-right pull-left">罚款原因：</span><span class="pull-left col-md-10">${ bizOrderTaskpackageSettlementVo.companyDeductReason}</span>
                        </div>
                    </div>

                    <div class="item">
                        <div class="text-right">
                            工人组结算金额：<span
                                class="text-red">+${bizOrderTaskpackageSettlementVo.workerGroupSettleAmount}元</span>
                        </div>
                    </div>
                </div>
                <div class="content-block-item _spacing clearfix">
                    <div class="item border-bottom">项目经理结算明细：</div>
                    <div class="item clearfix">
                        <div class="col-md-4 pull-left">
                            <span class="item-name text-right">辅料费结算总金额：</span>+${bizOrderTaskpackageSettlementVo.auxiliaryMaterialsSettleAmount}元
                        </div>
                        <div class="col-md-4 pull-left">
                            <span class="item-name text-right">辅料扣款：</span>-${totalPrice}元
                        </div>
                        <div class="col-md-4 pull-left">
                            <span class="item-name text-right">沙子水泥扣款：</span>-${sandTotalPrice}元
                        </div>
                    </div>
                    <div class="item">
                        <div class="text-right">
                            项目经理材料结算金额：<span class="text-red"><c:if
                                test="${bizOrderTaskpackageSettlementVo.pmMaterialsSettleAmount >0}">+</c:if>${bizOrderTaskpackageSettlementVo.pmMaterialsSettleAmount}元</span>
                        </div>
                    </div>
                </div>
                <div class="content-block-item pd10">
                    <div class="item">
                        <div class="text-right text-blue">
                            任务包结算总金额合计： <input type="text" readonly="readonly" style="color: red;font-size: 20px;"
                                               class="item-input"
                                               value="￥${bizOrderTaskpackageSettlementVo.settlementAmount}">
                        </div>
                    </div>
                </div>
                <div class="content-block-item _demo-txt">
                    <p>说明：</p>
                    <p>结算方式为“包工”表示把任务包的【人工费结算总金额发给工人组，辅料费结算总金额发给项目经理】</p>
                    <p>工人组实际结算金额 =【人工费结算总金额 - 工期扣款 - 管理处罚款 - 质检罚款 - 公司扣款 - 质保金扣除 +
                        奖励金额】</p>
                    <p>项目经理材料结算金额 = 【辅料费结算总金额 - 辅料扣款 - 沙子水泥扣款 】</p>
                </div>
            </div>
        </c:if>
        <div class="content-block">
            <div class="content-block-title">分配薪酬：</div>
            <table class="item-table">
                <thead>
                <tr class="bg-grey">
                    <th>序号</th>
                    <th>工人姓名</th>
                    <th>角色</th>
                    <th>分配金额（元）</th>
                    <th>备注</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${payments}" var="payment" varStatus="status">
                    <tr>
                        <td>${status.index+1}</td>
                        <td>${payment.employeeName}</td>
                        <td><c:if test="${payment.isLeader == '1'}">组长</c:if> <c:if
                                test="${payment.isLeader == '0'}">组员</c:if></td>
                        <td>${payment.paymentAmount}</td>
                        <td class="text-left">${payment.paymentRemarks}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <div class="btn-area pull-left">
            <a href="javascript:void(0)" class="btn btn-reback"
               onclick="history.go(-1);">返回上一步</a>
        </div>
    </div>
</div>
<div class="modal-overlay">
    <div class="modal">
        <div class="modal-title">查看详情</div>
        <div class="modal-inner">
            <div class="modal-text">
                <p id="showRemarks"></p>
            </div>
        </div>
        <div class="modal-button-box">
            <button type="button" class="modal-button" id="btn-close"
                    onclick="closeRemarks()">关闭
            </button>
        </div>
    </div>
</div>
<script>
    function showRemarks(remarks) {
        $("#showRemarks").html(remarks);
        $('.modal-overlay').addClass('_in');
    }

    function closeRemarks() {
        $("#showRemarks").html("");
        $('.modal-overlay').removeClass('_in');
    }
</script>

<script type="text/javascript">
    function TrOnClick(index) {
        var trStr = "tr" + index;
        var tbl = document.getElementById("tblMain");
        var trs = tbl.getElementsByTagName("tr");
        for (var i = 0; i < trs.length; i++) {
            if (trs[i].id == trStr) {
                trs[i].style.background = "#ddd";
            } else {
                trs[i].style.background = "white";
            }
        }
    }
</script>
</body>

</html>