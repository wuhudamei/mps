<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
<title>工人组管理</title>
<meta name="decorator" content="default" />
<script src="${ctxStatic}/echarts/echarts.min.js"></script>
</head>
<body>
<form:form id="searchForm" modelAttribute="bizEmployeegroupVO"
		action="${ctx}/empgroup/bizEmployeegroup/detail" method="post"
		class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}" />
		<input type="hidden" id="employeeId" name="employeeId" value="${employeeId}">
		<input type="hidden" id="empId" name="empId" value="${empId}">
		<ul class="ul-form">
			<li><label>更新星级时间 ：</label> <input name="startChangeDatetime" value="<fmt:formatDate value="${start}" pattern="yyyy-MM-dd"/>"
			id="startChangeDatetime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
			maxlength="20" class="input-medium Wdate"
			onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});" />&nbsp;至&nbsp;
			<input name="endChangeDatetime"
			id="endChangeDatetime" type="text"  value="<fmt:formatDate value="${end}" pattern="yyyy-MM-dd"/>"
			maxlength="20" class="input-medium Wdate" readonly="readonly" maxlength="20" class="input-medium Wdate"
			onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});" />
			</li>
		<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" /></li>
		<li class="clearfix"></li> 
	</ul>
</form:form>
	<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
	<div id="main" style="width: 600px;height:400px;"></div>
	<script>
	function returnHistory(){
		var url = "${ctx}/empgroup/bizEmployeegroup/list";
		 window.location.href= url;
	}
	
// 对Date的扩展，将 Date 转化为指定格式的String 
// 月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符， 
// 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字) 
// 例子： 
// (new Date()).Format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423 
// (new Date()).Format("yyyy-M-d h:m:s.S")      ==> 2006-7-2 8:9:4.18 
 Date.prototype.Format = function(fmt) 
{ //author: meizz 
  var o = { 
    "M+" : this.getMonth()+1,                 //月份 
    "d+" : this.getDate(),                    //日 
    "h+" : this.getHours(),                   //小时 
    "m+" : this.getMinutes(),                 //分 
    "s+" : this.getSeconds(),                 //秒 
    "q+" : Math.floor((this.getMonth()+3)/3), //季度 
    "S"  : this.getMilliseconds()             //毫秒 
  }; 
  if(/(y+)/.test(fmt)) 
    fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length)); 
  for(var k in o) 
    if(new RegExp("("+ k +")").test(fmt)) 
  fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length))); 
  return fmt; 
}
	if(document.getElementById("startChangeDatetime").value==null ||document.getElementById("startChangeDatetime").value==""){
				var now =new Date();
				var start=now.Format("yyyy-MM-01");
				document.getElementById("startChangeDatetime").value=start;
		}
	if(document.getElementById("endChangeDatetime").value==null ||document.getElementById("endChangeDatetime").value==""){
		var now =new Date();
		var end=now.Format("yyyy-MM-dd");
		document.getElementById("endChangeDatetime").value=end;
		}
			
		
	</script>
	<script type="text/javascript">
		// 基于准备好的dom，初始化echarts实例
		var myChart = echarts.init(document.getElementById('main'));
		// 指定图表的配置项和数据
		option = {
			title : {
				text : '${realName}星级变化图',
				left : 'center'
			},
			tooltip : {
				trigger : 'item',
				formatter : '{a} <br/>{b} : {c}'
			},
			legend : {
				left : 'left',
				data : [ '星级变化指数' ]
			},
			xAxis : {
				type : 'category',
				name : '日期',
				splitLine : {
					show : false
				},
				data : [ ${xdata} ]
			},
			grid : {
				left : '3%',
				right : '15%',
				bottom : '3%',
				containLabel : true
			},
			yAxis : {
				type : 'category',
				name : '星级',
				data : [ '1', '2', '3', '4', '5' ]
			},
			series : [ {
				name : [ '星级变更日期', '星级' ],
				type : 'line',
				data : [ ${ydata} ]
			} ]
		};

		// 使用刚指定的配置项和数据显示图表。
		myChart.setOption(option);
	</script>
			<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>序号</th>
				<th>门店</th>
				<th>更新工人星级时间</th>
				<th>工程模式</th>
				<th>区域</th>
				<th>工种</th>				
				<th>工人组长</th>				
				<th>组长手机号</th>		
				<th>当前星级</th>
				<th>本次变更前星级</th>
				<th>本次变更后星级</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${listchangeStar}" var="bizEmployeegroup"  varStatus="status">
				<tr>
					<td>${status.index + 1}</td>
					<td>${bizEmployeegroup.storeName}</td>
					<td>
					<fmt:formatDate value="${bizEmployeegroup.starChangeDatetime}" pattern="yyyy-MM-dd HH:mm:ss"/>
					</td>
					<td>${fns:getDictLabel(bizEmployeegroup.projectMode,'employee_project_mode', '')}</td>
					<td>${bizEmployeegroup.elactricationName}</td>
					<td>${fns:getDictLabel(bizEmployeegroup.workType, 'emp_work_type', '')}</td>
					<td>${bizEmployeegroup.leaderRealName}</td>
					<td>${bizEmployeegroup.leaderPhone}</td>
					<td>${fns:getDictLabel(bizEmployeegroup.star, 'emp_star', '')}</td>
					<td>${fns:getDictLabel(bizEmployeegroup.starBefore, 'emp_star', '')}</td>
					<td>${fns:getDictLabel(bizEmployeegroup.starAfter, 'emp_star', '')}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div style="margin-top:15px;text-align: center;">
	<input id="btnCancel" align="center" class="btn" type="button" value="返 回"	onclick="returnHistory()" />
	</div>
</body>

</html>