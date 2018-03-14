<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>催促验收列表</title>
	<meta name="decorator" content="default"/>
	<meta content="*" name="Access-Control-Allow-Origin"><!--跨域请求  -->
	<script type="text/javascript">
		$(document).ready(function() {
			getDepartments();
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
		function getDepartments(){
			$("#enginDepartId").html('');
			$.ajax({
				url:'${ctx}/engdept/bizEngineeringDepartment/departmentList',
				type:'post',
				dataType:'json',
				data:{
					'storeId':$('#storeId').val(),
					'projectMode':$('#projectMode').val(),
					'employeeId':$('#employeeId').val()
				},
				success:function(data){
					if(data == null){
						$("#enginDepartId").append('');
					} else {
						var html = "<option value=''></option>";
						for(var i=0;i<data.length;i++){
							var sec = "";
							if('${urgeCheck.engineDepartId}' == data[i].value){
			            		sec = "selected='selected'";
			            		$("#s2id_enginDepartId .select2-chosen").html(data[i].label);
			            	}
							html += "<option value='" + data[i].value +"' " + sec + ">" + data[i].label + "</option>"
						}
						html+='';
		        		$("#enginDepartId").append(html);
					}
				}
			});
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/ordertaskpackageaudit/orderTaskpackageAudit/urgeCheckPage">催促验收列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="urgeCheck" action="${ctx}/ordertaskpackageaudit/orderTaskpackageAudit/urgeCheckList" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>门店：</label>
				<form:select path="storeId" class="input-medium needClear" onchange="getDepartments()" id="storeId">
					<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
			</li>
			<li><label>工程模式：</label>
				<c:if test="${empty gongcheng}">
					<form:select path="projectMode" id="projectMode" class="input-medium" disabled="true" onchange="getDepartments()">
						<form:option value="" label=""/>
						<form:options items="${fns:getDictList('package_project_mode')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
				<c:if test="${!empty gongcheng}">
					<form:select path="projectMode" id="projectMode" class="input-medium needClear" onchange="getDepartments()">
						<form:option value="" label=""/>
						<form:options items="${fns:getDictList('package_project_mode')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
			</li>
			<li><label>区域：</label>
				<form:select path="engineDepartId" id="enginDepartId" class="input-medium needClear">
					<form:option value="${urgeCheck.engineDepartId }" label="${urgeCheck.departmentName }" />
				</form:select>
			</li>
			<li><label>订单编号：</label>
				<form:input path="orderNumber" htmlEscape="false" maxlength="18" class="input-medium needClear"/>
			</li>

			<li><label>客户姓名：</label>
				<form:input path="customerName" htmlEscape="false" maxlength="18" class="input-medium needClear"/>
			</li>
			<li><label>工人组长姓名：</label>
				<form:input path="groupRealname" htmlEscape="false" maxlength="18" class="input-medium needClear"/>
			</li>
			<li><label style="width:160px;">催促验收时间：</label>
				<input name="beginUrgeTime" type="text" id="beginUrgeTime" readonly="readonly" maxlength="20" class="input-medium Wdate needClear"
					value="<fmt:formatDate value="${bizOrderTaskpackage.beginUrgeTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'endUrgeTime\')}',isShowClear:false});"/> - 
				<input name="endUrgeTime" type="text" id="endUrgeTime" readonly="readonly" maxlength="20" class="input-medium Wdate needClear"
					value="<fmt:formatDate value="${bizOrderTaskpackage.endUrgeTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'beginUrgeTime\')}',isShowClear:false});"/>
			</li>
			<li class="btns" style="width:100px"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="btns"><input class="btn btn-primary clearBtn" type="button" value="清空"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>门店</th>
				<th>工程模式</th>
				<th>区域</th>
				<th>催促验收时间</th>
				<th>工人组长</th>
				<th>订单编号</th>
				<th>顾客信息</th>
				<th>任务包名称</th>
				<th>项目经理</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="urgeCheck" varStatus="index">
			<tr>
				<td>
					${fns:getStoreLabel(urgeCheck.storeId, '')}
				</td>
				<td>${fns:getDictLabel(urgeCheck.projectMode, 'package_project_mode', '')}</td>
				<td>
					${urgeCheck.departmentName }
				</td>
				<td>
					<fmt:formatDate value="${urgeCheck.urgeTime }" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${urgeCheck.groupRealname }
				</td>
				<td>
					${urgeCheck.orderNumber }
				</td>
				<td>
					${urgeCheck.customerMessage }-${urgeCheck.customerName }
				</td>
				<td>
					${urgeCheck.packageName }
				</td>
				<td>
					${urgeCheck.itemCustomer }
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>	
</body>
</html>