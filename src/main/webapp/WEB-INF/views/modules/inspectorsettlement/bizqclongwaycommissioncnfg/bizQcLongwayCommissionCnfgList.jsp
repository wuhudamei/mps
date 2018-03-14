<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>远程费提成金额设置管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
			var a = $("#panduan").val();
			if(a == "1"){
				alertx("保存远程费提成金额设置成功")
				$("#panduan").val("2");
			}else if(a == "0"){
				alertx("远程费提成金额已存在,保存失败")
				$("#panduan").val("2");
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
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/inspectorsettlement/bizqclongwaycommissioncnfg/bizQcLongwayCommissionCnfg/list">远程费提成金额设置列表</a></li>
		<shiro:hasPermission name="bizqclongwaycommissioncnfg:bizQcLongwayCommissionCnfg:edit"><li><a href="${ctx}/inspectorsettlement/bizqclongwaycommissioncnfg/bizQcLongwayCommissionCnfg/form">远程费提成金额设置添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="bizQcLongwayCommissionCnfg" action="${ctx}/inspectorsettlement/bizqclongwaycommissioncnfg/bizQcLongwayCommissionCnfg/list1" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<div hidden="hidden">
			<input type="text" hidden="hidden" id="panduan" value="${panduan }"/>
		</div>
		<ul class="ul-form">
			<li><label>门店：</label>
				<form:select path="storeId" class="input-medium needClear">
					<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
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
				<th>远程费金额</th>
				<th>中期提成比例</th>
				<th>竣工提成比例</th>
				<shiro:hasPermission name="bizqclongwaycommissioncnfg:bizQcLongwayCommissionCnfg:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="bizQcLongwayCommissionCnfg">
			<tr>
				<td>
					${fns:getStoreLabel(bizQcLongwayCommissionCnfg.storeId, '')}
				</td>
				<td>
					${bizQcLongwayCommissionCnfg.commissionAmount}
				</td>
				<td>
					${bizQcLongwayCommissionCnfg.commissionRateMidway}%
				</td>
				<td>
					${bizQcLongwayCommissionCnfg.commissionRateComplete}%
				</td>
				<shiro:hasPermission name="bizqclongwaycommissioncnfg:bizQcLongwayCommissionCnfg:edit"><td>
    				<a href="${ctx}/inspectorsettlement/bizqclongwaycommissioncnfg/bizQcLongwayCommissionCnfg/form?id=${bizQcLongwayCommissionCnfg.id}">修改</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>