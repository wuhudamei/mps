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
	<title>申请标化材料</title>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Worker/css/globalUtil2.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/reset.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/header.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/common.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/new1/common.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/doneList.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/search.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/auxiliary_apply.css"/>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/calcRootFontSize.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/history.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Worker/js/utils/global.js"></script>
	<style>
		.two_btn_act{background: #b1b1b1;}
		/*mask style*/
		.pad_btm40{padding-bottom:.4rem;}
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
		.maskBtnOne{background: #0780ec;border-radius: .1rem;display: block;width: 47.6%;
    		text-align: center;line-height: .76rem;font-size:.33rem;margin: 0 auto;}
    	.twoBtns .maskBtn:first-child{background: #0780ec;border-radius: .1rem;float: left;}
		.twoBtns .maskBtn:last-child{border: 1px solid #0780ec;box-sizing: border-box;border-radius: .1rem;float: right;background: #fff;}
		
		.pad_btm40{padding-bottom:.4rem;}
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
    	.maskBtnOne{background: #0780ec;border-radius: .1rem;display: block;width: 47.6%;
    		text-align: center;line-height: .76rem;font-size:.33rem;margin: 0 auto;}
    	.twoBtns .maskBtn:first-child{background: #0780ec;border-radius: .1rem;float: left;}
		.twoBtns .maskBtn:last-child{border: 1px solid #0780ec;box-sizing: border-box;border-radius: .1rem;float: right;background: #fff;}
	
		
	</style>
</head>
<body>
	<script type="text/javascript">
		$(function(){
			
			if($("#isView").val()==true || $("#isView").val()==''){
				
			}else{
				//显示
				$("#applyForRecord").removeClass("undis");
			}
			
			if($("#applyFlag").val()==true || $("#applyFlag").val()==''){
				
			}else{
				//显示
				$("#alert").show();
			}
			
				
					
				
					
			 
			
			
			
		})
			
			
				
		
		
			function sure(){
				$("#alert").hide();
				
			}
		/* 是否申请 */
			function isApply(flag){
				if(flag){
					
				}else{
					$("#alert").show();
					
				}
			}
		
		function submitForm(){
			
			$("#fromSearch").submit();
			
		}
	</script>
	<div class="">
		<header>
			<a class="back_btn" href="${ctx}/app/manager/meterialManagementList" onclick="myhistory.back()"></a>
			<h2 class="title">申请标化材料</h2>
			<a class="tit_rgt" href="javascript:;">申请须知</a>
		</header><!-- /header -->
		<section class="pad_top11">
		<form action="${ctx}/app/manager/applyStandardMaterial/standarMaterialList" id="fromSearch">
			<div class="mar_btm14 font0 search-area">
				<input name ="villageAndName" class="search-box" type="text" placeholder=" 小区名称、客户姓名">
				<input type="submit" style="border:none;" class="search-btn">
			</div>
		</form>
		<input value="${isView}" id="isView" type="hidden" >
		<input value="${applyFlag}" id="applyFlag" type="hidden" >
			<c:forEach items="${listOrder}" var="order">
				<c:if test="${ order.isScrap eq 1 }">
				<div class="sec font0 border_top border_btm mar_btm24 bg_f clearfix">
				<a href="javascript:;" class="arrow_rgt pad_left3">
					<ul class="pad_top3 abandon">
						<li class="mar_btm24 clearfix  ">
							<span class="col_grey font28 flo_left">顾　　　　客：</span>                          
							<p class="font28 col_3 flow">${order.communityName }-${order.buildNumber }-${order.buildUnit }-${order.buildRoom }-${order.customerName }</p>
						</li>
						<li class="mar_btm24 clearfix">
							<span class="col_grey font28 flo_left">合同开工日期：</span>
							<p class="font28 col_3 flow">
								<fmt:formatDate value="${order.contractStartDate }" pattern="yyyy-MM-dd"/>
							</p>
						</li>
						<li class="mar_btm24 clearfix">
							<span class="col_grey font28 flo_left">实际开工日期：</span>
							<p class="font28 col_3 flow">
								<fmt:formatDate value="${order.actualStartDate }" pattern="yyyy-MM-dd"/>
							
							</p>
						</li>
						<li class="mar_btm24 clearfix">
							<span class="col_grey font28 flo_left">合 同 工 期 ：</span>
							<p class="font28 col_3 flow">${order.contractTime }天</p>
						</li>
						<li class="mar_btm24 clearfix">
							<span class="col_grey font28 flo_left">订 单 状 态 ：</span>
							<p class="font28 col_3 flow">${order.orderStatusDescription }</p>
						</li>
					</ul>
				</a>
				<div class="btn_wrapper clearfix">
<%-- 					<c:if test="${order.isBasicworkCompleted =='0'}"> --%>
<%-- 					<a id ="send" class="two_btn1" value="${applyFlag}" href="${ctx}/app/manager/applyStandardMaterial/applyStandarMaterialList/${order.id }">申请标化材料</a> --%>
<%-- 					</c:if> --%>
<%-- 					<c:if test="${ order.isBasicworkCompleted !='0'}"> --%>
<%-- 					<a id ="send" class="one_btn_grey" value="${applyFlag}" href="${ctx}/app/manager/applyStandardMaterial/applyStandarMaterialList/${order.id }">申请标化材料</a> --%>
<%-- 					</c:if> --%>
					<a class="two_btn2" href="${ctx}/app/manager/applyStandardMaterial/record?orderId=${order.id }">申请记录</a>
				</div>
			</div>
				</c:if>
				<c:if test="${ order.isScrap eq 0 }">
				<div class="sec font0 border_top border_btm mar_btm24 bg_f clearfix">
				<a href="javascript:;" class="arrow_rgt pad_left3">
					<ul class="pad_top3">
						<li class="mar_btm24 clearfix">
							<span class="col_grey font28 flo_left">顾　　　　客：</span>                          
							<p class="font28 col_3 flow">${order.communityName }-${order.buildNumber }-${order.buildUnit }-${order.buildRoom }-${order.customerName }</p>
						</li>
						<li class="mar_btm24 clearfix">
							<span class="col_grey font28 flo_left">合同开工日期：</span>
							<p class="font28 col_3 flow">
								<fmt:formatDate value="${order.contractStartDate }" pattern="yyyy-MM-dd"/>
							</p>
						</li>
						<li class="mar_btm24 clearfix">
							<span class="col_grey font28 flo_left">实际开工日期：</span>
							<p class="font28 col_3 flow">
								<fmt:formatDate value="${order.actualStartDate }" pattern="yyyy-MM-dd"/>
							
							</p>
						</li>
						<li class="mar_btm24 clearfix">
							<span class="col_grey font28 flo_left">合 同 工 期 ：</span>
							<p class="font28 col_3 flow">${order.contractTime }天</p>
						</li>
						<li class="mar_btm24 clearfix">
							<span class="col_grey font28 flo_left">订 单 状 态 ：</span>
							<p class="font28 col_3 flow">${order.orderStatusDescription }</p>
						</li>
					</ul>
				</a>
				<div class="btn_wrapper clearfix">
					<c:if test="${order.isBasicworkCompleted =='0'}">
					<a id ="send" class="two_btn1" value="${applyFlag}" href="${ctx}/app/manager/applyStandardMaterial/applyStandarMaterialList/${order.id }">申请标化材料</a>
					</c:if>
					<c:if test="${ order.isBasicworkCompleted !='0'}">
					<a id ="send" class="one_btn_grey" value="${applyFlag}" href="${ctx}/app/manager/applyStandardMaterial/applyStandarMaterialList/${order.id }">申请标化材料</a>
					</c:if>
					<a class="two_btn2" href="${ctx}/app/manager/applyStandardMaterial/record?orderId=${order.id }">申请记录</a>
				</div>
			</div>
				</c:if>
			
			</c:forEach>
		</section>
	</div>
	
	<!-- 提示框 -->
				<div style="background:rgba(0,0,0,.4);z-index:9;display:none;" class="g-mask undis" id ="alert" >
					<div id="g-done_ask">
						<p class="first">此订单基装验收完成，
						不允许再次申请</p>
						<p class="second">
							<a href="${ctx}/app/manager/applyStandardMaterial/standarMaterialList">确认</a>
						</p>
					</div> 
				</div>
				<div class="alertMask alertMask1 undis">
			<div class="maskWrapper">
				<p class="col_3 maskTit">申请须知</p>
				<div class="font28 col_6 maskContent">
					1.每个订单可以多次申请标化辅料材料<br>
					2.单个材料累计申请数量不能超过申请上限值<br>
					3.该订单完成基装验收后不可再次申请标化辅料<br>
					4.该订单下已领取的所有标化辅料会在中期结算时扣除相应费用<br>
					以上内容，请在申请前知悉，谢谢！
				</div>
				<div class="maskBtns clearfix twoBtns">
					<a class="maskBtnOne font33 col_fdfcfa" href="javascript:void(0);">我已了解</a>
				</div>
			</div>
		</div>
		
		<div class="alertMask undis" id="applyForRecord">
			<div class="maskWrapper">
				<p class="col_3 maskTit">提示</p>
				<div class="font28 col_6 maskContent">您有未查看的申请记录，请查看后再申请。</div>
				<div class="maskBtns clearfix twoBtns">
					<a class="maskBtnOne font33 col_fdfcfa" href="${ctx}/app/manager/applyStandardMaterial/detail?id=${billId}&&orderId=${orderId}">确定</a>
				</div>
			</div>
		</div>
</body>
<script>
		$(function(){
			$(document).on('click', '.tit_rgt', function(){
				$('.alertMask1').removeClass('undis');
			});
			$(document).on('click', '.alertMask1 .maskBtnOne', function(){
				$('.alertMask1').addClass('undis');
			});
		});
</script>
</html>