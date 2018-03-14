<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>订单投诉问题管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
            $("tbody>tr").click(function(){

          	   $(this).find('td').css('background',"orange").parents('tr').siblings().find('td').css('background',"#f5f5f5");

          	          });
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
        function clearForm1(){
			$("#storeId").val('');
			$("#projectMode").val('');
			$("#formacceptArea").val('');
			$("#status").val('');
			$("#formorderNumber").val('');
			$("#formcustomerName").val('');
			$("#formitemManager").val('');
			$("#complaintSource").val('');
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

    	function findEngineListByStoreIdAndProjectMode(){
			var html3 = '<option value=""></option>';
		var storeId = $("#storeId").val();
		var projectModeValue = $("#projectMode").val();
		if (storeId=="" ||undefined==storeId ) {
			return;
		}
		//根据门店个,工程模式    动态加载工程区域
		$.ajax({

					url : "${ctx}/order/order/findEngineDepartmentBystoreIdAndProjectMode?storeId="
							+ storeId + "&projectModeValue=" + projectModeValue,
					type : "get",
					success : function(data) {
						if (null!= data && data.length > 0) {

							for (var v = 0; v < data.length; v++) {
								html3 +='<option value="'+data[v].engineDepartId+'">'+data[v].engineDepartName+'</option>' 
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
		<li class="active"><a href="${ctx}/ordercomplan/bizOrderComplaint/listall">订单投诉问题列表</a></li>
		<shiro:hasPermission name="ordercomplan:bizOrderComplaint:edit"><li><a href="${ctx}/ordercomplan/bizOrderComplaint/form">订单投诉问题添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="bizOrderComplaint" action="${ctx}/ordercomplan/bizOrderComplaint/listall" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>门店：</label>
					<form:select  id="storeId" path="order.storeId" class="input-medium needClear" onchange="findEngineListByStoreIdAndProjectMode()">
						<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
			</li>
		
			<li><label>工程模式：</label>
			<form:select id="projectMode"   path="projectMode" class="input-medium needClear" onchange="findEngineListByStoreIdAndProjectMode()">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('project_mode')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
			</form:select>
			</li>
			
				<li><label>区域：</label>
				<form:select path="acceptArea" class="input-medium needClear" id="engineDepartSelect">
					<form:options items="${fns:getEngineListWithUserConditions()}"   itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			
			<li><label>状态：</label>
			<form:select id="status"   path="status" class="input-medium needClear">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('status_cus_comp')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
			</form:select>
			</li>
			<li><label>订单编号：</label>
				<form:input   id="formorderNumber"  path="orderNumber" htmlEscape="false" maxlength="32" class="input-medium needClear"/>
			</li>
			<li><label>客户姓名：</label>
				<form:input  id="formcustomerName" path="customerName" htmlEscape="false" maxlength="11" class="input-medium needClear"/>
			</li>
			<li><label>项目经理：</label>
				<form:input id="formitemManager"  path="itemManager" htmlEscape="false" maxlength="11" class="input-medium needClear"/>
			</li>
			
			<li><label>投诉问题来源：</label>
				<form:select id="complaintSource"   path="complaintSource" class="input-medium needClear">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('complaintSource')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
		
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
				<th>订单编号</th>
				<th>工程模式</th>
				<th>区域</th>
				<th>地址</th>
				<th>客户姓名</th>
				<th>客户手机</th>
				<th>项目经理</th>
				<th>项目经理手机</th>
				<th>问题创建时间</th>
				<th>投诉来源</th>
				<th>投诉人姓名</th>
				<th>状态</th>
				<shiro:hasPermission name="ordercomplan:bizOrderComplaint:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="bizOrderComplaint">
			<tr>
<%-- 				<td><a href="${ctx}/ordercomplan/bizOrderComplaint/form?id=${bizOrderComplaint.id}"> --%>
<%-- 					${bizOrderComplaint.orderId} --%>
<!-- 				</a></td> -->
				<td>
				${fns:getStoreLabel(bizOrderComplaint.storeId, '')}
				</td>
				<td>
					${bizOrderComplaint.orderNumber}
				</td>
				<td>
				${fns:getDictLabel(bizOrderComplaint.projectMode, 'project_mode','')}
				</td>
				<td>
				${fns:getElacLabel(bizOrderComplaint.acceptArea, '')}
				</td>
				<td>
					${bizOrderComplaint.customerAddress}
				</td>
				<td>
					${bizOrderComplaint.customerName}
				</td>
				<td>
					${bizOrderComplaint.customerPhone}
				</td>
				<td>
					${bizOrderComplaint.itemManager}
				</td>
				<td>
					${bizOrderComplaint.itemManagerIphnoe}
				</td>
				<td>
					<fmt:formatDate value="${bizOrderComplaint.comcreateDateNstring}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate>
				</td>
				
				<td>
			
				${fns:getDictLabel(bizOrderComplaint.complaintSource, 'complaintSource','')}
				</td>

				<td>
					${bizOrderComplaint.complaintPersonName}
				</td>
	
				<td>
				${fns:getDictLabel(bizOrderComplaint.status, 'status_cus_comp','')}
				</td>
			
				<td>
    				<a href="${ctx}/ordercomplan/bizOrderComplaint/formDetails?complaintId=${bizOrderComplaint.complaintId}">详情</a>
    			</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>