<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>竣工审核</title>
<meta name="decorator" content="default" />
<style>
.undis {
	display: none;
}

.g-mask {
	width: 100%;
	height: 100%;
	position: relative;
	z-index: 99;
	font-size: 0;
}

#g-done_ask {
	width: 400px;
	height: 200px;
	position: fixed;
	left: 50%;
	top: 50%;
	transform: translate(-50%, -50%);
	border: 1px solid #333;
	border-radius: 4px;
}

.refuse {
	height: 50px;
	line-height: 50px;
	font-size: 20px;
	background: #54b4eb;
	margin: 0;
}

.content {
	color: #000;
	width: 400px;
	height: 100px;
	resize: none;
	margin: 0;
}

.second {
	width: 400px;
}

.second a {
	display: block;
	width: 200px;
	height: 50px;
	line-height: 50px;
	font-size: 24px;
	text-decoration: none;
	float: left;
	text-align: center;
	background: #54b4eb;
	color: #fff;
}

.second a:first-child {
	box-sizing: border-box;
	border-right: 1px solid #ccc;
}
</style>
<script type="text/javascript">
	$(document).ready(function() {
		getDepartments();
		//自动提示输入字符串个数
		$("#reason").keyup(function(){
	    	if($("#reason").val().length > 100){
	    	   $("#reason").val( $("#reason").val().substring(0,100) );
	    	}
	    	$("#word").text( 100 - $("#reason").val().length ) ;
	    });
		
		var arrSon = document.getElementsByName("orderStatusNumber");
		var cbAll = document.getElementById("chkAll");
		var boo = true;
		for(var i = 0; i < arrSon.length; i++){
			if(arrSon[i].checked == false){
				boo=false;
			}
		}
		if(boo){
			cbAll.checked=true;
		}
		
	});
	function page(n, s) {
		$("#pageNo").val(n);
		$("#pageSize").val(s);
		$("#searchForm").submit();
		return false;
	}

	//审核通过
	function auditSucess(orderID){
	//${ctx }/bizorderfinishprojectbill/bizOrderFinishProjectBill/auditSucess?orderID=${orderList.id }
		//alert("订单编号："+orderID);
		if(orderID){
			if(confirm("确定竣工审核通过吗？")){
				//禁用提交按钮
				$("#auditSuccess").css("color","#CCC");
				$("#auditSuccess").unbind("click");
				$.ajax({
					url : "${ctx }/bizorderfinishprojectbill/bizOrderFinishProjectBill/auditSucess",
					type : "POST",
				    //async:false,
				    data : {
				    	orderID : orderID
					},
				  	success : function(data){
				  		//alert("date="+data);
                        if(data == 3){
						  alertx("请不要重复提交！");	
						}
						if(data == 0){
						  alertx("操作成功!");
						  $("#searchForm").submit();
					  	}
						if(data == 1){
							alertx("修改竣工数据错误!");
						 	return false;
					  	}
						
						if(data == 2){
							alertx("修改订单状态错误!");
						 	return false;
					  	}
						if(data == 4){
							alertx("请财务确认二期款！");
						}
				  }
				});
			}
		}
	}
	
	//清空查询条件
	function clearCondition() {
		document.getElementById("searchForm").reset();

		var inputObjs = jQuery("#searchForm input[type='text']");
		for (var i = 0; i < inputObjs.length; i++) {
			var inputObj = inputObjs[i];
			inputObj.value = "";
		}

		var selectObjs = jQuery("#searchForm input[class='input-medium']");
		for (var i = 0; i < selectObjs.length; i++) {
			var selectObj = selectObjs[i];
			selectObj.value = "";
		}
		
		var arrSon = document.getElementsByName("orderStatusNumber");
		for (i = 0; i < arrSon.length; i++) {
			if(arrSon[i].checked){
				arrSon[i].checked=false;
			}
		}
	}
	
	//驳回
	function abolish(id){
		$("#id").val(id);
		$("#abolishSummary").removeClass("undis");
	}
	//驳回取消
	function noAbolish(){
		$("#reason").val("");
		$("#id").val("");
		$("#abolishSummary").attr("class", "undis");
	}
	//驳回确认
	function yesAbolish(){
		//loading('正在提交，请稍等...');
		$("a[data='abolish']").removeAttr("onclick");
		$("a[data='abolish']").removeAttr("href");
		
		var cancelReason = $("#reason").val();
		var id = $("#id").val();
		 $("#abolishSummary").attr("class", "undis");
		$.ajax({
			url : "${ctx}/bizorderfinishprojectbill/bizOrderFinishProjectBill/auditFail",
			type : "POST",
		    data : {orderID : id,remarks : $("#reason").val()},
		  	success : function(data){
		  		//alert("date="+data);
		  		if(data == 3){
					  alertx("请不要重复提交！");	
				}
				if(data == 0){
				  alertx("操作成功!");
				  $("#searchForm").submit();
			  	}
				if(data == 1){
					alertx("修改竣工数据错误!");
				 	return false;
			  	}
				if(data == 2){
					alertx("修改订单状态错误!");
				 	return false;
			  	}
		  }
		});
		//window.location.href="${ctx}/bizorderfinishprojectbill/bizOrderFinishProjectBill/auditFail?orderID="+id;
	}
	
	//加载区域信息
	function getDepartments(){
		$("#enginDepartId").html('');
		$.ajax({
			url:'${ctx}/engdept/bizEngineeringDepartment/departmentList',
			type:'post',
			dataType : 'json',
			data:{
				'storeId':$('#storeId').val(),
				'projectMode':$('#projectMode').val(),
				'employeeId':$('#employeeId').val()
			},
			success:function(data){
				if(data == null){
					$("#enginDepartId").html('');
				} else {
					var html = "<option value=''></option>";
					for(var i=0;i<data.length;i++){
						var sec = "";
						if('${bizCompletedAudit.enginDepartId}' == data[i].value){
		            		sec = "selected='selected'";
		            		$("#s2id_enginDepartId .select2-chosen").html(data[i].label);
		            	}
						html += "<option value='" + data[i].value +"' " + sec + ">" + data[i].label + "</option>";
					}
					html+='';
	        		$("#enginDepartId").append(html);
				}
			}
		});
	}
	
	// --全选框被单击---
	function ChkAllClick(sonName, cbAllId) {
		var arrSon = document.getElementsByName(sonName);
		var cbAll = document.getElementById(cbAllId);
		var tempState = cbAll.checked;
		for (i = 0; i < arrSon.length; i++) {
			if (arrSon[i].checked != tempState)
				arrSon[i].click();
		}
	}

	// --子项复选框被单击---
	function ChkSonClick(sonName, cbAllId) {
		var arrSon = document.getElementsByName(sonName);
		var cbAll = document.getElementById(cbAllId);
		for (var i = 0; i < arrSon.length; i++) {
			if (!arrSon[i].checked) {
				cbAll.checked = false;
				return;
			}
		}
		cbAll.checked = true;
	}
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a
			href="${ctx}/bizcompletedaudit/bizCompletedAudit/preList">竣工审核列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="bizCompletedAudit"
		action="${ctx}/bizcompletedaudit/bizCompletedAudit/list" method="post"
		class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden"
			value="${page.pageSize}" />
		<input id="employeeId" name="employeeId" type="hidden"
			value="${employeeId}" />
		<ul class="ul-form">
			<li><label>门店：</label> <c:if test="${empty storeDropEnable}">
					<form:select path="storeId" class="input-medium" disabled="true"
						onchange="getDepartments()" id="storeId">
						<form:option value="" label="" />
						<form:options items="${fns:getStoreList()}" itemLabel="label"
							itemValue="value" htmlEscape="false" />
					</form:select>
				</c:if> <c:if test="${!empty storeDropEnable}">
					<form:select path="storeId" class="input-medium needClear"
						onchange="getDepartments()" id="storeId">
						<form:option value="" label="" />
						<form:options items="${fns:getStoreList()}" itemLabel="label"
							itemValue="value" htmlEscape="false" />
					</form:select>
				</c:if></li>
			<li><label>工程模式：</label> <c:if test="${empty gongcheng}">
					<form:select path="projectMode" class="input-medium"
						disabled="true" onchange="getDepartments()" id="projectMode">
						<form:option value="" label="" />
						<form:options items="${fns:getDictList('project_mode')}"
							itemLabel="label" itemValue="value" htmlEscape="false" />
					</form:select>
				</c:if> <c:if test="${!empty gongcheng}">
					<form:select path="projectMode" class="input-medium needClear"
						onchange="getDepartments()" id="projectMode">
						<form:option value="" label="" />
						<form:options items="${fns:getDictList('project_mode')}"
							itemLabel="label" itemValue="value" htmlEscape="false" />
					</form:select>
				</c:if></li>
			<li><label>区域：</label> <form:select path="enginDepartId"
					class="input-medium needClear" id="enginDepartId">
					<form:option value="${bizCompletedAudit.enginDepartId }"
						label="${bizCompletedAudit.departmentName }" />
				</form:select></li>
			<li><label>订单编号：</label> <form:input path="orderNumber"
					htmlEscape="false" maxlength="18" class="input-medium needClear" />
			</li>
			<%-- <li><label>订单状态：</label> <form:select path="orderStatusNumber"
					class="input-medium needClear">
					<form:option value="" label="" />
					<form:options items="${fns:getDictList('order_status')}"
						itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select></li> --%>
			<li><label>项目经理：</label> <form:input path="itemManager"
					htmlEscape="false" maxlength="255" class="input-medium needClear" />
			</li>
			<li><label>申请日期：</label> <input name="appStartDate" type="text"
				id="appStartDate" readonly="readonly" maxlength="20"
				class="input-medium Wdate"
				value="<fmt:formatDate value="${bizCompletedAudit.appStartDate}" pattern="yyyy-MM-dd"/>"
				onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'appEndDate\')}',isShowClear:false});" />
				&nbsp;至&nbsp; <input name="appEndDate" type="text" id="appEndDate"
				readonly="readonly" maxlength="20" class="input-medium Wdate"
				value="<fmt:formatDate value="${bizCompletedAudit.appEndDate}" pattern="yyyy-MM-dd"/>"
				onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'appStartDate\')}',isShowClear:false});" />
			</li>
			<li><label>实际竣工日期：</label> <input
				name="realFinishProjectStartDate" type="text"
				id="realFinishProjectStartDate" readonly="readonly" maxlength="20"
				class="input-medium Wdate"
				value="<fmt:formatDate value="${bizCompletedAudit.realFinishProjectStartDate}" pattern="yyyy-MM-dd"/>"
				onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'realFinishProjectEndDate\')}',isShowClear:false});" />
				&nbsp;至&nbsp; <input name="realFinishProjectEndDate" type="text"
				id="realFinishProjectEndDate" readonly="readonly" maxlength="20"
				class="input-medium Wdate"
				value="<fmt:formatDate value="${bizCompletedAudit.realFinishProjectEndDate}" pattern="yyyy-MM-dd"/>"
				onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'realFinishProjectStartDate\')}',isShowClear:false});" />
			</li>
			
			<li style="width:100%">
				<label>状态：</label>
				<input id="chkAll" name="chkAll" type="checkbox" value="全选" onclick="ChkAllClick('orderStatusNumber','chkAll')" />全选
			</li>
			<li>
				<input type="checkbox" name="orderStatusNumber"  value="300"  onclick="ChkSonClick('orderStatusNumber','chkAll')"  <c:forEach items="${bizCompletedAudit.status}" var="item"><c:if test="${item == '300'}">checked="checked"</c:if></c:forEach>  />300--已申请竣工
				<input type="checkbox" name="orderStatusNumber"  value="330"  onclick="ChkSonClick('orderStatusNumber','chkAll')"  <c:forEach items="${bizCompletedAudit.status}" var="item"><c:if test="${item == '330'}">checked="checked"</c:if></c:forEach> />330--结算员竣工审核不通过
				<input type="checkbox" name="orderStatusNumber"  value="340"  onclick="ChkSonClick('orderStatusNumber','chkAll')"  <c:forEach items="${bizCompletedAudit.status}" var="item"><c:if test="${item == '340'}">checked="checked"</c:if></c:forEach> />340--结算员竣工审核通过
				<input type="checkbox" name="orderStatusNumber"  value="400"  onclick="ChkSonClick('orderStatusNumber','chkAll')"  <c:forEach items="${bizCompletedAudit.status}" var="item"><c:if test="${item == '400'}">checked="checked"</c:if></c:forEach> />400--确认已竣工
			</li>
			
			<li class="btns"><input id="btnSubmit" class="btn btn-primary"
				type="submit" value="查询" /></li>
			<li class="btns"><input class="btn btn-primary clearBtn"
				type="button" value="清空" onclick="clearCondition()" /></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}" />
	<table id="contentTable"
		class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>门店</th>
				<th>工程模式</th>
				<th>区域</th>
				<th>订单编号</th>
				<th>小区门牌号</th>
				<th>顾客</th>
				<th>项目经理</th>
				<th>合同开工日期</th>
				<th>合同竣工日期</th>
				<th>申请日期</th>
				<th>实际竣工日期</th>
				<th>图片</th>
				<th>订单状态</th>
				<th>任务包数量</th>
				<th>项目经理竣工提成明细</th>
				<shiro:hasPermission name="bizcompletedaudit:bizCompletedAudit:edit">
					<th>操作</th>
				</shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="orderList">
				<tr>
					<td>${fns:getStoreLabel(orderList.storeId, '')}</td>
					<td>${fns:getDictLabel(orderList.projectMode, 'project_mode','')}</td>
					<td>${orderList.departmentName}</td>
					<td>${orderList.orderNumber}</td>
					<td>${orderList.communityName}-${orderList.buildNumber }-${orderList.buildUnit }-${orderList.buildRoom }
					</td>
					<td>${orderList.customerName }</td>
					<td>${orderList.employeeRealName}</td>
					<td><fmt:formatDate type="date"
							value="${orderList.contractStartDate}" /></td>
					<td><fmt:formatDate type="date"
							value="${orderList.contractEndDate}" /></td>
					<td><fmt:formatDate type="date"
							value="${orderList.applyDatetime}" /></td>
					<td><fmt:formatDate type="date"
							value="${orderList.realFinishProjectDate}" /></td>
					<td><a href="${ctx }/bizbusinesspic/bizBusinessPic/getByBusinessIdOrAudit?orderID=${orderList.id }">查看</a></td>
					<td>${fns:getDictLabel(orderList.orderStatusNumber, 'order_status', '')}
					</td>
					<td><a href="${ctx}/scheduling/orderTaskpackage/viewByOrderID?orderID=${orderList.id }">查看</a></td>
					<td><a href="${ctx}/bizcompletedaudit/bizCompletedAudit/queryManagerCompletedDetail?orderId=${orderList.id}">查看</a></td>
					<td><c:if
							test="${orderList.orderStatusNumber == '320' || orderList.orderStatusNumber == '340'}">
							<a
								href="${ctx }/bizorderfinishprojectbill/bizOrderFinishProjectBill/preUpdate?orderID=${orderList.id }">修改</a>
						</c:if> <c:if
							test="${orderList.orderStatusNumber == '320' || orderList.orderStatusNumber == '300'}">
							<%-- <input id="auditSuccess" class="btn btn-primary" type="button" value="通过" onclick="auditSucess(${orderList.id})"/> --%>
							<a id="auditSuccess" href="javascript:void(0)"  onclick="auditSucess(${orderList.id})">通过</a>
							<%-- <a href="${ctx}/bizcompletedaudit/bizCompletedAudit/auditFail?id=${orderList.id }">驳回</a> --%>
							<a href="javascript:void(0)" data="abolish"
								onclick="abolish('${orderList.id}')">驳回</a>
						</c:if></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<!-- 驳回弹框div -->
	<div class="g-mask undis" id="abolishSummary">
		<div id="g-done_ask">
			<p class="refuse">请输入作废理由：</p>
			<input type="hidden" id="id" />
			<textarea class="content" id="reason"></textarea>
			<p style="font-size: 13px; background: #2fa4e7; margin: 0px">
				还可以输入<i id="word">100</i>个字
			</p>
			<p class="second">
				<a href="javascript:void(0)" onclick="noAbolish()">取消</a> <a
					href="javascript:void(0)" id="yesSubmit" onclick="yesAbolish()">确认</a>
			</p>
		</div>
	</div>
	<div class="pagination">${page}</div>
</body>
</html>