<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>主材催促表管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
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
		//加载区域信息
		function findEngineListByStoreIdAndProjectMode(){
			
			var storeId = $("#storeId").val();
			var projectModeValue = $("#projectMode").val();
			if (storeId=="" ||projectModeValue=="" ||undefined==storeId ||undefined==projectModeValue) {
				return;
			}
			//根据门店个,工程模式    动态加载工程区域
			var html3 = '<option value=""></option>';
			$.ajax({
					url : "${ctx}/order/order/findEngineDepartmentBystoreIdAndProjectMode?storeId="
							+ storeId + "&projectModeValue=" + projectModeValue,
					type : "get",
					success : function(data) {
						if (null!= data && data.length > 0) {

							for (var v = 0; v < data.length; v++) {
								if('${installBusinessUrge.enginDepartId}' == data[v].engineDepartId){
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
			
			var html2='<option value=""></option>';
			var html4='<option value=""></option>';
			
			$.ajax({
				url:"${ctx}/bizengininstall/bizEnginInstall/findInstallItemByStoreIdAndProjectMode?storeId="+storeId+"&projectModeId="+projectModeValue,					
				success: function(data){
					
					if(null!=data && data.length>0){
				
						for (var v = 0; v < data.length; v++) {
							
							
							if(data[v].isOn == 1){
								//启用
								if('${installBusinessUrge.projectInstallItemId}' == data[v].id){
									$("#s2id_projectInstallItemId .select2-chosen").html(data[v].installItemName);
									html2 = html2 + "<option value='"+data[v].id+"' selected='selected'>"+data[v].installItemName+"</option>";
								}else{
									html2 = html2 + "<option value='"+data[v].id+"'>"+data[v].installItemName+"</option>";
								}
							}else{
								//停用
								if('${installBusinessUrge.projectInstallItemIdStop}' == data[v].id){
									$("#s2id_projectInstallItemIdStop .select2-chosen").html(data[v].installItemName);
									html4 = html4 + "<option value='"+data[v].id+"' selected='selected'>"+data[v].installItemName+"</option>";
								}else{
									html4 = html4 + "<option value='"+data[v].id+"'>"+data[v].installItemName+"</option>";
								}
								
							}
						}
						
						$("#projectInstallItemId").html(html2);
						$("#projectInstallItemIdStop").html(html4);
					} else {
						$("#projectInstallItemId").html(html2);
						$("#projectInstallItemIdStop").html(html4);
					}
				}
			})
			
			
		}
		//根据门店和工程模式  加载区域信息
		function findEngineListByStoreIdAndProjectModeTwo(){
			
			var storeId = $("#storeId").val();
			var projectModeValue = $("#projectMode").val();
			if (storeId=="" ||projectModeValue=="" ||undefined==storeId ||undefined==projectModeValue) {
				return;
			}
			//根据门店个,工程模式    动态加载工程区域
			var html3 = '<option value=""></option>';
			$.ajax({
					url : "${ctx}/order/order/findEngineDepartmentBystoreIdAndProjectMode?storeId="
							+ storeId + "&projectModeValue=" + projectModeValue,
					type : "get",
					success : function(data) {
						if (null!= data && data.length > 0) {

							for (var v = 0; v < data.length; v++) {
								html3 = html3 + "<option value='"+data[v].engineDepartId+"'>"+data[v].engineDepartName+"</option>";
							}
							
							$("#engineDepartSelect").html(html3);
						} else {
							$("#engineDepartSelect").html(html3);
						}
					}
			});	
			
			var html2='<option value=""></option>';
			var html4='<option value=""></option>';
			
			$.ajax({
				url:"${ctx}/bizengininstall/bizEnginInstall/findInstallItemByStoreIdAndProjectMode?storeId="+storeId+"&projectModeId="+projectModeValue,					
				success: function(data){
					
					if(null!=data && data.length>0){
				
						for (var v = 0; v < data.length; v++) {
							
							
							if(data[v].isOn == 1){
								//启用
								html2 = html2 + "<option value='"+data[v].id+"'>"+data[v].installItemName+"</option>";
							}else{
								//停用
								html4 = html4 + "<option value='"+data[v].id+"'>"+data[v].installItemName+"</option>";
								
							}
						}
						
						$("#projectInstallItemId").html(html2);
						$("#projectInstallItemIdStop").html(html4);
					} else {
						$("#projectInstallItemId").html(html2);
						$("#projectInstallItemIdStop").html(html4);
					}
				}
			})
			
			
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/bizbusinessurge/bizBusinessUrge/list">主材催促查询</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="installBusinessUrge" action="${ctx}/bizbusinessurge/bizBusinessUrge/list2" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>门店：</label>
				<c:if test="${empty storeDropEnable}">
					<form:select path="storeId" class="input-medium" disabled="true" onchange="findEngineListByStoreIdAndProjectModeTwo()">
						<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
				<c:if test="${!empty storeDropEnable}">
					<form:select path="storeId" class="input-medium needClear" onchange="findEngineListByStoreIdAndProjectModeTwo()">
						<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
			</li>
			<li><label>工程模式：</label>
				<c:if test="${empty gongcheng}">
					<form:select path="projectMode" class="input-medium" disabled="true" onchange="findEngineListByStoreIdAndProjectModeTwo()">
						<form:option value="" label=""/>
						<form:options items="${fns:getDictList('project_mode')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
				<c:if test="${!empty gongcheng}">
					<form:select path="projectMode" class="input-medium needClear" onchange="findEngineListByStoreIdAndProjectModeTwo()">
						<form:option value="" label=""/>
						<form:options items="${fns:getDictList('project_mode')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
					
			</li>
			<li><label>区域：</label>
				 <form:select path="enginDepartId" class="input-medium needClear" id="engineDepartSelect" >
					<form:option value=""></form:option>
				</form:select>
			</li>
			<li><label>客户姓名：</label>
				<form:input path="customerName" htmlEscape="false" maxlength="11" class="input-medium"/>
			</li>
			<li><label>项目经理：</label>
				<form:input path="itemManagerName" htmlEscape="false" maxlength="11" class="input-medium"/>
			</li>
			<li><label style="width:140px;">安装项名称（停用）：</label>
				<form:select path="projectInstallItemIdStop" class="input-medium needClear">
				</form:select>
			</li>
			<li><label>安装项名称：</label>
				<form:select path="projectInstallItemId" class="input-medium needClear">
				</form:select>
			</li>
			<li><label>催促时间：</label>
				<input name="beginOperateDatetime" id="beginOperateDatetime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${installBusinessUrge.beginOperateDatetime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'endOperateDatetime\')}',isShowClear:true});"/> 至  
				<input name="endOperateDatetime" id="endOperateDatetime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${installBusinessUrge.endOperateDatetime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'beginOperateDatetime\')}',isShowClear:true});"/>
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
				<th>申请时间</th>
				<th>供应商确认时间</th>
				<th>催促时间</th>
				<th>工程模式</th>
				<th>区域</th>
				<th>小区</th>
				<th>客户</th>
				<th>项目经理</th>
				<th>安装项名称</th>
				<th>催促总次数</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="bizBusinessUrge">
			<tr>
				<td>
					${bizBusinessUrge.storeName}
				</td>
				<td>
					<fmt:formatDate value="${bizBusinessUrge.applyIntoDate}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					<fmt:formatDate value="${bizBusinessUrge.supplierConfirmIntoDate}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					<fmt:formatDate value="${bizBusinessUrge.operateDatetime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${bizBusinessUrge.projectModeName}
				</td>
				<td>
					${bizBusinessUrge.enginDepartName}
				</td>
				<td>
					${bizBusinessUrge.communityName}-${bizBusinessUrge.buildNumber}-${bizBusinessUrge.buildUnit}-${bizBusinessUrge.buildRoom}
				</td>
				<td>
					${bizBusinessUrge.customerName}-${bizBusinessUrge.customerPhone}
				</td>
				<td>
					${bizBusinessUrge.itemManagerName}-${bizBusinessUrge.itemManagerPhone}
				</td>
				<td>
					${bizBusinessUrge.installItemName}
				</td>
				<td>
					${bizBusinessUrge.urgeCount}
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>