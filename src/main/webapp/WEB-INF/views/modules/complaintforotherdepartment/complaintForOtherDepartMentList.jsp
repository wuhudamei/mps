<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>投诉管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
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
		<li class="active"><a href="${ctx}/complaintforotherdepartment/complaintForOtherDepartMent/">投诉列表</a></li>
		<shiro:hasPermission name="complaintforotherdepartment:complaintForOtherDepartMent:edit"><li><a href="${ctx}/complaintforotherdepartment/complaintForOtherDepartMent/add">投诉添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="complaintForOtherDepartMent" action="${ctx}/complaintforotherdepartment/complaintForOtherDepartMent/list" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<li><label>门店：</label>
			<form:select path="storeId" class="input-medium needClear" onchange="findEngineListByStoreIdAndProjectMode()" id="sel">
				<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
			</form:select>
		</li>
		<li><label>工程模式：</label>


				<form:select path="projectMode" class="input-medium needClear">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('project_mode')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>


		</li>
		<li><label>区域：</label>
			<form:select path="departId" class="input-medium needClear" id="engineDepartSelect">
				<form:options items="${fns:getEngineListWithUserConditionsForBiddenChange()}"   itemLabel="label" itemValue="value" htmlEscape="false"/>
			</form:select>
		</li>
		<li><label>订单编号：</label>
			<form:input path="orderNumber" htmlEscape="false" maxlength="100" class="input-medium"/>
		</li>


		<li><label>状态：</label>
			<form:select path="status" class="input-medium needClear">
				<form:option value="" label=""/>
				<form:options items="${fns:getDictList('preComplaintStatus')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
			</form:select>


		</li>




		<li><label>客户姓名：</label>
			<form:input path="customerName" htmlEscape="false" maxlength="20" class="input-medium"/>
		</li>



		<li><label>项目经理：</label>
			<form:input path="managerName" htmlEscape="false" maxlength="20" class="input-medium"/>
		</li>




		<li><label>问题创建时间：</label>
			<input name="startDate" id="startDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
				   value="<fmt:formatDate value="${complaintForOtherDepartMent.startDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
				   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'endDate\')}',isShowClear:true});"/> 至
			<input name="endDate" id="endDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
				   value="<fmt:formatDate value="${complaintForOtherDepartMent.endDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
				   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'startDate\')}',isShowClear:true});"/>
		</li>









		<ul class="ul-form">
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
				<th>订单编号</th>
				<th>工程模式</th>
				<th>区域</th>
				<th>地址</th>
				<th>客户姓名</th>
				<th>客户手机号</th>
				<th>项目经理</th>
				<th>项目经理手机号</th>
				<th>问题创建时间</th>
				<th>投诉人</th>
				<th>状态</th>
				<shiro:hasPermission name="complaintforotherdepartment:complaintForOtherDepartMent:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="complaintForOtherDepartMent">
			<tr>
				<td>

					${fns:getStoreLabel(complaintForOtherDepartMent.storeId,'' )}

				</td>
				<td>${complaintForOtherDepartMent.orderNumber}</td>

				<td>
					${fns:getDictLabel(complaintForOtherDepartMent.projectMode,'project_mode','' )}

				</td>

				<td>${complaintForOtherDepartMent.departName}</td>
				<td>${complaintForOtherDepartMent.customerAddress}</td>
				<td>${complaintForOtherDepartMent.customerName}</td>
				<td>${complaintForOtherDepartMent.customerPhone}</td>
				<td>${complaintForOtherDepartMent.managerName}</td>
				<td>${complaintForOtherDepartMent.managerPhone}</td>
				<td><fmt:formatDate value="${complaintForOtherDepartMent.createDate}"  pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate> </td>
				<td>${complaintForOtherDepartMent.complaintPersonName}</td>

				<td>
						${fns:getDictLabel(complaintForOtherDepartMent.status,'preComplaintStatus','' )}

				</td>

				<shiro:hasPermission name="complaintforotherdepartment:complaintForOtherDepartMent:edit"><td>

					<a href="${ctx}/complaintforotherdepartment/complaintForOtherDepartMent/detailLog?id=${complaintForOtherDepartMent.id}">详情</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>