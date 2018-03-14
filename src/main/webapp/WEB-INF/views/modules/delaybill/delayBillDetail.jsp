<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>延期详情</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="#">延期详情</a></li>
	</ul><br/>
	<form action="" class="form-horizontal">
		<sys:message content="${message}"/>
		<div class="control-group">
		<label class="control-label" style="font-weight:bold;font-size: 15px;">订单编号:</label>
			<label class="control-label" style="text-align: left;">${delayBill.orderNumber }</label>
			<label class="control-label" style="font-weight:bold;font-size: 15px;">顾客:</label>
			<label class="control-label" style="text-align: left;">${delayBill.customerAddress }-${delayBill.customerName }</label>
		</div>
		<div class="control-group">
			<label class="control-label" style="font-weight:bold;font-size: 15px;">门店:</label>
			<label class="control-label" style="text-align: left;">${fns:getStoreLabel(delayBill.storeId, '')}</label>
			<label class="control-label" style="font-weight:bold;font-size: 15px;">工程模式:</label>
			<label class="control-label" style="text-align: left;">${fns:getDictLabel(delayBill.projectMode,'project_mode', '')}</label>
		</div>
		
		<div class="control-group">
			<label class="control-label" style="font-weight:bold;font-size: 15px;">项目经理:</label>
			<div class="controls">
				<label class="control-label" style="text-align: left;">${delayBill.itemManager }</label>
			</div>
		</div>
		<c:forEach items="${list }" var="delay">
		<div class="control-group">
			<label class="control-label">延期阶段:</label>
			<div class="controls" style="background-color: #BABABA;">
					${delay.delayBillStageStatusName }
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">申请时间:</label>
			<div class="controls">
			<fmt:formatDate value="${delay.createDate }" pattern="yyyy-MM-dd HH:mm:ss"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">延期开始:</label>
			<div class="controls">
				${delay.delayBeginDatetime }
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">延期结束:</label>
			<div class="controls">
				${delay.delayEndDatetime }
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">延期天数:</label>
			<div class="controls">
				${delay.delayDays }天
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">延期类别:</label>
			<div class="controls">
				${delay.delayBillCategoryId }
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">延期原因:</label>
			<div class="controls">
				${delay.delayBillCategoryIdReson }
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">延期图片:</label>
			<div class="controls">
				<a href="${ctx}\delayBill\photo?id=${delay.id }">查看</a>
			</div>
		</div>
		</c:forEach>
		<div class="control-group">
			<label class="control-label"></label>
			<div class="controls">
				<a class="btn" href="${ctx}/delayBill/list" >返 回</a>
			</div>
		</div>
	</form>
</body>
</html>