<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>待回访订单列表</title>
	<meta name="decorator" content="default"/>
	<style type="text/css">
		.dataTrBg{
			background-color:#3daae9;
			color:#fff;
			
		}
	</style>
	<script type="text/javascript">
		$(document).ready(function() {
			checkNodeList();
			
			$("tbody>tr").click(function(){
			    $(this).find('td').css('background',"orange").parents('tr').siblings().find('td').css('background',"#f5f5f5");
			});
		});
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
		//节点下拉表 添加用
		function checkNodeList(){
			var projectMode = $("#projectMode").val();
			var storeId=$("#storeId").val()
			if(projectMode!=undefined && projectMode!='' && storeId!='' && storeId!=undefined){
				 $.ajax({
					url: "${ctx}/customerreturnvisit/bizCustomerReturnVisit/queryReturnVisitNodeByStoreId",    //请求的url地址
				    dataType: "json",   //返回格式为json
				    async: true, //请求是否异步，默认为异步，这也是ajax重要特性
				    data: { 
				   		"storeId":storeId,
						"projectMode":projectMode
				   	},    //参数值
				    type: "GET",   //请求方式
				    success: function(req) {
				    	var htmls="<option value=''></option>";
				    	$("#returnVisitNode").html('');
				    	//htmls+="";
				    	$("#returnVisitNode").parent().find(".select2-chosen").text('');
				    	for(var i=0;i<req.data.length;i++){
				    		if('${bizCustomerReturnVisitRecord.returnVisitNode}' == req.data[i].value + ''){
								$("#s2id_returnVisitNode .select2-chosen").html(req.data[i].label);
								htmls = htmls + "<option value="+req.data[i].value+" selected='selected'>"+req.data[i].label+"</option>";
				    		}
				    		else{
								htmls = htmls + "<option value="+req.data[i].value+">"+req.data[i].label+"</option>";
				    		}
				    	}
				    	$("#returnVisitNode").append(htmls);
			    	}
				}); 
			}
			var html3 = '<option value=""></option>';
			if (storeId=="" ||projectMode=="" ||undefined==storeId ||undefined==projectMode) {
				return;
			}
			//根据门店个,工程模式    动态加载工程区域
			$.ajax({
					url : "${ctx}/order/order/findEngineDepartmentBystoreIdAndProjectMode?storeId="
							+ storeId + "&projectModeValue=" + projectMode,
					type : "get",
					success : function(data) {
						if (null!= data && data.length > 0) {
							for (var v = 0; v < data.length; v++) {
								if('${bizCustomerReturnVisitRecord.area}' == data[v].engineDepartId){
									$("#s2id_area .select2-chosen").html(data[v].engineDepartName);
									html3 = html3 + "<option value='"+data[v].engineDepartId+"' selected='selected'>"+data[v].engineDepartName+"</option>";
								}else{
									html3 = html3 + "<option value='"+data[v].engineDepartId+"'>"+data[v].engineDepartName+"</option>";
								}
							}
							$("#area").html(html3);
						} else {
							$("#area").html(html3);
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
		
		function returnVisitDeal(orderId,returnVisitNode){
			var url = "${ctx}/customerreturnvisitrecorecord/bizCustomerReturnVisitRecord/form?";
			url = url +"orderId="+ orderId + "&dealReturnVisitNode="+ returnVisitNode;
			url = url +"&storeId="+ $("#storeId").val();
			url = url +"&projectMode="+ $("#projectMode").val();
            url = url +"&pageNo="+ $("#pageNo").val();
            url = url +"&pageSize="+ $("#pageSize").val();
			if( $("#area").val() != null && $("#area").val() != ''  ){
				url = url +"&area="+ $("#area").val();
			}
			url = url +"&orderNumber="+ $("#orderNumber").val();
			url = url +"&customerPhone="+ $("#customerPhone").val();
			url = url +"&customerName="+ $("#customerName").val();
			url = url +"&designerName="+ $("#designerName").val();
			url = url +"&itemManager="+ $("#itemManager").val();
			url = url +"&orderInspector="+ $("#orderInspector").val();
			url = url +"&returnVisitNode="+ $("#returnVisitNode").val();
			url = url +"&nodeCheckDateBegin="+ $("#nodeCheckDateBegin").val();
			url = url +"&nodeCheckDateEnd="+ $("#nodeCheckDateEnd").val();
			window.location.href = url;
			//href="${ctx}/customerreturnvisitrecorecord/bizCustomerReturnVisitRecord/form?orderId=${map.orderId}&returnVisitNode=${map.returnVisitNode}"
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/customerreturnvisitrecorecord/bizCustomerReturnVisitRecord/checkingList">待回访问卷列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="bizCustomerReturnVisitRecord" action="${ctx}/customerreturnvisitrecorecord/bizCustomerReturnVisitRecord/checkingList" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>门店：</label>
				<form:select path="storeId" class="input-medium needClear" onclick="checkNodeList()">
						<form:option value="" label=""/>
						<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
			</li>
			<li><label>工程模式：</label>
					<form:select path="projectMode" class="input-medium needClear" onclick="checkNodeList()">
						<form:option value="" label=""/>
						<form:options items="${listStatus}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
			</li>
			<li><label>区域：</label>
				<form:select path="area" class="input-medium needClear" id="area">
				</form:select>
			</li>
			<li><label>订单编号：</label>
				<form:input path="orderNumber" htmlEscape="false" maxlength="12" class="input-medium"/>
			</li>
			<li><label>回访节点：</label>
				<form:select path="returnVisitNode" class="input-medium needClear">
					<form:option value="" label=""/>
				</form:select>
			</li>
			<li><label>客户手机：</label>
				<form:input path="customerPhone" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>客户姓名：</label>
				<form:input path="customerName" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>设计师姓名：</label>
				<form:input path="designerName" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>项目经理：</label>
				<form:input path="itemManager" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>质检：</label>
				<form:input path="orderInspector" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>节点验收时间：</label>
				<input name="nodeCheckDateBegin" id="nodeCheckDateBegin" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${bizCustomerReturnVisitRecord.nodeCheckDateBegin}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'nodeCheckDateEnd\')}',isShowClear:false});"/>
				<input name="nodeCheckDateEnd" id="nodeCheckDateEnd" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${bizCustomerReturnVisitRecord.nodeCheckDateEnd}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'nodeCheckDateBegin\')}',isShowClear:false});"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="btns"><input class="btn btn-primary clearBtn" type="button" value="清空" onclick="clearCondition()"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>门店</th>
				<th>工程模式</th>
				<th>区域</th>
				<!-- <th>订单编号</th> -->
				<th>工程地址</th>
				<th>客户姓名</th>
				<th>客户电话</th>
				<!-- <th>设计师</th> -->
				<th>项目经理</th>
				<th>质检员</th>
				<th>节点验收时间</th>
				<th>回访节点</th>
				<th>回访次数</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="map">
			<tr>
				<td>
					${fns:getStoreLabel(map.storeId, '')}
				</td>
				<td>
					${fns:getDictLabel(map.projectMode,'project_mode', '')}
				</td>
				<td>
					${map.areaName}
				</td>
				<%-- <td>
					${map.orderNumber}
				</td> --%>
				<td>
					${map.customerAddress}
				</td>
				<td>
					${map.customerName}
				</td>
				<td>
					${map.customerPhone}
				</td>
				<%-- <td>
					${map.designerName}
				</td> --%>
				<td>
					${map.itemManager}
				</td>
				<td>
					${map.orderInspector}
				</td>
				<td>
					<fmt:formatDate value="${map.nodeCheckDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${map.returnVisitNodeName}
				</td>
				<td>
					${map.invalidNum}
				</td>
				<td>
					<c:choose>
						<c:when test="${map.invalidNum == 0}">
							<a href="#" onClick="returnVisitDeal(${map.orderId},${map.returnVisitNode})">回访</a>
						</c:when>
						<c:otherwise>
							<a href="#" onClick="returnVisitDeal(${map.orderId},${map.returnVisitNode})">再联系</a>
						</c:otherwise>
					</c:choose>
    				
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>