<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
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
                    <p>
                        <span>人工费预算总金额：</span>
                        <span>${pack.laborBudgetAmount}元</span>
                    </p>
                    <p>
                        <span>要求工期：</span>
                        <span><fmt:formatDate value="${pack.planStartdate}" pattern="yyyy-MM-dd"/> 至 <fmt:formatDate value="${pack.planEnddate}" pattern="yyyy-MM-dd"/></span>
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
                 <c:forEach items="${procedureList }" var="procedure" varStatus="status">
					<c:if test="${procedure.budgetNumber !=0}">
		                 <div class="sec_bot">
		                    <h6>${status.index+1}、${procedure.procedureName}</h6>
		                    <p>
		                        <span>人工费预算金额：</span><span>${procedure.laborPrice}元/${procedure.unit}*${procedure.budgetNumber}=${procedure.laborBudgetAmount}元</span>
		                    </p>
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