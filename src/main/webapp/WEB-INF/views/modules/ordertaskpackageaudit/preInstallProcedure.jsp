<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>新增一项工序</title>
	<meta name="decorator" content="default"/>
</head>
<body>
	<ul class="nav nav-tabs">
		<c:if test="${flag == '0'}">
			<li>
				<c:if test="${isSpecial == '0' }"><a href="${ctx}/ordertaskpackageaudit/orderTaskpackageAudit/stayList">待审核任务包列表</a></c:if>
				<c:if test="${isSpecial == '1' }"><a href="${ctx}/ordertaskpackageaudit/orderTaskpackageAudit/staySpectialList">待审核特殊任务包列表</a></c:if>
			</li>
			<li class="active"><a>查看详情</a></li>
		</c:if>
		<c:if test="${flag == '1'}">
			<li><a href="${ctx}/ordertaskpackageaudit/orderTaskpackageAudit/list">已审核任务包列表</a></li>
			<li class="active"><a>查看详情</a></li>
		</c:if>
	</ul>
	<%-- <ul class="nav nav-tabs">
		<li><a href="${ctx}/ordertaskpackageaudit/orderTaskpackageAudit/preInstallProcedure?packageCode=
			${orderTaskpackage.packageCode }&orderId=${orderTaskpackage.orderId }&taskpackageId=${orderTaskpackage.id }">新增一项工序</a></li>
	</ul>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/ordertaskpackageaudit/orderTaskpackageAudit/preInstallProcedure?packageCode=
			${orderTaskpackage.packageCode }&orderId=${orderTaskpackage.orderId }&taskpackageId=${orderTaskpackage.id }">返回上一步</a></li>
	</ul> --%>

	<form:form id="inputForm" modelAttribute="templatRelList" action="${ctx}/order/order/save" method="post" class="form-horizontal">
	<sys:message content="${message}" />
	<table>
			<tr>
				<td><div class="control-group">
						<label class="control-label">任务包名称：</label>
						<div class="controls">
							<form:input path="" htmlEscape="false" maxlength="18" value="${templat.templatName }"
								class="input-xlarge " disabled="true" />
							<span class="help-inline"><font color="red">*</font></span>
						</div>
				</div></td>
			</tr>
			<tr>
				<td><div class="control-group">
						<label class="control-label">工序编号：</label>
						<div class="controls">
							<form:input path="" htmlEscape="false" id="procedureNo" readonly="true" maxlength="100"
								class="input-xlarge " />
							<span class="help-inline"><font color="red">*</font></span>
						</div>
				</div></td>
			</tr>
			<tr>
				<td><div class="control-group">
						<label class="control-label">工序名称：</label>
						<div class="controls">
							<select class="input-xlarge" id="procedureName" onchange="procedureValue();">
								<option value="">请选择</option>
							<c:forEach items="${procedureList }" var="p">
								<option value="${p.procedureNo }">${p.procedureName }</option>
							</c:forEach>
							</select>
						</div>
				</div></td>
			</tr>
		</table>
		
		<div class="form-actions">
			<shiro:hasPermission name="order:order:edit">
				<input id="btnSubmit" class="btn btn-primary" type="button"
					value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回"
				onclick="history.go(-1)" />
		</div>
	</form:form>
	
	<script type="text/javascript">
		$(document).ready(function() {
			//绑定onclick事件
			$("#btnSubmit").bind('click',subData);
		});
		
		function procedureValue(){
			 var value = $("#procedureName").val();
			 $("#procedureNo").val(value);
		}
		
		function subData(){
			 var procedureName = $("#procedureName").val();
			 var procedureNo = $("#procedureNo").val();
			 var taskPackageTemplatId = ${templat.id };
			 var taskPackageTemplatNo = '${templat.no}';
			 var orderId = ${orderId};
			 var taskpackageId = '${taskpackageId}';
			 
			 if(procedureNo == ""){
				 alert("工序编号不能为空");
				 return false;
			 }
			 
			 if(procedureName == ""){
				 alert("工序名称不能为空");
				 return false;
			 }
			 
			 //跳转页面
			 var url = "${ctx}/ordertaskpackageaudit/orderTaskpackageAudit/preUpdate?orderId="+orderId+"&taskpackageId="+taskpackageId+"&packCode="+taskPackageTemplatNo+"&flag="+${flag}+"&isSpecial="+${isSpecial};
			 
			//禁用提交按钮
			$("#btnSubmit").css("color","#CCC");
			$("#btnSubmit").unbind("click");
			 $.ajax({
					url : "${ctx}/ordertaskpackageprocedure/orderTaskpackageProcedure/insertProcedure",
					type : "POST",
				    //async:false,
				    data : {
				    	procedureName : procedureName,
				    	procedureNo : procedureNo,
				    	taskPackageTemplatId : taskPackageTemplatId,
				    	taskPackageTemplatNo : taskPackageTemplatNo,
				    	orderId : orderId,
				    	taskpackageId : taskpackageId
					},
				  	success : function(data){
					  //alert("ajax返回的数据："+data);
						if(data == 0){
						  alert("操作成功!");
						 window.location.href = url;
					  	}
						if(data == 1){
							alert("新增数据错误!");
						 	return false;
					  	}
						if(data == 2){
							alertx("工序的价格获取不到，是按合同开工日期与价格生效日期进行匹配的，请调整工序定额的价格生效日期");
						}
				  }
			});
		}
	</script>
</body>
</html>