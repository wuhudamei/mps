<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>无效回访记录</title>
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
				   		"storeId":storeId
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

		} 
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
		
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/customerreturnvisitrecorecord/bizCustomerReturnVisitRecord/invalidList">无效回访记录</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="bizCustomerReturnVisitRecord" action="${ctx}/customerreturnvisitrecorecord/bizCustomerReturnVisitRecord/invalidList" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>门店：</label>
				<form:select path="storeId" class="input-medium needClear" onclick="checkNodeList()">
						<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
			</li>
			<li><label>工程模式：</label>
					<form:select path="projectMode" class="input-medium needClear" onclick="checkNodeList()">
						<form:option value="" label=""/>
						<form:options items="${fns:getDictList('project_mode')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
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
			<li><label>客户姓名：</label>
				<form:input path="customerName" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>回访人员：</label>
				<form:input path="customServiceName" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>回访时间：</label>
				<input name="invalidDateBegin" id="invalidDateBegin" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${bizCustomerReturnVisitRecord.invalidDateBegin}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
				<input name="invalidDateEnd" id="invalidDateEnd" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${bizCustomerReturnVisitRecord.invalidDateEnd}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
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
				<th>序号</th>
				<th>门店</th>
				<th>工程模式</th>
				<th>订单编号</th>
				<th>客户信息</th>
				<th>无效原因</th>
				<th>回访人员</th>
				<th>回访节点</th>
				<th>回访时间</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="map" varStatus="status">
			<tr>
				<td>
					${status.index+1}
				</td>			
				<td>
					${fns:getStoreLabel(map.storeId, '')}
				</td>
				<td>
					${fns:getDictLabel(map.projectMode,'project_mode', '')}
				</td>
				<td>
					${map.orderNumber}
				</td>
				<td>
					${map.customerAddress}-${map.customerName}
				</td>
				<td>
					${map.invalidReason}
				</td>
				<td>
					${map.customServiceName}
				</td>
				<td>
					${map.returnVisitNodeName}
				</td>				
				<td>
					<fmt:formatDate value="${map.invalid}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>