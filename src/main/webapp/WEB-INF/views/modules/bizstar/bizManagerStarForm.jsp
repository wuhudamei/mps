<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>项目经理星级和提成设置管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(function(){
			var id = $("#id").val();
			if("" == id){
				$(":radio[name='status'][value='" + 1 + "']").prop("checked", "checked");
			}
			
			$("#inputForm").validate({
				rules : {
					storeId : "required",
					projectMode:"required",
					starLevel : "required",
					status : "required",
				},
				messages : {
					storeId : "请选择门店",
					projectMode : "请选择工程模式",
					starLevel : "请选择星级",
					status : "请选择是否启用",
				},
				errorContainer: "#messageBox",
				
			});
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/bizstar/bizManagerStar/list">星级配置列表</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="bizManagerStar" action="${ctx}/bizstar/bizManagerStar/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">门店：</label>
			<div class="controls">
				<form:select path="storeId" class="input-xlarge required">
					<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">工程模式：</label>
			<div class="controls">
				<form:select path="projectMode" class="input-xlarge required">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('project_mode')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">星级：</label>
			<div class="controls">
				<form:select path="starLevel" class="input-xlarge required">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('manager_star')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">是否启用：</label>
			<div class="controls">
				<form:radiobuttons path="status" items="${fns:getDictList('biz_enable_status')}" itemLabel="label" itemValue="value" htmlEscape="false" class="required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="bizmanagerstar:bizmanagerstar:edit">
				<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
			</shiro:hasPermission>
				<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>