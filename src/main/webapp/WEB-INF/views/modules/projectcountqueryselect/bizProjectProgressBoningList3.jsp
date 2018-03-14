<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>大看板</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			findEngineListByStoreIdAndProjectMode();
		});

		function findEngineListByStoreIdAndProjectMode(){
			var html3 = '<option value=""></option>';
			var storeId = $("#storeId").val();
			var projectModeValue = "2";
			if (storeId=="" ||projectModeValue=="" ||undefined==storeId ||undefined==projectModeValue) {
				return;
			}
			//根据门店个,工程模式    动态加载工程区域
			$.ajax({
				url:'${ctx}/engdept/bizEngineeringDepartment/departmentList',
				type:'post',
				dataType : 'json',
				data:{
					'storeId':storeId,
					'projectMode':projectModeValue,
					'employeeId':$('#employeeId').val()
				},
				success:function(data){
					if(data == null || data == ""){
						$("#engineDepartId").html("");
						$("#s2id_engineDepartId .select2-chosen").html("");
					}else{
						var content = "<option></option>";
						for(var i=0;i<data.length;i++){
							if('${bizTraditionOrder.engineDepartId}' == data[i].value){
								$("#s2id_engineDepartId .select2-chosen").html(data[i].label);
								content = content + "<option value='"+data[i].value+"' selected='selected'>"+data[i].label+"</option>";
							}else{
								content = content + "<option value='"+data[i].value+"'>"+data[i].label+"</option>";
							}
						}
						$("#engineDepartId").html(content);
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

		
		function exportExcel(){
			var storeId = $("#storeId").val();
			if(null == storeId || storeId <1){
				alertx("请先选择门店后，再导出");
				return;
			}
			// 判断门店
			if($("#storeId").val() != null && '${bizTraditionOrder.storeId}' != $("#storeId").val()){
				alertx("请先查询后，再导出");
				return;
			}
			// 判断区域
			if($("#engineDepartId").val() != null && '${bizTraditionOrder.engineDepartId}' != $("#engineDepartId").val()){
				alertx("请先查询后，再导出");
				return;
			}
			//实际开工日期  开始
			var beginActualStartDate1 = $("#beginActualStartDate1").val();
			var beginActualStartDate = $("#beginActualStartDate").val();
			if(beginActualStartDate1 != beginActualStartDate){
				alertx("请先查询后，再导出");
				return;
			}
			
			//实际开工日期  结束
			var endActualStartDate1 = $("#endActualStartDate1").val();
			var endActualStartDate = $("#endActualStartDate").val();
			if(endActualStartDate1 != endActualStartDate){
				alertx("请先查询后，再导出");
				return;
			}
			$("#searchForm").attr("action", "${ctx}/projectprogressboning/bizProjectProgressBoningTraditionOrder/exportExcel");
			$("#searchForm").submit();
		}

		function searchList(){
			var storeId = $("#storeId").val();
			if(null == storeId || storeId <1){
				alertx("请先选择门店后，再查询");
				return;
			}
			$("#searchForm").attr("action", "${ctx}/projectprogressboning/bizProjectProgressBoningTraditionOrder/loadList");
			$("#searchForm").submit();
		}
	
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/projectprogressboning/bizProjectProgressBoningTraditionOrder/list">传统订单工程进度大看板</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="bizTraditionOrder"  method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<input type="hidden" id="employeeId" name="employeeId" value="${employeeId}">
		
		<!-- 实际开工日期 -->
		<input id="beginActualStartDate1" type="hidden" value="<fmt:formatDate value="${bizTraditionOrder.beginActualStartDate}" pattern="yyyy-MM-dd"/>">
		<input id="endActualStartDate1" type="hidden" value="<fmt:formatDate value="${bizTraditionOrder.endActualStartDate}" pattern="yyyy-MM-dd"/>">
		
		<ul class="ul-form">
			<li><label>门店：</label>
				<c:if test="${empty storeDropEnable}">
					<form:select path="storeId" class="input-medium" disabled="true" onchange="findEngineListByStoreIdAndProjectMode()">
						<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
				<c:if test="${!empty storeDropEnable}">
					<form:select path="storeId" class="input-medium needClear" onchange="findEngineListByStoreIdAndProjectMode()">
						<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
			</li>
			<li><label>区域：</label>
				<select id="engineDepartId" name="engineDepartId" class="input-medium needClear">

				</select>
			</li>
			<li><label>实际开工日期：</label>
				<input name="beginActualStartDate" id="beginActualStartDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${bizTraditionOrder.beginActualStartDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'endActualStartDate\')}',isShowClear:true});"/> 至  
				<input name="endActualStartDate" id="endActualStartDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${bizTraditionOrder.endActualStartDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'beginActualStartDate\')}',isShowClear:true});"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="button" value="查询" onclick="searchList()"/></li>
			<li class="btns"><input class="btn btn-primary clearBtn" type="button" value="清空" onclick="clearCondition()"/></li>
			<li class="btns"><input class="btn btn-primary" type="button" value="导出Excel" onclick="exportExcel()"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th rowspan="2" style="vertical-align:middle">门店</th>
				<th rowspan="2" style="vertical-align:middle">区域</th>
				<th rowspan="2" style="vertical-align:middle">新老房</th>
				<th rowspan="2" style="vertical-align:middle">接单日期</th>
				<th rowspan="2" style="vertical-align:middle">客户姓名</th>
				<th rowspan="2" style="vertical-align:middle">客户电话</th>
				<th rowspan="2" style="vertical-align:middle">工程地址</th>
				<th colspan="2" style="text-align: center">项目经理</th>
				<th colspan="2" style="text-align: center">开同开工时间</th>
				<th colspan="2" style="text-align: center">实际工期时间</th>
				<th rowspan="2" style="vertical-align:middle">开工延期天数</th>
				<th rowspan="2" style="vertical-align:middle">实际开工客<br/>户是否签字</th>
				<th rowspan="2" style="vertical-align:middle">变更/停电/停<br/>水/客户自装项<br/>目延期天数</th>
				<th rowspan="2" style="vertical-align:middle">客户是<br/>否签字</th>
				<th colspan="3" style="text-align: center">主材第一次核尺</th>
				<th colspan="3" style="text-align: center">辅材进场</th>
				<th colspan="3" style="text-align: center">瓷砖</th>
				<th colspan="3" style="text-align: center">水电隐蔽验收</th>
				<th colspan="3" style="text-align: center">防水验收</th>
				<th colspan="3" style="text-align: center">橱柜核尺</th>
				<th colspan="3" style="text-align: center">瓦工验收</th>
				<th colspan="3" style="text-align: center">二期款（同瓦工验收日期）</th>
				<th colspan="3" style="text-align: center">基础施工验收</th>
				<th colspan="3" style="text-align: center">厨卫吊顶</th>
				<th colspan="3" style="text-align: center">洁具</th>
				<th colspan="3" style="text-align: center">五金，灯具，开关面板</th>
				<th colspan="3" style="text-align: center">橱柜</th>
				<th colspan="3" style="text-align: center">定制衣柜</th>
				<th colspan="3" style="text-align: center">壁纸</th>
				<th colspan="3" style="text-align: center">木门，铝镁门，门窗套</th>
				<th colspan="3" style="text-align: center">木地板</th>
				<th colspan="3" style="text-align: center">窗帘</th>
				<th colspan="3" style="text-align: center">竣工验收</th>
				<th colspan="3" style="text-align: center">尾款</th>
				<th colspan="3" style="text-align: center">家电</th>
				<th colspan="3" style="text-align: center">家具</th>
			</tr>
			<tr>
				<th style="vertical-align: middle">姓名</th>
				<th style="vertical-align: middle">电话</th>
				<th style="vertical-align: middle">合同签订开工日期</th>
				<th style="vertical-align: middle">合同签订竣工日期</th>
				<th style="vertical-align: middle">实际开工日期</th>
				<th style="vertical-align: middle">实际竣工日期</th>
				<th style="vertical-align: middle">计划</th>
				<th style="vertical-align: middle">实际</th>
				<th style="vertical-align: middle">正常/延期/<br/>提前天数</th>
				<th style="vertical-align: middle">计划</th>
				<th style="vertical-align: middle">实际</th>
				<th style="vertical-align: middle">正常/延期/<br/>提前天数</th>
				<th style="vertical-align: middle">计划</th>
				<th style="vertical-align: middle">实际</th>
				<th style="vertical-align: middle">正常/延期/<br/>提前天数</th>
				<th style="vertical-align: middle">计划</th>
				<th style="vertical-align: middle">实际</th>
				<th style="vertical-align: middle">正常/延期/<br/>提前天数</th>
				<th style="vertical-align: middle">计划</th>
				<th style="vertical-align: middle">实际</th>
				<th style="vertical-align: middle">正常/延期/<br/>提前天数</th>
				<th style="vertical-align: middle">计划</th>
				<th style="vertical-align: middle">实际</th>
				<th style="vertical-align: middle">正常/延期/<br/>提前天数</th>
				<th style="vertical-align: middle">计划</th>
				<th style="vertical-align: middle">实际</th>
				<th style="vertical-align: middle">正常/延期/<br/>提前天数</th>
				<th style="vertical-align: middle">计划</th>
				<th style="vertical-align: middle">实际</th>
				<th style="vertical-align: middle">正常/延期/<br/>提前天数</th>
				<th style="vertical-align: middle">计划</th>
				<th style="vertical-align: middle">实际</th>
				<th style="vertical-align: middle">正常/延期/<br/>提前天数</th>
				<th style="vertical-align: middle">计划</th>
				<th style="vertical-align: middle">实际</th>
				<th style="vertical-align: middle">正常/延期/<br/>提前天数</th>
				<th style="vertical-align: middle">计划</th>
				<th style="vertical-align: middle">实际</th>
				<th style="vertical-align: middle">正常/延期/<br/>提前天数</th>
				<th style="vertical-align: middle">计划</th>
				<th style="vertical-align: middle">实际</th>
				<th style="vertical-align: middle">正常/延期/<br/>提前天数</th>
				<th style="vertical-align: middle">计划</th>
				<th style="vertical-align: middle">实际</th>
				<th style="vertical-align: middle">正常/延期/<br/>提前天数</th>
				<th style="vertical-align: middle">计划</th>
				<th style="vertical-align: middle">实际</th>
				<th style="vertical-align: middle">正常/延期/<br/>提前天数</th>
				<th style="vertical-align: middle">计划</th>
				<th style="vertical-align: middle">实际</th>
				<th style="vertical-align: middle">正常/延期/<br/>提前天数</th>
				<th style="vertical-align: middle">计划</th>
				<th style="vertical-align: middle">实际</th>
				<th style="vertical-align: middle">正常/延期/<br/>提前天数</th>
				<th style="vertical-align: middle">计划</th>
				<th style="vertical-align: middle">实际</th>
				<th style="vertical-align: middle">正常/延期/<br/>提前天数</th>
				<th style="vertical-align: middle">计划</th>
				<th style="vertical-align: middle">实际</th>
				<th style="vertical-align: middle">正常/延期/<br/>提前天数</th>
				<th style="vertical-align: middle">计划</th>
				<th style="vertical-align: middle">实际</th>
				<th style="vertical-align: middle">正常/延期/<br/>提前天数</th>
				<th style="vertical-align: middle">计划</th>
				<th style="vertical-align: middle">实际</th>
				<th style="vertical-align: middle">正常/延期/<br/>提前天数</th>
				<th style="vertical-align: middle">计划</th>
				<th style="vertical-align: middle">实际</th>
				<th style="vertical-align: middle">正常/延期/<br/>提前天数</th>
				<th style="vertical-align: middle">计划</th>
				<th style="vertical-align: middle">实际</th>
				<th style="vertical-align: middle">正常/延期/<br/>提前天数</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="nodePlan">
			<tr>
				<td>${nodePlan.storeName}</td>
				<td>${nodePlan.engineDepartName}</td>
				<td>${nodePlan.houseIsNewName}</td>
				<td><fmt:formatDate value="${nodePlan.getOrderDatetime}" pattern="yyyy-MM-dd"/></td>
				<td>${nodePlan.customerName}</td>
				<td>${nodePlan.customerPhone}</td>
				<td>${nodePlan.communityName}-${nodePlan.buildNumber}-${nodePlan.buildUnit}-${nodePlan.buildRoom}</td>
				<td>${nodePlan.itemManager}</td>
				<td>${nodePlan.itemManagerPhone}</td>
				<td><fmt:formatDate value="${nodePlan.contractStartDate}" pattern="yyyy-MM-dd"/></td>
				<td><fmt:formatDate value="${nodePlan.contractEndDate}" pattern="yyyy-MM-dd"/></td>
				<td><fmt:formatDate value="${nodePlan.actualStartDate}" pattern="yyyy-MM-dd"/></td>
				<td><fmt:formatDate value="${nodePlan.actualEndDate}" pattern="yyyy-MM-dd"/></td>
				<td>${nodePlan.startDiffDay}</td>
				<td><c:if test="${nodePlan.isNeedSign eq '0'}">否</c:if><c:if test="${nodePlan.isNeedSign eq '1'}">是</c:if></td>
				<td>${nodePlan.selfDecorateDelayDays}</td>
				<td><c:if test="${nodePlan.isSelfDecorateNeedSign eq '0'}">否</c:if><c:if test="${nodePlan.isSelfDecorateNeedSign eq '1'}">是</c:if></td>
				<!-- 第1个节点 -->
				<td>
					<c:forEach items="${nodePlan.nodePlanList}" var="a">
						<c:if test="${a.nodeIndex==1 }">
							<fmt:formatDate value="${a.planDoneDate}" pattern="yyyy-MM-dd"/>
						</c:if>
					</c:forEach>
				</td>
				<td>
					<c:forEach items="${nodePlan.nodePlanList}" var="a">
						<c:if test="${a.nodeIndex==1 }">
							<fmt:formatDate value="${a.realDoneDate}" pattern="yyyy-MM-dd"/>
						</c:if>
					</c:forEach>
				</td>
				<td>
					<c:forEach items="${nodePlan.nodePlanList}" var="a">
						<c:if test="${a.nodeIndex==1 }">
							${a.planDiffDay}
						</c:if>
					</c:forEach>
				</td>
				<!-- 第2个节点 -->
				<td>
					<c:forEach items="${nodePlan.nodePlanList}" var="a">
						<c:if test="${a.nodeIndex==2 }">
							<fmt:formatDate value="${a.planDoneDate}" pattern="yyyy-MM-dd"/>
						</c:if>
					</c:forEach>
				</td>
				<td>
					<c:forEach items="${nodePlan.nodePlanList}" var="a">
						<c:if test="${a.nodeIndex==2 }">
							<fmt:formatDate value="${a.realDoneDate}" pattern="yyyy-MM-dd"/>
						</c:if>
					</c:forEach>
				</td>
				<td>
					<c:forEach items="${nodePlan.nodePlanList}" var="a">
						<c:if test="${a.nodeIndex==2 }">
							${a.planDiffDay}
						</c:if>
					</c:forEach>
				</td>
				<!-- 第3个节点 -->
				<td>
					<c:forEach items="${nodePlan.nodePlanList}" var="a">
						<c:if test="${a.nodeIndex==3 }">
							<fmt:formatDate value="${a.planDoneDate}" pattern="yyyy-MM-dd"/>
						</c:if>
					</c:forEach>
				</td>
				<td>
					<c:forEach items="${nodePlan.nodePlanList}" var="a">
						<c:if test="${a.nodeIndex==3 }">
							<fmt:formatDate value="${a.realDoneDate}" pattern="yyyy-MM-dd"/>
						</c:if>
					</c:forEach>
				</td>
				<td>
					<c:forEach items="${nodePlan.nodePlanList}" var="a">
						<c:if test="${a.nodeIndex==3 }">
							${a.planDiffDay}
						</c:if>
					</c:forEach>
				</td>
				<!-- 第4个节点 -->
				<td>
					<c:forEach items="${nodePlan.nodePlanList}" var="a">
						<c:if test="${a.nodeIndex==4 }">
							<fmt:formatDate value="${a.planDoneDate}" pattern="yyyy-MM-dd"/>
						</c:if>
					</c:forEach>
				</td>
				<td>
					<c:forEach items="${nodePlan.nodePlanList}" var="a">
						<c:if test="${a.nodeIndex==4 }">
							<fmt:formatDate value="${a.realDoneDate}" pattern="yyyy-MM-dd"/>
						</c:if>
					</c:forEach>
				</td>
				<td>
					<c:forEach items="${nodePlan.nodePlanList}" var="a">
						<c:if test="${a.nodeIndex==4 }">
							${a.planDiffDay}
						</c:if>
					</c:forEach>
				</td>
				<!-- 第5个节点 -->
				<td>
					<c:forEach items="${nodePlan.nodePlanList}" var="a">
						<c:if test="${a.nodeIndex==5 }">
							<fmt:formatDate value="${a.planDoneDate}" pattern="yyyy-MM-dd"/>
						</c:if>
					</c:forEach>
				</td>
				<td>
					<c:forEach items="${nodePlan.nodePlanList}" var="a">
						<c:if test="${a.nodeIndex==5 }">
							<fmt:formatDate value="${a.realDoneDate}" pattern="yyyy-MM-dd"/>
						</c:if>
					</c:forEach>
				</td>
				<td>
					<c:forEach items="${nodePlan.nodePlanList}" var="a">
						<c:if test="${a.nodeIndex==5 }">
							${a.planDiffDay}
						</c:if>
					</c:forEach>
				</td>
				<!-- 第6个节点 -->
				<td>
					<c:forEach items="${nodePlan.nodePlanList}" var="a">
						<c:if test="${a.nodeIndex==6 }">
							<fmt:formatDate value="${a.planDoneDate}" pattern="yyyy-MM-dd"/>
						</c:if>
					</c:forEach>
				</td>
				<td>
					<c:forEach items="${nodePlan.nodePlanList}" var="a">
						<c:if test="${a.nodeIndex==6 }">
							<fmt:formatDate value="${a.realDoneDate}" pattern="yyyy-MM-dd"/>
						</c:if>
					</c:forEach>
				</td>
				<td>
					<c:forEach items="${nodePlan.nodePlanList}" var="a">
						<c:if test="${a.nodeIndex==6 }">
							${a.planDiffDay}
						</c:if>
					</c:forEach>
				</td>
				<!-- 第7个节点 -->
				<td>
					<c:forEach items="${nodePlan.nodePlanList}" var="a">
						<c:if test="${a.nodeIndex==7 }">
							<fmt:formatDate value="${a.planDoneDate}" pattern="yyyy-MM-dd"/>
						</c:if>
					</c:forEach>
				</td>
				<td>
					<c:forEach items="${nodePlan.nodePlanList}" var="a">
						<c:if test="${a.nodeIndex==7 }">
							<fmt:formatDate value="${a.realDoneDate}" pattern="yyyy-MM-dd"/>
						</c:if>
					</c:forEach>
				</td>
				<td>
					<c:forEach items="${nodePlan.nodePlanList}" var="a">
						<c:if test="${a.nodeIndex==7 }">
							${a.planDiffDay}
						</c:if>
					</c:forEach>
				</td>
				<!-- 第8个节点 -->
				<td>
					<c:forEach items="${nodePlan.nodePlanList}" var="a">
						<c:if test="${a.nodeIndex==8 }">
							<fmt:formatDate value="${a.planDoneDate}" pattern="yyyy-MM-dd"/>
						</c:if>
					</c:forEach>
				</td>
				<td>
					<c:forEach items="${nodePlan.nodePlanList}" var="a">
						<c:if test="${a.nodeIndex==8 }">
							<fmt:formatDate value="${a.realDoneDate}" pattern="yyyy-MM-dd"/>
						</c:if>
					</c:forEach>
				</td>
				<td>
					<c:forEach items="${nodePlan.nodePlanList}" var="a">
						<c:if test="${a.nodeIndex==8 }">
							${a.planDiffDay}
						</c:if>
					</c:forEach>
				</td>
				<!-- 第9个节点 -->
				<td>
					<c:forEach items="${nodePlan.nodePlanList}" var="a">
						<c:if test="${a.nodeIndex==9 }">
							<fmt:formatDate value="${a.planDoneDate}" pattern="yyyy-MM-dd"/>
						</c:if>
					</c:forEach>
				</td>
				<td>
					<c:forEach items="${nodePlan.nodePlanList}" var="a">
						<c:if test="${a.nodeIndex==9 }">
							<fmt:formatDate value="${a.realDoneDate}" pattern="yyyy-MM-dd"/>
						</c:if>
					</c:forEach>
				</td>
				<td>
					<c:forEach items="${nodePlan.nodePlanList}" var="a">
						<c:if test="${a.nodeIndex==9 }">
							${a.planDiffDay}
						</c:if>
					</c:forEach>
				</td>
				<!-- 第10个节点 -->
				<td>
					<c:forEach items="${nodePlan.nodePlanList}" var="a">
						<c:if test="${a.nodeIndex==10 }">
							<fmt:formatDate value="${a.planDoneDate}" pattern="yyyy-MM-dd"/>
						</c:if>
					</c:forEach>
				</td>
				<td>
					<c:forEach items="${nodePlan.nodePlanList}" var="a">
						<c:if test="${a.nodeIndex==10 }">
							<fmt:formatDate value="${a.realDoneDate}" pattern="yyyy-MM-dd"/>
						</c:if>
					</c:forEach>
				</td>
				<td>
					<c:forEach items="${nodePlan.nodePlanList}" var="a">
						<c:if test="${a.nodeIndex==10 }">
							${a.planDiffDay}
						</c:if>
					</c:forEach>
				</td>
				<!-- 第11个节点 -->
				<td>
					<c:forEach items="${nodePlan.nodePlanList}" var="a">
						<c:if test="${a.nodeIndex==11 }">
							<fmt:formatDate value="${a.planDoneDate}" pattern="yyyy-MM-dd"/>
						</c:if>
					</c:forEach>
				</td>
				<td>
					<c:forEach items="${nodePlan.nodePlanList}" var="a">
						<c:if test="${a.nodeIndex==11 }">
							<fmt:formatDate value="${a.realDoneDate}" pattern="yyyy-MM-dd"/>
						</c:if>
					</c:forEach>
				</td>
				<td>
					<c:forEach items="${nodePlan.nodePlanList}" var="a">
						<c:if test="${a.nodeIndex==11 }">
							${a.planDiffDay}
						</c:if>
					</c:forEach>
				</td>
				<!-- 第12个节点 -->
				<td>
					<c:forEach items="${nodePlan.nodePlanList}" var="a">
						<c:if test="${a.nodeIndex==12 }">
							<fmt:formatDate value="${a.planDoneDate}" pattern="yyyy-MM-dd"/>
						</c:if>
					</c:forEach>
				</td>
				<td>
					<c:forEach items="${nodePlan.nodePlanList}" var="a">
						<c:if test="${a.nodeIndex==12 }">
							<fmt:formatDate value="${a.realDoneDate}" pattern="yyyy-MM-dd"/>
						</c:if>
					</c:forEach>
				</td>
				<td>
					<c:forEach items="${nodePlan.nodePlanList}" var="a">
						<c:if test="${a.nodeIndex==12 }">
							${a.planDiffDay}
						</c:if>
					</c:forEach>
				</td>
				<!-- 第13个节点 -->
				<td>
					<c:forEach items="${nodePlan.nodePlanList}" var="a">
						<c:if test="${a.nodeIndex==13 }">
							<fmt:formatDate value="${a.planDoneDate}" pattern="yyyy-MM-dd"/>
						</c:if>
					</c:forEach>
				</td>
				<td>
					<c:forEach items="${nodePlan.nodePlanList}" var="a">
						<c:if test="${a.nodeIndex==13 }">
							<fmt:formatDate value="${a.realDoneDate}" pattern="yyyy-MM-dd"/>
						</c:if>
					</c:forEach>
				</td>
				<td>
					<c:forEach items="${nodePlan.nodePlanList}" var="a">
						<c:if test="${a.nodeIndex==13 }">
							${a.planDiffDay}
						</c:if>
					</c:forEach>
				</td>
				<!-- 第14个节点 -->
				<td>
					<c:forEach items="${nodePlan.nodePlanList}" var="a">
						<c:if test="${a.nodeIndex==14 }">
							<fmt:formatDate value="${a.planDoneDate}" pattern="yyyy-MM-dd"/>
						</c:if>
					</c:forEach>
				</td>
				<td>
					<c:forEach items="${nodePlan.nodePlanList}" var="a">
						<c:if test="${a.nodeIndex==14 }">
							<fmt:formatDate value="${a.realDoneDate}" pattern="yyyy-MM-dd"/>
						</c:if>
					</c:forEach>
				</td>
				<td>
					<c:forEach items="${nodePlan.nodePlanList}" var="a">
						<c:if test="${a.nodeIndex==14 }">
							${a.planDiffDay}
						</c:if>
					</c:forEach>
				</td>
				<!-- 第15个节点 -->
				<td>
					<c:forEach items="${nodePlan.nodePlanList}" var="a">
						<c:if test="${a.nodeIndex==15 }">
							<fmt:formatDate value="${a.planDoneDate}" pattern="yyyy-MM-dd"/>
						</c:if>
					</c:forEach>
				</td>
				<td>
					<c:forEach items="${nodePlan.nodePlanList}" var="a">
						<c:if test="${a.nodeIndex==15 }">
							<fmt:formatDate value="${a.realDoneDate}" pattern="yyyy-MM-dd"/>
						</c:if>
					</c:forEach>
				</td>
				<td>
					<c:forEach items="${nodePlan.nodePlanList}" var="a">
						<c:if test="${a.nodeIndex==15 }">
							${a.planDiffDay}
						</c:if>
					</c:forEach>
				</td>
				<!-- 第16个节点 -->
				<td>
					<c:forEach items="${nodePlan.nodePlanList}" var="a">
						<c:if test="${a.nodeIndex==16 }">
							<fmt:formatDate value="${a.planDoneDate}" pattern="yyyy-MM-dd"/>
						</c:if>
					</c:forEach>
				</td>
				<td>
					<c:forEach items="${nodePlan.nodePlanList}" var="a">
						<c:if test="${a.nodeIndex==16 }">
							<fmt:formatDate value="${a.realDoneDate}" pattern="yyyy-MM-dd"/>
						</c:if>
					</c:forEach>
				</td>
				<td>
					<c:forEach items="${nodePlan.nodePlanList}" var="a">
						<c:if test="${a.nodeIndex==16 }">
							${a.planDiffDay}
						</c:if>
					</c:forEach>
				</td>
				<!-- 第17个节点 -->
				<td>
					<c:forEach items="${nodePlan.nodePlanList}" var="a">
						<c:if test="${a.nodeIndex==17 }">
							<fmt:formatDate value="${a.planDoneDate}" pattern="yyyy-MM-dd"/>
						</c:if>
					</c:forEach>
				</td>
				<td>
					<c:forEach items="${nodePlan.nodePlanList}" var="a">
						<c:if test="${a.nodeIndex==17 }">
							<fmt:formatDate value="${a.realDoneDate}" pattern="yyyy-MM-dd"/>
						</c:if>
					</c:forEach>
				</td>
				<td>
					<c:forEach items="${nodePlan.nodePlanList}" var="a">
						<c:if test="${a.nodeIndex==17 }">
							${a.planDiffDay}
						</c:if>
					</c:forEach>
				</td>
				<!-- 第18个节点 -->
				<td>
					<c:forEach items="${nodePlan.nodePlanList}" var="a">
						<c:if test="${a.nodeIndex==18 }">
							<fmt:formatDate value="${a.planDoneDate}" pattern="yyyy-MM-dd"/>
						</c:if>
					</c:forEach>
				</td>
				<td>
					<c:forEach items="${nodePlan.nodePlanList}" var="a">
						<c:if test="${a.nodeIndex==18 }">
							<fmt:formatDate value="${a.realDoneDate}" pattern="yyyy-MM-dd"/>
						</c:if>
					</c:forEach>
				</td>
				<td>
					<c:forEach items="${nodePlan.nodePlanList}" var="a">
						<c:if test="${a.nodeIndex==18 }">
							${a.planDiffDay}
						</c:if>
					</c:forEach>
				</td>
				<!-- 第19个节点 -->
				<td>
					<c:forEach items="${nodePlan.nodePlanList}" var="a">
						<c:if test="${a.nodeIndex==19 }">
							<fmt:formatDate value="${a.planDoneDate}" pattern="yyyy-MM-dd"/>
						</c:if>
					</c:forEach>
				</td>
				<td>
					<c:forEach items="${nodePlan.nodePlanList}" var="a">
						<c:if test="${a.nodeIndex==19 }">
							<fmt:formatDate value="${a.realDoneDate}" pattern="yyyy-MM-dd"/>
						</c:if>
					</c:forEach>
				</td>
				<td>
					<c:forEach items="${nodePlan.nodePlanList}" var="a">
						<c:if test="${a.nodeIndex==19 }">
							${a.planDiffDay}
						</c:if>
					</c:forEach>
				</td>
				<!-- 第20个节点 -->
				<td>
					<c:forEach items="${nodePlan.nodePlanList}" var="a">
						<c:if test="${a.nodeIndex==20 }">
							<fmt:formatDate value="${a.planDoneDate}" pattern="yyyy-MM-dd"/>
						</c:if>
					</c:forEach>
				</td>
				<td>
					<c:forEach items="${nodePlan.nodePlanList}" var="a">
						<c:if test="${a.nodeIndex==20 }">
							<fmt:formatDate value="${a.realDoneDate}" pattern="yyyy-MM-dd"/>
						</c:if>
					</c:forEach>
				</td>
				<td>
					<c:forEach items="${nodePlan.nodePlanList}" var="a">
						<c:if test="${a.nodeIndex==20 }">
							${a.planDiffDay}
						</c:if>
					</c:forEach>
				</td>
				<!-- 第21个节点 -->
				<td>
					<c:forEach items="${nodePlan.nodePlanList}" var="a">
						<c:if test="${a.nodeIndex==21 }">
							<fmt:formatDate value="${a.planDoneDate}" pattern="yyyy-MM-dd"/>
						</c:if>
					</c:forEach>
				</td>
				<td>
					<c:forEach items="${nodePlan.nodePlanList}" var="a">
						<c:if test="${a.nodeIndex==21 }">
							<fmt:formatDate value="${a.realDoneDate}" pattern="yyyy-MM-dd"/>
						</c:if>
					</c:forEach>
				</td>
				<td>
					<c:forEach items="${nodePlan.nodePlanList}" var="a">
						<c:if test="${a.nodeIndex==21 }">
							${a.planDiffDay}
						</c:if>
					</c:forEach>
				</td>
				<!-- 第22个节点 -->
				<td>
					<c:forEach items="${nodePlan.nodePlanList}" var="a">
						<c:if test="${a.nodeIndex==22 }">
							<fmt:formatDate value="${a.planDoneDate}" pattern="yyyy-MM-dd"/>
						</c:if>
					</c:forEach>
				</td>
				<td>
					<c:forEach items="${nodePlan.nodePlanList}" var="a">
						<c:if test="${a.nodeIndex==22 }">
							<fmt:formatDate value="${a.realDoneDate}" pattern="yyyy-MM-dd"/>
						</c:if>
					</c:forEach>
				</td>
				<td>
					<c:forEach items="${nodePlan.nodePlanList}" var="a">
						<c:if test="${a.nodeIndex==22 }">
							${a.planDiffDay}
						</c:if>
					</c:forEach>
				</td>
				
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>