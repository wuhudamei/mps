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
	<title>催促评价</title>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Worker/css/new1/reset.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Worker/css/new1/header.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Worker/css/new1/common.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Worker/css/new1/search.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/new1/mask.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Worker/css/new1/allBtn.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Worker/css/new1/applyDoneList.css"/>
</head>
<body>
	<div class="font0">
		<header>
		<c:if test="${isLeader == 0 }">
			<a class="back_btn" href="${ctx }/app/worker/toindex"></a>
		</c:if>
		<c:if test="${isLeader == 1 }">
			<a class="back_btn" href="${ctx }/app/worker/toindex1"></a>
		</c:if>
		<h2 class="title">催促评价</h2>
		</header><!-- /header -->
		<section class="pad_top88">
		<c:if test="${listSize == 0 }">
			<div class="tipWrapper">
				<p class="font28 col_f09859 pb20">温馨提示：</p>
				<p class="font28 col_f09859 lHgt15">太棒了，您所有的任务包质检员都已经评价完了，不需要催促质检员了哟。</p>
			</div>
		</c:if>
		<c:if test="${listSize != 0 }">
			<div class="tipWrapper">
				<p class="font28 col_f09859 pb20">温馨提示：</p>
				<p class="font28 col_f09859 lHgt15">下面的任务包需要质检员评价后才可以结算哟，请您及时催促质检员对任务包进行评价吧。</p>
			</div>
		</c:if>
		
			
			<c:forEach items="${list }" var="taskpackage">
			<div class="sec font0 border_top border_btm mb24 bg_f shadow clearfix">
				<ul class="pad_top3 pad_left3 bor_dashed">
					<li class="mb30 clearfix">
						<span class="col_grey font30 flo_left pl3em">顾客：</span>
						<p class="font30 col_3 pad_rgt3 flow">${taskpackage.communityName }-${taskpackage.buildNumber }-${taskpackage.buildUnit }-${taskpackage.buildRoom }-${taskpackage.customerName }</p>
					</li>
					<li class="mb30 clearfix">
						<span class="col_grey font30 flo_left">任务包名称：</span>
						<p class="font30 col_3 pad_rgt3 flow">${taskpackage.packageName }</p>
					</li>
					<li class="mb30 clearfix">
						<span class="col_grey font30 flo_left pl1em">项目经理：</span>
						<p class="font30 col_3 pad_rgt3 flow">${taskpackage.itemManager }</p>
					</li>
					<li class="mb30 clearfix">
						<span class="col_grey font30 flo_left pl1em">验收时间：</span>
						<p class="font30 col_3 pad_rgt3 flow"><fmt:formatDate value="${taskpackage.createDate }" pattern="yyyy-MM-dd HH:mm:ss"/>  </p>
					</li>
					<li class="mb30 clearfix">
						<span class="col_grey font30 flo_left pl2em">质检员：</span>
						<p class="font30 col_3 pad_rgt3 flow">${taskpackage.orderInspectorName }</p>
					</li>
					<li class="mb30 rele clearfix">
						<span class="col_grey font28 flo_left">质检员手机：</span>
						<p class="font28 col_3 pad_rgt3 flow">${taskpackage.orderInspectorPhone }</p>
						
						<a class="teleBtn" href="javascript:;"><i class="teleIcon"><img src="${ctxStatic}/mobile/modules/Worker/img/tele.png" alt=""></i>拨打电话</a>
					</li>
				</ul>
				<div class="btn_wrapper clearfix">
					<a class="btnBlueBg" href="javascript:;" onclick="tip(${taskpackage.id })">催促评价</a>
				</div>
			</div>
			</c:forEach>
		</section>
		<div style="padding-bottom:300px;"></div>
		
		<div class="g-mask undis" id = "alertMasksu">
			<div class="alertMask">
				<div class="maskWrapper">
					<p class="col_3 maskTit">通知</p>
					<div class="font28 col_6 maskContent align_ctr">一天只允许催促评价一次，如果您着急可以点击拨打电话跟质检员电话联系哟，您确定提交催促评价吗？</div>
					<div class="maskBtns twoBtns clearfix">
						<a class="maskBtn font33 col_f" href="javascript:;" onclick="sendPost()">确定</a>
						<a class="maskBtn font33" href="javascript:;">取消</a>
						<input type="hidden" id = "orderpackId"/>
					</div>
				</div>
			</div>
		</div>
		
		<!--111111111111111  -->
		<div class="g-mask undis" style="text-align:center;" id="agree">
		<div class="alertMask">
			<div class="maskWrapper">
				<p class="col_3 maskTit">通知</p>
				<div class="font28 col_6 maskContent">一天只允许催促评价一次，如果您着急可以点击拨打电话跟质检员电话联系哟，您确定提交催促评价吗？</div>
				<div class="maskBtns twoBtns clearfix">
					<a class="maskBtn font33 col_f" onclick="sendPost()">确定</a>
					<a class="maskBtn font33" href="javascript:;" onclick="hide()">取消</a>
				</div>
			</div>
		</div>
		</div>
		
		
		<div class="g-mask undis" style="text-align:center;" id="agree1">
		<div class="alertMask">
			<div class="maskWrapper">
				<p class="col_3 maskTit">通知</p>
				<div class="font28 col_6 maskContent">今日已催促过，请不要多次催促！</div>
				<div class="maskBtns twoBtns clearfix">
					<a class="maskBtn font33" href="javascript:;" onclick="hide()">我知道了</a>
				</div>
			</div>
		</div>
		</div>
		<div class="g-mask undis" style="text-align:center;" id="agree2">
		<div class="alertMask">
			<div class="maskWrapper">
				<p class="col_3 maskTit">通知</p>
				<div class="font28 col_6 maskContent">催促成功！</div>
				<div class="maskBtns twoBtns clearfix">
					<a class="maskBtn font33" onclick="hide()">确定</a>
				</div>
			</div>
		</div>
		</div>
	</div>
	
	<!-- <div class="maskBtns twoBtns clearfix">
					<a class="maskBtn font33 col_f" onclick="comfim1()">确定</a>
				</div> -->
	
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Worker/js/lib/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Worker/js/utils/calcRootFontSize.js"></script>
	<script type="text/javascript">
	function tip(id){
		$("#orderpackId").val(id);
		$("#agree").show();
	}
	function sendPost(){
		var id = $("#orderpackId").val();
		 $.ajax({
		    	url:"${ctx}/app/worker/urgeevaluation/urgeevaluation",
		    	data:{id:id},
		    	dataType:"json",
		    	method : "post",
		    	success:function(result){
		       		if(result == '0'){
		       			$("#agree2").show();
		       		}
		       		if(result != '0'){
		       			$("#agree1").show();
		       		}
		    	}
		});
	 }
	
	function hide(){
		$("#agree").hide();
		$("#agree1").hide();
		$("#agree2").hide();
	}
	</script>
</body>

</html>