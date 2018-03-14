<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>安装项施工单问题上报</title>
	<meta name="decorator" content="default"/>
	
	
	<script type="text/javascript">
		$(document).ready(function() {
			findProjectInstallItemList();
			findEngineListAndProblemTypeList();
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
		
		
		
		//根据供应商加载安装项
		function findProjectInstallItemList(){
			
			var supplierId = $("#supplierId").val();
			if (supplierId=="" ||undefined==supplierId || null==supplierId) {
				return;
			}
			$.ajax({
				url:"${ctx}/bizsupplierinstallbill/bizSupplierInstallBill/findProjectInstallItemList",					
				type: "post",
				data:{
	            		supplierId:supplierId
	            	},
				success: function(data){
					var html='<option value=""></option>';
					if(null!=data && data.length>0){
						for (var v = 0; v < data.length; v++) {
							if('${bizOrderInstallItemProblemVo.projectInstallItemId}' == data[v].id){
								$("#s2id_projectInstallItemId .select2-chosen").html(data[v].installItemName);
								html = html + "<option value='"+data[v].id+"' selected='selected'>"+data[v].installItemName+"</option>";
							}else{
								html = html + "<option value='"+data[v].id+"'>"+data[v].installItemName+"</option>";
							}
						}
						
						$("#projectInstallItemId").html(html);
					} else {
						$("#projectInstallItemId").html(html);
					}
				}
			})
			
			
		}
		
		
		
		
		
		//根据门店和工程模式  动态加载工程区域和问题分类
		function findEngineListAndProblemTypeList(){
			
			var storeId = $("#storeId").val();
			var projectModeValue = $("#projectMode").val();
			
			if (storeId=="" ||projectModeValue=="" ||undefined==storeId ||undefined==projectModeValue) {
				return;
			}
			
			
			//根据门店个,工程模式    动态加载工程区域
			var html3 = '<option value=""></option>';
			$.ajax({

					url : "${ctx}/order/order/findEngineDepartmentBystoreIdAndProjectMode?storeId="
							+ storeId + "&projectModeValue=" + projectModeValue,
					type : "get",
					success : function(data) {
						if (null!= data && data.length > 0) {

							for (var v = 0; v < data.length; v++) {
								
								if('${bizOrderInstallItemProblemVo.engineDepartId}' == data[v].engineDepartId){
									$("#s2id_engineDepartId .select2-chosen").html(data[v].engineDepartName);
									html3 = html3 + "<option value='"+data[v].engineDepartId+"' selected='selected'>"+data[v].engineDepartName+"</option>";
								}else{
									html3 = html3 + "<option value='"+data[v].engineDepartId+"'>"+data[v].engineDepartName+"</option>";
								}
							
							}
							
							$("#engineDepartId").html(html3);
						} else {
							$("#engineDepartId").html(html3);
						}

					}

				});	
			
			
			//根据门店和工程模式去加载问题分类
			$("#problemTypeId").html('');
			var businessType = "5";
			var html2 = "<option value=''></option>";
			$.ajax({
		        url : '${ctx}/bizorderinstallitemproblem/bizOrderInstallItemProblem/queryInstallItemProblemTypeList',
				type : 'POST',
				dataType : 'json',
		        data : {
		            storeId : storeId,
		            projectMode : projectModeValue,
		            businessType : businessType
		        },
		        success : function(data){
		        	
		        	if (null!= data && data.length > 0) {

						for (var v = 0; v < data.length; v++) {
							
							if('${bizOrderInstallItemProblemVo.problemTypeId}' == data[v].value){
								$("#s2id_problemTypeId .select2-chosen").html(data[v].label);
								html2 = html2 + "<option value='"+data[v].value+"' selected='selected'>"+data[v].label+"</option>";
							}else{
								html2 = html2 + "<option value='"+data[v].value+"'>"+data[v].label+"</option>";
							}
						
						}
						
						$("#problemTypeId").html(html2);
					} else {
						$("#problemTypeId").html(html2);
					}
		        }
		        
			})
			
		}
		
		//导出
		function exportExcel(){
			$("#searchForm").attr("action", "${ctx}/installConstructBillProblem/installConstructBillProblem/exportExcel");
			$("#searchForm").submit();
			$("#searchForm").attr("action", "${ctx}/installConstructBillProblem/installConstructBillProblem/list");
		}
		//查询
		function searchButton(){
			$("#searchForm").attr("action", "${ctx}/installConstructBillProblem/installConstructBillProblem/list");
            $("#searchForm").submit();
		}
	</script>
	<style>
		a{color:#2fa4e7;}
		.undis{display:none;}
		body {
		    background-color: #fff;
		    font-size: 16px;
		}
		
		body {
		    width: 100%;
		    height: 100%;
		    position: relative
		}
		
    	
    	.alertMask{position: fixed;top: 0;bottom: 0;left: 0;right: 0;background: rgba(0,0,0,.5);}
		.maskWrapper{width: 380px;border-radius: 4px;background: #fff;font-size: 16px;
		    position: fixed;top: 50%;left: 50%;transform: translate(-50%,-50%);}
		.col_3{color: #333}
		.col_6{color: #666;}
		.col_f{color: #fff;}
		.col_fdfcfa{color: #fdfcfa;}
		.col_0780ec{color: #0780ec;}
		.font28{font-size: 14px;}
		.font33{font-size: 16.5px;}
		.maskTit{height: 50px;line-height: 50px;text-align: center;}
		.maskContent{padding: 25px;border-top: 1px solid #ddd;border-bottom: 1px solid #ddd;line-height: 1.5em;}
		.maskBtns{width: 83%;margin: 0 auto;padding-bottom: 10px;padding-top: 10px;}
		.maskBtn{display: block;width: 60%;text-align: center;line-height: 38px;}
		.maskBtn{background: #0780ec;border-radius: 4px;display: block;width: 47.6%;
    		text-align: center;line-height: 38px;font-size:16px;margin: 0 auto;}
    	.twoBtns .maskBtn:first-child{background: #0780ec;border-radius: .1rem;float: left;}
		.twoBtns .maskBtn:last-child{border: 1px solid #0780ec;box-sizing: border-box;border-radius: .1rem;float: right;background: #fff;}		
	</style>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/installConstructBillProblem/installConstructBillProblem/preList">安装工主材问题上报列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="bizOrderInstallItemProblemVo" action="${ctx}/installConstructBillProblem/installConstructBillProblem/list" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
		
			<li><label>门店：</label> 
				<form:select path="storeId" class="input-medium needClear" onchange="findEngineListAndProblemTypeList()">
					<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>工程模式：</label>
				<c:if test="${empty gongcheng}">
					<form:select path="projectMode" class="input-medium" disabled="true" onchange="findEngineListAndProblemTypeList()">
						<form:option value="" label=""/>
						<form:options items="${fns:getDictList('project_mode')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
				<c:if test="${!empty gongcheng}">
					<form:select path="projectMode" class="input-medium needClear" onchange="findEngineListAndProblemTypeList()">
						<form:option value="" label=""/>
						<form:options items="${fns:getDictList('project_mode')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
					
			</li>
			<li><label>区域：</label>
				<form:select path="engineDepartId" class="input-medium needClear">
				</form:select>
			</li>
			
			<li><label>订单编号：</label>
				<form:input path="orderNumber" htmlEscape="false" maxlength="16" class="input-medium needClear"/>
			</li>
			<li><label>客户姓名：</label>
				<form:input path="customerName" htmlEscape="false" maxlength="10" class="input-medium needClear"/>
			</li>
			<li><label>项目经理：</label>
				<form:input path="itemManager" htmlEscape="false" maxlength="10" class="input-medium needClear"/>
			</li>
			<li><label>供应商：</label>
				<form:select path="supplierId" class="input-medium needClear" onclick="findProjectInstallItemList()">
					<form:option value="" label=""/>
					<c:forEach items="${supplierList }" var="supplier">
						<form:option value="${supplier.supplierId }" label="${supplier.supplierName }"/>
					</c:forEach>
				</form:select>
			</li>
			<li><label>安装项：</label>
				<form:select path="projectInstallItemId" class="input-medium needClear">
				</form:select>
			</li>
			<li><label>问题分类:</label>
				<form:select path="problemTypeId" class="input-medium needClear" >
				</form:select> 
			</li>
			<li><label style="width:120px;">安装工上报时间：</label>
				<input name="beginCreateDate" id="beginCreateDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate needClear"
					value="<fmt:formatDate value="${bizOrderInstallItemProblemVo.beginCreateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'endCreateDate\')}',isShowClear:true});"/> 至 
				<input name="endCreateDate" id="endCreateDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate needClear"
					value="<fmt:formatDate value="${bizOrderInstallItemProblemVo.endCreateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'beginCreateDate\')}',isShowClear:true});"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="btns"><input class="btn btn-primary clearBtn" type="button" value="清空"/></li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="button" value="导出" onclick="exportExcel()"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>门店</th>
				<th>上报时间</th>
				<th>工程模式</th>
				<th>区域</th>
				<th>小区</th>
				<th>客户</th>
				<th>供应商</th>
				<th>安装项名称</th>
				<th>问题分类</th>
				<th>描述</th>
				<th>现场照片</th>
				<th>项目经理</th>
				<th>设计师</th>
				<th>质检员</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="problem">
			<tr>
				<td>
					${problem.storeName}
				</td>
				<td>
					<fmt:formatDate value="${problem.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${problem.projectModeName}
				</td>
				<td>
					${problem.engineDepartName}
				</td>
				<td>
					${problem.communityName}-
					${problem.buildNumber}-
					${problem.buildUnit}-
					${problem.buildRoom}
				</td>
				<td>
					${problem.customerName}<br>${problem.customerPhone}
				</td>
				<td>
					${problem.supplierName}
				</td>
				<td>
					${problem.installItemName}
				</td>
				<td>
					${problem.problemTypeName}
				</td>
				<td>
					<span hidden="hidden">${problem.problemDescribe}</span>
					<a href="#" onclick="problemDescribe(this)">查看</a>
				</td>
				<td>
					<a  href="${ctx}/installConstructBillProblem/installConstructBillProblem/picture?problemId=${problem.problemId}" >查看</a>
				</td>
				<td>
					${problem.itemManager}<br>${problem.itemManagerPhone}
				</td>
				<td>
					${problem.designerName}<br>${problem.designerPhone}
				</td>
				<td>
					${problem.orderInspector}<br>${problem.inspectorPhone}
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>


	
	<div style="background:rgba(0,0,0,.4);z-index:9;" class="g-mask undis" id ="subReport">
		<div class="alertMask">
			<div class="maskWrapper">
				<p class="col_3 maskTit">描述</p>
				<div class="font28 col_6 maskContent" id="alertRemarks"></div>
				<div class="maskBtns clearfix">
					<a class="maskBtn font33 col_f"  onclick="sendMessage()" href="javascript:;">返回</a>
				</div>
			</div>
		</div>
	</div>
	
	
	
	
	<div class="pagination">${page}</div>
	<script type="text/javascript">
	
		 //关闭 
	    function sendMessage(){
	    	$("#subReport").hide();
	    }
    
//---------------------------------------问题描述start----------------------------------------------------

		function problemDescribe(obj){
			
			var problemDescribe = $(obj).parent().find("span").text().trim();
			
			if(null!=problemDescribe && problemDescribe!="" && undefined != problemDescribe){
				$("#alertRemarks").text(problemDescribe);
	    		$("#subReport").show(); 
			}else{
				$("#alertRemarks").text("暂无问题描述");
	    		$("#subReport").show(); 
			}
			
		} 

//---------------------------------------问题描述end----------------------------------------------------
	
	</script>
</body>
</html>