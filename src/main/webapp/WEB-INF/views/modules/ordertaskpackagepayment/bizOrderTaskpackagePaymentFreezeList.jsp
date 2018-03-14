<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>付款单冻结/解冻</title>
<meta name="decorator" content="default" />
<style>
a {
	color: #2fa4e7;
}

.undis {
	display: none;
}

body {
	background-color: #fff;
	font-size: 16px;
}

body {
	width: 100%;
	height: 100%;
	position: relative
}

.Black {
	position: absolute;
	top: 0;
	left: 0;
	background: rgba(0, 0, 0, .6);
	width: 100%;
	height: 100%
}

.Black .tc_center {
	padding: 15px;
	position: absolute;
	top: 40%;
	left: 50%;
	width: 560px;
	height: 420px;
	margin-left: -300px;
	margin-top: -210px;
	background: #fff;
	color: #666
}

.Black .tc_center h2 {
	font-size: 20px;
	text-align: center;
	line-height: 40px
}

.Black .tc_center .cen_t {
	width: 100%
}

.Black .tc_center .cen_t p {
	line-height: 30px;
	font-size: 16px;
	text-align: center;
	margin-bottom: 12px
}

.Black .tc_center .cen_t p input {
	width: 50%;
	line-height: 30px;
	padding-left: 10px;
	border-radius: 3px;
	border: #ccc solid 1px
}

.Black .tc_center .cen_t .cen_btn {
	position: absolute;
	width: 100%;
	bottom: 20px;
	text-align: center;
	line-height: 30px
}

.Black .tc_center .cen_t .cen_btn span {
	width: 120px;
	display: inline-block;
	text-align: center;
	cursor: pointer;
	border-radius: 3px
}

.Black .tc_center .cen_t .cen_btn .btn_y {
	background: #3da5ee;
	color: #fff
}

.Black .tc_center .cen_t .cen_btn .btn_n {
	margin-left: 50px;
	border: solid 1px #3da5ee;
	color: #3da5ee
}

.Black .tc_center .cen_t .cen_tex {
	width: 90%;
	margin-left: 5%;
	font-size: 16px;
	clear: both;
}

.Black .tc_center .cen_t .cen_tex .span_l {
	display: block;
	line-height: 30px;
	float: left;
	width: 28%;
	text-align: right;
	vertical-align: middle
}

.Black .tc_center .cen_t .cen_tex .span_r {
	text-align: left;
	vertical-align: middle;
	position: relative;
	margin-bottom: 8px
}

.Black .tc_center .cen_t .cen_tex .span_r input {
	width: 90%;
	background: 0 0
}

.Black .tc_center .cen_t .cen_tex .span_r textarea {
	color: #666;
	width: 90%;
	height: 200px;
	padding: 5px 15px;
	line-height: 20px;
	font-size: 12px;
	border: #ccc solid 1px;
	border-radius: 3px
}

.Black .tc_close {
	background: url(../images/close.png) no-repeat;
	background-size: 100% 100%;
	width: 50px;
	height: 50px;
	position: absolute;
	top: -10px;
	right: -12px
}

.pic {
	background: #fff;
}

.pic textarea {
	padding-left: 0.92rem;
	width: 100%;
	height: 8rem;
	color: #333;
	line-height: 2rem;
	font-size: 1.4rem;
}

.pic .span_pic {
	text-align: right;
	width: 18%;
	font-size: 16px;
	margin-bottom: 5px;
}

.pic p {
	padding-left: 0.92rem;
	padding-right: 0.92rem;
	line-height: 2.75rem;
	height: 2.75rem;
	font-size: 1.5rem;
	color: #cacaca;
}

.pic p span {
	padding: 2px;
	float: right;
	color: #bcbbbb;
}

.pic .Fol {
	height: 200px;
	overflow: auto;
	padding-right: 0.92rem;
}

.pic .Fol img {
	margin-bottom: 3px;
	margin-right: 1px;
	width: 24.5%;
	height: 7rem;
	float: left;
}

.pic .Fol span {
	display: inline-block;
	width: 25%;
	height: 7rem;
	background: url(../images/zhaop_06.png) no-repeat;
	background-size: 100% 100%;
}

.pic .Fol span .Up {
	width: 100%;
	height: 100%;
	opacity: 0;
}

.Big {
	position: absolute;
	top: -112px;
	left: 50%;
	margin-left: -200px;
	width: 400px !important;
	height: 300px !important;
	z-index: 99;
}

.black_small {
	display: none;
	position: absolute;
	top: 0;
	left: 0;
	width: 100%;
	height: 100%;
	background: rgba(0, 0, 0, 0.9)
}

.black_small img {
	position: absolute;
	top: 8%;
	left: 10%;
	width: 80%;
	height: 75%;
}

.black_small .cen_btn {
	width: 100%;
	position: absolute;
	bottom: 5%;
	left: 15px;
	text-align: center;
}

.black_small .cen_btn span {
	width: 120px;
	display: inline-block;
	line-height: 30px;
	text-align: center;
	cursor: pointer;
	border-radius: 3px
}

.black_small .btn_y {
	background: #3da5ee;
	color: #fff
}

.black_small .btn_n {
	margin-left: 50px;
	border: solid 1px #3da5ee;
	color: #3da5ee
}

.del_img {
	position: absolute;
	bottom: 5%;
	right: 50%;
	width: 3rem;
	height: 3rem;
	margin-right: -1.5rem;
}

.alertMask {
	position: fixed;
	top: 0;
	bottom: 0;
	left: 0;
	right: 0;
	background: rgba(0, 0, 0, .5);
}

.maskWrapper {
	width: 380px;
	border-radius: 4px;
	background: #fff;
	font-size: 16px;
	position: fixed;
	top: 50%;
	left: 50%;
	transform: translate(-50%, -50%);
}

.col_3 {
	color: #333
}

.col_6 {
	color: #666;
}

.col_f {
	color: #fff;
}

.col_fdfcfa {
	color: #fdfcfa;
}

.col_0780ec {
	color: #0780ec;
}

.font28 {
	font-size: 14px;
}

.font33 {
	font-size: 16.5px;
}

.maskTit {
	height: 50px;
	line-height: 50px;
	text-align: center;
}

.maskContent {
	padding: 25px;
	border-top: 1px solid #ddd;
	border-bottom: 1px solid #ddd;
	line-height: 1.5em;
}

.maskBtns {
	width: 83%;
	margin: 0 auto;
	padding-bottom: 10px;
	padding-top: 10px;
}

.maskBtn {
	display: block;
	width: 60%;
	text-align: center;
	line-height: 38px;
}

.maskBtn {
	background: #0780ec;
	border-radius: 4px;
	display: block;
	width: 47.6%;
	text-align: center;
	line-height: 38px;
	font-size: 16px;
	margin: 0 auto;
}
</style>
<script type="text/javascript">
		$(document).ready(function() {
			getDepartments();
		});
		
		function getDepartments(){
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
					if(data == null || data == ""){
						$("#enginDepartId").html("");
						$("#s2id_enginDepartId .select2-chosen").html("");
					}else{
						var content = "<option></option>";
						for(var i=0;i<data.length;i++){
							if('${bizOrderTaskpackagePaymentVo.enginDepartId}' == data[i].value){
								$("#s2id_enginDepartId .select2-chosen").html(data[i].label);
								content = content + "<option value='"+data[i].value+"' selected='selected'>"+data[i].label+"</option>";
							}else{
								content = content + "<option value='"+data[i].value+"'>"+data[i].label+"</option>";
							}
						}
						$("#enginDepartId").html(content);
					}
				}
			});
		}
		
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

			$("input[name='status']").removeAttr("checked");
		}
		
		function showModel(id,customerMessage,customerName,groupRealname,status,orderTaskpackage,paymentType,paymentAmount){
			$("#id").val(id);
			$("#freezeStatus").val(status);
			if(status == 18){
				$("#fonzeType").text("冻结");
				if(paymentType == 0){
					$("#tc_center").css("height", "600px");
					$("#frozenBtn1").show();
					$("#frozenBtn2").show();
					$("#frozenBtn3").hide();
					$("#firstPayment").html(parseFloat(paymentAmount).toFixed(2));
					$("#payment1").show();
					$.ajax({
						url:"${ctx}/ordertaskpackagepayment/bizOrderTaskpackagePayment/findBalancePaymentByPaymentId?paymentId="+id,
						type:"post",
						success:function(data){
							if(data != null && data != ""){
								$("#balancePayment").html(parseFloat(data.amount).toFixed(2));
								$("#payment2").show();
							}else{
								$("#frozenBtn1").hide();
							}
							$("#orderAddress").text(customerMessage+"-"+customerName);
							$("#orderTaskpackage").text(orderTaskpackage);
							$("#groupRealName").text(groupRealname);
							$("#freezeModel").show();
						}
					});
				}else if(paymentType == 1){
					$("#frozenBtn3").show();
					$("#frozenBtn1").hide();
					$("#frozenBtn2").hide();
					$("#orderAddress").text(customerMessage+"-"+customerName);
					$("#orderTaskpackage").text(orderTaskpackage);
					$("#groupRealName").text(groupRealname);
					$("#payment1").hide();
					$("#payment2").hide();
					$("#freezeModel").show();
				}
			}else if(status == 15){
				$("#fonzeType").text("解冻");
				$("#frozenBtn3").show();
				$("#frozenBtn1").hide();
				$("#frozenBtn2").hide();
				$("#orderAddress").text(customerMessage+"-"+customerName);
				$("#orderTaskpackage").text(orderTaskpackage);
				$("#groupRealName").text(groupRealname);
				$("#freezeModel").show();
			}
			
		}
		
		function no(){
			$("#frozenRemarks").val(null);
			$("#freezeModel").hide();
		}
		function yes(isFrozenType){
			var frozenRemarks = $("#frozenRemarks").val();
			if(frozenRemarks == null || frozenRemarks == ''){
				$("#alertRemarks").text("请输入说明");
           		$("#subReport").show();
				return false;
			}
			var status=$("#freezeStatus").val();
			var id= $("#id").val();
			top.$.jBox.confirm("确认要提交吗？","系统提示",function(v,h,f){
				if (v == "ok") {
				$.ajax({
					url:'${ctx}/ordertaskpackagepayment/bizOrderTaskpackagePayment/freezePayment?id='+id+'&status='+status+'&frozenRemarks='+frozenRemarks+"&isFrozenType="+isFrozenType,
					type:'post',
					success:function(data){
						if(data == "repeat"){
							$("#alertRemarks").text("请不要重复提交！");
			           		$("#subReport").show();
						}else if(data == "success"){
							$("#freezeModel").hide();
							window.location.href="${ctx}/ordertaskpackagepayment/bizOrderTaskpackagePayment/paymentFreezeList";
						}else if(data == "error"){
							$("#alertRemarks").text("系统异常,请联系管理员！");
			           		$("#subReport").show();
						}
					}
				});
				}
			},{buttonsFocus:1});
			top.$('.jbox-body .jbox-icon').css('top','55px');
			
		}
		 function sendMessage(){
		    	$("#subReport").hide();
		    }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a
			href="${ctx}/ordertaskpackagepayment/bizOrderTaskpackagePayment/paymentFreezeList">付款单冻结/解冻</a></li>
	</ul>
	<form:form id="searchForm"
		modelAttribute="bizOrderTaskpackagePaymentVo"
		action="${ctx}/ordertaskpackagepayment/bizOrderTaskpackagePayment/paymentFreezeList"
		method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden"
			value="${page.pageSize}" />
		<ul class="ul-form">
			<li><label>门店：</label> <c:if test="${empty storeDropEnable}">
					<form:select path="storeId" class="input-medium"
						id="storeId" onchange="getDepartments()">
						<form:option value=""></form:option>
						<form:options items="${fns:getStoreList()}" itemLabel="label"
							itemValue="value" htmlEscape="false" />
					</form:select>
				</c:if> <c:if test="${!empty storeDropEnable}">
					<form:select path="storeId" class="input-medium needClear" id="storeId"
						onchange="getDepartments()">
						<form:option value=""></form:option>
						<form:options items="${fns:getStoreList()}" itemLabel="label"
							itemValue="value" htmlEscape="false" />
					</form:select>
				</c:if>
			</li>

            <li><label>工程模式：</label>
				<c:if test="${empty gongcheng}">
					<form:select path="projectMode" id="projectMode" class="input-medium" disabled="true" onchange="getDepartments()">
						<form:option value="" label=""/>
						<form:options items="${fns:getDictList('package_project_mode')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
				<c:if test="${!empty gongcheng}">
					<form:select path="projectMode" id="projectMode" class="input-medium needClear" onchange="getDepartments()">
						<form:option value="" label=""/>
						<form:options items="${fns:getDictList('package_project_mode')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
			</li>
			
			<li><label>区域：</label>
				<select id="enginDepartId" name="enginDepartId" class="input-medium needClear">

				</select>
			</li>
            
			<li><label>客户姓名：</label> <form:input path="customerName"
					htmlEscape="false" maxlength="64" class="input-medium needClear" />
			</li>
			<li><label>项目经理：</label> <form:input path="itemCustomer"
					htmlEscape="false" maxlength="64" class="input-medium needClear" />
			</li>
			<li><label>工人组长：</label> <form:input path="groupRealname"
					htmlEscape="false" maxlength="64" class="input-medium needClear" />
			</li>
			<li><label>付款单状态：</label> <form:select path="status"
					class="input-medium needClear">
					<form:option value=""></form:option>
					<form:option value="15">已审核通过待打款</form:option>
					<form:option value="18">已冻结付款单</form:option>
					<form:option value="90">批次已作废</form:option>
				</form:select></li>

			<li><label>付款单号：</label> <form:input
					path="orderTaskpackagePaymentCode" htmlEscape="false"
					maxlength="64" class="input-medium needClear" /></li>

			<li><label>结算单号：</label> <form:input path="settlementNo"
					htmlEscape="false" maxlength="64" class="input-medium needClear" />
			</li>
			<li><label>冻结/解冻原因：</label> <form:input path="frozenRemarksTow" htmlEscape="false" maxlength="64" class="input-medium needClear" />
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
				<th>付款单编号</th>
				<th>结算单编号</th>
				<th>小区名称</th>
				<th>客户</th>
				<th>项目经理</th>
				<th>工人组长</th>
				<th>任务包名称</th>
				<th>付款单状态</th>
				<th>付款类型</th>
				<th>结算金额</th>
				<th>冻结/解冻说明</th>
				<%-- <shiro:hasPermission name="ordertaskpackagepayment:bizOrderTaskpackagePayment:view">  ${payment.customerPhone}- --%>
				<th>操作</th>
				<%-- </shiro:hasPermission> --%>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="payment">
				<tr>
					<td>${fns:getStoreLabel(payment.storeId, '')}</td>
					<td>${fns:getDictLabel(payment.projectMode, 'package_project_mode', '')}</td>
					<td>${payment.enginDepartName}</td>
					<td>${payment.orderTaskpackagePaymentCode}</td>
					<td>${payment.settlementNo}</td>
					<td>${payment.customerMessage}</td>
					<td>${payment. customerName }</td>
					<td>${payment.itemCustomer}</td>
					<td>${payment.groupRealname}</td>
					<td>${payment.packageName}</td>
					<td>${fns:getDictLabel(payment.status, 'payment_status', '')}</td>
					<td><c:if test="${payment.orderTaskpackagePaymentType eq '0'}">首款</c:if>
						<c:if test="${payment.orderTaskpackagePaymentType eq '1'}">尾款</c:if>
					</td>
					<td>${payment.amount}</td>
					<td>${payment.frozenRemarksTow}</td>
					<td><c:if test="${payment.status == '18'}">
							<a href="#" onclick="showModel('${payment.id}','${payment.customerMessage}','${payment.customerName}','${payment.groupRealname}','15','${payment.packageName}','${payment.orderTaskpackagePaymentType}',${payment.amount})">解冻</a>
						</c:if> <c:if test="${payment.status != '18'}">
							<a href="#" onclick="showModel('${payment.id}','${payment.customerMessage}','${payment.customerName}','${payment.groupRealname}','18','${payment.packageName}','${payment.orderTaskpackagePaymentType}',${payment.amount})">冻结</a>
						</c:if> <a
						href="${ctx}/ordertaskpackagepayment/bizOrderTaskpackagePayment/findfreezePaymentDetailList?bizOrderTaskpackagePaymentId=${payment.id}">详情</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
	
	<div class="Black undis" id="freezeModel">
	    <input  type="hidden" id="id"/>
	    <input  type="hidden" id="freezeStatus"/>
		<form id="urgeForm">
			<div class="tc_center" id="tc_center">
			    <h2 style="font-size: 15px;color: red" id="fonzeType"></h2>
				<h2 id="orderAddress"></h2>
				<h2 style="font-size: 15px" id="orderTaskpackage"></h2>
	            <div class="cen_t">
	            	<div class="cen_tex">
	            		<span class="span_l">工人组长：</span>
	                    <p class="span_r" id="groupRealName"></p>
	                </div>
	                <div class="cen_tex" id="payment1" style="display: none;">
	            		<span class="span_l">首款金额：</span>
	                    <p class="span_r" id="firstPayment"></p>
	                </div>
	                <div class="cen_tex" id="payment2" style="display: none;">
	            		<span class="span_l">尾款金额：</span>
	                    <p class="span_r" id="balancePayment"></p>
	                </div>
	                <div class="cen_tex">
						<span class="span_l">说明：</span>
						<p class="span_r">
							<textarea id="frozenRemarks" maxlength="50" name="urgeReply"></textarea>
						</p>
					</div>
	                <div class="cen_btn">
	                    <span class="btn_y" onclick="yes(1)" id="frozenBtn1" >冻结首款+尾款</span>
	                	<span class="btn_y" onclick="yes(0)" id="frozenBtn2" style="margin-left: 50px;">冻结首款</span>
	                	<span class="btn_y" onclick="yes(0)" style="margin-left: 50px;" id="frozenBtn3">提交</span>
	                    <span class="btn_n" onclick="no()"> 取消 </span>
	                </div>
	            </div>
	        </div>
		</form>
    </div>
    
    <div style="background:rgba(0,0,0,.4);z-index:9;" class="g-mask undis" id ="subReport">
		<div class="alertMask">
			<div class="maskWrapper">
				<p class="col_3 maskTit">通知</p>
				<div class="font28 col_6 maskContent" id="alertRemarks"></div>
				<div class="maskBtns clearfix">
					<a class="maskBtn font33 col_f"  onclick="sendMessage()" href="javascript:;">我知道了</a>
				</div>
			</div>
		</div>
	</div>
</body>
</html>