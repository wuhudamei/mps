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
	<title>发表评价</title>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/pqc/css/reset.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/pqc/css/globalUtil.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/pqc/css/header.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/pqc/css/common.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/pqc/css/comment.css"/>
</head>
<body>
	<div class="">
		<header>
			<a class="back_btn" href="${ctx}/app/pqc/evaluate/evalWorker/list?text=${text }"></a>
			<h2 class="title">发表评价</h2>
		</header><!-- /header -->
		<form id="jvForm" action="" method="post" enctype="multipart/form-data">
			<input type="text" hidden="hidden" id="text" name="text" value="${text }">
			<input type="text" hidden="hidden" id="evalTaskpackScoreId" name="evalTaskpackScoreId" value="${evaluateWorker.evalTaskpackScoreId }">
			<section class="pad_top8">
				<p class="font0 locate">
					<i class="location-icon"></i><span class="font30 col_blue1 location-text">${evaluateWorker.communityName}-${evaluateWorker.buildNumber}-${evaluateWorker.buildUnit}-${evaluateWorker.buildRoom}-${evaluateWorker.customerName}</span>
				</p>
				<div class="work-sec font0 bg_f pad_btm24 mar_btm24 border_btm">
					<p class="person font32 col_3">工人组长：${evaluateWorker.groupRealname}</p>
					<ul class="pad_top3 pad_left3">
						<c:forEach items="${list }" var="every" varStatus="status">
							<li class="mar_btm46 clearfix a">
								<input type="text" hidden="hidden" name="evalActivityIndexId" value="${every.id }">
								<input type="text" hidden="hidden" name="evalTotalScore" value="${every.evalTotalScore }">
								<span class="col_3 font28 flo_left mar_ght32 left-small">${every.indexName }</span>
								<p class="flow star-p">
									<span class="star1"></span>
									<span class="star1"></span>
									<span class="star1"></span>
									<span class="star1"></span>
									<span class="star1"></span>
								</p>
								<input name="number" type="text" hidden="hidden">
							<!-- 	
								<em class="must">*</em> -->
							</li>
						</c:forEach>
					</ul>
					<div class="advise-area font0 col_9">
						<textarea class="advise" name="advise" maxlength="100" placeholder="请输入您的反馈意见"></textarea>
						<p class="limite font24 col_9"><span class="limite-num">100</span>字</p>
					</div>
				</div>
			</section>
		</form>
		<a class="one_btn" href="javascript:;">发表评价</a>
	</div>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/pqc/js/lib/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/pqc/js/utils/calcRootFontSize.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/pqc/js/utils/history.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/pqc/js/utils/global.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Worker/js/lib/jquery.form.js"></script>
	<script>
		$(function(){
			$(document).on('click','.star-p span',function(){
				$(this).prevAll().addClass('star2');
				if($(this).hasClass('star2')){
					$(this).nextAll().removeClass('star2').addClass('star1');
				}else{
					$(this).addClass('star2');
				}
				var shuliang = $(this).parent().find('.star2').size();
				$(this).parent().next('input').attr("value",shuliang);
			});
			$(".one_btn").bind('click',evalSubmit);
		}());
		
		function evalSubmit(){
			
			var els1 =document.getElementsByName("number");
			for (var i = 0, j = els1.length; i < j; i++){
				var role = els1[i].value;
				if(null==role || role=="" || role<1){
					globalUtil.fn.alert('您有未评价的项目！',2.0);
					return false;
				}
			}
			
			var advise = $(".advise").val();
		/* 	if(null==advise.trim() || advise.trim()==""){
				globalUtil.fn.alert('请输入您的反馈意见！',2.0);
				return false;
			} */
			$(".one_btn").css("color","#CCC");
			$(".one_btn").unbind("click");
			var text = $("#text").val();
			var options ={
					url : "${ctx}/app/pqc/evaluate/evalWorker/evaluate",
					type: "post",
					success : function(data){
						if(data==0){
							globalUtil.fn.alert("评价成功...",2.0);
							var a ="${ctx}/app/pqc/evaluate/evalWorker/list?text="+text;
							setTimeout('window.location.href="'+a+'"', 2000);	
						}
						if(data==1){
							globalUtil.fn.alert("评价失败,已超过评价时间...",2.0);
							var b ="${ctx}/app/pqc/evaluate/evalWorker/list?text="+text;
							setTimeout('window.location.href="'+b+'"', 2000);	
						}
					}
				}
			//ajax提交form
			$("#jvForm").ajaxSubmit(options);
			
		}
	</script>
</body>
</html>