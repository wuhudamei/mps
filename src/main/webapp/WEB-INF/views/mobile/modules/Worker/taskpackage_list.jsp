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
	<title>接单列表</title>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Worker/css/reset.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Worker/css/header.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Worker/css/task_list.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Worker/css/done_demand.css"/>
		<link rel="stylesheet" type="text/css"
	href="${ctxStatic}/mobile/modules/pqc/css/globalUtil.css" />
</head>
<body>
	<div class="g-task_list">
		<header>
			<a class="back_btn" href="${ctx }/app/worker/toindex"></a>
			<h2 class="title">接单列表</h2>
		</header>
		<section class="task_list">
			<c:forEach items="${list }" var="taskPackage">
			
		
					<a href="${ctx}/app/worker/packDetail?packageId=${taskPackage.id}&settleStyle=${taskPackage.settleStyle}" class="task_query_section clearfix" id="Atag">			
			
			
				<div class="">
					<ul>
						<li class="clearfix">
							<span>任务包名称：</span>
							<p>${taskPackage.packageName }</p>
						</li>
						<li class="clearfix">
							<span>项目经理：</span>
							<p>${taskPackage.itemCustomer }</p>
						</li>
						<li class="clearfix">
							<span>手机号：</span>
							<p>${taskPackage.managerPhone }</p>
						</li>
						<li class="clearfix">
							<span>施工地点：</span>
							<p>${taskPackage.customerMessage }-${taskPackage.customerName }</p>
						</li>
						<li class="clearfix">
							<span>施工时间：</span>
							<p>
								<fmt:formatDate value="${taskPackage.planStartdate }" pattern="yyyy-MM-dd" /> 至
								<fmt:formatDate value="${taskPackage.planEnddate }" pattern="yyyy-MM-dd" />
							</p>
						</li>
						<li class="clearfix">
							<span>订单状态：</span>
							<p class="col_blue">${taskPackage.packageStatename }</p>
						</li>
					</ul>
					<div class="btns clearfix">
						<a class="btn" href="#"  onclick="accept('${taskPackage.id}',this)">接受</a>
						<a class="btn" href="#" onclick="refuse('${taskPackage.id}',this)">拒绝</a>
					</div>
				</div>
				
				</a>
				
		
		
			</c:forEach>
			
		</section>
	
		
	</div>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Worker/js/lib/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Worker/js/utils/calcRootFontSize.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Worker/js/utils/history.js"></script>
	<script type="text/javascript"
		src="${ctxStatic}/mobile/modules/Worker/js/lib/jquery.form.js"></script>
			<script type="text/javascript"
		src="${ctxStatic}/mobile/modules/pqc/js/utils/global.js"></script>
	
</body>
<script type="text/javascript">

function accept(packId,obj){
	$(obj).css("color","#CCC");
	$(obj).removeAttr("onclick");
	$.ajax({
		url:"${ctx }/app/worker/acceptTaskPackage?id="+packId,
		success:function(data){
			globalUtil.fn.alert("操作成功",2.0);	
			window.location.href="${ctx}/app/worker/taskPackageList";
		}
		
	})
	
	

	
	
}

function refuse(packId,obj){
	
	$(obj).css("color","#CCC");
	$(obj).removeAttr("onclick");
	
	$.ajax({
		url:"${ctx }/app/worker/refuseTaskPackage?id="+packId,
		success:function(data){
			globalUtil.fn.alert('操作成功',2.0);	
			window.location.href="${ctx}/app/worker/taskPackageList";
		}
		
	})
	
}





</script>




</html>