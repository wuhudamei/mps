<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>项目约检情况查询</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			findInstallItem();
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
		
		function findInstallItem(){
			var html3 = '<option value=""></option>';
			var storeId = $("#storeId").val();
			var projectModeValue = $("#projectMode").val();
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
								if('${checkDetails.engineDepartId}' == data[v].engineDepartId){
									$("#s2id_engineDepartId .select2-chosen").html(data[v].engineDepartName);
									html3 = html3 + "<option value='"+data[v].engineDepartId+"' selected='selected'>"+data[v].engineDepartName+"</option>";
								}else{
									html3 = html3 + "<option value='"+data[v].engineDepartId+"'>"+data[v].engineDepartName+"</option>";
								}
								
							}
							
							$("#engineDepartId").html(html3);
						} else {
							$("#engineDepartId").html(html3);
						}

					}

				});	
			
			//加载约检节点
			var html2 = '<option value=""></option>';
			$.ajax({
				url : "${ctx}/pqc/checkDetails/checkDetails/checkNode?storeId="
						+ storeId + "&projectModeValue=" + projectModeValue,
				type : "get",
				success : function(data) {
					if (data.length > 0) {
						for (var v = 0; v < data.length; v++) {
							
							if('${checkDetails.checkNodeNameNewId}' == data[v].id){
								$("#s2id_checkNodeNameNewId .select2-chosen").html(data[v].qcCheckNodeName);
								html2 = html2 + "<option value='"+data[v].id+"' selected='selected'>"+data[v].qcCheckNodeName+"</option>";
							}else{
								html2 = html2 + "<option value='"+data[v].id+"'>"+data[v].qcCheckNodeName+"</option>";
							}
							
						}
						$("#checkNodeNameNewId").html(html2);
					} else {
						$("#checkNodeNameNewId").html(html2);
					}
				}
			});
		}
		
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/pqc/checkDetails/checkDetails/list">项目约检情况查询</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="checkDetails" action="${ctx}/pqc/checkDetails/checkDetails/checkDetailsList" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>门店：</label>
				<c:if test="${empty storeDropEnable}">
					<form:select path="storeId" class="input-medium" disabled="true" onchange="findInstallItem()">
						<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
				<c:if test="${!empty storeDropEnable}">
					<form:select path="storeId" class="input-medium needClear" onchange="findInstallItem()">
						<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
			</li>
			<li><label>工程模式：</label>
				<c:if test="${empty gongcheng}">
					<form:select path="projectMode" class="input-medium" disabled="true" onchange="findInstallItem()">
						<form:option value="" label=""/>
						<form:options items="${fns:getDictList('project_mode')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
				<c:if test="${!empty gongcheng}">
					<form:select path="projectMode" class="input-medium needClear" onchange="findInstallItem()">
						<form:option value="" label=""/>
						<form:options items="${fns:getDictList('project_mode')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
					
			</li>
			<li><label>区域：</label>
				<select name="engineDepartId" style="width: 170px" class="input-medium needClear"  id="engineDepartId">
					<option value="${checkDetails.engineDepartId }" selected="selected">${checkDetails.engineDepartName }</option>
				</select> 
			</li>
			<li><label style="width:150px;">最新已完成约检节点：</label>
				<select name="checkNodeNameNewId" style="width: 170px" class="input-medium needClear" id="checkNodeNameNewId">
					<option value="${checkDetails.checkNodeNameNewId }" selected="selected">${checkDetails.checkNodeNameNew }</option>
				</select> 
			</li>
			<li><label>订单编号：</label>
				<form:input path="orderNumber" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			
			<li><label>客户姓名：</label>
				<form:input path="customerName" htmlEscape="false" maxlength="16" class="input-medium"/>
			</li>
			
			<li><label>项目经理：</label>
				<form:input path="itemManager" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>实际开工日期：</label>
				<input name="beginActualStartDate" type="text" readonly="readonly" id="beginActualStartDate" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${checkDetails.beginActualStartDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'endActualStartDate\')}',isShowClear:false});"/> - 
				<input name="endActualStartDate" type="text" readonly="readonly" id="endActualStartDate" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${checkDetails.endActualStartDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'beginActualStartDate\')}',isShowClear:false});"/>
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
				<th>工程模式</th>
				<th>区域</th>
				<th>订单编号</th>
				<th>顾客信息</th>
				<th>项目经理</th>
				<th>实际开工日期</th>
				<th>最新已约检节点名称</th>
				<th>最新已完成约检节点</th>
				<th>总节点数</th>
				<th>已约节点数</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="checkNode" varStatus="status">
			<tr>
				<td>
					${fns:getStoreLabel(checkNode.storeId, '')}
				</td>
				<td>
					${fns:getDictLabel(checkNode.projectMode, 'project_mode', '')}
				</td>
				<td>
					${checkNode.engineDepartName }
				</td>
				<td>
					${checkNode.orderNumber }
				</td>
				<td>
					${checkNode.communityName }-${checkNode.buildNumber }-${checkNode.buildUnit }-${checkNode.buildRoom }-${checkNode.customerName }
				</td>
				<td>
					${checkNode.itemManager }
				</td>
				<td>
					<fmt:formatDate value="${checkNode.actualStartDate}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					${checkNode.checkNodeName }
				</td>
				<td>
					${checkNode.checkNodeNameNew }
				</td>
				<td>
					<c:if test="${checkNode.allCount!='0' }">
						${checkNode.allCount }
					</c:if>
					<c:if test="${checkNode.allCount=='0' }">
						${checkNode.allCountTwo }
					</c:if>
				</td>
				<td>
					${checkNode.nowCount }
				</td>
				<shiro:hasPermission name="checkDetails:checkDetails:view"><td>
    				<a href="${ctx}/pqc/checkDetails/checkDetails/detailsList?orderId=${checkNode.orderId}">详情</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>