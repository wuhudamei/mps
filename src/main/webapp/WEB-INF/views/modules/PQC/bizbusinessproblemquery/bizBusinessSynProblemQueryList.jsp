<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>约检问题管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
            findInfo();
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
        <%--function findEngineListByStoreIdAndProjectMode(){--%>
            <%--var html3 = '<option value=""></option>';--%>
            <%--var storeId = $("#storeId").val();--%>
            <%--var projectModeValue = $("#projectMode").val();--%>
            <%--if (storeId=="" ||projectModeValue=="" ||undefined==storeId ||undefined==projectModeValue) {--%>
                <%--return;--%>
            <%--}--%>
            <%--//根据门店个,工程模式    动态加载工程区域--%>
            <%--$.ajax({--%>

                <%--url : "${ctx}/order/order/findEngineDepartmentBystoreIdAndProjectMode?storeId="--%>
                <%--+ storeId + "&projectModeValue=" + projectModeValue,--%>
                <%--type : "get",--%>
                <%--success : function(data) {--%>
                    <%--if (null!= data && data.length > 0) {--%>

                        <%--for (var v = 0; v < data.length; v++) {--%>
                            <%--html3 +='<option value="'+data[v].engineDepartId+'">'+data[v].engineDepartName+'</option>'--%>
                        <%--}--%>

                        <%--$("#engineDepartId").html(html3);--%>
                    <%--} else {--%>
                        <%--$("#engineDepartId").html(html3);--%>
                    <%--}--%>

                <%--}--%>

            <%--});--%>
        <%--}--%>


	</script>
</head>
<body>
<c:set var="user" value="${fns:getUser()}"></c:set>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/bizbusinessproblemsynquery/bizBusinessProblemQuery/list">项目经理约检问题统计</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="bizBusinessSynProblemQuery" action="${ctx}/bizbusinessproblemsynquery/bizBusinessProblemQuery/list" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>门店：</label>
				<form:select path="storeId" class="input-medium needClear" >
					<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label class="control-label">工程模式：</label>


				<c:if test="${user.projectMode ==3}">

					<form:select path="projectMode" class="input-medium needClear" >
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
			<%--<li><label>区域：</label>--%>
				<%--<form:select path="engineDepartId" class="input-medium needClear" >--%>
					<%--<form:options items="${fns:getEngineListWithUserConditions()}"   itemLabel="label" itemValue="value" htmlEscape="false"/>--%>
				<%--</form:select>--%>
			<%--</li>--%>


			<li><label>问题上报日期：</label>
				<input name="start" id="issueReportStart" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					   value="<fmt:formatDate value="${bizBusinessProblemQuery.start}" pattern="yyyy-MM-dd"/>"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'issueReportEnd\')}',isShowClear:true});"/> 至
				<input name="end" id="issueReportEnd" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					   value="<fmt:formatDate value="${bizBusinessProblemQuery.end}" pattern="yyyy-MM-dd"/>"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'issueReportStart\')}',isShowClear:true});"/>
			</li>

			<li><label>项目经理：</label>
			<form:input path="realName" htmlEscape="false" maxlength="11" class="input-medium"/>
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
				<%--<th>大区</th>--%>

				<th>项目经理</th>
				<th>项目经理手机号</th>
				<th>被上报次数</th>

				<shiro:hasPermission name="bizbusinessproblemquery:bizBusinessProblemQuery:view"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="bizBusinessProblemQuery">
			<tr>
				<td>${fns:getStoreLabel(bizBusinessProblemQuery.storeId, '')}</td>
				<td>${fns:getDictLabel(bizBusinessProblemQuery.projectMode,'project_mode' , '')}</td>

				<%--<td>${bizBusinessProblemQuery.engineDepartName}</td>--%>
				<td>${bizBusinessProblemQuery.realName}</td>
				<td>${bizBusinessProblemQuery.managerPhone}</td>
				<td>${bizBusinessProblemQuery.managerCount}</td>



				<shiro:hasPermission name="bizbusinessproblemquery:bizBusinessProblemQuery:view"><td>
    				<a href="${ctx}/bizbusinessproblemsynquery/bizBusinessProblemQuery/form?managerId=${bizBusinessProblemQuery.managerId}">查看详情</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>