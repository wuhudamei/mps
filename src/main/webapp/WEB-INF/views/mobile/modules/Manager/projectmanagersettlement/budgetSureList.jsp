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
	<title>结算单金额确认列表</title>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/1new/reset.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/1new/header.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/1new/common.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/1new/allBtn.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/1new/list.css"/>
	
	
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/1new/mask.css" />
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/1new/allBtn.css" />
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/1new/account.css" />
</head>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/calcRootFontSize.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		var v = '${myflag}';
		if(v != ''){
			$("#agree").show();
		}
		
	});
	function comfim1(){
		
		$("#agree").hide();
	}
</script>
<body>
	<div class="font0">
		<header>
			<a class="back_btn" href="${ctx }/app/manager/toQueryPmGuaranteeMoneyLog"></a>
			<h2 class="title">结算单金额确认列表</h2>
		</header><!-- /header -->
		<section class="pt112">
		<c:forEach items="${list}" var="settleBill">
			<div class="sec font0 border_top border_btm mb30 bg_f shadow clearfix">
				<ul class="pad_top3 pad_left3 bor_dashed">
					<li class="mb30 clearfix">
						<span class="col_grey font30 flo_left pl1em">顾客信息：</span>
						<p class="font30 col_3 pad_rgt3 flow">${settleBill.communityName }-${settleBill.buildNumber }-${settleBill.buildUnit }-${settleBill.buildRoom }-${settleBill.customerName }</p>
					</li>
					<li class="mb30 clearfix">
						<span class="col_grey font30 flo_left pl1em">结算阶段：</span>
						<p class="font30 col_3 pad_rgt3 flow">
						<c:if test="${settleBill.settleBillType == 1}">
							中期结算
						</c:if>
						<c:if test="${settleBill.settleBillType == 2}">
							竣工结算
						</c:if>
						</p>
					</li>
					<li class="mb30 clearfix">
						<span class="col_grey font30 flo_left pl1em">下发时间：</span>
						<p class="font30 col_3 pad_rgt3 flow">${settleBill.statusDatetime }</p>
					</li>
					<li class="mb30 clearfix">
						<span class="col_grey font30 flo_left">结算单金额：</span>
						<p class="font30 col_3 pad_rgt3 flow">${settleBill.realSettleAmount }元</p>
					</li>
					<li class="mb30 clearfix">
						<span class="col_grey font30 flo_left">结算单状态：</span>
						<p class="font30 col_blue pad_rgt3 flow">${settleBill.statusDescribe }</p>
					</li>
				</ul>
				<div class="btn_wrapper clearfix">
					<a class="btnBlueBg" href="${ctx }/app/manager/projectManagerSettlement/confirmAccountEnd?id=${settleBill.id }&settleBillType=${settleBill.settleBillType}">确认结算单</a>
				</div>
			</div>
		</c:forEach>
		</section>
		<div style="padding-bottom:300px;"></div>
		
		<div class="g-mask undis" style="text-align:center;" id="agree">
		<div class="alertMask">
			<div class="maskWrapper">
				<p class="col_3 maskTit">通知</p>
				<div class="font28 col_6 maskContent">该结算单已同意或拒绝，请不要重复操作！</div>
				<div class="maskBtns twoBtns clearfix">
					<a class="maskBtn font33 col_f" onclick="comfim1()">确定</a>
				</div>
			</div>
		</div>
	</div>
	</div>
	
</body>
</html>