<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>任务包模板管理</title>
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
		//启用
		function TaskpackageEnable(id,storeId,projectMode,empWorkType){
			//如果工种不为空时
			if(empWorkType != ""){
				$.ajax({
					url: "${ctx}/taskpackage/bizTaskPackageTemplat/check_emp_work_type_ajax",
		            type: "post",
		            async: false,
		            data:{
		            		id:id,
		            		storeId:storeId,
		            		projectMode:projectMode,
		            		empWorkType:empWorkType
		            	},
		            success: function(data) {
		            	
		            	if(null!=data && data=="0"){
		            		window.location.href = "${ctx}/taskpackage/bizTaskPackageTemplat/enable?id="+id;
		            	}else{
		            		alertx("该工种已经配置了任务包模板，不允许重复配置。");
		            	}
		            	
		            }
		    	});
			}else{
           		window.location.href = "${ctx}/taskpackage/bizTaskPackageTemplat/enable?id="+id;
			}
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/taskpackage/bizTaskPackageTemplat/">任务包模板列表</a></li>
		<shiro:hasPermission name="taskpackage:bizTaskPackageTemplat:edit"><li><a href="${ctx}/taskpackage/bizTaskPackageTemplat/form">任务包模板添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="bizTaskPackageTemplat" action="${ctx}/taskpackage/bizTaskPackageTemplat/" method="post" class="breadcrumb form-search">
		<form:input path="frontSort" htmlEscape="false"  type="hidden" maxlength="18" class="input-medium"/>
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>门店：</label>
				<c:if test="${empty storeDropEnable}">
					<form:select path="storeId" class="input-medium" disabled="true">
						<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
				<c:if test="${!empty storeDropEnable}">
					<form:select path="storeId" class="input-medium needClear">
						<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
			</li>
			
			<li><label>工程模式：</label>
				<c:if test="${empty gongcheng}">
					<form:select path="projectMode" class="input-medium" disabled="true">
						<form:option value="" label=""/>
						<form:options items="${fns:getDictList('package_project_mode')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
				<c:if test="${!empty gongcheng}">
					<form:select path="projectMode" class="input-medium needClear">
						<form:option value="" label=""/>
						<form:options items="${fns:getDictList('package_project_mode')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
			</li>
			
			<li><label>任务包类型：</label>
				<form:select path="taskPackageTypeId" class="input-medium needClear">
					<form:option value="" label=""/>
					<form:options items="${fns:getTaskPackageTypeList()}" itemLabel="name" itemValue="id" htmlEscape="false"/>
				</form:select>
			</li>
			
			<li><label>工种：</label>
				<form:select path="empWorkType" class="input-medium needClear">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('emp_work_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			
			<li><label>任务包编号：</label>
				<form:input path="no" htmlEscape="false" maxlength="18" class="input-medium needClear"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="btns"><input class="btn btn-primary clearBtn" type="button" value="清空"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable"  class="table table-striped table-bordered table-condensed sortable">
		<thead>
			<tr>
				<!--<th onclick="setSort('no')">任务包模板编号</th>-->
				<!--<th onclick="setSort('templatName')">任务包模板名称</th>-->
				<th>任务包编号</th>
				<th>任务包名称</th>
				<th>任务包类型</th>
				<th>门店</th>
				<th>门店顺序</th>
				<th>工程模式</th>
				<th>首付款比例</th>
				<th>付尾款比例</th>
				<th>是否扣质保金</th>
				<th>质保金扣除比例</th>
				<th>最大派单数量</th>
				<th>工人结算方式</th>
				<th>工种</th>
				<th>状态</th>
				<shiro:hasPermission name="taskpackage:bizTaskPackageTemplat:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="bizTaskPackageTemplat">
			<tr>
				<td><a href="${ctx}/taskpackage/bizTaskPackageTemplat/form?id=${bizTaskPackageTemplat.id}">
					${bizTaskPackageTemplat.no}
				</a></td>
				<td>
					${bizTaskPackageTemplat.templatName}
				</td>
				<td>
					${fns:getTaskPackageTypeLabel(bizTaskPackageTemplat.taskPackageTypeId, '')}
				</td>
				<td>
					${fns:getStoreLabel(bizTaskPackageTemplat.storeId, '')}
				</td>
				<td>
					${bizTaskPackageTemplat.storeOrder}
				</td>
				<td>${fns:getDictLabel(bizTaskPackageTemplat.projectMode, 'package_project_mode', '')}</td>
				<td>
					${bizTaskPackageTemplat.advancePaymentRates}
				</td>
				<td>
					${bizTaskPackageTemplat.restPaymentRates}
				</td>
				<td>
					${fns:getDictLabel(bizTaskPackageTemplat.isQualityGuarantee, 'yes_no', '')}
				</td>
				<td>
					${bizTaskPackageTemplat.qualityGuaranteeRate}
				</td>
				<td>
					${bizTaskPackageTemplat.projectMaxCount }
				</td>
				<td>
					${fns:getDictLabel(bizTaskPackageTemplat.settleStyle, 'settle_style', '')}
				</td>
				<td>
					${fns:getDictLabel(bizTaskPackageTemplat.empWorkType, 'emp_work_type', '')}
				</td>
				<td>
					${fns:getDictLabel(bizTaskPackageTemplat.status, 'biz_enable_status', '')}
				</td>
				<shiro:hasPermission name="taskpackage:bizTaskPackageTemplat:edit"><td>
					<c:if test="${bizTaskPackageTemplat.status==1}">
						<a href="${ctx}/taskpackage/bizTaskPackageTemplat/enable?id=${bizTaskPackageTemplat.id}">
							停用
						</a>
					</c:if>
					<c:if test="${bizTaskPackageTemplat.status==0}">
						<a onclick="TaskpackageEnable('${bizTaskPackageTemplat.id}','${bizTaskPackageTemplat.storeId}','${bizTaskPackageTemplat.projectMode}','${bizTaskPackageTemplat.empWorkType}')">
							启用
						</a>
					</c:if>
    				<a href="${ctx}/taskpackage/bizTaskPackageTemplat/form?id=${bizTaskPackageTemplat.id}">修改</a>
    				<a href="${ctx}/taskpackage/bizTaskPackageTemplat/queryTemplatBudgetAmountList?taskpackageTemplatId=${bizTaskPackageTemplat.id}">预算金额上限</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>