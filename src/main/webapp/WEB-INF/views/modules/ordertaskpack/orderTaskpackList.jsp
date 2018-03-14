<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>生成任务包管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			getDepartments();
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
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
			 $(":checkbox").attr("checked",false)
		}
		
		$(function(){
			var taskpackType = '${taskpackType}';
			if(null!=taskpackType && taskpackType=="1"){
				alertx("生成任务包失败，请完善该订单数据，再操作此功能");
			}
		});
		
		//加载区域信息
		function getDepartments(){
			$("#enginDepartId").html('');
			$.ajax({
				url:'${ctx}/engdept/bizEngineeringDepartment/departmentList',
				type:'post',
				dataType : 'json',
				data:{
					'storeId':$('#storeId').val(),
					'projectMode':$('#projectMode').val(),
					'employeeId':$('#employeeId').val()
				},
				success:function(data){
					if(data == null){
						$("#enginDepartId").append('');
					} else {
						var html = "<option value=''></option>";
						for(var i=0;i<data.length;i++){
							var sec = "";
							if('${orderTaskpack.enginDepartId}' == data[i].value){
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
		
		function searchButton(){
			//门店
			var storeId = $("#storeId").val();
			if(storeId == ""){
				alertx("请选择门店+订单状态才能进行查询。"); 
				return false;
			}
			if ($("input[type='checkbox']").is(':checked')) {
			} else {
				alertx("请选择门店+订单状态才能进行查询。"); 
				return false;
			}
			
			$("#searchForm").attr("action", "${ctx}/ordertaskpack/orderTaskpack/list");
            $("#searchForm").submit();
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/ordertaskpack/orderTaskpack/preList">已生成任务包的订单</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="orderTaskpack" action="" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<input id="employeeId" name="employeeId" type="hidden" value="${employeeId}"/>
		<ul class="ul-form">
			<li><label>门店：</label>
				<c:if test="${empty storeDropEnable}">
					<form:select path="storeId" id="storeId" class="input-medium" disabled="true" onchange="getDepartments()">
						<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
				<c:if test="${!empty storeDropEnable}">
					<form:select path="storeId" id="storeId" class="input-medium needClear" onchange="getDepartments()">
						<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
			</li>
			<li><label>工程模式：</label>
				<c:if test="${empty gongcheng}">
					<form:select path="projectMode" id="projectMode" class="input-medium" disabled="true" onchange="getDepartments()">
						<form:option value="" label=""/>
						<form:options items="${fns:getDictList('package_project_mode')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
				<c:if test="${!empty gongcheng}">
					<form:select path="projectMode" id="projectMode" class="input-medium needClear" onchange="getDepartments()">
						<form:option value="" label=""/>
						<form:options items="${fns:getDictList('package_project_mode')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
			</li>
			
			<li><label>区域：</label>
				<form:select path="enginDepartId" id ="enginDepartId" class="input-medium needClear">
					<form:options items="${fns:getEngineListWithUserConditionsForBiddenChange()}"   itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			
			<li><label>订单编号：</label>
				<form:input path="orderNumber" htmlEscape="false" maxlength="100" class="input-medium needClear"/>
			</li>
			
			<li><label>客户姓名：</label>
				<form:input path="customerName" htmlEscape="false" maxlength="100" class="input-medium needClear"/>
			</li>
			<li><label>项目经理：</label>
				<form:input path="itemManager" htmlEscape="false" maxlength="64" class="input-medium needClear"/>
			</li>
			<li style="height: 60px;">
				<label>订单状态：</label>
				<form:checkboxes path="orderStatusNumbers" id="orderStatusNumbers" items="${orderStatusNumbers}" itemLabel="label" itemValue="value" htmlEscape="false" class="required"/>
			</li>
			<li style="height:50px;">
				<label style="width: 104px;">生成任务包时间：</label>
				<input name="startTaskpackageTime" id="startTaskpackageTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					   value="<fmt:formatDate value="${orderTaskpack.startTaskpackageTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'endTaskpackageTime\')}',isShowClear:true});"/>
				&nbsp;&nbsp;
				<input name="endTaskpackageTime" id="endTaskpackageTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					   value="<fmt:formatDate value="${orderTaskpack.endTaskpackageTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'startTaskpackageTime\')}',isShowClear:true});"/>
			</li>
			<li><label>是否作废：</label> 
				<form:select path="isScrap" class="input-medium needClear">
					<form:option value="" label="" />
					<form:options items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
			</li>
			
			
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="button" value="查询"  onclick="searchButton()"/></li>
			<li class="btns"><input class="btn btn-primary clearBtn" type="button" value="清空" onclick="clearCondition()"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>门店编号</th>
				<th>工程模式</th>
				<th>区域</th>
				<th>订单编号</th>
				<th>客户姓名</th>
				<th>客户地址</th>
				<th>项目经理</th>
				<th>订单状态</th>
				<th>生成任务包时间</th>
				<th>生成任务包操作人</th>
				<shiro:hasPermission name="ordertaskpack:orderTaskpack:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="orderTaskpack">
			<tr>
				<td>${fns:getStoreLabel(orderTaskpack.storeId, '')}</td>
				<td>${fns:getDictLabel(orderTaskpack.projectMode, 'package_project_mode', '')}</td>
				<td>
					${orderTaskpack.departmentName}
				</td>
				<td>
					${orderTaskpack.orderNumber}
				</td>
				<td>
					${orderTaskpack.customerName}
				</td>
				<td>
					${orderTaskpack.customerAddress}
				</td>
				<td>
					${orderTaskpack.itemManager}
				</td>
				<td>${fns:getDictLabel(orderTaskpack.orderStatusNumber, 'order_status', '')}<br/></td>
				<td>
					<fmt:formatDate value="${orderTaskpack.taskpackageTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>${orderTaskpack.createName}</td>
				<shiro:hasPermission name="ordertaskpack:orderTaskpack:edit"><td>
					<c:if test="${orderTaskpack.ordertaskpackStatus == '0'}">
						<a href="${ctx}/ordertaskpack/orderTaskpack/form?id=${orderTaskpack.id}&mark=1&storeId=
						${orderTaskpack.storeId }&projectMode=${orderTaskpack.projectMode}&contractStartDate=<fmt:formatDate type="both"
							value="${orderTaskpack.contractStartDate }"/>">生成任务包</a><!-- pattern="yyyy-MM-dd HH:mm:ss"  -->
					</c:if>
					<c:if test="${orderTaskpack.ordertaskpackStatus == '1'}">
						<a href="${ctx}/ordertaskpack/orderTaskpack/form?id=${orderTaskpack.id}&mark=2&storeId=
						${orderTaskpack.storeId }&projectMode=${orderTaskpack.projectMode}&packCode=0">查看详情</a>
						<a href="${ctx}/ordertaskpack/orderTaskpack/toCreateSpecialTaskpackage?id=${orderTaskpack.id}">生成特殊任务包</a>
					</c:if>
					<c:if test="${orderTaskpack.ordertaskpackStatus == null}">
						<a href="${ctx}/ordertaskpack/orderTaskpack/form?id=${orderTaskpack.id}&mark=1&storeId=
						${orderTaskpack.storeId }&projectMode=${orderTaskpack.projectMode}&contractStartDate=<fmt:formatDate type="both"
							value="${orderTaskpack.contractStartDate }"/>">生成任务包</a><!-- pattern="yyyy-MM-dd HH:mm:ss"  -->
					</c:if>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>