<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%> 
<html>
	<head>
		<title>结算单详情</title>
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
			<li class="active"><a href="javascript:void(0)">质保金明细</a></li>
		</ul>  
		<div class="container">
			<div class="row clearfix">
				<div class="col-md-12 column" >
					<div>
						结算总金额：<input type="text" value="${totalAmount}" readonly="readonly">
					</div>
				<table id="contentTable" class="table table-striped table-bordered table-condensed">
						<thead>
							<tr>
								<th>客户姓名</th>
								<th>客户地址</th>
								<th>项目经理</th>
								<th>扣取质保金日期</th>
								<th>质保金金额</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${list}" var="money">
								<tr>
									<td>${money.customerName}</td>
									<td>${money.customerAddress}</td>
									<td>${money.itemCustomer}</td>
									<td><fmt:formatDate value="${money.deductTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
									<td>${money.guaranteeMoneyAmount}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
			<div class="errorMessage">
				<a class="btn" href="javascript:" onclick="history.go(-1);">返回上一页</a>
			</div>	
		</div>
	</body>
</html>