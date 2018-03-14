<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>预算金额上限添加</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
	$(document).ready(function() {
						var projectMode = "${bizTaskPackageTemplatBugetAmount.id}"
						var readOnly = "${bizTaskPackageTemplatBugetAmount.projectMode}"
						//如果是产业或者是传统的人,  就不允许修改
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
							$(":radio[name='projectMode'][value='" + 2 + "']")
									.attr("disabled", true);
							$(":radio[name='projectMode'][value='" + 1 + "']")
									.attr("disabled", true);
						}
						//新增
						if ("" == projectMode) {
							//如果不是产业或者是传统的人 ,默认传统,并且可以修改
							if ("1" != readOnly && "2" != readOnly
									&& "4" != readOnly) {
								$(
										":radio[name='projectMode'][value='"
												+ 1 + "']").prop("checked",
										"checked");
							} else {
								//如果是产业或者是传统的人   工程模式也一样,并且不能修改
								$(
										":radio[name='projectMode'][value='"
												+ readOnly + "']").prop(
										"checked", "checked");
								if ("1" == readOnly) {
									$(
											":radio[name='projectMode'][value='"
													+ 2 + "']").attr(
											"disabled", true);
									$(
											":radio[name='projectMode'][value='"
													+ 4 + "']").attr(
											"disabled", true);
								}
								if ("2" == readOnly) {
									$(
											":radio[name='projectMode'][value='"
													+ 1 + "']").attr(
											"disabled", true);
									$(
											":radio[name='projectMode'][value='"
													+ 4 + "']").attr(
											"disabled", true);
								}
								if ("4" == readOnly) {
									$(
											":radio[name='projectMode'][value='"
													+ 1 + "']").attr(
											"disabled", true);
									$(
											":radio[name='projectMode'][value='"
													+ 2 + "']").attr(
											"disabled", true);
								}
							}
						}
						if("${bizTaskPackageTemplatBugetAmount.isEnabled}" == "1"){
							$(":radio[name='isEnabled'][value='" + 1 + "']").prop(
									"checked", "checked");
						}
						
						/* if("${bizTaskPackageTemplatBugetAmount.isEnabled}" == "2"){
							$(":radio[name='isEnabled'][value='" + 2 + "']").prop(
									"checked", "checked");
						} */
						
					});
	
	function updateValue(obj){
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
	
	function submitDate(){
		var taskpackageTemplatId = $("#taskpackageTemplatId").val();
		var budgetSquareMin = parseFloat($("#budgetSquareMin").val());
		var budgetSquareMax = parseFloat($("#budgetSquareMax").val());
		if(budgetSquareMax<budgetSquareMin){
			alertx("面积结束值必须大于面积开始值");
			return;
		}
		$.ajax({
			url:'${ctx}/taskpackage/bizTaskPackageTemplat/checkDate',
			type:'post',
			dataType : 'json',
			data:{
				'taskpackageTemplatId':$('#taskpackageTemplatId').val(),
				'budgetSquareMin':$('#budgetSquareMin').val(),
				'budgetSquareMax':$('#budgetSquareMax').val(),
				'isEnabled':'1',
				'id':$("#id").val()
			},
			success:function(data){
				if(data.result == "1"){
					$("#inputForm").submit();
				}else if(data.result == "2"){
					var result="已经存在开始值或者结束值的配置项了（"+data.templatBugetAmount.budgetSquareMin+"~"+data.templatBugetAmount.budgetSquareMax+"），开始值和结算值是不允许重复的，请您修改开始值和结算值。";
					alertx(result);
				}
			}
		});
	}
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/taskpackage/bizTaskPackageTemplat/">任务包模板列表</a></li>
		<li class="active"><a
			href="${ctx}/taskpackage/bizTaskPackageTemplat/form?id=${bizTaskPackageTemplat.id}">任务包模板</a></li>
	</ul>
	<br />
	<form:form id="inputForm"
		modelAttribute="bizTaskPackageTemplatBugetAmount"
		action="${ctx}/taskpackage/bizTaskPackageTemplat/saveTemplatBugetAmount" method="post"
		class="form-horizontal">
		<form:hidden path="id" />
		<sys:message content="${message}" />


		<div class="control-group">
			<label class="control-label">门店：</label>
			<div class="controls">
				<form:select path="storeId" class="input-xlarge required"
					disabled="true">
					<form:options items="${fns:getStoreList()}" itemLabel="label"
						itemValue="value" htmlEscape="false" />
				</form:select>
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">工程模式：</label>
			<div class="controls">
				<form:radiobuttons path="projectMode"
					items="${fns:getDictList('package_project_mode')}"
					itemLabel="label" itemValue="value" htmlEscape="false"
					class="required" />
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">任务包模板名称：</label>
			<div class="controls">
			    <input type="hidden" value="${bizTaskPackageTemplatBugetAmount.taskpackageTemplatId}" name="taskpackageTemplatId"/>
				<select  id="taskpackageTemplatId" class="input-xlarge  required"
					disabled="true">
					<option
						value="${bizTaskPackageTemplatBugetAmount.taskpackageTemplatId}">
						${bizTaskPackageTemplatBugetAmount.templatName}</option>
				</select>
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">面积开始值：</label>
			<div class="controls">
				<form:input path="budgetSquareMin" htmlEscape="false" maxlength="7"
					class="input-xlarge  required" onkeyup="updateValue(this);" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">面积结束值：</label>
			<div class="controls">
				<form:input path="budgetSquareMax" htmlEscape="false" maxlength="7"
					class="input-xlarge  required" onkeyup="updateValue(this);" />
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">任务包预算上限金额：</label>
			<div class="controls">
				<form:input path="laborAuxiliaryMaterialsBudgetAmountMax"
					htmlEscape="false" maxlength="7"
					class="input-xlarge digits required" />
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
			<input id="btnSubmit" class="btn btn-primary" type="button"
				value="保 存" onclick="submitDate()"/>&nbsp; <input id="btnCancel" class="btn"
				type="button" value="返 回" onclick="history.go(-1)" />
		</div>
	</form:form>
	<div>
</body>
</html>