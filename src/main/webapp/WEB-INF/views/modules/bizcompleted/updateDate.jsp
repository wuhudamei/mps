<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>任务包数量</title>
<meta name="decorator" content="default"/>
<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/jquery-2.1.1.min.js"></script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/bizcompletedaudit/bizCompletedAudit/list">竣工审核列表</a></li>
		<li><a>修改</a></li>
	</ul>
	<form id="submitForm" action="" method="post" class="breadcrumb form-search">
	<input type="hidden" id="id" name="id" value="${orderFinishProjectBill.id }"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>实际竣工日期：
					<input name="realFinishProjectDate" type="text" maxlength="20" class="input-medium Wdate" id="realFinishProjectDate"
						value="<fmt:formatDate value="${orderFinishProjectBill.realFinishProjectDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
				</th>
			</tr>
			<tr>
				<th>
					<input id="submit" type="button" value="提交" class="submit"/>
					<input id="reset" type="reset" value="返回" class="submit" onclick="back(-1)"/>
				</th>
			</tr>
		</thead>
	</table>
	</form>
	
	<script type="text/javascript">
	$(document).ready(function() {
		//绑定onclick事件
		$("#submit").bind('click',submitAuditData);
	});
	
	//提交
	function submitAuditData(){
		var id = ${orderFinishProjectBill.id};
		var realFinishProjectDate = $("#realFinishProjectDate").val();
		//禁用提交按钮
		$("#submit").css("color","#CCC");
		$("#submit").unbind("click");
		$.ajax({
			url : "${ctx}/bizorderfinishprojectbill/bizOrderFinishProjectBill/updateByDate",
			type : "POST",
		    //async:false,
		    data : {
		    	realFinishProjectDate : realFinishProjectDate,
		    	id : id,
		    	orderID : ${orderFinishProjectBill.orderId}
			},
		  	success : function(data){
				if(data == 0){
				  alert("操作成功!");
				  window.location.href = "${ctx}/bizcompletedaudit/bizCompletedAudit/list";
			  	}
				if(data == 1){
					alert("新增数据错误!");
				 	return false;
			  	}
		  }
		});
	}
	
	function back(index){
		window.location.href = "${ctx}/bizcompletedaudit/bizCompletedAudit/list";
	}
	</script>
</body>
</html>