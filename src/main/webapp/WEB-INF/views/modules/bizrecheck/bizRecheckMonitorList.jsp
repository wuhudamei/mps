<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta name="decorator" content="default"/>
	<title>复尺未转单监控</title>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#allCheck").click(function () {
	            if ($(this).attr("checked")) { // 全选 
	                $("input[name='recheckType']").each(function () {
	                    $(this).attr("checked", true);
	                });
	            } else { // 取消全选 
	                $("input[name='recheckType']").each(function () {
	                    $(this).attr("checked", false);
	                });
	            }
	        });	
		});
		
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
		
		//按门店
		function nextValue(){
			//$("#nodeIndex").empty();
			//获取当前选中的值
			var storeIdSelected = $("#storeId option:selected").text();
			var storeIdValue = $("#storeId").val();
			//alert("storeIdSelected="+storeIdSelected +"\t" +"storeIdValue=="+storeIdValue);
			if(storeIdValue){
				$.ajax({
					url : "${ctx}/constructionschedule/bizConstructionSchedule/getByStoreIdList",
		        	type : "post",
		        	data : {storeId : storeIdValue},
		        	success : function(data){
		        		var htmls = "";//<option value='' selected='true'></option>
				   		for(var i = 0; i < data.length; i++){
				   			htmls += "<option value='"+data[i].sort+"'>"+data[i].constructionScheduleName+"</option>";
				   		}
				   		$("#nodeIndex").append(htmls);
					  }
				});
			}
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
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/bizorderrecheckmonitor/bizOrderRecheckMonitor/preList">复尺未转单监控列表</a></li>
		<%-- <shiro:hasPermission name="projectbulletin:projectBulletin:edit"><li><a href="${ctx}/projectbulletin/projectBulletin/form">订单添加</a></li></shiro:hasPermission> --%>
	</ul>
	<form:form id="searchForm" modelAttribute="bizOrderRecheckMonitor" action="${ctx}/bizorderrecheckmonitor/bizOrderRecheckMonitor/list" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<%-- <li><label>门店：</label> 
				<form:select path="storeId" class="input-medium needClear" id="storeId">
					<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
			</li> --%>
			<li><label>门店：</label>
				<c:if test="${empty storeDropEnable}">
					<form:select path="storeId" class="input-medium" disabled="true">
						<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
				<c:if test="${!empty storeDropEnable}">
					<form:select path="storeId" class="input-medium needClear">
						<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
			</li>
			<li><label>工程模式：</label>
				<c:if test="${empty gongcheng}">
					<form:select path="projectMode" class="input-medium" disabled="true">
						<form:option value="" label=""/>
						<form:options items="${fns:getDictList('project_mode')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
				<c:if test="${!empty gongcheng}">
					<form:select path="projectMode" class="input-medium needClear">
						<form:option value="" label=""/>
						<form:options items="${fns:getDictList('project_mode')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
			</li>
			<li><label>订单编号：</label>
				<form:input path="orderNumber" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>项目经理：</label>
				<form:input path="itemManager" htmlEscape="false" maxlength="64" class="input-medium needClear"/>
			</li>
			<li><label>客户姓名：</label>
				<form:input path="customerName" htmlEscape="false" maxlength="16" class="input-medium"/>
			</li>
			<li><label>复尺申请日期：</label>
				<input name="appRecheckStart" type="text" id="appRecheckStart" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${bizOrderRecheckMonitor.appRecheckStart}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'appRecheckEnd\')}',isShowClear:false});"/> &nbsp;至&nbsp;

				<input name="appRecheckEnd" type="text" id="appRecheckEnd" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${bizOrderRecheckMonitor.appRecheckEnd}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'appRecheckStart\')}',isShowClear:false});"/>
			</li>
			<li><label>预计安装日期：</label>
				<input name="appRecheckPlanInstallStart" type="text" id="appRecheckPlanInstallStart" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${bizOrderRecheckMonitor.appRecheckPlanInstallStart}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'appRecheckPlanInstallEnd\')}',isShowClear:false});"/> &nbsp;至&nbsp;
					
				<input name="appRecheckPlanInstallEnd" type="text" id="appRecheckPlanInstallEnd" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${bizOrderRecheckMonitor.appRecheckPlanInstallEnd}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'appRecheckPlanInstallStart\')}',isShowClear:false});"/>
			</li>
			<li><label>复尺项目：</label>
				<input type="checkbox" id="allCheck"/>全选
				<form:checkboxes path="recheckType" items="${fns:getDictList('complex_content')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				<%-- <form:select path="recheckType" class="input-medium needClear">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('complex_content')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select> --%>
			</li>
			<%-- <li><label>复尺状态：</label>
				<form:select path="recheckStatus" class="input-medium needClear">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('recheck_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li> --%>
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
				<th>工程模式</th>
				<th>订单编号</th>
				<th>顾客</th>
				<th>项目经理</th>
				<th>复尺项目</th>
				<th>复尺申请时间</th>
				<th>预计安装日期</th>
				<th>状态</th>
				<th>图片</th>
				<shiro:hasPermission name="bizorderrecheck:bizOrderRecheck:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="p">
			<tr>
				<td>
					<!-- 门店列表 -->
					${fns:getStoreLabel(p.storeId, '')}
				</td>
				<td>${fns:getDictLabel(p.projectMode, 'project_mode','')}</td>
				<td>
					${p.orderNumber}
				</a></td>
				<td>
					${p.communityName}-${p.buildNumber}-${p.buildUnit}-${p.buildRoom}-${p.customerName}  
				</td>
				<td>
					${p.managerName}
				</td>
				<td>
					${fns:getDictLabel(p.recheckType, 'complex_content', '')}
				</td>
				<td>
					<fmt:formatDate type="both" value="${p.appRecheckDate }"/>
				</td>
				<td>
					<fmt:formatDate type="date" value="${p.recheckPlanInstallDate }"/>
				</td>
				<td>
					${fns:getDictLabel(p.recheckStatus, 'recheck_status', '')}
				</td>
				<td>
					<c:if test="${p.recheckType == '1' }">
						<a href="#" data-toggle="modal" data-target="#myModal-photo"   onclick="ajaxGetImgs ('${p.recheckID}','201')">查看</a>
		
<%-- 						<a href="${ctx}/bizbusinesspic/bizBusinessPic/showRecheckMonitorPic?recheckID=${p.recheckID}&type=201"> --%>
<!-- 							查看 -->
<!-- 						</a> -->
					</c:if>
					<c:if test="${p.recheckType == '2' }">
						<a href="#" data-toggle="modal" data-target="#myModal-photo"   onclick="ajaxGetImgs ('${p.recheckID}','202')">查看</a>
<%-- 						<a href="${ctx}/bizbusinesspic/bizBusinessPic/showRecheckMonitorPic?recheckID=${p.recheckID}&type=202"> --%>
<!-- 							查看 -->
<!-- 						</a> -->
					</c:if>
					<c:if test="${p.recheckType == '3' }">
						<a href="#" data-toggle="modal" data-target="#myModal-photo"   onclick="ajaxGetImgs ('${p.recheckID}','203')">查看</a>
<%-- 						<a href="${ctx}/bizbusinesspic/bizBusinessPic/showRecheckMonitorPic?recheckID=${p.recheckID}&type=203"> --%>
<!-- 							查看 -->
<!-- 						</a> -->
					</c:if>
					<c:if test="${p.recheckType == '4' }">
						<a href="#" data-toggle="modal" data-target="#myModal-photo"   onclick="ajaxGetImgs ('${p.recheckID}','204')">查看</a>
<%-- 						<a href="${ctx}/bizbusinesspic/bizBusinessPic/showRecheckMonitorPic?recheckID=${p.recheckID}&type=204"> --%>
<!-- 							查看 -->
<!-- 						</a> -->
					</c:if>
					<c:if test="${p.recheckType == '5' }">
						<a href="#" data-toggle="modal" data-target="#myModal-photo"   onclick="ajaxGetImgs ('${p.recheckID}','205')">查看</a>
<%-- 						<a href="${ctx}/bizbusinesspic/bizBusinessPic/showRecheckMonitorPic?recheckID=${p.recheckID}&type=205"> --%>
<!-- 							查看 -->
<!-- 						</a> -->
					</c:if>
					<c:if test="${p.recheckType == '6' }">
						<a href="#" data-toggle="modal" data-target="#myModal-photo"   onclick="ajaxGetImgs ('${p.recheckID}','206')">查看</a>
<%-- 						<a href="${ctx}/bizbusinesspic/bizBusinessPic/showRecheckMonitorPic?recheckID=${p.recheckID}&type=206"> --%>
<!-- 							查看 -->
<!-- 						</a> -->
					</c:if>
				</td>
				<shiro:hasPermission name="bizorderrecheck:bizOrderRecheck:view"><td>
					<%-- <c:if test="${p.recheckStatus == '0'}">
    					<a href="${ctx}/bizorderrecheck/bizOrderRecheck/updateByRecheckStatus?recheckID=${p.recheckID}">接收</a>
    				</c:if> --%>
    				<a href="${ctx}/bizorderrecheck/bizOrderRecheck/recheckMonitorDetail?recheckID=${p.recheckID}&type=${p.recheckType }&orderID=${p.id }">详情</a>
					<%-- <a href="${ctx}/projectbulletin/projectBulletin/delete?id=${projectBulletin.id}" onclick="return confirmx('确认要删除该订单吗？', this.href)">删除</a> --%>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
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
	                url: '${ctx}/bizbusinesspic/bizBusinessPic/ajaxShowRecheckPic',
	                data: {
	                	recheckID:installID,
	                	type:orderId
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
	
	
	
	
</body>
</html>