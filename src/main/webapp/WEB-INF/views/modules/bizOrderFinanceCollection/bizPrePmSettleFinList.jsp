<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>接口传递客户款查询</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
	$(document).ready(function() {
		var arrSon = document.getElementsByName("collectionStatusList");
		for (i = 0; i < arrSon.length; i++) {
			if (!arrSon[i].checked){
				document.getElementById("chkAll").checked=false;
				break;
			}
				
		}
		getDepartments();
	});
	function page(n, s) {
		$("#pageNo").val(n);
		$("#pageSize").val(s);
		$("#searchForm").submit();
		return false;
	}
	function getDepartments(){
		$("#enginDepartId").html('');
		$("#packageCode").html('');
		$.ajax({
			url:'${ctx}/engdept/bizEngineeringDepartment/departmentList',
			type:'post',
			dataType:'json',
			data:{
				'storeId':$('#storeId').val(),
				'projectMode':$("#projectMode").val(),
				'employeeId':$('#employeeId').val()
			},
			success:function(data){
				if(data == null){
					$("#enginDepartId").append('');
				} else {
					var html = "<option value=''></option>";
					for(var i=0;i<data.length;i++){
						var sec = "";
						if('${bizOrderFinanceCollection.enginDepartId}' == data[i].value){
		            		sec = "selected='selected'";
		            		$("#s2id_enginDepartId .select2-chosen").html(data[i].label);
		            	}
						html += "<option value='" + data[i].value +"' " + sec + ">" + data[i].label + "</option>"
					}
					html+='';
	        		$("#enginDepartId").append(html);
				}
			}
		});
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
	
	function ChkAllClick(sonName, cbAllId) {
		var arrSon = document.getElementsByName(sonName);
		var cbAll = document.getElementById(cbAllId);
		var tempState = cbAll.checked;
		for (i = 0; i < arrSon.length; i++) {
			if (arrSon[i].checked != tempState)
				arrSon[i].click();
		}
	}
	
	// --子项复选框被单击---
	function ChkSonClick(sonName, cbAllId) {
		var arrSon = document.getElementsByName(sonName);
		var cbAll = document.getElementById(cbAllId);
		for (var i = 0; i < arrSon.length; i++) {
			if (!arrSon[i].checked) {
				cbAll.checked = false;
				return;
			}
		}
		cbAll.checked = true;
	}
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="#">接口传递客户款查询</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="bizOrderFinanceCollection"
		action="${ctx}/bizOrderFinanceCollection/bizOrderFinanceCollection/queryfindListPrePmSettleFinList"
		method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden"
			value="${page.pageSize}" />
		<ul class="ul-form">
			<li><label>门店：</label> <c:if test="${empty storeDropEnable}">
					<form:select path="storeId" class="input-medium" disabled="true"
						id="storeId" onchange="getDepartments()">
						<form:options items="${fns:getStoreList()}" itemLabel="label"
							itemValue="value" htmlEscape="false" />
					</form:select>
				</c:if> <c:if test="${!empty storeDropEnable}">
					<form:select path="storeId" class="input-medium needClear" id="storeId"
						onchange="getDepartments()">
						<form:options items="${fns:getStoreList()}" itemLabel="label"
							itemValue="value" htmlEscape="false" />
					</form:select>
				</c:if></li>

			<li><label>工程模式：</label> <c:if test="${empty gongcheng}">
					<form:select path="projectMode" id="projectMode"
						class="input-medium" disabled="true" onchange="getDepartments()">
						<form:option value="" label="" />
						<form:options items="${fns:getDictList('project_mode')}"
							itemLabel="label" itemValue="value" htmlEscape="false" />
					</form:select>
				</c:if> <c:if test="${!empty gongcheng}">
					<form:select path="projectMode" id="projectMode"
						class="input-medium needClear" onchange="getDepartments()">
						<form:option value="" label="" />
						<form:options items="${fns:getDictList('project_mode')}"
							itemLabel="label" itemValue="value" htmlEscape="false" />
					</form:select>
				</c:if></li>

			<li><label>区域：</label> <select id="enginDepartId"
				name="enginDepartId" class="input-medium needClear">
			</select></li>

			<li><label>订单编号：</label> <form:input path="orderNum"
					htmlEscape="false" maxlength="64" class="input-medium needClear" />
			</li>
			<li><label>客户姓名：</label> <form:input path="customerName"
					htmlEscape="false" maxlength="64" class="input-medium needClear" />
			</li>
			<li><label>交款类别：</label> 
			<form:select id="collectionType"
				path="collectionType" class="input-medium needClear">
					<form:option value="" label="" />
					<form:options items="${fns:getDictList('collection_type')}"
						itemLabel="label" itemValue="value" htmlEscape="false" />
			</form:select> 
			</li>

			<li><label>客户交款时间：</label> <input name="beginCheckDate"
				type="text" readonly="readonly" maxlength="20"
				class="input-medium Wdate needClear"
				value="<fmt:formatDate value="${bizOrderFinanceCollection.beginCheckDate}" pattern="yyyy-MM-dd"/>"
				onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" />
				- <input name="endCheckDate" type="text" readonly="readonly"
				maxlength="20" class="input-medium Wdate needClear"
				value="<fmt:formatDate value="${bizOrderFinanceCollection.endCheckDate}" pattern="yyyy-MM-dd"/>"
				onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" />
			</li>
			
            <li><label>系统接收时间：</label> <input name="sysBeginCheckDate"
				type="text" readonly="readonly" maxlength="20"
				class="input-medium Wdate needClear"
				value="<fmt:formatDate value="${bizOrderFinanceCollection.sysBeginCheckDate}" pattern="yyyy-MM-dd"/>"
				onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" />
				- <input name="sysEndCheckDate" type="text" readonly="readonly"
				maxlength="20" class="input-medium Wdate needClear"
				value="<fmt:formatDate value="${bizOrderFinanceCollection.sysEndCheckDate}" pattern="yyyy-MM-dd"/>"
				onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" />
			</li>

            <li style="width: 100%"><label>交款状态：</label>
            <input id="chkAll"
				name="chkAll" type="checkbox" value="全选" checked="checked"
				onclick="ChkAllClick('collectionStatusList','chkAll')"  />全选
            </li>
			<li style="height: 90px">
				<input type="checkbox" name="collectionStatusList" id="collectionStatusList"
						value="10" <c:if test="${fns:checkIsExits(bizOrderFinanceCollection.collectionStatusStrs,10)}"> checked="checked"</c:if>
						onclick="ChkSonClick('collectionStatusList','chkAll')"
						 />10-已交款（实收>应收）&nbsp;
			    
			    <input type="checkbox" name="collectionStatusList" id="collectionStatusList"
						value="20" <c:if test="${fns:checkIsExits(bizOrderFinanceCollection.collectionStatusStrs,20)}"> checked="checked"</c:if>
						onclick="ChkSonClick('collectionStatusList','chkAll')"
						 />20-已交款（0&lt;=应收-实收&lt;=1000）&nbsp;
				
				<input type="checkbox" name="collectionStatusList" id="collectionStatusList"
						value="30" <c:if test="${fns:checkIsExits(bizOrderFinanceCollection.collectionStatusStrs,30)}"> checked="checked"</c:if>
						onclick="ChkSonClick('collectionStatusList','chkAll')"
						/>30-已交款（特批）&nbsp;
			</li>
            
			<li class="btns"><input id="btnSubmit" class="btn btn-primary"
				type="submit" value="查询" /></li>
			<li class="btns"><input class="btn btn-primary clearBtn" type="button" value="清空"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}" />
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>门店</th>
				<th>系统接收时间</th>
				<th>工程模式</th>
				<th>区域</th>
				<th>订单编号</th>
				<th>客户</th>
				<th>客户手机号</th>
				<th>地址</th>
				<th>合同签约日期</th>
				<th>交款类别</th>
				<th>交款金额</th>
				<th>交款状态</th>
				<th>客户交款完成时间</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="bizOrderFinanceCollection">
			<tr>
				<td>${fns:getStoreLabel(bizOrderFinanceCollection.storeId, '')}</td>
				<td><fmt:formatDate value="${bizOrderFinanceCollection.collectionOperatorDatetime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td>${fns:getDictLabel(bizOrderFinanceCollection.projectMode, 'project_mode', '')}</td>
				<td>${bizOrderFinanceCollection.departmentName}</td>
				<td>${bizOrderFinanceCollection.orderNum }</td>
				<td>${bizOrderFinanceCollection.customerName }</td>
				<td>${bizOrderFinanceCollection.customerPhone }</td>
				<td>${bizOrderFinanceCollection.communityName}-${bizOrderFinanceCollection.buildNumber}-${bizOrderFinanceCollection.buildUnit}-${bizOrderFinanceCollection.buildRoom}</td>
				<td><fmt:formatDate value="${bizOrderFinanceCollection.signContractDate}" pattern="yyyy-MM-dd"/></td>
				<td>${fns:getDictLabel(bizOrderFinanceCollection.collectionType, 'collection_type', '')}</td>
				<td>${bizOrderFinanceCollection.collectionAmount}</td>
				<td>${fns:getDictLabel(bizOrderFinanceCollection.collectionStatus, 'collection_status', '')}</td>
				<td><fmt:formatDate value="${bizOrderFinanceCollection.collectionDatetime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>