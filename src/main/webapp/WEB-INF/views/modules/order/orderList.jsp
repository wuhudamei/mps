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
			var storeId = $("#sel").val();
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
								
								if('${order.engineDepartId}' == data[v].engineDepartId){
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
		<li class="active"><a href="${ctx}/order/order/list">订单列表</a></li>
		<shiro:hasPermission name="order:order:edit"><li><a href="${ctx}/order/order/form">订单添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="order" action="${ctx}/order/order/list1" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>门店：</label>
				<form:select path="storeId" class="input-medium needClear" onchange="findEngineListByStoreIdAndProjectMode()" id="sel">
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
					<%-- <form:options items="${fns:getEngineListWithUserConditions()}"   itemLabel="label" itemValue="value" htmlEscape="false"/> --%>
				</form:select>
			</li>
			
			
			<li><label>订单编号：</label>
				<form:input path="orderNumber" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>

			<li><label>订单状态：</label>
				<form:select path="orderStatusDescription" class="input-medium needClear">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('order_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>订单来源：</label>
				<form:select path="orderSource" class="input-medium needClear">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('order_source')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>客户姓名：</label>
				<form:input path="customerName" htmlEscape="false" maxlength="16" class="input-medium"/>
			</li>
			<li><label>客户手机号：</label>
				<form:input path="customerPhone" htmlEscape="false" maxlength="11" class="input-medium"/>
			</li>
			<li><label>客服经理：</label>
				<form:input path="cusManager" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>设计师：</label>
				<form:input path="designerName" htmlEscape="false" maxlength="12" class="input-medium"/>
			</li>
			<li><label>签约日期：</label>
				<input name="beginSignContractDate" id="beginSignContractDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${order.beginSignContractDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'endSignContractDate\')}',isShowClear:true});"/> 至  
				<input name="endSignContractDate" id="endSignContractDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${order.endSignContractDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'beginSignContractDate\')}',isShowClear:true});"/>
			</li>
			<li><label>合同开工日期：</label>
				<input name="beginContractStartDate" id="beginContractStartDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${order.beginContractStartDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'endContractStartDate\')}',isShowClear:true});"/> 至  
				<input name="endContractStartDate" id="endContractStartDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${order.endContractStartDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'beginContractStartDate\')}',isShowClear:true});"/>
			</li>
			
			<li><label>订单创建日期：</label>
				<input name="beginCreateDate" id="beginCreateDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${order.beginCreateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'endCreateDate\')}',isShowClear:true});"/> 至 
				<input name="endCreateDate" id="endCreateDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${order.endCreateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'beginCreateDate\')}',isShowClear:true});"/>
			</li>
			<li><label style="width: 130px;">工程部确认接单日期：</label>
				<input name="acceptOrderDate" id="acceptOrderDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${acceptOrderDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"/>
				
			</li>
			<li><label>是否作废：</label>
				<form:select path="isScrap" class="input-medium needClear">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('dict_iscountsquare')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>状态：</label>
				<form:select path="enabled" class="input-medium needClear" >
						<form:option value="1"  selected="selected">启用</form:option>
						<form:option value="0">未启用</form:option>
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
				<th>门店</th>
				<th>工程模式</th>
				<th>区域</th>
				<th>订单编号</th>
				<th>小区门牌号</th>
				<th>客户姓名</th>
				<th>客户电话</th>
				<th>客服经理</th>
				<th>设计师</th>
				<th>签约日期</th>
				<th>合同开工日期</th>
				<th>合同竣工日期</th>
				<th>订单创建时间</th>
				<th>订单状态</th>
				<th>工程部确认接单日期</th>
				<th>竣工验收日期</th>
				<th>是否作废</th>
				<th>订单来源</th>
				<th>状态</th>
				<shiro:hasPermission name="order:order:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="order" varStatus="status">
			<tr>
					<td>
							${fns:getStoreLabel(order.storeId, '')}
					</td>
				<td>${fns:getDictLabel(order.projectMode, 'project_mode', '')}</td>

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
				
					${order.buildRoom}
				<td>
					${order.customerName}
				</td>
				<td>
					${order.customerPhone}
				</td>
				<td>
					${order.cusManager}
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
					${order.orderStatusDescription}
				</td>
				<td>
				<fmt:formatDate value="${order.acceptOrderDate}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
				<fmt:formatDate value="${order.actualEndDate}" pattern="yyyy-MM-dd  HH:mm:ss"/>
				</td>
				<td>
					<c:if test="${order.isScrap eq 1}">是</c:if>
					<c:if test="${order.isScrap ne 1}">否</c:if>
				</td>
				<td>
					<c:if test="${not empty order.prepareOrderId}">系统接收</c:if>
					<c:if test="${empty order.prepareOrderId}">人工录入</c:if>
				</td>
				<td>
						<c:if test="${order.enabled==1}">
							启用
						</c:if>
						<c:if test="${order.enabled==0}">
							停止
						</c:if>
						<c:if test="${order.enabled==null}">
							启用
						</c:if>
				</td>
				
				<td>
				
					<shiro:hasPermission name="order:order:edit">
	    				<a href="${ctx}/order/order/details?id=${order.orderId}">详情</a>
	    				<c:if test="${order.orderStatusNumber<320 }">
	    					<a href="${ctx}/order/order/form?id=${order.orderId}">修改</a>
	    				</c:if>
					</shiro:hasPermission>
					
					<shiro:hasPermission name="orderexcel:bizOrderExcel:edit">
	    				<a href="${ctx}/orderexcel/bizOrderExcel/list?orderId=${order.orderId}">附件</a>
					</shiro:hasPermission>
					<shiro:hasPermission name="ordercad:orderCadfile:edit">
	    				<a href="${ctx}/ordercad/orderCadfile/list?orderId=${order.orderId}">图纸</a>
					</shiro:hasPermission>
					<shiro:hasPermission name="ordermainmate:bizOrderMainMate:view">
	    				<a href="${ctx}/ordermainmate/bizOrderMainMate/list?orderId=${order.orderId}">墙地砖</a>
					</shiro:hasPermission>
					<shiro:hasPermission name="projectOrderList:projectOrderList:edit">
	    				<a href="${ctx}/projectOrderList/materialInstallItem?orderId=${order.orderId}&storeId=${order.storeId}&projectMode=${order.projectMode}&orderNumber=${order.orderNumber}&orderStatusNumber=${order.orderStatusNumber}&flag=1">订单安装项</a>
					</shiro:hasPermission>
					
					<shiro:hasPermission name="order:order:edit1">
	    				<a href="${ctx}/order/order/formUser?id=${order.orderId}">修改客户信息</a>
					</shiro:hasPermission>
					<a href="${ctx}/order/order/enable?id=${order.orderId}">
							<c:if test="${order.enabled==1}">
								<span style="color:red;">停止回访</span>
							</c:if>
							<c:if test="${order.enabled==null}">
								<span style="color:red;">停止回访</span>
							</c:if>
					</a>
					<a href="${ctx}/order/order/able?id=${order.orderId}">
							<c:if test="${order.enabled==0}">
								<span style="color:green;">启用回访</span>
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