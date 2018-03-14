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
	<title>任务包派工计划</title>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/reset.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/header.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/intent.css"/>
	<style>
		.sch_text{width: 80%;line-height: .8rem;border: 1px solid #0780ec;font-size: .28rem;float: left;box-sizing: border-box;border-radius: 2px;text-indent: 1em;}
		.sch_btn{float: right;width: 20%;font-size: .28rem;text-align: center;line-height: .8rem;background: #0780ec;margin-top: 1px;border-radius: 2px;color: #fff;}
		.sch_div{margin-bottom: .2rem;}
	</style>
</head>
<body>
	<div class="g-sign_list">
		<header class="header">
			<a class="back_btn" href="${ctx }/app/manager/taskPackageManager"></a>
			<h2 class="title">任务包派工计划</h2>
			<c:if test="${ not empty error }">${error }</c:if>
		</header><!-- /header -->
		<section class="sign_list">
			<p class="sch_div clearfix">
				<input class="sch_text" type="text" placeholder="小区名字、客户姓名">
				<a class="sch_btn" href="javascript:;" onclick="ajaxSearch()">搜索</a>
			</p>
		<c:if test="${ not empty list }">
		<c:forEach items="${list }" var="pack">
			<div class="clearfix">
				<ul>
					<li class="clearfix">
						<span>任务包编号 ：</span>
						<p>${pack.orderTaskPackageCode }</p>
					</li>
					<li class="clearfix">
						<span>任务包名称 ：</span>
						<p class="">${pack.packageName }</p>
					</li>
					<li class="clearfix">
						<span>顾 客 信 息：</span>
						<p>   ${pack.customerMessage }-${pack.customerName }</p>
					</li>
					<li class="clearfix">
						<span>计划开工日期：</span>
						<p class=""> <fmt:formatDate value="${pack.planStartDate }" pattern="yyyy-MM-dd"/> </p>
					</li>
					<li class="clearfix">
						<span>计划完工日期：</span>
						<p class=""><fmt:formatDate value="${pack.planEndDate }" pattern="yyyy-MM-dd"/> </p>
					</li>
				</ul>
				<a class="sign_btn"  href="${ctx }/app/manager/packTimeList/detail?packId=${pack.packageId}">调整计划</a>
			</div>
			
			</c:forEach>
			</c:if>
			
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
	$(".sign_list").append('<p class="sch_div clearfix">	<input class="sch_text" type="text" placeholder="小区名字、客户姓名">'
+'	<a class="sch_btn" href="javascript:;" onclick="ajaxSearch()">搜索</a></p>'	)
	$.ajax({
		url: "${ctx}/app/manager/packTimeList/query_ajax_list?text="+text,
		type : "get",
		success: function (data){
			if(null!=data){
				for(var v = 0;v<data.length;v++){
				$(".sign_list").append('<div class="clearfix"><ul><li class="clearfix"><span>任务包编号 ：</span>'
+'<p>'+data[v].orderTaskPackageCode+'</p></li><li class="clearfix"><span>任务包名称 ：</span>'						
+'<p class="">'+data[v].packageName+'</p></li><li class="clearfix"><span>顾 客 信 息：</span>'			
+'<p>'+data[v].customerMessage+'-'+data[v].customerName+'</p></li><li class="clearfix"><span>计划开工日期：</span>'				
+'<p class="">'+format(data[v].planStartDate,"yyyy-MM-dd")+'</p></li><li class="clearfix"><span>计划完工日期：</span>'				
+'<p class="">'+format(data[v].planEndDate,"yyyy-MM-dd")+'</p></li></ul>'				
+'<a class="sign_btn"  href="${ctx }/app/manager/packTimeList/detail?packId='+data[v].packageId+'">调整计划</a></div>'
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