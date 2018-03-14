<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>质检罚款日志查询</title>
	<meta name="decorator" content="default"/>
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

        function ajaxGetImgs(checkItemId,qcCheckItemId,reportCode){
            $.ajax({
                type: 'POST',
                dataType: 'json',
                url: '${ctx}/qualityControlFineUpdate/ajaxphoto',
                data: {
                    checkItemId:checkItemId,
                    qcCheckItemId:qcCheckItemId,
                    qcBillCode:reportCode
                },
                success: function (data) {

                    var modelList = data.mapObject;
                    if (null!==modelList && modelList.length !== 0) {
//		                        '<input  type="hidden"  name="bOrContPros[1].packagename" value="' + packName + '"></input>'
                        var ainput='';
                        for (var i = 0; i < modelList.length; i++) {
                            ainput +=	 '<a  class="swiper-slide " ><img src="' + modelList[i].picUrl + '" ></a>';
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
							
							$("#engineDepartSelect").html(html3);
						} else {
							$("#engineDepartSelect").html(html3);
						}

					}

				});		
		}
		
		

	        function formSubmit() {


	            $("#searchForm").attr("action", "${ctx}/qualityControlFineUpdate/loglistInfo");
	            $("#searchForm").submit();
	        }
		
		
		
	</script>
</head>	
<body>
<c:set var="user" value="${fns:getUser()}"></c:set>
	<ul class="nav nav-tabs">
	<li class="active"><a href="#">质检罚款修改</a></li>
	</ul>
	<form:form id="searchForm"  modelAttribute="qualityControlFineUpdateEntity" action="${ctx}/qualityControlFineUpdate/listInfo" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		
		<ul class="ul-form">
			<li><label>门店：</label>
				<form:select path="storeId"  class="input-medium needClear"  onchange="findEngineListByStoreIdAndProjectMode()">
						<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			
			<li><label class="control-label">工程模式：</label>
		
			
			<c:if test="${user.projectMode ==3}">
			
			<form:select path="projectMode" class="input-medium needClear" onchange="findEngineListByStoreIdAndProjectMode()" >
							<form:option value="" label="" />
					<form:options items="${fns:getDictList('project_mode')}"
						itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
			</c:if>
			<c:if test="${user.projectMode !=3}">
		
			<form:select path="projectMode" class="input-medium needClear" onchange="findEngineListByStoreIdAndProjectMode()">
					
					<form:option label="${fns:getDictLabel(user.projectMode, 'project_mode', '')}"
					value="${user.projectMode}"/>
				</form:select>
			</c:if>
			
			<li><label>区域：</label>
				<form:select path="engineDepartId" class="input-medium needClear" id="engineDepartSelect">
					<form:options items="${fns:getEngineListWithUserConditions()}"   itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			
			</li>
			<li><label>订单编号：</label>
			<form:input path="orderNumber" htmlEscape="false" maxlength="30" class="input-large"/>
			</li>
			<li><label>客户姓名：</label>
				<form:input path="customerName" htmlEscape="false" maxlength="11" class="input-large"/>
			</li>
			
			<li><label>操作时间：</label>
				<input name="beginModifyDatetime" type="text" readonly="readonly"     value='<fmt:formatDate value="${beginModifyDatetime }"  pattern="yyyy-MM-dd HH:mm:ss"/>' id="beginCheckDatetime" maxlength="20" class="input-medium Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'endCheckDatetime\')}',isShowClear:false});"/>
				-
				<input name="endModifyDatetime" type="text" readonly="readonly" value='<fmt:formatDate value="${endModifyDatetime }"  pattern="yyyy-MM-dd HH:mm:ss"/>' id="endCheckDatetime" maxlength="20" class="input-medium Wdate"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'beginCheckDatetime\')}',isShowClear:false});"/>   
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="button" value="查询" onclick="formSubmit()"/></li>
			<li class="btns"><input class="btn btn-primary clearBtn" type="button" value="清空" onclick="clearCondition()"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>门店</th>
				<th>操作时间</th>
				<th>操作人</th>
				<th>订单编号</th>
				
				<th>小区</th>
				<th>客户</th>
				<th>质检报告编号</th>
				<th>报告类型</th>
				<th>检查人</th>
				<th>检查项分类</th>
				<th>检查项</th>
				<th>凭证</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="report" varStatus="status">
			<tr>
			
				<td>
					${fns:getStoreLabel(report.storeId, '')}
				</td>
				<td>
					<fmt:formatDate value="${report.modifyDatetime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${report.operatorName}
				</td>
				<td>
					${report.orderNumber}
				</td>
				<td>
				${report.xiaoqu}-${report.lou}-${report.danyuan}-${report.shi}
				</td>
				<td>${report.customerName}-${report.customerPhone}</td>
				<td>
					<a href="${ctx}/bizorderqcbill/bizOrderQcBill/details?qcBillId=${report.billId}">${report.reportCode}</a>
				</td>
				<td>
				<c:if test="${report.reportType==1 }">
				约检
				</c:if>
				<c:if test="${report.reportType==2 }">
				抽检
				</c:if>
				</td>
				<td>
					${report.checkManName}
				</td>
				<td>
					${report.checkTypeName}
				</td>
				<td>
					${report.checkItemName}
				</td>
				<td>
					<c:choose>
						   <c:when test="${report.picCount>0}">   
								<%--<a href="${ctx}/qualityControlFineUpdate/photo?checkItemId=${report.checkItemId}&qcCheckItemId=${report.qcCheckItemId}&qcBillCode=${report.reportCode}">查看</a>
						   --%>
							   <a href="#" data-toggle="modal" data-target="#myModal-photo"   onclick="ajaxGetImgs('${report.checkItemId}','${report.qcCheckItemId}','${report.reportCode}')">查看</a>
						   </c:when>
						   <c:otherwise>
						   		查看
						   </c:otherwise>
					</c:choose>
				</td>
				<td>
   					<a href="${ctx}/qualityControlFineUpdate/fineUpdateLog?qcBillCode=${report.reportCode}&qcCheckItemId=${report.qcCheckItemId}">详情</a>
				</td>
			
				
			
				
			
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
</script>
</body>
</html>