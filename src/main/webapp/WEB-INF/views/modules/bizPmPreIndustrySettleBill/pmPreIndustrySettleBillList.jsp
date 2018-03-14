<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>下发项目经理结算单</title>
<meta name="decorator" content="default" />
<style type="text/css">
.mask-text {
	resize: none;
	width: 280px;
	padding: 5px;
	box-sizing: border-box;
}
</style>
<script type="text/javascript">
		$(document).ready(function() {
			getDepartments();
			var arrSon = document.getElementsByName("status");
			var cbAll = document.getElementById("chkAll");
			var boo = true;
			for(var i = 0; i < arrSon.length; i++){
				if(arrSon[i].checked == false){
					boo=false;
				}
			}
			if(boo){
				cbAll.checked=true;
			}
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);

			$("#searchForm").submit();
        	return false;
        }
		
		function clearCondition(){
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
			
			var arrSon = document.getElementsByName("orderStatusNumber");
			for (i = 0; i < arrSon.length; i++) {
				if(arrSon[i].checked){
					arrSon[i].checked=false;
				}
			}
		}

		
		function getDepartments(){
			$.ajax({
				url:'${ctx}/engdept/bizEngineeringDepartment/departmentList',
				type:'post',
				dataType : 'json',
				data:{
					'storeId':$('#storeId').val(),
					'projectMode':$('#projectMode').val(),
					'employeeId':$('#employeeId').val()
				},
				success:function(data){
					if(data == null || data == ""){
						$("#enginDepartId").html("");
						$("#s2id_enginDepartId .select2-chosen").html("");
					}else{
						var content = "<option></option>";
						for(var i=0;i<data.length;i++){
							if('${bizPmPreIndustrySettleBill.enginDepartId}' == data[i].value){
								$("#s2id_enginDepartId .select2-chosen").html(data[i].label);
								content = content + "<option value='"+data[i].value+"' selected='selected'>"+data[i].label+"</option>";
							}else{
								content = content + "<option value='"+data[i].value+"'>"+data[i].label+"</option>";
							}
						}
						$("#enginDepartId").html(content);
					}
				}
			});
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
		
		function sendingSettleBill(id){
			top.$.jBox.confirm("您确定要将结算单下发给项目经理吗？", "系统提示", function(v, h, f) {
				if (v == "ok") {
					$(obj).attr({
						"disabled" : "disabled"
					});
					$.ajax({
						url:"${ctx}/bizPmPreIndustrySettleBill/bizPmPreIndustrySettleBill/sendingSettleBill?id="+id,
						type : "POST",
						success : function(data){
							if(data == "0"){
								alertx("下发成功！");
								$("#searchForm").submit();
							}else if(data == "1"){
								alertx("下发失败！");
							}
						}
					});
				}
			}, {
				buttonsFocus : 1
			});
			top.$('.jbox-body .jbox-icon').css('top', '55px');
			
		}
		
		function editSettleBill(id){
			window.location.href="${ctx}/bizPmPreIndustrySettleBill/bizPmPreIndustrySettleBill/editSettleBill?id="+id;
		}
		
		function showRemarks(remarks){
			$('#myAbandonedModalReject').modal('show');
			$("#resonReject").val(remarks);
		}
		
		function onclickNoAbandonedReject(){
			$('#myAbandonedModalReject').modal('hide');
			$("#resonReject").val('');
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="#">下发项目经理结算单</a></li>
	</ul>
	<br />


	<form:form id="searchForm" modelAttribute="bizPmPreIndustrySettleBill"
		action="${ctx}/bizPmPreIndustrySettleBill/bizPmPreIndustrySettleBill/pmPreIndustrySettleBillList"
		method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden"
			value="${page.pageSize}" />

		<ul class="ul-form">
			<li><label>门店：</label> <c:if test="${empty storeDropEnable}">
					<form:select path="storeId" class="input-medium" disabled="true"
						id="storeId" onchange="getDepartments()">
						<form:options items="${fns:getStoreList()}" itemLabel="label"
							itemValue="value" htmlEscape="false" />
					</form:select>
				</c:if> <c:if test="${!empty storeDropEnable}">
					<form:select path="storeId" class="input-medium" id="storeId"
						onchange="getDepartments()">
						<form:options items="${fns:getStoreList()}" itemLabel="label"
							itemValue="value" htmlEscape="false" />
					</form:select>
				</c:if></li>

			<li><label>工程模式：</label> <form:select path="projectMode"
					id="projectMode" class="input-medium"
					disabled="true">
					<form:option value="4" label="准产业" />
				</form:select></li>

			<li><label>区域：</label> <select id="enginDepartId"
				name="enginDepartId" class="input-medium">

			</select></li>
			<li><label>订单编号：</label> <form:input path="orderNum"
					htmlEscape="false" maxlength="64" class="input-medium needClear" />
			</li>
			<li><label>客户姓名：</label> <form:input path="customerName"
					htmlEscape="false" maxlength="64" class="input-medium needClear" />
			</li>
			<li><label>项目经理：</label> <form:input path="itemCustomer"
					htmlEscape="false" maxlength="64" class="input-medium needClear" />
			</li>
			<li><label>结算单编号：</label> <form:input
					path="pmPreIndustrySettleBillCode" htmlEscape="false"
					maxlength="64" class="input-medium needClear" /></li>
			<li><label>结算单类型 ：</label> <form:select id="settleBillType"
					path="settleBillType" class="input-medium">
					<form:option value="" label="" />
					<form:options items="${fns:getDictList('settle_bill_type')}"
						itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select></li>
			<li style="width: 100%"><label>结算单状态：</label> <input id="chkAll"
				name="chkAll" type="checkbox" value="全选"
				onclick="ChkAllClick('status','chkAll')" />全选</li>
			<li><input type="checkbox" name="status" value="10"
				onclick="ChkSonClick('status','chkAll')"
				<c:forEach items="${bizPmPreIndustrySettleBill.statusList}" var="item"><c:if test="${item == '10'}">checked="checked"</c:if></c:forEach> />已创建结算单
				<input type="checkbox" name="status" value="45"
				onclick="ChkSonClick('status','chkAll')"
				<c:forEach items="${bizPmPreIndustrySettleBill.statusList}" var="item"><c:if test="${item == '45'}">checked="checked"</c:if></c:forEach> />项目经理拒绝结算金额
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary"
				type="submit" value="查询" /></li>
			<li class="btns"><input class="btn btn-primary clearBtn"
				type="button" value="清空" onclick="clearCondition()" /></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}" />
	<span style="font-weight:bold;font-size:20px;">
		已创建结算单：<span style="color:red;">${createCount }</span>&nbsp;&nbsp;
		项目经理拒绝结算金额：<span style="color:red;">${rejectCount }</span>
		<hr size="3px" noshade=true/>
	</span>
	
	<table id="contentTable"
		class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>门店</th>
				<th>工程模式</th>
				<th>区域</th>
				<th>结算单创建时间</th>
				<th>订单编号</th>
				<th>小区</th>
				<th>客户</th>
				<th>项目经理</th>
				<th>项目经理手机号</th>
				<th>结算单编号</th>
				<th>结算单类型</th>
				<th>结算单金额</th>
				<th>项目经理拒绝原因</th>
				<th>结算单状态</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="settleBill">
				<tr>
					<td>${fns:getStoreLabel(settleBill.storeId, '')}</td>
					<td>${fns:getDictLabel(settleBill.projectMode, 'project_mode', '')}
					</td>
					<td>${settleBill.departmentName}</td>
					<td><fmt:formatDate value="${settleBill.createDate}"
							pattern="yyyy-MM-dd HH:mm:ss" /></td>
					<td>${settleBill.orderNum}</td>
					<td>
						${settleBill.communityName}-${settleBill.buildNumber}-${settleBill.buildUnit}-${settleBill.buildRoom}
					</td>
					<td>${settleBill.customerName}</td>
					<td>${settleBill.itemCustomer}</td>
					<td>${settleBill.itemPhone}</td>
					<td>${settleBill.pmPreIndustrySettleBillCode}</td>
					<td>${fns:getDictLabel(settleBill.settleBillType, 'settle_bill_type', '')}</td>
					<td>${settleBill.realSettleAmount}</td>
					<td><c:if test="${settleBill.status == '45'}">
							<a href="#" onclick="showRemarks('${settleBill.remarks}')">查看</a>
						</c:if></td>
					<td>${fns:getDictLabel(settleBill.status, 'pm_settle_status', '')}</td>
					<td><a href="#" onclick="sendingSettleBill(${settleBill.id})">下发结算单给项目经理</a>
						<a href="#" onclick="editSettleBill(${settleBill.id})">修改结算单</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="modal fade" id="myAbandonedModalReject" tabindex="-1"
		role="dialog" style="width: 350px">
		<input id="myIdReject" type="hidden">
		<div class="modal-header">
			<button class="close" type="button" data-dismiss="modal">×</button>
			<h4 id="myModalLabel" align="center" style="color: black;">
				项目经理拒绝结算单原因
				</h3>
				<br>
				<div
					style="margin: 10px; min-height: 20px; height: auto; padding-left: 5px; text-align: center;"
					class="modal-body">
					<textarea id="resonReject"  readonly="readonly" class="mask-text"></textarea>
					<a href="javascript:void(0)" class="btn"
						onclick="onclickNoAbandonedReject()">关闭</a>
				</div>
		</div>

	</div>
	<div class="pagination">${page}</div>
</body>
</html>