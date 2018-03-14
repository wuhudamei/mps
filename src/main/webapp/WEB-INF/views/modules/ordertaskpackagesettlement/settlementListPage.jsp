<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>结算单列表</title>
	<meta name="decorator" content="default"/>
	<meta content="*" name="Access-Control-Allow-Origin"><!--跨域请求  -->
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
		}
		// --全选框被单击---
			function ChkAllClick(sonName, cbAllId){
			    var arrSon = document.getElementsByName(sonName);
			 var cbAll = document.getElementById(cbAllId);
			 var tempState=cbAll.checked;
			 for(i=0;i<arrSon.length;i++) {
			  if(arrSon[i].checked!=tempState)
			           arrSon[i].click();
			 }
			}
			 
			// --子项复选框被单击---
			function ChkSonClick(sonName, cbAllId) {
			 var arrSon = document.getElementsByName(sonName);
			 var cbAll = document.getElementById(cbAllId);
			 for(var i=0; i<arrSon.length; i++) {
			     if(!arrSon[i].checked) {
			     cbAll.checked = false;
			     return;
			     }
			 }
			 cbAll.checked = true;
			}
		//加载区域信息
		function getDepartments(){
			$("#enginDepartId").html('');
			$.ajax({
				url:'${ctx}/engdept/bizEngineeringDepartment/departmentList',
				type:'post',
				dataType : 'json',
				data:{
					'storeId':$('#storeId').val(),
					'projectMode':$('#projectMode').val(),
					'employeeId':$('#employeeId').val(),
				},
				success:function(data){
					if(data == null){
						$("#enginDepartId").html('');
					} else {
						var html = "<option value=''></option>";
						for(var i=0;i<data.length;i++){
							var sec = "";
							if('${ settlementForDetail.enginDepartId }' == data[i].value){
			            		sec = "selected='selected'";
			            		$("#s2id_enginDepartId .select2-chosen").html(data[i].label);
			            	}
							html += "<option value='" + data[i].value +"' " + sec + ">" + data[i].label + "</option>";
						}
						html+='';
		        		$("#enginDepartId").append(html);
					}
				}
			});
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/ordertaskpackagesettlement/bizOrderTaskpackageSettlement/settlementList">结算单列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="settlementForDetail" action="${ctx}/ordertaskpackagesettlement/bizOrderTaskpackageSettlement/settlementList" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<input id="employeeId" name="employeeId" type="hidden" value="${employeeId}"/>
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
				<c:if test="${empty gongcheng}">
					<form:select path="projectMode" id="projectMode" class="input-medium" disabled="true" onchange="getDepartments()">
						<form:option value="" label=""/>
						<form:options items="${fns:getDictList('package_project_mode')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
				<c:if test="${!empty gongcheng}">
					<form:select path="projectMode" id="projectMode" class="input-medium needClear" onchange="getDepartments()">
						<form:option value="" label=""/>
						<form:options items="${fns:getDictList('package_project_mode')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
			</li>
			
			<li><label>区域：</label>
				<form:select path="enginDepartId" class="input-medium needClear" id="enginDepartId">
					<form:option value="${settlementForDetail.enginDepartId }" label="${settlementForDetail.departmentName }" />
				</form:select>
			</li>
			<li><label>结算单编号：</label>
				<form:input path="settlementNo" htmlEscape="false" maxlength="18" class="input-medium needClear"/>
			</li>
			
			<li><label>任务包名称：</label>
				<form:input path="packageName" htmlEscape="false" maxlength="18" class="input-medium needClear"/>
			</li>
			
			<li><label>工人组长姓名：</label>
				<form:input path="groupRealname" htmlEscape="false" maxlength="18" class="input-medium needClear"/>
			</li>
			
			<li><label>订单编号：</label>
				<form:input path="orderNumber" htmlEscape="false" maxlength="18" class="input-medium needClear"/>
			</li>

			<li><label>客户姓名：</label>
				<form:input path="customerName" htmlEscape="false" maxlength="18" class="input-medium needClear"/>
			</li>
			<li><label>项目经理：</label>
				<form:input path="itemCustomer" htmlEscape="false" maxlength="255" class="input-medium needClear"/>
			</li>
			<li><label>小区名称：</label>
				<form:input path="communityName" htmlEscape="false" maxlength="255" class="input-medium needClear"/>
			</li>
			<li><label style="width:160px;">任务包验收日期：</label>
				<input name="beginCheckDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate needClear"
					value="<fmt:formatDate value="${settlementForDetail.beginCheckDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/> - 
				<input name="endCheckDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate needClear"
					value="<fmt:formatDate value="${settlementForDetail.endCheckDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</li>
			<li><label style="width:160px;">结算单创建日期：</label>
				<input name="beginCreateDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate needClear"
					value="<fmt:formatDate value="${settlementForDetail.beginCreateDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/> - 
				<input name="endCreateDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate needClear"
					value="<fmt:formatDate value="${settlementForDetail.endCreateDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</li>
			<li><label style="width:160px;">工人同意薪酬时间：</label>
				<input name="beginApproveSalaryTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate needClear"
					value="<fmt:formatDate value="${settlementForDetail.beginApproveSalaryTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/> - 
				<input name="endApproveSalaryTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate needClear"
					value="<fmt:formatDate value="${settlementForDetail.endApproveSalaryTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li style="width:100%">
				<label>结算单状态：</label>
				<input id="chkAll" name="chkAll" type="checkbox" value="全选" onclick="ChkAllClick('status','chkAll')" />全选
			</li>
			<li style="height:90px">
				<c:forEach items="${fns:getDictList('settlement_status')}" var="dict">
					<input type="checkbox" name="status" id="status" value="${dict.value}"  onclick="ChkSonClick('status','chkAll')"  <c:if test="${fns:checkIsExits(settlementForDetail.status,dict.value)}"> checked="checked"</c:if>/>${dict.label}&nbsp;
				</c:forEach>
			</li>
			<li class="btns" style="width:100px"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="btns"><input id="btnClear" class="btn btn-primary clearBtn" type="button" value="清空" onclick="clearCondition()"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>门店</th>
				<th>工程模式</th>
				<th>区域</th>
				<th>订单编号</th>
				<th>结算单编号</th>
				<th>结算单状态</th>
				<th>任务包编号</th>
				<th>任务包名称</th>
				<th>小区名称</th>
				<th>客户姓名</th>
				<th>项目经理</th>
				<th>工人组长</th>
				<th>结算金额</th>
				<th>预算金额</th>
				<th>任务包验收日期</th>
				<th>结算单创建日期</th>
				<th>工人同意薪酬时间</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="settlement" varStatus="index">
			<tr>
				<td>
					${fns:getStoreLabel(settlement.storeId, '')}
				</td>
				<td>${fns:getDictLabel(settlement.projectMode, 'package_project_mode', '')}</td>
				<td>
					${settlement.departmentName }
				</td>
				<td>
					${settlement.orderNumber }
				</td>
				<td>
					<a href="${ctx}/ordertaskpackagesettlement/bizOrderTaskpackageSettlementVo/details?id=${settlement.id}">${settlement.settlementNo }</a>
				</td>
				<td>
					<c:if test="${settlement.status == '10' }">待质检员复核</c:if>
					<c:if test="${settlement.status == '20' }">质检员已复核</c:if>
					<c:if test="${settlement.status == '50' }">项目经理已提交</c:if>
					<c:if test="${settlement.status == '60' }">工人不认可分配金额</c:if>
					<c:if test="${settlement.status == '65' }">工人已确认分配金额</c:if>
					<c:if test="${settlement.status == '70' }">结算员已驳回</c:if>
					<c:if test="${settlement.status == '80' }">结算员已通过</c:if>
					<c:if test="${settlement.status == '90' }">已付首款</c:if>
					<c:if test="${settlement.status == '100' }">已付款完成</c:if>
				</td>
				<td>
					${settlement.orderTaskpackageCode }
				</td>
				<td>
					${settlement.packageName }
				</td>
				<td>
					${settlement.customerMessage }
				</td>
				<td>
					${settlement.customerName }
				</td>
				<td>
					${settlement.itemCustomer }
				</td>
				<td>
					${settlement.groupRealname }
				</td>
				<td>
					${settlement.settlementAmount }
				</td>
				<td>
					${settlement.budgetAmount }
				</td>
				<td>
					<fmt:formatDate value="${settlement.checkDate }" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					<fmt:formatDate value="${settlement.createDate }" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					<fmt:formatDate value="${settlement.approveSalaryTime }" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<a href="${ctx}/ordertaskpackagesettlement/bizOrderTaskpackageSettlementVo/details?id=${settlement.id}">详情</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>