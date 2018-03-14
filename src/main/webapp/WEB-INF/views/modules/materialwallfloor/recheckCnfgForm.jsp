<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>复尺配置表管理</title>
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
		});
	    function fix_amountthis(obj){
	    	
	        //先把非数字的都替换掉，除了数字和. 
		    obj.value = obj.value.replace(/[^\d.]/g,""); 
		    //必须保证第一个为数字而不是. 
		    obj.value = obj.value.replace(/^\./g,""); 
		    //保证只有出现一个.而没有多个. 
		    obj.value = obj.value.replace(/\.{2,}/g,"."); 
		    //保证.只出现一次，而不能出现两次以上 
		    obj.value = obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$",".");
		    //只能输入两个小数 
		   obj.value = obj.value.replace(/^(\-)*(\d+)\.(\d\d).*$/,'$1$2.$3');
	        
	     }
  
	       function checkNum(obj)
	         {obj.value=obj.value.replace(/[^\-\d]/g,'');
	             if(obj.value.indexOf('-')>=0){
	                 obj.value='-'+obj.value.replace(/-/g,'');
	             }
	         }

	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/materialwallfloor/recheckCnfg/">复尺配置表列表</a></li>
		<li class="active"><a href="${ctx}/materialwallfloor/recheckCnfg/form?price=${recheckCnfg.price}">复尺配置表<shiro:hasPermission name="materialwallfloor:recheckCnfg:edit">${not empty recheckCnfg.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="materialwallfloor:recheckCnfg:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="recheckCnfg" action="${ctx}/materialwallfloor/recheckCnfg/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">材料单价：</label>
			<div class="controls">
				<form:input path="price" htmlEscape="false" class="input-xlarge "  onkeyup="fix_amountthis(this)" onafterpaste="fix_amountthis(this)" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">面积超出上限比例：</label>
			<div class="controls">
				<form:input path="squareOverMaxRate" htmlEscape="false" class="input-xlarge "  onkeyup="checkNum(this)" maxlength="2" onafterpaste="checkNum(this)" />
			</div>
		</div>
<!-- 		<div class="control-group"> -->
<!-- 			<label class="control-label">备注：</label> -->
<!-- 			<div class="controls"> -->
<%-- 				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/> --%>
<!-- 			</div> -->
<!-- 		</div> -->
		<div class="form-actions">
			<shiro:hasPermission name="materialwallfloor:recheckCnfg:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>