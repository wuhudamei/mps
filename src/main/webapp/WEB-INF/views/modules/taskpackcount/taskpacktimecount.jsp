<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<meta name="decorator" content="default" />
<title>任务包整体进度</title>
<script type="text/javascript">
	$(document).ready(function(){
		 //区域请选择
		 $("#area option").each(function(){ //遍历全部option
		       var txt = $(this).text(); //获取option的内容
		       var i =  $(this).attr("selected");
		       
		       if(txt == ''){
			        $(this).text("请选择...") 
			        } 
		       if(i == "selected"){
		    	   if(txt == ''){
		    		   $("#s2id_area").find(".select2-chosen").text("请选择...");
		    	   }else{
		    		   $("#s2id_area").find(".select2-chosen").text(txt);
		    	   } 
			        
		       }
		    });
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
	}
	function tip(){
		var start = $("#planStartdate").val();
		var end = $("#planEnddate").val();
		if(start !='' && end !=''){
			$("#searchForm").submit();
		}else{
			alertx("请选择时间范围！")
		}
		
	}
	function findEngineList(){
		
		
		var html3 = '<option value=""></option>';
		var storeId = $("#storeId").val();
		if (storeId=="" ||undefined==storeId ) {
			return;
		}
		//根据门店    动态加载工程区域
		$.ajax({

					url : "${ctx}/order/order/findEngineDepartmentBystoreIdAndProjectMode?storeId="
							+ storeId,
					type : "get",
					success : function(data) {
						if (null!= data && data.length > 0) {

							for (var v = 0; v < data.length; v++) {
								html3 +='<option value="'+data[v].engineDepartId+'">'+data[v].engineDepartName+'</option>' 
							}
							
							$("#area").html(html3);
						} else {
							$("#area").html(html3);
						}

					}

				});		
		}
</script>

</head>
<body>
	<form:form id="searchForm" modelAttribute="taskPackCount" action="${ctx}/taskpackcount/listtime" method="post" class="breadcrumb form-search">
	<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
	<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
	<li><label>门店：</label> 
		<form:select path="storeId" class="input-medium needClear" onchange="findEngineList()">
			<form:option value="" label="请选择..." />
			<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false" />
		</form:select>
	</li>
	<li><label>区域：</label>
		<form:select path="area" class="input-medium needClear">
				<form:options items="${fns:getEngineListWithUserConditionsForBiddenChange()}"   itemLabel="label" itemValue="value" htmlEscape="false"/>
		</form:select>
	</li>
	<li><label>订单编号：</label>
		<form:input path="orderNumber" htmlEscape="false" class="input-medium needClear"/>
	</li>
	<li><label>任务包编号：</label>
		<form:input path="orderTaskPackageCode" htmlEscape="false" class="input-medium needClear"/>
	</li>
	<li><label>项目经理：</label>
		<form:input path="itemManager" htmlEscape="false" class="input-medium needClear"/>
	</li>
	<li><label>客户名称：</label>
		<form:input path="customerName" htmlEscape="false" class="input-medium needClear"/>
	</li>
	<li><label>日期：</label>
		<input name="planStartdate" id = "planStartdate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate needClear"
							value="<fmt:formatDate value="${start}" pattern="yyyy-MM-dd"/>"
							onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/> - 
		<input name="planEnddate" id="planEnddate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate needClear"
							value="<fmt:formatDate value="${end}" pattern="yyyy-MM-dd"/>"
							onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'planStartdate\')}',isShowClear:false});"/>
	</li>
	<li><label>是否作废：</label>
			<form:select path="isScrap" class="input-medium needClear">
				<form:option value="0" label=""/>
				<form:options items="${fns:getDictList('dict_iscountsquare')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
			</form:select>
	</li>
		<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="button" onclick="tip()" value="查询"/></li>
		<li class="btns"><input class="btn btn-primary clearBtn" type="button" value="清空" onclick="clearCondition()"/></li>
	</form:form>
	<table id = "table" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>门店</th>
				<th>区域</th>
				<th>订单编号</th>
				<th>项目经理</th>
				<th>客户名称</th>
				<th>任务包编号</th>
				<th>任务包名称</th>
				<th>10-创建</th>
				<th>20-预算员审核通过</th>
				<th>50-已派单到工人</th>
				<th>55-工人拒绝任务包</th>
				<th>60-已确定工人组</th>
				<th>70-施工中（开工签到）</th>
				<th>80-工人申请完工</th>
				<th>是否作废</th>
				<th>作废日期</th>
			</tr>
		</thead>
	<tbody>
		<c:forEach items="${page.list}" var="data" varStatus="status">
			<tr>
				<td>
					${fns:getStoreLabel(data.storeId, '')}
				</td>
				<td>
					${ data.area }
				</td>
				<td>
					${ data.orderNumber }
				</td>
				<td>
					${ data.itemManager }
				</td>
				<td>
					${ data.customerName }
				</td>
				<td>
					${ data.orderTaskPackageCode }
				</td>
				<td>
					${ data.packageName }
				</td>
				<td>
				<fmt:formatDate value="${ data.taskPackStatus10 }" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${ data.taskPackStatus20 }" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${ data.taskPackStatus50 }" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${ data.taskPackStatus55 }" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${ data.taskPackStatus60 }" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${ data.taskPackStatus70 }" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${ data.taskPackStatus80 }" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<c:if test="${data.isScrap eq 1}">是</c:if>
					<c:if test="${data.isScrap ne 1}">否</c:if>
				</td>
				<td>
					<fmt:formatDate value="${ data.scrapDatetime }" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
	
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination" align="right">${page}</div>
</body>
</html>