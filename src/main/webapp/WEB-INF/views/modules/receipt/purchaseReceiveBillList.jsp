<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>收货单管理</title>
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
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/receipt/purchaseReceiveBill/">收货单列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="purchaseReceiveBill" action="${ctx}/receipt/purchaseReceiveBill/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>门店：</label>
				<form:select path="storeId" class="input-medium">
					<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>工程模式：</label>
				<c:if test="${!empty projectModeEnable}">
					<form:select path="projectMode" class="input-medium" disabled="true" >
						<form:option value="" label=""/>
						<form:options items="${fns:getDictList('project_mode')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
				<c:if test="${empty projectModeEnable}">
					<form:select path="projectMode" class="input-medium needClear">
						<form:option value="" label=""/>
						<form:options items="${fns:getDictList('project_mode')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
			</li>
			<li><label>订单编号：</label>
				<form:input path="orderNumber" htmlEscape="false" class="input-medium needClear"/>
			</li>
			<li><label>客户姓名：</label>
				<form:input path="customerName" htmlEscape="false" class="input-medium needClear"/>
			</li>
			<li><label>采购单编号：</label>
				<form:input path="purchaseCode" htmlEscape="false" maxlength="64" class="input-medium needClear"/>
			</li>
			<li><label>收货单编号：</label>
				<form:input path="purchaseReceiveBillCode" htmlEscape="false" maxlength="64" class="input-medium needClear"/>
			</li>
			<li><label>收货类型：</label>
				<form:select path="purchaseType" class="input-medium needClear">
						<form:option value="" label=""/>
						<form:options items="${fns:getDictList('purchase_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
			</li>
			<li><label>收货人：</label>
				<form:input path="receiveName" htmlEscape="false" maxlength="64" class="input-medium needClear"/>
			</li>
			<li><label>收货日期：</label>
				<input name="beginReceiveDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate needClear"
					value="<fmt:formatDate value="${purchaseReceiveBill.beginReceiveDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/> - 
				<input name="endReceiveDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate needClear"
					value="<fmt:formatDate value="${purchaseReceiveBill.endReceiveDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
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
				<th>客户信息</th>
				<th>采购单编号</th>
				<th>收货单编号</th>
				<th>收货类型</th>
				<th>收货人</th>
				<th>收货日期</th>
				<th>收货单照片</th>
				<shiro:hasPermission name="receipt:purchaseReceiveBill:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="purchaseReceiveBill">
			<tr>
				<td>
					${fns:getStoreLabel(purchaseReceiveBill.storeId, '')}
				</td>
				<td>
					${fns:getDictLabel(purchaseReceiveBill.projectMode, 'project_mode', '')}
				</td>
				<td>
					${purchaseReceiveBill.orderNumber}
				</td>
				<td>
					<%-- ${purchaseReceiveBill.customerAddress}- --%>${purchaseReceiveBill.communityName}-${purchaseReceiveBill.buildNumber}-${purchaseReceiveBill.buildUnit}-${purchaseReceiveBill.buildRoom}-${purchaseReceiveBill.customerName}
				</td>
				<td>
					${purchaseReceiveBill.purchaseCode}
				</td>
				<td>
				${purchaseReceiveBill.purchaseReceiveBillCode}
				</td>
				<td>
					${purchaseReceiveBill.purchaseTypeName}
				</td>
				<td>
					${purchaseReceiveBill.receiveName}
				</td>
				<td>
					<fmt:formatDate value="${purchaseReceiveBill.receiveDate}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
				 <a href="#" data-toggle="modal" data-target="#myModal-photo"   onclick="ajaxGetImgs ('${purchaseReceiveBill.id}')">查看</a>
				
<%-- 					<a href ="${ctx}/receipt/purchaseReceiveBill/seePhoto?id=${purchaseReceiveBill.id}">收货单照片</a> --%>
				</td>
				<shiro:hasPermission name="receipt:purchaseReceiveBill:edit"><td>
    				<a href="${ctx}/receipt/purchaseReceiveBill/receiptDetail?id=${purchaseReceiveBill.id}">详情</a>
				</td></shiro:hasPermission>
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
	            $.ajax({
	                type: 'POST',
	                dataType: 'json',
	                url: '${ctx}/receipt/purchaseReceiveBill/ajaxseePhoto',
	                data: {
	                	id:installID,
	                	orderID:orderId
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
        
        
    </script>
	
	
	
</body>
</html>