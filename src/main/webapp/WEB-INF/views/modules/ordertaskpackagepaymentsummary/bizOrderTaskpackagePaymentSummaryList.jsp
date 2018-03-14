<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>付款单批次管理</title>
	<meta name="decorator" content="default"/>
	<style>
		.undis{display:none;}
		.g-mask{width:100%;height:100%;position:relative;z-index:99;font-size:0;}
		#g-done_ask{width:400px;height:200px;position:fixed;left:50%;top:50%;transform:translate(-50%,-50%);border:1px solid #333;border-radius:4px;}
		.refuse{height:50px;line-height:50px;font-size:20px;background:#54b4eb;margin:0;}
		.content{color:#000;width:400px;height:100px;resize: none;margin:0;}
		.second{width:400px;}
		.second a{display:block;width:200px;height:50px;line-height:50px;font-size:24px;text-decoration:none;float:left;text-align:center;background:#54b4eb;color:#fff;}
		.second a:first-child{box-sizing:border-box;border-right:1px solid #ccc;}
	</style>
	<script type="text/javascript">
		
		$(document).ready(function() {
			getDepartments();
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
		
		function abolish(id){
			$("#id").val(id);
			$("#abolishSummary").removeClass("undis");
		}
		function yesReject(){
			var reason = $("#reason").val();
			$("#reason").val("");
			$("#abolishSummary").addClass("undis");
			//window.location.href = "${ctx}/ordertaskpackagesettlement/bizOrderTaskpackageSettlementVo/updateTaskpackageStatus?orderTaskpackageId="+orderTaskpackageId+"&status=130&statusName=结算员驳回"+"&reason="+reason;
		}
		function noReject(){
			$("#reason").val("");
			$("#abolishSummary").addClass("undis");
		}
		
		// 批次作废理由取消
		function noAbolish(){
			$("#reason").val("");
			$("#id").val("");
			$("#abolishSummary").attr("class", "undis");
		}
		
		// 批次作废理由确认
		function yesAbolish(){
			loading('正在提交，请稍等...');
			 $("a[data='abolish']").removeAttr("onclick");
			$("a[data='abolish']").removeAttr("href");
			
			var cancelReason = $("#reason").val();
			var id = $("#id").val();
			$("#abolishSummary").attr("class", "undis");
			$.post("${ctx}/ordertaskpackagepaymentsummary/bizOrderTaskpackagePaymentSummary/summaryAbolish",{id:id,cancleReason:cancelReason},function(data){
				if(data == "success"){
					window.location.reload();
				}else if(data == "already"){
					alertx("此批次已经废除，不可再废除");
					window.location.reload();
				}else{
					alertx("废除失败");
				}
			}); 
			//window.location.href="${ctx}/ordertaskpackagepaymentsummary/bizOrderTaskpackagePaymentSummary/summaryAbolish?id="+id+"&cancleReason="+cancelReason;
		}
		
		// 批次通过
		function approvePass(obj,id){
			top.$.jBox.confirm("您确认该批次审批通过吗？","系统提示",function(v,h,f){
				if(v=="ok"){
					loading('正在提交，请稍等...');
					$(obj).removeAttr("href");
					$(obj).removeAttr("onclick");
					window.location.href="${ctx}/ordertaskpackagepaymentsummary/bizOrderTaskpackagePaymentSummary/approvePass?id="+id;
				}
			},{buttonsFocus:1});
			top.$('.jbox-body .jbox-icon').css('top','55px');			
		}
		
		
		//加载区域信息
		function getDepartments(){
			$("#enginDepartId").html('');
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
					if(data == null){
						$("#enginDepartId").html('');
					} else {
						var html = "<option value=''></option>";
						for(var i=0;i<data.length;i++){
							var sec = "";
							if('${bizOrderTaskpackagePaymentSummary.enginDepartId }' == data[i].value){
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
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/ordertaskpackagepaymentsummary/bizOrderTaskpackagePaymentSummary/list">付款单批次列表</a></li>
		<%-- <shiro:hasPermission name="ordertaskpackagepaymentsummary:bizOrderTaskpackagePaymentSummary:edit"><li><a href="${ctx}/ordertaskpackagepaymentsummary/bizOrderTaskpackagePaymentSummary/form">付款单批次添加</a></li></shiro:hasPermission> --%>
	</ul>
	<form:form id="searchForm" modelAttribute="bizOrderTaskpackagePaymentSummary" action="${ctx}/ordertaskpackagepaymentsummary/bizOrderTaskpackagePaymentSummary/list" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<input id="employeeId" name="employeeId" type="hidden" value="${employeeId}"/>
		<ul class="ul-form">
			<li><label>门店：</label>
				<form:select path="storeId" class="input-medium needClear" id="storeId" onchange="getDepartments()">
					<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			
			<li><label>工程模式：</label>
				<c:if test="${empty gongcheng}">
					<form:select path="projectMode" id="projectMode" class="input-medium" disabled="true" onchange="getDepartments()">
						<form:option value="" label=""/>
						<form:options items="${fns:getDictList('package_project_mode')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
				<c:if test="${!empty gongcheng}">
					<form:select path="projectMode" id="projectMode" class="input-medium needClear" onchange="getDepartments()">
						<form:option value="" label=""/>
						<form:options items="${fns:getDictList('package_project_mode')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
			</li>
			
			<li><label>区域：</label>
				<form:select path="enginDepartId" class="input-medium needClear" id="enginDepartId">
					<form:option value="${bizOrderTaskpackagePaymentSummary.enginDepartId }" label="${bizOrderTaskpackagePaymentSummary.departmentName }" />
				</form:select>
			</li>
			<li><label>付款批次编号：</label>
				<form:input path="orderTaskpackagePaymentSummaryCode" htmlEscape="false" maxlength="14" class="input-medium"/>
			</li>
			<li><label>付款批次状态：</label>
				<form:select path="status" class="input-medium needClear">
					<form:option value="" label=""/>
					<form:options items="${fns:getSummaryStatusList('summary_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>操作人：</label>
				<form:input path="applyEmployeeName" htmlEscape="false" maxlength="14" class="input-medium"/>
			</li>
			<li><label>批次生成日期：</label>
				<input name="beginGeneratedDatetime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate needClear"
					value="<fmt:formatDate value="${bizOrderTaskpackagePaymentSummary.beginGeneratedDatetime}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/> - 
				<input name="endGeneratedDatetime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate needClear"
					value="<fmt:formatDate value="${bizOrderTaskpackagePaymentSummary.endGeneratedDatetime}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="btns"><input class="btn btn-primary clearBtn" type="button" value="清空"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>门店 </th>
				<th>工程模式</th>
				<th>区域 </th>
				<th>付款批次编号 </th>
				<th>批次生成日期</th>
				<th>付款单数量</th>
				<th>付款批次状态</th>
				<th>操作人</th>
				<shiro:hasPermission name="ordertaskpackagepaymentsummary:bizOrderTaskpackagePaymentSummary:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="bizOrderTaskpackagePaymentSummary">
			<tr>
				<td>
					${fns:getStoreLabel(bizOrderTaskpackagePaymentSummary.storeId, '')}
				</td>
				<td>${fns:getDictLabel(bizOrderTaskpackagePaymentSummary.projectMode, 'package_project_mode', '')}</td>
				<td>
					${bizOrderTaskpackagePaymentSummary.departmentName }
				</td>
				<td>
					${bizOrderTaskpackagePaymentSummary.orderTaskpackagePaymentSummaryCode }
				</td>
				<td>
					<fmt:formatDate value="${bizOrderTaskpackagePaymentSummary.generatedDatetime }" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					${bizOrderTaskpackagePaymentSummary.orderTaskpackagePaymentCount }
				</td>
				<td>
					${fns:getDictLabel(bizOrderTaskpackagePaymentSummary.status , 'summary_status', '')}
				</td>
				<td>
					${bizOrderTaskpackagePaymentSummary.applyEmployeeName }
				</td>
				<shiro:hasPermission name="ordertaskpackagepaymentsummary:bizOrderTaskpackagePaymentSummary:edit"><td>
					<c:if test="${bizOrderTaskpackagePaymentSummary.status == '10'}">
    					<%-- <a href="${ctx}/ordertaskpackagepaymentsummary/bizOrderTaskpackagePaymentSummary/approvePass?id=${bizOrderTaskpackagePaymentSummary.id}" onclick="return confirmx('您确认该批次审批通过吗？', this.href);">审核通过</a> --%>
						<a href="#" onclick="approvePass(this,${bizOrderTaskpackagePaymentSummary.id})">审核通过</a>
					</c:if>
					<c:if test="${bizOrderTaskpackagePaymentSummary.status == '10' || bizOrderTaskpackagePaymentSummary.status == '20'}">
    					<a href="javascript:void(0)" data="abolish" onclick="abolish('${bizOrderTaskpackagePaymentSummary.id}')">批次作废</a>
    				</c:if>
    				<a href="${ctx}/ordertaskpackagepaymentsummary/bizOrderTaskpackagePaymentSummary/exportProjectExcel?id=${bizOrderTaskpackagePaymentSummary.id}">导出工程excel</a>
    				<a href="${ctx}/ordertaskpackagepaymentsummary/bizOrderTaskpackagePaymentSummary/splitAndFinance?id=${bizOrderTaskpackagePaymentSummary.id}">拆分并导出财务excel</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	
	<div class="g-mask undis" id="abolishSummary">
		<div id="g-done_ask">
			<p class="refuse">请输入作废理由：</p>
			<input type="hidden" id="id"/>
			<textarea class="content" id="reason"></textarea>
			<p class="second">
				<a href="javascript:void(0)" onclick="noAbolish()">取消</a>
				<a href="javascript:void(0)" onclick="yesAbolish()">确认</a>
			</p>
		</div>
	</div>
	
	<div class="pagination">${page}</div>
</body>
</html>