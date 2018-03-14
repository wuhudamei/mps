<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>工人组星级调整</title>
<meta name="decorator" content="default" />
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=9sTsTQyg2l9Y8GIo5uk2a5Be"></script>
<style type="text/css">
		.undis{display:none;}
		.Black {
		    position: absolute;
		    top: 0;
		    left: 0;
		    background: rgba(0, 0, 0, .6);
		    width: 100%;
		   /*  height: 140%; */
		}
		
		.Black .tc_center {
		    padding: 15px;
		    position: absolute;
		    top: 50%;
		    left: 55%;
		    width: 360px;
		    height: 220px;
		    margin-left: -300px;
		    background: #fff;
		    color: #666;
		}
		
		 .Black .tc_center h2 {
		    font-size: 20px;
		    text-align: center;
		    line-height: 40px
		}
		
		 .Black .tc_center .cen_t {
		    width: 100%
		}
		
		 .Black .tc_center .cen_t p {
		    line-height: 30px;
		    font-size: 16px;
		    text-align: center;
		    margin-bottom: 12px
		}
		
		 .Black .tc_center .cen_t p input {
		    width: 50%;
		    line-height: 30px;
		    padding-left: 10px;
		    border-radius: 3px;
		    border: #ccc solid 1px
		}
		
		 .Black .tc_center .cen_t .cen_btn {
		    position: absolute;
		    width: 100%;
		    bottom: 20px;
		    text-align: center;
		    line-height: 30px
		}
		
		 .Black .tc_center .cen_t .cen_btn span {
		    width: 120px;
		    display: inline-block;
		    text-align: center;
		    cursor: pointer;
		    border-radius: 3px
		}
		
		 .Black .tc_center .cen_t .cen_btn .btn_y {
		    background: #3da5ee;
		    color: #fff
		}
		
		 .Black .tc_center .cen_t .cen_btn .btn_n {
		    margin-left: 50px;
		    border: solid 1px #3da5ee;
		    color: #3da5ee
		}
		
		 .Black .tc_center .cen_t .cen_tex {
		    width: 90%;
		    margin-left: 5%;
		    font-size: 16px;
		        clear: both;
		}
		
		 .Black .tc_center .cen_t .cen_tex .span_l {
		    display: block;
		    line-height: 30px;
		    float: left;
		    width: 28%;
		    text-align: right;
		    vertical-align: middle
		}
		
		 .Black .tc_center .cen_t .cen_tex .span_r {
		    
		    text-align: left;
		    vertical-align: middle;
		    position: relative;
		    margin-bottom: 8px
		}
		
		 .Black .tc_center .cen_t .cen_tex .span_r input {
		    width: 90%;
		    background: 0 0
		}
		
		
	
	</style>
<script type="text/javascript">
	$(document).ready(
			function() {
				$(".Black").height(document.body.offsetHeight);
				getEngByStoreid();
				
				var radioValtemp = '${bizEmployee.projectMode}';
				var userProjectMode = '${userProjectMode}';
				$(":input[name=projectMode][value=3]").hide();
				$(":input[name=projectMode][value=2]").hide();
				$("[for=projectMode1]").hide();
				$("[for=projectMode3]").hide();
		    	if(userProjectMode == '3' || userProjectMode == "" ||userProjectMode == '2'){
		    		$(":input[name=projectMode][value="+radioValtemp+"]").attr("checked",true);
		    	}else{
		    		if(radioValtemp == ''){
		    			$(":input[name=projectMode]").attr("disabled",true);
			        	$(":input[name=projectMode][value="+userProjectMode+"]").removeAttr("disabled");
			        	$(":input[name=projectMode][value="+userProjectMode+"]").attr("checked",true);
		    		}else{
		    			
		    			$(":input[name=projectMode]").attr("disabled",true);
			        	$(":input[name=projectMode][value="+radioValtemp+"]").removeAttr("disabled");
			        	$(":input[name=projectMode][value="+radioValtemp+"]").attr("checked",true);
		    		}
		    	}
				//初始化区域
		    	findEngineListByStoreIdAndProjectMode();
		    	
		    	
				$("#inputForm").validate({
    				submitHandler : function(form) {
    					var val=leaderValid();
						if(!val){    					
							return false;
						}
    					loading('正在提交，请稍等...');
    					form.submit();
						
    				},
                    rules : {
                        star : "required",
                        reason : "required",
                        changeDescribe : "required",
                    },
                    messages : {
                        star : "星级不能为空",
                        reason: "原因不能为空",
                        changeDescribe : "修改说明不能为空",
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
	function leaderValid()
	{
		return true;
	}
	var employeeIdMap = new Object();//临时存储对象,用于保存工人是否已经被选中
    var employeeTemp;                //临时存储对象，用于工人组工人列表的回写
	function onAddEmployee() {
		var employeeId = $("#employeeList").val();
		//alert("employeeId:" + employeeId +", employeeIdMap['employeeIdMap_" + employeeId + "']:" + employeeIdMap["employeeIdMap_" + employeeId]);
		if (!employeeId || employeeId == '') {
			alert("请选择工人");
			return;
		} else if (employeeIdMap["employeeIdMap_" + employeeId]) {
			alert("已经选择了该工人");
			return;
		}
		var aj = $.ajax({
					url : '${ctx}/empgroup/bizEmployeegroup/employee_add?bizemployeeId=' + employeeId,// 跳转到 action  
					data : {},
					type : 'post',
					cache : false,
					dataType : 'json',
					success : function(data) {
						console.info(data)
						if("1"==data.hadIn){
							alert("该工人已经在其他工人组里了，请选择其他工人！");
							return;
						}
						addEmployeeRow(data);
					},
					error : function(XMLHttpRequest, textStatus, errorThrown) {
						// view("异常！");  
						alert("异常！" + errorThrown);
					}
				});
	}
	
	function addEmployeeRow(data){
		var isLeaderSelected = "";
		var isLeaderUnSelected = "";
		if(data.isLeader && data.isLeader == 1){
			isLeaderSelected = "selected=\"selected\"";
		}else{
			isLeaderUnSelected = "selected=\"selected\"";
		}
		//alert(data.isLeader + " isLeaderSelected:" + isLeaderSelected + " isLeaderUnSelected:" + isLeaderUnSelected);
		var html = "" + "<tr id='employeeTr_"+data.id+"'>"
        + "  <td>" + data.realname + "<input type='hidden' id='empId' name='empId' value=\""+data.id+"\"></input></td>"
        + "  <td>" + data.no + "</td>"
        + "  <td>" + data.workTypeName + "</td>"
        + "  <td>" + data.managerRealName + "</td>"
        + "  <td>"
        + "    <select id='isLeader_" + data.id + "' id='isLeader' name='isLeader' onchange='leaderValid()'>"
        + "      <option value='1' " + isLeaderSelected + ">是</option>"    
        + "      <option value='0' " + isLeaderUnSelected + "+>否</option>"
        + "    </select>"
        + "  </td>"
        + "  <td>"
        + "     <select id='salaryRatio' id='ratio' name='ratio'>";
        for(var i=10;i<101;i=i+5){
        	var sec = "";
        	if(data.salaryRatio && data.salaryRatio == i){
        		sec = "selected=\"selected\"";
        	}
        	html += "<option value='" + i +"' " + sec + ">" + i + "%</option>";
        }
        html += "</select></td>" + 
        "  <td><a onclick='removeEmployee(\"" + data.id + "\")'>删除</a></td>" + "</tr>";
        $("#employeeTbody").append(html);
        employeeIdMap["employeeIdMap_" + data.id] = "0";
	}
	function removeEmployee(employeeId) {
		$("#employeeTr_" + employeeId).remove();
		employeeIdMap["employeeIdMap_" + employeeId] = undefined;
	}

	var orderarea = '${bizEmployeegroup.ordersarea}';
	var taskpackageId = '${bizEmployeegroup.taskpackageid}';
	
	function initOrderarea() {
		$("#orderareaDiv").html("");
		$("#taskpackageIdDiv").html("");
		var dataArray = orderarea.split(",");
		$.ajax({
            type : 'POST',
            dataType : 'json',
            url : '${ctx}/employee/bizEmployee/manager_store_form',
            data : {
            	'storeId' : $("#storeid").val(),
            	'id' : $("#id").val(),
            	'projectMode' : '${projectMode}'
            },
            success : function(data) {
            	var html = "";
            	for (var i = 0; i < data.length; i++) {
            		var checked = "";
            		for (var x = 0; x < dataArray.length; x++) {
            			if (dataArray[x] == data[i].value) {
            				checked = "checked";
            			}
            		}
            		html += '<input id="ordersarea" type="checkbox" ' + checked + ' value="' + data[i].value + '" name="ordersarea">'
            		+ data[i].label;
            	}
            	$("#orderareaDiv").html(html);
            }
		})
		initTaskpackage();
		getEmpStoreid();
	}


	function initTaskpackage() {
	    var dataArray = taskpackageId.split(",");
	    var pro = $(":input[type = radio][checked=checked]").val();
	    $.ajax({
	        type : 'POST',
	        dataType : 'json',
	        url : '${ctx}/taskpackage/bizTaskPackageTemplat/taskListByNowStoreId',
	        data : {
	            'storeid' : $("#storeid").val(),
	            'id' : $("#id").val(),
	            'projectMode' : pro
	        },
	        success : function(data) {
	            var html = "";
	            for (var i = 0; i < data.length; i++) {
	                var checked = "";
	                for (var x = 0; x < dataArray.length; x++) {
	                    if (dataArray[x] == data[i].value) {
	                        checked = "checked";
	                    }
	                }
	                html += '<input id="taskpackageid" type="checkbox" ' + checked + ' value="' + data[i].value + '" name="taskpackageid">'
	                        + data[i].label;
	            }
	            $("#taskpackageIdDiv").html(html);
	        }
	    })
	}
	
	function getEngByStoreid() {
		$("#engOptions").html('');
	    $.ajax({
	        type : 'POST',
	        dataType : 'json',
	        url : '${ctx}/engdept/bizEngineeringDepartment/engListByStorId',
	        data : {
	            'storeid' : $("#storeid").val(),
	            'eid' : $("#eid").val()
	        },
	        success : function(data) {
	        	
	            var html = "<select id='elactricationid' id='elactricationid' name='elactricationid' class='input-xlarge '>";
	          	  html += "<option value=''></option>"
	            for (var i = 0; i < data.length; i++) {
	            	
	            	var sec = "";
	            	if($("#eid").val() == data[i].id){
	            		sec = "selected=\"selected\"";
	            	}
	            	
	            	html += "<option value='" + data[i].id +"' " + sec + ">" + data[i].name + "</option>"
	            }
	            html+="</select>";
	           /*  $("#engOptions").html('');
	            $("#engOptions").append(html); */
	        }
	    })
	}
	
	function getEmpStoreid() {
		$("#employeeList").html('');
	    $.ajax({
	        type : 'POST',
	        dataType : 'json',
	        url : '${ctx}/empgroup/bizEmployeegroup/employees',
	        data : {
	        	'storeid' : $("#storeid").val()
	        },
	        success : function(data) {
	            var html = "<select id='elactricationid' id='elactricationid' name='elactricationid' class='input-xlarge '>";
	            html += "<option value='' ></option>"
	            for (var i = 0; i < data.length; i++) {
	            	html += "<option value='" + data[i].value +"' >" + data[i].label + "</option>"
	            }
	            html+="</select>";
	            $("#employeeList").html('');
	            $("#employeeList").html(html);
	        }
	    })
	}
	
	
	function findEngineListByStoreIdAndProjectMode(){
				initOrderarea();
				var html3 = '<option value=""></option>';
				var storeId = $("#storeid").val();
				var projectModeValue = $(":input[type=radio][name=projectMode][checked=checked]").val();
				var temp = "${bizEmployeegroup.elactricationid}";
				
				if (storeId=="" ||projectModeValue=="" ||undefined==storeId ||undefined==projectModeValue) {
					return;
				}
				//根据门店个,工程模式    动态加载工程区域
				$.ajax({
				
							url : "${ctx}/order/order/findEngineDepartmentBystoreIdAndProjectMode?storeId="
									+ storeId + "&projectModeValue=" + projectModeValue,
							type : "get",
							success : function(data) {
								if (null!= data && data.length > 0) {
				
									for (var v = 0; v < data.length; v++) {
										if(data[v].engineDepartId == temp){
											$("#s2id_elactricationid").find(".select2-chosen").text(data[v].engineDepartName);
											html3 +='<option selected="selected" value="'+data[v].engineDepartId+'">'+data[v].engineDepartName+'</option>' 
										}else{
											html3 +='<option value="'+data[v].engineDepartId+'">'+data[v].engineDepartName+'</option>'
										}
										
									}
									
									$("#elactricationid").html(html3);
								} else {
									$("#elactricationid").html(html3);
								}
				
							}
				
						});	
				
	}
	function submitForm(){
		var orderstop = '';
		$("input.orderstop:radio").each(function(){  
	        if(true == $(this).is(':checked')){  
	        	orderstop = $(this).val();
       		 }  
		}); 
		//action="${ctx}/empgroup/bizEmployeegroup/save"
		if($("#inputForm").valid()){
			if(orderstop==1){
				$("#supplierDateBoxNew").show();
			}else{
				$("#inputForm").attr("action","${ctx}/empStar/empStarAdjustment/update").submit();
			}
		}
	}
	function yesNew(){
		$("#inputForm").attr("action","${ctx}/empStar/empStarAdjustment/update").submit();
	}
	function noNew(){
		$("#supplierDateBoxNew").hide();	
	}	
</script>

</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/empStar/empStarAdjustment/">工人组星级调整</a></li>
	</ul>
	<br />
	<form:form id="inputForm" modelAttribute="bizEmployeegroup" action="${ctx}/empStar/empStarAdjustment/update"
		 method="post"
		class="form-horizontal">
		<form:hidden path="id" />
		<input type="hidden" value="${bizEmployeegroup.elactricationid}" id="eid" name="eid">
		<form:hidden path="pointXy1" id="pointXy" name="pointXy" />
		<sys:message content="${message}" />
		<div class="control-group">
			<label class="control-label">门店：</label>
			<div class="controls">
				<form:select path="storeid" class="input-xlarge " disabled="true"
					onChange="findEngineListByStoreIdAndProjectMode()" >
					<form:options items="${fns:getStoreList()}" itemLabel="label"
						itemValue="value" htmlEscape="false" />
				</form:select><span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">工程模式：</label>
			<div class="controls" id ="gongchengmoshi">
               <form:radiobuttons path="projectMode" items="${fns:getDictList('employee_project_mode')}" itemLabel="label" itemValue="value" htmlEscape="false" disabled="true" onChange="findEngineListByStoreIdAndProjectMode()"/>
               <span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">区域：</label>
			<div class="controls" >
                <select id="elactricationid" name="elactricationid" class="input-medium needClear" disabled="true">
				</select>
			</div>
           
		</div>
		<!-- <div class="control-group">
			<label class="control-label">接单区域：</label>
			<div class="controls">
                <div id="orderareaDiv" >
                </div>
                <span class="help-inline"><font color="red">*</font> </span>
            </div>
			<script>
			initOrderarea();
			</script>
		</div> -->
		<div class="control-group">
			<label class="control-label">组长姓名：</label>
			<div class="controls">
				<form:input path="groupid" value="${realName}" htmlEscape="false" maxlength="50" disabled="true"
					class="input-xlarge " /><span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">星级：</label>
			<div class="controls">
			<form:select path="star" class="input-xlarge required"  htmlEscape="false" >
						<form:option value="5">五星</form:option>
						<form:option value="4">四星</form:option>
						<form:option value="3">三星</form:option>
						<form:option value="2">二星</form:option>
						<form:option value="1">一星</form:option>
					</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">原因：</label>
			<div class="controls">
			<form:select path="reason" class="input-xlarge required"  htmlEscape="false" >
						<form:option value="1">客户表扬</form:option>
						<form:option value="2">客户批评</form:option>
					</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">修改说明：</label>
			<div class="controls">
				<form:textarea path="changeDescribe" style="resize: none;" htmlEscape="false" maxlength="50"
					class="input-xlarge"></form:textarea>
					
					<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="empgroup:bizEmployeegroup:edit">
                <input id="btnSubmit" onclick="submitForm();" class="btn btn-primary" type="button"	value="保 存"/>&nbsp;
            </shiro:hasPermission>
            <input id="btnCancel" class="btn" type="button" value="返 回"	onclick="history.go(-1)" />
		</div>
	</form:form>
</body>
</html>