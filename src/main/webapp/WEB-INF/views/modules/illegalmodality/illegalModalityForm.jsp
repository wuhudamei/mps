<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>违规形式管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//备注默认否
			$(":radio[name='isRequiredRemarks'][value='" + 0 + "']").prop("checked", "checked")
			$(":radio[name='status'][value='" + 1 + "']").prop("checked", "checked")
			//$("#name").focus();
			
				if(($(":radio[name='projectMode']:checked").val())=="1"){
					
					$(":radio[name='projectMode'][value='" + 2 + "']").attr("disabled",true);
					$(":radio[name='projectMode'][value='" + 4 + "']").attr("disabled",true);
				}else if(($(":radio[name='projectMode']:checked").val())=="2"){
					$(":radio[name='projectMode'][value='" + 1 + "']").attr("disabled",true);
					$(":radio[name='projectMode'][value='" + 4 + "']").attr("disabled",true);
				}else{
					$(":radio[name='projectMode'][value='" + 1 + "']").attr("disabled",true);
					$(":radio[name='projectMode'][value='" + 2 + "']").attr("disabled",true);
				}
				
			
				
				
			
			
			
			
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
		
		 var selects = document.getElementsById("isRequiredRemarks2"); 
		    selects.checked= true;
		
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/illegalmodality/illegalModality/">违规形式列表</a></li>
		<li class="active"><a href="${ctx}/illegalmodality/illegalModality/form?checkItemId=${illegalModality.checkItemId}&projectMode=${modality.projectMode}">违规形式添加<shiro:lacksPermission name="illegalmodality:illegalModality:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="illegalModality" action="${ctx}/illegalmodality/illegalModality/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">门店：</label>
			<div class="controls">
				<input class="input-xlarge" name="storeName" value="${modality.storeName }" readonly="true"/>
				<input type="hidden"  name ="storeId"  value ="${modality.storeId }" /> 
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
			<label class="control-label">检查分类</label>
			<div class="controls">
				<input class="input-xlarge" name="checkKindName" value="${modality.checkKindName }" readonly="true"/>
					<input type="hidden"  name ="checkKindId"  value ="${modality.checkKindId }"  /> 
				
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">检查项</label>
			<div class="controls">
			
			<input class="input-xlarge" name="checkItemName" value="${modality.checkItemName }" readonly="true"/>
		<input type="hidden"  name ="checkItemId"  value ="${modality.checkItemId }"   /> 
				
				
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">违规形式描述</label>
			<div class="controls">
			<textarea class="input-xlarge" name="breakDescribe" >
			</textarea>
			</div>
			
		</div>
		<div class="control-group">
			<label class="control-label">是否填写备注</label>
			
			<div class="controls">
				<form:radiobuttons path="isRequiredRemarks" items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false" class="required" />
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
			
		</div>
		<div class="control-group">
			<label class="control-label">状态</label>
			<div class="controls">
				<form:radiobuttons path="status" items="${fns:getDictList('biz_enable_status')}" itemLabel="label" itemValue="value" htmlEscape="false" class="required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="illegalmodality:illegalModality:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>