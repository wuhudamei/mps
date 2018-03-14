<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>工人管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }

		function clearButton(){
			$("input[name='noRelateCard']").removeAttr("checked");
			$("input[name='noRelateGroup']").removeAttr("checked");
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/employee/bizEmployee/worker_list?empType=2">工人信息</a></li>
		<shiro:hasPermission name="manager:bizManager:edit"><li><a href="${ctx}/employee/bizEmployee/worker_form">新增工人</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="bizEmployee" action="${ctx}/employee/bizEmployee/worker_list?empType=2" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>门店：</label>
				<form:select path="storeid" class="input-medium needClear">
					<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>工程模式：</label>
				<c:if test="${!empty projectModeEnable}">
					<form:select path="projectMode" class="input-medium" disabled="true" >
						<form:option value="" label=""/>
						<form:options items="${fns:getDictList('pre_employee_project_mode')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
				<c:if test="${empty projectModeEnable}">
					<form:select path="projectMode" class="input-medium needClear">
						<form:option value="" label=""/>
						<form:options items="${fns:getDictList('pre_employee_project_mode')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
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
			<li><label>工种：</label>
				<form:select path="worktype" class="input-medium needClear">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('emp_work_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>是否停单：</label> 
                <form:select path="orderstop" class="input-medium needClear">
					<form:option value="" label="" />
					<form:option value="0" label="否" />
					<form:option value="1" label="是" />
				</form:select>
            </li>
			<li><label><input type="checkbox" name="noRelateCard" value="1" <c:if test="${bizEmployee.noRelateCard eq 1}">checked="checked" </c:if>/></label>
				未添加关联身份证
			</li>
			<li><label><input type="checkbox" name="noRelateGroup" value="1" <c:if test="${bizEmployee.noRelateGroup eq 1}">checked="checked" </c:if>/></label>
				未分任何工人组
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>

			<li class="btns"><input class="btn btn-primary clearBtn" type="button" value="清空" onclick="clearButton()"/></li>

			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>门店</th>
				<th>工程模式</th>
				<th>员工编号</th>
				<th>用户名</th>
				<th>真实姓名</th>
				<th>手机号</th>
				<th>星级</th>
				<th>工种</th>
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
				<td><a href="${ctx}/employee/bizEmployee/worker_form?id=${bizEmployee.id}">
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
					${fns:getDictLabel(bizEmployee.star, 'emp_star', '')}
				</td>
				<td>
                    ${fns:getDictLabel(bizEmployee.worktype, 'emp_work_type', '')}
				</td>
				<td>
                   <c:if test="${bizEmployee.orderstop=='0'}"><span style="color:#00EC00;">否</span></c:if> 
				   <c:if test="${bizEmployee.orderstop=='1'}"><span style="color:red">是</span></c:if>
				</td>
				<shiro:hasPermission name="manager:bizManager:edit"><td>
    				<a href="${ctx}/employee/bizEmployee/worker_form?id=${bizEmployee.id}">修改</a>
					<a href="${ctx}/employee/bizEmployee/worker_delete?id=${bizEmployee.id}" onclick="return confirmx('确认要删除该员工信息吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>