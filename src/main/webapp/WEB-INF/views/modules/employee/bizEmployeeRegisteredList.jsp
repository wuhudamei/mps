<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>员工信息管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
            $("#searchForm").validate({
                errorPlacement: function(error, element) {
                    if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
                        error.appendTo(element.parent().parent());
                    } else {
                        error.insertAfter(element);
                    }
                }
            })
			
			
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/employee/bizEmployeeRegistered/list">在册人员</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="bizEmployeeRegistered" action="${ctx}/employee/bizEmployeeRegistered/exportExcel" method="post" class="breadcrumb form-search">

		<ul class="ul-form">
			<li><label>门店：</label>
			
				<form:select path="storeId" class="input-medium needClear" >
					<form:options items="${fns:getStoreList()}" itemLabel="label" selectIndex="1" itemValue="value" htmlEscape="false"/>
				</form:select>
				
			</li>
			<li><label>工程模式：</label>
				<form:select path="projectMode" class="input-medium needClear">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('project_mode')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>员工类型：</label>
				<form:select path="empType" class="input-medium required">
					<form:option value="2" label="工人"/>
					<form:option value="3" label="项目经理"/>
					<%--<form:options items="${fns:getDictList('emp_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>--%>
				</form:select>
			</li>
			<li><label>录入日期：</label>
				<input name="startEntryDate" id="startEntryDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required"
					   value="<fmt:formatDate value="${bizEmployeeRegistered.startEntryDate}" pattern="yyyy-MM-dd"/>"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'endEntryDate\')}',isShowClear:false});"/> 至
				<input name="endEntryDate" id="endEntryDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required"
					   value="<fmt:formatDate value="${bizEmployeeRegistered.endEntryDate}" pattern="yyyy-MM-dd"/>"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'startEntryDate\')}',isShowClear:false});"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="导出"/></li>
			<li class="btns"><input onclick="clearWorker()" class="btn btn-primary clearBtn" type="button" value="清空"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>

</body>
</html>