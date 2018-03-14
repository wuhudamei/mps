<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>查询订单列表</title>
	<meta name="decorator" content="default"/>
	
		<style type="text/css">
		.mask-text{resize:none;width:280px;padding:5px;box-sizing:border-box;}
	</style>
	
	<script type="text/javascript">
		$(document).ready(function() {
		var fistCode ="${entity.liableDepartmentCode}"

            $("#liableDepartmentCodeId").html('<option value=""></option>')
			$("#liableTypeCode1Id").html('<option value=""></option>')
			if(fistCode==92){

                $("#liableDepartmentCodeId").append('<option value="92" selected=selected>工程部</option>')
                $("#s2id_liableDepartmentCodeId").find(".select2-chosen").text("工程部")
			}else{
                $("#liableDepartmentCodeId").append('<option value="92" >工程部</option>')

			}


			if(fistCode==95){
                $("#s2id_liableDepartmentCodeId").find(".select2-chosen").text("质检部")
                $("#liableDepartmentCodeId").append(' <option value="95" selected=selected>质检部</option>')
			}else {

                $("#liableDepartmentCodeId").append(' <option value="95">质检部</option>')
            }

            if(fistCode==96){
                $("#s2id_liableDepartmentCodeId").find(".select2-chosen").text("工程售后部")
                $("#liableDepartmentCodeId").append(' <option value="96" selected=selected>工程售后部</option>')
			}else {

                $("#liableDepartmentCodeId").append(' <option value="96">工程售后部</option>')
            }

		var secondCode ="${entity.liableTypeCode1}"

		if(secondCode==104){
            $("#liableTypeCode1Id").append('<option value="104" selected=selected>工长</option>')

           $("#s2id_liableTypeCode1Id").find(".select2-chosen").text("工长")
        }else{
            $("#liableTypeCode1Id").append('<option value="104">工长</option>')

        }

            if(secondCode==116){

                $("#liableTypeCode1Id").append('<option value="116" selected=selected>质检</option>')
                $("#s2id_liableTypeCode1Id").find(".select2-chosen").text("质检")
            }else{
                $("#liableTypeCode1Id").append('<option value="116">质检</option>')

            }

            $("tbody>tr").click(function(){

            	   $(this).find('td').css('background',"orange").parents('tr').siblings().find('td').css('background',"#f5f5f5");

            	          });
   
            


		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }

        function clearCondition(){
            document.getElementById("searchForm").reset();

            var inputObjs=jQuery("#searchForm input[type='text']");
            for(var i=0;i<inputObjs.length;i++){
                var inputObj = inputObjs[i];
                inputObj.value="";
            }

            var selectObjs = jQuery("#searchForm input[class='input-medium']");
            for(var i=0;i<selectObjs.length;i++){
                var selectObj = selectObjs[i];
                selectObj.value="";
            }
        }
		//处理触发的事件
		function abandoned(id){
			$("#myAbandonedModal").modal('show');
			$("#myId").val(id);
		}
		//处理 原因提交的事件
		function onclickAbandoned(){
			var v = $("#myId").val();
			var date = $("#reson").val();
			//有没有输入原因
			if(date == ""){
				$("#myNoAbandonedModal").modal('show');
			}else{
					window.location.href="${ctx}/cusserviceproblem/bizCusServiceProblem/handle?id="+v+"&statusdescribe="+date;
					}
			$("#reson").val('');
			$('#myAbandonedModal').modal('hide');		
			}
		//处理 取消 事件
		function onclickNoAbandoned(){
			$('#myAbandonedModal').modal('hide');
			$("#reson").val('');
		}
		
		
		
		
        
		//驳回触发的事件
		function abandonedReject(id){
			$("#myAbandonedModalReject").modal('show');
			$("#myIdReject").val(id);
		}
		
		
		//处驳回 原因提交的事件
		function onclickAbandonedReject(){
			var v = $("#myIdReject").val();
			var date = $("#resonReject").val();
			//有没有输入原因
			if(date == ""){
				$("#myNoAbandonedModalReject").modal('show');
			}else{
					window.location.href="${ctx}/cusserviceproblem/bizCusServiceProblem/reject?id="+v+"&statusdescribe="+date;
					}
			$("#resonReject").val('');
			$('#myAbandonedModalReject').modal('hide');		
			}
		//驳回 取消 事件
		function onclickNoAbandonedReject(){
			$('#myAbandonedModalReject').modal('hide');
			$("#resonReject").val('');
		}
        
		
		//驳回触发的事件
		function abandonedxm(){
			var v = $("#storeId").val();
			
			window.location.href="${ctx}/cusserviceproblem/bizCusServiceProblem/ProjectView?id="+v;
		}
        
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
<!-- 		<li class="active"><a href="#" onclick="javascript:history.go(-1)">投诉接收页面</a></li> -->
		<li class="active"><a href="#">投诉问题列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="bizCusServiceProblem" action="${ctx}/order/order/listProjectaflt" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<input id="pageSize1" name="storeId" type="hidden" value="${bizCusServiceProblem.storeId}"/>
		<input id="pageSize2" name="cusServiceId" type="hidden" value="${bizCusServiceProblem.cusServiceId}"/>
		<input id="complaintProblemNrId" name="complaintProblemNr" type="hidden" value="${bizCusServiceProblem.complaintProblemNr}"/>
		<ul class="ul-form">
	
			<li>
<!-- 				<input name ="oNumAndcusNameIph"  value=""    htmlEscape="false" maxlength="16" class="input-medium"/> -->
					<form:input    placeholder="请输入客户姓名/手机号/订单编号"  path="workOrderCode" htmlEscape="false" maxlength="25" class="input-medium"/>
			</li>
			
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="btns"><input class="btn btn-primary clearBtn" type="button" value="清空" onclick="clearCondition()"/></li>
			<li class="btns"><input class="btn btn-primary clearBtn" type="button" value="返回" onclick="javascript:history.go(-1)"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
		<tr>
		
			<th>订单编号</th>
			<th>客户姓名</th>
			<th>客户手机号</th>
			<th>工程地址</th>
			<th>项目经理</th>
			<th>操作</th>

		</tr>

		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="findList">
			<tr>
			
				<td>
						${findList.orderId}
				</td>
				<td>
						${findList.customerName}
				</td>
				<td>
						${findList.customerMobile}
				</td>
				<td>
					   ${findList.supervisorName}
				</td>
				<td>
					   ${findList.supervisorMobile}
				</td>
				<td>
	    		<a href="${ctx}/cusserviceproblem/bizCusServiceProblem/projectbyIdaflt?id=${findList.id}&cusServiceId=${bizCusServiceProblem.cusServiceId}&complaintProblemNr=${bizCusServiceProblem.complaintProblemNr}">选择</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
	
	
</body>
</html>