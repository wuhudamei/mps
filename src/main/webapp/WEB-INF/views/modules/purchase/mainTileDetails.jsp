<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>墙地砖明细</title>
	<meta name="decorator" content="default"/>
	<link href="${ctxStatic}/modules/popUp/css/style.css" rel="stylesheet" type="text/css" />
	<link href="${ctxStatic}/modules/popUp/css/swiper.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="${ctxStatic}/vendor/jquery/jquery-2.2.4.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/vendor/bootstrap/js/bootstrap.min.js" charset="UTF-8"></script>
	<script type="text/javascript" src="${ctxStatic}/vendor/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
	<script src="${ctxStatic}/vendor/jquery/swiper.min.js"></script>
	<!-- <script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script> -->
</head>
<body>
		<ul class="nav nav-tabs">
			<li class="active"><a href="${ctx}/purchase/bizPurchaseMainTile/tileList">墙地砖申请列表</a></li>
			<li class="active"><a href="${ctx}/purchase/bizPurchaseMainTile/mainTileDetails?id=${bizPurchaseMainTile.id}">墙地砖明细</a></li>
		</ul>
		<div>
			<a class="btn" href="javascript:" onclick="goToHistory()">返回</a>
		</div>
		
	
		<div>
			<div class="col-md-12 column">
				<div >
					<h3>
						<center>
							美得你--采购单-${ bizPurchaseMainTile.purchaseCode }
						</center>
					</h3>
				</div>
			</div>
			<table style="width:100%" align="center" valign="center" class="table table-striped table-bordered table-condensed">
				<tr>
					<td colspan="2" style="width:100%"><label>地址：</label>
						${bizPurchaseMainTile.customerAddress }<%-- ${bizPurchaseMainTile.communityName }-${bizPurchaseMainTile.buildNumber }-${bizPurchaseMainTile.buildUnit }-${bizPurchaseMainTile.buildRoom }-  --%>${bizPurchaseMainTile.customerName } 
					</td>
				</tr>
				<tr>
					<td style="width:50%"><label>期望到货日期：</label>
						<fmt:formatDate value="${bizPurchaseMainTile.applyReceiveTime }" pattern="yyyy-MM-dd"/>
					</td>
					<td style="width:50%"><label>项目经理：</label>${bizPurchaseMainTile.itemManager }</td>
				</tr>
				<tr>
					<td style="width:50%"><label>采购单编号：</label>${ bizPurchaseMainTile.purchaseCode }</td>
					<td style="width:50%"><label>采购类型：</label>
						${fns:getDictLabel(bizPurchaseMainTile.purchaseType, 'main_material_type', '')}
					</td>
				</tr>
				<tr>
					<td style="width:50%"><label>申请人：</label>${ bizPurchaseMainTile.applyName }</td>
					<td style="width:50%"><label>提交申请时间：</label>
						<fmt:formatDate value="${ bizPurchaseMainTile.applyTime }" pattern="yyyy-MM-dd HH:mm:ss"/>
					</td>
				</tr>
				<tr>
					<td style="width:50%"><label>备注：</label>${ bizPurchaseMainTile.remarks }</td>
					<td>
						<li class="btns"><input id="btnPhoto" class="btn btn-primary" data-toggle="modal" data-target="#myModal-photo" type="button"  value="查看收货单" onclick="ajaxGetImgs('${bizPurchaseMainTile.id}')"/></li>
						<input type="hidden" id="bizPurchaseMainTileId" value="${bizPurchaseMainTile.id }">
					</td>
				</tr>
			</table>
		
	
	
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>序号</th>
				<th>项目名称 </th>
				<th>位置</th>
				<th>品牌</th>
				<th>型号</th>
				<th>规格</th>
				<th>单位 </th>
				<th>数量 </th>
				<th>含损耗数量 </th>
				<th>实发数量 </th>
				<th>已收货数量</th>
				<th>欠货数量</th>
				<th>备注 </th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${mainTiles}" var="mainTile" varStatus="status">
			<tr>
				<td>
					${status.count} 
				</td>
				<td>
					<%-- ${mainTiles.mainMateType}  --%>
					${fns:getDictLabel(mainTile.mainMateType,'main_material_type', '')}
				</td>
				<td>
					${mainTile.position}
				</td>
				<td>
					${mainTile.brandCombo}
				</td>
				<td>
					${mainTile.model}
				</td>
				<td>
					${mainTile.specification}
				</td>
				<td>
					${mainTile.unit}
				</td>
				<td>
					${mainTile.mainMateCount}
				</td>
				<td>
					${mainTile.includLossCount}
				</td>
				<td>
					${mainTile.applyCount}
				</td>
						<c:if test="${mainTile.receivedCount == mainTile.applyCount }">
									<td>
										${mainTile.receivedCount}
									</td>
						</c:if>
						<c:if test="${mainTile.receivedCount != mainTile.applyCount }">
							<td style="color:blue">
								${mainTile.receivedCount}
							</td>
						</c:if>
						<c:if test="${mainTile.owedCount != 0}">
							<td style="color:red">
								${mainTile.owedCount}
							</td>
						</c:if>
						<c:if test="${mainTile.owedCount == 0}">
							<td>
								${mainTile.owedCount}
							</td>
					</c:if>
				<td>
					${mainTile.remarks}
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	</div>	
	<script type="text/javascript">
		/*$("#btnPhoto").click(function(){
			var id = $("#bizPurchaseMainTileId").val();
			window.location.href = "${ctx}/purchase/bizPurchaseMainTile/seePhoto?id="+id;
		});*/
        function ajaxGetImgs(id){
            $.ajax({
                type: 'POST',
                dataType: 'json',
                url: '${ctx}/purchase/bizPurchaseMainTile/seePhoto',
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