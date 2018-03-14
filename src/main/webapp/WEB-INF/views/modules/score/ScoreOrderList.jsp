<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>订单评分统计</title>
	<style>
		.undis{display:none;}
		.g-mask{width:100%;height:100%;position:relative;z-index:99;font-size:0;}
		#g-done_ask{width:600px;height:360px;position:fixed;left:50%;top:50%;transform:translate(-50%,-50%);border:1px solid #333;border-radius:4px;}
		.refuse{height:40px;line-height:40px;font-size:20px;background:#54b4eb;margin:0;}
		.content{color:#000;width:600px;height:100px;resize: none;margin:0;}
		.second{width:600px;}
		.second a{display:block;width:300px;height:40px;line-height:40px;font-size:24px;text-decoration:none;float:left;text-align:center;background:#54b4eb;color:#fff;}
		.second a:first-child{box-sizing:border-box;border-right:1px solid #ccc;}
	</style>
	
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
	$(document).ready(function() {
		scoreOrderType();
	});
	function page(n,s){
		$("#pageNo").val(n);
		$("#pageSize").val(s);
		$("#searchForm").submit();
    	return false;
    }
	function submitForm(type){
			if (type == 'query'){
				$("#searchForm").attr("action","${ctx}/score/order/ScoreOrderlist2");
			}else {
				$("#searchForm").attr("action","${ctx}/score/order/export");
			}
			$("#searchForm").submit();	
			
	}
	function scoreOrderType(){
		var name=$("#storeName").val();
		$.ajax({
		    url: "${ctx}/score/order/getScoreContentByStoreId",    //请求的url地址
		    dataType: "json",   //返回格式为json
		    async: true, //请求是否异步，默认为异步，这也是ajax重要特性
		    data: { 
		   	storeId:name
		    	},    //参数值
		    type: "POST",   //请求方式
		    success: function(req) {
		    	if(req != undefined && req != null && req != ""){
					var jsonObj = eval(req);
					var htmls = "<option></option>";
					for (var i = 0; i < jsonObj.length; i++) {
						if('${scoreOrderQuery.typeCode}' == jsonObj[i].typeCode){
							$("#s2id_position .select2-chosen").html(jsonObj[i].typeName);
							htmls = htmls + "<option value='"+jsonObj[i].typeCode+"' selected='selected'>"+jsonObj[i].typeName+"</option>";
						
						}else{
							htmls = htmls + "<option value='"+jsonObj[i].typeCode+"'>"+jsonObj[i].typeName+"</option>";
						}
					} 
					$('#position').html(htmls);
				}else{
					$('#position').html("");
				}
		    },
		    error: function() {
		    	 
		    }
		});	
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
		 
		 var inputObjs=jQuery("#searchForm input[type='number']"); 
		 for(var i=0;i<inputObjs.length;i++){ 
		 var inputObj = inputObjs[i]; 
		 inputObj.value=""; 
		 }
	}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"></li>
	</ul>
		<form:form id="searchForm" modelAttribute="scoreOrderQuery" action="${ctx}/score/order/ScoreOrderlist2" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>门店：</label>
				<form:select path="name" id="storeName" class="input-medium needClear" onchange="scoreOrderType()" >
					<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li>
			<label>评分类型：</label>
				<form:select path="typeCode" id="position" class="input-medium needClear">
					<form:option value="" label=""/>
					<%-- <form:options items="${fns:getContentType()}" itemLabel="label" itemValue="value" htmlEscape="false"/> --%>
				</form:select>
			</li>
			<li><label>评分内容：</label>
				<form:select path="scoreContent" class="input-medium needClear">
					<form:option value="" label=""/>
					<form:option value="y" label="有"/>
					<form:option value="n" label="无"/>
				</form:select>
			</li>
			<li><label>分值区间：</label>
			<input id="numberBegin" value="${ scoreOrderQuery.scoreBegin}" name="scoreBegin" type="number"  min=0   max=10/>~<input value="${scoreOrderQuery.scoreEnd}" name="scoreEnd" id="numberEnd" type="number"  min=0 max=10>
			</li>
			<li><label>评价起止日期 ：</label>
				<input name="scoreDateBegin" type="text" id="beginCreateDate" readonly="readonly" maxlength="20" class="input-medium Wdate needClear"
					value="<fmt:formatDate value="${scoreOrderQuery.scoreDateBegin}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'endCreateDate\')}',isShowClear:false});"/> - 
				<input name="scoreDateEnd" type="text" id="endCreateDate" readonly="readonly" maxlength="20" class="input-medium Wdate needClear"
					value="<fmt:formatDate value="${scoreOrderQuery.scoreDateEnd}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'beginCreateDate\')}',isShowClear:false});"/>
			</li>
			<li>
				<input type="text" path="scoreQuery" name="scoreQuery" value="${scoreOrderQuery.scoreQuery}" placeholder="评价内容/客户姓名/订单地址/客户电话/订单号"  >
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="button"  value="查询" onclick ="submitForm('query')" /></li>
			 <li class="btns"><input id="btnExport" class="btn btn-primary" type="button" value="导出" onclick ="submitForm('export')"/></li> 
			<li class="btns"><input class="btn btn-primary clearBtn" type="button" value="清空" onclick="clearCondition()"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>门店</th>
				<th>评分类型</th>
				<th>分值</th>
				<th>订单编号</th>
				<th>客户姓名</th>
				<th>客户电话</th>
				<th>订单地址</th>
				<th>干系</th>
				<th>评价内容</th>
				<th>评价时间</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="scoreOrder">
			<tr>
				<td title="${scoreOrder.name}">
					${scoreOrder.name}
				</td>
				<td title="${scoreOrder.typeName}">
					${scoreOrder.typeName}
				</td>
				<td title="${scoreOrder.scoreValue}">
					${scoreOrder.scoreValue}
				</td>
				<td title="${scoreOrder.orderNumber}">
					${scoreOrder.orderNumber}
				</td>
				<td title="${scoreOrder.customerName}">
					${scoreOrder.customerName}
				</td>
				<td title="${scoreOrder.customerPhone}">
					${scoreOrder.customerPhone}
				</td>
				<td title="${scoreOrder.province}${scoreOrder.city }${scoreOrder.county }${scoreOrder.detailAddress} ">
					<%-- ${scoreOrder.province}
					${scoreOrder.city }
					${scoreOrder.county } --%>
					<div style="max-height:50px;overflow:hidden">${scoreOrder.detailAddress} </div>
					
				</td>
				<td title="${scoreOrder.stakeholder} ">
					 
					<div style="max-height:50px;overflow:hidden">${scoreOrder.stakeholder} </div>
				</td>
				<td title="${scoreOrder.scoreContent}">
					<div style="max-height:20px;overflow:hidden;max-width:100px">${scoreOrder.scoreContent}</div>
					
				</td>
				<td>
					<fmt:formatDate value="${scoreOrder.scoreTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td></td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="g-mask undis" id="approve">
	</div>
	<div class="pagination">${page}</div>
	<script type="text/javascript">
		$(document).ready(function() {
		function submitForm(){
			var a = $("#storeId").val();
		}
		});
	</script>
	<script type="text/javascript">
	</script>
</body>
</html>