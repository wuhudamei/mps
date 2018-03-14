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
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/pqc/css/newCommon/search.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/pqc/css/newCommon/list.css"/>
</head>
<body>

	<div class="font0">
		<header>
			<a class="back_btn" href="${ctx}/app/pqc/indexMine"></a>
			<h2 class="title">约检计划</h2>
		</header><!-- /header -->
		<section class="pad_top11">
			<%--<div class="mar_btm14 font0 search-area">--%>
				<%--<input class="search-box" type="text" placeholder="小区名称、客户姓名或项目经理">--%>
				<%--<a class="search-btn" href="javascript:;"></a>--%>
			<%--</div>--%>
			<%--<div class="sec font0 border_top border_btm mar_btm24 bg_f clearfix">--%>
				<%--<ul class="pad_top3 pad_left3 bor_dotted">--%>
					<%--<li class="mar_btm24 clearfix">--%>
						<%--<span class="col_grey font28 flo_left spanRgt">顾客：</span>--%>
						<%--<p class="font28 col_3 pad_rgt3 flow">莲花里-5-5-1-张三丰莲花里-5-5-1-张三丰莲花里-5-5-1-张三丰</p>--%>
					<%--</li>--%>
					<%--<li class="mar_btm24 clearfix">--%>
						<%--<span class="col_grey font28 flo_left spanRgt">客户手机：</span>--%>
						<%--<p class="font28 col_3 pad_rgt3 flow">13010102020</p>--%>
					<%--</li>--%>
					<%--<li class="mar_btm24 clearfix">--%>
						<%--<span class="col_grey font28 flo_left spanRgt">项目经理：</span>--%>
						<%--<p class="font28 col_3 pad_rgt3 flow">韩振刚-13010102020</p>--%>
					<%--</li>--%>
					<%--<li class="mar_btm24 clearfix">--%>
						<%--<span class="col_grey font28 flo_left spanRgt">实际开工日期：</span>--%>
						<%--<p class="font28 col_3 pad_rgt3 flow">2016-10-05</p>--%>
					<%--</li>--%>
				<%--</ul>--%>
				<%--<div class="clearfix">--%>
					<%--<a class="one_btn" href="javascript:;">约检计划</a>--%>
				<%--</div>--%>
			<%--</div>--%>

		</section>
		<div style="padding-bottom:300px;"></div>
	</div>

	<script type="text/javascript" src="${ctxStatic}/mobile/modules/pqc/js/utils/calcRootFontSize.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/pqc/js/utils/history.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/pqc/js/applyCheckBase/applyCheckPlan/applyCheckPlanList.js"></script>
	<script type="text/javascript">
        serachOrderInfoBytext();

	</script>
</body>
</html>