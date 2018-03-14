<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/jquery.form.js"></script>
<head>
	<title>工程进度看板</title>
	<meta name="decorator" content="default"/>
	<style type="text/css">
		table th.vertical{height:58px;line-height:58px;}
	</style>
	<script type="text/javascript">
		$(document).ready(function() {
			//导出excel
			$("#exportExcel").click(function(){
				//top.$.jBox.confirm("确认要导出用户数据吗？","系统提示",function(v,h,f){
					//if(v=="ok"){
						$("#searchForm").attr("action","${ctx}/progresskanban/progressKanban/export");
						$("#searchForm").submit();
					//}
				//},{buttonsFocus:1});
				//top.$('.jbox-body .jbox-icon').css('top','55px');			
			});
		});
		function page(n, s) {
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
		<li class="active"><a href="${ctx}/progresskanban2/progressKanban2/preList">工程进度看板</a></li>
		<%-- <shiro:hasPermission name="ordertaskpack:orderTaskpack:edit"><li><a href="${ctx}/ordertaskpack/orderTaskpack/form">订单添加</a></li></shiro:hasPermission> --%>
	</ul>
	<form:form id="searchForm" modelAttribute="progressKanban2" action="${ctx}/progresskanban2/progressKanban2/list" method="post" class="breadcrumb form-search" onsubmit="return checkStoreID();">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<%-- <li><label>所属门店：</label> 
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
			<li><label>订单编号：</label>
				<form:input path="orderNumber" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>顾客姓名：</label>
				<form:input path="customerName" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>项目经理：</label>
				<form:input path="managerName" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>实际开工日期：</label>
				<input name="actualBegin" type="text" id="actualBegin" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${progressKanban2.actualBegin}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'actualEnd\')}',isShowClear:false});" onblur="actualBeginChange();"/> &nbsp;至&nbsp;
					
				<input name="actualEnd" type="text" id="actualEnd" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${progressKanban2.actualEnd}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'actualBegin\')}',isShowClear:false});" onblur="actualEndChange();"/>
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
				<th class="vertical">订单编号</th>
				<th class="vertical">工程模式</th>
				<th class="vertical">顾客</th>
				<th class="vertical">项目经理</th>
				<th class="vertical">质检员</th>
				<th class="vertical">实际开工</th>
				<th class="vertical">合同工期</th>
				<th style="text-align:center">
					设计交底
					<table class="table table-striped table-bordered table-condensed">
						<thead>
							<tr>
								<th>计划交底</th>
								<th>实际交底</th>
								<th>延期</th>
							</tr>
						</thead>
					</table>
				</th>
				<th style="text-align:center">
					实际开工
					<table class="table table-striped table-bordered table-condensed">
						<thead>
							<tr>
								<th>合同开工</th>
								<th>合同竣工</th>
								<th>实际开工</th>
								<th>延期</th>
								<th>延期原因</th>
							</tr>
						</thead>
					</table>
				</th>
				<c:forEach items="${constructionScheduleList }" var="csList">
				<th style="text-align:center">
					${csList.constructionScheduleName }
					<table class="table table-striped table-bordered table-condensed">
						<thead>
							<tr>
								<th>执行计划</th>
								<th>实际完成</th>
								<th>延期</th>
							</tr>
						</thead>
					</table>
				</th>
				</c:forEach>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="progressKanban" varStatus="status">
			<tr>
				<td>
					${progressKanban.orderNumber }
				</td>
				<td>${fns:getDictLabel(progressKanban.projectMode, 'project_mode','')}</td>
				<td>
					${progressKanban.customerName}
				</td>
				<td>
					${progressKanban.managerName }
				</td>
				<td>
					${progressKanban.inspectorName}
				</td>
				<td>
					<fmt:formatDate type="date" value="${progressKanban.actualStartDate}" />
				</td>
				<td>
					${progressKanban.contractTime}
				</td>
				<td>
					<table>
						<tbody>
							<tr>
								<td>
									<input type="hidden" id="contractStartDate" value="<fmt:formatDate type="date" value="${progressKanban.contractStartDate}"/>"/>
									<fmt:formatDate type="date" value="${progressKanban.contractStartDate}"/>
								</td>
								<td>
									<c:forEach items="${orderDiscloseList }" var="disclose">
										<c:if test="${disclose.orderId == progressKanban.id }">
											<input type="hidden" id="discloseDate" value="<fmt:formatDate type="date" value="${disclose.discloseDate}"/>"/>
											<fmt:formatDate type="date" value="${disclose.discloseDate}"/>
										</c:if>
									</c:forEach>
								</td>
								<td>
									<%-- <c:forEach items="${orderDiscloseList }" var="disclose">
									<c:if test="${disclose.orderId == progressKanban.id }"> --%>
										${progressKanban.discloseDays }
									<%-- </c:if>
									</c:forEach> --%>
								</td>
							</tr>
						</tbody>
					</table>
				</td>
				<td>
					<table>
						<tbody>
							<tr>
								<td>
									<fmt:formatDate type="date" value="${progressKanban.contractStartDate }"/>
								</td>
								<td>
									<fmt:formatDate type="date" value="${progressKanban.contractEndDate }"/>
								</td>
								<td>
									<fmt:formatDate type="date" value="${progressKanban.actualStartDate }"/>
								</td>
								<td>
									${progressKanban.actualDelayDays }
								</td>
								<td>
									${fns:getDictLabel(progressKanban.delayType, 'confirmstart_delaytype', '')}
									<%-- <c:if test="${progressKanban.delayType == '0'}">客户原因</c:if>
									<c:if test="${progressKanban.delayType == '1'}">公司原因</c:if> --%>
								</td>
							</tr>
						</tbody>
					</table>								
				</td>
				
				<c:forEach items="${constructionScheduleList }" var="scheduleList">
				<td id="${csList.id }">
				<c:forEach items="${nodePlanList }" var="npList">
				<c:if test="${npList.orderId == progressKanban.id }">
				<c:if test="${npList.nodeIndex == scheduleList.sort }">
					<table>
					<tbody>
						<td>
							<tr><td><fmt:formatDate type="date" value="${npList.exeDoneDate }"/></td>
							<td><fmt:formatDate type="date" value="${npList.realDoneDate }"/></td>
							<td>
								${npList.realLessExe }
							</td></tr>
						</td>
					</tbody>
					</table>
					</c:if>
					</c:if>
					</c:forEach>
				</td>
				</c:forEach>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>