<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>发起项目经理竣工结算</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			getDepartments();
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
							if('${proIndustryPmSettleInfo.enginDepartId}' == data[i].value){
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
		


        function openCompleteSettleInfoPage(orderId){
            $.ajax({
                url:'${ctx}/newProIndustryPmSettle/isCheckedCreateSettleBille',
                type:'post',
                dataType : 'text',
                data:{
                    'orderId':orderId,
                    'flag':'0'
                },
                success:function(data){
                    if(data == 'success'){
                        window.location.href="${ctx}/newProIndustryPmSettle/openCompleteSettleInfoPage?orderId="+orderId;
                    }else {
                        alertx("请到新的发起项目经理竣工结算创建结算单")
                    }
                }
            });
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="#">发起项目经理竣工结算</a></li>
	</ul>
	<br/>
	
	
	<form:form id="searchForm" modelAttribute="proIndustryPmSettleInfo" action="${ctx}/proIndustryPmSettle/proIndustryPmSettle/queryProIndustryPmCompleteSettleList" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		
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
			
			<li><label>工程模式：</label>
				<form:select path="projectMode" id="projectMode" name="projectMode" class="input-medium" disabled="true">
                     <form:option value="4" label="准产业"/>
				</form:select>
			</li>
			
			<li><label>区域：</label>
				<select id="enginDepartId" name="enginDepartId" class="input-medium">

				</select>
			</li>
			<li><label>客户姓名：</label>
				<form:input path="customerName" htmlEscape="false" maxlength="64" class="input-medium needClear"/>
			</li>
			<li><label>项目经理：</label>
				<form:input path="itemCustomer" htmlEscape="false" maxlength="64" class="input-medium needClear"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="btns"><input class="btn btn-primary clearBtn" type="button" value="清空" onclick="clearEnginDepart()"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>门店</th>
				<th>工程模式</th>
				<th>区域</th>
				<th>订单编号</th>
				<th>小区</th>
				<th>客户姓名</th>
				<th>客户手机号</th>
				<th>项目经理</th>
				<th>项目经理手机号</th>
				<th>质检员</th>
				<th>竣工申请通过时间</th>
				<th>客户尾款交款金额</th>
				<th>交款时间</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="proIndustryPmSettleInfo">
			<tr>
				<td>
					${fns:getStoreLabel(proIndustryPmSettleInfo.storeId, '')}
				</td>
				<td>
					${fns:getDictLabel(proIndustryPmSettleInfo.projectMode, 'project_mode', '')}
				</td>
				<td>
					${proIndustryPmSettleInfo.departmentName}
				</td>
				<td>
					${proIndustryPmSettleInfo.orderNum}
				</td>
				<td>
					${proIndustryPmSettleInfo.communityName}-${proIndustryPmSettleInfo.buildNumber}-${proIndustryPmSettleInfo.buildUnit}-${proIndustryPmSettleInfo.buildRoom}
				</td>
				
				<td>
					${proIndustryPmSettleInfo.customerName}
				</td>
				<td>
					${proIndustryPmSettleInfo.customerPhone}
				</td>
				<td>
					${proIndustryPmSettleInfo.itemCustomer}
				</td>
				<td>
					${proIndustryPmSettleInfo.itemPhone}
				</td>
				
				<td>
					${proIndustryPmSettleInfo.inspector}
				</td>
				
				
				<td>
					<fmt:formatDate value="${proIndustryPmSettleInfo.completeAuditPassTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${proIndustryPmSettleInfo.finalPayment}
				</td>
				
				<td>
					<fmt:formatDate value="${proIndustryPmSettleInfo.finalPaymentDate}" pattern="yyyy-MM-dd"/>
				</td>
				
				<td>
				 <a href="#" onclick="openCompleteSettleInfoPage(${proIndustryPmSettleInfo.orderId})">创建结算单</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>