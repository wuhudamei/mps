<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>墙地砖复尺管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
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
			datetimeUp();
		});
		
		
		function datetimeUp(){
			var hidate=$("#hiplanMeasureDateid").val();
			
			if(null!=hidate && hidate!="" && undefined!=hidate){
			$("#planMeasureDate").val(hidate);
			}
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/materialwallfloor/wallRecheck/">墙地砖复尺列表</a></li>
		<li class="active"><a href="${ctx}/materialwallfloor/wallRecheck/agreRecheckUpdate?id=${wallRecheck.id}">墙地砖复尺<shiro:hasPermission name="materialwallfloor:wallRecheck:edit">${not empty wallRecheck.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="materialwallfloor:wallRecheck:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="wallRecheck" action="${ctx}/materialwallfloor/wallRecheck/agreRecheckUpdate" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">订单编号：</label>
			<div class="controls">
			${wallRecheck.orderNmber}
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">门店：</label>
			<div class="controls">
			${fns:getStoreLabel(wallRecheck.storeId, '')}
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">区域：</label>
			<div class="controls">
			${fns:getElacLabel(wallRecheck.orderacceptarea, '')}
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">工程模式：</label>
			<div class="controls">
			${fns:getDictLabel(wallRecheck.projectMode, 'project_mode', '')}
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">项目经理：</label>
			<div class="controls">
			${wallRecheck.itemManager}
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">设计师：</label>
			<div class="controls">
			${wallRecheck.designerName}
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">地址：</label>
			<div class="controls">
				${wallRecheck.coveredAdd}
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">期望复核日期：</label>
			<div class="controls">
				<input name="planMeasureDate" id="planMeasureDate" type="text"  maxlength="20" class="input-medium Wdate required "
					value="<fmt:formatDate value="${planMeasureDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/> 	<span class="help-inline"><font color="red">*</font> </span>
				<input  id="hiplanMeasureDateid" type="hidden"  maxlength="20" class="input-medium Wdate required "
					value="<fmt:formatDate value="${wallRecheck.planMeasureDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>

			</div>
		</div>

		<div class="control-group">
			<label class="control-label">预算面积：</label>
			<div class="controls">
				${wallRecheck.squareBudget}
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">实际下单面积：</label>
			<div class="controls">
				${wallRecheck.squarePurchase}
			</div>
		</div>
		

		<div class="control-group">
			<label class="control-label">复尺备注：</label>
			<div class="controls">
				<form:textarea path="recheckRemarks" htmlEscape="false" onkeyup="this.value = this.value.substring(0,100)" placeholder="最多输入100个汉字"  maxLength="100" class="input-xlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="materialwallfloor:wallRecheck:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>