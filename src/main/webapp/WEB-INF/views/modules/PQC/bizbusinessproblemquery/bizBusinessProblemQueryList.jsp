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

        function findInfo(){
            var html3 = '<option value=""></option>';
            var html = '<option value=""></option>';
            var storeId = $("#storeId").val();
            var projectModeValue = $("#projectMode").val();
            if (storeId=="" ||projectModeValue=="" ||undefined==storeId ||undefined==projectModeValue) {
                return;
            }
            var issueTypeId = "${bizBusinessProblemQuery.issueTypeId}";
            var checkNodeId = "${bizBusinessProblemQuery.checkNodeId}";


            //根据门店个,工程模式    动态加载工程区域
            $.ajax({

                url : "${ctx}/bizbusinessproblemquery/bizBusinessProblemQuery/findInfoByStoreIdAndProjectMode?storeId="
                + storeId + "&projectMode=" + projectModeValue,
                type : "get",
                success : function(data) {
                    var issueTypeList =data.issueTypeList;
                    var qcNodeList =data.qcNodeList;
                    if (undefined!= issueTypeList && issueTypeList.length > 0) {


                        for (var v = 0; v < issueTypeList.length; v++) {

                            if(issueTypeId==issueTypeList[v].issueTypeId){

                                $("#s2id_issueTypeId").find(".select2-chosen").text(issueTypeList[v].typeName)
                                html3 +='<option value="'+issueTypeList[v].issueTypeId+'" selected="selected">'+issueTypeList[v].typeName+'</option>'
                            }else{
                                html3 +='<option value="'+issueTypeList[v].issueTypeId+'">'+issueTypeList[v].typeName+'</option>'

                            }

                        }

                        $("#issueTypeId").html(html3);
                    } else {
                        $("#issueTypeId").html(html3);
                    }



                    if (undefined!= qcNodeList && qcNodeList.length > 0) {

                        for (var v = 0; v < qcNodeList.length; v++) {
                            if(checkNodeId==qcNodeList[v].checkNodeId){
                                $("#s2id_checkNodeId").find(".select2-chosen").text(qcNodeList[v].qcCheckNodeName)
                                html +='<option value="'+qcNodeList[v].checkNodeId+'" selected="selected">'+qcNodeList[v].qcCheckNodeName+'</option>'

                            }else{
                                html +='<option value="'+qcNodeList[v].checkNodeId+'" >'+qcNodeList[v].qcCheckNodeName+'</option>'

                            }

                        }

                        $("#checkNodeId").html(html);
                    } else {
                        $("#checkNodeId").html(html);
                    }

                }

            });
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
<c:set var="user" value="${fns:getUser()}"></c:set>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/bizbusinessproblemquery/bizBusinessProblemQuery/">质检检查问题上报列表</a></li>
		<shiro:hasPermission name="bizbusinessproblemquery:bizBusinessProblemQuery:edit"><li><a href="${ctx}/bizbusinessproblemquery/bizBusinessProblemQuery/form">约检问题添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="bizBusinessProblemQuery" action="${ctx}/bizbusinessproblemquery/bizBusinessProblemQuery/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>门店：</label>
				<form:select path="storeId" class="input-medium needClear" onchange="findInfo()">
					<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label class="control-label">工程模式：</label>


				<c:if test="${user.projectMode ==3}">

					<form:select path="projectMode" class="input-medium needClear" onchange="findInfo()" >
						<form:option value="" label="" />
						<form:options items="${fns:getDictList('project_mode')}"
									  itemLabel="label" itemValue="value" htmlEscape="false" />
					</form:select>
				</c:if>
				<c:if test="${user.projectMode !=3}">

					<form:select path="projectMode" class="input-medium needClear" onchange="findInfo()">

						<form:option label="${fns:getDictLabel(user.projectMode, 'project_mode', '')}"
									 value="${user.projectMode}"/>
					</form:select>
				</c:if>
			</li>

			<li><label>约检日期：</label>
				<input name="start" id="beginSignContractDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					   value="<fmt:formatDate value="${bizBusinessProblemQuery.start}" pattern="yyyy-MM-dd"/>"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'endSignContractDate\')}',isShowClear:true});"/> 至
				<input name="end" id="endSignContractDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					   value="<fmt:formatDate value="${bizBusinessProblemQuery.end}" pattern="yyyy-MM-dd"/>"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'beginSignContractDate\')}',isShowClear:true});"/>
			</li>
			<li><label>质检员：</label>
				<form:input path="pqcName" htmlEscape="false" maxlength="11" class="input-medium"/>
			</li>
			<li><label>订单编号：</label>
				<form:input path="orderNumber" htmlEscape="false" maxlength="11" class="input-medium"/>
			</li>
			<li><label>客户名称：</label>
				<form:input path="customerName" htmlEscape="false" maxlength="11" class="input-medium"/>
			</li>
			<li><label>项目经理：</label>
			<form:input path="managerName" htmlEscape="false" maxlength="11" class="input-medium"/>
		</li>

			<li><label>问题上报日期：</label>
				<input name="issueReportStart" id="issueReportStart" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					   value="<fmt:formatDate value="${bizBusinessProblemQuery.issueReportStart}" pattern="yyyy-MM-dd"/>"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'issueReportEnd\')}',isShowClear:true});"/> 至
				<input name="issueReportEnd" id="issueReportEnd" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					   value="<fmt:formatDate value="${bizBusinessProblemQuery.issueReportEnd}" pattern="yyyy-MM-dd"/>"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'issueReportStart\')}',isShowClear:true});"/>
			</li>

			<li><label>约检节点：</label>
				<select class="input-medium needClear" name="checkNodeId" id="checkNodeId">



				</select>
			</li>

			<li><label>问题类型：</label>
				<select class="input-medium needClear" name="issueTypeId" id="issueTypeId">



				</select>
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
				<th>客户姓名</th>
				<th>客户地址</th>
				<th>项目经理</th>
				<th>质检员</th>
				<th>约检节点</th>
				<th>约检日期</th>
				<th>上报日期</th>
				<th>问题类型</th>

				<shiro:hasPermission name="bizbusinessproblemquery:bizBusinessProblemQuery:view"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="bizBusinessProblemQuery">
			<tr>
				<td>${fns:getStoreLabel(bizBusinessProblemQuery.storeId, '')}</td>
				<td>${fns:getDictLabel(bizBusinessProblemQuery.projectMode,'project_mode' , '')}</td>
				<td>${bizBusinessProblemQuery.orderNumber}</td>
				<td>${bizBusinessProblemQuery.customerName}</td>
				<td>${bizBusinessProblemQuery.customerInfo}</td>
				<td>${bizBusinessProblemQuery.managerName}</td>
				<td>${bizBusinessProblemQuery.pqcName}</td>
				<td>${bizBusinessProblemQuery.qcCheckNodeName}</td>
				<td>${bizBusinessProblemQuery.qcExpectCheckDate}</td>
				<td><fmt:formatDate value="${bizBusinessProblemQuery.problemCreateDate}" pattern="yyyy-MM-dd"></fmt:formatDate> </td>
				<td>${bizBusinessProblemQuery.typeName}</td>

				<shiro:hasPermission name="bizbusinessproblemquery:bizBusinessProblemQuery:view"><td>
    				<a href="${ctx}/bizbusinessproblemquery/bizBusinessProblemQuery/form?problemId=${bizBusinessProblemQuery.id}">查看</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>