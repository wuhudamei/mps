<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>售后问题列表管理</title>
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
        
        
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/cusserviceproblem/bizCusServiceProblem/list">售后问题列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="bizCusServiceProblem" action="${ctx}/cusserviceproblem/bizCusServiceProblem/list" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>门店：</label>
				<form:select path="storeId" class="input-medium needClear">
					<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>客户姓名：</label>
				<form:input path="customerName" htmlEscape="false" maxlength="16" class="input-medium"/>
			</li>
			<li><label>项目经理：</label>
				<form:input path="managerName" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>质检：</label>
				<form:input path="pqcName" htmlEscape="false" maxlength="12" class="input-medium"/>
			</li>

			<li><label>责任部门：</label>
				<select class="input-medium needClear" name="liableDepartmentCode" id="liableDepartmentCodeId">

				</select>
			</li>



			<li><label>责任类别：</label>
				<select class="input-medium needClear" name="liableTypeCode1" id="liableTypeCode1Id">

				</select>
			</li>



			<li><label>问题创建时间：</label>
				<input name="start" id="beginSignContractDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					   value="<fmt:formatDate value="${entity.start}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'endSignContractDate\')}',isShowClear:true});"/> 至
				<input name="end" id="endSignContractDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					   value="<fmt:formatDate value="${entity.end}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'beginSignContractDate\')}',isShowClear:true});"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="btns"><input class="btn btn-primary clearBtn" type="button" value="清空" onclick="clearCondition()"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>

		<tr>
			<th>门店</th>
			<th>地址</th>
			<th>客户姓名</th>
			<th>客户手机号</th>
			<th>项目经理</th>
			<th>项目经理手机号</th>
			<th>质检</th>
			<th>质检手机号</th>
			<th>问题创建时间</th>
			<th>责任部门</th>
			<th>责任类别1</th>
			<th>责任类别2</th>
			<th>重要程度</th>
			<th>重要程度2</th>
			<th>附件</th>
			<th>状态</th>
			<th>操作</th>

		</tr>

		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="bizCusServiceProblem">
			<tr>

				<td>
						${fns:getStoreLabel(bizCusServiceProblem.storeId, '')}
				</td>

				<td>
					${bizCusServiceProblem.customerAddress}


				</td>
				<td>
						${bizCusServiceProblem.customerName}


				</td><td>
						${bizCusServiceProblem.customerMobile}


				</td><td>
						${bizCusServiceProblem.contractorName}


				</td><td>
						${bizCusServiceProblem.contractorMobile}


				</td><td>
						${bizCusServiceProblem.supervisorName}


				</td><td>
						${bizCusServiceProblem.supervisorMobile}


				</td><td>
					<fmt:formatDate value="${bizCusServiceProblem.problemCreateDatetime}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate>


				</td>
				<td>
						${bizCusServiceProblem.liableDepartmentCode}


				</td><td>
						${bizCusServiceProblem.liableTypeCode1}


				</td>
				<td>
						${bizCusServiceProblem.liableTypeCode2}


				</td>
				<td>
						${bizCusServiceProblem.importantDegreeCode1}


				</td>
				<td>
						${bizCusServiceProblem.importantDegreeCode2}


				</td>

				<td>
				<a href="${ctx}/cusserviceproblem/bizCusServiceProblem/viewPicsById?id=${bizCusServiceProblem.id}">图片</a>
				</td>
				<td>
					
					${fns:getDictLabel(bizCusServiceProblem.status, 'status', '')}
						
				</td>
				<td>

					<shiro:hasPermission name="cusserviceproblem:bizCusServiceProblem:edit">
	    				<a href="${ctx}/cusserviceproblem/bizCusServiceProblem/update?id=${bizCusServiceProblem.id}">接收</a>
						<a href="javascript:void(0)" onclick="abandoned ('${bizCusServiceProblem.id}')" >处理</a>
						<a href="javascript:void(0)" onclick="abandonedReject ('${bizCusServiceProblem.id}')" >驳回</a>
					</shiro:hasPermission>
					<shiro:hasPermission name="cusserviceproblem:bizCusServiceProblem:view">
	    				<a href="${ctx}/cusserviceproblem/bizCusServiceProblem/details?id=${bizCusServiceProblem.id}">详情</a>
					</shiro:hasPermission>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
						<!-- 处理btn弹框的model -->
			<div  class="modal fade" id="myAbandonedModal" tabindex="-1" role="dialog" style="width:350px">
								<input  id="myId" type="hidden">
								<div class="modal-header">
									<button class="close" type="button" data-dismiss="modal">×</button>
									<h4 id="myModalLabel" align="center" style="color: black;">处理内容</h3><br>
									<form:form id="timeForm" modelAttribute="bizMaterialsStandardReceiveBillApply" action="${ctx}/standradmaterialsrecords/bizMaterialsStandardReceiveBillApply/from">
										<input id = "ids"name="id" value="${bizMaterialsStandardReceiveBill.id}" type="hidden">
										<input id = "status" name="status" value="20" type="hidden">
									<div  style="margin:10px;min-height:22px;height:auto;padding-left:5px;text-align:center;" class="modal-body">
										<textarea id="reson" class="mask-text" placeholder="请输入处理内容，多行输入，不多于30个汉字" maxlength="30" ></textarea>
										<a href="javascript:void(0)" class="btn btn-primary" onclick="onclickAbandoned()" >确定</a>
										<a href="javascript:void(0)" class="btn" onclick="onclickNoAbandoned()">关闭</a>
									</div>
									</div>
									</form:form>
		</div>
						<!-- 驳回btn弹框的model -->
			<div  class="modal fade" id="myAbandonedModalReject" tabindex="-1" role="dialog" style="width:350px">
								<input  id="myIdReject" type="hidden">
								<div class="modal-header">
									<button class="close" type="button" data-dismiss="modal">×</button>
									<h4 id="myModalLabel" align="center" style="color: black;">驳回内容</h3><br>
									<form:form id="timeForm" modelAttribute="bizMaterialsStandardReceiveBillApply" action="${ctx}/standradmaterialsrecords/bizMaterialsStandardReceiveBillApply/from">
										<input id = "ids"name="id" value="${bizMaterialsStandardReceiveBill.id}" type="hidden">
										<input id = "status" name="status" value="20" type="hidden">
									<div  style="margin:10px;min-height:20px;height:auto;padding-left:5px;text-align:center;" class="modal-body">
										<textarea id="resonReject" class="mask-text" placeholder="请输入驳回内容，多行输入，不多于30个汉字" maxlength="30" ></textarea>
										<a href="javascript:void(0)" class="btn btn-primary" onclick="onclickAbandonedReject()" >确定</a>
										<a href="javascript:void(0)" class="btn" onclick="onclickNoAbandonedReject()">关闭</a>
									</div>
									</div>
									</form:form>
		</div>
	
	
	
	
</body>
</html>