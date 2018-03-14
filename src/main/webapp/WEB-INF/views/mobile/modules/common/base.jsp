<%--
  Created by IntelliJ IDEA.
  User: joseph
  Date: 2017/6/13
  Time: 14:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="shiro" uri="/WEB-INF/tlds/shiros.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fns" uri="/WEB-INF/tlds/fns.tld" %>
<%@ taglib prefix="fnc" uri="/WEB-INF/tlds/fnc.tld" %>
<%@ taglib prefix="sys" tagdir="/WEB-INF/tags/sys" %>
<%@ taglib prefix="act" tagdir="/WEB-INF/tags/act" %>
<%@ taglib prefix="cms" tagdir="/WEB-INF/tags/cms" %>
<c:set var="ctx" value="${pageContext.request.contextPath}${fns:getAdminPath()}"/>
<c:set var="ctxStatic" value="${pageContext.request.contextPath}/static"/>
<c:set var="root" value="${pageContext.request.contextPath}"/>
<html>
<head>

</head>


<link type="text/css" rel="stylesheet" href="${ctxStatic}/mobile/modules/Manager/css/waitMe.css">
<link rel="stylesheet" type="text/css"
      href="${ctxStatic}/mobile/modules/pqc/css/globalUtil.css" />

<div style="background:rgba(0,0,0,.4);z-index:9;" class="g-mask undis">
    <div class="alertMask">
        <div class="maskWrapper">
            <p class="col_3 maskTit">提示</p>
            <div class="font28 col_6 maskContent"></div>
            <div class="maskBtns clearfix">
                <a class="maskBtn font33 col_f" href="javascript:;">确定</a>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/jquery-2.1.1.min.js"></script>
<script type="text/javascript"
        src="${ctxStatic}/mobile/modules/Manager/js/utils/waitMe.js"></script>

<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/global.js"></script>

<script type="text/javascript" src="${ctxStatic}/mobile/modules/pqc/js/applyCheckBase/CommonJs/dataLoading.js"></script>
<body>
<script type="text/javascript">
    var baseUrl = "${ctx}/app/";



</script>


</body>
</html>
