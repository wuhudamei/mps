<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>项目经理星级和提成设置管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(function(){
			
			$(":input[name='status'][value='" + 1 + "']").prop("checked", "checked");
			
			$("#inputForm").validate({
				rules : {
					storeId : "required",
					projectMode:"required",
					starLevel : "required",
					status : "required",
					basicSalary : {
						required:true,
						number:true
					},
					effectDate : "required",
				},
				messages : {
					storeId : "请选择门店",
					projectMode : "请选择工程模式",
					starLevel : "请选择星级",
					status : "请选择是否启用",
					basicSalary:{
						required:"不能为空",
						number : "只能是数字",
					}
				},
				errorContainer: "#messageBox",
				
			});
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active">
		<shiro:hasPermission name="bizmanagerstar:bizManagerstar:view">
			
			<ul class="nav nav-tabs">
				<li class="active"><a href="${ctx}/bizstar/bizManagerStar/list">项目经理薪级列表</a></li>
			</ul>
		</shiro:hasPermission>
		</li>
	</ul>
	<br/>
	<form:form id="inputForm" modelAttribute="bizManagerStar" action="${ctx}/bizstar/bizManagerStar/bizStarBasicSalary/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">门店：</label>
			<div class="controls">
				<form:select path="storeId" class="input-xlarge required" disabled="true">
					<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">工程模式：</label>
			<div class="controls">
				<form:select path="projectMode" class="input-xlarge required" disabled="true">
					<form:options items="${fns:getDictList('project_mode')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">星级：</label>
			<div class="controls">
				<form:select path="starLevel" class="input-xlarge required" disabled="true">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('manager_star')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">是否启用：</label>
			<div class="controls">
				<input  class="required" name="status" value="1" type="radio">启用</input>
				<input  class="required" name="status" value="0" type="radio">停用</input>
				
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">对应底薪：</label>
			<div class="controls">
				<input name="basicSalary" type="text">
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">生效日期：</label>
			<div class="controls">
				<input name="effectDate" type="text" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});" maxlength="20" class="input-medium Wdate " readonly="readonly">
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