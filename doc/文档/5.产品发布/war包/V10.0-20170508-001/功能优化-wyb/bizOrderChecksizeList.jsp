<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>上传复尺管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
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
		
		function operation(orderChecksizeId){
			$("#orderChecksizeId").val(orderChecksizeId);
			$("#searchForm").attr("action", "${ctx}/bizorderchecksize/bizOrderChecksize/operation");
			$("#searchForm").submit();
			$("#searchForm").attr("action", "${ctx}/bizorderchecksize/bizOrderChecksize/checksizeList");
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/bizorderchecksize/bizOrderChecksize/list">上传厂家复尺列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="bizOrderChecksize" action="${ctx}/bizorderchecksize/bizOrderChecksize/checksizeList" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		
		<input type="hidden" id= "orderChecksizeId" name="orderChecksizeId">
		<ul class="ul-form">
			<li><label>门店：</label>
				<c:if test="${empty storeDropEnable}">
					<form:select path="storeId" class="input-medium" disabled="true">
						<form:option value="" label=""/>
						<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
				<c:if test="${!empty storeDropEnable}">
					<form:select path="storeId" class="input-medium needClear">
						<form:option value="" label=""/>
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
			<li><label>订单编号：</label>
				<form:input path="orderNumber" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>顾客姓名：</label>
				<form:input path="customerName" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>项目经理：</label>
				<form:input path="itemManager" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>复尺类型：</label>
				<form:select path="checksizeType" class="input-medium needClear">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('check_scale')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>复尺状态：</label>
				<form:select path="checksizeStatus" class="input-medium needClear">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('checksize_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>合同开工日期：</label>
				<input name="beginContractStartDate" type="text" id="beginContractStartDate" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${bizOrderChecksize.beginContractStartDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'endContractStartDate\')}',isShowClear:false});"/> - 
				<input name="endContractStartDate" type="text" id="endContractStartDate" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${bizOrderChecksize.endContractStartDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'beginContractStartDate\')}',isShowClear:false});"/>
			</li>
			<li><label>提交申请日期：</label>
				<input name="beginCreateDate" type="text" id="beginCreateDate" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${bizOrderChecksize.beginCreateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'endCreateDate\')}',isShowClear:false});"/> - 
				<input name="endCreateDate" type="text" id="endCreateDate" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${bizOrderChecksize.endCreateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'beginCreateDate\')}',isShowClear:false});"/>
			</li>
			<li><label>期望复尺日期：</label>
				<input name="beginChecksizeDate" type="text" id="beginChecksizeDate" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${bizOrderChecksize.beginChecksizeDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'endChecksizeDate\')}',isShowClear:false});"/> - 
				<input name="endChecksizeDate" type="text" id="endChecksizeDate" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${bizOrderChecksize.endChecksizeDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'beginChecksizeDate\')}',isShowClear:false});"/>
			</li>
			<li><label style="width:110px;">供应商确认日期：</label>
				<input name="beginSupplierConfirmDate" type="text" id="beginSupplierConfirmDate" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${bizOrderChecksize.beginSupplierConfirmDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'endSupplierConfirmDate\')}',isShowClear:false});"/> - 
				<input name="endSupplierConfirmDate" type="text" id="endSupplierConfirmDate" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${bizOrderChecksize.endSupplierConfirmDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'beginSupplierConfirmDate\')}',isShowClear:false});"/>
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
				<th>序号</th>
				<th>门店</th>
				<th>工程模式</th>
				<th>提交申请时间</th>
				<th>期望复尺日期</th>
				<th>复尺内容</th>
				<th>复尺状态</th>
				<th>供应商确认时间</th>
				<th>回复</th>
				<th>项目经理</th>
				<th>顾客</th>
				<th>合同开工日期</th>
				<th>合同竣工日期</th>
				<!-- <th>复尺照片</th> -->
				<shiro:hasPermission name="bizorderchecksize:bizOrderChecksize:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="bizOrderChecksize" varStatus="status">
			<tr>
				<td>${status.index+1 }</td>
				<td>
					${fns:getStoreLabel(bizOrderChecksize.storeId, '')}
				</td>
				<td>
					${fns:getDictLabel(bizOrderChecksize.projectMode, 'project_mode', '')}
				</td>
				<td>
					<fmt:formatDate value="${bizOrderChecksize.createDate}" type="both"/>
				</td>
				<td>
					<fmt:formatDate value="${bizOrderChecksize.checksizeDate}" type="date"/>
				</td>
				<td>
					${bizOrderChecksize.checksizeTypeName}
				</td>
				<td>
					${bizOrderChecksize.checksizeStatusName}
				</td>
				<td>
					<fmt:formatDate value="${bizOrderChecksize.supplierConfirmDate}" type="date"/>
				</td>
				<td>
					${bizOrderChecksize.materialDepartmentDealReply}
				</td>
				<td>
					${bizOrderChecksize.itemManager}</br>${bizOrderChecksize.phone }
				</td>
				<td>${bizOrderChecksize.communityName }-${bizOrderChecksize.buildNumber }-${bizOrderChecksize.buildUnit }-${bizOrderChecksize.buildRoom }-${bizOrderChecksize.customerName }</td>
				<td>
					<fmt:formatDate value="${bizOrderChecksize.contractStartDate}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					<fmt:formatDate value="${bizOrderChecksize.contractEndDate}" pattern="yyyy-MM-dd"/>
				</td>
				
				
				
				<%-- <td>
					<c:if test="${bizOrderChecksize.checksizePhoto == 0 }">0</c:if>
					<c:if test="${bizOrderChecksize.checksizePhoto > 0 }">
						<a href="${ctx}/bizorderchecksize/bizOrderChecksize/checksizePhoto?orderChecksizeId=${bizOrderChecksize.orderChecksizeId}">
							${bizOrderChecksize.checksizePhoto }
						</a>
					</c:if>张
				</td> --%>
				<shiro:hasPermission name="bizorderchecksize:bizOrderChecksize:edit"><td>
    				<c:if test="${bizOrderChecksize.checksizeStatus eq 10 }">
	    				<%-- <a href="${ctx}/bizorderchecksize/bizOrderChecksize/operation?orderChecksizeId=${bizOrderChecksize.orderChecksizeId}">处理</a> --%>
	    				<a href="#" onclick="operation('${bizOrderChecksize.orderChecksizeId}')">处理</a>
    				</c:if>
    				<a href="${ctx}/bizorderchecksize/bizOrderChecksize/checksizeDetails?orderChecksizeId=${bizOrderChecksize.orderChecksizeId}">详情</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>