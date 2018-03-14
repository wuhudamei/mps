<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>远程费提成金额设置管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctxStatic}/cusumerValidate/cusumerValidate.js"></script>
	
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			// 远程费金额验证
			jQuery.validator.addMethod("iscommissionAmount", function(value, element) {
				var reg=/^[0-9]{1,}(?:.[0-9]{0,2})?$/;
			    return this.optional(element) || (reg.test(value));
			}, "请正确填写远程费金额");
			// 中期提成比例验证
			jQuery.validator.addMethod("iscommissionRateMidway", function(value, element) {
			    var reg=/^(100|[1-9]\d|\d)$/;
			    var panduan = false;
			    if(this.optional(element) || (reg.test(value))){
			    	panduan = true;
			    	var num = parseInt(100)-parseInt(value);
					$("#commissionRateComplete").val(parseInt(num));
			    }
			    return panduan;
			}, "请正确填写中期提成比例");
			// 竣工提成比例验证
			jQuery.validator.addMethod("iscommissionRateComplete", function(value, element) {
			    var reg=/^(100|[1-9]\d|\d)$/;
			    var panduan = false;
			    if(this.optional(element) || (reg.test(value))){
			    	panduan = true;
			    	var num = parseInt(100)-parseInt(value);
					$("#commissionRateMidway").val(parseInt(num));
			    }
			    return panduan;
			}, "请正确填写竣工提成比例");
			
			$("#inputForm").validate({
				debug : false,
				rules : {
					storeId : "required",
					commissionAmount : {
						required : true,
						iscommissionAmount : true,
					},
					commissionRateMidway : {
						required : true,
						iscommissionRateMidway : true,
					},
					commissionRateComplete : {
						required : true,
						iscommissionRateComplete : true,
					},
				},
				messages : {
					storeId : "请选择门店",
					commissionAmount : {
						required : "请输入远程费金额",
						iscommissionAmount : "远程费金额格式错误",
					},
					commissionRateMidway : {
						required : "请输入中期提成比例",
						iscommissionRateMidway : "中期提成比例格式错误",
					},
					commissionRateComplete : {
						required : "请输入竣工提成比例",
						iscommissionRateComplete : "竣工提成比例格式错误",
					},
				},
				submitHandler: function(form){
					//loading('正在提交，请稍等...');
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
		<li><a href="${ctx}/inspectorsettlement/bizqclongwaycommissioncnfg/bizQcLongwayCommissionCnfg/list">远程费提成金额设置列表</a></li>
		<li class="active"><a href="${ctx}/inspectorsettlement/bizqclongwaycommissioncnfg/bizQcLongwayCommissionCnfg/form?id=${bizQcLongwayCommissionCnfg.id}">远程费提成金额设置<shiro:hasPermission name="bizqclongwaycommissioncnfg:bizQcLongwayCommissionCnfg:edit">${not empty bizQcLongwayCommissionCnfg.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="bizqclongwaycommissioncnfg:bizQcLongwayCommissionCnfg:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="bizQcLongwayCommissionCnfg" action="${ctx}/inspectorsettlement/bizqclongwaycommissioncnfg/bizQcLongwayCommissionCnfg/save" method="post" class="form-horizontal" >
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
			<label class="control-label">远程费金额：</label>
			<div class="controls">
				<form:input path="commissionAmount" htmlEscape="false" class="input-xlarge  number required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">中期提成比例：</label>
			<div class="controls">
				<form:input path="commissionRateMidway" htmlEscape="false" class="input-xlarge  digits required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">竣工提成比例：</label>
			<div class="controls">
				<form:input path="commissionRateComplete" htmlEscape="false" class="input-xlarge  digits required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="bizqclongwaycommissioncnfg:bizQcLongwayCommissionCnfg:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>