<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>约检节点验收明细管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }

        function findEngineListByStoreIdAndProjectMode(){
            var html3 = '<option value=""></option>';
            var storeId = $("#sel").val();
            var projectModeValue = $("#projectModeId").val();
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

                        $("#engineDepartSelect").html(html3);
                    } else {
                        $("#engineDepartSelect").html(html3);
                    }

                }

            });
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
	</script>
</head>
<body>
<c:set var="user" value="${fns:getUser()}"></c:set>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/arrangecheckdonedetailquery/arrangeCheckDoneDetailQuery/list">约检节点验收明细列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="arrangeCheckDoneDetailQuery" action="${ctx}/arrangecheckdonedetailquery/arrangeCheckDoneDetailQuery/list2" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">

			<li><label>门店：</label>
				<form:select path="storeId" class="input-medium needClear" onchange="findEngineListByStoreIdAndProjectMode()" id="sel">
					<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label class="control-label">工程模式：</label>


				<c:if test="${user.projectMode ==3}">

					<form:select path="projectMode" class="input-medium needClear" onchange="findEngineListByStoreIdAndProjectMode()" id="projectModeId">
						<form:option value="" label="" />
						<form:options items="${fns:getDictList('project_mode')}"
									  itemLabel="label" itemValue="value" htmlEscape="false" />
					</form:select>
				</c:if>
				<c:if test="${user.projectMode !=3}">

					<form:select path="projectMode" class="input-medium needClear" onchange="findEngineListByStoreIdAndProjectMode()"  id="projectModeId">

						<form:option label="${fns:getDictLabel(user.projectMode, 'project_mode', '')}"
									 value="${user.projectMode}"/>
					</form:select>
				</c:if>
			</li>

			<li><label>区域：</label>
				<form:select path="engineDepartId" class="input-medium needClear" id="engineDepartSelect">
					<form:options items="${fns:getEngineListWithUserConditions()}"   itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>

			<li><label>订单编号：</label>
				<form:input path="orderNumber" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>客户姓名：</label>
				<form:input path="customerName" htmlEscape="false" maxlength="16" class="input-medium"/>
			</li>

			<li><label>项目经理：</label>
				<form:input path="managerName" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>

			<li><label>质检员：</label>
				<form:input path="pqcName" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>约检节点状态：</label>
				<form:select path="qcStatus" class="input-medium needClear">
					<form:option value="" label=""/>
					<form:option value="10" label="质检员已提交约检验收"/>
					<form:option value="20" label="结算员已驳回约检单"/>
					<form:option value="30" label="已通过约检验收"/>
				</form:select>
			</li>

			<li><label>约检节点名称：</label>
				<form:input path="qcNodeName" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				质检员确认节点验收通过时间：
			<input name="applyCheckDoneStartDate" id="beginSignContractDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
							value="<fmt:formatDate value="${entity.applyCheckDoneStartDate}" pattern="yyyy-MM-dd  HH:mm:ss"/>"
							onclick="WdatePicker({dateFmt:'yyyy-MM-dd  HH:mm:ss',maxDate:'#F{$dp.$D(\'endSignContractDate\')}',isShowClear:true});"/> 至

				<input name="applyCheckDoneEndDate" id="endSignContractDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					   value="<fmt:formatDate value="${entity.applyCheckDoneEndDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd  HH:mm:ss',minDate:'#F{$dp.$D(\'beginSignContractDate\')}',isShowClear:true});"/>
			</li>



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
				<th>工程模式</th>
				<th>区域</th>
				<th>订单编号</th>
				<th>质检员确认节点验收通过时间</th>
				<th>约检节点名称</th>
				<th>约检节点状态</th>
				<th>小区名称</th>
				<th>客户姓名</th>
				<th>项目经理</th>
				<th>质检员</th>
				<%--<th>质检员确认节点验收通过日期</th>--%>
				<th>
					质检员验收照片
				</th>

			<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="arrangeCheckDoneDetailQuery">
			<tr>

				<td>
						${fns:getStoreLabel(arrangeCheckDoneDetailQuery.storeId, '')}
				</td>
				<td>${fns:getDictLabel(arrangeCheckDoneDetailQuery.projectMode, 'project_mode', '')}</td>


				<td>
						${arrangeCheckDoneDetailQuery.engineDepartName}
				</td>
				<td>
						${arrangeCheckDoneDetailQuery.orderNumber}
				</td>
				<td>
					<fmt:formatDate value="${arrangeCheckDoneDetailQuery.applyCheckDoneDate}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate>

				</td>
				<td>
						${arrangeCheckDoneDetailQuery.qcNodeName}
				</td><td>
						${arrangeCheckDoneDetailQuery.qcStatus}
				</td>
				<td>
						${arrangeCheckDoneDetailQuery.customerInfo}
				</td>
				<td>
						${arrangeCheckDoneDetailQuery.customerName}
				</td>
				<td>
						${arrangeCheckDoneDetailQuery.managerName}
				</td>
				<td>
						${arrangeCheckDoneDetailQuery.pqcName}
				</td>
				<%--<td>--%>
					<%--<fmt:formatDate value="${arrangeCheckDoneDetailQuery.checkDoneDate}" pattern="yyyy-MM-dd"></fmt:formatDate>--%>

				<%--</td>--%>
				<td>
<%-- 						<a href="${ctx}/arrangecheckdonedetailquery/arrangeCheckDoneDetailQuery/findPqcByBillId?qcbillId=${arrangeCheckDoneDetailQuery.qcBillId}">查看照片</a> --%>
				
						<a href="#" data-toggle="modal" data-target="#myModal-photo"   onclick="ajaxGetImgs ('${arrangeCheckDoneDetailQuery.qcBillId}')">查看</a>
		
				</td>


				<td><a href="${ctx}/bizorderqcbill/bizOrderQcBill/details?qcBillId=${arrangeCheckDoneDetailQuery.qcBillId}">查看报告</a></td>

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
	                url: '${ctx}/arrangecheckdonedetailquery/arrangeCheckDoneDetailQuery/ajaxFindPqcByBillId',
	                data: {
	                	qcbillId:installID,
	                	orderID:orderId
	                },
	                success: function (data) {
						
						  	var modelList = data.mapObject;
		                    if (null!==modelList && modelList.length !== 0) {
// 		                        '<input  type="hidden"  name="bOrContPros[1].packagename" value="' + packName + '"></input>'
		                        var ainput='';
		                        for (var i = 0; i < modelList.length; i++) {
		                         ainput +=	 '<a class="swiper-slide " ><img src="' + modelList[i]+ '" ></a>';
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