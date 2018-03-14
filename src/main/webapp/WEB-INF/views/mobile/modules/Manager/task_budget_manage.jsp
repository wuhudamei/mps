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
	<title>结算单管理</title>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/reset.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/header.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/globalUtil.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/sign.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/task_query.css"/>
</head>
<body>
	<div class="g_task_query">
		<header>
			<a class="back_btn" href="${ctx}/app/manager/taskPackageManager"></a>
			<h2 class="title">结算单管理</h2>
		</header><!-- /header -->
		<nav class="task_nav clearfix">
			<a class="nav_a" href="javascript:void(0);">
				<span>任务包名称</span>
				<img src="${ctxStatic}/mobile/modules/Manager/images/down_btn.png" height="8" width="12" alt="btn">
			</a>
			<a class="nav_a" href="javascript:void(0);">
				<span>全部状态</span>				
				<img src="${ctxStatic}/mobile/modules/Manager/images/down_btn.png" height="8" width="12" alt="btn">
			</a>
			<a class="nav_a" href="javascript:void(0);">
				<span>综合排序</span>				
				<img src="${ctxStatic}/mobile/modules/Manager/images/down_btn.png" height="8" width="12" alt="btn">
			</a>
			<div class="options undis">
				<a onclick="queryList(1,'')">全部任务包</a>
				<c:forEach items="${templatList}" var="templat">
					<a onclick="queryList(1,${templat.id})">${templat.templatName}</a>
				</c:forEach>
			</div>
			<div class="options undis">
				<a href="javascript:void(0);" onclick="queryList(2,'')">全部状态</a>
				<c:forEach items="${taskList}" var="task">
					<a onclick="queryList(2,${task.packageStateId})">${fn:substringAfter(task.packageStatename, '-')}</a>
				</c:forEach>
			</div>
			<div class="options undis">
				<a onclick="queryList(3,1)">任务包名</a>
				<a onclick="queryList(3,2)">验收日期</a>
			</div>
		</nav>
		<section class="task_list g-sign" id="dataList">
			<!-- <ul class="mar_top2 pad_top2 pad_btm2 clearfix shadow">
				<li class="clearfix">
					<span>顾客信息：</span>
					<p>鹿港嘉苑鹿港嘉苑鹿港嘉苑鹿港嘉苑鹿港嘉苑-10-3-2001-张三</p>
				</li>
				<li class="clearfix">
					<span>任务包名称：</span>
					<p>铲墙皮</p>
				</li>
				<li class="clearfix">
					<span>结算金额：</span>
					<p>6000元</p>
				</li>
				<li class="clearfix">
					<span>任务包状态：</span>
					<p>项目经理已验收</p>
				</li>
				<div class="btns clearfix">
					<a class="btn" href="javascript:void(0)">查看结算单</a>
					<a class="btn" href="javascript:void(0)">修改结算单</a>
				</div>
			</ul>-->
		</section>
	</div>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/calcRootFontSize.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/history.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/global.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/home/task_query.js"></script>
	<script>
	var taskPackageTemplatId = "";
	var stateId = "";
	var orderType = "";
	
		$(function(){
			getData();
		});
		
		function queryList(type,value){
			if(type == 1){
				taskPackageTemplatId = value;
			}else if(type == 2){
				stateId = value;
			}else if(type == 3){
				orderType = value;
			}
			getData();
		}
		
		function getData(){
			$.ajax({
				url : "${ctx}/app/manager/orderPackageList",
				type : "post",
				data:{taskPackageTemplatId:taskPackageTemplatId,stateId:stateId,orderType:orderType},
				dataType : "json" ,
				success:function (data){
					var content = '';
					 for(var i=0;i<data.length;i++){
						 var btn = '';
						 if(data[i].stateId == 110 || data[i].stateId == 130){
							 btn = '<a class="btn" onclick="updateSettlement('+data[i].settlementId+','+data[i].packageId+')">修改结算单</a>';
						 }
						content = content + '<ul class="mar_top2 pad_top2 pad_btm2 clearfix shadow">'+
						'<li class="clearfix"><span>任务编号：</span><p>'+data[i].orderTaskPackageCode+'</p></li>'+
						'<li class="clearfix"><span>任务包名称：</span><p>'+data[i].packageName+'</p></li>'+
						'<li class="clearfix"><span>顾客信息：</span><p>'+data[i].customerMessage+'-'+data[i].customerName+'</p></li>'+
						'<li class="clearfix"><span>结算金额：</span><p>'+data[i].settlementAmount+'</p></li>'+
						'<li class="clearfix"><span>任务包状态：</span><p>'+data[i].stateName+'</p></li>'+
						'<li class="clearfix"><span>验收日期：</span><p>'+format(data[i].checkDate,"yyyy-MM-dd")+'</p></li>'+
						'<div class="btns clearfix"><a class="btn" onclick="detailsSettlement('+data[i].packageId+','+data[i].settleStyle+')">查看结算单</a>'+btn+'</div></ul>';
					}
					$("#dataList").html(content);
				}
			 });
		}
		
		function updateSettlement(settlementId,orderTaskPackageId){
			window.location.href="${ctx}/app/manager/taskpackageConfirmAgain?orderTaskpackageId="+orderTaskPackageId+"&id="+settlementId;
		}
		function detailsSettlement(orderTaskPackageId, settleStyle){
			window.location.href="${ctx }/app/manager/account?id="+orderTaskPackageId + "&settleStyle=" + settleStyle; 
		}
		
		var format = function(time, format){
		    var t = new Date(time);
		    var tf = function(i){return (i < 10 ? '0' : '') + i};
		    return format.replace(/yyyy|MM|dd|HH|mm|ss/g, function(a){
		        switch(a){
		            case 'yyyy':
		                return tf(t.getFullYear());
		                break;
		            case 'MM':
		                return tf(t.getMonth() + 1);
		                break;
		            case 'mm':
		                return tf(t.getMinutes());
		                break;
		            case 'dd':
		                return tf(t.getDate());
		                break;
		            case 'HH':
		                return tf(t.getHours());
		                break;
		            case 'ss':
		                return tf(t.getSeconds());
		                break;
		        }
		    })
		}
	</script>
</body>
</html>