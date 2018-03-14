<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>特殊任务包生成</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
	$(document).ready(function() {
		getProcedures();
	});
	function page(n, s) {
		$("#pageNo").val(n);
		$("#pageSize").val(s);
		$("#searchForm").submit();
		return false;
	}

	function getProcedures() {
		$("#procedureList").html('');
		$
				.ajax({
					type : 'POST',
					dataType : 'json',
					url : '${ctx}/ordertaskpack/orderTaskpack/procedures',
					/* data : {
						'storeid' : $("#storeid").val()
					}, */
					success : function(data) {
						var html = "<select id='elactricationid' id='elactricationid' name='elactricationid' class='input-xlarge '>";
						html += "<option value='' ></option>"
						for (var i = 0; i < data.length; i++) {
							html += "<option value='" + data[i].value +"' >"
									+ data[i].label + "</option>"
						}
						html += "</select>";
						$("#procedureList").html('');
						$("#procedureList").html(html);
					}
				})
	}
</script>
<style>
.undis {
	display: none;
}

.g-mask {
	width: 100%;
	height: 100%;
	position: relative;
	z-index: 99;
	font-size: 0;
}

#g-done_ask {
	width: 400px;
	height: 230px;
	position: fixed;
	left: 50%;
	top: 50%;
	transform: translate(-50%, -50%);
	border: 1px solid #333;
	border-radius: 4px;
}

.refuse {
	height: 50px;
	line-height: 50px;
	font-size: 15px;
	background: #54b4eb;
	margin: 0;
}
/* .content{color:#000;width:400px;height:100px;resize: none;margin:0;} */
.content {
	color: #000;
	width: 400px;
	height: 50px;
	resize: none;
	margin: 0;
}

.second {
	width: 400px;
}

.second a {
	display: block;
	width: 200px;
	height: 50px;
	line-height: 50px;
	font-size: 24px;
	text-decoration: none;
	float: left;
	text-align: center;
	background: #54b4eb;
	color: #fff;
}

.second a:first-child {
	box-sizing: border-box;
	border-right: 1px solid #ccc;
}

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
</head>
<body>
	<ul>${ order.communityName}-${ order.buildNumber}-${ order.buildUnit}-${ order.buildRoom}-${ order.customerName}
	</ul>
	<input type="hidden" value="${order.id }" id="orderId">
	<form:form id="searchForm" modelAttribute="orderTaskpack" action=""
		method="post" class="breadcrumb form-search">
		<div class="control-group">
			<div class="controls">
				<label class="control-label">工序名称：</label>
				<form:select path="remarks" class="input-medium" id="procedureList"
					name="procedureList">
				</form:select>
				&nbsp;&nbsp;&nbsp;&nbsp; <a href="javascript:addProcedure()">添加</a>
				<ul class="ul-form">
					<li class="btns"><input id="btnCreateTaskpackage"
						class="btn btn-primary" type="button" value="生成任务包"
						onclick="createTaskpackage()" /></li>
					<li class="btns"><input id="btnBack" class="btn btn-primary"
						type="button" value="返回" onclick="history.go(-1)" /></li>
						
					<div class="order-total">
						<li><label
							style="width: 300px; margin-left: 40px; margin-top: 7px;">
								特殊任务包工料费预算总金额：<span class="item-price _total" id="totalAmount">0.00</span>
								元 </li>
						<c:if test="${templatBugetAmountMax != null}">

							</label>
							<input type="hidden"
								value="${templatBugetAmountMax.laborAuxiliaryMaterialsBudgetAmountMax}"
								id="templatBugetAmountMax" />
						</c:if>
				</div>
				</ul>

				


			</div>
		</div>
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
			<tbody id="tbody">

			</tbody>
		</table>
		<div class="g-mask undis" id="onCreateTaskpackage">
			<div id="g-done_ask">
				<p class="refuse">任务包名称：</p>
				<input type="text" class="content" id="packageName"
					required="required" maxlength="49"/>
				<p class="refuse">计划开工日期：</p>
				<!-- <input type="text" class="content" id="startDate" required="required" placeholder="格式为：XXXX-XX-XX"> -->
				<input id="startDate" type="text" readonly="readonly" maxlength="20"
					class="input-medium Wdate content"
					<%-- value="<fmt:formatDate value="" pattern="yyyy-MM-dd HH:mm:ss"/>" --%>
				onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});" />
				<p class="refuse">计划完工日期：</p>
				<!-- <input type="text" class="content" id="endDate" required="required" placeholder="格式为：XXXX-XX-XX"> -->
				<input id="endDate" type="text" readonly="readonly" maxlength="20"
					class="input-medium Wdate content"
					<%-- value="<fmt:formatDate value="" pattern="yyyy-MM-dd HH:mm:ss"/>" --%>
				onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});" />
				<p class="second">
					<a href="javascript:void(0)" onclick="noAdd()">取消</a> <a
						href="javascript:void(0)" onclick="yesAdd()">确定</a>
				</p>
			</div>
		</div>
	</form:form>
	<script type="text/javascript">
		function addProcedure() {
			var procedureId = $("#procedureList").val();
			if (procedureId == null || procedureId == '') {
				alertx("请先选择工序");
				return;
			}
			var elements = document.getElementsByName("ids");
			$.ajax({
				type : 'post',
				dataType : 'json',
				url : '${ctx}/ordertaskpack/orderTaskpack/getProcedureById',
				data : {
					'procedureId' : procedureId,
					'orderId' : $("#orderId").val(),
				},
				success : function(data) {
					if (data == null) {
						alertx("请先维护该工序的价格！");
					} else {
						if (elements.length > 0) {
							for (var i = 0; i < elements.length; i++) {
								if (data.id == elements[i].value) {
									//alert(elements[i].value);
									alertx("已经添加该工序！");
									return;
								}
							}
						}
						onAddProcedure(data);
					}

				}
			});
		}
		function onAddProcedure(data) {
			var html = "<tr>" + "<td>"
					+ data.packageName
					+ "<input type='hidden' value=\""+data.id+"\" name='ids'></td>"
					+ "<td>"
					+ data.procedureNo
					+ "</td>"
					+ "<td>"
					+ data.procedureName
					+ "</td>"
					+ "<td><input type='text' value='0.00' id='budgetNumber"
					+ data.id
					+ "' name='budgetNumbers' onkeyup='updateValue("
					+ data.id
					+ ",this)'></td>"
					+ "<td>"
					+ data.label
					+ "</td>"
					+ "<td id='laborPrice"+data.id+"'>"
					+ data.laborPrice
					+ "</td>"
					+ "<td id='accessoriesPrice"+data.id+"'>"
					+ data.accessoriesPrice
					+ "</td>"
					+ "<td id='synthesizePrice"+data.id+"'>"
					+ data.synthesizePrice
					+ "</td>"
					+ "<td><input type='text' value='0.00' id='laborBudgetAmount"+data.id+"' name='laborBudgetAmounts' readonly='readonly'></td>"
					+ "<td><input type='text' value='0.00' id='auxiliaryMaterialsBudgetAmount"+data.id+"' name='auxiliaryMaterialsBudgetAmounts' readonly='readonly'></td>"
					+ "<td><input type='text' value='0.00' id='total"+data.id+"' name='totals' readonly='readonly'></td>"
					+ "<td><textarea style='width:200px;height:100px;resize:none;' id='remarks"+data.id+"' name='remarks'>"
					+ data.remarks + "</textarea></td>"
			$("#tbody").append(html);
		}

		function updateValue(procedureId, obj) {
			//先把非数字的都替换掉，除了数字和. 
			obj.value = obj.value.replace(/[^\d.]/g, "");
			//必须保证第一个为数字而不是. 
			obj.value = obj.value.replace(/^\./g, "");
			//保证只有出现一个.而没有多个. 
			obj.value = obj.value.replace(/\.{2,}/g, ".");
			//保证.只出现一次，而不能出现两次以上 
			obj.value = obj.value.replace(".", "$#$").replace(/\./g, "")
					.replace("$#$", ".");
			//只能输入两个小数 
			obj.value = obj.value.replace(/^(\-)*(\d+)\.(\d\d).*$/, '$1$2.$3');
            
			var oldTotal = $("#total"+procedureId).val();
			
			var budgetNumber = $("#budgetNumber" + procedureId).val();//预算员确认数量
			var laborPrice = $("#laborPrice" + procedureId).text();//人工结算价
			var accessoriesPrice = $("#accessoriesPrice" + procedureId).text();//辅料结算价 
			var synthesizePrice = $("#synthesizePrice" + procedureId).text()//工料结算价 
			$("#total" + procedureId).val(
					Number(budgetNumber * synthesizePrice).toFixed(2));//工料费预算金额  
			$("#laborBudgetAmount" + procedureId).val(
					Number(budgetNumber * laborPrice).toFixed(2)) //人工费预算金额 = 预算员确认数量 * 人工结算价    
			$("#auxiliaryMaterialsBudgetAmount" + procedureId).val(
					Number(budgetNumber * accessoriesPrice).toFixed(2)) //辅料费预算金额 = 预算员确认数量 * 辅料结算价 
					
			var totalAmount = 0.00;
			var protTotals = document.getElementsByName("totals");
		    for (var i = 0; i < protTotals.length; i++) {
		    	var totalAmount = totalAmount + Number(protTotals[i].value);
		    }
		    $("#totalAmount").html(totalAmount.toFixed(2));
		}
		
		var ids1 = new Array();
		var numbers1 = new Array();
		var totals1 = new Array();
		var remarks1 = new Array();
		var laborBudgetAmounts1 = new Array();
		var auxiliaryMaterialsBudgetAmounts1 = new Array();
		var boo = true;
		function createTaskpackage() {
			if(boo == false){
				return;
			}
			var ids = document.getElementsByName("ids");
			var numbers = document.getElementsByName("budgetNumbers");
			var totals = document.getElementsByName("totals");
			var laborBudgetAmounts = document
					.getElementsByName("laborBudgetAmounts");
			var auxiliaryMaterialsBudgetAmounts = document
					.getElementsByName("auxiliaryMaterialsBudgetAmounts");
			var total = 0;
			if (ids.length > 0) {
				for (var i = 0; i < ids.length; i++) {
					ids1[i] = ids[i].value;
					numbers1[i] = numbers[i].value;
					totals1[i] = totals[i].value;
					remarks1[i] = $("#remarks" + ids[i].value).val();
					total = total + (parseFloat(totals[i].value));
					laborBudgetAmounts1[i] = laborBudgetAmounts[i].value;
					auxiliaryMaterialsBudgetAmounts1[i] = auxiliaryMaterialsBudgetAmounts[i].value;
				}
				if (total <= 0) {
					alert("请填写数量！");
					return;
				}
			} else {
				alertx("请先添加工序！");
				return;
			}
			$("#onCreateTaskpackage").removeClass("undis");
		}

		function noAdd() {
			$("#packageName").val("");
			$("#startDate").val("");
			$("#endDate").val("");
			$("#onCreateTaskpackage").addClass("undis");
		}

		function yesAdd() {
			if(boo == false){
				return;
			}
			var parmeters = '';
			for (var j = 0; j < ids1.length; j++) {
				if (j == ids1.length - 1) {
					parmeters += ids1[j] + '-' + numbers1[j] + '-' + totals1[j]
							+ '-' + remarks1[j] + '-' + laborBudgetAmounts1[j]
							+ '-' + auxiliaryMaterialsBudgetAmounts1[j];
				} else {
					parmeters += ids1[j] + '-' + numbers1[j] + '-' + totals1[j]
							+ '-' + remarks1[j] + '-' + laborBudgetAmounts1[j]
							+ '-' + auxiliaryMaterialsBudgetAmounts1[j]
							+ "####";
				}
			}
			var packageName = $("#packageName").val();
			var reg = /^(?:(?!0000)[0-9]{4}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-8])|(?:0[13-9]|1[0-2])-(?:29|30)|(?:0[13578]|1[02])-31)|(?:[0-9]{2}(?:0[48]|[2468][048]|[13579][26])|(?:0[48]|[2468][048]|[13579][26])00)-02-29)$/;
			var startDate = $("#startDate").val();
			var endDate = $("#endDate").val();
			if (packageName == null || packageName == '') {
				alertx("请填写任务包名称！");
				return;
			}
			var result = "";
			var templatBugetAmountMax = $("#templatBugetAmountMax").val();
		    var totalAmount = $("#totalAmount").html();
		    if(Number(totalAmount)>Number(templatBugetAmountMax)){
		    	result="特殊任务包预算总金额:"+totalAmount+"元，超过了预算上限金额（"+templatBugetAmountMax+"）。<br />";
		    }
		    result = result+"您确定要生成特殊任务包吗？";
			if (reg.test(startDate) == true && reg.test(endDate) == true) {
				/* if(result != ""){ */
					top.$.jBox.confirm(result,"系统提示",function(v,h,f){
						if(v=="ok"){
							boo = false;
							$.ajax({
								type : 'post',
								url : '${ctx}/ordertaskpack/orderTaskpack/createTaskpackage',
								data : {
									'packageName' : packageName,
									'startDate' : startDate,
									'endDate' : endDate,
									'parmeters' : parmeters,
									'orderId' : $("#orderId").val()
								},
								success : function(data) {
									if (data == "success") {
										alert("任务包生成成功！");
										window.location.href = "${ctx}/ordertaskpack/orderTaskpack/preList";
									} else if(data=="error"){
										alert("任务包生成失败！");
										window.location.href = "${ctx}/ordertaskpack/orderTaskpack/preList";
									}else if(data == "1"){
                                        alertx("不存在有效的任务包模板！");
									}

								}
							});
						}
					},{buttonsFocus:1});
					top.$('.jbox-body .jbox-icon').css('top','55px');
				/* }else{
					$.ajax({
						type : 'post',
						url : '${ctx}/ordertaskpack/orderTaskpack/createTaskpackage',
						data : {
							'packageName' : packageName,
							'startDate' : startDate,
							'endDate' : endDate,
							'parmeters' : parmeters,
							'orderId' : $("#orderId").val()
						},
						success : function(data) {
							if (data == "success") {
								alert("任务包生成成功！");
								window.location.href = "${ctx}/ordertaskpack/orderTaskpack/preList";
							} else {
								alert("任务包生成失败！");
								window.location.href = "${ctx}/ordertaskpack/orderTaskpack/preList";
							}
						}
					});
				} */
			} else {
				$("#startDate").val("");
				$("#endDate").val("");
				alertx("请输入正确的日期格式！");
			}
			$("#onCreateTaskpackage").addClass("undis");
		}
	</script>
</body>
</html>