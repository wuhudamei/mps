<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>订单安装项问题管理</title>
	<meta name="decorator" content="default"/>
	
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/modules/popUp/css/base.css"/> 
 	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/calcRootFontSize.js"></script>
 	
	<script type="text/javascript">
		$(document).ready(function() {
			queryInstallItemProblemType();
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
		
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
		
		
		function queryInstallItemProblemType(){
			
			$("#problemTypeId").html('');
			var businessType = "1";
			$.ajax({
				type : 'POST',
		        dataType : 'json',
		        url : '${ctx}/bizorderinstallitemproblem/bizOrderInstallItemProblem/queryInstallItemProblemTypeList',
		        data : {
		            'storeId' : $("#storeId").val(),
		            'projectMode' : $("#projectMode").val(),
		            'businessType' : businessType,
		        },
		        success : function(data){
		        	if(data == null){
		        		$("#problemTypeId").append('');
		        	}else{
		        		var html = "<option value=''></option>";
		        		for(var i=0;i<data.length;i++){
			            	
			            	if('${bizOrderInstallItemProblemVo.problemTypeId}' == data[i].value){
								$("#s2id_problemTypeId .select2-chosen").html(data[i].label);
								html = html + "<option value='"+data[i].value+"' selected='selected'>"+data[i].label+"</option>";
							}else{
								html = html + "<option value='"+data[i].value+"'>"+data[i].label+"</option>";
							}
			            	
		        		}
		        		html+="";
		        		$("#problemTypeId").append(html);
		        	}
		        }
			});
			
			
			
			var html2='<option value=""></option>';
			var html3='<option value=""></option>';
			var storeId =$("#storeId").val();
			var projectModeId=$("#projectMode").val();
			if (storeId!="" && projectModeId!="" && undefined!=storeId && undefined!=projectModeId) {
				
				$.ajax({
					url:"${ctx}/bizengininstall/bizEnginInstall/findInstallItemByStoreIdAndProjectMode?storeId="+storeId+"&projectModeId="+projectModeId,					
					success: function(data){
						
						if(null!=data&&data.length>0){
					
							for (var v = 0; v < data.length; v++) {
								
								if(data[v].isOn == 1){
									//启用
									if('${bizOrderInstallItemProblemVo.projectInstallItemId}' == data[v].id){
										$("#s2id_projectInstallItemId .select2-chosen").html(data[v].installItemName);
										html2 = html2 + "<option value='"+data[v].id+"' selected='selected'>"+data[v].installItemName+"</option>";
									}else{
										html2 = html2 + "<option value='"+data[v].id+"'>"+data[v].installItemName+"</option>";
									}
								}else{
									//停用
									if('${bizOrderInstallItemProblemVo.projectInstallItemIdStop}' == data[v].id){
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
			
				
		}
		//根据门店和工程模式动态加载
		function queryInstallItemProblemTypeTwo(){
			
			$("#problemTypeId").html('');
			var businessType = "1";
			$.ajax({
				type : 'POST',
		        dataType : 'json',
		        url : '${ctx}/bizorderinstallitemproblem/bizOrderInstallItemProblem/queryInstallItemProblemTypeList',
		        data : {
		            'storeId' : $("#storeId").val(),
		            'projectMode' : $("#projectMode").val(),
		            'businessType' : businessType,
		        },
		        success : function(data){
		        	if(data == null){
		        		$("#problemTypeId").append('');
		        	}else{
		        		var html = "<option value=''></option>";
		        		for(var i=0;i<data.length;i++){
			            	
							html = html + "<option value='"+data[i].value+"'>"+data[i].label+"</option>";
			            	
		        		}
		        		html+="";
		        		$("#problemTypeId").append(html);
		        	}
		        }
			});
			
			
			
			var html2='<option value=""></option>';
			var html3='<option value=""></option>';
			var storeId =$("#storeId").val();
			var projectModeId=$("#projectMode").val();
			if (storeId!="" && projectModeId!="" && undefined!=storeId && undefined!=projectModeId) {
				
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
		    color: #666;
		    overflow-Y:scroll;
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
		    font-size: 14px;
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
		    /* position: absolute; */
		    width: 100%;
		    /* bottom: 20px; */
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
		    width: 28%;
		    text-align: right;
		    vertical-align: middle
		}
		
		 .Black .tc_center .cen_t .cen_tex .span_r {
		    
		    text-align: left;
		    vertical-align: middle;
		    position: relative;
		    margin-bottom: 8px;
		    float: none;
    		overflow: hidden;
		}
		
		 .Black .tc_center .cen_t .cen_tex .span_r input {
		    width: 90%;
		    background: 0 0
		}
		
		 .Black .tc_center .cen_t .cen_tex .span_r textarea {
		    color: #666;
		    width: 90%;
		    height: 120px;
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
		 .pic {
		    background: #fff;
		}
		
		 .pic textarea {
		    padding-left: 0.92rem;
		    width: 100%;
		    height: 8rem;
		    color: #333;
		    line-height: 2rem;
		    font-size: 1.4rem;
		}
		
		 .pic .span_pic{
			text-align:right;
			width: 18%;
			font-size: 16px;
			margin-bottom:5px;
		}
		 .pic p {
		    padding-left: 0.92rem;
		    padding-right: 0.92rem;
		    line-height: 2.75rem;
		    height: 2.75rem;
		    font-size: 1.5rem;
		    color: #cacaca;
		}
		
		 .pic p span {
		    padding: 2px;
		    float: right;
		    color: #bcbbbb;
		}
		
		 .pic .Fol {
		    height: 200px;
		    overflow: auto;
		    padding-right: 0.92rem;
		}
		
		 .pic .Fol img {
		    margin-bottom: 3px;
		    margin-right: 1px;
		    width: 24.5%;
		    height: 7rem;
		    float: left;
		}
		
		 .pic .Fol span {
		    display: inline-block;
		    width: 25%;
		    height: 7rem;
		    background: url(../images/zhaop_06.png) no-repeat;
		    background-size: 100% 100%;
		}
		
		 .pic .Fol span .Up {
		    width: 100%;
		    height: 100%;
		    opacity: 0;
		}
		.Big {
		    position: absolute;
		    top: -112px;
		    left: 50%;
		    margin-left: -200px;
		    width: 400px !important;
		    height: 300px !important;
		    z-index: 99;
		}
		.black_small{
		    display:none;
		    position:absolute;
		    top: 0;
		    left:0;
		    width: 100%;
		    height: 100%;
		    background:rgba(0,0,0,0.9)
		
		}
		.black_small img{
		    position:absolute;
		    top: 8%;
		    left: 10%;
		    width: 80%;
		    height: 75%;
		}
		.black_small .cen_btn{
		    width: 100%;
		    position:absolute;
		    bottom:5%;
		    left:15px;
		    text-align:center;
		}
		.black_small .cen_btn span {
		    width: 120px;
		    display: inline-block;
		    line-height:30px;
		    text-align: center;
		    cursor: pointer;
		    border-radius: 3px
		}
		
		.black_small .btn_y {
		    background: #3da5ee;
		    color: #fff
		}
		
		.black_small .btn_n {
		    margin-left: 50px;
		    border: solid 1px #3da5ee;
		    color: #3da5ee
		}
		.del_img{
		    position: absolute;
		    bottom: 5%;
		    right: 50%;
		    width: 3rem;
		    height: 3rem;
		    margin-right: -1.5rem;
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
		<li class="active"><a href="${ctx}/bizorderinstallitemproblem/bizOrderInstallItemProblem/list2">订单安装项问题列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="bizOrderInstallItemProblemVo" action="${ctx}/bizorderinstallitemproblem/bizOrderInstallItemProblem/list2Last" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>门店：</label>
				<c:if test="${empty storeDropEnable}">
					<form:select path="storeId" class="input-medium" disabled="true" onchange="queryInstallItemProblemTypeTwo()">
						<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
				<c:if test="${!empty storeDropEnable}">
					<form:select path="storeId" class="input-medium needClear" onchange="queryInstallItemProblemTypeTwo()">
						<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
			</li>
			<li><label>工程模式：</label>
				<c:if test="${empty gongcheng}">
					<form:select path="projectMode" class="input-medium" disabled="true" onchange="queryInstallItemProblemTypeTwo()">
						<form:option value="" label=""/>
						<form:options items="${fns:getDictList('project_mode')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
				<c:if test="${!empty gongcheng}">
					<form:select path="projectMode" class="input-medium needClear" onchange="queryInstallItemProblemTypeTwo()">
						<form:option value="" label=""/>
						<form:options items="${fns:getDictList('project_mode')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
					
			</li>
			<li><label>问题分类:</label>
				<form:select path="problemTypeId" class="input-medium needClear" id="problemTypeId">
					<form:option value="${bizOrderInstallItemProblemVo.problemTypeId }" label="${bizOrderInstallItemProblemVo.problemTypeName }" />
				</form:select> 
			</li>
			<li><label>状态：</label>
				<form:select path="status" class="input-medium needClear">
					<form:option value="" label=""/>
					<form:options items="${fns:getInstallProblemStatusList('install_item_problem_status')}"   itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
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
				<form:input path="customerName" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>项目经理：</label>
				<form:input path="itemManager" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>提交时间 ：</label>
				<input name="beginCreateDate" type="text" id="beginCreateDate" readonly="readonly" maxlength="20" class="input-medium Wdate needClear"
					value="<fmt:formatDate value="${bizOrderInstallItemProblemVo.beginCreateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'endCreateDate\')}',isShowClear:false});"/> - 
				<input name="endCreateDate" type="text" id="endCreateDate" readonly="readonly" maxlength="20" class="input-medium Wdate needClear"
					value="<fmt:formatDate value="${bizOrderInstallItemProblemVo.endCreateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'beginCreateDate\')}',isShowClear:false});"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="btns"><input class="btn btn-primary clearBtn" type="button" value="清空" onclick="clearCondition()"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>门店</th>
				<th>提交时间</th>
				<th>状态</th>
				<th>小区</th>
				<th>客户</th>
				<th>项目经理</th>
				<th>安装项名称</th>
				<th>问题分类</th>
				<th>影响工期天数</th>
				<th>描述</th>
				<th>照片</th>
				<th>选材清单</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="bizOrderInstallItemProblem">
			<tr>
				<td>
					${bizOrderInstallItemProblem.storeName}
				</td>
				<td>
					<fmt:formatDate value="${bizOrderInstallItemProblem.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${bizOrderInstallItemProblem.statusName}
				</td>
				<td>
					${bizOrderInstallItemProblem.communityName}-${bizOrderInstallItemProblem.buildNumber}-${bizOrderInstallItemProblem.buildUnit}-${bizOrderInstallItemProblem.buildRoom}
				</td>
				<td>
					${bizOrderInstallItemProblem.customerName}</br>${bizOrderInstallItemProblem.customerPhone}
				</td>
				<td>
					${bizOrderInstallItemProblem.itemManager}</br>${bizOrderInstallItemProblem.itemManagerPhone}
				</td>
				<td>
					${bizOrderInstallItemProblem.installItemName}
				</td>
				<td>
					${bizOrderInstallItemProblem.problemTypeName}
				</td>
				<td>
					<c:if test="${bizOrderInstallItemProblem.isDelay == '1'}">
						${bizOrderInstallItemProblem.delayDays}天
					</c:if>
					<c:if test="${bizOrderInstallItemProblem.isDelay == '0'}">
						0天
					</c:if>
				</td>
				<td>
					${bizOrderInstallItemProblem.problemDescribe}
				</td>
				<td>
<%-- 					<a href="${ctx}/bizorderinstallitemproblem/bizOrderInstallItemProblem/photo?id=${bizOrderInstallItemProblem.id}">查看</a> --%>
					<a href="#" data-toggle="modal" data-target="#myModal-photo"   onclick="ajaxGetImgs ('${bizOrderInstallItemProblem.id}')">查看</a>
		
				</td>
				<td>
					<a href="${ctx}/bizmaterialchoicebill/bizMaterialsChoiceBill/order_materials_choice_bill_detail?orderId=${bizOrderInstallItemProblem.orderId}">
							查看
					</a>
				</td>
				<td>
					<c:if test="${bizOrderInstallItemProblem.status == '30'}">
						<a href="#" onclick="showDeal(this,'${bizOrderInstallItemProblem.id}')">处理</a>
					</c:if>
					<a href ="${ctx}/bizorderinstallitemproblem/bizOrderInstallItemProblem/detail?id=${bizOrderInstallItemProblem.id}">详情</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	



	<div class="Black undis" id="onCreateTaskpackage">
		<form id="urgeForm">
			<div class="tc_center">
				<h2 id="orderAddress"></h2>
	            <div class="cen_t">
	            	<div class="cen_tex">
	            		<span class="span_l"> 安装项名称：</span>
	                    <p class="span_r" id="installItemName"></p>
	                </div>
	            	<div class="cen_tex">
	            		<span class="span_l"> 问题分类：</span>
	                    <p class="span_r" id="problemTypeName"></p>
	                </div>
	            	<div class="cen_tex">
	            		<span class="span_l"> 描述：</span>
	                    <p class="span_r" id="problemDescribe"></p>
	                </div>
	                <div class="cen_tex">
						<span class="span_l">回复：</span>
						<p class="span_r">
							<textarea id="problemSolveNotes" maxlength="50" name="problemSolveNotes"></textarea>
						</p>
					</div>
	                <div class="cen_btn">
	                	<span class="btn_y" onclick="yes()"> 提交 </span>
	                    <span class="btn_n" onclick="no()"> 取消 </span>
	                </div>
	            </div>
	        </div>
		</form>
    </div>
    
    
    
    
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
	<div style="background:rgba(0,0,0,.4);z-index:9;" class="g-mask undis" id ="subReport1">
		<div class="alertMask">
			<div class="maskWrapper">
				<p class="col_3 maskTit">通知</p>
				<div class="font28 col_6 maskContent">问题上报审核成功</div>
				<div class="maskBtns clearfix">
					<a class="maskBtn font33 col_f"  onclick="sendMessage1()" href="javascript:;">我知道了</a>
				</div>
			</div>
		</div>
	</div>





	<div class="pagination">${page}</div>
	
	
	
		<link href="${ctxStatic}/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="${ctxStatic}/vendor/bootstrap-table/bootstrap-table.min.css" rel="stylesheet" type="text/css" />
    <link href="${ctxStatic}/vendor/bootstrap-datetimepicker/css/datetimepicker.css" rel="stylesheet">
    <link href="${ctxStatic}/modules/popUp/css/style.css" rel="stylesheet" type="text/css" />
    <link href="${ctxStatic}/modules/popUp/css/swiper.css" rel="stylesheet" type="text/css" />
	
	
	
    <script type="text/javascript" src="${ctxStatic}/vendor/jquery/jquery-2.2.4.min.js"></script>
    <script type="text/javascript" src="${ctxStatic}/vendor/bootstrap/js/bootstrap.min.js" charset="UTF-8"></script>
    <script type="text/javascript" src="${ctxStatic}/vendor/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
    <script src="${ctxStatic}/vendor/jquery/swiper.min.js"></script>
    
    
		
	    <!-- 照片弹层 -->
    <div class="modal _large fade disN" id="myModal-photo" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"></button>
                    <h4 class="modal-title text-center" id="myModalLabel">图片预览</h4>
                </div>
                <div class="modal-body">
                    <div class="swiper-container swiper-item01" id="swiper-item01">
                        <div class="swiper-wrapper clearfix" id="inputId">
<!--                             <a href="http://www.baidu.com" class="swiper-slide img-01"></a> -->
                    
                        </div>
                        <div class="swiper-button-next"></div>
                        <div class="swiper-button-prev"></div>
                        <div class="swiper-pagination swiper-pagination-fraction">
                            <span class="swiper-pagination-current">1</span> / <span class="swiper-pagination-total">10</span>
                        </div>
                    </div>
                    <!--/.swiper-container-->
                </div>
            </div>
        </div>
    </div>
	
	
	

	    <script type="text/javascript">
        $("#myModal-manage").on("shown.bs.modal", function() {
            $(".form_datetime2").datetimepicker({
                format: "dd MM yyyy - hh:ii"
            });
        });

        $("#myModal-photo").on("shown.bs.modal", function() {
            var mySwiper01 = new Swiper('#swiper-item01', {
//                 autoplay: 5000, //可选选项，自动滑动
                speed: 800,
                loop: true, //可选选项，开启循环
                paginationClickable: true,
                nextButton: '.swiper-button-next',
                prevButton: '.swiper-button-prev',
                pagination: '.swiper-pagination',
                paginationType: 'fraction'
            });
        });
		
				 function ajaxGetImgs(installID,orderId){
				 // 	 alert(installID+":"+orderId);
	            $.ajax({
	                type: 'POST',
	                dataType: 'json',
	                url: '${ctx}/bizorderinstallitemproblem/bizOrderInstallItemProblem/ajaxPhoto',
	                data: {
	                	id:installID,
	                	orderID:orderId
	                },
	                success: function (data) {
						
						  	var modelList = data.mapObject;
		                    if (null!==modelList && modelList.length !== 0) {
// 		                        '<input  type="hidden"  name="bOrContPros[1].packagename" value="' + packName + '"></input>'
		                        var ainput='';
		                        for (var i = 0; i < modelList.length; i++) {
		                         ainput +=	 '<a class="swiper-slide " ><img src="' + modelList[i].picUrl + '" ></a>';
		                        }
		                        $("#inputId").html(ainput);
		                    }else{
		                        var ainput='';
		                         ainput ='<a class="swiper-slide " ><img src="${ctxStatic}/images/nopic.jpg" ></a>';
		                        $("#inputId").html(ainput);
	                    }
						
		    			
	                }
	            })
	        	
	        }

		
		
		
		
    </script>
	
	
	
	<script type="text/javascript">
	
		var problemIdGlobal;
		//关闭 
	    function sendMessage(){
	    	$("#subReport").hide();
	    }
	    function sendMessage1(){
	    	//提交表单
	    	$("#searchForm").submit();
	    }
	    
	    
	  	//工程安装问题上报审核
		function showDeal(obj,problemId){
	  		
			problemIdGlobal=problemId;
			
	  		var orderAddress;
	  		var installItemName;
	  		var problemTypeName;
	  		var problemDescribe;
	  		
			var trObj = $(obj).parent().parent().find("td").each(function(){
		  			if($(this).index()==3){
		  				orderAddress = $(this).text();
		  			}
		  			if($(this).index()==6){
		  				installItemName = $(this).text();
		  			}
		  			if($(this).index()==7){
		  				problemTypeName = $(this).text();
		  			}
		  			if($(this).index()==9){
		  				problemDescribe = $(this).text();
		  			}
			})
				
	  		
			$("#onCreateTaskpackage").show();
			$("#orderAddress").text(orderAddress);
			$("#installItemName").text(installItemName);
			$("#problemTypeName").text(problemTypeName);
			$("#problemDescribe").text(problemDescribe); 
			
		}
	    
		function no (){
			$("#problemSolveNotes").val("");
			$("#onCreateTaskpackage").hide();	
		}
		
		
		function yes(){
			var problemSolveNotes = $("#problemSolveNotes").val();
			
			if(null==problemSolveNotes || problemSolveNotes==""){
				$("#alertRemarks").text("请输入回复内容");
           		$("#subReport").show();
				return false;
			}
			
			$.ajax({
				url: "${ctx}/bizorderinstallitemproblem/wallAndFloor/problem/update_problem_ajax",
	            type: "post",
	            data:{
	            		problemId:problemIdGlobal,
	            		problemSolveNotes:problemSolveNotes
	            	},
	            success: function(data) {
	            	
	            	$("#problemSolveNotes").val("");
	    			$("#onCreateTaskpackage").hide();	
	    			
	            	if(null!=data && data=="0"){
	            		$("#subReport1").show();
	            	}else if(data=="1"){
	            		$("#alertRemarks").text("问题上报ID为空");
	            		$("#subReport").show();
	            	}else if(data=="2"){
	            		$("#alertRemarks").text("问题上报处理回复内容为空");
	            		$("#subReport").show();
	            	}else if(data=="3"){
	            		$("#alertRemarks").text("该问题已处理");
	            		$("#subReport").show();
	            	}else if(data=="4"){
	            		$("#alertRemarks").text("当前登录人未登录");
	            		$("#subReport").show();
	            	}else if(data=="5"){
	            		$("#alertRemarks").text("更新问题上报状态失败");
	            		$("#subReport").show();
	            	}else{
	            		$("#alertRemarks").text("保存问题上报日志失败");
	            		$("#subReport").show();
	            	}
	            	
	            }
	    	})
			
		}
		
		
	    
	
	</script>
</body>
</html>