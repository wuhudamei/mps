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
	<title>订单竣工结算单明细</title>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/1new/reset.css" />
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/1new/header.css" />
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/lib/mobiscroll.custom-2.16.1.min.css">
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/1new/common.css" />
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/1new/allBtn.css" />
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/1new/account.css" />
</head>
<body>
	<div class="font0">
		<header>
			<a class="back_btn" href="javascript:;"></a>
			<h2 class="title">订单竣工结算单明细</h2>
		</header>
		<section class="pad_top88">
			<p class="pt32 pb20 pl30">
				<span class="font30 col_blue pl20 blue_bar">基本信息</span>
			</p>
			<div class="sec pl30 pr30 font0 bor_top_ddd bor_btm_ddd bg_f clearfix">
				<ul class="pt30 pb30">
					<li class="mb30 clearfix">
						<span class="col_grey font30 fl pl1em">顾客信息：</span>
						<p class="font30 col_3 flow">合顺家园合顺家园合顺家园-5-1-102-张三丰</p>
					</li>
					<li class="mb30 clearfix">
						<span class="col_grey font30 fl pl1em">合同面积：</span>
						<p class="font30 col_3">75m<sup>2</sup></p>
					</li>
					<li class="mb30 clearfix">
						<span class="col_grey font30 fl">结算单编号：</span>
						<p class="font30 col_3 flow">JS20714589653</p>
					</li>
					<li class="mb30 clearfix">
						<span class="col_grey font30 fl pl1em">结算阶段：</span>
						<p class="font30 col_3 flow">中期结算</p>
					</li> 
					<li class="mb30 clearfix">
						<span class="col_grey font30 fl">结算单状态：</span>
						<p class="font30 col_3 flow">已下发结算单给项目经理</p>
					</li>
				</ul>
			</div>
		</section>
		<section class="">
			<p class="pt32 pb20 pl30">
				<span class="font30 col_blue pl20 blue_bar">结算类目金额</span>
			</p>
			<div class="sec font0 bor_top_ddd bor_btm_ddd bg_f clearfix">
				<ul class="tableUl3">
					<li class="clearfix">
						<span class="col_6 font30 fl">承包总价：</span>
						<span class="col_3 font30 fl">+8000.0元</span>
					</li>
					<li class="clearfix">
						<span class="col_6 font30 fl">竣工质检罚款：</span>
						<span class="col_3 font30 fl">-100.0元</span>
					</li> 
					<li class="clearfix">
						<span class="col_6 font30 fl">质保金：</span>
						<span class="col_3 font30 fl">-100.0元</span>
					</li>
					<li class="clearfix">
						<span class="col_6 font30 fl">远程费：</span>
						<span class="col_3 font30 fl">-100.0元</span>
					</li>
					<li class="clearfix">
						<span class="col_6 font30 fl">竣工奖励：</span>
						<span class="col_3 font30 fl">+100.0元</span>
					</li>
					<li class="clearfix">
						<span class="col_6 font30 fl">竣工扣款：</span>
						<span class="col_3 font30 fl">-100.0元</span>
					</li>
					<li class="clearfix">
						<span class="col_6 font30 fl">竣工变更增项：</span>
						<span class="col_3 font30 fl">+100.0元</span>
					</li>
					<li class="clearfix">
						<span class="col_6 font30 fl">竣工变更减项：</span>
						<span class="col_3 font30 fl">-100.0元</span>
					</li>
					<li class="clearfix">
						<span class="col_6 font30 fl">竣工承包价结算金额：</span>
						<span style="padding-left:0;" class="col_3 font30 fl">3000.0*60%=+1800.0</span>
					</li>
				</ul>
				<p class="totalP">
					<span class="font30 col_cd0707">竣工实发结算金额：</span>
					<span class="font40 col_cd0707 pl10">￥2000</span>
				</p>
			</div>
			<div>
				<p class="font28 col_c30606 pl30 pr30 pt30 pb30">结算说明：</p>
				<p class="font26 col_c30606 pl30 pr30 lineHgt18">中期承包价结算金额  = 【承包总价 + 基装增项 - 辅料 - 沙子水泥 - 标化材料 - 开关面板 - 工人人工费   】* 60%</p>
				<p class="font26 col_c30606 pl30 pr30 lineHgt18 pt36">中期实际结算金额  =  中期承包价结算金额 + 中期奖励 + 材料搬运及运输费 + 中期变更增项 - 中期质检罚款 - 中期变更减项  - 中期扣款</p>
			</div>
		</section>
		<div style="padding-bottom:200px;"></div>
	</div>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/calcRootFontSize.js"></script>
</body>
</html>