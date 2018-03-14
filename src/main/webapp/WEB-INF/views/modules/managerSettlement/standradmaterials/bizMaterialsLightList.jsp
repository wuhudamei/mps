<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>灯具和五金管理</title>
	<meta name="decorator" content="default"/>
	<style>
		.half{height: 50%;display: block;/* text-align:center; */box-sizing:border-box;}
		.half:nth-child(2){border-top:1px solid #ddd;}
	</style>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/standradmaterials/bizMaterialsLight/list">灯具和五金列表</a></li>
		<shiro:hasPermission name="standradmaterials:bizMaterialsLight:edit"><li><a href="${ctx}/standradmaterials/bizMaterialsLight/form">灯具和五金添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="bizMaterialsStandard" action="${ctx}/standradmaterials/bizMaterialsLight/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>门店：</label>
				<form:select path="storeId" class="input-medium">
					<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>门店</th>
				<th>物料类别 </th>
				<th>物料名称</th>
				<th>物料单位</th>
				<th>物料单价 </th>
				<th>建议申请数量</th>
				<th>面积区</th>
				<th>是否启用</th>
				<th>备注</th>
				<shiro:hasPermission name="standradmaterials:bizMaterialsLight:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="bizMaterialsStandard">
			<tr>
				<td  >
					${fns:getStoreLabel(bizMaterialsStandard.storeId, '')}
				</td>
				<td >
					${bizMaterialsStandard.materialsType}
				</td>
				<td >
					${bizMaterialsStandard.materialsName}
				</td>
				<td >
					${bizMaterialsStandard.materialsUnit}
				</td>
				<td >
					${bizMaterialsStandard.materialsPrice}
				</td>
				<td>
				<c:forEach items="${bizMaterialsStandard.bizMaterialsStandardNumberSquareList}"  var="squere" varStatus="stat">
					<span class="half ">
					${squere.numberRuleDescribe }
					</span>
				</c:forEach>
				</td>
				<td>
				<c:forEach items="${bizMaterialsStandard.bizMaterialsStandardNumberSquareList}"  var="squere" varStatus="stat">
					<span class="half">(${squere.squareMin }
					,${squere.squareMax })m<sup>2</sup>
					</span>
				</c:forEach>
				</td>
				<td >
					${fns:getDictLabel(bizMaterialsStandard.isEnabled, 'biz_enable_status', '')}
				</td>
				<td >
					${bizMaterialsStandard.remarks}
				</td>
				<shiro:hasPermission name="standradmaterials:bizMaterialsLight:edit"><td>
    				<a href="${ctx}/standradmaterials/bizMaterialsLight/form?id=${bizMaterialsStandard.id}">修改</a>
					<a href="${ctx}/standradmaterials/bizMaterialsLight/enable?id=${bizMaterialsStandard.id}">
						<c:if test="${bizMaterialsStandard.isEnabled=='1'}">
							停用
						</c:if>
						<c:if test="${bizMaterialsStandard.isEnabled=='0'}">
							启用
						</c:if>
					</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>