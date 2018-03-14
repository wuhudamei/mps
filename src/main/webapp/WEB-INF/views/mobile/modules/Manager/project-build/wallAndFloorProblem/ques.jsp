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
	<title>上报问题</title>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/reset.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/header.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/new/common.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/quesDone.css"/>
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
	<div class="">
		<header>
			<a class="back_btn" href="${ctx}/app/manager/problem/wallAndFloor/list"></a>
			<h2 class="title">上报问题</h2>
		</header><!-- /header -->
		<p class="every">${materialManagement.communityName }-${materialManagement.buildNumber }-${materialManagement.buildUnit }-${materialManagement.buildRoom }-${materialManagement.customerName }</p>
		<form id="jvForm" action="" method="post" enctype="multipart/form-data">
			<input type="text" hidden="hidden" name="orderId" id= "orderId" value="${orderId }">
			<input type="text" hidden="hidden" name="quality" id= "quality" value="no1">
			<section class="">
				<ul class="item_lists bg_f font0">
					<li class="mar_btm50">
						<span class="font28 col_6">问 题 分 类 ：</span>
						<select class="select font24 col_9" id="problemTypeId" name="problemTypeId">
							<c:forEach items="${problemList }" var="problem">
								<option value="${problem.id }">${problem.typeName }</option>
							</c:forEach>
						</select>
					</li>
					<li class="mar_btm50 clearfix">
						<span class="font28 col_6 flo_left">是否影响工期：</span>
						<p class="p_right">
							<span class="radio_span marrgt1">
								<input type="radio" id="yes1" name="isDelay" value="yes1">
								<label class="radio0 font26 " id="yes" data-name="isDelay" for="yes1">是</label>
							</span>
							<span class="radio_span mar_left160">
								<input type="radio" id="no1" name="isDelay" value="no1">
								<label class="radio0 font26 checked" id="no" data-name="isDelay" for="no1">否</label>
							</span>
						</p>
					</li>
					<li class="mar_btm50 clearfix" id="tianshu">
						<span class="font28 col_6">影响工期天数：</span>
						<input class="effecfDay" name="delayDays" id="delayDays" type="text" value="0" onkeyup="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}"
									onafterpaste="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}">
					</li>
					<li class="mar_btm50 clearfix">
						<span class="font28 col_6 flo_left">描　　　　述：</span>
						<textarea class="describ" name="problemDescribe" id="problemDescribe" maxlength="100"></textarea>
					</li>
				</ul>
				<%-- <div class="bg_f bor_btm">
					<div class="intro font0 pad_left3">
						<span class="font28 col_3">上传图片</span>
					</div>
					<div class="pics font0 clearfix">
						<div class="pic camera" id="camera" href="javascript:void(0)">
							<!-- <img src="${ctxStatic}/mobile/modules/Manager/images/append.png" alt="调取摄像头"> -->
							<input type="file" accept="image/*" capture="camera" onchange="preview(this)">
						</div>
						<div class="pic">
							<img src="${ctxStatic}/mobile/modules/Manager/images/eg.png" alt="上传图片">
							<a class="del_pic" href="javascript:void(0)"></a>
						</div>
						<div class="pic">
							<img src="${ctxStatic}/mobile/modules/Manager/images/canlender.png" alt="上传图片">
							<a class="del_pic" href="javascript:void(0)"></a>
						</div>
						<div class="pic">
							<img src="${ctxStatic}/mobile/modules/Manager/images/budget_icon1.png" alt="上传图片">
							<a class="del_pic" href="javascript:void(0)"></a>
						</div>
						<div class="pic">
							<img src="${ctxStatic}/mobile/modules/Manager/images/budget_icon2.png" alt="上传图片">
							<a class="del_pic" href="javascript:void(0)"></a>
						</div>
						<div class="pic">
							<img src="${ctxStatic}/mobile/modules/Manager/images/eg.png" alt="上传图片">
							<a class="del_pic" href="javascript:void(0)"></a>
						</div>
					</div>
				</div> --%>
			</section>
		</form>
		<footer class="footer">
			<a id="start" class="footer_btn" href="javascript:;">提交问题</a>
		</footer>
	</div>
	
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
				<div class="font28 col_6 maskContent">墙地砖问题上报成功</div>
				<div class="maskBtns clearfix">
					<a class="maskBtn font33 col_f"  onclick="backlast()" href="javascript:;">我知道了</a>
				</div>
			</div>
		</div>
		
		
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/calcRootFontSize.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/history.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/waitMe.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/jquery.form.js"></script>
	<script type="text/javascript">
		$(function(){
			$(document).on('click', '.del_pic', function(){
				$(this).parent().remove();
			});
			// 单选框
			$('.qua_check label').click(function(){
			    var thisName = $(this).attr('data-name');
			    $('label[data-name="'+thisName+'"]').removeAttr('class') && $(this).attr('class', 'checked');
			  });
			$('.p_right label').click(function(){
			    var thisName = $(this).attr('data-name');
			    $('label[data-name="'+thisName+'"]').removeAttr('class') && $(this).attr('class', 'checked');
			  });
			// console.log('1---' + $('.describ').val());
			// console.log('2---' + $('.describ').text())
		});
		
		$(document).ready(function(){
			$("#start").bind('click',tijiao);
			$("#tianshu").addClass("undis");
			$('#yes1').click(function(){
				$("#tianshu").removeClass("undis");
			})
			$('#no1').click(function(){
				$("#tianshu").addClass("undis");
				$("#delayDays").val(0);
				
			})
		});
		
		var orderId = '${orderId}';
		
		//返回
		function backlast(){
				
			window.location.href="${ctx}/app/manager/problem/wallAndFloor/problemRecord?orderId="+orderId;
				
		}
		
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
		
		function tijiao(){
			//问题分类
			var problemTypeId = $("#problemTypeId").val();
			if(null==problemTypeId || problemTypeId=="" || undefined==problemTypeId){
				$("#alertRemarks").text("请选择问题分类");
				$("#alert").show();
				return;
			}
			//影响工期天数
			if($("#yes").hasClass("checked")){
				$("#quality").val("yes1");
				var days = $("#delayDays").val();
				if(days==""||null==days||undefined==days||0==days){
					$("#alertRemarks").text("确认影响工期后,影响工期天数不可为空");
					$("#alert").show();
					return;
					
				}
			}
			
			//描述
			var problemDescribe = $("#problemDescribe").val();
			if(problemDescribe == "" || problemDescribe == null){
				$("#alertRemarks").text("请输入问题描述...");
				$("#alert").show();
				return false;
			}
			
			$("#alert2").show();
			
		}
		
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
		function sure2(){
			$("#alert").hide();
			$("#alert2").hide();
			$("#alert3").hide();
			$("#start").css("color","#CCC");
			$("#start").unbind("click");
			var businessType = "2";
			$.ajax({
	            url: "${ctx}/app/manager/problem/wallAndFloor/problem_wallAndFloor_ajax_time",
	            type: "post",
	            data: {
	            		orderId:orderId,
	            		businessType:businessType
	            	  },
	            success: function(data){
	            	if(null!=data && data == "0"){
	            		applySubmit();
	            	}else if(data == "1"){
	            		$("#alertRemarks").text("订单id传送失败");
	            		$("#alert").show();
	            	}else{
	            		$("#alertRemarks").text("您刚刚提交过墙地砖问题上报，请耐心等待五分钟后再次操作");
	            		$("#alert").show();
	            	}
	            }
			 }); 
			
		}
		
		
		function applySubmit(){
			
			//防止重复提交
			run_waitMe('正在提交数据,请稍等');
			$("#start").css("color","#CCC");
			$("#start").unbind("click");
			
			//上报提交表单
			var options ={
					url : "${ctx}/app/manager/problem/wallAndFloor/wallAndFloor_problem_submit",
					type: "post",
					success : function(data){
						$('body').waitMe('hide');
						if(null!=data && data=="0"){
							$("#alert3").show();
						}else if(data=="1"){
							$("#alertRemarks").text("订单id为空");
		        			$("#alert").show();
						}else if(data=="2"){
							$("#alertRemarks").text("问题分类为空");
		        			$("#alert").show();
						}else if(data=="3"){
							$("#alertRemarks").text("是否影响工期为空");
		        			$("#alert").show();
						}else if(data=="4"){
							$("#alertRemarks").text("延期天数为空");
		        			$("#alert").show();
						}else if(data=="5"){
							$("#alertRemarks").text("上报问题描述为空");
		        			$("#alert").show();
						}else if(data=="6"){
							$("#alertRemarks").text("项目经理未登录");
		        			$("#alert").show();
						}else if(data=="7"){
							$("#alertRemarks").text("该问题分类内容不存在");
		        			$("#alert").show();
						}else if(data=="8"){
							$("#alertRemarks").text("上报问题保存失败");
		        			$("#alert").show();
						}else if(data=="9"){
							$("#alertRemarks").text("上报问题日志保存失败");
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