<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>安装项管理</title>
<meta name="decorator" content="default" />

</head>
<body>
	<c:set var="user" value="${fns:getUser()}"></c:set>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/installitem/bizProjectInstallItem/">安装项列表</a></li>
		<li class="active"><a
			href="${ctx}/installitem/bizProjectInstallItem/form?id=${bizProjectInstallItem.id}">安装项<shiro:hasPermission
					name="installitem:bizProjectInstallItem:edit">${not empty bizProjectInstallItem.id?'修改':'添加'}</shiro:hasPermission>
				<shiro:lacksPermission name="installitem:bizProjectInstallItem:edit">查看</shiro:lacksPermission></a></li>
	</ul>
	<br />
	<form:form id="inputForm" modelAttribute="bizProjectInstallItem"
		action="${ctx}/installitem/bizProjectInstallItem/save" method="post"
		class="form-horizontal">
		<form:hidden path="id" />
		<sys:message content="${message}" />
		<div class="control-group">
			<label class="control-label">门店：</label>
			<div class="controls">
				<%-- <sys:treeselect id="storeId" name="storeId" value="${bizProjectInstallItem.storeId}" labelName="" labelValue="${bizProjectInstallItem.storeId}"
					title="部门" url="/sys/office/treeData?type=2" cssClass="required" allowClear="true" notAllowSelectParent="true"/>
				<span class="help-inline"><font color="red">*</font> </span> --%>
				<form:select path="storeId" class="input-medium required">
					<c:if test="${user.projectMode ==3}">
					<form:option value="" label="" />
					</c:if>
					<form:options items="${fns:getStoreList()}" itemLabel="label"
						itemValue="value" htmlEscape="false" />
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
				</li>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">工程模式：</label>
			<div class="controls">
			<form:radiobuttons path="projectMode" class="input-medium required" items="${fns:getDictList('project_mode')}"
			itemLabel="label" itemValue="value" htmlEscape="false"/>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">安装项名称：</label>
			<div class="controls">
				<form:input path="installItemName" htmlEscape="false"
					maxlength="100" class="input-xlarge required" />
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">安装项顺序：</label>
			<div class="controls">
				<form:input path="installItemSequence" htmlEscape="false"
					maxlength="1500" class="input-xlarge required digits"
					onkeyup="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}"
					onafterpaste="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}" />
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">安装模式：</label>
			<div class="controls">
			<form:radiobuttons path="installMode" class="input-medium required" items="${fns:getDictList('install_mode')}"
			itemLabel="label" itemValue="value" htmlEscape="false"/>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">开工第几天申请：</label>
			<div class="controls">
				<form:input path="workApplyDay" htmlEscape="false" maxlength="256"
					class="input-xlarge required digits"
					onkeyup="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}"
					onafterpaste="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}" />
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">开工第几天进场：</label>
			<div class="controls">
				<form:input path="workEnterDay" htmlEscape="false" maxlength="256"
					class="input-xlarge required digits"
					onkeyup="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}"
					onafterpaste="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}" />
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">开工第几天完成：</label>
			<div class="controls">
				<form:input path="workCompleteDay" htmlEscape="false" 
					maxlength="256" class="input-xlarge required digits"
					onkeyup="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}"
					onafterpaste="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}" />
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">是否复尺(默认否)：</label>
			<div class="controls">
				<form:radiobuttons path="isToChecksize" class="input-xlarge required" items="${fns:getDictList('yes_no')}" onclick="checkscale(this)"
						itemLabel="label" itemValue="value" htmlEscape="false" />
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">开工第几天复尺：</label>
			<div class="controls">
				<form:input path="daysPlanChecksize" htmlEscape="false" 
					maxlength="1500" class="input-xlarge required digits"
					onkeyup="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}"
					onafterpaste="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}" />
				<span  class='help-inline'><font id = "spanred" color='white'>*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">是否展示安装说明：</label>
			<div class="controls">
				<form:radiobuttons path="isShowInstallDescription" class="input-xlarge required" items="${fns:getDictList('yes_no')}" onclick="showInstall(this)"
						itemLabel="label" itemValue="value" htmlEscape="false" />
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">安装说明描述：</label>
			<div class="controls">
				<form:textarea path="installDescription" htmlEscape="false" rows="4"
					maxlength="255" class="input-xxlarge required"/>
				<span class='help-inline'><font id="spanredTwo" color='white'>*</font> </span>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">停用或启用 (默认启用)：</label>
			<div class="controls">
				<form:radiobuttons path="isOn" class="input-xlarge required" items="${fns:getDictList('biz_enable_status')}"
						itemLabel="label" itemValue="value" htmlEscape="false" />
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注信息：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4"
					maxlength="255" class="input-xxlarge " />
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="installitem:bizProjectInstallItem:edit">
				<input id="btnSubmit" class="btn btn-primary" type="submit"
					value="保 存" />&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回"
				onclick="history.go(-1)" />
		</div>
	</form:form>
	<script type="text/javascript">
	$(document).ready(function() {
			var dpcval = $("[name='isToChecksize' ]:radio[checked='checked']").val();
			if(dpcval == '0'){
				
				$("#daysPlanChecksize").val(null);
				$("#daysPlanChecksize").css("background-color","#ccc");
				$("#spanred").css("color","white");
				$("#daysPlanChecksize").attr("disabled",true);
			}else{
				$("#daysPlanChecksize").attr("disabled",false);
				$("#daysPlanChecksize").css("background-color","#f3f3f3");
				$("#spanred").css("color","red");
			}
			var isShowInstallDescription = $("[name='isShowInstallDescription' ]:radio[checked='checked']").val();
			if(isShowInstallDescription == '0'){
				$("#installDescription").val(null);
				$("#installDescription").css("background-color","#ccc");
				$("#spanredTwo").css("color","white");
				$("#installDescription").attr("disabled",true);
			}else{
				$("#installDescription").attr("disabled",false);
				$("#installDescription").css("background-color","#f3f3f3");
				$("#spanredTwo").css("color","red");
			}
			
			var projectMode  ="${bizProjectInstallItem.id}";
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

							$(":radio[name='projectMode'][value='" + 2 + "']")
									.prop("checked", "checked");
						} else {
							//如果是产业或者是传统的人   工程模式也一样,并且不能修改
							$(":radio[name='projectMode'][value='" + readOnly
											+ "']").prop("checked", "checked");

							if ("1" == readOnly) {

								$(":radio[name='projectMode'][value='" + 2 + "']").attr("disabled",true);
								

							}
							if ("2" == readOnly) {

								$(":radio[name='projectMode'][value='" + 1 + "']").attr("disabled",true);
								

							}
						}

						//修改
					}
			//不做动作, 保证回显, 上面也保证readOnly	
				
				
			
		$(":radio[name='isOn'][value='" + 1 + "']").prop("checked", "checked");
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
	
		function checkscale(de){
			var v = $(de).val();
			if(v == '0'){
				$("#daysPlanChecksize").val(null);
				$("#daysPlanChecksize").css("background-color","#ccc");
				$("#spanred").css("color","white");
				$("#daysPlanChecksize").attr("disabled",true);
			}else{
				$("#daysPlanChecksize").attr("disabled",false);
				$("#daysPlanChecksize").css("background-color","#f3f3f3");
				$("#spanred").css("color","red");
			}
		}
		function showInstall(de){
			var v = $(de).val();
			if(v == '0'){
				$("#installDescription").val(null);
				$("#installDescription").css("background-color","#ccc");
				$("#spanredTwo").css("color","white");
				$("#installDescription").attr("disabled",true);
			}else{
				$("#installDescription").attr("disabled",false);
				$("#installDescription").css("background-color","#f3f3f3");
				$("#spanredTwo").css("color","red");
			}
		}
		
</script>
</body>

</html>