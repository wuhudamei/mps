<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>问题分类管理</title>
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
		<li class="active"><a href="${ctx}/bizcomplaintproblemtype/bizComplaintProblemType/">问题分类列表</a></li>
		<shiro:hasPermission name="bizcomplaintproblemtype:bizComplaintProblemType:edit"><li><a href="${ctx}/bizcomplaintproblemtype/bizComplaintProblemType/form">问题分类添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="bizComplaintProblemType" action="${ctx}/bizcomplaintproblemtype/bizComplaintProblemType/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">

			<li><label>门店：</label>
				<form:select path="storeId" class="input-medium needClear" onchange="findEngineListByStoreIdAndProjectMode()" id="sel">
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
				<th>序号</th>
				<th>门店</th>
				<th>问题分类</th>
				<th>任务包</th>
				<th>处理人员</th>
				<th>状态</th>
				<shiro:hasPermission name="bizcomplaintproblemtype:bizComplaintProblemType:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="bizComplaintProblemType" varStatus="status">
			<tr>
				<td>
					${status.index+1}
				</td>
				<td>
						${fns:getStoreLabel(bizComplaintProblemType.storeId, '')}
				</td>



				<td>
						${bizComplaintProblemType.typeName}
				</td>
				<td>
						${bizComplaintProblemType.packName}
				</td>

				<td>
						${fns:getDictLabel(bizComplaintProblemType.dealPersonType, 'deal_person_type', '')}
				</td>

				<td>
						${fns:getDictLabel(bizComplaintProblemType.isEnabled, 'biz_enable_status', '')}
				</td>
				<shiro:hasPermission name="bizcomplaintproblemtype:bizComplaintProblemType:edit"><td>
    				<a href="${ctx}/bizcomplaintproblemtype/bizComplaintProblemType/form?id=${bizComplaintProblemType.id}">修改</a>
					<c:if test="${bizComplaintProblemType.isEnabled==1}">
						<a href="${ctx}/bizcomplaintproblemtype/bizComplaintProblemType/delete?id=${bizComplaintProblemType.id}&isEnabled=0" onclick="return confirmx('确认要停用该问题分类吗？', this.href)">停用</a>

					</c:if>

					<c:if test="${bizComplaintProblemType.isEnabled==0}">
						<a href="${ctx}/bizcomplaintproblemtype/bizComplaintProblemType/delete?id=${bizComplaintProblemType.id}&isEnabled=1" onclick="return confirmx('确认要启用该问题分类吗？', this.href)">启用</a>

					</c:if>

				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>