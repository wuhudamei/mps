<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%> 
<html>
	<head>
		<title>选材清单详情</title>
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
			
			
			function materialInstallItemfun (){
				var orderId = $("#orderId").val();
				var storeId = $("#storeId").val();
				var projectMode = $("#projectModeid").val();
				var orderNumber = $("#orderNumberid").val();
				var orderStatusNumber = $("#orderStatusNumberId").val();
				var flag = $("#flagid").val();
// 				alert(orderId)
// 				orderId="+orderId+"&storeId="+storeId+"&projectMode="+projectMode+"&orderNumber="+orderNumber+"&orderStatusNumber="+orderStatusNumber+"&flag="+flag
				$("#searchForm").attr("action","${ctx}/ordermainmate/bizOrderMainMate/list");
				$("#searchForm").submit();
			}
		</script>
	</head>
	<body>
		<form id="searchForm" action="${ctx}/ordermainmate/bizOrderMainMate/list" method="post">
		<input id = "orderId" name = "orderId" value="${bizMaterialsChoiceBill.orderId }" style="display: none;"/>
		<input  id = "orderNumberid"  name = "orderNumber" value="${bizMaterialsChoiceBill.orderNumber }" style="display: none;"/>
		</form>
	    <ul class="nav nav-tabs">                                                                                      
			<li class="active"><a href="javascript:void(0)">订单选材清单详情</a></li>
		</ul>  
<!-- 		history.go(-1) -->
		<ul class="ul-form breadcrumb">
			<li class="btns"><input class="btn" type="button" value="返回" onclick=" materialInstallItemfun()"/></li>
		</ul>
		<div class="container">
			<div class="row clearfix">
				<div class="col-md-12 column">
					<div class="nav nav-tabs">
						<h2>
							<center>
								订单选材清单
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
								订单编号：${bizMaterialsChoiceBill.orderNumber }
							</td>
							<td width="50%">
								客户姓名：${bizMaterialsChoiceBill.customerName }
							</td>
						</tr>
						<tr>
							<td width="50%">
								客户地址：${bizMaterialsChoiceBill.communityName }-${bizMaterialsChoiceBill.buildNumber }-${bizMaterialsChoiceBill.buildUnit }-${bizMaterialsChoiceBill.buildRoom }
							</td>
							<td width="50%">
								客户电话：${bizMaterialsChoiceBill.customerPhone }
							</td>
						</tr>
						<tr>
							<td width="50%">
								设计师姓名：${bizMaterialsChoiceBill.orderDesignerName }
							</td>
							<td width="50%">
								设计师电话：${bizMaterialsChoiceBill.orderDesignerPhone}
							</td>
						</tr>
						<tr>
							<td width="50%">
								合同编号：${bizMaterialsChoiceBill.contractNumber }
							</td>
							<td width="50%">
								项目经理姓名：${bizMaterialsChoiceBill.itemManager }
							</td>
						</tr>
					</table>
				</div>
				
				<div class="col-md-12 column" >
					
					<h3>
						<center>
							选材清单
						</center>
					</h3>
		 <ul class="">
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		 导入状态:<c:if test="${bizMaterialsChoiceBill.status == 1 }">
		已导入
		<a href="#"  style="border: 1px solid #DDD; background-color: #F5F5F5; color:#ACA899;">全部导入</a>
		 </c:if> 
		 <c:if test="${bizMaterialsChoiceBill.status == 0 }">
		 未导入&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		 
		 	<a href="${ctx}/ordermainmate/bizOrderMainMate/insertOrderMainMate?orderId=${bizMaterialsChoiceBill.orderId}">全部导入</a>
		 </c:if>
	
		
		</ul>
					<table class="table table-striped table-bordered table-condensed">
						<thead>
							<tr>
								<th>类型</th>
								<th>二级类目</th>
								<th>品牌</th>
								<th>型号</th>
								<th>属性</th>
								<th>供应商</th>
								<th>单位</th>
								<th>规格</th>
								<th>位置</th>
								<th>数量(块)</th>
								<th>数量(面积)</th>
								<th>损耗系数</th>
								<th>含损耗用量</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${materialsChoiceList}" var="materialsChoice">
								<tr>
									<td>
										${materialsChoice.materialsChoiceType}	
									</td>
									<td>
										${materialsChoice.categoryName}	
									</td>
									<td>
										${materialsChoice.brand}	
									</td>
									<td>
										${materialsChoice.model}	
									</td>
									<td>
										${materialsChoice.attribute}	
									</td>
									<td>
										${materialsChoice.supplierName}	
									</td>
									<td>
										${materialsChoice.unit}	
									</td>
									<td>
										${materialsChoice.spec}	
									</td>
									<td>
										${materialsChoice.position}	
									</td>
									<td>
										${materialsChoice.budgetNumber1}	
									</td>
									<td>
										${materialsChoice.budgetNumber2}	
									</td>
									<td>
										${materialsChoice.lossRatio}	
									</td>
									<td>
										${materialsChoice.includeLossNumber}	
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