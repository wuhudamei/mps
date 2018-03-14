<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>业主APP登录日志查询</title>
<meta name="decorator" content="default" />


<script type="text/javascript">
	$(document).ready(function() {

	});
	
	
	function page(n, s) {
		$("#pageNo").val(n);
		$("#pageSize").val(s);
		$("#searchForm").submit();
		return false;
	}
	
	
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/app/homeLog/list">业主APP登录日志列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="homeLoginLogoutLog" action="${ctx}/app/homeLog/list" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}" />
		
		<ul class="ul-form">
			<li><label>操作日期</label>
				<input name="beginDeal" id="beginDeal" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate needClear"
					value="${homeLoginLogoutLog.beginDeal}"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});"/> 至  
				<input name="endDeal" id="endDeal" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate needClear"
					value="${homeLoginLogoutLog.endDeal}"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'beginDeal\')}',isShowClear:true});"/>
			</li>
			
			<li><label>登录终端：</label>
                <form:select path="dealMode" class="input-medium needClear">
                    <form:option value="" label=""/>
                    <form:option value="app" label="APP"/>
                    <form:option value="wechat" label="微信"/>
                </form:select>
            </li>
            <li><label>操作类型：</label>
                <form:select path="dealType" class="input-medium needClear">
                    <form:option value="" label=""/>
                    <form:option value="in" label="登录"/>
                    <form:option value="out" label="登出"/>
                </form:select>
            </li>
		
            <li class="btns"><input id="btnSubmit" class="btn btn-primary"	type="submit" value="查询" /></li>
            <li class="btns"><input class="btn btn-primary clearBtn" type="button" value="清空"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}" />
	<div id="summaryResult" style="margin-bottom:10px;">
		<span style="padding-left:15px;">实际使用人数：${actualLogNum}</span>
		<span style="padding-left:15px;">微信端人数：${wechatNum}</span>
		<span style="padding-left:15px;">APP端人数：${appNum}</span>
	</div>
	<table id="contentTable"
		class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>序号</th>
				<th>操作手机号</th>
				<th>操作终端</th>
				<th>操作类型</th>
				<th>操作时间</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" varStatus="i" var="homeLoginLogoutLog">
				<tr>
					<td>${i.index + 1}</td>
					<td>${homeLoginLogoutLog.dealPhone}</td>
					<td>${homeLoginLogoutLog.dealMode}</td>
					<td>
						<c:choose>
						   <c:when test="${fn:contains(homeLoginLogoutLog.dealType, 'in')}">
						   		登录
						   </c:when>
						   <c:otherwise>
						   		登出
						   </c:otherwise>
						</c:choose>
					</td>
					<td>
						<fmt:formatDate value="${homeLoginLogoutLog.dealTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>