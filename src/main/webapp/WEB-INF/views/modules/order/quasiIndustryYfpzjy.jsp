<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>订单管理</title>
	<meta name="decorator" content="default" />
	<style>
		ul.ul1{list-style:none;
			margin-left:30px}
		li.btns{
			float:left;
			margin-left:20px;
		}
	</style>
	<script type="text/javascript">
		$(document).ready(function() {
			getDepartments();
			
			$("#searchForm").validate({
				errorPlacement: function(error, element) {
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			})
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }

		function formReset(){
			$("input[name='orderStatus']").removeAttr("checked");
		 	document.getElementById("searchForm").reset()
		 	 //$("#enginDepartId").html('');
		}
		
		$(function(){
			 $("#isZeroId").click(function() {
              	 $('input[name="isAllotManager"]').attr("checked",this.checked); 
          	 });
          	 var $status = $("input[name='isAllotManager']");
          	 $status.click(function(){
               $("#isZeroId").attr("checked",$status.length == $("input[name='isAllotManager']:checked").length ? true : false);
            });
          	 
          	var inspectorType = '${inspectorType}';
			if(null!=inspectorType && inspectorType=="0"){
				alertx("分配质检员成功");
			}else if(inspectorType=="1"){
				alertx("分配质检员失败，请完善该订单数据，再操作此功能");
			}else if(inspectorType=="2"){
				alertx("重新分配质检员成功");
			}else if(inspectorType=="3"){
				alertx("重新分配质检员失败，请完善该订单数据，再操作此功能");
			}
			
		 });
		
		function gotoAllotInspectorPage(orderId){
			window.location.href = "${ctx}/order2/order2/allotInspector?id=" + orderId ;
		}
		function gotoreAllotInspectorPage(orderId){
			window.location.href = "${ctx}/order2/order2/reAllotInspector?id=" + orderId ;
		}
	
		//分配质检员
		function allotInspector(){
			//拿到订单的id
			var r =document.getElementsByName("ids");
			var orderId;
		    for(var i=0;i<r.length;i++){
		         if(r[i].checked){
		         orderId = r[i].value;
		       }
		    }
		  	var size = $("input[name='ids']:checked").size();
		    if(size == 1){
			    	$.ajax({
			    		url:"${ctx}/order2/order2/findOrderById",
			    		type:"post",
			    		data:{id:orderId},
			    		success:function(data){
			    			if(data.orderInspector == null || ""== data.orderInspector){
			    				gotoAllotInspectorPage(orderId);
			    			}else{
			    				alert("已经分配质检员！");
			    				return;
			    			}
			    		}
			    	});
		    }else{
		    	alert("请选择订单！");
		    	return;
		    }
		}
	
		//重新分配质检员
		function reAllotInspector(){
			//拿到订单的id
			var r =document.getElementsByName("ids");
			var orderId;
		    for(var i=0;i<r.length;i++){
		         if(r[i].checked){
		         orderId = r[i].value;
		       }
		    }
		  	var size = $("input[name='ids']:checked").size();
		    if(size == 1){
			    	$.ajax({
			    		//url:"${ctx}/order2/order2/findOrderById?id="+orderId,
			    		url:"${ctx}/order2/order2/findOrderById",
			    		type:"post",
			    		data:{id:orderId},
			    		dataType:"json",
			    		success:function(data){
			    			//alert(data.orderInspector);
			    			if(data.orderInspector == null || ""== data.orderInspector){
			    				alert("请先分配质检员！");
			    				return;
			    			}else{
			    				gotoreAllotInspectorPage(orderId);
			    			}
			    		}
			    	});
		    }else{
		    	alert("请选择订单！");
		    	return;
		    }
		} 
		
		//加载区域信息
		function getDepartments(){
			$("#enginDepartId").html('');
			$.ajax({
				url:'${ctx}/engdept/bizEngineeringDepartment/departmentList',
				type:'post',
				dataType:'json',
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
							if('${order.enginDepartId}' == data[i].value){
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
		
	</script>	
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/order2/order2/quasiIndustryYfpzjy">分配订单</a></li>
		<%-- <shiro:hasPermission name="order:order:edit"><li><a href="${ctx}/order/order/form">订单添加</a></li></shiro:hasPermission> --%>
	</ul>
	<ul class="ul-form">
		
	</ul>
	<form:form style="width:110%;" id="searchForm" modelAttribute="order2" action="${ctx}/order2/order2/quasiIndustryYfpzjy1" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<input id="employeeId" name="employeeId" type="hidden" value="${employeeId}"/>
		<ul class="ul-form">
		
			<li><label><font color="red">*</font>门店：</label>
				<form:select path="storeId" id="storeId" class="input-medium needClear required" onchange="getDepartments()">
					<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<%-- <li><label>工程模式：</label>
				<c:if test="${!empty projectModeEnable}">
					<form:select path="projectMode" id="projectMode" class="input-medium needClear" disabled="true" onchange="getDepartments()" >
						<form:option value="" label=""/>
						<form:options items="${fns:getDictList('project_mode')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
				<c:if test="${empty projectModeEnable}">
					<form:select path="projectMode" id="projectMode" class="input-medium needClear" onchange="getDepartments()">
						<form:option value="" label=""/>
						<form:options items="${fns:getDictList('project_mode')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
			</li> --%>
			<li><label>区域：</label>
				<form:select path="enginDepartId" id="enginDepartId" class="input-medium needClear">
					<%-- <form:options items="${fns:getEngineListWithUserConditionsForBiddenChangeForOrder()}" itemLabel="label" itemValue="value" htmlEscape="false"/> --%>
					<form:option value="${order.enginDepartId }" label="${order.departmentName }" />
				</form:select>
			</li>
			<li><label>订单编号：</label>
				<form:input path="orderNumber" htmlEscape="false" maxlength="100" class="input-medium needClear"/>
			</li>
			
			<li><label>客户姓名：</label>
				<form:input path="customerName" htmlEscape="false" maxlength="16" class="input-medium needClear"/>
			</li>
			
			<li><label>客户电话：</label>
				<form:input path="customerPhone" htmlEscape="false" maxlength="11" class="input-medium needClear"/>
			</li>
			
			<li><label>客户类型：</label>
				<form:select path="customerType" class="input-medium needClear">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('cus_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			
			<li><label>设计师姓名：</label>
				<form:input path="designerName" htmlEscape="false" maxlength="12" class="input-medium needClear"/>
			</li>
			
			<li><label>客户经理：</label>
				<form:input path="cusManager" htmlEscape="false" maxlength="64" class="input-medium needClear"/>
			</li>
			
			<li><label>项目经理：</label>
				<form:input path="itemManager" htmlEscape="false" maxlength="64" class="input-medium needClear"/>
			</li>
			
			<li><label>质检员：</label>
				<form:input path="orderInspector" htmlEscape="false" maxlength="64" class="input-medium needClear"/>
			</li>
			
			<li><label>签约日期：</label>
				<input name="beginSignContractDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate needClear"
					value="<fmt:formatDate value="${order.beginSignContractDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"/> 至 
				<input name="endSignContractDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate needClear"
					value="<fmt:formatDate value="${order.endSignContractDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"/>
			</li>
			
			<li style="width:500px;"><label>合同开工日期：</label>
				<input name="beginContractStartDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate needClear"
					value="<fmt:formatDate value="${order.beginContractStartDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"/> 至 
				<input name="endContractStartDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate needClear"
					value="<fmt:formatDate value="${order.endContractStartDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"/>
			</li>
			<li><label><font color="red">*</font>订单状态：</label>
				<form:checkboxes path="orderStatus" items="${orderStatus}" itemLabel="label" itemValue="value" htmlEscape="false" class="required"/>
			</li>
			<%-- <li>
				<input type="checkbox" name="isAllotInspector" value="1" id="isZeroId" <c:if test="${fns:checkIsExits(order2.isAllotInspector,'1')}"> checked="checked"</c:if>/>未分配质检员的订单
			</li> --%>
			<li class="clearfix"></li>
			
		</ul>
		 <ul class="ul-form ul1">
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>	
			<li class="btns"><input id="btnSubmit" class="btn btn-primary clearBtn" type="reset" value="清除" onclick="formReset()"/></li>
			<!-- <li class="btns"><input id="btnAllotInspector" class="btn btn-primary" type="button" value="分配质检员" onclick="allotInspector()"/></li> -->
			<li class="btns"><input id="btnClearInspector" class="btn btn-primary" type="button" value="重派质检员" onclick="reAllotInspector()"/></li> 
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	
<form:form id="jvForm">
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>选择</th>
				<th>门&nbsp;&nbsp;店</th>
				<th>工程模式</th>
				<th>区域</th>
				<th>订单编号</th>
				<th>订单状态</th>
				<th>客户姓名</th>
				<th>客户手机号</th>
				<th>客户地址</th>
				<th>设计师</th>
				<th>客户经理</th>
				<th>签约日期</th>
				<th>质检员</th>
				<th>合同开工日期</th>
				<th>合同竣工日期</th>
				<th>接单日期</th>
				<th>分派质检员操作人</th>
				<th>操作时间</th>
				<!-- <shiro:hasPermission name="order:order:edit"><th>操作</th></shiro:hasPermission> -->
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="order">
			<tr>
				<td>
					<input type="radio" name="ids" value="${order.id }"/>
				</td>
				<td>
					${fns:getStoreLabel(order.storeId, '')}
				</td>
				<td>
					${fns:getDictLabel(order.projectMode, 'project_mode', '')}
				</td>
				<td>
					${order.departmentName}
				</td>
				<td>
					${order.orderNumber}
				</td>
				<td>
					${order.orderStatusNumber}-${order.orderStatusDescription}
				</td>
				<td>
					${order.customerName}
				</td>
				<td>
					${order.customerPhone}
				</td>
				<td>
					${order.communityName}-${order.buildNumber}-${order.buildUnit}-${order.buildRoom}
				</td>
				<td>
					${order.designerName}
				</td>
				<td>
					${order.cusManager}
				</td>
				<td>
					<fmt:formatDate value="${order.signContractDate}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					${order.orderInspector}
				</td>
				<td>
					<fmt:formatDate value="${order.contractStartDate}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					<fmt:formatDate value="${order.contractEndDate}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					<fmt:formatDate value="${order.getOrderDatetime}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					${order.userName}
				</td>
				<td>
					<fmt:formatDate value="${order.updateByDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
	
				<!-- <shiro:hasPermission name="order:order:edit"><td>
    				<a href="${ctx}/order/order/form?id=${order.id}">修改</a>
					<a href="${ctx}/order/order/delete?id=${order.id}" onclick="return confirmx('确认要删除该订单吗？', this.href)">删除</a>
				</td></shiro:hasPermission> -->
			</tr>
		</c:forEach>
		</tbody>
	</table>
</form:form>
	<div class="pagination">${page}</div>
</body>
</html>