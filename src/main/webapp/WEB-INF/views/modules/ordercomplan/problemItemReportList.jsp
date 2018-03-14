<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>订单投诉问题管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			findEngineListByStoreIdAndProjectMode();
			    
			    function mc(tabl1, startRow, endRow, col) {
			        var tb = document.getElementById(tabl1);
			        if (col >= tb.rows[0].cells.length) {
			            return;
			        }
			        endRow = tb.rows.length-1;
			        if (col == 0) { endRow = tb.rows.length-1; }
			        for (var i = startRow; i < endRow; i++) {
			            if ($.trim(tb.rows[startRow].cells[col].innerHTML) == $.trim(tb.rows[i + 1].cells[col].innerHTML)) {
			                tb.rows[i + 1].removeChild(tb.rows[i + 1].cells[col]);
			                tb.rows[startRow].cells[col].rowSpan = (tb.rows[startRow].cells[col].rowSpan | 0) + 1;
			            } else {
			                startRow = i + 1;
			            }
			        }
			    }
			    
			    mc('contentTable',1,0,7); 
			    function mc(tabl1, startRow, endRow, col) {
			        var tb = document.getElementById(tabl1);
			        if (col >= tb.rows[0].cells.length) {
			            return;
			        }
			        endRow = tb.rows.length-1;
			        if (col == 0) { endRow = tb.rows.length-1; }
			        for (var i = startRow; i < endRow; i++) {
			            if ($.trim(tb.rows[startRow].cells[col].innerHTML) == $.trim(tb.rows[i + 1].cells[col].innerHTML)) {
			                tb.rows[i + 1].removeChild(tb.rows[i + 1].cells[col]);
			                tb.rows[startRow].cells[col].rowSpan = (tb.rows[startRow].cells[col].rowSpan | 0) + 1;
			            } else {
			                startRow = i + 1;
			            }
			        }
			    }
			    mc('contentTable',1,0,6); 
			    
			    function mc(tabl1, startRow, endRow, col) {
			        var tb = document.getElementById(tabl1);
			        if (col >= tb.rows[0].cells.length) {
			            return;
			        }
			        endRow = tb.rows.length-1;
			        if (col == 0) { endRow = tb.rows.length-1; }
			        for (var i = startRow; i < endRow; i++) {
			            if ($.trim(tb.rows[startRow].cells[col].innerHTML) == $.trim(tb.rows[i + 1].cells[col].innerHTML)) {
			                tb.rows[i + 1].removeChild(tb.rows[i + 1].cells[col]);
			                tb.rows[startRow].cells[col].rowSpan = (tb.rows[startRow].cells[col].rowSpan | 0) + 1;
			            } else {
			                startRow = i + 1;
			            }
			        }
			    }
			    mc('contentTable',1,0,4); 
			    
			    function mc(tabl1, startRow, endRow, col) {
			        var tb = document.getElementById(tabl1);
			        if (col >= tb.rows[0].cells.length) {
			            return;
			        }
			        endRow = tb.rows.length-1;
			        if (col == 0) { endRow = tb.rows.length-1; }
			        for (var i = startRow; i < endRow; i++) {
			            if ($.trim(tb.rows[startRow].cells[col].innerHTML) == $.trim(tb.rows[i + 1].cells[col].innerHTML)) {
			                tb.rows[i + 1].removeChild(tb.rows[i + 1].cells[col]);
			                tb.rows[startRow].cells[col].rowSpan = (tb.rows[startRow].cells[col].rowSpan | 0) + 1;
			            } else {
			                startRow = i + 1;
			            }
			        }
			    }
			    
			    mc('contentTable',1,0,1);
			    
			    function mc(tabl1, startRow, endRow, col) {
			        var tb = document.getElementById(tabl1);
			        if (col >= tb.rows[0].cells.length) {
			            return;
			        }
			        endRow = tb.rows.length-1;
			        if (col == 0) { endRow = tb.rows.length-1; }
			        for (var i = startRow; i < endRow; i++) {
			            if ($.trim(tb.rows[startRow].cells[col].innerHTML) == $.trim(tb.rows[i + 1].cells[col].innerHTML)) {
			                tb.rows[i + 1].removeChild(tb.rows[i + 1].cells[col]);
			                tb.rows[startRow].cells[col].rowSpan = (tb.rows[startRow].cells[col].rowSpan | 0) + 1;
			            } else {
			                startRow = i + 1;
			            }
			        }
			    }
			        mc('contentTable',1,0,0); 
            
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
        function clearForm1(){
			$("#storeId").val('');
			$("#projectMode").val('');
			$("#formacceptArea").val('');
			$("#status").val('');
			$("#formorderNumber").val('');
			$("#formcustomerName").val('');
			$("#formitemManager").val('');
			$("#complaintSource").val('');
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

    	function findEngineListByStoreIdAndProjectMode(){
		var html3 = '<option value=""></option>';
		var storeId = $("#storeId").val();
		var projectModeValue = $("#projectMode").val();
		if (storeId=="" ||undefined==storeId ) {
			return false;
		}
		//根据门店个   动态加载工程区域     + "&projectModeValue=" + projectModeValue ,工程模式 
		$.ajax({

					url : "${ctx}/order/order/findEngineDepartmentBystoreIdAndProjectMode?storeId="
							+ storeId ,
					type : "get",
					success : function(data) {
						if (null!= data && data.length > 0) {

							for (var v = 0; v < data.length; v++) {
								if('${problemItemReport.acceptArea}' == data[v].engineDepartId){
									$("#s2id_engineDepartSelect .select2-chosen").html(data[v].engineDepartName);
									html3 = html3 + "<option value='"+data[v].engineDepartId+"' selected='selected'>"+data[v].engineDepartName+"</option>";
								}else{
									html3 = html3 + "<option value='"+data[v].engineDepartId+"'>"+data[v].engineDepartName+"</option>";
								}
							}
							
							$("#engineDepartSelect").html(html3);
						} else {
							$("#engineDepartSelect").html(html3);
						}

					}

				});		
		}
    	
    	function excelExportItem(){
    		$("#searchForm").attr("action","${ctx}/ordercomplan/problemItemReport/excelExportItem")
    		$("#searchForm").submit();
    	}
    	function submitItem(){
    		$("#searchForm").attr("action","${ctx}/ordercomplan/problemItemReport/queryItemReport")
    		$("#searchForm").submit();
    	}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/ordercomplan/problemItemReport/queryItemReport">投诉事项报表列表</a></li>
		<shiro:hasPermission name="ordercomplan:bizOrderComplaint:edit">
		<li></li>
		</shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="problemItemReport" action="${ctx}/ordercomplan/problemItemReport/queryItemReport" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>门店：</label>
					<form:select  id="storeId" path="storeId" class="input-medium needClear" onchange="findEngineListByStoreIdAndProjectMode()">
						<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
			</li>
		
<!-- 			<li><label>工程模式：</label> -->
<%-- 			<form:select id="projectMode"   path="projectMode" class="input-medium needClear" onchange="findEngineListByStoreIdAndProjectMode()"> --%>
<%-- 					<form:option value="" label=""/> --%>
<%-- 					<form:options items="${fns:getDictList('project_mode')}" itemLabel="label" itemValue="value" htmlEscape="false"/> --%>
<%-- 			</form:select> --%>
<!-- 			</li> -->
			
				<li><label>区域：</label>
				<form:select path="acceptArea" class="input-medium needClear" id="engineDepartSelect">
<%-- 					<form:options items="${fns:getEngineListWithUserConditions()}"   itemLabel="label" itemValue="value" htmlEscape="false"/> --%>
				</form:select>
			</li>
			
			<li><label>投诉事项日期：</label>
				<input name="itemStartDate" id="beginContractStartDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${problemItemReport.itemStartDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'endContractStartDate\')}',isShowClear:true});"/> 至  
				<input name=itemEndDate id="endContractStartDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${problemItemReport.itemEndDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'beginContractStartDate\')}',isShowClear:true});"/>
			</li>
		
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="button"  onclick="submitItem()" value="查询"/></li>
	<li class="btns"><input class="btn btn-primary clearBtn" type="button" value="清空" onclick="clearCondition()"/></li>
	<li class="btns"><input class="btn btn-primary clearBtn" type="button" value="导出" onclick="excelExportItem()"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
		

			<tr>
				<th>区域</th>
				<th>工种</th>
				<th>投诉事项</th>
				<th>投诉数量</th>
				<th>小计</th>
				<th>投诉事项占比</th>
				<th>投诉工种占比</th>
				<th>分区总计</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${itemList}" var="problemItemReport">
			<tr>
<%-- 				<td>${fns:getStoreLabel(problemItemReport.storeId, '')}</td> --%>
				<td>
					${problemItemReport.acceptArea}
				</td>
				<td>
					${problemItemReport.workType}
				</td>
				<td>
					${problemItemReport.itemName}
				</td>
				<td>
					${problemItemReport.itemCount}
				</td>
			
				<td>
					${problemItemReport.workTypeSubtotal}
				<input name ="itemCount" type="hidden" value="${problemItemReport.workTypeSuflag}"/>
				</td>
				<td>
					${problemItemReport.itemProportion}%
				</td>
				<td>
					${problemItemReport.workTypePro}%
				<input name ="itemCount" type="hidden" value="${problemItemReport.workTypeProflag}"/>
				</td>
				<td>
					${problemItemReport.acceptAreaPro}
				<input name ="itemCount" type="hidden" value="${problemItemReport.accAreaProflag}"/>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>