<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>订单管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#searchForm").validate({
				errorPlacement: function(error, element) {
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			})
		});
	</script>
</head>
<body>
<c:set var="user" value="${fns:getUser()}"></c:set>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/order2/order2/inspectorDailyList">订单列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="order2" action="${ctx}/order2/order2/inspectorDailyList" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label><font color="red">*</font>门店：</label>
				<form:select path="storeId" class="input-medium needClear required" onchange="findEngineListByStoreIdAndProjectMode()" id="sel">
					<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label><font color="red">*</font>日期：</label>
				<input name="beginGeneratedDatetime" id="beginGeneratedDatetime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${order.beginGeneratedDatetime}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'endGeneratedDatetime\')}',isShowClear:true});"/> 至  
				<input name="endGeneratedDatetime" id="endGeneratedDatetime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required"
					value="<fmt:formatDate value="${order.endGeneratedDatetime}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'beginGeneratedDatetime\')}',isShowClear:true});"/>
			</li>
		</ul>
		<ul class="ul-form">
			<li style="font-size:25px;font-weight:bold;text-align:center;">门店当前待派质检员订单：<span style="font-size:25px;font-weight:bold;text-align:center;color:red;" id="unAllotCount">${inspectorDailyCount }</span></li>
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
				<th>区域</th>
				<th>当前未分派分派质检员订单数</th>
				<th>选择日期已分派质检员订单数</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="order" varStatus="status">
			<tr>
				<td>
					${fns:getStoreLabel(order.storeId, '')}
				</td>
				<td>
					${order.departmentName}
				</td>
				<td>
					${order.noDistribution}
				</td>
				<td>
					${order.distribution}
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
<script type="text/javascript">

</script>
</html>