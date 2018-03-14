<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>劳资部查询</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			getDepartments();
			
		});
		function subClick(){
			var storeId = $("#storeId").val();
			if(storeId == null || storeId == ''){
				alertx("请先选择门店,工程模式，大区，再查询");
				return;
			}
			
			// || projectMode == null || projectMode == '' || enginDepartId == null || enginDepartId == ''
			var projectMode = $("#projectMode").val();
			if(projectMode == null || projectMode == ''){
				alertx("请先选择门店,工程模式，大区，再查询");
				return ;
			}
			var enginDepartId = $("#enginDepartId").val();
			if(enginDepartId == null || enginDepartId == ''){
				alertx("请先选择门店,工程模式，大区，再查询");
				return ;
			}
			$("#searchForm").attr("action", "${ctx}/laborCapital/laborCapital/queryOrderList1");
			$("#searchForm").submit();
		}
		
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
			
			var html3 = '<option value=""></option>';
			var storeId = $("#storeId").val();
			var projectModeValue = $("#projectMode").val();
			if (storeId=="" ||projectModeValue=="" ||undefined==storeId ||undefined==projectModeValue) {
				return;
			}
			//根据门店个,工程模式    动态加载工程区域
			$.ajax({
				url : "${ctx}/order/order/findEngineDepartmentBystoreIdAndProjectMode?storeId="
								+ storeId + "&projectModeValue=" + projectModeValue,
				type : "get",
				success : function(data) {
					if (null!= data && data.length > 0) {

						/* for (var v = 0; v < data.length; v++) {
							html3 +='<option value="'+data[v].engineDepartId+'">'+data[v].engineDepartName+'</option>' 
						} */
						
						for (var v = 0; v < data.length; v++) {
							if('${laborCapital.enginDepartId}' == data[v].engineDepartId){
								$("#s2id_enginDepartId .select2-chosen").html(data[v].engineDepartName);
								html3 = html3 + "<option value='"+data[v].engineDepartId+"' selected='selected'>"+data[v].engineDepartName+"</option>";
							}else{
								html3 = html3 + "<option value='"+data[v].engineDepartId+"'>"+data[v].engineDepartName+"</option>";
							}
							
							/* html3 +='<option value="'+data[v].engineDepartId+'">'+data[v].engineDepartName+'</option>'  */
						}
								
						$("#enginDepartId").html(html3);
					} else {
						$("#enginDepartId").html(html3);
					}
				}

			});
	}

    function exporta() {
            var pageSize = $("#pageSize").val()
            if(pageSize == 0 && pageSize == ''){
                alertx("请先查询再导出！")
                return false;
            }
            var storeId = $("#storeId").val();
            var storeId1 = '${laborCapital.storeId}';
            if(storeId != storeId1){
                alertx("请先查询在导出！")
                return false;
            }
            var projectMode = $("#projectMode").val();
            var projectMode1 = '${laborCapital.projectMode}';
            if(projectMode != projectMode1){
                alertx("请先查询在导出！")
                return false;
            }

            var orderNumber = $("#enginDepartId").val();
            var orderNumber1 = '${laborCapital.enginDepartId}';
            if(orderNumber != orderNumber1){

                alertx("请先查询在导出！")
                return false;
            }

            var customerName = $("#custumer").val();
            var customerName1 = '${laborCapital.custumer}';
            if(customerName != customerName1){

                alertx("请先查询在导出！")
                return false;
            }


            var status = $("#customerAddress").val();
            var status1 = '${laborCapital.customerAddress}';
            if(status != status1){

                alertx("请先查询在导出！")
                return false;
            }


            var itemManager = $("#orderStatusNumber").val();
            var itemManager1 = '${laborCapital.orderStatusNumber}';
            if(itemManager != itemManager1){

                alertx("请先查询在导出！")
                return false;
            }

            var delayBillStageStatus = $("#itemManager").val();
            var delayBillStageStatus1 = '${laborCapital.itemManager}';
            if(delayBillStageStatus != delayBillStageStatus1){

                alertx("请先查询在导出！")
                return false;
            }

            var delayBillCategoryId = $("#taskPackageType").val();
            var delayBillCategoryId1 = '${laborCapital.taskPackageType}';
            if(delayBillCategoryId != delayBillCategoryId1){
                alertx("请先查询在导出！")
                return false;
            }

            var delayBillCategoryIdReson = $("#packageStateId").val();
            var delayBillCategoryIdReson1 = '${laborCapital.packageStateId}';
            if(delayBillCategoryIdReson != delayBillCategoryIdReson1){
                alertx("请先查询在导出！")
                return false;
            }

            var groupLeaderPhone = $("#groupLeaderPhone").val();
            var groupLeaderPhone1 = '${laborCapital.groupLeaderPhone}';
            if(groupLeaderPhone != groupLeaderPhone1){
                alertx("请先查询在导出！")
                return false;
            }


            $("#searchForm").attr("action","${ctx}/laborCapital/laborCapital/exportexcel")

            $("#searchForm").submit();

            $("#searchForm").attr("action","${ctx}/laborCapital/laborCapital/queryOrderList1")

            alertx("正在导出，请稍等...")


    }


	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/laborCapital/laborCapital/queryOrderList">查询列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="laborCapital" action="${ctx}/laborCapital/laborCapital/queryOrderList1" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
				<li><label>门店：</label>
				<c:if test="${empty storeDropEnable}">
					<form:select path="storeId" class="input-medium" disabled="true" onchange="getDepartments()">
						<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
				<c:if test="${!empty storeDropEnable}">
					<form:select path="storeId" class="input-medium needClear" onchange="getDepartments()">
						<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
			</li>
			<li><label>工程模式：</label>
				<c:if test="${!empty gongcheng}">
					<form:select path="projectMode" class="input-medium needClear" onchange="getDepartments()">
                        <form:option value="" label=""/>
						<form:options items="${fns:getDictList('project_mode')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
				<c:if test="${empty gongcheng}">
					<form:select path="projectMode" class="input-medium needClear"  disabled="true" onchange="getDepartments()">
						<form:option value="" label=""/>
						<form:options items="${fns:getDictList('project_mode')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
			</li>
			<li><label>区域：</label>
				<form:select path="enginDepartId"  class="input-medium needClear" id="enginDepartId" >
					<form:option value=""></form:option>
				</form:select>
			</li>
			
			<li><label>客户姓名：</label>
				<form:input path="custumer" htmlEscape="false" maxlength="16" class="input-medium"/>
			</li>
			<li><label>客户地址：</label>
				<form:input path="customerAddress" htmlEscape="false" maxlength="16" class="input-medium"/>
			</li>
			
			<li><label>订单状态：</label>
				<form:select path="orderStatusNumber" class="input-medium needClear">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('order_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			
			<li><label>项目经理：</label>
				<form:input path="itemManager" htmlEscape="false" maxlength="16" class="input-medium"/>
			</li>
			
			<li><label>任务包类型：</label>
				<form:select path="taskPackageType" class="input-medium needClear">
					<form:option value="" label=""/>
					<form:options items="${fns:getTaskPackageTypeList()}" itemLabel="name" itemValue="id" htmlEscape="false"/>
				</form:select>
			</li>
			
			<li><label>任务包状态：</label> 
				<form:select path="packageStateId" class="input-medium needClear">
					<form:option value="" label="" />
					<form:options items="${fns:getDictList('taskpackage_status')}" itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
			</li>
			
			<li><label>组长手机号：</label>
				<form:input path="groupLeaderPhone" htmlEscape="false" maxlength="16" class="input-medium"/>
			</li>
			
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="button" value="查询" onclick="subClick()"/></li>
			<li class="btns">
				<a class="btn btn-primary" onclick="exporta()">导出Excel</a>
			</li>
			<li class="btns"><input class="btn btn-primary clearBtn" type="button" value="清空" onclick="clearCondition()"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>门店</th>
				<th>区域</th>
				<th>客户姓名</th>
				<th>客户地址</th>
				<th>项目经理</th>
				<th>质检员</th>
				<th>订单状态</th>
				<th>订单接单时间</th>
				<th>任务包类型</th>
				<th>任务包状态</th>
				<th>工人组长</th>
				<th>工人组组长手机号</th>
				<th>任务包开始时间</th>
				<th>任务包结束时间</th>
				<th>管理扣款</th>
				<th>延期扣款</th>
				<th>公司扣款</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="lbaorCapital">
			<tr>
				<td>${fns:getStoreLabel(lbaorCapital.storeId, '')}</td>
				<td>${lbaorCapital.departmentName }</td>
				<td>${lbaorCapital.custumer }</td>
				<td>${lbaorCapital.customerAddress }</td>
				<td>${lbaorCapital.itemManager }</td>
				<td>${lbaorCapital.inspectorName }</td>
				<td>${lbaorCapital.orderStatusDescription }</td>
				<td>
					<fmt:formatDate value="${lbaorCapital.getOrderDatetime }" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${fns:getTaskPackageTypeLabel(lbaorCapital.taskPackageType, '')}
				</td>
				<td>${lbaorCapital.packageStatename }</td>
				<td>${lbaorCapital.groupLeaderName }</td>
				<td>${lbaorCapital.groupLeaderPhone }</td>
				<td>
					<fmt:formatDate value="${lbaorCapital.actualStartdate }" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${lbaorCapital.actualEnddate }" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				
				<td>
					<c:if test="${lbaorCapital.punishAmerce == null || lbaorCapital.punishAmerce ==0 || lbaorCapital.punishAmerce ==0.0}">
						0
					</c:if>
					<c:if test="${lbaorCapital.punishAmerce != null && lbaorCapital.punishAmerce != 0.00 && lbaorCapital.punishAmerce != 0}">
						${lbaorCapital.punishAmerce}
					</c:if>
				</td>
				<td>
					<c:if test="${lbaorCapital.delayAmerce == null || lbaorCapital.delayAmerce ==0 || lbaorCapital.delayAmerce ==0.0}">
						0
					</c:if>
					<c:if test="${lbaorCapital.delayAmerce != null && lbaorCapital.delayAmerce != 0.00 && lbaorCapital.delayAmerce != 0}">
						${lbaorCapital.delayAmerce}
					</c:if>
				</td>
				<td>
					<c:if test="${lbaorCapital.companyDeductAmount == null || lbaorCapital.companyDeductAmount == 0 || lbaorCapital.companyDeductAmount==0.0}">
						0
					</c:if>
					<c:if test="${lbaorCapital.companyDeductAmount != null && lbaorCapital.companyDeductAmount != 0.00 && lbaorCapital.companyDeductAmount != 0}">
						${lbaorCapital.companyDeductAmount}
					</c:if>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>