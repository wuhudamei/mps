<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>返单信息添加</title>
<meta name="decorator" content="default" />
<script type="text/javascript"
	src="${ctxStatic}/cusumerValidate/cusumerValidate.js"></script>
<script type="text/javascript">
	//手机校验


	function checkPhone(phoneId) {
		$("#btnSubmit").attr('disabled', false);
		var phone = $("#" + phoneId).val();


        if(!isNaN(phone)&& phone.length==11){
            if(phoneId=="customerPhone"){
                $.ajax({
                    url : "${ctx}/orderReport/bizOrderReport/checkCustomerPhone?customerPhone=" + phone,
                    type : "post",
                    success : function(data) {
                        if (data == "0") {
                            $("#error").hide();
                            $("#btnSubmit").attr("disabled", false);
                        } else {
                            $("#error").show();
                            $("#btnSubmit").attr('disabled', true);
                        }
                    }
                });
            }


        }else{

            alertx('您的手机格式不正确,请重新输入');
            $("#btnSubmit").attr('disabled', true);
            return false;
        }


		
	}
	
	$(document).ready(
					function() {
						$("#inputForm").validate(
										{
											submitHandler : function(form) {
												loading('正在提交，请稍等...');
												$("#btnSubmit").attr(
														"disabled", true);
												form.submit();
												
											},
											errorContainer : "#messageBox",
											errorPlacement : function(error,
													element) {
												$("#messageBox").text(
														"输入有误，请先更正。");
												if (element.is(":checkbox")
														|| element.is(":radio")
														|| element
																.parent()
																.is(
																		".input-append")) {
													error.appendTo(element
															.parent().parent());
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
		<li><a href="${ctx}/orderReport/bizOrderReport/list">返单信息上报列表</a></li>
		<li class="active"><a
			href="${ctx}/orderReport/bizOrderReport/form">返单信息添加</a></li>
	</ul>
	<br />
	<form:form id="inputForm" modelAttribute="bizOrderReport"
		action="${ctx}/orderReport/bizOrderReport/save" method="post"
		class="form-horizontal">
		<form:hidden path="id" id="id" name="id" />

		<sys:message content="${message}" />
		<table>
			<tr>
				<td><div class="control-group">
						<label class="control-label">门店：</label>
						<div class="controls">
							<form:select path="storeId" class="input-medium required"
								id="storeId" >
								<form:option value="" label="" />
								<form:options items="${fns:getStoreList()}" itemLabel="label"
									itemValue="value" htmlEscape="false" />
							</form:select>
							<span class="help-inline"><font color="red">*</font> </span>
						</div>
					</div></td>
			</tr>

			<tr>
				<td><div class="control-group">
						<label class="control-label">客户姓名：</label>
						<div class="controls">
							<form:input path="customerName" htmlEscape="false" maxlength="15"
								class="input-medium required" id="customerName"
								name="customerName" />
							<span class="help-inline"><font color="red">*</font> </span>
						</div>
					</div></td>

				<td><div class="control-group">
						<label class="control-label">客户手机号：</label>
						<div class="controls">
							<form:input path="customerPhone" htmlEscape="false"
								maxlength="11" class="input-medium required" id="customerPhone"
								name="customerPhone" onblur="checkPhone('customerPhone')" />
							<span class="help-inline"><font color="red">*</font> </span>

						</div>
					</div></td>
			</tr>
			<tr>
				<td>
					<div class="control-group">
						<label class="control-label">小区名称：</label>
						<div class="controls">
							<form:input path="communityName" htmlEscape="false"
								maxlength="15" class="input-medium required" id="communityName"
								name="communityName" />
							<span class="help-inline"><font color="red">*</font> </span>
						</div>
					</div>
				</td>

				<td><div class="control-group">
						<label class="control-label">楼号：</label>
						<div class="controls">
							<form:input path="buildNumber" htmlEscape="false" maxlength="100"
								class="input-medium " id="buildNumber" name="buildNumber" />
						</div>
					</div></td>
			</tr>
			<tr>
				<td><div class="control-group">
						<label class="control-label">单元门：</label>
						<div class="controls">
							<form:input path="buildUnit" htmlEscape="false" maxlength="100"
								class="input-medium " id="buildUnit" name="buildUnit" />
						</div>
					</div></td>
				<td><div class="control-group">
						<label class="control-label">门牌号：</label>
						<div class="controls">
							<form:input path="buildRoom" htmlEscape="false" maxlength="100"
								class="input-medium " id="buildRoom" name="buildRoom" />
						</div>
					</div></td>
			</tr>
			<tr>
				<td><div class="control-group">
						<label class="control-label">详细地址：</label>
						<div class="controls">
							<form:input path="address" style="width:90%" htmlEscape="false"
								maxlength="30" class="input-medium " id="address" name="address" />
						</div>
					</div></td>
			</tr>
			<tr>
				<td><div class="control-group">
						<label class="control-label">返单推荐人：</label>
						<div class="controls">
							<form:input path="reporterName" htmlEscape="false" maxlength="15"
								class="input-medium required" id="reporterName"
								name="reporterName" />
							<span class="help-inline"><font color="red">*</font> </span>
						</div>
					</div></td>
				<td><div class="control-group">
						<label class="control-label">返单推荐人手机号：</label>
						<div class="controls">
							<form:input path="reporterPhone" htmlEscape="false"
								maxlength="11" class="input-medium required" id="reporterPhone"
								name="reporterPhone" onblur="checkPhone('reporterPhone')" />
							<span class="help-inline"><font color="red">*</font> </span>
						</div>
					</div></td>
			</tr>
			<tr>
				<td><div class="control-group">
						<label class="control-label">返单上报日期：</label>
						<div class="controls">
							<input name="reportDatetime" type="text" readonly="readonly"
								maxlength="20" id="reportDatetime"
								class="input-medium Wdate required"
								value="<fmt:formatDate value="${nowDate}" pattern="yyyy-MM-dd"/>"
								onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});" />
							<span class="help-inline"><font color="red">*</font> </span>
						</div>
					</div></td>
				<td><div class="control-group">
						<label class="control-label">返单推荐人类型：</label>
						<div class="controls">
							<form:select path="reporterType" class="input-medium required">
								<form:option value="" label="" />
								<form:options items="${fns:getDictList('reporterType')}"
									itemLabel="label" itemValue="value" htmlEscape="false" />
							</form:select>
							<span class="help-inline"><font color="red">*</font> </span>
						</div>
					</div></td>
			</tr>

			<tr>
				<td>
					<div class="control-group">
						<label class="control-label">上报备注：</label>
						<div class="controls">
							<form:textarea path="reportRemarks" htmlEscape="false" rows="4"
								maxlength="30" class="input-xxlarge " id="reportRemarks"
								name="reportRemarks" />
						</div>
					</div>
				</td>

				<td><div class="control-group">
					<label class="control-label">客户本人或家人是否曾在美得你咨询过：</label>
					<div class="controls">
						<form:radiobuttons path="isAsked"
										   items="${fns:getDictList('yes_no')}" itemLabel="label"
										   itemValue="value" htmlEscape="false" class=" required" />
						<span class="help-inline"><font color="red">*</font> </span>
					</div>
				</div></td>


			</tr>





		</table>
		<div class="form-actions">
			<shiro:hasPermission name="orderReport:orderReport:edit">
				<input id="btnSubmit" class="btn btn-primary" type="submit"
					value="保 存" />&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回"
				onclick="history.go(-1)" />
		</div>
	</form:form>
</body>
</html>