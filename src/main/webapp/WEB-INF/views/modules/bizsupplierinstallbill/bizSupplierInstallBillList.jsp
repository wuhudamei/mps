<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>供应商安装单表管理</title>
	<meta name="decorator" content="default"/>
	
	<link rel="stylesheet" href="${ctxStatic}/modules/supplierInstallBill/maskA.css">
	<link rel="stylesheet" href="${ctxStatic}/modules/supplierInstallBill/maskB.css">
	<link rel="stylesheet" href="${ctxStatic}/modules/supplierInstallBill/reset.css">
	
	<script type="text/javascript">
		$(document).ready(function() {
			findProjectInstallItemList();
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
		// --全选框被单击---
		function ChkAllClick(sonName, cbAllId){
		    var arrSon = document.getElementsByName(sonName);
		 var cbAll = document.getElementById(cbAllId);
		 var tempState=cbAll.checked;
		 for(i=0;i<arrSon.length;i++) {
		  if(arrSon[i].checked!=tempState)
		           arrSon[i].click();
		 }
		}
		 
		// --子项复选框被单击---
		function ChkSonClick(sonName, cbAllId) {
		 var arrSon = document.getElementsByName(sonName);
		 var cbAll = document.getElementById(cbAllId);
		 for(var i=0; i<arrSon.length; i++) {
		     if(!arrSon[i].checked) {
		     cbAll.checked = false;
		     return;
		     }
		 }
		 cbAll.checked = true;
		}
		
		
		
		
		//根据供应商加载安装项
		function findProjectInstallItemList(){
			
			var supplierId = $("#supplierId").val();
			if (supplierId=="" ||undefined==supplierId || null==supplierId) {
				return;
			}
			$.ajax({
				url:"${ctx}/bizsupplierinstallbill/bizSupplierInstallBill/findProjectInstallItemList",					
				type: "post",
				data:{
	            		supplierId:supplierId
	            	},
				success: function(data){
					var html='<option value=""></option>';
					if(null!=data && data.length>0){
						for (var v = 0; v < data.length; v++) {
							if('${bizSupplierInstallBill.projectInstallItemId}' == data[v].id){
								$("#s2id_projectInstallItemId .select2-chosen").html(data[v].installItemName);
								html = html + "<option value='"+data[v].id+"' selected='selected'>"+data[v].installItemName+"</option>";
							}else{
								html = html + "<option value='"+data[v].id+"'>"+data[v].installItemName+"</option>";
							}
						}
						
						$("#projectInstallItemId").html(html);
					} else {
						$("#projectInstallItemId").html(html);
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
    	.twoBtns .maskBtn:first-child{background: #0780ec;border-radius: .1rem;float: left;}
		.twoBtns .maskBtn:last-child{border: 1px solid #0780ec;box-sizing: border-box;border-radius: .1rem;float: right;background: #fff;}		
	</style>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/bizsupplierinstallbill/bizSupplierInstallBill/preList">供应商安装单表列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="bizSupplierInstallBill" action="${ctx}/bizsupplierinstallbill/bizSupplierInstallBill/list" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
		
			<li><label>门店：</label> 
				<form:select path="storeId" class="input-medium needClear">
					<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>工人组：</label>
				<form:input path="employeeGroupId" htmlEscape="false" maxlength="16" class="input-medium needClear"/>
			</li>
			<li><label>客户姓名：</label>
				<form:input path="customerName" htmlEscape="false" maxlength="16" class="input-medium needClear"/>
			</li>
			<li><label>项目经理：</label>
				<form:input path="itemManagerName" htmlEscape="false" maxlength="16" class="input-medium needClear"/>
			</li>
			<li><label>供应商：</label>
				<form:select path="supplierId" class="input-medium needClear" onclick="findProjectInstallItemList()">
					<form:option value="" label=""/>
					<c:forEach items="${supplierList }" var="supplier">
						<form:option value="${supplier.supplierId }" label="${supplier.supplierName }"/>
					</c:forEach>
				</form:select>
			</li>
			<li><label>安装项：</label>
				<form:select path="projectInstallItemId" class="input-medium needClear">
				</form:select>
			</li>
			<li><label>安装单号：</label>
				<form:input path="installBillCode" htmlEscape="false" maxlength="11" class="input-medium needClear"/>
			</li>
			<li><label>接收时间：</label>
				<input name="beginCreateDate" id="beginCreateDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate needClear"
					value="<fmt:formatDate value="${bizSupplierInstallBill.beginCreateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'endCreateDate\')}',isShowClear:true});"/> 至 
				<input name="endCreateDate" id="endCreateDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate needClear"
					value="<fmt:formatDate value="${bizSupplierInstallBill.endCreateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'beginCreateDate\')}',isShowClear:true});"/>
			</li>
			<li style="width: 100%"><label>安装单状态：</label>
				<input name="chkAll" id="chkAll" title="全部" onclick="ChkAllClick('status','chkAll')" type="checkbox" />全部
			</li>
			<li style="height: 80px">
				<c:forEach items="${fns:getDictList('supplier_install_bill_status')}" var="dict">
					<input type="checkbox" name="status" id="status" value="${dict.value}"  onclick="ChkSonClick('status','chkAll')"  <c:if test="${fns:checkIsExits(bizSupplierInstallBill.status,dict.value)}"> checked="checked"</c:if>/>${dict.label}&nbsp;
				</c:forEach>
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
				<th>材料部派单时间</th>
				<th>安装单号</th>
				<th>小区名称</th>
				<th>客户姓名</th>
				<th>项目经理</th>
				<th>项目经理手机号</th>
				<th>安装项</th>
				<th>材料部说明</th>
				<th>计划开始日期</th>
				<th>计划结束日期</th>
				<th>安装单状态</th>
				<th>工人组</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="bizSupplierInstallBill">
			<tr>
				<td>
					${bizSupplierInstallBill.storeName}
				</td>
				<td>
					<fmt:formatDate value="${bizSupplierInstallBill.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${bizSupplierInstallBill.installBillCode}
				</td>
				<td>
					${bizSupplierInstallBill.communityName}-
					${bizSupplierInstallBill.buildNumber}-
					${bizSupplierInstallBill.buildUnit}-
					${bizSupplierInstallBill.buildRoom}
				</td>
				<td>
					${bizSupplierInstallBill.customerName}
				</td>
				<td>
					${bizSupplierInstallBill.itemManagerName}
				</td>
				<td>
					${bizSupplierInstallBill.itemManagerPhone}
				</td>
				<td>
					${bizSupplierInstallBill.installItemName}
				</td>
				<td>
					<span hidden="hidden">${bizSupplierInstallBill.supplierConfirmRemarks}</span>
					<a href="#" onclick="supplierConfirmRemarks(this)">查看</a>
				</td>
				<td>
					<fmt:formatDate value="${bizSupplierInstallBill.planIntoDate}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					<fmt:formatDate value="${bizSupplierInstallBill.planCompleteDate}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					${bizSupplierInstallBill.statusName}
				</td>
				<td>
					<c:if test="${not empty bizSupplierInstallBill.installConstructBillId}">
						<a  href="#" onclick="findEmployeeGroupMessage('${bizSupplierInstallBill.installConstructBillId}')">${bizSupplierInstallBill.employeeGroupName }</a>
					</c:if>
					<c:if test="${empty bizSupplierInstallBill.installConstructBillId}">
						<a  href="#"></a>
					</c:if>
				</td>
				<td>
					<span hidden="hidden">${bizSupplierInstallBill.detailAddress }</span>
					<p class="supplierConfirmIntoDate" hidden="hidden"><fmt:formatDate value="${bizSupplierInstallBill.supplierConfirmIntoDate}" pattern="yyyy-MM-dd"/></p>
					<p class="supplierConfirmCompleteDate" hidden="hidden"><fmt:formatDate value="${bizSupplierInstallBill.supplierConfirmCompleteDate}" pattern="yyyy-MM-dd"/></p>
					
					<c:if test="${bizSupplierInstallBill.status eq 10}">
	    				<a href="#" onclick="confirmTimeLimit(this,'${bizSupplierInstallBill.installBillId}')">确认工期</a>
	    				<a href="#" onclick="distributionWorkersTeam(this,'${bizSupplierInstallBill.installBillId}','${bizSupplierInstallBill.status}','${bizSupplierInstallBill.employeeGroupId}')">分配工人组</a>
					</c:if>
					<c:if test="${bizSupplierInstallBill.status eq 15 || bizSupplierInstallBill.status eq 20 ||bizSupplierInstallBill.status eq 30 ||bizSupplierInstallBill.status eq 40}">
	    				<a href="#" onclick="distributionWorkersTeam(this,'${bizSupplierInstallBill.installBillId}','${bizSupplierInstallBill.status}','${bizSupplierInstallBill.employeeGroupId}')">分配工人组</a>
					</c:if>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	
	
	<!-- 确认工期   弹框 -->
	<div class="mask maskA font0 undis" id="confirmTimeLimitBox">
		<ul class="maskContent bg_f">
			<li class="font14 mb18 fl" id="boxA1">安装项：橱柜安装</li>
			<li class="font14 mb18 fl" id="boxA2">安装单编号：AZ201707100001</li>
			<li class="font14 mb18 fl" id="boxA3">项目经理：张三丰</li>
			<li class="font14 mb18 fl" id="boxA4">项目经理手机号：13020301050</li>
			<li class="font14 mb18 fl" id="boxA5">小区名称：合顺家园-1-1-203</li>
			<li class="font14 mb18 fl" id="boxA6">客户姓名：张小小</li>
			<p class="font14 mb18" id="boxA7">小区名称：海淀区金沟河路12号院7号楼21号</p>
			<p class="font14 mb18">要求工期：
				<input name="boxA8" id="boxA8"  class="readInput" readonly type="text" >至
				<input name="boxA11" id="boxA9" class="readInput" readonly type="text" >
			</p>
			<p class="font14 mb18">确认工期：
				<input name="boxA10" id="boxA10" type="text" readonly="readonly" maxlength="20" class="dateInput Wdate needClear"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'boxA11\')}',isShowClear:true});"/> 至 
				<input name="boxA11" id="boxA11" type="text" readonly="readonly" maxlength="20" class="dateInput Wdate needClear"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'boxA10\')}',isShowClear:true});"/>
			</p>
			<div class="btnWrapper clearfix">
				<a class="btnBlueBg" href="javascript:;" onclick="confirmTimeLimitBoxYes()">保存</a>
				<a class="btnBlueBorder" href="javascript:;" onclick="confirmTimeLimitBoxNo()">取消</a>
			</div>
		</ul>
	</div>
	
					
					
	<!-- 分配工人组 -->
	<div class="mask maskB font0 undis" id="distributionWorkersTeamBox">
		<ul class="maskContent bg_f">
			<li class="font14 mb18 fl" id="boxB1">安装项：橱柜安装</li>
			<li class="font14 mb18 fl" id="boxB2">安装单编号：AZ201707100001</li>
			<li class="font14 mb18 fl" id="boxB3">项目经理：张三丰</li>
			<li class="font14 mb18 fl" id="boxB4">项目经理手机号：13020301050</li>
			<li class="font14 mb18 fl" id="boxB5">小区名称：合顺家园-1-1-203</li>
			<li class="font14 mb18 fl" id="boxB6">客户姓名：张小小</li>
			<p class="font14 mb18" id="boxB7">小区名称：海淀区金沟河路12号院7号楼21号</p>
			<p class="font14 mb18">要求工期：
				<input class="readInput" readonly type="text" id="boxB8">至
				<input class="readInput" readonly type="text" id="boxB9">
			</p>
			<p class="font14 mb18" id="confirmDate1">确认工期：
				<input name="boxB10" id="boxB10" type="text" readonly="readonly" maxlength="20" class="dateInput Wdate needClear"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'boxB11\')}',isShowClear:true});"/> 至 
				<input name="boxB11" id="boxB11" type="text" readonly="readonly" maxlength="20" class="dateInput Wdate needClear"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'boxB10\')}',isShowClear:true});"/>
			</p>
			<p class="font14 mb18" id="confirmDate2">确认工期：
				<input class="readInput" readonly type="text" id="boxB12">至
				<input class="readInput" readonly type="text" id="boxB13">
			</p>
			<div class="mb18">
				<span class="font14">工人组长姓名：</span>
				<input class="workName" type="text" id="workerName">
				<input hidden="hidden" style="display:none;" type="text" id="employeegroupId">
				<a class="btn mr20" href="javascript:;" onclick="findInstallWorkerList()">查询</a>
				<a class="btn" href="javascript:;" onclick="distributionWorkersTeamBoxYes()">分配工人</a>
			</div>
			<table id="workerList">
				<tr>
				    <th>选择</th>
				    <th>组长姓名</th>
				    <th>手机</th>
				    <th>组内成员数</th>
				    <th>当前未完工任务包数</th>
				    <th>住址</th>
				    <th>距离施工地点（米）</th>
				</tr>
				<tr>
				    <td><input type="radio"></td>
				    <td>张三</td>
				    <td>13911112222</td>
				    <td>2</td>
				    <td>2</td>
				    <td>亮马厂</td>
				    <td>1000</td>
				</tr>
				<tr>
				    <td><input type="radio"></td>
				    <td>张三</td>
				    <td>13911112222</td>
				    <td>2</td>
				    <td>2</td>
				    <td>亮马厂</td>
				    <td>1000</td>
				</tr>
			</table>
			<a class="closeBtn" href="javascript:;" onclick="distributionWorkersTeamBoxNo()">关闭</a>
		</ul>
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
				<p class="col_3 maskTit">通知</p>
				<div class="font28 col_6 maskContent">您确认要提交吗？</div>
				<div class="maskBtns clearfix twoBtns">
					<a class="maskBtn font33 col_fdfcfa" href="javascript:void(0);" onclick="sendMessage2()">
						确定
					</a>
					<a class="maskBtn font33 col_0780ec" href="javascript:void(0);" onclick="cancel()">取消</a>
				</div>
			</div>
		</div>
	</div>
	<div style="background:rgba(0,0,0,.4);z-index:9;" class="g-mask undis" id ="subReport3">
		<div class="alertMask">
			<div class="maskWrapper">
				<p class="col_3 maskTit">通知</p>
				<div class="font28 col_6 maskContent">您确认要提交吗？</div>
				<div class="maskBtns clearfix twoBtns">
					<a class="maskBtn font33 col_fdfcfa" href="javascript:void(0);" onclick="sendMessage3()">
						确定
					</a>
					<a class="maskBtn font33 col_0780ec" href="javascript:void(0);" onclick="cancel3()">取消</a>
				</div>
			</div>
		</div>
	</div>
	<div style="background:rgba(0,0,0,.4);z-index:9;" class="g-mask undis" id ="subReport4">
		<div class="alertMask">
			<div class="maskWrapper">
				<p class="col_3 maskTit">材料部说明</p>
				<div class="font28 col_6 maskContent" id="alertRemarks4"></div>
				<div class="maskBtns clearfix">
					<a class="maskBtn font33 col_f"  onclick="sendMessage4()" href="javascript:;">我知道了</a>
				</div>
			</div>
		</div>
	</div>
	<div style="background:rgba(0,0,0,.4);z-index:9;" class="g-mask undis" id ="subReport5">
		<div class="alertMask">
			<div class="maskWrapper">
				<p class="col_3 maskTit">工人组</p>
				<div class="font28 col_6 maskContent" id="alertRemarks5">
					<p id="workerRemarks1"></p><br>
					<p id="workerRemarks2"></p><br>
					<p id="workerRemarks3"></p><br>
					<p id="workerRemarks4"></p><br>
					<p id="workerRemarks5"></p>
				</div>
				<div class="maskBtns clearfix">
					<a class="maskBtn font33 col_f"  onclick="sendMessage5()" href="javascript:;">我知道了</a>
				</div>
			</div>
		</div>
	</div>
	
	<div class="pagination">${page}</div>
	<script type="text/javascript">
	
		 //关闭 
	    function sendMessage(){
	    	$("#subReport").hide();
	    }
	    function sendMessage1(){
	    	//提交表单
	    	$("#searchForm").submit();
	    }
	    function sendMessage4(){
	    	$("#subReport4").hide();
	    }
	    function sendMessage5(){
	    	$("#subReport5").hide();
	    }
    
//---------------------------------------材料部说明start----------------------------------------------------

		function supplierConfirmRemarks(obj){
			
			var supplierConfirmRemarks = $(obj).parent().find("span").text().trim();
			
			if(null!=supplierConfirmRemarks && supplierConfirmRemarks!="" && undefined != supplierConfirmRemarks){
				$("#alertRemarks4").text(supplierConfirmRemarks);
	    		$("#subReport4").show(); 
			}else{
				$("#alertRemarks4").text("材料部暂无说明");
	    		$("#subReport4").show(); 
			}
			
		} 

//---------------------------------------材料部说明end----------------------------------------------------

//---------------------------------------工人组信息start----------------------------------------------------

		function findEmployeeGroupMessage(installConstructBillId){
	
			
			$.ajax({
				url: "${ctx }/bizsupplierinstallbill/bizSupplierInstallBill/findEmployeeGroupMessage",
	            type: "post",
	            async: false,
	            data:{
	            		installConstructBillId:installConstructBillId
	            	},
	            success: function(data) {
	            	
	            	if(null!=data){
	            		if(data.id != null && data.id != "undefined"){
	            			$("#workerRemarks1").text("工人组长："+data.workerName);
	            			$("#workerRemarks2").text("工人组长手机号："+data.workerPhone);
	            			$("#workerRemarks3").text("上门签到时间："+data.realIntoDateString);
	            			$("#workerRemarks4").text("申请完工时间："+data.realCompleteDateString);
	            			$("#workerRemarks5").text("项目经理验收通过时间："+data.realAcceptDateString);
	            			
		            		$("#subReport5").show();
	            		}
	            	}
        	    }
	        }) 
			 
			
		} 

//---------------------------------------工人组信息end----------------------------------------------------

	</script>
	<!-- 确认工期 -->
	<script type="text/javascript">
	
//---------------------------------------确认工期start----------------------------------------------------

		var installBillIdGlobalBoxA;
		
		//1.弹框
		function confirmTimeLimit(obj,installBillId){
	
			installBillIdGlobalBoxA=installBillId;
	
			var installBillCode;
			var orderAddress;
			var customerName;
			var itemManagerName;
			var itemManagerPhone;
			var installItemName;
			var planIntoDate;
			var planCompleteDate;
			var detailsAddress = $(obj).parent().find("span").text();;
	
			var trObj = $(obj).parent().parent().find("td").each(function(){
				
	  			if($(this).index()==2){
	  				installBillCode = $(this).text();
	  			}
	  			if($(this).index()==3){
	  				orderAddress = $(this).text();
	  			}
	  			if($(this).index()==4){
	  				customerName = $(this).text();
	  			}
	  			if($(this).index()==5){
	  				itemManagerName = $(this).text();
	  			}
	  			if($(this).index()==6){
	  				itemManagerPhone = $(this).text();
	  			}
	  			if($(this).index()==7){
	  				installItemName = $(this).text();
	  			}
	  			if($(this).index()==9){
	  				planIntoDate = $(this).text().trim();
	  			}
	  			if($(this).index()==10){
	  				planCompleteDate = $(this).text().trim();
	  			}
	  			
			})
			
			$("#boxA1").text("安装项："+installItemName);
			$("#boxA2").text("安装单编号："+installBillCode);
			$("#boxA3").text("项目经理："+itemManagerName);
			$("#boxA4").text("项目经理手机号："+itemManagerPhone);
			$("#boxA5").text("小区名称："+orderAddress);
			$("#boxA6").text("客户姓名："+customerName);
			$("#boxA7").text("客户地址："+detailsAddress);
			$("#boxA8").val(planIntoDate);
			$("#boxA9").val(planCompleteDate);
			$("#boxA10").val(planIntoDate);
			$("#boxA11").val(planCompleteDate);
			
			$("#confirmTimeLimitBox").show(); 
	
		} 

		//2.弹框--取消
		function confirmTimeLimitBoxNo(){
			$("#boxA1").text("");
			$("#boxA2").text("");
			$("#boxA3").text("");
			$("#boxA4").text("");
			$("#boxA5").text("");
			$("#boxA6").text("");
			$("#boxA7").text("");
			$("#boxA8").val("");
			$("#boxA9").val("");
			$("#boxA10").val("");
			$("#boxA11").val("");
			
			$("#confirmTimeLimitBox").hide();
		}
		
		//3.弹框--保存
		function confirmTimeLimitBoxYes(){
			
			var boxA10 = $("#boxA10").val();
			var boxA11 = $("#boxA11").val();
			
			//数据校验
			if(null==boxA10 || ""==boxA10 || undefined==boxA10 ){
				$("#alertRemarks").text("请输入确认工期");
	    		$("#subReport").show(); 
	    		return false;
			}
			if(null==boxA11 || ""==boxA11 || undefined==boxA11 ){
				$("#alertRemarks").text("请输入确认工期");
	    		$("#subReport").show(); 
	    		return false;
			}
			
			
			$("#subReport2").show();
			
		}
		
		//是否保存--取消
		function cancel(){
			$("#subReport2").hide();
		}
		
		//是否保存--是
		function sendMessage2(){
			var boxA10 = $("#boxA10").val();
			var boxA11 = $("#boxA11").val();
			
			
			$.ajax({
				url: "${ctx}/bizsupplierinstallbill/bizSupplierInstallBill/update_supplier_confirm_date_ajax",
	            type: "post",
	            data:{
	            		supplierConfirmIntoDate:boxA10,
	            		supplierConfirmCompleteDate:boxA11,
	            		installBillId:installBillIdGlobalBoxA
	            	},
	            success: function(data) {
	            	
	            	$("#boxA1").text("");
	    			$("#boxA2").text("");
	    			$("#boxA3").text("");
	    			$("#boxA4").text("");
	    			$("#boxA5").text("");
	    			$("#boxA6").text("");
	    			$("#boxA7").text("");
	    			$("#boxA8").val("");
	    			$("#boxA9").val("");
	    			$("#boxA10").val("");
	    			$("#boxA11").val("");
	    			
	    			$("#confirmTimeLimitBox").hide();
	    			$("#subReport2").hide();
	    			
	            	if(null!=data && data=="0"){
	            		$("#alertRemarks1").text("供应商确认工期成功");
	            		$("#subReport1").show();
	            	}else if(data=="1"){
	            		$("#alertRemarks").text("安装单ID为空");
	            		$("#subReport").show();
	            	}else if(data=="2"){
	            		$("#alertRemarks").text("供应商确认进场日期为空");
	            		$("#subReport").show();
	            	}else if(data=="3"){
	            		$("#alertRemarks").text("供应商确认完工日期为空");
	            		$("#subReport").show();
	            	}else if(data=="4"){
	            		$("#alertRemarks").text("当前登录人未登录");
	            		$("#subReport").show();
	            	}else if(data=="5"){
	            		$("#alertRemarks").text("该安装单已经确认过工期了");
	            		$("#subReport").show();
	            	}else if(data=="6"){
	            		$("#alertRemarks").text("安装单更新失败");
	            		$("#subReport").show();
	            	}else if(data=="7"){
	            		$("#alertRemarks").text("安装单状态日志保存失败");
	            		$("#subReport").show();
	            	}
	            	
	            }
	    	})
	    	
			
		}
//---------------------------------------确认工期end----------------------------------------------------
	
	</script>
	<!-- 分配工人组 -->
	<script type="text/javascript">
	
//---------------------------------------分配工人组start----------------------------------------------------

		var installBillIdGlobalBoxB;
		var statusGlobalBoxB;
		var supplierIdGlobalBoxB;
		var employeeGroupIdGlobalBoxB;
		
		//1.弹框
		function distributionWorkersTeam(obj,installBillId,status,employeeGroupIdBox){
			
			installBillIdGlobalBoxB = installBillId;
			statusGlobalBoxB = status;
			employeeGroupIdGlobalBoxB = employeeGroupIdBox;
			
			var installBillCode;
			var orderAddress;
			var customerName;
			var itemManagerName;
			var itemManagerPhone;
			var installItemName;
			var planIntoDate;
			var planCompleteDate;
			var detailsAddress = $(obj).parent().find("span").text();;
			var supplierConfirmIntoDate = $(obj).parent().find(".supplierConfirmIntoDate").text().trim();
			var supplierConfirmCompleteDate = $(obj).parent().find(".supplierConfirmCompleteDate").text().trim();
	
			var trObj = $(obj).parent().parent().find("td").each(function(){
				
	  			if($(this).index()==2){
	  				installBillCode = $(this).text();
	  			}
	  			if($(this).index()==3){
	  				orderAddress = $(this).text();
	  			}
	  			if($(this).index()==4){
	  				customerName = $(this).text();
	  			}
	  			if($(this).index()==5){
	  				itemManagerName = $(this).text();
	  			}
	  			if($(this).index()==6){
	  				itemManagerPhone = $(this).text();
	  			}
	  			if($(this).index()==7){
	  				installItemName = $(this).text();
	  			}
	  			if($(this).index()==9){
	  				planIntoDate = $(this).text().trim();
	  			}
	  			if($(this).index()==10){
	  				planCompleteDate = $(this).text().trim();
	  			}
	  			
			})
			
			$("#boxB1").text("安装项："+installItemName);
			$("#boxB2").text("安装单编号："+installBillCode);
			$("#boxB3").text("项目经理："+itemManagerName);
			$("#boxB4").text("项目经理手机号："+itemManagerPhone);
			$("#boxB5").text("小区名称："+orderAddress);
			$("#boxB6").text("客户姓名："+customerName);
			$("#boxB7").text("客户地址："+detailsAddress);
			$("#boxB8").val(planIntoDate);
			$("#boxB9").val(planCompleteDate);
			
			if(statusGlobalBoxB == "10"){
				$("#boxB10").val(planIntoDate);
				$("#boxB11").val(planCompleteDate);
				$("#confirmDate1").show();
				$("#confirmDate2").hide();
				
			}else{
				$("#boxB12").val(supplierConfirmIntoDate);
				$("#boxB13").val(supplierConfirmCompleteDate);
				$("#confirmDate1").hide();
				$("#confirmDate2").show();
			}
			
			
			
			var html='<tr>'+
					    '<th>选择</th>'+
					    '<th>组长姓名</th>'+
					    '<th>手机</th>'+
					    '<th>组内成员数</th>'+
					    '<th>当前未完工任务包数</th>'+
					    '<th>住址</th>'+
					    '<th>距离施工地点（米）</th>'+
					'</tr>';
			
			var workerName = $("#workerName").val();
			
			
			$.ajax({
				url: "${ctx}/bizsupplierinstallbill/bizSupplierInstallBill/findInstallWorkerList",
	            type: "post",
	            async: false,
	            data:{
	            		installBillId:installBillIdGlobalBoxB,
	            		workerName:workerName
	            	},
	            success: function(data) {
	    			
	            	if(null!=data && data.length>0){
	            		for(var v=0;v<data.length;v++){
	            			if(data[v].employeegroupId != null && data[v].employeegroupId != "undefined"){
	            				html += '<tr>'+
			            				    '<td><input type="radio" name="ids"value="'+data[v].employeegroupId+'@-#-@'+data[v].workerName+'" onclick="getWorkerName()"></td>'+
			            				    '<td>'+data[v].workerName+'</td>'+
			            				    '<td>'+data[v].workerPhone+'</td>'+
			            				    '<td>'+data[v].workerNumber+'</td>'+
			            				    '<td>'+data[v].supplierInstallConstrunctBillCount+'</td>'+
			            				    '<td>'+data[v].address+'</td>'+
			            				    '<td>'+data[v].distance+'</td>'+
			            				'</tr>';
	            			}
	            		}
	            		
	            	}else{
	            		$("#alertRemarks").text("该供应商没有工人组");
	            		$("#subReport").show(); 
	            	}
	            }
	    	})
	    	
			
			$("#workerList").html(html);
			$("#distributionWorkersTeamBox").show();
		} 
		
		//2.查询工人组
		function findInstallWorkerList(){
			
			var html='<tr>'+
					    '<th>选择</th>'+
					    '<th>组长姓名</th>'+
					    '<th>手机</th>'+
					    '<th>组内成员数</th>'+
					    '<th>当前未完工任务包数</th>'+
					    '<th>住址</th>'+
					    '<th>距离施工地点（米）</th>'+
					'</tr>';

			var workerName = $("#workerName").val();
			
			
			$.ajax({
				url: "${ctx}/bizsupplierinstallbill/bizSupplierInstallBill/findInstallWorkerList",
			    type: "post",
			    async: false,
			    data:{
			    		installBillId:installBillIdGlobalBoxB,
			    		workerName:workerName
			    	},
			    success: function(data) {
					
			    	if(null!=data && data.length>0){
			    		for(var v=0;v<data.length;v++){
			    			if(data[v].employeegroupId != null && data[v].employeegroupId != "undefined"){
			    				html += '<tr>'+
			            				    '<td><input type="radio" name="ids"value="'+data[v].employeegroupId+'@-#-@'+data[v].workerName+'" onclick="getWorkerName()"></td>'+
			            				    '<td>'+data[v].workerName+'</td>'+
			            				    '<td>'+data[v].workerPhone+'</td>'+
			            				    '<td>'+data[v].workerNumber+'</td>'+
			            				    '<td>'+data[v].supplierInstallConstrunctBillCount+'</td>'+
			            				    '<td>'+data[v].address+'</td>'+
			            				    '<td>'+data[v].distance+'</td>'+
			            				'</tr>';
			    			}
			    		}
			    		
			    	}else{
			    		$("#alertRemarks").text("该供应商没有工人组");
			    		$("#subReport").show(); 
			    	}
			    }
			})
			
			$("#workerList").html(html);
		}
		
		
		//3.获取工人组
		function getWorkerName(){
			var name = $(":radio[name='ids'][checked='checked']").val();
			var arr =  name.split("@-#-@");
			employeegroupId = arr[0];
			workerName = arr[1];
			$("#employeegroupId").val(employeegroupId);
			$("#workerName").val(workerName);
			
		}

		//4.弹框--关闭
		function distributionWorkersTeamBoxNo(){
			$("#boxB1").text("");
			$("#boxB2").text("");
			$("#boxB3").text("");
			$("#boxB4").text("");
			$("#boxB5").text("");
			$("#boxB6").text("");
			$("#boxB7").text("");
			$("#boxB8").val("");
			$("#boxB9").val("");
			$("#boxB10").val("");
			$("#boxB11").val("");
			$("#boxB12").val("");
			$("#boxB13").val("");
			$("#employeegroupId").val("");
			$("#workerName").val("");
			$("#workerList").html("");
			
			$("#distributionWorkersTeamBox").hide();
			
		}
		
		//5.弹框--分配工人
		function distributionWorkersTeamBoxYes(){
			
			if(statusGlobalBoxB == "10"){
				
				var boxB10 = $("#boxB10").val();
				var boxB11 = $("#boxB11").val();
				
				//数据校验
				if(null==boxB10 || ""==boxB10 || undefined==boxB10 ){
					$("#alertRemarks").text("请输入确认工期");
		    		$("#subReport").show(); 
		    		return false;
				}
				if(null==boxB11 || ""==boxB11 || undefined==boxB11 ){
					$("#alertRemarks").text("请输入确认工期");
		    		$("#subReport").show(); 
		    		return false;
				}
				
			}
			
			var employeegroupId = $("#employeegroupId").val();
			//数据校验
			if(null==employeegroupId || ""==employeegroupId || undefined==employeegroupId ){
				$("#alertRemarks").text("请选择工人组");
	    		$("#subReport").show(); 
	    		return false;
			}
			if(employeegroupId == employeeGroupIdGlobalBoxB){
				$("#alertRemarks").text("您选择的工人组与现在的工人组一样，请选择其他的工人组。");
	    		$("#subReport").show(); 
	    		return false;
			}
			$("#subReport3").show(); 
		}
		
		//是否保存--取消
		function cancel3(){
			$("#subReport3").hide();
		}
		
		//是否保存--是
		function sendMessage3(){
			var boxB10 = $("#boxB10").val();
			var boxB11 = $("#boxB11").val();
			var employeegroupId = $("#employeegroupId").val();
			
			$.ajax({
				url: "${ctx}/bizsupplierinstallbill/bizSupplierInstallBill/update_supplier_distribution_workers_ajax",
	            type: "post",
	            data:{
	            		supplierConfirmIntoDate:boxB10,
	            		supplierConfirmCompleteDate:boxB11,
	            		installBillId:installBillIdGlobalBoxB,
	            		employeegroupId:employeegroupId
	            	},
	            success: function(data) {
	            	
	            	$("#boxB1").text("");
	    			$("#boxB2").text("");
	    			$("#boxB3").text("");
	    			$("#boxB4").text("");
	    			$("#boxB5").text("");
	    			$("#boxB6").text("");
	    			$("#boxB7").text("");
	    			$("#boxB8").val("");
	    			$("#boxB9").val("");
	    			$("#boxB10").val("");
	    			$("#boxB11").val("");
	    			$("#boxB12").val("");
	    			$("#boxB13").val("");
	    			$("#employeegroupId").val("");
	    			$("#workerName").val("");
	    			$("#workerList").html("");
	    			
	    			$("#distributionWorkersTeamBox").hide();
	    			$("#subReport3").hide();
	    			
	            	if(null!=data && data=="0"){
	            		$("#alertRemarks1").text("供应商分派工人组成功");
	            		$("#subReport1").show();
	            	}else if(data=="1"){
	            		$("#alertRemarks").text("安装单ID为空");
	            		$("#subReport").show();
	            	}else if(data=="2"){
	            		$("#alertRemarks").text("该安装单已经验收完成了，不可分派工人组");
	            		$("#subReport").show();
	            	}else if(data=="3"){
	            		$("#alertRemarks").text("供应商确认进场日期为空");
	            		$("#subReport").show();
	            	}else if(data=="4"){
	            		$("#alertRemarks").text("供应商确认完工日期为空");
	            		$("#subReport").show();
	            	}else if(data=="5"){
	            		$("#alertRemarks").text("工人组id为空");
	            		$("#subReport").show();
	            	}else if(data=="6"){
	            		$("#alertRemarks").text("工人选择的工人组与现在的工人组一样");
	            		$("#subReport").show();
	            	}else if(data=="7"){
	            		$("#alertRemarks").text("当前登录人未登录");
	            		$("#subReport").show();
	            	}else if(data=="8"){
	            		$("#alertRemarks").text("安装项计划更新失败");
	            		$("#subReport").show();
	            	}else if(data=="9"){
	            		$("#alertRemarks").text("安装项计划状态日志保存失败");
	            		$("#subReport").show();
	            	}else if(data=="10"){
	            		$("#alertRemarks").text("安装单更新失败");
	            		$("#subReport").show();
	            	}else if(data=="11"){
	            		$("#alertRemarks").text("安装单状态日志保存失败");
	            		$("#subReport").show();
	            	}else if(data=="12"){
	            		$("#alertRemarks").text("之前的施工单更新失败");
	            		$("#subReport").show();
	            	}else if(data=="13"){
	            		$("#alertRemarks").text("之前的施工单状态日志保存失败");
	            		$("#subReport").show();
	            	}else if(data=="14"){
	            		$("#alertRemarks").text("施工单保存失败");
	            		$("#subReport").show();
	            	}else if(data=="15"){
	            		$("#alertRemarks").text("施工单状态日志保存失败");
	            		$("#subReport").show();
	            	}else if(data=="16"){
	            		$("#alertRemarks").text("工人组信息为空");
	            		$("#subReport").show();
	            	}else if(data=="17"){
	            		$("#alertRemarks").text("工人组长短信发送失败");
	            		$("#subReport").show();
	            	}else if(data=="18"){
	            		$("#alertRemarks").text("项目经理短信发送失败");
	            		$("#subReport").show();
	            	}else if(data=="19"){
	            		$("#alertRemarks").text("供应商分派工人失败");
	            		$("#subReport").show();
	            	}
	            	
	            }
	    	})
	    	
	    	
			
		}
//---------------------------------------分配工人组end----------------------------------------------------
	
	
	</script>
</body>
</html>