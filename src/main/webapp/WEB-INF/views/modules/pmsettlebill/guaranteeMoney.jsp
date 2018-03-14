<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>质保金明细</title>
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
		<li class="active"><a href="javascript:void(0)">质保金明细</a></li>
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
						<center>质保金明细</center>
					</h2>
				</div>
			</div>
		</div>
	
	<div class="row clearfix">
		<div class="col-md-12 column">
			<div>
				<h3>
					<center>基本信息</center>
				</h3>
			</div>
			<table class="table table-striped table-bordered table-condensed">
				<tr>
					<td width="50%">订单编号：${bizPmStarCommissionCnfgSnap.orderNumber}</td>
					<td width="50%">小区名称：${bizPmStarCommissionCnfgSnap.communityName}</td>
				</tr>
				<tr>
					<td width="50%">客户：${bizPmStarCommissionCnfgSnap.customerName}-${bizPmStarCommissionCnfgSnap.customerPhone}</td>
					<td width="50%">项目经理：${bizPmStarCommissionCnfgSnap.itemManager}</td>
				</tr>
				<tr>
					<td width="50%">派单时间：<fmt:formatDate
							value="${bizPmStarCommissionCnfgSnap.sendOrderDate}"
							pattern="yyyy-MM-dd HH:mm:ss" /></td>
					<td width="50%">派单时项目经理星级：${bizPmStarCommissionCnfgSnap.starLever}</td>
				</tr>
			</table>
		</div>

		<div>
			<h3>
				<center>竣工质保金明细</center>
			</h3>
			<table class="table table-striped table-bordered table-condensed">
				<tr>
					<td colspan="2" width="50%">竣工上缴质保金金额：<input type="text"
						id="allAmount" readonly="readonly" value="${pmGuaranteeMoney}" /></td>
				</tr>
				<tr>
					<td width="50%">质保金上限定额： <input type="text"
						readonly="readonly" value="${gmcs.guaranteeMoneyMax}" />
					</td>
					<td width="50%">每个订单扣除额度： <input type="text"
						readonly="readonly" value="${gmcs.guaranteeMoneyPerOrder}" />
					</td>
				</tr>
			</table>
		</div>

	</div>
	</div>
</body>
</html>