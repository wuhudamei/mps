<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>采购单管理</title>
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
			<li class="active"><a href="${ctx}/purchase/bizPurchase/sand/details?id=${bizPurchaseVo.id}">沙子水泥采购单明细</a></li>
		</ul>
		<div>
			<a class="btn" href="javascript:" onclick="goToHistory()">返回</a>
		</div>
		<div>
				<div class="col-md-12 column">
					<div >
						<h3>
							<center>
								大美装饰管理平台--沙子水泥采购单-${ bizPurchaseVo.purchaseCode }
							</center>
						</h3>
					</div>
				</div>
				<table style="width:100%" align="center" valign="center" class="table table-striped table-bordered table-condensed">
					<tr>
						<td colspan="2" style="width:100%"><label>项目地址：</label>
							${bizPurchaseVo.customerAddress }-${bizPurchaseVo.customerName }
						</td>
					</tr>
					<tr>
						<td style="width:50%"><label>小区名称：</label>
							${bizPurchaseVo.communityName }
						</td>
						<td style="width:50%"><label>楼牌号：</label>
							${bizPurchaseVo.buildNumber }号楼-${bizPurchaseVo.buildUnit }单元-${bizPurchaseVo.buildRoom }
						</td>
					</tr>
					<tr>
						<td style="width:50%"><label>采购单编号：</label>
							${bizPurchaseVo.purchaseCode }
						</td>
						<td style="width:50%"><label>采购单类型：</label>
							${fns:getDictLabel(bizPurchaseVo.purchaseType, 'main_material_type', '')}
						</td>
					</tr>
					<tr>
						<td style="width:50%"><label>期望到货日期：</label>
							<fmt:formatDate value="${bizPurchaseVo.applyReceiveTime }" pattern="yyyy-MM-dd"/>
						</td>
						<td style="width:50%"><label>项目经理：</label>${bizPurchaseVo.itemManager }</td>
					</tr>
					<tr>
						<td style="width:50%"><label>申请人：</label>
							${bizPurchaseVo.applyName }
						</td>
						<td style="width:50%"><label>提交申请时间：</label>
							<fmt:formatDate value="${bizPurchaseVo.applyTime }" pattern="yyyy-MM-dd HH:mm:ss "/>
						</td>
					</tr>
					<tr>
						<td style="width:50%"><label>是否有电梯：</label>
							<c:if test="${bizPurchaseVo.isElevator eq 1}">
								是
							</c:if>
							<c:if test="${bizPurchaseVo.isElevator ne 1 }">
								否
							</c:if>
						</td>
						<td style="width:50%"><label>楼层：</label>
								${bizPurchaseVo.upstairsFloor }层
						</td>
					</tr>
					<tr>
						<td style="width:50%"><label>备注：</label>${bizPurchaseVo.remarks }</td>
						<td>
							<li class="btns"><input id="btnPhoto" class="btn btn-primary" data-toggle="modal" data-target="#myModal-photo" type="button"  value="查看收货单" onclick="ajaxGetImgs('${bizPurchaseVo.id}')"/></li>
							<input type="hidden" id="bizPurchaseVoId" value="${bizPurchaseVo.id }">
						</td>
					</tr>
				</table>
		
			<table id="contentTable" class="table table-striped table-bordered table-condensed">
				<thead>
					<tr>
						<th>序号</th>
						<th>商品编号 </th>
						<th>材料类别</th>
						<th>品牌</th>
						<th>商品名称</th>
						<th>规格 </th>
						<th>单位</th>
						<th>单价</th>
						<th>申请数量</th>
						<th>已收货数量</th>
						<th>欠货数量</th>
					</tr>
				</thead>
				<tbody>
				<c:forEach items="${goodsList}" var="goods" varStatus="status">
					<tr>
						<td>
							${status.index+1} 
						</td>
						<td>
							${goods.auxiMateCode} 
						</td>
						<td>
							${goods.categoryName}
						</td>
						<td>
							${goods.brand} 
						</td>
						<td>
							${goods.name}
						</td>
						<td>
							${goods.specifications}
						</td>
						<td>
							${goods.unit}
						</td>
						<td>
							${goods.price}
						</td>
						<td>
							${goods.auxiMateCount}
						</td>
						<c:if test="${goods.receivedAuxiMateCount == goods.auxiMateCount }">
							<td>
								${goods.receivedAuxiMateCount}
							</td>
						</c:if>
						<c:if test="${goods.receivedAuxiMateCount != goods.auxiMateCount }">
							<td style="color:blue">
								${goods.receivedAuxiMateCount}
							</td>
						</c:if>
						<c:if test="${goods.owedAuxiMateCount != 0}">
							<td style="color:red">
								${goods.owedAuxiMateCount}
							</td>
						</c:if>
						<c:if test="${goods.owedAuxiMateCount == 0}">
							<td>
								${goods.owedAuxiMateCount}
							</td>
						</c:if>
					</tr>
				</c:forEach> 
				</tbody>
			</table>
	</div>
	<script type="text/javascript">
		/*$("#btnPhoto").click(function(){
			var id = $("#bizPurchaseVoId").val();
			window.location.href = "${ctx}/purchase/bizPurchase/seePhoto?id="+id;
		});*/
        function ajaxGetImgs(id){
            $.ajax({
                type: 'POST',
                dataType: 'json',
                url: '${ctx}/purchase/bizPurchase/seePhoto',
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