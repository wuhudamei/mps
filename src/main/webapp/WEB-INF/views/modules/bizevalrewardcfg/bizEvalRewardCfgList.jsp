<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>评价奖励设置管理</title>
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

		// 启用
		function isEnabledNo(obj,id){
			// 判断任务包重复
			var flag = "";
			$.ajax({
				url: "${ctx}/bizevalrewardcfg/bizEvalRewardCfg/checkIsEnabledNo",    //请求的url地址
				dataType: "json",   //返回格式为json
				type: "POST",   //请求方式
				async: false, //请求是否异步，默认为异步，这也是ajax重要特性
				data: {
					"id":id
				},
				success: function(req) {
					if(req == '1'){
						flag = "1";
					}
				}
			});

			if(flag == "1"){
				alertx("此评价奖励的任务包在数据库中已存在");
				return;
			}

			top.$.jBox.confirm("确认要启用该评价奖励设置吗？","系统提示",function(v,h,f){
				if(v=="ok"){
					loading('正在提交，请稍等...');
					$(obj).removeAttr("href");
					$(obj).removeAttr("onclick");
					window.location.href="${ctx}/bizevalrewardcfg/bizEvalRewardCfg/isEnabledNo?id="+id;
				}
			},{buttonsFocus:1});
			top.$('.jbox-body .jbox-icon').css('top','55px');
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/bizevalrewardcfg/bizEvalRewardCfg/loadList">评价奖励设置列表</a></li>
		<shiro:hasPermission name="bizevalrewardcfg:bizEvalRewardCfg:edit"><li><a href="${ctx}/bizevalrewardcfg/bizEvalRewardCfg/form">评价奖励设置添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="bizEvalRewardCfg" action="${ctx}/bizevalrewardcfg/bizEvalRewardCfg/loadList" method="post" class="breadcrumb form-search">
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
				<c:if test="${!empty gongcheng}">
					<form:select path="projectMode" class="input-medium" >
					    <form:option value="" label=""/>
						<form:options items="${fns:getDictList('project_mode')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
				<c:if test="${empty gongcheng}">
					<form:select path="projectMode" class="input-medium needClear" disabled="true">
						<form:option value="" label=""/>
						<form:options items="${fns:getDictList('project_mode')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
			</li>
			<li><label>活动方案：</label>
				<form:input path="rewardName" htmlEscape="false" maxlength="20" class="input-medium needClear"/>
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
				<th>门店</th>
				<th>工程模式</th>
				<th>活动方案</th>
				<th>活动时间</th>
				<th>奖励对象</th>
				<th>停用/启用</th>
				<th>添加奖励方案时间</th>
				<shiro:hasPermission name="bizevalrewardcfg:bizEvalRewardCfg:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="bizEvalRewardCfg">
			<tr>
				<td>${fns:getStoreLabel(bizEvalRewardCfg.storeId, '')}</td>
				<td>${fns:getDictLabel(bizEvalRewardCfg.projectMode, 'project_mode', '')}</td>
				<td>${bizEvalRewardCfg.rewardName}</td>
				<td><fmt:formatDate value="${bizEvalRewardCfg.rewardStartDatetime}" pattern="yyyy-MM-dd"/>至<fmt:formatDate value="${bizEvalRewardCfg.rewardEndDatetime}" pattern="yyyy-MM-dd"/></td>
				<td>${fns:getDictLabel(bizEvalRewardCfg.rewardTargetType, 'eval_target_type', '')}</td>
				<td><c:if test="${bizEvalRewardCfg.isEnabled eq '0'}">停用</c:if><c:if test="${bizEvalRewardCfg.isEnabled eq '1'}">启用</c:if></td>
				<td><fmt:formatDate value="${bizEvalRewardCfg.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<shiro:hasPermission name="bizevalrewardcfg:bizEvalRewardCfg:edit"><td>
					<a href="${ctx}/bizevalrewardcfg/bizEvalRewardCfg/detail?id=${bizEvalRewardCfg.id}">详情</a>
					<c:if test="${bizEvalRewardCfg.isEnabled eq '0'}"><a href="#" onclick="isEnabledNo(this,${bizEvalRewardCfg.id})">启用</a></c:if>
					<c:if test="${bizEvalRewardCfg.isEnabled eq '1'}"><a href="${ctx}/bizevalrewardcfg/bizEvalRewardCfg/isEnabledYes?id=${bizEvalRewardCfg.id}" onclick="return confirmx('确认要停用该评价奖励设置吗？', this.href)">停用</a></c:if>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>