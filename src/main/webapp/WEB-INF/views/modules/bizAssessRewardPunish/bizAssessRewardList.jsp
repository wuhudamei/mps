<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>项目经理奖励列表</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
	$(document).ready(function() {
		getDepartments();
	});
	function page(n, s) {
		$("#pageNo").val(n);
		$("#pageSize").val(s);
		$("#searchForm").submit();
		return false;
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
	}

	//加载区域信息
	function getDepartments(){
		$("#enginDepartId").html('');
		$.ajax({
			url:'${ctx}/engdept/bizEngineeringDepartment/departmentList',
			type:'post',
			dataType : 'json',
			data:{
				'storeId':$('#storeId').val(),
				'projectMode':$('#projectMode').val(),
				'employeeId':$('#employeeId').val()
			},
			success:function(data){
				if(data == null){
					$("#enginDepartId").append('');
				} else {
					var html = "<option value=''></option>";
					for(var i=0;i<data.length;i++){
						var sec = "";
						if('${bizAssessRewardPunish.enginDepartId}' == data[i].value){
		            		sec = "selected='selected'";
		            		$("#s2id_enginDepartId .select2-chosen").html(data[i].label);
		            	}
						html += "<option value='" + data[i].value +"' " + sec + ">" + data[i].label + "</option>"
					}
					html+='';
	        		$("#enginDepartId").append(html);
				}
			}
		});
	}
	
	function del(id){
		top.$.jBox.confirm("确定删除吗？","系统提示",function(v,h,f){
			if(v=="ok"){
				$.ajax({
					url:'${ctx}/bizAssessRewardPunish/bizAssessRewardPunish/delete',
					type:'post',
					dataType : 'json',
					data:{
						'id':id
					},
					success:function(data){
						if(data == 0){
							alertx("删除成功！");
							$("#searchForm").submit();
						}
					}
				});
			}
		},{buttonsFocus:1});
		top.$('.jbox-body .jbox-icon').css('top','55px');
	}
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active">
			<ul class="nav nav-tabs">
				<li class="active"><a href="#">项目经理奖励列表</a></li>
			</ul>
		<li><a
			href="${ctx}/bizAssessRewardPunish/bizAssessRewardPunish/openBizAssessRewardForm">项目经理奖励添加</a>
		</li>
		</li>
	</ul>
	<form:form id="searchForm" modelAttribute="bizAssessRewardPunish"
		action="${ctx}/bizAssessRewardPunish/bizAssessRewardPunish/queryBizAssessReward"
		method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden"
			value="${page.pageSize}" />
		<input type="hidden" name="isRewardOrPunish" value="1"/>
		<input type="hidden" name="isMonthInspection" value="${bizAssessRewardPunish.isMonthInspection}"/>
		<ul class="ul-form">
			<li><label>门店：</label> <c:if test="${empty storeDropEnable}">
					<form:select path="storeId" id="storeId" class="input-medium"
						disabled="true" onchange="getDepartments()">
						<form:options items="${fns:getStoreList()}" itemLabel="label"
							itemValue="value" htmlEscape="false" />
					</form:select>
				</c:if> <c:if test="${!empty storeDropEnable}">
					<form:select path="storeId" id="storeId"
						class="input-medium needClear" onchange="getDepartments()">
						<form:options items="${fns:getStoreList()}" itemLabel="label"
							itemValue="value" htmlEscape="false" />
					</form:select>
				</c:if></li>
			<li><label>工程模式：</label> <form:select path="projectMode"
					id="projectMode" class="input-medium" disabled="true"
					onchange="getDepartments()">
					<form:option value="1" label="产业" />
				</form:select></li>

			<li><label>区域：</label> <form:select path="enginDepartId"
					id="enginDepartId" class="input-medium needClear">
				</form:select></li>

			<li><label>客户姓名：</label> <form:input path="customerName"
					htmlEscape="false" class="input-medium needClear" /></li>

			<li><label>项目经理：</label> <form:input
					path="rewardPunishTargetEmployeeName" htmlEscape="false"
					class="input-medium needClear" /></li>

			<li><label>结算状态：</label> <form:select path="rewardPunishStatus"
					class="input-medium needClear">
					<form:option value="" label="" />
					<form:options items="${fns:getDictList('reward_punish_status')}"
						itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select></li>

			<li><label>所属结算阶段：</label> <form:select path="settleStage"
					class="input-medium needClear">
					<form:option value="" label="" />
					<form:option value="1" label="中期结算" />
					<form:option value="2" label="竣工结算" />
				</form:select></li>

			<li><label>奖励日期：</label> <input name="startDate" id="startDate"
				type="text" readonly="readonly" maxlength="20"
				class="input-medium Wdate"
				value="<fmt:formatDate value="${bizAssessRewardPunish.startDate}" pattern="yyyy-MM-dd"/>"
				onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'endDate\')}',isShowClear:true});" />
				至 <input name="endDate" id="endDate" type="text" readonly="readonly"
				maxlength="20" class="input-medium Wdate"
				value="<fmt:formatDate value="${bizAssessRewardPunish.endDate}" pattern="yyyy-MM-dd"/>"
				onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'startDate\')}',isShowClear:true});" />
			</li>

			<li class="btns"><input id="btnSubmit" class="btn btn-primary"
				type="submit" value="查询" /></li>
			<li class="btns"><input class="btn btn-primary clearBtn"
				type="button" value="清空" onclick="clearCondition()" /></li>
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
				<th>区域</th>
				<th>奖励日期</th>
				<th>奖励人员</th>
				<th>奖励金额</th>
				<th>结算状态</th>
				<th>所属结算阶段</th>
				<th>小区名称</th>
				<th>客户</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="bizAssessRewardPunish">
				<tr>
					<td>${fns:getStoreLabel(bizAssessRewardPunish.storeId, '')}</td>
					<td>${fns:getDictLabel(bizAssessRewardPunish.projectMode, 'project_mode','')}</td>
					<td>${bizAssessRewardPunish.enginDepartName}</td>
					<td><fmt:formatDate
							value="${bizAssessRewardPunish.rewardPunishDatetime}"
							pattern="yyyy-MM-dd" /></td>
					<td>${bizAssessRewardPunish.rewardPunishTargetEmployeeName}</td>
					<td>${bizAssessRewardPunish.rewardPunishAmount}</td>
					<td>${fns:getDictLabel(bizAssessRewardPunish.rewardPunishStatus, 'reward_punish_status','')}</td>
					<td>
					<c:if test="${bizAssessRewardPunish.settleStage == 1}">中期结算</c:if>
					<c:if test="${bizAssessRewardPunish.settleStage == 2}">竣工结算</c:if>
					</td>
					<td>${bizAssessRewardPunish.communityName}-${bizAssessRewardPunish.buildNumber}-${bizAssessRewardPunish.buildUnit}-${bizAssessRewardPunish.buildRoom}</td>
					<td>${bizAssessRewardPunish.customerName}</td>
					<td><a href="${ctx}/bizAssessRewardPunish/bizAssessRewardPunish/detail?id=${bizAssessRewardPunish.id}">详情</a> 
					    <c:if test="${bizAssessRewardPunish.rewardPunishStatus == 1}">
							<a href="${ctx}/bizAssessRewardPunish/bizAssessRewardPunish/openBizAssessRewardForm?id=${bizAssessRewardPunish.id}">修改</a>
							<a href="#" onclick="del('${bizAssessRewardPunish.id}')">删除</a>
						</c:if></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>