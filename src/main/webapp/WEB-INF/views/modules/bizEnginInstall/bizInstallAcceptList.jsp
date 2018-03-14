<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>安装项验收明细</title>
	<meta name="decorator" content="default"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Worker/css/globalUtil.css"/>
<%--     <link href="${ctxStatic}/modules/materialinstall/css/swiper.min.css" rel="stylesheet" type="text/css" /> --%>
    <script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/history.js"></script>
	
	
	
	<link href="${ctxStatic}/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="${ctxStatic}/vendor/bootstrap-table/bootstrap-table.min.css" rel="stylesheet" type="text/css" />
    <link href="${ctxStatic}/vendor/bootstrap-datetimepicker/css/datetimepicker.css" rel="stylesheet">
    <link href="${ctxStatic}/modules/popUp/css/style.css" rel="stylesheet" type="text/css" />
    <link href="${ctxStatic}/modules/popUp/css/swiper.css" rel="stylesheet" type="text/css" />
	
	
	
    <script type="text/javascript" src="${ctxStatic}/vendor/jquery/jquery-2.2.4.min.js"></script>
    <script type="text/javascript" src="${ctxStatic}/vendor/bootstrap/js/bootstrap.min.js" charset="UTF-8"></script>
    <script type="text/javascript" src="${ctxStatic}/vendor/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
    <script src="${ctxStatic}/vendor/jquery/swiper.min.js"></script>
    
    
	<script type="text/javascript">
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
// 		"${ctx}/bizorderinstallplanpic/bizOrderInstallPlanPic/installAcceptPhotos?installID=${order.installID}&orderID=" 
		

		
		
		
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/bizorderinstallacceptdetail/bizOrderInstallAcceptDetail/preList">安装项验收明细列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="bizOrderInstallAcceptanceDetail" action="${ctx}/bizorderinstallacceptdetail/bizOrderInstallAcceptDetail/list" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<%-- <li><label>门店：</label> 
				<form:select path="storeId" class="input-medium needClear" id="storeId" onchange="nextValue()">
					<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
			</li> --%>
			<li><label>门店：</label>
				<%-- <c:if test="${empty storeDropEnable}">
					<form:select path="storeId" class="input-medium" disabled="true">
						<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
				<c:if test="${!empty storeDropEnable}">
					<form:select path="storeId" class="input-medium needClear">
						<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if> --%>
				<form:select path="storeId" class="input-medium needClear">
						<form:option value=""></form:option>
						<c:forEach items="${storeList}" var = "store">
							<form:option value="${store.value }">${store.label }</form:option>
						</c:forEach>
				</form:select>
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
			<li><label>安装项名称：</label>
				<form:input path="installItemName" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>订单编号：</label>
				<form:input path="orderNumber" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>客户姓名：</label>
				<form:input path="customerName" htmlEscape="false" maxlength="16" class="input-medium"/>
			</li>
			<li><label>项目经理：</label>
				<form:input path="employeeRealName" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>提交验收日期：</label>
				<input name="confirmAcceptanceDateBegin" type="text" id="confirmAcceptanceDateBegin" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${bizOrderInstallAcceptanceDetail.confirmAcceptanceDateBegin}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'confirmAcceptanceDateEnd\')}',isShowClear:false});"/> &nbsp;至&nbsp;
					
				<input name="confirmAcceptanceDateEnd" type="text" id="confirmAcceptanceDateEnd" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${bizOrderInstallAcceptanceDetail.confirmAcceptanceDateEnd}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'confirmAcceptanceDateBegin\')}',isShowClear:false});"/>
			</li>
			<li><label>实际进场日期：</label>
				<input name="installRealIntoDateStart" type="text" id="installRealIntoDateStart" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${bizOrderInstallAcceptanceDetail.installRealIntoDateStart}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'installRealIntoDateEnd\')}',isShowClear:false});"/> &nbsp;至&nbsp;
					
				<input name="installRealIntoDateEnd" type="text" id="installRealIntoDateEnd" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${bizOrderInstallAcceptanceDetail.installRealIntoDateEnd}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'installRealIntoDateStart\')}',isShowClear:false});"/>
			</li>
			<li><label>实际完工日期：</label>
				<input name="installRealCompleteDateStart" type="text" readonly="readonly" id="installRealCompleteDateStart" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${bizOrderInstallAcceptanceDetail.installRealCompleteDateStart}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'installRealCompleteDateEnd\')}',isShowClear:false});"/> &nbsp;至&nbsp;
					
				<input name="installRealCompleteDateEnd" type="text" readonly="readonly" id="installRealCompleteDateEnd" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${bizOrderInstallAcceptanceDetail.installRealCompleteDateEnd}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'installRealCompleteDateStart\')}',isShowClear:false});"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="btns"><input class="btn btn-primary clearBtn" type="button" value="清空" onclick="clearCondition()"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
<%-- 	<sys:message content="${message}"/> --%>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>门店</th>
				<th>工程模式</th>
				<th>提交验收日期</th>
				<th>订单编号</th>
				<th>顾客</th>
				<th>项目经理</th>
				<th>设计师</th>
				<th>设计师手机号</th>
				<th>安装项名称</th>
				<th>实际进场日期</th>
				<th>实际完工日期</th>
				<th>是否延期</th>
				<th>安装验收单据</th>
				<th>选材清单</th>
				<th>备注说明 </th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="order" varStatus="status">
			<tr>
				<td>${order.name}</td>
				<td>${fns:getDictLabel(order.projectMode, 'project_mode','')}</td>
				<td><fmt:formatDate type="both" value="${order.installUpdateDate }"/></td>
				<td>${order.orderNumber}</td>
				<td>${order.communityName}-${order.buildNumber}-${order.buildUnit}-${order.buildRoom}-${order.customerName}</td>
				<td>${order.employeeRealName }</td>
				<td>${order.designerName }</td>
				<td>${order.designerPhone }</td>
				<td>${order.installItemName }</td>
				<td><fmt:formatDate type="date" value="${order.installRealIntoDate }"/></td>
				<td><fmt:formatDate type="date" value="${order.installRealCompleteDate }"/></td>
				<td>${fns:getDictLabel(order.installIsCompleteDelay,'install_is_no_extension','')}</td>
				<td>
 			<%--javascript:void(0);	"${ctx}/bizorderinstallplanpic/bizOrderInstallPlanPic/installAcceptPhotos?installID=${order.installID}&orderID="--%>
				    <a href="#" data-toggle="modal" data-target="#myModal-photo"   onclick="ajaxGetImgs('${order.installID}','${order.id }')">查看</a>
				
				</td>
				<td><a href="${ctx}/bizmaterialchoicebill/bizMaterialsChoiceBill/order_materials_choice_bill_detail?orderId=${order.id}">查看</a></td>
				<td>${order.installDeplayRemarks }</td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
	
	
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
            $.ajax({
                type: 'POST',
                dataType: 'json',
                url: '${ctx}/bizorderinstallplanpic/bizOrderInstallPlanPic/ajaxinstallAcceptPhotos',
                data: {
                    installID:installID,
                    orderID:orderId
                },
                success: function (data) {
                    var modelList = data.mapObject;
                    if (null!==modelList && modelList.length !== 0) {
                        var ainput='';
                        for (var i = 0; i < modelList.length; i++) {
                            ainput +=	 '<a class="swiper-slide " ><img src="' + modelList[i].picUrl + '" ></a>';
                        }
                        $("#inputId").html(ainput);
                    }else{
                        var ainput='';
                        ainput ='<a  class="swiper-slide " ><img src="${ctxStatic}/images/nopic.jpg" ></a>';
                        $("#inputId").html(ainput);
                    }
                }
            })

        }


    </script>
	
	
</body>
</html>