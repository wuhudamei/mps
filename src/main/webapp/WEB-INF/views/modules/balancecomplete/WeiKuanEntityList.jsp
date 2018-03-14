<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>尾款管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
		$(document).ready(function() {
			findEngineListByStoreIdAndProjectMode();
			var orderIds='${wkOrderIds}';
			
			<c:forEach var="ids" items="${wkOrderIds}"> 
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
			var url ="${ctx}/balancecomplete/WeiKuan/saveCheckWKValue";
			var param ={checkedStr:id};
			$.post(url,param,function(data){
				
			},"json")
		}
		
		//批量尾款
		function ConfirmWeiKuanpayment(){
			
			var wkOrderIds='${wkOrderIds}';
			
			if($("input[name='orderIds']:checked").length==0 && wkOrderIds == '[]'){
				alert("请至少选中一个在提交");
				return;
			}else{
				if(window.confirm("确定要提交吗")){
					loading('正在提交，请稍等...');
					$("#jform").attr("action","${ctx}/balancecomplete/WeiKuan/confirmCheckEndMoneys");
					$("#jform").attr("method","post").submit();
				}else{
					return;
				}
			}
			
		}
		
		function searchButton(){
			$("#searchForm").attr("action", "${ctx}/balancecomplete/WeiKuan/list2");
			$("#searchForm").submit();
		}
		
		//导出execl
		function exportExcelClick(){
			
			/* //门店
			var store=$("#sel").val();
			var storeId = '${weiKuanEntity.storeId}';
			if(store ==null || store==''){
				alertx("请先选择门店");
				return;
			}
			if(storeId != store){
				alertx("请先查询后在导出")
				return;
			}
			
			//区域
			var engineDepartId1 = '${weiKuanEntity.engineDepartId}';
			var engineDepartId = $("#engineDepartSelect").val();
			if(engineDepartId1 != engineDepartId){
				alertx("请先点击查询按钮后，再导出订单信息");
				return false;
			}
			
			//客户
			var customerName1 = '${weiKuanEntity.customerName}';
			var customerName = $("#customerName").val();
			if(customerName1 != customerName){
				alertx("请先点击查询按钮后，再导出订单信息");
				return false;
			}
			
			//项目经理
			var itemManagerName1 = '${weiKuanEntity.managerName}';
			var itemManagerName = $("#managerName").val();
			if(customerName1 != customerName){
				alertx("请先点击查询按钮后，再导出订单信息");
				return false;
			}
			
			//竣工审核日期  开始
			var beginContractStartDate1 = '${weiKuanEntity.start}';
			var beginContractStartDate = $("#beginContractStartDate").val();
			if(beginContractStartDate1 != beginContractStartDate){
				alertx("请先点击查询按钮后，再导出订单信息");
				return false;
			}
			
			//竣工审核日期  结束
			var endContractStartDate1 ='${weiKuanEntity.end}';
			var endContractStartDate = $("#endContractStartDate").val();
			if(endContractStartDate1 != endContractStartDate){
				alertx("请先点击查询按钮后，再导出订单信息");
				return false;
			} */
					
			$("#searchForm").attr("action","${ctx}/balancecomplete/WeiKuan/exportExcel");
			$("#searchForm").attr("method","post").submit();
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

		function confirmEndMoney(obj,orderId){
			top.$.jBox.confirm("您确认已收尾款吗？","系统提示",function(v,h,f){
				if(v=="ok"){
					loading('正在提交，请稍等...');
					$(obj).removeAttr("href");
					$(obj).removeAttr("onclick");
					/* window.location.href="${ctx}/balancecomplete/WeiKuan/confirmCheckEndMoney?orderId="+orderId; */
					$.ajax({
						url:"${ctx}/balancecomplete/WeiKuan/confirmCheckEndMoney?orderId="+orderId,
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
							if('${WeiKuanEntity.engineDepartId}' == data[i].value){
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
		/* var html3 = "<option value=''></option>";
		var storeId = $("#storeId").val();
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
								if('${WeiKuanEntity.engineDepartId}' == data[v].engineDepartId){
				            		sec = "selected='selected'";
				            		$("#s2id_enginDepartId .select2-chosen").html(data[v].engineDepartName);
				            		alert(sec);
				            	}
								html3 += "<option value='" + data[v].engineDepartId +"' " + sec + ">" + data[v].engineDepartName + "</option>";
							}
							alert(html3);
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
		<li class="active"><a href="${ctx}/balancecomplete/WeiKuan/">尾款列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="weiKuanEntity"
		action="${ctx}/balancecomplete/WeiKuan/list2" method="post"
		class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden"
			value="${page.pageSize}" />
		<ul class="ul-form">
			<li><label>门店：</label> <form:select path="storeId"
					class="input-medium needClear" id="storeId"
					onchange="findEngineListByStoreIdAndProjectMode()">
					<form:options items="${fns:getStoreList()}" itemLabel="label"
						itemValue="value" htmlEscape="false" />
				</form:select></li>

			<li><label>区域：</label> <form:select path="engineDepartId"
					class="input-medium needClear" id="enginDepartId">
					<form:option value="${WeiKuanEntity.engineDepartId }"
						label="${WeiKuanEntity.engineDepartName }" />
				</form:select></li>

			<li><label>客户：</label> <form:input path="customerName"
					id="customerName" htmlEscape="false" maxlength="100"
					class="input-medium" /></li>
			<li><label>项目经理</label> <form:input path="managerName"
					id="managerName" htmlEscape="false" maxlength="11"
					class="input-medium" /></li>
			<li><label>竣工审核日期</label> <input name="start"
				id="beginContractStartDate" type="text" readonly="readonly"
				maxlength="20" class="input-medium Wdate"
				value="<fmt:formatDate value="${WeiKuanEntity.start}" pattern="yyyy-MM-dd HH:mm:ss"/>"
				onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'endContractStartDate\')}',isShowClear:true});" />
				至 <input name="end" id="endContractStartDate" type="text"
				readonly="readonly" maxlength="20" class="input-medium Wdate"
				value="<fmt:formatDate value="${WeiKuanEntity.end}" pattern="yyyy-MM-dd HH:mm:ss"/>"
				onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'beginContractStartDate\')}',isShowClear:true});" />
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary"
				type="button" onclick="searchButton()" value="查询" /></li>
			<li class="btns"><input class="btn btn-primary clearBtn"
				type="button" value="清空" onclick="clearCondition()" /></li>
			<li class="btns"><input class="btn btn-primary" type="button"
				id="affirms" value="确认尾款" onclick="ConfirmWeiKuanpayment()" /></li>
			<li class="btns"><input class="btn btn-primary" type="button"
				value="导出execl" id="exportExcelId" onclick="exportExcelClick()" /></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}" />
	<form id="jform">
		<table id="contentTable"
			class="table table-striped table-bordered table-condensed">
			<thead>
				<tr>
					<th><input type="checkbox" id="controllercheckbox" /></th>
					<th>门店</th>
					<th>区域</th>
					<th>竣工审核通过日期</th>
					<th>订单号</th>
					<th>客户地址</th>
					<th>客户姓名</th>
					<th>项目经理</th>
					<th>交款金额</th>
					<th>交款时间</th>
					<th>申请竣工日期</th>
					<shiro:hasPermission name="balancecomplete:WeiKuanEntity:edit">
						<th>操作</th>
					</shiro:hasPermission>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${page.list}" var="bizOrderFinishProjectBill">
					<tr>
						<td><input type="checkbox" name="orderIds"
							value="${bizOrderFinishProjectBill.orderId}"
							onclick="clickCheck('${bizOrderFinishProjectBill.orderId}')" /></td>
						<td>${fns:getStoreLabel(bizOrderFinishProjectBill.storeId, '')}
						</td>
						<td>${bizOrderFinishProjectBill.engineDepartName}</td>
						<td><fmt:formatDate
								value="${bizOrderFinishProjectBill.applyDoneDate}"
								pattern="yyyy-MM-dd HH:mm:ss" /></td>
						<td>${bizOrderFinishProjectBill.orderNumber}</td>
						<td>${bizOrderFinishProjectBill.communityName}

							${bizOrderFinishProjectBill.buildNumber}-

							${bizOrderFinishProjectBill.buildUnit}-

							${bizOrderFinishProjectBill.buildRoom}</td>
						<td>${bizOrderFinishProjectBill.customerName}</td>
						<td>${bizOrderFinishProjectBill.managerName}</td>
						<td>${bizOrderFinishProjectBill.paymentAmount}</td>
						<td><fmt:formatDate
								value="${bizOrderFinishProjectBill.paymentDate}"
								pattern="yyyy-MM-dd" /></td>
						<td><fmt:formatDate
								value="${bizOrderFinishProjectBill.passDate}"
								pattern="yyyy-MM-dd HH:mm:ss" /></td>
						<shiro:hasPermission name="balancecomplete:WeiKuanEntity:edit">
							<td><a href="#"
								onclick="confirmEndMoney(this,${bizOrderFinishProjectBill.orderId})">确认已收尾款</a></td>
						</shiro:hasPermission>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</form>
	<div class="pagination">${page}</div>
</body>
</html>