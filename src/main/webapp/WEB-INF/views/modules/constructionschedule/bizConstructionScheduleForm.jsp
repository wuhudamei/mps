<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>工程进度节点管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
	    	var radioValtemp = '${bizConstructionSchedule.projectMode}';
			var userProjectMode = '${userProjectMode}';
		/* 	$(":input[name=projectMode][value=3]").hide(); */
			/* $(":input[name=projectMode][value=2]").hide();
			$("[for=projectMode1]").hide(); */
			/* $("[for=projectMode3]").hide(); */
	    	if(userProjectMode == '3' || userProjectMode == "" ||userProjectMode == '2'){
	    		$(":input[name=projectMode][value="+userProjectMode+"]").attr("checked",true);
	    		$(":input[name=projectMode][value="+radioValtemp+"]").attr("checked",true);
	    		
	    	}else{
	    		if(radioValtemp == ''){
	    			$(":input[name=projectMode]").attr("disabled",true);
		        	$(":input[name=projectMode][value="+userProjectMode+"]").removeAttr("disabled");
		        	$(":input[name=projectMode][value="+userProjectMode+"]").attr("checked",true);
	    		}else{
	    			$(":input[name=projectMode]").attr("disabled",true);
		        	$(":input[name=projectMode][value="+radioValtemp+"]").removeAttr("disabled");
	    		}
	    	}
			
			var id  ="${bizConstructionSchedule.id}";
			
			
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/constructionschedule/bizConstructionSchedule/">工程进度节点列表</a></li>
		<li class="active"><a href="${ctx}/constructionschedule/bizConstructionSchedule/form?id=${bizConstructionSchedule.id}">工程进度节点<shiro:hasPermission name="constructionschedule:bizConstructionSchedule:edit">${not empty bizConstructionSchedule.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="constructionschedule:bizConstructionSchedule:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="bizConstructionSchedule" action="${ctx}/constructionschedule/bizConstructionSchedule/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">门店：</label>
			<div class="controls">
			<form:select path="storeId" class="input-medium required">
					<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
						<label class="control-label">工程模式：</label>
						<div class="controls">
							<form:radiobuttons path="projectMode"
								items="${fns:getDictList('project_mode')}" itemLabel="label"
								itemValue="value" htmlEscape="false" class=" required"
								 />
							<span class="help-inline"><font color="red">*</font> </span>
						</div>
					</div>
		
		<div class="control-group">
			<label class="control-label">是否老房：</label>
			<div class="controls">
				<form:radiobuttons path="isOldHouse" class="input-medium required" items="${fns:getDictList('biz_is_new_house')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
			
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">进度节点名称：</label>
			<div class="controls">
				<form:input path="constructionScheduleName" htmlEscape="false" maxlength="100" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">进度节点顺序：</label>
			<div class="controls">
				<form:input path="sort" htmlEscape="false" maxlength="2" class="input-xlarge number required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">正常完工天数：</label>
			<div class="controls">
				<form:input path="normalCompletionDays" htmlEscape="false" maxlength="2" class="input-xlarge number required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">最晚完工天数：</label>
			<div class="controls">
				<form:input path="lateCompletionDays" htmlEscape="false" maxlength="2" class=" number required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注信息：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">启用标记：</label>
			<div class="controls">
				<form:radiobuttons path="isEnable" class="required" items="${fns:getDictList('biz_enable_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
			
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="constructionschedule:bizConstructionSchedule:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>