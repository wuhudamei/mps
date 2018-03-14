<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>厂家复尺处理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$(":radio[name='status'][value='" + 1 + "']").prop("checked", "checked");
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					/* if(check()){
						loading('正在提交，请稍等...');
						form.submit();
					} */
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
		
		/* function check(){
			var supplierConfirmDate = $("#supplierConfirmDate").val();
			var materialDepartmentDealReply = $("#materialDepartmentDealReply").val();
			if("" == supplierConfirmDate && "" == materialDepartmentDealReply){
				alertx("请填写供应商确认日期或者回复内容。")
				return false;
			} 
			return true;
		} */
		
				
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/bizorderchecksize/bizOrderChecksize/list">上传厂家复尺列表</a></li>
		<li class="active"><a href="#">复尺处理</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="bizOrderChecksize" action="${ctx}/bizorderchecksize/bizOrderChecksize/save" method="post" class="form-horizontal">
		<form:hidden path="orderChecksizeId"/>
		<sys:message content="${message}"/>	
		
		<input type="hidden" id="storeId" name="storeId" value="${orderChecksize.storeId }">
		<input type="hidden" id="projectMode" name="projectMode" value="${orderChecksize.projectMode }">
		<input type="hidden" id="orderNumber" name="orderNumber" value="${orderChecksize.orderNumber }">
		<input type="hidden" id="customerName" name="customerName" value="${orderChecksize.customerName }">
		<input type="hidden" id="itemManager" name="itemManager" value="${orderChecksize.itemManager }">
		<input type="hidden" id="checksizeType" name="checksizeType" value="${orderChecksize.checksizeType }">
		<input type="hidden" id="checksizeStatus" name="checksizeStatus" value="${orderChecksize.checksizeStatus }">
		
		<input type="hidden" name="beginContractStartDate" id="beginContractStartDate" readonly="readonly" maxlength="20" 
					value="<fmt:formatDate value="${orderChecksize.beginContractStartDate}" pattern="yyyy-MM-dd"/>"/>
		<input type="hidden" name="endContractStartDate" id="endContractStartDate" readonly="readonly" maxlength="20" 
					value="<fmt:formatDate value="${orderChecksize.endContractStartDate}" pattern="yyyy-MM-dd"/>"/>
		<input type="hidden" name="beginCreateDate" id="beginCreateDate" readonly="readonly" maxlength="20" 
					value="<fmt:formatDate value="${orderChecksize.beginCreateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"/>
		<input type="hidden" name="endCreateDate" id="endCreateDate" readonly="readonly" maxlength="20" 
					value="<fmt:formatDate value="${orderChecksize.endCreateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"/>
		<input type="hidden" name="beginChecksizeDate" id="beginChecksizeDate" readonly="readonly" maxlength="20" 
					value="<fmt:formatDate value="${orderChecksize.beginChecksizeDate}" pattern="yyyy-MM-dd"/>"/>
		<input type="hidden" name="endChecksizeDate" id="endChecksizeDate" readonly="readonly" maxlength="20" 
					value="<fmt:formatDate value="${orderChecksize.endChecksizeDate}" pattern="yyyy-MM-dd"/>"/>
		<input type="hidden" name="beginSupplierConfirmDate" id="beginSupplierConfirmDate" readonly="readonly" maxlength="20" 
					value="<fmt:formatDate value="${orderChecksize.beginSupplierConfirmDate}" pattern="yyyy-MM-dd"/>"/>
		<input type="hidden" name="endSupplierConfirmDate" id="endSupplierConfirmDate" readonly="readonly" maxlength="20" 
					value="<fmt:formatDate value="${orderChecksize.endSupplierConfirmDate}" pattern="yyyy-MM-dd"/>"/>
		
		<div class="control-group">
			<div class="controls">
				<h2>
					${bizOrderChecksize.communityName }-${bizOrderChecksize.buildNumber }-${bizOrderChecksize.buildUnit }-${bizOrderChecksize.buildRoom }-${bizOrderChecksize.customerName }
				</h2>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">复尺内容：</label>
			<div class="controls">
				${bizOrderChecksize.checksizeTypeName }
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">供应商确认时间：</label>
			<div class="controls">
				<input name="supplierConfirmDate" id="supplierConfirmDate" type="text" readonly="readonly"
					maxlength="20" class="input-medium Wdate required"
					value="<fmt:formatDate value="${bizOrderChecksize.supplierConfirmDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">回复：</label>
			<div class="controls">
				<form:textarea path="materialDepartmentDealReply" htmlEscape="false"
					maxlength="500" class="input-xlarge " />
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="bizorderchecksize:bizOrderChecksize:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>