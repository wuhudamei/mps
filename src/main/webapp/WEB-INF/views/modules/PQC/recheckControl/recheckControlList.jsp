<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>复检单监控表</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			var times = ${recheckControl.recheckTimes}
			$("#recheckTimesId option").each(function(){

			    if($(this).val()==times){

			        $(this).attr("selected","selected");
			        $("#s2id_recheckTimesId").find(".select2-chosen").text(times);

				}



			})
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
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/pqc/recheckControl/recheckControl/list">复检单监控表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="recheckControl" action="${ctx}/pqc/recheckControl/recheckControl/recheckControlList" method="post" class="breadcrumb form-search">
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
					<form:select path="projectMode" class="input-medium needClear" disabled="true">
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
			<li><label>订单编号：</label>
				<form:input path="orderNumber" htmlEscape="false" maxlength="100" class="input-medium needClear"/>
			</li>
			
			<li><label>客户姓名：</label>
				<form:input path="customerName" htmlEscape="false" maxlength="16" class="input-medium needClear"/>
			</li>
			
			<li><label>质检报告编号：</label>
				<form:input path="qcBillCode" htmlEscape="false" maxlength="64" class="input-medium needClear"/>
			<li><label>复检次数：</label>
				<select class="input-medium needClear" name="recheckTimes" id="recheckTimesId">
					<option value=""></option>
					<option value="0">0</option>
					<option value="1">1</option>
					<option value="2">2</option>
					<option value="3">3</option>
					<option value="4">4</option>
					<option value="5">5</option>
					<option value="6">6</option>
					<option value="7">7</option>
					<option value="8">8</option>
					<option value="9">9</option>
					<option value="10">10</option>


				</select>
			</li>





			<li><label>复检单状态：</label>
			
					<form:select path="status" class="input-medium" >
						<form:option value="" label=""/>
						<form:options items="${fns:getDictList('recheck_qcBill_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
			
			
				
			</li>


			<li><label style="width:120px">复检单生成日期：</label>
				<input name="beginCreateDate" type="text" readonly="readonly" id="beginCreateDate" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${recheckControl.beginCreateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'endCreateDate\')}',isShowClear:false});"/> - 
				<input name="endCreateDate" type="text" readonly="readonly" id="endCreateDate" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${recheckControl.endCreateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'beginCreateDate\')}',isShowClear:false});"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
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
				<th>订单编号</th>
				<th>顾客信息</th>
				<th>质检报告编号</th>
				<th>复检单生成日期</th>
				<th>项目经理申请日期</th>
				<th>质检员检查日期</th>
				<th>复检次数</th>
				<th>复检单状态</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="recheck" varStatus="status">
			<tr>
				<td>
					${fns:getStoreLabel(recheck.storeId, '')}
				</td>
				<td>
					${fns:getDictLabel(recheck.projectMode, 'project_mode', '')}
				</td>
				<td>
					${recheck.orderNumber }
				</td>
				<td>
					${recheck.communityName }-${recheck.buildNumber }-${recheck.buildUnit }-${recheck.buildRoom }-${recheck.customerName }
				</td>
				<td>
					${recheck.qcBillCode }
				</td>
				<td>
					<fmt:formatDate value="${recheck.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${recheck.expectCheckDatetime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${recheck.checkDatetime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${recheck.recheckTimes }
				</td>
				<td>
					${fns:getDictLabel(recheck.status, 'recheck_qcBill_status', '')} 
				</td>
				<shiro:hasPermission name="bizqcbill:bizQcBill:edit"><td>
    				<c:if test="${recheck.status>2 }">
	    				<a href="${ctx}/bizorderqcbill/bizOrderQcBill/details?qcBillId=${recheck.qcBillId}">详情</a>
    				</c:if>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>