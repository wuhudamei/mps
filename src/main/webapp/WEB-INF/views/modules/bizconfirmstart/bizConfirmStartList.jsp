<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>确认开工查询</title>
	<meta name="decorator" content="default"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Worker/css/globalUtil.css"/>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/history.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			
			findEngineListByStoreIdAndProjectMode();
			
			$("#allCheck").click(function () {
	            if ($(this).attr("checked")) { // 全选 
	                $("input[name='installStatus']").each(function () {
	                    $(this).attr("checked", true);
	                });
	            } else { // 取消全选 
	                $("input[name='installStatus']").each(function () {
	                    $(this).attr("checked", false);
	                });
	            }
	        });	
			
			
			/* findEngineListByStoreIdAndProjectMode(); */
		
			/* var v = $("#engineDepartIdInput").val();
			alert(v)
			("#engineDepartId option[value='"+v+"']").attr("selected", true) */
			
			
		});
	
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
		
		
		 function findEngineListByStoreIdAndProjectMode(){
			 
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
		
		function exportExcel(){
			var txt = $("#pageSize").val();
		 	
			/*  $.ajax({
		             type: "get",
		             url: "${ctx}/bizconfirmstart/bizConfirmStart/exportExcel?pageSize="+txt,
		             dataType: "json",
		             success: function(data){
			  					if(data){
			  						$("#searchForm").attr("action", "${ctx}/bizconfirmstart/bizConfirmStart/exportExcel");
			  						$("#searchForm").submit();
			  						$("#searchForm").attr("action", "${ctx}/bizconfirmstart/bizConfirmStart/list");
			  					}else{
			  						alert("请查询后在导出！")
			  					}
			    
		                   }
		         }); */
			if(txt != ''){
				
				$("#searchForm").attr("action", "${ctx}/bizconfirmstart/bizConfirmStart/exportExcel");
				$("#searchForm").submit();
				$("#searchForm").attr("action", "${ctx}/bizconfirmstart/bizConfirmStart/list");
			}else{
				alertx("请查询后在导出！");
			}
				
		}
		
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/bizconfirmstart/bizConfirmStart/preList">确认开工列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="bizConfirmStartOrder" action="${ctx}/bizconfirmstart/bizConfirmStart/list" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>门店：</label> 
				<form:select path="storeId" class="input-medium needClear" id="storeId" onchange="findEngineListByStoreIdAndProjectMode()">
					<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
			</li>
		<%-- 	<li><label>门店：</label>
				<form:select path="storeId" class="input-medium needClear" onchange="findEngineListByStoreIdAndProjectMode()">
						<form:option value=""></form:option>
						<c:forEach items="${storeList}" var = "store">
							<form:option value="${store.value }">${store.label }</form:option>
						</c:forEach>
				</form:select>
			</li> --%>
			<li><label>工程模式：</label>
				<c:if test="${empty gongcheng}">
					<form:select path="projectMode" class="input-medium" disabled="true">
						<form:option value="" label=""/>
						<form:options items="${fns:getDictList('project_mode')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
				<c:if test="${!empty gongcheng}">
					<form:select path="projectMode" class="input-medium needClear" onchange="findEngineListByStoreIdAndProjectMode()">
						<form:option value="" label=""/>
						<form:options items="${fns:getDictList('project_mode')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
			</li>
			<li><label>区域：</label>
				<form:select path="engineDepartId" class="input-medium needClear">
					<form:options items="${fns:getEngineListWithUserConditions()}"  itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<input id = "engineDepartIdInput" style="display: none;" value="${bizConfirmStartOrder.engineDepartId }" >
			</li>
			<%-- <li><label>安装项名称：</label>
				<form:input path="installItemName" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li> --%>
			<li><label>订单编号：</label>
				<form:input path="orderNumber" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>客户姓名：</label>
				<form:input path="customerName" htmlEscape="false" maxlength="16" class="input-medium"/>
			</li>
			<li><label>项目经理：</label>
				<form:input path="managerRealName" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>实际开工日期：</label>
				<input name="actualStartDateBegin" type="text" id="actualStartDateBegin" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${bizConfirmStartOrder.actualStartDateBegin}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'actualStartDateEnd\')}',isShowClear:false});"/> &nbsp;至&nbsp;
					
				<input name="actualStartDateEnd" type="text" id="actualStartDateEnd" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${bizConfirmStartOrder.actualStartDateEnd}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'actualStartDateBegin\')}',isShowClear:false});"/>
			</li>
			<%-- <li><label>期望进场日期：</label>
				<input name="installApplyIntoDateStart" type="text" id="installApplyIntoDateStart" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${bizConfirmStartOrder.installApplyIntoDateStart}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'installApplyIntoDateEnd\')}',isShowClear:false});"/> &nbsp;至&nbsp;
					
				<input name="installApplyIntoDateEnd" type="text" id="installApplyIntoDateEnd" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${bizConfirmStartOrder.installApplyIntoDateEnd}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'installApplyIntoDateStart\')}',isShowClear:false});"/>
			</li>
			<li><label>提交验收日期：</label>
				<input name="confirmAcceptanceDateBegin" type="text" id="confirmAcceptanceDateBegin" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${bizConfirmStartOrder.confirmAcceptanceDateBegin}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'confirmAcceptanceDateEnd\')}',isShowClear:false});"/> &nbsp;至&nbsp;
					
				<input name="confirmAcceptanceDateEnd" type="text" id="confirmAcceptanceDateEnd" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${bizConfirmStartOrder.confirmAcceptanceDateEnd}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'confirmAcceptanceDateBegin\')}',isShowClear:false});"/>
			</li>
			<li><label>实际进场日期：</label>
				<input name="installRealIntoDateStart" type="text" id="installRealIntoDateStart" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${bizConfirmStartOrder.installRealIntoDateStart}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'installRealIntoDateEnd\')}',isShowClear:false});"/> &nbsp;至&nbsp;
					
				<input name="installRealIntoDateEnd" type="text" id="installRealIntoDateEnd" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${bizConfirmStartOrder.installRealIntoDateEnd}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'installRealIntoDateStart\')}',isShowClear:false});"/>
			</li>
			<li><label>实际完工日期：</label>
				<input name="installRealCompleteDateStart" type="text" readonly="readonly" id="installRealCompleteDateStart" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${bizConfirmStartOrder.installRealCompleteDateStart}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'installRealCompleteDateEnd\')}',isShowClear:false});"/> &nbsp;至&nbsp;
					
				<input name="installRealCompleteDateEnd" type="text" readonly="readonly" id="installRealCompleteDateEnd" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${bizConfirmStartOrder.installRealCompleteDateEnd}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'installRealCompleteDateStart\')}',isShowClear:false});"/>
			</li>
			<li><label>安装项状态：</label>
				<input type="checkbox" id="allCheck"/>全选
				<form:checkboxes path="installStatus" items="${fns:getDictList('install_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
			</li> --%>
			<%-- <li><label>工程模式：</label>
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
			</li> --%>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="btns"><input class="btn btn-primary clearBtn" type="button" value="清空" onclick="clearCondition()"/></li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="button" value="导出" onclick="exportExcel()"/></li>
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
				<th>提交时间</th>
				<th>订单编号</th>
				<th>小区名称</th>
				<th>客户</th>
				<th>项目经理</th>
				<th>设计师</th>
				<th>设计师手机号</th>
				<th>合同开工日期</th>
				<th>实际开工日期</th>
				<th>延期天数</th>
				<th>开工客户签字</th>
				<th>开工延期类型</th>
				<th>开工延期说明</th>
				<th>自装延期天数</th>
				<th>自装客户签字</th>
				<th>开工照片</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="order" varStatus="status">
			<tr>
				<td>${order.name}</td>
				<td>${fns:getDictLabel(order.projectMode, 'project_mode','')}</td>
				<td>${fns:getElacLabel(order.engineDepartId,'')}</td>
				<td><fmt:formatDate type="both" value="${order.staCreateDate }"/></td>
				<td>${order.orderNumber}</td>
				<td>${order.communityName}-${order.buildNumber}-${order.buildUnit}-${order.buildRoom}</td>
				<td>${order.customerName}-${order.customerPhone}</td>
				<td>${order.managerRealName }</td>
				<td>${order.designerName }</td>
				<td>${order.designerPhone }</td>
				<td><fmt:formatDate type="date" value="${order.contractStartDate }"/></td>
				<td><fmt:formatDate type="date" value="${order.actualStartDate }"/></td>
				<td>${order.deplayDays }</td>
				<td>${fns:getDictLabel(order.dicIsNeedSign,'confirmstart_isneedsign','')}</td>
				<td>${fns:getDictLabel(order.delayType,'confirmstart_delaytype','')}</td>
				<td>${order.staRemarks }</td>
				<td>${order.staSelfDecorateDelayDays }</td>
				<td>${fns:getDictLabel(order.staIsSelfDecorateNeedSign,'confirmstart_isselfdecorateneedsign','')}</a></td>
				<td>
<%-- 					<a href="${ctx}/bizconfirmstartwork/bizConfirmStartwork/confirmPhotos?startWorkID=${order.orderStartWorkID}"> --%>
<!-- 						查看 -->
<!-- 					</a> -->
						
		<a href="#" data-toggle="modal" data-target="#myModal-photo"   onclick="ajaxGetImgs ('${order.orderStartWorkID}')">查看</a>
			
					
				</td>
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
                    <h4 class="modal-title text-center" id="myModalLabel">图片浏览</h4>
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
	                url: '${ctx}/bizconfirmstartwork/bizConfirmStartwork/ajaxConfirmPhotos',
	                data: {
	                	startWorkID:installID,
	                	orderID:orderId
	                },
	                success: function (data) {
						
						  	var modelList = data.mapObject;
		                    if (null!==modelList && modelList.length !== 0) {
// 		                        '<input  type="hidden"  name="bOrContPros[1].packagename" value="' + packName + '"></input>'
		                        var ainput='';
		                        for (var i = 0; i < modelList.length; i++) {
		                         ainput +=	 '<a  class="swiper-slide " ><img src="' + modelList[i].picUrl + '" ></a>';
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