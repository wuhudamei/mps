<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1,maximum-scale=1, user-scalable=no">
<meta content="yes" name="apple-mobile-web-app-capable">
<meta content="black" name="apple-mobile-web-app-status-bar-style">
<meta content="telephone=no" name="format-detection">
<meta content="email=no" name="format-detection">
<title>施工变更</title>
<link rel="stylesheet" type="text/css"
	href="${ctxStatic}/mobile/modules/Manager/css/reset.css" />
<link rel="stylesheet" type="text/css"
	href="${ctxStatic}/mobile/modules/Manager/css/header.css" />
<link rel="stylesheet" type="text/css"
	href="${ctxStatic}/mobile/modules/Manager/css/common.css" />
<link rel="stylesheet" type="text/css"
	href="${ctxStatic}/mobile/modules/Manager/css/change_details.css" />
<link rel="stylesheet" type="text/css"
	href="${ctxStatic}/mobile/modules/pqc/css/globalUtil.css" />
<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/quesDone.css" />
<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/jquery-2.1.1.min.js"></script>
<script type="text/javascript">
$(function(){
	$("#picWrapperDiv").hide();
	var count = $(".pic_container > div").size() - 1;
	$("#showPicId").html('客户签字凭证图片('+count+')');
	
	
	$("#showPicId").click(function(){
		$("#picWrapperDiv").show();
		return false;
	});
	
	$("#goToUploadPic").click(function(){
		showDiv();
		$(".alertMask1").hide();
	});
	
	$(".back_btn").click(function(){
		$("#picWrapperDiv").hide();
		var count = $(".pic_container > div").size()-1;
		$("#showPicId").html('客户签字凭证图片('+count+')');
	});
	
});
	/* function hiddenDiv(){
		$("#picWrapperDiv").hide();
		var count = $(".pic_container > div").size();
		$("#spanPic").html('客户签字凭证图片('+count+')');
	} */
	
	function showDiv(){
		$("#picWrapperDiv").show();
	}

</script>
<style>
		#bitian{line-height: .66rem;}
		#showPicId{width:3.2rem;line-height:.66rem;background:#0780ec;font-size:.28rem;color:#fff;border:none;border-radius:.35rem;float:right;}
		#toPicDetails{width:3.2rem;line-height:.66rem;background:#0780ec;font-size:.28rem;color:#fff;border:none;border-radius:.35rem;float:right;}
		.two_btn_act{background: #b1b1b1;text-align:center;}
		/*mask style*/
		.pad_btm40{padding-bottom:.4rem;}
		.alertMask{z-index:99;position: fixed;top: 0;bottom: 0;left: 0;right: 0;background: rgba(0,0,0,.5);}
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
		
		.picWrapper{z-index:990;background:#b1b1b1;position:fixed;top:0;left:0;width:100%;bottom:0;}
</style>
</head>
<body>
	<div class="g-review">
		<header>
			<a class="back_btn"  onclick="history.go(-1)" href="#"
				></a>
			<h2 class="title">施工变更</h2>
		</header>
		<form id="submitFormId">
		<section class="pad_top10 mar_btm50 font0">
			<div class="mar_left3 mar_rgt3 mar_top1">
				<span class="font28 co_3" id="bitian">变更原因<em
					class="font24 co_6">（必填）</em>：
				</span>
				<c:if test="${bill.signaturePic !=0}">
					<%-- <button id="toPicDetails"><span class="font28 co_0780ec flo_rgt" id="goToPic">客户签字凭证图片 (${bill.signaturePic})</span></button> --%>
					<button id="toPicDetails">客户签字凭证图片 (${bill.signaturePic})</button>
				</c:if>
				<c:if test="${bill.signaturePic ==0}">
					<!-- <a id="showPicId" href="javascript:void(0)"><button><span class="font28 co_0780ec flo_rgt" id="spanPic">客户签字凭证图片</span></button></a> -->
					<button id="showPicId">客户签字凭证图片</button>
				</c:if>
				<textarea class="reason_area" name="changeReason" id="changeReason" >${bill.changeReason }</textarea>
			</div>
			<!-- 具体项 -->
			
				<section class="after undis" id="afterSection">
				
				<c:forEach items="${itemList }" var="item">
				<ul class="pad_left3 pad_rgt3 pad_top3 pad_btm3 mar_top3 bg_f shadow">
					<input  type="text"  hidden ="hidden" name="itemId" value="${item.itemId}" />
					<li class="mar_btm5 clearfix">
						<span class="font28 col_6">施工项　：</span>
						<input class="font24 col_6 wid_476 grey_border pad_top1 pad_btm1 mar_rgt2 bg_grey" value="${item.itemName }" readonly="readonly" type="text" />
					</li>
					<li class="mar_btm5 clearfix">
						<span class="font28 col_6">套餐类型：</span>
						
						<c:if test="${item.groupType == '1' }">	<span class="font24 col_3">套餐内</span></c:if>
						<c:if test="${item.groupType == '2' }">	<span class="font24 col_3">套餐外</span></c:if>
					
					</li>
					<li class="mar_btm5 clearfix">
						<span class="font28 col_6">单　　位：</span>
						<span class="font24 col_3">${item.itemUnit }</span>
					</li>
					<li class="mar_btm5 clearfix">
						<span class="font28 col_6">单　　价：</span>
						<input type="text" hidden="hidden" name="price" value="${item.itemPrice }" />
						<span class="font24 col_3 price">${item.itemPrice }</span>
					</li>
					<li class="mar_btm5 clearfix">
						<span class="font28 col_6">数　　量：</span>
						<input class="itemCount font24 col_6 wid_200 grey_border pad_top1 pad_btm1 mar_rgt2  bg_grey" type="text" value="${item.itemCount }" name="itemCount" onchange="setToal()"  onafterpaste="this.value=this.value.replace(/[^0-9]/g,&apos;&apos;)">
					</li>
					<li class="mar_btm5 clearfix">
						<span class="font28 col_6">金　　额：</span>
						
						<input class="totalMoney font24 col_6 wid_200 grey_border pad_top1 pad_btm1 mar_rgt2 bg_grey" type="text" readonly  value="<fmt:formatNumber value="${item.itemCount * item.itemPrice }" pattern="0.00" ></fmt:formatNumber>" />
					</li>
					<li class="clearfix">
						<span class="font28 col_6">说　　明：</span>
						<input class="font24 col_6 wid_476 grey_border pad_top1 pad_btm1 mar_rgt2  bg_grey" type="text" value="${item.itemDescription }" name="itemDetail">
					</li>
					<a class="del_cross" href="javascript:void(0)"><img src="${ctxStatic}/mobile/modules/Manager/images/del_cross.png" alt=""></a>
					<c:if test="${item.itemWay == '1' }">		<div class="blue">增</div></c:if>
						<c:if test="${item.itemWay == '2' }">	<div class="blue">减</div></c:if>
				</ul>
				
				</c:forEach>
					
				
				
				</section>
				<input  type="text"  hidden ="hidden" name="projectChangeId" value="${bill.projectChangeId}" />
				<input  type="text"  hidden ="hidden" name="orderId" value="${bill.orderId}" />
				
				
			
			
			
			
		</section>
		</form>
		<footer>
			<a href="javascript:void(0)"><span
				class="btn font28 bor_rht btn1">增项</span></a> <a
				href="javascript:void(0)"><span class="btn font28 bor_rht btn2">减项</span></a>
			<a href="javascript:void(0)"><span class="btn font28 btn3"
				onclick="submit()">提交</span></a>
		</footer>
		<div class="mask mask1 undis">
			<div class="btn_wraper">
				<a class="reduce_btn btn1_1 border_btm" href="javascript:void(0)">套餐内减项</a>
				<a class="reduce_btn btn1_2 mar_btm2" href="javascript:void(0)">套餐外减项</a>
				<a class="reduce_btn btn1_3" href="javascript:void(0)">取消</a>
			</div>
		</div>

			<form id="addForm">
			<div class="mask mask2 undis" id="addDiv">
				<div class="g-item">
					<header>
						<h2 class="title">施工项增项列表</h2>
					</header>
					<section class="item">
						<!-- 施工项 和 分类 -->
						<ul id="add">
							<c:forEach items="${list }" var="typeNameList" varStatus="status">
							<li class="clearfix font28" id="li${status.index+1}"><p
									class="font28 borBtm relative hgt86 pad_left30">
									<input type="checkbox" id="build"  >
									<label data-name="constr" for="build">${typeNameList.itemTypeName }</label><a
										class="showAll" onclick="showAll('${typeNameList.itemTypeId}','add','${status.index+1}')">展开</a>
								</p>
								 <ul class="item_bar undis">
									
								</ul>
							</c:forEach> 
						</li>
						</ul>
					</section>
					<footer>
						<a class="btn_a btn2_1" href="javascript:void(0)"><span
							class="mask_btn bor_rht" href="javascript:void(0)"
							onclick="hideUndis('addDiv')">取消</span></a> <a class="btn_a btn2_2"
							href="javascript:void(0)"><span class="mask_btn bor_rht"
							href="javascript:void(0)" onclick="saveItem('addForm')">保存</span></a>
					</footer>
				</div>
			</div>

		</form>


		<form id="innerForm">
			<div class="mask mask2 undis" id="innerDiv">
				<div class="g-item">
					<header>
						<h2 class="title">施工项减项套餐内列表</h2>
					</header>
					<section class="item">
				<!-- 施工项 和 分类 -->
						<ul class="container-ul" id="inner">
						<c:forEach items="${list }" var="typeNameList" varStatus="status">
							<li class="clearfix font28" id="li${status.index+200}"><p
									class="font28 borBtm relative hgt86 pad_left30">
									<input type="checkbox" id="build">
									<label data-name="constr" for="build">${typeNameList.itemTypeName }</label><a
										class="showAll" href="javascript:void(0)" onclick="showAll('${typeNameList.itemTypeId}','inner','${status.index+200}')">展开</a>
								</p>
								<ul class="item_bar undis">
							
								</ul>
							</c:forEach>
						
						
						</ul> 
					</section>
					<footer>
						<a class="btn_a btn2_1" href="javascript:void(0)"><span
							class="mask_btn bor_rht" href="javascript:void(0)"
							onclick="hideUndis('innerDiv')">取消</span></a> <a class="btn_a btn2_2"
							href="javascript:void(0)"><span class="mask_btn bor_rht"
							href="javascript:void(0)" onclick="saveItem('innerForm')">保存</span></a>
					</footer>
				</div>
			</div>
		</form>



		<form id="outerForm">
			<div class="mask mask2 undis" id="outerDiv">
				<div class="g-item">
					<header>
						<h2 class="title">施工项减项套餐外列表</h2>
					</header>
					<section class="item">
						 <!-- 施工项 和 分类 -->
						<ul class="container-ul" id="outer">
						<c:forEach items="${list }" var="typeNameList" varStatus="status">
							<li class="clearfix font28" id="li${status.index+500}"><p
									class="font28 borBtm relative hgt86 pad_left30">
									<input type="checkbox" id="build" >
									<label data-name="constr" for="build">${typeNameList.itemTypeName }</label><a
										class="showAll" href="javascript:void(0)"  onclick="showAll('${typeNameList.itemTypeId}','outer','${status.index+500}')">展开</a>
								</p>
								<ul class="item_bar undis">
							
								</ul>
							</c:forEach>
						
						
						</ul> 
					</section>
					<footer>
						<a class="btn_a btn2_1" href="javascript:void(0)"><span
							class="mask_btn bor_rht" href="javascript:void(0)"
							onclick="hideUndis('outerDiv')">取消</span></a> <a class="btn_a btn2_2"
							href="javascript:void(0)"><span class="mask_btn bor_rht"
							href="javascript:void(0)" onclick="saveItem('outerForm')">保存</span></a>
					</footer>
				</div>
			</div>

		</form>
	</div>
	
		
		<div class="alertMask alertMask1 undis">
		<div class="maskWrapper">
			<p class="col_3 maskTit">提示</p>
			<div class="font28 col_6 maskContent">上传客户签字凭证图片后才能提交施工变更！！</div>
			<div class="maskBtns clearfix twoBtns">
				<a class="maskBtnOne font33 col_fdfcfa" href="javascript:void(0);" id="goToUploadPic">去上传图片</a>
			</div>
		</div>
	</div>
	
	<div class="font0 picWrapper" id="picWrapperDiv">
		<header>
			<a class="back_btn" href="javascript:void(0);"></a>
			<h2 class="title">查看图片</h2>
		</header><!-- /header -->
		<section style="padding-top:.6rem;">
			<div class="bg_f pad_top11">
					<div class="pics pic_container font0 clearfix">
						<div class="pic camera" id="camera" href="javascript:void(0)">
							<img src="${ctxStatic}/mobile/modules/Manager/images/upPic.png" alt="">
							<input type="file" accept="image/*" onchange="preview(this)">
						</div>
						<input type="text" hidden="hidden" id="shit" value="1">
					</div>
					<!-- <button class="btn btn-info" onclick="hiddenDiv()">返回</button> -->
				</div>
				
				<!-- <div class="pics">
					<div class="pic_container clearfix">
						
					</div>
				</div> -->
		</section>
	</div>
	
	<script type="text/javascript"
		src="${ctxStatic}/mobile/modules/Manager/js/lib/jquery-2.1.1.min.js"></script>
	<script type="text/javascript"
		src="${ctxStatic}/mobile/modules/Manager/js/utils/calcRootFontSize.js"></script>
	<script type="text/javascript"
		src="${ctxStatic}/mobile/modules/Manager/js/utils/history.js"></script>
	<script type="text/javascript"
		src="${ctxStatic}/mobile/modules/Worker/js/lib/jquery.form.js"></script>
	<script type="text/javascript"
		src="${ctxStatic}/mobile/modules/pqc/js/utils/global.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/pqc/js/home/compresspic.js"></script>
	<script type="text/javascript">
	

	function showAll(typeId,str,liId){
		
		if('1'==$(("#li"+liId)).val()){
			return;
		}
		
				
		$.get("${ctx}/app/manager/changeManagement/findItemByTypeId",{typeId:typeId,addOrMinus:str},function(data){
			
			if(data==null||data.length<1){
				alert('该分类暂无变更项');
				
			}else{
				var html ='';
				for(var v=0;v<data.length;v++){
					html+='<li class="hgt86 wid_per90 borBtm mar_left10"><input type="checkbox" id="one"><input type="text" hidden="hidden" name="itemId" value="'+data[v].itemId+'" /> <label data-name="one" for="one">'+data[v].itemName+'</label></li>'
					
				}
				$(("#li"+liId+" ul")).append(html);
				$(("#li"+liId)).val("1");
				
				
			}
			
			
		})
		
	
		
		
	
		
		
	}
	
	
	
	//图片上传显示 
	function preview(file) {  
		var shit = $("#shit").val();
		var prevDiv = $('.camera');  
		if (file.files && file.files[0]) {  
			var reader = new FileReader();  
			reader.onload = function(evt){
				$('<div class="pic"><img style="width:100%;height:100px;" src="' + evt.target.result + '" onload="compresspic(this,uploadpic)" id="'+shit+'"/><a class="del_pic" href="javascript:void(0)">删除</a></div>').appendTo('.pic_container');
			}    
			reader.readAsDataURL(file.files[0]);  
		}else {  
			prevDiv.innerHTML = '<div class="img" style="filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src=\'' + file.value + '\'"></div>';  
			var pic = '<div class="img" style="filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src=\'' + file.value + '\'"></div>';
			console.log(file,file.value);
		}
	} 

	//删除按钮
	$(document).on('click', '.del_pic', function(){
	    var numReal=$(this).prev().attr("id");
	    $(("#num"+numReal)).remove();

	    var num = $("#num").val();
	    num--;
	    $("#num").val(num);

	    //$(this).parent().remove();
		$(this).parent().remove();
		//$("#num").val("0");
	});


	function uploadpic(pic){
		
		var hiddenForm = document.getElementById("submitFormId");
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
	
	
	
	
	
	
	
	//提交
	function  submit(){
		var size = $("#afterSection > ul").size();
		if(size<1){
			globalUtil.fn.alert('您还没有选择增减项',2.0);
			return;
		}
		
		
		
		var x = 1;
	if(($("#changeReason").val()=="")){
		
		globalUtil.fn.alert('变更原因 为必选项',2.0);
		$("#bitian").css("color","red");
	}else{
		$(".after ul").each(function(){
			var num =	parseFloat($(this).find('input[class*=itemCount]').val());
            $(this).find(
                'input[class*=itemCount]').val(num)
	  		if(isNaN(num)){
	  			globalUtil.fn.alert('请检查变更项数量是否填写',2.0);
	  		 x++;
	  		}
	  		
	  	
		
		})
		
		if(x>1){
			
			return;
		}
		
		var count = $(".pic_container > div").size();
		var picConut='${bill.signaturePic}';
		if(picConut >0 || (count != null && count>=2)){
				
			$(".btn").css("color","#CCC")
			$("footer").hide();
			globalUtil.fn.alert('正在提交..请稍后..',2.0);
	
			var options ={
				url: "${ctx}/app/manager/changeManagement/updateChangeForm",
				type: "post",
					success:function(data){
						if(data=="1"){
							//成功
							globalUtil.fn.alert('更新变更单成功',2.0);
							window.location.href="${ctx}/app/manager/changeManagement/applyRecord?orderId=${bill.orderId}"; 
						}else{
							//失败
							globalUtil.fn.alert('数据异常,请联系开发人员',2.0);
						}
						
						
						}
					}
		
				$("#submitFormId").ajaxSubmit(options);
			}else{
				$(".alertMask1").show();
			}
		}
	
}
						
						
	
	function setToal(){
		$(".after ul").each(function(){
			var num =	parseFloat($(this).find('input[class*=itemCount]').val());
	  		var price = parseFloat($(this).find('span[class*=price]').text());
            var reg = /^(([1-9]+)|([0-9]+\.[0-9]{1,2}))$/;
            var isMoneyFormatRight = reg.test(num);
            if(!isMoneyFormatRight){

                globalUtil.fn.alert('请输入正确的数量,支持两位小数', 2.0);
                return;
            }
            $(this).find(
                'input[class*=itemCount]').val(num)
            if(isNaN(num)){
	  			$(this).find('input[class*=totalMoney]').val(0);
	  			
	  		}else{
	  			
	  		$(this).find('input[class*=totalMoney]').val((num*price).toFixed(2));
	  		}
		
		})
		
	}
	
  		
	
	
	//取消
	function hideUndis(divName){
		
		$("#"+divName).addClass('undis');
		$(".after").removeClass('undis');	
	}
	
	
	
	
	function saveItem(formId){

		$(".mask_btn").css("color","#CCC");
		$(".mask_btn").unbind("click");
		//去掉没有选的选项
		$(".item_bar label").each(function() {
				if ($(this).hasClass("checked")) {
					var item = $(this).prev();
					$(item).attr("name","itemId")
				} else {
					var item = $(this).prev();
					$(item).removeAttr("name");
				
				}

			})
		
		
		var html ="";
			var options ={
					url: "${ctx}/app/manager/changeManagement/saveItem",
					type: "post",
						success:function(data){
						
							if(null!=data){
							for(var v =0;v<data.length;v++){
								var taocan;
								if(data[v].groupType==1){
									taocan="套餐内";
									
								}else{
									
									taocan="套餐外";
								}
								var addOrMinus;
								if(data[v].itemWay==1){
									addOrMinus="增";
									
								}else{
									
									addOrMinus="减";
								}
								
								
								html+='<ul class="pad_left3 pad_rgt3 pad_top3 pad_btm3 mar_top3 bg_f"><li class="mar_btm5 clearfix"> <input type="text" hidden="hidden" name="itemId" value="'+data[v].itemId+'" /> <span class="font28 col_6">施工项　：</span><input class="font24 col_6 wid_476 grey_border pad_top1 pad_btm1 mar_rgt2 bg_grey" value="'+data[v].itemName+'" readonly="readonly" type="text" /></li><li class="mar_btm5 clearfix"><span class="font28 col_6">套餐类型：</span><span class="font24 col_3">'+taocan+'</span></li><li class="mar_btm5 clearfix"><span class="font28 col_6">单　　位：</span><span class="font24 col_3">'+data[v].itemUnit+'</span></li><li class="mar_btm5 clearfix"><span class="font28 col_6">单　　价：</span><input type="text" name="price" hidden="hidden" value="'+data[v].itemPrice+'" /> <span class="price font24 col_3">'+data[v].itemPrice+'</span></li><li class="mar_btm5 clearfix"><span class="font28 col_6">数　　量：</span><input class="itemCount  font24 col_6 wid_200 blue_border pad_top1 pad_btm1 mar_rgt2" type="text" name="itemCount" onchange="setToal()"   onafterpaste="this.value=this.value.replace(/[^0-9]/g,&apos;&apos;)" ><span class="countWarn" hidden="hidden">数量不允许为空</span></li><li class="mar_btm5 clearfix"><span class="font28 col_6">金　　额：</span><input class="totalMoney font24 col_6 wid_200 grey_border pad_top1 pad_btm1 mar_rgt2 bg_grey" type="text" readonly="readOnly" value="0" name="totalMoney"></li><li class="clearfix"><span class="font28 col_6">说　　明：</span><input class="font24 col_6 wid_476 blue_border pad_top1 pad_btm1 mar_rgt2" type="text" name="itemDetail"></li><a class="del_cross" href="javascript:void(0)"><img src="${ctxStatic}/mobile/modules/Manager/images/del_cross.png" alt=""></a><div class="blue">'+addOrMinus+'</div></ul>';
								
							}	
							$("#afterSection").append(html);
								
							}							
					}
			}
			$("#"+formId).ajaxSubmit(options);
			
	}	
			
			
			
	/* 		
//         再次填充 施工项和  相关js			
			
			var addList = JSON.parse('${addList}');
			var innerList = JSON.parse('${innerList}');
			var outerList = JSON.parse('${outerList}');
		
			var addHtml1="";
			//增项
			if(null!=addList){
				var q =1;
				for(var v =0; v<addList.length;v++){
					//施工分类
					var y =1;
					
					addHtml1+= '<li class="clearfix font28" id="li'+q+'"><p class="font28 borBtm relative hgt86 pad_left30"><input type="checkbox" id="build" name="constr" value="build"> <label data-name="constr" for="build">'+addList[v].itemTypeName+'</label><a class="showAll" href="javascript:void(0)">展开</a></p>';
					q++;
					var itemList =  addList[v].itemList
					for(var x = 0;x<itemList.length;x++){
						//施工项  itemId , itemName
					addHtml1+= '<ul class="item_bar undis"><li class="hgt86 wid_per90 borBtm mar_left10"><input type="checkbox" id="one" name="one" value="one"><input type="text" hidden="hidden" name="itemId"  value = "'+itemList[x].itemId+'" /> <label data-name="one" for="one">'+y+'. '+itemList[x].itemName+'</label></li></ul>';
						y++;
						
						
					}
					addHtml1+="</li>";
				}
				$("#add").html(addHtml1);
				addHtml1="";
			}else{
				$("#add").html("没有增项施工项");
				
			}
			
			
			
			//减项 套餐内
			
				var innerHtml1="";
			if(null!=innerList){
				var q =1;
				for(var v =0; v<innerList.length;v++){
					//施工分类
					
					innerHtml1+= '<li class="clearfix font28" id="li'+q+'"><p class="font28 borBtm relative hgt86 pad_left30"><input type="checkbox" id="build" name="constr" value="build">  <label data-name="constr" for="build">'+innerList[v].itemTypeName+'</label><a class="showAll" href="javascript:void(0)">展开</a></p>';
					q++;
					var y =0;
					var itemList =  innerList[v].itemList
					for(var x = 0;x<itemList.length;x++){
						//施工项  itemId , itemName
						y++;
						innerHtml1+= '<ul class="item_bar undis"><li class="hgt86 wid_per90 borBtm mar_left10"><input type="checkbox" id="one" name="one" value="one"><input type="text" hidden="hidden" name="itemId"  value = "'+itemList[x].itemId+'" /> <label data-name="one" for="one">'+y+'. '+itemList[x].itemName+'</label></li></ul>';
						
						
					}
					innerHtml1+="</li>";
				}
				$("#inner").html(innerHtml1);
				innerHtml1="";
			}else{
				$("#inner").html("没有减项套餐内施工项");
				
			}
			
			var outerHtml1 ="";
			//减项 套餐外
			if(null!=outerList){
				var q =1;
				for(var v =0; v<outerList.length;v++){
					//施工分类
					
					outerHtml1+= '<li class="clearfix font28" id="li'+q+'"><p class="font28 borBtm relative hgt86 pad_left30"><input type="checkbox" id="build" name="constr" value="build">  <label data-name="constr" for="build">'+outerList[v].itemTypeName+'</label><a class="showAll" href="javascript:void(0)">展开</a></p>';
					q++;
					var y =0;
					var itemList =  outerList[v].itemList
					for(var x = 0;x<itemList.length;x++){
						//施工项  itemId , itemName
						y++;
						outerHtml1+= '<ul class="item_bar undis"><li class="hgt86 wid_per90 borBtm mar_left10"><input type="checkbox" id="one" name="one" value="one"><input type="text" hidden="hidden" name="itemId"  value = "'+itemList[x].itemId+'" /> <label data-name="one" for="one">'+y+'. '+itemList[x].itemName+'</label></li></ul>';
						
						
					}
					outerHtml1+="</li>";
				}
				$("#outer").html(outerHtml1);
				outerHtml1="";
			}else{
				
				$("#outer").html("没有减项套餐外施工项");
			}		
			
			
			
			
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	$(document).ready(function(){
		
		
		var addList = JSON.parse('${addList}');
		var innerList = JSON.parse('${innerList}');
		var outerList = JSON.parse('${outerList}');
	
		var html="";
		//增项
		if(null!=addList){
			var q =1;
			for(var v =0; v<addList.length;v++){
				//施工分类
				var y =1;
				
				html+= '<li class="clearfix font28" id="li'+q+'"><p class="font28 borBtm relative hgt86 pad_left30"><input type="checkbox" id="build" name="constr" value="build"> <label data-name="constr" for="build">'+addList[v].itemTypeName+'</label><a class="showAll" href="javascript:void(0)">展开</a></p>';
				q++;
				var itemList =  addList[v].itemList
				for(var x = 0;x<itemList.length;x++){
					//施工项  itemId , itemName
				html+= '<ul class="item_bar undis"><li class="hgt86 wid_per90 borBtm mar_left10"><input type="checkbox" id="one" name="one" value="one"><input type="text" hidden="hidden" name="itemId"  value = "'+itemList[x].itemId+'" /> <label data-name="one" for="one">'+y+'. '+itemList[x].itemName+'</label></li></ul>';
					y++;
					
					
				}
				html+="</li>";
			}
			$("#add").html(html);
			html="";
		}else{
			$("#add").html("没有增项施工项");
			
		}
		
		
		
		//减项 套餐内
		
		var innerHtml="";
		if(null!=innerList){
			var q =1;
			for(var v =0; v<innerList.length;v++){
				//施工分类
				
				innerHtml+= '<li class="clearfix font28" id="li'+q+'"><p class="font28 borBtm relative hgt86 pad_left30"><input type="checkbox" id="build" name="constr" value="build">  <label data-name="constr" for="build">'+innerList[v].itemTypeName+'</label><a class="showAll" href="javascript:void(0)">展开</a></p>';
				q++;
				var y =0;
				var itemList =  innerList[v].itemList
				for(var x = 0;x<itemList.length;x++){
					//施工项  itemId , itemName
					y++
					innerHtml+= '<ul class="item_bar undis"><li class="hgt86 wid_per90 borBtm mar_left10"><input type="checkbox" id="one" name="one" value="one"><input type="text" hidden="hidden" name="itemId"  value = "'+itemList[x].itemId+'" /> <label data-name="one" for="one">'+y+'. '+itemList[x].itemName+'</label></li></ul>';
					
					
				}
				innerHtml+="</li>";
			}
			$("#inner").html(innerHtml);
			innerHtml="";
		}else{
			$("#inner").html("没有减项套餐内施工项");
			
		}
		
		var outerHtml ="";
		//减项 套餐外
		if(null!=outerList){
			var q =1;
			for(var v =0; v<outerList.length;v++){
				//施工分类
				
				outerHtml+= '<li class="clearfix font28" id="li'+q+'"><p class="font28 borBtm relative hgt86 pad_left30"><input type="checkbox" id="build" name="constr" value="build">  <label data-name="constr" for="build">'+outerList[v].itemTypeName+'</label><a class="showAll" href="javascript:void(0)">展开</a></p>';
				q++;
				var y =0;
				var itemList =  outerList[v].itemList
				for(var x = 0;x<itemList.length;x++){
					//施工项  itemId , itemName
					y++
					outerHtml+= '<ul class="item_bar undis"><li class="hgt86 wid_per90 borBtm mar_left10"><input type="checkbox" id="one" name="one" value="one"><input type="text" hidden="hidden" name="itemId"  value = "'+itemList[x].itemId+'" /> <label data-name="one" for="one">'+y+'. '+itemList[x].itemName+'</label></li></ul>';
					
					
				}
				outerHtml+="</li>";
			}
			$("#outer").html(outerHtml);
			outerHtml="";
		}else{
			
			$("#outer").html("没有减项套餐外施工项");
		}
		
		
		
		
	}) */


	
window.onload=(function(){
	$("#afterSection").removeClass("undis");
	var arrObj = {};
	var bindClick = function (liId, selArr,ulName){
		$(document).on('click', '#'+ulName+' #'+liId+' ul label', function(){
		    if($(this).attr('class') === 'checked'){
				// unselect
				$(this).attr('class', '');
				$(this).parent().removeClass('bg_light');
				$('#'+ulName+' #'+liId+' p label').attr('class','');
				$('#'+ulName+' #'+liId+' p').removeClass('bg_light');
			}else{
				// select
				$(this).parent().addClass('bg_light');
				$(this).attr('class', 'checked');
				var isAllTrue = '';
				$.each($(this).parents('#'+ulName+' #'+liId).find('li label'), function(i, v){
					if ($(v).attr('class') === "checked") {
						isAllTrue += '1';
					}else{
						isAllTrue += '0';
					}
				});
				if (isAllTrue.indexOf('0') > -1) {
					$('#'+ulName+' #'+liId+' p label').attr('class','');
					$('#'+ulName+' #'+liId+' p').removeClass('bg_light');
				}else{
					$('#'+ulName+' #'+liId+' p label').attr('class','checked');
					$('#'+ulName+' #'+liId+' p').addClass('bg_light');
				};
			}
			var theitem = liId;
			selArr.length = 0;
			$.each($('#'+ulName+' #'+liId+' ul label'), function(i, v){
				if ($(v).attr('class') === 'checked') {
					selArr.push($(v).parent().find('input').val());
				}
			});
			console.log(arrObj);
		});

		$(document).on('click', '#'+ulName+' #'+liId+' p label', function(){
			var b = $(this).parent().parent().find('label').attr('class');
			if (b === 'checked') {
				$(this).parent().parent().find('label').attr('class', '');
				$('#'+ulName+' #'+liId+' ul label').attr('class', '');
				selArr.length = 0;
				$(this).parent().removeClass('bg_light');
				$(this).parent().parent().find('li').removeClass('bg_light');
			}else{
				$(this).parent().addClass('bg_light');
				$(this).parent().parent().find('li').addClass('bg_light');
				$(this).parent().parent().find('label').attr('class', 'checked');
				$('#'+ulName+' #'+liId+' ul label').attr('class', 'checked');
				$.each($('#'+ulName+' #'+liId+' ul label'), function(i, v){
					selArr.push($(v).parent().find('input').val());
				});
			}
			console.log(arrObj);
		});
	}
	for (var i = 0, len = $('#add > li').size(); i < len; i++) {
		var liId = $('#add > li').eq(i).attr('id');
		arrObj['selArrli'+i]=[];
		bindClick(liId, arrObj['selArrli'+i],"add");
	}
	 
	
	for (var i = 0, len = $('#inner > li').size(); i < len; i++) {
		var liId = $('#inner > li').eq(i).attr('id');
		arrObj['selArrli'+i]=[];
		bindClick(liId, arrObj['selArrli'+i],"inner");
	}
	for (var i = 0, len = $('#outer > li').size(); i < len; i++) {
		var liId = $('#outer > li').eq(i).attr('id');
		arrObj['selArrli'+i]=[];
		bindClick(liId, arrObj['selArrli'+i],"outer");
	} 
	
	
	
	
	
	//以事件委托的形式，绑定元素点击事件
	$(document).on('click', '.showAll', function(){
		$(this).parent().parent().find('.item_bar').show();
		$(this).text('折叠').addClass('showSome').removeClass('showAll');
	});
	$(document).on('click', '.showSome', function(){
		$(this).parent().parent().find('.item_bar').hide();
		$(this).text('展开').addClass('showAll').removeClass('showSome');
	});
	// 选择列表end
	

	$(document).on('click', '.btn1', function(){
		$('#addDiv').removeClass('undis');
	});
	$(document).on('click', '.btn2', function(){
		$('.mask1').removeClass('undis');
	});
	$(document).on('click', '.btn1_1', function(){
		$('#innerDiv').removeClass('undis');
		$('.mask1').addClass('undis');
	});
	$(document).on('click', '.btn1_2', function(){
		$('#outerDiv').removeClass('undis');
		$('.mask1').addClass('undis');
	});
	$(document).on('click', '.btn1_3', function(){
		$('.mask1').addClass('undis');	
	});
	$(document).on('click', '.btn2_2', function(){
		$(this).parent().parent().parent().addClass('undis');
		$('.after').removeClass('undis');
	});
	// 删除按钮
	$(document).on('click', '.del_cross', function(){
		
		var yes =confirm("确定要删除吗?");
		if(yes){
			$(this).parent().remove();
			
		}
	
	});
	
	$("#toPicDetails").click(function(){
		window.location.href="${ctx }/app/manager/changeManagement/querySignaturePic?businessID="+${bill.projectChangeId}+"&businessType=105";
		return false;
	});
	
})

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	</script>
</body>
</html>