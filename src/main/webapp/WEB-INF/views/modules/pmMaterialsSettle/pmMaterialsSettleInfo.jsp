<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>任务包材料结算单列表</title>
<meta name="decorator" content="default" />
<meta content="*" name="Access-Control-Allow-Origin">
<!--跨域请求  -->
<script type="text/javascript">
	$(document).ready(function() {
		getDepartments();
	});
	function page(n, s) {
		$("#pageNo").val(n);
		$("#pageSize").val(s);
		$("#searchForm").submit();
		return false;
	}

	function clearCondition() {
		document.getElementById("searchForm").reset();
	}
	// --全选框被单击---
	function ChkAllClick(sonName, cbAllId) {
		var arrSon = document.getElementsByName(sonName);
		var cbAll = document.getElementById(cbAllId);
		var tempState = cbAll.checked;
		for (i = 0; i < arrSon.length; i++) {
			if (arrSon[i].checked != tempState)
				arrSon[i].click();
		}
	}

	// --子项复选框被单击---
	function ChkSonClick(sonName, cbAllId) {
		var arrSon = document.getElementsByName(sonName);
		var cbAll = document.getElementById(cbAllId);
		for (var i = 0; i < arrSon.length; i++) {
			if (!arrSon[i].checked) {
				cbAll.checked = false;
				return;
			}
		}
		cbAll.checked = true;
	}
	//加载区域信息
	function getDepartments() {
		$("#enginDepartId").html('');
		$
				.ajax({
					url : '${ctx}/engdept/bizEngineeringDepartment/departmentList',
					type : 'post',
					dataType : 'json',
					data : {
						'storeId' : $('#storeId').val(),
						'projectMode' : '1',
						'employeeId' : $('#employeeId').val(),
					},
					success : function(data) {
						if (data == null) {
							$("#enginDepartId").html('');
						} else {
							var html = "<option value=''></option>";
							for (var i = 0; i < data.length; i++) {
								var sec = "";
								if ('${ pmMaterialsSettleInfo.enginDepartId }' == data[i].value) {
									sec = "selected='selected'";
									$("#s2id_enginDepartId .select2-chosen")
											.html(data[i].label);
								}
								html += "<option value='" + data[i].value +"' " + sec + ">"
										+ data[i].label + "</option>";
							}
							html += '';
							$("#enginDepartId").append(html);
						}
					}
				});
	}
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a
			href="${ctx}/pmMaterialsSettleInfo/pmMaterialsSettleInfo/queryPmMaterialsSettleInfo">任务包材料结算单列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="pmMaterialsSettleInfo"
		action="${ctx}/pmMaterialsSettleInfo/pmMaterialsSettleInfo/queryPmMaterialsSettleInfo"
		method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden"
			value="${page.pageSize}" />
		<input id="employeeId" name="employeeId" type="hidden"
			value="${employeeId}" />
		<ul class="ul-form">
			<li><label>门店：</label> <form:select path="storeId"
					class="input-medium needClear" onchange="getDepartments()"
					id="storeId">
					<form:options items="${fns:getStoreList()}" itemLabel="label"
						itemValue="value" htmlEscape="false" />
				</form:select></li>
			<li><label>区域：</label> <form:select path="enginDepartId"
					class="input-medium needClear" id="enginDepartId">
					<form:option value="${pmMaterialsSettleInfo.enginDepartId }"
						label="${pmMaterialsSettleInfo.engineDepartName }" />
				</form:select></li>
			<li><label>订单编号：</label> <form:input path="orderNumber"
					htmlEscape="false" maxlength="18" class="input-medium needClear" />
			</li>

			<li><label>客户姓名：</label> <form:input path="customerName"
					htmlEscape="false" maxlength="18" class="input-medium needClear" />
			</li>

			<li><label>任务包编号：</label> <form:input path="taskPackageNo"
					htmlEscape="false" maxlength="18" class="input-medium needClear" />
			</li>

			<li><label>任务包名称：</label> <form:input path="taskPackageName"
					htmlEscape="false" maxlength="18" class="input-medium needClear" />
			</li>

			<li><label>项目经理：</label> <form:input path="itemManager"
					htmlEscape="false" maxlength="255" class="input-medium needClear" />
			</li>

			<li style="width:100%">
				<label>状态：</label>
				<input id="chkAll" name="chkAll" type="checkbox" value="全选" onclick="ChkAllClick('settleStatus','chkAll')" />全选
			</li>
			<li>
				<c:forEach items="${fns:getDictList('pm_settle_status')}" var="dict">
					<input type="checkbox" name="settleStatus" id="status" value="${dict.value}"  onclick="ChkSonClick('settleStatus','chkAll')"  <c:if test="${fns:checkIsExits(pmMaterialsSettleInfo.settleStatus,dict.value)}"> checked="checked"</c:if>/>${dict.label}&nbsp;
				</c:forEach>
			</li>
			<li class="btns" style="width: 100px"><input id="btnSubmit"
				class="btn btn-primary" type="submit" value="查询" /></li>
			<li class="btns"><input id="btnClear"
				class="btn btn-primary clearBtn" type="button" value="清空"
				onclick="clearCondition()" /></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>

	<table id="contentTable"
		class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>门店</th>
				<th>区域</th>
				<th>结算员审核通过时间</th>
				<th>任务包材料结算单状态</th>
				<th>小区名称</th>
				<th>客户姓名</th>
				<th>任务包编号</th>
				<th>任务包名称</th>
				<th>结算项目经理</th>
				<th>辅料结算金额</th>
				<th>辅料扣款金额</th>
				<th>沙子水泥扣款金额</th>
				<th>项目经理结算金额</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="pmMaterialsSettleInfo">
				<tr>
					<td>${fns:getStoreLabel(pmMaterialsSettleInfo.storeId, '')}</td>
					<td>${pmMaterialsSettleInfo.engineDepartName}</td>
					<td><fmt:formatDate value="${pmMaterialsSettleInfo.auditDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
					<td>${fns:getDictLabel(pmMaterialsSettleInfo.settleStatus, 'pm_settle_status', '')}</td>
					<td>${pmMaterialsSettleInfo.communityName}-${pmMaterialsSettleInfo.buildNumber}-${pmMaterialsSettleInfo.buildUnit}-${pmMaterialsSettleInfo.buildRoom}</td>
				    <td>${pmMaterialsSettleInfo.customerName}</td>
				    <td>${pmMaterialsSettleInfo.taskPackageNo}</td>
				    <td>${pmMaterialsSettleInfo.taskPackageName}</td>
				    <td>${pmMaterialsSettleInfo.itemManager}-${pmMaterialsSettleInfo.itemPhone}</td>
				    <td>${pmMaterialsSettleInfo.auxiliaryMaterialsSettleAmount}</td>
				    <td>${pmMaterialsSettleInfo.auxiliaryMaterialsAmount}</td>
				    <td>${pmMaterialsSettleInfo.sandCementAmount}</td>
				    <td>${pmMaterialsSettleInfo.pmMaterialsSettleAmount}</td>
				    <td><a href="${ctx}/ordertaskpackagesettlement/bizOrderTaskpackageSettlementVo/details?id=${pmMaterialsSettleInfo.settleId}">详情</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>