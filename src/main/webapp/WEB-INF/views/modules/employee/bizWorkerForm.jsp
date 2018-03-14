<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>新增工人</title>
<meta name="decorator" content="default" />
<script type="text/javascript" src="${ctxStatic}/cusumerValidate/cusumerValidate.js"></script>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=9sTsTQyg2l9Y8GIo5uk2a5Be"></script>
<script type="text/javascript">
	$(document)
			.ready(

					function() {
						var radioValtemp = '${bizEmployee.projectMode}';
				    	var userProjectMode = '${userProjectMode}';
				    	if(userProjectMode == '3' || userProjectMode == ""){
				    	}else{
				    		$(":input[name=projectMode]").attr("disabled",true);
				        	$(":input[name=projectMode][value="+radioValtemp+"]").removeAttr("disabled");
				    	}

						// 手机号码验证
						jQuery.validator.addMethod("isPhone", function(value, element) {
						    var length = value.length;
						  //var mobile = /^(1[0-9]{10})$/;
						    var mobile = /^1[34578]\d{9}$/;
						    return this.optional(element) || (length == 11 && mobile.test(value));
						}, "请正确填写您的手机号码");
						// 身份证号码验证 
						jQuery.validator.addMethod("isIdCardNo", function(value, element) { 
						  return this.optional(element) || isIdCardNo(value);     
						}, "请正确输入您的身份证号码"); 
						$("#inputForm")
								.validate(
										{
											rules : {
												storeid : "required",
												no : {
													required : true,
													remote : {
														url : "${ctx}/employee/bizEmployee/validatNo",
														type : "post",
														data : {
															no : function() {
																return $('#no')
																		.val();
															},
															id : function() {
																return $('#id')
																		.val();
															},
															noValid : function() {
																return $(
																		'#noValid')
																		.val();
															},
														}
													},

												},
												realname : {
													required : true,
													minlength : 1,
													maxlength : 255
												},
												loginname : {
													required : true,
													remote : {
														url : "${ctx}/employee/bizEmployee/validatLoginName",
														type : "post",
														data : {
															loginname : function() {
																return $(
																		'#loginname')
																		.val();
															},
															id : function() {
																return $('#id')
																		.val();
															},
															loginNameValid : function() {
																return $(
																		'#loginNameValid')
																		.val();
															},
														}
													},
												},
												phone : {
													required : true,
													isPhone:true,
													remote : {
														url : "${ctx}/employee/bizEmployee/validatPhone",
														type : "post",
														data : {
															loginname : function() {
																return $(
																		'#phone')
																		.val();
															},
															id : function() {
																return $('#id')
																		.val();
															},
															phoneValid : function() {
																return $(
																		'#phoneValid')
																		.val();
															},
														}
													},
												},
												idcardno : {
													required : true,
													isIdCardNo : true,
													remote : {
														url : "${ctx}/employee/bizEmployee/validatIdcardno",
														type : "post",
														data : {
															idcardno : function() {
																return $('#idcardno').val();
															},
															id : function() {
																return $('#id').val();
															},
															idcardnoValid : function() {
																return $('#idcardnoValid').val();
															},
														}
													},
												},
												entrytime : "required",
												sex : "required",
												address : "required",
												orderarea : "required",
												worktype : "required",
												/*
												birthday : "required",
												household : "required",
												roots : "required",
												family : "required",
												age : "required",
												education : "required",
												postno : "required",
												headpic : "required",
												
												orderstop : "required",
												*/
											},
											messages : {
												storeid : "请选择门店",
												no : {
													required : "请输入员工编号",
													remote : "编号已存在",
												},
												realname : {
													required : "请输入真实姓名",
													minlength : "字符长度不能小于6个字符",
													maxlength : "字符长度不能大于255个字符",
													ischinese : "只能输入汉字"
												},
												loginname : {
													required : "用户名不能为空",
													remote : "用户名已存在",

												},
												phone : {
													required : "电话号码不能为空",
													isphone : "电话号码格式错误",
													remote : "手机号已存在",
												},
												idcardno : {
													required : "身份证号不能为空",
													isIdCardNo : "身份证号格式错误",
													remote : "身份证号已存在",

												},
												entrytime : "入职时间不能为空",
												birthday : "生日不能为空",
												sex : "请选择性别",
												household : "请输入户口所在地",
												roots : "籍贯不能为空",
												family : "民族不能为空",
												age : "年龄不能为空",
												education : "教育不能为空",
												postno : "邮编不能为空",
												address : "现住址不能为空",
												headpic : "头像不能为空",
												worktype : "请选择工种",
												orderarea : "请选择接单区域",
												orderstop : "请选择是否停单",
											},
											submitHandler : function(form) {
												loading('正在提交，请稍等...');
												form.submit();
											},
											errorContainer : "#messageBox",
											errorPlacement : function(error,
													element) {
												$("#messageBox").text(
														"输入有误，请先更正。");
												if (element.is(":checkbox")
														|| element.is(":radio")
														|| element
																.parent()
																.is(
																		".input-append")) {
													error.appendTo(element
															.parent().parent());
												} else {
													error.insertAfter(element);
												}
											}
										});
					});

	var orderarea = '${bizEmployee.orderarea}';
	var radioVal = '${bizEmployee.projectMode}';
	$(document).on('click','input[name="projectMode"]',function(){
		radioVal = $(this).val();
		storeClick();});
	function storeClick() {
		var orderareaArray = orderarea.split(",");
			$.ajax({
					type : 'POST',
					dataType : 'json',
					url : '${ctx}/employee/bizEmployee/manager_store_form',
					data : {
						'storeId' : $("#storeid").val(),
						'projectMode' : radioVal,
						'id' : $("#id").val()
					},
					success : function(data) {
						var html = "";
						for (var i = 0; i < data.length; i++) {
							//alert(data[i].value +" -- "+ data[i].label);
							var checked = "";
							for (var x = 0; x < orderareaArray.length; x++) {
								if (orderareaArray[x] == data[i].value) {
									checked = "checked";
								}
							}
							//alert("data[i].value:"+data[i].value+", orderareaArray[data[i].value]:" + orderareaArray[data[i].value] + ",checked:" + checked + ";");
							html += '<input id="orderarea" type="checkbox" ' + checked + ' value="' + data[i].value + '" name="orderarea">'
									+ data[i].label;
						}
						$("#orderarea").html(html);
					}
				})
	}
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/employee/bizEmployee/worker_list?empType=2">工人信息</a></li>
		<li class="active"><a
			href="${ctx}/employee/bizEmployee/worker_form?id=${bizEmployee.id}">${not empty bizEmployee.id?'修改':'添加'}工人<shiro:hasPermission
					name="employee:bizManager:edit">${not empty bizEmployee.id?'修改':'添加'}</shiro:hasPermission>
				<shiro:lacksPermission name="manager:bizManager:edit">查看</shiro:lacksPermission></a></li>
	</ul>
	<br />
	<form:form id="inputForm" modelAttribute="bizEmployee"
		action="${ctx}/employee/bizEmployee/worker_save" method="post"
		class="form-horizontal">
		<form:hidden path="id" />
		<form:hidden path="noValid" id="noValid" name="noValid" />
		<form:hidden path="loginNameValid" id="loginNameValid"
			name="loginNameValid" />
		<form:hidden path="phoneValid" id="phoneValid" name="phoneValid" />
		<form:hidden path="idcardnoValid" id="idcardnoValid" name="idcardnoValid" />
		<form:hidden path="pointXy" id="pointXy" name="pointXy" />
		<sys:message content="${message}" />
		<table>
			<tr>
				<td><div class="control-group">
						<label class="control-label">门店：</label>
						<div class="controls">
							<form:select path="storeid" onClick="storeClick()"
								class="input-medium">
								<form:option value="" label="" />
								<form:options items="${fns:getStoreList()}" itemLabel="label"
									itemValue="value" htmlEscape="false" id="storeid"
									name="storeid" />
							</form:select>
							<span class="help-inline"><font color="red">*</font> </span>
						</div>
					</div></td>
				<td>
					<div class="control-group">
						<label class="control-label">工程模式：</label>
						<div class="controls project" id ="gongchengmoshi">
                               	 <form:radiobuttons path="projectMode" items="${fns:getDictList('pre_employee_project_mode')}" itemLabel="label" itemValue="value" htmlEscape="false" class="" 
								id="projectMode" name="projectMode" />
                                <span class="help-inline"><font color="red">*</font></span>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td><div class="control-group">
						<label class="control-label">员工编号：</label>
						<div class="controls">
							<form:input path="no" htmlEscape="false" maxlength="10"
								class="input-medium" placeholder="系统自动生成" disabled="true" />
							<span class="help-inline"><font color="red">*</font> </span>
						</div>
					</div></td>
				<td><div class="control-group">
						<label class="control-label">真实姓名：</label>
						<div class="controls">
							<form:input path="realname" htmlEscape="false" maxlength="10"
								class="input-medium" />
							<span class="help-inline"><font color="red">*</font> </span>
						</div>
					</div></td>
			</tr>
			<tr>
				<td><div class="control-group">
						<label class="control-label">用户名：</label>
						<div class="controls">
							<form:input path="loginname" htmlEscape="false" maxlength="10"
								class="input-medium" />
							<span class="help-inline"><font color="red">*</font> </span>
						</div>
					</div></td>
				<td><div class="control-group">
						<label class="control-label">手机号：</label>
						<div class="controls">
							<form:input path="phone" htmlEscape="false" maxlength="11"
								class="input-medium" />
							<span class="help-inline"><font color="red">*</font> </span>
						</div>
					</div></td>
			</tr>
			<tr>
				<td><div class="control-group">
						<label class="control-label">员工身份证：</label>
						<div class="controls">
							<form:input path="idcardno" htmlEscape="false" maxlength="18" class="input-medium"/>
							<span class="help-inline"><font color="red">*</font> </span>
						</div>
					</div></td>
				<td><div class="control-group">
						<label class="control-label">入职时间：</label>
						<div class="controls">
							<input name="entrytime" type="text" readonly="readonly"
								maxlength="20" class="input-medium Wdate "
								value="<fmt:formatDate value="${bizEmployee.entrytime}" pattern="yyyy-MM-dd"/>"
								onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});" />
							<span class="help-inline"><font color="red">*</font> </span>
						</div>
					</div></td>
			</tr>
			<tr>
				<td><div class="control-group">
						<label class="control-label">员工类型：</label>
						<div class="controls">
							<form:select path="empType" class="input-medium" id="empType"
								name="empType" disabled="true">
								<form:option value="2" label="工人" />
							</form:select>
							<span class="help-inline"><font color="red">*</font> </span>
						</div>
					</div></td>
				<td><div class="control-group">
						<label class="control-label">生日：</label>
						<div class="controls">
							<input name="birthday" type="text" readonly="readonly"
								maxlength="20" class="input-medium Wdate "
								value="<fmt:formatDate value="${bizEmployee.birthday}" pattern="yyyy-MM-dd"/>"
								onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});" />
						</div>
					</div></td>
			</tr>
			<tr>
				<td><div class="control-group">
						<label class="control-label">性别：</label>
						<div class="controls">
							<form:radiobuttons path="sex" id="sex" name="sex" items="${fns:getDictList('sex')}"
								itemLabel="label" itemValue="value" htmlEscape="false" class="" />
							<span class="help-inline"><font color="red">*</font> </span>
						</div>
					</div></td>
				<td><div class="control-group">
						<label class="control-label">户口所在地：</label>
						<div class="controls">
							<form:input path="household" htmlEscape="false" maxlength="255"
								class="input-medium" />
						</div>
					</div></td>
			</tr>
			<tr>
				<td><div class="control-group">
						<label class="control-label">籍贯：</label>
						<div class="controls">
							<form:input path="roots" htmlEscape="false" maxlength="255"
								class="input-medium" />
						</div>
					</div></td>
				<td><div class="control-group">
						<label class="control-label">民族：</label>
						<div class="controls">
							<form:input path="family" htmlEscape="false" maxlength="20"
								class="input-medium" />
						</div>
					</div></td>
			</tr>
			<tr>
				<td><div class="control-group">
						<label class="control-label">年龄：</label>
						<div class="controls">
							<form:input path="age" htmlEscape="false" maxlength="3"
								class="input-medium digits" />
						</div>
					</div></td>
				<td><div class="control-group">
						<label class="control-label">学历：</label>
						<div class="controls">
							<form:select path="education" class="input-medium">
								<form:option value="" label="" />
								<form:options items="${fns:getDictList('emp_education')}"
									itemLabel="label" itemValue="value" htmlEscape="false" />
							</form:select>
						</div>
					</div></td>
			</tr>
			<tr>
				<td><div class="control-group">
						<label class="control-label">邮编：</label>
						<div class="controls">
							<form:input path="postno" htmlEscape="false" maxlength="50"
								class="input-medium" />
						</div>
					</div></td>
			</tr>
			<tr>
                <td>
                    <div class="control-group">
                        <label class="control-label">地址：</label>
                        <div class="controls">
                            <form:input path="address" htmlEscape="false" maxlength="255" class="input-xlarge" id="address" name="address" />
                            <span class="help-inline"><span class="help-inline"><font color="red">*</font> </span> </span>
                            </br>
                            <input htmlEscape="false" maxlength="255" class="input-xlarge" id="address1" name="address1" placeholder="地图搜索" />
                        </div>
                    </div>
                </td>
				<td>
                    <div class="control-group">
						<label class="control-label">头像：</label>
						<div class="controls">
							<form:hidden id="headpic" path="headpic" htmlEscape="false" maxlength="128" class="input-medium" />
							<sys:ckfinder input="headpic" type="images" uploadPath="/upload/employeeHeadPic" selectMultiple="false" />
							<!-- <span class="help-inline"><font color="red">*</font> </span> -->
						</div>
					</div>
                 </td>
			</tr>
			<tr>
                <td>
                    <div id="allmap" style="border:1px solid #C0C0C0; width:600px; height:400px;"/>
                </td>
				<%-- <td><div class="control-group">
						<div id="headPic"
							style="width: 200px; height: 200px; margin-left: 100px; text-align: center; border: solid 1px;">
							<img alt="" src="${bizEmployee.headpic}">
						</div>
					</div></td> --%>
			</tr>
		</table>
		<hr style="height: 1px; border: none; border-top: 1px solid #555555;" />
		<table>
			<tr>
				<td><div class="control-group">
						<label class="control-label">工种：</label>
						<div class="controls">
							<form:select path="worktype" id="worktype" class="input-medium">
								<form:option value="" label="" />
								<form:options items="${fns:getDictList('emp_work_type')}"
									itemLabel="label" itemValue="value" htmlEscape="false" />
							</form:select>
                            <span class="help-inline"><font color="red">*</font> </span>
						</div>
					</div></td>
				<td><div class="control-group">
						<label class="control-label">星级：</label>
						<div class="controls">
							<form:select path="star" class="input-medium">
								<form:option value="" label="" />
								<form:options items="${fns:getDictList('emp_star')}"
									itemLabel="label" itemValue="value" htmlEscape="false" />
							</form:select>
							
						</div>
					</div></td>
			</tr>
			<tr>
				<td><div class="control-group">
						<label class="control-label">NPS值：</label>
						<div class="controls">
							<form:input path="nps" htmlEscape="false" maxlength="3"
								class="input-medium" />
						</div>
					</div></td>
				<td><div class="control-group">
						<label class="control-label">是否停单：</label>
						<div class="controls">
							<form:radiobuttons path="orderstop" id="orderstop" name="orderstop"
								items="${fns:getDictList('yes_no')}" itemLabel="label"
								itemValue="value" htmlEscape="false" class="" />
							<span class="help-inline"><font color="red">*</font> </span>
						</div>
					</div></td>
			</tr>
			<tr>
				<td><div class="control-group">
						<label class="control-label">排名：</label>
						<div class="controls">
							<form:input path="sort" htmlEscape="false" maxlength="5"
								class="input-medium digits" />
							
						</div>
					</div></td>
				<td>
					<div class="control-group">
						<label class="control-label">推荐人:</label>
						<div class="controls">
							<form:select path="managerid" class="input-medium" id="managerid"
								name="managerid">
								<form:option value="" label="" />
								<form:options items="${fns:getEmployeeList('')}" itemLabel="label"
									itemValue="value" htmlEscape="false" />
							</form:select>
							
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td><div class="control-group">
						<label class="control-label">是否有电工证：</label>
						<div class="controls">
							<form:radiobuttons path="electricancard" id="electricancard" name="electricancard"
								items="${fns:getDictList('yes_no')}" itemLabel="label"
								itemValue="value" htmlEscape="false" class="" />
							
						</div>
					</div></td>
				<td><div class="control-group">
						<label class="control-label">是否加入新农合：</label>
						<div class="controls">
							<form:radiobuttons path="ncms" id="ncms" name="ncms"
								items="${fns:getDictList('yes_no')}" itemLabel="label"
								itemValue="value" htmlEscape="false" class="" />
						</div>
					</div></td>
			</tr>
			<tr>
				<td><div class="control-group">
						<label class="control-label">是否会微信：</label>
						<div class="controls">
							<form:radiobuttons path="iswebchat" id="iswebchat" name="iswebchat"
								items="${fns:getDictList('yes_no')}" itemLabel="label"
								itemValue="value" htmlEscape="false" class="" />
							
						</div>
					</div></td>
				<td><div class="control-group">
						<label class="control-label">是否智能手机：</label>
						<div class="controls">
							<form:radiobuttons path="smartphone" id="smartphone" name="smartphone"
								items="${fns:getDictList('yes_no')}" itemLabel="label"
								itemValue="value" htmlEscape="false" class="" />
							
						</div>
					</div></td>
			</tr>
			<tr>
				<td colspan="2"><div class="control-group">
						<label class="control-label">订单区域：</label>
						<div class="controls">
                            <div id="orderarea" >
                            </div>
                            <span class="help-inline"><font color="red">*</font> </span>
                           <script>storeClick();</script>
                        </div>
					</div></td>
			</tr>
		</table>

		<div class="form-actions">
			<shiro:hasPermission name="employee:bizEmployee:edit">
				<input id="btnSubmit" class="btn btn-primary" type="submit"
					value="保 存" />&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回"
				onclick="history.go(-1)" />
		</div>
	</form:form>
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
                            $("#address").val($("#address1").val());
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