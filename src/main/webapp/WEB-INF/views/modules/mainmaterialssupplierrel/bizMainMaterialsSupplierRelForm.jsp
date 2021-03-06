<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>主材对应供应商管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					checkEffectiveDate($('#mainMaterialsId').val(),$('#supplierId').val(),$('#effectiveDate').val(),form)
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
		
		//检查同一个供应商同一天是否只有一条记录
		function checkEffectiveDate(mainMaterialsId,supplierId,effectiveDate,form){
			 $.ajax({
			    url: "${ctx}/mainmaterialssupplierrel/bizMainMaterialsSupplierRel/checkEffectiveDate",    //请求的url地址
			    dataType: "json",   //返回格式为json
			    async: true, //请求是否异步，默认为异步，这也是ajax重要特性
			    data: { 
			    	"mainMaterialsId":mainMaterialsId,
			    	"supplierId": supplierId, 
			    	"effectiveDate": effectiveDate
			    	},    //参数值
			    type: "POST",   //请求方式
			    beforeSend: function() {
			        //请求前的处理
			    },
			    success: function(req) {
			       if(req=="1"){//检查通过
			    	   loading('正在提交，请稍等...');
			    	   form.submit();
			       }else{
			    	   alert("错误！，同一个供应商同一天生效日期只能有一条记录");
			       }
			    }
			}); 
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/mainmaterialssupplierrel/bizMainMaterialsSupplierRel/">主材对应供应商列表</a></li>
		<li class="active"><a href="${ctx}/mainmaterialssupplierrel/bizMainMaterialsSupplierRel/form?id=${bizMainMaterialsSupplierRel.id}">主材对应供应商<shiro:hasPermission name="mainmaterialssupplierrel:bizMainMaterialsSupplierRel:edit">${not empty bizMainMaterialsSupplierRel.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="mainmaterialssupplierrel:bizMainMaterialsSupplierRel:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<sys:message content="${message}"/>
	<form:form id="inputForm" modelAttribute="bizMainMaterialsSupplierRel" action="${ctx}/mainmaterialssupplierrel/bizMainMaterialsSupplierRel/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<form:hidden path="delFlag" value="0"/>
		<form:hidden path="mainMaterialsId" htmlEscape="false" maxlength="100" class="input-xlarge required"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">供应商名称：</label>
			<div class="controls">
				<form:select path="supplierId" id="supplierId" class="input-xlarge " required="required">
					<form:option value="" label=""/>
					<form:options items="${fns:getSupplierList()}" itemLabel="supplierName" itemValue="id" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">供货周期（天）：</label>
			<div class="controls">
				<form:input path="supplierCycle" htmlEscape="false" maxlength="3" min="0"  class="input-xlarge number" required="required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">供应商供货价（元）：</label>
			<div class="controls">
				<form:input path="supplierPrice" htmlEscape="false" min="0"  class="input-xlarge  number" required="required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">工人结算价（元）：</label>
			<div class="controls">
				<form:input path="laborPrice" htmlEscape="false" min="0"  class="input-xlarge number " required="required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">版本号：</label>
			<div class="controls">
				<form:input path="version" htmlEscape="false" maxlength="4" min="0"  class="input-xlarge digits" placeholder="系统自动生成" disabled="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">生效日期：</label>
			<div class="controls">
				<input name="effectiveDate" id="effectiveDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${bizMainMaterialsSupplierRel.effectiveDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" required="required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>