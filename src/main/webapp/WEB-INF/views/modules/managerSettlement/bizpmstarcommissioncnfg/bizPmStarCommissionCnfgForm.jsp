<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>项目经理星级和提成设置管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
			var star  ="${bizPmStarCommissionCnfg.id}"
			if ("" == star) {
				//新增
				$(":radio[name='isOldNew'][value='" + 1 + "']").prop("checked", "checked");
				$(":radio[name='isEnabled'][value='" + 1 + "']").prop("checked", "checked");
			}else{
				//修改
			}
			
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
		
		function zhongqi(a){
			/* var reg=/^(0([\.]\d*[0-9]+)|0|1)$/; */
			var reg=/^(100|[1-9]\d|\d)$/;
			if(reg.test(a)){
				var num = parseInt(100)-parseInt(a);
				$("#commissionRateComplete").val(parseInt(num));
			}else{
				$("#commissionRateMidway").val("");
				$("#commissionRateComplete").val("");
			}
		}
		function jungong(b){
			/* var reg=/^(0([\.]\d*[0-9]+)|0|1)$/; */
			var reg=/^(100|[1-9]\d|\d)$/;
			if(reg.test(b)){
				var num = parseInt(100)-parseInt(b);
				$("#commissionRateMidway").val(parseInt(num));
			}else{
				$("#commissionRateMidway").val("");
				$("#commissionRateComplete").val("");
			}
		}
		
		function managerstar(){
			var commissionRateMidway = $("#commissionRateMidway").val();
			var commissionRateComplete = $("#commissionRateComplete").val();
			var all = parseInt(commissionRateMidway)+parseInt(commissionRateComplete);
			/* var reg=/^(0([\.]\d*[0-9]+)|0|1)$/; */
			var reg=/^(100|[1-9]\d|\d)$/;
			
			if(reg.test(commissionRateMidway) && commissionRateMidway!=0){
				if(reg.test(commissionRateComplete) && commissionRateComplete!=0){
					if(all ==100){
					    return true; 
					}else{
						alert("中期成提成比例+竣工提成比例必须等于100！")
						$("#commissionRateMidway").val("");
						$("#commissionRateComplete").val("");
					}
				}else{
					alert("竣工提成比例错误！");
					$("#commissionRateComplete").val("");
					return false;
				}
			}else{
				alert("中期提成比例错误！");
				$("#commissionRateMidway").val("");
				return false;
			}
			
		}
		
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/managerSettlement/bizpmstarcommissioncnfg/bizPmStarCommissionCnfg/list">项目经理星级和提成设置列表</a></li>
		<li class="active"><a href="${ctx}/managerSettlement/bizpmstarcommissioncnfg/bizPmStarCommissionCnfg/form?id=${bizPmStarCommissionCnfg.id}">项目经理星级和提成设置<shiro:hasPermission name="bizpmstarcommissioncnfg:bizPmStarCommissionCnfg:edit">${not empty bizPmStarCommissionCnfg.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="bizpmstarcommissioncnfg:bizPmStarCommissionCnfg:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="bizPmStarCommissionCnfg" action="${ctx}/managerSettlement/bizpmstarcommissioncnfg/bizPmStarCommissionCnfg/save" method="post" class="form-horizontal" onsubmit="return managerstar();">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">门店 ：</label>
			<div class="controls">
				<form:select path="storeId" class="input-xlarge required">
					<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">新老房 ：</label>
			<div class="controls">
				<form:radiobuttons path="isOldNew" items="${fns:getDictList('biz_is_new_house')}" itemLabel="label" itemValue="value" htmlEscape="false" class="required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">星级 ：</label>
			<div class="controls">
				<form:select path="starLevel" class="input-xlarge required">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('manager_star')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">提成金额 ：</label>
			<div class="controls">
				<form:input path="commissionAmount" htmlEscape="false" class="input-xlarge  number required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">中期提成比例 ：</label>
			<div class="controls">
				<form:input path="commissionRateMidway" htmlEscape="false" class="input-xlarge  digits required" onblur="zhongqi(this.value)"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">竣工提成比例 ：</label>
			<div class="controls">
				<form:input path="commissionRateComplete" htmlEscape="false" class="input-xlarge  digits required" onblur="jungong(this.value)"/>
				<span class="help-inline"><font color="red">*</font> </span>
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
			<shiro:hasPermission name="bizpmstarcommissioncnfg:bizPmStarCommissionCnfg:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>