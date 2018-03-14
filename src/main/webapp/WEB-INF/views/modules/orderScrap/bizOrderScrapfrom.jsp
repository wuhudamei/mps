<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<meta charset="utf-8">
	<meta content="email=no" name="format-detection">
	<title>订单作废</title>
	<script type="text/javascript" src="${ctxStatic}/modules/orderComplaint/compresspic.js"></script>
	<script type="text/javascript" src="${ctxStatic}/modules/orderComplaint/jquery-2.1.1.min.js"></script>
<%-- 	<script type="text/javascript" src="${ctxStatic}/modules/orderComplaint/global.js"></script> --%>
<%-- 	<script type="text/javascript" src="${ctxStatic}/modules/orderComplaint/waitMe.js"></script> --%>
	
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/modules/orderComplaint/reset.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/modules/orderComplaint/complain.css"/>
	 <link rel="stylesheet" type="text/css" href="${ctxStatic}/bizOrderComplaint/css/reset.css"/>
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/bizOrderComplaint/css/complain.css"/>
	<style>
		.page_backBtn{position:absolute;top: 23px;left:10px;font-size: 15px;color: #333;padding: 3px 5px;}
	</style>
</head>
<script type="text/javascript">
	function submitForm (){
		var scrapReasonTypeid= $("#scrapReasonTypeid").val();
		if(null==scrapReasonTypeid || scrapReasonTypeid==""|| undefined==scrapReasonTypeid){
			alert("请选择作废原因");
			return false;
		}
		$("#inputForm").attr("action","${ctx}/orderScrap/orderScrap/scrapUpdate");
		$("#inputForm").submit();
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
		
		var hiddenForm = document.getElementById("inputForm");
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
	
	
	

</script>
<body>
<form:form id="inputForm" modelAttribute="bizOrderScrap" action="${ctx}/orderScrap/orderScrap/scrapUpdate"
           method="post" class="form-horizontal" enctype="multipart/form-data">
           <form:hidden path="orderId"  />
		<header><h2 class="title">订单作废</h2></header>
		
		<section class="con">
			<div>
				<div class="item pl152">订单信息</div>
				<div class="pl152 tab pt32 pb32 info">
					<p class="col3 f14 mb20">
						<span>订单编号：${bizOrderScrap.orderNumber}</span>
						<span style="width:60%;">顾客信息：${bizOrderScrap.communityName}-${bizOrderScrap.buildNumber}-${bizOrderScrap.buildUnit}-${bizOrderScrap.buildRoom}-${bizOrderScrap.customerName}</span>
					</p>
					<p class="col3 f14 mb20">
						<span>签约日期：<fmt:formatDate value="${bizOrderScrap.signContractDate}" pattern="yyyy-MM-dd "/></span>
						<span >接单日期：<fmt:formatDate value="${bizOrderScrap.getOrderDatetime}" pattern="yyyy-MM-dd "/></span>
					</p>
					<p class="col3 f14 mb20">
						<span>合同开工日期：<fmt:formatDate value="${bizOrderScrap.contractStartDate}" pattern="yyyy-MM-dd "/></span>
						<span>合同竣工日期：<fmt:formatDate value="${bizOrderScrap.contractEndDate}" pattern="yyyy-MM-dd "/></span>
					</p>
					<p class="col3 f14 mb20">
						<span>项目经理：${bizOrderScrap.itemManager}</span>
						<span>设计师：${bizOrderScrap.designerName}</span>
					</p>
					<p class="col3 f14 mb20">
						<span>订单状态：${bizOrderScrap.orderStatusDescription}</span>
						<span></span>
					</p>
				</div>
			</div>
				<div>
					<div class="item pl152">作废原因</div>
					<div class="pl152 tab pt32 pb32 info">
					 <p class="col3 f14 mb20 indent2">作废原因：
					<form:select  id="scrapReasonTypeid" path="scrapReasonType"  cssStyle="width:150px ;height:20px"  class="input-medium required" >
						<form:options items="${fns:getDictList('scrap_reason_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>	<span class="help-inline"><font color="red">*</font> </span>
					</p>
						<p class="col3 f14 mb20 indent2">作废说明：<form:textarea path="scrapDescribe" htmlEscape="false" maxlength="200"  /></p>
					</div>
				</div>
				<div>
					<div class="item pl152">图片凭证</div>
					<div class="pl152 tab pt32 pb32 info">
						
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
				</div>
		</section>
		

		</div>
		 <div class="btnWrapper" style="top:800px;left30px; padding-top: 800px;">
                        <a class="btn subBtn" href="#" onclick="submitForm();">提交</a>
                        <a class="btn subBtn" href="${ctx}/orderScrap/orderScrap/list">返回</a>
     	</div>


	<script type="text/javascript" src="${ctxStatic}/modules/orderComplaint/jquery-2.1.1.min.js"></script>
	</form:form>
</body>
</html>