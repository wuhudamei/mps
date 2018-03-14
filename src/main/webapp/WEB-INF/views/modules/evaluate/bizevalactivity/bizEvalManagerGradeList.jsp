<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>工人评分查询</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
	$(document).ready(function() {
		findEngineListByStoreIdAndProjectMode();
	});
	function page(n, s) {
		$("#pageNo").val(n);
		$("#pageSize").val(s);
		$("#searchForm").submit();
		return false;
	}
	function clearCondition() {
		document.getElementById("searchForm").reset();

		var inputObjs = jQuery("#searchForm input[type='text']");
		for (var i = 0; i < inputObjs.length; i++) {
			var inputObj = inputObjs[i];
			inputObj.value = "";
		}

		var selectObjs = jQuery("#searchForm input[class='input-medium']");
		for (var i = 0; i < selectObjs.length; i++) {
			var selectObj = selectObjs[i];
			selectObj.value = "";
		}
	}

	function findEngineListByStoreIdAndProjectMode() {
		$("#engineDepartSelect").html('');
		var html3 = '<option value=""></option>';
		var storeId = $("#storeId").val();
		var projectModeValue = $("#projectMode").val();
		if (storeId == "" || projectModeValue == "" || undefined == storeId
				|| undefined == projectModeValue) {
			return;
		}
		//根据门店个,工程模式    动态加载工程区域
		$
				.ajax({

					url : "${ctx}/order/order/findEngineDepartmentBystoreIdAndProjectMode?storeId="
							+ storeId + "&projectModeValue=" + projectModeValue,
					type : "get",
					success : function(data) {
						if (null != data && data.length > 0) {

							for (var v = 0; v < data.length; v++) {
								html3 += '<option value="'+data[v].engineDepartId+'">'
										+ data[v].engineDepartName
										+ '</option>'
							}

							$("#engineDepartSelect").html(html3);
						} else {
							$("#engineDepartSelect").html(html3);
						}

					}

				});
	}

	function btnSubmit() {
		var storeId = $("#storeId").val();
		if (storeId == null || storeId == "") {
			alertx("请选择门店！");
			return;
		}
		var projectMode = $("#projectMode").val();
		if (projectMode == null || projectMode == "") {
			alertx("请选择工程模式！");
			return;
		}
		$("#searchForm").submit()
	}
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a
			href="${ctx}/evaluate/bizEvalManagerGrade/openBizEvalManagerGradePage">项目经理评分查询</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="bizEvalWorkGrade"
		action="${ctx}/evaluate/bizEvalManagerGrade/queryBizEvalManagerGrade"
		method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden"
			value="${page.pageSize}" />
		<ul class="ul-form">
			<li><label>门店：</label> <c:if test="${empty storeDropEnable}">
					<form:select id="storeId" path="storeId" class="input-medium"
						disabled="true" onchange="findEngineListByStoreIdAndProjectMode()">
						<form:options items="${fns:getStoreList()}" itemLabel="label"
							itemValue="value" htmlEscape="false" />
					</form:select>
				</c:if> <c:if test="${!empty storeDropEnable}">
					<form:select id="storeId" path="storeId"
						class="input-medium needClear"
						onchange="findEngineListByStoreIdAndProjectMode()">
						<form:options items="${fns:getStoreList()}" itemLabel="label"
							itemValue="value" htmlEscape="false" />
					</form:select>
				</c:if></li>

			<li><label>工程模式：</label> <c:if test="${empty gongcheng}">
					<form:select id="projectMode" path="projectMode"
						class="input-medium" disabled="true"
						onchange="findEngineListByStoreIdAndProjectMode()">
						<form:option value="" label="" />
						<form:options items="${fns:getDictList('project_mode')}"
							itemLabel="label" itemValue="value" htmlEscape="false" />
					</form:select>
				</c:if> <c:if test="${!empty gongcheng}">
					<form:select id="projectMode" path="projectMode"
						class="input-medium needClear"
						onchange="findEngineListByStoreIdAndProjectMode()">
						<form:option value="" label="" />
						<form:options items="${fns:getDictList('project_mode')}"
							itemLabel="label" itemValue="value" htmlEscape="false" />
					</form:select>
				</c:if></li>

			<li><label>区域：</label> <form:select path="enginDepartId"
					class="input-medium needClear" id="engineDepartSelect">
					<form:options
						items="${fns:getEngineListWithUserConditionsForBiddenChange()}"
						itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select></li>


			<li><label>评价类别：</label> <form:select path="evalRoleType"
					class="input-medium needClear">
					<form:option value="" label="" />
					<form:options items="${fns:getDictList('eval_role_type')}"
						itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select></li>

			<li><label>客户姓名：</label> <form:input path="customerName"
					htmlEscape="false" maxlength="64" class="input-medium" /></li>

			<li><label>项目经理：</label> <form:input path="manager"
					htmlEscape="false" maxlength="64" class="input-medium" /></li>



			<li><label style="width: 120px">评价时间：</label> <input
				name="startDate" type="text" readonly="readonly" maxlength="20"
				class="input-medium Wdate"
				value="<fmt:formatDate value="${bizEvalWorkGrade.startDate}" pattern="yyyy-MM-dd"/>"
				onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" />
				- <input name="endDate" type="text" readonly="readonly"
				maxlength="20" class="input-medium Wdate"
				value="<fmt:formatDate value="${bizEvalWorkGrade.endDate}" pattern="yyyy-MM-dd"/>"
				onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" />
			<li class="btns"><input class="btn btn-primary" type="button"
				value="查询" onclick="btnSubmit()" /></li>
			<li class="btns"><input class="btn btn-primary clearBtn"
				type="button" value="清空" onclick="clearCondition()" /></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}" />
	<table id="contentTable"
		class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>门店</th>
				<th>工程模式</th>
				<th>区域</th>
				<th>评价时间</th>
				<th>项目经理</th>
				<th>客户信息</th>
				<th>评价类别</th>
				<th>总分</th>
				<th>实际得分</th>
				<c:if test="${page.list != null && page.list.size()>0}">
					<c:forEach items="${page.list.get(0).bizEvalActivityIndexList}"
						var="evaLIndex">
						<th>${evaLIndex.indexName}</th>
					</c:forEach>
				</c:if>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="bizEvalWorkGrade">
				<tr>
					<td>${fns:getStoreLabel(bizEvalWorkGrade.storeId, '')}</td>
					<td>${fns:getDictLabel(bizEvalWorkGrade.projectMode, 'project_mode', '')}
					</td>
					<td>${bizEvalWorkGrade.enginDepartName}</td>
					<td><fmt:formatDate value="${bizEvalWorkGrade.gradeDate}"
							pattern="yyyy-MM-dd HH:mm:ss" /></td>
					<td>${bizEvalWorkGrade.manager}</td>
					<td>${bizEvalWorkGrade.communityName}-${bizEvalWorkGrade.buildNumber}-${bizEvalWorkGrade.buildUnit}-${bizEvalWorkGrade.buildRoom}-${bizEvalWorkGrade.customerName}</td>
					<td>${fns:getDictLabel(bizEvalWorkGrade.evalRoleType, 'eval_role_type', '')}</td>
					<td>${bizEvalWorkGrade.evaltotalScore}</td>
					<td>${bizEvalWorkGrade.gradtotalScore}</td>
					<c:forEach items="${bizEvalWorkGrade.bizEvalActivityIndexList}"
						var="evaLIndexScore">
						<c:if test="${evaLIndexScore.indexScore == null}">
							<td>--</td>
						</c:if>
						<c:if test="${evaLIndexScore.indexScore != null}">
							<td>${evaLIndexScore.indexScore}</td>
						</c:if>

					</c:forEach>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>