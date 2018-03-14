<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>供应商对账单申请</title>
<meta name="decorator" content="default" />
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

		$("input[name='orderStatusNumber']").removeAttr("checked");
	}
	
    function createVerify(){
    	$('#createModel').modal('show');
    	$('#fir').show();
    }
    
    function onclickClean(){
		$('#createModel').modal('hide');
	}
    
    function onclickFrom(){
    	var storeId = $("#storeId1").val();
    	var startDate = $("#startDate1").val();
    	var endDate = $("#endDate1").val();
    	if(storeId==null || storeId ==""){
    		alertx('请选择门店！');
    		return;
    	}
    	if(startDate==null || startDate ==""){
    		alertx('请选择起始时间！');
    		return;
    	}
    	if(endDate==null || endDate ==""){
    		alertx('请截止时间！');
    		return;
    	}
    	loading('正在生成，请稍等...');
    	$.ajax({
    		url:'${ctx}/verify/bizAuxiliaryMaterialsVerify/createVerify?storeId='
    				+storeId+"&startDate="+startDate+"&endDate="+endDate,
    		type:"post",
    		success:function(data){
    			if(data == "ok"){
    				window.location.href="${ctx}/verify/bizAuxiliaryMaterialsVerify/list?storeId="+storeId;
    			}else if(data == "error"){
    				alertx('系统错误，请联系管理员！');
    			}else if(data == "noVerify"){
    				closeTip();
    				alertx('没有可生成的对账单！')
    			}else if(data == "noSupplier"){
    				alertx('您不是供应商！');
    			}
    		}
    		
    	});
    }
    
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/verify/bizAuxiliaryMaterialsVerify/openApplyVerifyPage">对账单申请</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="bizAuxiliaryMaterialsVerify"
		action="${ctx}/verify/bizAuxiliaryMaterialsVerify/list" method="post"
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

			<li><label style="width: 120px">对账单生成日期：</label>
			    <input
				name="startDate" id="startDate" type="text"
				readonly="readonly" maxlength="20" class="input-medium Wdate"
				value="<fmt:formatDate value="${bizAuxiliaryMaterialsVerify.startDate}" pattern="yyyy-MM-dd"/>"
				onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'endDate\')}',isShowClear:true});" />
				至 <input name="endDate" id="endDate" type="text"
				readonly="readonly" maxlength="20" class="input-medium Wdate"
				value="<fmt:formatDate value="${bizAuxiliaryMaterialsVerify.endDate}" pattern="yyyy-MM-dd"/>"
				onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'startDate\')}',isShowClear:true});" />
			
			</li>
			
			<li><label>对账单编号：</label> <form:input path="verifyCode"
					htmlEscape="false" maxlength="64" class="input-medium" /></li>
			<li style="height: auto"><input id="btnSubmit"
				class="btn btn-primary" type="submit" value="查询" />&nbsp;&nbsp;&nbsp;
				<input class="btn btn-primary clearBtn" type="button" value="清空"
				onclick="clearCondition()" /> <input
				class="btn btn-primary" type="button" value="生成对账单"
				onclick="createVerify()" /></li>
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
				<th>对账单辅料总金额(供应商)</th>
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
					<td>${verify.auxiliaryMaterialsSupplierPriceAmount}</td>
					<td>${fns:getDictLabel(verify.status, 'biz_verify_status', '')}</td>
					<td>${verify.empName}</td>
					<td>${verify.statusRemark}</td>
					<td>
					    <c:if test="${verify.status == '10' || verify.status == '25' ||verify.status == '35'}">
					    <a href="${ctx}/verify/bizAuxiliaryMaterialsVerify/updateVerifyStatus?type=1&verifyId=${verify.id}&status=20" onclick="return confirmx('确认要申请吗？', this.href)">申请</a>
						<a href="${ctx}/verify/bizAuxiliaryMaterialsVerify/updateVerifyStatus?type=1&verifyId=${verify.id}&status=15" onclick="return confirmx('确认要作废吗？', this.href)">作废</a>
					    </c:if>
						<a href="${ctx}/verify/bizAuxiliaryMaterialsVerify/verifyAuxiliaryDetail?auxiliaryMaterialsVerifyId=${verify.id}&groupType=1">详情</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
	<div class="modal fade" id="createModel" tabindex="-1" role="dialog"
		style="top: 10px">
		<div id="fir" class="modal-header"
			style="width: 500px; height: 390px;" hidden="hidden">
			<h5 align="center" style="color: black;">生成对账单</h5>
			</h3>
			<br>
			<h4 class="modal-title" align="center" style="color: black;"></h4>
			<form:form  modelAttribute="bizAuxiliaryMaterialsVerify" method="post"
		class="breadcrumb form-search">
			<div class="modal-body">
				<font style="color: black; font-size: 15">门店：</font>
				<c:if test="${empty storeDropEnable}">
					<form:select path="storeId" id="storeId1" class="input-medium" disabled="true">
						<form:options items="${fns:getStoreList()}" itemLabel="label"
							itemValue="value" htmlEscape="false" />
					</form:select>
				</c:if> <c:if test="${!empty storeDropEnable}">
					<form:select path="storeId" id="storeId1" class="input-medium needClear">
						<form:options items="${fns:getStoreList()}" itemLabel="label"
							itemValue="value" htmlEscape="false" />
					</form:select>
				</c:if>
				
			</div>
			<div class="modal-body">
			   <font style="color: black; font-size: 15">对账日期：</font>
			  	 <input
				name="startDate1" id="startDate1" type="text"
				readonly="readonly" maxlength="20" class="input-medium Wdate"
				onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'endDate1\')}',isShowClear:true});" />
				至 <input name="endDate1" id="endDate1"
				type="text" readonly="readonly" maxlength="20"
				class="input-medium Wdate"
				onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'startDate1\')}',isShowClear:true});" />
			</div>
</form:form>
			

			<div class="modal-footer"
				style="text-align: center; padding-top: 10px">
				<a href="javascript:void(0)" class="btn btn-primary"
					onclick="onclickFrom()">确定</a> <a href="javascript:void(0)"
					class="btn" onclick="onclickClean()">关闭</a>
			</div>
		</div>
	</div>
	<div class="pagination">${page}</div>
</body>
</html>