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
	<title>申请辅料</title>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/reset.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/header.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Worker/css/globalUtil2.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/auxiliary_apply.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/new1/common.css"/>
	<style>
		.alertMask{position: fixed;top: 0;bottom: 0;left: 0;right: 0;background: rgba(0,0,0,.5);}
		.maskWrapper{width: 84%;margin: 30% auto 0;border-radius: .1rem;background: #fff;font-size: .32rem;}
		.col_3{color: #333}
		.col_6{color: #666;}
		.col_f{color: #fff;}
		.col_fdfcfa{color: #fdfcfa;}
		.col_0780ec{color: #0780ec;}
		.font28{font-size: .28rem;}
		.font33{font-size: .33rem;}
		.maskTit{height: 1rem;line-height: 1rem;text-align: center;}
		.maskContent{padding: .5rem;border-top: 1px solid #ddd;border-bottom: 1px solid #ddd;line-height: 1.5em;}
		.maskBtns{width: 83%;margin: 0 auto;padding-bottom: .2rem;padding-top: .2rem;}
		.maskBtn{background: #0780ec;border-radius: .1rem;display: block;width: 47.6%;
    		text-align: center;line-height: .76rem;font-size:.33rem;margin: 0 auto;}
    	.twoBtns .maskBtn:first-child{background: #0780ec;border-radius: .1rem;float: left;}
		.twoBtns .maskBtn:last-child{border: 1px solid #0780ec;box-sizing: border-box;border-radius: .1rem;float: right;background: #fff;}
	</style>
</head>
<body>
	<div class="g-sign">
		<header>
			<a class="back_btn" href="${ctx }/app/manager/meterialManagementList"></a>
			<h2 class="title">申请辅料</h2>
		</header><!-- /header -->
		<section class="sign_list">
			<c:forEach items="${orderList }" var="order" >
				<c:if test="${ order.isScrap eq 0 }">
					<ul class="clearfix shadow">
						<li class="clearfix">
							<span>顾客信息：</span>
							<p>${order.communityName }-${order.buildNumber }-${order.buildUnit }-${order.buildRoom }-${order.customerName }</p>
						</li>
						<li class="clearfix">
							<span>合同开工：</span>
							<p><fmt:formatDate value="${order.contractStartDate }" pattern="yyyy-MM-dd"/></p>
						</li>
						<li class="clearfix">
							<span>实际开工：</span>
							<p><fmt:formatDate value="${order.actualStartDate }" pattern="yyyy-MM-dd"/></p>
						</li>
						<li class="clearfix">
							<span>合同工期：</span>
							<p>${order.contractTime} 天</p>
						</li>
						<li class="clearfix">
							<span>订单状态：</span>
							<p class="">${order.orderStatus }</p>
						</li>
						<div class="btns clearfix">
							<input type="text" hidden = "hidden" id="customerName${order.id}" value="${order.customerName}">
							<c:if test="${order.projectMode eq 1 || order.projectMode eq 4 }">
								<c:if test="${order.orderNumber >= 200 || order.allCount > 0 }">
									<a class="btn" onclick="auxiliaryApply('${order.id}')" >辅料申请</a>
								</c:if>
								<c:if test="${order.orderNumber < 200 && order.allCount == 0}">
									<a class="btn" href="javascript:void(0)" onclick="applyAuxiliary()">辅料申请</a>
								</c:if>
							</c:if>
							<c:if test="${order.projectMode ne 1 && order.projectMode ne 4 }">
									<a class="btn" onclick="auxiliaryApply('${order.id}')" >辅料申请</a>
							</c:if>
							<a class="btn" href="${ctx}/app/manager/auxiliaryApplyRecord/record?orderId=${order.id}">申请记录</a>
						</div>
					</ul>
				</c:if>
				<c:if test="${ order.isScrap eq 1 }">
					<ul class="clearfix shadow abandon">
						<li class="clearfix">
							<span>顾客信息：</span>
							<p>${order.communityName }-${order.buildNumber }-${order.buildUnit }-${order.buildRoom }-${order.customerName }</p>
						</li>
						<li class="clearfix">
							<span>合同开工：</span>
							<p><fmt:formatDate value="${order.contractStartDate }" pattern="yyyy-MM-dd"/></p>
						</li>
						<li class="clearfix">
							<span>实际开工：</span>
							<p><fmt:formatDate value="${order.actualStartDate }" pattern="yyyy-MM-dd"/></p>
						</li>
						<li class="clearfix">
							<span>合同工期：</span>
							<p>${order.contractTime} 天</p>
						</li>
						<li class="clearfix">
							<span>订单状态：</span>
							<p class="">${order.orderStatus }</p>
						</li>
						<div class="btns clearfix">
							<a class="btn" href="${ctx}/app/manager/auxiliaryApplyRecord/record?orderId=${order.id}">申请记录</a>
						</div>
					</ul>
				</c:if>
			</c:forEach>
		   
		</section>
		
		<div class="alertMask undis" id ="alert">
			<div class="maskWrapper">
				<p class="col_3 maskTit">通知</p>
				<div class="font28 col_6 maskContent" id="alertRemarks"></div>
				<div class="maskBtns clearfix">
					<a class="maskBtn font33 col_f"  onclick="sure()" href="javascript:;">我知道了</a>
				</div>
			</div>
		</div>
		
		<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/jquery-2.1.1.min.js"></script>
		<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/calcRootFontSize.js"></script>
		<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/history.js"></script>
		<script type="text/javascript" src="${ctxStatic}/mobile/modules/Worker/js/utils/global.js"></script>
	
		<script type="text/javascript">
	
			function applyAuxiliary(){
				$("#alertRemarks").text("请先到进度管理中点击确认开工后,才可以申请辅料");
				$("#alert").show();
			}

			//关闭提示弹框
			function sure(){
				$("#alert").hide();
			}
		
			//申请辅料
			function auxiliaryApply(orderId){

                var customerName = $("#customerName"+orderId).val();

				 $.ajax({
						url: "${ctx}/app/manager/auxiliary/applyAuxiliary_data_check_ajax",
						type: "post",
						data: {
							orderId:orderId
						 },
						success: function(data){

							if(data!=null && data=="0"){
								window.location.href = "${ctx}/app/manager/auxiliary/auxiliarychoose?orderId="+orderId;
							}else if(data=="1"){
								$("#alertRemarks").text("订单id为空");
								$("#alert").show();
							}else if(data=="2"){
								$("#alertRemarks").text("项目经理未登录");
								$("#alert").show();
							}else if(data=="3"){
								$("#alertRemarks").text("“"+customerName+"”家的基础装修质检员已确认验收了，不允许再申请辅料了。");
								$("#alert").show();
							}else if(data=="4"){
								$("#alertRemarks").text("您有未阅读的辅料采购单，请到【申请记录】去查阅后再申请辅料");
								$("#alert").show();
							}else if(data=="5"){
								$("#alertRemarks").text("同一个订单两次辅料申请操作时间必须间隔5分钟，请过5分钟后再申请");
								$("#alert").show();
							}
						}
				 })
			}
		</script>
	</div>
</body>
</html>