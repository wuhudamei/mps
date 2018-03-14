<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>生成任务包->开始生成任务包</title>
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
		});
		
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
			var laborPrice = $("#laborPrice"+index).val();//人工结算价
			var accessoriesPrice = $("#accessoriesPrice"+index).val();//辅料结算价
			$("#total"+index).val(Number(budgetNumber*synthesizePrice).toFixed(2));//工料费预算金额 = 预算员确认数量 * 工料结算价
			$("#laborBudgetAmount"+index).val(Number(budgetNumber*laborPrice).toFixed(2)) //人工费预算金额 = 预算员确认数量 * 人工结算价    
			$("#auxiliaryMaterialsBudgetAmount"+index).val(Number(budgetNumber*accessoriesPrice).toFixed(2)) //辅料费预算金额 = 预算员确认数量 * 辅料结算价 
			
			var templatNumber = $("#templatNumber"+index).val();
			var totalAmount = 0.00;
			var templatBugetAmount = 0.00;
			var array = new Array();
			<c:forEach items="${orderTaskpackGenList}" var="p" varStatus="status">
			var no ='${p.templatNumber}';
			array.push(no);
			</c:forEach>
			
			for(var i=0;i<array.length;i++){ 
				totalAmount = Number(totalAmount) + Number($("#total"+i).val());
				var number = array[i];
				if( number == templatNumber){
					templatBugetAmount =templatBugetAmount+Number($("#total"+i).val());
				}
			}
			$("#templatBugetAmount"+templatNumber).html(templatBugetAmount);
			$("#totalAmount").html(totalAmount.toFixed(2));
		}
		
		//验证文本框只能输入数字和小数点
		function check(e,mark) {
			if(e.value == $("#budgetNumber"+mark).val()){
			    var re = /^\d+(?=\.{0,1}\d+$|$)/;
			    if (e.value != "") { 
			        if (!re.test(e.value)) { 
			            alert("请输入正确的数字和小数!"); 
			            $("#budgetNumber"+mark).val(0.00); 
			            $("#total"+mark).val("0.00")//如果输入正则验证不通过，则恢复默认值
			            e.focus(); 
			        } 
			    } 
			}
		} 
		var boo =true;
		//分装参数
		function submitGen(orderId,countSize,storeId,projectMode){//传订单ID
			if(boo == false){
				return;
			}
		   
			var n=0;
			var arr = new Array();
			var budgetNumber = $("#budgetNumber0");//预算数量
			var procedureNo = $("#procedureNo0");//工序编号
			var templatNumber = $("#templatNumber0");//任务包模板编号
			var total = $("#total0");//任务包总价
			var remarks = $("#remarks0");
			var laborBudgetAmount = $("#laborBudgetAmount0")//人工费预算金额
			var auxiliaryMaterialsBudgetAmount = $("#auxiliaryMaterialsBudgetAmount0")//辅料费预算金额
			while(n<=countSize){
				n++;
				remarks.each(function(index, val){
					var hidetext0 = $(this).val();
					if (hidetext0.length >= 200) {
						hidetext0 = hidetext0.substring(0, 199) + "...";
					}
					$(this).val(hidetext0);
				});
				arr.push({"templatNumber" : templatNumber.val(),"procedureNo" : procedureNo.val(),"budgetNumber" : budgetNumber.val(),"total" : total.val(),"remarks":remarks.val(),"laborBudgetAmount":laborBudgetAmount.val(),"auxiliaryMaterialsBudgetAmount":auxiliaryMaterialsBudgetAmount.val()});
				budgetNumber = $("#budgetNumber" + n);
				procedureNo = $("#procedureNo" + n);
				templatNumber = $("#templatNumber" + n);
				total = $("#total" + n);
				remarks = $("#remarks"+ n);
				laborBudgetAmount = $("#laborBudgetAmount"+ n);
				auxiliaryMaterialsBudgetAmount = $("#auxiliaryMaterialsBudgetAmount"+ n);
				if(n==countSize){
					break;
				}
			}
			
			var result="";
			var array = new Array();
			var arrayName = new Array(); 
			<c:forEach items="${templatBugetAmountMaxList}" var="t">  
			 var no ='${t.templatNumber}';
			 var name='${t.templatName}'
			array.push(no); //js中可以使用此标签，将EL表达式中的值push到数组中  
			arrayName.push(name);
			</c:forEach>  
			for(var i=0;i<array.length;i++){ 
			    var templatBugetAmountMax = $("#templatBugetAmountMax"+array[i]).val();
			    var templatBugetAmount = $("#templatBugetAmount"+array[i]).html();
			    if(Number(templatBugetAmount)>Number(templatBugetAmountMax)){
			    	result=result +arrayName[i]+"预算总金额:"+templatBugetAmount+"元，超过了预算上限金额（"+templatBugetAmountMax+"）。<br />";
			    }
			}
			result = result+"您确定要生成任务包吗？";
			if(orderId){
				/* if(result != ""){ */
					top.$.jBox.confirm(result,"系统提示",function(v,h,f){
						if(v=="ok"){
							boo=false;
							$.ajax({
								  type : "POST",
								  url : ctx + "/ordertaskpack/orderTaskpack/save",
								  //dataType : "json",
								  data:{
									  val : "{objson:" + JSON.stringify(arr) + "}",
									  orderId : orderId,
									  storeId : storeId,
									  projectMode : projectMode
								  },
								  success : function(msg){
								   	if(msg == 0){
								   		alert("订单任务包生成成功!");
								   		window.location.href = "${ctx}/ordertaskpack/orderTaskpack/stayList";
								   	}
								   	if(msg == 1){
								   		alert("写入订单任务表数据!");
								   	}
								   	if(msg == 2){
								   		alert("写入订单任务表工序数据!");
								   	}
								   	if(msg == 3){
								   		alert("订单任务表数据重复，请不要重复提交!");
								   	}
								   	if(msg == 4){
								   		alert("更新订单状态失败!");
								   	}
								  }
							}); 
						}
						
					},{buttonsFocus:1});
					top.$('.jbox-body .jbox-icon').css('top','55px');
				/* }else{
					$.ajax({
						  type : "POST",
						  url : ctx + "/ordertaskpack/orderTaskpack/save",
						  //dataType : "json",
						  data:{
							  val : "{objson:" + JSON.stringify(arr) + "}",
							  orderId : orderId,
							  storeId : storeId,
							  projectMode : projectMode
						  },
						  success : function(msg){
						   	if(msg == 0){
						   		alert("订单任务包生成成功!");
						   		window.location.href = "${ctx}/ordertaskpack/orderTaskpack/stayList";
						   	}
						   	if(msg == 1){
						   		alert("写入订单任务表数据!");
						   	}
						   	if(msg == 2){
						   		alert("写入订单任务表工序数据!");
						   	}
						   	if(msg == 3){
						   		alert("订单任务表数据重复，请不要重复提交!");
						   	}
						   	if(msg == 4){
						   		alert("更新订单状态失败!");
						   	}
						  }
					});
				} */
				
				
			} 
		}
		
		var size = '${countAll}';
		function isZero(){
			for(var i=0;i< size;i++ ){
				if($("#synthesizePrice"+i).val() == 0){
					if($("#trId"+i).hasClass("none")){
						$("#trId"+i).removeClass('none');
					}else{
						$("#trId"+i).addClass('none');
					}
				}
			}
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/ordertaskpack/orderTaskpack/stayList">任务包列表</a></li>
		<li class="active"><a href="javascript:void(0)">生成任务包</a></li>
	</ul>
	<ul class="nav nav-tabs">
		<%-- <li><a>${order.customerName }</a></li> --%>
		<li class="btns"><a href="javascript:void(0)"
			class="btn btn-primary"
			onclick="submitGen(${orderId},${countAll },${storeId },${projectMode});return false;">生成任务包</a></li>
		<li><label
			style="width: 180px; margin-left: 40px; margin-top: 7px;">隐藏定额综合价为零的工序：</label>
			<input type="checkbox" onclick="isZero()" /></li>
	</ul>
	<div class="order-total">
		<div class="order-row row-title">
			<div class="order-item">
				订单任务包工料费预算总金额 : <span class="item-price _total" id="totalAmount">0.00</span>
				元
			</div>
		</div>
		<ul class="nav nav-tabs">
			<c:forEach items="${taskpackageTemplatList}" var="templat">
				<li><label
					style="width: 300px; margin-left: 40px; margin-top: 7px;">
						${templat.templatName}工料费预算总金额：<span class="item-price _total" id="templatBugetAmount${templat.templatNumber}">0.00</span>
						元
				</label></li>
			</c:forEach>
		</ul>

		<c:forEach items="${templatBugetAmountMaxList}" var="templatBugetAmountMax">
			<input type="hidden"
				value="${templatBugetAmountMax.laborAuxiliaryMaterialsBudgetAmountMax}"
				id="templatBugetAmountMax${templatBugetAmountMax.templatNumber}" />
		</c:forEach>
	</div>

	<sys:message content="${message}" />
	<table id="contentTable"
		class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>工序归属任务包</th>
				<th>工序编号</th>
				<th>工序名称</th>
				<th>预算员确认数量</th>
				<th>单位</th>
				<th>人工结算单价</th>
				<th>辅料结算单价</th>
				<th>工料结算单价</th>
				<th>人工费预算金额</th>
				<th>辅料费预算金额</th>
				<th>(工人+辅料）预算金额</th>
				<th>备注</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${orderTaskpackGenList}" var="p" varStatus="status">
				<tr id="trId${status.index }">
					<td><input type="hidden" value="${p.templatNumber }"
						id="templatNumber${status.index }" /> ${p.templatName}</td>
					<td><input type="hidden" value="${p.procedureNo}"
						id="procedureNo${status.index }" /> ${p.procedureNo}</td>
					<td>${p.procedureName}</td>
					<td><input type="text" id="budgetNumber${status.index }"
						value="0.00" onkeyup="updateValue(${status.index },this);"
						maxLength="9" size="10" /></td>
					<td>${p.measurementUnit}</td>
					<td>
						<!-- 人工结算价 --> <input id="laborPrice${status.index }"
						value="${p.laborPrice}" readonly="readonly"
						style="border: 0; font-size: medium; color: -webkit-text;" />
					</td>
					<td>
						<!-- 辅料结算价 --> <input id="accessoriesPrice${status.index }"
						value="${p.accessoriesPrice}" readonly="readonly"
						style="border: 0; font-size: medium; color: -webkit-text;" />
					</td>
					<td>
						<!-- 工料结算价 --> <input id="synthesizePrice${status.index }"
						value="${p.synthesizePrice}" readonly="readonly"
						style="border: 0; font-size: medium; color: -webkit-text;" />
					</td>
					<td>
						<!-- 人工费预算金额 --> <input id="laborBudgetAmount${status.index }"
						readonly="readonly" value="0.00"
						style="border: 0; font-size: medium; color: -webkit-text;" />
					</td>
					<td>
						<!-- 辅料费预算金额 --> <input
						id="auxiliaryMaterialsBudgetAmount${status.index }"
						readonly="readonly" value="0.00"
						style="border: 0; font-size: medium; color: -webkit-text;" />
					</td>
					<td>
						<!-- 工料预算金额 --> <input id="total${status.index }" value="0.00"
						readonly="readonly"
						style="border: 0; font-size: medium; color: -webkit-text;" />
					</td>
					<td><textarea id="remarks${status.index }"
							style='width: 200px; height: 100px; resize: none;' name="remarks">${p.remarks}</textarea>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>