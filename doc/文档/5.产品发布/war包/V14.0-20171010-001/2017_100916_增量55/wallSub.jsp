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
	<title>提交墙地砖</title>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/1new/reset.css" />
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/1new/header.css" />
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/lib/mobiscroll.custom-2.16.1.min.css">
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/1new/common.css" />
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/1new/Details.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/1new/apply.css" />
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/1new/wallSub.css" />
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
	<div class="font0">
		<header>
			<a class="back_btn" href="${ctx}/app/manager/materialManagement/wallAndFloorNew/list"></a>
			<h2 class="title">提交墙地砖</h2>
		</header>
		<section class="pad_top88">
			<form id="jvForm" class="jvForm"  method="post" enctype="multipart/form-data">
				
				<input type="text" hidden="hidden" id="orderId" name="orderId" value="${order.orderId }" />
				<input type="text" hidden="hidden" name="projectMode" id="projectMode" value="${order.projectMode}">
																
				<input type="text" hidden="hidden" name="applyCountaTotal" id="applyCountaTotal" value="0">
				<input type="text" hidden="hidden" name="squarePurchaseTotal" id="squarePurchaseTotal" value="${squarePurchaseTotal}">
				<input type="text" hidden="hidden" name="squareBudgetOne" id="squareBudgetOne" value="${order.squareBudgetOne}">
				<input type="text" hidden="hidden" name="squareBudgetTwo" id="squareBudgetTwo" value="${order.squareBudgetOne}">
				<input type="text" hidden="hidden" name="squareOverMaxRate" id="squareOverMaxRate" value="${recheckCnfg.squareOverMaxRate}">
				<input type="text" hidden="hidden" name="price" id="price" value="${recheckCnfg.price}">
				
				
				<p class="pt20 pb20 align_center">
					<span class="font30 col_blue">${order.communityName }-${order.buildNumber }-${order.buildUnit }-${order.buildRoom }-${order.customerName }</span>
				</p>
				<div class="sec pl30 pr30 pb30 font0 bor_top_e5 bg_f shadow clearfix">
					<ul class="pt30">
					
						<c:if test="${order.projectMode eq 1 }">
							<li class="mb30 clearfix">
								<span class="col_grey font30 fl pl2em">预算面积：</span>
								<p class="font30 col_3 flow" id="yusuan">${order.squareBudgetOne} ㎡</p> 
							</li> 
							<li class="mb30 clearfix">
								<span class="col_grey font30 fl pl2em">实测面积：</span>
								<p class="font30 col_3 flow" id="yusuan">${squareActual} ㎡<span style="color: red;"> (含损耗)</span></p> 
							</li> 
						</c:if> 
						
						<li class="mb30 clearfix">
							<span class="col_grey font30">期望到场日期：</span>
							<input class="date date1" type="text" readonly="" value="" placeholder="请输入日期" data-lcalendar="" id="txtDate" name="inputDate">
						</li>
						<li class="rele clearfix">
							<span class="col_grey font30 pl4em">备注：</span>
							<textarea class="instruc" placeholder="" name="purchaseRemarks" id="purchaseRemarks">${manager.realname }-${manager.phone}</textarea>
						</li>
					</ul>
				</div>
				<section class="pt24 mb30 shadow">
					<p class="pb20">
						<span class="pl30 font30 col_blue bold">纸质下料单</span>
					</p>
					<div class="pics bor_top_ddd font0 shadow bg_f clearfix">
						
						<%-- <div class="pic">
							<img src="${ctxStatic}/mobile/modules/Manager/img/common/canlender.png" alt="">
							<a class="delBtn" href="javascript:void(0)"></a>
						</div> --%>
						
						<div class="pic camera" id="camera" href="javascript:void(0)">
							<img src="${ctxStatic}/mobile/modules/Manager/img/common/upPic.png" alt="">
							<input type="file" accept="image/*" onchange="preview(this)">
						</div>
						<input type="text" hidden="hidden" id="shit" value="1">
						<input type="text" hidden="hidden" id="num" value="">
					</div>
				</section>
				<div class="bor_top_e5" id="goods">
					<nav class="bg_f shadow_e2e2e2 clearfix">
						<a class="btnA" href="javascript:;"><span class="navA active">地砖</span></a>
						<a class="btnA" href="javascript:;"><span class="navA">墙砖</span></a>
					</nav>
					<section class="navSec mt10">
						<c:forEach items="${floor}" var="a">	
							<ul class="bg_f pad_left30 pad_rgt30 pt30 pb30 bor_btm_d">
								<input type="text" hidden="hidden" name="id" value="${a.id}"/>
								<input type="text" hidden="hidden" name="isCountSquare" value="${a.isCountSquare }"/>
								<input type="text" hidden="hidden" name="unitSquare" value="${a.unitSquare }"/>
								<input type="text" hidden="hidden" name="unit" value="${a.unit }"/>
								<input type="text" hidden="hidden" name="applyCounta" value="0"/>
								<input type="text" hidden="hidden" name="applySquareCount" value="0"/>
								<li class="mb30 clearfix">
									<span class="font30 col_grey fl   _name">项目名称：</span>
									<p class="font30 col_3">${a.mainMateTypeName }</p>
								</li>
								<li class="mb30 clearfix">
									<span class="font30 col_grey fl   _name">位置：</span>
									<p class="font30 col_3">${a.position }</p>
								</li>
								<li class="mb30 clearfix">
									<span class="font30 col_grey fl _name">品牌及套餐：</span>
									<p class="font30 col_3">${a.brandCombo }</p>
								</li>
								<li class="mb30 clearfix">
									<span class="font30 col_grey fl   _name">型号：</span>
									<p class="font30 col_3">${a.model }</p>
								</li> 
								<li class="mb30 clearfix">
									<span class="font30 col_grey fl   _name">规格：</span>
									<p class="font30 col_3">${a.specification }</p>
								</li>
								<li class="mb30 clearfix">
									<span class="font30 col_grey fl   _name">单位面积：</span>
									<p class="font30 col_3">${a.unitSquare }m<sup>2</sup></p>
								</li>
								<li class="mb30 clearfix">
									<span class="font30 col_grey fl   _name">属性：</span>
									<p class="font30 col_3">${a.attribute }</p>
								</li>
								<li class="mb30 clearfix">
									<span class="font30 col_grey fl   _name">供应商：</span>
									<p class="font30 col_3">${a.supplier }</p>
								</li>
								<li class="mb30 clearfix">
									<span class="font30 col_grey fl   _name">单位：</span>
									<p class="font30 col_3">${a.unit }</p>
<!-- 									<p class="font30 col_3">m<sup>2</sup></p> -->
								</li>

								<li class="mb30 clearfix">
									<span class="font30 col_grey fl   _name">预估数量：</span>
									<p class="font30 col_3"> ${a.includLossCount } ${a.unit } （含损耗数量）</p>
								</li>
								<li class="mb30 clearfix">
									<span class="col_grey font30  fl  _name">申请面积：</span>
									<input class="numInput mr20 mr20valueSquare"  style="border: 1px solid #DDD; background-color: #F5F5F5; color:#ACA899; width:4rem;"  readonly="readonly"  type="text" name="applySquare" value="" onkeyup="fix_amountthis(this)" onafterpaste="fix_amountthis(this)" onchange="getApplyCounta  (this)"/>
									<span class="col_3 font36"> ㎡</span>
								</li>
								<li class="mb30 clearfix">
									<span class="font30 col_grey fl    _name">申请数量：</span>
									<input class="numInput mr20 mr20value" style="width:4rem;"   type="text" name="applySquareCountz" value="" onkeyup="fix_amountthis(this)" onafterpaste="fix_amountthis (this)" onchange="getApplySquare (this)"/>
									<span class="col_3 font36">${a.unit }</span>
								</li>
								<li class="mb30 clearfix">
									<span class="font30 col_grey fl  _name">已申请数量：</span>
									<p class="font30 col_3">${a.purchaseCount }${a.unit }</p>
								</li>
								<li class="rele clearfix">
									<span class="col_grey font30  fl  _name">备注：</span>
									<textarea class="instruc instruc2" placeholder="" name="remarks"></textarea>
								</li>
							</ul>
						</c:forEach>
						
					</section>
					<section class="navSec mt10 undis">
						<c:forEach items="${wall}" var="a">	
							<ul class="bg_f pad_left30 pad_rgt30 pt30 pb30 bor_btm_d">
								<input type="text" hidden="hidden" name="id" value="${a.id}"/>
								<input type="text" hidden="hidden" name="isCountSquare" value="${a.isCountSquare }"/>
								<input type="text" hidden="hidden" name="unitSquare" value="${a.unitSquare }"/>
								<input type="text" hidden="hidden" name="unit" value="${a.unit }"/>
								<input type="text" hidden="hidden" name="applyCounta" value="0"/>
								<input type="text" hidden="hidden" name="applySquareCount" value="0"/>
								<li class="mb30 clearfix">
									<span class="font30 col_grey fl   _name ">项目名称：</span>
									<p class="font30 col_3">${a.mainMateTypeName }</p>
								</li>
								<li class="mb30 clearfix">
									<span class="font30 col_grey fl   _name">位置：</span>
									<p class="font30 col_3">${a.position }</p>
								</li>
								<li class="mb30 clearfix">
									<span class="font30 col_grey fl _name">品牌及套餐：</span>
									<p class="font30 col_3">${a.brandCombo }</p>
								</li>
								<li class="mb30 clearfix">
									<span class="font30 col_grey fl   _name" >型号：</span>
									<p class="font30 col_3">${a.model }</p>
								</li>
								<li class="mb30 clearfix">
									<span class="font30 col_grey fl   _name">规格：</span>
									<p class="font30 col_3">${a.specification }</p>
								</li>
								<li class="mb30 clearfix">
									<span class="font30 col_grey fl   _name">单位面积：</span>
									<p class="font30 col_3">${a.unitSquare } m<sup>2</sup></p>
								</li>
								<li class="mb30 clearfix">
									<span class="font30 col_grey fl   _name">属性：</span>
									<p class="font30 col_3">${a.attribute }</p>
								</li>
								<li class="mb30 clearfix">
									<span class="font30 col_grey fl   _name">供应商：</span>
									<p class="font30 col_3">${a.supplier }</p>
								</li>
					
								<li class="mb30 clearfix">
									<span class="font30 col_grey fl   _name">单位：</span>
									<p class="font30 col_3">${a.unit }</p>
<!-- 									<p class="font30 col_3">m<sup>2</sup></p> -->
								</li>

								<li class="mb30 clearfix">
									<span class="font30 col_grey fl   _name">预估数量：</span>
<%-- 									<c:if test="${a.unit eq 片 }"> --%>
									
<%-- 									<p class="font30 col_3"> ${a.includLossCount }片（含损耗数量）</p> --%>
<%-- 									</c:if> --%>
<%-- 									<c:if test="${a.unit eq ㎡ }"> --%>
									
									<p class="font30 col_3"> ${a.includLossCount } ${a.unit } （含损耗数量）</p>
<%-- 									</c:if> --%>
								</li>
								<li class="mb30 clearfix">
									<span class="col_grey font30  fl  _name">申请面积：</span>
									<input class="numInput mr20 mr20valueSquare"   style="border: 1px solid #DDD; background-color: #F5F5F5; color:#ACA899; width:4rem;"  readonly="readonly"  type="text" name="applySquare" value="" onkeyup="fix_amountthis(this)" onafterpaste="fix_amountthis(this)" onchange="getApplyCounta (this)"/>
									<span class="col_3 font36">㎡</span>
								</li>
								<li class="mb30 clearfix">
									<span    class="font30 col_grey fl   _name">申请数量：</span>
									<input class="numInput mr20  mr20value" style="width:4rem;" type="text" name="applySquareCountz" value="" onkeyup="fix_amountthis(this)" onafterpaste="fix_amountthis(this)" onchange="getApplySquare (this)"/>
									<span class="col_3 font36">${a.unit }</span>
								</li>
								<li class="mb30 clearfix">
									<span class="font30 col_grey fl _name">已申请数量：</span>
									<p class="font30 col_3">${a.purchaseCount }${a.unit }</p>
								</li>
								<li class="rele clearfix">
									<span class="col_grey font30  fl  _name">备注：</span>
									<textarea class="instruc instruc2" placeholder="" name="remarks"></textarea>
								</li>
							</ul>
						</c:forEach>
					
					</section>
						
				</div>
			</form>
		</section>
		<div class="pt44">
			<a class="subBtn" id="start">确认申请</a>
		</div>
		<div style="padding-bottom:200px;"></div>
	</div>
	
	
	
	
	<div class="alertMask undis" id ="alert">
		<div class="maskWrapper">
			<p class="col_3 maskTit">通知</p>
			<div class="font28 col_6 maskContent" id="alertRemarks"></div>
			<div class="maskBtns clearfix">
				<a class="maskBtn font33 col_f"  onclick="sure()" >我知道了</a>
			</div>
		</div>
	</div>
	
	
	<div class="alertMask undis" id ="alert2">
		<div class="maskWrapper">
			<p class="col_3 maskTit">通知</p>
			<div class="font28 col_6 maskContent" id="alertRemarks2"></div>
			<div class="maskBtns clearfix twoBtns">
				<a class="maskBtn font33 col_fdfcfa" onclick="sure2()">
					确定
				</a>
				<a class="maskBtn font33 col_0780ec" onclick="cancel()">取消</a>
			</div>
		</div>
	</div>
	
	<div class="alertMask undis" id ="alert3">
		<div class="maskWrapper">
			<p class="col_3 maskTit">通知</p>
			<div class="font28 col_6 maskContent">墙地砖采购单申请成功</div>
			<div class="maskBtns clearfix">
				<a class="maskBtn font33 col_f"  onclick="backlast()">我知道了</a>
			</div>
		</div>
	</div>
	
	
	
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/calcRootFontSize.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/mobiscroll.custom-2.16.1.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/global.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/waitMe.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/pqc/js/home/compresspic.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/jquery.form.js"></script>
	<script>
        
        var now = new Date();
		var currYear = now.getFullYear();
		var currMonth = now.getMonth() + 1;
		var currDay = now.getDate();
		$("#txtDate").val(globalUtil.fn.formatDate(new Date(currYear, currMonth - 1, currDay+3),'yyyy-MM-dd'));
		//mobiScroll插件选项
		var opt = {
			theme: "ios",
			lang: 'zh',
			dateFormat: 'yyyy-mm-dd', // 面板日期格式
			dateOrder: 'yyyymmdd', //面板中日期排列格式
			showNow: false,
			nowText: "今",
			endYear: currYear + 3, //结束年份
			minDate: new Date(currYear, currMonth - 1, currDay+3),
			onSelect: function (textVale, inst) { //选中时触发事件
				console.log("我被选中了.....");
			},
			onClose: function (textVale, inst) { //插件效果退出时执行 inst:表示点击的状态反馈：set/cancel
				console.log("textVale--" + textVale);
				console.log(this.id); //this表示调用该插件的对象
			}
		};
		$("#txtDate").mobiscroll().date(opt)
		
		
        $(document).on('click','.btnA',function(){
        	var i = $(this).index();
        	$('.btnA').find('.navA').removeClass('active');
        	$(this).find('.navA').addClass('active');
        	$('.navSec').addClass('undis');
        	$('.navSec').eq(i).removeClass('undis');
        })
        
        
        function run_waitMe(text){
		    $('body').waitMe({
		        effect: 'win8',
		        text: text,
		        bg: 'rgba(255,255,255,0.7)',
		        color:'#000',
		        sizeW:'',
		        sizeH:'',
		        source: 'img.svg'
		    });
		}
		
        //图片上传显示 
		function preview(file) {  
			var shit = $("#shit").val();
			var prevDiv = $('.camera');  
			if (file.files && file.files[0]) {  
				var reader = new FileReader();  
				reader.onload = function(evt){
					$('<div class="pic"><img src="' + evt.target.result + '" onload="compresspic(this,uploadpic)" id="'+shit+'"/><a class="delBtn" href="javascript:void(0)">删除</a></div>').insertBefore('#camera');
				}    
				reader.readAsDataURL(file.files[0]);  
			}else {  
				prevDiv.innerHTML = '<div class="img" style="filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src=\'' + file.value + '\'"></div>';  
				var pic = '<div class="img" style="filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src=\'' + file.value + '\'"></div>';
				console.log(file,file.value);
			}
		} 
		$(document).on('click', '.delBtn', function(){
			
			var numReal = $(this).prev().attr("id");
			$(("#num"+numReal)).remove();
			var num = $("#num").val();
			num--;
			$("#num").val(num);
			$(this).parent().remove();
		});
		
		function uploadpic(pic){
			
			var hiddenForm = document.getElementById("jvForm");
			var input =document.createElement("input");
			
			
			var num = $("#num").val();
			var shit = $("#shit").val();
			
			if(pic){
				num++;
				input.setAttribute("id","num"+shit);
				input.setAttribute('hidden', 'hidden'); 
				input.setAttribute('name', 'photo'); 
				input.setAttribute("type","text");
				input.setAttribute("value", pic);
				hiddenForm.appendChild(input);
				$("#num").val(num);
				shit++;
				$("#shit").val(shit);
			}
		}
		
		
		//校验数量
		function fix_amountthis(obj){
	    	
	        //先把非数字的都替换掉，除了数字和. 
		    obj.value = obj.value.replace(/[^\d.]/g,""); 
		    //必须保证第一个为数字而不是. 
		    obj.value = obj.value.replace(/^\./g,""); 
		    //保证只有出现一个.而没有多个. 
		    obj.value = obj.value.replace(/\.{2,}/g,"."); 
		    //保证.只出现一次，而不能出现两次以上 
		    obj.value = obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$",".");
		    //只能输入两个小数 
		    obj.value = obj.value.replace(/^(\-)*(\d+)\.(\d\d).*$/,'$1$2.$3');
		    //只能输入6个整数
		    var reg = /.*\..*/;
		    if(reg.test(obj.value)){
		    	var aa = obj.value.substring(0,obj.value.indexOf("."));
		    	if(aa.length>6){
		    		obj.value = obj.value.substring(0,6); 
		    	}
		    }else{
		    	if(obj.value.length>6){
		    		obj.value = obj.value.substring(0,6);
		    	}
		    }
	    }
		
		//申请面积
		function getApplyCounta(obj){
			var id = $(obj).parent().parent().find("input[name=id]").val();
			var isCountSquare = $(obj).parent().parent().find("input[name=isCountSquare]").val();
			var unitSquare = $(obj).parent().parent().find("input[name=unitSquare]").val();
			var applySquare = $(obj).parent().parent().find("input[name=applySquare]").val();
// 			alert(applySquare+":"+obj.value);
			var applyCounta = $(obj).parent().parent().find("input[name=applyCounta]").val();
			var applyCountaShow;
			
  			if(applySquare!="" && parseFloat(applySquare)>0){
	  			if(unitSquare!="" && parseFloat(unitSquare)>0){
	  				applyCountaShow = Math.ceil(parseFloat(applySquare) / parseFloat(unitSquare));
// 	  				alert(unitSquare+":"+applyCountaShow)
	  				if(parseFloat(applyCountaShow)>parseFloat(999999.99)){
	  					
	  					$("#alertRemarks").text("您申请的数量超过了上限，请重新填写");
	  					$("#alert").show();
	  					$(obj).parent().parent().find("input[name=applyCounta]").val("0");
	  					$(obj).parent().parent().find(".applyCountaShow").text("0");
	  		  			$(obj).parent().parent().find("input[name=applySquare]").val("");
	  		  			$(obj).parent().parent().find("input[name=applySquareCount]").val("0");
	  					return false;
	  				}
// 	  				alert(applyCountaShow.toFixed(0));
		  			$(obj).parent().parent().find(".mr20value").val(applyCountaShow.toFixed(0));
		  			$(obj).parent().parent().find("input[name=applyCounta]").val(applyCountaShow.toFixed(0));
		  			//如果计算面积
		  			if(isCountSquare=="1"){
			  			$(obj).parent().parent().find("input[name=applySquareCount]").val(applySquare);
		  			}
	  			}
  			}else{
  				$(obj).parent().parent().find("input[name=applyCounta]").val("0");
// 				$(obj).parent().parent().find(".applyCountaShow").text("");
	  			$(obj).parent().parent().find("input[name=applySquare]").val("");
	  			$(obj).parent().parent().find("input[name=applySquareCount]").val("0");
  			}
  			
		}
		//申请数量
		function getApplySquare(obj){
// 			alert(2)
			var id = $(obj).parent().parent().find("input[name=id]").val();
			var isCountSquare = $(obj).parent().parent().find("input[name=isCountSquare]").val();
			var unitSquare = $(obj).parent().parent().find("input[name=unitSquare]").val();
			var unit = $(obj).parent().parent().find("input[name=unit]").val();
			//得到数量用数量计算单价得到面积
			//获取页面输入的数量
			var applySquareCountz = $(obj).parent().parent().find("input[name=applySquareCountz]").val();
			//var applyCounta = $(obj).parent().parent().find("input[name=applyCounta]").val();
			//商品申请面积
			var applySquareShow;
			$(obj).parent().parent().find("input[name=applyCounta]").val(applySquareCountz);
			
  			if(applySquareCountz!="" && parseFloat(applySquareCountz)>0){
  				//如果有单位面积
	  			if((unitSquare!="" && parseFloat(unitSquare)>0) || unit!="片"){
	  				//计算本商品申请的面积
	  				if(unit!="片"){
	  					//如果单位为㎡
	  					applySquareShow = applySquareCountz;
	  				}else{
	  					//如果单位为片
	  					applySquareShow = ((parseFloat(applySquareCountz) * parseFloat(unitSquare))).toFixed(2);
	  				}
// 	  				applyCountaShow = parseFloat(parseFloat(applySquare) * parseFloat(unitSquare));
// 	  				alert(applyCountaShow+":"+parseFloat(applySquare) * parseFloat(unitSquare))
	  				if(parseFloat(applySquareShow)>parseFloat(999999.99)){
	  					$("#alertRemarks").text("您申请的数量超过了上限，请重新填写");
	  					$("#alert").show();
	  					$(obj).parent().parent().find("input[name=applyCounta]").val("0");
	  					$(obj).parent().parent().find("input[name=applySquareCountz]").val("0");
	  					//$(obj).parent().parent().find(".applyCountaShow").text("0");
	  		  			$(obj).parent().parent().find("input[name=applySquare]").val("");
	  		  			$(obj).parent().parent().find("input[name=applySquareCount]").val("0");
	  					return false;
	  				}
	  				if(unit=="片" && unitSquare==""){
	  		  			$(obj).parent().parent().find("input[name=applySquare]").val("");
			  			//$(obj).parent().parent().find(".mr20valueSquare").val("");
			  			$(obj).parent().parent().find("input[name=applySquareCount]").val("0");
	  				}else{
	  		  			$(obj).parent().parent().find("input[name=applySquare]").val(applySquareShow);
	  				}
		  			//如果计算面积
		  			
		  			if(isCountSquare=="1"){
			  			$(obj).parent().parent().find("input[name=applySquareCount]").val(applySquareShow);
			  			//$(obj).parent().parent().find("input[name=applyCounta]").val(applySquare);
		  			}
	  			}
  			}else{
  				$(obj).parent().parent().find("input[name=applyCounta]").val("0");
  				$(obj).parent().parent().find("input[name=applySquareCountz]").val("0");
// 				$(obj).parent().parent().find(".applyCountaShow").text("0");
	  			$(obj).parent().parent().find("input[name=applySquare]").val("");
	  			$(obj).parent().parent().find("input[name=applySquareCount]").val("0");
  			}
  			
		}
		
		$(document).ready(function(){
			$("#start").bind('click',tijiao);
		});
		
		
		function sure(){
			$("#alert").hide();
			$("#alert2").hide();
			$("#alert3").hide();
		}
		function cancel(){
			$("#alert").hide();
			$("#alert2").hide();
			$("#alert3").hide();
		}
		//返回
		function backlast(){
			window.location.href = "${ctx}/app/manager/materialManagement/wallAndFloorNew/list";
		}
		
		//提交问题--页面数据校验
		function tijiao(){
			
			//期望到场日期
			var txtDate = $("#txtDate").val();
			if(null==txtDate || txtDate=="" || undefined==txtDate){
				$("#alertRemarks").text("请添加期望进场日期！");
				$("#alert").show();
				return false;
			}
			//备注
			var purchaseRemarks = $("#purchaseRemarks").val().trim();
			if(null==purchaseRemarks || purchaseRemarks=="" || undefined==purchaseRemarks){
				$("#alertRemarks").text("请添加期望进场日期！");
				$("#alert").show();
				return false;
			}
			
			var projectMode = $("#projectMode").val();
			
			if(null!=projectMode && projectMode=='1'){
				//当订单为产业模式时
				
				//墙地砖实际申请面积总和
				var applySquareGlobal = 0;
				//墙地砖实际申请数量总和
				var applyCountaGlobal = 0;
				
				//墙地砖采购单实际面积
				var applySquareCountGlobal = $("#squarePurchaseTotal").val();
				
				//预算面积*108%
				var squareBudgetOne =  $("#squareBudgetOne").val();
				
				///面积超出上限比例
				var squareOverMaxRate = $("#squareOverMaxRate").val();
				
				if(squareOverMaxRate != "" && squareOverMaxRate >0){
					//预算面积*108%*(比例+1)
					squareBudgetOne = parseFloat(squareBudgetOne) * (parseFloat(squareOverMaxRate) + 1);
					squareBudgetOne = squareBudgetOne.toFixed(2);
					$("#squareBudgetTwo").val(squareBudgetOne);
				}
				
				
				$("#goods ul").each(function() {
						
						var applySquare = $(this).find("input[name=applySquare]").val();
						var applySquareCount = $(this).find("input[name=applySquareCount]").val();
						var applyCounta = $(this).find("input[name=applyCounta]").val();
						
						if(applySquare!="" && parseFloat(applySquare)>0){
							applySquareGlobal = parseFloat(applySquareGlobal) + parseFloat(applySquare);
							applySquareGlobal = applySquareGlobal.toFixed(2);
						}
						if(applySquareCount!="" && parseFloat(applySquareCount)>0){
							applySquareCountGlobal = parseFloat(applySquareCountGlobal) + parseFloat(applySquareCount);
							applySquareCountGlobal = applySquareCountGlobal.toFixed(2);
						}
						if(applyCounta!="" && parseFloat(applyCounta)>0){
							applyCountaGlobal = parseFloat(applyCountaGlobal) + parseFloat(applyCounta);
							applyCountaGlobal = applyCountaGlobal.toFixed(2);
							alert(applyCountaGlobal)
						}
				});
				
				$("#applyCountaTotal").val(applyCountaGlobal);
 				if(parseFloat(applyCountaGlobal)<=0){
 					$("#alertRemarks").text("请输入材料实际用量！");
 					$("#alert").show();
 					return false;
				}
				
				if(parseFloat(applyCountaGlobal)>parseFloat(999999.99)){
  					
  					$("#alertRemarks").text("您申请的数量超过了上限，请重新填写");
  					$("#alert").show();
  					return false;
  				}
				
				if(parseFloat(applySquareCountGlobal)>parseFloat(999999.99)){
  					
  					$("#alertRemarks").text("您申请的数量超过了上限，请重新填写");
  					$("#alert").show();
  					return false;
  				}
				if(parseFloat(applySquareCountGlobal)>parseFloat(squareBudgetOne)){
  					var yus=$("#yusuan").text();
  					if(yus!="0.0 ㎡"){
  						$("#alertRemarks2").text("累积申请面积超出预算面积"+(squareOverMaxRate*100)+"%，是否继续申请？");
						$("#alert2").show();
  					}else{
  						$("#alertRemarks2").text("您确认要提交采购单吗？");
  						$("#alert2").show();
  					}
					
  					
				}else{
					$("#alertRemarks2").text("您确认要提交采购单吗？");
					$("#alert2").show();
				}
				
				
			}else if(projectMode=='4'){
				//当订单为准产业模式
				
				var applyCountaGlobal = 0;
						
				$("#goods ul").each(function() {
					
					var applyCounta = $(this).find("input[name=applyCounta]").val();
					if(applyCounta!="" && parseFloat(applyCounta)>0){
						applyCountaGlobal = parseFloat(applyCountaGlobal) + parseFloat(applyCounta);
						applyCountaGlobal = applyCountaGlobal.toFixed(0);
					}
					
				});
				$("#applyCountaTotal").val(applyCountaGlobal);
				alert("2")
				alert(applyCountaGlobal)
 				if(parseFloat(applyCountaGlobal)<=0){
 					$("#alertRemarks").text("请输入材料实际用量！");
					$("#alert").show();
 					return false;
 				}
				
				$("#alertRemarks2").text("您确认要提交采购单吗？");
				$("#alert2").show();
				
			}else{
				//当订单为传统模式
				
				//至少上传1张图片			
				var num = $("#num").val();
				if(null==num || ""==num ||num<1){
					$("#alertRemarks").text("请上传纸质单据！");
					$("#alert").show();
					return false;
				}
				
				$("#alertRemarks2").text("您确认要提交采购单吗？");
				$("#alert2").show();
			}
			
		}
		
		//提交数据--校验5分钟内的重复提交问题
		function sure2(){
			$("#alert").hide();
			$("#alert2").hide();
			$("#alert3").hide();	
			
			$("#start").css("color","#CCC");
			$("#start").unbind("click");
			
			var orderId = $("#orderId").val();
			$.ajax({
	            url: "${ctx}/app/manager/materialManagement/wallAndFloorNew/apply_wallFloor_viewAndTime_ajax",
	            type: "post",
	            data: {
	            		orderId:orderId
	            	 },
	            success: function(data){
	            	if(null!=data && data=="0"){
	            		applySubmit();
	            	}else if(data=="1"){
	            		$("#alertRemarks").text("订单id传送失败");
	            		$("#alert").show();
	            	}else if(data=="2"){
	            		$("#alertRemarks").text("同一个订单两次墙地砖申请操作时间必须间隔5分钟，请过5分钟后再申请");
	            		$("#alert").show();
	            	}else if(data=="3"){
	            		$("#alertRemarks").text("您有未阅读的墙地砖采购单，请到【申请记录】去查阅后再申请墙地砖");
	            		$("#alert").show();
	            	}
	            	
	            	
	            }
			})
			
		}
		
		//表单提交
		function applySubmit(){
			//防止重复提交
			run_waitMe('正在提交数据,请稍等');
			$("#start").css("color","#CCC");
			$("#start").unbind("click");
			
			//上报提交表单
			var options ={
					url : "${ctx}/app/manager/materialManagement/wallAndFloorNew/apply_wallFloor_submit_ajax",
					type: "post",
					success : function(data){
						$('body').waitMe('hide');
						if(null!=data && data=="0"){
							$("#alert3").show();
						}else if(data=="1"){
							$("#alertRemarks").text("订单id为空");
		        			$("#alert").show();
						}else if(data=="2"){
							$("#alertRemarks").text("订单工程模式为空");
		        			$("#alert").show();
						}else if(data=="3"){
							$("#alertRemarks").text("期望到场日期为空");
		        			$("#alert").show();
						}else if(data=="4"){
							$("#alertRemarks").text("备注为空");
		        			$("#alert").show();
						}else if(data=="5"){
							$("#alertRemarks").text("当订单的工程模式为传统时，必须上传图片");
		        			$("#alert").show();
						}else if(data=="6"){
							$("#alertRemarks").text("请选择材料实际用量");
		        			$("#alert").show();
						}else if(data=="7"){
							$("#alertRemarks").text("预算面积等传送失败");
		        			$("#alert").show();
						}else if(data=="8"){
							$("#alertRemarks").text("项目经理未登录");
							$("#alert").show();
						}else if(data=="9"){
							$("#alertRemarks").text("采购单保存失败");
							$("#alert").show();
						}
					}
				}
			//ajax提交form
			$("#jvForm").ajaxSubmit(options);
		}
		
	</script>
</body>
</html>