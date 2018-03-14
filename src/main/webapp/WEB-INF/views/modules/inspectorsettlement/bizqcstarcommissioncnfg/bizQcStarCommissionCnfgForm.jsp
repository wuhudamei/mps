<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>质检员星级和提成设置管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctxStatic}/cusumerValidate/cusumerValidate.js"></script>
	
	<script type="text/javascript">
		$(document).ready(function() {
			var id = $("#id").val();
			if("" == id){
				$(":radio[name='isEnabled'][value='" + 1 + "']").prop("checked", "checked");
			}
			//$("#name").focus();
			// 提成金额验证
			jQuery.validator.addMethod("iscommissionAmount", function(value, element) {
				var reg=/^[0-9]{1,}(?:.[0-9]{0,2})?$/;
			    return this.optional(element) || (reg.test(value));
			}, "请正确填写提成金额");
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
					isOldNew : "required",
					houseType : "required",
					starLevel : "required",
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
					isEnabled : "required",
				},
				messages : {
					storeId : "请选择门店",
					isOldNew : "请选择新老房",
					houseType : "请选择房屋类型",
					starLevel : "请选择星级",
					commissionAmount : {
						required : "请输入提成金额",
						iscommissionAmount : "提成金额格式错误",
					},
					commissionRateMidway : {
						required : "请输入中期提成比例",
						iscommissionRateMidway : "中期提成比例格式错误",
					},
					commissionRateComplete : {
						required : "请输入竣工提成比例",
						iscommissionRateComplete : "竣工提成比例格式错误",
					},
					isEnabled : "请选择是否启用",
				},
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
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/inspectorsettlement/bizqcstarcommissioncnfg/bizQcStarCommissionCnfg/list">质检员星级配置表列表</a></li>
		<li class="active"><a href="${ctx}/inspectorsettlement/bizqcstarcommissioncnfg/bizQcStarCommissionCnfg/form?id=${bizQcStarCommissionCnfg.id}">质检员星级配置表<shiro:hasPermission name="bizqcstarcommissioncnfg:bizQcStarCommissionCnfg:edit">${not empty bizQcStarCommissionCnfg.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="bizqcstarcommissioncnfg:bizQcStarCommissionCnfg:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="bizQcStarCommissionCnfg" action="${ctx}/inspectorsettlement/bizqcstarcommissioncnfg/bizQcStarCommissionCnfg/save" method="post" class="form-horizontal">
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
			<label class="control-label">新老房：</label>
			<div class="controls">
				<form:select path="isOldNew" class="input-xlarge required">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('biz_is_new_house')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">房屋类型：</label>
			<div class="controls">
				<form:select path="houseType" class="input-xlarge required">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('emp_house_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">星级：</label>
			<div class="controls">
				<form:select path="starLevel" class="input-xlarge required">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('manager_star')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">提成金额：</label>
			<div class="controls">
				<form:input path="commissionAmount" htmlEscape="false" class="input-xlarge number required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">中期提成比例：</label>
			<div class="controls">
				<form:input path="commissionRateMidway" htmlEscape="false" class="input-xlarge digits required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">竣工提成比例：</label>
			<div class="controls">
				<form:input path="commissionRateComplete" htmlEscape="false" class="input-xlarge digits required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">是否启用：</label>
			<div class="controls">
				<form:radiobuttons path="isEnabled" items="${fns:getDictList('biz_enable_status')}" itemLabel="label" itemValue="value" htmlEscape="false" class="required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="bizqcstarcommissioncnfg:bizQcStarCommissionCnfg:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>