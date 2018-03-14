<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>二期款结算管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			findEngineListByStoreIdAndProjectMode();
			var orderIds='${orderIds}';
			
			<c:forEach var="ids" items="${orderIds}"> 
				$("input[name='orderIds'][value='"+${ids}+"']").prop("checked",true);
			</c:forEach> 
			
			/**
			 * 全选与全不选
			 */
			$("#controllercheckbox").unbind("click");
			$("#controllercheckbox").bind("click",function(){
				if($(this).attr("checked")){
					$("input[name='orderIds']").attr("checked",true);
				}else{
					$("input[name='orderIds']").attr("checked",false);
				}
				
			});
			
			/**
			 * 表格中checked事件
			 */
			$("input[name='orderIds']").unbind("click");
			$("input[name='orderIds']").bind("click",function(){
				//全选
				if($("input[name='orderIds']:not(:checked)").length==0){
					$("#controllercheckbox").attr("checked",true);
				}else{
					//不选
					$("#controllercheckbox").attr("checked",false);
				}
			});
			

		});
		
		
		function clickCheck(id){
			var url ="${ctx}/balancemid/Erqi/saveCheckValue";
			var param ={checkedStr:id};
			$.post(url,param,function(data){
				
			},"json")
		}
		
		//批量二期款
		function ConfirmErqipayment(){
			var orderIds='${orderIds}';
			
			if($("input[name='orderIds']:checked").length==0 && orderIds == '[]'){
				alert("请至少选中一个在提交");
				return;
			}else{
				if(window.confirm("确定要提交吗")){
					loading('正在提交，请稍等...');
					$("#jform").attr("action","${ctx}/balancemid/Erqi/confirmCheckSecondMoneys");
					$("#jform").attr("method","post").submit();
					
				}else{
					return;
				}
			}
			
		}
		
		//导出execl
		function exportExcelClick(){
			
			/* //门店
			var store=$("#sel").val();
			var storeId = '${erqiEntity.storeId}';
			if(store ==null || store==''){
				alertx("请先选择门店");
				return false;
			}
			if(storeId != store){
				alertx("请先查询后在导出")
				return;
			}
			
			//区域
			var engineDepartId1 = '${erqiEntity.engineDepartId}';
			var engineDepartId = $("#engineDepartSelect").val();
			if(engineDepartId1 != engineDepartId){
				alertx("请先点击查询按钮后，再导出订单信息");
				return false;
			}
			
			//客户
			var customerName1 = '${erqiEntity.customerName}';
			var customerName = $("#customerName").val();
			if(customerName1 != customerName){
				alertx("请先点击查询按钮后，再导出订单信息");
				return false;
			}
			
			//项目经理
			var itemManagerName1 = '${erqiEntity.itemManagerName}';
			var itemManagerName = $("#itemManagerName").val();
			if(customerName1 != customerName){
				alertx("请先点击查询按钮后，再导出订单信息");
				return false;
			}
			
			//基装验收日期  开始
			var beginContractStartDate1 = '${erqiEntity.start}';
			var beginContractStartDate = $("#beginContractStartDate").val();
			if(beginContractStartDate1 != beginContractStartDate){
				alertx("请先点击查询按钮后，再导出订单信息");
				return false;
			}
			
			//基装验收日期  结束
			var endContractStartDate1 ='${erqiEntity.end}';
			var endContractStartDate = $("#endContractStartDate").val();
			if(endContractStartDate1 != endContractStartDate){
				alertx("请先点击查询按钮后，再导出订单信息");
				return false;
			} */
					
			$("#searchForm").attr("action","${ctx}/balancemid/Erqi/exportExcel").submit();
			
		}
		
		function searchButton(){
			$("#searchForm").attr("action", "${ctx}/balancemid/Erqi/list2");
			$("#searchForm").submit();
		}
		
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);

			searchButton();
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
		
		function confirmSecondMoney(obj,orderId){
			top.$.jBox.confirm("您确认已收二期款吗？","系统提示",function(v,h,f){
				if(v=="ok"){
					loading('正在提交，请稍等...');
					$(obj).removeAttr("href");
					$(obj).removeAttr("onclick");
					/* window.location.href=""+; */
					$.ajax({
						url:"${ctx}/balancemid/Erqi/confirmCheckSecondMoney?orderId="+orderId,
						type:"post",
						success:function(data){
							closeTip();
							if(data == "0"){
								alertx("操作成功!");
								$("#searchForm").submit();
							}else if(data == "1"){
								alertx("该订单已收二期款,请不要重复提交！");
							}else if(data == "2"){
								alertx("系统异常，请联系管理员！");
							}
						}
					});
				}
			},{buttonsFocus:1});
			top.$('.jbox-body .jbox-icon').css('top','55px');
		}
		
		function findEngineListByStoreIdAndProjectMode(){
			$("#enginDepartId").html('');
			$.ajax({
				url:'${ctx}/engdept/bizEngineeringDepartment/departmentList',
				type:'post',
				dataType : 'json',
				data:{
					'storeId':$('#storeId').val(),
					'projectMode':1
				},
				success:function(data){
					if(data == null){
						$("#enginDepartId").html('');
					} else {
						var html = "<option value=''></option>";
						for(var i=0;i<data.length;i++){
							var sec = "";
							if('${ErqiEntity.engineDepartId}' == data[i].value){
			            		sec = "selected='selected'";
			            		$("#s2id_enginDepartId .select2-chosen").html(data[i].label);
			            	}
							html += "<option value='" + data[i].value +"' " + sec + ">" + data[i].label + "</option>";
						}
						html+='';
		        		$("#enginDepartId").append(html);
					}
				}
			});
			/* var html3 = '<option value=""></option>';
		var storeId = $("#sel").val();
		if (storeId==""  ||undefined==storeId) {
			return;
		}
		//根据门店个,工程模式    动态加载工程区域
		$.ajax({

					url : "${ctx}/order/order/findEngineDepartmentBystoreIdAndProjectMode?storeId="
							+ storeId + "&projectModeValue=1",
					type : "get",
					success : function(data) {
						if (null!= data && data.length > 0) {

							for (var v = 0; v < data.length; v++) {
								var sec = "";
								if('${ErqiEntity.engineDepartId}' == data[i].value){
				            		sec = "selected='selected'";
				            		$("#s2id_enginDepartId .select2-chosen").html(data[i].label);
				            	}
								html3 += "<option value='" + data[v].engineDepartId +"' " + sec + ">" + data[v].engineDepartName + "</option>";
							}
							
							$("#engineDepartSelect").html(html3);
						} else {
							$("#engineDepartSelect").html(html3);
						}

					}

				});		 */
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/balancemid/Erqi/">列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="erqiEntity" action="${ctx}/balancemid/Erqi/list2" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>门店：</label>
				<form:select path="storeId" class="input-medium needClear" id="storeId" onchange="findEngineListByStoreIdAndProjectMode()">
					<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>区域：</label>
				<form:select path="engineDepartId" class="input-medium needClear" id="enginDepartId">
					
				</form:select>
			</li>
			<li><label>客户：</label>
				<form:input path="customerName" id="customerName" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>项目经理</label>
				<form:input path="itemManagerName" id="itemManagerName" htmlEscape="false" maxlength="11" class="input-medium"/>
			</li>
			<li><label>基装验收日期</label>
				<input name="start" id="beginContractStartDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${ErqiEntity.start}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'endContractStartDate\')}',isShowClear:true});"/> 至  
				<input name="end" id="endContractStartDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${ErqiEntity.end}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'beginContractStartDate\')}',isShowClear:true});"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="button" value="查询" onclick="searchButton()"></li>
			<li class="btns"><input class="btn btn-primary clearBtn" type="button" value="清空" onclick="clearCondition()"/></li>
			<shiro:hasPermission name="balancemid:ErqiEntity:edit">
				<li class="btns">
					<input class="btn btn-primary" type="button" id="affirms" value="确认二期款" onclick="ConfirmErqipayment()"/>
				</li>
				<li class="btns">
					<input class="btn btn-primary" type="button" value="导出execl" id="exportExcelId" onclick="exportExcelClick()"/>
				</li>
			</shiro:hasPermission>
			
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<form id="jform">
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th><input type="checkbox" id="controllercheckbox" /></th>
				<th>门店</th>
				<th>区域</th>
				<th>结算员通过验收时间</th>
				<th>订单号</th>
				<th>客户地址</th>
				<th>客户姓名</th>
				<th>项目经理</th>
				<th>交款金额</th>
				<th>交款时间</th>
				<th>申请约检日期</th>
				<th>质检员</th>
				<th>质检验收日期</th>
				<shiro:hasPermission name="balancemid:ErqiEntity:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="bizQcBill">
			<tr>
			<td><input type="checkbox" id="orderIds" name="orderIds" value="${bizQcBill.orderId}" onclick="clickCheck('${bizQcBill.orderId}')"/></td>
				<td>
					${fns:getStoreLabel(bizQcBill.storeId, '')}
				</td>
				<td>${bizQcBill.engineDepartName}</td>
				<td><fmt:formatDate value="${bizQcBill.reviewDateTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td>
					${bizQcBill.orderNumber}
				</td>
				<td>
				${bizQcBill.communityName}-
				
					${bizQcBill.buildNumber}-
				
					${bizQcBill.buildUnit}-
				
					${bizQcBill.buildRoom}
				</td>
				<td>
					${bizQcBill.customerName}
				</td>
				<td>
				${bizQcBill.itemManagerName}
				</td>
				<td>
				${bizQcBill.paymentAmount}
				</td>
				<td>
				    <fmt:formatDate value="${bizQcBill.paymentDate}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					<fmt:formatDate value="${bizQcBill.applyCheckDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${bizQcBill.inspectName}
				</td>
				<td>
					<fmt:formatDate value="${bizQcBill.yanshouDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="balancemid:ErqiEntity:edit"><td><a href="#" onclick="confirmSecondMoney(this,${bizQcBill.orderId})">确认已收二期款</a></td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	</form>
	<div class="pagination">${page}</div>
</body>
</html>