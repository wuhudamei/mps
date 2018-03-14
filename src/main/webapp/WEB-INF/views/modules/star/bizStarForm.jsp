<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>星级承接量管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
			var projectMode  ="${bizStar.id}"
			var readOnly = "${readOnly}"
			if(projectMode== null||projectMode==''){
				
			}else{
				$(":radio[name='projectMode']").attr("disabled",true);
				$("#storeId").attr("disabled",true);
			}
			//如果是产业或者是传统的人,  就不允许修改
			if ("1" == readOnly) {
				$(":radio[name='projectMode'][value='" + 2 + "']").attr("disabled",true);
				$(":radio[name='projectMode'][value='" + 4 + "']").attr("disabled",true);
			}
			if ("2" == readOnly) {
				$(":radio[name='projectMode'][value='" + 1 + "']").attr("disabled",true);
				$(":radio[name='projectMode'][value='" + 4 + "']").attr("disabled",true);
			}
			if("4" == readOnly) {
				$(":radio[name='projectMode'][value='" + 1 + "']").attr("disabled",true);
				$(":radio[name='projectMode'][value='" + 2 + "']").attr("disabled",true);
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
					if("4" == readOnly) {
						$(":radio[name='projectMode'][value='" + 1 + "']").attr("disabled",true);
						$(":radio[name='projectMode'][value='" + 2 + "']").attr("disabled",true);
					}
				}
				//修改
			}
			//不做动作, 保证回显, 上面也保证readOnly
				
				
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					$.ajax({
						url:'${ctx}/star/bizStar/validateForm',
						type : "post",
						dataType:'json',
						data:{
							'storeId':$('#storeId').val(),
							'projectMode':$(":input[name=projectMode][type=radio][checked=checked]").val()
						},
						success : function(data) {
							if(data.flag){
								loading('正在提交，请稍等...');
								form.submit();
							}else{
								alertx("当前门店工程模式已存在，请重新选择");
							}
						}
					});
					/* loading('正在提交，请稍等...');
					form.submit(); */
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
		<li><a href="${ctx}/star/bizStar/list">星级承接量列表</a></li>
		<li class="active"><a href="${ctx}/star/bizStar/form?id=${bizStar.id}">星级承接量<shiro:hasPermission name="star:bizStar:edit">${not empty bizStar.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="star:bizStar:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="bizStar" action="${ctx}/star/bizStar/save" method="post" class="form-horizontal">
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
				<form:radiobuttons path="projectMode" class="input-medium required" items="${fns:getDictList('project_mode')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">试用：</label>
			<div class="controls">
				<form:input path="star0" htmlEscape="false" maxlength="64" class="input-xlarge required"
				onkeyup="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}" 
				onafterpaste="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		

		
		<div class="control-group">
			<label class="control-label">一星：</label>
			<div class="controls">
				<form:input path="star1" htmlEscape="false" maxlength="64" class="input-xlarge required"
				onkeyup="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}" 
				onafterpaste="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">二星：</label>
			<div class="controls">
				<form:input path="star2" htmlEscape="false" maxlength="64" class="input-xlarge required"
				onkeyup="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}" 
				onafterpaste="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">三星：</label>
			<div class="controls">
				<form:input path="star3" htmlEscape="false" maxlength="64" class="input-xlarge required"
				onkeyup="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}" 
				onafterpaste="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">四星：</label>
			<div class="controls">
				<form:input path="star4" htmlEscape="false" maxlength="64" class="input-xlarge required"
				onkeyup="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}" 
				onafterpaste="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">五星：</label>
			<div class="controls">
				<form:input path="star5" htmlEscape="false" maxlength="64" class="input-xlarge required"
				onkeyup="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}" 
				onafterpaste="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		
		<div class="form-actions">
			<shiro:hasPermission name="star:bizStar:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>