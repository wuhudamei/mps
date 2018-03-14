<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>订单特殊任务包审核管理</title>
	<meta name="decorator" content="default"/>
	<style>
		.none{display:none;}
	</style>

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
		

		function submitData(id,storeId,index){
			var customerMessage = $("#customerMessage").val();
			var packageName = $("#packageName" + index).val();
			
			top.$.jBox.confirm("您确认审核通过吗？","系统提示",function(v,h,f){
				if(v=="ok"){
					if(id){
						$.ajax({
							url : "${ctx}/ordertaskpackageaudit/orderTaskpackageAudit/update",
				        	type : "post",
				        	data : {id : id,packageName : packageName},
				        	success : function(msg){
							   	if(msg == 0){
							   		jBox.tip("任务包已审核通过", 'success');
							   		searchButton();
							   	}
							  }
						});
					}
				}
			},{buttonsFocus:1});
			top.$('.jbox-body .jbox-icon').css('top','55px');
		}
		
		//清空查询条件
		function clearCondition(){
			 document.getElementById("searchForm").reset();
			 //$("#enginDepartId").html('');
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
		var size = '${size}';
		function isZero(){
			for(var i = 0;i< size;i++ ){
				if($("#total"+i).val() == 0){
					if($("#trId"+i).hasClass("none")){
						$("#trId"+i).removeClass('none');
					}else{
						$("#trId"+i).addClass('none');
					}
				}
			}
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
							if('${orderTaskpackageAudit.engineDepartId}' == data[i].value){
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

		function searchButton(){
			$("#searchForm").attr("action", "${ctx}/ordertaskpackageaudit/orderTaskpackageAudit/staySpectialList");
            $("#searchForm").submit();
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/ordertaskpackageaudit/orderTaskpackageAudit/staySpectialList">待审核特殊任务包列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="orderTaskpackageAudit" action="" method="post" class="breadcrumb form-search">
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
				<form:select path="engineDepartId" id="enginDepartId" class="input-medium needClear">
					<form:options items="${fns:getEngineListWithUserConditionsForBiddenChange()}"   itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>订单编号：</label>
				<form:input path="orderNumber" htmlEscape="false" maxlength="18" class="input-medium needClear"/>
			</li>
			<li><label>客户姓名：</label>
				<form:input path="customerName" htmlEscape="false" maxlength="100" class="input-medium needClear"/>
			</li>
			<li><label>项目经理：</label>
				<form:input path="itemCustomer" htmlEscape="false" maxlength="255" class="input-medium needClear"/>
			</li>

			<li><label style="width: 200px;">特殊任务包生成时间：</label> <input
					name="createStartDate" type="text"
					id="createStartDate" readonly="readonly" maxlength="20"
					class="input-medium Wdate"
					value="<fmt:formatDate value="${orderTaskpackageAudit.createStartDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'createEndDate\')}',isShowClear:false});" />
				&nbsp;至&nbsp; <input name="createEndDate" type="text"
									 id="createEndDate" readonly="readonly" maxlength="20"
									 class="input-medium Wdate"
									 value="<fmt:formatDate value="${orderTaskpackageAudit.createEndDate}" pattern="yyyy-MM-dd"/>"
									 onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'createStartDate\')}',isShowClear:false});" />
			</li>

			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="button" value="查询"  onclick="searchButton()"/></li>
			<li class="btns"><input class="btn btn-primary clearBtn" type="button" value="清空" onclick="clearCondition()"/></li>
			
			<li class="clearfix"></li>
		</ul>
	</form:form>
	
	<sys:message content="${message}"/>
	<span style="font-weight:bold;font-size:20px;">
		待审核任务包数量：<span style="color:red;">${stayCount }</span>
		<hr size="3px" noshade=true/>
	</span>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>门店</th>
				<th>工程模式</th>
				<th>区域</th>
				<th>订单编号</th>
				<th>客户姓名</th>
				<th>客户地址</th>
				<th>项目经理</th>
				<th>任务包编号</th>
				<th>任务包名称</th>
				<th>工料费预算总金额</th>
				<th>生成任务包时间</th>
				<th>生成任务包操作人</th>
				<shiro:hasPermission name="ordertaskpackageaudit:orderTaskpackageAudit:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="orderTaskpackageAudit" varStatus="index">
			<tr id="trId${index.index }">
				<td>
					${fns:getStoreLabel(orderTaskpackageAudit.storeId, '')}
				</td>
				<td>${fns:getDictLabel(orderTaskpackageAudit.projectMode, 'package_project_mode', '')}</td>
				<td>
					${orderTaskpackageAudit.departmentName}
				</td>
				<td><%-- <a href="${ctx}/ordertaskpackageaudit/orderTaskpackageAudit/form?id=${orderTaskpackageAudit.id}"> --%>
					${orderTaskpackageAudit.orderNumber}
					<input type ="hidden" id ="total${index.index }" value="${orderTaskpackageAudit.total }" >
				</td>
				<td>
					${orderTaskpackageAudit.customerName}
				</td>
				<td>
					${orderTaskpackageAudit.customerAddress}
				</td>
				<td>
					${orderTaskpackageAudit.itemCustomer}
				</td>
				<td>
					${orderTaskpackageAudit.orderTaskPackageCode}
				</td>
				<td>
					<input type="hidden" id="packageName${index.index }" value="${orderTaskpackageAudit.packageName}"/>
					${orderTaskpackageAudit.packageName}
				</td>
				<td>${orderTaskpackageAudit.total}</td>
				<td>
					<fmt:formatDate value="${orderTaskpackageAudit.packCreateTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${orderTaskpackageAudit.packCreateBy}
				</td>
				<shiro:hasPermission name="ordertaskpackageaudit:orderTaskpackageAudit:edit"><td>
					<c:if test="${orderTaskpackageAudit.packageStateid == '10'}">
						<a href="${ctx}/ordertaskpackageaudit/orderTaskpackageAudit/showView?orderId=${orderTaskpackageAudit.orderId}&packCode=${orderTaskpackageAudit.packageCode}&id=${orderTaskpackageAudit.id}&flag=0&isSpecial=1">查看详情</a>
	   				</c:if>
	   				<c:if test="${orderTaskpackageAudit.packageStateid == '10'}">
	   					<a href="javascript:void(0)" onclick="submitData(${orderTaskpackageAudit.id},${orderTaskpackageAudit.storeId},${index.index })">${budget }</a>
	   				</c:if>
	   				<c:if test="${orderTaskpackageAudit.packageStateid != '10'}">
						<a href="${ctx}/ordertaskpackageaudit/orderTaskpackageAudit/showView?orderId=${orderTaskpackageAudit.orderId}&packCode=${orderTaskpackageAudit.packageCode}&id=${orderTaskpackageAudit.id}&flag=0&isSpecial=1">查看详情</a>
	   				</c:if>
	   				<!-- 显示状态小于【80-工人申请完工】的任务包，默认按任务包编号降序-->
	   				<c:if test="${orderTaskpackageAudit.packageStateid == '10' || orderTaskpackageAudit.packageStateid == '20' || orderTaskpackageAudit.packageStateid == '50'
	   						|| orderTaskpackageAudit.packageStateid == '55' || orderTaskpackageAudit.packageStateid == '60' || orderTaskpackageAudit.packageStateid == '70' ||
	   						orderTaskpackageAudit.packageStateid == '80' }">
						<a href="${ctx}/ordertaskpackageaudit/orderTaskpackageAudit/preUpdate?orderId=${orderTaskpackageAudit.orderId}
						&packCode=${orderTaskpackageAudit.packageCode}&taskpackageId=${orderTaskpackageAudit.id}&flag=0&isSpecial=1">修改</a>
	   				</c:if>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>