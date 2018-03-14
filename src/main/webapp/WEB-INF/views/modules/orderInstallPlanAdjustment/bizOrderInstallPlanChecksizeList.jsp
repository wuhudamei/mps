<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>主材可申请复尺日期查询</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			findEngineList();
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
		
		//回显区域
		function findEngineList(){
			var html3 = '<option value=""></option>';
			var storeId = $("#storeId").val();
			var projectModeValue = $("#projectMode").val();
			if (storeId=="" ||projectModeValue=="" ||undefined==storeId ||undefined==projectModeValue) {
				return;
			}
			//根据门店个,工程模式    动态加载工程区域
			$.ajax({

					url : "${ctx}/order/order/findEngineDepartmentBystoreIdAndProjectMode?storeId="
							+ storeId + "&projectModeValue=" + projectModeValue,
					type : "get",
					success : function(data) {
						if (null!= data && data.length > 0) {

							for (var v = 0; v < data.length; v++) {
								
								if('${orderInstallPlanAdjustment.engineDepartId}' == data[v].engineDepartId){
									$("#s2id_engineDepartId .select2-chosen").html(data[v].engineDepartName);
									html3 = html3 + "<option value='"+data[v].engineDepartId+"' selected='selected'>"+data[v].engineDepartName+"</option>";
								}else{
									html3 = html3 + "<option value='"+data[v].engineDepartId+"'>"+data[v].engineDepartName+"</option>";
								}
								
							}
							
							$("#engineDepartId").html(html3);
						} else {
							$("#engineDepartId").html(html3);
						}

					}

				});		
		}
		//动态加载区域
		function findEngineListTwo(){
			var html3 = '<option value=""></option>';
			var storeId = $("#storeId").val();
			var projectModeValue = $("#projectMode").val();
			if (storeId=="" ||projectModeValue=="" ||undefined==storeId ||undefined==projectModeValue) {
				return;
			}
			//根据门店个,工程模式    动态加载工程区域
			$.ajax({

					url : "${ctx}/order/order/findEngineDepartmentBystoreIdAndProjectMode?storeId="
							+ storeId + "&projectModeValue=" + projectModeValue,
					type : "get",
					success : function(data) {
						if (null!= data && data.length > 0) {

							for (var v = 0; v < data.length; v++) {
								html3 +='<option value="'+data[v].engineDepartId+'">'+data[v].engineDepartName+'</option>' 
							}
							
							$("#engineDepartId").html(html3);
						} else {
							$("#engineDepartId").html(html3);
						}

					}

				});		
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
    	.twoBtns .maskBtn:first-child{background: #0780ec;border-radius: .1rem;float: left;}
		.twoBtns .maskBtn:last-child{border: 1px solid #0780ec;box-sizing: border-box;border-radius: .1rem;float: right;background: #fff;}			
	</style>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/modules/orderInstallPlanAdjustment/web/orderInstallPlanAdjustment/preInstallPlanQueryChecksizeList">主材可申请复尺日期查询</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="orderInstallPlanAdjustment" action="${ctx}/modules/orderInstallPlanAdjustment/web/orderInstallPlanAdjustment/installPlanQueryChecksizeList" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>门店：</label> 
				<form:select path="storeId" class="input-medium needClear"  onchange="findEngineListTwo()">
					<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>工程模式：</label>
				<c:if test="${empty gongcheng}">
					<form:select path="projectMode" class="input-medium" disabled="true" onchange="findEngineListTwo()">
						<form:option value="" label=""/>
						<form:options items="${fns:getDictList('project_mode')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
				<c:if test="${!empty gongcheng}">
					<form:select path="projectMode" class="input-medium needClear" onchange="findEngineListTwo()">
						<form:option value="" label=""/>
						<form:options items="${fns:getDictList('project_mode')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
			</li>
			<li><label>区域：</label>
				<form:select path="engineDepartId" class="input-medium needClear">
				</form:select>
			</li>
			<li><label>订单编号：</label>
				<form:input path="orderNumber" htmlEscape="false" maxlength="100" class="input-medium needClear"/>
			</li>
			<li><label>客户姓名：</label>
				<form:input path="customerName" htmlEscape="false" maxlength="16" class="input-medium needClear"/>
			</li>
			<li><label>项目经理：</label>
				<form:input path="itemManager" htmlEscape="false" maxlength="11" class="input-medium needClear"/>
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
				<th>订单编号</th>
				<th>客户姓名</th>
				<th>项目经理</th>
				<th>确认开工日期</th>
				<th>安装项名称</th>
				<th>确认开工后可<br>申请复尺日期</th>
				<shiro:hasPermission name="orderInstallPlanAdjustment:orderInstallPlanAdjustment:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="orderInstallPlanAdjustment">
			<tr>
				<td>
					${orderInstallPlanAdjustment.storeName}
				</td>
				<td>
					${orderInstallPlanAdjustment.projectModeName}
				</td>
				<td>
					${orderInstallPlanAdjustment.engineDepartName}
				</td>
				<td>
					${orderInstallPlanAdjustment.orderNumber}
				</td>
				<td>
					${orderInstallPlanAdjustment.customerName}
				</td>
				<td>
					${orderInstallPlanAdjustment.itemManager}
				</td>
				<td>
					<fmt:formatDate type="date" value="${orderInstallPlanAdjustment.actualStartDate }"/>
				</td>
				<td>
					${orderInstallPlanAdjustment.installItemName}
				</td>
				<td>
					<fmt:formatDate type="date" value="${orderInstallPlanAdjustment.allowApplyChecksizeDate }"/>
				</td>
				<shiro:hasPermission name="orderInstallPlanAdjustment:orderInstallPlanAdjustment:edit"><td>
					<a onclick="deal(this,'${orderInstallPlanAdjustment.id }')">修改可申请日期</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	
	
	<!-- 修改可申请日期弹框 -->
	<div style="background:rgba(0,0,0,.4);z-index:9;" class="g-mask undis" id ="dealAlert">
		<div class="alertMask">
			<div class="maskWrapper">
				<p class="col_3 maskTit">提示</p>
				<div class="font28 col_6 maskContent">
					可申请复尺日期：
					<input id="newPlanApplyDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate content"
				onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
				</div>
				<div class="maskBtns clearfix twoBtns">
					<a class="maskBtn font33 col_fdfcfa" onclick="dealAlertSure()">确定</a>
					<a class="maskBtn font33 col_0780ec" onclick="dealAlertClose()">取消</a>
				</div>
			</div>
		</div>
	</div>
	<div style="background:rgba(0,0,0,.4);z-index:9;" class="g-mask undis" id ="informBox">
		<div class="alertMask">
			<div class="maskWrapper">
				<p class="col_3 maskTit">通知</p>
				<div class="font28 col_6 maskContent" id="informBoxRemark"></div>
				<div class="maskBtns clearfix">
					<a class="maskBtn font33 col_f"  onclick="informBoxClose()">我知道了</a>
				</div>
			</div>
		</div>
	</div>
	<div style="background:rgba(0,0,0,.4);z-index:9;" class="g-mask undis" id ="informBoxTwo">
		<div class="alertMask">
			<div class="maskWrapper">
				<p class="col_3 maskTit">通知</p>
				<div class="font28 col_6 maskContent" id="informBoxRemarkTwo"></div>
				<div class="maskBtns clearfix">
					<a class="maskBtn font33 col_f"  onclick="informBoxCloseTwo()">我知道了</a>
				</div>
			</div>
		</div>
	</div>
	
	
	<div class="pagination">${page}</div>
	
	
	<script type="text/javascript">
	
		var idGlobal;
		
		//提示弹框
		function dealAlertClose(){
			$("#dealAlert").hide();
		}
		function informBoxClose(){
			$("#informBox").hide();
		}
		function informBoxCloseTwo(){
			//提交表单
	    	$("#searchForm").submit();
		}
		
		//处理
		function deal(obj,id){
			
			idGlobal = id;
			
			var newPlanApplyDate;
			var trObj = $(obj).parent().parent().find("td").each(function(){
	  			if($(this).index()==8){
	  				newPlanApplyDate = $(this).text();
	  			}
			})
			$("#dealAlert").show();
			$("#newPlanApplyDate").val(newPlanApplyDate.replace(/(^\s*)|(\s*$)/g, ""));
			
			
		}
		
		
		//同意
		function dealAlertSure(){
			var newPlanApplyDate  = $("#newPlanApplyDate").val();
			if(newPlanApplyDate == ""){
				$("#informBoxRemark").text("请输入可申请复尺日期");
        		$("#informBox").show();
        		return;
			}
			$.ajax({
				url: "${ctx}/modules/orderInstallPlanAdjustment/web/orderInstallPlanAdjustment/save_order_install_plan_checksize_date_apply",
	            type: "post",
	            data:{
	            		id:idGlobal,
	            		newPlanApplyDate:newPlanApplyDate
	            	},
	            success: function(data) {
	            	
	            	if(null!=data && data=="0"){
	            		$("#informBoxRemarkTwo").text("可申请复尺日期修改成功");
	            		$("#informBoxTwo").show();
	            	}else if(data=="2"){
	            		$("#informBoxRemark").text("您刚刚该安装项的可申请复尺日期，请耐心等待五分钟后再次操作");
	            		$("#informBox").show();
	            	}else if(data=="3"){
	            		$("#informBoxRemark").text("确认开工后可申请复尺日期为空，请及时联系相关人员");
	            		$("#informBox").show();
	            	}else if(data=="4"){
	            		$("#informBoxRemark").text("请输入可申请复尺日期");
	            		$("#informBox").show();
	            	}else if(data=="5"){
	            		$("#informBoxRemark").text("请修改可申请复尺日期");
	            		$("#informBox").show();
	            	}else{
	            		$("#informBoxRemark").text("可申请复尺日期修改失败");
	            		$("#informBox").show();
	            	}
	            }
	    	})
		}
	
	
	
	</script>
</body>
</html>