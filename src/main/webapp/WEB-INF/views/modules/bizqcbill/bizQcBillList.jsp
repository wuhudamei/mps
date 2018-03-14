<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>质检报告管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
		    closeTip();
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
        function  formSubmit(){


            $("#searchForm").submit();

            loading("查询中...请稍后")
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
		 $(function(){
			 $("#isEqual").click(function() {
	           	 $('input[name="isEqual"]').attr("checked",this.checked); 
	       	 });
	       	 var $status = $("input[name='isEqual']");
	       	 $status.click(function(){
	            $("#isEqual").attr("checked",$status.length == $("input[name='isEqual']:checked").length ? true : false);
	         });
		 });
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/bizqcbill/bizQcBill/list">质检报告列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="bizQcBill" action="${ctx}/bizqcbill/bizQcBill/bizqcbillList" method="post" class="breadcrumb form-search">
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
					<form:select path="projectMode" class="input-medium" disabled="true">
						<form:option value="" label=""/>
						<form:options items="${fns:getDictList('project_mode')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
				<c:if test="${!empty gongcheng}">
					<form:select path="projectMode" class="input-medium needClear">
						<form:option value="" label=""/>
						<form:options items="${fns:getDictList('project_mode')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
					
			</li>
			<li><label>报告类型：</label>
				<form:select path="qcBillType" class="input-medium needClear">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('qcBill_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>订单编号：</label>
				<form:input path="orderNumber" htmlEscape="false" maxlength="25" class="input-medium"/>
			</li>
			<li><label>质检单编号：</label>
				<form:input path="qcBillCode" htmlEscape="false" maxlength="25" class="input-medium"/>
			</li>
			<li><label>质检员：</label>
				<form:input path="inspectorRealName" htmlEscape="false" maxlength="11" class="input-medium"/>
			</li>
			<li><label>客户姓名：</label>
				<form:input path="customerName" htmlEscape="false" maxlength="11" class="input-medium"/>
			</li>
			<li><label>检查人：</label>
				<form:input path="checkRealName" htmlEscape="false" maxlength="11" class="input-medium"/>
			</li>
			<li><label>提交报告日期：</label>
				<input name="beginCheckDatetime" type="text" readonly="readonly" id="beginCheckDatetime" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${bizQcBill.beginCheckDatetime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'endCheckDatetime\')}',isShowClear:false});"/> - 
				<input name="endCheckDatetime" type="text" readonly="readonly" id="endCheckDatetime" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${bizQcBill.endCheckDatetime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'beginCheckDatetime\')}',isShowClear:false});"/>
			</li>
			<li>
				<input type="checkbox" name="isEqual" value="1" id="isEqual" <c:if test="${fns:checkIsExits(bizQcBill.isEqual,'1')}"> checked="checked"</c:if>/>总分与实际得分不相等
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="button" value="查询" onclick="formSubmit()"/></li>
			<li class="btns"><input class="btn btn-primary clearBtn" type="button" value="清空" onclick="clearCondition()"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>门店</th>
				<th>工程模式</th>
				<th>提交报告日期</th>
				<th>质检报告编号</th>
				<th>报告类型</th>
				<th>约检节点</th>
				<th>质检员</th>
				<th>检查人</th>
				<th>订单编号</th>
				<th>顾客</th>
				<th>项目经理</th>
				<th>总分</th>
				<th>实际得分</th>
				<th>复检次数</th>
				<shiro:hasPermission name="bizqcbill:bizQcBill:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="bizQcBill" varStatus="status">
			<tr>
				<td>
					${fns:getStoreLabel(bizQcBill.storeId, '')}
				</td>
				<td>
					${fns:getDictLabel(bizQcBill.projectMode, 'project_mode', '')}
				</td>
				<td>
					<fmt:formatDate value="${bizQcBill.checkDatetime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${bizQcBill.qcBillCode}
				</td>
				<td>
					${fns:getDictLabel(bizQcBill.qcBillType, 'qcBill_type', '')}
					<c:if test="${bizQcBill.isRecheck=='1' }">
						【复检】
					</c:if>
				</td>
				<td>
					${bizQcBill.qcCheckNodeName}
				</td>
				<td>
					${bizQcBill.inspectorRealName}
				</td>
				<td>
					${bizQcBill.checkRealName}
				</td>
				<td>
					${bizQcBill.orderNumber}
				</td>
				<td>
					${bizQcBill.communityName}-${bizQcBill.buildNumber}-${bizQcBill.buildUnit}-${bizQcBill.buildRoom}-${bizQcBill.customerName}
				</td>
				<td>
					${bizQcBill.managerRealName}
				</td>
				<td>
					${bizQcBill.totalScore}
				</td>
				<td>
					${bizQcBill.gotScore}
				</td>
				<td>
					${bizQcBill.recheckTimes}
				</td>
				<shiro:hasPermission name="bizqcbill:bizQcBill:edit"><td>
    				<a href="${ctx}/bizorderqcbill/bizOrderQcBill/details?qcBillId=${bizQcBill.qcBillId}">详情</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>