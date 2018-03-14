<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>施工项管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		var  myArray;
		var  myArray1;
		var p = 0;
		$(document).ready(function() {
			myArray = $.makeArray($("#valuationMethod option"));
			myArray1 = $.makeArray($("#subordinateCategory option"));
            dynamicLoadingMethod($("#valuationMethod option[value = ${projectIntem.valuationMethod}]"));
			var status = '${projectIntem.status}';
			var isdefault = '${projectIntem.isDefault}';
			if(status != ''){
				$(":radio[name='status'][value='" + status + "']").prop("checked", "checked");
			}else{
				$(":radio[name='status'][value='" + 1 + "']").prop("checked", "checked");
			}
			
			if(isdefault != ''){
				$(":radio[name='isDefault'][value='" + isdefault + "']").prop("checked", "checked");
			}else{
				$(":radio[name='isDefault'][value='" + 0 + "']").prop("checked", "checked");
			}
			
			//使用平台回显
			var checkeds = '${projectIntem.usingPlatform}';
			var v1 = checkeds.replace(/\s/g,'')
			var checkArray =v1.split(",");
			for(var i=0;i<checkArray.length;i++){
				$("input[value="+checkArray[i]+"][name=usingPlatform]").attr("checked",true);
		 	}
			//施工项类型
			var projectIntemMold = '${projectIntem.projectIntemMold}';
			projectIntemMold = projectIntemMold.replace(/\s/g,'')
			var projectIntemMoldArray =projectIntemMold.split(",");
			for(var i=0;i<projectIntemMoldArray.length;i++){
				$("input[value="+projectIntemMoldArray[i]+"][name=projectIntemMold]").attr("checked",true);
		 	}
			/*dynamicLoading();*/

            var usingPlatform11 = $("#usingPlatform1").attr("checked");
            var usingPlatform22 = $("#usingPlatform2").attr("checked");
            var usingPlatform1 = $.trim(usingPlatform11);
            var usingPlatform2 = $.trim(usingPlatform22);

            if(usingPlatform1 == '' && usingPlatform1 == ''){

            }

            if(usingPlatform2 == 'checked' && usingPlatform1 == ''){
                $("#valuationMethod").html(myArray)
            }

            if(usingPlatform1 == 'checked' && usingPlatform2 == 'checked' || usingPlatform1 == 'checked'){
                $("#valuationMethod option").each(function(index,element){
                    var provalue = $(this).val();
                    if(provalue != 'fixedUnitPrice'){
                        $("#valuationMethod option[value="+provalue+"]").remove();
                    }
                });

            }
           /* alert($("#valuationMethod option[value = ${projectIntem.valuationMethod}]").val())*/
            $("#valuationMethod option[value = ${projectIntem.valuationMethod}]").attr("selected","selected");



			
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
		
		function dynamicLoading(){
			var usingPlatform11 = $("#usingPlatform1").attr("checked");
			var usingPlatform22 = $("#usingPlatform2").attr("checked");
			var usingPlatform1 = $.trim(usingPlatform11);
			var usingPlatform2 = $.trim(usingPlatform22);
			if(usingPlatform1 == '' && usingPlatform1 == ''){
				
			}
			
			if(usingPlatform2 == 'checked' && usingPlatform1 == ''){
				$("#valuationMethod").html(myArray)
			 }
			
			if(usingPlatform1 == 'checked' && usingPlatform2 == 'checked' || usingPlatform1 == 'checked'){
				$("#valuationMethod option").each(function(index,element){
	                var provalue = $(this).val();
	                if(provalue != 'fixedUnitPrice'){
	                    $("#valuationMethod option[value="+provalue+"]").remove();
	                }
				});
				
			}
                var myarr = $.makeArray($("#valuationMethod option"));
                $("#s2id_valuationMethod").find(".select2-chosen").text($(myarr[0]).text());
                $(myarr[0]).attr("selected","selected");
                dynamicLoadingMethod(myarr[0]);



		}
		
		function dynamicLoadingMethod(a) {
            var danjia = $(a).val()
            if (danjia == 'fixedUnitPrice') {
                $("#subordinateCategory").html(myArray1)
            }
            if (danjia == 'foundationPileTotal') {
                $("#subordinateCategory").html(myArray1[1])
                $("#subordinateCategory").append(myArray1[2])
            }
            if (danjia == 'renovationFoundationPile') {
                $("#subordinateCategory").html(myArray1[2])
            }
            if (danjia == 'dismantleFoundationPile') {
                $("#subordinateCategory").html(myArray1[4])
                $("#subordinateCategory").append(myArray1[5])
            }
            if (danjia == 'demolitionProjectTotal') {
                $("#subordinateCategory").html(myArray1[5])
            }
            if (p != '0') {
                var temp = $.makeArray($("#subordinateCategory option"));
                $("#s2id_subordinateCategory").find(".select2-chosen").text($(temp[0]).text());
                $(temp[0]).attr("selected", "selected");
            }
            p = p + 1;
		}
		
		
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/projectitem/projectIntem/">施工项列表</a></li>
		<li class="active"><a href="${ctx}/projectitem/projectIntem/form?id=${projectIntem.projectItemId}">施工项<shiro:hasPermission name="projectitem:projectIntem:edit">${not empty projectIntem.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="projectitem:projectIntem:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="projectIntem" action="${ctx}/projectitem/projectIntem/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">施工项编码：</label>
			<div class="controls">
				<form:input path="projectIntemCode" htmlEscape="false" maxlength="64" class="input-xlarge " readonly="true" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">施工项分类：</label>
			<div class="controls">
			<form:select path="projectIntemTypeId" class="input-xlarge required" id="projectIntemTypeId" name="projectIntemTypeId">
					
					<!-- 不为空, 回显 -->
					<c:if test="${not empty projectIntem.projectItemId}">
					<form:option value="${projectIntem.projectIntemTypeId }">${projectIntem.projectIntemTypeName }</form:option>
					</c:if>
					
					
					<!-- id是空 表明是新增 -->
					<c:if test="${ empty projectIntem.projectItemId}">
					<form:option value=""></form:option>
					</c:if>
					<c:forEach items="${projectItemTypeList }" var="type">
					<form:option value="${type.projectItemTypeId }">${type.projectIntemTypeName }</form:option>
					</c:forEach>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">施工项名称：</label>
			<div class="controls">
				<form:input path="projectIntemName" htmlEscape="false" maxlength="100" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">单位：</label>
			<div class="controls">
				<form:select path="projectIntemUnit" class="input-xlarge required">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('biz_build_unit')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
					<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">使用平台：</label>
			<div class="controls">
			<!-- usingPlatform -->
				<form:checkboxes path="usingPlatform" class="required" items="${fns:getDictList('using_platform')}" itemLabel="label" itemValue="value" htmlEscape="false" onchange="dynamicLoading()"/>
					<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">计价方式：</label>
			<div class="controls">
				<form:select path="valuationMethod" class="input-xlarge required" onchange="dynamicLoadingMethod(this)">
				<!-- valuation_method -->
					<form:options items="${fns:getDictList('valuation_method')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		
		
		
		<div class="control-group">
			<label class="control-label">套餐类型：</label>
			<div class="controls">
				<form:select path="groupType" class="input-xlarge required">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('group_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font></span>
			</div>
		</div>
		
		
		
		<div class="control-group">
			<label class="control-label">施工项类型：</label>
			<div class="controls">
			<!-- project_item_type projectIntemMold -->
				<form:checkboxes path="projectIntemMold" class="required" items="${fns:getDictList('project_item_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">所属类别：</label>
			<div class="controls">
				<form:select path="subordinateCategory" class="input-xlarge required">
				<!-- subordinate_category -->
					<form:options items="${fns:getDictList('subordinate_category')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		
		
		<div class="control-group">
			<label class="control-label">状态：</label>
			<div class="controls">
				<form:radiobuttons path="status" items="${fns:getDictList('biz_enable_status')}" itemLabel="label" itemValue="value" htmlEscape="false" class=""/>
			</div>
		</div>
		
		
		<div class="control-group">
			<label class="control-label">是否默认项：</label>
			<div class="controls">
				<form:radiobuttons path="isDefault" items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false" class=""/>
			</div>
		</div>
		
		
		<div class="control-group">
			<label class="control-label">施工项详情：</label>
			<div class="controls">
				<form:textarea path="projectIntemDetail" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		
		<div class="form-actions">
			<shiro:hasPermission name="projectitem:projectIntem:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>