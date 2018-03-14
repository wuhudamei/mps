<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>质检罚款明细查询</title>
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
	</script>
</head>
<body>
<c:set var="user" value="${fns:getUser()}"></c:set>
<ul class="nav nav-tabs">
	<li class="active"><a href="${ctx}/unqualifiedItemsStatistics/list">不合格项统计</a></li>
	</ul>
	<form:form id="searchForm"  modelAttribute="unqualifiedItemsStatisticsEntity" action="${ctx}/unqualifiedItemsStatistics/listInfo" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>门店：</label>
				<form:select path="storeId"  class="input-medium needClear">
						<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			
			<li><label class="control-label">工程模式：</label>
		
			
			<c:if test="${user.projectMode ==3}">
			
			<form:select path="projectMode" class="input-medium needClear">
							<form:option value="" label="" />
					<form:options items="${fns:getDictList('project_mode')}"
						itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
			</c:if>
			<c:if test="${user.projectMode !=3}">
		
			<form:select path="projectMode" class="input-medium needClear">
					
					<form:option label="${fns:getDictLabel(user.projectMode, 'project_mode', '')}"
					value="${user.projectMode}"/>
				</form:select>
			</c:if>
			</li>
			
			<li><label>订单编号：</label>
			<form:input path="orderNumber" htmlEscape="false" maxlength="30" class="input-large"/>
			<li><label>客户姓名：</label>
				<form:input path="customerName" htmlEscape="false" maxlength="11" class="input-large"/>
			</li>
			</li>
			<li><label>质检员：</label>
			<form:input path="orderInspectName" htmlEscape="false" maxlength="11" class="input-large"/>
			</li>
			<li><label>提交报告日期：</label>
				<input name="startDate" type="text" readonly="readonly"     value='<fmt:formatDate value="${startDate }"  pattern="yyyy-MM-dd HH:mm:ss"/>' id="beginCheckDatetime" maxlength="20" class="input-medium Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'endCheckDatetime\')}',isShowClear:false});"/>
				<input name="endDate" type="text" readonly="readonly" value='<fmt:formatDate value="${endDate }"  pattern="yyyy-MM-dd HH:mm:ss"/>' id="endCheckDatetime" maxlength="20" class="input-medium Wdate"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'beginCheckDatetime\')}',isShowClear:false});"/>   
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
				<th>质检员</th>
				<th>合格项</th>
				<th>警告次数</th>
				<th>现场整改次数</th>
				<th>罚款次数</th>
				<th>罚款金额</th>
				<th>限期整改次数</th>
				<th>限期整改-线上整改次数</th>
				<th>限期整改-线下整改次数</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="unqualifiedItem" varStatus="status">
			<tr>
			
				<td>
					${fns:getStoreLabel(unqualifiedItem.storeId, '')}
				</td>
				<td>${fns:getDictLabel(unqualifiedItem.projectMode, 'project_mode', '')}</td>
				<td>
				${unqualifiedItem.orderNumber}
				
				</td>
				<td>
					${unqualifiedItem.xiaoqu}-${unqualifiedItem.lou}-${unqualifiedItem.danyuan}-${unqualifiedItem.shi}-${unqualifiedItem.customerName}
				</td>
				<td>
				${unqualifiedItem.orderInspectName}
				</td>
				<td>
					${unqualifiedItem.qualifiedItems }
				</td>
				<td>
					${unqualifiedItem.warnCount}
				</td>
				<td>
					${unqualifiedItem.localChangeCount}
				</td>
				<td>
					${unqualifiedItem.punishMoneyCount}
				</td>
				<td>
					<c:if test="${empty unqualifiedItem.punishMoney}" >
						0
					</c:if><c:if test="${not empty unqualifiedItem.punishMoney}" >
					${unqualifiedItem.punishMoney}
					</c:if>

				</td>
				<td>
					${unqualifiedItem.limitDateChangeCount}
				</td>
				<td>
					${unqualifiedItem.xianshangCount}
				</td>
				<td>
					${unqualifiedItem.xianxiaCount}
				</td>
				
			
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>