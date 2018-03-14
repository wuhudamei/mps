<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <title>订单安装项不合格详情</title>
    <meta name="decorator" content="default"/>


    <link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Worker/css/globalUtil.css"/>
    <script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/history.js"></script>
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/modules/orderinstallacceptquery/css/reset.css" />
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/modules/orderinstallacceptquery/css/style.css" />
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/modules/orderinstallacceptquery/css/modal.css" />






</head>
<body onload="IniEvent()">
    <div class="wrap">
        <div class="header"><span>订单安装项不合格详情</span></div>
        <!--/.header-->
        <div class="content clearfixOrder">
            <div class="content-block">
                <div class="content-block-title"><i class="icontitle icon01">icon</i>订单基本信息</div>
                <div class="content-block-item">
                    <div class="item clearfixOrder">
                        <div class="col-md-6 pull-left">
                            <span class="item-name">门&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;店：</span> ${fns:getStoreLabel(orderDetail.storeId,"")}
                        </div>
                        <div class="col-md-6 pull-left">
                            <span class="item-name">工程模式：</span> ${fns:getDictLabel(orderDetail.projectMode, 'project_mode', '')}
                        </div>
                    </div>
                    <div class="item clearfixOrder">
                        <div class="col-md-6 pull-left">
                            <span class="item-name">订单编号：</span>${orderDetail.orderNumber}
                        </div>
                        <div class="col-md-6 pull-left">
                            <span class="item-name">客户姓名：</span>${orderDetail.customerName}
                        </div>
                    </div>
                    <div class="item clearfixOrder">
                        <div class="col-md-6 pull-left">
                            <span class="item-name">客户地址：</span>${orderDetail.customerAddressDetaill}
                        </div>
                        <div class="col-md-6 pull-left">
                            <span class="item-name">项目经理：</span>${orderDetail.itemManager}
                        </div>
                    </div>
                </div>
                <!--/.content-block-item-->
            </div>
            <!--/.content-block-->
            <div class="content-block">
                <div class="content-block-title border-0"><i class="icon icon02">icon</i>明细信息：</div>
                <table class="item-table" id = "tblMain">
                    <thead>
                        <tr class="bg-grey">
                            <th>安装项名称</th>
                            <th>验收提交时间</th>
                            <th>不合格原因</th>
                            <th>备注</th>
                            <th>图片</th>
                        </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${listPlanLog}" var="plan">
                        <tr>
                            <td>${plan.installItemName}</td>
                            <td><fmt:formatDate value="${plan.unqualifiedAcceptTime}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></td>
                            <td>${plan.unqualifiedReasonConfigure}</td>
                            <td>${plan.acceptRemarks}</td>

                            <td><a href="#" data-toggle="modal" data-target="#myModal-photo"   onclick="ajaxGetImgs('${plan.id}')">图片</a></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <!--/.item-table-->
            </div>
            <!--/.content-block-->
        </div>
        <!--/.content-->
        <div class="content clearfixOrder">
            <input class="btn btns" type="button" value="返回"
                   onclick="history.go(-1)" />
        </div>





    </div>
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

        function ajaxGetImgs(id){
            $.ajax({
                type: 'POST',
                dataType: 'json',
                url: '${ctx}/bizorderinstallplan/orderinstallacceptquery/photo',
                data: {
                    id:id,
                    acceptType:"2"
                },
                success: function (data) {

                    var ainput='';
                    if (null !=data && data.length != 0) {
                        for (var i = 0; i < data.length; i++) {
                            ainput +=	 '<a  class="swiper-slide " ><img src="' + data[i] + '" ></a>';
                        }
                    }else{
                        ainput ='<a  class="swiper-slide " ><img src="${ctxStatic}/images/nopic.jpg" ></a>';
                    }
                    $("#inputId").html(ainput);
                }
            })

        }


        $('.btn-primary').on('click', function() {
            $('.modal-overlay').addClass('_in');
        })
        $('#btn-close').on('click', function() {
            $('.modal-overlay').removeClass('_in');
        })


        function IniEvent() {
            var tbl = document.getElementById("tblMain");
            var trs = tbl.getElementsByTagName("tr");
            for (var i = 0; i < trs.length; i++) {
                trs[i].onclick = TrOnClick;
            }
        }

        function TrOnClick() {
            var tbl = document.getElementById("tblMain");
            var trs = tbl.getElementsByTagName("tr");
            for (var i = 0; i < trs.length; i++) {
                if (trs[i] == this) { //判断是不是当前选择的行
                    trs[i].style.background = "#f9f9f9";
                } else {
                    trs[i].style.background = "white";
                }
            }
        }
    </script>





</body>



</html>