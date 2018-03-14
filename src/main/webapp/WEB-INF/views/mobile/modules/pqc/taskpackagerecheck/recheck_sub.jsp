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
	<title>任务包复核</title>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/pqc/css/reset.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/pqc/css/header.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/pqc/css/check_confirm.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/pqc/css/globalUtil.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/waitMe.css" >
</head>
<body>
	<div class="g-check" id="aboveId">
		<header>
			<a class="back_btn" href="${ctx}/app/pqc/taskpackagerecheck/taskpackageRecheckList"></a>
			<h2 class="title">任务包复核</h2>
		</header><!-- /header -->
		<section class="check_section padtop8 mar_btm2">
			<h3 class="dot">基本信息</h3>
			<ul class="lists shadow">
				<li class="line_hgt6 clearfix">
					<span class="span_left">顾客信息：</span>
					<p class="p_right">${vo.customerMessage}-${vo.customerName}</p>
				</li>
				<li class="line_hgt6 clearfix">
					<span class="span_left">任务包名称：</span>
					<p class="p_right">${vo.packageName}</p>
				</li>
				<li class="line_hgt6 clearfix">
					<span class="span_left">项目经理：</span>
					<p class="p_right">${vo.itemCustomer}-${vo.phone}</p>
				</li>
				<li class="line_hgt6 clearfix">
					<span class="span_left">户　　型：</span>
					<p class="p_right">
						<c:choose>
							<c:when test="${vo.houseType == '1'}">一居室</c:when>
							<c:when test="${vo.houseType == '2'}">二居室</c:when>
							<c:when test="${vo.houseType == '3'}">三居室</c:when>
							<c:when test="${vo.houseType == '4'}">四居室</c:when>
							<c:when test="${vo.houseType == '5'}">五居室</c:when>
							<c:otherwise>其它居室</c:otherwise>
						</c:choose>
					</p>
				</li>
				<li class="line_hgt6 clearfix">
					<span class="span_left">建筑面积：</span>
					<p class="p_right"><c:if test="${not empty vo.coveredArea}">${vo.coveredArea}平米</c:if></p>
				</li>
			</ul>
			<h3 class="dot pos_ret">工程清单</h3>
			<form id="inputSubmit">
				<input type="hidden" name="id" value="${vo.id}"/>
				<c:forEach items="${list}" var="procedure" varStatus="status">
					<ul class="lists shadow">
						<li class="salary btm_border mar_top20">
						<ul class="rnd">
							<p class="line_hgt6 font28 col3 clearfix">${status.index+1}、${procedure.procedureName}- 单位：${procedure.measurementUnitLabel}</p>
							<li class="line_hgt6 clearfix">
								<span class="pro_span font0">
									<input type="hidden" id="procedureName${status.index}" value="${procedure.procedureName}"/>
									<input type="hidden" name="procedureList[${status.index}].id" value="${procedure.id}"/>
									<span class="col6 font28">预算工程量：</span>
									<span class="font28<c:if test="${procedure.budgetNumber*1.03 < procedure.realNumber}"> col_red</c:if>">${procedure.budgetNumber}</span>
								</span>
								<span class="pro_span font0">
									<span class="col6 font28">实际工程量：</span>
									<span class="font28<c:if test="${procedure.budgetNumber*1.03 < procedure.realNumber}"> col_red</c:if>">${procedure.realNumber}</span>
								</span>
							</li>
							<li class="line_hgt6 clearfix">
								<span class="pro_span font0">
									<span class="col6 font28">工料费单价：</span>
									<span class="col3 font28">${procedure.synthesizePrice}元</span>
									<input type="hidden" id="synthesizePrice${status.index}" value="${procedure.synthesizePrice}">
								</span>
								<span class="pro_span font0">
									<span class="col6 font28">工料费金额：</span>
									<span class="col3 font28" id="laborAuxiliaryMaterialsSettleAmount${status.index}">${procedure.laborAuxiliaryMaterialsSettleAmount}元</span>
									<input type="hidden" id="laborAuxiliaryMaterialsSettleAmountPrice${status.index}" name="procedureList[${status.index}].laborAuxiliaryMaterialsSettleAmount" value="${procedure.laborAuxiliaryMaterialsSettleAmount}"/>
								</span>
							</li>
							<li class="line_hgt6 clearfix">
								<span class="pro_span font0">
									<span class="col6 font28">人工费单价：</span>
									<span class="col3 font28">${procedure.laborPrice}元</span>
									<input type="hidden" id="laborPrice${status.index}" value="${procedure.laborPrice}">
								</span>
								<span class="pro_span font0">
									<span class="col6 font28">人工费金额：</span>
									<span class="col3 font28" id="laborSettleAmount${status.index}">${procedure.laborSettleAmount}元</span>
									<input type="hidden" id="laborSettleAmountPrice${status.index}" name="procedureList[${status.index}].laborSettleAmount" value="${procedure.laborSettleAmount}"/>
								</span>
							</li>
							<li class="line_hgt6 clearfix">
								<span class="pro_span font0">
									<span class="col6 font28">辅料费单价：</span>
									<span class="col3 font28">${procedure.accessoriesPrice}元</span>
									<input type="hidden" id="accessoriesPrice${status.index}" value="${procedure.accessoriesPrice}">
								</span>
								<span class="pro_span font0">
									<span class="col6 font28">辅料费金额：</span>
									<span class="col3 font28" id="auxiliaryMaterialsSettleAmount${status.index}">${procedure.auxiliaryMaterialsSettleAmount}元</span>
									<input type="hidden" id="auxiliaryMaterialsSettleAmountPrice${status.index}" name="procedureList[${status.index}].auxiliaryMaterialsSettleAmount" value="${procedure.auxiliaryMaterialsSettleAmount}"/>
								</span>
							</li>
							<li class="line_hgt6 clearfix">
								<span class="pro_span font0">
									<span class="col6 font28">备注：</span>
									<span class="col3 font28">${procedure.remarks}</span>
								</span>
							</li>
							<li class="line_hgt6 clearfix">
								<span class="pro_span font0">
								    <input type="hidden" id="settlementNumber${status.index}" name="procedureList[${status.index}].settlementNumber" value="${procedure.settlementNumber}"/>
									<span class="col6 font28">复核工程量 ：<input class="days font28 wid_200 mar_top05" type="text" id="recheckNumber${status.index}" name="procedureList[${status.index}].recheckNumber" value="${procedure.realNumber}" oninput="this.value=this.value.replace(/[^\d\.]/g,'');getTotal(${status.index});"></span>
								</span>
							</li>
							<li class="line_hgt6 mar_btm20 clearfix">
								<span class="span_left col6">复 核 说 明 ：</span>
								<p class=""><input class="days wid370 font28 mar_top05" type="text" name="procedureList[${status.index}].recheckRemarks"/></p>
							</li>
						</ul>
					</li>
				</ul>
			</c:forEach>
			</form>
		</section>
		<footer>
			<a class="btn watch_btn font28" id="confirmButton">提交复核</a>
		</footer>
		
		<div style="background:rgba(0,0,0,.4);z-index:9;" class="g-mask undis" id ="subReport">
			<div id="g-done_ask">
				<p class="first">确认要提交复核吗？</p>
				<p class="second">
					<a href="#" onclick="hideSubmitAlert()">取消</a>
					<a href="#" onclick="submitForm()" id="formButtonId">确认</a>
				</p>
			</div> 
		</div>
	</div>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/pqc/js/lib/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/pqc/js/utils/global.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/pqc/js/utils/calcRootFontSize.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/pqc/js/utils/history.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/waitMe.js"></script>
	<script type="text/javascript">
	
		//蒙层效果
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
	
	
		$(function(){
			$("#confirmButton").bind('click',alertReport);
            var backUrl = "${backUrl}";
            if(""!=backUrl){
                var decode = decodeURIComponent(backUrl);
                $(".back_btn").attr("href",decode);


            }


		});
	
		function alertReport(){
			$("#confirmButton").unbind("click");
			$("#subReport").show();
		}
		
		function hideSubmitAlert(){
			$("#confirmButton").bind('click',alertReport);
			$("#subReport").hide();
		}
		function submitForm() {
			$("#subReport").hide();
			var length = ${fn:length(list)};
		    var bool = false;
			for(var i=0;i<length;i++){
				var recheckNumber = $("#recheckNumber"+i).val();
				var procedureName = $("#procedureName"+i).val();
				if(recheckNumber == null || recheckNumber == ""){
					globalUtil.fn.alert("第"+(i+1)+"道工序【"+procedureName+"】的复核工程量不能为空",2.0);
					bool = true;
					break;
				}
				$("#settlementNumber"+i).val(recheckNumber);
			}
			if(bool){
				$("#confirmButton").bind('click',alertReport);
				return;
			}
			
			run_waitMe("win8","正在提交...请稍等");
			
			// 提交复核
			$.post("${ctx}/app/pqc/taskpackagerecheck/confirmTaskpackageRecheck",$("#inputSubmit").serialize(),function(date){
				if(date == "success"){
					$('#aboveId').waitMe('hide');
					globalUtil.fn.alert("提交复核成功!",2.0);
					var b ="${ctx}/app/pqc/taskpackagerecheck/taskpackageRecheckList";
					setTimeout('window.location.href="'+b+'"', 2000);
				}else{
					$('#aboveId').waitMe('hide');
					globalUtil.fn.alert("提交复核失败!",2.0);
				}
			});
		}
		function getTotal(index){
			var recheckNumber = ($("#recheckNumber"+index).val() == null || $("#recheckNumber"+index).val() == "") ? 0 : $("#recheckNumber"+index).val();
			var synthesizePrice = ($("#synthesizePrice"+index).val() == null || $("#synthesizePrice"+index).val() == "") ? 0 :$("#synthesizePrice"+index).val();
			var laborPrice = ($("#laborPrice"+index).val() == null || $("#laborPrice"+index).val() == "") ? 0 :$("#laborPrice"+index).val();
			var accessoriesPrice = ($("#accessoriesPrice"+index).val() == null || $("#accessoriesPrice"+index).val() == "") ? 0 :$("#accessoriesPrice"+index).val();
			
			var laborAuxiliaryMaterialsSettleAmount = (parseFloat(synthesizePrice) * parseFloat(recheckNumber)).toFixed(2);
			$("#laborAuxiliaryMaterialsSettleAmount"+index).html(laborAuxiliaryMaterialsSettleAmount+"元");
			$("#laborAuxiliaryMaterialsSettleAmountPrice"+index).val(laborAuxiliaryMaterialsSettleAmount);
			
			var laborSettleAmount = (parseFloat(laborPrice) * parseFloat(recheckNumber)).toFixed(2);
			$("#laborSettleAmount"+index).html(laborSettleAmount+"元");
			$("#laborSettleAmountPrice"+index).val(laborSettleAmount);
			
			
			var auxiliaryMaterialsSettleAmount = (parseFloat(accessoriesPrice) * parseFloat(recheckNumber)).toFixed(2);
			$("#auxiliaryMaterialsSettleAmount"+index).html(auxiliaryMaterialsSettleAmount+"元");
			$("#auxiliaryMaterialsSettleAmountPrice"+index).val(auxiliaryMaterialsSettleAmount);
		}
	</script>
</body>
</html>