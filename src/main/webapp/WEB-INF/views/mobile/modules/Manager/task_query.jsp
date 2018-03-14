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
	<title>任务包查询</title>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/reset.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/globalUtil.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/header.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/task_query.css"/>
</head>


<script type="text/javascript">
var name1;
var value1;
var name2;
var value2;
var flag=1;
 function subForm(value,id,name){
	
		 
		 $.ajax({
				url : "${ctx}/app/manager/packCondition?"+name+"="+value+"&"+name1+"="+value1 ,
				type : "get",
				dataType : "json" ,
				success:function (data){
				
					$("#section").remove();
					$("#all").append("<section class='task_list' id='section'>");
					
					jQuery.each(data, function(i,packageList){ 
					
						var startTime =format(packageList.startTime,"yyyy-MM-dd");
						
						var customerInfo = packageList.customerMessage+"-"+packageList.customerName;
						var endTime = format(packageList.endTime,"yyyy-MM-dd");
						
						var statusName = packageList.stateName;
						
						$("#section").append("<a href= 	'${ctx}/app/manager/packDetail?packageId="+packageList.packageId+"' class= 	'task_query_section clearfix'><ul><li class='clearfix'><span>顾客信息：</span><p>"+customerInfo+"</p></li><li class= 	'clearfix'><span>开工日期：</span><p>"+startTime+"</p></li><li class='clearfix'><span>完成日期：</span><p>"+endTime+"</p></li><li class='clearfix'><span>当前状态：</span><p>"+statusName+"</p></li></ul><span class='task_work'>"+packageList.packageName+"</span></a>");
		            
					});
					
				}
				 
				 
			 });
		
			 
	
	 
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
	
	 
	 
	 
	//$("#form1").submit();
	
 }

</script>
<body>

	<div class="g_task_query" id="all">
		<header>
			<a class="back_btn" href="${ctx}/app/manager/taskPackageManager"></a>
			<h2 class="title">任务包查询</h2>
		</header><!-- /header -->
		<!--title怎么写？？？-->
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
			<a href="javascript:void(0)" onclick="subForm('','allPack','packageName')">全部任务包</a>
			<input type="text" hidden="hidden"  id="allPack" />
			<c:forEach items="${nameList }" var="pack">
			
				<a href="javascript:void(0)" onclick="subForm('${pack}','packageName','packageName')">
				<input type="text" hidden="hidden"  id="packageName" />
				${pack}</a>
			</c:forEach>
			
			</div>
			
			<div class="options undis">
				<a href="javascript:void(0)" onclick="subForm('','allState','stateName')">全部状态</a>
				<input type="text" hidden="hidden"  id="allState" />
				<c:forEach items="${stateName }" var="state">
				
				<a href="javascript:void(0)" onclick="subForm('${state}','stateName','stateName')">
				<input type="text" hidden="hidden"  id="stateName" />
				${state}</a>
		</c:forEach>
			</div>
			<div class="options undis">
				<a href="javascript:void(0)" onclick="subForm('a.customer_name','name','orderBy')">
				<input type="text" hidden="hidden"  id="name"/>
				客户姓名</a>
				<a href="javascript:void(0)" onclick="subForm('a.plan_startdate','start','orderBy')">
				<input type="text" hidden="hidden"  id="start"/>
				计划开工日期</a>
				
				<a href="javascript:void(0)" onclick="subForm('a.plan_enddate','end','orderBy')">
				<input type="text" hidden="hidden"  id="end"/>
				计划完成日期</a>
			</div>
		</nav>
		<section class="task_list" id="section">
		
					<c:forEach items="${packList }" var="pack">
					
					
			<a href="${ctx}/app/manager/packDetail?packageId=${pack.packageId}" class="task_query_section mar clearfix" id="Atag">
				<ul>
					<li class="clearfix">
						<span>顾客信息：</span>
						<p>${pack.customerMessage}-${pack.customerName}</p>
					</li>
					<li class="clearfix">
						<span>开工日期：</span>
						<p><fmt:formatDate value="${pack.startTime }" pattern="yyyy-MM-dd"/></p>
					</li>
					<li class="clearfix">
						<span>完成日期：</span>
						<p><fmt:formatDate value="${pack.endTime }" pattern="yyyy-MM-dd"/></p>
					</li>
					<li class="clearfix">
						<span>当前状态：</span>
						<p>${pack.stateName }</p>
					</li>
				</ul>
				<span class="task_work">${pack.packageName}</span>
			</a>
			
			</c:forEach>
	
	</div>
	</form>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/calcRootFontSize.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/history.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/global.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/home/task_query.js"></script>
	<script>
		// globalUtil.fn.showMask();
	</script>
</body>
</html>