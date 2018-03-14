<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>约检单列表</title>
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
			<li class="active"><a href="${ctx}/pqc/checkDetails/checkDetails/list">返回</a></li>
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
				<th>约检节点名称</th>
				<th>约检单状态</th>
				<th>申请约检时间</th>
				<th>约检检查时间</th>
				<th>约检确认验收时间</th>
				<th>实际得分</th>
				<th>总分</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${bizQcBill}" var="qcBill" varStatus="status">
			<tr>
				<td>
					${status.index+1} 
				</td>
				<td>
					${qcBill.qcCheckNodeName }
				</td>
				<td>
					${fns:getDictLabel(qcBill.status, 'check_qcBill_status', '')} 
				</td>
				<td>
					<fmt:formatDate value="${qcBill.createDate}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					<fmt:formatDate value="${qcBill.checkDatetime}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					<fmt:formatDate value="${qcBill.acceptCheckDatetime}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					${qcBill.gotScore}
				</td>
				<td>
					${qcBill.totalScore}
				</td>
				<td>
					<c:if test="${qcBill.status>4 }">
						<a href="${ctx}/bizorderqcbill/bizOrderQcBill/details?qcBillId=${qcBill.qcBillId}">查看报告</a>
					</c:if>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>