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
	<title>安装申请</title>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/20170919/reset.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/20170919/header.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/lib/mobiscroll.custom-2.16.1.min.css">
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/20170919/common.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/20170919/allBtn.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/20170919/apply.css"/>
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/20170919/mask.css" />
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/waitMe.css" >
</head>
<body>
	<div class="wrap">
		<header>
			<a class="back_btn" href="${ctx }/app/manager/newEnginInstallController/installApplication?id=${order.id }"></a>
			<h2 class="title">安装申请</h2>
		</header>
		<!-- /header -->
		<section class="pad_top88 shadow bor_btm_e5">
			<p class="font32 col_blue pt30 pb30 pl30 pr30 tc">${order.communityName }-${order.buildNumber }-${order.buildUnit }-${order.buildRoom}-${order.customerName }</p>
			
			<form id="jvForm" action="" method="post" enctype="multipart/form-data">
				
				<input type="text" hidden="hidden" name="orderId" value="${order.id }">
				<input type="text" hidden="hidden" name="orderInstallItemId" value="${orderInstallPlan.orderInstallItemId}">
				<input type="text" hidden="hidden" name="isShowInstallDescription" value="${orderInstallPlan.isShowInstallDescription}">
				
				<div class="sec font0 bor_top_e5 bg_f clearfix">
					<ul class="pad_top3">
						<li class="mb30 rela pl30 pr30 clearfix">
							<span class="col_grey font30 fl">安装项目名称：</span>
							<p class="font30 col_3 flow">${orderInstallPlan.installItemName }</p>
						</li>
						<li class="mb30 pl30 pr30 clearfix">
							<span class="col_grey font30">期望进场日期：</span>
							<input class="date date1" type="text" readonly="" id="dataday2" name="dataday2" value="" placeholder="请输入日期" data-lcalendar="">
						</li>
						<li class="mb30 pl30 pr30 rela clearfix">
							<span class="col_grey font30 pl2em">备注说明：</span>
							<textarea class="instruc limitText" placeholder="最多输入200字!" maxlength="200" id="remarks" name="remarks">${order.employeeRealName }${order.employeePhone }</textarea>
						</li>
						<c:if test="${orderInstallPlan.isShowInstallDescription eq 1 }">
							<li class="mb30 pl30 pr30 rela clearfix">
								<span class="col_ea0a0a font30 pl1em must">*</span>
								<span class="col_grey font30">安装要求：</span>
								<textarea class="instruc limitText" placeholder="请您按照下方提示输入数据！" maxlength="200" id="installRemarks" name="installRemarks"></textarea>
								<p class="font24 tips">${orderInstallPlan.installDescription}</p>
							</li>
						</c:if>
					</ul>
				</div>
			</form>
		</section>
		<!-- /section -->
		<div class="pt50">
			<a class="subBtn">确认申请</a>
		</div>
		<div style="padding-bottom:300px;"></div>
	</div>
	<!-- /wrap -->
	
	<!--公共弹层背景  class 「_in」 显示弹层背景-->
    <div class="alertMask" id="alertMask">
    </div>
    <!-- /.alertMask -->
    <!--公共弹层  class 「_in」 显示弹层内容-->
    <div class="maskWrapper" id="alertAlert">
        <div class="maskTit">提 示</div>
        <div class="maskContent">
            <p id="alertRemark">
            </p>
        </div>
        <div class="maskBtns">
            <button class="maskBtnOne" type="button" onclick="sure()">确 定</button>
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
            <p>安装项申请成功
            </p>
        </div>
        <div class="maskBtns">
            <button class="maskBtnOne" type="button" onclick="backlast()">确 定</button>
        </div>
    </div>
    
    
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/calcRootFontSize.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/mobiscroll.custom-2.16.1.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/global.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/waitMe.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/jquery.form.js"></script>
	<script>
	
	
			var now = new Date();
			var currYear = now.getFullYear();
			var currMonth = now.getMonth() + 1;
			var currDay = now.getDate();
			$("#dataday2").val(globalUtil.fn.formatDate(new Date(currYear, currMonth - 1, currDay + 4),'yyyy-MM-dd'));
			//mobiScroll插件选项
			var opt = {
				theme: "ios",
				lang: 'zh',
				dateFormat: 'yyyy-mm-dd', // 面板日期格式
				dateOrder: 'yyyymmdd', //面板中日期排列格式
				showNow: false,
				nowText: "今",
				endYear: currYear + 3, //结束年份
				minDate: new Date(currYear, currMonth - 1, currDay + 4),
				onSelect: function (textVale, inst) { //选中时触发事件
					console.log("我被选中了.....");
				},
				onClose: function (textVale, inst) { //插件效果退出时执行 inst:表示点击的状态反馈：set/cancel
					console.log("textVale--" + textVale);
					console.log(this.id); //this表示调用该插件的对象
				}
			};
			$("#dataday2").mobiscroll().date(opt);
		     
				
			// 字数限制
			(function limit() {
				 $(".limitText").keyup(function(){  
		               if($(".limitText").val().length > 200){
		                   $(".limitText").val( $(".limitText").val().substring(0,200) );
		               }
		            })
			})()
			
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
			
			//申请安装--校验
			function cartSubmit() {
				//期望进场日期
				var dataday2 = $("#dataday2").val();
				if(dataday2 == "" || dataday2 == null){
					$("#alertRemark").text("请选择期望进场日期")
					$("#alertMask").addClass('_in');
			        $("#alertAlert").addClass('_in');
					return false;
				}
				//备注说明
				var remarks = $("#remarks").val();
				if(remarks == "" || remarks == null){
					$("#alertRemark").text("请输入备注说明")
					$("#alertMask").addClass('_in');
			        $("#alertAlert").addClass('_in');
					return false;
				}
				var isShowInstallDescription = '${orderInstallPlan.isShowInstallDescription}';
				if(isShowInstallDescription==1){
					var installRemarks = $("#installRemarks").val();
					if(installRemarks == "" || installRemarks == null){
						$("#alertRemark").text("请输入安装要求")
						$("#alertMask").addClass('_in');
				        $("#alertAlert").addClass('_in');
						return false;
					}
				}
				$("#alertMask").addClass('_in');
		        $("#alertAlertTwo").addClass('_in');
				
			}
			
			function sure2(){
				
				//防止重复提交
				$("#alertMask").removeClass('_in');
	        	$("#alertAlertTwo").removeClass('_in');
	        
				$(".submit_btn").css("color","#CCC");
				$(".submit_btn").unbind("click");
				
				var installItemName = '${orderInstallPlan.installItemName }';
				
				$.ajax({
					url : "${ctx}/app/manager/newEnginInstallController/checksizeAjax",
					type : "POST",
					dataType : "json", 
					data:{
						id:id
					},
					success : function(data){
						if(data == 0){
		
							formSubmit();
							
						}else if(data == 1){
							$("#alertRemark").text("安装项id为空");
							$("#alertMask").addClass('_in');
					        $("#alertAlert").addClass('_in');
						}else if(data == 2){
							$("#alertRemark").text("该主材只有复尺后才能申请安装!");
							$("#alertMask").addClass('_in');
					        $("#alertAlert").addClass('_in');
						}else if(data == 3){
							$("#alertRemark").text("订单包含的橱柜、木门、木地板安装项未验收完毕，请及时处理。");
							$("#alertMask").addClass('_in');
					        $("#alertAlert").addClass('_in');
						}else if(data.code == 4){
							$("#alertRemark").text("该工地" + data.orderConfirmStartWorkDateString + "开工，按照工程部规定主材（" + installItemName + "）开工" + data.workApplyDayString + "天后（" + data.applyIntoRemarks + "）才可以申请安装，如果需提前申请，请点击【提前申请】，如有其它问题请联系大区经理或助理。");
							$("#alertMask").addClass('_in');
					        $("#alertAlert").addClass('_in');
						}else if(data.code == 5){
							$("#alertRemark").text("该工地" + data.orderConfirmStartWorkDateString + "开工，按照工程部规定主材（" + installItemName + "）开工" + data.workApplyDayString + "天后（" + data.applyIntoRemarks + "）才可以申请安装，如果需提前申请，请点击【提前申请】，如有其它问题请联系大区经理或助理。");
							$("#alertMask").addClass('_in');
					        $("#alertAlert").addClass('_in');
						}else{
							$("#alertRemark").text("安装项不可申请安装");
							$("#alertMask").addClass('_in');
					        $("#alertAlert").addClass('_in');
						}
					}
				});
			}
			function formSubmit(){
				//防止重复提交
				run_waitMe('正在提交数据,请稍等');
				$(".submit_btn").css("color","#CCC");
				$(".submit_btn").unbind("click");
				
				//上报提交表单
				var options ={
						url : "${ctx}/app/manager/newEnginInstallController/save_new_install_apply_ajax",
						type: "post",
						success : function(data){
							$('body').waitMe('hide');
							if(null!=data && data=="0"){
								$("#alertMask").addClass('_in');
			    		        $("#alertAlertThree").addClass('_in');
							}else if(data=="1"){
								$("#alertRemark").text("订单id为空");
			        			$("#alertMask").addClass('_in');
						        $("#alertAlert").addClass('_in');
							}else if(data=="2"){
								$("#alertRemark").text("安装项id为空");
			        			$("#alertMask").addClass('_in');
						        $("#alertAlert").addClass('_in');
							}else if(data=="3"){
								$("#alertRemark").text("期望进场日期为空");
			        			$("#alertMask").addClass('_in');
						        $("#alertAlert").addClass('_in');
							}else if(data=="4"){
								$("#alertRemark").text("备注说明为空");
			        			$("#alertMask").addClass('_in');
						        $("#alertAlert").addClass('_in');
							}else if(data=="5"){
								$("#alertRemark").text("安装要求为空");
			        			$("#alertMask").addClass('_in');
						        $("#alertAlert").addClass('_in');
							}else if(data=="6"){
								$("#alertRemark").text("该安装项已经申请过了");
			        			$("#alertMask").addClass('_in');
						        $("#alertAlert").addClass('_in');
							}else if(data=="7"){
								$("#alertRemark").text("安装项内容不存在");
			        			$("#alertMask").addClass('_in');
						        $("#alertAlert").addClass('_in');
							}else if(data=="8"){
								$("#alertRemark").text("项目经理未登陆");
			        			$("#alertMask").addClass('_in');
						        $("#alertAlert").addClass('_in');
							}else if(data=="9"){
								$("#alertRemark").text("订单安装项更新失败");
			        			$("#alertMask").addClass('_in');
						        $("#alertAlert").addClass('_in');
							}else if(data="10"){
								$("#alertRemark").text("订单安装项日志保存失败");
			        			$("#alertMask").addClass('_in');
						        $("#alertAlert").addClass('_in');
							}else{
								$("#alertRemark").text("订单安装项保存失败");
			        			$("#alertMask").addClass('_in');
						        $("#alertAlert").addClass('_in');
							}
							
						}
					}
				//ajax提交form
				$("#jvForm").ajaxSubmit(options);
				
				
			}
	
    </script>
</body>
</html>