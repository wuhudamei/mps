<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>付款单明细列表</title>
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
                            if('${bizOrderTaskpackagePaymentVo.enginDepartId}' == data[i].value){
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

			$("input[name='status']").removeAttr("checked");
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/ordertaskpackagepayment/bizOrderTaskpackagePayment/paymentLoadList">付款单明细列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="bizOrderTaskpackagePaymentVo" action="${ctx}/ordertaskpackagepayment/bizOrderTaskpackagePayment/paymentLoadList" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>门店：</label>
				<c:if test="${empty storeDropEnable}">
					<form:select path="storeId" class="input-medium" disabled="true">
						<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
				<c:if test="${!empty storeDropEnable}">
					<form:select path="storeId" class="input-medium needClear">
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
				<select id="enginDepartId" name="enginDepartId" class="input-medium needClear">

				</select>
			</li>

			<li>
				<label>客户姓名：</label>
				<form:input path="customerName" htmlEscape="false" maxlength="64" class="input-medium needClear"/>
			</li>
			<li>
				<label>项目经理：</label>
				<form:input path="realName" htmlEscape="false" maxlength="64" class="input-medium needClear"/>
			</li>
			<li>
				<label>工人组长：</label>
				<form:input path="groupRealname" htmlEscape="false" maxlength="64" class="input-medium needClear"/>
			</li>

			<li><label>付款单编号：</label>
				<form:input path="orderTaskpackagePaymentCode" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label style="width: 110px">付款单生成日期：</label>
				<input name="beginGeneratedDatetime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					   value="<fmt:formatDate value="${bizOrderTaskpackagePaymentVo.beginGeneratedDatetime}" pattern="yyyy-MM-dd"/>"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/> -
				<input name="endGeneratedDatetime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					   value="<fmt:formatDate value="${bizOrderTaskpackagePaymentVo.endGeneratedDatetime}" pattern="yyyy-MM-dd"/>"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</li>
			<li><label>付款单状态：</label>
				<c:forEach items="${fns:getDictListByType('payment_status')}" var="dict">
					<input type="checkbox" name="status" value="${dict.value}" <c:if test="${fns:checkIsExits(bizOrderTaskpackagePaymentVo.status,dict.value)}"> checked="checked"</c:if>/>${dict.label}&nbsp;
				</c:forEach>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" /></li>
			<li class="btns"><input class="btn btn-primary clearBtn" type="button" value="清空" onclick="clearCondition()"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>门店</th>
				<th>客户地址</th>
				<th>客户名称</th>
				<th>客户电话</th>
				<th>付款单编号</th>
				<th>任务包名称</th>
				<th>项目经理</th>
				<th>付款单生成日期</th>
				<th>工人</th>
				<th>付款类型</th>
				<th>结算金额</th>
				<th>付款单状态</th>
				<th>冻结/解冻详情</th>
				<shiro:hasPermission name="ordertaskpackagepayment:bizOrderTaskpackagePayment:view"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="payment">
			<tr>
				<td>${fns:getStoreLabel(payment.storeId, '')}</td>
				<td>${payment.customerMessage}</td>
				<td>${payment.customerName}</td>
				<td>${payment.customerPhone}</td>
				<td>${payment.orderTaskpackagePaymentCode}</td>
				<td>${payment.packageName}</td>
				<td>${payment.itemCustomer}</td>
				<td><fmt:formatDate value="${payment.generatedDatetime}" pattern="yyyy-MM-dd"/></td>
				<td>${payment.realName}</td>
				<td><c:if test="${payment.orderTaskpackagePaymentType eq '0'}">首款</c:if><c:if test="${payment.orderTaskpackagePaymentType eq '1'}">尾款</c:if></td>
				<td>${payment.amount}</td>
				<td>${fns:getOrderTaskpackagePaymentStatus(payment.status)}</td>
				<td>
				<c:if test="${payment.status == '18'}">
				<a href="${ctx}/ordertaskpackagepayment/bizOrderTaskpackagePayment/findfreezePaymentDetailList?bizOrderTaskpackagePaymentId=${payment.id}">详情</a>
				</c:if>
				</td>
				<shiro:hasPermission name="ordertaskpackagepayment:bizOrderTaskpackagePayment:view"><td>
					<a href="${ctx}/ordertaskpackagepayment/bizOrderTaskpackagePayment/paymentListView?id=${payment.id}">付款明细查看</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>