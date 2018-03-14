<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>主材可申请安装日期处理页面</title>
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
								
								if('${bizOrderInstallPlanAdvanceApply.engineDepartId}' == data[v].engineDepartId){
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
		a{color:#2fa4e7;}
		.undis{display:none;}
		body {
		    background-color: #fff;
		    font-size: 16px;
		}
		
		body {
		    width: 100%;
		    height: 100%;
		    position: relative
		}
		
		 .Black {
		    position: fixed;
		    top: 0;
		    left: 0;
		    background: rgba(0, 0, 0, .6);
		    width: 100%;
		    height: 100%
		}
		
		.Black .tc_center {
		    padding: 15px;
		    position: absolute;
		    top: 50%;
		    left: 50%;
		    width: 560px;
		    height: 420px;
		    margin-left: -300px;
		    margin-top: -210px;
		    background: #fff;
		    color: #666
		}
		
		 .Black .tc_center h2 {
		    font-size: 20px;
		    text-align: center;
		    line-height: 40px
		}
		
		 .Black .tc_center .cen_t {
		    width: 100%
		}
		
		 .Black .tc_center .cen_t p {
		    line-height: 30px;
		    font-size: 16px;
		    text-align: center;
		    margin-bottom: 12px
		}
		
		 .Black .tc_center .cen_t p input {
		    width: 50%;
		    line-height: 30px;
		    padding-left: 10px;
		    border-radius: 3px;
		    border: #ccc solid 1px
		}
		
		 .Black .tc_center .cen_t .cen_btn {
		    position: absolute;
		    width: 100%;
		    bottom: 20px;
		    text-align: center;
		    line-height: 30px
		}
		
		 .Black .tc_center .cen_t .cen_btn span {
		    width: 120px;
		    display: inline-block;
		    text-align: center;
		    cursor: pointer;
		    border-radius: 3px
		}
		
		 .Black .tc_center .cen_t .cen_btn .btn_y {
		    background: #3da5ee;
		    color: #fff
		}
		
		 .Black .tc_center .cen_t .cen_btn .btn_n {
		    margin-left: 50px;
		    border: solid 1px #3da5ee;
		    color: #3da5ee
		}
		
		 .Black .tc_center .cen_t .cen_tex {
		    width: 90%;
		    margin-left: 5%;
		    font-size: 16px;
		        clear: both;
		}
		
		 .Black .tc_center .cen_t .cen_tex .span_l {
		    display: block;
		    line-height: 30px;
		    float: left;
		    width: 32%;
		    text-align: right;
		    vertical-align: middle
		}
		
		 .Black .tc_center .cen_t .cen_tex .span_r {
		    
		    text-align: left;
		    vertical-align: middle;
		    position: relative;
		    margin-bottom: 8px
		}
		
		 .Black .tc_center .cen_t .cen_tex .span_r input {
		    width: 90%;
		    background: 0 0
		}
		
		 .Black .tc_center .cen_t .cen_tex .span_r textarea {
		    color: #666;
		    width: 90%;
		    height: 200px;
		    padding: 5px 15px;
		    line-height: 20px;
		    font-size: 12px;
		    border: #ccc solid 1px;
		    border-radius: 3px
		}
		
		 .Black .tc_close {
		    background: url(../images/close.png) no-repeat;
		    background-size: 100% 100%;
		    width: 50px;
		    height: 50px;
		    position: absolute;
		    top: -10px;
		    right: -12px
		}
		


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
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/modules/orderinstallplanadvanceapply/web/bizOrderInstallPlanAdvanceApply/preInstallPlanAdvanceApplyList">主材可申请安装日期处理页面</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="bizOrderInstallPlanAdvanceApply" action="${ctx}/modules/orderinstallplanadvanceapply/web/bizOrderInstallPlanAdvanceApply/installPlanAdvanceApplyList" method="post" class="breadcrumb form-search">
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
			<li><label>处理状态：</label>
				<form:select path="dealStatus" class="input-medium needClear">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('deal_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
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
				<th>确认开工后可<br>申请安装日期</th>
				<th>项目经理申请时间</th>
				<th>处理状态</th>
				<th>处理人</th>
				<th>处理时间</th>
				<shiro:hasPermission name="orderinstallplanadvanceapply:bizOrderInstallPlanAdvanceApply:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="bizOrderInstallPlanAdvanceApply">
			<tr>
				<td>
					${bizOrderInstallPlanAdvanceApply.storeName}
				</td>
				<td>
					${bizOrderInstallPlanAdvanceApply.projectModeName}
				</td>
				<td>
					${bizOrderInstallPlanAdvanceApply.engineDepartName}
				</td>
				<td>
					${bizOrderInstallPlanAdvanceApply.orderNumber}
				</td>
				<td>
					${bizOrderInstallPlanAdvanceApply.customerName}
				</td>
				<td>
					${bizOrderInstallPlanAdvanceApply.itemManager}
				</td>
				<td>
					<fmt:formatDate type="date" value="${bizOrderInstallPlanAdvanceApply.actualStartDate }"/>
				</td>
				<td>
					${bizOrderInstallPlanAdvanceApply.installItemName}
				</td>
				<td>
					<fmt:formatDate type="date" value="${bizOrderInstallPlanAdvanceApply.oldPlanApplyDate }"/>
				</td>
				<td>
					<fmt:formatDate type="both" value="${bizOrderInstallPlanAdvanceApply.createDate }"/>
				</td>
				<td>
					${bizOrderInstallPlanAdvanceApply.dealStatusName}
				</td>
				<td>
					<c:if test="${not empty bizOrderInstallPlanAdvanceApply.dealEmployeeName}">
						${bizOrderInstallPlanAdvanceApply.dealEmployeeName}
					</c:if>
					<c:if test="${empty bizOrderInstallPlanAdvanceApply.dealEmployeeName}">
						<c:if test="${bizOrderInstallPlanAdvanceApply.updateBy.id eq 1}">
							系统管理员
						</c:if>
					</c:if>
				</td>
				<td>
					<fmt:formatDate type="both" value="${bizOrderInstallPlanAdvanceApply.delaTime }"/>
				</td>
				<td hidden="hidden">
					${bizOrderInstallPlanAdvanceApply.communityName }-${bizOrderInstallPlanAdvanceApply.buildNumber }-${bizOrderInstallPlanAdvanceApply.buildUnit }-${bizOrderInstallPlanAdvanceApply.buildRoom }
				</td>
				<shiro:hasPermission name="orderinstallplanadvanceapply:bizOrderInstallPlanAdvanceApply:edit"><td>
					<c:if test="${bizOrderInstallPlanAdvanceApply.dealStatus eq 1 }">
						<a onclick="deal(this,'${bizOrderInstallPlanAdvanceApply.id }')">处理</a>
					</c:if>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	
	
	<!-- 处理弹框 -->
	<div class="Black undis" id="dealAlert">
		<div class="tc_center">
			<h2>提示</h2>
			<div class="cen_t">
				<div class="cen_tex">
					<span class="span_l">工地信息：</span>
					<p class="span_r" id="orderAlert"></p>
				</div>
				<div class="cen_tex">
					<span class="span_l">客户姓名：</span>
					<p class="span_r" id="customerNameAlert"></p>
				</div>
				<div class="cen_tex">
					<span class="span_l">项目经理：</span>
					<p class="span_r" id="itemManagerAlert"></p>
				</div>
				<div class="cen_tex">
					<span class="span_l">申请日期：</span>
					<p class="span_r" id="applyDateAlert"></p>
				</div>
				<div class="cen_tex">
					<span class="span_l">可申请安装计划日期：</span>
					<p class="span_r" id="oldPlanApplyDateAlert"></p>
				</div>
				<div class="cen_tex" style="color: red;">
					是否同意项目经理提前申请主材安装。
				</div>
				<div class="cen_btn">
					<span class="btn_y" onclick="dealAlertYes()">同意</span>
					<span class="btn_n" onclick="dealAlertNo()">拒绝</span>
					<span class="btn_n" onclick="dealClose()">取消</span>
				</div>
			</div>
			<div class="tc_close"></div>
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
		function informBoxClose(){
			$("#informBox").hide();
		}
		function informBoxCloseTwo(){
			//提交表单
	    	$("#searchForm").submit();
		}
		function dealClose(){
			$("#dealAlert").hide();
		}
		
		//处理
		function deal(obj,id){
			
			idGlobal = id;
			
			var orderAlert;
	  		var customerNameAlert;
	  		var itemManagerAlert;
	  		var applyDateAlert;
	  		var oldPlanApplyDateAlert;
	  		
			var trObj = $(obj).parent().parent().find("td").each(function(){
	  			if($(this).index()==13){
	  				orderAlert = $(this).text();
	  			}
	  			if($(this).index()==4){
	  				customerNameAlert = $(this).text();
	  			}
	  			if($(this).index()==5){
	  				itemManagerAlert = $(this).text();
	  			}
	  			if($(this).index()==9){
	  				applyDateAlert = $(this).text();
	  			}
	  			if($(this).index()==8){
	  				oldPlanApplyDateAlert = $(this).text();
	  			}
	  			
			})
			
			$("#dealAlert").show();
			$("#orderAlert").text(orderAlert);
			$("#customerNameAlert").text(customerNameAlert);
			$("#itemManagerAlert").text(itemManagerAlert);
			$("#applyDateAlert").text(applyDateAlert);
			$("#oldPlanApplyDateAlert").text(oldPlanApplyDateAlert);
		}
		
		
		//拒绝
		function dealAlertNo(){
			$.ajax({
				url: "${ctx}/modules/orderinstallplanadvanceapply/web/bizOrderInstallPlanAdvanceApply/save_install_advance_apply_refuse",
	            type: "post",
	            data:{
	            		id:idGlobal
	            	},
	            success: function(data) {
	            	
	            	if(null!=data && data=="0"){
	            		$("#informBoxRemarkTwo").text("提前申请安装【拒绝】成功");
	            		$("#informBoxTwo").show();
	            	}else if(data=="1"){
	            		$("#informBoxRemark").text("提前申请已经处理，不可重复处理");
	            		$("#informBox").show();
	            	}else{
	            		$("#informBoxRemark").text("提前申请安装【拒绝】失败");
	            		$("#informBox").show();
	            	}
	            }
	    	})
		}
		
		//同意
		function dealAlertYes(){
			$.ajax({
				url: "${ctx}/modules/orderinstallplanadvanceapply/web/bizOrderInstallPlanAdvanceApply/save_install_advance_apply_agree",
	            type: "post",
	            data:{
	            		id:idGlobal
	            	},
	            success: function(data) {
	            	
	            	if(null!=data && data=="0"){
	            		$("#informBoxRemarkTwo").text("提前申请安装【同意】成功");
	            		$("#informBoxTwo").show();
	            	}else if(data=="2"){
	            		$("#informBoxRemark").text("提前申请已经处理，不可重复处理");
	            		$("#informBox").show();
	            	}else if(data=="3"){
	            		$("#informBoxRemark").text("确认开工后可申请安装日期为空，请及时联系相关人员");
	            		$("#informBox").show();
	            	}else{
	            		$("#informBoxRemark").text("提前申请安装【同意】失败");
	            		$("#informBox").show();
	            	}
	            }
	    	})
		}
	
	
	
	</script>
</body>
</html>