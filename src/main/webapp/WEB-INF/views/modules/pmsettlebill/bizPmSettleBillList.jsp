<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>生成月度工程结算单</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {

            $("#allCheck").click(function () {
                if ($(this).attr("checked")) { // 全选
                    $("input[name='orderIds']").each(function () {
                        $(this).attr("checked", true);
                    });
                } else { // 取消全选
                    $("input[name='orderIds']").each(function () {
                        $(this).attr("checked", false);
                    });
                }
            });

            $("input[name='orderIds']").bind("click",function(){
                //全选
                if($("input[name='orderIds']:not(:checked)").length==0){
                    $("#allCheck").attr("checked",true);
                }else{
                    //不选
                    $("#allCheck").attr("checked",false);
                }
            });

			$("#createButton").click(function(){
                if($("input[name='orderIds']:checked").length == 0){
                    alertx("请选择结算单!");
                    return;
                }
				/*var count = 0;*/
                var orderIds = "";
                var billCount = $("input[name='orderIds']:checked").length;
                $("input[name='orderIds']:checked").each(function () {
                    if(orderIds == ""){
                        orderIds = $(this).val();
                    }else{
                        orderIds = orderIds + "," + $(this).val();
                    }
                });
				var settleMonth = "";
				$.ajax({
					url:"${ctx}/pmsettlebill/bizPmSettleBill/queryCountByCondition",
					type : "post",
					dataType : "json",
					async : false,
					data:{
						storeId:$("#storeId").val(),
						settleRole:'1'
					},
					success : function(data){
//						count = data.count;
//						billCount = data.billCount;
						settleMonth = data.settleMonth;
					}
				});

//				if(billCount > 0){
//					if(count > 0){
//						alertx(settleMonth+"月已生成了工程结算单，不允许再生成");
//					}else{
						top.$.jBox.confirm("选中的订单记录将会生成月度工程结算单，结算月份:"+settleMonth+",确定要生成吗？","系统提示",function(v,h,f){
							if(v=="ok"){
								loading('正在提交，请稍等...');
								$("#createButton").unbind("click");
								/*$("#searchForm").attr("action", "${ctx}/pmsettlebill/bizPmSettleBill/createSettleSummaryBill");
								 $("#searchForm").submit();*/
								window.location.href="${ctx}/pmsettlebill/bizPmSettleBill/createSettleSummaryBill?storeId="+$("#storeId").val()+"&settleMonth="+settleMonth+"&orderIds="+orderIds;

							}
						},{buttonsFocus:1});
						top.$('.jbox-body .jbox-icon').css('top','55px');
//					}
//				}else{
//					alertx("没有可生成月度工程结算单的数据！");
//				}
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
		
		function searchButton(){
			$("#searchForm").attr("action", "${ctx}/pmsettlebill/bizPmSettleBill/loadList");
			$("#searchForm").submit();
		}
		
		function exportExcel(){
			$("#searchForm").attr("action", "${ctx}/pmsettlebill/bizPmSettleBill/exportExcel2");
			$("#searchForm").submit();
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/pmsettlebill/bizPmSettleBill/loadList">生成月度工程结算单</a></li>
		<%--<shiro:hasPermission name="pmsettlebill:bizPmSettleBill:edit"><li><a href="${ctx}/pmsettlebill/bizPmSettleBill/form">结算单添加</a></li></shiro:hasPermission>--%>
	</ul>
	<form:form id="searchForm" modelAttribute="bizPmSettleBill" action="${ctx}/pmsettlebill/bizPmSettleBill/loadList" method="post" class="breadcrumb form-search">
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
			<li><label>项目经理：</label>
				<form:input path="itemManager" htmlEscape="false" maxlength="64" class="input-medium needClear"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="button" value="查询" onclick="searchButton()"/></li>
			<li class="btns"><input class="btn btn-primary clearBtn" type="button" value="清空" onclick="clearEnginDepart()"/></li>
			<shiro:hasPermission name="pmsettlebill:bizPmSettleBill:edit"><li class="btns"><input class="btn btn-primary" type="button" value="生成月度工程清单" id="createButton"/></li></shiro:hasPermission>
			<li class="btns"><input class="btn btn-primary" type="button" value="导出excel" onclick="exportExcel()"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<%--form id="createForm" action="${ctx}/pmsettlebill/bizPmSettleBill/createSettleSummaryBill" method="post"></form>--%>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th><input type="checkbox" id="allCheck"/>全选</th>
				<th>门店</th>
				<th>区域</th>
				<th>订单号</th>
				<th>小区名称</th>
				<th>客户姓名</th>
				<th>客户电话</th>
				<th>项目经理</th>
				<th>中期提成</th>
				<th>竣工提成</th>
				<th>自主支配项</th>
				<th>标化辅材</th>
				<th>中期质检罚款</th>
				<th>竣工质检罚款</th>
				<th>中期奖励</th>
				<th>竣工奖励</th>
				<th>中期扣款</th>
				<th>竣工扣款</th>
				<th>中期巡检奖励</th>
				<th>竣工巡检奖励</th>
				<th>中期巡检扣款</th>
				<th>竣工巡检扣款</th>
				<th>中期任务包材料结算</th>
				<th>竣工任务包材料结算</th>
				<th>自采材料</th>
				<th>质保金</th>
				<th>合计</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${list}" var="bizPmSettleBill">
			<tr>
				<td><input type="checkbox" id="orderIds" name="orderIds" value="${bizPmSettleBill.orderId}"/></td>
			    <td>${fns:getStoreLabel(bizPmSettleBill.storeId, '')}</td>
				<td>${bizPmSettleBill.enginDepartName}</td>
				<td>${bizPmSettleBill.orderNumber}</td>
				<td>${bizPmSettleBill.customerMessage}</td>
				<td>${bizPmSettleBill.customerName}</td>
				<td>${bizPmSettleBill.customerPhone}</td>
				<td>${bizPmSettleBill.itemManager}</td>
				<td><a href="${ctx}/pmsettlebill/bizPmSettleBill/queryMidwayCommission?orderId=${bizPmSettleBill.orderId}&pmEmployeeId=${bizPmSettleBill.pmEmployeeId}">${bizPmSettleBill.midwayCommissionAmount}</a></td>
				<td><a href="${ctx}/pmsettlebill/bizPmSettleBill/queryCompleteCommission?orderId=${bizPmSettleBill.orderId}&pmEmployeeId=${bizPmSettleBill.pmEmployeeId}">${bizPmSettleBill.completeCommissionAmount}</a></td>
				<td><c:if test="${not empty bizPmSettleBill.ownpayAmount}"><a href="${ctx}/pmsettlebill/bizPmSettleBill/ownpayAmount?id=${fn:split(bizPmSettleBill.settleBillId,',')[0]}">${bizPmSettleBill.ownpayAmount}</a></c:if></td>
				<td><a href="${ctx}/pmsettlebill/bizPmSettleBill/materialsStandardDetails?orderId=${bizPmSettleBill.orderId}">${bizPmSettleBill.materialsStandardAmount}</a></td>
				<%-- <td><c:if test="${not empty bizPmSettleBill.midwayQcCheckPunishAmount}"><a href="${ctx}/pmsettlebill/bizPmSettleBill/midwayQcCheckPunishAmount?id=${fn:split(bizPmSettleBill.settleBillId,',')[0]}">${bizPmSettleBill.midwayQcCheckPunishAmount}</a></c:if></td>
				<td><c:if test="${not empty bizPmSettleBill.completQcCheckPunishAmount}"><a href="${ctx}/pmsettlebill/bizPmSettleBill/completQcCheckPunishAmount?id=<c:if test="${fn:length(fn:split(bizPmSettleBill.settleBillId,',')) eq 1}">${fn:split(bizPmSettleBill.settleBillId,',')[0]}</c:if><c:if test="${fn:length(fn:split(bizPmSettleBill.settleBillId,',')) eq 2}">${fn:split(bizPmSettleBill.settleBillId,',')[1]}</c:if>">${bizPmSettleBill.completQcCheckPunishAmount}</a></c:if></td>
				 --%>
				<td><a href="${ctx}/pmsettlebill/bizPmSettleBill/midwayQcCheckPunishAmount?orderId=${bizPmSettleBill.orderId}&pmEmployeeId=${bizPmSettleBill.pmEmployeeId}">${bizPmSettleBill.midwayQcCheckPunishAmount}</a></td>
				<td><a href="${ctx}/pmsettlebill/bizPmSettleBill/completQcCheckPunishAmount?orderId=${bizPmSettleBill.orderId}&pmEmployeeId=${bizPmSettleBill.pmEmployeeId}">${bizPmSettleBill.completQcCheckPunishAmount}</a></td>
				
				
				<td><a href="${ctx}/pmsettlebill/bizPmSettleBill/querymidwayReward?orderId=${bizPmSettleBill.orderId}&pmEmployeeId=${bizPmSettleBill.pmEmployeeId}&midwayRewardAmount=${bizPmSettleBill.midwayRewardAmount}">${bizPmSettleBill.midwayRewardAmount}</a></td>
				<td><a href="${ctx}/pmsettlebill/bizPmSettleBill/querycompleteReward?orderId=${bizPmSettleBill.orderId}&pmEmployeeId=${bizPmSettleBill.pmEmployeeId}&completeRewardAmount=${bizPmSettleBill.completeRewardAmount}">${bizPmSettleBill.completeRewardAmount}</a></td>
				<td><a href="${ctx}/pmsettlebill/bizPmSettleBill/querymidwayPunish?orderId=${bizPmSettleBill.orderId}&pmEmployeeId=${bizPmSettleBill.pmEmployeeId}&midwayPunishAmount=${bizPmSettleBill.midwayPunishAmount}">${bizPmSettleBill.midwayPunishAmount}</a></td>
				<td><a href="${ctx}/pmsettlebill/bizPmSettleBill/querycompletePunish?orderId=${bizPmSettleBill.orderId}&pmEmployeeId=${bizPmSettleBill.pmEmployeeId}&completePunishAmount=${bizPmSettleBill.completePunishAmount}">${bizPmSettleBill.completePunishAmount}</a></td>

				<td><a href="${ctx}/pmsettlebill/bizPmSettleBill/queryMidwayInspectionReward?orderId=${bizPmSettleBill.orderId}&pmEmployeeId=${bizPmSettleBill.pmEmployeeId}&midwayInspectionRewardAmount=${bizPmSettleBill.midwayInspectionRewardAmount}">${bizPmSettleBill.midwayInspectionRewardAmount}</a></td>
				<td><a href="${ctx}/pmsettlebill/bizPmSettleBill/queryCompleteInspectionReward?orderId=${bizPmSettleBill.orderId}&pmEmployeeId=${bizPmSettleBill.pmEmployeeId}&completeInspectionRewardAmount=${bizPmSettleBill.completeInspectionRewardAmount}">${bizPmSettleBill.completeInspectionRewardAmount}</a></td>
				<td><a href="${ctx}/pmsettlebill/bizPmSettleBill/queryMidwayInspectionPunish?orderId=${bizPmSettleBill.orderId}&pmEmployeeId=${bizPmSettleBill.pmEmployeeId}&midwayInspectionPunishAmount=${bizPmSettleBill.midwayInspectionPunishAmount}">${bizPmSettleBill.midwayInspectionPunishAmount}</a></td>
				<td><a href="${ctx}/pmsettlebill/bizPmSettleBill/queryCompleteInspectionPunish?orderId=${bizPmSettleBill.orderId}&pmEmployeeId=${bizPmSettleBill.pmEmployeeId}&completeInspectionPunishAmount=${bizPmSettleBill.completeInspectionPunishAmount}">${bizPmSettleBill.completeInspectionPunishAmount}</a></td>

				<td><c:if test="${not empty bizPmSettleBill.midwayAuxiliaryMaterialsSettleAmount}"><a href="${ctx}/pmsettlebill/bizPmSettleBill/queryPmMaterials?orderId=${bizPmSettleBill.orderId}&pmEmployeeId=${bizPmSettleBill.pmEmployeeId}&settleCategory=121&settleStatus=30">${bizPmSettleBill.midwayAuxiliaryMaterialsSettleAmount}</a></c:if></td>
				<td><c:if test="${not empty bizPmSettleBill.completeAuxiliaryMaterialsSettleAmount}"><a href="${ctx}/pmsettlebill/bizPmSettleBill/queryPmMaterials?orderId=${bizPmSettleBill.orderId}&pmEmployeeId=${bizPmSettleBill.pmEmployeeId}&settleCategory=122&settleStatus=30">${bizPmSettleBill.completeAuxiliaryMaterialsSettleAmount}</a></c:if></td>
				<td><c:if test="${not empty bizPmSettleBill.materialSelfbuyReimburseAmount}"><a href="${ctx}/pmsettlebill/bizPmSettleBill/querySelfbuyMaterial?orderId=${bizPmSettleBill.orderId}&pmEmployeeId=${bizPmSettleBill.pmEmployeeId}">${bizPmSettleBill.materialSelfbuyReimburseAmount}</a></c:if></td>
				<td><a href="${ctx}/pmsettlebill/bizPmSettleBill/queryGuaranteeMoney?orderId=${bizPmSettleBill.orderId}&pmGuaranteeMoney=${bizPmSettleBill.guaranteeMoneyAmount}">${bizPmSettleBill.guaranteeMoneyAmount}</a></td>
				<td>${bizPmSettleBill.totalAmount}</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<%--<div class="pagination">${page}</div>--%>
</body>
</html>