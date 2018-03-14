<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/mobile/modules/pqc/applyCheckBase/pqcBaseJsp.jsp"%>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1, user-scalable=no">
	<meta content="yes" name="apple-mobile-web-app-capable">
	<meta content="black" name="apple-mobile-web-app-status-bar-style">
	<meta content="telephone=no" name="format-detection">
	<meta content="email=no" name="format-detection">
	<title>约检计划</title>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/pqc/css/reset.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/pqc/css/newCommon/common.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/pqc/css/newCommon/header.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/pqc/css/newDatecheck/dateCheckRecordDetails.css"/>
</head>
<body>
	<div class="font0">
		<header>
			<a class="back_btn" onclick="history.go(-1)"></a>
			<h2 class="title">约检计划</h2>
		</header><!-- /header -->
		<section class="pad_top88">
			<p class="font30 col_6 pl30 pr30 pt26 pb34 align_ct">${orderInfo}</p>
			<ul class="scrollSec">
				
				<c:forEach items="${list}" var="item" varStatus="status">

					<c:if test="${not empty item.isChecked}">
					<li class="scrollLi">
						<span class="blueRnd">${status.index+1}</span>
						<div class="">
							<p class="font30 col_blue pb14 pl60 pr30 pt28 borTop borRgt bg_f">${item.qcNodeName}</p>
							<p class="font26 col_blue pb30 pl60 pr30 borBtm borRgt bg_f">计划完成日期：${item.planCheckDate}</p>
						</div>
					</li>
					</c:if>

					<c:if test="${ empty item.isChecked}">

						<li class="scrollLi greyLi">
							<span class="greyRnd">${status.index+1}</span>
							<div class="">
								<p class="font30 col_6 pb14 pl60 pr30 pt28 borTop borRgt bg_f">${item.qcNodeName}</p>
								<p class="font26 col_6 pb30 pl60 pr30 borBtm borRgt bg_f">计划完成日期：${item.planCheckDate}</p>
							</div>
						</li>

					</c:if>

					
				</c:forEach>
				<%--<li class="scrollLi">--%>
					<%--<span class="blueRnd">1</span>--%>
					<%--<div class="">--%>
						<%--<p class="font30 col_blue pb14 pl60 pr30 pt28 borTop borRgt bg_f">闭水实验和瓦工隐蔽工程验收</p>--%>
						<%--<p class="font26 col_blue pb30 pl60 pr30 borBtm borRgt bg_f">计划完成日期：2016-08-12</p>--%>
					<%--</div>--%>
				<%--</li>--%>
				<%--<li class="scrollLi">--%>
					<%--<span class="blueRnd">2</span>--%>
					<%--<div class="">--%>
						<%--<p class="font30 col_blue pb14 pl60 pr30 pt28 borTop borRgt bg_f">闭水实验和瓦工隐蔽工程验收闭水实验 和瓦工隐蔽工程验收</p>--%>
						<%--<p class="font26 col_blue pb30 pl60 pr30 borBtm borRgt bg_f">计划完成日期：2016-08-12</p>--%>
					<%--</div>--%>
				<%--</li>--%>
				<%--<li class="scrollLi">--%>
					<%--<span class="blueRnd">3</span>--%>
					<%--<div class="">--%>
						<%--<p class="font30 col_blue pb14 pl60 pr30 pt28 borTop borRgt bg_f">闭水实验和瓦工隐蔽工程验收</p>--%>
						<%--<p class="font26 col_blue pb30 pl60 pr30 borBtm borRgt bg_f">计划完成日期：2016-08-12</p>--%>
					<%--</div>--%>
				<%--</li>--%>
				<%--<li class="scrollLi">--%>
					<%--<span class="blueRnd">4</span>--%>
					<%--<div class="">--%>
						<%--<p class="font30 col_blue pb14 pl60 pr30 pt28 borTop borRgt bg_f">闭水实验和瓦工隐蔽工程验收</p>--%>
						<%--<p class="font26 col_blue pb30 pl60 pr30 borBtm borRgt bg_f">计划完成日期：2016-08-12</p>--%>
					<%--</div>--%>
				<%--</li>--%>
				<%--<li class="scrollLi greyLi">--%>
					<%--<span class="greyRnd">5</span>--%>
					<%--<div class="">--%>
						<%--<p class="font30 col_6 pb14 pl60 pr30 pt28 borTop borRgt bg_f">闭水实验和瓦工隐蔽工程验收</p>--%>
						<%--<p class="font26 col_6 pb30 pl60 pr30 borBtm borRgt bg_f">计划完成日期：2016-08-12</p>--%>
					<%--</div>--%>
				<%--</li>--%>
				<%--<li class="scrollLi greyLi">--%>
					<%--<span class="greyRnd">6</span>--%>
					<%--<div class="">--%>
						<%--<p class="font30 col_6 pb14 pl60 pr30 pt28 borTop borRgt bg_f">闭水实验和瓦工隐蔽工程验收</p>--%>
						<%--<p class="font26 col_6 pb30 pl60 pr30 borBtm borRgt bg_f">计划完成日期：2016-08-12</p>--%>
					<%--</div>--%>
				<%--</li>--%>
			</ul>
		</section>
		<div style="padding-bottom:300px;"></div>
	</div>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/pqc/js/utils/calcRootFontSize.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/pqc/js/utils/history.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/pqc/js/applyCheckBase/applyCheckPlan/applyCheckPlanDetails.js"></script>
</body>
<script type="text/javascript">

    applyCheckPlanDetails();

</script>
</html>