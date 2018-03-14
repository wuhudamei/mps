<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>任务包修改</title>
<meta name="decorator" content="default" />
<style>
.none {
	display: none;
}

.order-total {
	width: 100%;
	font-size: 12px;
	color: #5a5858;
}

.order-row {
	border: 1px solid #ccc;
	display: flex;
	border-top: 0;
	line-height: 2;
}

.order-row.row-title {
	border-width: 0 0 1px 0;
	text-align: center;
}

.order-item {
	flex: 1;
	display: inline-block;
}

.order-total .item-price {
	font-weight: bold;
	color: #3b3939;
	font-size: 14px;
}

.order-total .item-price._total {
	font-size: 18px;
	color: #d80101;
}
</style>
<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
			
			//绑定onclick事件
			$("#update").bind('click',submitTaskpackage);
			
			$("#btnSubmit").bind('click',submitTaskpackage);
		});
		
		//校验预算员输入数量
		function updateValue(index,obj){
			//先把非数字的都替换掉，除了数字和. 
	        obj.value = obj.value.replace(/[^\d.]/g,""); 
	        //必须保证第一个为数字而不是. 
	        obj.value = obj.value.replace(/^\./g,""); 
	        //保证只有出现一个.而没有多个. 
	        obj.value = obj.value.replace(/\.{2,}/g,"."); 
	        //保证.只出现一次，而不能出现两次以上 
	        obj.value = obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$",".");
	        //只能输入两个小数 
	        obj.value = obj.value.replace(/^(\-)*(\d+)\.(\d\d).*$/,'$1$2.$3');
	        
			var budgetNumber = $("#budgetNumber"+index).val();//预算员确认数量
			var synthesizePrice = $("#synthesizePrice"+index).val();//工料结算价 
			var laborPrice  = $("#laborPrice"+index).val();// 人工结算价
			var accessoriesPrice = $("#accessoriesPrice"+index).val();// 辅料结算价
			$("#laborBudgetAmount"+index).val(Number(budgetNumber*laborPrice).toFixed(2)) //人工费预算金额 = 预算员确认数量 * 人工结算价
			$("#auxiliaryMaterialsBudgetAmount"+index).val(Number(budgetNumber*accessoriesPrice).toFixed(2))//辅料费预算金额 = 预算员确认数量 * 辅料结算价
			var total = budgetNumber*synthesizePrice;//工料费预算金额  = 预算员确认数量 * 工料结算价 
			$("#total"+index).val(total.toFixed(2));//保留小数后2位
		
			var length = ${orderTaskpackageList.size()};
			var totalAmount = 0.00;
			for(var i=0;i<length;i++){ 
				totalAmount = Number(totalAmount) + Number($("#total"+i).val());
			}
			$("#totalAmount").html(totalAmount.toFixed(2));
		}
		
		//修改
		function submitTaskpackage(){
			var num = 0;
			var arr = new Array();
			var listSize = ${listSize};
			var budgetNumber = $("#budgetNumber0");
			var total = $("#total0");
			var procedureID = $("#procedureID0");
			var remarks=$("#remarks0");
			var laborBudgetAmount = $("#laborBudgetAmount0");
			var auxiliaryMaterialsBudgetAmount = $("#auxiliaryMaterialsBudgetAmount0");
			while(num <= listSize){
				num ++;
				//组装JSON串
				arr.push({"procedureID" : procedureID.val() ,"budgetNumber" : budgetNumber.val(), "total" : total.val(),"remarks":remarks.val(),"laborBudgetAmount":laborBudgetAmount.val(),"auxiliaryMaterialsBudgetAmount":auxiliaryMaterialsBudgetAmount.val()});
				budgetNumber = $("#budgetNumber" + num);
				total = $("#total" + num);
				procedureID = $("#procedureID" + num);
				remarks = $("#remarks"+num);
				laborBudgetAmount = $("#laborBudgetAmount"+num);
				auxiliaryMaterialsBudgetAmount = $("#auxiliaryMaterialsBudgetAmount"+num);
				if(num == listSize){
					break;
				}
			}
			$("#update").css("color","#CCC");
			$("#update").unbind("click");
			var result = "";
			var templatBugetAmountMax = $("#templatBugetAmountMax").val();
		    var totalAmount = $("#totalAmount").html();
		    if(Number(totalAmount)>Number(templatBugetAmountMax)){
		    	result="${templatBugetAmount.templatName}"+"预算总金额:"+totalAmount+"元，超过了预算上限金额（"+templatBugetAmountMax+"）。<br />";
		    }
		    result = result+"您确定要修改${templatBugetAmount.templatName}任务包吗";
			if(listSize){
				if(result != ""){
					top.$.jBox.confirm(result,"系统提示",function(v,h,f){
						if(v=="ok"){
							$.ajax({
								  type : "POST",
								  url : ctx + "/ordertaskpackageprocedure/orderTaskpackageProcedure/updateByAuditProcedure",
								  dataType : "json",
								  data : {
									  jsonVal : "{json:" + JSON.stringify(arr) + "}",
									  taskpackageID : ${orderTaskpackage.id },
									  viewFlag: ${flag}
								  },
								  success : function(msg){
								   	if(msg.result == 0){
								   		alert("修改成功!");
								   		if(msg.flag == 0){
								   		    var isSpecial = '${isSpecial}';
								   		    if( isSpecial == '0'){
                                                window.location.href = "${ctx}/ordertaskpackageaudit/orderTaskpackageAudit/stayList";
											}else if(isSpecial == '1'){
                                                window.location.href = "${ctx}/ordertaskpackageaudit/orderTaskpackageAudit/staySpectialList";
											}
								   		} else {
								   			window.location.href = "${ctx}/ordertaskpackageaudit/orderTaskpackageAudit/list";
								   		}
								   	}
								   	if(msg.result == 1){
								   		alert("修改订单任务包工序失败!");
								   	}
								   	if(msg.result == 2){
								   		alert("修改订单任务包失败!");
								   	}
								  }
								});
						}
					},{buttonsFocus:1});
					top.$('.jbox-body .jbox-icon').css('top','55px');
				}else{
					$.ajax({
						  type : "POST",
						  url : ctx + "/ordertaskpackageprocedure/orderTaskpackageProcedure/updateByAuditProcedure",
						  dataType : "json",
						  data : {
							  jsonVal : "{json:" + JSON.stringify(arr) + "}",
							  taskpackageID : ${orderTaskpackage.id },
							  viewFlag: ${flag}
						  },
						  success : function(msg){
						   	if(msg.result == 0){
						   		alert("修改成功!");
						   		if(msg.flag == 0){
						   			window.location.href = "${ctx}/ordertaskpackageaudit/orderTaskpackageAudit/stayList";
						   		} else {
						   			window.location.href = "${ctx}/ordertaskpackageaudit/orderTaskpackageAudit/list";
						   		}
						   	}
						   	if(msg.result == 1){
						   		alert("修改订单任务包工序失败!");
						   	}
						   	if(msg.result == 2){
						   		alert("修改订单任务包失败!");
						   	}
						  }
						});
				}
				
			}
		}
		
		function  addProduce(packageCode,orderId,taskpackageId,flag,isSpecial) {
			$.ajax({
			    url:"${ctx}/ordertaskpackageaudit/orderTaskpackageAudit/checkTemplat?packageCode="+packageCode,
				type:"POST",
				success:function(data){
				if(data == 0){
                    window.location.href = "${ctx}/ordertaskpackageaudit/orderTaskpackageAudit/preInstallProcedure?packageCode="+packageCode+"&orderId="+orderId+"&taskpackageId="+taskpackageId+"&flag="+flag+"&isSpecial="+isSpecial;
				}else if(data == 1){
				    alertx("该任务包模板已停用，请先启用！");
				}
			  }
			});
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<c:if test="${flag == '0'}">
			<li>
				<c:if test="${isSpecial == '0' }"><a href="${ctx}/ordertaskpackageaudit/orderTaskpackageAudit/stayList">待审核任务包列表</a></c:if>
				<c:if test="${isSpecial == '1' }"><a href="${ctx}/ordertaskpackageaudit/orderTaskpackageAudit/staySpectialList">待审核特殊任务包列表</a></c:if>
			</li>
			<li class="active"><a>查看详情</a></li>
		</c:if>
		<c:if test="${flag == '1'}">
			<li><a
				href="${ctx}/ordertaskpackageaudit/orderTaskpackageAudit/list">已审核任务包列表</a></li>
			<li class="active"><a>查看详情</a></li>
		</c:if>
	</ul>
	<ul class="nav nav-tabs">
		<li><a onclick="addProduce('${orderTaskpackage.packageCode }',${orderTaskpackage.orderId },'${orderTaskpackage.id }',${flag},${isSpecial})"
			href="javascript:void(0)">新增一项工序</a></li>
		<li class="active"><a href="javascript:void(0)" id="update">更新任务包</a></li>
		<div class="order-total">
			<li><label
				style="width: 300px; margin-left: 40px; margin-top: 7px;">
					${orderTaskpackage.packageName}工料费预算总金额：<span
					class="item-price _total" id="totalAmount">${orderTaskpackage.total}</span>
					元
			</label></li>

			<c:if test="${templatBugetAmountMax != null}">
				<input type="hidden"
					value="${templatBugetAmountMax.laborAuxiliaryMaterialsBudgetAmountMax}"
					id="templatBugetAmountMax" />
			</c:if>
		</div>
	</ul>
	<table id="contentTable"
		class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>工序归属任务包</th>
				<th>工序编号</th>
				<th>工序名称</th>
				<th>预算员确认数量</th>
				<th>单位</th>
				<th>人工结算价</th>
				<th>辅料结算价</th>
				<th>工料结算价</th>
				<th>人工费预算金额</th>
				<th>辅料费预算金额</th>
				<th>工料费预算金额</th>
				<th>备注</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${orderTaskpackageList}" var="p" varStatus="status">
				<tr>
					<td>${p.packageName}</td>
					<td>${p.procedureNo}</td>
					<td><input type="hidden" id="procedureID${status.index }"
						value="${p.id }"> ${p.procedureName}</td>
					<td><input id="budgetNumber${status.index }"
						value="${p.budgetNumber}" maxLength="9" size="10"
						onkeyup="updateValue(${status.index },this);" /></td>
					<td>${p.label}</td>
					<td><input id="laborPrice${status.index }"
						value="${p.laborPrice}" readonly="readonly"
						style="border: 0; font-size: medium; color: -webkit-text;" /></td>
					<td><input id="accessoriesPrice${status.index }"
						value="${p.accessoriesPrice}" readonly="readonly"
						style="border: 0; font-size: medium; color: -webkit-text;" /></td>
					<td>
						<!-- 工料结算价 --> <input id="synthesizePrice${status.index }"
						value="${p.synthesizePrice}" readonly="readonly"
						style="border: 0; font-size: medium; color: -webkit-text;" />
					</td>
					<td><input id="laborBudgetAmount${status.index }"
						value="${p.laborBudgetAmount}" readonly="readonly"
						style="border: 0; font-size: medium; color: -webkit-text;" /></td>
					<td><input id="auxiliaryMaterialsBudgetAmount${status.index }"
						value="${p.auxiliaryMaterialsBudgetAmount}" readonly="readonly"
						style="border: 0; font-size: medium; color: -webkit-text;" /></td>
					<td>
						<!-- 工料费预算金额 --> <input id="total${status.index }"
						value="${p.laborAuxiliaryMaterialsBudgetAmount}"
						readonly="readonly"
						style="border: 0; font-size: medium; color: -webkit-text;" />
					</td>
					<td><textarea id="remarks${status.index}">${p.remarks}</textarea>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<input id="btnSubmit" class="btn btn-primary" type="button"
		value="保  存" />
</body>
</html>