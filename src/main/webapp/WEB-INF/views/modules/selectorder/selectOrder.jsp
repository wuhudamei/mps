<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>订单明细查询</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$(":radio[name='houseIsNew'][value='" + 2 + "']").next().text("全部");
			var name = $(":radio[name='houseIsNew'][checked='checked']").val();
			if(name==null || name=="2"){
				$(":radio[name='houseIsNew'][value='" + 2 + "']").prop("checked", "checked");
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
			 $(":radio[name='houseIsNew'][value='" + 2 + "']").prop("checked", "checked");
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
		
		window.onload = function(){
			
			 var obj=document.getElementsByName('orderStatusNumber');  
			  var s='';    
			  for(var i=0; i<obj.length; i++){    
			    if(!obj[i].checked){
			    	s+="11"+obj[i].value+',';  //如果选中，将value添加到变量s中    
			    }
			  } 
			  if(s==null || s==""){
				  $("#chkAll").prop("checked", "checked");
			  }
		}
		
		
		function exportExcel(){
			
			//门店
			var storeId1 = '${selectOrder.storeId}';
			var storeId = $("#storeId").val();
			if(storeId1 != storeId){
				alertx("请先点击查询按钮后，再导出订单信息");
				return false;
			}
			//工程模式
			var projectMode1 = '${selectOrder.projectMode}';
			var projectMode = $("#projectMode").val();
			if(projectMode1 != projectMode){
				alertx("请先点击查询按钮后，再导出订单信息");
				return false;
			}
			//区域
			var engineDepartId1 = '${selectOrder.engineDepartId}';
			var engineDepartId = $("#engineDepartSelect").val();
			if(engineDepartId1 != engineDepartId){
				alertx("请先点击查询按钮后，再导出订单信息");
				return false;
			}
			
			//订单编号
			var orderNumber1 = '${selectOrder.orderNumber}';
			var orderNumber = $("#orderNumber").val();
			if(orderNumber1 != orderNumber){
				alertx("请先点击查询按钮后，再导出订单信息");
				return false;
			}
			
			//小区名称
			var communityName1 = '${selectOrder.communityName}';
			var communityName = $("#communityName").val();
			if(communityName1 != communityName){
				alertx("请先点击查询按钮后，再导出订单信息");
				return false;
			}
			
			//客户姓名
			var customerName1 = '${selectOrder.customerName}';
			var customerName = $("#customerName").val();
			if(customerName1 != customerName){
				alertx("请先点击查询按钮后，再导出订单信息");
				return false;
			}
			
			//客户手机号
			var customerPhone1 = '${selectOrder.customerPhone}';
			var customerPhone = $("#customerPhone").val();
			if(customerPhone1 != customerPhone){
				alertx("请先点击查询按钮后，再导出订单信息");
				return false;
			}
			
			//项目经理
			var itemManager1 = '${selectOrder.itemManager}';
			var itemManager = $("#itemManager").val();
			if(itemManager1 != itemManager){
				alertx("请先点击查询按钮后，再导出订单信息");
				return false;
			}
			
			//质检员
			var orderInspector1 = '${selectOrder.orderInspector}';
			var orderInspector = $("#orderInspector").val();
			if(orderInspector1 != orderInspector){
				alertx("请先点击查询按钮后，再导出订单信息");
				return false;
			}
			
			//设计师
			var designerName1 = '${selectOrder.designerName}';
			var designerName = $("#designerName").val();
			if(designerName1 != designerName){
				alertx("请先点击查询按钮后，再导出订单信息");
				return false;
			}
			
			//客户经理
			var cusManager1 = '${selectOrder.cusManager}';
			var cusManager = $("#cusManager").val();
			if(cusManager1 != cusManager){
				alertx("请先点击查询按钮后，再导出订单信息");
				return false;
			}
			
			//新老房  
			var houseIsNew1 = '${selectOrder.houseIsNew}';
			var houseIsNew = $(":radio[name='houseIsNew'][checked='checked']").val();
			if(null==houseIsNew1 || houseIsNew1==""){
				houseIsNew1='2';
			}
			if(houseIsNew1 != houseIsNew){
				alertx("请先点击查询按钮后，再导出订单信息");
				return false;
			}
			
			//合同开工日期  开始
			var beginContractStartDate1 = $("#beginContractStartDate1").val();
			var beginContractStartDate = $("#beginContractStartDate").val();
			if(beginContractStartDate1 != beginContractStartDate){
				alertx("请先点击查询按钮后，再导出订单信息");
				return false;
			}
			
			//合同开工日期  结束
			var endContractStartDate1 = $("#endContractStartDate1").val();
			var endContractStartDate = $("#endContractStartDate").val();
			if(endContractStartDate1 != endContractStartDate){
				alertx("请先点击查询按钮后，再导出订单信息");
				return false;
			}
			
			//合同竣工日期  开始
			var beginContractEndDate1 = $("#beginContractEndDate1").val();
			var beginContractEndDate = $("#beginContractEndDate").val();
			if(beginContractEndDate1 != beginContractEndDate){
				alertx("请先点击查询按钮后，再导出订单信息");
				return false;
			}
			
			//合同竣工日期  结束
			var endContractEndDate1 = $("#endContractEndDate1").val();
			var endContractEndDate = $("#endContractEndDate").val();
			if(endContractEndDate1 != endContractEndDate){
				alertx("请先点击查询按钮后，再导出订单信息");
				return false;
			}
			
			//签约日期  开始
			var beginSignContractDate1 = $("#beginSignContractDate1").val();
			var beginSignContractDate = $("#beginSignContractDate").val();
			if(beginSignContractDate1 != beginSignContractDate){
				alertx("请先点击查询按钮后，再导出订单信息");
				return false;
			}
			
			//签约日期  结束
			var endSignContractDate1 = $("#endSignContractDate1").val();
			var endSignContractDate = $("#endSignContractDate").val();
			if(endSignContractDate1 != endSignContractDate){
				alertx("请先点击查询按钮后，再导出订单信息");
				return false;
			}
			
			//实际开工日期  开始
			var beginActualStartDate1 = $("#beginActualStartDate1").val();
			var beginActualStartDate = $("#beginActualStartDate").val();
			if(beginActualStartDate1 != beginActualStartDate){
				alertx("请先点击查询按钮后，再导出订单信息");
				return false;
			}
			
			//实际开工日期  结束
			var endActualStartDate1 = $("#endActualStartDate1").val();
			var endActualStartDate = $("#endActualStartDate").val();
			if(endActualStartDate1 != endActualStartDate){
				alertx("请先点击查询按钮后，再导出订单信息");
				return false;
			}
			
			//实际竣工日期  开始
			var beginActualEndDate1 = $("#beginActualEndDate1").val();
			var beginActualEndDate = $("#beginActualEndDate").val();
			if(beginActualEndDate1 != beginActualEndDate){
				alertx("请先点击查询按钮后，再导出订单信息");
				return false;
			}
			
			//实际竣工日期  结束
			var endActualEndDate1 = $("#endActualEndDate1").val();
			var endActualEndDate = $("#endActualEndDate").val();
			if(endActualEndDate1 != endActualEndDate){
				alertx("请先点击查询按钮后，再导出订单信息");
				return false;
			}
			
			//订单创建日期  开始
			var beginCreateDate1 = $("#beginCreateDate1").val();
			var beginCreateDate = $("#beginCreateDate").val();
			if(beginCreateDate1 != beginCreateDate){
				alertx("请先点击查询按钮后，再导出订单信息");
				return false;
			}
			
			//订单创建日期  结束
			var endCreateDate1 = $("#endCreateDate1").val();
			var endCreateDate = $("#endCreateDate").val();
			if(endCreateDate1 != endCreateDate){
				alertx("请先点击查询按钮后，再导出订单信息");
				return false;
			}
			
			//接单日期  开始
			var beginGetOrderDatetime1 = $("#beginGetOrderDatetime1").val();
			var beginGetOrderDatetime = $("#beginGetOrderDatetime").val();
			if(beginGetOrderDatetime1 != beginGetOrderDatetime){
				alertx("请先点击查询按钮后，再导出订单信息");
				return false;
			}
			
			//接单日期  结束
			var endGetOrderDatetime1 = $("#endGetOrderDatetime1").val();
			var endGetOrderDatetime = $("#endGetOrderDatetime").val();
			if(endGetOrderDatetime1 != endGetOrderDatetime){
				alertx("请先点击查询按钮后，再导出订单信息");
				return false;
			}
			
			//派项目经理日期  开始
			var beginOrderDistributeLogDate1 = $("#beginOrderDistributeLogDate1").val();
			var beginOrderDistributeLogDate = $("#beginOrderDistributeLogDate").val();
			if(beginOrderDistributeLogDate1 != beginOrderDistributeLogDate){
				alertx("请先点击查询按钮后，再导出订单信息");
				return false;
			}
			
			//派项目经理日期  结束
			var endOrderDistributeLogDate1 = $("#endOrderDistributeLogDate1").val();
			var endOrderDistributeLogDate = $("#endOrderDistributeLogDate").val();
			if(endOrderDistributeLogDate1 != endOrderDistributeLogDate){
				alertx("请先点击查询按钮后，再导出订单信息");
				return false;
			}
			
			//任务包类型
			var orderStatusNumber1 = '${selectOrder.orderStatusNumber}';
			if(orderStatusNumber1.length > 0){
				orderStatusNumber1 +=',';
			}
			var obj=document.getElementsByName('orderStatusNumber');  //选择所有name="'taskpackTempId'"的对象，返回数组    
			var orderStatusNumber='';  
			var j = 0;
			for(var i=0; i<obj.length; i++){ 
				if(obj[i].checked){
					orderStatusNumber+=obj[i].value+',';  //如果选中，将value添加到变量s中  
					j++;
				}
			} 
			if(orderStatusNumber1 != orderStatusNumber){
				alertx("请先点击查询按钮后，再导出订单信息");
				return false;
			}
			
			$("#searchForm").attr("action", "${ctx}/selectOrder/selectOrder/exportExcel");
			$("#searchForm").submit();
			$("#searchForm").attr("action", "${ctx}/selectOrder/selectOrder/selectOrderList");
		}
		


		function searchButton(){

           /*  var beginContractStartDate = $("#beginContractStartDate").val();
            var endContractStartDate = $("#endContractStartDate").val();


            var beginContractEndDate = $("#beginContractEndDate").val();
            var endContractEndDate = $("#endContractEndDate").val();

            var beginSignContractDate = $("#beginSignContractDate").val();
            var endSignContractDate = $("#endSignContractDate").val();



            var beginActualStartDate = $("#beginActualStartDate").val();
            var endActualStartDate = $("#endActualStartDate").val();

            var beginActualEndDate = $("#beginActualEndDate").val();
            var endActualEndDate = $("#endActualEndDate").val();


            var beginCreateDate = $("#beginCreateDate").val();
            var endCreateDate = $("#endCreateDate").val();


            var beginGetOrderDatetime = $("#beginGetOrderDatetime").val();
            var endGetOrderDatetime = $("#endGetOrderDatetime").val();

            var beginOrderDistributeLogDate = $("#beginOrderDistributeLogDate").val();
            var endOrderDistributeLogDate = $("#endOrderDistributeLogDate").val();

		    if($("#storeId").val()!=""&&$("#projectMode").val()!=""&&$("#engineDepartId").val()!=""&& ((beginContractStartDate!=""&&endContractStartDate!="") ||(beginContractEndDate!=""&&endContractEndDate!="")  ||(beginSignContractDate!=""&&endSignContractDate!="")  ||(beginActualStartDate!=""&&endActualStartDate!="") ||(beginActualEndDate!=""&&endActualEndDate!="") ||(beginCreateDate!=""&&endCreateDate!="")   ||(beginGetOrderDatetime!=""&&endGetOrderDatetime!="")   ||(beginOrderDistributeLogDate!=""&&endOrderDistributeLogDate!="") )){

 */
                loading("订单查询中...")
                $("#searchForm").attr("action", "${ctx}/selectOrder/selectOrder/selectOrderList");
                $("#searchForm").submit();

			/* }else{

		        alertx("门店,模式,区域必选,时间段任选一个即可查询");
			} */



		}
		function findEngineListByStoreIdAndProjectMode(){
			var html3 = '<option value=""></option>';
		var storeId = $("#storeId").val();
		var projectModeValue = $("#projectMode").val();
		if (storeId=="" ||projectModeValue=="" ||undefined==storeId ||undefined==projectModeValue) {
			return;
		}
		//根据门店个,工程模式    动态加载工程区域
		$.ajax({

					url : "${ctx}/order/order/findEngineDepartmentBystoreIdAndProjectMode?storeId="
							+ storeId + "&projectModeValue=" + projectModeValue,
					type : "get",
					success : function(data) {
						if (null!= data && data.length > 0) {

							for (var v = 0; v < data.length; v++) {
								html3 +='<option value="'+data[v].engineDepartId+'">'+data[v].engineDepartName+'</option>' 
							}
							
							$("#engineDepartSelect").html(html3);
						} else {
							$("#engineDepartSelect").html(html3);
						}

					}

				});		
		}
		
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/selectOrder/selectOrder/list">订单明细查询</a></li>
	</ul>
	<br/>
	
	
	<form:form id="searchForm" modelAttribute="selectOrder" action="${ctx}/selectOrder/selectOrder/selectOrderList" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		
		<!-- 合同开工日期 -->
		<input id="beginContractStartDate1" type="hidden" value="<fmt:formatDate value="${selectOrder.beginContractStartDate}" pattern="yyyy-MM-dd HH:mm:ss"/>">
		<input id="endContractStartDate1" type="hidden" value="<fmt:formatDate value="${selectOrder.endContractStartDate}" pattern="yyyy-MM-dd HH:mm:ss"/>">
		
		<!-- 合同竣工日期 -->
		<input id="beginContractEndDate1" type="hidden" value="<fmt:formatDate value="${selectOrder.beginContractEndDate}" pattern="yyyy-MM-dd HH:mm:ss"/>">
		<input id="endContractEndDate1" type="hidden" value="<fmt:formatDate value="${selectOrder.endContractEndDate}" pattern="yyyy-MM-dd HH:mm:ss"/>">
		
		<!-- 签约日期 -->
		<input id="beginSignContractDate1" type="hidden" value="<fmt:formatDate value="${selectOrder.beginSignContractDate}" pattern="yyyy-MM-dd HH:mm:ss"/>">
		<input id="endSignContractDate1" type="hidden" value="<fmt:formatDate value="${selectOrder.endSignContractDate}" pattern="yyyy-MM-dd HH:mm:ss"/>">
		
		<!-- 实际开工日期 -->
		<input id="beginActualStartDate1" type="hidden" value="<fmt:formatDate value="${selectOrder.beginActualStartDate}" pattern="yyyy-MM-dd HH:mm:ss"/>">
		<input id="endActualStartDate1" type="hidden" value="<fmt:formatDate value="${selectOrder.endActualStartDate}" pattern="yyyy-MM-dd HH:mm:ss"/>">
		
		<!-- 实际竣工日期 -->
		<input id="beginActualEndDate1" type="hidden" value="<fmt:formatDate value="${selectOrder.beginActualEndDate}" pattern="yyyy-MM-dd HH:mm:ss"/>">
		<input id="endActualEndDate1" type="hidden" value="<fmt:formatDate value="${selectOrder.endActualEndDate}" pattern="yyyy-MM-dd HH:mm:ss"/>">
		
		<!-- 订单创建日期 -->
		<input id="beginCreateDate1" type="hidden" value="<fmt:formatDate value="${selectOrder.beginCreateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>">
		<input id="endCreateDate1" type="hidden" value="<fmt:formatDate value="${selectOrder.endCreateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>">
		
		<!-- 接单日期 -->
		<input id="beginGetOrderDatetime1" type="hidden" value="<fmt:formatDate value="${selectOrder.beginGetOrderDatetime}" pattern="yyyy-MM-dd HH:mm:ss"/>">
		<input id="endGetOrderDatetime1" type="hidden" value="<fmt:formatDate value="${selectOrder.endGetOrderDatetime}" pattern="yyyy-MM-dd HH:mm:ss"/>">
		
		<!-- 派项目经理日期 -->
		<input id="beginOrderDistributeLogDate1" type="hidden" value="<fmt:formatDate value="${selectOrder.beginOrderDistributeLogDate}" pattern="yyyy-MM-dd HH:mm:ss"/>">
		<input id="endOrderDistributeLogDate1" type="hidden" value="<fmt:formatDate value="${selectOrder.endOrderDistributeLogDate}" pattern="yyyy-MM-dd HH:mm:ss"/>">
		
		<ul class="ul-form">
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="button" value="查询"  onclick="searchButton()"/></li>
			<li class="btns"><input class="btn btn-primary clearBtn" type="button" value="清空" onclick="clearCondition()"/></li>
			<!-- <li class="btns"><input class="btn btn-primary" type="button" value="导出订单信息" onclick="exportExcel()"/></li> -->
			<li class="clearfix"></li>
			<li><label>门店：</label>
				<form:select path="storeId" class="input-medium needClear">
						<form:option value=""></form:option>
						<c:forEach items="${storeList}" var = "store">
							<form:option value="${store.value }">${store.label }</form:option>
						</c:forEach>
				</form:select>
			</li>
			<li><label>工程模式：</label>
				<c:if test="${empty gongcheng}">
					<form:select path="projectMode" class="input-medium" disabled="true" onchange="findEngineListByStoreIdAndProjectMode()">
						<form:option value="" label=""/>
						<form:options items="${fns:getDictList('project_mode')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
				<c:if test="${!empty gongcheng}">
					<form:select path="projectMode" class="input-medium needClear" onchange="findEngineListByStoreIdAndProjectMode()">
						<form:option value="" label=""/>
						<form:options items="${fns:getDictList('project_mode')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
					
			</li>
			<li><label>区域：</label>
				<form:select path="engineDepartId" class="input-medium needClear" id="engineDepartSelect">
					<form:options items="${fns:getEngineListWithUserConditionsForBiddenChange()}"   itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>订单编号：</label>
				<form:input path="orderNumber" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>小区名称：</label>
				<form:input path="communityName" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>客户姓名：</label>
				<form:input path="customerName" htmlEscape="false" maxlength="16" class="input-medium"/>
			</li>
			<li><label>客户手机号：</label>
				<form:input path="customerPhone" htmlEscape="false" maxlength="11" class="input-medium"/>
			</li>
			<li><label>项目经理：</label>
				<form:input path="itemManager" htmlEscape="false" maxlength="11" class="input-medium"/>
			</li>
			<li><label>质检员：</label>
				<form:input path="orderInspector" htmlEscape="false" maxlength="11" class="input-medium"/>
			</li>
			<li><label>设计师：</label>
				<form:input path="designerName" htmlEscape="false" maxlength="12" class="input-medium"/>
			</li>
			<li><label>客服经理：</label>
				<form:input path="cusManager" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>新老房：</label>
				<form:radiobuttons path="houseIsNew" items="${fns:getDictList('biz_order_is_new_house')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
			</li>
			
			<li><label>合同开工日期：</label>
				<input name="beginContractStartDate" id="beginContractStartDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${selectOrder.beginContractStartDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'endContractStartDate\')}',isShowClear:true});"/> 至  
				<input name="endContractStartDate" id="endContractStartDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${selectOrder.endContractStartDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'beginContractStartDate\')}',isShowClear:true});"/>
			</li>
			<li><label>合同竣工日期：</label>
				<input name="beginContractEndDate" id="beginContractEndDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${selectOrder.beginContractEndDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'endContractEndDate\')}',isShowClear:true});"/> 至  
				<input name="endContractEndDate" id="endContractEndDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${selectOrder.endContractEndDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'beginContractEndDate\')}',isShowClear:true});"/>
			</li>
			<li><label>签约日期：</label>
				<input name="beginSignContractDate" id="beginSignContractDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${selectOrder.beginSignContractDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'endSignContractDate\')}',isShowClear:true});"/> 至  
				<input name="endSignContractDate" id="endSignContractDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${selectOrder.endSignContractDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'beginSignContractDate\')}',isShowClear:true});"/>
			</li>
			<li><label>实际开工日期：</label>
				<input name="beginActualStartDate" id="beginActualStartDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${selectOrder.beginActualStartDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'endActualStartDate\')}',isShowClear:true});"/> 至  
				<input name="endActualStartDate" id="endActualStartDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${selectOrder.endActualStartDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'beginActualStartDate\')}',isShowClear:true});"/>
			</li>
			<li><label>实际竣工日期：</label>
				<input name="beginActualEndDate" id="beginActualEndDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${selectOrder.beginActualEndDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'endActualEndDate\')}',isShowClear:true});"/> 至  
				<input name="endActualEndDate" id="endActualEndDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${selectOrder.endActualEndDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'beginActualEndDate\')}',isShowClear:true});"/>
			</li>
			<li><label>订单创建日期：</label>
				<input name="beginCreateDate" id="beginCreateDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${selectOrder.beginCreateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'endCreateDate\')}',isShowClear:true});"/> 至 
				<input name="endCreateDate" id="endCreateDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${selectOrder.endCreateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'beginCreateDate\')}',isShowClear:true});"/>
			</li>
			<li><label>接单日期：</label>
				<input name="beginGetOrderDatetime" id="beginGetOrderDatetime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${selectOrder.beginGetOrderDatetime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'endGetOrderDatetime\')}',isShowClear:true});"/> 至 
				<input name="endGetOrderDatetime" id="endGetOrderDatetime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${selectOrder.endGetOrderDatetime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'beginGetOrderDatetime\')}',isShowClear:true});"/>
			</li>
			<%-- <li><label style="width: 110px;">派项目经理日期：</label>
				<input name="beginOrderDistributeLogDate" id="beginOrderDistributeLogDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${selectOrder.beginOrderDistributeLogDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'endOrderDistributeLogDate\')}',isShowClear:true});"/> 至 
				<input name="endOrderDistributeLogDate" id="endOrderDistributeLogDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${selectOrder.endOrderDistributeLogDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'beginOrderDistributeLogDate\')}',isShowClear:true});"/>
			</li> --%>
			<li style="width: 100%"><label>订单状态：</label>
				<input name="chkAll" id="chkAll" title="全部" onclick="ChkAllClick('orderStatusNumber','chkAll')" type="checkbox" />全部
			</li>
			<li style="height: 80px">
				<%-- <form:checkboxes path="orderStatusNumber" items="${fns:getDictList('order_status')}" onclick="ChkSonClick('orderStatusNumber','chkAll')" itemLabel="label" itemValue="value" htmlEscape="false"/> --%>
				<c:forEach items="${fns:getDictList('order_status')}" var="dict">
					<input type="checkbox" name="orderStatusNumber" id="orderStatusNumber" value="${dict.value}"  onclick="ChkSonClick('orderStatusNumber','chkAll')"  <c:if test="${fns:checkIsExits(selectOrder.orderStatusNumber,dict.value)}"> checked="checked"</c:if>/>${dict.label}&nbsp;
				</c:forEach>
			</li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>门店</th>
				<th>工程模式</th>
				<th>区域</th>
				<th>订单编号</th>
				<th>订单状态</th>
				<th>小区名称</th>
				<th>新老房</th>
				<th>客户姓名</th>
				<th>客户手机号</th>
				<th>项目经理</th>
				<th>项目经理手机号</th>
				<th>质检员</th>
				<th>质检员手机号</th>
				<th>设计师</th>
				<th>客服经理</th>
				<th>合同开工日期</th>
				<th>合同竣工日期</th>
				<th>实际开工日期</th>
				<th>实际竣工日期</th>
				<th>签约日期</th>
				<th>订单创建时间</th>
				<th>接单日期</th>
				<!-- <th>派项目经理日期</th> -->
				
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="order">
			<tr>
				<td>
					${order.name}
				</td>
				<td>
					${fns:getDictLabel(order.projectMode, 'project_mode', '')}
				</td>
				<td>
					${order.engineDepartName}
				</td>
				<td>
					<a href="${ctx}/orderDtails/orderDtailsLook?orderId=${order.orderId}">${order.orderNumber}</a>
				</td>
				<td>
					${fns:getDictLabel(order.orderStatusNumber,'order_status', '')}
				</td>
				<td>
					${order.communityName}-${order.buildNumber}-${order.buildUnit}-${order.buildRoom}
				</td>
				<td>
				 	${fns:getDictLabel(order.houseIsNew,'biz_order_is_new_house', '')}
				</td>
				<td>
					${order.customerName}
				</td>
				<td>
					${order.customerPhone}
				</td>
				<td>
					${order.itemManager}
				</td>
				<td>
					${order.itemManagerPhone}
				</td>
				<td>
					${order.orderInspector}
				</td>
				<td>
					${order.orderInspectorPhone}
				</td>
				
				
				<td>
					${order.designerName}
				</td>
				<td>
					${order.cusManager}
				</td>
				
				<td>
					<fmt:formatDate value="${order.contractStartDate}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					<fmt:formatDate value="${order.contractEndDate}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					<fmt:formatDate value="${order.actualStartDate}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					<fmt:formatDate value="${order.actualEndDate}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					<fmt:formatDate value="${order.signContractDate}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					<fmt:formatDate value="${order.createDate}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					<fmt:formatDate value="${order.getOrderDatetime}" pattern="yyyy-MM-dd"/>
				</td>
				<%-- <td>
					<fmt:formatDate value="${order.orderDistributeLogDate}" pattern="yyyy-MM-dd"/>
				</td> --%>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
<script type="text/javascript">

</script>
</html>