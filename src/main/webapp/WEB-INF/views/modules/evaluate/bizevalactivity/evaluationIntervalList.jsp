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
		<li class="active"><a href="#">系统评价时间列表</a></li>
		<li><a href="${ctx}/evaluate/evaluationInterval/from?id=${bizEvalActivity.id}&evalTargetType=${bizEvalActivity.evalTargetType}&storeId=${bizEvalActivity.storeId}&projectMode=${bizEvalActivity.projectMode}">系统评价时间添加</a></li>
	</ul>
		<!-- <input id="btnCancel" class="btn" type="button" value="返 回"
				onclick="history.go(-1)" /> -->
		<a href="${ctx }/evaluate/bizevalactivity/bizEvalActivity/list1" class="btn btn-primary">返回活动评价设置列表</a>
	<form:form id="searchForm" modelAttribute="bizEvalActivity" action="${ctx}/evaluate/bizevalactivity/bizEvalActivity/list1" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>门店</th>
				<th>工程模式</th>
				<th>评价对象</th>
				<th>评价类别</th>
				<th>系统评价间隔时间（小时）</th>
				<th>状态</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="bizEvalActivity">
			<tr>
				<td>
					${fns:getStoreLabel(bizEvalActivity.storeId, '')}
				</td>
				<td>
					${fns:getDictLabel(bizEvalActivity.projectMode,'project_mode', '')}
				</td>
				<td>
					${fns:getDictLabel(bizEvalActivity.evalTargetType,'eval_target_type', '')}
				</td>
				<td>
					${fns:getDictLabel(bizEvalActivity.evalType,'eval_role_type','')}
				</td>
				<td>
					${bizEvalActivity.interval }
				</td>
				<td>
					<c:if test="${bizEvalActivity.isEnabled=='1' }"><span style="color:#00EC00;">启用</span></c:if>
					<c:if test="${bizEvalActivity.isEnabled=='0' }"><span style="color:red;">停用</span></c:if>
				</td>

				<td>
					<c:if test="${bizEvalActivity.isEnabled=='0' }"><a href="#" onclick="isEnabledNo(this,${bizEvalActivity.id})"><span style="color:#00EC00;">启用</span></a></c:if>
					<c:if test="${bizEvalActivity.isEnabled=='1' }"><a href="${ctx}/evaluate/bizevalactivity/bizEvalActivity/isEnabled?id=${bizEvalActivity.id}" onclick="return confirmx('确认要停用该评价活动吗？', this.href)"><span style="color:red;">停用</span></a></c:if>
					<a href="${ctx}/evaluate/evaluationInterval/from?roleCycleId=${bizEvalActivity.roleCycleId}&id=${bizEvalActivity.id}&evalTargetType=${bizEvalActivity.evalTargetType}&storeId=${bizEvalActivity.storeId}&projectMode=${bizEvalActivity.projectMode}&interval=${bizEvalActivity.interval}&evalType=${bizEvalActivity.evalType}&isEnabled=${bizEvalActivity.isEnabled}&delFlag=1"><span>修改</span></a>
				</td>
				
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>