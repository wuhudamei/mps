<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1, user-scalable=no">
	<meta content="yes" name="apple-mobile-web-app-capable">
	<meta content="black" name="apple-mobile-web-app-status-bar-style">
	<meta content="telephone=no" name="format-detection">
	<meta content="email=no" name="format-detection">
	<title>施工变更</title>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/reset.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/header.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/common.css"/>
</head>
<body>
	<div class="g-review">
		<header>
			<a class="back_btn" href="${ctx }/app/manager/changeManagement/projectChangeList"></a>
			<h2 class="title">施工变更</h2>
		</header>
		<section class="pad_top10 mar_btm50">
		
		<c:forEach items="${list }" var="bill"> 
		
			<ul class="clearfix shadow bg_f radius1 mar_left2 mar_rgt2 mar_btm2 pad_left2 pad_rgt2 font0 has_one">
				<li class="line_hgt clearfix">
					<span class="font28 col_6 flo_left">提报日期：</span>
					<p class="font28 col_3 flow">  <fmt:formatDate value="${bill.applyDate }"  pattern="yyyy-MM-dd" />  </p>
				</li>
				<li class="line_hgt clearfix">
					<span class="font28 col_6 flo_left">增项总价：</span>
					<p class="font28 col_3 flow">${bill.addItemTotalPrice }元</p>
				</li>
				<li class="line_hgt clearfix">
					<span class="font28 col_6 flo_left">减项总价：</span>
					<p class="font28 col_3 flow">-${bill.minusItemTotalPrice }元</p>
				</li>
				<li class="line_hgt clearfix">
					<span class="font28 col_6 flo_left">当前状态：</span>
					<p class="font28 col_3 flow">${bill.status }-${bill.statusName }</p>
				</li>
				
				
				<div class="btns clearfix">
				
				<c:if test="${bill.statusShiro == '0' }">
				<a href="javascript:void(0)" class="one_btn"  onclick="look('${bill.projectChangeId}','this')">查看</a>
				</c:if>
				<c:if test="${bill.statusShiro == '1' }">
				<a href="javascript:void(0)" class="one_btn"  onclick="modify('${bill.projectChangeId}','this','${storeId}')">修改</a>
				</c:if>
				<c:if test="${bill.statusShiro == '2' }">
				<a href="javascript:void(0)" class="one_btn"  onclick="reSubmit('${bill.projectChangeId}','this','${storeId}')">重新提报</a>
				</c:if>
				
				</div>
			</ul>
				</c:forEach>
			
			
		
		</section>
	</div>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/calcRootFontSize.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/history.js"></script>
</body>

<script type="text/javascript">


//查看
function look(projectChangeId,obj){
	if(projectChangeId!=null||projectChangeId==undefined){
		$(obj).removeAttr("onclick");
		$(obj).css("color","#CCC")
		
		
		window.location.href="${ctx}/app/manager/changeManagement/look?projectChangeId="+projectChangeId;
		
		
	}else{
		
		alert("订单数据异常, 请联系管理员 :projectChangeId ="+projectChangeId)
		
	}
	
	
}
//修改
function  modify(projectChangeId,obj,storeId){
	if(projectChangeId!=null||projectChangeId==undefined){
	$(obj).removeAttr("onclick");
	$(obj).css("color","#CCC")
	
	window.location.href="${ctx}/app/manager/changeManagement/modify?projectChangeId="+projectChangeId+"&storeId="+storeId;
	}else{
		alert("订单数据异常, 请联系管理员 :projectChangeId ="+projectChangeId)
	}
	
	
	}
//重新提报
function reSubmit(projectChangeId,obj,storeId){
	if(projectChangeId!=null||projectChangeId!=undefined){
	$(obj).removeAttr("onclick");
	$(obj).css("color","#CCC")
	
	window.location.href="${ctx}/app/manager/changeManagement/reSubmit?projectChangeId="+projectChangeId+"&storeId="+storeId;
	}else{
		alert("订单数据异常, 请联系管理员 :projectChangeId ="+projectChangeId)
	}
	
	
	
}



</script>

</html>