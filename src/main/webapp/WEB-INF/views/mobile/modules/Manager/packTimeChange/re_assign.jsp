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
	<title>调整计划</title>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/reset.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/lib/lCalendar.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/header.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/start.css"/>
		<link rel="stylesheet" type="text/css"
	href="${ctxStatic}/mobile/modules/pqc/css/globalUtil.css" />
</head>

<script type="text/javascript">

</script>
<body>
	<div class="g-start">
		<header>
			<a class="back_btn"  onclick="history.go(-1)"></a>
			<h2 class="title">调整计划</h2>
		</header><!-- /header -->
		<form action="${ctx }/app/manager/packTimeList/changePlan" id="packForm">
		<section class="start_section">
			<h3>${taskpackage.customerMessage }-${taskpackage.customerName }</h3>
			<ul class="shadow">
				<li class="clearfix">
				<input type="text" hidden="hidden" name="packageId" value="${taskpackage.packageId }">
				<input type="text" hidden="hidden" name="customerInfo" value="${taskpackage.customerMessage}-${taskpackage.customerName}">
					<span>任务包编号：</span>
					<p>${taskpackage.orderTaskPackageCode }</p>
				</li>
				<li class="clearfix">
					<span>任务包名称：</span>
					<p>${taskpackage.packageName }</p>
					<input type="text" hidden="hidden" name="packName" value="${taskpackage.packageName  }" />
					
				</li>
				<li class="clearfix">
					<span>计划开工日期：</span>
					<p>
						<input id="txtBeginDate" class="date" type="text" readonly="" value="<fmt:formatDate value="${taskpackage.planStartDate}" pattern="yyyy-MM-dd"/>" name="planStartDate" placeholder="请输入日期" data-lcalendar="2000-01-01,2000-01-01" />
					</p>
					<!-- <div class="calMain"></div> -->
				</li>
				<li class="clearfix">
					<span>计划完工日期：</span>
					<p>
						<input id="txtEndDate" class="date" type="text" readonly=""  value="<fmt:formatDate value="${taskpackage.planEndDate}" pattern="yyyy-MM-dd"/>" name="planEndDate" placeholder="请输入日期" data-lcalendar="2000-01-01,2000-01-01" />
					</p>
					<!-- <div class="calMain"></div> -->
				</li>
				<li class="clearfix" style="margin-top:.2rem;">
					<span>备注说明：</span>
					<p><textarea name="remarks"></textarea></p>
				</li>
				<a id="start" >确认调整</a>
			</ul>
		</section>
		</form>
		
	</div>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/global.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/calcRootFontSize.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/lCalendar.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/history.js"></script>
	<script type="text/javascript"
		src="${ctxStatic}/mobile/modules/Worker/js/lib/jquery.form.js"></script>
			<script type="text/javascript"
		src="${ctxStatic}/mobile/modules/pqc/js/utils/global.js"></script>
	<script>
	    $(function () {
			  $('label').click(function(){
			    var radioId = $(this).attr('id');
			    $('label').removeAttr('class') && $(this).attr('class', 'checked');
			    $('input[type="radio"]').removeAttr('checked') && $('#' + radioId).attr('checked', 'checked');
			  });
	       		// 获取当前日期，结束日期
	    	var now = new Date(),
	    	start = new Date(now.setDate(now.getDate()+3)),
	    		start = globalUtil.fn.formatDate(start, 'yyyy-MM-dd'),
	    		end = new Date(now.setFullYear(now.getFullYear()+4)),
	    		end = globalUtil.fn.formatDate(end, 'yyyy-MM-dd');

	    	var lcalendar = start+','+end;
	    	// 将时间限制加到input标签上。
	    	$('#txtBeginDate').attr('data-lcalendar', lcalendar);
			var calendar = new lCalendar();
			calendar.init({
			    'trigger': '#txtBeginDate',//标签id
			    'type': 'date'//date 调出日期选择 datetime 调出日期时间选择 time 调出时间选择
			});
			$('#txtEndDate').attr('data-lcalendar', lcalendar);
			var calendar = new lCalendar();
			calendar.init({
			    'trigger': '#txtEndDate',//标签id
			    'type': 'date'//date 调出日期选择 datetime 调出日期时间选择 time 调出时间选择
			});
	    });
	    
	    
	    
	    
	    
	    
	    $(document).ready(function() {
	    	//绑定onclick事件
	    	$("#start").bind('click',submit);
	    });



	    var  startDate = "${taskpackage.planStartDate}";
	    var  endDate = "${taskpackage.planEndDate}";

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




	    function submit(){

	    	
	    	startDate =  format(startDate,"yyyy-MM-dd")
	    	endDate =  format(endDate,"yyyy-MM-dd")
	    	 var inputStart = 	$("#txtBeginDate").val();
	    	 var inputEnd = 	$("#txtEndDate").val();
	    	 
	    	 
	    	 if(startDate!=inputStart || endDate!=inputEnd){
	    		 
	    		 var options={
	    				 url: "${ctx }/app/manager/packTimeList/changePlan",
	    				 type: "post",
	    				 success : function(data){
	    						globalUtil.fn.alert('调整计划成功',2.0);			
	    					 window.location.href="${ctx}/app/manager/packTimeList/list";
	    				 }
	    				 
	    		 }
	    		 $("#start").css("color","#CCC");
	    			$("#start").unbind("click");
	    			$('#packForm').ajaxSubmit(options);
	    		
	    	 }else{
	    	 
	    		 window.location.href="${ctx}/app/manager/packTimeList/list";
	    		 
	    	 }
	    	 
	    	 	//是否修改 判断
	    }

	    
</script>
</body>
</html>