<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>安装项查询</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			findInstallName();
		});
		
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
		
		
		function findInstallName(){
			var html2='<option value=""></option>';
			var html3='<option value=""></option>';
			var storeId = $("#storeId").val();
			var projectModeId = $("#projectMode").val();
			
			if (storeId=="" ||projectModeId=="" ||undefined==storeId ||undefined==projectModeId) {
				return;
			}
			$.ajax({
				url:"${ctx}/bizengininstall/bizEnginInstall/findInstallItemByStoreIdAndProjectMode?storeId="+storeId+"&projectModeId="+projectModeId,					
				success: function(data){
					
					if(null!=data&&data.length>0){
				
						for (var v = 0; v < data.length; v++) {
							
							if(data[v].isOn == 1){
								//启用
								if('${enginInstallNew.projectInstallItemId}' == data[v].id){
									$("#s2id_projectInstallItemId .select2-chosen").html(data[v].installItemName);
									html2 = html2 + "<option value='"+data[v].id+"' selected='selected'>"+data[v].installItemName+"</option>";
								}else{
									html2 = html2 + "<option value='"+data[v].id+"'>"+data[v].installItemName+"</option>";
								}
							}else{
								//停用
								if('${enginInstallNew.projectInstallItemIdStop}' == data[v].id){
									$("#s2id_projectInstallItemIdStop .select2-chosen").html(data[v].installItemName);
									html3 = html3 + "<option value='"+data[v].id+"' selected='selected'>"+data[v].installItemName+"</option>";
								}else{
									html3 = html3 + "<option value='"+data[v].id+"'>"+data[v].installItemName+"</option>";
								}
								
							}
						}
						
						$("#projectInstallItemId").html(html2);
						$("#projectInstallItemIdStop").html(html3);
					} else {
						$("#projectInstallItemId").html(html2);
						$("#projectInstallItemIdStop").html(html3);
					}
				}
			})
		} 
		
		//根据门店和工程模式 动态加载
		function findInstallNameTwo(){
			var html2='<option value=""></option>';
			var html3='<option value=""></option>';
			var storeId = $("#storeId").val();
			var projectModeId = $("#projectMode").val();
			
			if (storeId=="" ||projectModeId=="" ||undefined==storeId ||undefined==projectModeId) {
				return;
			}
			$.ajax({
				url:"${ctx}/bizengininstall/bizEnginInstall/findInstallItemByStoreIdAndProjectMode?storeId="+storeId+"&projectModeId="+projectModeId,					
				success: function(data){
					
					if(null!=data&&data.length>0){
				
						for (var v = 0; v < data.length; v++) {
							
							if(data[v].isOn == 1){
								//启用
								html2 = html2 + "<option value='"+data[v].id+"'>"+data[v].installItemName+"</option>";
							}else{
								//停用
								html3 = html3 + "<option value='"+data[v].id+"'>"+data[v].installItemName+"</option>";
								
							}
						}
						
						$("#projectInstallItemId").html(html2);
						$("#projectInstallItemIdStop").html(html3);
					} else {
						$("#projectInstallItemId").html(html2);
						$("#projectInstallItemIdStop").html(html3);
					}
				}
			})
		} 
		
		function formSelectSubmit(){
			
			var orderNumber = $("#orderNumber").val().trim();
			var customerName = $("#customerName").val().trim();
			
			if(orderNumber == ""  && customerName == ""){
				$("#alertRemarks").text("请输入订单编号或者客户姓名，再进行查询");
        		$("#subReport").show();
        		return false;
			}
			//提交表单
	    	$("#searchForm").submit();
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
		<li class="active"><a href="${ctx}/enginInstallNew/enginInstallNew/preSpecialDealWithList">主材安装申请-特殊处理</a></li>
	
	</ul>
	<form:form id="searchForm" modelAttribute=	"enginInstallNew" action="${ctx}/enginInstallNew/enginInstallNew/specialDealWithList" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>门店：</label> 
				<form:select path="storeId" class="input-medium needClear"  onchange="findInstallNameTwo()">
					<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>工程模式：</label>
				<c:if test="${empty gongcheng}">
					<form:select path="projectMode" class="input-medium" disabled="true" onchange="findInstallNameTwo()">
						<form:option value="" label=""/>
						<form:options items="${fns:getDictList('project_mode')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
				<c:if test="${!empty gongcheng}">
					<form:select path="projectMode" class="input-medium needClear" onchange="findInstallNameTwo()">
						<form:option value="" label=""/>
						<form:options items="${fns:getDictList('project_mode')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
					
			</li>
			<li><label>订单编号：</label>
				<form:input path="orderNumber" htmlEscape="false" maxlength="64" class="input-medium needClear"/>
			</li>
			<li><label>客户姓名：</label>
				<form:input path="customerName" htmlEscape="false" maxlength="16" class="input-medium needClear"/>
			</li>
			<li><label style="width:140px;">安装项名称（停用）：</label>
				<form:select path="projectInstallItemIdStop" class="input-medium needClear">
				</form:select>
			</li>
			<li><label>主材安装项：</label>
				<form:select path="projectInstallItemId" class="input-medium needClear">
				</form:select>
			</li>
			<li style="width: 40%"><label>安装项状态：</label>
				<input type="checkbox" name="installStatus"  value="3" <c:if test="${fns:checkIsExits(enginInstallNew.installStatus,'3')}"> checked="checked"</c:if>/>已转给供应商&nbsp;
				<input type="checkbox" name="installStatus"  value="310" <c:if test="${fns:checkIsExits(enginInstallNew.installStatus,'310')}"> checked="checked"</c:if>/>已确定工人组&nbsp;
				<input type="checkbox" name="installStatus"  value="320" <c:if test="${fns:checkIsExits(enginInstallNew.installStatus,'320')}"> checked="checked"</c:if>/>施工中&nbsp;
			</li>
			<li class="btns"><input class="btn btn-primary" type="button" value="查询" onclick="formSelectSubmit()"/></li>
			<li class="btns"><input class="btn btn-primary clearBtn" type="button" value="清空"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>门店</th>
				<th>工程模式</th>
				<th>订单编号</th>
				<th>申请日期</th>
				<th>客户姓名</th>
				<th>客户地址</th>
				<th>项目经理</th>
				<th>项目经理手机号</th>
				<th>设计师</th>
				<th>设计师手机号</th>
				<th>安装模式</th>
				<th>安装项名称</th>
				<th>期望进场时间</th>
				<th>安装说明</th>
				<th>选材清单</th>
				<th>状态</th>
				<th>日志</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>	
			<c:forEach items="${page.list}" var="install" >
			<tr>
				<td>${install.storeName}</td>
				<td>${install.projectModeName}</td>
				<td>${install.orderNumber}</td>
				<td><fmt:formatDate type="both" value="${install.applyIntoCreateDatetime }"/></td>
				<td>${install.customerName }</td>
				<td>${install.communityName}-${install.buildNumber}-${install.buildUnit}-${install.buildRoom}</td>
				<td>${install.managerName }</td>
				<td>${install.managerPhone }</td>
				<td>${install.designerName }</td>
				<td>${install.designerPhone }</td>
				<td>${install.installModeName }</td>
				<td>${install.installItemName }</td>
				<td><fmt:formatDate type="date" value="${install.applyIntoDate }"/></td>
				<td>
					<a href="#" onclick="installExplain('${install.id}')">
							查看
					</a>
				</td>
				<td>
					<a href="${ctx}/bizmaterialchoicebill/bizMaterialsChoiceBill/order_materials_choice_bill_detail?orderId=${install.orderId}">
							查看
					</a>
				</td>
				<td>${install.installStatusName}</td>
				<td>
				
					<a href="${ctx}/enginInstallNew/enginInstallNew/installLogOperation?installId=${install.id}&orderID=${install.id}">操作日志</a> 
					
					<a href="${ctx}/enginInstallNew/enginInstallNew/installLogUrge?installId=${install.id}&orderID=${install.id}">催促日志</a> 
				
				</td>
				<td>
					<a href="#" onclick="showSupplierDate(this,'${install.id}','${install.orderId}','${install.sendSupplierId }')">重新转供应商</a>
					<a href="${ctx}/enginInstallNew/enginInstallNew/installDetails?installId=${install.id}&orderID=${install.orderId}">详情</a>
				
				</td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
	
            
    
    
    
    <!-- 下达供应商弹框 安装模式产业 -->
	<div class="Black undis" id="supplierDateBoxNew">
		<div class="tc_center">
			<h2 id="orderAddressNew"></h2>
			<div class="cen_t">
				<div class="cen_tex">
					<span class="span_l">安装项名称：</span>
					<p class="span_r" id="installNameNew"></p>
				</div>
				<div class="cen_tex">
					<span class="span_l">供应商名称：</span>
					<p class="span_r">
						<select id="supplierId" style="width: 200px;">
						</select>
					</p>
				</div>
				<div class="cen_tex">
					<span class="span_l">说明：</span>
					<p class="span_r">
						<textarea id="supplierConfirmRemarksNew" maxlength="50"></textarea>
					</p>
				</div>
				<div class="cen_btn">
					<span class="btn_y" onclick="yesNew()">提交</span>
					<span class="btn_n" onclick="noNew()">取消</span>
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
	<div style="background:rgba(0,0,0,.4);z-index:9;" class="g-mask undis" id ="subReport1">
		<div class="alertMask">
			<div class="maskWrapper">
				<p class="col_3 maskTit">通知</p>
				<div class="font28 col_6 maskContent" id="alertRemarks1"></div>
				<div class="maskBtns clearfix">
					<a class="maskBtn font33 col_f"  onclick="sendMessage1()" href="javascript:;">我知道了</a>
				</div>
			</div>
		</div>
	</div>
	
	
	<div class="pagination">${page}</div>
	<script type="text/javascript">
	
	
		var installIdGlobal;
		var orderIdGlobal;
		var sendSupplierIdGlobal;
	
		 //关闭 
	    function sendMessage(){
	    	$("#subReport").hide();
	    }
	    function sendMessage1(){
	    	//提交表单
	    	$("#searchForm").submit();
	    }

//--------------------------------安装说明start-----------------------------------------------------------
		
		//安装说明
		function installExplain(installId){
	
			$.ajax({
				url: "${ctx }/enginInstallNewDeal/enginInstallNewDeal/installExplain",
	            type: "post",
	            data:{
	            		installId:installId
	            	},
	            success: function(data) {
	            	
	            	if(null!=data && data=="1"){
	            		$("#alertRemarks").text("安装项ID为空");
	            		$("#subReport1").show();
	            	}else if(data=="2"){
	            		$("#alertRemarks").text("该安装项不需要安装说明");
	            		$("#subReport").show();
	            	}else{
	            		$("#alertRemarks").text(data);
	            		$("#subReport").show();
	            	}
	            	
	            }
	    	})
	
	
		}


//--------------------------------安装说明end-----------------------------------------------------------



	    
//--------------------------------下达供应商start-----------------------------------------------------------
	    
		//下达供应商
		function  showSupplierDate (obj,installId,orderId,sendSupplierId){
			installIdGlobal=installId;
			orderIdGlobal= orderId;
			sendSupplierIdGlobal= sendSupplierId;
			
			
			var orderAddress;
	  		var customerName;
	  		var installItemName;
			
			var trObj = $(obj).parent().parent().find("td").each(function(){
	  			if($(this).index()==4){
	  				customerName = $(this).text();
	  			}
	  			if($(this).index()==5){
	  				orderAddress = $(this).text();
	  			}
	  			if($(this).index()==11){
	  				installItemName = $(this).text();
	  			}
	  			
			})
			
			var html = '<option value="">-----请选择供应商------</option>';
			$.ajax({
				url: "${ctx }/enginInstallNewDeal/enginInstallNewDeal/findSupplierList",
	            type: "post",
	            async: false,
	            data:{
	            		installId:installIdGlobal
	            	},
	            success: function(data) {
	            	
	            	if(null!=data && data.length>0){
		            	for(var v=0;v<data.length;v++){
		            		if(data[v].supplierId != null && data[v].supplierId != "undefined"){
			            		html += '<option value="'+data[v].supplierId+'">'+data[v].supplierName+'</option>';
		            		}
		            	}
	            	}
        	    }
	        })
			$("#supplierDateBoxNew").show();
			$("#orderAddressNew").text(orderAddress+"-"+customerName);
			$("#installNameNew").text(installItemName);
			$("#supplierId").html(html);
			
		}
		
		
		
		
		//产业
		function noNew (){
			
			$("#s2id_supplierId .select2-chosen").html("");
			$("#supplierId").val("");
			$("#supplierConfirmRemarksNew").val("");
			$("#supplierDateBoxNew").hide();	
			
		}
		//产业
		function yesNew(){
			var supplierId = $("#supplierId").val();
			var supplierConfirmRemarks = $("#supplierConfirmRemarksNew").val();
			
			
			if(null==supplierId || supplierId==""){
				$("#alertRemarks").text("请选择供应商");
           		$("#subReport").show();
				return false;
			}
			
			if(sendSupplierIdGlobal==supplierId){
				$("#alertRemarks").text("您选择的供应商与现在的供应商一样，请选择其他的供应商。");
           		$("#subReport").show();
				return false;
			}
			
			$.ajax({
				url: "${ctx }/enginInstallNewDeal/enginInstallNewDeal/updateByStatusSupplierIdAgain",
	            type: "post",
	            data:{
	            		installId:installIdGlobal,
	            		orderId:orderIdGlobal,
	            		supplierId:supplierId,
	            		supplierConfirmRemarks:supplierConfirmRemarks
	            	},
	            success: function(data) {
	            	
	    			
	    			$("#s2id_supplierId .select2-chosen").html("");
	    			$("#supplierId").val("");
	    			$("#supplierConfirmRemarksNew").val("");
	    			$("#supplierDateBoxNew").hide();	
	    			
	            	if(null!=data && data=="0"){
	            		$("#alertRemarks1").text("重新转供应商成功");
	            		$("#subReport1").show();
	            	}else if(data=="1"){
	            		$("#alertRemarks").text("安装项ID为空");
	            		$("#subReport").show();
	            	}else if(data=="2"){
	            		$("#alertRemarks").text("订单ID为空");
	            		$("#subReport").show();
	            	}else if(data=="3"){
	            		$("#alertRemarks").text("供应商为空");
	            		$("#subReport").show();
	            	}else if(data=="4"){
	            		$("#alertRemarks").text("当前登录人未登录");
	            		$("#subReport").show();
	            	}else if(data=="5"){
	            		$("#alertRemarks").text("该安装项不可重新转给供应商");
	            		$("#subReport").show();
	            	}else if(data=="6"){
	            		$("#alertRemarks").text("您选择的供应商与现在的供应商一样，请选择其他的供应商。");
	            		$("#subReport").show();
	            	}else if(data=="7"){
	            		$("#alertRemarks").text("订单安装项更新失败");
	            		$("#subReport").show();
	            	}else if(data=="8"){
	            		$("#alertRemarks").text("保存安装项状态日志失败");
	            		$("#subReport").show();
	            	}else if(data=="9"){
	            		$("#alertRemarks").text("查询最新一次的安装单以及施工单为空");
	            		$("#subReport").show();
	            	}else if(data=="10"){
	            		$("#alertRemarks").text("安装单更新失败");
	            		$("#subReport").show();
	            	}else if(data=="11"){
	            		$("#alertRemarks").text("安装单废除日志保存失败");
	            		$("#subReport").show();
	            	}else if(data=="12"){
	            		$("#alertRemarks").text("施工单更新失败");
	            		$("#subReport").show();
	            	}else if(data=="13"){
	            		$("#alertRemarks").text("施工单废除日志保存失败");
	            		$("#subReport").show();
	            	}else if(data=="14"){
	            		$("#alertRemarks").text("安装单保存失败");
	            		$("#subReport").show();
	            	}else if(data=="15"){
	            		$("#alertRemarks").text("安装单状态日志保存失败");
	            		$("#subReport").show();
	            	}else if(data=="16"){
	            		$("#alertRemarks").text("重新下达供应商失败");
	            		$("#subReport").show();
	            	}
	            	
	            }
	    	})
			
			
		}
		
//--------------------------------下达供应商end-----------------------------------------------------------
		
	    



	</script>
</body>
</html>