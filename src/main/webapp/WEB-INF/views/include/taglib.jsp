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
<%--页面返回的方法--%>
<script src="${ctxStatic}/return/history.js" type="text/javascript"></script>

<script type="text/javascript">
var baseAppUrl ="${ctx}/app/";
	function onFormSubmit(form){
		$(form).find("select").attr("disabled",false);
	}
	function setSort(value){
		var sort = $("#frontSort").val();
		var isDesc = sort.indexOf("DESC");
		if(isDesc==-1 && sort==value){
			$("#frontSort").val(value+" DESC");
		}else{
			$("#frontSort").val(value);
		}
		$("#btnSubmit").click();
	};

</script>