<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>评价指标设置管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
				var projectMode  ="${bizEvalIndex.id}"
				var readOnly = "${readOnly}"
				//如果是产业、准产业或者是传统的人,  就不允许修改
				if ("1" == readOnly) {
					$(":radio[name='projectMode'][value='" + 2 + "']").attr("disabled",true);
					$(":radio[name='projectMode'][value='" + 4 + "']").attr("disabled",true);
				}
				if ("2" == readOnly) {
					$(":radio[name='projectMode'][value='" + 1 + "']").attr("disabled",true);
					$(":radio[name='projectMode'][value='" + 4 + "']").attr("disabled",true);
				}
				if("4" == readOnly){
					$(":radio[name='projectMode'][value='" + 2 + "']").attr("disabled",true);
					$(":radio[name='projectMode'][value='" + 1 + "']").attr("disabled",true);
				}
				//新增
				if ("" == projectMode) {
					//如果不是产业或者是传统的人 ,默认传统,并且可以修改
					if ("1" != readOnly && "2" != readOnly && "4" != readOnly) {
						$(":radio[name='projectMode'][value='" + 2 + "']").prop("checked", "checked");
					} else {
						//如果是产业或者是传统的人   工程模式也一样,并且不能修改
						$(":radio[name='projectMode'][value='" + readOnly + "']").prop("checked", "checked");
						if ("1" == readOnly) {
							$(":radio[name='projectMode'][value='" + 2 + "']").attr("disabled",true);
							$(":radio[name='projectMode'][value='" + 4 + "']").attr("disabled",true);
						}
						if ("2" == readOnly) {
							$(":radio[name='projectMode'][value='" + 1 + "']").attr("disabled",true);
							$(":radio[name='projectMode'][value='" + 4 + "']").attr("disabled",true);
						}
						if ("4" == readOnly) {
							$(":radio[name='projectMode'][value='" + 1 + "']").attr("disabled",true);
							$(":radio[name='projectMode'][value='" + 2 + "']").attr("disabled",true);
						}
					}
					
					$(":radio[name='isEnabled'][value='" + 0 + "']").prop("checked", "checked");
					
					//修改
				}
				
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
		<li><a href="${ctx}/evaluate/bizevalindex/bizEvalIndex/list">评价指标设置列表</a></li>
		<li class="active"><a href="${ctx}/evaluate/bizevalindex/bizEvalIndex/form?id=${bizEvalIndex.id}">评价指标设置<shiro:hasPermission name="bizevalindex:bizEvalIndex:edit">${not empty bizEvalIndex.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="bizevalindex:bizEvalIndex:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="bizEvalIndex" action="${ctx}/evaluate/bizevalindex/bizEvalIndex/save" method="post" class="form-horizontal">
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
				<form:radiobuttons path="projectMode" items="${fns:getDictList('project_mode')}" itemLabel="label" itemValue="value" htmlEscape="false" class="required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">评价指标：</label>
			<div class="controls">
				<form:input path="indexName" htmlEscape="false" maxlength="100" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">启用模式：</label>
			<div class="controls">
				<form:radiobuttons path="isEnabled" items="${fns:getDictList('biz_enable_status')}" itemLabel="label" itemValue="value" htmlEscape="false" class="required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="bizevalindex:bizEvalIndex:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>