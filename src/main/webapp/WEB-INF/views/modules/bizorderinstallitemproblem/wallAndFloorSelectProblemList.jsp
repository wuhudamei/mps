<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>墙地砖问题管理</title>
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
		
		
		
		
		function findEngineListByStoreIdAndProjectMode(){
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
								
								if('${bizOrderInstallItemProblemVo.engineDepartId}' == data[v].engineDepartId){
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
			
			
			$("#problemTypeId").html('');
			var businessType = "2";
			$.ajax({
				type : 'POST',
		        dataType : 'json',
		        url : '${ctx}/bizorderinstallitemproblem/bizOrderInstallItemProblem/queryInstallItemProblemTypeList',
		        data : {
		            'storeId' : $("#storeId").val(),
		            'projectMode' : $("#projectMode").val(),
		            'businessType' : businessType,
		        },
		        success : function(data){
		        	if(data == null){
		        		$("#problemTypeId").append('');
		        	}else{
		        		var html = "<option value=''></option>";
		        		for(var i=0;i<data.length;i++){
			            	
			            	if('${bizOrderInstallItemProblemVo.problemTypeId}' == data[i].value){
								$("#s2id_problemTypeId .select2-chosen").html(data[i].label);
								html = html + "<option value='"+data[i].value+"' selected='selected'>"+data[i].label+"</option>";
							}else{
								html = html + "<option value='"+data[i].value+"'>"+data[i].label+"</option>";
							}
			            	
			            	
		        		}
		        		html+="";
		        		$("#problemTypeId").append(html);
		        	}
		        }
			});
			
		}
		
		
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/bizorderinstallitemproblem/wallAndFloor/problem/preList">墙地砖问题上报查询列表</a></li>
	</ul>
		<form:form id="searchForm" modelAttribute="bizOrderInstallItemProblemVo" action="${ctx}/bizorderinstallitemproblem/wallAndFloor/problem/list" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>门店：</label>
				<c:if test="${empty storeDropEnable}">
					<form:select path="storeId" class="input-medium" disabled="true" onchange="findEngineListByStoreIdAndProjectMode()">
						<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
				<c:if test="${!empty storeDropEnable}">
					<form:select path="storeId" class="input-medium needClear" onchange="findEngineListByStoreIdAndProjectMode()">
						<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
			</li>
			<li><label>工程模式：</label>
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
			<li><label>区域：</label>
				<form:select path="engineDepartId" class="input-medium needClear" id="engineDepartSelect">
					<form:options items="${fns:getEngineListWithUserConditionsForBiddenChange()}"   itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>客户姓名：</label>
				<form:input path="customerName" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>项目经理：</label>
				<form:input path="itemManager" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>问题分类:</label>
				<form:select path="problemTypeId" class="input-medium needClear" id="problemTypeId">
					<form:option value="${bizOrderInstallItemProblemVo.problemTypeId }" label="${bizOrderInstallItemProblemVo.problemTypeName }" />
				</form:select> 
			</li>
			
			<li><label>提交时间 ：</label>
				<input name="beginCreateDate" type="text" id="beginCreateDate" readonly="readonly" maxlength="20" class="input-medium Wdate needClear"
					value="<fmt:formatDate value="${bizOrderInstallItemProblemVo.beginCreateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'endCreateDate\')}',isShowClear:false});"/> - 
				<input name="endCreateDate" type="text" id="endCreateDate" readonly="readonly" maxlength="20" class="input-medium Wdate needClear"
					value="<fmt:formatDate value="${bizOrderInstallItemProblemVo.endCreateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'beginCreateDate\')}',isShowClear:false});"/>
			</li>
			<li>
				<label>状态：</label>
				<form:select path="status" class="input-medium needClear">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('wall_and_floor_problem_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
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
				<th>状态</th>
				<th>提交时间</th>
				<th>材料部处理时间</th>
				<th>工程模式</th>
				<th>区域</th>
				<th>小区</th>
				<th>客户</th>
				<th>项目经理</th>
				<th>问题分类</th>
				<th>影响工期天数</th>
				<th>描述</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="bizOrderInstallItemProblem">
			<tr>
				<td>
					${bizOrderInstallItemProblem.storeName}
				</td>
				<td>
					${bizOrderInstallItemProblem.statusName}
				</td>
				<td>
					<fmt:formatDate value="${bizOrderInstallItemProblem.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${bizOrderInstallItemProblem.materialCreateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${bizOrderInstallItemProblem.projectModeName}
				</td>
				<td>
					${bizOrderInstallItemProblem.engineDepartName}
				</td>
				<td>
					${bizOrderInstallItemProblem.communityName}-${bizOrderInstallItemProblem.buildNumber}-${bizOrderInstallItemProblem.buildUnit}-${bizOrderInstallItemProblem.buildRoom}
				</td>
				<td>
					${bizOrderInstallItemProblem.customerName}</br>${bizOrderInstallItemProblem.customerPhone}
				</td>
				<td>
					${bizOrderInstallItemProblem.itemManager}</br>${bizOrderInstallItemProblem.itemManagerPhone}
				</td>
				<td>
					${bizOrderInstallItemProblem.problemTypeName}
				</td>
				<td>
					<c:if test="${bizOrderInstallItemProblem.isDelay == '1'}">
						${bizOrderInstallItemProblem.delayDays}天
					</c:if>
					<c:if test="${bizOrderInstallItemProblem.isDelay == '0'}">
						0天
					</c:if>
				</td>
				<td>
					${bizOrderInstallItemProblem.problemDescribe}
				</td>
				<td>
					<a href="${ctx}/bizorderinstallitemproblem/wallAndFloor/problem/details?problemId=${bizOrderInstallItemProblem.problemId}">详情</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>