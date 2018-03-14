<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>公告管理</title>
	<meta name="decorator" content="default"/>
	<style type="text/css">
		.member{width: 20%;float: left;text-align:center;padding-top:10px;padding-bottom:10px;}
		.msg_wrapper{background:#ccc;margin-top:20px;margin-bottom:20px;}
		.remove_mem{margin-left:10px;}
		.remove_mem:hover{text-decoration: none;}
	</style>
	<script type="text/javascript" src="${ctxStatic}/ckeditor1/ckeditor.js"></script>
	<script type="text/javascript" src="${ctxStatic}/ckeditor1/sample.js"></script>
	<script type="text/javascript">
		var employeeIdMap = new Object();
		window.onload = function(){
			var editor = CKEDITOR.replace('noticeContent');
		}

		$(document).ready(function() {
			init();
		});

		function init(){
			$.post("${ctx}/employee/bizEmployee/queryEmployeeListByEmpType",{storeId:$("#storeId").val(),projectMode:$("input:radio[name='projectMode']:checked").val()},function(result){
				var options = "<option></option>";
				if(result != null && result != ""){
					for(var i=0;i<result.length;i++){
						options = options + "<option value="+result[i].value+">"+result[i].label+"</option>";
					}
				}
				$("#receiverEmployeeId").html(options);
			});
		}
		var temp = 0;
		function addEmployee(){
			var text = $("#receiverEmployeeId option:selected").text();
			var value = $("#receiverEmployeeId").val();
			if(value == undefined || value == null || value == ""){
				alertx("请选择接收人员!");
				return;
			}
			if (employeeIdMap[value]) {
				alertx("已经选择了该人员");
				return;
			}

			var content = "<div class='member' id='receiverEmployeeDiv"+value+"'><span>"+text+"</span><input type='hidden' name='receiverEmployeeIds' value='"+value+"'/><a class='remove_mem' href='javascript:removeEmployee("+value+")'>删除</a></div>";
			$("#receiverEmployeeDiv").append(content);
			employeeIdMap[value]="0";
			temp++;
		}

		function removeEmployee(value) {
			$("#receiverEmployeeDiv"+value).remove();
			employeeIdMap[value] = undefined;
			temp--;
		}

		function saveMethod(){
			$("#btnSave").removeAttr("onclick");
			$("#btnPublish").removeAttr("onclick");   
			loading('正在提交，请稍等...');
			$('#noticeContent').val(CKEDITOR.instances.noticeContent.getData());
			$("#noticeStatus").val("1");
			$("#inputForm").submit();
		}
		
		function saveMethodChoose(){
			var v = $('input[type=checkbox]:checked').val();
			var title = $("#noticeTitle").val();
			var Content = $('#noticeContent').val(CKEDITOR.instances.noticeContent.getData())
			var s = Content.val();
			s = $.trim(s);
			if(temp== '0' && v == undefined){
				alertx("请选择接受对象或者添加接收人员")
			}else if(title == ''){
				alertx("请填写标题")
			}else if(s == ''){
				alertx("请填写内容")
			}else{
				 saveMethod();
			}
		}
		
		function saveChoose(){
			var v = $('input[type=checkbox]:checked').val();
			var title = $("#noticeTitle").val();
			var Content = $('#noticeContent').val(CKEDITOR.instances.noticeContent.getData())
			var s = Content.val();
			s = $.trim(s);
			if(temp== '0' && v == undefined){
				alertx("请选择接受对象或者添加接收人员")
			}else if(title == ''){
				alertx("请填写标题")
			}else if(s == ''){
				alertx("请填写内容")
			}else{
				 savePublish();
			}
	
		}
		

		function savePublish(){
			$("#btnSave").removeAttr("onclick");
			$("#btnPublish").removeAttr("onclick");
			loading('正在提交，请稍等...');
			$('#noticeContent').val(CKEDITOR.instances.noticeContent.getData());
			$("#noticeStatus").val("2");
			$("#inputForm").submit();
		}

	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/notice/bizNotice/loadList">公告列表</a></li>
		<li class="active"><a href="${ctx}/notice/bizNotice/form?id=${bizNotice.id}">公告<shiro:hasPermission name="notice:bizNotice:edit">${not empty bizNotice.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="notice:bizNotice:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="bizNotice" action="${ctx}/notice/bizNotice/save" method="post" class="form-horizontal">
		<input type="hidden" name="id" value="${bizNotice.id}"/>
		<input type="hidden" name="noticeStatus" value="${bizNotice.noticeStatus}" id="noticeStatus"/>
		<div class="control-group">
			<label class="control-label">门店：</label>
			<div class="controls">
					<form:select path="storeId" class="input-medium required" id="storeId" onchange="init()">
						<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">工程模式：</label>
			<div class="controls">
				<input type="radio" name="projectMode" value="3" onchange="init()"
					   <c:if test="${not empty projectModeEnable}">disabled="disabled" </c:if>
					   <c:if test="${empty projectModeEnable}"><c:if test="${empty bizNotice.projectMode or bizNotice.projectMode eq '3'}">checked="checked" </c:if></c:if>
						/>全部&nbsp;
				<input type="radio" name="projectMode" value="1" onchange="init()"
					   <c:if test="${not empty projectModeEnable}"><c:if test="${bizNotice.projectMode eq '2'}">disabled="disabled" </c:if><c:if test="${bizNotice.projectMode eq '1'}">checked="checked"</c:if></c:if>
					   <c:if test="${empty projectModeEnable}"><c:if test="${bizNotice.projectMode eq '1'}">checked="checked" </c:if></c:if>
						/>产业&nbsp;
				<input type="radio" name="projectMode" value="2" onchange="init()"
					   <c:if test="${not empty projectModeEnable}"><c:if test="${bizNotice.projectMode eq '1'}">disabled="disabled" </c:if><c:if test="${bizNotice.projectMode eq '2'}">checked="checked"</c:if></c:if>
					   <c:if test="${empty projectModeEnable}"><c:if test="${bizNotice.projectMode eq '2'}">checked="checked" </c:if></c:if>
						/>传统&nbsp;
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">公告类型：</label>
			<div class="controls">
				<input type="radio" name="noticeType" value="1" <c:if test="${empty bizNotice.noticeType or bizNotice.noticeType eq '1'}">checked</c:if>/>紧急通知&nbsp;
				<input type="radio" name="noticeType" value="2" <c:if test="${not empty bizNotice.noticeType and bizNotice.noticeType eq '2'}">checked</c:if>/>重要通知&nbsp;
				<input type="radio" name="noticeType" value="3" <c:if test="${not empty bizNotice.noticeType and bizNotice.noticeType eq '3'}">checked</c:if>/>日常通知&nbsp;
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">接收对象：</label>
			<div class="controls">
				<c:forEach items="${fns:getEmpTypeList('emp_type')}" var="dict">
					<input type="checkbox" name="receiverRole" value="${dict.value}" 
					<c:if test="${not empty bizNotice.receiverRoleId and fns:checkIsExits(bizNotice.receiverRoleId,dict.value)}">
					checked="checked"
					</c:if>
					/>${dict.label}&nbsp;
				</c:forEach>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">接收人员：</label>
			<div class="controls">
				<select class="input-medium" id="receiverEmployeeId" style="width: 180px">

				</select>&nbsp;&nbsp;&nbsp;&nbsp; <a href="javascript:addEmployee()">添加</a>
			</div>
			<div class="controls msg_wrapper" id="receiverEmployeeDiv">
				<c:if test="${not empty employeeList}">
					<c:forEach items="${employeeList}" var="employee">
						<div class="member" id="receiverEmployeeDiv${employee.value}"><span>${employee.label}</span><input type="hidden" name="receiverEmployeeIds" value="${employee.value}"/><a class="remove_mem" href="javascript:removeEmployee(${employee.value})">删除</a></div>
						<script>
							employeeIdMap[${employee.value}] = "0";
						</script>
					</c:forEach>
				</c:if>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">标题：</label>
			<div class="controls">
				<input type="text" id = "noticeTitle" name="noticeTitle" value="${bizNotice.noticeTitle}" htmlEscape="false" maxlength="30" style ="width:500px" class="input-xlarge"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">内容：</label>
			<div class="controls">
				<textarea name="noticeContent" id="noticeContent" style="width:1000px">
						${bizNotice.noticeContent}
				</textarea>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="notice:bizNotice:edit"><input id="btnSave" class="btn btn-primary" type="button" value="暂 存" onclick="saveMethodChoose()"/>&nbsp;</shiro:hasPermission>
			<shiro:hasPermission name="notice:bizNotice:edit"><input id="btnPublish" class="btn btn-primary" type="button" value="发 布" onclick="saveChoose()"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>