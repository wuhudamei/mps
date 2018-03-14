<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1, user-scalable=no">
    <meta content="yes" name="apple-mobile-web-app-capable">
    <meta content="black" name="apple-mobile-web-app-status-bar-style">
    <meta content="telephone=no" name="format-detection">
    <meta content="email=no" name="format-detection">
    <title>申请主材安装</title>
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/20170919/reset.css" />
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/20170919/header.css" />
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/lib/mobiscroll.custom-2.16.1.min.css">
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/20170919/common.css" />
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/20170919/allBtn.css" />
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/20170919/apply.css" />
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/20170919/layout.css" />
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/20170919/mask.css" />
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/waitMe.css" >
</head>

<body>
    <div class="wrap">
        <header>
            <a class="back_btn" href="${ctx }/app/manager/newEnginInstallController/installApplication?id=${order.id }"></a>
            <h2 class="title">提交申请</h2>
        </header>
        <!-- /header -->
        <section class="pt112 shadow bor_btm_e5 mb24">
            <p class="font32 col_blue pb30 pl30 pr30 tc">${order.communityName }-${order.buildNumber }-${order.buildUnit }-${order.buildRoom}-${order.customerName }
            </p>
            <div class="sec pl30 pr30 font0 bg_f clearfix">
                <ul class="pad_top3">
                    <li class="mb30 rele clearfix">
                        <span class="col_grey font30 fl">安装项名称：</span>
                        <p class="font30 col_3 flow">${orderInstallPlan.installItemName }</p>
                    </li>
                    <li class="mb30 clearfix">
                        <span class="col_grey font30 fl">计划申请安装日期：</span>
                        <p class="font30 col_3 flow"><fmt:formatDate value="${orderInstallPlan.planIntoDate}" pattern="yyyy-MM-dd"/></p>
                    </li>
                    <li class="mb30 rele clearfix">
                        <span class="col_grey font30">请上传至少一张现场图片 :</span>
                    </li>
                </ul>
            </div>
        </section>
        <!-- /section -->
        <form id="jvForm" action="" method="post" enctype="multipart/form-data">
        
        	<input type="text" hidden="hidden" name="orderInstallItemId" value="${orderInstallPlan.orderInstallItemId}">
        	
	        <section>
	            <div class="pics bor_top_ddd font0 shadow bg_f clearfix">
	            
	                <%-- <div class="pic">
	                    <img src="${ctxStatic}/mobile/modules/Manager/img/common/eg.png" alt="">
	                    <a class="delBtn" href="javascript:void(0)"></a>
	                </div> --%>
	               
	                <div class="pic camera" id="camera" href="javascript:void(0)">
	                    <img src="${ctxStatic}/mobile/modules/Manager/img/common/upPic.png" alt="">
	                    <input type="file" accept="image/*" onchange="preview(this)">
	                </div>
	                
	                <input type="text" hidden="hidden" id="shit"  value="1">
	           		<input type="text" hidden="hidden" id="num" name="num" value="">
	            
	            </div>
	        </section>
        </form>
        <!-- /section -->
        <div class="pt44">
            <a class="subBtn">确认申请</a>
        </div>
        <div style="padding-bottom:300px;"></div>
    </div>
    <!-- /.wrap -->
    <!--公共弹层背景  class 「_in」 显示弹层背景-->
    <div class="alertMask" id="alertMask">
    </div>
    <!-- /.alertMask -->
    <!--公共弹层  class 「_in」 显示弹层内容-->
    <div class="maskWrapper" id="alertAlert">
        <div class="maskTit">提 示</div>
        <div class="maskContent">
            <p class="text-center" id="alertRemark">请上传至少一张图片
            </p>
        </div>
        <div class="maskBtns">
            <button class="maskBtnOne" type="button" onclick="sure()">确定</button>
        </div>
    </div>
    <!-- /.maskWrapper -->
    <div class="maskWrapper" id="alertAlertTwo">
        <div class="maskTit">提 示</div>
        <div class="maskContent">
            <p>您确定要提交吗？
            </p>
        </div>
        <div class="maskBtns twoBtns clearfix">
            <button class="maskBtn" type="button" onclick="sure2()">确定</button>
            <button class="maskBtn" type="button" onclick="cancel()">取消</button>
        </div>
    </div>
    <!-- /.maskWrapper -->
    <div class="maskWrapper" id="alertAlertThree">
        <div class="maskTit">提 示</div>
        <div class="maskContent">
            <p>主材安装快捷申请提交成功
            </p>
        </div>
        <div class="maskBtns">
            <button class="maskBtnOne" type="button" onclick="backlast()">确 定</button>
        </div>
    </div>
    <!-- /.maskWrapper -->
    <script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/jquery-2.1.1.min.js"></script>
    <script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/calcRootFontSize.js"></script>
    <script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/mobiscroll.custom-2.16.1.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/waitMe.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/pqc/js/home/compresspic.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/jquery.form.js"></script>
    <script>
    
	    function run_waitMe(effect,text){
			$('body').waitMe({
				effect: effect,
				text: text,
				bg: 'rgba(255,255,255,0.7)',
				color:'#000',
				sizeW:'',
				sizeH:'',
				source: 'img.svg'
			});
		}
    	var id = '${orderInstallPlan.orderInstallItemId}';
		var orderId = '${order.id}';
		
		function backlast(){
			window.location.href = "${ctx }/app/manager/newEnginInstallController/installApplication?id="+orderId;
		}
		
		function sure(){
			$("#alertMask").removeClass('_in');
	        $("#alertAlert").removeClass('_in');
		}
		function cancel(){
			$("#alertMask").removeClass('_in');
	        $("#alertAlertTwo").removeClass('_in');
		}
		
		$(document).ready(function() {
			//绑定onclick事件
			$(".subBtn").bind('click',cartSubmit);
		});
	
		//主材安装提前申请--校验
		function cartSubmit() {
			//至少上传一张图片
			var num = $("#num").val();
			if(null==num || ""==num ||num<1){
				$("#alertRemark").text("请上传至少一张图片")
				$("#alertMask").addClass('_in');
		        $("#alertAlert").addClass('_in');
				return false;
			}
			$("#alertMask").addClass('_in');
	        $("#alertAlertTwo").addClass('_in');
		}
		
		function sure2(){
			
			//防止重复提交
			$("#alertMask").removeClass('_in');
        	$("#alertAlertTwo").removeClass('_in');
			
			//防止重复提交
			run_waitMe('正在提交数据,请稍等');
			$(".submit_btn").css("color","#CCC");
			$(".submit_btn").unbind("click");
			
			//上报提交表单
			var options ={
					url : "${ctx}/app/manager/newEnginInstallController/save_install_apply_advance_apply_ajax",
					type: "post",
					success : function(data){
						$('body').waitMe('hide');
						if(null!=data && data=="0"){
							$("#alertMask").addClass('_in');
		    		        $("#alertAlertThree").addClass('_in');
						}else if(data=="1"){
							$("#alertRemark").text("安装项id为空");
		        			$("#alertMask").addClass('_in');
					        $("#alertAlert").addClass('_in');
						}else if(data=="2"){
							$("#alertRemark").text("请上传至少一张图片");
		        			$("#alertMask").addClass('_in');
					        $("#alertAlert").addClass('_in');
						}else if(data=="3"){
							$("#alertRemark").text("每个安装项【提前申请】只能申请一次");
		        			$("#alertMask").addClass('_in');
					        $("#alertAlert").addClass('_in');
						}else if(data=="4"){
							$("#alertRemark").text("项目经理未登录");
		        			$("#alertMask").addClass('_in');
					        $("#alertAlert").addClass('_in');
						}else if(data=="5"){
							$("#alertRemark").text("安装项【提前申请】申请失败");
		        			$("#alertMask").addClass('_in');
					        $("#alertAlert").addClass('_in');
						}
						
					}
				}
			//ajax提交form
			$("#jvForm").ajaxSubmit(options);
			
			
		}
		
		
		
		 //--------------------------------图片上传显示 start--------------------------------------
		function preview(file) { 
			var num = $("#shit").val();
			var prevDiv = $('.camera');  
			if (file.files && file.files[0]) {  
				var reader = new FileReader();  
				reader.onload = function(evt){
					$('<div class="pic"><img src="' + evt.target.result + '" onload="compresspic(this,uploadpic);" id= "'+num+'"/><a class="delBtn" href="javascript:void(0)">删除</a></div>').insertBefore('.camera');
				}    
				reader.readAsDataURL(file.files[0]);  
			}else {  
				prevDiv.innerHTML = '<div class="img" style="filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src=\'' + file.value + '\'"></div>';  
				var pic = '<div class="img" style="filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src=\'' + file.value + '\'"></div>';
				console.log(file,file.value);
			}
		} 
		$(document).on('click', '.delBtn', function(){
			var numReal=$(this).prev().attr("id");
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
			var shit =	$("#shit").val();
			if(pic){
				num++;
				input.setAttribute("id","num"+shit);
				input.setAttribute('hidden', 'hidden'); 
				input.setAttribute('name', 'photo'); 
				input.setAttribute("type","text");
				input.setAttribute("value", pic);
				hiddenForm.appendChild(input);
				shit++;
				$("#num").val(num);
				$("#shit").val(shit);
			}
		}
	    
	  	//--------------------------------图片上传显示 end--------------------------------------
	    
	    
    </script>
</body>

</html>