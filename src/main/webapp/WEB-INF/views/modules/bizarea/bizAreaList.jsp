<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>区域管理管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
	$(document).ready(function() {
		var isFalg=$("#isFalg").val();
	   if(isFalg==1){
// 		   abandoned();
	   }
	});
	function page(n, s) {
		$("#pageNo").val(n);
		$("#pageSize").val(s);
		$("#searchForm").submit();
		return false;
	}
	
	//处理触发的事件
	function abandoned(id , workOrderCode){
		$("#myAbandonedModal").modal('show');
		$("#myId").val(id , workOrderCode);
		$("#workOrderCode").val( workOrderCode);
	}
	
	//处理 取消 事件
	function onclickNoAbandoned(){
		$('#myAbandonedModal').modal('hide');
		$("#reson").val('');
	}
	
	
	//处理 原因提交的事件
	function onclickAbandoned(){
	
	window.location.href="${ctx}/projectOrderList/preList";
			
		
		$('#myAbandonedModal').modal('hide');		
		}
	
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/bizarea/bizArea/">区域管理列表</a></li>
		<shiro:hasPermission name="bizarea:bizArea:edit">
			<li><a href="${ctx}/bizarea/bizArea/form">区域管理添加</a></li>
		</shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="bizArea"
		action="${ctx}/bizarea/bizArea/" method="post"
		class="breadcrumb form-search">
		<input id="isFalg" name="isFalg" type="hidden" value="${findChoiceBillCount.isFalg}" />
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden"
			value="${page.pageSize}" />
		<ul class="ul-form">
			<li><label>区域名称：</label> <form:input path="name"
					htmlEscape="false" maxlength="64" class="input-medium needClear" /></li>
			<li><label>门店：</label>
                <c:if test="${empty storeDropEnable}">
                    <form:select path="storeId" class="input-medium" disabled="true" onchange="changeStroe(this.options[this.selectedIndex].value)">
                        <form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
                    </form:select>
                </c:if>
                <c:if test="${!empty storeDropEnable}">
                    <form:select path="storeId" class="input-medium needClear" onchange="changeStroe(this.options[this.selectedIndex].value)">
                        <form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
                    </form:select>
                </c:if>
            </li>
			<li><label>工程模式：</label>
				<c:if test="${!empty projectModeEnable}">
					<form:select path="projectMode" class="input-medium" disabled="true">
						<form:options items="${fns:getDictList('project_mode')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
				<c:if test="${empty projectModeEnable}">
					<form:select path="projectMode" class="input-medium needClear">
						<form:option value="" label=""/>
						<form:options items="${fns:getDictList('project_mode')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary"	type="submit" value="查询" /></li>
            <li class="btns"><input class="btn btn-primary clearBtn" type="button" value="清空"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}" />
	<table id="contentTable"
		class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>门店</th>
				<th>工程模式</th>
				<th>区域编号</th>
				<th>区域</th>
				<shiro:hasPermission name="bizarea:bizArea:edit">
					<th>操作</th>
				</shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="bizArea">
				<tr>
					<td>${fns:getStoreLabel(bizArea.storeId, '')}</td>
					<td>${fns:getDictLabel(bizArea.projectMode, 'project_mode', '')}</td>
					<td>${bizArea.code}</td>
					<td>${bizArea.name}</td>
					<shiro:hasPermission name="bizarea:bizArea:edit">
						<td><a href="${ctx}/bizarea/bizArea/form?id=${bizArea.id}">修改</a>
							<a href="${ctx}/bizarea/bizArea/delete?id=${bizArea.id}"
							onclick="return confirmx('确认要删除该区域吗？', this.href)">删除</a></td>
					</shiro:hasPermission>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
							<!-- 处理btn弹框的model -->
			<div  class="modal fade" id="myAbandonedModal" tabindex="-1" role="dialog" style="width:350px">
								<input  id="myId" type="hidden">
								<input  id="workOrderCode" type="hidden">
								<div class="modal-header">
									<button class="close" type="button" data-dismiss="modal">×</button>
									<h4 id="myModalLabel" align="center" style="color: black;">通知</h3><br>
									<form:form id="timeForm" modelAttribute="bizMaterialsStandardReceiveBillApply" action="${ctx}/standradmaterialsrecords/bizMaterialsStandardReceiveBillApply/from">
<%-- 										<input id = "workOrderCode1"name="workOrderCode" value="${bizMaterialsStandardReceiveBill.workOrderCode}" type="hidden"> --%>
									<div  style="margin:10px;min-height:22px;height:auto;padding-left:5px;text-align:center;" class="modal-body">
<!-- 										<textarea id="reson" class="mask-text"  onkeyup="this.value = this.value.substring(0,100)" placeholder="请输入处理内容，多行输入，不多于100个汉字" maxlength="100" ></textarea> -->
										
										(${findChoiceBillCount.storeName})(${findChoiceBillCount.contractNumber})现有(${findChoiceBillCount.counts}个订单)有选材变更,请及时处理!
										
										<a href="javascript:void(0)" class="btn btn-primary" onclick="onclickAbandoned()" >去处理</a>
										<a href="javascript:void(0)" class="btn" onclick="onclickNoAbandoned()">暂不处理</a>
									</div>
									</div>
									</form:form>
		</div>
</body>
</html>