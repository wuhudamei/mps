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
	<title>提交标化材料</title>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Worker/css/globalUtil2.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/reset.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/header.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/common.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/standarApply1.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/auxiliary_apply.css"/>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/calcRootFontSize.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/history.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Worker/js/utils/global.js"></script>
	<style>
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

<script type="text/javascript" charset="utf-8">
$(function(){

	if($("#isView").val()==true||$("#isView").val()==''){

	}else{
		//显示
		$("#applyForRecord").removeClass("undis");
	}
    $.ajax({
        url: "${ctx}/app/manager/getShippingFee?bizMaterialsStandardType=1",
        type: "get",
        success: function(data) {
            if(null!=data){
				$("#shippingFee").val(data.shippingFees);
				if(data.shippingFees){
				    $("#tFee").append(data.shippingFees+"元配送费")
                }else {
                    $("#tFee").append("0.00元配送费")
				}
            }else{
                $("#shippingType").val(0);
            }
        }

    });

})
	//申请上限的框 的确定
	function promptSure(){
	//不显示框体
		$("#prompt").addClass("undis")
	}
var checkSubmitFlg = false;

	function checkSubmit(){ 
	
	if(checkSubmitFlg ==true){
		return false; //当表单被提交过一次后checkSubmitFlg将变为true,根据判断将无法进行提交。 
	} 
	
	checkSubmitFlg ==true; 
	
	return true; 

	} 



	function sure(){
		$("#alert").hide();
	}
	function sure2(){
	    if($("input[name='shippingType1']:checked").val()){
            var val = $("input[name='shippingType1']:checked").val();
            //自提
            if(val==0){
                $("#shippingFee").val(0);
                $("#shippingType").val(0);
                //配送
			}else if(val==1){
                $("#shippingType").val(1);
			}
		}
		$("#form").submit();
		$("#alert").hide();
	}
	function cancel(){
		$("#alert2").hide();
	}

	//选好了
	function cart() {
		//校驗
		/* if(){
			
		} */
		var v = $("#countnumber").val();
		if(v == '' || v=='0'){
			$("#alert").show();
		}else{
			
			$("#alert2").show();
		}
		
	}
	</script>

<body>
	<div class="show_sec">
		<header>
			<a class="back_btn" href="${ctx}/app/manager/applyStandardMaterial/standarMaterialList" onclick="myhistory.back()"></a>
			<h2 class="title">提交标化材料</h2>
		</header><!-- /header -->
		
		<section id="toptop" class="pad_top88">
			<ul class="font0">
			<form id="form" action="${ctx}/app/manager/applyStandardMaterial/saveStandardBill" method="post">
			<input name="orderId" value="${orderId }" hidden="hidden">
			<input name="shippingFee" id="shippingFee" hidden="hidden">
			<input name="shippingType" id="shippingType" hidden="hidden" >
			<input value="${isView}" id="isView" hidden="true">
			<c:forEach items="${standardlist }"  var="standard">
				<li class="item bg_f pad_left3 pad_top24 pad_btm36 mar_top24 relative">
					<input type="hidden" name ="id"value=${standard.materialsStandardId }>
					<input type="hidden" id ="countnumber">
					<p class="font30 col_3 line_hgt16 mar_btm50">【${standard.materialsType }】${standard.materialsName }</p>
					<p class="font28 col_6"><span class="col_red3 price1">${standard.materialsPrice }</span>/${standard.materialsUnit }</p>
					<div id="numbox" class="clearfix shopcount" >
								<a class="reduce" 
									 onclick="setTotal(this,2)">-</a>
								<input type ="number" id="num" name = "number" class="shopNum font28"  type="number" value=0
									 name="count" onchange="setTotal(this,3,${standard.maxReceiveNumber},${standard.applyNumberTotal },${standard.receiveNumberTotal })" onkeyup="this.value=this.value.replace(/\D/g, '')"/> 
								<a class="plus"
									 onclick="setTotal(this,1,${standard.maxReceiveNumber},${standard.applyNumberTotal },${standard.receiveNumberTotal })">+</a>
					</div>
					<div class="pad_top3 countNum clearfix">
						<p class="font28 col_6 flo_left a1">申请上限：<span class="col_red3 a11" > 
							<c:if test="${empty standard.maxReceiveNumber }">
								∞ 
							</c:if>
							<c:if test="${!empty standard.maxReceiveNumber }">
								${standard.maxReceiveNumber }
							</c:if>
						</span></p >
						<p class="font28 col_6 flo_left a2">待领取数量：<span class="col_red3 a22">
						<c:if test="${empty standard.applyNumberTotal }">
							0.0
						</c:if>
						<c:if test="${!empty standard.applyNumberTotal }">
							${standard.applyNumberTotal }
						</c:if>
						</span></p >
						<p class="font28 col_6 flo_left a3">已领取数量：<span class="col_red3 a33" >
						<c:if test="${empty standard.receiveNumberTotal }">
							0.0
						</c:if>
						<c:if test="${!empty standard.receiveNumberTotal }">
							${standard.receiveNumberTotal }
						</c:if>
						</span></p >
					</div>
				</li>
			</c:forEach>
			<div style="padding-top:300px;"></div>
			</form>
			</ul>
		</section>
		<a class="gotToTop" href="#toptop"></a>
		<footer>
			<div>
				<p class="col_red font28">合计：<span id="total"></span></p>
				<p class="font24 col_6" id="count"></p>
			</div>
			<a class="subBtn" href="javascript:void(0)" onclick="cart()">提交标化材料</a>
		</footer>
		<div class="alertMask undis" id="applyForRecord">
			<div class="maskWrapper">
				<p class="col_3 maskTit">提示</p>
				<div class="font28 col_6 maskContent">您有未查看的申请记录，请查看后再申请。</div>
				<div class="maskBtns clearfix twoBtns">
					<a class="maskBtnOne font33 col_fdfcfa" href="${ctx}/app/manager/applyStandardMaterial/detail?id=${billId}&&orderId=${orderId}">确定</a>
				</div>
			</div>
		</div>
		<div class="alertMask undis" id="prompt">
			<div class="maskWrapper">
				<p class="col_3 maskTit">提示</p>
				<div class="font28 col_6 maskContent">已超过申请上限</div>
				<div class="maskBtns clearfix twoBtns">
					<a class="maskBtnOne font33 col_fdfcfa" href="javascript:void(0);" onclick="promptSure()">确定</a>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/calcRootFontSize.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/history.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/shopcountSpan.js"></script>
	
	<script type="text/javascript"
		src="${ctxStatic}/mobile/modules/Manager/js/utils/calcRootFontSize.js"></script>
	<script type="text/javascript"
		src="${ctxStatic}/mobile/modules/Manager/js/lib/swiper-3.3.1.jquery.min.js"></script>
	
		<script>
		var index0 = 0;
		$('.g-auxiliary_choose nav').on('click', 'a.nav1', function() {
			index0 = $(this).index();
			$('.nav1').find('span').removeClass('active');
			$(this).find('span').addClass('active');
			$('.show_sec').addClass('undis');
			$('.show_sec').eq(index0).removeClass('undis');
		});

		function setTotal(obj,category,a,b,c) {
			
			if (2== category) {
				var inputObj = $(obj).next();
				var number = $(inputObj).val();
				if (number == 0) {
					return;
				}
				$(inputObj).val(parseInt(number) - 1);
				
			}
			if (1 == category) {
				var inputObj = $(obj).prev();
				var number = $(inputObj).val();
				if (number == 999) {
					return;
				}
				$(inputObj).val( parseInt(number) + 1);
				//var ss= $(this).parent().parent().find("#maxReceiveNumber").find('input');
				//var ss=$(this).parent().next().find("p").eq(0)
			//校驗
			var d=$(inputObj).val();
			 if(a!=0){
				if(a-b-c-d<0){
					$("#prompt").removeClass("undis");
					$(inputObj).val( d - 1);
				} 
			 }
			}
			if(3==category){
				if($(obj).val()>999){
					$(obj).val(999);
					
				}
				var d=$(obj).val();
				 if(a!=0){
					if(a-b-c-d<0){
						$("#prompt").removeClass("undis");
						$(obj).val( a-b-c);
					} 
				 }
			}
			setTimeout(function() {
				var s = 0;
				var c = 0;
				$(".show_sec ul li").each(
						function() {
					
							c += parseInt($(this).find(
									'input[name*=number]').val());
							s += parseInt($(this).find(
									'input[name*=number]').val())
									* parseFloat($(this).find(
											'span[class*=price1]').text());
							$("#countnumber").val(s);
						});
				$("#total").html(s.toFixed(2) + "元");
				$("#count").html("您一共选择了" + c + "个材料");
			}, "50");
		}
	</script>
	<!-- 提示框 -->
				<div style="background:rgba(0,0,0,.4);z-index:9;display:none;" class="g-mask undis" id ="alert" >
					<div id="g-done_ask">
						<p class="first">请选择材料！</p>
						<p class="second">
							<a href="javascript:void(0);" onclick="sure()">确认</a>
						</p>
					</div> 
				</div>
				
				<div style="background:rgba(0,0,0,.4);z-index:9;display:none;" class="g-mask undis" id ="alert2" >
					<div id="g-done_ask" style="height: auto; padding: .2rem;">
						<p class="font26 border_btm pad_top1 pad_btm1">选择配送方式:</p>
						<p class="font26 pad_top2 pad_btm2">配送方式：<input type="radio" name="shippingType1" checked value="0">自提 <input type="radio" name="shippingType1" value="1">配送

						</p>
						<p class="col_red font26 tl" id="tFee">提示：选择配送时，每个订单会收取</p>

						<p >
							 <a href="javascript:void(0);" onclick="sure2()">确定</a>
							 <a href="javascript:void(0);" onclick="cancel()">取消</a>
						</p>
					</div> 
				</div>
	
</body>
</html>