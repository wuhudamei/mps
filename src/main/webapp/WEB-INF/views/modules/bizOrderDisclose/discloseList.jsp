<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>订单交底列表</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			findEngineListByStoreIdAndProjectMode();
		});
		function page(n, s) {
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
        function exportExcel(){

            //门店
            var storeId1 = '${bizOrderDisclose.storeId}';
            var storeId = $("#storeId").val();
            if(storeId1 != storeId){
                alertx("请先点击查询按钮后，再导出订单信息");
                return false;

            }
            //工程模式
            var projectMode1 = '${bizOrderDisclose.projectMode}';
            var projectMode = $("#projectMode").val();
            if(projectMode1 != projectMode){
                alertx("请先点击查询按钮后，再导出订单信息");
                return false;

            }
            //区域
            var engineDepartId1 = '${bizOrderDisclose.engineDepartId}';
            var engineDepartId = $("#engineDepartId").val();
            if(engineDepartId1 != engineDepartId){
                alertx("请先点击查询按钮后，再导出订单信息");
                return false;

            }

            //订单编号
            var orderNumber1 = '${bizOrderDisclose.orderNumber}';
            var orderNumber = $("#orderNumber").val();
            if(orderNumber1 != orderNumber){
                alertx("请先点击查询按钮后，再导出订单信息");
                return false;

            }
            //客户姓名
            var customerName1 = '${bizOrderDisclose.customerName}';
            var customerName = $("#customerName").val();
            if(customerName1 != customerName){
                alertx("请先点击查询按钮后，再导出订单信息");
                return false;

            }//项目经理
            var itemManager1 = '${bizOrderDisclose.managerRealName}';
            var itemManager = $("#managerRealName").val();
            if(itemManager1 != itemManager){
                alertx("请先点击查询按钮后，再导出订单信息");
                return false;

            }


            //实际交底日期 开始
            var beginContractStartDate1 = $("#discloseBegin").val();
            var beginContractStartDate = $("#discloseBegin1").val();
            if(beginContractStartDate1 != beginContractStartDate){
                alertx("请先点击查询按钮后，再导出订单信息");
                return false;

            }

            //实际交底日期 结束
            var endContractStartDate1 = $("#discloseEnd").val();
            var endContractStartDate = $("#discloseEnd1").val();
            if(endContractStartDate1 != endContractStartDate){
                alertx("请先点击查询按钮后，再导出订单信息");
                return false;

            }

            $("#searchForm").attr("action", "${ctx}/bizorderdisclose/bizOrderDisclose/exportDiscloseExcel");

            $("#searchForm").submit();

            $("#searchForm").attr("action", "${ctx}/bizorderdisclose/bizOrderDisclose/list2");

        }

        function formSubmit() {


            $("#searchForm").attr("action", "${ctx}/bizorderdisclose/bizOrderDisclose/list2");
            $("#searchForm").submit();
        }

	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/bizorderdisclose/bizOrderDisclose/preList">订单交底列表</a></li>
		<%-- <shiro:hasPermission name="ordertaskpack:orderTaskpack:edit"><li><a href="${ctx}/ordertaskpack/orderTaskpack/form">订单添加</a></li></shiro:hasPermission> --%>
	</ul>
	<form:form id="searchForm" modelAttribute="bizOrderDisclose" action="${ctx}/bizorderdisclose/bizOrderDisclose/list2" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<input id="discloseBegin1" type="hidden" value="<fmt:formatDate value="${bizOrderDisclose.discloseBegin}" pattern="yyyy-MM-dd"/>">
		<input id="discloseEnd1" type="hidden" value="<fmt:formatDate value="${bizOrderDisclose.discloseEnd}" pattern="yyyy-MM-dd"/>">


		<ul class="ul-form">
			<li><label>门店：</label>
				<c:if test="${empty storeDropEnable}">
					<form:select path="storeId" class="input-medium needClear" onchange="findEngineListByStoreIdAndProjectMode()">
						<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
				<c:if test="${!empty storeDropEnable}">
					<form:select path="storeId" class="input-medium needClear" disabled="true" onchange="findEngineListByStoreIdAndProjectMode()">
						<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
				<%-- <form:select path="storeId" class="input-medium needClear" onchange="findEngineListByStoreIdAndProjectMode()">
						<form:option value=""></form:option>
						<c:forEach items="${storeList}" var = "store">
							<form:option value="${store.value }">${store.label }</form:option>
						</c:forEach>
				</form:select> --%>
			</li>
			<li><label>工程模式：</label>
				<c:if test="${empty gongcheng}">
					<form:select path="projectMode" class="input-medium needClear" disabled="true" >
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
					<form:options items="${fns:getEngineListWithUserConditions()}"   itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>

			<li><label>实际交底日期：</label>
				<input name="discloseBegin" type="text" id="discloseBegin" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${bizOrderDisclose.discloseBegin}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'discloseEnd\')}',isShowClear:false});"/> &nbsp;至&nbsp;
					
				<input name="discloseEnd" type="text" id=discloseEnd readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${bizOrderDisclose.discloseEnd}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'discloseBegin\')}',isShowClear:false});"/>
			</li>
			<li><label>订单号：</label>
				<form:input path="orderNumber" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>客户姓名：</label>
				<form:input path="customerName" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>项目经理：</label>
				<form:input path="managerRealName" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<%-- <li><label>合同开工日期：</label>
				<input name="contractBegin" type="text" id="contractBegin" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${bizOrderDisclose.contractBegin}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'contractEnd\')}',isShowClear:false});"/> &nbsp;至&nbsp;
					
				<input name="contractEnd" type="text" id="contractEnd" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${bizOrderDisclose.contractEnd}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'contractBegin\')}',isShowClear:false});"/>
			</li> --%>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="button" value="查询" onclick="formSubmit()"/></li>
			<li class="btns"><input class="btn btn-primary clearBtn" type="button" value="清空" onclick="clearCondition()"/></li>
			<li class="btns"><input class="btn btn-primary" type="button" value="导出" onclick="exportExcel()"/></li>
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
				<th>订单号</th>
				<th>顾客信息</th>
				<th>实际交底日期</th>
				<th>合同开工日期</th>
				<th>项目经理</th>
				<th>设计师</th>
				<th>交底照片</th>
				<%-- <shiro:hasPermission name="orderclarificaiton:orderClarificaiton:edit"><th>操作</th></shiro:hasPermission> --%>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="bizOrderDisclose" varStatus="status">
			<tr>
				<td>
				${bizOrderDisclose.name }
				</td>
				<td>${fns:getDictLabel(bizOrderDisclose.projectMode, 'project_mode','')}</td>
				<td>
						${fns:getElacLabel(bizOrderDisclose.engineDepartId, '')}
				</td>

				<td><fmt:formatDate type="both" value="${bizOrderDisclose.disCreateDate }"/></td>
				<td>
					${bizOrderDisclose.orderNumber }
				</td>
				<td>
					${bizOrderDisclose.communityName}-${bizOrderDisclose.buildNumber }-${bizOrderDisclose.buildUnit }-${bizOrderDisclose.buildRoom }-${bizOrderDisclose.customerName }
				</td>
				<td>
					<fmt:formatDate type="date" value="${bizOrderDisclose.discloseDate}"/>
				</td>
				<td>
					<fmt:formatDate type="date" value="${bizOrderDisclose.contractStartDate}" />
				</td>
				<td>${bizOrderDisclose.managerRealName}</td>
				<td>${bizOrderDisclose.designerName}</td>
				<td>
<%-- 					<a href="${ctx}/bizorderdisclosepic/bizOrderDisclosePic/showOrderDisclosePhoto?orderDiscloseId=${bizOrderDisclose.orderDiscloseId}"> --%>
<!-- 						查看 -->
<!-- 					</a> -->
					
							<a href="#" data-toggle="modal" data-target="#myModal-photo"   onclick="ajaxGetImgs ('${bizOrderDisclose.orderDiscloseId}')">查看</a>
	
					
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
	                url: '${ctx}/bizorderdisclosepic/bizOrderDisclosePic/ajaxShowOrderDisclosePhoto',
	                data: {
	                	orderDiscloseId:installID,
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
	
</body>
</html>