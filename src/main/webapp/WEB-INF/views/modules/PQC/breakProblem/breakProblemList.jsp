<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>违规问题统计</title>
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
		
		//检验工程模式是否有值
		function checkProjectMode(){
			var projectMode = $("#projectMode").val();
			if(!projectMode){
				alert("必须选择工程模式！");
				return false;
			}else{
				return true;
				/* $("#searchForm").submit();//jquery提交form */
			}
		}


		function onSubmit(){
            $("#searchForm").attr("action","${ctx}/pqc/breakProblem/breakProblem/breakProblemList");
            $("#searchForm").submit();
            return true;
		}
		function exportDetailed(id){
            $("#qcCheckItemBreakId").val('');
            $("#searchForm").attr("action","${ctx}/pqc/breakProblem/breakProblem/exportDetailed");
            $("#searchForm").submit();
            return true;
		}
		function breakProblemDetails(id){
		    $("#qcCheckItemBreakId").val(id);
            $("#searchForm").attr("action","${ctx}/pqc/breakProblem/breakProblem/breakProblemDetails");
            $("#searchForm").submit();
            return true;
		}

        function searchFormEx(){
            $("#searchForm").attr("action","${ctx}/pqc/breakProblem/breakProblem/searchFormEx");
            $("#searchForm").submit();
            return true;
        }



        function clearCondition() {
            document.getElementById("searchForm").reset();

            var inputObjs = jQuery("#searchForm input[type='text']");
            for (var i = 0; i < inputObjs.length; i++) {
                var inputObj = inputObjs[i];
                inputObj.value = "";
            }

            var selectObjs = jQuery("#searchForm input[class='input-medium']");
            for (var i = 0; i < selectObjs.length; i++) {
                var selectObj = selectObjs[i];
                selectObj.value = "";
            }
            return true;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/pqc/breakProblem/breakProblem/list">违规问题统计</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="breakProblem" action="${ctx}/pqc/breakProblem/breakProblem/breakProblemList" method="post" class="breadcrumb form-search" onsubmit="return checkProjectMode();">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="qcCheckItemBreakId" name="qcCheckItemBreakId" type="hidden" value=""/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">

			<li><label>门店：</label>
				<form:select path="storeId" class="input-medium needClear">
					<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>

			<li><label class="control-label">工程模式：</label>

					<form:select path="projectMode" class="input-medium needClear">
						<form:option value="" label=""/>
						<form:options items="${fns:getDictList('project_mode')}"
									  itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
			</li>



			<li><label>提报告日期：</label>
				<input name="beginCheckDatetime" type="text" readonly="readonly" id="beginCheckDatetime" maxlength="20" class="input-medium Wdate needClear"
					value="<fmt:formatDate value="${breakProblem.beginCheckDatetime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'endCheckDatetime\')}',isShowClear:false});"/> - 
				<input name="endCheckDatetime" type="text" readonly="readonly" id="endCheckDatetime" maxlength="20" class="input-medium Wdate needClear"
					value="<fmt:formatDate value="${breakProblem.endCheckDatetime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'beginCheckDatetime\')}',isShowClear:false});"/>
			</li>
			<li class="btns"><input id="btnSubmit1" class="btn btn-primary"  type="button" onclick="onSubmit()" value="查询"/></li>
			<li class="btns"><input id="btnSubmit3" class="btn btn-primary " type="button" onclick="searchFormEx()"  value="导出"/></li>
			<li class="btns"><input id="btnSubmit4" class="btn btn-primary " type="button" onclick="exportDetailed()" value="导出全部"/></li>
			<li class="btns"><input class="btn btn-primary clearBtn" type="button" value="清空" onclick="clearCondition()"/></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>门店</th>
				<th>工程模式</th>
				<th>检查分类</th>
				<th>检查项</th>
				<th>违规形式</th>
				<th>出现次数</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="breakproblem" varStatus="status">
			<tr>
				<td>
					${fns:getStoreLabel(breakproblem.storeId, '')}
				</td>
				<td>
					${fns:getDictLabel(breakproblem.projectMode, 'project_mode', '')}
				</td>
				<td>
					${breakproblem.qcCheckKindName }
				</td>
				<td>
					${breakproblem.qcCheckItemName }
				</td>
				<td>
					${breakproblem.breakDescribe }
				</td>
				<td>
					<a href="#" onclick="breakProblemDetails(${breakproblem.qcCheckItemBreakId })">	${breakproblem.breakTimes }</a>

				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>