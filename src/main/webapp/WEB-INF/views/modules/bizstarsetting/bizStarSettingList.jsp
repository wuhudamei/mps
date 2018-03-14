<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>星级分数区间设置管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
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
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/starSetting/starRatingSetting/list">星级分数区间设置列表</a></li>
		<li><a href="${ctx}/starSetting/starRatingSetting/form">星级分数区间设置添加</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="bizStarSetting" action="${ctx}/starSetting/starRatingSetting/list" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>门店：</label>
					<form:select path="storeId" class="input-medium needClear" >
						<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
			</li>
			<li><label>工程模式：</label>
					<form:select path="projectMode" class="input-medium needClear" >
						<form:option value="" label=""/>
						<form:options items="${fns:getDictList('project_mode')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
			</li>
			<li><label>星级：</label>
				<form:select path="star" class="input-medium needClear"  htmlEscape="false" >
						<form:option value=""></form:option>
						<form:option value="5">五星</form:option>
						<form:option value="4">四星</form:option>
						<form:option value="3">三星</form:option>
						<form:option value="2">二星</form:option>
						<form:option value="1">一星</form:option>
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
				<th>起始分数</th>
				<th>结束分数</th>
				<th>星级</th>
				<th>状态</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="bizStarSetting">
			<tr>
				<td>
					${fns:getStoreLabel(bizStarSetting.storeId, '')}
				</td>
				<td>
					${fns:getDictLabel(bizStarSetting.projectMode, 'project_mode', '')}
				</td>
				<td>
					${bizStarSetting.startScore}
				</td>
				<td>
					${bizStarSetting.endScore}
				</td>
				<td>
					<c:if test="${bizStarSetting.star==5}"><span>五星</span></c:if>
					<c:if test="${bizStarSetting.star==4}"><span>四星</span></c:if>
					<c:if test="${bizStarSetting.star==3}"><span>三星</span></c:if>
					<c:if test="${bizStarSetting.star==2}"><span>二星</span></c:if>
					<c:if test="${bizStarSetting.star==1}"><span>一星</span></c:if>
				</td>
				<td>
					<c:if test="${bizStarSetting.isEnabled=='1' }"><span style="color:#00EC00;">启用</span></c:if>
					<c:if test="${bizStarSetting.isEnabled=='0' }"><span style="color:red;">停用</span></c:if>
				</td>
				<td>
					<a href="${ctx}/starSetting/starRatingSetting/delete?id=${bizStarSetting.id}" onclick="return confirmx('确认要修改该星级分数区间的状态吗？', this.href)">
						<c:if test="${bizStarSetting.isEnabled=='0' }"><span style="color:#00EC00;">启用</span></c:if>
						<c:if test="${bizStarSetting.isEnabled=='1' }"><span style="color:red;">停用</span></c:if>
					</a>
					<a href="${ctx}/starSetting/starRatingSetting/formEdit?id=${bizStarSetting.id}">修改</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>