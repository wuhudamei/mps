<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>安装项查询</title>
	<meta name="decorator" content="default"/>
	
 	<link rel="stylesheet" type="text/css" href="${ctxStatic}/modules/popUp/css/base.css"/> 
 	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/calcRootFontSize.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/jquery.form.js"></script>		
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
		
		
		//清空查询条件
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
		
		
		 var format = function(time, format){
		        var t = new Date(time);
		        var tf = function(i){return (i < 10 ? '0' : '') + i};
		        return format.replace(/yyyy|MM|dd|HH|mm|ss/g, function(a){
		            switch(a){
		                case 'yyyy':
		                    return tf(t.getFullYear());
		                    break;
		                case 'MM':
		                    return tf(t.getMonth() + 1);
		                    break;
		                case 'mm':
		                    return tf(t.getMinutes());
		                    break;
		                case 'dd':
		                    return tf(t.getDate());
		                    break;
		                case 'HH':
		                    return tf(t.getHours());
		                    break;
		                case 'ss':
		                    return tf(t.getSeconds());
		                    break;
		            }
		        })
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
<c:set var="user" value="${fns:getUser()}"></c:set>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/enginInstallNew/enginInstallNew/preList">主材安装申请-待办</a></li>
	
	</ul>
	<form:form id="searchForm" modelAttribute=	"enginInstallNew" action="${ctx}/enginInstallNew/enginInstallNew/list" method="post" class="breadcrumb form-search">
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
			<li><label>项目经理：</label>
				<form:input path="managerName" htmlEscape="false" maxlength="64" class="input-medium needClear"/>
			</li>
			<li><label>回复状态：</label>
				<form:select path="isUrgeReply" class="input-medium needClear" >
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('is_urge_reply')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>安装模式：</label>
				<form:select path="installMode" class="input-medium needClear" >
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('install_mode')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label style="width:140px;">安装项名称（停用）：</label>
				<form:select path="projectInstallItemIdStop" class="input-medium needClear">
				</form:select>
			</li>
			<li><label>安装项名称：</label>
				<form:select path="projectInstallItemId" class="input-medium needClear">
				</form:select>
			</li>
			<li><label>客户姓名：</label>
				<form:input path="customerName" htmlEscape="false" maxlength="16" class="input-medium needClear"/>
			</li>
		 	 
			<li><label style="width: 120px;">项目经理申请日期：</label>
				<input name="beginApplyIntoCreateDatetime" type="text" id="beginApplyIntoCreateDatetime" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${enginInstallNew.beginApplyIntoCreateDatetime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'endApplyIntoCreateDatetime\')}',isShowClear:false});"/> &nbsp;至&nbsp;
					
				<input name="endApplyIntoCreateDatetime" type="text" id="endApplyIntoCreateDatetime" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${enginInstallNew.endApplyIntoCreateDatetime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'beginApplyIntoCreateDatetime\')}',isShowClear:false});"/>
			</li>
			<li><label>期望进场日期：</label>
				<input name="beginApplyIntoDate" type="text" readonly="readonly" id="beginApplyIntoDate" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${enginInstallNew.beginApplyIntoDate}" pattern="yyyy-MM-dd "/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd ',maxDate:'#F{$dp.$D(\'endApplyIntoDate\')}',isShowClear:false});"/> &nbsp;至&nbsp;
					
				<input name="endApplyIntoDate" type="text" readonly="readonly" id="endApplyIntoDate" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${enginInstallNew.endApplyIntoDate}" pattern="yyyy-MM-dd "/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd ',minDate:'#F{$dp.$D(\'beginApplyIntoDate\')}',isShowClear:false});"/>
			</li>
			<li style="width: 40%"><label>申请单状态：</label>
					<input type="checkbox" name="installStatus"  value="2" <c:if test="${fns:checkIsExits(enginInstallNew.installStatus,'2')}"> checked="checked"</c:if>/>已申请&nbsp;
					<input type="checkbox" name="installStatus"  value="6" <c:if test="${fns:checkIsExits(enginInstallNew.installStatus,'6')}"> checked="checked"</c:if>/>驳回后申请&nbsp;
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="btns"><input class="btn btn-primary clearBtn" type="button" value="清空" onclick="clearCondition()"/></li>
			<li style="width: 100%">
				<span style="font-size:20px;color:#333;">项目经理已申请安装：<span style="color:red;">${applyCount }</span> 条      驳回后申请安装：<span style="color:red;">${reApplyCount }</span> 条</span>
			</li>
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
				<th>设计师姓名</th>
				<th>设计师手机号</th>
				<th>安装模式</th>
				<th>安装项名称</th>
				<th>期望进场时间</th>
				<th>项目经理催促次数</th>
				<th>安装说明</th>
				<th>选材清单</th>
				<th>状态</th>
				<th>查看二期款</th>
				<th>复尺情况</th>
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
				<td>${install.designerName}</td>
				<td>${install.designerPhone }</td>
				<td>${install.installModeName }</td>
				<td>${install.installItemName }</td>
				<td><fmt:formatDate type="date" value="${install.applyIntoDate }"/></td>
				<td>${install.urgeCount }</td>
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
					<a href="#" onclick="showSecondPayment('${install.orderId}')">二期款</a>
				</td>
				<td>
					<a href="#" onclick="showCheckSize('${install.id}','${install.orderId}')">复尺情况</a>
				</td>
				<td>
					<a href="${ctx}/enginInstallNew/enginInstallNew/installLogOperation?installId=${install.id}&orderID=${install.id}">操作日志</a> 
					
					<a href="${ctx}/enginInstallNew/enginInstallNew/installLogUrge?installId=${install.id}&orderID=${install.id}">催促日志</a> 
				</td>
				<td>
				
					<a href="#" onclick="showSupplierDate(this,'${install.id}','${install.orderId}','${install.supplierConfirmIntoDate }','${install.installMode }')">下达给供应商</a>
					
					<a href="#" onclick="showRejected(this,'${install.id}','${install.orderId}')">驳回</a>
					
					<a href="#" onclick="showUrgeReply(this,'${install.id}','${install.orderId}')">回复</a>
					
					<a href="${ctx}/enginInstallNew/enginInstallNew/installDetails?installId=${install.id}&orderID=${install.orderId}">详情</a>
					
				</td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
	
	<!-- 下达供应商弹框  安装模式传统 -->
	<div class="Black undis" id="supplierDateBox">
		<div class="tc_center">
			<h2 id="orderAddress"></h2>
			<div class="cen_t">
				<div class="cen_tex">
					<span class="span_l">安装项名称：</span>
					<p class="span_r" id="installName"></p>
				</div>
				<div class="cen_tex">
					<span class="span_l">供应商确认日期：</span>
					<p class="span_r"><input id="supplierConfirmIntoDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate content"
				onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/></p>
				</div>
				<div class="cen_tex">
					<span class="span_l">说明：</span>
					<p class="span_r">
						<textarea id="supplierConfirmRemarks" maxlength="50"></textarea>
					</p>
				</div>
				<div class="cen_btn">
					<span class="btn_y" onclick="yes()">提交</span>
					<span class="btn_n" onclick="no()">取消</span>
				</div>
			</div>
			<div class="tc_close"></div>
		</div>
	</div> 
	
	
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



	<!-- 回复弹框 -->
	<div class="Black undis" id="urgeReplyBox">
		<form id="urgeForm">
			<div class="tc_center">
				<h2 id="orderAddress2"></h2>
				<input hidden="hidden" id="installPlanId" name="installPlanId"/>
	            <div class="cen_t">
	            	<div class="cen_tex">
	            		<span class="span_l"> 安装项名称：</span>
	                    <p class="span_r" id="installName2"></p>
	                </div>
	                <div class="cen_tex">
						<span class="span_l">内容：</span>
						<p class="span_r">
							<textarea id="urgeReply" maxlength="50" name="urgeReply"></textarea>
						</p>
					</div>
	                <div class="cen_btn">
	                	<span class="btn_y" onclick="yes2()"> 提交 </span>
	                    <span class="btn_n" onclick="no2()"> 取消 </span>
	                </div>
	            </div>
	        </div>
		</form>
    </div>
    
    
     <!-- 驳回弹框 -->
	<div class="Black undis" id="rejectedBox">
		<div class="tc_center">
			<h2 id="orderAddress3"></h2>
			<div class="cen_t">
				<div class="cen_tex">
					<span class="span_l">安装项名称：</span>
					<p class="span_r" id="installName3"></p>
				</div>
				<div class="cen_tex">
					<span class="span_l">驳回类型：</span>
					<p class="span_r">
						<select id="rejectId" style="width: 200px;">
							<option value="">-----请选择驳回类型------</option>
							<c:forEach items="${fns:getDictList('install_reject_reason_type')}" var="aa">
								<option value="${aa.value }">${aa.label}</option>
							</c:forEach>
						</select>
					</p>
				</div>
				<div class="cen_tex">
					<span class="span_l">备注：</span>
					<p class="span_r">
						<textarea id="rejectedRemarks" maxlength="100"></textarea>
					</p>
				</div>
				<div class="cen_btn">
					<span class="btn_y" onclick="yes3()">提交</span>
					<span class="btn_n" onclick="no3()">取消</span>
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
	<div style="background:rgba(0,0,0,.4);z-index:9;" class="g-mask undis" id ="subReport2">
		<div class="alertMask">
			<div class="maskWrapper">
				<p class="col_3 maskTit" id="alertRemarks4"></p>
				<div class="font28 col_6 maskContent">
					<p id="alertRemarks2"></p><br>
					<p id="alertRemarks3"></p>
				</div>
				<div class="maskBtns clearfix">
					<a class="maskBtn font33 col_f"  onclick="sendMessage2()" href="javascript:;">我知道了</a>
				</div>
			</div>
		</div>
	</div>
	
	
	
	<div class="pagination">${page}</div>
	<script type="text/javascript">
	
	
		var installIdGlobal;
		var orderIdGlobal;
	
		 //关闭 
	    function sendMessage(){
	    	$("#subReport").hide();
	    }
	    function sendMessage1(){
	    	//提交表单
	    	$("#searchForm").submit();
	    }
		 //关闭 
	    function sendMessage2(){
	    	$("#subReport2").hide();
	    }
		 
	    
	    
	    
	    
//--------------------------------下达供应商start-----------------------------------------------------------
	    
		//下达供应商
		function  showSupplierDate (obj,installId,orderId,supplierConfirmIntoDate,installMode){
			installIdGlobal=installId;
			orderIdGlobal= orderId;
			
			
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
			
			
			//产业
			if(installMode == '1'){
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
				
			}else{
				//传统
				$("#supplierDateBox").show();
				$("#orderAddress").text(orderAddress+"-"+customerName);
				$("#installName").text(installItemName);
				if(null!=supplierConfirmIntoDate && supplierConfirmIntoDate!=""){
					$("#supplierConfirmIntoDate").val(format(supplierConfirmIntoDate,'yyyy-MM-dd'));
				}
			}
			
			
			
		}
		
		//传统
		function no (){
			$("#supplierConfirmIntoDate").val("");
			$("#supplierConfirmRemarks").val("");
			$("#supplierDateBox").hide();	
		}
		//传统
		function yes(){
			var supplierConfirmIntoDate = $("#supplierConfirmIntoDate").val();
			var supplierConfirmRemarks = $("#supplierConfirmRemarks").val();
			
			
			if(null==supplierConfirmIntoDate || supplierConfirmIntoDate==""){
				$("#alertRemarks").text("请选择供应商确认日期");
           		$("#subReport").show();
				return false;
			}
			
			$.ajax({
				url: "${ctx }/enginInstallNewDeal/enginInstallNewDeal/updateByStatus",
	            type: "post",
	            data:{
	            		installId:installIdGlobal,
	            		orderId:orderIdGlobal,
	            		supplierConfirmIntoDate:supplierConfirmIntoDate,
	            		supplierConfirmRemarks:supplierConfirmRemarks
	            	},
	            success: function(data) {
	            	
	            	$("#supplierConfirmIntoDate").val("");
	    			$("#supplierConfirmRemarks").val("");
	    			$("#supplierDateBox").hide();	
	    			
	            	if(null!=data && data=="0"){
	            		$("#alertRemarks1").text("下达供应商成功");
	            		$("#subReport1").show();
	            	}else if(data=="1"){
	            		$("#alertRemarks").text("安装项ID为空");
	            		$("#subReport").show();
	            	}else if(data=="2"){
	            		$("#alertRemarks").text("订单ID为空");
	            		$("#subReport").show();
	            	}else if(data=="3"){
	            		$("#alertRemarks").text("供应商确认日期为空");
	            		$("#subReport").show();
	            	}else if(data=="4"){
	            		$("#alertRemarks").text("当前登录人未登录");
	            		$("#subReport").show();
	            	}else if(data=="5"){
	            		$("#alertRemarks").text("该安装项已转给供应商");
	            		$("#subReport").show();
	            	}else if(data=="6"){
	            		$("#alertRemarks").text("订单安装项更新失败");
	            		$("#subReport").show();
	            	}else if(data=="7"){
	            		$("#alertRemarks").text("保存状态日志失败");
	            		$("#subReport").show();
	            	}else if(data=="8"){
	            		$("#alertRemarks").text("短信保存失败");
	            		$("#subReport").show();
	            	}else if(data=="9"){
	            		$("#alertRemarks").text("栈内消息保存失败");
	            		$("#subReport").show();
	            	}
	            	
	            }
	    	})
			
			
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
			
			$.ajax({
				url: "${ctx }/enginInstallNewDeal/enginInstallNewDeal/updateByStatusSupplierId",
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
	            		$("#alertRemarks1").text("下达供应商成功");
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
	            		$("#alertRemarks").text("该安装项已转给供应商");
	            		$("#subReport").show();
	            	}else if(data=="6"){
	            		$("#alertRemarks").text("供应商信息为空");
	            		$("#subReport").show();
	            	}else if(data=="7"){
	            		$("#alertRemarks").text("订单安装项更新失败");
	            		$("#subReport").show();
	            	}else if(data=="8"){
	            		$("#alertRemarks").text("保存安装项状态日志失败");
	            		$("#subReport").show();
	            	}else if(data=="9"){
	            		$("#alertRemarks").text("安装单保存失败");
	            		$("#subReport").show();
	            	}else if(data=="10"){
	            		$("#alertRemarks").text("安装单状态日志保存失败");
	            		$("#subReport").show();
	            	}else if(data=="11"){
	            		$("#alertRemarks").text("发送短信给项目经理保存失败");
	            		$("#subReport").show();
	            	}else if(data=="12"){
	            		$("#alertRemarks").text("发送短信给供应商保存失败");
	            		$("#subReport").show();
	            	}
	            	
	            }
	    	})
			
			
		}
		
//--------------------------------下达供应商end-----------------------------------------------------------
		
	    
//--------------------------------驳回start-----------------------------------------------------------
	    
		//驳回
		function  showRejected (obj,installId,orderId){
			installIdGlobal=installId;
			orderIdGlobal= orderId;
			
			
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
			
			
			$("#rejectedBox").show();
			$("#orderAddress3").text(orderAddress+"-"+customerName);
			$("#installName3").text(installItemName);
			
			
		}
		function no3(){
			$("#rejectId").val("");
			$("#rejectedRemarks").val("");
			$("#rejectedBox").hide();	
		}
		
		function yes3(){
			var rejectId = $("#rejectId").val();
			var rejectedRemarks = $("#rejectedRemarks").val();
			
			
			if(null==rejectId || rejectId==""){
				$("#alertRemarks").text("请选择驳回类型");
           		$("#subReport").show();
				return false;
			}
			if(rejectId=="5"){
				if(null==rejectedRemarks || rejectedRemarks==""){
					$("#alertRemarks").text("驳回类型选择【其他】时，必须填写备注内容！");
	           		$("#subReport").show();
					return false;
				}
			}
			
			$.ajax({
				url: "${ctx }/enginInstallNewDeal/enginInstallNewDeal/updateRejected",
	            type: "post",
	            data:{
	            		installId:installIdGlobal,
	            		orderId:orderIdGlobal,
	            		rejectId:rejectId,
	            		rejectedRemarks:rejectedRemarks
	            	},
	            success: function(data) {
	            	
	            	$("#rejectId").val("");
	    			$("#rejectedRemarks").val("");
	    			$("#rejectedBox").hide();	
	    			
	            	if(null!=data && data=="0"){
	            		$("#alertRemarks1").text("驳回成功");
	            		$("#subReport1").show();
	            	}else if(data=="1"){
	            		$("#alertRemarks").text("安装项ID为空");
	            		$("#subReport").show();
	            	}else if(data=="2"){
	            		$("#alertRemarks").text("订单ID为空");
	            		$("#subReport").show();
	            	}else if(data=="3"){
	            		$("#alertRemarks").text("驳回类型为空");
	            		$("#subReport").show();
	            	}else if(data=="4"){
	            		$("#alertRemarks").text("当驳回类型为其他时，驳回备注为空");
	            		$("#subReport").show();
	            	}else if(data=="5"){
	            		$("#alertRemarks").text("当前登录人未登录");
	            		$("#subReport").show();
	            	}else if(data=="6"){
	            		$("#alertRemarks").text("该安装项已驳回");
	            		$("#subReport").show();
	            	}else if(data=="7"){
	            		$("#alertRemarks").text("订单安装项更新失败");
	            		$("#subReport").show();
	            	}else if(data=="8"){
	            		$("#alertRemarks").text("保存状态日志失败");
	            		$("#subReport").show();
	            	}
	            	
	            }
	    	})
			
			
		}
		
//--------------------------------驳回end-----------------------------------------------------------
		
		
		
		
		
//--------------------------------回复start-----------------------------------------------------------
		
		//回复
		function  showUrgeReply (obj,installId,orderId){
			installIdGlobal=installId;
			orderIdGlobal= orderId;
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
			$("#urgeReplyBox").show();
			$("#orderAddress2").text(orderAddress+"-"+customerName);
			$("#installName2").text(installItemName);
			$("#installPlanId").val(installId);
		}
		
		function no2(){
			$("#urgeReply").val("");
			$("#urgeReplyBox").hide();	
		}
		function yes2(){
			var urgeReply = $("#urgeReply").val();
			if(null==urgeReply || urgeReply==""){
				$("#alertRemarks").text("请输入回复内容");
           		$("#subReport").show();
				return false;
			}
			var options ={
					url: "${ctx }/enginInstallNewDeal/enginInstallNewDeal/save_install_reply",
					type: "post",
					success:function(data){
						
						$("#urgeReply").val("");
		    			$("#urgeReplyBox").hide();	
						if(null!=data && data=="0"){
							$("#alertRemarks").text("材料部回复成功");
		        			$("#subReport").show();
						}else if(data=="1"){
							$("#alertRemarks").text("回复安装项ID为空");
		        			$("#subReport").show();
						}else if(data=="2"){
							$("#alertRemarks").text("材料员未登录");
		        			$("#subReport").show();
						}else if(data=="3"){
							$("#alertRemarks").text("您刚刚提交过安装申请回复，请耐心等待五分钟后再次操作");
		        			$("#subReport").show();
						}else if(data=="4"){
							$("#alertRemarks").text("安装申请回复内容为空");
		        			$("#subReport").show();
						}else if(data=="5"){
							$("#alertRemarks").text("安装申请回复保存失败");
		        			$("#subReport").show();
						}else if(data=="6"){
							$("#alertRemarks").text("安装申请回复图片保存失败");
		            		$("#subReport").show();
						}
					}
			}
			$("#urgeForm").ajaxSubmit(options);
		}
		
//--------------------------------回复end-----------------------------------------------------------


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


//--------------------------------二期款start-----------------------------------------------------------
		
		//二期款
		function showSecondPayment(orderId){
	
			$.ajax({
				url: "${ctx }/enginInstallNewDeal/enginInstallNewDeal/secondPayment",
	            type: "post",
	            data:{
	            		orderId:orderId
	            	},
	            success: function(data) {
	            	
	            	if(null!=data && data=="1"){
	            		$("#alertRemarks").text("订单ID为空");
	            		$("#subReport").show();
	            	}else if(data=="2"){
	            		$("#alertRemarks").text("暂无二期款");
	            		$("#subReport").show();
	            	}else{
	            		$("#alertRemarks2").text(data.substring(0,data.indexOf("=")));
	            		$("#alertRemarks3").text(data.substring(data.indexOf("=")+1));
	            		$("#alertRemarks4").text("二期款");
	            		$("#subReport2").show();
	            	}
	            	
	            }
	    	})
	
	
		}


//--------------------------------二期款end-----------------------------------------------------------


//--------------------------------复尺情况start-----------------------------------------------------------
		
		//复尺情况
		function showCheckSize(installId,orderId){
	
			$.ajax({
				url: "${ctx }/enginInstallNewDeal/enginInstallNewDeal/checkSize",
	            type: "post",
	            data:{
	            		installId:installId,
	            		orderId:orderId
	            	},
	            success: function(data) {
	            	
	            	if(null!=data && data=="1"){
	            		$("#alertRemarks").text("安装项ID为空");
	            		$("#subReport").show();
	            	}else if(data=="2"){
	            		$("#alertRemarks").text("订单ID为空");
	            		$("#subReport").show();
	            	}else if(data=="3"){
	            		$("#alertRemarks").text("该安装项不需要复尺");
	            		$("#subReport").show();
	            	}else if(data=="4"){
	            		$("#alertRemarks").text("该安装项暂无复尺");
	            		$("#subReport").show();
	            	}else{
	            		$("#alertRemarks2").text(data.substring(0,data.indexOf("=")));
	            		$("#alertRemarks3").text(data.substring(data.indexOf("=")+1));
	            		$("#alertRemarks4").text("复尺情况");
	            		$("#subReport2").show();
	            	}
	            	
	            	
	            }
	    	})
	
	
		}


//--------------------------------复尺情况end-----------------------------------------------------------





	</script>
</body>
</html>