<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>网真对账单审核</title>
<meta name="decorator" content="default" />
<style>
		.undis{display:none;}
		.g-mask{width:100%;height:100%;position:relative;z-index:99;font-size:0;}
		#g-done_ask{width:400px;height:200px;position:fixed;left:50%;top:50%;transform:translate(-50%,-50%);border:1px solid #333;border-radius:4px;}
		.refuse{height:50px;line-height:50px;font-size:20px;background:#54b4eb;margin:0;}
		.content{color:#000;width:400px;height:100px;resize: none;margin:0;}
		.second{width:400px;}
		.second a{display:block;width:200px;height:50px;line-height:50px;font-size:24px;text-decoration:none;float:left;text-align:center;background:#54b4eb;color:#fff;}
		.second a:first-child{box-sizing:border-box;border-right:1px solid #ccc;}
	</style>
<script type="text/javascript">
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
	var verifyId=null;
	function reject(id){
		verifyId = id;
		$("#refuseSalary").removeClass("undis");
	}
	
	function noReject(){
		$("#reason").val("");
		$("#refuseSalary").addClass("undis");
	}
	
	function yesReject(){
		var reason = $("#reason").val();
		$("#reason").val("");
		$("#refuseSalary").addClass("undis");
		window.location.href = "${ctx}/verify/bizAuxiliaryMaterialsVerify/updateVerifyStatus?type=3&verifyId="+verifyId+"&status=35&cancelReason="+reason;
	}
    
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a
			href="${ctx}/verify/bizAuxiliaryMaterialsVerify/openWangzhenVerifyPage">网真对账单审核</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="bizAuxiliaryMaterialsVerify"
		action="${ctx}/verify/bizAuxiliaryMaterialsVerify/wangzhenVerifyList" method="post"
		class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden"
			value="${page.pageSize}" />
		<ul class="ul-form">
			<li><label>门店：</label> <c:if test="${empty storeDropEnable}">
					<form:select path="storeId" class="input-medium" disabled="true">
						<form:options items="${fns:getStoreList()}" itemLabel="label"
							itemValue="value" htmlEscape="false" />
					</form:select>
				</c:if> <c:if test="${!empty storeDropEnable}">
					<form:select path="storeId" class="input-medium needClear">
						<form:options items="${fns:getStoreList()}" itemLabel="label"
							itemValue="value" htmlEscape="false" />
					</form:select>
				</c:if></li>

			<li><label style="width: 120px">对账单生成日期：</label> <input
				name="startDate" id="startDate" type="text" readonly="readonly"
				maxlength="20" class="input-medium Wdate"
				value="<fmt:formatDate value="${bizAuxiliaryMaterialsVerify.startDate}" pattern="yyyy-MM-dd"/>"
				onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'endDate\')}',isShowClear:true});" />
				至 <input name="endDate" id="endDate" type="text" readonly="readonly"
				maxlength="20" class="input-medium Wdate"
				value="<fmt:formatDate value="${bizAuxiliaryMaterialsVerify.endDate}" pattern="yyyy-MM-dd"/>"
				onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'startDate\')}',isShowClear:true});" />

			</li>

            <li>
            <label style="width: 120px">对账单状态：</label>
            <form:select
					path="status" class="input-medium needClear">
					<form:option value="30" label="工程部对账单通过" />
					<form:option value="35" label="网真对账单驳回" />
					<form:option value="40" label="网真对账单通过" />
			</form:select>
            </li>
            <li>
             <label>操作人：</label>
             <form:input path="empName"
					htmlEscape="false" maxlength="64" class="input-medium" />
            </li>
			<li><label>对账单编号：</label> <form:input path="verifyCode"
					htmlEscape="false" maxlength="64" class="input-medium" /></li>
			<li style="height: auto"><input id="btnSubmit"
				class="btn btn-primary" type="submit" value="查询" />&nbsp;&nbsp;&nbsp;
				<input class="btn btn-primary clearBtn" type="button" value="清空"
				onclick="clearCondition()" /></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}" />
	<table id="contentTable"
		class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>门店</th>
				<th>对账单编号</th>
				<th>供应商名称</th>
				<th>对账单生成日期</th>
				<th>对账单日期</th>
				<th>结算单数量</th>
				<th>对账单辅料总金额(门店价)</th>
				<th>对账单状态</th>
				<th>操作人</th>
				<th>备注</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="verify">
				<tr>
					<td>${fns:getStoreLabel(verify.storeId, '')}</td>
					<td>${verify.verifyCode}</td>
					<td>${verify.supplierName}</td>
					<td><fmt:formatDate value="${verify.generateDatetime}"
							pattern="yyyy-MM-dd" /></td>
					<td><fmt:formatDate value="${verify.startDate}"
							pattern="yyyy-MM-dd" />至 <fmt:formatDate
							value="${verify.endDate}" pattern="yyyy-MM-dd" /></td>
					<td>${verify.orderTaskpackageSettlementCount}</td>
					<td>${verify.auxiliaryMaterialsWangzhenPriceAmount}</td>
					<td>${fns:getDictLabel(verify.status, 'biz_verify_status', '')}</td>
					<td>${verify.empName}</td>
					<td>${verify.statusRemark}</td>
					<td>
					    <c:if test="${verify.status == '30'}">
					     <a href="${ctx}/verify/bizAuxiliaryMaterialsVerify/updateVerifyStatus?type=3&verifyId=${verify.id}&status=30" onclick="return confirmx('确认要审核通过吗？', this.href)">通过</a>
					     <a href="#" onclick="reject(${verify.id})">驳回</a>
					    </c:if>
						<a href="${ctx}/verify/bizAuxiliaryMaterialsVerify/verifyAuxiliaryDetail?auxiliaryMaterialsVerifyId=${verify.id}&groupType=3">详情</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="g-mask undis" id="refuseSalary">
		<div id="g-done_ask">
			<p class="refuse">请输入驳回理由：</p>
			<textarea class="content" id="reason"></textarea>
			<p class="second">
				<a href="javascript:void(0)" onclick="noReject()">取消</a>
				<a href="javascript:void(0)" onclick="yesReject()">确认</a>
			</p>
		</div>
	</div>
	<div class="pagination">${page}</div>
</body>
</html>