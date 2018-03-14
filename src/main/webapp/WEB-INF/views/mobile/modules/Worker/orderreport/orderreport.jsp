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
<title>上报返单</title>
<link rel="stylesheet" type="text/css"
	href="${ctxStatic}/mobile/modules/Manager/css/reset.css" />
<link rel="stylesheet" type="text/css"
	href="${ctxStatic}/mobile/modules/Manager/css/header.css" />
<link rel="stylesheet" type="text/css"
	href="${ctxStatic}/mobile/modules/Manager/css/common.css" />
<link rel="stylesheet" type="text/css"
	href="${ctxStatic}/mobile/modules/Manager/css/list.css" />
<link rel="stylesheet" type="text/css"
	href="${ctxStatic}/mobile/modules/Manager/css/returnBrod.css" />
	<link rel="stylesheet" type="text/css"
		  href="${ctxStatic}/mobile/modules/pqc/css/globalUtil.css" />
	<link type="text/css" rel="stylesheet" 	href="${ctxStatic}/mobile/modules/Manager/css/waitMe.css" >
<script type="text/javascript"
		src="${ctxStatic}/mobile/modules/Manager/js/lib/jquery-2.1.1.min.js"></script>
	<script type="text/javascript"
		src="${ctxStatic}/mobile/modules/Manager/js/utils/calcRootFontSize.js"></script>
	<script type="text/javascript"
		src="${ctxStatic}/mobile/modules/Manager/js/utils/history.js"></script>
	<script type="text/javascript"
			src="${ctxStatic}/mobile/modules/Manager/js/utils/waitMe.js"></script>
	<script type="text/javascript"
			src="${ctxStatic}/mobile/modules/Worker/js/lib/jquery.form.js"></script>
	<script type="text/javascript"
			src="${ctxStatic}/mobile/modules/pqc/js/utils/global.js"></script>
</head>
<script type="text/javascript">

var boo=false;
function submit(){
	checkForm();
	if(boo){
		$("#inputForm").submit();	
	}
	
}

//手机校验
function checkPhone() {
	$("#error1").hide();
	$("#error").hide();
	var phone = $("#customerPhone").val();
	if (phone == null || phone == "") {
		boo=false;
		return false;
	}
	
	if (phone.length != 11) {
		$("#error1").show();
		boo=false;
		return false;
	}else{
		$("#error1").hide();
			$.ajax({
				url : "${ctx}/app/worker/orderReport/checkCustomerPhone?customerPhone=" + phone,
				type : "post",
				success : function(data) {
					if (data == "0") {
						$("#error").hide();
						boo=true;
						return true;
					} else {
						$("#error").show();
						boo=false;
						return false;
					}	
				}
			});
	}
}
function checkForm(){
	var customerName = $("#customerName").val();
	if (customerName == null || customerName == "") {
		boo=false;
		return false;
	}
	
	var communityName = $("#communityName").val();
	if (communityName == null || communityName == "") {
		boo=false;
		return false;
	}
	checkPhone();
	return boo;
	 
}
</script>
<body>
	<div class="font0">
		<header>
		    <c:if test="${sta=='0'}">
		    <a class="back_btn" href="${ctx}/app/worker/toindex"></a>
		    </c:if>
			 <c:if test="${sta=='1'}">
		    <a class="back_btn" href="${ctx}/app/worker/toindex1"></a>
		    </c:if>
			<h2 class="title">介绍客户给公司</h2>
		</header>
		<form id="inputForm" action="${ctx}/app/worker/orderReport/orderReportSave"
			method="post" class="form-horizontal">

			<section class="pad_top11">
				<p class="tip-p font28 col_f91b0a pad_left3 pad_rgt3">您介绍的客户，两个月内，去了公司，或是签了装修合同并进入施工流程，公司将给予您一定金额的奖励。</p>
				<div class="sec font0 clearfix">
					<ul class="pad_top3 pad_left3">
						<li class="mar_btm24 clearfix"><span
							class="col_5 font28 flo_left">客户姓名：<input id="customerName" name="customerName"
								class="wrt bor_eb" placeholder="最多15个汉字，必须填写" type="text">
						</span></li>
						<li class="mar_btm30 clearfix"><span
							class="col_5 font28 flo_left">手机号码：<input name="customerPhone" id="customerPhone"
								class="wrt bor_eb" placeholder="只验证11位数字，必须填写" type="text" onblur="checkPhone()"/>
								<br />
								<span hidden="hidden" id="error1"><font color="red">手机格式错误！</font></span>
								<span hidden="hidden" id="error"><font color="red">客户手机号已存在！</font></span>
						</span>
						</li>
						
						<li class="mar_btm30 clearfix"><span
							class="col_5 font28 flo_left">小区名称：<input id="communityName" name="communityName"
								class="wrt bor_eb" placeholder="最多10个汉字，必须填写" type="text">
						</span></li>
						<li class="clearfix"><span
							class="col_5 font28 flo_left line_hgt12">详细地址：</span> <textarea name="address"
								class="wrt_area bor_eb font24" placeholder="最多30个汉字"></textarea></li>
						<!-- <a class="destiny" href="javascript:;">获取当前位置</a> -->
						
						<li class="mar_btm30 clearfix" style="padding-top: 15px"><span
							class="col_5 font28 flo_left line_hgt12">备注信息：</span>
							<textarea name="reportRemarks"
								class="wrt_area bor_eb font24" placeholder="最多30个汉字"></textarea>
					   </li>
					</ul>
					</span> 
					<a class="sub" id="subOrderReport" href="javascript:;" onclick="submit()">上报返单</a>
				</div>
			</section>
		</form>
		<div style="padding-bottom: 300px;"></div>
	</div>
</body>
</html>