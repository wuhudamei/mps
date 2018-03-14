<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>材料进度看板</title>
	<meta name="decorator" content="default"/>
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
<c:set var="user" value="${fns:getUser()}"></c:set>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx }/materialScheduleQuery/list">材料进度看板</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="materialScheduleQueryEntity" action="${ctx }/materialScheduleQuery/listInfo" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>门店：</label>
				<form:select path="storeId" class="input-medium needClear">
					<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			
			<li><label class="control-label">工程模式：</label>
		
			
			<c:if test="${user.projectMode ==3}">
			
			<form:select path="projectMode" class="input-medium needClear">
							<form:option value="" label="" />
					<form:options items="${fns:getDictList('project_mode')}"
						itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
			</c:if>
			<c:if test="${user.projectMode !=3}">
		
			<form:select path="projectMode" class="input-medium needClear">
					
					<form:option label="${fns:getDictLabel(user.projectMode, 'project_mode', '')}"
					value="${user.projectMode}"/>
				</form:select>
			</c:if>
			</li>
			
			
			
			<li><label>订单编号：</label>
				<form:input path="orderNumber" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			
			<li><label>客户姓名：</label>
				<form:input path="customerName" htmlEscape="false" maxlength="16" class="input-medium"/>
			</li>
			<li><label>采购单编号：</label>
				<form:input path="purchaseCode" htmlEscape="false" maxlength="16" class="input-medium"/>
			</li>
			<li><label style="width:120px;">项目经理申请日期：</label>
				<input name="beginApplyTime" type="text" readonly="readonly" id="beginApplyTime" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${materialScheduleQueryEntity.beginApplyTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'endAapplyTime\')}',isShowClear:false});"/> - 
				<input name="endAapplyTime" type="text" readonly="readonly" id="endAapplyTime" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${materialScheduleQueryEntity.endAapplyTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'beginApplyTime\')}',isShowClear:false});"/>
			</li>
			<li><label>转单日期：</label>
				<input name="beginTransferDate" type="text" readonly="readonly" id="beginTransferDate" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${materialScheduleQueryEntity.beginTransferDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'endTransferDate\')}',isShowClear:false});"/> - 
				<input name="endTransferDate" type="text" readonly="readonly" id="endTransferDate" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${materialScheduleQueryEntity.endTransferDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'beginTransferDate\')}',isShowClear:false});"/>
			</li>
			<li><label>收货完成日期：</label>
				<input name="beginCompletionDate" type="text" readonly="readonly" id="beginCompletionDate" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${materialScheduleQueryEntity.beginCompletionDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'endCompletionDate\')}',isShowClear:false});"/> - 
				<input name="endCompletionDate" type="text" readonly="readonly" id="endCompletionDate" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${materialScheduleQueryEntity.endCompletionDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'beginCompletionDate\')}',isShowClear:false});"/>
			</li>
			<li style="width: 100%"><label>采购单状态：</label>
				<input name="chkAll" id="chkAll" title="全部" onclick="ChkAllClick('status','chkAll')" type="checkbox" />全部
			</li>
			<li style="height: 80px">
			<%-- 	<form:checkboxes path="status" items="${fns:getDictList('purchase_status')}" onclick="ChkSonClick('status','chkAll')" itemLabel="label" itemValue="value" htmlEscape="false" class=" needClear" /> --%>
			<c:forEach items="${fns:getDictList('purchase_status')}" var="dict">
					<input type="checkbox" name="status" id="status" value="${dict.value}"  onclick="ChkSonClick('status','chkAll')"  <c:if test="${fns:checkIsExits(materialScheduleQueryEntity.status,dict.value)}"> checked="checked"</c:if>/>${dict.label}&nbsp;
				</c:forEach>
			
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
				<th>门店</th>
				<th>工程模式</th>
				<th>订单编号</th>
				<th>顾客</th>
				<th>项目经理</th>
				<th>材料类型</th>
				<th>采购单编号</th>
				<th>采购单状态</th>
				<th>项目经理申请日期</th>
				<th>转单日期</th>
				<th>收货完成日期</th>
				<th>收货次数</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="purchase" varStatus="status">
			<tr>
				<td>
					${fns:getStoreLabel(purchase.storeId, '')}
				</td>
				<td>${fns:getDictLabel(purchase.projectMode,'project_mode', '')}</td>
				
				<td>
					${purchase.orderNumber }
				</td>
				<td>
					${purchase.communityName }-${purchase.buildNumber }-${purchase.buildUnit }-${purchase.buildRoom }-${purchase.customerName }
				</td>
				<td>
					${purchase.itemManager }
				</td>
				<td>
				<c:if test="${purchase.purchaseType == 1}">
				辅材
				</c:if>
				<c:if test="${purchase.purchaseType == 2}">
				开关面板
				</c:if>
				<c:if test="${purchase.purchaseType == 5}">
				墙地砖
				</c:if>
					
				</td>
				<td>
					${purchase.purchaseCode }
				</td>
				<td>
					${fns:getDictLabel(purchase.status,'purchase_status', '')}
				</td>
				<td>
					<fmt:formatDate value="${purchase.applyTime }" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${purchase.transferDate }" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${purchase.recieveGoodsDate }" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${purchase.acceptMaterialCount }
				</td>
				<td>
				<shiro:hasPermission name="materialScheduleQuery:materialScheduleQuery:view">
				<c:if test="${purchase.purchaseType==1}">
				<a href="${ctx}/purchase/bizPurchase/details?id=${purchase.purchaseId}">详情</a>
				</c:if>
				<c:if test="${purchase.purchaseType==2}">
    				<a href="${ctx}/purchase/bizPurchaseMainPanel/mainPanelDetails?id=${purchase.purchaseId}">详情</a>
				
				</c:if>
				<c:if test="${purchase.purchaseType==5}">
    				<a href="${ctx}/purchase/bizPurchaseMainTile/mainTileDetails?id=${purchase.purchaseId}">详情</a>
				
				</c:if>
    				
    			
    			</shiro:hasPermission>
    			<td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>