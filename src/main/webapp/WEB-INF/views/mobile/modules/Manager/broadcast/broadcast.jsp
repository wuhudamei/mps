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
	<title>播报图片</title>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/reset.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/header.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/intent.css"/>
</head>
<body>
	<div class="g-sign_list">
		<header class="header">
			<a class="back_btn" href="${ctx }/app/manager/taskPackageManager" ></a>
			<h2 class="title">播报图片</h2>
		</header><!-- /header -->
		<section class="sign_list">
			<p class="sch_div clearfix">
				<input class="sch_text" type="text" placeholder="播报类型 、小区、客户名称">
				<a class="sch_btn" href="javascript:;" onclick="ajaxSearch()">搜索</a>
			</p>
			<c:forEach items="${list }" var="broadcast">
			<div class="clearfix">
				<ul>
					<li class="clearfix">
						<span>播 报 类 型  ：</span>
						<p class="">${broadcast.broadcastName }</p>
					</li>
					<li class="clearfix">
						<span>小 区 名 称 ：</span>
						<p class="">${broadcast.communityName }-${broadcast.buildNumber }-${broadcast.buildUnit }-${broadcast.buildName }-${broadcast.customerName }</p>
					</li>
					<li class="clearfix">
						<span>申请播报日期：</span>
						<p> <fmt:formatDate value="${broadcast.applyBroadCastDate }" pattern="yyyy-MM-dd"/>  </p>
					</li>
					<li class="clearfix">
						<span>播报人姓名 ：</span>
						<p class="">${broadcast.applyBroadCastName }</p>
					</li>
				</ul>
				<a class="sign_btn" href="${ctx }/app/manager/broadcast/broadcast_check?id=${broadcast.broadcastId}">播报验收</a>
			</div>
			</c:forEach>
		</section>
	</div>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/calcRootFontSize.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/history.js"></script>
</body>
<script type="text/javascript">
function ajaxSearch(){
	var text = $(".sch_text").val();
	$(".sign_list").html("");
	$(".sign_list").append('<p class="sch_div clearfix">	<input class="sch_text" type="text" placeholder="播报类型 、小区、客户名称">'
+'	<a class="sch_btn" href="javascript:;" onclick="ajaxSearch()">搜索</a></p>'	)
	$.ajax({
		url: "${ctx}/app/manager/broadcast/query_ajax_list?text="+text,
		type : "get",
		success: function (data){
			if(null!=data){
				for(var v = 0;v<data.length;v++){
				$(".sign_list").append('<div class="clearfix"><ul><li class="clearfix"><span>播报类型:</span>'
+'<p>'+data[v].broadcastName+'</p></li><li class="clearfix"><span>客户信息:</span>'						
+'<p class="">'+data[v].communityName+"-"+data[v].buildNumber+"-"+data[v].buildUnit+"-"+data[v].buildName+"-"+data[v].customerName+'</p></li><li class="clearfix"><span>申请播报日期：</span>'				
+'<p class="">'+format(data[v].applyBroadCastDate,"yyyy-MM-dd")+'</p></li><li class="clearfix"><span>播报人姓名:</span>'				
+'<p class="">'+data[v].applyBroadCastName+'</p></li></ul>'				
+'<a class="sign_btn"  href="${ctx }/app/manager/broadcast/broadcast_check?id='+data[v].broadcastId+'">播报验收</a></div>'
				);	
		
		}
				$(".sch_text").val(text);   		
			}
		
		}
	})
	
	
	
	
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
</html>