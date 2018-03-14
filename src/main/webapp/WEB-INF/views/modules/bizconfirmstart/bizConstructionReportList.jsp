<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>大统计表-施工中查询</title>
	<meta name="decorator" content="default"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Worker/css/globalUtil.css"/>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/history.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#allCheck").click(function () {
	            if ($(this).attr("checked")) { // 全选 
	                $("input[name='installStatus']").each(function () {
	                    $(this).attr("checked", true);
	                });
	            } else { // 取消全选 
	                $("input[name='installStatus']").each(function () {
	                    $(this).attr("checked", false);
	                });
	            }
	        });
			
			var size = $("#countOrderSize").val();
			var otherSize = $("#countOther").val();

			if(size == '0'){
				if(otherSize != '0'){
					$("#numberSize").text(parseInt(otherSize));
				}else{
					$("#numberSize").text('');
				}
			}else{
				if(otherSize == '0'){
					$("#numberSize").text('');
				}else{
					$("#numberSize").text(parseInt(otherSize));
				}
				
			}
			
			if(otherSize == '0'){
				$("#otherSize").text('');
			}else{
				if(parseInt(otherSize) - parseInt(size) == 0){
					$("#otherSize").text('');
				}else{
					$("#otherSize").text(parseInt(otherSize)-parseInt(size));
				}
			}
			
		});
	
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
		
		//检验所属门店是否有值
		function checkStoreID(){
			var storeID = $("#storeID").val();
			var storeIDAll = $("#storeIDAll").val();
			var isOldHouse = $("#isOldHouse").val()

			if(!storeID && !storeIDAll){
				alert("必须选择一个所属门店！");
				return false;
			}else if(!isOldHouse){
				alert("必须选择新老房！");
				return false;
			}else{
				return true;
				/* $("#searchForm").submit();//jquery提交form */
			}
		}
		
		//清空查询条件
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
		<li class="active"><a href="${ctx}/bizorderconstructionreport/bizOrderConstructionReport/preList">大统计表-施工中列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="bizOrderConstructionReport" action="${ctx}/bizorderconstructionreport/bizOrderConstructionReport/list" 
		method="post" class="breadcrumb form-search" onsubmit="return checkStoreID();">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<%-- <li><label>门店：</label> 
				<form:select path="storeId" class="input-medium needClear" id="storeID">
					<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
			</li> --%>
			<li><label>门店：</label>
				<c:if test="${empty storeDropEnable}">
					<form:select path="storeId" class="input-medium" disabled="true" id="storeID">
						<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
				<c:if test="${!empty storeDropEnable}">
					<form:select path="storeId" class="input-medium needClear" id="storeIDAll">
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
			<li><label>新老房：</label>
				<form:select path="houseIsNew" class="input-medium needClear" id="isOldHouse">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('biz_is_new_house')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<%-- <li><label>订单编号：</label>
				<form:input path="orderNumber" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>客户姓名：</label>
				<form:input path="customerName" htmlEscape="false" maxlength="16" class="input-medium"/>
			</li> --%>
			<li><label>合同开工日期：</label>
				<input name="contractStartDateBegin" type="text" id="contractStartDateBegin" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${bizOrderConstructionReport.contractStartDateBegin}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'contractEndDateEnd\')}',isShowClear:false});"/> &nbsp;至&nbsp;
					
				<input name="contractEndDateEnd" type="text" id="contractEndDateEnd" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${bizOrderConstructionReport.contractEndDateEnd}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'contractStartDateBegin\')}',isShowClear:false});"/>
			</li>
			<li><label>实际开工日期：</label>
				<input name="actualStartDateBegin" type="text" id="actualStartDateBegin" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${bizOrderConstructionReport.actualStartDateBegin}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'actualStartDateEnd\')}',isShowClear:false});"/> &nbsp;至&nbsp;
					
				<input name="actualStartDateEnd" type="text" id="actualStartDateEnd" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${bizOrderConstructionReport.actualStartDateEnd}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'actualStartDateBegin\')}',isShowClear:false});"/>
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
				<th>订单总数</th>
				<th>其他</th>
				<c:forEach items="${ScheduleList }" var ="list">
				<th>${list.constructionScheduleName }</th>
				</c:forEach>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${StoreList}" var="order" varStatus="status">
			<tr>
				<td>${fns:getStoreLabel(order.storeId, '')}</td>
				<td><c:if test="${modeType == '' || modeType == null }">全部</c:if>
				<c:if test="${modeType == '1'}">产业</c:if><c:if test="${modeType == '2'}">传统</c:if></td>
				<td style="color:red"><b id="numberSize"></b></td>
				<td><b id="otherSize"></b></td>
				<c:set var="nowSum" value="0"></c:set>
				<c:forEach items="${ScheduleList }" var ="list1">
					<td id="${list1.id }">
					<c:forEach items="${page.list}" var="order1" varStatus="status">
						<c:if test="${list1.sort == order1.nodeIndex}">
							<c:set var="nowSum" value="${nowSum + order1.countOrder}"></c:set>
							${order1.countOrder }
						</c:if>
					</c:forEach>
					</td>
				</c:forEach>
				<input type="hidden" id="countOrderSize" value="${nowSum }"/>
			</tr>
		</c:forEach>
		</tbody>
		
		<c:set var="count" value="0"></c:set>
		<c:forEach items="${page.list}" var="order1" varStatus="status">
			<c:set var="count" value="${count + order1.countOrder}"></c:set>
		</c:forEach>
		<input type="hidden" id="countOther" value="${count }"/>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>