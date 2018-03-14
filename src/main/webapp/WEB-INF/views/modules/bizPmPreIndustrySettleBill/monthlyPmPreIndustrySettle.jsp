<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>生成月度结算</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
		$(document).ready(function() {
			getDepartments();
			
			$("#allCheck").click(function () {
	            if ($(this).attr("checked")) { // 全选 
	                $("input[name='ids']").each(function () {
	                    $(this).attr("checked", true);
	                });
	            } else { // 取消全选 
	                $("input[name='ids']").each(function () {
	                    $(this).attr("checked", false);
	                });
	            }
	        });
			
			$("input[name='ids']").bind("click",function(){
				//全选
				if($("input[name='ids']:not(:checked)").length==0){
					$("#allCheck").attr("checked",true);
				}else{
					//不选
					$("#allCheck").attr("checked",false);
				}
			});
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);

			$("#searchForm").submit();
        	return false;
        }
		
		function clearCondition(){
			document.getElementById("searchForm").reset();

			var inputObjs = jQuery("#searchForm input[type='text']");
			for (var i = 0; i < inputObjs.length; i++) {
				var inputObj = inputObjs[i];
				inputObj.value = "";
			}

			var selectObjs = jQuery("#searchForm input[class='input-medium']");
			for (var i = 0; i < selectObjs.length; i++) {
				var selectObj = selectObjs[i];
				selectObj.value = "";
			}
			
			var arrSon = document.getElementsByName("orderStatusNumber");
			for (i = 0; i < arrSon.length; i++) {
				if(arrSon[i].checked){
					arrSon[i].checked=false;
				}
			}
		}

		
		function getDepartments(){
			$.ajax({
				url:'${ctx}/engdept/bizEngineeringDepartment/departmentList',
				type:'post',
				dataType : 'json',
				data:{
					'storeId':$('#storeId').val(),
					'projectMode':$('#projectMode').val(),
					'employeeId':$('#employeeId').val()
				},
				success:function(data){
					if(data == null || data == ""){
						$("#enginDepartId").html("");
						$("#s2id_enginDepartId .select2-chosen").html("");
					}else{
						var content = "<option></option>";
						for(var i=0;i<data.length;i++){
							if('${bizPmPreIndustrySettleBill.enginDepartId}' == data[i].value){
								$("#s2id_enginDepartId .select2-chosen").html(data[i].label);
								content = content + "<option value='"+data[i].value+"' selected='selected'>"+data[i].label+"</option>";
							}else{
								content = content + "<option value='"+data[i].value+"'>"+data[i].label+"</option>";
							}
						}
						$("#enginDepartId").html(content);
					}
				}
			});
		}
		
		function querySettleBillInfo(id){
			window.location.href="${ctx}/bizPmPreIndustrySettleBill/bizPmPreIndustrySettleBill/openSettleInfoInfo?settleBillId="+id;
		}
		
		function createMonthlyPmPreIndustrySettle(){
			if($("input[name='ids']:checked").length == 0){
				alertx("请选择结算单!");
				return;
			}
			var storeId = $("#storeId").val();
            if(storeId == ""){
                alertx("请选择门店!");
                return;
            }
            var storeIdseach = "${bizPmPreIndustrySettleBill.storeId}";
            if(storeIdseach != storeId){
                alertx("请先查询再生成月度结算单!");
                return;
            }

			var ids = "";
			var billCount = $("input[name='ids']:checked").length;
			$("input[name='ids']:checked").each(function () {
				if(ids == ""){
					ids = $(this).val();
				}else{
					ids = ids + "," + $(this).val();
				}
            });
			
//			$("#settleBillIds").val(ids);
			var storeId = $("#storeId").val();
			var count = 0;
			var settleMonth = "";
			
			$.ajax({
				url:'${ctx}/bizPmPreIndustrySettleBill/bizPmPreIndustrySettleBill/checkSettleBill?storeId='+storeId,
				type:'post',
				success:function(data){
					count = data.count;
					settleMonth = data.settleMonth;
//					if(count > 0){
//						alertx(settleMonth+"月已生成了工程结算单，不允许再生成");
//					}else{
						top.$.jBox.confirm("一共有"+billCount+"条记录将会生成月度工程结算单，结算月份:"+settleMonth+",确定要生成吗？","系统提示",function(v,h,f){
							if(v=="ok"){
								loading('正在提交，请稍等...');
								$("#createButton").unbind("click");
								window.location.href="${ctx}/bizPmPreIndustrySettleBill/bizPmPreIndustrySettleBill/createMonthlySettle?ids="+ids+"&settleMonth="+settleMonth+"&storeId="+storeId;
							}
						},{buttonsFocus:1});
						top.$('.jbox-body .jbox-icon').css('top','55px');
//					}
				}
			});
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="#">生成月度结算</a></li>
	</ul>
	<br />
	<form:form id="searchForm" modelAttribute="bizPmPreIndustrySettleBill"
		action="${ctx}/bizPmPreIndustrySettleBill/bizPmPreIndustrySettleBill/monthlyPmPreIndustrySettleList"
		method="post" class="breadcrumb form-search">

		<ul class="ul-form">
			<li><label>门店：</label> <c:if test="${empty storeDropEnable}">
					<form:select path="storeId" class="input-medium" disabled="true"
						id="storeId" onchange="getDepartments()">
						<form:options items="${fns:getStoreList()}" itemLabel="label"
							itemValue="value" htmlEscape="false" />
					</form:select>
				</c:if> <c:if test="${!empty storeDropEnable}">
					<form:select path="storeId" class="input-medium" id="storeId"
						onchange="getDepartments()">
						<form:options items="${fns:getStoreList()}" itemLabel="label"
							itemValue="value" htmlEscape="false" />
					</form:select>
				</c:if></li>

			<li><label>工程模式：</label> <form:select path="projectMode"
					id="projectMode" class="input-medium"
					disabled="true">
					<form:option value="4" label="准产业" />
				</form:select></li>

			<li><label>区域：</label> <select id="enginDepartId"
				name="enginDepartId" class="input-medium">

			</select></li>
			<li><label>订单编号：</label> <form:input path="orderNum"
					htmlEscape="false" maxlength="64" class="input-medium needClear" />
			</li>
			<li><label>客户姓名：</label> <form:input path="customerName"
					htmlEscape="false" maxlength="64" class="input-medium needClear" />
			</li>
			<li><label>项目经理：</label> <form:input path="itemCustomer"
					htmlEscape="false" maxlength="64" class="input-medium needClear" />
			</li>
			<li><label>结算单编号：</label> <form:input
					path="pmPreIndustrySettleBillCode" htmlEscape="false"
					maxlength="64" class="input-medium needClear" /></li>
			<li><label>结算单类型 ：</label> <form:select id="settleBillType"
					path="settleBillType" class="input-medium">
					<form:option value="" label="" />
					<form:options items="${fns:getDictList('settle_bill_type')}"
						itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select></li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary"
				type="submit" value="查询" /></li>
			<li class="btns"><input class="btn btn-primary clearBtn"
				type="button" value="清空" onclick="clearCondition()" /></li>
			<li class="btns"><input class="btn btn-primary clearBtn"
				type="button" value="生成月度结算" id="createButton" onclick="createMonthlyPmPreIndustrySettle()" /></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}" />
	
	<table id="contentTable"
		class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
			    <th><input type="checkbox" id="allCheck"/>全选</th>
				<th>门店</th>
				<th>工程模式</th>
				<th>区域</th>
				<th>项目经理同意时间</th>
				<th>订单编号</th>
				<th>小区</th>
				<th>客户</th>
				<th>项目经理</th>
				<th>项目经理手机号</th>
				<th>结算单编号</th>
				<th>结算单类型</th>
				<th>结算单金额</th>
				<th>结算单状态</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list}" var="settleBill">
				<tr>
				    <td><input type="checkbox" id="ids" name="ids" value="${settleBill.id}"/></td>
					<td>${fns:getStoreLabel(settleBill.storeId, '')}</td>
					<td>${fns:getDictLabel(settleBill.projectMode, 'project_mode', '')}
					</td>
					<td>${settleBill.departmentName}</td>
					<td><fmt:formatDate value="${settleBill.statusDatetime}"
							pattern="yyyy-MM-dd HH:mm:ss" /></td>
					<td>${settleBill.orderNum}</td>
					<td>
						${settleBill.communityName}-${settleBill.buildNumber}-${settleBill.buildUnit}-${settleBill.buildRoom}
					</td>
					<td>${settleBill.customerName}</td>
					<td>${settleBill.itemCustomer}</td>
					<td>${settleBill.itemPhone}</td>
					<td>${settleBill.pmPreIndustrySettleBillCode}</td>
					<td>${fns:getDictLabel(settleBill.settleBillType, 'settle_bill_type', '')}</td>
					<td>${settleBill.realSettleAmount}</td>
					<td>${fns:getDictLabel(settleBill.status, 'pm_settle_status', '')}</td>
					<td><a href="#" onclick="querySettleBillInfo(${settleBill.id})">查看结算单</a>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>