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
	<title>申请沙子水泥</title>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/reset.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/header.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/new/common.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/new/list.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/new1/common.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/search.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/progress/signList.css"/>
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
		/* .maskBtn{display: block;width: 60%;} */
		.maskBtn{background: #0780ec;border-radius: .1rem;display: block;width: 47.6%;
    		text-align: center;line-height: .76rem;font-size:.33rem;margin: 0 auto;}
    	.twoBtns .maskBtn:first-child{background: #0780ec;border-radius: .1rem;float: left;}
		.twoBtns .maskBtn:last-child{border: 1px solid #0780ec;box-sizing: border-box;border-radius: .1rem;float: right;background: #fff;}
	</style>
</head>
<body>
	<div class="">
		<header>
			<a class="back_btn" href="${ctx}/app/manager/meterialManagementList"></a>
			<h2 class="title">申请沙子水泥</h2>
		</header><!-- /header -->
		<section class="pad_top11">
			<div class="mar_btm14 font0 search-area">
				<input class="search-box" type="text" placeholder=" 小区名称、客户姓名">
				<a class="search-btn" href="javascript:;" onclick="findText()"></a>
			</div>
			<input type="text" hidden="hidden" id="timeError" value="${timeError}">
			<input type="text" hidden="hidden" id="notRead" value="${notRead}">
			<c:if test="${not empty list }">
				<c:forEach items="${list}" var="order">
					<c:if test="${ order.isScrap eq 1 }">
					<div class="sec font0 border_top border_btm mar_btm24 bg_f clearfix">
						<ul class="pad_top3 pad_left3 bor_dotted  abandon">
							<li class="mar_btm24 clearfix">
								<span class="col_grey font28 flo_left">顾　　　　客：</span>
								<p class="font28 col_3 pad_rgt3 flow customer">${order.communityName }-${order.buildNumber }-${order.buildUnit }-${order.buildRoom }-${order.customerName }</p>
							</li>
							<li class="mar_btm24 clearfix">
								<span class="col_grey font28 flo_left">合同开工日期：</span>
								<p class="font28 col_3 pad_rgt3 flow"><fmt:formatDate value="${order.contractStartDate }" pattern="yyyy-MM-dd"/></p>
							</li>
							<li class="mar_btm24 clearfix">
								<span class="col_grey font28 flo_left">实际开工日期：</span>
								<p class="font28 col_3 pad_rgt3 flow"><fmt:formatDate value="${order.actualStartDate }" pattern="yyyy-MM-dd"/></p>
							</li>
							<li class="mar_btm24 clearfix">
								<span class="col_grey font28 flo_left">合 同 工 期 ：</span>
								<p class="font28 col_3 pad_rgt3 flow">${order.contractTime}天</p>
							</li>
							<li class="mar_btm24 clearfix">
								<span class="col_grey font28 flo_left">订 单 状 态 ：</span>
								<p class="font28 col_3 pad_rgt3 flow">${order.orderStatus }</p>
							</li>
						</ul>
						<div class="btn_wrapper clearfix">
<%-- 							<a class="two_btn1" href="${ctx}/app/manager/applySand/sandApply?orderId=${order.id}">申请沙子水泥</a> --%>
							<a class="two_btn2" href="${ctx}/app/manager/applySand/sandRecord?orderId=${order.id}">申请记录</a>
						</div>
					</div>
					</c:if>
					<c:if test="${ order.isScrap eq 0 }">
					<div class="sec font0 border_top border_btm mar_btm24 bg_f clearfix">
						<ul class="pad_top3 pad_left3 bor_dotted">
							<li class="mar_btm24 clearfix">
								<span class="col_grey font28 flo_left">顾　　　　客：</span>
								<p class="font28 col_3 pad_rgt3 flow customer">${order.communityName }-${order.buildNumber }-${order.buildUnit }-${order.buildRoom }-${order.customerName }</p>
							</li>
							<li class="mar_btm24 clearfix">
								<span class="col_grey font28 flo_left">合同开工日期：</span>
								<p class="font28 col_3 pad_rgt3 flow"><fmt:formatDate value="${order.contractStartDate }" pattern="yyyy-MM-dd"/></p>
							</li>
							<li class="mar_btm24 clearfix">
								<span class="col_grey font28 flo_left">实际开工日期：</span>
								<p class="font28 col_3 pad_rgt3 flow"><fmt:formatDate value="${order.actualStartDate }" pattern="yyyy-MM-dd"/></p>
							</li>
							<li class="mar_btm24 clearfix">
								<span class="col_grey font28 flo_left">合 同 工 期 ：</span>
								<p class="font28 col_3 pad_rgt3 flow">${order.contractTime}天</p>
							</li>
							<li class="mar_btm24 clearfix">
								<span class="col_grey font28 flo_left">订 单 状 态 ：</span>
								<p class="font28 col_3 pad_rgt3 flow">${order.orderStatus }</p>
							</li>
						</ul>
						<div class="btn_wrapper clearfix">
							<input type="text" hidden = "hidden" id="customerName${order.id}" value="${order.customerName}">
							<a class="two_btn1" onclick="sandApply('${order.id}')" >申请沙子水泥</a>
							<a class="two_btn2" href="${ctx}/app/manager/applySand/sandRecord?orderId=${order.id}">申请记录</a>
						</div>
					</div>
					</c:if>
					
				</c:forEach>
			</c:if>
			
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
		<div style="padding-bottom:300px;"></div>
	</div>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/calcRootFontSize.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/history.js"></script>
	<style type="text/css">
        .highlight { background-color:yellow; }
    </style>
	<script type="text/javascript">
		
		//关闭提示弹框
		function sure(){
			$("#alert").hide();
		}
		
		//申请沙子水泥
		function sandApply(orderId){

            var customerName = $("#customerName"+orderId).val();

			 $.ajax({
		            url: "${ctx}/app/manager/applySand/applySand_data_check_ajax",
		            type: "post",
		            data: {
	            		orderId:orderId
	            	 },
		            success: function(data){
		            	
		            	if(data!=null && data=="0"){
		            		window.location.href = "${ctx}/app/manager/applySand/sandApply?orderId="+orderId;
		            	}else if(data=="1"){
		            		$("#alertRemarks").text("订单id为空");
		    				$("#alert").show();
		            	}else if(data=="2"){
		            		$("#alertRemarks").text("项目经理未登录");
		    				$("#alert").show();
		            	}else if(data=="3"){
		            		$("#alertRemarks").text("“"+customerName+"”家的基础装修质检员已确认验收了，不允许再申请沙子水泥了。");
		    				$("#alert").show();
		            	}else if(data=="4"){
		            		$("#alertRemarks").text("您有未阅读的沙子水泥采购单，请到【申请记录】去查阅后再申请沙子水泥");
		    				$("#alert").show();
		            	}else if(data=="5"){
		            		$("#alertRemarks").text("同一个订单两次沙子水泥申请操作时间必须间隔5分钟，请过5分钟后再申请");
		    				$("#alert").show();
		            	}
		            }
			 })
			
			
		}
		
		
		//页内搜索
		function findText(){
			clearSelection();
			var searchText = $('.search-box').val();
			if(null==searchText || searchText==""){
				$('.sec').show();
				return false;
			}
			$('.sec').hide();
			var regExp = new RegExp(searchText, 'g');
			$('.customer').each(function()//遍历文章；
	            {
	                var html = $(this).html();
	                var newHtml = html.replace(regExp, '<span class="highlight">'+searchText+'</span>');//将找到的关键字替换，加上highlight属性；
	 
	                $(this).html(newHtml);//更新文章；
	            });
			$(".highlight").parent().parent().parent().parent().show();	
		}
		function clearSelection(){
			 $('.customer').each(function()//遍历
            {
                $(this).find('.highlight').each(function()//找到所有highlight属性的元素；
                {
                    $(this).replaceWith($(this).html());//将他们的属性去掉；
                });
            });
        }
	</script>
</body>
</html>