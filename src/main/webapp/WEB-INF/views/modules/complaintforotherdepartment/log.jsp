<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>投诉日志</title>
    <link rel="stylesheet" href="${ctxStatic} /modules/orderReportRelatedContract/css/packahesChoice.css">
    <style type="text/css">
        .Page_back {
            float: right;
            margin-top: 20px;
            margin-bottom: 40px;
        }

        .Page_back .page_backBtn {
            border: 1px solid #1296db;
            background: #1296db;
            color: #fff;
            padding: 8px 20px;

        }
    </style>
</head>
<body>
<div class="packagesChoice_warp">
    <div class="packagesChoice_warpper">
        <div class="packagesChoice_content">
            <div class="basic">
                <h2 class="col_blue mb20 mt33"><i class="icon_line"></i>
                    <p class="pl16">基本信息</p></h2>
                <div class="basic_content">
                    <ul class="clearfix">
                        <li class="fl"><span>门店 :</span><span class="ml12">${preComlaint.storeName}</span></li>
                        <li class="fl ml134"><span>问题创建时间 :</span><span class="ml12"> <fmt:formatDate
                                value="${preComlaint.createDate}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></span></li>
                    </ul>
                    <ul class="clearfix">
                        <li class="fl"><span>工程地址 :</span><span class="ml12">${preComlaint.customerAddress}</span></li>
                        <li class="fl ml134"><span>订单编号 :</span><span class="ml12">${preComlaint.orderNumber}</span>
                        </li>
                    </ul>
                    <ul class="clearfix">
                        <li class="fl"><span>客户姓名 :</span><span class="ml12">${preComlaint.customerName}</span></li>
                        <li class="fl ml134"><span>客户手机号 :</span><span class="ml12">${preComlaint.customerPhone}</span>
                        </li>
                    </ul>
                    <ul class="clearfix">
                        <li class="fl"><span>项目经理 :</span><span class="ml12">${preComlaint.managerName}</span></li>
                        <li class="fl ml134 w61"><span class="fl">质检员 :</span><span
                                class="ml12 content_info_cont">${preComlaint.pqcName}</span></li>
                    </ul>

                    <ul class="clearfix info_cont">
                        <li class="fl"><span class="fl">问题描述 :</span><span
                                class="ml12 content_info">${preComlaint.complaintDescribe}</span></li>
                    </ul>
                    <ul class="clearfix info_cont">
                        <li class="fl"><span class="fl">附件 :</span><span class="ml12 content_info"><a style="background-color: #808ea6"
                                href="${ctx}/complaintforotherdepartment/complaintForOtherDepartMent/photo?id=${preComlaint.id}">查看图片</a></span></li>
                    </ul>
                </div>

            </div>

                <div class="basic  processesLog">
                    <h2 class="col_blue mb20 mt33"><i class="icon_line"></i>
                        <p class="pl16">流程日志</p></h2>

                    <div class="process_warp mt40">
                        <h4 class="col_333 mb14">
                            处理记录----${fns:getDictLabel(preComlaint.complaintStatus,'preComplaintStatus','')}</h4>
                        <div class="basic_content">
                            <ul class="clearfix">
                                <li class="fl"><span>操作时间 :</span><span class="ml12"><fmt:formatDate
                                        value="${preComlaint.statusDateTime}"
                                        pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate> </span></li>

                            </ul>

                            <ul class="clearfix info_cont">
                                <li class="fl"><span class="fl">内容 :</span><span
                                        class="ml12 content_info">${preComlaint.statusDescribe}</span></li>
                            </ul>

                        </div>
                    </div>


                </div>

            <div class="Page_back">
                <input type="button" value="返回" class="page_backBtn"  onclick="history.go(-1)"/>
                       <%--onclick="window.location.href='${ctx}/complaintforotherdepartment/complaintForOtherDepartMent/list'">--%>
            </div>
        </div>
    </div>

</div>
</body>
</html>