<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>订单管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).keydown(function(event){

		  if(event.keyCode==13){
		      $("#btnSubmit").click();

		  }

		})
		
		$(document).ready(function() {
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
		}

		function findEngineListByStoreIdAndProjectMode(){
			var html3 = '<option value=""></option>';
			var storeId = $("#storeId").val();
			var projectModeValue = $("#projectModeId").val();
			if (storeId=="" ||projectModeValue=="" ||undefined==storeId ||undefined==projectModeValue) {
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
								
								if('${confirmStartOrder.engineDepartId}' == data[v].engineDepartId){
									$("#s2id_engineDepartSelect .select2-chosen").html(data[v].engineDepartName);
									html3 = html3 + "<option value='"+data[v].engineDepartId+"' selected='selected'>"+data[v].engineDepartName+"</option>";
								}else{
									html3 = html3 + "<option value='"+data[v].engineDepartId+"'>"+data[v].engineDepartName+"</option>";
								}
							}
							$("#engineDepartSelect").html(html3);
						} else {
							$("#engineDepartSelect").html(html3);
						}

					}

				});		
		}
	
		
		
		function formSelectSubmit(){
			
			/* var storeId = $("#sel").val();
			var orderStatusDescription = $("#orderStatusDescription").val().trim();
			
			if(storeId == ""  || storeId == undefined){
				
				alertx("请选择门店");
        		return false;
			}
			if(orderStatusDescription == ""  || orderStatusDescription == undefined){
				alertx("请选择订单状态");
        		return false;
			} */
			//提交表单
	    	$("#searchForm").submit();
		} 
		
	</script>
</head>
<body>
<c:set var="user" value="${fns:getUser()}"></c:set>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/orderStartWorkerDateSet/list">订单开工时间设置列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="confirmStartOrder" action="${ctx}/orderStartWorkerDateSet/list" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>门店：</label>
				<form:select path="storeId" class="input-medium needClear" onchange="findEngineListByStoreIdAndProjectMode()">
					<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label class="control-label">工程模式：</label>
		
			
			<c:if test="${user.projectMode ==3}">
			
			<form:select path="projectMode" class="input-medium needClear" onchange="findEngineListByStoreIdAndProjectMode()" id="projectModeId">
							<form:option value="" label="" />
					<form:options items="${fns:getDictList('project_mode')}"
						itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
			</c:if>
			<c:if test="${user.projectMode !=3}">
		
			<form:select path="projectMode" class="input-medium needClear" onchange="findEngineListByStoreIdAndProjectMode()"  id="projectModeId">
					
					<form:option label="${fns:getDictLabel(user.projectMode, 'project_mode', '')}"
					value="${user.projectMode}"/>
			</form:select>
			</c:if>
			</li>
			
			<li><label>区域：</label>
				<form:select path="engineDepartId" class="input-medium needClear" id="engineDepartSelect">
				</form:select>
			</li>
			
			
			<li><label>订单编号：</label>
				<form:input path="orderNumber" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>

			
			<li><label>客户姓名：</label>
				<form:input path="customerName" htmlEscape="false" maxlength="16" class="input-medium"/>
			</li>
			<li><label>项目经理：</label>
				<form:input path="itemManager" htmlEscape="false" maxlength="12" class="input-medium"/>
			</li>
			
			<li><label>设计师：</label>
				<form:input path="designerName" htmlEscape="false" maxlength="12" class="input-medium"/>
			</li>
			<li><label>是否确认开工：</label>
				<form:select path="isModifiedByHand" class="input-medium needClear">
					<form:option value="" label="" />
					<form:options items="${fns:getDictList('is_modified_by_hand')}" itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
			</li>
			<li class="btns"><input class="btn btn-primary" type="button" value="查询" onclick="formSelectSubmit()"/></li>
			<li class="btns"><input class="btn btn-primary clearBtn" type="button" value="清空" onclick="clearCondition()"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>序号</th>
				<th>门店</th>
				<th>工程模式</th>
				<th>区域</th>
				<th>订单编号</th>
				<th>客户信息</th>
				<th>项目经理</th>
				<th>设计师</th>
				<th>签约日期</th>
				<th>合同开工日期</th>
				<th>合同竣工日期</th>
				<th>接单日期</th>
				<th>实际开工日期</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="order" varStatus="status">
			<tr>
				<td>
					${status.index+1}
				</td>
				<td>
					${fns:getStoreLabel(order.storeId, '')}
				</td>
				<td>
					${fns:getDictLabel(order.projectMode, 'project_mode', '')}
				</td>
				<td>
					${fns:getElacLabel(order.engineDepartId, '')}
				</td>
				<td>
					${order.orderNumber}
				</td>
				<td>
					${order.communityName}
				
					${order.buildNumber}-
				
					${order.buildUnit}-
				
					${order.buildRoom}-
					
					${order.customerName}
				</td>
				<td>
					${order.itemManager}
				</td>
				<td>
					${order.designerName}
				</td>
				<td>
					<fmt:formatDate value="${order.signContractDate}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					<fmt:formatDate value="${order.contractStartDate}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					<fmt:formatDate value="${order.contractEndDate}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					<fmt:formatDate value="${order.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${order.actualStartDate}" pattern="yyyy-MM-dd  HH:mm:ss"/>
				</td>
				<td>
					<c:if test="${order.isModifiedByHand == 1}">
						<a href="${ctx}/orderStartWorkerDateSet/detail?id=${order.id}">查看详情</a>
					</c:if>
					<c:if test="${order.isModifiedByHand != 1}">
						<a href="${ctx}/orderStartWorkerDateSet/set?id=${order.id}">设置</a>
					</c:if>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
<script type="text/javascript">

</script>
</html>