<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>采购单管理</title>
	<meta name="decorator" content="default"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/modules/popUp/css/base.css"/> 
 	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/calcRootFontSize.js"></script>
 	
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
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
		<li class="active"><a href="${ctx}/purchase/bizPurchaseMainPanel/panelList">开关面板申请列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="bizPurchaseMainPanel" action="${ctx}/purchase/bizPurchaseMainPanel/panelList" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>门店：</label>
				<form:select path="storeId" class="input-medium needClear">
					<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>工程模式：</label>
				<c:if test="${!empty projectModeEnable}">
					<form:select path="projectMode" class="input-medium" disabled="true" >
						<form:option value="" label=""/>
						<form:options items="${fns:getDictList('project_mode')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
				<c:if test="${empty projectModeEnable}">
					<form:select path="projectMode" class="input-medium needClear">
						<form:option value="" label=""/>
						<form:options items="${fns:getDictList('project_mode')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
			</li>
			
		 	<li><label>订单编号：</label>
				<form:input path="orderNumber" htmlEscape="false" maxlength="64" class="input-medium needClear"/>
			</li>
			<li><label>采购单编码：</label>
				<form:input path="purchaseCode" htmlEscape="false" maxlength="64" class="input-medium needClear"/>
			</li>
			<li><label>项目经理：</label>
				<form:input path="itemManager" htmlEscape="false" maxlength="64" class="input-medium needClear"/>
			</li>
			<li><label>客户姓名：</label>
				<form:input path="customerName" htmlEscape="false" maxlength="64" class="input-medium needClear"/>
			</li>
			<li><label>订单是否作废：</label>
				<form:select path="isScrap"   class="input-medium needClear" >
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('dict_iscountsquare')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>	
			</li>
			<li><label>期望送货日期：</label>
				<input name="beginApplyReceiveTime" type="text" id="" readonly="readonly" maxlength="20" class="input-medium Wdate needClear"
					value="<fmt:formatDate value="${bizPurchaseMainPanel.beginApplyReceiveTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'beginApplyTime\')}',isShowClear:false});"/> - 
				<input name="endApplyReceiveTime" type="text" id="" readonly="readonly" maxlength="20" class="input-medium Wdate needClear"
					value="<fmt:formatDate value="${bizPurchaseMainPanel.endApplyReceiveTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'beginApplyTime\')}',isShowClear:false});"/>
			</li>
			<li><label>提交申请时间 ：</label>
				<input name="beginApplyTime" type="text" id="beginApplyTime" readonly="readonly" maxlength="20" class="input-medium Wdate needClear"
					value="<fmt:formatDate value="${bizPurchaseMainPanel.beginApplyTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'endApplyTime\')}',isShowClear:false});"/> - 
				<input name="endApplyTime" type="text" id="endApplyTime" readonly="readonly" maxlength="20" class="input-medium Wdate needClear"
					value="<fmt:formatDate value="${bizPurchaseMainPanel.endApplyTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'beginApplyTime\')}',isShowClear:false});"/>
			</li>
			<li><label>状态：</label>
				<form:select path="status" class="input-medium needClear">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('purchase_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
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
				<th>提交申请时间 </th>
				<th>订单单号 </th>
				<th>采购单号 </th>
				<th>客户信息</th>
				<th>订单是否作废</th>
				<th>申请人（项目经理）</th>
				<th>申请到货日期</th>
				<th>主材类型</th>
				<th>状态 </th>
				<th>第N次申请 </th>
				<th>累计申请数量 </th>
				<th>标配数量 </th>
				<th>合同面积 </th>
				<shiro:hasPermission name="purchase:bizPurchaseMainPanel:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="bizPurchaseMainPanel">
			<tr>
				<td>
					${fns:getStoreLabel(bizPurchaseMainPanel.storeId, '')}
				</td>
				<td>
					${fns:getDictLabel(bizPurchaseMainPanel.projectMode, 'project_mode', '')}
				</td>
				<td>
					<fmt:formatDate value="${bizPurchaseMainPanel.applyTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
					<td>
					${bizPurchaseMainPanel.orderNumber}
				</td>
				<td>
					${bizPurchaseMainPanel.purchaseCode}
				</td>
				<td>
					${bizPurchaseMainPanel.communityName }-${bizPurchaseMainPanel.buildNumber }-${bizPurchaseMainPanel.buildUnit }-${bizPurchaseMainPanel.buildRoom }-${bizPurchaseMainPanel.customerName }
				</td>
				<td>
					${fns:getDictLabel(bizPurchaseMainPanel.isScrap, 'dict_iscountsquare', '')}
				</td>
				<td>
					${bizPurchaseMainPanel.applyName }</br>${bizPurchaseMainPanel.applyEmployeePhone }
				</td>
				<td>
					<fmt:formatDate value="${bizPurchaseMainPanel.applyReceiveTime}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					${fns:getDictLabel(bizPurchaseMainPanel.purchaseType, 'main_material_type', '')}
				</td>
				<td>
					${fns:getDictLabel(bizPurchaseMainPanel.status, 'purchase_status', '')}
				</td>
				<td>
					${bizPurchaseMainPanel.purchaseApplyIndex}
				</td>
				<td>
					${bizPurchaseMainPanel.purchaseCountTotal}
				</td>
				<td>
					${bizPurchaseMainPanel.standardCountTotal}
				</td>
				<td>
					${bizPurchaseMainPanel.contractArea}
				</td>
				<shiro:hasPermission name="purchase:bizPurchaseMainPanel:edit">
					<td>
	    				<a href="${ctx}/purchase/bizPurchaseMainPanel/mainPanelDetails?id=${bizPurchaseMainPanel.id}">查看明细</a>
<%-- 						<c:if test="${bizPurchaseMainPanel.status eq 10 && bizPurchaseMainPanel.isScrap eq 0}"> --%>
						<c:if test="${bizPurchaseMainPanel.status eq 10 }">
							<a  onclick="transferToSupplier('${bizPurchaseMainPanel.id}','${bizPurchaseMainPanel.orderId}','${bizPurchaseMainPanel.projectMode}')">
								转给供应商
							</a>
							<a href="#" onclick="abandonedBecause('${bizPurchaseMainPanel.id}','${bizPurchaseMainPanel.purchaseCode}','${bizPurchaseMainPanel.communityName }','${bizPurchaseMainPanel.buildNumber }','${bizPurchaseMainPanel.buildUnit }','${bizPurchaseMainPanel.buildRoom }','${bizPurchaseMainPanel.customerName }')">
								废弃
							</a>
						</c:if>
					</td>
				</shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	
	
	
	
	<div class="Black undis" id="onCreateTaskpackage">
		<div class="tc_center">
			<h2 id="orderAddress"></h2>
            <div class="cen_t">
            	<div class="cen_tex">
            		<span class="span_l"> 采购单编号：</span>
                    <p class="span_r" id="purchaseCode2"></p>
                </div>
                <div class="cen_tex">
					<span class="span_l">废弃原因：</span>
					<p class="span_r">
						<textarea id="urgeReply" maxlength="10" name="urgeReply"></textarea>
					</p>
				</div>
                <div class="cen_btn">
                	<span class="btn_y" onclick="yes()"> 提交 </span>
                    <span class="btn_n" onclick="no()"> 取消 </span>
                </div>
            </div>
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
				<div class="font28 col_6 maskContent">采购单废弃成功</div>
				<div class="maskBtns clearfix">
					<a class="maskBtn font33 col_f"  onclick="sendMessage1()" href="javascript:;">我知道了</a>
				</div>
			</div>
		</div>
	</div>
	<div style="background:rgba(0,0,0,.4);z-index:9;" class="g-mask undis" id ="subReport2">
		<div class="alertMask">
			<div class="maskWrapper">
				<p class="col_3 maskTit">通知</p>
				<div class="font28 col_6 maskContent">项目经理中期结算单已生成，不允许再转给供应商，系统会自动作废此开关面板的采购单。</div>
				<div class="maskBtns clearfix">
					<a class="maskBtn font33 col_f"  onclick="sendMessage2()" href="javascript:;">我知道了</a>
				</div>
			</div>
		</div>
	</div>
	
	
	
	<div class="pagination">${page}</div>
	<script type="text/javascript">
	
		var purchaseIdGlobal;
		var statusGlobal;
		 //关闭 
	    function sendMessage(){
	    	$("#subReport").hide();
	    }
	    function sendMessage1(){
	    	//提交表单
	    	$("#searchForm").submit();
	    }
	
	
		function abandonedBecause(purchaseId,purchaseCode,communityName,buildNumber,buildUnit,buildRoom,customerName){
			
			purchaseIdGlobal = purchaseId;
			statusGlobal = "21";
			$("#onCreateTaskpackage").show();
			
			$("#orderAddress").text(communityName+"-"+buildNumber+"-"+buildUnit+"-"+buildRoom+"-"+customerName);
			$("#purchaseCode2").text(purchaseCode);
		}
		
		function no (){
			$("#urgeReply").val("");
			$("#onCreateTaskpackage").hide();	
		}
		
		function yes(){
			var urgeReply = $("#urgeReply").val();
			
			if(null==urgeReply || urgeReply==""){
				$("#alertRemarks").text("请输入废弃原因");
           		$("#subReport").show();
				return false;
			}
			
			$.ajax({
				url: "${ctx }/purchase1/purchase1/main_panel_abandoned_because_ajax",
	            type: "post",
	            data:{
	            		purchaseId:purchaseIdGlobal,
	            		status:statusGlobal,
	            		urgeReply:urgeReply
	            	},
	            success: function(data) {
	            	$("#urgeReply").val("");
	    			$("#onCreateTaskpackage").hide();	
	    			
	    			if(null!=data && data=="0"){
	            		$("#subReport1").show();
	            		
	            	}else if(data=="1"){
	            		$("#alertRemarks").text("采购单ID为空");
	            		$("#subReport").show();
	            	}else if(data=="2"){
	            		$("#alertRemarks").text("状态为空");
	            		$("#subReport").show();
	            	}else if(data=="3"){
	            		$("#alertRemarks").text("废弃原因为空");
	            		$("#subReport").show();
	            	}else if(data=="4"){
	            		$("#alertRemarks").text("保存废弃状态失败");
	            		$("#subReport").show();
	            	}
	            }
			})
			
		}
	
		//转给供应商
		function transferToSupplier(id,orderId,projectMode){
			purchaseIdGlobal = id;
			if(projectMode!="4"){
				window.location.href = "${ctx}/purchase1/purchase1/reviseMainPanelStatus?id="+id+"&status=40";
			}else if(projectMode=="4"){
				//如果是准产业，点击【转给供应商】时，判断项目经理的中期结算单是否已存在
				//如果存在，则给出提示“项目经理结算单已生成，不允许再转给供应商，系统会自动作废此开关面板的采购单。”；
				//点击提示确认时，系统将此开关面板的采购单状态更新为“已作废”
				$.ajax({
					url: "${ctx }/purchase1/purchase1/settlement_is_exist_ajax",
		            type: "post",
		            data:{
		            		orderId:orderId
		            	},
		            success: function(data) {
		    			
		    			if(null!=data && data=="0"){
		    				window.location.href = "${ctx}/purchase1/purchase1/reviseMainPanelStatus?id="+id+"&status=40";
		            	}else if(data=="1"){
		            		$("#alertRemarks").text("订单ID为空");
		            		$("#subReport").show();
		            	}else if(data=="2"){
		            		$("#subReport2").show();
		            	}
		            }
				})
			}
		}
		
		function sendMessage2(){
			
			var status = "21";
			var urgeReply = "系统会自动作废";
			
			$.ajax({
				url: "${ctx }/purchase1/purchase1/main_panel_abandoned_because_ajax",
	            type: "post",
	            data:{
	            		purchaseId:purchaseIdGlobal,
	            		status:status,
	            		urgeReply:urgeReply
	            	},
	            success: function(data) {
	    			
	    			if(null!=data && data=="0"){
	    				$("#searchForm").submit();
	            	}else if(data=="1"){
	            		$("#alertRemarks").text("采购单ID为空");
	            		$("#subReport").show();
	            	}else if(data=="2"){
	            		$("#alertRemarks").text("状态为空");
	            		$("#subReport").show();
	            	}else if(data=="3"){
	            		$("#alertRemarks").text("废弃原因为空");
	            		$("#subReport").show();
	            	}else if(data=="4"){
	            		$("#alertRemarks").text("保存废弃状态失败");
	            		$("#subReport").show();
	            	}
	            }
			})
		}
		
	</script>
</body>
</html>