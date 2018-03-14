<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>供应商管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
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
		
		  function onclickxiaoyan(){
				var contacts= $("#contacts").val();
				var contactsPhone = $("#contactsPhone").val();
				
		        $("#inputForm").attr("action", "${ctx}/supplier/bizSupplier/save");
		        $("#inputForm").submit();
		}

	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/supplier/bizSupplier/">供应商列表</a></li>
		<li class="active"><a href="${ctx}/supplier/bizSupplier/form?id=${bizSupplier.id}">供应商<shiro:hasPermission name="supplier:bizSupplier:edit">${not empty bizSupplier.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="supplier:bizSupplier:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="bizSupplier" action="${ctx}/supplier/bizSupplier/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">业务分类：</label>
			<div class="controls">
		<form:select onchange="delectDiv()"   path="installitemtype"    cssStyle="width:190px" id="complaintSource"    class="input-medium">
			<form:options items="${fns:getDictList('install_item_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
		</form:select>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">供应商编号：</label>
			<div class="controls">
				<form:input path="supplierNo" htmlEscape="false" maxlength="18" class="input-xlarge" placeholder="系统自动生成" disabled="true"/>
				
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">供应商名称：</label>
			<div class="controls">
				<form:input path="supplierName" htmlEscape="false" maxlength="255" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">联系人：</label>
			<div class="controls">
				<form:input id="contacts" path="contacts" htmlEscape="false" maxlength="100" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">联系电话：</label>
			<div class="controls">
				<form:input  id="contactsPhone" path="contactsPhone" htmlEscape="false" maxlength="20" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">邮箱：</label>
			<div class="controls">
				<form:input path="email" htmlEscape="false" maxlength="100" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">状态：</label>
			<div class="controls">
				<form:radiobuttons path="status" delimiter="&nbsp;" class="input-xlarge required" items="${fns:getDictList('biz_enable_status')}" itemLabel="label" itemValue="value" htmlEscape="false" />
			    <span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="supplier:bizSupplier:edit"><input id="btnSubmit" class="btn btn-primary" type="submit"   onclick="onclickxiaoyan()"  value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>