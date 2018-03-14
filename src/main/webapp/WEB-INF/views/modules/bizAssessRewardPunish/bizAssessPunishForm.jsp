<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>项目经理扣款添加</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
	$(document).ready(function() {
		getDepartments();
		findOrderInfo();
		findOrderInfoById();
		findAssessRule();
		queryTotalAmount();
	});

	$(function() {
		$("#inputForm").validate({
			rules : {
				storeId : "required",
				projectMode : "required",
				enginDepartId : "required",
				relatedBusinessIdInt : "required",
				assessRuleTypeId : "required",
				assessRuleId : "required",
				rewardPunishTargetEmployeeId : "required",
			},
			messages : {
				storeId : "请选择门店",
				projectMode : "请选择工程模式",
				enginDepartId : "请选择区域",
				relatedBusinessIdInt : "请选择客户",
				assessRuleTypeId : "请选择考核条例分类",
				assessRuleId : "请选择考核条例细则说明",
				rewardPunishTargetEmployeeId : "请选择奖励人员",
			},
			errorContainer : "#messageBox",

		});
	});

	function checkNumber(obj) {
		//先把非数字的都替换掉，除了数字和.
		obj.value = obj.value.replace(/[^\d.]/g, "");
		//必须保证第一个为数字而不是.
		obj.value = obj.value.replace(/^\./g, "");
		//保证.只出现一次，而不能出现两次以上
		obj.value = obj.value.replace(".", "$#$").replace(/\./g, "").replace(
				"$#$", ".");
		//只能输入两个小数
		obj.value = obj.value.replace(/^(\-)*(\d+)\.(\d\d).*$/, '$1$2.$3');
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
				'projectMode':$('#projectMode').val()
			},
			success:function(data){
				if(data == null){
                    $("#rewardPunishTargetEmployeeId").html('');
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
		findAssessRuleType();
	}
	
	function findOrderInfo(){
		var html3 = '<option value=""></option>';
		var storeId = $("#storeId").val();
		var projectModeValue = $("#projectMode").val();
		var engineDepartId=null;
		if($("#enginDepartId").val() ==null){
			engineDepartId='${bizAssessRewardPunish.enginDepartId}';
		}else{
			engineDepartId = $("#enginDepartId").val();
		}
        $("#relatedBusinessIdInt").html('');
		//根据 门店、工程模式、区域查询订单
		$.ajax({
			url:"${ctx}/order/order/findOrderByParam?storeId="
				+ storeId + "&projectModeValue=" + projectModeValue+"&engineDepartId="+engineDepartId,
			type:"post",
			success : function(data) {
				if(data == null){
					$("#relatedBusinessIdInt").append('');
				} else {
					var html = "<option value=''></option>";
					for(var i=0;i<data.length;i++){
						var label = data[i].communityName+"-"+data[i].buildRoom+"-"+data[i].customerName
						var sec = "";
						if('${bizAssessRewardPunish.relatedBusinessIdInt}' == data[i].orderId){
		            		sec = "selected='selected'";
		            		$("#s2id_relatedBusinessIdInt .select2-chosen").html(label);
		            	}
						html += "<option value='" + data[i].orderId +"' " + sec + ">" + label + "</option>"
					}
					html+='';
	        		$("#relatedBusinessIdInt").append(html);
				}
			}
		});
	}
	
	function findAssessRuleType(){
		$("#assessRuleTypeId").html('');
		$.ajax({
			url:'${ctx}/bizAssessRuleType/bizAssessRuleType/queryRuleTypeByParam',
			type:'post',
			dataType : 'json',
			data:{
				'storeId':$('#storeId').val(),
				'projectMode':$('#projectMode').val(),
				'isRewardOrPunish':$('#isRewardOrPunish').val(),
				'rewardPunishTargetType':$('#rewardPunishTargetType').val(),
                'isMonthInspection': $('#isMonthInspection').val(),
				'isEnabled':1
			},
			success:function(data){
				if(data == null){
					$("#assessRuleTypeId").append('');
				} else {
					var html = "<option value=''></option>";
					for(var i=0;i<data.length;i++){
						var sec = "";
						if('${bizAssessRewardPunish.assessRuleTypeId}' == data[i].id){
		            		sec = "selected='selected'";
		            		$("#s2id_assessRuleTypeId .select2-chosen").html(data[i].typeName);
		            	}
						html += "<option value='" + data[i].id +"' " + sec + ">" + data[i].typeName + "</option>"
					}
					html+='';
	        		$("#assessRuleTypeId").append(html);
				}
			}
		});
	}
	
	function findAssessRule(){
		$("#assessRuleId").html('');
		var assessRuleTypeId=$('#assessRuleTypeId').val();
		if(assessRuleTypeId == null){
			assessRuleTypeId = '${bizAssessRewardPunish.assessRuleTypeId}';
		}
		$.ajax({
			url:'${ctx}/bizAssessRule/bizAssessRule/queryAssessRuleByAssessRuleType',
			type:'post',
			dataType : 'json',
			data:{
				'bizAssessRuleTypeId':assessRuleTypeId,
				'rewardPunishTargetEmployeeType':$("#rewardPunishTargetEmployeeType").val(),
				'isEnabled':1
			},
			success:function(data){
				if(data == null){
					$("#assessRuleId").append('');
				} else {
					var html = "<option value=''></option>";
					for(var i=0;i<data.length;i++){
						var sec = "";
						if('${bizAssessRewardPunish.assessRuleId}' == data[i].id){
		            		sec = "selected='selected'";
		            		$("#s2id_assessRuleId .select2-chosen").html(data[i].bizAssessRuleDescribe);
		            	}
						html += "<option value='" + data[i].id +"' " + sec + ">" + data[i].bizAssessRuleDescribe + "</option>"
					}
					html+='';
	        		$("#assessRuleId").append(html);
				}
			}
		});
	}
	
	function findBizAssessRuleById(){
		var id = $("#assessRuleId").val();
		$.ajax({
			url:"${ctx}/bizAssessRule/bizAssessRule/findBizAssessRuleById?id="+id,
		    type:"post",
		    success:function(data){
		    	if(data!=null){
		    		$("#rewardPunishAmount").val(data.rewardPunishAmount);
		    		$("#rewardPunishScore").val(data.rewardPunishScore);
		    		if(data.rewardPunishAmount > 0){
		    			$("#rewardPunishAmount").attr("readonly","readonly");
		    		}
		    		
		    		if(data.rewardPunishScore > 0){
		    			$("#rewardPunishScore").attr("readonly","readonly");
		    		}
		    	}
		    }
		});
	}
	
	function findOrderInfoById(){
		var orderId = $("#relatedBusinessIdInt").val();
		if(orderId == null){
			orderId = '${bizAssessRewardPunish.relatedBusinessIdInt}';
		}
		$.ajax({
			url:"${ctx}/order/order/findOrderById?orderId="+orderId,
		    type:"post",
		    success:function(data){
		    	if(data!=null){
		    		$("#communityName").val(data.communityName);
		    		$("#buildNumber").val(data.buildNumber);
		    		$("#buildUnit").val(data.buildUnit);
		    		$("#buildRoom").val(data.buildRoom);
		    	}
		    }
		});
		findBizOrderDistributeLogByOrderId();
	}
	
	function findBizOrderDistributeLogByOrderId(){
		var orderId = $("#relatedBusinessIdInt").val();
		if(orderId == null){
			orderId = '${bizAssessRewardPunish.relatedBusinessIdInt}';
		}
		$("#rewardPunishTargetEmployeeId").html('');
		$.ajax({
			url:"${ctx}/bizorderdistributelog/bizOrderDistributeLog/queryOrderPmDistributeLogByOrderId?orderId="+orderId,
		    type:"post",
		    success:function(data){
		    	if(data == null){
					$("#rewardPunishTargetEmployeeId").append('');
				} else {
					var html = "<option value=''></option>";
					for(var i=0;i<data.length;i++){
						var sec = "";
						if('${bizAssessRewardPunish.rewardPunishTargetEmployeeId}' == data[i].distributedEmployeeId){
		            		sec = "selected='selected'";
		            		$("#s2id_rewardPunishTargetEmployeeId .select2-chosen").html(data[i].distributedEmployeeName);
		            	}
						html += "<option value='" + data[i].distributedEmployeeId +"' " + sec + ">" + data[i].distributedEmployeeName + "</option>"
					}
					html+='';
	        		$("#rewardPunishTargetEmployeeId").append(html);
				}
		    }
		});
	}
	
	function queryTotalAmount(){
		$("#totalAmount").val(0);
		var relatedBusinessIdInt = $("#relatedBusinessIdInt").val();
		if(relatedBusinessIdInt == null){
			relatedBusinessIdInt = "${bizAssessRewardPunish.relatedBusinessIdInt}";
		}
		var rewardPunishTargetEmployeeId = $("#rewardPunishTargetEmployeeId").val();
		if(rewardPunishTargetEmployeeId == null){
			rewardPunishTargetEmployeeId = "${bizAssessRewardPunish.rewardPunishTargetEmployeeId}";
		}
		$.ajax({
			url:'${ctx}/bizAssessRewardPunish/bizAssessRewardPunish/queryTotalAmountByParam',
			type:'post',
			dataType : 'json',
			data:{
				'relatedBusinessIdInt':relatedBusinessIdInt,
				'rewardPunishTargetEmployeeId':rewardPunishTargetEmployeeId,
				'rewardPunishTargetEmployeeType':$("#rewardPunishTargetEmployeeType").val(),
				'rewardPunishTargetType':$("#rewardPunishTargetType").val(),
				'isRewardOrPunish':$("#isRewardOrPunish").val(),
				'rewardPunishStatus':1
			},
			success:function(data){
				if(data != null && data >0){
					$("#totalAmount").val(data);
				}
			}
		});
	}
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a
			href="${ctx}/bizAssessRewardPunish/bizAssessRewardPunish/queryBizAssessPunish">项目经理扣款列表</a></li>
	</ul>
	<br />
	<form:form id="inputForm" modelAttribute="bizAssessRewardPunish"
		action="${ctx}/bizAssessRewardPunish/bizAssessRewardPunish/saveBizAssessReward"
		method="post" class="form-horizontal">
		<input type="hidden" value="0" id="isMonthInspection" name="isMonthInspection"/>
		<form:hidden path="id" />
		<sys:message content="${message}" />
		<table>
			<tr>
				<td>
					<div class="control-group">
						<label class="control-label">门店：</label>
						<div class="controls">
							<c:if test="${empty storeDropEnable}">
								<form:select path="storeId" id="storeId" class="input-medium"
									disabled="true" onchange="getDepartments()">
									<form:option value="" label="" />
									<form:options items="${fns:getStoreList()}" itemLabel="label"
										itemValue="value" htmlEscape="false" />
								</form:select>
							</c:if>
							<c:if test="${!empty storeDropEnable}">
								<form:select path="storeId" id="storeId"
									class="input-medium needClear" onchange="getDepartments()">
									<form:option value="" label="" />
									<form:options items="${fns:getStoreList()}" itemLabel="label"
										itemValue="value" htmlEscape="false" />
								</form:select>
							</c:if>
							<span class="help-inline"><font color="red">*</font> </span>
						</div>
					</div>
				</td>
				<td>
					<div class="control-group">
						<label class="control-label">工程模式：</label>
						<div class="controls">
							<form:select path="projectMode" id="projectMode"
								class="input-medium" disabled="true" onchange="getDepartments()">
								<form:option value="1" label="产业" />
							</form:select>
							<span class="help-inline"><font color="red">*</font> </span>
						</div>
					</div>
				</td>
			</tr>

			<tr>
				<td>
					<div class="control-group">
						<label class="control-label">区域：</label>
						<div class="controls">
							<form:select path="enginDepartId" id="enginDepartId"
								class="input-medium needClear" onchange="findOrderInfo()">
							</form:select>
							<span class="help-inline"><font color="red">*</font> </span>
						</div>
					</div>
				</td>

				<td>
					<div class="control-group">
						<label class="control-label">客户姓名：</label>
						<div class="controls">
							<form:select path="relatedBusinessIdInt"
								id="relatedBusinessIdInt" class="input-medium needClear" onchange="findOrderInfoById()">
							</form:select>
							<span class="help-inline"><font color="red">*</font> </span>
						</div>
					</div>
				</td>
			</tr>

			<tr>
				<td><div class="control-group">
						<label class="control-label">小区名称：</label>
						<div class="controls">
							<form:input path="communityName" id="communityName"
								htmlEscape="false" class="input-medium required" readonly="true" />
						</div>
					</div></td>

				<td><div class="control-group">
						<label class="control-label">楼号：</label>
						<div class="controls">
							<form:input path="buildNumber" id="buildNumber"
								htmlEscape="false" class="input-medium required" readonly="true" />
						</div>
					</div></td>
			</tr>

			<tr>
				<td><div class="control-group">
						<label class="control-label">单元门：</label>
						<div class="controls">
							<form:input path="buildUnit" id="buildUnit" readonly="true"
								htmlEscape="false" class="input-medium required" />
						</div>
					</div></td>

				<td><div class="control-group">
						<label class="control-label">门牌号：</label>
						<div class="controls">
							<form:input path="buildRoom" id="buildRoom" readonly="true"
								htmlEscape="false" class="input-medium required" />
						</div>
					</div></td>
			</tr>

            <tr>
              <td colspan="2">
                  <div class="control-group">
						<label class="control-label">当前已累计扣款金额：</label>
						<div class="controls">
							<input type="text" id="totalAmount" value="" readonly="readonly" />
						</div>
					</div>
              </td>
            </tr>
            
			<tr>
				<td>
					<div class="control-group">
						<label class="control-label">考核条例分类：</label>
						<input type="hidden" value="2" id="isRewardOrPunish" name="isRewardOrPunish"/>
						<input type="hidden" value="10" id="rewardPunishTargetType" name="rewardPunishTargetType"/>
						<div class="controls">
							<form:select path="assessRuleTypeId" id="assessRuleTypeId"
								class="input-medium" onchange="findAssessRule()">
							</form:select>
							<span class="help-inline"><font color="red">*</font> </span>
						</div>
					</div>
				</td>

				<td>
					<div class="control-group">
						<label class="control-label">扣款日期：</label>
						<div class="controls">
							<input name="rewardPunishDatetime" type="text"
								readonly="readonly" maxlength="20" id="rewardPunishDatetime"
								class="input-medium Wdate required"
								onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});" value="<fmt:formatDate
							value="${bizAssessRewardPunish.rewardPunishDatetime}"
							pattern="yyyy-MM-dd" />"/>
							<span class="help-inline"><font color="red">*</font> </span>
						</div>
					</div>
				</td>
			</tr>

			<tr>
				<td colspan="2">
					<div class="control-group">
						<label class="control-label">考核条例细则说明：</label>
						<div class="controls">
							<form:select path="assessRuleId" id="assessRuleId"
								class="input-medium" cssStyle="width:500px;" onchange="findBizAssessRuleById()">
							</form:select>
							<span class="help-inline"><font color="red">*</font> </span>
						</div>
					</div>
				</td>
			</tr>

			<tr>
				<td>
					<div class="control-group">
						<label class="control-label">扣款金额：</label>
						<div class="controls">
							<form:input path="rewardPunishAmount" id="rewardPunishAmount"
								htmlEscape="false" class="input-medium required" oninput="checkNumber(this)"/>
							<span class="help-inline"><font color="red">*</font> </span>
						</div>
					</div>
				</td>

				<td>
					<div class="control-group">
						<label class="control-label">扣款分数：</label>
						<div class="controls">
							<form:input path="rewardPunishScore" id="rewardPunishScore"
								htmlEscape="false" class="input-medium required" oninput="checkNumber(this)"/>
							<span class="help-inline"><font color="red">*</font> </span>
						</div>
					</div>
				</td>
			</tr>

			<tr>
               <td>
                  <div class="control-group">
						<label class="control-label">责任人：</label>
						<div class="controls">
						    <input type="hidden" value="1" name="rewardPunishTargetEmployeeType" />
							<form:select path="rewardPunishTargetEmployeeType" id="rewardPunishTargetEmployeeType"
								class="input-medium" disabled="true">
								<form:option value="1" label="项目经理" />
							</form:select>
							<span class="help-inline"><font color="red">*</font> </span>
						</div>
					</div>
               </td>
               
               <td>
                   <div class="control-group">
						<label class="control-label">扣款人员：</label>
						<div class="controls">
							<form:select path="rewardPunishTargetEmployeeId" id="rewardPunishTargetEmployeeId"
								class="input-medium" onchange="queryTotalAmount()">
							</form:select>
							<span class="help-inline"><font color="red">*</font> </span>
						</div>
					</div>
               </td>
			</tr>
		</table>
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit"
				value="保 存" />&nbsp; <input id="btnCancel" class="btn"
				type="button" value="返 回" onclick="history.go(-1)" />
		</div>
	</form:form>
</body>
</html>