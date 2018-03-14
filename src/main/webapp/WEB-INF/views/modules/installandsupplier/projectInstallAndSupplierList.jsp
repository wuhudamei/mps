<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>主材安装供应商设置管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$('#myAbandonedModal').modal('hide');
			getstoreAndpMode();
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
		
		function getstoreAndpMode(radiobuttons){
			var storeid = $("#storeid").val();
			var radioValue = $("#projectModeid").val();
			
			$("#projectInstallItemNameid").html('');
		    $.ajax({
		        type : 'POST',
		        dataType : 'json',
		        url : '${ctx}/installitem/bizProjectInstallItem/ajaxGetInstallItem',
		        data : {
		        	storeId : storeid,
		        	projectMode : radioValue
		        },
		        success : function(data) {
// 		        	alert(data.resultMap[0].installItemName+":"+data.resultMap[0].id);
		            var html = "<select id='InstallItemNameId' name='projectInstallItemId' class='input-xlarge '>";
		            html += "<option value='' ></option>"
		            for (var i = 0; i < data.resultMap.length; i++) {
		            	html += "<option value='" + data.resultMap[i].id+"' >" + data.resultMap[i].installItemName + "</option>"
		            }
		            html+="</select>";
// 		            alert(html);
		            $("#projectInstallItemNameid").html('');
		            $("#projectInstallItemNameid").html(html);
		        }
		    })
		}
		
		
        function clearCondition(){
			 document.getElementById("searchForm").reset();
			
			 var inputObjs=jQuery("#searchForm input[type='text']"); 
			 for(var i=0;i<inputObjs.length;i++){ 
			 var inputObj = inputObjs[i]; 
			 inputObj.value=""; 
			 } 
			
			 var selectObjs = jQuery("#searchForm input[class='input-medium']"); 
			 for(var i=0;i<selectObjs.length;i++){ 
			 var selectObj = selectObjs[i]; 
			 selectObj.value=""; 
			 } 
		}

		
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/installandsupplier/projectInstallAndSupplier/list1">主材安装供应商设置列表</a></li>
		<shiro:hasPermission name="installandsupplier:projectInstallAndSupplier:edit"><li><a href="${ctx}/installandsupplier/projectInstallAndSupplier/form">主材安装供应商设置添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="projectInstallAndSupplier" action="${ctx}/installandsupplier/projectInstallAndSupplier/list1" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<input id="InstallItemName" name="InstallItemName" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>门店：</label>
				<form:select id="storeid" path="storeid" class="input-xlarge needClear" >
							<form:options items="${fns:getStoreList()}" itemLabel="label"
						itemValue="value" htmlEscape="false" />
				</form:select><span class="help-inline"><font color="red">*</font> </span>
			</li>
			<li><label>工程模式：</label>
				<form:select  id="projectModeid" path="projectMode" onclick="getstoreAndpMode(this)" class="input-medium needClear">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('project_mode')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>安装项：</label>
				<form:select  id="projectInstallItemNameid" path="installItemName1"   class="input-medium needClear">
				<form:option value="${bizSupplier.id}" label="${bizSupplier.supplierName}"/>
				</form:select>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="btns"><input class="btn btn-primary clearBtn" type="button" value="清空" onclick="clearCondition()"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>门店</th>
				<th>工程模式</th>
				<th>安装项</th>
				<th>对应供应商名称</th>
				<th>状态</th>
				<shiro:hasPermission name="installandsupplier:projectInstallAndSupplier:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="projectInstallAndSupplier">
			<tr>
			<td>
					${fns:getStoreLabel(projectInstallAndSupplier.storeid, '')}
			</td>
				 <td>
					${fns:getDictLabel(projectInstallAndSupplier.projectMode, 'project_mode', '')}
				</td>
				<td>
					${projectInstallAndSupplier.installItemName1}
				</td>
				<td><a  href="${ctx}/installandsupplier/projectInstallAndSupplier/querySupplierList?id=${projectInstallAndSupplier.id}"> 查看</a>
				</td>
					<td>
					<c:if test="${projectInstallAndSupplier.isOn=='1'}">
							<span style="color: #00EC00;">启用</span>
						</c:if> 
						<c:if test="${projectInstallAndSupplier.isOn=='0'}">
							<span style="color: red">停止</span>
					</c:if>
					</td>
				
				<shiro:hasPermission name="installandsupplier:projectInstallAndSupplier:edit"><td>
					<c:if test="${projectInstallAndSupplier.isOn=='1'}">
					<a href="${ctx}/installandsupplier/projectInstallAndSupplier/delete?id=${projectInstallAndSupplier.id}&isOn=0" onclick="return confirmx('确认要停用该主材安装供应商设置吗？', this.href)">停用</a>
						</c:if> 
					<c:if test="${projectInstallAndSupplier.isOn=='0'}">
					<a href="${ctx}/installandsupplier/projectInstallAndSupplier/delete?id=${projectInstallAndSupplier.id}&isOn=1" onclick="return confirmx('确认要停用该主材安装供应商设置吗？', this.href)">启用</a>
						</c:if> 
    				<a href="${ctx}/installandsupplier/projectInstallAndSupplier/formUpdata?id=${projectInstallAndSupplier.id}">修改</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
	
	

</body>
</html>