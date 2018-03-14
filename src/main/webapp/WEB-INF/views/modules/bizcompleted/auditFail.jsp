<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>驳回原因</title>
<meta name="decorator" content="default"/>
<script type="text/javascript" src="${ctxStatic}/mobile/modules/Worker/js/lib/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/jquery.form.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		//绑定onclick事件
		$("#submit").bind('click',submitAuditFail);
		
		//自动提示输入字符串个数
		$("#remarks").keyup(function(){
	    	if($("#remarks").val().length > 100){
	    	   $("#remarks").val( $("#remarks").val().substring(0,100) );
	    	}
	    	$("#word").text( 100 - $("#remarks").val().length ) ;
	    });
	});
	
	//提交
	function submitAuditFail(){
		
		$("#submit").css("color","#CCC");
		$("#submit").unbind("click");
		
		var options ={
				url : "${ctx}/bizorderfinishprojectbill/bizOrderFinishProjectBill/auditFail",
				type: "POST",
				success : function(data){
					//alert("data==="+data);
					if(data == 0){
						alert('操作成功...');			
						window.location.href = "${ctx}/bizcompletedaudit/bizCompletedAudit/list";
					}
					if(data == 1){
						alert("修改竣工数据失败...");
					}
					if(data == 2){
						alert("修改订单状态失败...");
					}
				}
			}
			//ajax提交FORM
		$("#submitForm").ajaxSubmit(options);
	}
	
	function back(index){
		window.location.href = "${ctx}/bizcompletedaudit/bizCompletedAudit/list";
	}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/bizcompletedaudit/bizCompletedAudit/list">竣工审核列表</a></li>
		<li><a>驳回</a></li>
	</ul>
	<form id="submitForm" action="" method="post" class="breadcrumb form-search">
	<input type="hidden" id="id" name="orderID" value="${orderID }"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>驳回原因：
					<textarea id="remarks" cols ="5" rows = "5" name="remarks"></textarea>
					<p style="font-size:13px;">还可以输入<i id="word">100</i>个字</p>
				</th>
			</tr>
			<tr>
				<th>
					<input id="submit" class="btn btn-primary" type="button" value="提交"/>&nbsp;
					<input class="btn btn-primary" type="button" value="返回" onclick="back(-1)"/>
				</th>
			</tr>
		</thead>
	</table>
	</form>
</body>
</html>