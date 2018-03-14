<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>工人管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
			getChiefByStoreid();
			
			$("#searchForm").validate({
				rules : {
					
					freeBeginDate : {
						required:true,
						
					},
					freeEndDate : {
						required:true,
						
					},
					taskPackageId : {
						required:true,
					},
					storeId : {
						required:true,
					},
				},
				messages : {
					
					freeBeginDate:{
						required:"时间不能为空",
					},
					freeEndDate:{
						required:"时间不能为空",
					},
					taskPackageId:{
						required:"任务包不能为空",
					},
					storeId:{
						required:"门店不能为空",
					},
				},
				errorContainer: "#messageBox",
				
			});
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
		
		
		
		
		   function getChiefByStoreid(){
				$("#taskPackageId").html('');
				$("#s2id_taskPackageId .select2-chosen").html('');
				 $.ajax({
				        type : 'POST',
				        dataType : 'json',
				        url : '${ctx}/employee/bizEmployee/leader_list_byStoreId',
				        data : {
				            'storeid' : $("#storeId").val()
				        },
				        success : function(data) {
				        	if(data == null){
				        		$("#taskPackageId").html('');
				        		$("#s2id_taskPackageId .select2-chosen").html('');
				        		$("#s2id_enginDepartId .select2-chosen").html('');
				        		
				        		$("#enginDepartId").html('');
				        	}else{
					           	var taskpackage = data[1];
					           	
					        	var html1 = "<option value=''></option>";
					        	
					        	 for (var j = 0; j < taskpackage.length; j++ ){
						            	
						        	if('${bizEmployeegroupVO.taskPackageId}' == taskpackage[j].value){
										$("#s2id_taskPackageId .select2-chosen").html(taskpackage[j].label);
											html1 = html1 + "<option value='"+taskpackage[j].value+"' selected='selected'>"+taskpackage[j].label+"</option>";
											
						        	}else{
											html1 = html1 + "<option value='"+taskpackage[j].value+"'>"+taskpackage[j].label+"</option>";
											
										}
						        		
						            } 
						            
					            $("#taskPackageId").append(html1);
				        	}
				        }
				    });
				 

				$("#elactricationId").html("");
				$("#s2id_elactricationId .select2-chosen").html("");
				//根据门店个,工程模式    动态加载工程区域
				$.ajax({
	            	url:'${ctx}/engdept/bizEngineeringDepartment/departmentList',
	            	type:'post',
					dataType:'json',
					data:{
						'storeId':$('#storeId').val(),
						'projectMode':$("#projectMode").val(),
						'employeeId':$('#employeeId').val()
					},
	                success : function(data) {
	                	if(data.length<1){
							$("#elactricationId").append("<option value='' selected='selected' >请选择...</option>");
							$("#s2id_elactricationId .select2-chosen").html("请选择...");
						} else {
							var html = "<option value=''>请选择...</option>";
							$("#s2id_elactricationId .select2-chosen").html("请选择...");
							for(var i=0;i<data.length;i++){
								var sec = "";
								if('${bizEmployeegroupVO.elactricationId}' == data[i].value){
				            		$("#s2id_elactricationId .select2-chosen").html(data[i].label);
				            		html += "<option value='" + data[i].value +"' selected='selected' >" + data[i].label + "</option>"
				            	}else{
				            		html += "<option value='" + data[i].value +"'>" + data[i].label + "</option>"
				            	}
							}
			        		$("#elactricationId").append(html);
						}
	                }
	            });
				 
			}


		function clearButton(){
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
		
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/empgroup/bizEmployeegroup/findFreeLeader">工人信息</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="bizEmployeegroupVO" action="${ctx}/empgroup/bizEmployeegroup/findFreeLeader" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>门店：</label>
				<form:select path="storeId" class="input-medium needClear" id="storeId" onchange="getChiefByStoreid()" >
					<form:option value="" label="请选择..."/>
					<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li>
				<label>工程模式</label>
				<c:if test="${empty gongcheng}">
					<form:select path="projectMode" id="projectMode" class="input-medium" disabled="true" onchange="getChiefByStoreid()">
						<form:option value="" label="请选择..." />
						<form:options items="${fns:getDictList('package_project_mode')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
				<c:if test="${!empty gongcheng}">
					<form:select path="projectMode" id="projectMode" class="input-medium needClear" onchange="getChiefByStoreid()">
						<form:option value="" label="请选择..."/>
						<form:options items="${fns:getDictList('package_project_mode')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>			
			</li>
			
			<li><label>区域：</label>
				<form:select path="elactricationId"  class="input-medium needClear" >
				</form:select>
			</li>
			
			<li><label>组长姓名：</label>
				<form:input path="leaderRealName" htmlEscape="false" maxlength="255" class="input-medium needClear"/>
				
			</li>
			<li><label>可接任务包：</label>
				<form:select path="taskPackageId"  class="input-medium needClear" id="taskPackageId" >
				</form:select>
			</li>
			
			<li><label>空闲日期：</label>
				<input name="freeBeginDate" id="freeBeginDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${bizEmployeegroupVO.freeBeginDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'freeEndDate\')}',isShowClear:true});"/> 至 
				<input name="freeEndDate" id="freeEndDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${bizEmployeegroupVO.freeEndDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'freeBeginDate\')}',isShowClear:true});"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>

			<li class="btns"><input class="btn btn-primary clearBtn" type="button" value="清空" onclick="clearButton()"/></li>

		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>门店</th>
				<th>工程模式</th>
				<th>归属大区</th>
				<th>组长姓名</th>
				<th>组长手机号</th>
				<th>是否停单</th>
				<th>可接任务包</th>
				<th>现住址</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="bizEmployee">
			<tr>
				<td>
					${fns:getStoreLabel(bizEmployee.storeId, '')}
				</td>
				<td>
					${fns:getDictLabel(bizEmployee.projectMode, 'project_mode', '')}
				</td>
				<td>
					${bizEmployee.elactricationName}
				</td>
				<td>
					${bizEmployee.leaderRealName}
				</td>
				<td>
					${bizEmployee.leaderPhone}
				</td>
				<td>
                   <c:if test="${bizEmployee.orderstop=='0'}"><span style="color:#00EC00;">否</span></c:if> 
				   <c:if test="${bizEmployee.orderstop=='1'}"><span style="color:red">是</span></c:if>
				</td>
				<td>
					${packageName }
				</td>
				<td>
					${bizEmployee.address}
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>