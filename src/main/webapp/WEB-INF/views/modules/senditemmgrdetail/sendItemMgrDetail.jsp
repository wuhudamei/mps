<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>派项目经理明细查询</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$(":radio[name='houseIsNew'][value='" + 2 + "']").next().text("全部");
			var name = $(":radio[name='houseIsNew'][checked='checked']").val();
			if(name==null || name=="2"){
				$(":radio[name='houseIsNew'][value='" + 2 + "']").prop("checked", "checked");
			}
			findEngineListByStoreIdAndProjectMode();
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
			 $(":radio[name='houseIsNew'][value='" + 2 + "']").prop("checked", "checked");
		}

		// --全选框被单击---
		function ChkAllClick(sonName, cbAllId){
		    var arrSon = document.getElementsByName(sonName);
		 var cbAll = document.getElementById(cbAllId);
		 var tempState=cbAll.checked;
		 for(i=0;i<arrSon.length;i++) {
		  if(arrSon[i].checked!=tempState)
		           arrSon[i].click();
		 }
		}
		 
		// --子项复选框被单击---
		function ChkSonClick(sonName, cbAllId) {
		 var arrSon = document.getElementsByName(sonName);
		 var cbAll = document.getElementById(cbAllId);
		 for(var i=0; i<arrSon.length; i++) {
		     if(!arrSon[i].checked) {
		     cbAll.checked = false;
		     return;
		     }
		 }
		 cbAll.checked = true;
		}
		
		function exportExcel(){
			//门店
			var storeId1 = '${sendItemMgrDetail.storeId}';
			var storeId = $("#storeId").val();
			if(storeId1 != storeId){
				alertx("请先点击查询后，再导出。");
				return false;
			}
			if(storeId == ""){
				alertx("请选择门店后，再导出。"); 
				return false;
			}
			
			//工程模式
			var projectMode1 = '${sendItemMgrDetail.projectMode}';
			var projectMode = $("#projectMode").val();
			if(projectMode1 != projectMode){
				alertx("请先点击查询后，再导出。");
				return false;
			}
			//区域
			var engineDepartId1 = '${sendItemMgrDetail.engineDepartId}';
			var engineDepartId = $("#engineDepartSelect").val();
			if(engineDepartId == null){
				engineDepartId = '';
			}
			if(engineDepartId1 != engineDepartId){
				alertx("请先点击查询后，再导出。");
				return false;
			}
			
			//订单编号
			var orderNumber1 = '${sendItemMgrDetail.orderNumber}';
			var orderNumber = $("#orderNumber").val();
			if(orderNumber1 != orderNumber){
				alertx("请先点击查询后，再导出。");
				return false;
			}
			
			//客户姓名
			var customerName1 = '${sendItemMgrDetail.customerName}';
			var customerName = $("#customerName").val();
			if(customerName1 != customerName){
				alertx("请先点击查询后，再导出。");
				return false;
			}
			
			//项目经理
			var itemManager1 = '${sendItemMgrDetail.itemManager}';
			var itemManager = $("#itemManager").val();
			if(itemManager1 != itemManager){
				alertx("请先点击查询后，再导出。");
				return false;
			}
			
			//派单时间 开始
			var beginCreateDate1 = $("#beginCreateDate1").val();
			var beginCreateDate = $("#beginCreateDate").val();
			if(beginCreateDate1 != beginCreateDate){
				alertx("请先点击查询后，再导出。");
				return false;
			}
			if(beginCreateDate == ""){
				alertx("请选择派单时间后再导出。"); 
				return false;
			}
			
			//派单时间 结束 
			var endCreateDate1 = $("#endCreateDate1").val();
			var endCreateDate = $("#endCreateDate").val();
			if(endCreateDate1 != endCreateDate){
				alertx("请先点击查询后，再导出。");
				return false;
			}
			if(endCreateDate == ""){
				alertx("请选择派单时间后再导出。"); 
			}
			
			$("#searchForm").attr("action", "${ctx}/sendItemMgrDetail/sendItemMgrDetail/exportExcel");
			$("#searchForm").submit();
			$("#searchForm").attr("action", "${ctx}/sendItemMgrDetail/sendItemMgrDetail/senditemMgrDetailList");
			
			alertx("正在导出，请稍后...");
		}
		
		
		function exportDetailExcel(){
			//门店
			var storeId1 = '${sendItemMgrDetail.storeId}';
			var storeId = $("#storeId").val();
			if(storeId1 != storeId){
				alertx("请先点击查询后，再导出。");
				return false;
			}
			if(storeId == ""){
				alertx("请选择门店后，再导出。"); 
				return false;
			}
			
			//工程模式
			var projectMode1 = '${sendItemMgrDetail.projectMode}';
			var projectMode = $("#projectMode").val();
			
			if(projectMode1 != projectMode){
				alertx("请先点击查询后，再导出。");
				return false;
			}
			//区域
			var engineDepartId1 = '${sendItemMgrDetail.engineDepartId}';
			var engineDepartId = $("#engineDepartSelect").val();
			if(engineDepartId == null){
				engineDepartId = '';
			}
			if(engineDepartId1 != engineDepartId){
				alertx("请先点击查询后，再导出。");
				return false;
			}
			
			//订单编号
			var orderNumber1 = '${sendItemMgrDetail.orderNumber}';
			var orderNumber = $("#orderNumber").val();
			if(orderNumber1 != orderNumber){
				alertx("请先点击查询后，再导出。");
				return false;
			}
			
			//客户姓名
			var customerName1 = '${sendItemMgrDetail.customerName}';
			var customerName = $("#customerName").val();
			if(customerName1 != customerName){
				alertx("请先点击查询后，再导出。");
				return false;
			}
			
			//项目经理
			var itemManager1 = '${sendItemMgrDetail.itemManager}';
			var itemManager = $("#itemManager").val();
			if(itemManager1 != itemManager){
				
				alertx("请先点击查询后，再导出。");
				return false;
			}
			
			//派单时间 开始
			var beginCreateDate1 = $("#beginCreateDate1").val();
			var beginCreateDate = $("#beginCreateDate").val();
			if(beginCreateDate1 != beginCreateDate){
				
				alertx("请先点击查询后，再导出。");
				return false;
			}
			if(beginCreateDate == ""){
				alertx("请选择派单时间后再导出。"); 
				return false;
			}
			
			//派单时间 结束 
			var endCreateDate1 = $("#endCreateDate1").val();
			var endCreateDate = $("#endCreateDate").val();
			if(endCreateDate1 != endCreateDate){
				alertx("请先点击查询后，再导出。");
				return false;
			}
			if(endCreateDate == ""){
				alertx("请选择派单时间后再导出。"); 
				return false;
			}
			
			$("#searchForm").attr("action", "${ctx}/sendItemMgrDetail/sendItemMgrDetail/exportDetailExcel");
			$("#searchForm").submit();
			$("#searchForm").attr("action", "${ctx}/sendItemMgrDetail/sendItemMgrDetail/senditemMgrDetailList");
			alertx("正在导出，请稍后...");
		}
		

		function searchButton(){
            $("#searchForm").attr("action", "${ctx}/sendItemMgrDetail/sendItemMgrDetail/senditemMgrDetailList");
            $("#searchForm").submit();
		}
		
		function findEngineListByStoreIdAndProjectMode(){
			var html3 = '<option value=""></option>';
			var storeId = $("#storeId").val();
			var projectModeValue = $("#projectMode").val();
			if (storeId=="" ||projectModeValue=="" ||undefined==storeId ||undefined==projectModeValue) {
				return;
			}
		
			// 根据门店个,工程模式动态加载工程区域
			$.ajax({
				url : "${ctx}/order/order/findEngineDepartmentBystoreIdAndProjectMode?storeId=" + storeId + "&projectModeValue=" + projectModeValue,
				type : "get",
				success : function(data) {
					if (null!= data && data.length > 0) {
						
						
						for (var v = 0; v < data.length; v++) {
							
							if(data[v].engineDepartId == "${sendItemMgrDetail.engineDepartId}"){
                                $("#s2id_engineDepartSelect").find(".select2-chosen").text(data[v].engineDepartName);
                                html3 +='<option selected="selected" value="'+data[v].engineDepartId+'">'+data[v].engineDepartName+'</option>'
                           }else{
                                html3 +='<option value="'+data[v].engineDepartId+'">'+data[v].engineDepartName+'</option>'
                           }

						}
									
						$("#engineDepartSelect").html(html3);
					} else {
						$("#engineDepartSelect").html(html3);
					}
	
				}
			});		
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/sendItemMgrDetail/sendItemMgrDetail/senditemMgrDetailList">派项目经理明细查询</a></li>
	</ul>
	<br/>
	
	
	<form:form id="searchForm" modelAttribute="sendItemMgrDetail" action="${ctx}/sendItemMgrDetail/sendItemMgrDetail/senditemMgrDetailList" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		
		<!-- 派单时间 -->
		<input id="beginCreateDate1" type="hidden" value="<fmt:formatDate value="${sendItemMgrDetail.beginCreateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>">
		<input id="endCreateDate1" type="hidden" value="<fmt:formatDate value="${sendItemMgrDetail.endCreateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>">
		
		<ul class="ul-form">
			<li>
				<label>门店：</label>
				<form:select path="storeId" class="input-medium needClear" id="storeId">
					<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li>
				<label>工程模式：</label>
				<c:if test="${empty gongcheng}">
					<form:select path="projectMode" class="input-medium" disabled="true" onchange="findEngineListByStoreIdAndProjectMode()">
						<form:option value="" label=""/>
						<form:options items="${fns:getDictList('project_mode')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
				<c:if test="${!empty gongcheng}">
					<form:select path="projectMode" class="input-medium needClear" onchange="findEngineListByStoreIdAndProjectMode()">
						<form:option value="" label=""/>
						<form:options items="${fns:getDictList('project_mode')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
			</li>
			<li>
				<label>区域：</label>
				<form:select path="engineDepartId" class="input-medium needClear" id="engineDepartSelect">
					<%-- <form:options items="${fns:getEngineListWithUserConditionsForBiddenChange()}"   itemLabel="label" itemValue="value" htmlEscape="false"/> --%>
				</form:select>
			</li>
			<li>
				<label>订单编号：</label>
				<form:input path="orderNumber" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
		</ul>
		<ul class="ul-form">
			<li>
				<label>客户姓名：</label>
				<form:input path="customerName" htmlEscape="false" maxlength="16" class="input-medium"/>
			</li>
			<li>
				<label>项目经理：</label>
				<form:input path="itemManager" htmlEscape="false" maxlength="11" class="input-medium"/>
			</li>
			<li><label>派单时间：</label>
				<input name="beginCreateDate" id="beginCreateDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					   value="<fmt:formatDate value="${sendItemMgrDetail.beginCreateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'endCreateDate\')}',isShowClear:true});"/>
				&nbsp;-&nbsp;
				<input name="endCreateDate" id="endCreateDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					   value="<fmt:formatDate value="${sendItemMgrDetail.endCreateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'beginCreateDate\')}',isShowClear:true});"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="button" value="查询"  onclick="searchButton()"/></li>
			<li class="btns"><input class="btn btn-primary clearBtn" type="button" value="清空" onclick="clearCondition()"/></li>
			<li class="btns"><input class="btn btn-primary" type="button" value="导出派单汇总表" onclick="exportExcel()"/></li>
			<li class="btns"><input class="btn btn-primary" type="button" value="导出派单明细表" onclick="exportDetailExcel()"/></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>门店</th>
				<th>派单时间</th>
				<th>操作人</th>
				<th>新派项目经理</th>
				<th>原项目经理</th>
				<th>工程模式</th>
				<th>区域</th>
				<th>订单编号</th>
				<th>实际开工日期</th>
				<th>小区</th>
				<th>客户</th>
				<th>客户手机号</th>
				<th>设计师</th>
				<th>设计师手机号</th>
				<th>项目经理</th>
				<th>项目经理手机号</th>
				<th>质检员</th>
				<th>质检员手机号</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="sendItemMgrDetail">
			<tr>
				<td>${sendItemMgrDetail.storeName }</td>
				<td><fmt:formatDate value="${sendItemMgrDetail.createDate }" pattern="yyyy-MM-dd HH:mm:ss"/></td></td>
				<td>${sendItemMgrDetail.operName }</td>
				<td>${sendItemMgrDetail.newItemManager }</td>
				<td>${sendItemMgrDetail.oldItemManager }</td>
				<td>${sendItemMgrDetail.projectModeName }</td>
				<td>${sendItemMgrDetail.engineDepartName }</td>
				<td>${sendItemMgrDetail.orderNumber }</td>
				<td><fmt:formatDate value="${sendItemMgrDetail.actualStartDate }" pattern="yyyy-MM-dd"/></td>
				<td>${sendItemMgrDetail.communityName }<br/>${sendItemMgrDetail.buildNumber }-${sendItemMgrDetail.buildUnit }-${sendItemMgrDetail.buildRoom }</td>
				<td>${sendItemMgrDetail.customerName }</td>
				<td>${sendItemMgrDetail.customerPhone }</td>
				
				<td>${sendItemMgrDetail.designerName }</td>
				<td>${sendItemMgrDetail.designerPhone }</td>
				
				<td>${sendItemMgrDetail.itemManager }</td>
				<td>${sendItemMgrDetail.itemManagerPhone }</td>
				
				<td>${sendItemMgrDetail.orderInspector }</td>
				<td>${sendItemMgrDetail.orderInspectorPhone }</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>