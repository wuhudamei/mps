<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>结算单管理</title>
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
					'employeeId':$('#employeeId').val(),
				},
				success:function(data){
					if(data == null){
						$("#enginDepartId").html('');
					} else {
						var html = "<option value=''></option>";
						for(var i=0;i<data.length;i++){
							var sec = "";
							if('${ bizOrderTaskpackageSettlementVo.enginDepartId }' == data[i].value){
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
		<li class="active"><a href="${ctx}/ordertaskpackagesettlement/bizOrderTaskpackageSettlementVo/settleOrderList">结算单列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="bizOrderTaskpackageSettlementVo" action="${ctx}/ordertaskpackagesettlement/bizOrderTaskpackageSettlementVo/settleOrderList" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<input id="employeeId" name="employeeId" type="hidden" value="${employeeId}"/>
		<ul class="ul-form">
		
			<li><label>门店：</label>
				<form:select path="storeid" class="input-medium needClear" onchange="getDepartments()" id="storeId">
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
					<form:option value="${bizOrderTaskpackageSettlementVo.enginDepartId }" label="${bizOrderTaskpackageSettlementVo.departmentName }" />
				</form:select>
			</li>
			<li><label>订单编号：</label>
				<form:input path="orderNo" htmlEscape="false" maxlength="64" class="input-medium needClear"/>
			</li>
			<li><label>任务包编号：</label>
				<form:input path="taskPackageNo" htmlEscape="false" maxlength="64" class="input-medium needClear"/>
			</li>
			<li><label>结算单编号：</label>
				<form:input path="settlementNo" htmlEscape="false" maxlength="64" class="input-medium needClear"/>
			</li>
			<li><label>任务包状态：</label>
				<form:select path="packageStateid" class="input-medium needClear">
					<form:option value="" label="" />
					<form:options items="${fns:getTaskPackageStatusList('taskpackage_status')}" itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
			</li>
			<li><label>项目经理：</label>
				<form:input path="itemManager" htmlEscape="false" maxlength="64" class="input-medium needClear"/>
			</li>
			<li><label>组长姓名：</label>
				<form:input path="groupRealname" htmlEscape="false" maxlength="64" class="input-medium needClear"/>
			</li>
			<li><label>客户姓名：</label>
				<form:input path="customerName" htmlEscape="false" maxlength="64" class="input-medium needClear"/>
			</li>
			<li><label>验收日期：</label>
				<input name="beginCheckDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate needClear"
					value="<fmt:formatDate value="${bizOrderTaskpackageSettlement.beginCheckDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/> - 
				<input name="endCheckDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${bizOrderTaskpackageSettlement.endCheckDate}" pattern="yyyy-MM-dd HH:mm:ss needClear"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
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
				<th>门店</th>
				<th>工程模式</th>
				<th>区域</th>
				<th>工人确认薪酬时间</th>
				<th>评价完成时间</th>
				<th>结算单编号</th>
				<th>订单编号</th>
				<th>任务包编号</th>
				<th>客户信息</th>
				<th>项目经理</th>
				<th>工人组长</th>
				<th>任务包名称</th>
				<th>任务包状态</th>
				<th>结算总金额</th>
				<th>工人组结算金额</th>
				<th>项目经理材料结算金额</th>
				<th>验收日期</th>
				<th>质检验收</th>
				<shiro:hasPermission name="ordertaskpackagesettlement:bizOrderTaskpackageSettlementVo:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="bizOrderTaskpackageSettlementVo">
			<tr>
				<td>
					${fns:getStoreLabel(bizOrderTaskpackageSettlementVo.storeid, '')}
				</td>
				<td>${fns:getDictLabel(bizOrderTaskpackageSettlementVo.projectMode, 'package_project_mode', '')}</td>
				<td>
					${ bizOrderTaskpackageSettlementVo.departmentName }
				</td>
				<td>
					<fmt:formatDate value="${bizOrderTaskpackageSettlementVo.confirmSalaryTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${bizOrderTaskpackageSettlementVo.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${bizOrderTaskpackageSettlementVo.settlementNo}
				</td>
				<td>
					${bizOrderTaskpackageSettlementVo.orderNo}
				</td>
				<td>
					${bizOrderTaskpackageSettlementVo.taskPackageNo}
				</td>
				<td>
					${bizOrderTaskpackageSettlementVo.customerMessage}-${bizOrderTaskpackageSettlementVo.customerName}
				</td>
				<td>
					${bizOrderTaskpackageSettlementVo.itemManager}
				</td>
				<td>
					${bizOrderTaskpackageSettlementVo.groupRealname}
				</td>
				<td>
					${bizOrderTaskpackageSettlementVo.orderTaskpackageName}
				</td>
				<td>
					${fns:getDictLabel(bizOrderTaskpackageSettlementVo.packageStateid, 'taskpackage_status', '')}
				</td>
				<td>
					${bizOrderTaskpackageSettlementVo.settlementAmount}
				</td>
				<td>
					${bizOrderTaskpackageSettlementVo.workerGroupSettleAmount}
				</td>
				<td>
					${bizOrderTaskpackageSettlementVo.pmMaterialsSettleAmount}
				</td>
				<td>
					<fmt:formatDate value="${bizOrderTaskpackageSettlementVo.checkDate}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
				   <a href="${ctx}/pqc/checkDetails/checkDetails/detailsList2?orderId=${bizOrderTaskpackageSettlementVo.orderId}">验收详情</a>
				</td>
				<shiro:hasPermission name="ordertaskpackagesettlement:bizOrderTaskpackageSettlementVo:edit"><td>
					<a href="${ctx}/ordertaskpackagesettlement/bizOrderTaskpackageSettlementVo/details?id=${bizOrderTaskpackageSettlementVo.id}">详情</a>
					<c:if test="${bizOrderTaskpackageSettlementVo.packageStateid =='120'}" >
						<%-- <a href="${ctx}/ordertaskpackagesettlement/bizOrderTaskpackageSettlementVo/updateTaskpackageStatus?orderTaskpackageId=${bizOrderTaskpackageSettlementVo.orderTaskpackageId}&status=140&statusName=结算员审核通过" onclick="return confirmx('确认通过该结算单吗？', this.href)">
							通过
						</a> --%>
						<a href="#" id="approvePass" onclick="approvePass('${bizOrderTaskpackageSettlementVo.orderTaskpackageId}')">
							通过
						</a>
						<a href="#" onclick="reject('${bizOrderTaskpackageSettlementVo.orderTaskpackageId}')">
							驳回
						</a>
					</c:if>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	
	<div class="g-mask undis" id="refuseSalary">
		<div id="g-done_ask">
			<p class="refuse">请输入驳回理由：</p>
			<textarea class="content" id="reason"></textarea>
			<p class="second">
				<a href="javascript:void(0)" onclick="noReject()">取消</a>
				<a href="javascript:void(0)" onclick="yesReject()">确认</a>
			</p>
		</div>
	</div>
	
	<div class="pagination">${page}</div>
	
	<script type="text/javascript">

		var orderTaskpackageId;
		
		function reject(id){
			orderTaskpackageId = id;
			$("#refuseSalary").removeClass("undis");
		}
		function yesReject(){
			var reason = $("#reason").val();
			$("#reason").val("");
			$("#refuseSalary").addClass("undis");
			$.ajax({
				url:'${ctx}/ordertaskpackagesettlement/bizOrderTaskpackageSettlementVo/updateTaskpackageStatus',
				type:'post',
				dataType : 'json',
				data:{
					'orderTaskpackageId':orderTaskpackageId,
					'status':130,
					'reason':reason
				},
				success:function(data){
					if(data == "1"){
						alertx("请不要重复提交");
					}else if(data == "0"){
						alertx("提交成功！");
						$("#searchForm").submit();
					}
				}
			});
			/* window.location.href = "${ctx}/ordertaskpackagesettlement/bizOrderTaskpackageSettlementVo/updateTaskpackageStatus?orderTaskpackageId="+orderTaskpackageId+"&status=130"+"&reason="+reason; */
		}
		function noReject(){
			$("#reason").val("");
			$("#refuseSalary").addClass("undis");
		}
		
		function approvePass(id){
			top.$.jBox.confirm("您确认该批次审批通过吗？","系统提示",function(v,h,f){
				if(v=="ok"){
					$("#approvePass").removeAttr("href");
					$("#approvePass").removeAttr("onclick");
					$.ajax({
						url:'${ctx}/ordertaskpackagesettlement/bizOrderTaskpackageSettlementVo/updateTaskpackageStatus',
						type:'post',
						dataType : 'json',
						data:{
							'orderTaskpackageId':id,
							'status':140
						},
						success:function(data){
							if(data == "1"){
								alertx("请不要重复提交");
							}else if(data == "0"){
								alertx("提交成功！");
								$("#searchForm").submit();
							}
						}
					});
					/* window.location.href="${ctx}/ordertaskpackagesettlement/bizOrderTaskpackageSettlementVo/updateTaskpackageStatus?orderTaskpackageId="+id+"&status=140"; */
				}
			},{buttonsFocus:1});
			top.$('.jbox-body .jbox-icon').css('top','55px');			
		}
	
	</script>
</body>
</html>