<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>工程部管理管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
	$(document).ready(
			function() {
				//隐藏全部选项
				$("#projectMode1[type = radio]").hide();
				$("[for = projectMode1]").hide();
				//工程模式控制
				var radioValtemp = '${bizEngineeringDepartment.projectMode}';
				var userProjectMode = '${userProjectMode}';
				$(":input[name=projectMode][value=3]").hide();
				/* $(":input[name=projectMode][value=2]").hide();
				$("[for=projectMode1]").hide(); */
				/* $("[for=projectMode3]").hide(); */
		    	if(userProjectMode == '3' || userProjectMode == "" ||userProjectMode == '2'){
		    		$(":input[name=projectMode][value="+userProjectMode+"]").attr("checked",true);
		    		$(":input[name=projectMode][value="+radioValtemp+"]").attr("checked",true);
		    		
		    	}else{
		    		if(radioValtemp == ''){
		    			$(":input[name=projectMode]").attr("disabled",true);
			        	$(":input[name=projectMode][value="+userProjectMode+"]").removeAttr("disabled");
			        	$(":input[name=projectMode][value="+userProjectMode+"]").attr("checked",true);
		    		}else{
		    			$(":input[name=projectMode]").attr("disabled",true);
			        	$(":input[name=projectMode][value="+radioValtemp+"]").removeAttr("disabled");
		    		}
		    	}
				
				bulidEmpolyeeSelect('${bizEngineeringDepartment.storeId}');
				$("#inputForm")
						.validate(
								{
									rules : {
										storeId : "required",
										name : "required",
										leader : "required",
									},
									messages : {
										storeId : "请选择门店",
										name : "工程部不能为空",
										leader : "负责人不能为空",
									},
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

	var employeeIdMap = new Object();
	employeeIdMap.jobDispatcherSel = new Object();
	employeeIdMap.managerSel = new Object();
	employeeIdMap.materialDispatcherSel = new Object();
	employeeIdMap.inspectorSel = new Object();
	employeeIdMap.orderDispatcherSel = new Object();
	employeeIdMap.settlementClerkSel = new Object();
	employeeIdMap.receptionistSel = new Object();
	employeeIdMap.budgeteerSel = new Object();
	
	
	function onAddEmployee(type) {
		var typeSel = type + "Sel";
		var text = $("#" + typeSel + " option:selected").text();
		var value = $("#" + typeSel).val();
		if (!type || !value) {
			alert("请选择人员");
			return;
		}
	    //alert("type:" + type + ",value:" + value + ",text:" + text);
		if (employeeIdMap[typeSel] && employeeIdMap[typeSel][value]) {
			alert("已经选择了该人员");
			return;
		}
		//alert("employeeIdMap[type]:" + employeeIdMap[type]);
		var html = ""
				+ "<td width='10%' id='"
				+ typeSel
				+ "Td"
				+ value
				+ "'>"
				+ text
				+ "    <input id='" + type + "' name='" + type + "' type='hidden' value='" + value + "' />"
				+ "    &nbsp;&nbsp;<a onclick='removeEmployee(\"" + type
				+ "\",\"" + value + "\")'>删除</a>" + "</td>";
		$("#" + typeSel + "Tr").append(html);
		employeeIdMap[typeSel][value] = "0";
	}

	function removeEmployee(type, value) {
		var typeSel = type + "Sel";
		$("#" + typeSel + "Td" + value).remove();
		employeeIdMap[typeSel][value] = undefined;
	}
    
	function cleanEmployes(){
        $('#jobDispatcherSel').html("");
        $('#materialDispatcherSel').html("");  
        $('#leader').html("");       
        $('#inspectorSel').html("");
        $('#managerSel').html("");
        $('#orderDispatcherSel').html("");
        $('#settlementClerkSel').html("");
        $('#receptionistSel').html("");
        $('#budgeteerSel').html("");
        
        $('#jobDispatcherSelTr').html("");
        $('#materialDispatcherSelTr').html("");  
        $('#inspectorSelTr').html("");
        $('#managerSelTr').html("");
        $('#orderDispatcherSelTr').html("");
        $('#settlementClerkSelTr').html("");
        $('#receptionistSelTr').html("");
        $('#budgeteerSelTr').html("");
        
        $("#managerSel").parent().find('span.select2-chosen').html("");
        $("#jobDispatcherSel").parent().find('span.select2-chosen').html("");
        $("#materialDispatcherSel").parent().find('span.select2-chosen').html("");
        $("#leader").parent().find('span.select2-chosen').html("");
        $("#inspectorSel").parent().find('span.select2-chosen').html("");
        $("#orderDispatcherSel").parent().find('span.select2-chosen').html("");
        $("#settlementClerkSel").parent().find('span.select2-chosen').html("");
        $("#receptionistSel").parent().find('span.select2-chosen').html("");
        $("#budgeteerSel").parent().find('span.select2-chosen').html("");
	}
	
    function changeStroe(storeId){
    	cleanEmployes();
        bulidEmpolyeeSelect(storeId);
        projectModeChange();
    }
	
	//根据选择的门店，构造任务包下拉框的选项
    function bulidEmpolyeeSelect(storeId){
        var empTypes = ""; 
        var workTypes = ""; 
        var unauth = ""; 
        var storeIds = storeId;
        var leaderId=$('#leader').val();
        //项目经理
        $.ajax({
            url: "${ctx}/employee/bizEmployee/employeeByCondition",    //请求的url地址
            dataType: "json",   //返回格式为json
            async: true, //请求是否异步，默认为异步，这也是ajax重要特性
            data: {
                "empType":  "3",
                "projectMode": $("input[name=projectMode]:checked").val(),
                "storeId":  storeIds
            },
            type: "POST",   //请求方式
            success: function(req) {
				if(req != undefined && req != null && req != ""){
					var jsonObj = eval(req);
					var htmls = "<option></option>";
					for (var i = 0; i < jsonObj.length; i++) {
						htmls+="<option value='"+jsonObj[i].value+"'>"+jsonObj[i].label+"</option>";
					}
					$('#managerSel').html(htmls);
					if(typeof(leaderId)!= "undefined"&&leaderId!=""){
						$('#leader').val(leaderId);
					}
				}
            },
            complete: function() {},
            error: function() {}
        });
    	
    	
        //材料\派工\負責人
        $.ajax({
            url: "${ctx}/employee/bizEmployee/employeeListByCondition",    //请求的url地址
            dataType: "json",   //返回格式为json
            async: true, //请求是否异步，默认为异步，这也是ajax重要特性
            data: {
                "empTypes":  "99",
                "workTypes": "",
                "unauth":    "false",
                "storeIds":  storeIds
            },
            type: "POST",   //请求方式
            success: function(req) {
                var jsonObj = eval(req);
                var htmls = "<option value='' selected='true'></option>";
                for (var i = 0; i < jsonObj.length; i++) {
                    htmls+="<option value='"+jsonObj[i].value+"'>"+jsonObj[i].label+"</option>";
                }
                $('#jobDispatcherSel').html(htmls);
                $('#materialDispatcherSel').html(htmls);  
                $('#leader').html(htmls);  
                $('#orderDispatcherSel').html(htmls);  
                $('#settlementClerkSel').html(htmls);
                $('#receptionistSel').html(htmls);
                $('#budgeteerSel').html(htmls);
            },
            complete: function() {},
            error: function() {}
        });

        //质检员
        $.ajax({
            url: "${ctx}/employee/bizEmployee/employeeByCondition",    //请求的url地址
            dataType: "json",   //返回格式为json
            async: true, //请求是否异步，默认为异步，这也是ajax重要特性
            data: {
				"empType":  "1",
				"projectMode": $("input[name=projectMode]:checked").val(),
				"storeId":  storeIds
            },
            type: "POST",   //请求方式
            success: function(req) {
				if(req != undefined && req != null && req != ""){
					var jsonObj = eval(req);
					var htmls = "<option></option>";
					for (var i = 0; i < jsonObj.length; i++) {
						htmls+="<option value='"+jsonObj[i].value+"'>"+jsonObj[i].label+"</option>";
					}
					$('#inspectorSel').html(htmls);
				}
            },
            complete: function() {},
            error: function() {}
        });
    }
	
	function projectModeChange(){
		var storeId = $("#storeId").val();
		var leaderId=$('#leader').val();
		
		if(storeId == undefined || storeId == null || storeId == ""){
			return;
		}

		//项目经理
		$.ajax({
			url: "${ctx}/employee/bizEmployee/employeeByCondition",    //请求的url地址
			dataType: "json",   //返回格式为json
			async: true, //请求是否异步，默认为异步，这也是ajax重要特性
			data: {
				"empType":  "3",
				"projectMode": $("input[name=projectMode]:checked").val(),
				"storeId":  storeId
			},
			type: "POST",   //请求方式
			success: function(req) {
				if(req != undefined && req != null && req != ""){
					var jsonObj = eval(req);
					var htmls = "<option></option>";
					for (var i = 0; i < jsonObj.length; i++) {
						htmls+="<option value='"+jsonObj[i].value+"'>"+jsonObj[i].label+"</option>";
					}
					$('#managerSel').html(htmls);
					if(typeof(leaderId)!= "undefined"&&leaderId!=""){
						$('#leader').val(leaderId);
					}
				}else{
					$('#managerSel').html("");
				}
			},
			complete: function() {},
			error: function() {}
		});

		//质检员
		$.ajax({
			url: "${ctx}/employee/bizEmployee/employeeByCondition",    //请求的url地址
			dataType: "json",   //返回格式为json
			async: true, //请求是否异步，默认为异步，这也是ajax重要特性
			data: {
				"empType":  "1",
				"projectMode": $("input[name=projectMode]:checked").val(),
				"storeId":  storeId
			},
			type: "POST",   //请求方式
			success: function(req) {
				if(req != undefined && req != null && req != ""){
					var jsonObj = eval(req);
					var htmls = "<option></option>";
					for (var i = 0; i < jsonObj.length; i++) {
						htmls+="<option value='"+jsonObj[i].value+"'>"+jsonObj[i].label+"</option>";
					}
					$('#inspectorSel').html(htmls);
				}else{
					$('#inspectorSel').html("");
				}
			},
			complete: function() {},
			error: function() {}
		});
	}
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/engdept/bizEngineeringDepartment/">工程部管理列表</a></li>
		<li class="active"><a
			href="${ctx}/engdept/bizEngineeringDepartment/form?id=${bizEngineeringDepartment.id}">工程部管理<shiro:hasPermission
					name="engdept:bizEngineeringDepartment:edit">${not empty bizEngineeringDepartment.id?'修改':'添加'}</shiro:hasPermission>
				<shiro:lacksPermission name="engdept:bizEngineeringDepartment:edit">查看</shiro:lacksPermission></a></li>
	</ul>
	<br />
	<form:form id="inputForm" onsubmit="onFormSubmit(this)" modelAttribute="bizEngineeringDepartment"
		action="${ctx}/engdept/bizEngineeringDepartment/save" method="post"
		class="form-horizontal">
		<form:hidden path="id" />
		<sys:message content="${message}" />
		<div class="control-group">
			<label class="control-label">门店：</label>
            <div class="controls">
                <c:if test="${empty storeDropEnable}">
                    <form:select path="storeId" class="input-xlarge required" disabled="true" onchange="changeStroe(this.options[this.selectedIndex].value)" id="storeId">
                        <form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
                    </form:select>
                </c:if>
                <c:if test="${!empty storeDropEnable}">
                    <form:select path="storeId" class="input-xlarge required" onchange="changeStroe(this.options[this.selectedIndex].value)" id="storeId">
                        <form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
                    </form:select>
                </c:if>
                <script type="text/javascript">
                    
                </script>
            </div>
		</div>
		<div class="control-group">
			<label class="control-label">工程模式：</label>
			<div class="controls">
				<div class="project" id ="gongchengmoshi">
                 <form:radiobuttons path="projectMode" items="${fns:getDictList('employee_project_mode')}" itemLabel="label" itemValue="value" htmlEscape="false" id="projectMode" name="projectMode" onchange="changeStroe($('#storeId').val())" />
                 <span class="help-inline"><font color="red">*</font></span>
				</div>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">工程部名称：</label>
			<div class="controls">
				<form:input path="name" id="name" name="name" htmlEscape="false"
					maxlength="255" class="input-xlarge " />
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">负责人：</label>
			<div class="controls">
				<form:select path="leader" id="leader" name="leader" class="input-medium">
					<form:option value="" label="" />
					<form:options items="${fns:getEmployeeListByConditions('99','','false')}" itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">接单员：</label>
			<div class="controls">
					<select name="receptionistNon" class="input-medium" id="receptionistSel">
					</select>
				&nbsp;&nbsp;&nbsp;&nbsp; <a href="javascript:onAddEmployee('receptionist')">添加</a>
			</div>
			<div class="controls">
				<table class="table table-striped table-bordered table-condensed">
					<tr id="receptionistSelTr">
						<c:forEach var="item"
							items="${fns:getEmployeeListByDepartmentId(bizEngineeringDepartment.id,'6')}">
							<td width='10%' id='receptionistSelTd${item.value}'>${item.label}
								<input id='receptionist' name='receptionist' type='hidden'
								value='${item.value}' /> &nbsp;&nbsp;<a
								onclick='removeEmployee("receptionist","${item.value}")'>删除</a>
							</td>
							<script>
								employeeIdMap["receptionistSel"]['${item.value}'] = "0";
							</script>
						</c:forEach>
					</tr>
				</table>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">派单员：</label>
			<div class="controls">
					<select name="orderDispatcherNon" class="input-medium" id="orderDispatcherSel">
					</select>
				&nbsp;&nbsp;&nbsp;&nbsp; <a href="javascript:onAddEmployee('orderDispatcher')">添加</a>
			</div>
			<div class="controls">
				<table class="table table-striped table-bordered table-condensed">
					<tr id="orderDispatcherSelTr">
						<c:forEach var="item"
							items="${fns:getEmployeeListByDepartmentId(bizEngineeringDepartment.id,'7')}">
							<td width='10%' id='orderDispatcherSelTd${item.value}'>${item.label}
								<input id='orderDispatcher' name='orderDispatcher' type='hidden'
								value='${item.value}' /> &nbsp;&nbsp;<a
								onclick='removeEmployee("orderDispatcher","${item.value}")'>删除</a>
							</td>
							<script>
								employeeIdMap["orderDispatcherSel"]['${item.value}'] = "0";
							</script>
						</c:forEach>
					</tr>
				</table>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">预算员：</label>
			<div class="controls">
					<select name="budgeteerNon" class="input-medium" id="budgeteerSel">
					</select>
				&nbsp;&nbsp;&nbsp;&nbsp; <a href="javascript:onAddEmployee('budgeteer')">添加</a>
			</div>
			<div class="controls">
				<table class="table table-striped table-bordered table-condensed">
					<tr id="budgeteerSelTr">
						<c:forEach var="item"
							items="${fns:getEmployeeListByDepartmentId(bizEngineeringDepartment.id,'8')}">
							<td width='10%' id='budgeteerSelTd${item.value}'>${item.label}
								<input id='budgeteer' name='budgeteer' type='hidden'
								value='${item.value}' /> &nbsp;&nbsp;<a
								onclick='removeEmployee("budgeteer","${item.value}")'>删除</a>
							</td>
							<script>
								employeeIdMap["budgeteerSel"]['${item.value}'] = "0";
							</script>
						</c:forEach>
					</tr>
				</table>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">派工调度员：</label>
			<div class="controls">
				<form:select path="jobDispatcherNon" class="input-medium"
					id="jobDispatcherSel" name="jobDispatcherSel">
					<form:option value="" label="" />
					<form:options items="${fns:getEmployeeListByConditions('99','','false')}" itemLabel="label"
						itemValue="value" htmlEscape="false" />
				</form:select>
				&nbsp;&nbsp;&nbsp;&nbsp; <a href="javascript:onAddEmployee('jobDispatcher')">添加</a>
			</div>
			<div class="controls">
				<table class="table table-striped table-bordered table-condensed">
					<tr id="jobDispatcherSelTr">
						<c:forEach var="item"
							items="${fns:getEmployeeListByIds(bizEngineeringDepartment.jobDispatcher)}">
							<td width='10%' id='jobDispatcherSelTd${item.value}'>${item.label}
								<input id='jobDispatcher' name='jobDispatcher' type='hidden'
								value='${item.value}' /> &nbsp;&nbsp;<a
								onclick='removeEmployee("jobDispatcher","${item.value}")'>删除</a>
							</td>
							<script>
								employeeIdMap["jobDispatcherSel"]['${item.value}'] = "0";
							</script>
						</c:forEach>
					</tr>
				</table>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">材料调度员：</label>
			<div class="controls">
				<form:select path="materialDispatcherNon" class="input-medium"
					id="materialDispatcherSel" name="materialDispatcherSel">
					<form:option value="" label="" />
					<form:options items="${fns:getEmployeeListByConditions('99','','false')}" itemLabel="label"
						itemValue="value" htmlEscape="false" />
				</form:select>
				&nbsp;&nbsp;&nbsp;&nbsp; <a href="javascript:onAddEmployee('materialDispatcher')">添加</a>
			</div>
			<div class="controls">
				<table class="table table-striped table-bordered table-condensed">
					<tr id="materialDispatcherSelTr">
						<c:forEach var="item"
							items="${fns:getEmployeeListByIds(bizEngineeringDepartment.materialDispatcher)}">
							<td width='10%' id='materialDispatcherSelTd${item.value}'>${item.label}
								<input id='materialDispatcher' name='materialDispatcher'
								type='hidden' value='${item.value}' /> &nbsp;&nbsp;<a
								onclick='removeEmployee("materialDispatcher","${item.value}")'>删除</a>
							</td>
							<script>
								employeeIdMap["materialDispatcherSel"]['${item.value}'] = "0";
							</script>
						</c:forEach>
					</tr>
				</table>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">结算员：</label>
			<div class="controls">
					<select name="settlementClerkNon" class="input-medium" id="settlementClerkSel">
					</select>
				&nbsp;&nbsp;&nbsp;&nbsp; <a href="javascript:onAddEmployee('settlementClerk')">添加</a>
			</div>
			<div class="controls">
				<table class="table table-striped table-bordered table-condensed">
					<tr id="settlementClerkSelTr">
						<c:forEach var="item"
							items="${fns:getEmployeeListByDepartmentId(bizEngineeringDepartment.id,'9')}">
							<td width='10%' id='settlementClerkSelTd${item.value}'>${item.label}
								<input id='settlementClerk' name='settlementClerk' type='hidden'
								value='${item.value}' /> &nbsp;&nbsp;<a
								onclick='removeEmployee("settlementClerk","${item.value}")'>删除</a>
							</td>
							<script>
								employeeIdMap["settlementClerkSel"]['${item.value}'] = "0";
							</script>
						</c:forEach>
					</tr>
				</table>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">质检员：</label>
			<div class="controls">
				<%--<form:select path="inspectorNon" class="input-medium"
					id="inspectorSel" name="inspectorSel">
					<form:option value="" label="" />
					<form:options items="${fns:getEmployeeListByConditions('1','','false')}" itemLabel="label"
						itemValue="value" htmlEscape="false" />
				</form:select>--%>
					<select name="inspectorNon" class="input-medium" id="inspectorSel">
					</select>
				&nbsp;&nbsp;&nbsp;&nbsp; <a
					href="javascript:onAddEmployee('inspector')">添加</a>
			</div>
			<div class="controls">
				<table class="table table-striped table-bordered table-condensed">
					<tr id="inspectorSelTr">
						<c:forEach var="item"
							items="${fns:getEmployeeListByIds(bizEngineeringDepartment.inspector)}">
							<td width='10%' id='inspectorSelTd${item.value}'>${item.label}
								<input id='inspector' name='inspector' type='hidden'
								value='${item.value}' /> &nbsp;&nbsp;<a
								onclick='removeEmployee("inspector","${item.value}")'>删除</a>
							</td>
							<script>
								employeeIdMap["inspectorSel"]['${item.value}'] = "0";
							</script>
						</c:forEach>
					</tr>
				</table>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">项目经理：</label>
			<div class="controls">
				<%--<form:select path="managerNon" class="input-medium" id="managerSel"
					name="managerSel">
					<form:option value="" label="" />
					<form:options items="${fns:getEmployeeListByConditions('3','','false')}" itemLabel="label"
						itemValue="value" htmlEscape="false" />
				</form:select>--%>
					<select name="managerNon" class="input-medium" id="managerSel">
					</select>
				&nbsp;&nbsp;&nbsp;&nbsp; <a
					href="javascript:onAddEmployee('manager')">添加</a>
			</div>
			<div class="controls">
				<table class="table table-striped table-bordered table-condensed">
					<tr id="managerSelTr">
						<c:forEach var="item"
							items="${fns:getEmployeeListByIds(bizEngineeringDepartment.manager)}">
							<td width='10%' id='managerSelTd${item.value}'>${item.label}
								<input id='manager' name='manager' type='hidden'
								value='${item.value}' /> &nbsp;&nbsp;<a
								onclick='removeEmployee("manager","${item.value}")'>删除</a>
							</td>
							<script>
								employeeIdMap["managerSel"]['${item.value}'] = "0";
							</script>
						</c:forEach>
					</tr>
				</table>
			</div>
		</div>
		
		<div class="form-actions">
			<shiro:hasPermission name="engdept:bizEngineeringDepartment:edit">
				<input id="btnSubmit" class="btn btn-primary" type="submit"
					value="保 存" />&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回"
				onclick="history.go(-1)" />
		</div>
	</form:form>
</body>
</html>