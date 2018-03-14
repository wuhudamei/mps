<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>工程安装问题分类设置管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
			var projectMode  ="${bizProjectInstallItemProblemType.id}"
			var readOnly = "${readOnly}"
			//如果是产业或者是传统的人,  就不允许修改
			if ("1" == readOnly) {
				$(":radio[name='projectMode'][value='" + 2 + "']").attr("disabled",true);
			}
			if ("2" == readOnly) {
				$(":radio[name='projectMode'][value='" + 1 + "']").attr("disabled",true);
			}
			//新增
			if ("" == projectMode) {
				//如果不是产业或者是传统的人 ,默认传统,并且可以修改
				if ("1" != readOnly && "2" != readOnly) {
					$(":radio[name='projectMode'][value='" + 2 + "']").prop("checked", "checked");
				} else {
					//如果是产业或者是传统的人   工程模式也一样,并且不能修改
					$(":radio[name='projectMode'][value='" + readOnly + "']").prop("checked", "checked");
					if ("1" == readOnly) {
						$(":radio[name='projectMode'][value='" + 2 + "']").attr("disabled",true);
					}
					if ("2" == readOnly) {
						$(":radio[name='projectMode'][value='" + 1 + "']").attr("disabled",true);
					}
				}
				
				$(":radio[name='isEnabled'][value='" + 1 + "']").prop("checked", "checked");
				
				//修改
			}
			//不做动作, 保证回显, 上面也保证readOnly
			
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
		
		function initNumber (){
			var s = $("#punishScore").val();
			var m = $("#punishMoney").val();
			if(s == ""){
				$("#punishScore").val(0.0);
			}
			if(m == ""){
				$("#punishMoney").val(0.0);
			}
		}
		
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/bizProjectInstallItemProblemType/bizProjectInstallItemProblemType/list">上报问题分类列表</a></li>
		<li class="active"><a href="${ctx}/bizProjectInstallItemProblemType/bizProjectInstallItemProblemType/form?id=${bizProjectInstallItemProblemType.id}">工程安装问题分类<shiro:hasPermission name="bizProjectInstallItemProblemType:bizProjectInstallItemProblemType:edit">${not empty bizProjectInstallItemProblemType.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="bizProjectInstallItemProblemType:bizProjectInstallItemProblemType:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="bizProjectInstallItemProblemType" action="${ctx}/bizProjectInstallItemProblemType/bizProjectInstallItemProblemType/save" method="post" class="form-horizontal">
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
			<label class="control-label">所属业务：</label>
			<div class="controls">
				<form:radiobuttons path="businessType" class="input-medium required" items="${fns:getDictList('problem_business_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">问题分类：</label>
			<div class="controls">
				<form:textarea path="typeName" htmlEscape="false" rows="4" maxlength="100" class="input-xxlarge required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">扣除分数：</label>
			<div class="controls">
				<form:input path="punishScore" htmlEscape="false" class="input-xlarge" onkeyup="this.value=this.value.replace(/[^0-9-]+/,'');"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">罚款金额：</label>
			<div class="controls">
				<form:input path="punishMoney" htmlEscape="false" class="input-xlarge" type="number" step="10" max="99999.99" min="0"/>元
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">处罚说明：</label>
			<div class="controls">
				<form:textarea path="punishRemarks" htmlEscape="false" rows="4" maxlength="50" class="input-xxlarge" value=""/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">状态：</label>
			<div class="controls">
				<form:radiobuttons path="isEnabled" items="${fns:getDictList('biz_enable_status')}" itemLabel="label" itemValue="value" htmlEscape="false" class="required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit" onclick = "initNumber()" value="保 存"/>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
	
	
</body>
</html>