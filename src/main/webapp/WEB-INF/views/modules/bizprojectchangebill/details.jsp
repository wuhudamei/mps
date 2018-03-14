<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%> 
<html>
	<head>
		<title>施工变更单详情</title>
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
			function exportExcel(){
				window.location.href="${ctx}/bizprojectchangebill/bizProjectChangeBill/exportExcel?projectChangeId=${bizProjectChangeBill.id}";
			}
		</script>
	</head>
	<body>
	    <ul class="nav nav-tabs">                                                                                      
			
			<li class="active"><a href="javascript:void(0)">施工变更单详情</a></li>
		</ul>  
		<ul class="ul-form breadcrumb">
			<li class="btns"><input class="btn" type="button" value="返回" onclick="history.go(-1)"/></li>
			<li class="btns"><input class="btn btn-primary" type="button" value="导出变更单excel格式文件" onclick="exportExcel()"/></li>
		</ul>
		<div class="container">
			<div class="row clearfix">
				<div class="col-md-12 column">
					<div class="nav nav-tabs">
						<h2>
							<center>
								施工变更单详情
							</center>
						</h2>
					</div>
				</div>
			</div>
			<div class="row clearfix">
				<div class="col-md-12 column">
					<table id="contentTable" class="table table-striped table-bordered table-condensed">
						<tr>
							<td width="50%">
								客户姓名：${bizProjectChangeBill.customerName }
							</td>
							<td width="50%">
								客户电话：${bizProjectChangeBill.customerPhone }
							</td>
						</tr>
						<tr>
							<td width="50%">
								工程地址：${bizProjectChangeBill.communityName }-${bizProjectChangeBill.buildNumber }-${bizProjectChangeBill.buildUnit }-${bizProjectChangeBill.buildRoom }
							</td>
							<td width="50%">
								建筑面积：${bizProjectChangeBill.contractArea }
							</td>
						</tr>
						<tr>
							<td width="50%">
								项目经理：${bizProjectChangeBill.itemManager }
							</td>
							<td width="50%">
								项目经理电话：${bizProjectChangeBill.itemManagerPhone }
							</td>
						</tr>
						<tr>
							<td width="50%">
								设计师：${bizProjectChangeBill.designerName }
							</td>
							<td width="50%">
								设计师电话：${bizProjectChangeBill.designerPhone}
							</td>
						</tr>
						<tr>
							<td width="50%">
								合同编号：${bizProjectChangeBill.contractNumber }
							</td>
							<td width="50%">
								申请日期：<fmt:formatDate value="${bizProjectChangeBill.applyDate }" pattern="yyyy-MM-dd"/>
							</td>
						</tr>
						<tr>
							<td width="50%">
								有无电梯：${fns:getDictLabel(bizProjectChangeBill.isElevator, 'is_elevator', '')}
							</td>
							<td width="50%">
								变更原因：${bizProjectChangeBill.changeReason }
							</td>
						</tr>
					</table>
				</div>
				
				<div class="col-md-12 column" >
					
					<h3>
						<center>
							增项清单
						</center>
					</h3>
				
					<div style="text-align:right;">
						合计：<input type="text" value="${addMoney }" readonly="readonly">
					</div>
				<table id="contentTable" class="table table-striped table-bordered table-condensed">
						<thead>
							<tr>
								<th>序号</th>
								<th>项目名称</th>
								<th>单位</th>
								<th>数量</th>
								<th>损耗</th>
								<th>人工费（元）</th>
								<th>综合单价（元）</th>
								<th>总价 </th>
								<th>说明</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${addItem}" var="item" varStatus="status">
								<tr>
									<td>
										${status.index+1 }	
									</td>
									<td>
										${item.projectIntemName }	
									</td>
									<td>
										${item.projectIntemUnit }
									</td>
									<td>
										${item.projectIntemAmount }
									</td>
									<td>
										
									</td>
									<td>
										
									</td>
									<td>
										${item.everyPrice }
									</td>
									<td>
										${item.allPrice }
									</td>
									<td>
										${item.explainWords }
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			
				<div class="col-md-12 column">
					<h3>
						<center>
							减项清单
						</center>
					</h3>
						
					<div style="text-align:right;">
						合计：<input type="text" value="${reducePrice }" readonly="readonly">
					</div>
				
				
				<table id="contentTable" class="table table-striped table-bordered table-condensed">
						<thead>
							<tr>
								<th>序号</th>
								<th>项目名称</th>
								<th>单位</th>
								<th>数量</th>
								<th>损耗</th>
								<th>人工费（元）</th>
								<th>综合单价（元）</th>
								<th>总价 </th>
								<th>说明</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${subItem}" var="item" varStatus="status">
								<tr>
									<td>
										${status.index+1 }	
									</td>
									<td>
										${item.projectIntemName }	
									</td>
									<td>
										${item.projectIntemUnit }
									</td>
									<td>
										${item.projectIntemAmount }
									</td>
									<td>
										
									</td>
									<td>
										
									</td>
									<td>
										${item.everyPrice }
									</td>
									<td>
										${item.allPrice }
									</td>
									<td>
										${item.explainWords }
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<div style="text-align:right;">
						合计：<input type="text" value="${totalMoney }" readonly="readonly">
					</div>
				</div>
				
			</div>
		</div>	
	</body>
</html>