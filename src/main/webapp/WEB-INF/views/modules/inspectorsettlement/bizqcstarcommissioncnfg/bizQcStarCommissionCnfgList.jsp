<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>质检员星级和提成设置管理</title>
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
		<li class="active"><a href="${ctx}/inspectorsettlement/bizqcstarcommissioncnfg/bizQcStarCommissionCnfg/list">质检员星级配置表列表</a></li>
		<shiro:hasPermission name="bizqcstarcommissioncnfg:bizQcStarCommissionCnfg:edit"><li><a href="${ctx}/inspectorsettlement/bizqcstarcommissioncnfg/bizQcStarCommissionCnfg/form">质检员星级配置表添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="bizQcStarCommissionCnfg" action="${ctx}/inspectorsettlement/bizqcstarcommissioncnfg/bizQcStarCommissionCnfg/list1" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>门店：</label>
				<form:select path="storeId" class="input-medium needClear">
					<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>新老房：</label>
				<form:select path="isOldNew" class="input-medium needClear">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('biz_is_new_house')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>房屋类型：</label>
				<form:select path="houseType" class="input-medium needClear">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('emp_house_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
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
				<th>门店</th>
				<th>新老房</th>
				<th>房屋类型</th>
				<th>星级</th>
				<th>提成金额</th>
				<th>中期提成比例</th>
				<th>竣工提成比例</th>
				<shiro:hasPermission name="bizqcstarcommissioncnfg:bizQcStarCommissionCnfg:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="bizQcStarCommissionCnfg">
			<tr>
				<td><a href="${ctx}/bizqcstarcommissioncnfg/bizQcStarCommissionCnfg/form?id=${bizQcStarCommissionCnfg.id}">
					${fns:getStoreLabel(bizQcStarCommissionCnfg.storeId, '')}
				</a></td>
				<td>
					${fns:getDictLabel(bizQcStarCommissionCnfg.isOldNew, 'biz_is_new_house', '')}
				</td>
				<td>
					${fns:getDictLabel(bizQcStarCommissionCnfg.houseType, 'emp_house_type', '')}
				</td>
				<td>
					${fns:getDictLabel(bizQcStarCommissionCnfg.starLevel, 'manager_star', '')}
				</td>
				<td>
					${bizQcStarCommissionCnfg.commissionAmount}
				</td>
				<td>
					${bizQcStarCommissionCnfg.commissionRateMidway}%
				</td>
				<td>
					${bizQcStarCommissionCnfg.commissionRateComplete}%
				</td>
				<shiro:hasPermission name="bizqcstarcommissioncnfg:bizQcStarCommissionCnfg:edit"><td>
    				<a href="${ctx}/inspectorsettlement/bizqcstarcommissioncnfg/bizQcStarCommissionCnfg/form?id=${bizQcStarCommissionCnfg.id}">修改</a>
					<a href="${ctx}/inspectorsettlement/bizqcstarcommissioncnfg/bizQcStarCommissionCnfg/delete?id=${bizQcStarCommissionCnfg.id}">
						<c:if test="${bizQcStarCommissionCnfg.isEnabled=='0' }"><span style="color:#00EC00;">启用</span></c:if>
						<c:if test="${bizQcStarCommissionCnfg.isEnabled=='1' }"><span style="color:red;">停用</span></c:if>
					</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>