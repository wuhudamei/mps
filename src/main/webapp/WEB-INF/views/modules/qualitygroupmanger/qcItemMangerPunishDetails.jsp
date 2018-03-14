<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>项目经理违规次数详情</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
            findEngineListByStoreIdAndProjectMode();
            $("#searchForm").validate({
                errorPlacement: function(error, element) {
                    if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
                        error.appendTo(element.parent().parent());
                    } else {
                        error.insertAfter(element);
                    }
                }
            })
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
		<li class="active"><a href="${ctx}/qualitygroupmanger/qcItemGroupManger/listManagerall">返回</a></li>
		<shiro:hasPermission name="qualitygroupmanger:qcItemGroupManger:edit"><li><a href="${ctx}/qualitygroupmanger/qcItemGroupManger/form">检查项工人组和项目经理添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="qcItemGroupManger" action="${ctx}/qualitygroupmanger/qcItemGroupManger/listManagerall" method="post" class="breadcrumb form-search">
			<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>项目经理:</label>
					${qcItemGroupManger.mnagerPerson}
			</li>
			<li><label>罚款次数:</label>
				${qcItemGroupManger.mnagerPersonPunishCount} 次
			</li>


			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>质检员提交报告时间</th>
				<th>工程模式</th>
				<th>区域</th>
				<th>小区</th>
				<th>客户名称</th>
				<th>项目经理</th>
				<th>质检员</th>
				<th>检查分类</th>
				<th>检查项</th>
				<th>违规形式</th>
				<th>被罚项目经理</th>
				<th>罚款金额</th>
				<th>扣除分数</th>
				<shiro:hasPermission name="qualitygroupmanger:qcItemGroupManger:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="qcItemGroupManger">
			<tr>
				<td>

					<fmt:formatDate value="${qcItemGroupManger.quCreateDate}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
						${qcItemGroupManger.projectModeName}
				</td>
				<td>
						${qcItemGroupManger.enginDepartName}
				</td>
				<td>
						${qcItemGroupManger.customerAddr}
				</td>
				<td>
						${qcItemGroupManger.customerName}
				</td>
				<td>
						${qcItemGroupManger.mnagerPerson}
				</td>
				<td>
						${qcItemGroupManger.orderInspector}
				</td>
				<td>
						${qcItemGroupManger.qcCheckKindName}
				</td>
				<td>
						${qcItemGroupManger.qcCheckItemName}
				</td>
				<td>
						${qcItemGroupManger.breakDescribe}
				</td>
				<td>
						${qcItemGroupManger.mnagerPerson}</br>
						${qcItemGroupManger.mnagerPersonPhone}
				</td>
				<td>
						${qcItemGroupManger.punishMoneyAmountReal}
				</td>
				<td>
						${qcItemGroupManger.pmPunishScore}
				</td>

				<shiro:hasPermission name="qualitygroupmanger:qcItemGroupManger:edit"><td>
    				<a href="${ctx}/qualitygroupmanger/qcItemGroupManger/form?id=${qcItemGroupManger.id}">修改</a>
					<a href="${ctx}/qualitygroupmanger/qcItemGroupManger/delete?id=${qcItemGroupManger.id}" onclick="return confirmx('确认要删除该检查项工人组和项目经理吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>