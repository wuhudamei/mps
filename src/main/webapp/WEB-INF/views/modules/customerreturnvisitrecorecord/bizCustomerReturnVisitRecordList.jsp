<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>回访记录列表</title>
	<meta name="decorator" content="default"/>
	<style>
		.pad_btm40{padding-bottom:20px;}
		.alertMask{position: fixed;top: 0;bottom: 0;left: 0;right: 0;background: rgba(0,0,0,.5);}
		.maskWrapper{width: 30%;margin: 10% auto 0;border-radius: 4px;background: #fff;font-size: 16px;}
		.col_3{color: #333}
		.col_6{color: #666;}
		.col_f{color: #fff;}
		.col_fdfcfa{color: #fdfcfa;}
		.col_0780ec{color: #0780ec;}
		.font28{font-size: 14px;}
		.font33{font-size: 16.5px;}
		.maskTit{height: 50px;line-height: 50px;text-align: center;}
		.maskContent{padding: 25px;border-top: 1px solid #ddd;border-bottom: 1px solid #ddd;line-height: 75px;}
		.maskBtns{width: 83%;margin: 0 auto;padding-bottom: 10px;padding-top: 10px;}
		/* .maskBtn{display: block;width: 60%;} */
		.maskBtn{background: #0780ec;border-radius: 2px;display: block;width: 47.6%;
    		text-align: center;line-height: 38px;font-size:16.5px;margin: 0 auto;}
    	.maskBtnOne{background: #0780ec;border-radius: 2px;display: block;width: 47.6%;
    		text-align: center;line-height: 38px;font-size:16.5px;margin: 0 auto;}
    	.twoBtns .maskBtn:first-child{background: #0780ec;border-radius: 2px;float: left;}
		.twoBtns .maskBtn:last-child{border: 1px solid #0780ec;box-sizing: border-box;border-radius: 2px;float: right;background: #fff;}
		.undis{display:none;}
	</style>
	<script type="text/javascript">
		$(document).ready(function() {
			checkNodeList(0);
			
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
		function checkNodeList(obj){
			var projectMode = $("#projectMode").val();
			var storeId=$("#storeId").val()
			if (storeId=="" ||projectMode=="" ||undefined==storeId ||undefined==projectMode) {
				return;
			}
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
			    },
			    complete:function(){
			     	var html3 = '<option value=""></option>';
					//根据门店个,工程模式    动态加载工程区域
					$.ajax({
						url : "${ctx}/order/order/findEngineDepartmentBystoreIdAndProjectMode?storeId="
								+ storeId + "&projectModeValue=" + projectMode,
						type : "get",
						success : function(data) {
							$("#area").parent().find(".select2-chosen").text('');
							if( data != null && data.length > 0 ){
								for (var v = 0; v < data.length; v++) {
									if( obj == 0 ){
										if('${bizCustomerReturnVisitRecord.area}' == data[v].engineDepartId){
											$("#s2id_area .select2-chosen").html(data[v].engineDepartName);
											html3 = html3 + "<option value='"+data[v].engineDepartId+"' selected='selected'>"+data[v].engineDepartName+"</option>";
										}else{
											html3 = html3 + "<option value='"+data[v].engineDepartId+"'>"+data[v].engineDepartName+"</option>";
										}	
									}else{
										html3 = html3 + "<option value='"+data[v].engineDepartId+"'>"+data[v].engineDepartName+"</option>";
									}
								}
								$("#area").html(html3);
							}
						}

					});	
			    }
			}); 
		
		} 
		function exportExl(a){
			if(a=="query"){
			$("#searchForm").attr("action","${ctx}/customerreturnvisitrecorecord/bizCustomerReturnVisitRecord/list");
			}
			if(a=="export"){
			 	if($("#returnVisitNode").val()==null ||$("#returnVisitNode").val()==''){
			 		$("#warnModel").removeClass("undis");
			 		return ;
			 	}else{
				$("#searchForm").attr("action","${ctx}/customerreturnvisitrecorecord/bizCustomerReturnVisitRecord/export");
			 	}
			}
			$("#searchForm").submit();
		}
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
		//有提示框的 确定
		function sureTable(){
			$("#warnModel").addClass("undis");
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/customerreturnvisitrecorecord/bizCustomerReturnVisitRecord/list">回访记录列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="bizCustomerReturnVisitRecord" action="${ctx}/customerreturnvisitrecorecord/bizCustomerReturnVisitRecord/list" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>门店：</label>
				<form:select path="storeId" class="input-medium needClear" onchange="checkNodeList(1)">
						<form:option value="" label=""/>
						<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
			</li>
			<li><label>工程模式：</label>
					<form:select path="projectMode" class="input-medium needClear" onchange="checkNodeList(1)">
						<form:option value="" label=""/>
						<form:options items="${fns:getDictList('project_mode')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
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
			
			<li><label>回访员：</label>
				<form:input path="customServiceName" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>回访时间：</label>
				<input name="visitDateBegin" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${bizCustomerReturnVisitRecord.visitDateBegin}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
				<input name="visitDateEnd" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${bizCustomerReturnVisitRecord.visitDateEnd}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</li>
			<li><label>是否有效：</label>
				<form:select path="isValid" class="input-medium needClear"  htmlEscape="false" >
						<form:option value="1" selected="selected">是</form:option>
						<form:option value="2">否</form:option>
				</form:select>
			</li>		
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="button" onclick="exportExl('query')" value="查询"/></li>
			<li class="btns"><input class="btn btn-primary clearBtn" type="button" value="清空" onclick="clearCondition()"/></li>
			<li class="btns"><input id="btnExport" class="btn btn-primary" type="button" onclick="exportExl('export')" value="导出" /></li> 
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
				<th>订单编号</th>
				<th>工程地址</th>
				<th>客户姓名</th>
				<th>客户电话</th>
				<!-- <th>设计师</th> -->
				<th>项目经理</th>
				<th>质检</th>
				<th>回访节点</th>
				<th>回访员</th>
				<th>回访日期</th>
				<th>是否有效</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="bizCustomerReturnVisitRecord">
			<tr>
				<td>
					${bizCustomerReturnVisitRecord.storeName}
				</td>
				<td>
					${bizCustomerReturnVisitRecord.projectModeName}
				</td>
				<td>
					${bizCustomerReturnVisitRecord.areaName}
				</td>
				<td>
					${bizCustomerReturnVisitRecord.orderNumber}
				</td>
				<td>
					${bizCustomerReturnVisitRecord.customerAddress}
				</td>
				<td>
					${bizCustomerReturnVisitRecord.customerName}
				</td>
				<td>
					${bizCustomerReturnVisitRecord.customerPhone}
				</td>
				<%-- <td>
					${bizCustomerReturnVisitRecord.designerName}
				</td> --%>
				<td>
					${bizCustomerReturnVisitRecord.itemManager}
					<%-- <fmt:formatDate value="${bizCustomerReturnVisitRecord.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/> --%>
				</td>
				<td>
					${bizCustomerReturnVisitRecord.orderInspector}
				</td>
				<td>
					${bizCustomerReturnVisitRecord.returnVisitNodeName}
				</td>
				<td>
					${bizCustomerReturnVisitRecord.customServiceName}
				</td>
				<td>
					<fmt:formatDate value="${bizCustomerReturnVisitRecord.returnVisitTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					 <c:if test="${bizCustomerReturnVisitRecord.invalidNum>=3}">否 </c:if> 
					 <c:if test="${bizCustomerReturnVisitRecord.invalidNum<3 || bizCustomerReturnVisitRecord.invalidNum==null}">是 </c:if> 
				</td>
				<td>
    				<a href="${ctx}/customerreturnvisitrecorecord/bizCustomerReturnVisitRecord/recordDetail?id=${bizCustomerReturnVisitRecord.id}">详情</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
		
		<div class="alertMask undis" id="warnModel">
			<div class="maskWrapper">
				<p class="col_3 maskTit">提示</p>
				<div class="font28 col_6 maskContent">必须选取回访节点</div>
				<div class="maskBtns clearfix twoBtns">
					<a class="maskBtnOne font33 col_fdfcfa"  onclick="sureTable()" href="javascript:void(0);">确定</a>
				</div>
			</div>
		</div>
</body>
</html>