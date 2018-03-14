<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>评价活动设置</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
	$(document).ready(function(){
		$.ajax({
			type : "POST",
			url: "${ctx}/evaluate/evaluationInterval/findEvalType",
			dataType: "json",
			data:{id:${bizEvalActivity.id}},
			success: function(data){
				 $.each(data, function(i,val){ 
					 $("#evalType option[value="+val+"]").remove();
					})
			}
		});
		
		var isEnabled = "${bizEvalActivity.isEnabled}"
		if(isEnabled == null || isEnabled == ''){
			 $(":radio[name=isEnabled][value=1]").attr("checked","checked")
		}
	});
	
	$("#inputForm").validate({
		debug : false,
		rules : {
			evalType : "required",
			interval : "required"
		},
		messages : {
			evalType : "必填项！",
			interval : "必填项！"
		}
	});
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/evaluate/evaluationInterval/list?id=${bizEvalActivity.id}&evalTargetType=${bizEvalActivity.evalTargetType}&storeId=${bizEvalActivity.storeId}&projectMode=${bizEvalActivity.projectMode}">系统评价时间列表</a></li>
		<li class="active"><a href="javascript:void(0);">系统评价时间添加</a></li>
	</ul>
	<br />
	<form:form id="inputForm" modelAttribute="bizEvalActivity"
		action="${ctx}/evaluate/evaluationInterval/save"
		method="post" class="form-horizontal">
		<form:hidden path="id" />
		<form:hidden path="storeId" />
		<form:hidden path="projectMode" />
		<form:hidden path="evalTargetType" />
		<sys:message content="${message}" />
		<div class="control-group">
			<label class="control-label">门店：</label>
			<div class="controls">
				<form:select path="storeId" class="input-xlarge required" disabled="true">
					<form:options items="${fns:getStoreList()}" itemLabel="label"
						itemValue="value" htmlEscape="false" />
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">工程模式：</label>
			<div class="controls">
				<form:radiobuttons path="projectMode" disabled="true"
					items="${fns:getDictList('project_mode')}" itemLabel="label"
					itemValue="value" htmlEscape="false" class="required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">评价对象：</label>
			<div class="controls">
				<form:select path="evalTargetType" disabled="true"
					class="input-xlarge required">
					<form:option value="" label="" />
					<form:options items="${fns:getDictList('eval_target_type')}"
						itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">评价类别：</label>
			<div class="controls">
				<form:select path="evalType" class="input-xlarge required">
					<form:option value="" label="" />
					<form:options items="${fns:getDictList('eval_role_type')}"
						itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
				<span class="help-inline"><font color="red">*</font><font color="red" id ="myevalType" style="display: none;">必填项！</font></span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">系统评价间隔时间：</label>
			<div class="controls">
				<form:input path="interval" onkeyup="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}" onafterpaste="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}" class="required" itemLabel="label" itemValue="value" htmlEscape="false"/> 小时
				<span class="help-inline"><font color="red">*</font><font color="red" id ="myinterval" style="display: none;">必填项！</font></span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">是否启用：</label>
			<div class="controls">
				<form:radiobuttons path="isEnabled"
					items="${fns:getDictList('biz_enable_status')}" itemLabel="label"
					itemValue="value" htmlEscape="false" class="required" />
			</div>
		</div>
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="button" onclick="tip()"
				value="保 存" />&nbsp;
			<input id="btnCancel" class="btn" type="button" value="返 回"
				onclick="history.go(-1)" />
		</div>
		<form:hidden path="delFlag"/>
		<form:hidden path="roleCycleId"/>
	</form:form>
	<script type="text/javascript">
		function tip(){
			var evalType = $("#evalType").val();
			var interval = $("#interval").val();
			var isEnabled = $(":radio[name=isEnabled][checked=checked]").val();
			if(evalType == null || evalType == ""){
				$("#myevalType").css("display","block")
				return;
			}
			if(interval == null || interval == ""){
				$("#myinterval").css("display","block")
				return;
			
			}
			
			$.ajax({
				type : "POST",
				url: "${ctx}/evaluate/evaluationInterval/checkExist",
				dataType: "json",
				data:{roleCycleId:'${bizEvalActivity.roleCycleId}',id:${bizEvalActivity.id},evalType:evalType,evalTargetType:${bizEvalActivity.evalTargetType},isEnabled:isEnabled,delFlag:${bizEvalActivity.delFlag}},
				success: function(data){
					if(data == '0'){
						 $("#inputForm").submit();
					}else{
						alertx("同一评价对象+评价类别只能设置一个时间，不允许重复!")
					}
				}
			});
			
			
		}
	
	</script>
</body>
</html>