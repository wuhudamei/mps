<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<meta name="decorator" content="default" />
<title>中期扣款明细</title>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="javascript:void(0)">中期扣款明细</a></li>
	</ul>
	<ul class="ul-form breadcrumb">
		<li class="btns"><input class="btn" type="button" value="返回"
			onclick="history.go(-1)" /></li>
	</ul>

	<div class="container">
		<div class="row clearfix">
			<div class="col-md-12 column">
				<div>
					<h3>
						<center>中期扣款明细</center>
					</h3>
					扣款合计金额：<input type="text" value="${midwayPunishAmount}"
						readonly="readonly" />
				</div>
				<table class="table table-striped table-bordered table-condensed">
					<thead>
						<tr>
							<th>项目经理</th>
							<th>小区名称</th>
							<th>客户</th>
							<th>扣款日期</th>
							<th>扣款金额</th>
							<th>考核条例细则说明</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${list}" var="item">
							<tr>
								<td>${item.rewardPunishTargetEmployeeName}-${item.rewardPunishTargetEmployeePhone}</td>
								<td>${item.communityName}-${item.buildNumber}-${item.buildUnit}-${item.buildRoom}</td>
								<td>${item.customerName}-${item.customerPhone}</td>
								<td><fmt:formatDate value="${item.rewardPunishDatetime}" pattern="yyyy-MM-dd"/></td>
								<td>-${item.rewardPunishAmount}</td>
								<td>${item.assessRuleDescribe}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</body>
</html>