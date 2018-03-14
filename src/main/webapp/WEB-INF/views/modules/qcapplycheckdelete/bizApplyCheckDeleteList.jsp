<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>333管理</title>
	<meta name="decorator" content="default"/>
	<style>


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

        function deleteObj(obj,key){
	if(	confirm("你确定要删除吗")){

        $.ajax({

            url : "${ctx}/qcapplycheckdelete/bizApplyCheckDelete/delete",
            type: "post",
            data: {id:key},
            success : function(data) {

                if(1==data){
                    $(obj).parent().parent().remove();

                   alertx("删除成功");

                }else{
                   alertx("由于某些原因 失败了");

                }

            }

        });

    }
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
	<script type="text/javascript" src="${ctxStatic}/modules/settleJs/layer/layer.js"></script>
	<script src="${ctxStatic}/modules/settleJs/layer/extend/layer.ext.js"></script>
</head>

<body>
<c:set var="user" value="${fns:getUser()}"></c:set>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/qcapplycheckdelete/bizApplyCheckDelete/">约检信息管理列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="bizApplyCheckDelete" action="${ctx}/qcapplycheckdelete/bizApplyCheckDelete/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>门店：</label>
				<form:select path="storeId" class="input-medium needClear">
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

					<form:select path="projectMode" class="input-medium needClear" >

						<form:option label="${fns:getDictLabel(user.projectMode, 'project_mode', '')}"
									 value="${user.projectMode}"/>
					</form:select>
				</c:if>
			</li>

			<li><label>项目经理：</label>
				<form:input path="managerName" htmlEscape="false" maxlength="16" class="input-medium"/>
			</li>
			<li><label>客户姓名：</label>
				<form:input path="customerName" htmlEscape="false" maxlength="16" class="input-medium"/>
			</li>
			<li><label>质检姓名：</label>
				<form:input path="pqcName" htmlEscape="false" maxlength="16" class="input-medium"/>
			</li>
			<li><label>申请约检日期：</label>
				<input name="startDate" id="beginContractStartDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					   value="<fmt:formatDate value="${bizApplyCheckDelete.startDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'endContractStartDate\')}',isShowClear:true});"/> 至
				<input name="endDate" id="endContractStartDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					   value="<fmt:formatDate value="${bizApplyCheckDelete.endDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'beginContractStartDate\')}',isShowClear:true});"/>
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
				<th>申请约检日期</th>
				<th>约检节点名称</th>
				<th>小区名称</th>
				<th>客户姓名</th>
				<th>项目经理</th>
				<th>质检员</th>
				<th>实际开工日期</th>
				<th>开工第几天申请</th>
				<shiro:hasPermission name="qcapplycheckdelete:bizApplyCheckDelete:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="bizApplyCheckDelete">
			<tr>
				<td>${fns:getStoreLabel(bizApplyCheckDelete.storeId, '')}</td>
				<td>
					<fmt:formatDate value="${bizApplyCheckDelete.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>${bizApplyCheckDelete.qcNodeName}</td>
				<td>${bizApplyCheckDelete.customerAddress}</td>
				<td>${bizApplyCheckDelete.customerName}</td>
				<td>${bizApplyCheckDelete.managerName}</td>
				<td>${bizApplyCheckDelete.inspectorName}</td>
				<td>	<fmt:formatDate value="${bizApplyCheckDelete.actualStartDate}" pattern="yyyy-MM-dd"/></td>
				<td>${bizApplyCheckDelete.dateDiff}</td>
				<shiro:hasPermission name="qcapplycheckdelete:bizApplyCheckDelete:edit"><td>
					<a href="#" onclick="deleteObj(this,${bizApplyCheckDelete.id})">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>