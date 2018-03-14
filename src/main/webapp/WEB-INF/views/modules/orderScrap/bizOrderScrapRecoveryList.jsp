<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>订单回收</title>
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
			findEngineListByStoreIdAndProjectMode();
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
								
								if('${bizOrderScrap.enginDepartId}' == data[v].engineDepartId){
									$("#s2id_enginDepartId .select2-chosen").html(data[v].engineDepartName);
									html3 = html3 + "<option value='"+data[v].engineDepartId+"' selected='selected'>"+data[v].engineDepartName+"</option>";
								}else{
									html3 = html3 + "<option value='"+data[v].engineDepartId+"'>"+data[v].engineDepartName+"</option>";
								}
							}
							
							
							$("#enginDepartId").html(html3);
						} else {
							$("#enginDepartId").html(html3);
						}

					}

				});		
		}

		//查看图片
        function ajaxGetImgs(orderId){
            $.ajax({
                type: 'POST',
                dataType: 'json',
                url: '${ctx}/orderScrap/orderScrap/photo',
                data: {
                    orderId:orderId
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
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/orderScrap/orderScrap/preRecoveryList">订单回收列表</a></li>
	</ul>
	
	
	<form:form id="searchForm" modelAttribute="bizOrderScrap" action="${ctx}/orderScrap/orderScrap/recoveryList" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		
		
		<ul class="ul-form">
			
			<li><label>门店：</label>
				<form:select path="storeId" class="input-medium needClear" onchange="findEngineListByStoreIdAndProjectMode()">
					<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>工程模式：</label>
				<c:if test="${empty gongcheng}">
					<form:select path="projectMode" class="input-medium" disabled="true" onchange="findEngineListByStoreIdAndProjectMode()">
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
				<form:select path="enginDepartId" class="input-medium needClear">
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
			<li><label>设计师：</label>
				<form:input path="designerName" htmlEscape="false" maxlength="12" class="input-medium needClear"/>
			</li>
			<li><label>作废原因：</label>
				<form:select path="scrapReasonType" class="input-medium needClear">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('scrap_reason_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" /></li>
			<li class="btns"><input class="btn btn-primary clearBtn" type="button" value="清空" /></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>序号</th>
				<th>门店</th>
				<th>工程模式</th>
				<th>区域</th>
				<th>订单编号</th>
				<th>客户信息</th>
				<th>项目经理</th>
				<th>设计师</th>
				<th>合同开工日期</th>
				<th>合同竣工日期</th>
				<th>作废时间</th>
				<th>图片凭证</th>
				<th>作废原因</th>
				<th>作废说明</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="bizOrderScrap" varStatus="status">
			<tr>
				<td>
					${status.index +1}
				</td>
				<td>
					${bizOrderScrap.storeName}
				</td>
				<td>
					${bizOrderScrap.projectModeName}
				</td>
				<td>
					${bizOrderScrap.enginDepartName}
				</td>
				<td>
					${bizOrderScrap.orderNumber}
				</td>
				<td>
					${bizOrderScrap.communityName}-${bizOrderScrap.buildNumber}-${bizOrderScrap.buildUnit}-${bizOrderScrap.buildRoom}-${bizOrderScrap.customerName}
				</td>
				<td>
					${bizOrderScrap.itemManager}
				</td>
				<td>
					${bizOrderScrap.designerName}
				</td>
				<td>
					<fmt:formatDate value="${bizOrderScrap.contractStartDate}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					<fmt:formatDate value="${bizOrderScrap.contractEndDate}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					<fmt:formatDate value="${bizOrderScrap.scrapDatetime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<%--<a href="${ctx}/orderScrap/orderScrap/getPic?orderId=${bizOrderScrap.orderId}">查看图片</a>--%>
					<a href="#" data-toggle="modal" data-target="#myModal-photo" onclick="ajaxGetImgs('${bizOrderScrap.orderId}')">查看图片</a>
				</td>
				<td>
					${bizOrderScrap.scrapReasonTypeName}
				</td>
				<td>
					${bizOrderScrap.scrapDescribe}
				</td>
				<td>
					<a href="${ctx}/orderScrap/orderScrap/details?orderId=${bizOrderScrap.orderId}">查看详情</a>
					<c:if test="${bizOrderScrap.flag == '1'}">
						<a href="${ctx}/orderScrap/orderScrap/orderNumberRecovery?orderId=${bizOrderScrap.orderId}&orderNumber=${bizOrderScrap.orderNumber}" onclick="return confirmx('确认要订单编号回收吗？', this.href)">编号回收</a>
					</c:if>
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