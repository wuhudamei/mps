<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>材料类订单汇总查询</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		 $(document).ready(function() {
             getDepartments();
			$("tbody>tr").click(function(){
				 $(this).find('td').css('background',"orange").parents('tr').siblings().find('td').css('background',"#f5f5f5");
			});	
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
		function exportExcel(){
	        $("#searchForm").attr("action", "${ctx}/bizMaterialSortedByOrder/materialSandAndCementByOrder/export");
	        $("#searchForm").submit();
	        $("#searchForm").attr("action", "${ctx}/bizMaterialSortedByOrder/materialSandAndCementByOrder/SandAndCementList");
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
                             if('${bizMaterialsSandAndCementByOrder.enginDepartId}' == data[i].value){
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

         // --全选框被单击---
         function ChkAllClick(sonName, cbAllId){
             var arrSon = document.getElementsByName(sonName);
             var cbAll = document.getElementById(cbAllId);
             var tempState=cbAll.checked;
             for(var i=0;i<arrSon.length;i++) {
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

             var obj=document.getElementsByName('status');
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
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/bizMaterialSortedByOrder/materialSandAndCementByOrder/SandAndCementList">订单沙子水泥统计</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="bizMaterialsSandAndCementByOrder" action="${ctx}/bizMaterialSortedByOrder/materialSandAndCementByOrder/SandAndCementList" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>门店：</label>
				<form:select path="storeId" class="input-medium needClear" onchange="getDepartments()">
					<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>区域：</label>
				<form:select path="enginDepartId" class="input-medium needClear">
					<form:option value="" label=""/>
					<form:option value="${bizMaterialsSandAndCementByOrder.enginDepartId }" label="${bizMaterialsSandAndCementByOrder.enginDepartName }" />
				</form:select>
			</li>
			<li><label>订单编号：</label>
				<form:input path="orderNumber" htmlEscape="false" maxlength="64" class="input-medium needClear"/>
			</li>
			<li><label>项目经理：</label>
				<form:input path="itemManager" htmlEscape="false" maxlength="64" class="input-medium needClear"/>
			</li>
			<li><label style="width: 150px;">项目经理收货日期 ：</label>
				<input name="finalReceiveTimeBegin" type="text" id="finalReceiveTimeBegin" readonly="readonly" maxlength="20" class="input-medium Wdate needClear"
					value="<fmt:formatDate value="${bizMaterialsSandAndCementByOrder.finalReceiveTimeBegin}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'finalReceiveTimeEnd\')}',isShowClear:false});"/> - 
				<input name="finalReceiveTimeEnd" type="text" id="finalReceiveTimeEnd" readonly="readonly" maxlength="20" class="input-medium Wdate needClear"
					value="<fmt:formatDate value="${bizMaterialsSandAndCementByOrder.finalReceiveTimeEnd}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'finalReceiveTimeBegin\')}',isShowClear:false});"/>
			</li>
			<li><label style="width: 150px;">项目经理申请日期 ：</label>
				<input name="applyTimeBegin" type="text" id="applyTimeBegin" readonly="readonly" maxlength="20" class="input-medium Wdate needClear"
					   value="<fmt:formatDate value="${bizMaterialsSandAndCementByOrder.applyTimeBegin}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'applyTimeEnd\')}',isShowClear:false});"/> -
				<input name="applyTimeEnd" type="text" id="applyTimeEnd" readonly="readonly" maxlength="20" class="input-medium Wdate needClear"
					   value="<fmt:formatDate value="${bizMaterialsSandAndCementByOrder.applyTimeEnd}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'applyTimeBegin\')}',isShowClear:false});"/>
			</li>
			<li><label>客户姓名：</label>
				<form:input path="customerName" htmlEscape="false" maxlength="64" class="input-medium needClear"/>
			</li>
			<li ><label>采购单状态：</label>
				<input name="chkAll" id="chkAll" title="全部" onclick="ChkAllClick('status','chkAll')" type="checkbox" />全部
				<c:forEach items="${listStatus}" var="dict">
					<input type="checkbox" name="status" id="status" value="${dict.value}"  onclick="ChkSonClick('status','chkAll')"  <c:if test="${fns:checkIsExits(bizMaterialsSandAndCementByOrder.status,dict.value)}"> checked="checked"</c:if>/>${dict.label}&nbsp;
				</c:forEach>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="btns"><input class="btn btn-primary clearBtn" type="button" value="清空" onclick="clearCondition()"/></li>
			<li class="btns"><input class="btn btn-primary" type="button" value="导出" onclick="exportExcel()"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>门店</th>
				<th>区域</th>
				<th>订单编号 </th>
				<th>客户姓名</th>
				<th>项目经理</th>
				<th>申请沙子水泥次数</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="bizPurchaseVo">
			<tr>
				<td>
					${fns:getStoreLabel(bizPurchaseVo.storeId, '')}
				</td>
				<td>
					${bizPurchaseVo.enginDepartName}
				</td>
				<td>
					${bizPurchaseVo.orderNumber}
				</td>
				<td>
					${bizPurchaseVo.customerName}
				</td>
				<td>
					${bizPurchaseVo.itemManager }
				</td>
				<td>
					${bizPurchaseVo.applyTimes }
				</td>
				<td>
					<a href="${ctx}/bizMaterialSortedByOrder/materialSandAndCementByOrder/detail?orderId=${bizPurchaseVo.orderId}">
							详情
					</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>