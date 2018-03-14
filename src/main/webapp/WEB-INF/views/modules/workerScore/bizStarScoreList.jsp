<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>评价奖励管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
$(document).ready(function() {
	getChiefByStoreid();
	$("#projectMode option").each(function(index,element){
		var provalue = $(this).val();
		if(provalue == '2' || provalue == '3'){
			$("#projectMode option[value="+provalue+"]").remove();
		}
	});
});


function page(n, s) {
	$("#pageNo").val(n);
	$("#pageSize").val(s);
	$("#searchForm").submit();
	return false;
}

//___________________________________________________
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
					var temp = "${bizWorkerScore.elactricationId}";
					if (null!= data && data.length > 0) {
						for (var v = 0; v < data.length; v++) {
							if(data[v].engineDepartId == temp){
                                $("#s2id_elactricationId").find(".select2-chosen").text(data[v].engineDepartName);
                                html3 +='<option selected="selected" value="'+data[v].engineDepartId+'">'+data[v].engineDepartName+'</option>'
                           }else{
                                html3 +='<option value="'+data[v].engineDepartId+'">'+data[v].engineDepartName+'</option>'
                           }

						}
						$("#elactricationId").html(html3);
					} else {
						$("#elactricationId").html(html3);
					}

				}

			});		
	}

//----------------------------------------
function getChiefByStoreid()
{
	findEngineListByStoreIdAndProjectMode();
	$("#empId").html('');
	$("#taskPackageId").html('');
	
	 $.ajax({
	        type : 'POST',
	        dataType : 'json',
	        url : '${ctx}/employee/bizEmployee/leader_list_byStoreId',
	        data : {
	            'storeid' : $("#storeId").val()
	        },
	        success : function(data) {
	        	if(data == null){
	        		
	        		$("#empId").html('');
	        		$("#s2id_empId .select2-chosen").html('');
	        		$("#taskPackageId").html('');
	        		$("#s2id_taskPackageId .select2-chosen").html('');
	        		$("#elactricationId").html(''); 
	        	}else{
		           	var leader = data[0];
		           	var taskpackage = data[1];
		         
		        	var html = "<option value=''></option>";
		        	var html1 = "<option value=''></option>";
		        
		            for (var i = 0; i < leader.length; i++) {
		            	var sec = "";
		            	if('${bizWorkerScore.empId}' == leader[i].value){
		            		sec = "selected='selected'";
		            		$("#s2id_empId .select2-chosen").html(leader[i].label);

		            	}
		            	
		            	html += "<option value='" + leader[i].value +"' " + sec + ">" + leader[i].label + "</option>"
		            }
		           
		            for (var j = 0; j < taskpackage.length; j++ ){
		            	
		            	var sec = "";
		            	if('${bizWorkerScore.taskPackageId}' == taskpackage[j].value){
		            		sec = "selected='selected'";
		            		$("#s2id_taskPackageId .select2-chosen").html(taskpackage[j].label);

		            	}
		            	
		            	html1 += "<option value='" + taskpackage[j].value +"' " + sec + ">" + taskpackage[j].label + "</option>"
		            }
		           
		            $("#empId").append(html);
		            $("#taskPackageId").append(html1);
		          
	        	}
	        }
	    });

	$.ajax({
		url:'${ctx}/engdept/bizEngineeringDepartment/departmentList',
		type:'post',
		dataType : 'json',
		data:{
			'storeId':$('#storeId').val(),
			'projectMode':'1',
			'employeeId':$('#employeeId').val()
		},
		success:function(data){
			if(data == null || data == ""){
				/* $("#elactricationId").html(""); */
				$("#s2id_enginDepartId .select2-chosen").html("");
			}else{
				var content = "<option></option>";
				for(var i=0;i<data.length;i++){
					if('${bizWorkerScore.elactricationId}' == data[i].value){
						$("#s2id_elactricationId.select2-chosen").html(data[i].label);
						content = content + "<option value='"+data[i].value+"' selected='selected'>"+data[i].label+"</option>";
					}else{
						content = content + "<option value='"+data[i].value+"'>"+data[i].label+"</option>";
					}
				}
				/* $("#elactricationId").html(content); */
			}
		}
	});
}

function clearButton(){
	$("#elactricationId").html("");
	$("input[name='noInDepartment']").removeAttr("checked");
}
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/workerScore/workerScoreSelect/">工人组分数查询</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="bizWorkerScore"
		action="${ctx}/workerScore/workerScoreSelect/" method="post"
		class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}" />
		<input type="hidden" id="employeeId" name="employeeId" value="${employeeId}">
		<ul class="ul-form">
			<li><label>门店：</label> <form:select path="storeId"
					class="input-medium needClear" id="storeId"
					onChange="getChiefByStoreid()">
					<form:options items="${fns:getStoreList()}" itemLabel="label"
						itemValue="value" htmlEscape="false" />
				</form:select></li>
			<li><label>工程模式：</label> <c:if
					test="${!empty projectModeEnable}">
					<form:select path="projectMode" class="input-medium"
						disabled="true" onChange="getChiefByStoreid()">
						<form:option value="" label="" />
						<form:options items="${fns:getDictList('employee_project_mode')}"
							itemLabel="label" itemValue="value" htmlEscape="false" />
					</form:select>
				</c:if> <c:if test="${empty projectModeEnable}">
					<form:select path="projectMode" class="input-medium needClear"
						onChange="getChiefByStoreid()">
						<form:option value="" label="" />
						<form:options items="${fns:getDictList('employee_project_mode')}"
							itemLabel="label" itemValue="value" htmlEscape="false" />
					</form:select>
				</c:if></li>
			<li><label>区域：</label>
				<select id="elactricationId" name="elactricationId" value="${ElactricationId}" class="input-medium needClear">
				</select>
			</li>
			<li><label>组长姓名：</label> 
				<select id="empId" name="empId" value="${EmpId}" class="input-medium needClear">
				</select>
				</li>
			<li>
			<li><label>工种：</label> <form:select path="workType"
					class="input-medium needClear">
					<form:option value="" label="" />
					<form:options items="${fns:getDictList('emp_work_type')}"
						itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select></li>
			<li><label>平均分数：</label> <form:input path="starScore1"
					htmlEscape="false" class="input-medium" maxlength="7" />&nbsp;至&nbsp;
				<form:input path="starScore2" htmlEscape="false"
					class="input-medium" maxlength="7" /></li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary"
				type="submit" value="查询" /></li>
			<li class="btns"><input class="btn btn-primary clearBtn"
				type="button" value="清空" onclick="clearButton()" /></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}" />
	<table id="contentTable"
		class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>门店</th>
				<th>工程模式</th>
				<th>区域</th>
				<th>工种</th>
				<th>工人组长</th>
				<th>组长手机号</th>
				<th>当前星级</th>
				<th>排名</th>
				<th>平均分数</th>
				<th>任务包评价详情</th>
			</tr>
		</thead>
		<tbody>
			<c:set var="index" value="${(no-1)*30}" />
			<c:forEach items="${page.list}" var="bizEmployeegroup">

				<tr>
					<td>${bizEmployeegroup.storeName}</td>
					<td>${fns:getDictLabel(bizEmployeegroup.projectMode,'employee_project_mode', '')}</td>
					<td>${bizEmployeegroup.elactricationName}</td>
					<td>${fns:getDictLabel(bizEmployeegroup.workType, 'emp_work_type', '')}</td>
					<td>${bizEmployeegroup.leaderRealName}</td>
					<td>${bizEmployeegroup.leaderPhone}</td>
					<td>${fns:getDictLabel(bizEmployeegroup.star, 'emp_star', '')}</td>
					<td>${bb[index]}</td>
					<td>${bizEmployeegroup.starScore}</td>
					<td><a
						href="${ctx}/workerScore/workerScoreSelect/queryBizWorkerScoreDetail?empId= ${bizEmployeegroup.empId} &storeId=  ${bizEmployeegroup.storeId} &projectMode=  ${bizEmployeegroup.projectMode}">查看</a>
					</td>
				</tr>
				<c:set var="index" value="${index+1}" />
			</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>