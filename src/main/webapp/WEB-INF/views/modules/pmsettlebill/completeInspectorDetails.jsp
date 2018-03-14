<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%> 
<html>
	<head>
		<title>竣工质检罚款明细</title>
		<meta name="decorator" content="default"/>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<script type="text/javascript">
			$(document).ready(function() {
			});
			function page(n,s){
				$("#pageNo").val(n);
				$("#pageSize").val(s);
				$("#searchForm").submit();
	        	return false;
	        }
			
		</script>
	</head>
	<body>
	    <ul class="nav nav-tabs">                                                                                      
			<li class="active"><a href="javascript:void(0)">竣工质检罚款明细</a></li>
		</ul>  
		<ul class="ul-form breadcrumb">
			<li class="btns"><input class="btn" type="button" value="返回" onclick="history.go(-1)"/></li>
		</ul>
		<div class="container">
			<div class="row clearfix">
				<div class="col-md-12 column">
					<div class="nav nav-tabs">
						<h2>
							<center>
								竣工质检罚款明细
							</center>
						</h2>
					</div>
				</div>
			</div>
			<div class="row clearfix">
				<div class="col-md-12 column" >
					<table id="contentTable" class="table table-striped table-bordered table-condensed">
						<thead>
							<tr>
								<th>订单编号</th>
								<th>顾客</th>
								<th>质检单编号</th>
								<th>提交人</th>
								<th>提交时间</th>
								<th>检查项分类</th>
								<th>检查项</th>
								<th>违规形式</th>
								<th>罚款金额</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${completInspectorList}" var="inspector" varStatus="status">
								<tr>
									<td>
										${inspector.orderNumber }
									</td>
									<td>
										${inspector.communityName }-${inspector.buildNumber }-${inspector.buildUnit }-${inspector.buildRoom }-${inspector.customerName }
									</td>
									<td>
										${inspector.qcBillCode }
									</td>
									<td>
										${inspector.checkEmployee }
									</td>
									<td>
										<fmt:formatDate value="${inspector.actualCheckDate }" pattern="yyyy-MM-dd" />
									</td>
									<td>
										${inspector.qcBillCheckKindName }
									</td>
									<td>
										${inspector.qcBillCheckItemName }
									</td>
									<td>
										<c:forEach items="${inspector.reportCheckBreakSettleBillList }" var="reportBreak">
												${reportBreak.breakDescribe }
										</c:forEach>
									</td>
									<td>
										${inspector.money }
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>	
	</body>
</html>