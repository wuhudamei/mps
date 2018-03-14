<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>工人组管理</title>
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
                    	storeid : "required",
//                     	elactricationid : "required",
                        star : "required",
                        nps : "required",
                        ordersarea : "required",
                        sort : "required",
                        orderstop : "required",
                        address : "required",
//                         taskpackageid : "required",
                    },
                    messages : {
                    	storeid : "请选择门店",
//                     	elactricationid : "请选择工程部",
                        star : "星级不能为空",
                        nps : "nps不能为空",
                        ordersarea : "接单区域不能为空",
                        sort : "排序不能为空",
                        orderstop : "是否停服不能为空",
                        address : "地址不能为空",
//                         taskpackageid : "任务包不能为空",
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
	/*如果选择没有选择组长提示请选择，组长多余一个提示。*/
	function leaderValid()
	{
		var data = [];
		$("#employeeTb").find("select option:selected").each(function(){
		var val = $(this).text();
		if(val=="是")
		{
			data.push(val);
		}
		});
		if(data.length==0)
		{ alert("请设置一个组长。"); return false;}
		if(data.length>1)
		{return alert("组长只能选择一个。");return false;}
		
		return true;
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
		$("#star").attr("disabled",false);
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
				$("#inputForm").attr("action","${ctx}/empgroup/bizEmployeegroup/save").submit();
			}
		}
	}
	function yesNew(){
		$("#inputForm").attr("action","${ctx}/empgroup/bizEmployeegroup/save").submit();
	}
	function noNew(){
		$("#supplierDateBoxNew").hide();	
	}	
</script>

</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/empgroup/bizEmployeegroup/">工人组列表</a></li>
		<li class="active"><a
			href="${ctx}/empgroup/bizEmployeegroup/form?id=${bizEmployeegroup.id}">工人组<shiro:hasPermission
					name="empgroup:bizEmployeegroup:edit">${not empty bizEmployeegroup.id?'修改':'添加'}</shiro:hasPermission>
				<shiro:lacksPermission name="empgroup:bizEmployeegroup:edit">查看</shiro:lacksPermission></a></li>
	</ul>
	<br />
	<form:form id="inputForm" modelAttribute="bizEmployeegroup"
		 method="post"
		class="form-horizontal">
		<form:hidden path="id" />
		<input type="hidden" value="${bizEmployeegroup.elactricationid}" id="eid" name="eid">
		<form:hidden path="pointXy1" id="pointXy" name="pointXy" />
		<sys:message content="${message}" />
		<div class="control-group">
			<label class="control-label">门店：</label>
			<div class="controls">
				<form:select path="storeid" class="input-xlarge "
					onChange="findEngineListByStoreIdAndProjectMode()" >
					<form:options items="${fns:getStoreList()}" itemLabel="label"
						itemValue="value" htmlEscape="false" />
				</form:select><span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">工程模式：</label>
			<div class="controls" id ="gongchengmoshi">
               <form:radiobuttons path="projectMode" items="${fns:getDictList('employee_project_mode')}" itemLabel="label" itemValue="value" htmlEscape="false" onChange="findEngineListByStoreIdAndProjectMode()"/>
               <span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">归属工程部：</label>
			<div class="controls" >
                <select id="elactricationid" name="elactricationid" class="input-medium needClear">
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
			<label class="control-label">星级：</label>
			<div class="controls">
					<form:select path="star" class="input-xlarge "  htmlEscape="false" disabled="true">
						<form:option value="5">五星</form:option>
						<form:option value="4">四星</form:option>
						<form:option value="3" selected="selected">三星</form:option>
						<form:option value="2">二星</form:option>
						<form:option value="1">一星</form:option>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">NPS：</label>
			<div class="controls">
				<form:input path="nps" htmlEscape="false" maxlength="50"
					class="input-xlarge " /><span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">排名：</label>
			<div class="controls">
				<form:input path="sort" htmlEscape="false" maxlength="5"
					class="input-xlarge  digits" /><span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">是否停单：</label>
			<div class="controls">
				<form:radiobuttons path="orderstop" id="orderstop" name="orderstop"
					items="${fns:getDictList('yes_no')}" itemLabel="label"
					itemValue="value" htmlEscape="false" class="orderstop" /><span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">现住址：</label>
			<div class="controls">
				<form:input path="address" htmlEscape="false" maxlength="255" class="input-xlarge" id="address" name="address" />
                <span class="help-inline"><font color="red">*</font> </span>
                </br>
                <input htmlEscape="false" maxlength="255" class="input-xlarge" id="address1" name="address1" placeholder="地图搜索" />
			</div>
			<div id="allmap" style="border:1px solid #C0C0C0; width:600px; height:400px;"/>
		</div>
		
		<div class="control-group" id="taskpackageDiv" name="taskpackageDiv">
			<label class="control-label">可接任务包：</label>
            <div id="taskpackageIdDiv" class="controls"></div>
            <script>
            initTaskpackage();
            </script>
		</div>
		<div class="control-group">
			<label class="control-label">工人：</label> <label class="control-label">
				<form:select path="remarks" class="input-medium" id="employeeList"
					name="employeeList">
					
				</form:select> &nbsp;&nbsp;&nbsp;&nbsp; <a href="javascript:onAddEmployee()">添加</a>
			</label><span class="help-inline"><font color="red">*</font> </span>
		</div>

		<table id="employeeTb"
			class="table table-striped table-bordered table-condensed">
			<thead>
				<tr>
					<td>姓名</td>
					<td>工人编号</td>
					<td>工种</td>
					<td>推荐项目经理</td>
					<td>是否组长</td>
					<td>工资比例</td>
					<td>操作</td>
				</tr>
			</thead>
			<tbody id="employeeTbody">
				
			</tbody>
		</table>
		<div class="form-actions">
			<shiro:hasPermission name="empgroup:bizEmployeegroup:edit">
                <input id="btnSubmit" onclick="submitForm();" class="btn btn-primary" type="button"	value="保 存"/>&nbsp;
            </shiro:hasPermission>
            <input id="btnCancel" class="btn" type="button" value="返 回"	onclick="history.go(-1)" />
		</div>
		<div class="Black undis" id="supplierDateBoxNew" >
			<div class="tc_center">
				<h2 id="orderAddressNew">停单原因</h2>
				<div class="cen_t">
					<div class="cen_tex">
						<p class="span_r">
								<form:select path="orderStopReasonType" id="orderStopReasonType" name="orderStopReasonType" class="input-xlarge " style="width: 300px;">
									<form:options items="${fns:getDictList('order_stop_reason_type')}"
										itemLabel="label" itemValue="value" htmlEscape="false" />
								</form:select>
						</p>
						<p class="span_r">
							<form:textarea path="orderStopReason" id="orderStopReason" name="orderStopReason"  rows="4" cols="1" style="width: 300px;resize:none;"/>
						<!-- 	<textarea id="orderStopReason" name= rows="4" cols="1" style="width: 300px;resize:none;"></textarea> -->
						</p>
					</div>
					<input id="dayOrderId" type="hidden"/>				
					<div class="cen_btn">
						<span class="btn_y" onclick="yesNew()">确认</span>
						<span class="btn_n" onclick="noNew()">取消</span>
					</div>
				</div>
			</div>
		</div> 
	</form:form>
    <c:forEach items="${bizEmployeegroup.empGropRelation}" var="relation">
        <script>
        employeeTemp = new Object();
        employeeTemp.id = "${relation.empId}";
        employeeTemp.realname = "${relation.empName}";
        employeeTemp.no = "${relation.no}";
        employeeTemp.workTypeName = "${relation.workType}";
        employeeTemp.managerRealName = "${relation.managerName}";
        employeeTemp.isLeader = "${relation.isLeader}";
        employeeTemp.salaryRatio = "${relation.salaryRatio}";
        addEmployeeRow(employeeTemp);
        </script>
    </c:forEach>
    
    <div id="searchResultPanel" style="border:1px solid #C0C0C0;width:300px;height:200px; display:none;"></div>	
	<script type="text/javascript">
	var baiduAK = "9sTsTQyg2l9Y8GIo5uk2a5Be";
	// 百度地图API功能
	var map = new BMap.Map("allmap");
	 
	addMapControl();
	//自动完成
	var ac = new BMap.Autocomplete(    //建立一个自动完成的对象
		{"input" : "address1","location" : map}
	);
	var myValue; //中间变量
	//地图标记对象
	var mkr ;//= new BMap.Marker(0,0,{enableDragging: true,raiseOnDrag: true});

	ac.addEventListener("onhighlight", function(e) {  //鼠标放在下拉列表上的事件
		var str = "";
		var _value = e.fromitem.value;
		var value = "";
		if (e.fromitem.index > -1) {
			value = _value.province +  _value.city +  _value.district +  _value.street +  _value.business;
		}    
		str = "FromItem<br />index = " + e.fromitem.index + "<br />value = " + value;
		
		value = "";
		if (e.toitem.index > -1) {
			_value = e.toitem.value;
			value = _value.province +  _value.city +  _value.district +  _value.street +  _value.business;
		}    
		str += "<br />ToItem<br />index = " + e.toitem.index + "<br />value = " + value;
		G("searchResultPanel").innerHTML = str;
	});

	//鼠标点击下拉列表后的事件
	ac.addEventListener("onconfirm", function(e) {    
		var _value = e.item.value;
		myValue = _value.province +  _value.city +  _value.district +  _value.street +  _value.business;
		G("searchResultPanel").innerHTML ="onconfirm<br />index = " + e.item.index + "<br />myValue = " + myValue;		
		setPlace();
	});

	function setPlace(){
		//alert(1);
		map.clearOverlays();                      //清除地图上所有覆盖物
		var local = new BMap.LocalSearch(map, {   //智能搜索
		  onSearchComplete: myFun
		});
		local.search(myValue);
		function myFun(){
			
			var pp = local.getResults().getPoi(0).point;    //获取第一个智能搜索的结果
			$("#pointXy").val(pp.lat + "," + pp.lng);
            $("#address").val( $("#address1").val());
// 			var pp =  new BMap.Point(39.979197,116.301697);
			map.centerAndZoom(pp, 100);
			mkr = new BMap.Marker(pp,{enableDragging: true,raiseOnDrag: true});
			//添加标注
			map.addOverlay(mkr);
			//标记对象点击完成事件
			mkr.addEventListener("dragend", function(e){
				var posi =  e.point.lat + "," + e.point.lng;
                $("#pointXy").val(posi);
				var url = "http://api.map.baidu.com/geocoder/v2/?ak="+baiduAK+"&callback=renderReverse&location=" + posi + "&output=json&pois=1"
				$("#location").val(posi);
				var aj = $.ajax({    
					url : url,// 跳转到 action    
					data:{},    
					type:'get',    
					cache:true,    
					dataType:'jsonp',
					jsonp:"callback", 
					jsonpCallback:"renderReverse", 
					success:function(data){
						if(data.status == "0" ){
                            $("#address1").val(data.result.formatted_address);
                            $("#address").val( $("#address1").val());
						}else{    
							alert("地址查询失败！" + data.msg);      
						}    
					 },    
					 error : function() {    
						  alert("地址查询异常！");    
					 }    
				});
			}); 
		}		
	}
	
	function myFun2(pp){
		
		if(pp==null){
			 pp = local.getResults().getPoi(0).point;    //获取第一个智能搜索的结果
		}
		map.centerAndZoom(pp, 15);
		mkr = new BMap.Marker(pp,{enableDragging: true,raiseOnDrag: true});
		//添加标注
		map.addOverlay(mkr);
		//标记对象点击完成事件
		mkr.addEventListener("dragend", function(e){
			var posi =  e.point.lat + "," + e.point.lng;
			var url = "http://api.map.baidu.com/geocoder/v2/?ak="+baiduAK+"&callback=renderReverse&location=" + posi + "&output=json&pois=1"
			$("#location").val(posi);
			var aj = $.ajax({    
				url : url,// 跳转到 action    
				data:{},    
				type:'get',    
				cache:true,    
				dataType:'jsonp',
				jsonp:"callback", 
				jsonpCallback:"renderReverse", 
				success:function(data){
					if(data.status == "0" ){
						$("#pointXy").val(data.result.location.lat + "," + data.result.location.lng);
						$("#address").val(data.result.formatted_address);
						$("#address1").val(data.result.formatted_address);
					}else{    
						alert("地址查询失败！" + data.msg);      
					}    
				 },    
				 error : function() {    
					  alert("地址查询异常！");    
				 }    
			});
		}); 
	}	
    function G(id) {
        return document.getElementById(id);
    }
	
	//地图控件添加函数：
    function addMapControl() {
      //向地图中添加缩放控件
      var ctrl_nav = new BMap.NavigationControl({ anchor: BMAP_ANCHOR_TOP_LEFT, type: BMAP_NAVIGATION_CONTROL_LARGE });
      map.addControl(ctrl_nav);
      //向地图中添加缩略图控件
      var ctrl_ove = new BMap.OverviewMapControl({ anchor: BMAP_ANCHOR_BOTTOM_RIGHT, isOpen: 1 });
      map.addControl(ctrl_ove);
      //向地图中添加比例尺控件
      var ctrl_sca = new BMap.ScaleControl({ anchor: BMAP_ANCHOR_BOTTOM_LEFT });
//       map.centerAndZoom("北京",15);      // 初始化地图,设置城市和地图级别。
	var point = new BMap.Point(39.979197,116.301697); //定义一个中心点坐标
// 	 map.centerAndZoom(new BMap.Point(117.269945,31.86713),15); //设定地图的中心点和坐标并将地图显示在地图容器中
	var existPointXy = $("#pointXy").val();
	var xy = existPointXy.split(",");
	 myFun2(new BMap.Point(xy[1],xy[0]));
      map.enableKeyboard(); //启用键盘上下左右键移动地图
      map.addControl(ctrl_sca);
      
      
    }
</script>
</body>
</html>