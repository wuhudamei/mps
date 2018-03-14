<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<meta name="decorator" content="default" />
<title>中期巡检罚款明细</title>
	<script type="text/javascript">


        //添加明细备注触发的事件
        function addRemarks(date , row){
            $("#resonReject").val(date.name);
            $("#myAbandonedModalReject").modal('show');
            $("#myIdReject").val(row);
        }

        //添加明细备注 取消 事件
        function onclickNoAbandonedReject(){
            $('#myAbandonedModalReject').modal('hide');
            $("#resonReject").val('');
        }



	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="javascript:void(0)">中期巡检罚款明细</a></li>
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
						<center>中期巡检罚款明细</center>
					</h3>
					扣款合计金额：<input type="text" value="${midwayInspectionPunishAmount}"
						readonly="readonly" />
				</div>
				<table class="table table-striped table-bordered table-condensed">
					<thead>
						<tr>
							<th>项目经理</th>
							<th>小区名称</th>
							<th>客户</th>
							<th>罚款日期</th>
							<th>罚款金额</th>
							<th>考核条例分类</th>
							<th>考核条例细则说明</th>
							<th>备注</th>
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
								<td>${item.assessRuleType}</td>
								<td>${item.assessRuleDescribe}</td>
								<td><input type='button' name='${item.detailRemarks}' id='detailRemarks' value='查看'  onclick='addRemarks(this);'  class='input-medium required'/></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>


	<!-- 明细备注弹框的model -->
	<div  class="modal fade" id="myAbandonedModalReject" tabindex="-1" role="dialog" style="width:350px">
		<input  id="myIdReject" type="hidden">
		<div class="modal-header">
			<button class="close" type="button" data-dismiss="modal">×</button>
			<h4 id="myModalLabel" align="center" style="color: black;">备注</h3><br>
				<form:form id="timeForm" modelAttribute="bizMaterialsStandardReceiveBillApply" action="${ctx}/standradmaterialsrecords/bizMaterialsStandardReceiveBillApply/from">
				<input id = "ids"name="id" value="${bizMaterialsStandardReceiveBill.id}" type="hidden">
				<input id = "status" name="status" value="20" type="hidden">
				<div  style="margin:10px;min-height:20px;height:auto;padding-left:5px;text-align:center;" class="modal-body">
					<textarea id="resonReject" class="mask-text"  onkeyup="this.value = this.value.substring(0,100)" placeholder="请输入备注内容，多行输入，不多于100个汉字" maxlength="100" ></textarea>
						<%--<a href="javascript:void(0)" class="btn btn-primary" onclick="onclickAbandonedReject()" >确定</a>--%>
					<a href="javascript:void(0)" class="btn" onclick="onclickNoAbandonedReject()">关闭</a>
				</div>
		</div>
		</form:form>
	</div>

</body>
</html>