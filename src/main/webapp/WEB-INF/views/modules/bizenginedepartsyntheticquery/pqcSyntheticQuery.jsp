<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>质检节点报表</title>
	<meta name="decorator" content="default"/>
	<%--<link rel="stylesheet" type="text/css" href="/static/modules/css/xcConfirm.css"/>
	<script src="/static/modules/js/jquery-1.9.1.js" type="text/javascript" charset="utf-8"></script>
	<script src="/static/modules/js/xcConfirm.js" type="text/javascript" charset="utf-8"></script>--%>
	<script type="text/javascript">


		
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

		
		<%--function exportExcel(){--%>
			<%--var storeId = $("#storeId").val();--%>
			<%--if(null == storeId || storeId == ""){--%>
				<%--alertx("请先选择门店后，再导出");--%>
				<%--return;--%>
			<%--}--%>
			<%--// 判断门店--%>
			<%--if($("#storeId").val() != null && '${entity.storeId}' != $("#storeId").val()){--%>
				<%--alertx("请先查询后，再导出");--%>
				<%--return;--%>
			<%--}--%>
			<%--//日期  开始--%>
			<%--var queryDate1 = $("#beginActualStartDate1").val();--%>
			<%--var queryDateId = $("#queryDateId").val();--%>

			<%--if(queryDateId != queryDate1){--%>
				<%--alertx("请先查询后，再导出");--%>
				<%--return;--%>
			<%--}--%>
              <%--loading("正在为您导出所有数据...请稍等....")--%>
            <%--setTimeout("closeTip()",5000);--%>

			<%--$("#searchForm").attr("action", "${ctx}/engineDepartSyntheticQuery/exportInspectorFineMoneyDetailToExcel");--%>
			<%--$("#searchForm").submit();--%>
            <%--$("#searchForm").attr("action", "${ctx}/engineDepartSyntheticQuery/list");--%>
		<%--}--%>

		function searchList(){
			var storeId = $("#storeId").val();
			if(null == storeId || storeId <1){
                alertx("请先选择门店后，再查询");
                return;
            }
			var queryDateId = $("#queryDateId").val();
			if(null == queryDateId || queryDateId <1){
                alertx("请先选择验收日期后，再查询");
                return;
            }var projectMode = $("#projectMode").val();
			if(null == projectMode || projectMode <1){
                alertx("请先选择产业模式后，再查询");
                return;
            }

      	 	 loading("正在为您查询所有数据...请稍等....")


			$("#searchForm").attr("action", "${ctx}/engineDepartSyntheticQuery/pqcSyntheticQuery");
			$("#searchForm").submit();
		}
	
	</script>
	<style>

		.tooltips{ border-width: 1px; border-style: solid; position: absolute; display: none; border-radius: 3px; opacity: 0; filter:alpha( opacity = 0) ; z-index: 999;}
		.tooltips p.content{ padding: 5px; }
		.tooltips .triangle-front,.tooltips .triangle-back{ width: 0; height: 0; overflow: hidden; border-width: 8px; border-style: solid; position: absolute; border-color: transparent ; top: 100%; left: 50%; margin-left: -8px;}
		.tooltips .triangle-back{ margin-top: -1px;}

		.yellow{ border-color: #c7c08d; background-color: #fffac3;}
		.yellow .triangle-front{ border-top-color: #c7c08d;}
		.yellow .triangle-back{ border-top-color: #fffac3;}



	</style>
</head>
<c:set var="user" value="${fns:getUser()}"></c:set>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/engineDepartSyntheticQuery/pqcSyntheticQuery">质检节点报表查询</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="engineDepartSyntheticQueryEntity"  method="post" class="breadcrumb form-search">

		

		<ul class="ul-form">
			<li><label>门店：</label>

					<form:select path="storeId" class="input-medium needClear">
						<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>

			</li>
			<li><label class="control-label">工程模式：</label>
			<c:if test="${user.projectMode ==3}">

				<form:select path="projectMode" class="input-medium needClear" >
					<form:option value="" label="" />
					<form:options items="${fns:getDictList('project_mode')}"
								  itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
			</c:if>
			<c:if test="${user.projectMode !=3}">

				<form:select path="projectMode" class="input-medium needClear" >

					<form:option label="${fns:getDictLabel(user.projectMode, 'project_mode', '')}"
								 value="${user.projectMode}"/>
				</form:select>
			</c:if>
			</li>
			<li><label>日期：</label>
				<input name="pqcQueryDate" id="queryDateId"  type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${entity.pqcQueryDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"/>
				
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="button" value="查询" onclick="searchList()"/></li>
			<li class="btns"><input class="btn btn-primary clearBtn" type="button" value="清空" onclick="clearCondition()"/></li>
			<%--<li class="btns"><input class="btn btn-primary" type="button" value="导出Excel" onclick="exportExcel()"/></li>--%>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>日期</th>
				<th>节点名称</th>
				<th title="约检节点计划验收日期为检索日期当天的订单总和"><i class="icon-question-sign"></i>按开工日期今日应验收节点数</th>
				<th title="项目经理申请验收日期为检索日期当天的订单总和"><i class="icon-question-sign"></i>项目经理申请今日验收数</th>
				<th title="质检员验收通过日期为检索日期当天的订单总和"><i class="icon-question-sign"></i>质检员今日验收通过数</th>
				<th title="质检员在检索日期当天应验收通过但是未验收通过的订单总和"><i class="icon-question-sign"></i>截至今日未验收通过累计数</th>





			</tr>
		</thead>
		<tbody>
		<c:forEach items="${list}" var="enginedepart">
			<tr>
				<td><fmt:formatDate value="${enginedepart.pqcQueryDate}" pattern="yyyy-MM-dd"></fmt:formatDate> </td>




				<td>${enginedepart.qcCheckNodeName}</td>
				<td>${enginedepart.shouldBeDoneCount}</td>





				<td>${enginedepart.managerApplyDoneCount}</td>
				<td>${enginedepart.alreadyDoneCount}</td>




				<td>${enginedepart.unFinishDoneCount}</td>

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

					<b>按开工日期今日应验收节点数：</b>
					约检节点计划验收日期为检索日期当天的订单总和。<br/>
					<b>项目经理审核今日验收数：</b>
					项目经理申请验收日期为检索日期当天的订单总和。<br/>
						<b>	质检员今日验收通过数：</b>
					质检员验收通过日期为检索日期当天的订单总和。<br/>
							<b>	截止今日未验收通过累计数：</b>
					质检员在检索日期当天应验收通过但是未验收通过的订单总和<br/>

				</td>






			</tr>


		</tbody>


	</table>


</body>
</html>