<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>约检节点验收统计管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
		
		
        function clearCondition(){
            document.getElementById("searchForm").reset();

            var inputObjs=jQuery("#searchForm input[type='text']");
            for(var i=0;i<inputObjs.length;i++){
                var inputObj = inputObjs[i];
                inputObj.value="";
            }

            var selectObjs = jQuery("#searchForm input[class='input-medium']");
            for(var i=0;i<selectObjs.length;i++){
                var selectObj = selectObjs[i];
                selectObj.value="";
            }
        }
        
		function searchList(){
			var qcBillType = $("#qcBillType").val();
			if(null == qcBillType || qcBillType <1){
                alertx("请先选择门店后，再查询");
                return false;
            }
	
			/* var queryDateId = $("#acceptCheckDatetimeStart").val();
			if(null == queryDateId || queryDateId <1){
                alertx("请先选择验收日期后，再查询");
                return false;
            } */
			if(null==$("#acceptCheckDatetimeStart").val()||$("#acceptCheckDatetimeStart").val()==''||null==$("#acceptCheckDatetimeEnd").val()||$("#acceptCheckDatetimeEnd").val()==''){
				alertx("请先选择验收日期后，再查询");
                return false;
			}
      	 	 loading("正在为您查询所有数据...请稍等....")


			$("#searchForm").attr("action", "${ctx}/arrangecheckstatisticsq/arrangeCheckStatisticsQ/listget");
			$("#searchForm").submit();
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/arrangecheckstatisticsq/arrangeCheckStatisticsQ/listget">约检节点验收统计列表</a></li>
		<shiro:hasPermission name="arrangecheckstatisticsq:arrangeCheckStatisticsQ:edit"><li><a href="${ctx}/arrangecheckstatisticsq/arrangeCheckStatisticsQ/form">约检节点验收统计添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="arrangeCheckStatisticsQ"  method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>门店：</label>
				<form:select path="qcBillType"  id="qcBillType" class="input-medium needClear">
						<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>验收日期时间 ：</label>
				<input id="acceptCheckDatetimeStart" name="acceptCheckDatetimeStart" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${arrangeCheckStatisticsQ.acceptCheckDatetimeStart}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>至
				<input id="acceptCheckDatetimeEnd" name="acceptCheckDatetimeEnd" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
				value="<fmt:formatDate value="${arrangeCheckStatisticsQ.acceptCheckDatetimeEnd}" pattern="yyyy-MM-dd HH:mm:ss"/>"
				onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'acceptCheckDatetimeStart\')}',isShowClear:true});"/>
			</li>
			<li class="btns"><input  class="btn btn-primary" type="button" value="查询"  onclick="searchList()"/></li>
			<li class="btns"><input class="btn btn-primary clearBtn" type="button" value="清空" onclick="clearCondition()"/></li>
			
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>序号</th>
				<th>日期</th>
				<th>门店</th>
				<th>约检节点名称</th>
				<th>产业</th>
				<th>传统</th>
				<th>合计</th>
				<shiro:hasPermission name="arrangecheckstatisticsq:arrangeCheckStatisticsQ:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="arrangeCheckStatisticsQ">
			<tr>
				<shiro:hasPermission name="arrangecheckstatisticsq:arrangeCheckStatisticsQ:edit"><td>
    				<a href="${ctx}/arrangecheckstatisticsq/arrangeCheckStatisticsQ/form?id=${arrangeCheckStatisticsQ.id}">修改</a>
					<a href="${ctx}/arrangecheckstatisticsq/arrangeCheckStatisticsQ/delete?id=${arrangeCheckStatisticsQ.id}" onclick="return confirmx('确认要删除该约检节点验收统计吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
		
					<td>${arrangeCheckStatisticsQ.cotid}</td>
					<td>${arrangeCheckStatisticsQ.datetime}</td>
					<td>${fns:getStoreLabel(arrangeCheckStatisticsQ.storeId, '')}</td>
					<td>${arrangeCheckStatisticsQ.checknodename}</td>
					<td>${arrangeCheckStatisticsQ.industry}</td>
					<td>${arrangeCheckStatisticsQ.tradition}</td>
					<td>${arrangeCheckStatisticsQ.total}</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<table  class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>

				<th>说明</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>
					<b>产业：</b>
					 质检员点约检节点确认验收的时间为选择日期的产业订单总数。<br/>
					<b>传统：</b>
					 质检员点约检节点确认验收的时间为选择日期的传统订单总数。<br/>
						<b>	合计：</b>
					 等于产业 + 传统 订单数之和。<br/>

				</td>


			</tr>


		</tbody>

	</table>
</body>
</html>