<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>售后问题详情</title>
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
                        <li class="fl"><span>门店 :</span><span class="ml12">${fns:getStoreLabel(bizCusServiceProblem.storeId, '')}</span></li>
<%--                         <fmt:formatDate  value="${preComlaint.createDate}" pattern="yyyy-MM-dd HH:mm:ss"> </fmt:formatDate> --%>
                        <li class="fl ml134"><span>订单编号：</span><span class="ml12">${bizCusServiceProblem.orderCode} </span></li>
                    </ul>
                     <ul class="clearfix">
                        <li class="fl "><span>地址：</span><span class="ml12">${bizCusServiceProblem.customerAddress} </span></li>
                        <li class="fl ml134"><span>客户姓名 :</span><span class="ml12">${bizCusServiceProblem.customerName}</span></li>
                        </li>
                    </ul>
                    <ul class="clearfix">
                        <li class="fl "><span>客户手机号 :</span><span class="ml12">${bizCusServiceProblem.customerMobile}</span>
                        <li class="fl ml134"><span>问题创建时间 :</span><span class="ml12"> <fmt:formatDate
                                value="${bizCusServiceProblem.problemCreateDatetime}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></span></li>
                       
<%--                         <li class="fl"><span>项目经理 :</span><span class="ml12">${bizCusServiceProblem.contractorName}</span></li> --%>
<%--                         <li class="fl ml134"><span>项目经理手机 :</span><span class="ml12">${bizCusServiceProblem.contractorMobile}</span> --%>
                        </li>
                    </ul>
                   
<!--                     <ul class="clearfix"> -->
<%--                         <li class="fl"><span>质检 :</span><span class="ml12">${bizCusServiceProblem.supervisorName}</span></li> --%>
<!--                         <li class="fl ml134 w61"><span class="ml12">质检手机号 :</span><span -->
<%--                                 class="ml12 content_info_cont">${bizCusServiceProblem.supervisorMobile}</span></li> --%>
<!--                     </ul> -->

                    <ul class="clearfix">
                        <li class="fl"><span>责任部门：</span><span class="ml12"> ${bizCusServiceProblem.liableDepartmentCode}</span></li>
                        <li class="fl ml134 "><span class="ml12">问题类别:</span><span
                                class="ml12">${bizCusServiceProblem.questionType1}</span></li>
                    </ul>

                    <ul class="clearfix">
                        <li class="fl"><span>问题类型：</span><span class="ml12"> ${bizCusServiceProblem.questionType2}</span></li>
                        <li class="fl ml134 "><span class="ml12">投诉类型：</span><span
                                class="ml12">${bizCusServiceProblem.complaintTypeName}</span></li>
                    </ul>
                    <ul class="clearfix">
                        <li class="fl"><span>重要程度：</span><span class="ml12"> ${bizCusServiceProblem.importantDegreeCode1}</span></li>
                        <li class="fl ml134 "><span class="ml12">工单来源：</span><span
                                class="ml12">${bizCusServiceProblem.workSource}</span></li>
                    </ul>
     
                    <ul class="clearfix ">
                        <li class="fl"><span class="fl">附件 :</span><span class="ml12 content_info"><a
                                href="${ctx}/cusserviceproblem/bizCusServiceProblem/viewPicsById?id=${bizCusServiceProblem.id}">点击查看图片</a></span></li>
                        <li class="fl ml134 "><span class="ml12">问题内容：</span><span
                                class="ml12">${bizCusServiceProblem.problemDescribe}</span></li>
              
                    </ul>
                </div>

            </div>
            <div class="basic  processesLog">
                <h2 class="col_blue mb20 mt33"><i class="icon_line"></i>
                    <p class="pl16">流程日志</p></h2>

                <div class="process_warp mt40">
                    <h4 class="col_333 mb14">
                        处理记录----${fns:getDictLabel(bizCusServiceProblem.status,'status_cus','')}</h4>
                    <div class="basic_content">
                        <ul class="clearfix">
                            <li class="fl"><span>操作时间 :</span><span class="ml12"><fmt:formatDate
                                    value="${bizCusServiceProblem.statusdatetime}"
                                    pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate> </span></li>

                        </ul>

                        <ul class="clearfix info_cont">
                            <li class="fl"><span class="fl">内容 :</span>
                            <span class="ml12 content_info">${bizCusServiceProblem.statusdescribe}</span></li>
                        </ul>

                    </div>
                </div>


            </div>
            <div class="Page_back">
                <input type="button" value="返回" class="page_backBtn"
                       onclick="window.location.href='${ctx}/cusserviceproblem/bizCusServiceProblem/list'">
            </div>
        </div>
    </div>

</div>
</body>
</html>