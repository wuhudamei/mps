<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>短信组管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			getEmpByStoreid();
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
		
		var employeeIdMap = new Object();
		employeeIdMap.employeesSel = new Object(); 
		function onAddEmployee(type){
			var typeSel = type + "Sel";
			var text  = $("#" + typeSel + " option:selected").text();
			var value = $("#" + typeSel).val();
			if(!type||!value){
				alert("请选择人员");
				return;
			}
			//alert("type:" + type + ",value:" + value + ",text:" + text);
			if(employeeIdMap[typeSel] && employeeIdMap[typeSel][value]){
	            alert("已经选择了该人员");
	            return;
	        }
			//alert("employeeIdMap[type]:" + employeeIdMap[type]);
			var html1 = ""+
            "<td width='10%' id='" + typeSel + "Td" + value + "'>" + text +
            "    <input id='" + type + "' name='" + type + "' type='hidden' value='" + value + "' />"+
            "    &nbsp;&nbsp;<a onclick='removeEmployee(\"" + type + "\",\"" + value + "\")'>删除</a>" +
            "</td>";
            $("#" + typeSel + "Tr").append(html1);
            employeeIdMap[typeSel][value] = "0";
		}
		
		function removeEmployee(type,value){
			var typeSel = type + "Sel";
			$("#" + typeSel + "Td" + value).remove();
			employeeIdMap[typeSel][value] = undefined;
		}
		
		function init(){
			var employees = '${bizMessagegroup.employees}';
			var employeesArray = employees.split(",");
			for(i=0;i<employeesArray.length;i++){
			}
		}
	
		function getEmpByStoreid()
		{
			$("#employeesSel").html('');
			 $.ajax({
			        type : 'POST',
			        dataType : 'json',
			        url : '${ctx}/employee/bizEmployee/other_employee_list_byStoreId',
			        data : {
			            'storeid' : $("#storeId").val(),
			        },
			        success : function(data) {
			            var html = "";
			           // alert(data.length);
			            for (var i = 0; i < data.length; i++) {
			            	//alert($("#empId").val());
			            	var sec = "";
			            	if($("#empIdStoreId").val() == data[i].value){
			            		//alert("dd");
			            		sec = "selected='selected'";
			            		//alert(data[i].label);
			            	}
			            	html += "<option value='" + data[i].value +"' " + sec + ">" + data[i].label + "</option>"
			            }
			            html+="";
			            $("#employeesSel").append(html);
			        }
			    })
		}
		
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/messagegroup/bizMessagegroup/">短信组列表</a></li>
		<li class="active"><a href="${ctx}/messagegroup/bizMessagegroup/form?id=${bizMessagegroup.id}">短信组<shiro:hasPermission name="messagegroup:bizMessagegroup:edit">${not empty bizMessagegroup.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="messagegroup:bizMessagegroup:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="bizMessagegroup" action="${ctx}/messagegroup/bizMessagegroup/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">门店：</label>
			<div class="controls">
				<form:select path="storeId" id ="storeId" class="input-xlarge required" onChange="getEmpByStoreid()">
					<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">短信组类型：</label>
			<div class="controls">
				<form:select path="messageGroupType" class="input-xlarge required">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('message_group_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		
		
		<div class="control-group">
			<label class="control-label">人员：</label>
			<div class="controls">
                <form:select path="employeesNo" class="input-medium required" id="employeesSel" name="employeesSel">
                    <%-- <form:option value="" label="" />
                    <form:options items="${fns:getEmployeeListByStoreId(storeId)}" itemLabel="label" itemValue="value" htmlEscape="false" /> --%>
                    <form:option value="${bizMessagegroup.empId }" label="${bizMessagegroup.empName }" />
                </form:select>
                &nbsp;&nbsp;&nbsp;&nbsp;
                <a href="javascript:onAddEmployee('employees')">添加</a>
                <span class="help-inline"><font color="red">*</font> </span>
			</div>
            <div class="controls">
                <table class="table table-striped table-bordered table-condensed">
                    <tr id="employeesSelTr">
                    <c:forEach var="item" items="${fns:getEmployeeListByIds(bizMessagegroup.employees)}">
                        <td width='10%' id='employeesSelTd${item.value}'>${item.label}
                        <input id='employees' name='employees' type='hidden' value='${item.value}'/>
                         &nbsp;&nbsp;<a onclick='removeEmployee("employees","${item.value}")'>删除</a>
                        </td>
                        <script>
                        employeeIdMap["employeesSel"]['${item.value}'] = "0";
                        </script>
                    </c:forEach>
                    </tr>
                </table>
            </div>
		</div>

		<div class="control-group">
			<label class="control-label">启用标记：</label>
			<div class="controls">
				<form:select path="isEnable" class="input-xlarge required">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('biz_enable_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="messagegroup:bizMessagegroup:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>