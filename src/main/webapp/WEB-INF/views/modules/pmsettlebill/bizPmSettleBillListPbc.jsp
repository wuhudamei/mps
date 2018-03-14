<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>结算单管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {

			$("#createButton").click(function(){
				var count = 0;
				var billCount = 0;
				var settleMonth = "";
				$.ajax({
					url:"${ctx}/pmsettlebill/bizPmSettleBill/queryCountByCondition",
					type : "post",
					dataType : "json",
					async : false,
					data:{
						storeId:$("#storeId").val(),
						settleRole:'2'
					},
					success : function(data){
						count = data.count;
						billCount = data.billCount;
						settleMonth = data.settleMonth;
					}
				});

				if(billCount > 0){
					if(count > 0){
						alertx(settleMonth+"月已生成了工程结算单，不允许再生成");
					}else{
						top.$.jBox.confirm("一共有"+billCount+"条记录将会生成月度工程结算单，结算月份:"+settleMonth+",确定要生成吗？","系统提示",function(v,h,f){
							if(v=="ok"){
								loading('正在提交，请稍等...');
								$("#createButton").unbind("click");
								/*$("#searchForm").attr("action", "${ctx}/pmsettlebill/bizPmSettleBill/createSettleSummaryBill");
								 $("#searchForm").submit();*/
								window.location.href="${ctx}/pmsettlebill/bizPmSettleBill/createSettleSummaryBillPbc?storeId="+$("#storeId").val()+"&settleMonth="+settleMonth;

							}
						},{buttonsFocus:1});
						top.$('.jbox-body .jbox-icon').css('top','55px');
					}
				}else{
					alertx("没有可生成月度工程结算单的数据！");
				}
			});

			getDepartments();
		});

		function getDepartments(){
			$.ajax({
				url:'${ctx}/engdept/bizEngineeringDepartment/departmentList',
				type:'post',
				dataType : 'json',
				data:{
					'storeId':$('#storeId').val(),
					'projectMode':'1',
					'employeeId':$('#employeeId').val()
				},
				success:function(data){
					if(data == null || data == ""){
						$("#enginDepartId").html("");
						$("#s2id_enginDepartId .select2-chosen").html("");
					}else{
						var content = "<option></option>";
						for(var i=0;i<data.length;i++){
							if('${bizPmSettleBill.enginDepartId}' == data[i].value){
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

		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }

		function clearEnginDepart(){
			//$("#enginDepartId").html("");
			//$("#s2id_enginDepartId .select2-chosen").html("");
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/pmsettlebill/bizPmSettleBill/loadListPbc">结算单列表</a></li>
		<%--<shiro:hasPermission name="pmsettlebill:bizPmSettleBill:edit"><li><a href="${ctx}/pmsettlebill/bizPmSettleBill/form">结算单添加</a></li></shiro:hasPermission>--%>
	</ul>
	<form:form id="searchForm" modelAttribute="bizPmSettleBill" action="${ctx}/pmsettlebill/bizPmSettleBill/loadListPbc" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<input type="hidden" id="employeeId" name="employeeId" value="${employeeId}">
		<ul class="ul-form">
			<li><label>门店：</label>
				<c:if test="${empty storeDropEnable}">
					<form:select path="storeId" class="input-medium" disabled="true" id="storeId" onchange="getDepartments()">
						<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
				<c:if test="${!empty storeDropEnable}">
					<form:select path="storeId" class="input-medium" id="storeId" onchange="getDepartments()">
						<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
			</li>
			<li><label>区域：</label>
				<select id="enginDepartId" name="enginDepartId" class="input-medium">

				</select>
			</li>
			<li><label>客户姓名：</label>
				<form:input path="customerName" htmlEscape="false" maxlength="64" class="input-medium needClear"/>
			</li>
			<li><label>质检员：</label>
				<form:input path="orderInspector" htmlEscape="false" maxlength="64" class="input-medium needClear"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="btns"><input class="btn btn-primary clearBtn" type="button" value="清空" onclick="clearEnginDepart()"/></li>
			<shiro:hasPermission name="pmsettlebill:bizPmSettleBill:edit"><li class="btns"><input class="btn btn-primary" type="button" value="生成月度工程清单" id="createButton"/></li></shiro:hasPermission>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<%--form id="createForm" action="${ctx}/pmsettlebill/bizPmSettleBill/createSettleSummaryBill" method="post"></form>--%>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>门店</th>
				<th>区域</th>
				<th>订单号</th>
				<th>小区名称</th>
				<th>客户姓名</th>
				<th>客户电话</th>
				<th>质检员</th>
				<th>中期提成</th>
				<th>中期远程费提成</th>
				<th>竣工提成</th>
				<th>竣工远程费提成</th>
				<th>合计</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="bizPmSettleBill">
			<tr>
				<td>${fns:getStoreLabel(bizPmSettleBill.storeId, '')}</td>
				<td>${bizPmSettleBill.enginDepartName}</td>
				<td>${bizPmSettleBill.orderNumber}</td>
				<td>${bizPmSettleBill.customerMessage}</td>
				<td>${bizPmSettleBill.customerName}</td>
				<td>${bizPmSettleBill.customerPhone}</td>
				<td>${bizPmSettleBill.orderInspector}</td>
				<td>${bizPmSettleBill.qcMidwayCommissionAmount}</td>
				<td>${bizPmSettleBill.qcMidwayLongwayAmount}</td>
				<td>${bizPmSettleBill.qcCompleteCommissionAmount}</td>
				<td>${bizPmSettleBill.qcCompleteLongwayAmount}</td>
				<td>${bizPmSettleBill.totalAmount}</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>