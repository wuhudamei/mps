<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>质检报告</title>
	<meta name="decorator" content="default"/>
	<!-- <script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script> -->
</head>
<body>
		<ul class="nav nav-tabs">
			<li class="active"><a href="${ctx}/bizorderqcbill/bizOrderQcBill/list">返回</a></li>
			<li class="active"><a href="${ctx}/bizorderqcbill/bizOrderQcBill/report?orderId=${bizOrderQcBill.orderId}">质检报告列表</a></li>
		</ul>
		
		
	
		<div class="breadcrumb form-search">
			<form:form class="breadcrumb form-search">
			<table style="width:100%" align="center" valign="center">
				<tr>
					<td style="width:40%"><label>顾客：</label>
						${bizOrderQcBill.communityName }-${bizOrderQcBill.buildNumber }-${bizOrderQcBill.buildUnit }-${bizOrderQcBill.buildRoom }-${bizOrderQcBill.customerName }
					</td>
					<td style="width:30%"><label>项目经理：</label>${bizOrderQcBill.itemManager }</td>
					<td style="width:30%"><label>质检员：</label>${bizOrderQcBill.orderInspector }</td>
				</tr>
			</table>
			</form:form>
		</div>	
	
	
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>序号</th>
				<th>检查内容</th>
				<th>检查人</th>
				<th>质检日期</th>
				<th>验收日期</th>
				<th>施工地址</th>
				<th>签到地址</th>
				<th>实际得分/总分</th>
				<th>复检次数</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${list}" var="qcBill" varStatus="status">
			<tr>
				<td>
					${status.index+1} 
				</td>
				<td>
					<c:if test="${qcBill.qcBillType=='1' }">
						<p>${qcBill.qcCheckNodeName }</p>
					</c:if>
					<c:if test="${qcBill.qcBillType=='2' }">
						<p>抽检</p>
					</c:if>
					<c:if test="${qcBill.isRecheck=='1' }">
						<p>【复检】</p>
					</c:if>
				</td>
				<td>
					${qcBill.checkRealName}
				</td>
				<td>
					<fmt:formatDate value="${qcBill.checkDatetime}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					<fmt:formatDate value="${qcBill.acceptCheckDatetime}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					${qcBill.customerAddress}
				</td>
				<td>
					${qcBill.signAddress}
				</td>
				<td>
					${qcBill.gotScore}/${qcBill.totalScore}
				</td>
				<td>
					${qcBill.recheckTimes}
				</td>
				<td>
					<a href="${ctx}/bizorderqcbill/bizOrderQcBill/details?qcBillId=${qcBill.qcBillId}">查看报告</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>