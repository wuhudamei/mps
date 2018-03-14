	<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>材料统计表</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		
		//查询
		function searchButton(){
			var storeId = $("#storeId").val();
			var projectMode = $("#projectMode").val();
			var beginDateTime = $("#beginDateTime").val();
			var endDateTime = $("#endDateTime").val();
			
			if(null==storeId || storeId==""){
				alertx("请选择门店");
				return false;
			}
			if(null==projectMode || projectMode==""){
				alertx("请选择工程模式");
				return false;
			}
			if(null==beginDateTime || beginDateTime==""){
				alertx("请选择日期开始时间");
				return false;
			}
			if(null==endDateTime || endDateTime==""){
				alertx("请选择日期结束时间");
				return false;
			}
			
			 loading("订单查询中...")
             $("#searchForm").submit();
		}
		
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/purchaseStatistics/purchaseStatistics/preList">材料统计表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="purchaseStatistics" action="${ctx}/purchaseStatistics/purchaseStatistics/list" method="post" class="breadcrumb form-search">
		<ul class="ul-form">
			
			<li><label>门店：</label>
				<form:select path="storeId" class="input-medium needClear">
					<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>工程模式：</label>
				<c:if test="${empty gongcheng}">
					<form:select path="projectMode" class="input-medium" disabled="true" >
						<form:option value="" label=""/>
						<form:options items="${fns:getDictList('project_mode')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
				<c:if test="${!empty gongcheng}">
					<form:select path="projectMode" class="input-medium needClear">
						<form:option value="" label=""/>
						<form:options items="${fns:getDictList('project_mode')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
					
			</li>
			
			<li><label>日期时间：</label>
				<input name="beginDateTime" id="beginDateTime" type="text" readonly="readonly" maxlength="20" class="input-medium needClear Wdate"
					value="<fmt:formatDate value="${purchaseStatistics.beginDateTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'endDateTime\')}',isShowClear:true});"/> 至  
				<input name="endDateTime" id="endDateTime" type="text" readonly="readonly" maxlength="20" class="input-medium needClear Wdate"
					value="<fmt:formatDate value="${purchaseStatistics.endDateTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'beginDateTime\')}',isShowClear:true});"/>
			</li>
			
			<li class="btns"><input class="btn btn-primary" type="button" value="查询" onclick="searchButton()"/></li>
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
				<th>项</th>
				<th>数值</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${list}" var="purchaseStatistics">
			
			<!--  1.辅料辅料辅料辅料辅料辅料辅料辅料辅料辅料辅料辅料辅料辅料辅料辅料        -->
			<tr>
				<td rowspan="16">
					${purchaseStatistics.storeName}
				</td>
				<td  rowspan="16">
					${purchaseStatistics.projectModeName}
				</td>
				<td>
					辅料发货申请单数
				</td>
				<td>
					<c:if test="${not empty purchaseStatistics.auxiliaryApplyCount}">${purchaseStatistics.auxiliaryApplyCount}</c:if>
					<c:if test="${empty purchaseStatistics.auxiliaryApplyCount}">0</c:if>
				</td>
			</tr>
			<tr>
				<td>
					辅料转供应商单数
				</td>
				<td>
					<c:if test="${not empty purchaseStatistics.auxiliaryTransferSupplierCount}">${purchaseStatistics.auxiliaryTransferSupplierCount}</c:if>
					<c:if test="${empty purchaseStatistics.auxiliaryTransferSupplierCount}">0</c:if>
				</td>
			</tr>
			<tr>
				<td>
					辅料收货单数
				</td>
				<td>
					<c:if test="${not empty purchaseStatistics.auxiliaryReceiveCount}">${purchaseStatistics.auxiliaryReceiveCount}</c:if>
					<c:if test="${empty purchaseStatistics.auxiliaryReceiveCount}">0</c:if>
				</td>
			</tr>
			
			
			<!--  2.沙子水泥沙子水泥沙子水泥沙子水泥沙子水泥沙子水泥沙子水泥沙子水泥沙子水泥        -->
			<tr>
				<td>
					沙子水泥发货申请单数
				</td>
				<td>
					<c:if test="${not empty purchaseStatistics.sandApplyCount}">${purchaseStatistics.sandApplyCount}</c:if>
					<c:if test="${empty purchaseStatistics.sandApplyCount}">0</c:if>
				</td>
			</tr>
			<tr>
				<td>
					沙子水泥转供应商单数
				</td>
				<td>
					<c:if test="${not empty purchaseStatistics.sandTransferSupplierCount}">${purchaseStatistics.sandTransferSupplierCount}</c:if>
					<c:if test="${empty purchaseStatistics.sandTransferSupplierCount}">0</c:if>
				</td>
			</tr>
			<tr>
				<td>
					沙子水泥收货单数
				</td>
				<td>
					<c:if test="${not empty purchaseStatistics.sandReceiveCount}">${purchaseStatistics.sandReceiveCount}</c:if>
					<c:if test="${empty purchaseStatistics.sandReceiveCount}">0</c:if>
				</td>
			</tr>
			
			
			<!--  3.标化标化标化标化标化标化标化标化标化标化标化标化标化标化标化标化标化标化标化        -->
			<tr>
				<td>
					标化申请单数
				</td>
				<td>
					<c:if test="${not empty purchaseStatistics.standardApplyCount}">${purchaseStatistics.standardApplyCount}</c:if>
					<c:if test="${empty purchaseStatistics.standardApplyCount}">0</c:if>
				</td>
			</tr>
			<tr>
				<td>
					标化领取单数
				</td>
				<td>
					<c:if test="${not empty purchaseStatistics.standardReceiveCount}">${purchaseStatistics.standardReceiveCount}</c:if>
					<c:if test="${empty purchaseStatistics.standardReceiveCount}">0</c:if>
				</td>
			</tr>
			
			
			<!--  4.筒灯灯带筒灯灯带筒灯灯带筒灯灯带筒灯灯带筒灯灯带筒灯灯带筒灯灯带筒灯灯带筒灯灯带       -->
			<tr>
				<td>
					筒灯灯带申请单数
				</td>
				<td>
					<c:if test="${not empty purchaseStatistics.downlightApplyCount}">${purchaseStatistics.downlightApplyCount}</c:if>
					<c:if test="${empty purchaseStatistics.downlightApplyCount}">0</c:if>
				</td>
			</tr>
			<tr>
				<td>
					筒灯灯带领取单数
				</td>
				<td>
					<c:if test="${not empty purchaseStatistics.downlightReceiveCount}">${purchaseStatistics.downlightReceiveCount}</c:if>
					<c:if test="${empty purchaseStatistics.downlightReceiveCount}">0</c:if>
				</td>
			</tr>
			
			
			<!--  5.墙地砖墙地砖墙地砖墙地砖墙地砖墙地砖墙地砖墙地砖墙地砖墙地砖墙地砖墙地砖墙地砖墙地砖       -->
			<tr>
				<td>
					墙地砖发货申请单数
				</td>
				<td>
					<c:if test="${not empty purchaseStatistics.wallFloorApplyCount}">${purchaseStatistics.wallFloorApplyCount}</c:if>
					<c:if test="${empty purchaseStatistics.wallFloorApplyCount}">0</c:if>
				</td>
			</tr>
			<tr>
				<td>
					墙地砖转供应商单数
				</td>
				<td>
					<c:if test="${not empty purchaseStatistics.wallFloorTransferSupplierCount}">${purchaseStatistics.wallFloorTransferSupplierCount}</c:if>
					<c:if test="${empty purchaseStatistics.wallFloorTransferSupplierCount}">0</c:if>
				</td>
			</tr>
			<tr>
				<td>
					墙地砖收货单数
				</td>
				<td>
					<c:if test="${not empty purchaseStatistics.wallFloorReceiveCount}">${purchaseStatistics.wallFloorReceiveCount}</c:if>
					<c:if test="${empty purchaseStatistics.wallFloorReceiveCount}">0</c:if>
				</td>
			</tr>
			
			
			<!--  6.开关面板开关面板开关面板开关面板开关面板开关面板开关面板开关面板开关面板开关面板开关面板       -->
			<tr>
				<td>
					开关面板发货申请单数
				</td>
				<td>
					<c:if test="${not empty purchaseStatistics.mainPanelApplyCount}">${purchaseStatistics.mainPanelApplyCount}</c:if>
					<c:if test="${empty purchaseStatistics.mainPanelApplyCount}">0</c:if>
				</td>
			</tr>
			<tr>
				<td>
					开关面板转供应商单数
				</td>
				<td>
					<c:if test="${not empty purchaseStatistics.mainPanelTransferSupplierCount}">${purchaseStatistics.mainPanelTransferSupplierCount}</c:if>
					<c:if test="${empty purchaseStatistics.mainPanelTransferSupplierCount}">0</c:if>
				</td>
			</tr>
			<tr>
				<td>
					开关面板收货单数
				</td>
				<td>
					<c:if test="${not empty purchaseStatistics.mainPanelReceiveCount}">${purchaseStatistics.mainPanelReceiveCount}</c:if>
					<c:if test="${empty purchaseStatistics.mainPanelReceiveCount}">0</c:if>
				</td>
			</tr>
			
			
			
		</c:forEach>
		</tbody>
	</table>
</body>
</html>