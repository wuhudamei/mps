<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>项目经理质保金设置管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
	$(document).ready(
			function() {

				var projectMode = "${bizPmGuaranteeMoneyCnfg.id}"
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
						$(
								":radio[name='projectMode'][value='" + readOnly
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

	function money() {
		var guaranteeMoneyMax = $("#guaranteeMoneyMax").val();
		var guaranteeMoneyPerOrder = $("#guaranteeMoneyPerOrder").val();

		var reg = /^[0-9]{1,}(?:.[0-9]{0,2})?$/;

		if (null != guaranteeMoneyMax && reg.test(guaranteeMoneyMax)) {
			if (null != guaranteeMoneyPerOrder
					&& reg.test(guaranteeMoneyPerOrder)) {
				return true;
			} else {
				alert("每个订单扣除额度错误！");
				$("#guaranteeMoneyPerOrder").val("");
				return false;
			}
		} else {
			alert("质保金上限定额错误！");
			$("#guaranteeMoneyMax").val("");
			return false;
		}
	}
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a
			href="${ctx}/managerSettlement/bizpmguaranteemoneycnfg/bizPmGuaranteeMoneyCnfg/list">项目经理质保金设置列表</a></li>
		<li class="active"><a
			href="${ctx}/managerSettlement/bizpmguaranteemoneycnfg/bizPmGuaranteeMoneyCnfg/form?id=${bizPmGuaranteeMoneyCnfg.id}">项目经理质保金设置<shiro:hasPermission
					name="bizpmguaranteemoneycnfg:bizPmGuaranteeMoneyCnfg:edit">${not empty bizPmGuaranteeMoneyCnfg.id?'修改':'添加'}</shiro:hasPermission>
				<shiro:lacksPermission
					name="bizpmguaranteemoneycnfg:bizPmGuaranteeMoneyCnfg:edit">查看</shiro:lacksPermission></a></li>
	</ul>
	<br />
	<form:form id="inputForm" modelAttribute="bizPmGuaranteeMoneyCnfg"
		action="${ctx}/managerSettlement/bizpmguaranteemoneycnfg/bizPmGuaranteeMoneyCnfg/save"
		method="post" class="form-horizontal" onsubmit="return money();">
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
			<label class="control-label">工程模式：</label>
			<div class="controls">
				<form:radiobuttons path="projectMode"
					items="${fns:getDictList('project_mode')}" itemLabel="label"
					itemValue="value" htmlEscape="false" class="required" />
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">质保金上限定额：</label>
			<div class="controls">
				<form:input path="guaranteeMoneyMax" htmlEscape="false"
					class="input-xlarge  number required" />
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">每个订单扣除额度：</label>
			<div class="controls">
				<form:input path="guaranteeMoneyPerOrder" htmlEscape="false"
					class="input-xlarge  number required" />
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission
				name="bizpmguaranteemoneycnfg:bizPmGuaranteeMoneyCnfg:edit">
				<input id="btnSubmit" class="btn btn-primary" type="submit"
					value="保 存" />&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回"
				onclick="history.go(-1)" />
		</div>
	</form:form>
</body>
</html>