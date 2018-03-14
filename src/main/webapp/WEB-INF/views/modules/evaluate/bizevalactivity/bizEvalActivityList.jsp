<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>评价活动设置管理</title>
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
		
		// 启用
		function isEnabledNo(obj,id){
			// 判断任务包重复
			var flag = "";
			$.ajax({
				url: "${ctx}/evaluate/bizevalactivity/bizEvalActivity/isEnabledEval",    //请求的url地址
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
				alertx("此评价活动的任务包在数据库中已存在");
				return;
			}

			top.$.jBox.confirm("确认要启用该评价活动吗？","系统提示",function(v,h,f){
				if(v=="ok"){
					loading('正在提交，请稍等...');
					$(obj).removeAttr("href");
					$(obj).removeAttr("onclick");
					window.location.href="${ctx}/evaluate/bizevalactivity/bizEvalActivity/isEnabled?id="+id;
				}
			},{buttonsFocus:1});
			top.$('.jbox-body .jbox-icon').css('top','55px');
		}
		
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/evaluate/bizevalactivity/bizEvalActivity/list">评价活动设置列表</a></li>
		<shiro:hasPermission name="bizevalactivity:bizEvalActivity:edit"><li><a href="${ctx}/evaluate/bizevalactivity/bizEvalActivity/form">评价活动设置添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="bizEvalActivity" action="${ctx}/evaluate/bizevalactivity/bizEvalActivity/list1" method="post" class="breadcrumb form-search">
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
					<form:select path="storeId" class="input-medium needClear" onchange="checkKind()">
						<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
			</li>
			<li><label>工程模式：</label>
				<c:if test="${empty gongcheng}">
					<form:select path="projectMode" class="input-medium" disabled="true">
						<form:option value="" label=""/>
						<form:options items="${fns:getDictList('project_mode')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
				<c:if test="${!empty gongcheng}">
					<form:select path="projectMode" class="input-medium needClear" onchange="checkKind()">
						<form:option value="" label=""/>
						<form:options items="${fns:getDictList('project_mode')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
					
			</li>
			<li><label>评价对象：</label>
				<form:select path="evalTargetType" class="input-medium needClear">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('eval_target_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
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
				<th>门店</th>
				<th>工程模式</th>
				<th>有效时间</th>
				<th>评价对象</th>
				<th>评价状态</th>
				<th>最后修改时间</th>
				<shiro:hasPermission name="bizevalactivity:bizEvalActivity:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="bizEvalActivity">
			<tr>
				<td>
					${fns:getStoreLabel(bizEvalActivity.storeId, '')}
				</td>
				<td>
					${fns:getDictLabel(bizEvalActivity.projectMode, 'project_mode', '')}
				</td>
				<td>
					<fmt:formatDate value="${bizEvalActivity.evalStartDatetime}" pattern="yyyy-MM-dd"/>
					至
					<fmt:formatDate value="${bizEvalActivity.evalEndDatetime}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					${fns:getDictLabel(bizEvalActivity.evalTargetType, 'eval_target_type', '')}
				</td>
				<td>
					<c:if test="${bizEvalActivity.isEnabled=='1' }"><span style="color:#00EC00;">启用</span></c:if>
					<c:if test="${bizEvalActivity.isEnabled=='0' }"><span style="color:red;">停用</span></c:if>
				</td>
				<td>
					<fmt:formatDate value="${bizEvalActivity.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="bizevalactivity:bizEvalActivity:edit"><td>
    				<%-- <a href="${ctx}/evaluate/bizevalactivity/bizEvalActivity/form?id=${bizEvalActivity.id}">修改</a>
					<a href="${ctx}/evaluate/bizevalactivity/bizEvalActivity/delete?id=${bizEvalActivity.id}" onclick="return confirmx('确认要删除该评价活动设置吗？', this.href)">删除</a> --%>
					
					<c:if test="${bizEvalActivity.isEnabled=='0' }"><a href="#" onclick="isEnabledNo(this,${bizEvalActivity.id})"><span style="color:#00EC00;">启用</span></a></c:if>
					<c:if test="${bizEvalActivity.isEnabled=='1' }"><a href="${ctx}/evaluate/bizevalactivity/bizEvalActivity/isEnabled?id=${bizEvalActivity.id}" onclick="return confirmx('确认要停用该评价活动吗？', this.href)"><span style="color:red;">停用</span></a></c:if>
					<a href="${ctx}/evaluate/bizevalactivity/bizEvalActivity/details?id=${bizEvalActivity.id}">详情</a>
					<a href="${ctx}/evaluate/evaluationInterval/list?id=${bizEvalActivity.id}&evalTargetType=${bizEvalActivity.evalTargetType}&storeId=${bizEvalActivity.storeId}&projectMode=${bizEvalActivity.projectMode}">系统评价时间设置</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>