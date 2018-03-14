<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>质检明细</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			getDepartments();
		});
		
		function getDepartments(){
			
			var html3 = '<option value=""></option>';
			var storeId = $("#storeid").val();
			var projectModeValue = $("#projectMode").val();
			if (storeId=="" ||projectModeValue=="" ||undefined==storeId ||undefined==projectModeValue) {
				return;
			}
			//根据门店个,工程模式    动态加载工程区域
			$.ajax({
				url : "${ctx}/order/order/findEngineDepartmentBystoreIdAndProjectMode?storeId="
								+ storeId + "&projectModeValue=" + projectModeValue,
				type : "get",
				success : function(data) {
					if (null!= data && data.length > 0) {

						/* for (var v = 0; v < data.length; v++) {
							html3 +='<option value="'+data[v].engineDepartId+'">'+data[v].engineDepartName+'</option>' 
						} */
						
						for (var v = 0; v < data.length; v++) {
							if('${bizEmployee.enginDepartId}' == data[v].engineDepartId){
								$("#s2id_enginDepartId .select2-chosen").html(data[v].engineDepartName);
								html3 = html3 + "<option value='"+data[v].engineDepartId+"' selected='selected'>"+data[v].engineDepartName+"</option>";
							}else{
								html3 = html3 + "<option value='"+data[v].engineDepartId+"'>"+data[v].engineDepartName+"</option>";
							}
							
							/* html3 +='<option value="'+data[v].engineDepartId+'">'+data[v].engineDepartName+'</option>'  */
						}
								
						$("#enginDepartId").html(html3);
					} else {
						$("#enginDepartId").html(html3);
					}
				}

			});
	}
		
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }

		function clearButton(){
			$("input[name='noRelateCard']").removeAttr("checked");
			$("input[name='noRelateGroup']").removeAttr("checked");
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
	<shiro:hasPermission name="inspector:bizInspectorExchange:view">
		<li class="active"><a href="${ctx}/exchangeOrderDetails/exchangeOrderDetails/inspector_list?empType=1">质检员信息</a></li>
	</shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="bizEmployee" action="${ctx}/exchangeOrderDetails/exchangeOrderDetails/findinspectorlist?empType=1" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>门店：</label>
				<form:select path="storeid" class="input-medium needClear" onchange="getDepartments()">
					<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>工程模式：</label>
				<form:select path="projectMode" class="input-medium needClear" id="projectMode" onchange="getDepartments()">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('project_mode')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>区域：</label>
				<form:select path="enginDepartId"  class="input-medium needClear" id="enginDepartId" >
					<form:option value=""></form:option>
				</form:select>
			</li>
			
			<li><label>质检员：</label>
				<form:input path="realname" htmlEscape="false" maxlength="11" class="input-medium needClear"/>
			</li>
			
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>

			<li class="btns"><input class="btn btn-primary clearBtn" type="button" value="清空" onclick="clearButton()"/></li>

			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>门店</th>
				<th>工程模式</th>
				<th>区域</th>
				<th>质检员</th>
				<th>手机号</th>
				<th>星级</th>
				<th>被换累计次数</th>
				<shiro:hasPermission name="inspector:bizInspectorExchange:view"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="bizEmployee">
			<tr>
				<td>
					${fns:getStoreLabel(bizEmployee.storeid, '')}
				</td>
				<td>
					${fns:getDictLabel(bizEmployee.projectMode, 'employee_project_mode', '')}
				</td>
				<td>
					${bizEmployee.departmentName}
				</td>
				<td>
					${bizEmployee.realname}
				</td>
				<td>
					${bizEmployee.phone}
				</td>
				<td>
					${fns:getDictLabel(bizEmployee.star, 'emp_star', '')}
				</td>
				<td>
					${bizEmployee.exchangeOrderTimes}
				</td>
				<shiro:hasPermission name="inspector:bizInspectorExchange:view">
				<td>
    				<a href="${ctx}/exchangeOrderDetails/exchangeOrderDetails/inspectorDetails?id=${bizEmployee.id}">详情</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>