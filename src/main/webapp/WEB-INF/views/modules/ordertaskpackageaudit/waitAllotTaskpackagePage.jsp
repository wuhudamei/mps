<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>待分配工人任务包列表</title>
	<meta name="decorator" content="default"/>
	<meta content="*" name="Access-Control-Allow-Origin"><!--跨域请求  -->
	<script type="text/javascript">
		$(document).ready(function() {
			  $("#engineDepartId option").each(function(){ //遍历全部option
			       var txt = $(this).text(); //获取option的内容
			       var i =  $(this).attr("selected");
			       
			       if(txt == ''){
				        $(this).text("请选择...") //添加到数组中
				        } 
			       
			       
			       if(i == "selected"){
			    	   if(txt == ''){
			    		   $("#s2id_engineDepartId").find(".select2-chosen").text("请选择...");
			    	   }else{
			    		   $("#s2id_engineDepartId").find(".select2-chosen").text(txt);
			    	   } 
				        
			       }
			        
			       /*  
			        */
			    });
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
		 $(function() {
	           $("#checkAll").click(function() {
	                $('input[name="packageStateId"]').attr("checked",this.checked); 
	            });
	            var $status = $("input[name='packageStateId']");
	            $status.click(function(){
	                $("#checkAll").attr("checked",$status.length == $("input[name='packageStateId']:checked").length ? true : false);
	            });
	      });
		 $(function(){
			 $("#isZeroId").click(function() {
               	 $('input[name="isZero"]').attr("checked",this.checked); 
           	 });
           	 var $status = $("input[name='isZero']");
           	 $status.click(function(){
                $("#isZeroId").attr("checked",$status.length == $("input[name='isZero']:checked").length ? true : false);
             });
		 });
		 function allotWorker(packId,managerName){
				if(""==managerName ||null==managerName){
					alertx("订单分配项目经理后任务包才可以分配工人");
					return;
				}
				window.location.href="${ctx}/AllotWorkerGroup/allotWorkerGroup/list?packageId="+packId+"&turnpageflag=waitAllot";
			}
		 function findProjectMode(){
			 $("#engineDepartId").html("");
			 $("#s2id_engineDepartId .select2-chosen").html("");
			 $.ajax({
	            	url:'${ctx}/engdept/bizEngineeringDepartment/departmentList',
	            	type:'post',
					dataType:'json',
					data:{
						'storeId':$('#storeId').val(),
						'projectMode':$("#projectMode").val(),
						'employeeId':$('#employeeId').val()
					},
	                success : function(data) {
	                	if(data.length<1){
							$("#engineDepartId").append("<option value='' selected='selected' >请选择...</option>");
							$("#s2id_engineDepartId .select2-chosen").html("请选择...");
						} else {
							var html = "<option value=''>请选择...</option>";
							$("#s2id_engineDepartId .select2-chosen").html("请选择...");
							for(var i=0;i<data.length;i++){
								var sec = "";
								if('${orderVo.engineDepartId}' == data[i].value){
				            		$("#s2id_engineDepartId .select2-chosen").html(data[i].label);
				            		html += "<option value='" + data[i].value +"' selected='selected' >" + data[i].label + "</option>"
				            	}else{
				            		html += "<option value='" + data[i].value +"'>" + data[i].label + "</option>"
				            	}
							}
			        		$("#engineDepartId").append(html);
						}
	                }
	            });
		 }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/ordertaskpackageaudit/orderTaskpackageAudit/waitAllotTaskpackagePage">任务包列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="bizOrderTaskpackage" action="${ctx}/ordertaskpackageaudit/orderTaskpackageAudit/waitAllotTaskpackage" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>门店：</label>
				<form:select path="storeId" class="input-medium needClear" onchange="findProjectMode()">
					<form:option value="" label="请选择..."/>
					<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li>
				<li><label>工程模式：</label>
				<c:if test="${empty gongcheng}">
					<form:select path="projectMode" id="projectMode" class="input-medium" disabled="true" onchange="findProjectMode()">
						<form:option value="" label="请选择..."/>
						<form:options items="${fns:getDictList('package_project_mode')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
				<c:if test="${!empty gongcheng}">
					<form:select path="projectMode" id="projectMode" class="input-medium needClear" onchange="findProjectMode()">
						<form:option value="" label="请选择..."/>
						<form:options items="${fns:getDictList('package_project_mode')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
			</li>	
			<li><label>区域：</label>
				<form:select path="engineDepartId" class="input-medium needClear">
				
					<form:options items="${fns:getEngineListWithUserConditionsForBiddenChange()}"   itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>订单编号：</label>
				<form:input path="orderNumber" htmlEscape="false" maxlength="18" class="input-medium needClear"/>
			</li>
			
			<li><label>客户姓名：</label>
				<form:input path="customerName" htmlEscape="false" maxlength="18" class="input-medium needClear"/>
			</li>
			<li><label>项目经理：</label>
				<form:input path="itemCustomer" htmlEscape="false" maxlength="255" class="input-medium needClear"/>
			</li>
			<li><label>任务包编号：</label>
				<form:input path="orderTaskpackageCode" htmlEscape="false" maxlength="18" class="input-medium needClear"/>
			</li>
			<li><label>任务包名称：</label>
				<form:input path="packageName" htmlEscape="false" maxlength="18" class="input-medium needClear"/>
			</li>
			<li>
				<label>任务包状态：</label>
				<form:select path="packageStateId" class="input-medium needClear">
					<form:option value="" label="请选择..." />
					<form:option value="20" label="20-预算员审核通过 " />
					<form:option value="55" label="55-工人拒绝任务包" />
				</form:select>
				
				
				<%-- <input type="checkbox" name="packageStateId" value="20" <c:if test="${fns:checkIsExits(bizOrderTaskpackage.packageStateId,'20')}"> checked="checked"</c:if>/>20-预算员审核通过
				<input type="checkbox" name="packageStateId" value="50" <c:if test="${fns:checkIsExits(bizOrderTaskpackage.packageStateId,'50')}"> checked="checked"</c:if>/>50-已派单到工人
				<input type="checkbox" name="packageStateId" value="55" <c:if test="${fns:checkIsExits(bizOrderTaskpackage.packageStateId,'55')}"> checked="checked"</c:if>/>55-工人拒绝任务包 --%>
			</li>

			<li>
				<label>计划开工日期：</label>
				<input name="beginPlanStartdate" type="text" id="beginPlanStartdate" readonly="readonly" maxlength="20" class="input-medium Wdate needClear"
					value="<fmt:formatDate value="${bizOrderTaskpackage.beginPlanStartdate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'endPlanStartdate\')}',isShowClear:false});"/> - 
				<input name="endPlanStartdate" type="text" id="endPlanStartdate" readonly="readonly" maxlength="20" class="input-medium Wdate needClear"
					value="<fmt:formatDate value="${bizOrderTaskpackage.endPlanStartdate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'beginPlanStartdate\')}',isShowClear:false});"/>
			</li>
			<li class="btns" style="width:100px"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="btns"><input class="btn btn-primary clearBtn" type="button" value="清空"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>门店</th>
				<th>工程模式</th>
				<th>区域</th>
				<th>订单编号</th>
				<th>顾客</th>
				<th>项目经理</th>
				<th>任务包编号</th>
				<th>任务包名称</th>
				<th>任务包状态</th>
				<th>计划开工日期</th>
				<th>计划完工日期</th>
				<th>工人组长</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="taskpackage" varStatus="index">
			<tr>
				<td>
					${fns:getStoreLabel(taskpackage.storeId, '')}
				</td>
				<td>
					${fns:getDictLabel(taskpackage.projectMode, 'project_mode', '')}
				</td>
				<td>
					${fns:getElacLabel(taskpackage.engineDepartId, '')}
				</td>
				
				<td>
					${taskpackage.orderNumber }
				</td>
				<td>
					${taskpackage.customerMessage }-${taskpackage.customerName }
				</td>
				<td>
					${taskpackage.itemCustomer }
				</td>
				<td>
					${taskpackage.orderTaskpackageCode }
				</td>
				<td>
					${taskpackage.packageName }
				</td>
				<td>
					${taskpackage.packageStateId }-${taskpackage.packageStateName }
				</td>
				<td>
					<fmt:formatDate value="${taskpackage.planStartdate }" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					<fmt:formatDate value="${taskpackage.planEnddate }" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					${taskpackage.groupRealname }
				</td>
				<td><shiro:hasPermission name="scheduling:orderTaskpackage:edit">
					<a href="${ctx}/ordertaskpackageaudit/orderTaskpackageAudit/procedureDetail?id=${taskpackage.id}">查看详情</a>
					</br>
					<c:if test="${taskpackage.packageStateId == '20' || taskpackage.packageStateId =='55'}">
						<a href="#" onclick="allotWorker('${taskpackage.id}','${taskpackage.itemCustomer}')">分配工人</a>
					</c:if>
					</shiro:hasPermission>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination" align="right">${page}</div>	
</body>
</html>