<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>开关面板明细</title>
	<meta name="decorator" content="default"/>
	<link href="${ctxStatic}/modules/popUp/css/style.css" rel="stylesheet" type="text/css" />
	<link href="${ctxStatic}/modules/popUp/css/swiper.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="${ctxStatic}/vendor/jquery/jquery-2.2.4.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/vendor/bootstrap/js/bootstrap.min.js" charset="UTF-8"></script>
	<script type="text/javascript" src="${ctxStatic}/vendor/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
	<script src="${ctxStatic}/vendor/jquery/swiper.min.js"></script>
</head>
<body>
		<ul class="nav nav-tabs">
			<li class="active"><a href="${ctx}/purchase/bizPurchaseMainPanel/panelList">开关面板申请列表</a></li>
			<li class="active"><a href="${ctx}/purchase/bizPurchaseMainPanel/mainPanelDetails?id=${bizPurchaseMainPanel.id}">开关面板明细</a></li>
		</ul>
		<div>
			<a class="btn" href="javascript:" onclick="goToHistory()">返回</a>
		</div>
		
	
		<div>
			<div class="col-md-12 column">
				<div >
					<h3>
						<center>
							大美装饰管理平台--采购单-${ bizPurchaseMainPanel.purchaseCode }
						</center>
					</h3>
				</div>
			</div>
			<table style="width:100%" align="center" valign="center" class="table table-striped table-bordered table-condensed">
				<tr>
					<td colspan="2" style="width:100%"><label>地址：</label>
						${bizPurchaseMainPanel.customerAddress }<%-- ${bizPurchaseMainPanel.communityName }-${bizPurchaseMainPanel.buildNumber }-${bizPurchaseMainPanel.buildUnit }-${bizPurchaseMainPanel.buildRoom }-- --%>${bizPurchaseMainPanel.customerName } 
					</td>
				</tr>
				<tr>
					<td style="width:50%"><label>期望到货日期：</label>
						<fmt:formatDate value="${bizPurchaseMainPanel.applyReceiveTime }" pattern="yyyy-MM-dd"/>
					</td>
					<td style="width:50%"><label>项目经理：</label>${bizPurchaseMainPanel.itemManager }</td>
				</tr>
				<tr>
					<td style="width:50%"><label>采购单编号：</label>${ bizPurchaseMainPanel.purchaseCode }</td>
					<td style="width:50%"><label>采购类型：</label>
						${fns:getDictLabel(bizPurchaseMainPanel.purchaseType, 'main_material_type', '')}
					</td>
				</tr>
				<tr>
					<td style="width:50%"><label>申请人：</label>${ bizPurchaseMainPanel.applyName }</td>
					<td style="width:50%"><label>提交申请时间：</label>
						<fmt:formatDate value="${ bizPurchaseMainPanel.applyTime }" pattern="yyyy-MM-dd HH:mm:ss"/>
					</td>
				</tr>
				<tr>
					<td style="width:50%"><label>累计数量：</label>${bizPurchaseMainPanel.purchaseCountTotal }</td>
					<td style="width:50%"><label>标配数量：</label>${bizPurchaseMainPanel.standardCountTotal }【标准数量 = 合同面积 * 0.47  （合同面积71平米以下按71平米计算）】</td>
				</tr>
				<tr>
					<td style="width:50%"><label>废弃原因：</label>
						<c:if test="${bizPurchaseMainPanel.status eq 21}">
							${bizPurchaseMainPanel.statusDescribe}
						</c:if>
					</td>
					<td style="width:50%"><label>超定额原因：</label>
						<c:if test="${bizPurchaseMainPanel.overReasonType =='1'}">变更原因</c:if>
						<c:if test="${bizPurchaseMainPanel.overReasonType =='2'}">其他原因</c:if>
					</td>
				</tr>
				<tr>
					<td style="width:50%"><label>超定额备注：${bizPurchaseMainPanel.overReasonWords} </label></td>
					<td style="width:50%"><label>超定额图片：</label><%--<a href="${ctx}/purchase/bizPurchaseMainPanel/seeOverPhoto?id=${bizPurchaseMainPanel.id}">查看图片</a>--%>
					<a href="#" data-toggle="modal" data-target="#myModal-photo"  onclick="ajaxGetImgsOver('${bizPurchaseMainPanel.id}')">查看图片</a>
					</td>
				</tr>
				<tr>
					<td style="width:50%"><label>备注：</label>${ bizPurchaseMainPanel.remarks }</td>
					<td>
						<ul><li class="btns"><input id="btnPhoto" class="btn btn-primary" data-toggle="modal" data-target="#myModal-photo" type="button"  value="查看收货单" onclick="ajaxGetImgs('${bizPurchaseMainPanel.id}')"/></li></ul>
						<input type="hidden" id="bizPurchaseMainPanelId" value="${bizPurchaseMainPanel.id }">
					</td>
				</tr>
			</table>
	
		<table id="contentTable" class="table table-striped table-bordered table-condensed">
			<thead>
				<tr>
					<th>序号</th>
					<th>商品编号 </th>
					<th>品牌 </th>
					<th>商品名称</th>
					<th>规格型号</th>
					<th>申请数量</th>
					<th>已收货数量</th>
					<th>欠货数量</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach items="${mainPanels}" var="mainPanel" varStatus="status">
				<tr>
					<td>
						${status.index+1} 
					</td>
					<td>
						${mainPanel.mainMaterialsCode}
					</td>
					<td>
						${mainPanel.brands} 
					</td>
					<td>
						${mainPanel.mainMaterialsName}
					</td>
					<td>
						${mainPanel.specifications}
					</td>
					<td>
						${mainPanel.mainMateCount}
					</td>
						<c:if test="${mainPanel.receivedCount == mainPanel.mainMateCount }">
							<td>
								${mainPanel.receivedCount}
							</td>
						</c:if>
						<c:if test="${mainPanel.receivedCount != mainPanel.mainMateCount }">
							<td style="color:blue">
								${mainPanel.receivedCount}
							</td>
						</c:if>
						<c:if test="${mainPanel.owedCount != 0}">
							<td style="color:red">
								${mainPanel.owedCount}
							</td>
						</c:if>
						<c:if test="${mainPanel.owedCount == 0}">
							<td>
								${mainPanel.owedCount}
							</td>
						</c:if>
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</div>	
	<script type="text/javascript">
		/*$("#btnPhoto").click(function(){
			var id = $("#bizPurchaseMainPanelId").val();
			window.location.href = "${ctx}/purchase/bizPurchaseMainPanel/seePhoto?id="+id;
		});*/
        function ajaxGetImgs(id){
            $.ajax({
                type: 'POST',
                dataType: 'json',
                url: '${ctx}/purchase/bizPurchaseMainPanel/seePhoto',
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
        function ajaxGetImgsOver(id){
            $.ajax({
                type: 'POST',
                dataType: 'json',
                url: '${ctx}/purchase/bizPurchaseMainPanel/seeOverPhoto',
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