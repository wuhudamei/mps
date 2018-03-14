<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>订单管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
	$(document).ready(function() {

	});
	function page(n, s) {
		$("#pageNo").val(n);
		$("#pageSize").val(s);
		$("#searchForm").submit();
		return false;
	}

	function allotWorkerGroup() {
		//得到选框
		if ($('input:radio:checked').val() == undefined) {

			alert("请选择要分配的工人组..");
		} else {
			//工人组id
			var workerGroupId = $('input:radio:checked').val();
		var startDate =  $('#start').val();
		var endDate = $('#end').val();
			window.location.href = "${ctx}/AllotWorkerGroup/allotWorkerGroup/allotWorker?packageId="
					+ "${taskPackage.id}" + "&id=" + workerGroupId+"&startDate="+startDate+"&endDate="+endDate+"&turnpageflag=waitAllot";
		}
	}
	
</script>
</head>
<body>
	<c:if test="${not empty error }">${error }</c:if>
	<c:if test="${empty error }">
		<div class="breadcrumb form-search">
			<form:form id="searchForm1" modelAttribute="WorkgroupVo"
				action="${ctx}/AllotWorkerGroup/allotWorkerGroup/list" method="post"
				class="breadcrumb form-search">
				<table>
					<tr>
						<td style="width: 33%"><label>工程地址：</label>${taskPackage.customerMessage}</td>
						<input type="hidden" name="packageId" value="${taskPackage.id}" />
					</tr>
					<tr>

				
	
						<td><label>要求工期：</label> 开始日期: <input name="planStartdate"
							id="start" type="text" readonly="readonly" maxlength="20"
							class="input-medium Wdate"
							value="<fmt:formatDate value="${taskPackage.planStartdate}" pattern="yyyy-MM-dd"/>"
							onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'end\')}',isShowClear:true});" />
						</td>
						<td>结束日期: <input name="planEnddate" id="end" type="text"
							readonly="readonly" maxlength="20" class="input-medium Wdate"
							value="<fmt:formatDate value="${taskPackage.planEnddate}" pattern="yyyy-MM-dd"/>"
							onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'start\')}',isShowClear:true});" />
						<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>

						<td><label>归属工程部：</label> <form:select path="elactricationId"
								class="input-medium">
								<form:options items="${fns:getEngineListWithUserConditionsForBiddenChange()}" itemLabel="label"
									itemValue="value" htmlEscape="false" />
							</form:select></td>
						<td colspan="3"><input id="btnSubmit" class="btn btn-primary"
							name="btnSubmit" type="submit" value="查询" /></td>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
						<td colspan="3"><input class="btn btn-primary" type="button"
							value="分配工人" onclick="allotWorkerGroup()" /></td>&nbsp;&nbsp;
						<td colspan="2"><input class="btn btn-primary" type="button"
							value="排序" onclick="isOrder()" /></td>
					</tr>

				</table>
			</form:form>
			<sys:message content="${message}" />
		</div>


		<sys:message content="${message}" />




		<table id="contentTable"
			class="table table-striped table-bordered table-condensed">
			<thead>
				<tr>
					<th>选择</th>
					<th>组长姓名</th>
					<th>手机</th>
					<th>星级</th>
					<th>NPS</th>
					<th>组内成员数</th>
					<th>当前未完成包数</th>
					<th>住址</th>
					<th>住址和工地距离</th>

					<!-- <shiro:hasPermission name="employee:workerGroup:edit"><th>操作</th></shiro:hasPermission> -->
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${page.list}" var="workerGroup">
					<tr>
						<td><input type="radio" name="ids" value="${workerGroup.id }" />
						</td>
						<td>${workerGroup.groupName}</td>
						<td>${workerGroup.phone}</td>
						<td>${workerGroup.starLevel}</td>
						<td>${workerGroup.NPS}</td>
						<td>${workerGroup.groupCount}</td>
						<td><c:if test="${empty workerGroup.targetPackageCount}">0</c:if>
							<c:if test="${not empty workerGroup.targetPackageCount}">${workerGroup.targetPackageCount}</c:if></td>
						<td>${workerGroup.address}</td>
						<td><fmt:formatNumber
								value="${workerGroup.addressToWorkerAddressDistance }"
								pattern="0.00"></fmt:formatNumber>米</td>


					</tr>
				</c:forEach>
			</tbody>
		</table>
	</c:if>

	<div class="pagination">${page}</div>
</body>
</html>