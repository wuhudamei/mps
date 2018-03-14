<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>中期提成明细</title>
<meta name="decorator" content="default" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script type="text/javascript">
	$(document).ready(function() {
	});
	function page(n, s) {
		$("#pageNo").val(n);
		$("#pageSize").val(s);
		$("#searchForm").submit();
		return false;
	}
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="javascript:void(0)">中期提成明细</a></li>
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
						<center>中期提成明细</center>
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
					<center>中期提成金额明细</center>
				</h3>
				<table class="table table-striped table-bordered table-condensed">
					<tr>
						<td colspan="2" width="50%">中期星级提成实发金额：<input type="text"
							id="allAmount" readonly="readonly"
							value="${bizPmStarCommissionCnfgSnap.commissionAmount* bizPmStarCommissionCnfgSnap.commissionRateMidway}" /></td>
					</tr>
					<tr>
						<td width="50%">新/老房： <input type="text" readonly="readonly"
							value="${fns:getDictLabel(bizPmStarCommissionCnfgSnap.isOldNew,'biz_is_new_house', '')}" />
						</td>
						<td width="50%">派单时项目经理星级： <input type="text"
							readonly="readonly"
							value="${bizPmStarCommissionCnfgSnap.starLever}" />
						</td>
					</tr>
					<tr>
						<td width="50%">提成总金额： <input type="text" readonly="readonly"
							value="${bizPmStarCommissionCnfgSnap.commissionAmount}" />
						</td>
						<td width="50%">中期提成比例： <input type="text"
							readonly="readonly"
							value="${bizPmStarCommissionCnfgSnap.commissionRateMidway*100}%" />
						</td>
					</tr>
				</table>
			</div>
		</div>
	</div>
</body>
</html>