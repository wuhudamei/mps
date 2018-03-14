<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="initial-scale=1, maximum-scale=1,minimum-scale=1.0,user-scalable=no">
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/forward/base.css">
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/forward/progessWarning.css">
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/new/common.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/new/list.css" />
	<title>进度预警明细</title>
</head>
<body>
	<div class="progress-warning">
		<header class="qustion-header col-fff font-36 height88 bgheader tc"><a href="${ctx}/app/manager/progressWarning/list"><span class="icon_back"></a></span>进度预警明细</header>
		<div class="process-content mt30 bg-fff pl30 pr30 mb30">
			<h3 class="font-34 col-font-b pb30 pt30 bor-b1 ">
			<c:if test="${purchaseType == '1' }">
				辅材
			</c:if>
			<c:if test="${purchaseType == '5' }">
				瓷砖
			</c:if>
			进场</h3>
			<ul class="col-666 font-28 enter-cont">
				<li class="clearfix pb30"><p class="fl">已延期订单</p><span class="col-font-r fr">${fn:length(list)}个</span></li>
			</ul>
		</div>
		<div class="bg-fff  mb30">
			<h3 class="col-333 font-34 posr expected-order cursor pr30 pl30 h90">延期订单
				<span class="col-999 font-28 ml4">/</span>
				<i class="col-font-r font-28 ml4">${fn:length(list)}个</i>
				<span class="icon_down">
				</span>
			</h3>
			<div class="expected-conent"/>
				<div class="shadow_border"></div>
				<c:forEach items="${list }" var = "backlog">
					<div class="sec font0   mar_btm24 bg_f border_e6 clearfix">
						<ul class="pad_top3 pad_left3 pad_bot3">
							<li class="mar_btm24 clearfix">
								<span class="col_grey font28 flo_left spanRgt">顾 客 ：</span>
								<p class="font28 col_3 pad_rgt3 flow"> ${backlog.communityName}${backlog.buildNumber}-${backlog.buildUnit}-${backlog.buildRoom}-${backlog.customerName}</p>
							</li>
							<li class="mar_btm24 clearfix">
								<span class="col_grey font28 flo_left spanRgt">计划日期 ：</span>
								<p class="font28 col_3 pad_rgt3 flow"><fmt:formatDate value="${backlog.planDoneDate}" pattern="yyyy-MM-dd"/></p>
							</li>
							<li class="mar_btm24 clearfix">
								<span class="col_grey font28 flo_left spanRgt">延期天数 ：</span>
								<p class="font28 col_3 pad_rgt3 flow"> ${backlog.extensionDays}天</p>
							</li>
						</ul>
					</div>
				</c:forEach>
			</div
		</div>
	</div>
	<div style="padding-bottom:300px;"></div>
</body>
<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/calcRootFontSize.js"></script>
<script>
	function showHide() {
		$('.expected-order').on('click',function () {
			var showCont = $(this).parents('div').children('.expected-conent');
			showCont.toggle();
			if(!showCont.is(":hidden")){
				$(this).find('.icon_down').removeClass('icon-left');
			} else {
				$(this).find('.icon_down').addClass('icon-left');
			}
		})
	}
	showHide();

</script>
</html>