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
		</script>
	</head>
	<body>
	    <ul class="nav nav-tabs">                                                                                      
			<li class="active"><a href="javascript:void(0)">付款单明细详情</a></li>
			<%-- <li><a href="${ctx}/ordertaskpackagesettlement/bizOrderTaskpackageSettlementVo/settleOrderList">结算单列表</a></li> --%>
		</ul>  
		<div class="container">
			<div class="row clearfix">
				<div class="col-md-12 column">
					<div>
						<h3>
							<center>
								付款单明细
							</center>
						</h3>
					</div>
					<table class="table table-striped table-bordered table-condensed">
						<tr>
							<td width="50%">
								付款单编号：${payment.orderTaskpackagePaymentCode}
							</td>
							<td width="50%">
								付款单生成日期：<fmt:formatDate value="${payment.generatedDatetime}" pattern="yyyy-MM-dd"/>
							</td>
						</tr>
						<tr>
							<td width="50%">
								付款单状态：${fns:getOrderTaskpackagePaymentStatus(payment.status)}
							</td>
							<td width="50%">
								任务包名称：${payment.packageName}
							</td>
						</tr>
						<tr>
							<td width="50%">
								付款类型：<c:if test="${payment.orderTaskpackagePaymentType eq '0'}">首款</c:if><c:if test="${payment.orderTaskpackagePaymentType eq '1'}">尾款</c:if>
							</td>
							<td width="50%">
							</td>
						</tr>
					</table>
				</div>

				<div class="col-md-12 column">
					<div>
						<h3>
							<center>
								付款单对应工人明细
							</center>
						</h3>
					</div>
					<table class="table table-striped table-bordered table-condensed">
						<tr>
							<td width="50%">
								付款单对应工人：${payment.realName}
							</td>
							<td width="50%">
								付款单总金额：${payment.amount}元
							</td>
						</tr>
						<c:forEach items="${paymentDetailList}" var="paymentDetail">
							<tr>
								<td width="50%">
									${paymentDetail.realName}：${paymentDetail.amount}元
								</td>
								<td width="50%">
								</td>
							</tr>
						</c:forEach>
					</table>
				</div>
			</div>
			<div class="errorMessage">
				<a class="btn" href="javascript:" onclick="history.go(-1);">返回上一页</a>
			</div>	
		</div>
	</body>
</html>