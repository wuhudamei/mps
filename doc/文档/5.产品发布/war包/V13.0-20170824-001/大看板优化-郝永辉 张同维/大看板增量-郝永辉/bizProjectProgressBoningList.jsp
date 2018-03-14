<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>大看板</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			getDepartments();
		});

		function getDepartments(){
			$("#s2id_enginDepartId .select2-chosen").html("");
			$.ajax({
				url:'${ctx}/engdept/bizEngineeringDepartment/departmentList',
				type:'post',
				dataType : 'json',
				data:{
					'storeId':$('#storeId').val(),
					'projectMode':$("#projectMode").val(),
					'employeeId':$('#employeeId').val()
				},
				success:function(data){
					if(data == null || data == ""){
						$("#enginDepartId").html("");
						$("#s2id_enginDepartId .select2-chosen").html("");
					}else{
						var content = "<option></option>";
						for(var i=0;i<data.length;i++){
							if('${bizProjectProgressBoning.enginDepartId}' == data[i].value){
								$("#s2id_enginDepartId .select2-chosen").html(data[i].label);
								content = content + "<option value='"+data[i].value+"' selected='selected'>"+data[i].label+"</option>";
							}else{
								content = content + "<option value='"+data[i].value+"'>"+data[i].label+"</option>";
							}
						}
						$("#enginDepartId").html(content);
					}
				}
			});
		}
		
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

		function eidtOrderNode(obj,orderId){
			top.$.jBox.confirm("您确认更新吗？","系统提示",function(v,h,f){
				if(v=="ok"){
					loading('正在提交，请稍等...');
					$(obj).removeAttr("href");
					$(obj).removeAttr("onclick");
					window.location.href="${ctx}/projectprogressboning/bizProjectProgressBoning/editOrderNode?orderId="+orderId;
				}
			},{buttonsFocus:1});
			top.$('.jbox-body .jbox-icon').css('top','55px');
		}

		function exportExcel(){
			// 判断门店
			if($("#storeId").val() != null && '${bizProjectProgressBoning.storeId}' != $("#storeId").val()){
				alertx("请先查询后，再导出");
				return;
			}
			// 判断工程模式
			if($("#projectMode").val() != null && '${bizProjectProgressBoning.projectMode}' != $("#projectMode").val()){
				alertx("请先查询后，再导出");
				return;
			}
			// 判断区域
			if($("#enginDepartId").val() != null && '${bizProjectProgressBoning.enginDepartId}' != $("#enginDepartId").val()){
				alertx("请先查询后，再导出");
				return;
			}
			// 判断项目经理
			if($("#itemManager").val() != null && '${bizProjectProgressBoning.itemManager}' != $("#itemManager").val()){
				alertx("请先查询后，再导出");
				return;
			}
			// 判断新老房
			if($("#houseIsNew").val() != null && '${bizProjectProgressBoning.houseIsNew}' != $("#houseIsNew").val()){
				alertx("请先查询后，再导出");
				return;
			}
			// 判断实际开工日期
			if(($("#beginActualStartDate").val() != null && format('${bizProjectProgressBoning.beginActualStartDate}',"yyyy-MM-dd") != $("#beginActualStartDate").val()) || ($("#endActualStartDate").val() != null && format('${bizProjectProgressBoning.endActualStartDate}',"yyyy-MM-dd") != $("#endActualStartDate").val())){
				alertx("请先查询后，再导出");
				return;
			}

			$("#searchForm").attr("action", "${ctx}/projectprogressboning/bizProjectProgressBoning/exportExcel");
			$("#searchForm").submit();
			$("#searchForm").attr("action", "${ctx}/projectprogressboning/bizProjectProgressBoning/loadList");
		}

		function searchList(){
			$("#searchForm").attr("action", "${ctx}/projectprogressboning/bizProjectProgressBoning/loadList");
			$("#searchForm").submit();
		}

		var format = function(time, format){
			if(time == null || time == ""){
				return "";
			}
			var t = new Date(time);
			var tf = function(i){return (i < 10 ? '0' : '') + i};
			return format.replace(/yyyy|MM|dd|HH|mm|ss/g, function(a){
				switch(a){
					case 'yyyy':
						return tf(t.getFullYear());
						break;
					case 'MM':
						return tf(t.getMonth() + 1);
						break;
					case 'mm':
						return tf(t.getMinutes());
						break;
					case 'dd':
						return tf(t.getDate());
						break;
					case 'HH':
						return tf(t.getHours());
						break;
					case 'ss':
						return tf(t.getSeconds());
						break;
				}
			})
		}
		
		function boingExplain(){
			window.location.href="${ctx}/projectprogressboning/bizProjectProgressBoning/openBizProjectProgressBoningExplain";	
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/projectprogressboning/bizProjectProgressBoning/loadList">大看板列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="bizProjectProgressBoning" action="${ctx}/projectprogressboning/bizProjectProgressBoning/loadList" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<input type="hidden" id="employeeId" name="employeeId" value="${employeeId}">
		<ul class="ul-form">
			<li><label>门店：</label>
				<c:if test="${empty storeDropEnable}">
					<form:select path="storeId" class="input-medium" disabled="true" id="storeId" onchange="getDepartments()">
						<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
				<c:if test="${!empty storeDropEnable}">
					<form:select path="storeId" class="input-medium" id="storeId" onchange="getDepartments()">
						<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
			</li>
			<li><label>工程模式：</label>
				<c:if test="${!empty gongcheng}">
					<form:select path="projectMode" class="input-medium"  id="projectMode" onchange="getDepartments()">
						<form:options items="${fns:getDictList('project_mode')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
				<c:if test="${empty gongcheng}">
					<form:select path="projectMode" class="input-medium" disabled="true" id="projectMode" onchange="getDepartments()">
						<form:options items="${fns:getDictList('project_mode')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
			</li>
			<li><label>区域：</label>
				<select id="enginDepartId" name="enginDepartId" class="input-medium needClear">

				</select>
			</li>
			<li><label>项目经理：</label>
				<form:input path="itemManager" htmlEscape="false" maxlength="64" class="input-medium" id="itemManager"/>
			</li>
			
			<li><label>客户姓名：</label>
				<form:input path="customerName" htmlEscape="false" maxlength="64" class="input-medium" id="customerName"/>
			</li>
			
			<li><label>新老房：</label>
				<form:select path="houseIsNew" class="input-medium needClear" id="houseIsNew">
					<form:option value="" label=""/>
					<form:option value="0" label="老房"/>
					<form:option value="1" label="新房"/>
				</form:select>
			</li>
			<li><label style="width: 110px">实际开工日期：</label>
				<input name="beginActualStartDate" id="beginActualStartDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					   value="<fmt:formatDate value="${bizProjectProgressBoning.beginActualStartDate}" pattern="yyyy-MM-dd"/>"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/> -
				<input name="endActualStartDate" id="endActualStartDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					   value="<fmt:formatDate value="${bizProjectProgressBoning.endActualStartDate}" pattern="yyyy-MM-dd"/>"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</li>
			
			<li><label>是否作废：</label>
				<form:select path="isScrap" class="input-medium needClear" id="isScrap">
					<form:option value="" label=""/>
					<form:option value="0" label="否"/>
					<form:option value="1" label="是"/>
				</form:select>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="button" value="查询" onclick="searchList()"/></li>
			<li class="btns"><input class="btn btn-primary clearBtn" type="button" value="清空" onclick="clearCondition()"/></li>
			<li class="btns"><input class="btn btn-primary" type="button" value="导出Excel" onclick="exportExcel()"/></li>
			<li class="btns"><input class="btn btn-primary" type="button" value="取值说明" onclick="boingExplain()"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th rowspan="2" style="vertical-align:middle">门店</th>
				<th rowspan="2" style="vertical-align:middle">区域</th>
				<th rowspan="2" style="vertical-align:middle">订单编号</th>
				<th rowspan="2" style="vertical-align:middle">片区</th>
				<th rowspan="2" style="vertical-align:middle">接单日期</th>
				<th rowspan="2" style="vertical-align:middle">客户姓名</th>
				<th rowspan="2" style="vertical-align:middle">客户电话</th>
				<th rowspan="2" style="vertical-align:middle">工程地址</th>
				<th colspan="2" style="text-align: center">项目经理</th>
				<th colspan="2" style="text-align: center">设计员</th>
				<th colspan="2" style="text-align: center">质检员</th>
				<th colspan="2" style="text-align: center">合同工期时间</th>
				<th colspan="2" style="text-align: center">实际工期时间</th>
				<th rowspan="2" style="vertical-align:middle">开工延期天数</th>
				<th rowspan="2" style="vertical-align:middle">客户认可延期天数</th>
				<th rowspan="2" style="vertical-align:middle">实际开工客<br/>户是否签字</th>
				<th rowspan="2" style="vertical-align:middle">变更/停电/停<br/>水/客户自装项<br/>目延期天数</th>
				<th rowspan="2" style="vertical-align:middle">客户是<br/>否签字</th>
				<th colspan="4" style="text-align: center">主材第一次核尺</th>
				<th colspan="5" style="text-align: center">辅材进场</th>
				<th colspan="5" style="text-align: center">瓷砖</th>
				<th colspan="5" style="text-align: center">水电隐蔽验收</th>
				<th colspan="5" style="text-align: center">防水验收</th>
				<th colspan="4" style="text-align: center">橱柜核尺</th>
				<th colspan="5" style="text-align: center">瓦工验收</th>
				<th colspan="4" style="text-align: center">二期款（同瓦工验收日期）</th>
				<th colspan="5" style="text-align: center">基础施工验收</th>
				<th colspan="7" style="text-align: center">厨卫吊顶</th>
				<th colspan="7" style="text-align: center">洁具</th>
				<th colspan="7" style="text-align: center">五金，灯具，开关面板</th>
				<th colspan="7" style="text-align: center">橱柜</th>
				<th colspan="7" style="text-align: center">定制衣柜</th>
				<th colspan="7" style="text-align: center">壁纸</th>
				<th colspan="7" style="text-align: center">木门，铝镁门，门窗套</th>
				<th colspan="7" style="text-align: center">木地板</th>
				<th colspan="7" style="text-align: center">窗帘</th>
				<th colspan="5" style="text-align: center">竣工验收</th>
				<th colspan="3" style="text-align: center">尾款</th>
				<th colspan="7" style="text-align: center">家电</th>
				<th colspan="7" style="text-align: center">家具</th>
				<shiro:hasPermission name="projectprogressboning:bizProjectProgressBoning:edit"><th rowspan="2" style="vertical-align:middle">操作</th></shiro:hasPermission>
			</tr>
			<tr>
				<th style="vertical-align: middle">姓名</th>
				<th style="vertical-align: middle">电话</th>
				<th style="vertical-align: middle">姓名</th>
				<th style="vertical-align: middle">电话</th>
				<th style="vertical-align: middle">姓名</th>
				<th style="vertical-align: middle">电话</th>
				<th style="vertical-align: middle">合同签订开工日期</th>
				<th style="vertical-align: middle">合同签订竣工日期</th>
				<th style="vertical-align: middle">实际开工日期</th>
				<th style="vertical-align: middle">实际竣工日期</th>
				<!-- 主材第一次核尺 -->
				<th style="vertical-align: middle">计划</th>
				<th style="vertical-align: middle">提报时间</th>
				<th style="vertical-align: middle">实际</th>
				<th style="vertical-align: middle">正常/延期/<br/>提前天数</th>
				<!-- 辅材进场 -->
				<th style="vertical-align: middle">计划</th>
					<th style="vertical-align: middle">提报时间</th>
				<th style="vertical-align: middle">申请期望</th>
				<th style="vertical-align: middle">实际</th>
				<th style="vertical-align: middle">正常/延期/<br/>提前天数</th>
				<!-- 瓷砖 -->
				<th style="vertical-align: middle">计划</th>
				<th style="vertical-align: middle">提报时间</th>
				<th style="vertical-align: middle">申请期望</th>
				<th style="vertical-align: middle">实际</th>
				<th style="vertical-align: middle">正常/延期/<br/>提前天数</th>
				<!-- 水电隐蔽验收 -->
				<th style="vertical-align: middle">计划</th>
				<th style="vertical-align: middle">提报时间</th>
				<th style="vertical-align: middle">期望</th>
				<th style="vertical-align: middle">实际</th>
				<th style="vertical-align: middle">正常/延期/<br/>提前天数</th>
				<!-- 防水验收 -->
				<th style="vertical-align: middle">计划</th>
				<th style="vertical-align: middle">提报时间</th>
				<th style="vertical-align: middle">期望</th>
				<th style="vertical-align: middle">实际</th>
				<th style="vertical-align: middle">正常/延期/<br/>提前天数</th>
				<!-- 橱柜核尺 -->
				<th style="vertical-align: middle">计划</th>
				<th style="vertical-align: middle">提报时间</th>
				<th style="vertical-align: middle">实际</th>
				<th style="vertical-align: middle">正常/延期/<br/>提前天数</th>
				<!-- 瓦工验收 -->
				<th style="vertical-align: middle">计划</th>
				<th style="vertical-align: middle">提报时间</th>
				<th style="vertical-align: middle">期望</th>
				<th style="vertical-align: middle">实际</th>
				<th style="vertical-align: middle">正常/延期/<br/>提前天数</th>
				<!-- 	二期款（同瓦工验收日期） -->
				<th style="vertical-align: middle">计划</th>
				<th style="vertical-align: middle">催款</th>
				<th style="vertical-align: middle">实际</th>
				<th style="vertical-align: middle">正常/延期/<br/>提前天数</th>
				<!-- 	基础施工验收 -->
				<th style="vertical-align: middle">计划</th>
				<th style="vertical-align: middle">提报时间</th>
				<th style="vertical-align: middle">期望</th>
				<th style="vertical-align: middle">实际</th>
				<th style="vertical-align: middle">正常/延期/<br/>提前天数</th>
				<!-- 厨卫吊顶 -->
				<th style="vertical-align: middle">计划</th>
				<th style="vertical-align: middle">提报时间</th>
				<th style="vertical-align: middle">申请进场</th>
				<th style="vertical-align: middle">实际进场</th>
				<th style="vertical-align: middle">实际完工</th>
				<th style="vertical-align: middle">实际验收</th>
				<th style="vertical-align: middle">正常/延期/<br/>提前天数</th>
				<!-- 洁具	 -->
				<th style="vertical-align: middle">计划时间</th>
				<th style="vertical-align: middle">提报时间</th>
				<th style="vertical-align: middle">申请进场</th>
				<th style="vertical-align: middle">实际进场</th>
				<th style="vertical-align: middle">实际完工</th>
				<th style="vertical-align: middle">实际验收</th>
				<th style="vertical-align: middle">正常/延期/<br/>提前天数</th>
				<!-- 五金，灯具，开关面板 -->
				<th style="vertical-align: middle">计划</th>
				<th style="vertical-align: middle">提报时间</th>
				<th style="vertical-align: middle">申请进场</th>
				<th style="vertical-align: middle">实际进场</th>
				<th style="vertical-align: middle">实际完工</th>
				<th style="vertical-align: middle">实际验收</th>
				<th style="vertical-align: middle">正常/延期/<br/>提前天数</th>
				<!-- 橱柜 -->
				<th style="vertical-align: middle">计划</th>
				<th style="vertical-align: middle">提报时间</th>
				<th style="vertical-align: middle">申请进场</th>
				<th style="vertical-align: middle">实际进场</th>
				<th style="vertical-align: middle">实际完工</th>
				<th style="vertical-align: middle">实际验收</th>
				<th style="vertical-align: middle">正常/延期/<br/>提前天数</th>
				<!-- 	定制衣柜-->
				<th style="vertical-align: middle">计划</th>
				<th style="vertical-align: middle">提报时间</th>
				<th style="vertical-align: middle">申请进场</th>
				<th style="vertical-align: middle">实际进场</th>
				<th style="vertical-align: middle">实际完工</th>
				<th style="vertical-align: middle">实际验收</th>
				<th style="vertical-align: middle">正常/延期/<br/>提前天数</th>
				<!-- 	壁纸-->
				<th style="vertical-align: middle">计划</th>
				<th style="vertical-align: middle">提报时间</th>
				<th style="vertical-align: middle">申请进场</th>
				<th style="vertical-align: middle">实际进场</th>
				<th style="vertical-align: middle">实际完工</th>
				<th style="vertical-align: middle">实际验收</th>
				<th style="vertical-align: middle">正常/延期/<br/>提前天数</th>
				<!-- 	木门，铝镁门，门窗套-->
				<th style="vertical-align: middle">计划</th>
				<th style="vertical-align: middle">提报时间</th>
				<th style="vertical-align: middle">申请进场</th>
				<th style="vertical-align: middle">实际进场</th>
				<th style="vertical-align: middle">实际完工</th>
				<th style="vertical-align: middle">实际验收</th>
				<th style="vertical-align: middle">正常/延期/<br/>提前天数</th>
				<!-- 木地板-->
				<th style="vertical-align: middle">计划</th>
				<th style="vertical-align: middle">提报时间</th>
				<th style="vertical-align: middle">申请进场</th>
				<th style="vertical-align: middle">实际进场</th>
				<th style="vertical-align: middle">实际完工</th>
				<th style="vertical-align: middle">实际验收</th>
				<th style="vertical-align: middle">正常/延期/<br/>提前天数</th>
				<!-- 窗帘-->
				<th style="vertical-align: middle">计划</th>
				<th style="vertical-align: middle">提报时间</th>
				<th style="vertical-align: middle">申请进场</th>
				<th style="vertical-align: middle">实际进场</th>
				<th style="vertical-align: middle">实际完工</th>
				<th style="vertical-align: middle">实际验收</th>
				<th style="vertical-align: middle">正常/延期/<br/>提前天数</th>
				<!-- 竣工验收-->
				<th style="vertical-align: middle">计划</th>
				<th style="vertical-align: middle">提报时间</th>
				<th style="vertical-align: middle">期望</th>
				<th style="vertical-align: middle">实际</th>
				<th style="vertical-align: middle">正常/延期/<br/>提前天数</th>
				<!-- 尾款-->
				<th style="vertical-align: middle">计划</th>
				<th style="vertical-align: middle">实际</th>
				<th style="vertical-align: middle">正常/延期/<br/>提前天数</th>
				<!-- 家电-->
				<th style="vertical-align: middle">计划</th>
				<th style="vertical-align: middle">提报时间</th>
				<th style="vertical-align: middle">申请进场</th>
				<th style="vertical-align: middle">实际进场</th>
				<th style="vertical-align: middle">实际完工</th>
				<th style="vertical-align: middle">实际验收</th>
				<th style="vertical-align: middle">正常/延期/<br/>提前天数</th>
				<!-- 家具 -->
				<th style="vertical-align: middle">计划</th>
				<th style="vertical-align: middle">提报时间</th>
				<th style="vertical-align: middle">申请进场</th>
				<th style="vertical-align: middle">实际进场</th>
				<th style="vertical-align: middle">实际完工</th>
				<th style="vertical-align: middle">实际验收</th>
				<th style="vertical-align: middle">正常/延期/<br/>提前天数</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="boning">
			<tr>
				<td>${fns:getStoreLabel(boning.storeId, '')}</td>
				<td>${boning.enginDepartName}</td>
				<td>${boning.orderNumber}</td>
				<td>${boning.area}</td>
				<td><fmt:formatDate value="${boning.orderCreateDate}" pattern="yyyy-MM-dd"/></td>
				<td>${boning.customerName}</td>
				<td>${boning.customerPhone}</td>
				<td>${boning.detailAddress}</td>
				<td>${boning.itemManager}</td>
				<td>${boning.itemManagerPhone}</td>
				<td>${boning.designerName}</td>
				<td>${boning.designerPhone}</td>
				<td>${boning.orderInspector}</td>
				<td>${boning.inspectorPhone}</td>
				<td><fmt:formatDate value="${boning.contractStartDate}" pattern="yyyy-MM-dd"/></td>
				<td><fmt:formatDate value="${boning.contractEndDate}" pattern="yyyy-MM-dd"/></td>
				<td><fmt:formatDate value="${boning.actualStartDate}" pattern="yyyy-MM-dd"/></td>
				<td><fmt:formatDate value="${boning.actualEndDate}" pattern="yyyy-MM-dd"/></td>
				<td>${boning.startDiffDay}</td>
				<td>${boning.delayDays}</td>
				<td><c:if test="${boning.isNeedSign eq '0'}">否</c:if><c:if test="${boning.isNeedSign eq '1'}">是</c:if></td>
				<td>${boning.selfDecorateDelayDays}</td>
				<td><c:if test="${boning.isSelfDecorateNeedSign eq '0'}">否</c:if><c:if test="${boning.isSelfDecorateNeedSign eq '1'}">是</c:if></td>
				<!-- 主材第一次核尺 -->
				<td><fmt:formatDate value="${boning.node1PlanDate}" pattern="yyyy-MM-dd"/></td>
				<td><fmt:formatDate value="${boning.node1SubmDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td><fmt:formatDate value="${boning.node1ActualDate}" pattern="yyyy-MM-dd"/></td>
				<td>${boning.node1DiffDay}</td>
				<!-- 辅材进场 -->
				<td><fmt:formatDate value="${boning.node2PlanDate}" pattern="yyyy-MM-dd"/></td>
				<td><fmt:formatDate value="${boning.node2SubmDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td><fmt:formatDate value="${boning.node2ExpectDate}" pattern="yyyy-MM-dd"/></td>
				<td><fmt:formatDate value="${boning.node2ActualDate}" pattern="yyyy-MM-dd"/></td>
				<td>${boning.node2DiffDay}</td>
				<!-- 瓷砖-->
				<td><fmt:formatDate value="${boning.node3PlanDate}" pattern="yyyy-MM-dd"/></td>
				<td><fmt:formatDate value="${boning.node3SubmDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td><fmt:formatDate value="${boning.node3ExpectDate}" pattern="yyyy-MM-dd"/></td>
				<td><fmt:formatDate value="${boning.node3ActualDate}" pattern="yyyy-MM-dd"/></td>
				<td>${boning.node3DiffDay}</td>
				<!-- 水电隐蔽验收-->
				<td><fmt:formatDate value="${boning.node4PlanDate}" pattern="yyyy-MM-dd"/></td>
				<td><fmt:formatDate value="${boning.node4SubmDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td><fmt:formatDate value="${boning.node4ExpectDate}" pattern="yyyy-MM-dd"/></td>
				<td><fmt:formatDate value="${boning.node4ActualDate}" pattern="yyyy-MM-dd"/></td>
				<td>${boning.node4DiffDay}</td>
				<!-- 防水验收-->
				<td><fmt:formatDate value="${boning.node5PlanDate}" pattern="yyyy-MM-dd"/></td>
				<td><fmt:formatDate value="${boning.node5SubmDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td><fmt:formatDate value="${boning.node5ExpectDate}" pattern="yyyy-MM-dd"/></td>
				<td><fmt:formatDate value="${boning.node5ActualDate}" pattern="yyyy-MM-dd"/></td>
				<td>${boning.node5DiffDay}</td>
				<!--橱柜核尺-->
				<td><fmt:formatDate value="${boning.node6PlanDate}" pattern="yyyy-MM-dd"/></td>
				<td><fmt:formatDate value="${boning.node6SubmDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td><fmt:formatDate value="${boning.node6ActualDate}" pattern="yyyy-MM-dd"/></td>
				<td>${boning.node6DiffDay}</td>
				<!--瓦工验收-->
				<td><fmt:formatDate value="${boning.node7PlanDate}" pattern="yyyy-MM-dd"/></td>
				<td><fmt:formatDate value="${boning.node7SubmDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td><fmt:formatDate value="${boning.node7ExpectDate}" pattern="yyyy-MM-dd"/></td>
				<td><fmt:formatDate value="${boning.node7ActualDate}" pattern="yyyy-MM-dd"/></td>
				<td>${boning.node7DiffDay}</td>
				<!--二期款（同瓦工验收日期）-->
				<td><fmt:formatDate value="${boning.node8PlanDate}" pattern="yyyy-MM-dd"/></td>
				<td><fmt:formatDate value="${boning.node8AmountDate}" pattern="yyyy-MM-dd"/></td>
				<td><fmt:formatDate value="${boning.node8ActualDate}" pattern="yyyy-MM-dd"/></td>
				<td>${boning.node8DiffDay}</td>
				<!--基础施工验收-->
				<td><fmt:formatDate value="${boning.node9PlanDate}" pattern="yyyy-MM-dd"/></td>
				<td><fmt:formatDate value="${boning.node9SubmDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td><fmt:formatDate value="${boning.node9ExpectDate}" pattern="yyyy-MM-dd"/></td>
				<td><fmt:formatDate value="${boning.node9ActualDate}" pattern="yyyy-MM-dd"/></td>
				<td>${boning.node9DiffDay}</td>
				<!-- 厨卫吊顶 -->
				<td><fmt:formatDate value="${boning.node10PlanDate}" pattern="yyyy-MM-dd"/></td>
				<td><fmt:formatDate value="${boning.node10SubmDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td><fmt:formatDate value="${boning.node10ApplyEntryDate}" pattern="yyyy-MM-dd"/></td>
				<td><fmt:formatDate value="${boning.node10ActualEntryDate}" pattern="yyyy-MM-dd"/></td>
				<td><fmt:formatDate value="${boning.node10ActualDate}" pattern="yyyy-MM-dd"/></td>
				<td><fmt:formatDate value="${boning.node10ActualCheckDate}" pattern="yyyy-MM-dd"/></td>
				<td>${boning.node10DiffDay}</td>
				<!-- 洁具 -->
				<td><fmt:formatDate value="${boning.node11PlanDate}" pattern="yyyy-MM-dd"/></td>
				<td><fmt:formatDate value="${boning.node11SubmDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td><fmt:formatDate value="${boning.node11ApplyEntryDate}" pattern="yyyy-MM-dd"/></td>
				<td><fmt:formatDate value="${boning.node11ActualEntryDate}" pattern="yyyy-MM-dd"/></td>
				<td><fmt:formatDate value="${boning.node11InstallDate}" pattern="yyyy-MM-dd"/></td>
				<td><fmt:formatDate value="${boning.node11ActualDate}" pattern="yyyy-MM-dd"/></td>
				<td>${boning.node11DiffDay}</td>
				<!--五金，灯具，开关面板 -->
				<td><fmt:formatDate value="${boning.node12PlanDate}" pattern="yyyy-MM-dd"/></td>
				<td><fmt:formatDate value="${boning.node12SubmDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td><fmt:formatDate value="${boning.node12ApplyEntryDate}" pattern="yyyy-MM-dd"/></td>
				<td><fmt:formatDate value="${boning.node12ActualEntryDate}" pattern="yyyy-MM-dd"/></td>
				<td><fmt:formatDate value="${boning.node12InstallDate}" pattern="yyyy-MM-dd"/></td>
				<td><fmt:formatDate value="${boning.node12ActualDate}" pattern="yyyy-MM-dd"/></td>
				<td>${boning.node12DiffDay}</td>
				<!--橱柜 -->
				<td><fmt:formatDate value="${boning.node13PlanDate}" pattern="yyyy-MM-dd"/></td>
				<td><fmt:formatDate value="${boning.node13SubmDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td><fmt:formatDate value="${boning.node13ApplyEntryDate}" pattern="yyyy-MM-dd"/></td>
				<td><fmt:formatDate value="${boning.node13ActualEntryDate}" pattern="yyyy-MM-dd"/></td>
				<td><fmt:formatDate value="${boning.node13InstallDate}" pattern="yyyy-MM-dd"/></td>
				<td><fmt:formatDate value="${boning.node13ActualDate}" pattern="yyyy-MM-dd"/></td>
				<td>${boning.node13DiffDay}</td>
				<!--定制衣柜 -->
				<td><fmt:formatDate value="${boning.node14PlanDate}" pattern="yyyy-MM-dd"/></td>
				<td><fmt:formatDate value="${boning.node14SubmDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td><fmt:formatDate value="${boning.node14ApplyEntryDate}" pattern="yyyy-MM-dd"/></td>
				<td><fmt:formatDate value="${boning.node14ActualEntryDate}" pattern="yyyy-MM-dd"/></td>
				<td><fmt:formatDate value="${boning.node14InstallDate}" pattern="yyyy-MM-dd"/></td>
				<td><fmt:formatDate value="${boning.node14ActualDate}" pattern="yyyy-MM-dd"/></td>
				<td>${boning.node14DiffDay}</td>
				<!--壁纸-->
				<td><fmt:formatDate value="${boning.node15PlanDate}" pattern="yyyy-MM-dd"/></td>
				<td><fmt:formatDate value="${boning.node15SubmDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td><fmt:formatDate value="${boning.node15ApplyEntryDate}" pattern="yyyy-MM-dd"/></td>
				<td><fmt:formatDate value="${boning.node15ActualEntryDate}" pattern="yyyy-MM-dd"/></td>
				<td><fmt:formatDate value="${boning.node15InstallDate}" pattern="yyyy-MM-dd"/></td>
				<td><fmt:formatDate value="${boning.node15ActualDate}" pattern="yyyy-MM-dd"/></td>
				<td>${boning.node15DiffDay}</td>
				<!--木门，铝镁门，门窗套-->
				<td><fmt:formatDate value="${boning.node16PlanDate}" pattern="yyyy-MM-dd"/></td>
				<td><fmt:formatDate value="${boning.node16SubmDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td><fmt:formatDate value="${boning.node16ApplyEntryDate}" pattern="yyyy-MM-dd"/></td>
				<td><fmt:formatDate value="${boning.node16ActualEntryDate}" pattern="yyyy-MM-dd"/></td>
				<td><fmt:formatDate value="${boning.node16InstallDate}" pattern="yyyy-MM-dd"/></td>
				<td><fmt:formatDate value="${boning.node16ActualDate}" pattern="yyyy-MM-dd"/></td>
				<td>${boning.node16DiffDay}</td>
				<!--木地板-->
				<td><fmt:formatDate value="${boning.node17PlanDate}" pattern="yyyy-MM-dd"/></td>
				<td><fmt:formatDate value="${boning.node17SubmDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td><fmt:formatDate value="${boning.node17ApplyEntryDate}" pattern="yyyy-MM-dd"/></td>
				<td><fmt:formatDate value="${boning.node17ActualEntryDate}" pattern="yyyy-MM-dd"/></td>
				<td><fmt:formatDate value="${boning.node17InstallDate}" pattern="yyyy-MM-dd"/></td>
				<td><fmt:formatDate value="${boning.node17ActualDate}" pattern="yyyy-MM-dd"/></td>
				<td>${boning.node17DiffDay}</td>
				<!--窗帘-->
				<td><fmt:formatDate value="${boning.node18PlanDate}" pattern="yyyy-MM-dd"/></td>
				<td><fmt:formatDate value="${boning.node18SubmDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td><fmt:formatDate value="${boning.node18ApplyEntryDate}" pattern="yyyy-MM-dd"/></td>
				<td><fmt:formatDate value="${boning.node18ActualEntryDate}" pattern="yyyy-MM-dd"/></td>
				<td><fmt:formatDate value="${boning.node18InstallDate}" pattern="yyyy-MM-dd"/></td>
				<td><fmt:formatDate value="${boning.node18ActualDate}" pattern="yyyy-MM-dd"/></td>
				<td>${boning.node18DiffDay}</td>
				<!--竣工验收-->
				<td><fmt:formatDate value="${boning.node19PlanDate}" pattern="yyyy-MM-dd"/></td>
				<td><fmt:formatDate value="${boning.node19SubmDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td><fmt:formatDate value="${boning.node19ExpectDate}" pattern="yyyy-MM-dd"/></td>
				<td><fmt:formatDate value="${boning.node19ActualDate}" pattern="yyyy-MM-dd"/></td>
				<td>${boning.node19DiffDay}</td>
				<!--尾款-->
				<td><fmt:formatDate value="${boning.node20PlanDate}" pattern="yyyy-MM-dd"/></td>
				<td><fmt:formatDate value="${boning.node20ActualDate}" pattern="yyyy-MM-dd"/></td>
				<td>${boning.node20DiffDay}</td>
				<!--家电-->
				<td><fmt:formatDate value="${boning.node21PlanDate}" pattern="yyyy-MM-dd"/></td>
				<td><fmt:formatDate value="${boning.node21SubmDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td><fmt:formatDate value="${boning.node21ApplyEntryDate}" pattern="yyyy-MM-dd"/></td>
				<td><fmt:formatDate value="${boning.node21ActualEntryDate}" pattern="yyyy-MM-dd"/></td>
				<td><fmt:formatDate value="${boning.node21InstallDate}" pattern="yyyy-MM-dd"/></td>
				<td><fmt:formatDate value="${boning.node21ActualDate}" pattern="yyyy-MM-dd"/></td>
				<td>${boning.node21DiffDay}</td>
				<!--家具-->
				<td><fmt:formatDate value="${boning.node22PlanDate}" pattern="yyyy-MM-dd"/></td>
				<td><fmt:formatDate value="${boning.node22SubmDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td><fmt:formatDate value="${boning.node22ApplyEntryDate}" pattern="yyyy-MM-dd"/></td>
				<td><fmt:formatDate value="${boning.node22ActualEntryDate}" pattern="yyyy-MM-dd"/></td>
				<td><fmt:formatDate value="${boning.node22InstallDate}" pattern="yyyy-MM-dd"/></td>
				<td><fmt:formatDate value="${boning.node22ActualDate}" pattern="yyyy-MM-dd"/></td>
				<td>${boning.node22DiffDay}</td>
				<shiro:hasPermission name="projectprogressboning:bizProjectProgressBoning:edit"><td><a href="#" onclick="eidtOrderNode(this,${boning.orderId})">更新</a></td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>