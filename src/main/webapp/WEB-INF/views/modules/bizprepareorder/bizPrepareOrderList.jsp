<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>预备订单表管理</title>
	<meta name="decorator" content="default"/>
	<!-- <style>
		.undis{display:none;}
		.g-mask{width:100%;height:100%;position:relative;z-index:99;font-size:0;}
		#g-done_ask{width:400px;height:200px;position:fixed;left:50%;top:50%;transform:translate(-50%,-50%);border:1px solid #333;border-radius:4px;}
		.refuse{height:50px;line-height:50px;font-size:20px;background:#54b4eb;margin:0;}
		.content{color:#000;width:400px;height:100px;resize: none;margin:0;}
		.second{width:400px;}
		.second a{display:block;width:200px;height:50px;line-height:50px;font-size:24px;text-decoration:none;float:left;text-align:center;background:#54b4eb;color:#fff;}
		.second a:first-child{box-sizing:border-box;border-right:1px solid #ccc;}
	</style> -->
	<style>
		a{color:#2fa4e7;}
		.undis{display:none;}
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
		    top: 50%;
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
		
		 .pic .span_pic{
			text-align:right;
			width: 18%;
			font-size: 16px;
			margin-bottom:5px;
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
		.black_small{
		    display:none;
		    position:absolute;
		    top: 0;
		    left:0;
		    width: 100%;
		    height: 100%;
		    background:rgba(0,0,0,0.9)
		
		}
		.black_small img{
		    position:absolute;
		    top: 8%;
		    left: 10%;
		    width: 80%;
		    height: 75%;
		}
		.black_small .cen_btn{
		    width: 100%;
		    position:absolute;
		    bottom:5%;
		    left:15px;
		    text-align:center;
		}
		.black_small .cen_btn span {
		    width: 120px;
		    display: inline-block;
		    line-height:30px;
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
		.del_img{
		    position: absolute;
		    bottom: 5%;
		    right: 50%;
		    width: 3rem;
		    height: 3rem;
		    margin-right: -1.5rem;
		}


		.alertMask{position: fixed;top: 0;bottom: 0;left: 0;right: 0;background: rgba(0,0,0,.5);}
		.maskWrapper{width: 380px;border-radius: 4px;background: #fff;font-size: 16px;
		    position: fixed;top: 50%;left: 50%;transform: translate(-50%,-50%);}
		.col_3{color: #333}
		.col_6{color: #666;}
		.col_f{color: #fff;}
		.col_fdfcfa{color: #fdfcfa;}
		.col_0780ec{color: #0780ec;}
		.font28{font-size: 14px;}
		.font33{font-size: 16.5px;}
		.maskTit{height: 50px;line-height: 50px;text-align: center;}
		.maskContent{padding: 25px;border-top: 1px solid #ddd;border-bottom: 1px solid #ddd;line-height: 1.5em;}
		.maskBtns{width: 83%;margin: 0 auto;padding-bottom: 10px;padding-top: 10px;}
		.maskBtn{display: block;width: 60%;text-align: center;line-height: 38px;}
		.maskBtn{background: #0780ec;border-radius: 4px;display: block;width: 47.6%;
    		text-align: center;line-height: 38px;font-size:16px;margin: 0 auto;}
    		
	</style>
	<script type="text/javascript">
		$(document).ready(function() {
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
		
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/bizprepareorder/bizPrepareOrder/list">预备订单列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="bizPrepareOrder" action="${ctx}/bizprepareorder/bizPrepareOrder/list1" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>门店：</label>
				<form:select path="storeId" class="input-medium needClear">
					<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>订单编号：</label>
				<form:input path="orderNumber" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>客户姓名：</label>
				<form:input path="customerName" htmlEscape="false" maxlength="16" class="input-medium"/>
			</li>
			<li><label>客户手机号：</label>
				<form:input path="customerPhone" htmlEscape="false" maxlength="11" class="input-medium"/>
			</li>
			<li><label>设计师：</label>
				<form:input path="designerName" htmlEscape="false" maxlength="12" class="input-medium"/>
			</li>
			<li><label>拒绝原因：</label>
				<form:select path="refuseReasonType" class="input-medium needClear" >
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('refuse_reason_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>签约日期：</label>
				<input name="beginSignContractDate" id="beginSignContractDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${bizPrepareOrder.beginSignContractDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'endSignContractDate\')}',isShowClear:true});"/> 至  
				<input name="endSignContractDate" id="endSignContractDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${bizPrepareOrder.endSignContractDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'beginSignContractDate\')}',isShowClear:true});"/>
			</li>
			<li><label>合同开工日期：</label>
				<input name="beginContractStartDate" id="beginContractStartDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${bizPrepareOrder.beginContractStartDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'endContractStartDate\')}',isShowClear:true});"/> 至 
				<input name="endContractStartDate" id="endContractStartDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${bizPrepareOrder.endContractStartDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'beginContractStartDate\')}',isShowClear:true});"/>
			</li>
			<li><label style="width: 120px;">系统接收订单时间：</label>
				<input name="beginCreateDate" id="beginCreateDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${bizPrepareOrder.beginCreateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'endCreateDate\')}',isShowClear:true});"/> 至 
				<input name="endCreateDate" id="endCreateDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${bizPrepareOrder.endCreateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'beginCreateDate\')}',isShowClear:true});"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="btns"><input class="btn btn-primary clearBtn" type="button" value="清空" onclick="clearCondition()"/></li>
			<li>
				<span style="font-size:25px;font-weight:bold;text-align:center;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;待接收订单数量：<span style="font-size:25px;font-weight:bold;text-align:center;color:red;">${count }</span>
			</li>
			<li class="clearfix"></li>
			
			
		</ul>
		
		
		
	</form:form>
	<sys:message content="${message}"/>
	
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>门店</th>
				<th>订单编号</th>
				<th>客户姓名</th>
				<th>客户电话</th>
				<th>设计师</th>
				<th>签约日期</th>
				<th>合同开工日期</th>
				<th>系统接收订单时间</th>
				<th>预备订单状态</th>
				<th>拒绝理由</th>
				<shiro:hasPermission name="bizprepareorder:bizPrepareOrder:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="bizPrepareOrder">
			<tr>
				<td>
					${fns:getStoreLabel(bizPrepareOrder.storeId, '')}
				</td>
				<td>
					${bizPrepareOrder.orderNumber}
				</td>
				<td>
					${bizPrepareOrder.customerName}
				</td>
				<td>
					${bizPrepareOrder.customerPhone}
				</td>
				<td>
					${bizPrepareOrder.designerName}
				</td>				
				<td>
					<fmt:formatDate value="${bizPrepareOrder.signContractDate}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					<fmt:formatDate value="${bizPrepareOrder.contractStartDate}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					<fmt:formatDate value="${bizPrepareOrder.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${fns:getDictLabel(bizPrepareOrder.status, 'prepare_order_status', '')}
				</td>
				<td>
					<c:if test="${bizPrepareOrder.status eq 20}">
						${bizPrepareOrder.refuseReasonTypeName}
						<c:if test="${not empty bizPrepareOrder.refuseReason}">
							-${bizPrepareOrder.refuseReason}
						</c:if>
					</c:if>
				</td>
				<shiro:hasPermission name="bizprepareorder:bizPrepareOrder:edit"><td>
					<c:if test="${bizPrepareOrder.status eq 10 || bizPrepareOrder.status eq 15}">
						<a href="${ctx}/bizprepareorder/bizPrepareOrder/receive?id=${bizPrepareOrder.id}" onclick="return confirmx('确认要接收该预备订单吗？', this.href)">接收</a>
						<a href="#"  onclick="reject('${bizPrepareOrder.id}','${bizPrepareOrder.customerName}','${bizPrepareOrder.orderNumber}')">拒绝</a>
					<%-- </c:if>
					<c:if test="${bizPrepareOrder.status eq 15 }"> --%>
						<a href="${ctx}/bizprepareorder/bizPrepareOrder/form?id=${bizPrepareOrder.id}">修改</a>
					</c:if>
    				<a href="${ctx}/bizprepareorder/bizPrepareOrder/details?id=${bizPrepareOrder.id}">详情</a>
    				<a href="${ctx}/bizprepareorder/bizPrepareOrder/order_materials_choice_bill_detail?id=${bizPrepareOrder.id}">选材清单</a>
    				<a href="${ctx}/ordercad/orderCadfile/list?orderNumber=${bizPrepareOrder.orderNumber}&flag=1">图纸</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
	
	<!--拒绝 -->
	<!-- <div class="g-mask undis" id="refuseSalary">
		<div id="g-done_ask">
			<p class="refuse">请输入拒绝理由：</p>
			<textarea class="content" id="reason"></textarea>
			<p class="second">
				<a href="javascript:void(0)" onclick="noReject()">取消</a>
				<a href="javascript:void(0)" onclick="yesReject()">确认</a>
			</p>
		</div>
	</div> -->
	
	
	
	<div class="Black undis" id="refuseSalary">
		<div class="tc_center">
			<h2 id="orderAddress"></h2>
			<div class="cen_t">
				<div class="cen_tex">
					<span class="span_l">拒绝理由：</span>
					<p class="span_r">
						<select id="reasonType" style="width: 200px;">
							<option value="">-----请选择驳回类型------</option>
							<c:forEach items="${fns:getDictList('refuse_reason_type')}" var="aa">
								<option value="${aa.value }">${aa.label}</option>
							</c:forEach>
						</select>
					</p>
				</div>
				<div class="cen_tex">
					<span class="span_l">备注：</span>
					<p class="span_r">
						<textarea id="reason" maxlength="100"></textarea>
					</p>
				</div>
				<div class="cen_btn">
					<span class="btn_y" onclick="yesReject()">提交</span>
					<span class="btn_n" onclick="noReject()">取消</span>
				</div>
			</div>
			<div class="tc_close"></div>
		</div>
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
	
	<script type="text/javascript">
	
	
	var prepareOrderId;
	
	function sendMessage(){
		$("#subReport").hide();
	}
	
	function reject(id,customerName,orderNumber){
		prepareOrderId = id;
		$("#orderAddress").text(customerName+"-"+orderNumber);  
		$("#refuseSalary").removeClass("undis");
	}
	function yesReject(){
		var reason = $("#reason").val();
		var reasonType = $("#reasonType").val();
		var reasonTypeName = $("#s2id_reasonType .select2-chosen").html();
		
		if(null==reasonType || reasonType==""){
			$("#alertRemarks").text("请选择拒绝理由");
       		$("#subReport").show();
			return false;
		}
		if(reasonType=="2"){
			if(null==reason || reason==""){
				$("#alertRemarks").text("拒绝理由选择【其他】时，必须填写备注内容！");
           		$("#subReport").show();
				return false;
			}
		}
		
		/* $("#reason").val("");  
		$("#reasonType").val("");
		$("#refuseSalary").addClass("undis"); */
		window.location.href = "${ctx}/bizprepareorder/bizPrepareOrder/refuse?id="+prepareOrderId+"&reason="+reason+"&reasonType="+reasonType+"&reasonTypeName="+reasonTypeName;
	
	}
	function noReject(){
		$("#reason").val("");
		$("#reasonType").val("");
		$("#refuseSalary").addClass("undis");
		
	}
	
	</script>
</body>
</html>