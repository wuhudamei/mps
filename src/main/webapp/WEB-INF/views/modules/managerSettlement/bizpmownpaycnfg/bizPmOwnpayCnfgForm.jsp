<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>自主支配项定义管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
	$(document).ready(
			function() {

				var projectMode = "${bizPmOwnpayCnfg.id}"
				var readOnly = "${readOnly}"
				//如果是产业或者是传统的人,  就不允许修改
				if ("1" == readOnly) {
					$(":radio[name='projectMode'][value='" + 2 + "']").attr(
							"disabled", true);
					$(":radio[name='projectMode'][value='" + 4 + "']").attr(
							"disabled", true);
				}
				if ("2" == readOnly) {
					$(":radio[name='projectMode'][value='" + 1 + "']").attr(
							"disabled", true);
					$(":radio[name='projectMode'][value='" + 4 + "']").attr(
							"disabled", true);
				}
				if ("4" == readOnly) {
					$(":radio[name='projectMode'][value='" + 2 + "']").attr(
							"disabled", true);
					$(":radio[name='projectMode'][value='" + 1 + "']").attr(
							"disabled", true);
				}
				//新增
				if ("" == projectMode) {
					//如果不是产业或者是传统的人 ,默认传统,并且可以修改
					if ("1" != readOnly && "2" != readOnly && "4" != readOnly) {
						$(":radio[name='projectMode'][value='" + 2 + "']")
								.prop("checked", "checked");
					} else {
						//如果是产业或者是传统的人   工程模式也一样,并且不能修改
						$(":radio[name='projectMode'][value='" + readOnly
										+ "']").prop("checked", "checked");
						if ("1" == readOnly) {
							$(":radio[name='projectMode'][value='" + 2 + "']")
									.attr("disabled", true);
							$(":radio[name='projectMode'][value='" + 4 + "']")
									.attr("disabled", true);
						}
						if ("2" == readOnly) {
							$(":radio[name='projectMode'][value='" + 1 + "']")
									.attr("disabled", true);
							$(":radio[name='projectMode'][value='" + 4 + "']")
									.attr("disabled", true);
						}
						if ("4" == readOnly) {
							$(":radio[name='projectMode'][value='" + 1 + "']")
									.attr("disabled", true);
							$(":radio[name='projectMode'][value='" + 2 + "']")
									.attr("disabled", true);
						}
					}
					$(":radio[name='isOldNew'][value='" + 1 + "']").prop(
							"checked", "checked");
					$(":radio[name='isEnabled'][value='" + 1 + "']").prop(
							"checked", "checked");
					//修改
				}

				//$("#name").focus();
				$("#inputForm")
						.validate(
								{
									submitHandler : function(form) {
										loading('正在提交，请稍等...');
										form.submit();
									},
									errorContainer : "#messageBox",
									errorPlacement : function(error, element) {
										$("#messageBox").text("输入有误，请先更正。");
										if (element.is(":checkbox")
												|| element.is(":radio")
												|| element.parent().is(
														".input-append")) {
											error.appendTo(element.parent()
													.parent());
										} else {
											error.insertAfter(element);
										}
									}
								});
			});

	function ownpay() {
		var amount = $("#amount").val();

		var reg = /^[0-9]{1,}(?:.[0-9]{0,2})?$/;

		if (null != amount && reg.test(amount)) {
			return true;
		} else {
			alert("金额错误！");
			$("#amount").val("");
			return false;
		}
	}
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a
			href="${ctx}/managerSettlement/bizpmownpaycnfg/bizPmOwnpayCnfg/list">自主支配项定义列表</a></li>
		<li class="active"><a
			href="${ctx}/managerSettlement/bizpmownpaycnfg/bizPmOwnpayCnfg/form?id=${bizPmOwnpayCnfg.id}">自主支配项定义<shiro:hasPermission
					name="bizpmownpaycnfg:bizPmOwnpayCnfg:edit">${not empty bizPmOwnpayCnfg.id?'修改':'添加'}</shiro:hasPermission>
				<shiro:lacksPermission name="bizpmownpaycnfg:bizPmOwnpayCnfg:edit">查看</shiro:lacksPermission></a></li>
	</ul>
	<br />
	<form:form id="inputForm" modelAttribute="bizPmOwnpayCnfg"
		action="${ctx}/managerSettlement/bizpmownpaycnfg/bizPmOwnpayCnfg/save"
		method="post" class="form-horizontal" onsubmit="return ownpay();">
		<form:hidden path="id" />
		<sys:message content="${message}" />
		<div class="control-group">
			<label class="control-label">门店 ：</label>
			<div class="controls">
				<form:select path="storeId" class="input-xlarge required">
					<form:options items="${fns:getStoreList()}" itemLabel="label"
						itemValue="value" htmlEscape="false" />
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">新老房：</label>
			<div class="controls">
				<form:radiobuttons path="isOldNew"
					items="${fns:getDictList('biz_is_new_house')}" itemLabel="label"
					itemValue="value" htmlEscape="false" class="required" />
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">工程模式：</label>
			<div class="controls">
				<form:radiobuttons path="projectMode"
					items="${fns:getDictList('project_mode')}" itemLabel="label"
					itemValue="value" htmlEscape="false" class="required" />
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">支配项名称：</label>
			<div class="controls">
				<form:input path="ownpayName" htmlEscape="false" maxlength="100"
					class="input-xlarge required" />
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">单位：</label>
			<div class="controls">
				<form:input path="unit" htmlEscape="false" maxlength="10"
					class="input-xlarge required" />
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">金额：</label>
			<div class="controls">
				<form:input path="amount" htmlEscape="false"
					class="input-xlarge  number required" />
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4"
					maxlength="500" class="input-xxlarge " />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">状态：</label>
			<div class="controls">
				<form:radiobuttons path="isEnabled"
					items="${fns:getDictList('biz_enable_status')}" itemLabel="label"
					itemValue="value" htmlEscape="false" class="" />
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="bizpmownpaycnfg:bizPmOwnpayCnfg:edit">
				<input id="btnSubmit" class="btn btn-primary" type="submit"
					value="保 存" />&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回"
				onclick="history.go(-1)" />
		</div>
	</form:form>
</body>
</html>