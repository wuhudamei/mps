<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>工序管理</title>
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
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/procedure/bizProcedure/">工序列表</a></li>
		<shiro:hasPermission name="procedure:bizProcedure:edit"><li><a href="${ctx}/procedure/bizProcedure/form">工序添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="bizProcedure" action="${ctx}/procedure/bizProcedure/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>工序编号：</label>
				<form:input path="procedureNo" htmlEscape="false" maxlength="50" class="input-small　 needClear"/>
			</li>
			<li><label>工序名称：</label>
				<form:input path="procedureName" htmlEscape="false" maxlength="50" class="input-small　 needClear"/>
			</li>
<!-- 			<li><label>开始时间：</label> -->
<!-- 				<input name="beginDate" type="text" readonly="readonly" maxlength="20" class="input-small Wdate　 needClear" -->
<%-- 					value="<fmt:formatDate value="${bizProcedure.beginDate}" pattern="yyyy-MM-dd"/>" --%>
<!-- 					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"/> -->
<!-- 			</li> -->
<!-- 			<li><label>结束时间：</label> -->
<!-- 				<input name="endDate" type="text" readonly="readonly" maxlength="20" class="input-small Wdate　 needClear" -->
<%-- 					value="<fmt:formatDate value="${bizProcedure.endDate}" pattern="yyyy-MM-dd"/>" --%>
<!-- 					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"/> -->
<!-- 			</li> -->
			<li><label>是否其他：</label>
				<form:select path="isOtherFlag" class="input-small needClear">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="btns"><input class="btn btn-primary clearBtn" type="button" value="清空"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>工序编号</th>
				<th>工序名称</th>
				
				<th>是否其他</th>
				<th>计量单位</th>
				<th>工序工法</th>
				<th>启用标记</th>
				
				<shiro:hasPermission name="procedure:bizProcedure:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="bizProcedure">
			<tr>
				<td><a href="${ctx}/procedure/bizProcedure/form?id=${bizProcedure.id}">
					${bizProcedure.procedureNo}
				</a></td>
				<td>
					${bizProcedure.procedureName}
				</td>
				<!--td>
					${bizProcedure.createBy.id}
				</td>
				<td>
					<fmt:formatDate value="${bizProcedure.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td-->
				<td>
					${fns:getDictLabel(bizProcedure.isOtherFlag, 'yes_no', '')}
				</td>
				<td>
					${fns:getDictLabel(bizProcedure.measurementUnit, 'biz_unit', '')}
				</td>
				<td style="word-wrap:break-word;" width="200">
					${bizProcedure.remarks}
				</td>
				<td>
					${fns:getDictLabel(bizProcedure.isEnable, 'biz_enable_status', '')}
				</td>
				
				<shiro:hasPermission name="procedure:bizProcedure:edit"><td>
    				<a href="${ctx}/procedure/bizProcedure/form?id=${bizProcedure.id}">修改基本信息</a>
    				<a href="${ctx}/procedureprice/bizProcedurePrice/list?procedureNo=${bizProcedure.procedureNo}">修改价格</a>
					<a href="${ctx}/procedure/bizProcedure/delete?id=${bizProcedure.id}&isEnable=<c:if test="${bizProcedure.isEnable==1}">
							0
						</c:if>
						<c:if test="${bizProcedure.isEnable==0}">
							1
						</c:if>" onclick="return confirmx('确认要<c:if test="${bizProcedure.isEnable==1}">
								停用
								</c:if>
								<c:if test="${bizProcedure.isEnable==0}">
									启用
								</c:if>该工序吗？', this.href)">

                        <c:if test="${bizProcedure.isEnable==1}">
							停用
						</c:if>
						<c:if test="${bizProcedure.isEnable==0}">
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