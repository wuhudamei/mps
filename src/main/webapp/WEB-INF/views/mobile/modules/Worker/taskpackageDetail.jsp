<%--<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1, user-scalable=no">
    <meta content="yes" name="apple-mobile-web-app-capable">
    <meta content="black" name="apple-mobile-web-app-status-bar-style">
    <meta content="telephone=no" name="format-detection">
    <meta content="email=no" name="format-detection">
    <title>任务包详情</title>
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Worker/css/reset.css"/>
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Worker/css/header.css"/>
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Worker/css/budget.css"/>
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/Packagebudget.css"/>
</head>
<body>
<div class="g-budget">
    <header>
        <a class="back_btn" onclick="history.go(-1)"></a>
        <h2 class="title">任务包详情</h2>
    </header><!-- /header -->
    <section class="budget_section">
        <h3>任务包信息</h3>
        <ul>
            <li class="clearfix">
                <span>任务包名称：${pack.packageName}</span>
            </li>
            <li class="clearfix">
                <span>任务包状态：</span>
                <p>${fns:getDictLabel(pack.packageStateid, 'taskpackage_status', '')}</p>
            </li>
            <!--包工包料-->
            <c:if test="${pack.settleStyle == null || pack.settleStyle == 1}">
                <!-- 结算 -->
                <c:if test="${settleTotalMoney > 0}">
                    <li class="clearfix">
                        <span>工料费结算总金额 ：</span>
                        <p>
                                ${settleTotalMoney}
                        </p>
                    </li>
                    <li class="clearfix">
                        <span>人工费结算总金额 ：</span>
                        <p>
                                ${laborSettleTotalMoney}
                        </p>
                    </li>
                    <li class="clearfix">
                        <span>辅料费结算总金额 ：</span>
                        <p>
                                ${auxiliaryMaterialsSettleTotalMoney}
                        </p>
                    </li>
                </c:if>
                <!-- 预算 -->
                <c:if test="${settleTotalMoney <= 0}">
                    <li class="clearfix">
                        <span>工料费预算总金额 ：</span>
                        <p>
                                ${budgetTotalMoney }
                        </p>
                    </li>
                    <li class="clearfix">
                        <span>人工费预算总金额 ：</span>
                        <p>
                                ${laborDudgetTotalMoney }
                        </p>
                    </li>
                    <li class="clearfix">
                        <span>辅料费预算总金额 ：</span>
                        <p>
                                ${auxiliaryMaterialsBudgetTotalMoney }
                        </p>
                    </li>
                </c:if>
            </c:if>

            <!-- 包工 -->
            <c:if test="${pack.settleStyle == 2}">
                <!-- 结算 -->
                <c:if test="${settleTotalMoney > 0}">
                    <li class="clearfix">
                        <span>人工费结算总金额 ：</span>
                        <p>
                                ${laborSettleTotalMoney}
                        </p>
                    </li>
                </c:if>
                <!-- 预算 -->
                <c:if test="${settleTotalMoney <= 0}">
                    <li class="clearfix">
                        <span>人工费预算总金额 ：</span>
                        <p>
                                ${laborDudgetTotalMoney }
                        </p>
                    </li>
                </c:if>
            </c:if>

            <li class="clearfix">
                <span>要求工期：</span>
                <c:if test="${pack.actualStartdate == null || pack.actualEnddate == null }">
                    <p>
                        <fmt:formatDate value="${pack.planStartdate }" pattern="yyyy-MM-dd"/>
                        至
                        <fmt:formatDate value="${pack.planEnddate }" pattern="yyyy-MM-dd"/>
                    </p>
                </c:if>
                <c:if test="${pack.actualStartdate != null && pack.actualEnddate != null }">
                    <p>
                        <fmt:formatDate value="${pack.actualStartdate }" pattern="yyyy-MM-dd"/>
                        至
                        <fmt:formatDate value="${pack.actualEnddate }" pattern="yyyy-MM-dd"/>
                    </p>
                </c:if>
                &lt;%&ndash; <p><fmt:formatDate value="${pack.planStartdate}" pattern="yyyy-MM-dd"/> 至 <fmt:formatDate value="${pack.planEnddate}" pattern="yyyy-MM-dd"/></p> &ndash;%&gt;
            </li>
            <li class="clearfix">
                <span>施工地点：</span>
                <p>${pack.customerMessage }--${pack.customerName}</p>
            </li>
            <li class="clearfix">
                <span>施工人员：</span>
                <p>${pack.leaderName}-${pack.leaderPhone}</p>
            </li>
            <a id="tele" href="tel:${pack.leaderPhone}"></a>
        </ul>
    </section>
    <h3>工序工价</h3>
    <div class="budget_details">
        <c:forEach items="${procedures }" var="procedure">
            <c:if test="${procedure.settlementNumber == null}">
                <p>${procedure.procedureName}</p>
                <table>
                    <tbody>
                    <!--包工包料-->
                    <c:if test="${pack.settleStyle == null || pack.settleStyle == 1}">
                        <tr>
                            <td>
                                工料费预算金额
                            </td>
                            <td>
                                    ${procedure.synthesizePrice}元/${procedure.measurementUnitLabel}*${procedure.budgetNumber}=${procedure.laborAuxiliaryMaterialsBudgetAmount}元
                            </td>
                        </tr>

                        <tr>
                            <td>
                                辅料费预算金额
                            </td>
                            <td>
                                    ${procedure.accessoriesPrice}元/${procedure.measurementUnitLabel}*${procedure.budgetNumber}=${procedure.auxiliaryMaterialsBudgetAmount}元
                            </td>
                        </tr>

                        <tr>
                            <td>
                                人工费预算金额
                            </td>
                            <td>
                                    ${procedure.laborPrice}元/${procedure.measurementUnitLabel}*${procedure.budgetNumber}=${procedure.laborDudgetAmount}元
                            </td>
                        </tr>
                    </c:if>
                    <!-- 包工 -->
                    <c:if test="${pack.settleStyle == 2}">
                        <tr>
                            <td>
                                人工费预算金额
                            </td>
                            <td>
                                    ${procedure.laborPrice}元/${procedure.measurementUnitLabel}*${procedure.budgetNumber}=${procedure.laborDudgetAmount}元
                            </td>
                        </tr>
                    </c:if>
                    <tr>
                        <td>
                            工料费结算金额
                        </td>
                        <td>
                                ${procedure.synthesizePrice}元/${procedure.measurementUnitLabel}*${procedure.settlementNumber}=${procedure.laborAuxiliaryMaterialsSettleAmount}元
                        </td>
                    </tr>

                    <tr>
                        <td>工序内容</td>
                        <td>${procedure.remarks}</td>
                    </tr>
                    </tbody>
                </table>
            </c:if>
            <c:if test="${procedure.settlementNumber != null}">
                <p>${procedure.procedureName}</p>
                <table>
                    <tbody>
                    <!--包工包料-->
                    <c:if test="${pack.settleStyle == null || pack.settleStyle == 1}">
                        <tr>
                            <td>
                                工料费结算金额
                            </td>
                            <td>
                                    ${procedure.synthesizePrice}元/${procedure.measurementUnitLabel}*${procedure.settlementNumber}=${procedure.laborAuxiliaryMaterialsSettleAmount}元
                            </td>
                        </tr>

                        <tr>
                            <td>
                                辅料费结算金额
                            </td>
                            <td>
                                    ${procedure.accessoriesPrice}元/${procedure.measurementUnitLabel}*${procedure.settlementNumber}=${procedure.auxiliaryMaterialsSettleAmount}元
                            </td>
                        </tr>

                        <tr>
                            <td>
                                人工费结算金额
                            </td>
                            <td>
                                    ${procedure.laborPrice}元/${procedure.measurementUnitLabel}*${procedure.settlementNumber}=${procedure.laborSettleAmount}元
                            </td>
                        </tr>
                    </c:if>
                    <!-- 包工 -->
                    <c:if test="${pack.settleStyle == 2}">
                        <tr>
                            <td>
                                人工费结算金额
                            </td>
                            <td>
                                    ${procedure.laborPrice}元/${procedure.measurementUnitLabel}*${procedure.settlementNumber}=${procedure.laborSettleAmount}元
                            </td>
                        </tr>
                    </c:if>
                    <tr>
                        <td>
                            工料费结算金额
                        </td>
                        <td>
                                ${procedure.synthesizePrice}元/${procedure.measurementUnitLabel}*${procedure.settlementNumber}=${procedure.laborAuxiliaryMaterialsSettleAmount}元
                        </td>
                    </tr>

                    <tr>
                        <td>工序内容</td>
                        <td>${procedure.remarks}</td>
                    </tr>
                    </tbody>
                </table>
            </c:if>
        </c:forEach>
    </div>
</div>
<script type="text/javascript" src="${ctxStatic}/mobile/modules/Worker/js/lib/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="${ctxStatic}/mobile/modules/Worker/js/utils/calcRootFontSize.js"></script>
<script type="text/javascript" src="${ctxStatic}/mobile/modules/Worker/js/utils/history.js"></script>
</body>
</html>--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1, user-scalable=no">
    <meta content="yes" name="apple-mobile-web-app-capable">
    <meta content="black" name="apple-mobile-web-app-status-bar-style">
    <meta content="telephone=no" name="format-detection">
    <meta content="email=no" name="format-detection">
    <title>任务包详情</title>
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Worker/css/reset.css"/>
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Worker/css/header.css"/>
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Worker/css/budget.css"/>
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/Packagebudget.css"/>
</head>

<body>
<div class="">
    <header>
        <a class="back_btn" href="javascript:;" onclick="history.go(-1)"></a>
        <h2 class="title">任务包详情</h2>
    </header>
    <!-- /header -->
    <section class="pad_top11 section">
        <div class="sec_list">
            <div class="sec_title"><h2>任务包基本信息</h2></div>
            <div class="sec_top">
                <p>
                    <span>任务包名称：</span>
                    <span>${pack.packageName}</span>
                </p>
                <p>
                    <span>任务包状态：</span>
                    <span>${fns:getDictLabel(pack.packageStateid, 'taskpackage_status', '')}</span>
                </p>
                <!--包工包料-->
                <c:if test="${pack.settleStyle == null || pack.settleStyle == 1}">
                    <!-- 结算 -->
                    <c:if test="${settleTotalMoney > 0}">
                        <p>
                            <span>工料费结算总金额：</span>
                            <span>${settleTotalMoney}元</span>
                        </p>

                        <p>
                            <span>人工费结算总金额：</span>
                            <span>${laborSettleTotalMoney}元</span>
                        </p>

                        <p>
                            <span>辅料费结算总金额：</span>
                            <span>${auxiliaryMaterialsSettleTotalMoney}元</span>
                        </p>
                    </c:if>
                    <!-- 预算 -->
                    <c:if test="${settleTotalMoney <= 0}">
                        <p>
                            <span>工料费预算总金额：</span>
                            <span>${budgetTotalMoney}元</span>
                        </p>

                        <p>
                            <span>人工费预算总金额：</span>
                            <span>${laborDudgetTotalMoney}元</span>
                        </p>

                        <p>
                            <span>辅料费预算总金额：</span>
                            <span>${auxiliaryMaterialsBudgetTotalMoney}元</span>
                        </p>
                    </c:if>
                </c:if>
                <!-- 包工 -->
                <c:if test="${pack.settleStyle == 2}">
                    <!-- 结算 -->
                    <c:if test="${settleTotalMoney > 0}">
                        <p>
                            <span>人工费结算总金额：</span>
                            <span>${laborSettleTotalMoney}元</span>
                        </p>
                    </c:if>
                    <!-- 预算 -->
                    <c:if test="${settleTotalMoney <= 0}">
                        <p>
                            <span>人工费预算总金额：</span>
                            <span>${laborDudgetTotalMoney}元</span>
                        </p>
                    </c:if>
                </c:if>

                <p>
                    <span>要求工期：</span>
                    <span><fmt:formatDate value="${pack.planStartdate}" pattern="yyyy-MM-dd"/> 至 <fmt:formatDate
                            value="${pack.planEnddate}" pattern="yyyy-MM-dd"/></span>
                </p>
                <p>
                    <span class="sec_place_left">施工地点：</span>
                    <span class="sec_place">${pack.customerMessage }--${pack.customerName}</span>
                </p>
                <p>
                    <span>项目经理：</span>
                    <span>${pack.leaderName}-${pack.leaderPhone}</span>
                    <a href="tel:${pack.leaderPhone}" class="tel"><i></i>拨打电话</a>
                </p>
            </div>
            <div class="sec_title"><h2>任务包工序信息</h2></div>
            <c:forEach items="${procedures }" var="procedure" varStatus="status">
                <c:if test="${procedure.budgetNumber !=0}">
                    <div class="sec_bot">
                        <h6>${status.index+1}、${procedure.procedureName}</h6>
                        <!-- 预算 -->
                        <c:if test="${procedure.settlementNumber == null}">
                            <!--包工包料-->
                            <c:if test="${pack.settleStyle == null || pack.settleStyle == 1}">
                                <p>
                                    <span>工料费预算金额：</span>
                                    <span> ${procedure.synthesizePrice}元/${procedure.measurementUnitLabel}*${procedure.budgetNumber}=${procedure.laborAuxiliaryMaterialsBudgetAmount}元</span>
                                </p>

                                <p>
                                    <span>人工费预算金额：</span>
                                    <span>${procedure.laborPrice}元/${procedure.measurementUnitLabel}*${procedure.budgetNumber}=${procedure.laborDudgetAmount}元</span>
                                </p>

                                <p>
                                    <span>辅料费预算金额：</span>
                                    <span>${procedure.accessoriesPrice}元/${procedure.measurementUnitLabel}*${procedure.budgetNumber}=${procedure.auxiliaryMaterialsBudgetAmount}元</span>
                                </p>

                            </c:if>
                            <!--包工-->
                            <c:if test="${pack.settleStyle == 2}">
                                <p>
                                    <span>人工费预算金额：</span>
                                    <span>${procedure.laborPrice}元/${procedure.measurementUnitLabel}*${procedure.budgetNumber}=${procedure.laborDudgetAmount}元</span>
                                </p>
                            </c:if>
                        </c:if>
                        <!-- 结算 -->
                        <c:if test="${procedure.settlementNumber != null}">
                            <!--包工包料-->
                            <c:if test="${pack.settleStyle == null || pack.settleStyle == 1}">
                                <p>
                                    <span>工料费结算金额：</span>
                                    <span> ${procedure.synthesizePrice}元/${procedure.measurementUnitLabel}*${procedure.settlementNumber}=${procedure.laborAuxiliaryMaterialsSettleAmount}元</span>
                                </p>

                                <p>
                                    <span>人工费结算金额：</span>
                                    <span>${procedure.laborPrice}元/${procedure.measurementUnitLabel}*${procedure.settlementNumber}=${procedure.laborSettleAmount}元</span>
                                </p>

                                <p>
                                    <span>辅料费结算金额：</span>
                                    <span>${procedure.accessoriesPrice}元/${procedure.measurementUnitLabel}*${procedure.settlementNumber}=${procedure.auxiliaryMaterialsSettleAmount}元</span>
                                </p>
                            </c:if>
                            <!--包工-->
                            <c:if test="${pack.settleStyle == 2}">
                                <p>
                                    <span>人工费结算金额：</span>
                                    <span>${procedure.laborPrice}元/${procedure.measurementUnitLabel}*${procedure.settlementNumber}=${procedure.laborSettleAmount}元</span>
                                </p>
                            </c:if>
                        </c:if>
                        <p class="last_bot">
                            <span>工序内容：</span>
                            <span class="last_info">${procedure.remarks}</span>
                        </p>
                    </div>
                </c:if>
            </c:forEach>
        </div>
    </section>
    <div style="padding-bottom:300px;"></div>
</div>
<script type="text/javascript" src="${ctxStatic}/mobile/modules/Worker/js/lib/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="${ctxStatic}/mobile/modules/Worker/js/utils/calcRootFontSize.js"></script>
<script type="text/javascript" src="${ctxStatic}/mobile/modules/Worker/js/utils/history.js"></script>
</body>

</html>