<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>安装项查询</title>
	<meta name="decorator" content="default"/>
	
	<script type="text/javascript">
		$(document).ready(function() {
			findInstallName();
		});
		
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
		
		
		//清空查询条件
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
		
		
		 var format = function(time, format){
		        var t = new Date(time);
		        var tf = function(i){return (i < 10 ? '0' : '') + i};
		        return format.replace(/yyyy|MM|dd|HH|mm|ss/g, function(a){
		            switch(a){
		                case 'yyyy':
		                    return tf(t.getFullYear());
		                    break;
		                case 'MM':
		                    return tf(t.getMonth() + 1);
		                    break;
		                case 'mm':
		                    return tf(t.getMinutes());
		                    break;
		                case 'dd':
		                    return tf(t.getDate());
		                    break;
		                case 'HH':
		                    return tf(t.getHours());
		                    break;
		                case 'ss':
		                    return tf(t.getSeconds());
		                    break;
		            }
		        })
		    }
		 
		
		function findInstallName(){
			var html2='<option value=""></option>';
			var html3='<option value=""></option>';
			var storeId = $("#storeId").val();
			var projectModeId = $("#projectMode").val();
			
			if (storeId=="" ||projectModeId=="" ||undefined==storeId ||undefined==projectModeId) {
				return;
			}
			$.ajax({
				url:"${ctx}/bizengininstall/bizEnginInstall/findInstallItemByStoreIdAndProjectMode?storeId="+storeId+"&projectModeId="+projectModeId,					
				success: function(data){
					
					if(null!=data&&data.length>0){
				
						for (var v = 0; v < data.length; v++) {
							
							if(data[v].isOn == 1){
								//启用
								if('${enginInstallNew.projectInstallItemId}' == data[v].id){
									$("#s2id_projectInstallItemId .select2-chosen").html(data[v].installItemName);
									html2 = html2 + "<option value='"+data[v].id+"' selected='selected'>"+data[v].installItemName+"</option>";
								}else{
									html2 = html2 + "<option value='"+data[v].id+"'>"+data[v].installItemName+"</option>";
								}
							}else{
								//停用
								if('${enginInstallNew.projectInstallItemIdStop}' == data[v].id){
									$("#s2id_projectInstallItemIdStop .select2-chosen").html(data[v].installItemName);
									html3 = html3 + "<option value='"+data[v].id+"' selected='selected'>"+data[v].installItemName+"</option>";
								}else{
									html3 = html3 + "<option value='"+data[v].id+"'>"+data[v].installItemName+"</option>";
								}
								
							}
						}
						
						$("#projectInstallItemId").html(html2);
						$("#projectInstallItemIdStop").html(html3);
					} else {
						$("#projectInstallItemId").html(html2);
						$("#projectInstallItemIdStop").html(html3);
					}
				}
			})
		} 

		//根据门店和工程模式 动态加载
		function findInstallNameTwo(){
			var html2='<option value=""></option>';
			var html3='<option value=""></option>';
			var storeId = $("#storeId").val();
			var projectModeId = $("#projectMode").val();
			
			if (storeId=="" ||projectModeId=="" ||undefined==storeId ||undefined==projectModeId) {
				return;
			}
			$.ajax({
				url:"${ctx}/bizengininstall/bizEnginInstall/findInstallItemByStoreIdAndProjectMode?storeId="+storeId+"&projectModeId="+projectModeId,					
				success: function(data){
					
					if(null!=data&&data.length>0){
				
						for (var v = 0; v < data.length; v++) {
							
							if(data[v].isOn == 1){
								//启用
								html2 = html2 + "<option value='"+data[v].id+"'>"+data[v].installItemName+"</option>";
							}else{
								//停用
								html3 = html3 + "<option value='"+data[v].id+"'>"+data[v].installItemName+"</option>";
								
							}
						}
						
						$("#projectInstallItemId").html(html2);
						$("#projectInstallItemIdStop").html(html3);
					} else {
						$("#projectInstallItemId").html(html2);
						$("#projectInstallItemIdStop").html(html3);
					}
				}
			})
		}  
		 
	</script>
</head>
<body>
<c:set var="user" value="${fns:getUser()}"></c:set>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/enginInstallNew/enginInstallNew/preRejectedList">主材安装申请-已驳回</a></li>
	
	</ul>
	<form:form id="searchForm" modelAttribute=	"enginInstallNew" action="${ctx}/enginInstallNew/enginInstallNew/rejectedList" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>门店：</label> 
				<form:select path="storeId" class="input-medium needClear"  onchange="findInstallNameTwo()">
					<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>工程模式：</label>
				<c:if test="${empty gongcheng}">
					<form:select path="projectMode" class="input-medium" disabled="true" onchange="findInstallNameTwo()">
						<form:option value="" label=""/>
						<form:options items="${fns:getDictList('project_mode')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
				<c:if test="${!empty gongcheng}">
					<form:select path="projectMode" class="input-medium needClear" onchange="findInstallNameTwo()">
						<form:option value="" label=""/>
						<form:options items="${fns:getDictList('project_mode')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
					
			</li>
			<li><label>订单编号：</label>
				<form:input path="orderNumber" htmlEscape="false" maxlength="64" class="input-medium needClear"/>
			</li>
			<li><label>项目经理：</label>
				<form:input path="managerName" htmlEscape="false" maxlength="64" class="input-medium needClear"/>
			</li>
			<li><label style="width:140px;">安装项名称（停用）：</label>
				<form:select path="projectInstallItemIdStop" class="input-medium needClear">
				</form:select>
			</li>
			<li><label>安装项名称：</label>
				<form:select path="projectInstallItemId" class="input-medium needClear">
				</form:select>
			</li>
			<li><label>客户姓名：</label>
				<form:input path="customerName" htmlEscape="false" maxlength="16" class="input-medium needClear"/>
			</li>
		 	<li><label>驳回类型：</label> 
				<form:select path="rejectedId" class="input-medium needClear">
						<form:option value="" label=""/>
						<form:options items="${fns:getDictList('install_reject_reason_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
			</li>
			<li><label>操作人：</label>
				<form:input path="operator" htmlEscape="false" maxlength="16" class="input-medium needClear"/>
			</li>
			<li><label style="width: 120px;">项目经理申请日期：</label>
				<input name="beginApplyIntoCreateDatetime" type="text" id="beginApplyIntoCreateDatetime" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${enginInstallNew.beginApplyIntoCreateDatetime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'endApplyIntoCreateDatetime\')}',isShowClear:false});"/> &nbsp;至&nbsp;
					
				<input name="endApplyIntoCreateDatetime" type="text" id="endApplyIntoCreateDatetime" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${enginInstallNew.endApplyIntoCreateDatetime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'beginApplyIntoCreateDatetime\')}',isShowClear:false});"/>
			</li>
			<li><label>期望进场日期：</label>
				<input name="beginApplyIntoDate" type="text" readonly="readonly" id="beginApplyIntoDate" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${enginInstallNew.beginApplyIntoDate}" pattern="yyyy-MM-dd "/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd ',maxDate:'#F{$dp.$D(\'endApplyIntoDate\')}',isShowClear:false});"/> &nbsp;至&nbsp;
					
				<input name="endApplyIntoDate" type="text" readonly="readonly" id="endApplyIntoDate" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${enginInstallNew.endApplyIntoDate}" pattern="yyyy-MM-dd "/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd ',minDate:'#F{$dp.$D(\'beginApplyIntoDate\')}',isShowClear:false});"/>
			</li>
			<li><label>驳回时间：</label>
				<input name="beginRejectedOperaterDate" type="text" id="beginRejectedOperaterDate" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${enginInstallNew.beginRejectedOperaterDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'endRejectedOperaterDate\')}',isShowClear:false});"/> &nbsp;至&nbsp;
					
				<input name="endRejectedOperaterDate" type="text" id="endRejectedOperaterDate" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${enginInstallNew.endRejectedOperaterDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'beginRejectedOperaterDate\')}',isShowClear:false});"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="btns"><input class="btn btn-primary clearBtn" type="button" value="清空" onclick="clearCondition()"/></li>
			<li style="width: 100%">
				<span style="font-size:20px;color:#333;">项目经理已驳回：<span style="color:red;">${rejectedCount }</span> 条 </span>
			</li> 
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>门店</th>
				<th>工程模式</th>
				<th>订单编号</th>
				<th>申请日期</th>
				<th>客户姓名</th>
				<th>客户地址</th>
				<th>项目经理</th>
				<th>项目经理手机号</th>
				<th>安装项名称</th>
				<th>期望进场时间</th>
				<th>驳回类型</th>
				<th>驳回备注</th>
				<th>驳回时间</th>
				<th>日志</th>
				<th>选材清单</th>
				<th>操作人</th>
				<th>状态</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>	
			<c:forEach items="${page.list}" var="install" >
			<tr>
				<td>${install.storeName}</td>
				<td>${install.projectModeName}</td>
				<td>${install.orderNumber}</td>
				<td><fmt:formatDate type="both" value="${install.applyIntoCreateDatetime }"/></td>
				<td>${install.customerName }</td>
				<td>${install.communityName}-${install.buildNumber}-${install.buildUnit}-${install.buildRoom}</td>
				<td>${install.managerName }</td>
				<td>${install.managerPhone }</td>
				<td>${install.installItemName }</td>
				<td><fmt:formatDate type="date" value="${install.applyIntoDate }"/></td>
				<td>${install.rejectedIdName }</td>
				<td>${install.rejectedRemarks }</td>
				<td><fmt:formatDate type="both" value="${install.rejectedOperaterDate }"/></td>
				<td>
				
					<a href="${ctx}/enginInstallNew/enginInstallNew/installLogOperation?installId=${install.id}&orderID=${install.id}">操作日志</a> 
					
					<a href="${ctx}/enginInstallNew/enginInstallNew/installLogUrge?installId=${install.id}&orderID=${install.id}">催促日志</a> 
				
				</td>
				<td>
					<a href="${ctx}/bizmaterialchoicebill/bizMaterialsChoiceBill/order_materials_choice_bill_detail?orderId=${install.orderId}">
							查看
					</a>
				</td>
				<td>${install.operator}</td>
				<td>${install.installStatusName}</td>
				<td>
				
					<a href="${ctx}/enginInstallNew/enginInstallNew/installDetails?installId=${install.id}&orderID=${install.orderId}">详情</a>
					
				</td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
	
            
	
	
	<div class="pagination">${page}</div>
</body>
</html>