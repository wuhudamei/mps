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
			<li class="active"><a href="javascript:void(0)">选材变更单详情</a></li>
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
								选材变更单详情
							</center>
						</h2>
					</div>
				</div>
			</div>
			<div class="row clearfix">
				<div class="col-md-12 column">
					<h3>
						<center>
							基本信息
						</center>
					</h3>
					<table class="table table-striped table-bordered table-condensed">
						<tr>
							<td width="50%">
								订单编号：${bizMaterialsChoiceChangeBill.orderNumber }
							</td>
							<td width="50%">
								合同编号：${bizMaterialsChoiceChangeBill.contractNumber }
							</td>
						</tr>
						<tr>
							<td width="50%">
								变更单号：${bizMaterialsChoiceChangeBill.changeBillCode }
							</td>
							<td width="50%">
								客户地址：${bizMaterialsChoiceChangeBill.communityName }-${bizMaterialsChoiceChangeBill.buildNumber }-${bizMaterialsChoiceChangeBill.buildUnit }-${bizMaterialsChoiceChangeBill.buildRoom }
							</td>
						</tr>
						<tr>
							<td width="50%">
								客户姓名：${bizMaterialsChoiceChangeBill.customerName }
							</td>
							<td width="50%">
								客户电话：${bizMaterialsChoiceChangeBill.customerPhone }
							</td>
						</tr>
						<tr>
							<td width="50%">
								设计师姓名：${bizMaterialsChoiceChangeBill.orderDesignerName }
							</td>
							<td width="50%">
								设计师电话：${bizMaterialsChoiceChangeBill.orderDesignerPhone}
							</td>
						</tr>
						<tr>
							<td width="50%">
								项目经理姓名：${bizMaterialsChoiceChangeBill.itemManager }
							</td>
							<td width="50%">
								变更原因：${bizMaterialsChoiceChangeBill.changeReason }
							</td>
						</tr>
					</table>
				</div>
				
				<div class="col-md-12 column" >
					
					<h3>
						<center>
							主材增加项目
						</center>
					</h3>
				
					<table class="table table-striped table-bordered table-condensed">
						<thead>
							<tr>
								<th>类型</th>
								<th>二级类目</th>
								<th>品牌</th>
								<th>型号</th>
								<th>属性</th>
								<th>单位</th>
								<th>规格</th>
								<th>用量增加</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${addList}" var="addItem">
								<tr>
									<td>
										${addItem.materialsChoiceType}	
									</td>
									<td>
										${addItem.categoryName}	
									</td>
									<td>
										${addItem.brand}	
									</td>
									<td>
										${addItem.model}	
									</td>
									<td>
										${addItem.attribute}	
									</td>
									<td>
										${addItem.unit}	
									</td>
									<td>
										${addItem.spec}	
									</td>
									<td>
										${addItem.changeNumber}	
									</td>
									
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			
				<div class="col-md-12 column">
					<h3>
						<center>
							主材减少项目
						</center>
					</h3>
						
					<table class="table table-striped table-bordered table-condensed">
						<thead>
							<tr>
								<th>类型</th>
								<th>二级类目</th>
								<th>品牌</th>
								<th>型号</th>
								<th>属性</th>
								<th>单位</th>
								<th>规格</th>
								<th>用量减少</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${subList}" var="subItem" >
								<tr>
									<td>
										${subItem.materialsChoiceType}	
									</td>
									<td>
										${subItem.categoryName}	
									</td>
									<td>
										${subItem.brand}	
									</td>
									<td>
										${subItem.model}	
									</td>
									<td>
										${subItem.attribute}	
									</td>
									<td>
										${subItem.unit}	
									</td>
									<td>
										${subItem.spec}	
									</td>
									<td>
										${subItem.changeNumber}	
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