<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%> 
<html>
	<head>
		<title>选材变更单详情</title>
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
			<li class="active"><a href="javascript:void(0)">变更单列表</a></li>
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
								变更单列表
							</center>
						</h2>
					</div>
				</div>
			</div>
			<div class="row clearfix">
				
				<div class="col-md-12 column" >
					
					<h3>
						<center>
							
						</center>
					</h3>
				
					<table class="table table-striped table-bordered table-condensed">
						<thead>
							<tr>
								<th>变更单号</th>
								<th>设计师申请变更日期</th>
								<th>设计师</th>
								<th>审计审核通过日期</th>
								<th>审计员</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${changeBillList}" var="changeBill">
								<tr>
									<td>
										${changeBill.changeBillCode}	
									</td>
									<td>
										<fmt:formatDate value="${changeBill.changeApplyDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
									</td>
									<td>
										${changeBill.designerName}	
									</td>
									<td>
										<fmt:formatDate value="${changeBill.changeCheckedDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
									</td>
									<td>
										${changeBill.checkerName}	
									</td>
									<td>
										<a href="${ctx}/bizmaterialschoicechangebill/bizMaterialsChoiceChangeBill/materialsChoiceChangeBillDetail?id=${changeBill.id}">查看</a>
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