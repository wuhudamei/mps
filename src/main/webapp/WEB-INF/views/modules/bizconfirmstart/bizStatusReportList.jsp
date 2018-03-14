<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>大统计表-大阶段查询</title>
	<meta name="decorator" content="default"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Worker/css/globalUtil.css"/>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/history.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#allCheck").click(function () {
	            if ($(this).attr("checked")) { // 全选 
	                $("input[name='installStatus']").each(function () {
	                    $(this).attr("checked", true);
	                });
	            } else { // 取消全选 
	                $("input[name='installStatus']").each(function () {
	                    $(this).attr("checked", false);
	                });
	            }
	        });	
			
			var size = $("#orderCountSize").val();
			if(size == '0'){
				$("#numberSize").text('');
			}else{
				$("#numberSize").text(size);
			}
		});
	
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
		
		//检验所属门店是否有值
		function checkStoreID(){
			var storeID = $("#storeID").val();
			var storeIDAll = $("#storeIDAll").val();
			if(!storeID && !storeIDAll){
				alert("必须选择一个所属门店！");
				return false;
			}else{
				return true;
				/* $("#searchForm").submit();//jquery提交form */
			}
		}
		
		//清空查询条件
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
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/bizorderstatusreport/bizOrderStatusReport/preList">大统计表-大阶段列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="bizOrderStatusReport" action="${ctx}/bizorderstatusreport/bizOrderStatusReport/list" method="post" class="breadcrumb form-search" onsubmit="return checkStoreID();">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<%-- <li><label>门店：</label> 
				<form:select path="storeId" class="input-medium needClear" id="storeID">
					<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
			</li> --%>
			<%-- <li><label>订单编号：</label>
				<form:input path="orderNumber" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>客户姓名：</label>
				<form:input path="customerName" htmlEscape="false" maxlength="16" class="input-medium"/>
			</li> --%>
			<li><label>门店：</label>
				<c:if test="${empty storeDropEnable}">
					<form:select path="storeId" class="input-medium" disabled="true" id="storeID">
						<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
				<c:if test="${!empty storeDropEnable}">
					<form:select path="storeId" class="input-medium needClear" id="storeIDAll">
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
			<li><label>合同开工日期：</label>
				<input name="contractStartDateBegin" type="text" id="contractStartDateBegin" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${bizOrderStatusReport.contractStartDateBegin}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'contractEndDateEnd\')}',isShowClear:false});"/> &nbsp;至&nbsp;
					
				<input name="contractEndDateEnd" type="text" id="contractEndDateEnd" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${bizOrderStatusReport.contractEndDateEnd}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'contractStartDateBegin\')}',isShowClear:false});"/>
			</li>
			<li><label>实际开工日期：</label>
				<input name="actualStartDateBegin" type="text" id="actualStartDateBegin" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${bizOrderStatusReport.actualStartDateBegin}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'actualStartDateEnd\')}',isShowClear:false});"/> &nbsp;至&nbsp;
					
				<input name="actualStartDateEnd" type="text" id="actualStartDateEnd" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${bizOrderStatusReport.actualStartDateEnd}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'actualStartDateBegin\')}',isShowClear:false});"/>
			</li>
			<%-- <li><label>提交验收日期：</label>
				<input name="confirmAcceptanceDateBegin" type="text" id="confirmAcceptanceDateBegin" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${bizConfirmStartOrder.confirmAcceptanceDateBegin}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'confirmAcceptanceDateEnd\')}',isShowClear:false});"/> &nbsp;至&nbsp;
					
				<input name="confirmAcceptanceDateEnd" type="text" id="confirmAcceptanceDateEnd" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${bizConfirmStartOrder.confirmAcceptanceDateEnd}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'confirmAcceptanceDateBegin\')}',isShowClear:false});"/>
			</li>
			<li><label>实际进场日期：</label>
				<input name="installRealIntoDateStart" type="text" id="installRealIntoDateStart" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${bizConfirmStartOrder.installRealIntoDateStart}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'installRealIntoDateEnd\')}',isShowClear:false});"/> &nbsp;至&nbsp;
					
				<input name="installRealIntoDateEnd" type="text" id="installRealIntoDateEnd" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${bizConfirmStartOrder.installRealIntoDateEnd}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'installRealIntoDateStart\')}',isShowClear:false});"/>
			</li>
			<li><label>实际完工日期：</label>
				<input name="installRealCompleteDateStart" type="text" readonly="readonly" id="installRealCompleteDateStart" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${bizConfirmStartOrder.installRealCompleteDateStart}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'installRealCompleteDateEnd\')}',isShowClear:false});"/> &nbsp;至&nbsp;
					
				<input name="installRealCompleteDateEnd" type="text" readonly="readonly" id="installRealCompleteDateEnd" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${bizConfirmStartOrder.installRealCompleteDateEnd}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'installRealCompleteDateStart\')}',isShowClear:false});"/>
			</li>
			<li><label>安装项状态：</label>
				<input type="checkbox" id="allCheck"/>全选
				<form:checkboxes path="installStatus" items="${fns:getDictList('install_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
			</li> --%>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="btns"><input class="btn btn-primary clearBtn" type="button" value="清空" onclick="clearCondition()"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>门店</th>
				<th>工程模式</th>
				<th>订单总数</th>
				<c:forEach items="${dictList }" var ="dictList">
				<th>${dictList.label }</th>
				</c:forEach>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list }" var ="list">
			<tr>
				<td>${fns:getStoreLabel(list.storeId, '')}</td>
				<td><c:if test="${modeType == '' || modeType == null }">全部</c:if>
				<c:if test="${modeType == '1'}">产业</c:if><c:if test="${modeType == '2'}">传统</c:if></td>
				<td style="color:red"><b id="numberSize"></b></td>
				<c:set var="sum" value="0"></c:set>
				<c:forEach items="${dictList }" var ="dictList">
					<td id="${dictList.id }">
					<c:forEach items="${page.list}" var="order" varStatus="status">
						<c:if test="${dictList.value == order.orderStatusNumber }">
							<c:set var="sum" value="${sum + order.orderCount}"></c:set>
							 ${order.orderCount }
						</c:if>
					</c:forEach>
					</td>
				</c:forEach>
				<input type="hidden" id="orderCountSize" value="${sum }"/>
			</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
	
	<script type="text/javascript">
	window.onload = function(){
		var storeIdSelected = $("#storeId option:selected").text();
		var storeIdValue = $("#storeId").val();
		//alert("storeIdSelected="+storeIdSelected +"\t" +"storeIdValue=="+storeIdValue);
		if(storeIdValue){
			$.ajax({
				url : "${ctx}/constructionschedule/bizConstructionSchedule/getByStoreIdList",
	        	type : "post",
	        	data : {storeId : storeIdValue},
	        	success : function(data){
	        		var htmls = "";//<option value='' selected='true'></option>
			   		for(var i = 0; i < data.length; i++){
			   			htmls += "<option value='"+data[i].sort+"'>"+data[i].constructionScheduleName+"</option>";
			   		}
			   		$("#nodeIndex").append(htmls);
				  }
			});
		}
	}
	</script>
</body>
</html>