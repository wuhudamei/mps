<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>评价活动设置管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
	var content = "<tr><th><select name='evalRoleType' class='input-xlarge required'>";

	$(document).ready(
			function() {

				var projectMode = "${bizEvalActivity.id}"
				var readOnly = "${readOnly}"
				//如果是产业或者是传统的人,  就不允许修改
				if ("1" == readOnly) {
					$(":radio[name='projectMode'][value='" + 2 + "']").attr(
							"disabled", true);
					$(":radio[name='projectMode'][value='" + 4 + "']").attr(
							"disabled", true);
				}
				if ("2" == readOnly) {
					$(":radio[name='projectMode'][value='" + 1 + "']").attr(
							"disabled", true);
					$(":radio[name='projectMode'][value='" + 4 + "']").attr(
							"disabled", true);
				}
				if ("4" == readOnly) {
					$(":radio[name='projectMode'][value='" + 2 + "']").attr(
							"disabled", true);
					$(":radio[name='projectMode'][value='" + 1 + "']").attr(
							"disabled", true);
				}
				//新增
				if ("" == projectMode) {
					//如果不是产业或者是传统的人 ,默认传统,并且可以修改
					if ("1" != readOnly && "2" != readOnly && "4" != readOnly) {
						$(":radio[name='projectMode'][value='" + 2 + "']")
								.prop("checked", "checked");
					} else {
						//如果是产业或者是传统的人   工程模式也一样,并且不能修改
						$(
								":radio[name='projectMode'][value='" + readOnly
										+ "']").prop("checked", "checked");
						if ("1" == readOnly) {
							$(":radio[name='projectMode'][value='" + 2 + "']")
									.attr("disabled", true);
							$(":radio[name='projectMode'][value='" + 4 + "']")
									.attr("disabled", true);
						}
						if ("2" == readOnly) {
							$(":radio[name='projectMode'][value='" + 1 + "']")
									.attr("disabled", true);
							$(":radio[name='projectMode'][value='" + 4 + "']")
									.attr("disabled", true);
						}
						if ("4" == readOnly) {
							$(":radio[name='projectMode'][value='" + 1 + "']")
									.attr("disabled", true);
							$(":radio[name='projectMode'][value='" + 2 + "']")
									.attr("disabled", true);
						}
					}
				}
				$(":radio[name='isEnabled'][value='" + 1 + "']").prop(
						"checked", "checked");
				addDictType();

				//提交表单
				//$("#name").focus();
				$("#inputForm")
						.validate(
								{
									submitHandler : function(form) {
										var a = checkData();
										if (a == "1") {
											loading('正在提交，请稍等...');
											form.submit();
										}
									},
									errorContainer : "#messageBox",
									errorPlacement : function(error, element) {
										$("#messageBox").text("输入有误，请先更正。");
										if (element.is(":checkbox")
												|| element.is(":radio")
												|| element.parent().is(
														".input-append")) {
											error.appendTo(element.parent()
													.parent());
										} else {
											error.insertAfter(element);
										}
									}
								});
			});

	function addDictType() {
		content += "<option value=''>--请选择--</option>";
		<c:forEach items="${dictList}" var="star">
		content = content
				+ "<option value='${star.value}'>${star.label}</option>";
		</c:forEach>
		content += "</select></th>";
	}

	function checkData() {
		var id = "${bizEvalActivity.id}"
		var storeId = $("#storeId").val();
		var projectMode = $(":radio[name='projectMode'][checked='checked']")
				.val();
		var evalTargetType = $("#evalTargetType").val();
		var evalStartDatetime = $("#evalStartDatetime").val();
		var evalEndDatetime = $("#evalEndDatetime").val();
		var biaoshi = "1";
		if (evalTargetType == "1") {//评价对象是工人
			//校验任务包是否已存在
			var obj = document.getElementsByName('taskpackTempId'); //选择所有name="'taskpackTempId'"的对象，返回数组    
			var s = '';
			var j = 0;
			for (var i = 0; i < obj.length; i++) {
				if (obj[i].checked) {
					s += obj[i].value + ','; //如果选中，将value添加到变量s中  
					j++;
				}
			}

			if (j < 1) {
				biaoshi = "2";
				alertx("请选择任务包类型")
				return biaoshi;
			}

			$.ajax({
						url : "${ctx}/evaluate/bizevalactivity/bizEvalActivity/isTaskpackage",
						type : "post",
						async : false,
						data : {
							id : id,
							taskpackTempId : s,
							storeId : storeId,
							projectMode : projectMode,
							evalTargetType : evalTargetType,
							evalStartDatetime : evalStartDatetime,
							evalEndDatetime : evalEndDatetime
						},
						success : function(data) {
							if (data == false) {
								biaoshi = "2";
								alertx("存在已添加过的任务包")
							}
						}
					});
		} else if (evalTargetType == "2") {//评价对象是项目经理
			var checkObj = document.getElementsByName('managerEvalStage');
			var obj = document.getElementsByName('evalStageCheckNode');
			var s = null;
			var j = 0;
			for (var i = 0; i < checkObj.length; i++) {
				if (checkObj[i].checked) {
					if (obj[i].options[obj[i].selectedIndex].value != null
							&& obj[i].options[obj[i].selectedIndex].value != ""
							&& obj[i].options[obj[i].selectedIndex].value.length > 0) {
						if (s == null) {
							s = obj[i].options[obj[i].selectedIndex].value;
						} else {
							s += ",";
							s += obj[i].options[obj[i].selectedIndex].value; //如果选中，将value添加到变量s中  
						}
						j++;
					} else {
						biaoshi = "2";
						alertx("请选择约检节点！")
						return biaoshi;
					}
				}

			}
			if (j < 1) {
				biaoshi = "2";
				alertx("请选择项目经理评价阶段和约检节点！")
				return biaoshi;
			}
			$.ajax({
						url : "${ctx}/evaluate/bizevalactivity/bizEvalActivity/isCheckStage",
						type : "post",
						async : false,
						data : {
							id : id,
							evalStageCheckNodeList : s,
							storeId : storeId,
							projectMode : projectMode,
							evalTargetType : evalTargetType,
							evalStartDatetime : evalStartDatetime,
							evalEndDatetime : evalEndDatetime
						},
						success : function(data) {
							if (data == false) {
								biaoshi = "2";
								alertx("约检节点已存在活动！")
							}
						}
					});
		}

		if (biaoshi == "2") {
			return biaoshi;
		}
		//评价类别不能为空
		var els1 = document.getElementsByName("evalRoleType");
		if (els1.length < 1) {
			alertx("评价指标设置至少包含一条以上的数据")
			return "2";
		}
		for (var i = 0, j = els1.length; i < j; i++) {
			var role = els1[i].value;
			if (null == role || role == "") {
				alertx("评价类别不能为空")
				return "2";
			}
		}
		//评价指标不能为空
		var els2 = document.getElementsByName("evalIndexId");
		for (var i = 0, j = els2.length; i < j; i++) {
			var role = els2[i].value;
			if (null == role || role == "") {
				alertx("评价指标不能为空")
				return "2";
			}
		}
		//分值不能为空
		var els = document.getElementsByName("evalTotalScore");
		for (var i = 0, j = els.length; i < j; i++) {
			var zhi = els[i].value;
			if ($.trim(zhi) == "") {
				alertx("分值不能为空")
				return "2";
			}
		}

		//评价类别+评价指标=不可重负
		for (var i = 0, j = els1.length; i < j; i++) {
			for (var w = 0, q = els2.length; w < q; w++) {
				if (i != w) {
					if (els1[i].value == els1[w].value
							&& els2[i].value == els2[w].value) {
						alertx("评价类别和评价指标不可同时重复")
						return "2";
					}
				}
			}
		}

		return biaoshi;
	}

	//点击门店加载任务包
	function findTaskpackage() {
		var storeId = $("#storeId").val();
		var projectMode = $(":radio[name='projectMode'][checked='checked']").val();
		if(storeId == null || storeId == "" || projectMode == null || projectMode == null){
			return;
		}
		$.ajax({
					url : "${ctx}/evaluate/bizevalactivity/bizEvalActivity/findTaskpackage?storeId="+ storeId+"&projectMode="+projectMode,
					type : "get",
					success : function(data) {
						var htmls = "";
						for (var i = 0; i < data.length; i++) {
							var j = i + 1;
							htmls += "<span><input id='taskpackTempId"+j+"' name='taskpackTempId' type='checkbox' class='taskpackTempId' value='"+data[i].id+"'><label for='taskpackTempId"+j+"'>"
									+ data[i].templatName + "</label></span>"
						}
						htmls += "<span class='help-inline'><font color='red'>*</font> </span>";
						$("#taskpackages").html(htmls);
					}
				});
	}

	//获取约检节点
	function findCheckNode() {
		var storeId = $("#storeId").val();
		var projectMode = $(":radio[name='projectMode'][checked='checked']")
				.val();
		$.ajax({
					url : '${ctx}/checknode/checkNode/findCheckNodeByStoreIdAndProjectMode?storeId='
							+ storeId + '&projectMode=' + projectMode,
					type : 'post',
					success : function(data) {
						if (data != null && data.length > 0) {
							var html = "<option value=''>--请选择--</option>";
							for (var i = 0; i < data.length; i++) {
								html += "<option value="+data[i].value+">"
										+ data[i].label + "</option>";

							}
							$("select[name='evalStageCheckNode']").each(
									function() {
										$(this).html(html);
									});
						}
					}
				});
	}

	//新增
	function add() {
		var storeId = $("#storeId").val();
		if (storeId < 1) {
			alertx("请选择门店")
			return false;
		}
		var projectMode = $(":radio[name='projectMode'][checked='checked']")
				.val();
		var evalTargetType = $("#evalTargetType").val();
		if (evalTargetType == null || evalTargetType == '') {
			alertx("请选择评价对象")
			return false;
		}
		var html = "";
		if (evalTargetType == "1") {
			html = content;
		} else if (evalTargetType == "2") {
			html += "<tr><th><select name='evalRoleType' class='input-xlarge required'>";
			html += "<option value=''>--请选择--</option>";
			<c:forEach items="${dictList}" var="star">
			if ("${star.value}" != "1") {
				html += "<option value='${star.value}'>${star.label}</option>";
			}
			</c:forEach>
			html += "</select></th>";
		}

		$.ajax({
					url : "${ctx}/evaluate/bizevalactivity/bizEvalActivity/addEvalIndex",
					type : "post",
					async : false,
					data : {
						storeId : storeId,
						projectMode : projectMode,
					},
					success : function(data) {
						if (null != data) {
							html += "<th><select name='evalIndexId' class='input-xlarge required'>";
							html += "<option value=''>--请选择--</option>";
							for (var i = 0; i < data.length; i++) {
								html += "<option value='"+data[i].id+"'>"
										+ data[i].indexName + "</option>";
							}
							html += "</select></th><th><input type='text' value='' name='evalTotalScore' class='required' onkeyup='chkPrice(this);' onblur='chkLast(this)'></th><th><input type='button' value='删除' class='btn btn-primary' onclick='del(this)'/></th></tr> ";
						}
					}
				});

		$("#pingjia").append(html);

	}

	//删除
	function del(k) {
		$(k).parent().parent().remove();
	}

	function chkPrice(obj) {
		obj.value = obj.value.replace(/[^\d.]/g, "");
		//必须保证第一位为数字而不是.
		obj.value = obj.value.replace(/^\./g, "");
		//保证只有出现一个.而没有多个.
		obj.value = obj.value.replace(/\.{2,}/g, ".");
		//保证.只出现一次，而不能出现两次以上
		obj.value = obj.value.replace(".", "$#$").replace(/\./g, "").replace(
				"$#$", ".");
	}
	function chkLast(obj) {
		// 如果出现非法字符就截取掉
		if (obj.value.substr((obj.value.length - 1), 1) == '.')
			obj.value = obj.value.substr(0, (obj.value.length - 1));
	}

	function changeEvalTargetType() {
		var evalTargetType = $("#evalTargetType").val();
		if (evalTargetType == "1") {
			$("#taskpackageType").show();
			$("#managerEval").hide();
		} else if (evalTargetType == "2") {
			findCheckNode();
			$("#taskpackageType").hide();
			$("#managerEval").show();
		}
	}
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a
			href="${ctx}/evaluate/bizevalactivity/bizEvalActivity/list">评价活动设置列表</a></li>
		<li class="active"><a
			href="${ctx}/evaluate/bizevalactivity/bizEvalActivity/form?id=${bizEvalActivity.id}">评价活动设置<shiro:hasPermission
					name="bizevalactivity:bizEvalActivity:edit">${not empty bizEvalActivity.id?'修改':'添加'}</shiro:hasPermission>
				<shiro:lacksPermission name="bizevalactivity:bizEvalActivity:edit">查看</shiro:lacksPermission></a></li>
	</ul>
	<br />
	<form:form id="inputForm" modelAttribute="bizEvalActivity"
		action="${ctx}/evaluate/bizevalactivity/bizEvalActivity/save"
		method="post" class="form-horizontal">
		<form:hidden path="id" />
		<sys:message content="${message}" />
		<div class="control-group">
			<label class="control-label">门店：</label>
			<div class="controls">
				<form:select path="storeId" class="input-xlarge required"
					onclick="findTaskpackage()">
					<form:options items="${fns:getStoreList()}" itemLabel="label"
						itemValue="value" htmlEscape="false" />
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">工程模式：</label>
			<div class="controls">
				<form:radiobuttons path="projectMode"
					items="${fns:getDictList('project_mode')}" itemLabel="label"
					itemValue="value" htmlEscape="false" class="required" onclick="findTaskpackage()"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">评价对象：</label>
			<div class="controls">
				<form:select id="evalTargetType" path="evalTargetType"
					class="input-xlarge required" onchange="changeEvalTargetType()">
					<form:option value="" label="" />
					<form:options items="${fns:getDictList('eval_target_type')}"
						itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">有效时间：</label>
			<div class="controls">
				<input id="evalStartDatetime" name="evalStartDatetime" type="text"
					readonly="readonly" maxlength="20"
					class="input-medium Wdate required"
					value="<fmt:formatDate value="${bizEvalActivity.evalStartDatetime}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'evalEndDatetime\')}',isShowClear:false});" />
				至 <input id="evalEndDatetime" name="evalEndDatetime" type="text"
					readonly="readonly" maxlength="20"
					class="input-medium Wdate required"
					value="<fmt:formatDate value="${bizEvalActivity.evalEndDatetime}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'evalStartDatetime\')}',isShowClear:false});" />
			</div>
		</div>
		<div class="control-group" hidden="hidden">
			<label class="control-label">是否启用：</label>
			<div class="controls">
				<form:radiobuttons path="isEnabled"
					items="${fns:getDictList('biz_enable_status')}" itemLabel="label"
					itemValue="value" htmlEscape="false" class="required" />
			</div>
		</div>
		<div class="control-group" id="taskpackageType">
			<label class="control-label">任务包类型：</label>
			<div class="controls" id="taskpackages">
				<%-- <c:if test="${bizEvalActivity.id != '' }">
					<c:forEach items="${taskPackageList }" var="taskPackageList" varStatus="status">
						<span><input id="taskpackTempId${status.index+1 }" name="taskpackTempId" type="checkbox" class="taskpackTempId" value="${taskPackageList.id }" 
							<c:forEach items="${nowTaskPackageList }" var="nowTaskPackageList">
								<c:if test="${taskPackageList.id == nowTaskPackageList.taskpackTempId }">
									checked="checked"
								</c:if>
							</c:forEach>
						><label for="taskpackTempId${status.index+1 }">${taskPackageList.templatName }</label></span>
					</c:forEach>
					<span class="help-inline"><font color="red">*</font> </span>
				</c:if> --%>
			</div>
		</div>

		<div class="control-group" id="managerEval" style="display: none">
			<label class="control-label">项目经理评价阶段：</label>
			<table>
				<c:forEach items="${managerEvalStageList}" var="managerEvalStage"
					varStatus="status">
					<tr>
						<td style="width: 300px"><input type="checkbox"
							name="managerEvalStage" value="${managerEvalStage.value}" />${managerEvalStage.label}
						</td>
						<td>评价关联的约检节点： <select name='evalStageCheckNode'
							class='input-xlarge'>

						</select>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>





		<div class="control-group">
			<label class="control-label">评价指标设置：</label>
			<div class="controls">
				<input type="button" value="新增" class="btn btn-primary"
					onclick="add()" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"></label>
			<div class="controls">
				<table id="eval"
					class="table table-striped table-bordered table-condensed">
					<thead>
						<tr>
							<th>评价类别</th>
							<th>评价指标</th>
							<th>分值</th>
							<th></th>
						</tr>
					</thead>
					<tbody id="pingjia">
						<%-- <c:forEach items="${nowIndexList }" var="item"> 
							<tr>
								<th>
									<select name="evalRoleType" class="input-xlarge required">
										<c:forEach items="${dictList }" var="dict">
											<option value="${dict.value }" <c:if test="${item.evalRoleType == dict.value }">selected="selected"</c:if>>${dict.label }</option>
										</c:forEach>
									</select>
								</th>
								<th>
									<select name="evalIndexId" class="input-xlarge required">
										
										<c:forEach items="${bizEvalIndexList }" var="evalIndex">
											<option value="${evalIndex.id }" <c:if test="${item.evalIndexId == evalIndex.id }">selected="selected"</c:if>>${evalIndex.indexName }</option>
										</c:forEach>
									</select>
								</th>
								<th>
									<input type="text" value="${item.evalTotalScore }" name="evalTotalScore" class="required" onkeyup="if(isNaN(value))execCommand('undo')" onafterpaste="if(isNaN(value))execCommand('undo')">
								</th>
								<th>
									<input type="button" value="删除" class="btn btn-primary" onclick="del(this)"/>
								</th>
							</tr> 
						</c:forEach>  --%>
					</tbody>
				</table>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="bizevalactivity:bizEvalActivity:edit">
				<input id="btnSubmit" class="btn btn-primary" type="submit"
					value="保 存" />&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回"
				onclick="history.go(-1)" />
		</div>
	</form:form>
</body>
</html>