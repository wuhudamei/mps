<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
	<head>
		<title>应打尾款付款单</title>
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
			<li class="active"><a href="javascript:void(0)">应打尾款付款单</a></li>
		</ul>
		<ul class="ul-form breadcrumb">
			<li class="btns"><input class="btn" type="button" value="返回" onclick="goToHistory()"/></li>
		</ul>
		<div class="container">
			<div class="row clearfix">
				<div class="col-md-12 column">
					<div class="nav nav-tabs">
						<h2>
							<center>
								应打尾款付款单
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
								<th>序号</th>
								<th>门店</th>
								<th>付款单编号</th>
								<th>结算单编号</th>
								<th>客户信息</th>
								<th>项目经理</th>
								<th>任务包名称</th>
								<th>结算金额</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${paymentList}" var="payment" varStatus="status">
								<tr>
									<td>
										${status.index+1 }
									</td>
									<td>
										${fns:getStoreLabel(payment.storeId, '')}
									</td>
									<td>
										${payment.paymentCode }
									</td>
									<td>
										${payment.settlementCode }
									</td>
									<td>
										${payment.communityName }-${payment.buildNumber }-${payment.buildUnit }-${payment.buildRoom }-${payment.customerName }
									</td>
									<td>
										${payment.itemManager }
									</td>
									<td>
										${payment.taskpackage }
									</td>
									<td>
										${payment.money }
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