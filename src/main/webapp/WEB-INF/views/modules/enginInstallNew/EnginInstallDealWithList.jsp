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
	<style>
		.undis{display:none;}
		.alertMask{position: fixed;top: 0;bottom: 0;left: 0;right: 0;background: rgba(0,0,0,.5);}
		.maskWrapper{width: 380px;border-radius: 4px;background: #fff;font-size: 16px;
		    position: fixed;top: 50%;left: 50%;transform: translate(-50%,-50%);}
		.col_3{color: #333}
		.col_6{color: #666;}
		.col_f{color: #fff;}
		.col_fdfcfa{color: #fdfcfa;}
		.col_0780ec{color: #0780ec;}
		.font28{font-size: 14px;}
		.font33{font-size: 16.5px;}
		.maskTit{height: 50px;line-height: 50px;text-align: center;}
		.maskContent{padding: 25px;border-top: 1px solid #ddd;border-bottom: 1px solid #ddd;line-height: 1.5em;}
		.maskBtns{width: 83%;margin: 0 auto;padding-bottom: 10px;padding-top: 10px;}
		.maskBtn{display: block;width: 60%;text-align: center;line-height: 38px;}
		.maskBtn{background: #0780ec;border-radius: 4px;display: block;width: 47.6%;
    		text-align: center;line-height: 38px;font-size:16px;margin: 0 auto;}
    		
	</style>
</head>
<body>
<c:set var="user" value="${fns:getUser()}"></c:set>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/enginInstallNew/enginInstallNew/preDealWithList">主材安装申请-已处理</a></li>
	
	</ul>
	<form:form id="searchForm" modelAttribute=	"enginInstallNew" action="${ctx}/enginInstallNew/enginInstallNew/dealWithList" method="post" class="breadcrumb form-search">
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
		 	<li><label>操作人：</label>
				<form:input path="operator" htmlEscape="false" maxlength="64" class="input-medium needClear"/>
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
			<li><label>转供应商时间：</label>
				<input name="beginSupplierOperaterDate" type="text" readonly="readonly" id="beginSupplierOperaterDate" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${enginInstallNew.beginSupplierOperaterDate}" pattern="yyyy-MM-dd HH:mm:ss "/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss ',maxDate:'#F{$dp.$D(\'endSupplierOperaterDate\')}',isShowClear:false});"/> &nbsp;至&nbsp;
					
				<input name="endSupplierOperaterDate" type="text" readonly="readonly" id="endSupplierOperaterDate" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${enginInstallNew.endSupplierOperaterDate}" pattern="yyyy-MM-dd HH:mm:ss "/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss ',minDate:'#F{$dp.$D(\'beginSupplierOperaterDate\')}',isShowClear:false});"/>
			</li>
			<li><label style="width: 110px;">供应商确认时间：</label>
				<input name="beginSupplierConfirmIntoDate" type="text" readonly="readonly" id="beginSupplierConfirmIntoDate" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${enginInstallNew.beginSupplierConfirmIntoDate}" pattern="yyyy-MM-dd "/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd ',maxDate:'#F{$dp.$D(\'endSupplierConfirmIntoDate\')}',isShowClear:false});"/> &nbsp;至&nbsp;
					
				<input name="endSupplierConfirmIntoDate" type="text" readonly="readonly" id="endSupplierConfirmIntoDate" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${enginInstallNew.endSupplierConfirmIntoDate}" pattern="yyyy-MM-dd "/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd ',minDate:'#F{$dp.$D(\'beginSupplierConfirmIntoDate\')}',isShowClear:false});"/>
			</li>
			
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="btns"><input class="btn btn-primary clearBtn" type="button" value="清空" onclick="clearCondition()"/></li>
			<li style="width: 100%">
				<span style="font-size:20px;color:#333;">材料部已转供应商数据：<span style="color:red;">${supplierCount }</span> 条</span>
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
				<th>供应商确认日期</th>
				<th>转供应商时间</th>
				<th>项目经理催促次数</th>
				<th>安装说明</th>
				<th>选材清单</th>
				<th>日志</th>
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
				<td><fmt:formatDate type="date" value="${install.supplierConfirmIntoDate }"/></td>
				<td><fmt:formatDate type="both" value="${install.supplierOperaterDate }"/></td>
				<td>${install.urgeCount }</td>
				<td>
					<a href="#" onclick="installExplain('${install.id}')">
							查看
					</a>
				</td>
				<td>
					<a href="${ctx}/bizmaterialchoicebill/bizMaterialsChoiceBill/order_materials_choice_bill_detail?orderId=${install.orderId}">
							查看
					</a>
				</td>
				<td>
				
					<a href="${ctx}/enginInstallNew/enginInstallNew/installLogOperation?installId=${install.id}&orderID=${install.id}">操作日志</a> 
					
					<a href="${ctx}/enginInstallNew/enginInstallNew/installLogUrge?installId=${install.id}&orderID=${install.id}">催促日志</a> 
				
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
	
            
            
	<div style="background:rgba(0,0,0,.4);z-index:9;" class="g-mask undis" id ="subReport">
		<div class="alertMask">
			<div class="maskWrapper">
				<p class="col_3 maskTit">通知</p>
				<div class="font28 col_6 maskContent" id="alertRemarks"></div>
				<div class="maskBtns clearfix">
					<a class="maskBtn font33 col_f"  onclick="sendMessage()" href="javascript:;">我知道了</a>
				</div>
			</div>
		</div>
	</div>
	
	
	
	<div class="pagination">${page}</div>
	<script type="text/javascript">
	
	
		var installIdGlobal;
		var orderIdGlobal;
	
		 //关闭 
	    function sendMessage(){
	    	$("#subReport").hide();
	    }
		 

//--------------------------------安装说明start-----------------------------------------------------------
		
		//安装说明
		function installExplain(installId){
	
			$.ajax({
				url: "${ctx }/enginInstallNewDeal/enginInstallNewDeal/installExplain",
	            type: "post",
	            data:{
	            		installId:installId
	            	},
	            success: function(data) {
	            	
	            	if(null!=data && data=="1"){
	            		$("#alertRemarks").text("安装项ID为空");
	            		$("#subReport1").show();
	            	}else if(data=="2"){
	            		$("#alertRemarks").text("该安装项不需要安装说明");
	            		$("#subReport").show();
	            	}else{
	            		$("#alertRemarks").text(data);
	            		$("#subReport").show();
	            	}
	            	
	            }
	    	})
	
	
		}


//--------------------------------安装说明end-----------------------------------------------------------







	</script>
</body>
</html>