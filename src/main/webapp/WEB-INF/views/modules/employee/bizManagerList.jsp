<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>项目经理信息</title>
	<meta name="decorator" content="default"/>
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
		//加载区域信息
		function getDepartments(){
			$("#enginDepartId").html('');
			$.ajax({
				url:'${ctx}/engdept/bizEngineeringDepartment/departmentList',
				type:'post',
				dataType : 'json',
				data:{
					'storeId':$('#storeId').val(),
					'projectMode':$('#projectMode').val(),
					'employeeId':$('#employeeId').val()
				},
				success:function(data){
					if(data == null){
						$("#enginDepartId").html('');
					} else {
						var html = "<option value=''></option>";
						for(var i=0;i<data.length;i++){
							var sec = "";
							if('${bizEmployee.enginDepartId}' == data[i].value){
			            		sec = "selected='selected'";
			            		$("#s2id_enginDepartId .select2-chosen").html(data[i].label);
			            	}
							html += "<option value='" + data[i].value +"' " + sec + ">" + data[i].label + "</option>";
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
		<li class="active"><a href="${ctx}/employee/bizEmployee/manager_list?empType=3">项目经理信息</a></li>
		<shiro:hasPermission name="manager:bizManager:edit"><li><a href="${ctx}/employee/bizEmployee/manager_form">新增项目经理</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="bizEmployee" action="${ctx}/employee/bizEmployee/manager_list?empType=3" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<input id="employeeId" name="employeeId" type="hidden" value="${employeeId}"/>
		<ul class="ul-form">
			<li><label>门店：</label>
				<form:select path="storeid" class="input-medium needClear" id="storeId" onchange="getDepartments()">
					<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>工程模式：</label>
				<c:if test="${!empty projectModeEnable}">
					<form:select path="projectMode" class="input-medium" disabled="true" id="projectMode" onchange="getDepartments()" >
						<form:option value="" label=""/>
						<form:options items="${fns:getDictList('pre_employee_project_mode')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
				<c:if test="${empty projectModeEnable}">
					<form:select path="projectMode" class="input-medium needClear" id="projectMode" onchange="getDepartments()">
						<form:option value="" label=""/>
						<form:options items="${fns:getDictList('pre_employee_project_mode')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
			</li>
			<li><label>区域：</label>
				<form:select path="enginDepartId" class="input-medium needClear" id="enginDepartId">
					<form:option value="${bizEmployee.enginDepartId }" label="${bizEmployee.departmentName }" />
				</form:select>
			</li>
			<li><label>员工编号：</label>
				<form:input path="no" htmlEscape="false" maxlength="18" class="input-medium needClear"/>
			</li>
			<li><label>用户名：</label>
				<form:input path="loginname" htmlEscape="false" maxlength="255" class="input-medium needClear"/>
			</li>
			<li><label>真实姓名：</label>
				<form:input path="realname" htmlEscape="false" maxlength="255" class="input-medium needClear"/>
			</li>
			<li><label>手机号：</label>
				<form:input path="phone" htmlEscape="false" maxlength="11" class="input-medium needClear"/>
			</li>
			<li><label style="width:130px; margin-left:0px;">未进入任何大区：</label>
				<input type="checkbox" id="noInDepartment" name="noInDepartment" value="1" ${ noChecked }/>
			</li>
			<li><label>是否停单：</label> 
                <form:select path="orderstop" class="input-medium needClear">
					<form:option value="" label="" />
					<form:option value="0" label="否" />
					<form:option value="1" label="是" />
				</form:select>
            </li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>

			<li class="btns"><input onclick = "clearManager()" class="btn btn-primary clearBtn" type="button" value="清空"/></li>
			<script type="text/javascript">
				function clearManager(){
					$("#noInDepartment").attr("checked",false)
				}
			
			</script>
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
				<th>员工编号</th>
				<th>用户名</th>
				<th>真实姓名</th>
				<th>手机号</th>
				<th>排名</th>
				<th>星级</th>
				<th>推荐工人数</th>
				<th>是否停单</th>
				<shiro:hasPermission name="employee:bizEmployee:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="bizEmployee">
			<tr>
				<td>
					${fns:getStoreLabel(bizEmployee.storeid, '')}
				</td>
				<td>
					${fns:getDictLabel(bizEmployee.projectMode, 'pre_employee_project_mode', '')}
				</td>
				<td>
					${bizEmployee.departmentName}
				</td>
				<td><a href="${ctx}/employee/bizEmployee/manager_form?id=${bizEmployee.id}">
					${bizEmployee.no}
				</a></td>
				<td>
					${bizEmployee.loginname}
				</td>
				<td>
					${bizEmployee.realname}
				</td>
				<td>
					${bizEmployee.phone}
				</td>
				<td>
					${bizEmployee.sort}
				</td>
				<td>
                    ${fns:getDictLabel(bizEmployee.star,'emp_star','')}
				</td>
				<td>
					${bizEmployee.workerIntroduceCount}
				</td>
				<td>
                   <c:if test="${bizEmployee.orderstop==0}"><span style="color:#00EC00;">否</span></c:if> 
				   <c:if test="${bizEmployee.orderstop==1}"><span style="color:red">是</span></c:if>
				</td>
				<shiro:hasPermission name="manager:bizManager:edit"><td>
    				<a href="${ctx}/employee/bizEmployee/manager_form?id=${bizEmployee.id}">修改</a>
					<a href="${ctx}/employee/bizEmployee/manager_delete?id=${bizEmployee.id}" onclick="return confirmx('确认要删除该员工信息吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>