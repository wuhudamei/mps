<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%> 
<html>
	<head>
		<title>自主支配明细</title>
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
			<li class="active"><a href="javascript:void(0)">自主支配明细</a></li>
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
								美的你--自主支配明细
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
								<th>支配项目</th>
								<th>单位</th>
								<th>金额</th>
								<th>备注</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${owypayList}" var="owypay" varStatus="status">
								<tr>
									<td>
										${owypay.ownpayName }
									</td>
									<td>
										${owypay.unit }
									</td>
									<td>
										${owypay.amount }
									</td>
									<td>
										${owypay.remarks }
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