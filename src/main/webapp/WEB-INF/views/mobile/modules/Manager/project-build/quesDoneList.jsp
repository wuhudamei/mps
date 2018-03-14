<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1, user-scalable=no">
	<meta content="yes" name="apple-mobile-web-app-capable">
	<meta content="black" name="apple-mobile-web-app-status-bar-style">
	<meta content="telephone=no" name="format-detection">
	<meta content="email=no" name="format-detection">
	<title>问题上报</title>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/reset.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/header.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/quesDoneList.css"/>
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
		.maskBtn{display: block;width: 60%;text-align: center;line-height: .76rem;}
		.maskBtn{background: #0780ec;border-radius: .1rem;display: block;width: 47.6%;
    		text-align: center;line-height: .76rem;font-size:.33rem;margin: 0 auto;}
	</style>
</head>
<body>
	<div class="g-build_apply">
		<header>
			<a class="back_btn" href="${ctx }/app/manager/problem/list"></a>
			<h2 class="title">问题上报</h2>
		</header><!-- /header -->
		<p class="every">${order.communityName }-${order.buildNumber }-${order.buildUnit }-${order.buildRoom }-${order.customerName }</p>
		<p class="work_date">实际开工日期：<fmt:formatDate value="${order.actualStartDate }" pattern="yyyy-MM-dd"/></p>
		<section class="build_apply_list">
			<ul class="shadow">
				<li class="clearfix">
					<span>安装项名称</span>
	          		<span>期望进场日期</span>
					<span>操作</span>
				</li>
				<c:forEach items="${list }" var="install">
					<li class="clearfix">
						<span>${install.installItemName }</span>
		          		<span><fmt:formatDate value="${install.applyIntoDate }" pattern="yyyy-MM-dd"/></span>
		          		<span class="apply"><a class="block" href="#" onclick="problem('${install.id}','${install.orderId}','${install.installItemName}')">上报</a></span>
					</li>
				</c:forEach>
			</ul>
		</section>
	</div>
	
	
	
	<div style="background:rgba(0,0,0,.4);z-index:9;" class="g-mask undis" id ="subReport">
		<div class="alertMask">
			<div class="maskWrapper">
				<p class="col_3 maskTit">通知</p>
				<div class="font28 col_6 maskContent" id="messageContext">您刚刚提交过问题上报，请耐心等待五分钟后再次操作</div>
				<div class="maskBtns clearfix">
					<a class="maskBtn font33 col_f"  onclick="sendMessage()" href="javascript:;">我知道了</a>
				</div>
			</div>
		</div>
	</div>
	
	
	
	
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/calcRootFontSize.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/history.js"></script>
	<script type="text/javascript">
	
		function sendMessage(){
			$("#subReport").hide();
		}
	
	
		//问题上报
		function problem(id,orderId,installItemName){
			var businessType = "1";
			//校验5分钟内的重复提交
			 $.ajax({
		            url: "${ctx}/app/manager/problem/wallAndFloor/problem_wallAndFloor_ajax_time",
		            type: "post",
		            data: {
		            		orderId:id,
		            		businessType:businessType
		            	 },
		            success: function(data){
		            	if(null!=data && data == "0"){
		            		
		            		window.location.href = "${ctx }/app/manager/problem/question-submit?id="+id+"&orderId="+orderId+"&installItemName="+installItemName;
		            	
		            	}else if(data == "1"){
		            		$("#messageContext").text("安装项id传送失败");
		            		$("#subReport").show();
		            	}else{
		            		$("#messageContext").text("您刚刚提交过问题上报，请耐心等待五分钟后再次操作");
		            		$("#subReport").show();
		            	}
		            }
			 })
		}
	
	
	
	
	
	
	
	
	
	</script>
</body>
</html>