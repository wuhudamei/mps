<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>成本核算明细</title>
	<meta name="decorator" content="default"/>
	<meta content="*" name="Access-Control-Allow-Origin"><!--跨域请求  -->
	<script type="text/javascript">
	$(document).ready(function() {
		findEngineList();
		/* $("#orderStatusNumber option").each(function(index,element){
            var provalue = $(this).val();
            if(provalue <= '130' && provalue != ''){
                $("#orderStatusNumber option[value="+provalue+"]").remove();
            } 
		});*/

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
			$("input[name='orderStatusNumber'][value = '']").attr("cheack",true);
		}
		
		function exportDetailExcel(){
			//门店
			var storeId1 = '${detailOfCostAccounting.storeId}';
			var storeId = $("#storeId").val();
			if(storeId1 != storeId){
				alertx("请先点击查询后，再导出。");
				return false;
			}
			
			//工程模式
			var projectMode1 = '${detailOfCostAccounting.projectMode}';
			var projectMode = $("#projectMode").val();
			
			if(projectMode1 != projectMode){
				alertx("请先点击查询后，再导出。");
				return false;
			}
			//区域
			var engineDepartId1 = '${detailOfCostAccounting.engineDepartId}';
			var engineDepartId = $("#engineDepartId").val();
			if(engineDepartId == null || engineDepartId1 == 'undefined'){
				engineDepartId = '';
			}
			if(engineDepartId1 != engineDepartId){
				alertx("请先点击查询后，再导出。");
				return false;
			}
			
			//项目经理
			var itemManager1 = '${detailOfCostAccounting.itemManager}';
			var itemManager = $("#itemManager").val();
			if(itemManager1 != itemManager){
				
				alertx("请先点击查询后，再导出。");
				return false;
			}
			
			//实际开工时间 开始
			/* var beginCreateDate1 = '${detailOfCostAccounting.startActualStartDate}'; */
			
			
			var beginCreateDate = $("#startActualStartDate").val();
			var beginCreateDate1 = $("#startActualStartDate1").val();
			if(beginCreateDate != beginCreateDate1){
				alertx("请先点击查询后，再导出。");
				return false;
			}
		
			//实际开工时间 结束 
			/* var endCreateDate1 = '${detailOfCostAccounting.endActualStartDate}'; */
			var endCreateDate = $("#endActualStartDate").val();
			var endCreateDate1 = $("#endActualStartDate1").val();
			if(endCreateDate1 != endCreateDate){
				alertx("请先点击查询后，再导出。");
				return false;
			}
			
			$("#searchForm").attr("action", "${ctx}/detailOfCostAccounting/exportExcel");
			$("#searchForm").submit();
			$("#searchForm").attr("action", "${ctx}/detailOfCostAccounting/list");
			
			alertx("正在导出，请稍后...");
		
		}
	
		 function findEngineList(){
				$("#engineDepartId").html("");
				 $("#s2id_engineDepartId .select2-chosen").html("");
				 $.ajax({
		            	url:'${ctx}/engdept/bizEngineeringDepartment/departmentList',
		            	type:'post',
						dataType:'json',
						data:{
							'storeId':$('#storeId').val(),
							'projectMode':$("#projectMode").val(),
							'employeeId':$('#employeeId').val()
						},
		                success : function(data) {
		                	if(data.length<1){
								$("#engineDepartId").append("<option value='' selected='selected' >请选择...</option>");
								$("#s2id_engineDepartId .select2-chosen").html("请选择...");
							} else {
								var html = "<option value=''>请选择...</option>";
								$("#s2id_engineDepartId .select2-chosen").html("请选择...");
								for(var i=0;i<data.length;i++){
									var sec = "";
									if('${detailOfCostAccounting.engineDepartId}' == data[i].value){
					            		$("#s2id_engineDepartId .select2-chosen").html(data[i].label);
					            		html += "<option value='" + data[i].value +"' selected='selected' >" + data[i].label + "</option>"
					            	}else{
					            		html += "<option value='" + data[i].value +"'>" + data[i].label + "</option>"
					            	}
								}
				        		$("#engineDepartId").append(html);
							}
		                }
		            });
				}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/detailOfCostAccounting/list">成本核算明细</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="detailOfCostAccounting" action="${ctx}/detailOfCostAccounting/list" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>门店：</label>
				<form:select path="storeId" class="input-medium needClear" onchange="findEngineList()">
					<%--<form:option value="" label="请选择..." />--%>
					<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li>
				<label>工程模式</label>
				<c:if test="${empty projectModeEnable}">
					<form:select path="projectMode" class="input-medium needClear" onchange="findEngineList()">
						<form:option value="" label="请选择..." />
						<form:options items="${fns:getDictList('project_mode')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
				<c:if test="${!empty projectModeEnable}">
					<form:select path="projectMode" class="input-medium needClear" onchange="findEngineList()">
						<form:option value="" label="请选择..." />
						<form:options items="${fns:getDictList('project_mode')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>				
			</li>
			<li>
				<label>区域：</label>
				<form:select path="engineDepartId" class="input-medium needClear">
				</form:select>
			</li>
			<%-- <li><label>订单编号：</label>
				<form:input path="orderNumber" htmlEscape="false" maxlength="18" class="input-medium needClear"/>
			</li> --%>
			<li>
				<label>订单状态：</label>
				<form:select path="orderStatusNumber" class="input-medium needClear">
					<form:option value="" label="请选择..." />
					<form:options items="${fns:getDictList('order_status_new')}" itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
			</li>
			<li><label>项目经理：</label>
				<form:input path="itemManager" htmlEscape="false" maxlength="64" class="input-medium needClear"/>
			</li>
			
			<li><label>实际开工日期：</label>
				<input name="startActualStartDate" type="text" id="startActualStartDate" readonly="readonly" maxlength="20" class="input-medium Wdate needClear"
					value="<fmt:formatDate value="${detailOfCostAccounting.startActualStartDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'endActualStartDate\')}',isShowClear:false});"/>
					<input name="startActualStartDate1" type="hidden" id="startActualStartDate1" readonly="readonly" maxlength="20" class="input-medium Wdate needClear"
					value="<fmt:formatDate value="${detailOfCostAccounting.startActualStartDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'endActualStartDate\')}',isShowClear:false});"/> - 
				<input name="endActualStartDate" type="text" id="endActualStartDate" readonly="readonly" maxlength="20" class="input-medium Wdate needClear"
					value="<fmt:formatDate value="${detailOfCostAccounting.endActualStartDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'startActualStartDate\')}',isShowClear:false});"/>
					<input name="endActualStartDate1" type="hidden" id="endActualStartDate1" readonly="readonly" maxlength="20" class="input-medium Wdate needClear"
					value="<fmt:formatDate value="${detailOfCostAccounting.endActualStartDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'startActualStartDate\')}',isShowClear:false});"/>
			</li>
			
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="button" value="导出成本核算Excel" onclick="exportDetailExcel()"/></li>
			<li class="btns"><input class="btn btn-primary clearBtn" type="button" value="清空"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th rowspan="2" style="vertical-align:middle">序号</th>
				<th rowspan="2" style="vertical-align:middle">门店</th>
				<th rowspan="2" style="vertical-align:middle">工程模式</th>
				<th rowspan="2" style="vertical-align:middle">区域</th>
				<th rowspan="2" style="vertical-align:middle">订单编号</th>
				<th rowspan="2" style="vertical-align:middle">房屋类型</th>
				<th rowspan="2" style="vertical-align:middle">合同面积㎡</th>
				<th rowspan="2" style="vertical-align:middle">实际开工日期</th>
				<th rowspan="2" style="vertical-align:middle">计划竣工</th>
				<th rowspan="2" style="vertical-align:middle">实际竣工</th>
				<th rowspan="2" style="vertical-align:middle">项目经理</th>
				<th rowspan="2" style="vertical-align:middle">客户信息</th>
				<th colspan="8" style="text-align: center">辅料成本（元）</th>
				<th rowspan="2" style="vertical-align:middle">沙子水泥</br>（元）</th>
				<th colspan="9" style="text-align: center">人工成本（元）</th>
				<th rowspan="2" style="vertical-align:middle">项目经理提成</br>（元）</th>
				<th rowspan="2" style="vertical-align:middle">合同金额</br>（元）</th>
				<th rowspan="2" style="vertical-align:middle">订单状态</th>
			</tr>
				<!-- 辅料 -->
			<tr>
				<th style="vertical-align: middle">水电类</th>
				<th style="vertical-align: middle">拆除类</th>
				<th style="vertical-align: middle">营销类</th>
				<th style="vertical-align: middle">特殊类</th>
				<th style="vertical-align: middle">瓦工类</th>
				<th style="vertical-align: middle">木工类</th>
				<th style="vertical-align: middle">油工类</th>
				<th style="vertical-align: middle">合计</th>
				<!-- 人工成本 -->
				<th style="vertical-align: middle">水电类</th>
				<th style="vertical-align: middle">拆除类</th>
				<th style="vertical-align: middle">营销类</th>
				<th style="vertical-align: middle">特殊类</th>
				<th style="vertical-align: middle">瓦工类</th>
				<th style="vertical-align: middle">木工类</th>
				<th style="vertical-align: middle">油工类</th>
				<th style="vertical-align: middle">安装类</th>
				<th style="vertical-align: middle">合计</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="detailOfCostAccounting" varStatus="index">
			<tr>
				<td>
					${index.index + 1}
				</td>
				<td>
					${detailOfCostAccounting.storeName}
				</td>
				<td>
					${detailOfCostAccounting.projectModeName}
				</td>
				<td>
					${detailOfCostAccounting.departmentName}
				</td>
				<td>
					${detailOfCostAccounting.orderNumber }
				</td>
				<td>
					${detailOfCostAccounting.buildName }
				</td>
				<td>
					${detailOfCostAccounting.contractArea }
				</td>
				
				<td>
					<fmt:formatDate value="${detailOfCostAccounting.actualStartDate }" pattern="yyyy-MM-dd"/>
				</td>
				
				<td>
					<fmt:formatDate value="${detailOfCostAccounting.contractStartDate }" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					<fmt:formatDate value="${detailOfCostAccounting.contractEndDate }" pattern="yyyy-MM-dd"/>
				</td>
				
				<td>
					${detailOfCostAccounting.itemManager }
				</td>
				<td>
					${detailOfCostAccounting.customerName }
				</td>
				
				<td>
					${detailOfCostAccounting.fuliaoshuidian }
				</td>
				<td>
					${detailOfCostAccounting.fuliaochaichu }
				</td>
				<td>
					${detailOfCostAccounting.fuliaoyingxiao }
				</td>
				<td>
					${detailOfCostAccounting.fuliaoteshu }
				</td>
				<td>
					${detailOfCostAccounting.fuliaowagong }
				</td>
				<td>
					${detailOfCostAccounting.fuliaomugong }
				</td>
				<td>
					${detailOfCostAccounting.fuliaoyougong }
				</td>
				<td>
					${detailOfCostAccounting.fuliaoheji }
				</td>
				
				<td>
					${detailOfCostAccounting.sandCement }
				</td>
				
				<td>
					${detailOfCostAccounting.shuidian }
				</td>
				<td>
					${detailOfCostAccounting.chaichu }
				</td>
				<td>
					${detailOfCostAccounting.yingxiao }
				</td>
				<td>
					${detailOfCostAccounting.teshu }
				</td>
				<td>
					${detailOfCostAccounting.wagong }
				</td>
				<td>
					${detailOfCostAccounting.mugong }
				</td>

				<td>
					${detailOfCostAccounting.yougong }
				</td>
				<td>
					${detailOfCostAccounting.anzhuang }
				</td>
				<td>
					${detailOfCostAccounting.rengongheji }
				</td>
				
				<td>
					${detailOfCostAccounting.managerAmount }
				</td>
				
				<td>
					${detailOfCostAccounting.contractAmount }
				</td>
				
				<td>
					${detailOfCostAccounting.orderStatusDescription }
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>	
</body>
</html>