<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>自采材料明细</title>
<meta name="decorator" content="default" />
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
		<li class="active"><a href="javascript:void(0)">自采材料明细</a></li>
	</ul>
	<ul class="ul-form breadcrumb">
		<li class="btns"><input class="btn" type="button" value="返回"
			onclick="history.go(-1)" /></li>
	</ul>
	<div class="container">
		<div class="row clearfix">
			<div class="col-md-12 column">
				<div class="nav nav-tabs">
					<h2>
						<center>自采材料明细</center>
					</h2>
				</div>
			</div>
		</div>
		<div class="row clearfix">
			<div class="col-md-12 column">
			    <h4>
                                             发放合计金额：+${totalAmount}元
			    </h4>
			    <br />
				<table id="contentTable"
					class="table table-striped table-bordered table-condensed">
					<thead>
						<tr>
							<th>项目经理</th>
							<th>小区名称</th>
							<th>客户</th>
							<th>自采材料名称</th>
							<th>客户交费金额</th>
							<th>结算比例</th>
							<th>实际结算金额</th>
						</tr>
					</thead>
					<tbody>
					    <c:forEach items="${list}" var="bizMaterialSelfbuyVo">
					    <tr>
							<td>${bizMaterialSelfbuyVo.itemManager}-${bizMaterialSelfbuyVo.itemPhone}</td>
							<td>${bizMaterialSelfbuyVo.communityName}-${bizMaterialSelfbuyVo.buildNumber}-${bizMaterialSelfbuyVo.buildUnit}-${bizMaterialSelfbuyVo.buildRoom}</td>
							<td>${bizMaterialSelfbuyVo.customerName}-${bizMaterialSelfbuyVo.customerPhone}</td>
							<td>${bizMaterialSelfbuyVo.materialName}</td>
							<td>${bizMaterialSelfbuyVo.customerPayAmount}</td>
							<td>${bizMaterialSelfbuyVo.settleRate*100}%</td>
							<td>${bizMaterialSelfbuyVo.settleAmount}</td>
						</tr>
					    </c:forEach>
						
						<%-- <c:forEach items="${completInspectorList}" var="inspector" varStatus="status">
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
							</c:forEach> --%>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</body>
</html>