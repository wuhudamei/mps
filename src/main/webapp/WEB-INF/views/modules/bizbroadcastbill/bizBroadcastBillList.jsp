<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>播报管理</title>
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
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/bizbroadcastbill/bizBroadcastBill/">播报列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="bizBroadcastBill" action="${ctx}/bizbroadcastbill/bizBroadcastBill/list1" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
		<li><label>门店：</label>
				<form:select path="storeId" class="input-medium needClear">
					<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
		<li><label>播报类型：</label>
				<form:select path="broadcastName" class="input-medium needClear">
					<form:option value="" label=""/>
					<form:option value="设计交底" label="设计交底"/>
					<form:option value="确认开工" label="确认开工"/>
				<c:forEach items="${packNameList}" var="broadcast">
						<form:option value="${broadcast}">${broadcast}</form:option>
					</c:forEach>
				</form:select>
			</li>
			<li><label>客户姓名：</label>
				<form:input path="customerName" htmlEscape="false" maxlength="16" class="input-medium"/>
			</li>
			<li><label>客户地址：</label>
				<form:input path="communityName" htmlEscape="false" maxlength="16" class="input-medium"/>
			</li>
			
			<li><label>项目经理：</label>
				<form:input path="managerName" htmlEscape="false" maxlength="12" class="input-medium"/>
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
				<th>订单号</th>
				<th>客户姓名</th>
				<th>施工地点</th>
				<th>项目经理</th>
				<th>播报类型</th>
				<th>播报单状态</th>
				<th>播报图片数量</th>
				<th>审核人</th>
				<th>申请播报时间</th>
				<shiro:hasPermission name="bizbroadcastbill:bizBroadcastBill:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="broadcast">
			<tr>
				<td>
					${fns:getStoreLabel(broadcast.storeId, '')}
				</td>
				<td>
					${broadcast.orderNumber}
				</td>
				<td>
					${broadcast.customerName}
				</td>
				<td>
					${broadcast.communityName}-${broadcast.buildNumber}-${broadcast.buildUnit}-${broadcast.buildRoom}
				</td>
				<td>
					${broadcast.managerName}
				</td>
				<td>
					${broadcast.broadcastName}
				</td>
				<td>
				<c:if test="${broadcast.status==10}">
				已生成图片播报单
				</c:if>
					
				</td>
				<td>
					${broadcast.picCount}
				</td>
				<td>
					${broadcast.applyName}
				</td>
				<td>
					<fmt:formatDate value="${broadcast.applyDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="bizbroadcastbill:bizBroadcastBill:edit"><td>
    				<a href="${ctx}/bizbroadcastbill/bizBroadcastBill/form?broadcastId=${broadcast.broadcastId}">详情</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>