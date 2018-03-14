<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>订单管理</title>
	<meta name="decorator" content="default"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/modules/popUp/css/base.css"/> 
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/calcRootFontSize.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#onCreateTaskpackage").hide();
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }

		var inspectorPhone;
		var inspectorName;
		var inspectorId;
		function getMangagerName(){
			//拿到质检员的姓名
			var r =document.getElementsByName("ids");
			/* var inspectorName;
			var inspectorId; */
		    for(var i=0;i<r.length;i++){
		         if(r[i].checked){
		       	 var nameAndId = r[i].value;
		         var arr =  r[i].value.split("@-#-@");
		         inspectorName = arr[0];
		         inspectorId = arr[1]; 
		         inspectorPhone = arr[2];
		       }
		    }
			$("#orderInspector").val(inspectorName);	 
			$("#orderInspectorId").val(inspectorId);
		}
		
		function yes(id){
			
			var employeeName = $("#orderInspector").val();	 
			var employeeId = $("#orderInspectorId").val();
			var isLongwayCommission = $("input[name='isLongwayCommission']:checked").val();
			
			var reasonRemarks =$("#reasonRemarks").val();
			
			var reasonType = $("#reasonType").val();
			
			if(reasonType=='-1'){
				alert("原因必须填写、说明允许为空");
				return;
			}
			
			window.location.href = "${ctx}/order2/order2/resendInspector?id="+id+"&inspectorName="+employeeName+"&inspectorId="+employeeId+"&inspectorPhone="+inspectorPhone+"&isLongwayCommission="+isLongwayCommission+
									"&reasonRemarks="+reasonRemarks+"&reasonType="+reasonType;
			//window.location.href = "${ctx}/order2/order2/saveInspector?id="+id+"&inspectorName="+employeeName+"&inspectorId="+employeeId+"&inspectorPhone="+inspectorPhone;
		}
		
		function no (){
			$("#reasonType").val("");
			$("#reasonRemarks").val("");
			$("#onCreateTaskpackage").hide();	
		}
		
		
		function save(id,communityName,buildNumber,buildUnit,buildRoom,customerName,customerPhone){
			
		  	var size = $("input[name='ids']:checked").size();
		    if(size == 1){
		    	
		    	
		    	if('${order.orderInspectorId}' == $("#orderInspectorId").val()){
		    		alert("目前订单的质检员已是"+'${order.orderInspector }'+"，不允许重新派相同的质检员");
					return;
		    	}
		    	
		    	$("#onCreateTaskpackage").show();
		    	
		    }else{
		    	alert("请选择质检员！");
		    	return;
		    }
		}
		
		//地图分配质检员
		function mapInspector(id){
			window.location.href = "${ctx}/order2/order2/mapReInspector?orderId="+id;
		}
		
		//返回
		function fanhui(){
			var hidProjectMode = $("#hidProjectMode").val();
			if(hidProjectMode == "1"){
				window.location.href = "${ctx}/order2/order2/listAllotInspector";
			}else if(hidProjectMode == "2"){
				window.location.href = "${ctx}/order2/order2/listAllotInspectorTradition";
			}else{
				window.location.href = "${ctx}/order2/order2/quasiIndustryYfpzjy";
			}
		}
		
	</script>
	
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
	
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/order2/order2/reAllotInspector?id=${order.id}">分配质检员</a></li>
	</ul>
	<div class="breadcrumb form-search">
	<%-- <form:form id="searchForm1" modelAttribute="order2" action="${ctx}/order2/order2/resendInspector?id=${order.id}" method="post" class="breadcrumb form-search"> --%>
	<form:form id="searchForm1" modelAttribute="order2" class="breadcrumb form-search">
		<input type="hidden" id="hidProjectMode" value="${order.projectMode }">
		<table style="text-align: left;">
			<tr >
				<td style="width:33%"><label>订单编号：</label>${order.orderNumber }</td>
				<td style="width:33%"><label>客户姓名：</label>${order.customerName }</td>
				<td style="width:33%"><label>客户手机号：</label>${order.customerPhone }</td>
			</tr>
			<tr>
				<td><label>客户类型：</label>
					${fns:getDictLabel(order.customerType, 'cus_type', '')}
				</td>
				<td><label>合同面积：</label>${order.contractArea }</td>
				<td><label>户型：</label>
					${fns:getDictLabel(order.houseType, 'home_type', '')}
				</td>
			</tr>
			<tr>
				<td><label>小区名称：</label>${order.communityName }</td>
				<td><label>门牌号：</label>${order.buildNumber }-${order.buildUnit }-${order.buildRoom }</td>
				<td><label>详细地址：</label>${order.communityName }</td>
			</tr>
			<tr>
			
				<td><label>签约日期：</label>
					<fmt:formatDate value="${order.signContractDate }" pattern="yyyy-MM-dd" />
				</td>
				<td><label>合同开工日期：</label>
				<fmt:formatDate value="${order.contractStartDate }" pattern="yyyy-MM-dd" />
				</td>
				<td><label>合同工期：</label>${order.contractTime }天</td>
			</tr>
			<tr>
				<td><label>客户经理：</label>${order.cusManager }</td>
				<td><label>设计师：</label>${order.designerName }</td>
				<td><label>质检员：</label>
				<input id="orderInspector" name="orderInspector" type="text" value="${order.orderInspector }" readonly="readonly"/>
				<input id="orderInspectorId" name="orderInspectorId" type="hidden" value="${order.orderInspector }" readonly="readonly"/>
				</td>
			</tr>
			<tr>
				<td><label>是否有远程费提成：</label>
					<c:if test="${order.isLongwayCommission == '1'}">
						<input type="radio" name="isLongwayCommission" value="0" />无
						<input type="radio" name="isLongwayCommission" value="1" checked="checked" />有
					</c:if>
					<c:if test="${order.isLongwayCommission == '0'}">
						<input type="radio" name="isLongwayCommission" value="0" checked="checked"/>无
						<input type="radio" name="isLongwayCommission" value="1" />有
					</c:if>
					<!-- <input type="radio" name="isLongwayCommission" value="0" checked="checked"/>无
					<input type="radio" name="isLongwayCommission" value="1" />有 -->
				</td>
			</tr>
			<tr>
				<!-- <td colspan="3"><input id="btnSubmit" class="btn btn-primary" name="btnSubmit" type="submit" value="保存"/></td> -->
				<td colspan="3" style="text-align:left;">
					<input id="btnSubmit" class="btn btn-primary" style="margin-left: 10px;" name="btnSubmit" type="button" value="保存" onclick="save('${order.id}','${order.communityName }','${order.buildNumber }','${order.buildUnit }','${order.buildRoom }','${order.customerName }','${order.customerPhone }')"/>
					<input id="btnBack" class="btn btn-primary" type="button" value="返回" onclick ="fanhui()"/></li>
					<input id="map" class="btn btn-primary" name="btnSubmit" type="button" value="地图重新分配质检员" onclick="mapInspector('${order.id}')"/>
				</td>
			</tr>
		</table>
	</form:form>
		<sys:message content="${message}"/>
	</div>
	 
	<form:form id="searchForm" modelAttribute="inspector" action="${ctx}/order2/order2/reAllotInspector?id=${order.id}" method="post" class="breadcrumb form-search">
	
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>姓名：</label>
				<form:input path="realname" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<tr>
				<th>选择</th>
				<th>质检员编号</th>
				<th>质检员姓名</th>
				<th>工程模式</th>
				<th>被换单次数</th>
				<th>星级</th>
				<th>NPS值</th>
				<th>排名</th>
				<th>在施在检工数</th>
				<th>累计检查工地数</th>
				<th>住址</th>
				<th>住址和工地距离(km)</th>
			</tr>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${list}" var="bizEmployee">
			<tr>
				<td>
					<input type="radio" name="ids" value="${bizEmployee.realname }@-#-@${bizEmployee.id}@-#-@${bizEmployee.phone}" onclick="getMangagerName()"/>
				</td>
				<td>
					${bizEmployee.no}
				</td>
				<td>
					${bizEmployee.realname}
				</td>
				<td>
					${fns:getDictLabel(bizEmployee.projectMode, 'employee_project_mode', '')}
				</td>
				<td>
					<c:if test="${bizEmployee.exchangeOrderTimes == null}">
						0
					</c:if>
					<c:if test="${bizEmployee.exchangeOrderTimes != null}">
						${bizEmployee.exchangeOrderTimes}
					</c:if>
				</td>
				<td>
					${bizEmployee.star}
				</td>
				<td>
					${bizEmployee.nps}
				</td>
				<td>
					${bizEmployee.sort}
				</td>
				<td>
					${bizEmployee.checkingCount} 
				</td>
				<td>
					${bizEmployee.checkedCount} 
				</td>
				<td>
					${bizEmployee.address}
				</td>
				<td>
					${bizEmployee.distance}
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	
	<div class="Black undis" id="onCreateTaskpackage">
		<div class="tc_center">
			<h2 id="orderAddress"></h2>
			<h2>${order.communityName}-${order.buildNumber}-${order.buildUnit}-${order.buildRoom}-${order.customerName}</h2>
			<div class="cen_t">
				<div class="cen_tex">
					
					<span class="span_l">原因：</span>
					<p class="span_r" id="installName">

						<select id="reasonType" name="reasonType">
							<option value="-1">-------请选择-------</option>
							<c:forEach items="${fns:getDictList('re_dispatch_cause')}" var="dis">
								<option value="${dis.value}">${dis.label}</option>
							</c:forEach>
						</select>
					</p>
				</div>
				<div class="cen_tex">
					<span class="span_l">说明：</span>
					<p class="span_r">
						<textarea id="reasonRemarks" maxlength="50"></textarea>
					</p>
				</div>
				<div class="cen_btn">
					<span class="btn_y" onclick="yes('${order.id}')">提交</span>
					<span class="btn_n" onclick="no()">取消</span>
				</div>
			</div>
			<div class="tc_close"></div>
		</div>
	</div>
	
	<%-- <div class="pagination">${page}</div> --%>
</body>
</html>