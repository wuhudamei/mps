<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>安装项查询</title>
	<meta name="decorator" content="default"/>
	<%-- <link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Worker/css/globalUtil.css"/> --%>
	<%-- <script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/history.js"></script> --%>
	
 	<link rel="stylesheet" type="text/css" href="${ctxStatic}/modules/popUp/css/base.css"/> 
 	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/calcRootFontSize.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/jquery.form.js"></script>		
	<script type="text/javascript">
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
		function findInstallItemByStoreIdAndProjectMode(){
			 var html2='<option value=""></option>';
			var storeId =$("#storeId option:selected").val();
			
			var projectModeId=$("#projectModeId option:selected").val();
			
			if(storeId!=""&&projectModeId!=""){
				$.ajax({
					url:"${ctx}/bizengininstall/bizEnginInstall/findInstallItemByStoreIdAndProjectMode?storeId="+storeId+"&projectModeId="+projectModeId,					
					success: function(data){
						
						if(null!=data&&data.length>0){
					
							for (var v = 0; v < data.length; v++) {
								html2 +='<option value="'+data[v].installItemSequence+'">'+data[v].installItemName+'</option>' 
							}
							
							$("#installNameId").html(html2);
						} else {
							html2 += '<option value="" selected="selected">没有发现安装项</option>';
							$("#installNameId").html(html2);
						}
						
					}
					
					
					
					
				})
				
				
				
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

		 window.onload=function(){
			 var html2='<option value=""></option>';
				var storeId =$("#storeId option:selected").val();
				var projectModeId=$("#projectModeId option:selected").val();
				if(storeId!=""&&projectModeId!=""){
					$.ajax({
						url:"${ctx}/bizengininstall/bizEnginInstall/findInstallItemByStoreIdAndProjectMode?storeId="+storeId+"&projectModeId="+projectModeId,					
						success: function(data){
							
							if(null!=data&&data.length>0){
						
								for (var v = 0; v < data.length; v++) {
																		
									if('${installName}' == data[v].installItemSequence){
										$("#s2id_installNameId .select2-chosen").html(data[v].installItemName);
										html2 = html2 + "<option value='"+data[v].installItemSequence+"' selected='selected'>"+data[v].installItemName+"</option>";
									}else{
										html2 = html2 + "<option value='"+data[v].installItemSequence+"'>"+data[v].installItemName+"</option>";
									}
								}
								
								$("#installNameId").html(html2);
							} else {
								//html2 += '<option value="" selected="selected">没有发现安装项</option>';
								$("#installNameId").html(html2);
							}
							
						}
						
						
						
						
					})
					
					
					
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
<c:set var="user" value="${fns:getUser()}"></c:set>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/bizengininstall/bizEnginInstall/preList">安装项列表</a></li>
	
	</ul>
	<form:form id="searchForm" modelAttribute=	"bizEnginInstall" action="${ctx}/bizengininstall/bizEnginInstall/list" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
	<%-- 	<c:if test="${user.office.id ==1 }"> --%>
	<%-- 	<li><label>门店：</label> 
				<form:select path="storeId" class="input-medium needClear" id="storeId" onchange="findInstallItemByStoreIdAndProjectMode()">
		<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
			</li>
		</c:if>
			<c:if test="${ user.office.id !=1 }">	
			<li><label>门店：</label> 
				<form:select path="storeId" class="input-medium needClear" id="storeId" onchange="findInstallItemByStoreIdAndProjectMode()">
					<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
			</li>
				</c:if> --%>
			<%-- <li><label>新老房：</label> 
				<form:select path="houseIsNew" class="input-medium needClear">
					<form:option value="" label="">全部</form:option>
					<form:option value="0" label="">0 - 老房</form:option>
					<form:option value="1" label="">1 - 新房</form:option>
				</form:select>
			</li> --%>
			<li><label>门店：</label> 
			<form:select path="storeId" class="input-medium needClear" id="storeId" onchange="findInstallItemByStoreIdAndProjectMode()">
						<form:option value=""></form:option>
						<c:forEach items="${storeList}" var = "store">
							<form:option value="${store.value }">${store.label }</form:option>
						</c:forEach>
				</form:select>
			</li>
			<li><label class="control-label">工程模式：</label>
		
			
			<c:if test="${user.projectMode ==3}">
			
			<form:select path="projectMode" class="input-medium needClear" onchange="findInstallItemByStoreIdAndProjectMode()" id="projectModeId">
							<form:option value="" label="" />
					<form:options items="${fns:getDictList('project_mode')}"
						itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
			</c:if>
			<c:if test="${user.projectMode !=3}">
		
			<form:select path="projectMode" class="input-medium needClear" id="projectModeId"  onchange="findInstallItemByStoreIdAndProjectMode()">
					
					<form:option label="${fns:getDictLabel(user.projectMode, 'project_mode', '')}"
					value="${user.projectMode}"/>
				</form:select>
			</c:if>
			</li>
			
			
			<li><label>订单编号：</label>
				<form:input path="orderNumber" htmlEscape="false" maxlength="64" class="input-medium needClear"/>
			</li>
			<li><label>项目经理：</label>
				<form:input path="employeeRealName" htmlEscape="false" maxlength="64" class="input-medium needClear"/>
			</li>
			<li><label>客户姓名：</label>
				<form:input path="customerName" htmlEscape="false" maxlength="16" class="input-medium needClear"/>
			</li>
			<li><label>状态：</label>
				<form:select path="installStatus" class="input-medium needClear">
					<form:option value="" label="" />
					<form:option value="2" label="已申请" />
					<form:option value="3" label="已转给供应商" />
					<form:option value="4" label="已验收" />
					<%-- <form:options items="${fns:getDictList('install_status')}" itemLabel="label" itemValue="value" htmlEscape="false" /> --%>
				</form:select>
			</li>
		 	<li><label>安装项名称：</label>
				<select id="installNameId" name="installItemName" style="width: 170px" class="input-medium needClear">
					<%-- <option value="${installName }" selected="selected">${installName }</option> --%>
				</select>
			</li> 
			<li><label>申请日期：</label>
				<input name="installApplyInfoCreateDateBegin" type="text" id="contractBegin" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${bizEnginInstall.installApplyInfoCreateDateBegin}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'contractEnd\')}',isShowClear:false});"/> &nbsp;至&nbsp;
					
				<input name="installApplyInfoCreateDateEnd" type="text" id="contractEnd" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${bizEnginInstall.installApplyInfoCreateDateEnd}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'contractBegin\')}',isShowClear:false});"/>
			</li>
			<li><label>期望进场日期：</label>
				<input name="applyIntoDateBegin" type="text" readonly="readonly" id="actualBegin" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${bizEnginInstall.applyIntoDateBegin}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'actualEnd\')}',isShowClear:false});"/> &nbsp;至&nbsp;
					
				<input name="applyIntoDateEnd" type="text" readonly="readonly" id="actualEnd" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${bizEnginInstall.applyIntoDateEnd}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'actualBegin\')}',isShowClear:false});"/>
			</li>
			<li><label style="width:120px;">催促信息是否回复：</label>
				<form:select path="isUrgeReply" class="input-medium needClear" >
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('is_urge_reply')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
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
				<th>门店编号</th>
				<th>工程模式</th>
				<th>申请日期</th>
				<th>客户信息</th>
				<th>项目经理</th>
				<th>项目经理手机号</th>
				<th>设计师姓名</th>
				<th>设计师手机号</th>
				<th>安装项名称</th>
				<th>期望进场时间</th>
				<th>供应商确认日期</th>
				<th>状态</th>
				<th>选材清单</th>
				<shiro:hasPermission name="bizengininstall:bizEnginInstall:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>	
			<c:forEach items="${page.list}" var="order" varStatus="status">
			<tr>
				<td>${order.storeName}</td>
				<td>${order.projectModeName}</td>
				<td><fmt:formatDate type="both" value="${order.installApplyInfoCreateDate }"/></td>
				<td>${order.communityName}-${order.buildNumber}-${order.buildUnit}-${order.buildRoom}-${order.customerName}</td>
				<td>${order.employeeRealName }</td>
				<td>${order.employeePhone }</td>
				<td>${order.designerName}</td>
				<td>${order.designerPhone }</td>
				<td>${order.installItemName }</td>
				<td><fmt:formatDate type="date" value="${order.installApplyIntoDate }"/></td>
				<td><fmt:formatDate type="date" value="${order.supplierConfirmIntoDate }"/></td>
				<td>${order.installStatusName}</td>
				<td>
					<a href="${ctx}/bizmaterialchoicebill/bizMaterialsChoiceBill/order_materials_choice_bill_detail?orderId=${order.id}">
							查看
					</a>
				</td>
				<td>
					<a href="${ctx}/bizengininstall/bizEnginInstall/selectByInstallID?id=${order.installID}&orderID=${order.id}">详情</a>
					
					<c:if test="${order.installStatus == '2'}">
						<a href="#" onclick="showUrgeReply('${order.installID}','${order.id}','${order.supplierConfirmIntoDate}','${order.communityName}','${order.buildNumber}','${order.buildUnit}','${order.buildRoom}','${order.customerName}','${order.installItemName }')">回复</a>
						<a href="#" onclick="showSupplierDate('${order.installID}','${order.id}','${order.supplierConfirmIntoDate}','${order.communityName}','${order.buildNumber}','${order.buildUnit}','${order.buildRoom}','${order.customerName}','${order.installItemName }')">下达给供应商</a>
					</c:if>
					
					
					<a href="${ctx}/bizengininstall/bizEnginInstall/urgeLog?id=${order.installID}&orderID=${order.id}">日志</a>
				</td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
	
	
	<div class="Black undis" id="onCreateTaskpackage">
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

	<div class="Black undis" id="onCreateTaskpackage2">
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
				<div class="font28 col_6 maskContent">下达供应商成功</div>
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
	
		 //关闭 
	    function sendMessage(){
	    	$("#subReport").hide();
	    }
	    function sendMessage1(){
	    	//提交表单
	    	$("#searchForm").submit();
	    }
		 
		//下达供应商
		function  showSupplierDate (installId,orderId,supplierDate,communityName,buildNumber,buildUnit,buildRoom,customerName,installItemName){
			installIdGlobal=installId;
			orderIdGlobal= orderId;
			$("#onCreateTaskpackage").show();
			
			if(null!=supplierDate && supplierDate!=""){
				
				$("#supplierConfirmIntoDate").val(format(supplierDate,'yyyy-MM-dd'));
			}
			
			$("#orderAddress").text(communityName+"-"+buildNumber+"-"+buildUnit+"-"+buildRoom+"-"+customerName);
			$("#installName").text(installItemName);
			
		}
		function no (){
			$("#supplierConfirmIntoDate").val("");
			$("#supplierConfirmRemarks").val("");
			$("#onCreateTaskpackage").hide();	
		}
		
		function yes(){
			var supplierConfirmIntoDate = $("#supplierConfirmIntoDate").val();
			var supplierConfirmRemarks = $("#supplierConfirmRemarks").val();
			
			
			if(null==supplierConfirmIntoDate || supplierConfirmIntoDate==""){
				$("#alertRemarks").text("请选择供应商确认日期");
           		$("#subReport").show();
				return false;
			}
			
			$.ajax({
				url: "${ctx }/bizengininstall/bizEnginInstall/updateByStatus",
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
	    			$("#onCreateTaskpackage").hide();	
	    			
	            	if(null!=data && data=="0"){
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
	            		$("#alertRemarks").text("当前登录人");
	            		$("#subReport").show();
	            	}else if(data=="5"){
	            		$("#alertRemarks").text("转给供应商失败");
	            		$("#subReport").show();
	            	}else{
	            		$("#alertRemarks").text("保存状态日志失败");
	            		$("#subReport").show();
	            	}
	            	
	            }
	    	})
			
			
		}
		
		
		//回复
		function  showUrgeReply (installId,orderId,supplierDate,communityName,buildNumber,buildUnit,buildRoom,customerName,installItemName){
			installIdGlobal=installId;
			orderIdGlobal= orderId;
			$("#onCreateTaskpackage2").show();
			$("#orderAddress2").text(communityName+"-"+buildNumber+"-"+buildUnit+"-"+buildRoom+"-"+customerName);
			$("#installName2").text(installItemName);
			$("#installPlanId").val(installId);
			
		}
		
		
		function no2(){
			$("#urgeReply").val("");
			$("#onCreateTaskpackage2").hide();	
		}
		
		function yes2(){
			var urgeReply = $("#urgeReply").val();
			
			
			if(null==urgeReply || urgeReply==""){
				$("#alertRemarks").text("请输入回复内容");
           		$("#subReport").show();
				return false;
			}
			
			var options ={
					url: "${ctx }/bizengininstall/bizEnginInstall/save_install_reply",
					type: "post",
					success:function(data){
						
						$("#urgeReply").val("");
		    			$("#onCreateTaskpackage2").hide();	
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
	</script>
</body>
</html>