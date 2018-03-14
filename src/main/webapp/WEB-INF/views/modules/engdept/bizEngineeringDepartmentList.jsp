<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>工程部管理管理</title>
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
		
        function cleanEmployes(){
            $('#leader').html("");
            $("#leader").parent().find('span.select2-chosen').html("");
        }
		  
		function changeStroe(storeId){
			cleanEmployes();
	        bulidEmpolyeeSelect(storeId);
	    }
	    
	    //根据选择的门店，构造任务包下拉框的选项
	    function bulidEmpolyeeSelect(storeId){
	        var empTypes = ""; 
	        var workTypes = ""; 
	        var unauth = ""; 
	        var storeIds = storeId;
	        $('#leader').attr("disabled",true);
	        $.ajax({
	            url: "${ctx}/employee/bizEmployee/employeeListByCondition",    //请求的url地址
	            dataType: "json",   //返回格式为json
	            async: true, //请求是否异步，默认为异步，这也是ajax重要特性
	            data: {
	                "empTypes":  "",
	                "workTypes": "",
	                "unauth":    "false",
	                "storeIds":  storeIds
	            },
	            type: "POST",   //请求方式
	            success: function(req) {
	                var jsonObj = eval(req);
	                var htmls = "<option value='' selected='true'></option>";
	                for (var i = 0; i < jsonObj.length; i++) {
	                    htmls+="<option value='"+jsonObj[i].value+"'>"+jsonObj[i].label+"</option>";
	                }
	                $('#leader').html(htmls);
	                $('#leader').attr("disabled",false);
	            },
	            complete: function() {},
	            error: function() {}
	        });
	    }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/engdept/bizEngineeringDepartment/">工程部管理列表</a></li>
		<shiro:hasPermission name="engdept:bizEngineeringDepartment:edit"><li><a href="${ctx}/engdept/bizEngineeringDepartment/form">工程部管理添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="bizEngineeringDepartment" action="${ctx}/engdept/bizEngineeringDepartment/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
            <li><label>门店：</label>
                <c:if test="${empty storeDropEnable}">
                    <form:select path="storeId" class="input-medium" disabled="true" onchange="changeStroe(this.options[this.selectedIndex].value)">
                        <form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
                    </form:select>
                </c:if>
                <c:if test="${!empty storeDropEnable}">
                    <form:select path="storeId" class="input-medium needClear" onchange="changeStroe(this.options[this.selectedIndex].value)">
                        <form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
                    </form:select>
                </c:if>
                <script type="text/javascript">
                    bulidEmpolyeeSelect($("#storeId").val());
                </script>
            </li>
			<li><label>工程模式：</label>
				<c:if test="${!empty projectModeEnable}">
					<form:select path="projectMode" class="input-medium" disabled="true">
						<form:options items="${fns:getDictList('project_mode')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
				<c:if test="${empty projectModeEnable}">
					<form:select path="projectMode" class="input-medium needClear">
						<form:option value="" label=""/>
						<form:options items="${fns:getDictList('project_mode')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
			</li>
			<li><label>工程部名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="255" class="input-medium needClear" id="name" name="name"/>
			</li>
			<li><label>负责人：</label>
                <form:select path="leader" id="leader" name="leader" class="input-medium" >
                    <form:option value="" label="" />
                    <form:options items="${fns:getEmployeeListByConditions('','','true')}" itemLabel="label" itemValue="value" htmlEscape="false" />
                </form:select>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>

			<li class="btns"><input class="btn btn-primary clearBtn" type="button" value="清空"/></li>
			<li class="clearfix"></li>

		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
                <th>门店名称</th>
				<th>工程模式</th>
				<th>工程部名称</th>
				<th>负责人</th>
				<th>接单员</th>
				<th>派单员</th>
				<th>预算员</th>
				<th>派工调度员</th>
				<th>材料调度员</th>
				<th>结算员</th>
				<th>质检员</th>
				<th>项目经理</th>
				<shiro:hasPermission name="engdept:bizEngineeringDepartment:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="bizEngineeringDepartment">
			<tr>
                <td>
                    ${fns:getStoreLabel(bizEngineeringDepartment.storeId, '')}
                </td>
				<td>${fns:getDictLabel(bizEngineeringDepartment.projectMode, 'project_mode', '')}</td>
				<td><a href="${ctx}/engdept/bizEngineeringDepartment/form?id=${bizEngineeringDepartment.id}">
					${bizEngineeringDepartment.name}
				</a></td>
				<td>
                    ${fns:getEmployeeLabel(bizEngineeringDepartment.leader, '')}
				</td>
				<td>
					${fns:getEmployeeNameByDepartmentId(bizEngineeringDepartment.id, '6')}
				</td>
				<td>
					${fns:getEmployeeNameByDepartmentId(bizEngineeringDepartment.id, '7')}
				</td>
				<td>
					${fns:getEmployeeNameByDepartmentId(bizEngineeringDepartment.id, '8')}
				</td>
				<td>
                    ${fns:getEmployeeNameByIds(bizEngineeringDepartment.jobDispatcher)}
				</td>
				<td>
                    ${fns:getEmployeeNameByIds(bizEngineeringDepartment.materialDispatcher)}
				</td>
				<td>
					${fns:getEmployeeNameByDepartmentId(bizEngineeringDepartment.id, '9')}
				</td>
				<td>
                    ${fns:getEmployeeNameByIds(bizEngineeringDepartment.inspector)}
				</td>
				<td>
                    ${fns:getEmployeeNameByIds(bizEngineeringDepartment.manager)}
				</td>
				<shiro:hasPermission name="engdept:bizEngineeringDepartment:edit"><td>
    				<a href="${ctx}/engdept/bizEngineeringDepartment/form?id=${bizEngineeringDepartment.id}">修改</a>
					<a href="${ctx}/engdept/bizEngineeringDepartment/delete?id=${bizEngineeringDepartment.id}" onclick="return confirmx('确认要删除该工程部管理吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>