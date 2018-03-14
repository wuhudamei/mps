<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>操作成功管理</title>
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
        function fix_amountthis(obj){

            //先把非数字的都替换掉，除了数字和.
            obj.value = obj.value.replace(/[^\d.]/g,"");
            //必须保证第一个为数字而不是.
            obj.value = obj.value.replace(/^\./g,"");
            //保证只有出现一个.而没有多个.
            obj.value = obj.value.replace(/\.{2,}/g,".");
            //保证.只出现一次，而不能出现两次以上
            obj.value = obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$",".");
            //只能输入两个小数
            obj.value = obj.value.replace(/^(\-)*(\d+)\.(\d\d).*$/,'$1$2.$3');

        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/bizmaterialsstandardshipfees/bizMaterialsStandardShippingFees/">筒灯五金配送费管理</a></li>
		<li class="active"><a href="${ctx}/bizmaterialsstandardshipfees/bizMaterialsStandardShippingFees/form?id=${bizMaterialsStandardShippingFees.id}">配送费用<shiro:hasPermission name="bizmaterialsstandardshipfees:bizMaterialsStandardShippingFees:edit">${not empty bizMaterialsStandardShippingFees.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="bizmaterialsstandardshipfees:bizMaterialsStandardShippingFees:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="bizMaterialsStandardShippingFees" action="${ctx}/bizmaterialsstandardshipfees/bizMaterialsStandardShippingFees/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label"><span class="help-inline"><font color="red">*</font> </span>门店：</label>
			<div class="controls">
				<form:select path="storeId"  class="input-xlarge required">
					<form:option value="" label="选择门店"/>
					<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><span class="help-inline"><font color="red">*</font> </span>材料：</label>
			<div class="controls">
				<form:select path="bizMaterialsStandardType" class="input-xlarge required">
					<form:option value="" label="选择材料"/>
					<form:options items="${fns:getDictList('bizMaterialsStandardType')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><span class="help-inline"><font color="red">*</font> </span>配送费用：</label>
			<div class="controls">
				<form:input path="shippingFees"
							class="input-xlarge required "  htmlEscape="false"
							onkeyup="fix_amountthis(this)" onafterpaste="fix_amountthis(this)"
							style="height:25px"
							maxlength="6"/>
			</div>
		</div>
		<br/>
		<div class="form-actions">
			<shiro:hasPermission name="bizmaterialsstandardshipfees:bizMaterialsStandardShippingFees:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>