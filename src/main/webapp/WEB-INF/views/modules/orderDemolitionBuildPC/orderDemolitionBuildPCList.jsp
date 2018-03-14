<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>拆改交底查询</title>
	<meta name="decorator" content="default"/>
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
								if('${signDetail.enginDepartId}' == data[v].engineDepartId){
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
		
		function exportDetailExcel(){
			
			$("#searchForm").attr("action", "${ctx}/modules/orderDemolitionBuildPC/web/OrderDemolitionBuildControllerPC/export");
			$("#searchForm").submit();
			$("#searchForm").attr("action", "${ctx}/modules/orderDemolitionBuildPC/web/OrderDemolitionBuildControllerPC/list");
			alertx("正在导出，请稍后...");
			
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/modules/orderDemolitionBuildPC/web/OrderDemolitionBuildControllerPC/list">拆改交底查询列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="signDetail" action="${ctx}/modules/orderDemolitionBuildPC/web/OrderDemolitionBuildControllerPC/list" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>

		<ul class="ul-form">
			<li><label>门店：</label>
				<form:select path="storeId" class="input-medium needClear" onchange="findEngineListByStoreIdAndProjectMode()">
					<form:options items="${fns:getStoreList()}" itemLabel="label" selectIndex="1" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>工程模式：</label>
				<c:if test="${!empty projectModeEnable}">
					<form:select path="projectMode" class="input-medium" disabled="true" onchange="findEngineListByStoreIdAndProjectMode()">
						<form:option value="" label=""/>
						<form:options items="${fns:getDictList('project_mode')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
				<c:if test="${empty projectModeEnable}">
					<form:select path="projectMode" class="input-medium needClear" onchange="findEngineListByStoreIdAndProjectMode()">
						<form:option value="" label=""/>
						<form:options items="${fns:getDictList('project_mode')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
			</li>
			<li><label>区域：</label>
				<form:select path="enginDepartId" class="input-medium needClear">
					<%-- <form:options items="${fns:getEngineListWithUserConditions()}"   itemLabel="label" itemValue="value" htmlEscape="false"/> --%>
				</form:select>
			</li>
			<li><label>客户姓名：</label>
				<form:input path="customerName" htmlEscape="false" maxlength="18" class="input-medium needClear"/>
			</li>
			<li><label>签到日期：</label>
				<input name="startDemolitionBuildDate" id="startDemolitionBuildDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate needClear"
					   value="<fmt:formatDate value="${signDetail.startDemolitionBuildDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'endDemolitionBuildDate\')}',isShowClear:true});"/> 至
				<input name="endDemolitionBuildDate" id="endDemolitionBuildDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate needClear"
					   value="<fmt:formatDate value="${signDetail.endDemolitionBuildDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'startDemolitionBuildDate\')}',isShowClear:true});"/>
			</li>
			
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="btns"><input onclick="clearWorker()" class="btn btn-primary clearBtn" type="button" value="清空"/></li>
			<!-- <li class="btns"><input id="btnSubmit" class="btn btn-primary" type="button" value="导出拆改交底Excel" onclick="exportDetailExcel()"/></li> -->
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
				<th>项目经理</th>
				<th>顾客信息</th>
				<th>签到日期</th>
				<th>签到地址</th>
				<th>交底日期</th>
				<th>延期天数</th>
				<th>交底图片</th>
				<th>误差（米）</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="order" varStatus="index">
			<tr>
				<td>
					${index.index + 1 }
				</td>
				<td>
					${fns:getStoreLabel(order.storeId, '')}
				</td>
				<td>
					${fns:getDictLabel(order.projectMode, 'project_mode', '')}
				</td>
				
				<td>
					${order.enginDepartName}
				</td>
				<td>
					${order.orderNumber}
				</td>
				<td>
					${order.managerName}
				</td>
				
				<td>
					${order.customerAddress}
				</td>
				
				<td>
					<fmt:formatDate value="${order.signDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				
				<td>
					${order.signAddress}
				</td>
				
				<td>
					<fmt:formatDate value="${order.demolitionBuildDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				
				<td>
					${order.delayDays}
				</td>
				<td>
					<%--<a href="${ctx}/modules/orderDemolitionBuildPC/web/OrderDemolitionBuildControllerPC/photo?picType=10020&id=${order.id}">查看</a>--%>
					<a href="#"	data-toggle="modal" data-target="#myModal-photo" onclick="ajaxGetImgs('${order.id}')">查看</a>
				</td>
				<td>
					${order.signDistance}
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
	<script type="text/javascript">
        function ajaxGetImgs(id){
            $.ajax({
                type: 'POST',
                dataType: 'json',
                url: '${ctx}/modules/orderDemolitionBuildPC/web/OrderDemolitionBuildControllerPC/photo',
                data: {
                    id:id
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