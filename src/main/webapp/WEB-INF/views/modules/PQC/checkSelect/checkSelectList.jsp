<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>约检明细查询</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
		    closeTip();
			
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
			 $("input[name='totalAndGotIsEqual']").removeAttr('checked');
		}
		
		function exportExcel(){
			//门店
			var storeId1 = '${checkSelect.storeId}';
			var storeId = $("#storeId").val();
			if(storeId1 != storeId){
				alertx("请先点击查询按钮后，再导出约检明细");
				return false;
			}
			//工程模式
			var projectMode1 = '${checkSelect.projectMode}';
			var projectMode = $("#projectMode").val();
			if(projectMode1 != projectMode){
				alertx("请先点击查询按钮后，再导出约检明细");
				return false;
			}
			//客户姓名
			var customerName1 = '${checkSelect.customerName}';
			var customerName = $("#customerName").val();
			if(customerName1 != customerName){
				alertx("请先点击查询按钮后，再导出约检明细");
				return false;
			}
			//项目经理
			var itemManager1 = '${checkSelect.itemManager}';
			var itemManager = $("#itemManager").val();
			if(itemManager1 != itemManager){
				alertx("请先点击查询按钮后，再导出约检明细");
				return false;
			}
			//项目经理申请约检日期  开始
			var beginCreateDate1 = $("#beginCreateDate1").val();
			var beginCreateDate = $("#beginCreateDate").val();
			if(beginCreateDate1 != beginCreateDate){
				alertx("请先点击查询按钮后，再导出约检明细");
				return false;
			}
			//项目经理申请约检日期  结束
			var endCreateDate1 = $("#endCreateDate1").val();
			var endCreateDate = $("#endCreateDate").val();
			if(endCreateDate1 != endCreateDate){
				alertx("请先点击查询按钮后，再导出约检明细");
				return false;
			}
			//约检检查日期  开始
			var beginCheckDatetime1 = $("#beginCheckDatetime1").val();
			var beginCheckDatetime = $("#beginCheckDatetime").val();
			if(beginCheckDatetime1 != beginCheckDatetime){
				alertx("请先点击查询按钮后，再导出约检明细");
				return false;
			}
			//约检检查日期  结束
			var endCheckDatetime1 = $("#endCheckDatetime1").val();
			var endCheckDatetime = $("#endCheckDatetime").val();
			if(endCheckDatetime1 != endCheckDatetime){
				alertx("请先点击查询按钮后，再导出约检明细");
				return false;
			}
			//约检验收日期  开始
			var beginAcceptCheckDatetime1 = $("#beginAcceptCheckDatetime1").val();
			var beginAcceptCheckDatetime = $("#beginAcceptCheckDatetime").val();
			if(beginAcceptCheckDatetime1 != beginAcceptCheckDatetime){
				alertx("请先点击查询按钮后，再导出约检明细");
				return false;
			}
			//约检验收日期  结束
			var endAcceptCheckDatetime1 = $("#endAcceptCheckDatetime1").val();
			var endAcceptCheckDatetime = $("#endAcceptCheckDatetime").val();
			if(endAcceptCheckDatetime1 != endAcceptCheckDatetime){
				alertx("请先点击查询按钮后，再导出约检明细");
				return false;
			}

            //约检验收日期  结束
            var assertNodeConfirmDoneStartDate = $("#assertNodeConfirmDoneStartDate").val();
            var assertNodeConfirmDoneStartDate1 = $("#assertNodeConfirmDoneStartDateId").val();
            if(assertNodeConfirmDoneStartDate != assertNodeConfirmDoneStartDate1){
                alertx("请先点击查询按钮后，再导出约检明细");
                return false;
            }


            //约检验收日期  结束
            var assertNodeConfirmDoneEndDate = $("#assertNodeConfirmDoneEndDateId").val();
            var assertNodeConfirmDoneEndDate1 = $("#assertNodeConfirmDoneEndDate").val();
            if(assertNodeConfirmDoneEndDate != assertNodeConfirmDoneEndDate1){
                alertx("请先点击查询按钮后，再导出约检明细");
                return false;
            }



            //分数
			var totalAndGotIsEqual1 = '${checkSelect.totalAndGotIsEqual}';
			var totalAndGotIsEqual = $(":radio[name='totalAndGotIsEqual'][checked='checked']").val();
			if(null==totalAndGotIsEqual || ""==totalAndGotIsEqual || totalAndGotIsEqual == "undefined"){
				totalAndGotIsEqual = "";
			}
			if(totalAndGotIsEqual1 != totalAndGotIsEqual){
				alertx("请先点击查询按钮后，再导出约检明细");
				return false;
			}
			$("#searchForm").attr("action", "${ctx}/pqc/checkSelect/checkSelect/exportExcel");
			$("#searchForm").submit();
			$("#searchForm").attr("action", "${ctx}/pqc/checkSelect/checkSelect/checkSelectList");
		}
		
		function searchButton(){
			$("#searchForm").attr("action", "${ctx}/pqc/checkSelect/checkSelect/checkSelectList");
			$("#searchForm").submit();
			loading("查询中...请稍后");
		}
		
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/pqc/checkSelect/checkSelect/list">约检明细查询</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="checkSelect" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		
		<!-- 项目经理申请约检日期 -->
		<input id="beginCreateDate1" type="hidden" value="<fmt:formatDate value="${checkSelect.beginCreateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>">
		<input id="endCreateDate1" type="hidden" value="<fmt:formatDate value="${checkSelect.endCreateDate}"  pattern="yyyy-MM-dd HH:mm:ss"/>">
		
		<!-- 约检检查日期 -->
		<input id="beginCheckDatetime1" type="hidden" value="<fmt:formatDate value="${checkSelect.beginCheckDatetime}" pattern="yyyy-MM-dd HH:mm:ss"/>">
		<input id="endCheckDatetime1" type="hidden" value="<fmt:formatDate value="${checkSelect.endCheckDatetime}"  pattern="yyyy-MM-dd HH:mm:ss"/>">
		
		<!-- 约检验收日期 -->
		<input id="beginAcceptCheckDatetime1" type="hidden" value="<fmt:formatDate value="${checkSelect.beginAcceptCheckDatetime}" pattern="yyyy-MM-dd HH:mm:ss"/>">
		<input id="endAcceptCheckDatetime1" type="hidden" value="<fmt:formatDate value="${checkSelect.endAcceptCheckDatetime}"  pattern="yyyy-MM-dd HH:mm:ss"/>">

		<input id="assertNodeConfirmDoneStartDateId" type="hidden" value="<fmt:formatDate value="${checkSelect.assertNodeConfirmDoneStartDate}" pattern="yyyy-MM-dd HH:mm:ss"/>">
		<input id="assertNodeConfirmDoneEndDateId" type="hidden" value="<fmt:formatDate value="${checkSelect.assertNodeConfirmDoneEndDate}"  pattern="yyyy-MM-dd HH:mm:ss"/>">

		<ul class="ul-form">
			<li><label>门店：</label>
				<c:if test="${empty storeDropEnable}">
					<form:select path="storeId" class="input-medium" disabled="true">
						<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
				<c:if test="${!empty storeDropEnable}">
					<form:select path="storeId" class="input-medium needClear">
						<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
			</li>
			<li><label>工程模式：</label>
				<c:if test="${empty gongcheng}">
					<form:select path="projectMode" class="input-medium" disabled="true">
						<form:option value="" label=""/>
						<form:options items="${fns:getDictList('project_mode')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
				<c:if test="${!empty gongcheng}">
					<form:select path="projectMode" class="input-medium needClear">
						<form:option value="" label=""/>
						<form:options items="${fns:getDictList('project_mode')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
					
			</li>
			<li><label>项目经理：</label>
				<form:input path="itemManager" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>客户姓名：</label>
				<form:input path="customerName" htmlEscape="false" maxlength="16" class="input-medium"/>
			</li>
			<li><label>约检节点名称：</label>
				<form:input path="qcCheckNodeName" htmlEscape="false" maxlength="16" class="input-medium"/>
			</li>



			<li><label style="width:150px;">项目经理提报申请日期：</label>
				<input name="beginCreateDate" type="text" readonly="readonly" id="beginCreateDate" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${checkSelect.beginCreateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'endCreateDate\')}',isShowClear:false});"/> - 
				<input name="endCreateDate" type="text" readonly="readonly" id="endCreateDate" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${checkSelect.endCreateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'beginCreateDate\')}',isShowClear:false});"/>
			</li>


			<li><label style="width:200px;">项目经理申请质检员上门日期：</label>
				<input name="beginCheckDatetime" type="text" readonly="readonly" id="beginCheckDatetime" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${checkSelect.beginCheckDatetime}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'endCheckDatetime\')}',isShowClear:false});"/> -
				<input name="endCheckDatetime" type="text" readonly="readonly" id="endCheckDatetime" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${checkSelect.endCheckDatetime}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'beginCheckDatetime\')}',isShowClear:false});"/>
			</li>




			<li><label style="width:200px;">质检员实际上门日期：</label>
				<input name="beginAcceptCheckDatetime" type="text" readonly="readonly" id="beginAcceptCheckDatetime" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${checkSelect.beginAcceptCheckDatetime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'endAcceptCheckDatetime\')}',isShowClear:false});"/> -
				<input name="endAcceptCheckDatetime" type="text" readonly="readonly" id="endAcceptCheckDatetime" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${checkSelect.endAcceptCheckDatetime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'beginAcceptCheckDatetime\')}',isShowClear:false});"/>
			</li>



			<li><label style="width:200px;">质检员确认节点验收通过日期：</label>
				<input name="assertNodeConfirmDoneStartDate" type="text" readonly="readonly" id="assertNodeConfirmDoneStartDate" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${checkSelect.assertNodeConfirmDoneStartDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'endAcceptCheckDatetime\')}',isShowClear:false});"/> -
				<input name="assertNodeConfirmDoneEndDate" type="text" readonly="readonly" id="assertNodeConfirmDoneEndDate" maxlength="0" class="input-medium Wdate"
					value="<fmt:formatDate value="${checkSelect.assertNodeConfirmDoneEndDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'beginAcceptCheckDatetime\')}',isShowClear:false});"/>
			</li>
			<li><label>分数：</label>
				<form:radiobuttons path="totalAndGotIsEqual" items="${fns:getDictList('total_and_got_is_equal')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="button" value="查询"  onclick="searchButton()"/></li>
			<li class="btns"><input class="btn btn-primary clearBtn" type="button" value="清空" onclick="clearCondition()"/></li>
			<li class="btns"><input class="btn btn-primary" type="button" value="导出" onclick="exportExcel()"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>门店</th>
				<th>订单编号</th>
				<th>小区名称</th>
				<th>客户姓名</th>
				<th>项目经理</th>
				<th>质检员</th>
				<th>实际开工日期</th>
				<th>约检节点名称</th>
				<th>项目经理提报申请时间</th>
				<th>项目经理申请质检员上门日期</th>
				 <th>项目经理申请延期原因</th>
				<th>质检员实际上门日期</th>
				<th>质检员确认节点验收通过日期</th>
				<th>质检员验收延期原因</th>
				<th>总分</th>
				<th>实际得分</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="check">
			<tr>
				<td>
					${check.storeName}
				</td>
				<td>
						${check.orderNumber}
				</td>
				<td>
					${check.communityName }-${check.buildNumber }-${check.buildUnit }-${check.buildRoom }
				</td>
				<td>
					${check.customerName }
				</td>
				<td>
					${check.itemManager }
				</td>
				<td>
					${check.orderInspector }
				</td>
				<td>
					<fmt:formatDate value="${check.actualStartDate}" type="date"/>
				</td>
				<td>
					${check.qcCheckNodeName }
				</td>
				<td>
					<fmt:formatDate value="${check.createDate}" type="both"/>
				</td>

				<td>
					<fmt:formatDate value="${check.expectCheckDatetime}" type="date"/>
				</td>

				<td>
						${check.delayReasonPm}
				</td>



				<td>
					<fmt:formatDate value="${check.checkDatetime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${check.acceptCheckDatetime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
						${check.delayReasonQc}
				</td>
				<td>
					${check.totalScore }
				</td>
				<td>
					${check.gotScore }
				</td>
				<td>
					<c:if test="${check.status>4 }">
						<a href="${ctx}/bizorderqcbill/bizOrderQcBill/details?qcBillId=${check.qcBillId}">查看报告</a>
					</c:if>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>