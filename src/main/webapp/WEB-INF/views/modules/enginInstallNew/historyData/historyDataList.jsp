<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>安装项历史数据</title>
	<meta name="decorator" content="default"/>
	
	<script type="text/javascript">
		$(document).ready(function() {
		});
		
		
		 function aa(){
			 window.location.href="${ctx}/installHistoryDateDeal/installHistoryDateDeal/deleteRepeatedData";
		 }
		 function bb(){
			 window.location.href="${ctx}/installHistoryDateDeal/installHistoryDateDeal/updateData";
		 }
		 function cc(){
			 window.location.href="${ctx}/installHistoryDateDeal/installHistoryDateDeal/insertApplyData";
		 }
		 function dd(){
			 window.location.href="${ctx}/installHistoryDateDeal/installHistoryDateDeal/insertSupplierData";
		 }
		 function ee(){
			 window.location.href="${ctx}/installHistoryDateDeal/installHistoryDateDeal/insertAcceptData";
		 }
		 function ff(){
			 window.location.href="${ctx}/installHistoryDateDeal/installHistoryDateDeal/acceptance_interface_history_data";
		 }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/installHistoryDateDeal/installHistoryDateDeal/preList">主材安装申请-历史数据处理</a></li>
	
	</ul>
	<form:form id="searchForm" modelAttribute="installHistoryData" action="${ctx}/installHistoryDateDeal/installHistoryDateDeal/list" method="post" class="breadcrumb form-search">
		<ul class="ul-form">
			<li><label>状态：</label>
				<form:select path="installStatus" class="input-medium">
					<form:option value="" label="" />
					<form:option value="2" label="已申请" />
					<form:option value="3" label="已转给供应商" />
					<form:option value="4" label="已验收" />
				</form:select>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="btns"><input class="btn btn-primary clearBtn" type="button" value="删除重复数据" onclick="aa()"/></li>
			<li class="btns"><input class="btn btn-primary clearBtn" type="button" value="批量更新数据" onclick="bb()"/></li>
			<li class="btns"><input class="btn btn-primary clearBtn" type="button" value="批量插入申请日志数据 " onclick="cc()"/></li>
			<li class="btns"><input class="btn btn-primary clearBtn" type="button" value="批量插入下达供应商日志数据 " onclick="dd()"/></li>
			<li class="btns"><input class="btn btn-primary clearBtn" type="button" value="批量插入验收日志数据 " onclick="ee()"/></li>
			<li class="btns"><input class="btn btn-primary clearBtn" type="button" value="基装验收接口 " onclick="ff()"/></li> 
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>安装项计划id</th>
				<th>订单id</th>
				<th>安装项名称</th>
				<th>状态</th>
				<th>期望进场时间</th>
				<th>项目经理申请安装时间</th>
				<th>供应商确认日期</th>
				<th>供应商备注</th>
				<th>实际验收时间</th>
				<th>实际进场日期</th>
				<th>实际完工日期</th>
				<th>日志-申请数量</th>
				<th>日志-供应商数量</th>
				<th>日志-验收数量</th>
			</tr>
		</thead>
		<tbody>	
			<c:forEach items="${list}" var="install" >
			<tr>
				<td>${install.id}</td>
				<td>${install.orderId}</td>
				<td>${install.installItemName }</td>
				<td>
					${install.installStatus }-
					<c:if test="${install.installStatus eq 2 }">
						已申请
					</c:if>
					<c:if test="${install.installStatus eq 3 }">
						已转供应商
					</c:if>
					<c:if test="${install.installStatus eq 4 }">
						已验收
					</c:if>
				</td>
				<td><fmt:formatDate type="date" value="${install.applyIntoDate }"/></td>
				<td><fmt:formatDate type="both" value="${install.applyIntoCreateDatetime }"/></td>
				<td><fmt:formatDate type="date" value="${install.supplierConfirmIntoDate }"/></td>
				<td>${install.supplierConfirmRemarks }</td>
				<td><fmt:formatDate type="both" value="${install.realAcceptDate }"/></td>
				<td><fmt:formatDate type="date" value="${install.realCompleteDate }"/></td>
				<td><fmt:formatDate type="date" value="${install.realIntoDate }"/></td>
				<td>${install.statusTwoCount }</td>
				<td>${install.statusThreeCount }</td>
				<td>${install.statusFourCount }</td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>