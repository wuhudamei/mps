<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>派工管理管理</title>
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
	$(window).load(function() {
	        var maxwidth = 123;
	        var maxHeight = 123;
	        if ( $("#pic").width() > maxwidth) {
	        	$("#pic").width(maxwidth);
	        }
	        if ( $("#pic").height() > maxHeight) {
	        	$("#pic").height(maxHeight);
	        }
	       
	});
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a
			href="${ctx}/scheduling/orderTaskpackage/findById?empGroupid=${orderTaskpackage.empGroupid}">工人组信息</a></li>
	</ul>
	<ul class="ul-form">
		<li class="btns"><input id="btnCancel" class="btn" type="button"
			value="返回"
			onclick="history.go(-1)" /></li>
	</ul>
	<sys:message content="${message}" />
	<table id="contentTable"
		class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>头像</th>
				<th>组长名称</th>
				<th>组长手机号</th>
				<th>小组人数</th>
				<th>可接任务包</th>


			</tr>
		</thead>
		<tbody>

			<tr>
				<td id="pic"><img alt="组长头像" src="${teamLeaderInfo.headPic}" ></td>
				<td>${teamLeaderInfo.realName}</td>
				<td>${teamLeaderInfo.phone}</td>
				<td>${teamLeaderInfo.teamNumber}</td>
				<td>
				<c:forEach items="${packageNameList}" var="taskpackage">
					${taskpackage} &nbsp;&nbsp;
			</c:forEach>
			</td>
			</tr>
		</tbody>
	</table>
</body>
</html>