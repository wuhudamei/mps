<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>考核条例细则</title>
<meta name="decorator" content="default" />
<style type="text/css">
.mask-text {
	resize: none;
	width: 400px;
	height: 200px;
	padding: 5px;
	box-sizing: border-box;
}
</style>
<script type="text/javascript">
	$(document).ready(function() {
		getRuleType();
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

	function getRuleType() {
		$("#bizAssessRuleTypeId").html('');
		$
				.ajax({
					url : '${ctx}/bizAssessRuleType/bizAssessRuleType/queryRuleTypeByParam',
					type : 'post',
					dataType : 'json',
					data : {
						'storeId' : $('#storeId').val(),
						'projectMode' : $('#projectMode').val()
					},
					success : function(data) {
						if (data == null) {
							$("#bizAssessRuleTypeId").append('');
						} else {
							var html = "<option value=''></option>";
							for (var i = 0; i < data.length; i++) {
								var sec = "";
								if ('${bizAssessRule.bizAssessRuleTypeId}' == data[i].id) {
									sec = "selected='selected'";
									$(
											"#s2id_bizAssessRuleTypeId .select2-chosen")
											.html(data[i].typeName);
								}
								html += "<option value='" + data[i].id +"' " + sec + ">"
										+ data[i].typeName + "</option>"
							}
							html += '';
							$("#bizAssessRuleTypeId").append(html);
						}
					}
				});
	}
	
	function showRemarks(remarks){
		$('#myAbandonedModalReject').modal('show');
		$("#myspan").html(remarks);
	}
	
	function onclickNoAbandonedReject(){
		$('#myAbandonedModalReject').modal('hide');
		$("#myspan").html('');
	}
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active">
			<ul class="nav nav-tabs">
				<li class="active"><a href="#">考核条例细则</a></li>
			</ul>
		<li><a
			href="${ctx}/bizAssessRule/bizAssessRule/openBizAssessRuleForm">考核条例细则添加</a>
		</li>
		</li>
	</ul>
	<form:form id="searchForm" modelAttribute="bizAssessRule"
		action="${ctx}/bizAssessRule/bizAssessRule/queryBizAssessRule"
		method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden"
			value="${page.pageSize}" />
		<ul class="ul-form">
			<li><label>门店：</label> <c:if test="${empty storeDropEnable}">
					<form:select path="storeId" class="input-medium" disabled="true"
						onchange="getRuleType()" id="storeId">
						<form:options items="${fns:getStoreList()}" itemLabel="label"
							itemValue="value" htmlEscape="false" />
					</form:select>
				</c:if> <c:if test="${!empty storeDropEnable}">
					<form:select path="storeId" class="input-medium needClear"
						onchange="getRuleType()" id="storeId">
						<form:options items="${fns:getStoreList()}" itemLabel="label"
							itemValue="value" htmlEscape="false" />
					</form:select>
				</c:if></li>
			<li><label>工程模式：</label> <c:if test="${empty gongcheng}">
					<form:select path="projectMode" class="input-medium"
						disabled="true" onchange="getRuleType()" id="projectMode">
						<form:option value="" label="" />
						<form:options items="${fns:getDictList('project_mode')}"
							itemLabel="label" itemValue="value" htmlEscape="false" />
					</form:select>
				</c:if> <c:if test="${!empty gongcheng}">
					<form:select path="projectMode" class="input-medium needClear"
						onchange="getRuleType()" id="projectMode">
						<form:option value="" label="" />
						<form:options items="${fns:getDictList('project_mode')}"
							itemLabel="label" itemValue="value" htmlEscape="false" />
					</form:select>
				</c:if></li>
			<li><label>分类：</label> <form:select path="bizAssessRuleTypeId"
					class="input-medium needClear">
				</form:select></li>

			<li><label>条例细则说明：</label> <form:input
					path="bizAssessRuleDescribe" htmlEscape="false"
					class="input-medium needClear" /></li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary"
				type="submit" value="查询" /></li>
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
				<th>分类名称</th>
				<th>考核条例细则说明</th>
				<th>奖励/惩罚金额</th>
				<th>分数</th>
				<th>责任人</th>
				<th>状态</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="bizAssessRule">
				<tr>
					<td>${fns:getStoreLabel(bizAssessRule.storeId, '')}</td>
					<td>${fns:getDictLabel(bizAssessRule.projectMode, 'project_mode','')}</td>
					<td>${bizAssessRule.bizAssessRuleTypeName}</td>
					<td><a href="#" onclick="showRemarks('${bizAssessRule.bizAssessRuleDescribe}')">查看</a></td>
					<td>${bizAssessRule.rewardPunishAmount}</td>
					<td>${bizAssessRule.rewardPunishScore}</td>
					<td><c:forEach
							items="${bizAssessRule.rewardPunishTargetEmployeeTypeArr}"
							var="employeeType" varStatus="status">
							<c:if test="${status.index == 0}">${fns:getDictLabel(employeeType, 'responsible_person','')}</c:if>
							<c:if test="${status.index > 0}">,${fns:getDictLabel(employeeType, 'responsible_person','')}</c:if>
						</c:forEach></td>
					<td>${bizAssessRule.isEnabled==1?'启用':'停用'}</td>
					<td><c:if test="${bizAssessRule.isEnabled==0}">
							<a
								href="${ctx}/bizAssessRule/bizAssessRule/enable?id=${bizAssessRule.id}&isEnabled=1">启用</a>
						</c:if> <c:if test="${bizAssessRule.isEnabled==1}">
							<a
								href="${ctx}/bizAssessRule/bizAssessRule/enable?id=${bizAssessRule.id}&isEnabled=0">停用</a>
						</c:if> <a
						href="${ctx}/bizAssessRule/bizAssessRule/openBizAssessRuleForm?id=${bizAssessRule.id}">修改</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="modal fade" id="myAbandonedModalReject" tabindex="-1"
		role="dialog" style="width: 500px">
		<input id="myIdReject" type="hidden">
		<div class="modal-header">
			<button class="close" type="button" data-dismiss="modal">×</button>
			<h4 id="myModalLabel" align="center" style="color: black;">
				考核条例细则说明
				</h3>
				<br>
				<div style="margin: 10px; min-height: 20px; height: auto; padding-left: 5px; text-align: center;"
					class="modal-body">
					<span id = "myspan"></span></br>
					<a href="javascript:void(0)" class="btn"
						onclick="onclickNoAbandonedReject()">关闭</a>
				</div>
		</div>
	</div>
	<div class="pagination">${page}</div>
</body>
</html>