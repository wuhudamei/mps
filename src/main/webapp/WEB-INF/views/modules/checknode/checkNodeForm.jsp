<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>约检节点管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript">


	function  checkIsOk() {
        var storeId=$("#store").val();
        var projectMode = $(":radio[name='projectMode'][checked='checked']").val();

        if(storeId==""||projectMode==""){

            alert("请先选择门店和工程模式")
            $(":radio[name='isForBasicWork'][value='" + 0 + "']").prop("checked", "checked")
		}

	    $.ajax({

			url:"${ctx}/checknode/checkNode/checkIsOkForBasicNode",
			data:{
                storeId:storeId,
				projectMode:projectMode

			},
			type:"get",
			success:function(data){

			    if(data>0){
                    $(":radio[name='isForBasicWork'][value='" + 0 + "']").prop("checked", "checked")
			        alert("同门店,同模式 只能有一个基装验收节点");


                }



			}

		})

    }
		$(document).ready(function() {
		    $("#isForBasicWork1").bind("click",checkIsOk);



			
			var projectMode  ="${checkNode.id}"
			var readOnly = "${readOnly}"
			//如果是产业或者是传统的人,  就不允许修改
			if ("1" == readOnly) {
				$(":radio[name='projectMode'][value='" + 2 + "']").attr("disabled",true);
				$(":radio[name='projectMode'][value='" + 4 + "']").attr("disabled",true);
			}
			if ("2" == readOnly) {
				$(":radio[name='projectMode'][value='" + 1 + "']").attr("disabled",true);
				$(":radio[name='projectMode'][value='" + 4 + "']").attr("disabled",true);
			}
			if ("4" == readOnly) {
				$(":radio[name='projectMode'][value='" + 1 + "']").attr("disabled",true);
				$(":radio[name='projectMode'][value='" + 2 + "']").attr("disabled",true);
			}
			//新增
			if ("" == projectMode) {
				//如果不是产业或者是传统的人 ,默认传统,并且可以修改
				if ("1" != readOnly && "2" != readOnly) {
					$(":radio[name='projectMode'][value='" + 2 + "']").prop("checked", "checked");
				} else {
					//如果是产业或者是传统的人   工程模式也一样,并且不能修改
					$(":radio[name='projectMode'][value='" + readOnly + "']").prop("checked", "checked");
					if ("1" == readOnly) {
						$(":radio[name='projectMode'][value='" + 2 + "']").attr("disabled",true);
					}
					if ("2" == readOnly) {
						$(":radio[name='projectMode'][value='" + 1 + "']").attr("disabled",true);
					}
				}
				$(":radio[name='isUrgePay'][value='" + 0 + "']").prop("checked", "checked")	
				$(":radio[name='isForBasicWork'][value='" + 0 + "']").prop("checked", "checked")
				$(":radio[name='status'][value='" + 1 + "']").prop("checked", "checked")
				
				//修改
			}
			//不做动作, 保证回显, 上面也保证readOnly
			
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
		
		function findConsNameByStoreId(){
			var storeId=$("#store").val();
			var projectMode = $(":radio[name='projectMode'][checked='checked']").val();
			if (storeId < 1) {

				return;
			}
			
			$.ajax({
				
				url: "${ctx}/checknode/checkNode/findConsByStoreId?storeId="+storeId+"&projectMode="+projectMode,
				type: "get",
				success : function(data){
					if(data!=null){
						var htmls = "<option value=''></option>";//<option value='' selected='true'></option>
				   		for(var i = 0; i < data.length; i++){
				   			htmls += "<option value='"+data[i].constructionScheduleId+"'>"+data[i].constructionScheduleName+"</option>";
				   		}
				   		$("#constructionScheduleId").html(htmls);
				   		var htmls = "";
				   		
				   		
				   		/* 
						$("#cons").html("");
						var html='<select class="input-medium needClear" id="consId" name="constructionScheduleId"><option value=""></option>';
						
						
						
						for(var v=0;v<data.length;v++){
							html+='<option value="'+data[v].constructionScheduleId+'">'+data[v].constructionScheduleName+'</option>'
							
						}
						html+='</select>';
						$("#cons").html(html); */
					}
				}
						
					
				});
	
		}

		
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/checknode/checkNode/list">约检节点列表</a></li>
		<li class="active"><a
			href="${ctx}/checknode/checkNode/form?id=${checkNode.id}">约检节点<shiro:hasPermission
					name="checknode:checkNode:edit">${not empty checkNode.id?'修改':'添加'}</shiro:hasPermission>
				<shiro:lacksPermission name="checknode:checkNode:edit">查看</shiro:lacksPermission></a></li>
	</ul>
	<br />
	<form:form id="inputForm" modelAttribute="checkNode"
		action="${ctx}/checknode/checkNode/save" method="post"
		class="form-horizontal">
		<form:hidden path="id" />
		<sys:message content="${message}" />
		<div class="control-group">
			<label class="control-label">门店</label>
			<div class="controls">
				<form:select path="storeId" class="input-medium needClear"
					id="store" onchange="findConsNameByStoreId()">
					<form:options items="${fns:getStoreList()}" itemLabel="label"
						itemValue="value" htmlEscape="false" />
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">工程模式：</label>
			<div class="controls">
				<form:radiobuttons path="projectMode" class="input-medium required" items="${fns:getDictList('project_mode')}" itemLabel="label" itemValue="value" htmlEscape="false"  onchange="findConsNameByStoreId()"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">节点名称</label>
			<div class="controls">
				<form:input path="qcCheckNodeName" htmlEscape="false"
					maxlength="100" class="input-xlarge required "/>
					<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">节点顺序</label>
			<div class="controls">
				<form:input path="qcCheckNodeIndex" htmlEscape="false"
					maxlength="11" class="input-xlarge number required" />
					<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">开工后第几天申请</label>
			<div class="controls">
				<form:input path="daysToApply" htmlEscape="false" maxlength="11"
					class="input-xlarge number required"/>
					<span class="help-inline"><font color="red">*</font> </span>
			</div>
			
		</div>	<div class="control-group">
			<label class="control-label">开工后第几天检查</label>
			<div class="controls">
				<form:input path="daysToCheck" htmlEscape="false" maxlength="11"
					class="input-xlarge number required"/>
					<span class="help-inline"><font color="red">*</font> </span>
			</div>

		</div>
		<div class="control-group">
			<label class="control-label">所属进度节点模板：</label>
			<div class="controls">
				<form:select path="constructionScheduleId" class="input-xlarge" id="constructionScheduleId" name="constructionScheduleId">
					<form:option value="${constructionScheduleId }">${checkNode.constructionScheduleName }</form:option>
				</form:select>
			</div>
		</div>
		<%-- <div class="control-group">
			<label class="control-label">所属进度节点模板</label>
			<div class="controls" id="cons">
				
				<!-- 如果没有门店, 就是添加 -->
				<c:if test="${checkNode.storeId==null }">
					<select class="input-medium" id="consId" name="constructionScheduleId">
							<option value=""></option>
						<c:forEach items="${ consList}" var="cons">
							<option value="${cons.constructionScheduleId }">${cons.constructionScheduleName }</option>
						</c:forEach>
					</select>
					

				</c:if>


				<!-- 如果有门店, 就是修改 -->

				<c:if test="${checkNode.storeId!=null }">
					<select class="input-medium needClear" id="consId">
						<option value="${constructionScheduleId }">${checkNode.constructionScheduleName }</option>
						<c:forEach items="${ consList}" var="cons">
							<option value="${cons.constructionScheduleId }">${cons.constructionScheduleName }</option>
						</c:forEach>
					</select>

				</c:if>




			</div>
		</div> --%>
		<div class="control-group">
			<label class="control-label">是否缴催 </label>
			<div class="controls">
				<form:radiobuttons path="isUrgePay"
					items="${fns:getDictList('yes_no')}" itemLabel="label"
					itemValue="value" htmlEscape="false" class="required" />
				<span class="help-inline"><font color="red">*</font> </span>



			</div>
		</div>


		<div class="control-group">
			<label class="control-label">是否基装约检节点 </label>
			<div class="controls">
				<form:radiobuttons path="isForBasicWork"
					items="${fns:getDictList('yes_no')}" itemLabel="label"
					itemValue="value" htmlEscape="false" class="required" />
				<span class="help-inline"><font color="red">*</font> </span>



			</div>
		</div>
		<div class="control-group">
			<label class="control-label">状态 </label>
			<div class="controls">
				<form:radiobuttons path="status"
					items="${fns:getDictList('biz_enable_status')}" itemLabel="label"
					itemValue="value" htmlEscape="false" class="required" />
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<%-- <div class="control-group">
			<label class="control-label">状态产生日期时间</label>
			<div class="controls">
				<input name="statusDatetime" type="text" readonly="readonly"
					maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${checkNode.statusDatetime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});" />
			</div>
		</div>
 --%>
		<div class="form-actions">
			<shiro:hasPermission name="checknode:checkNode:edit">
				<input id="btnSubmit" class="btn btn-primary" type="submit"
					value="保 存" />&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回"
				onclick="history.go(-1)" />
		</div>
	</form:form>
<script type="text/javascript">
	window.onload = function(){
		var storeId=$("#store").val();
		var projectMode = $(":radio[name='projectMode'][checked='checked']").val();
		if (storeId < 1) {

			return;
		}
		
		$.ajax({
			
			url: "${ctx}/checknode/checkNode/findConsByStoreId?storeId="+storeId+"&projectMode="+projectMode,
			type: "get",
			success : function(data){
				if(data!=null){
					var htmls = "";//<option value='' selected='true'></option>
			   		for(var i = 0; i < data.length; i++){
			   			htmls += "<option value='"+data[i].constructionScheduleId+"'>"+data[i].constructionScheduleName+"</option>";
			   		}
			   		$("#constructionScheduleId").html(htmls);
			   		var htmls = "";
				}
			}
					
				
		});
	}
</script>
</body>
</html>




