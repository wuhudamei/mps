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
	<title>提交申请</title>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/reset.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/header.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/lib/mobiscroll.custom-2.16.1.min.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/new/common.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/new/list.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/metri/metriGet.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/metri/sandGet.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/globalUtil.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/waitMe.css" >
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
    	.twoBtns .maskBtn:first-child{background: #0780ec;border-radius: .1rem;float: left;}
		.twoBtns .maskBtn:last-child{border: 1px solid #0780ec;box-sizing: border-box;border-radius: .1rem;float: right;background: #fff;}
	</style>
</head>
<body>
	<div class="" id="aboveId">
		<header>
			<a class="back_btn" href="${ctx}/app/manager/applySand/orderList"></a>
			<h2 class="title">提交申请</h2>
		</header><!-- /header -->
		<section class="pad_top88 font0">
			<form id="searchForm">
				<input type="text" hidden="hidden" id="totalCount" name="totalCount" value="0">
				<input type="text" hidden="hidden" id="totalMoney" name="totalMoney" value="0">
				<input type="text" hidden="hidden" id="orderId" name="orderId" value="${order.id }">
				<!-- <input type="text" hidden="hidden" id="delayTypeValue" name="delayTypeValue"> -->
				<input type="text" hidden="hidden" id="isElevator" name="isElevator" value="${order.isElevator}">
				<input type="text" hidden="hidden" name="floorNumber" id="floorNumber" value="${order.floor }" >
				<div class="date-div">
					<div class="mar_btm40 clearfix">
						<span class="font28 col_6f6f70 flo_left">
							<label class="label-left">期望到场日期：</label>
							<input id="txtDate" class="goods_date bor_73b7f3" type="text" value="" name="txtBeginDate" readonly="readonly" placeholder="请输入日期" data-lcalendar="2000-01-01,2000-01-01" />
						</span>
					</div>
					<div class="mar_btm24">
						<span class="font28 col_6f6f70">
							<label class="label-left">备注：</label>
							<textarea class="sand-tip" placeholder="${manager.realname }${manager.phone }" name="remark">${manager.realname }${manager.phone }</textarea>
						</span>
					</div>
					<div class="mar_btm3">
						<span class="font28 col_6f6f70">
							<label class="label-left">选择供应商：</label>
							<select class="sand-select" id = "supplierId" name="supplierId" onchange="changeSupplier()">
								<c:if test="${not empty list }">
									<c:forEach items="${list }" var="supplierlist">
										<option value ="${supplierlist.supplierId }">${supplierlist.supplierName }</option>
									</c:forEach>
								</c:if>
							</select>
						</span>
					</div>
					<div class="mar_btm24">
						<span class="font28 col_6f6f70">
							<span class="">是否有电梯：</span>
							<c:if test="${order.isElevator eq 1 }">
								<span class="radio_span marrgt1">
									<input type="radio" id="yes1" name="quality" value="1" disabled="disabled">
									<label class="checked" data-name="quality" for="yes">是</label>
								</span>
								<span class="radio_span">
									<input type="radio" id="no1" name="quality" value="0" disabled="disabled">
									<label class="" data-name="quality" for="no">否</label>
								</span>
							</c:if>
							<c:if test="${order.isElevator ne 1 }">
								<span class="radio_span marrgt1">
									<input type="radio" id="yes1" name="quality" value="1" disabled="disabled">
									<label class="" data-name="quality" for="yes">是</label>
								</span>
								<span class="radio_span">
									<input type="radio" id="no1" name="quality" value="0" disabled="disabled">
									<label class="checked" data-name="quality" for="no">否</label>
								</span>
							</c:if>
						</span>
					</div>
					<div class="mar_btm40 clearfix ">
						<span class="font28 col_6f6f70 flo_left">
							<label class="label-left">楼层：</label>
							<input class="floor bor_73b7f3" type="text" value="${order.floor }" maxlength="4" disabled="disabled"/>
							楼
						</span>
					</div>
				</div>
				<ul>
					<c:if test="${not empty list }">
						<c:forEach items="${list }" var="supplier">
							<c:forEach items="${supplier.sandGoodsList }" var="goods">
								<li class="pad_top24 pad_btm40 pad_left30 pad_rgt34 bg_f bor_top bor_btm mar_btm24 clearfix "  >
									<input hidden="hidden" type="text" name="good${supplier.supplierId }" value="${supplier.supplierId }">
									<input type="text" hidden="hidden" name="auxiliaryMaterialsId" value="${goods.auxiliaryMaterialsId }">
									<input type="text" hidden="hidden" name="auxiliaryMaterialsNo" value="${goods.auxiliaryMaterialsNo }">
									<input type="text" hidden="hidden" name="laborPrice" value="${goods.laborPrice }">
									<input type="text" hidden="hidden" name="supplierPrice" value="${goods.supplierPrice}">
									<input type="text" hidden="hidden" name="wangzhenPrice" value="${goods.wangzhenPrice}">
									<div class="img_container">
										<c:if test="${empty goods.picUrl }">
											<img src="${ctxStatic}/mobile/modules/Manager/img/metri/eg.png" alt="">
										</c:if>
										<c:if test="${not empty goods.picUrl }">
											<img src="${baseUrl }${goods.picUrl }" alt="">
										</c:if>
									</div>
									<p class="font28 col_4e4f50 mar_left237"><span class="col_302f2f">【${goods.brands }】</span>${goods.auxiliaryMaterialsName }</p>
									<p class="font26 col_4e4f50 pad_top2 mar_left237">规格：<span class="font24 col_5b5a5a">${goods.specifications }</span></p>
									<p class="font26 col_4e4f50 pad_top2 mar_left237 pad_btm2 bor_btm">单价：￥${goods.laborPrice }元/${goods.measurementUnitLabel }</p>
									<p class="mar_left237 pad_top14">
										<span class="clearfix shopcount">
											<a class="reduce" href="javascript:;" onclick="setTotal()"></a>
											<input type="number" class="shopNum font28 col_3" name="goodCount" value="0" onkeyup="if(this.value.length==1){this.value=this.value.replace(/[^0-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}" 
onafterpaste="if(this.value.length==1){this.value=this.value.replace(/[^0-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}" maxlength="3" onchange="setTotal()" minlength="1"/>
											<a class="plus" href="javascript:;" onclick="setTotal()"></a>
										</span>
									</p>
								</li>
							</c:forEach>
						</c:forEach>
					</c:if>
				</ul>
				<div style="padding-bottom:300px;"></div>
			</form>
		</section>
		<footer class="getFoot">
			<p class="font32 col_252424 total">合计：<span class="sand-account"></span></p>
			<a class="choose_btn" href="javascript:void(0)"  id="cart">提交申请</a>
		</footer>
		<div class="alertMask undis" id ="alert">
			<div class="maskWrapper">
				<p class="col_3 maskTit">通知</p>
				<div class="font28 col_6 maskContent" id="alertRemarks"></div>
				<div class="maskBtns clearfix">
					<a class="maskBtn font33 col_f"  onclick="sure()" href="javascript:;">我知道了</a>
				</div>
			</div>
		</div>
		
		
		<div class="alertMask undis" id ="alert2">
			<div class="maskWrapper">
				<p class="col_3 maskTit">通知</p>
				<div class="font28 col_6 maskContent">您确定要提交吗？</div>
				<div class="maskBtns clearfix twoBtns">
					<a class="maskBtn font33 col_fdfcfa" href="javascript:void(0);" onclick="sure2()">
						确定
					</a>
					<a class="maskBtn font33 col_0780ec" href="javascript:void(0);" onclick="cancel()">取消</a>
				</div>
			</div>
		</div>
		
		<div class="alertMask undis" id ="alert3">
			<div class="maskWrapper">
				<p class="col_3 maskTit">通知</p>
				<div class="font28 col_6 maskContent">沙子水泥采购单申请成功</div>
				<div class="maskBtns clearfix">
					<a class="maskBtn font33 col_f"  onclick="sure3()">我知道了</a>
				</div>
			</div>
		</div>
		
		
	</div>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/calcRootFontSize.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/global.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/history.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/shopcount.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/mobiscroll.custom-2.16.1.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/waitMe.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/jquery.form.js"></script>
	<script>
	
		function run_waitMe(effect,text){
			$('#aboveId').waitMe({
				effect: effect,
				text: text,
				bg: 'rgba(255,255,255,0.7)',
				color:'#000',
				sizeW:'',
				sizeH:'',
				source: 'img.svg'
			});
		}
        
		
        var now = new Date();
		var currYear = now.getFullYear();
		var currMonth = now.getMonth() + 1;
		var currDay = now.getDate();
		$("#txtDate").val(globalUtil.fn.formatDate(new Date(currYear, currMonth - 1, currDay + 2),'yyyy-MM-dd'));
		//mobiScroll插件选项
		var opt = {
			theme: "ios",
			lang: 'zh',
			dateFormat: 'yyyy-mm-dd', // 面板日期格式
			dateOrder: 'yyyymmdd', //面板中日期排列格式
			showNow: false,
			nowText: "今",
			endYear: currYear + 3, //结束年份
			minDate: new Date(currYear, currMonth - 1, currDay + 2),
			onSelect: function (textVale, inst) { //选中时触发事件
				console.log("我被选中了.....");
			},
			onClose: function (textVale, inst) { //插件效果退出时执行 inst:表示点击的状态反馈：set/cancel
				console.log("textVale--" + textVale);
				console.log(this.id); //this表示调用该插件的对象
			}
		};
		$("#txtDate").mobiscroll().date(opt)
        
        
       /*  $('.radio_span label').click(function(){
		    var thisName = $(this).attr('data-name');
		    $('label[data-name="'+thisName+'"]').removeAttr('class') && $(this).attr('class', 'checked');
		    floorNumber();
        }); */
		
		
		 $(function () {
			 changeSupplier();
			/*  floorNumber(); */
			 
		 })
		
		 //修改供应商
		 function changeSupplier(){
			 
			 var supplierId = $("#supplierId").val();
			 $(".pad_top24").hide();
			 var num = 0
			 $(".shopNum").text(num);
			 $("input[name='goodCount']").val(num);
			 $("#totalCount").val(num);
			 $("#totalMoney").val(num);
			 $(".sand-account").html("￥0.00元");
			 $("input[name='good"+supplierId+"']").parent().show();
			 
		 }
		/*  //是否给供应商上楼费
		 function floorNumber(){
			 var delayType = $("label[class='checked']").prev().val();
			 $("#delayTypeValue").val(delayType);
			 if(delayType==0){
				 $("#floor").hide();
			 }else{
				 $("#floor").show();
			 }
		 } */
		 //计算总价
		 function setTotal(){
			 
			 setTimeout(function() {
					
					var s = 0;
					var c = 0;
					var m = 0;
					var q = 999;
					$("ul li").each(
							function() {
								var n = parseInt($(this).find('input[name*=goodCount]').val());
								
								if(n!=null && "NaN"!=n && n>0 && n<1000){
									c += n;
									s += n * parseFloat($(this).find('input[name*=laborPrice]').val());
								}else if(n>999){
									$(this).find('input[name*=goodCount]').val(q)
									c += q;
									s += q * parseFloat($(this).find('input[name*=laborPrice]').val());
								}else{
									$(this).find('input[name*=goodCount]').val(m)
								}
								
							});
					$(".sand-account").html("￥" + s.toFixed(2) + "元");
					$("#totalMoney").val(s.toFixed(2));
					$("#totalCount").val(c);
					//$("#count").html("您一共选择了" + c + "个材料");
				}, "50");
		 }
		
		 
		$(document).ready(function() {
			//绑定onclick事件
			$("#cart").bind('click',cartSubmit);
		});
		//确认用量
		function cartSubmit() {
			
			setTotal();
			 setTimeout(function () { 
				 var txtDate = $("#txtDate").val();
					if(txtDate == null || txtDate ==""){
						$("#alertRemarks").text("请添加期望到场日期！");
						$("#alert").show();
						return false;
					}
					
					var info = $(".sand-tip").val();
					if(info == null || info == ""){
						$("#alertRemarks").text("请添加备注信息！");
						$("#alert").show();
						return false;
					}
					
					/* var delayType = $("label[class='checked']").prev().val();
					if(delayType==1){
						var floorNumber = $("#floorNumber").val();
						if(floorNumber == null || floorNumber == "" || floorNumber<1){
							globalUtil.fn.alert('请添加楼层信息！',2.0);
							return false;
						}
					} */
					 
					var v = $("#totalCount").val();
					if(v == '' || v=='0'){
						//用量为空
						$("#alertRemarks").text("请选择材料！");
						$("#alert").show();
					}else{
						//用量不为空
						$("#alert2").show();
					}
			    }, 2000);
			
		}
		function sure(){
			$("#alert").hide();
			$("#alert2").hide();
			$("#alert3").hide();
		}
		function sure3(){
			window.location.href = "${ctx}/app/manager/applySand/sandRecord?orderId=${order.id}";
		}
		function cancel(){
			$("#alert").hide();
			$("#alert2").hide();
			$("#alert3").hide();
		}
		function sure2(){
			$("#alert").hide();
			$("#alert2").hide();
			$("#alert3").hide();
			
			$("#cart").css("color","#CCC");
			$("#cart").unbind("click");
			
			run_waitMe("win8",'请稍等 ,拼命提交中....');
			
			var options ={
					url: "${ctx}/app/manager/applySand/saveSand",
					type: "post",
					success:function(data){
						
						$('#aboveId').waitMe('hide');
						var num = 0
						$(".shopNum").text(num);
						$("input[name='goodCount']").val(num);
						$("#totalCount").val(num);
						$("#totalMoney").val(num);
						$(".sand-account").html("￥0.00元");
						
						if(null!=data && data=="0"){
							$("#alert3").show();
						}else if(data=="1"){
							$("#alertRemarks").text("订单编号为空");
							$("#alert").show();
						}else if(data=="2"){
							$("#alertRemarks").text("项目经理登录异常");
							$("#alert").show();
						}else if(data=="3"){
							$("#alertRemarks").text("您还有未查看的沙子水泥采购单，请先查看详情");
							$("#alert").show();
						}else if(data=="4"){
							$("#alertRemarks").text("您刚刚提交过沙子水泥采购单，请耐心等待五分钟后再次操作");
							$("#alert").show();
						}else if(data=="5"){
							$("#alertRemarks").text("所选材料数量传递错误");
							$("#alert").show();
						}else if(data=="6"){
							$("#alertRemarks").text("所选材料总价格传递错误");
							$("#alert").show();
						}else if(data=="7"){
							$("#alertRemarks").text("供应商传递错误");
							$("#alert").show();
						}else if(data=="8"){
							$("#alertRemarks").text("供应商有上楼费，但楼层为空");
							$("#alert").show();
						}else if(data=="9"){
							$("#alertRemarks").text("采购单保存失败");
							$("#alert").show();
						}else if(data=="10"){
							$("#alertRemarks").text("商品保存失败");
							$("#alert").show();
						}else if(data=="11"){
							$("#alertRemarks").text("给订单的基础装修质检员已确认验收了，不允许再申请沙子水泥了。");
							$("#alert").show();
						}
						
					}
			}
			$("#searchForm").ajaxSubmit(options);
			
			
		}
    </script>
</body>
</html>