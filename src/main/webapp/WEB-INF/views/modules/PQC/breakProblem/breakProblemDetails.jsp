<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>质检报告</title>
	<meta name="decorator" content="default"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/reset.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/lib/swiper-3.3.1.min.css">
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/header.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/Manager/css/photo.css"/>
	<script type="text/javascript">
        $(document).ready(function() {

        });
        function page(n,s){
            $("#pageNo").val(n);
            $("#pageSize").val(s);
            $("#searchForm").submit();
            return false;
        }

        window.onload = function(){
            $.ajax({
                url:"${ctx}/bizorderqcbill/bizOrderQcBill/kind",
                type : "post",
                success : function(data){
                    var htmls = "";
                    for(var i = 0; i < data.length; i++){
                        htmls += "<option value='"+data[i].id+"'>"+data[i].qcCheckKindName+"</option>";
                    }
                    $("#qcCheckKindId").append(htmls);
                }
            });
        };


        function  exportDetailed(){
            $("#searchForm").attr("action","${ctx}/pqc/breakProblem/breakProblem/exportDetailed");
            $("#searchForm").submit();
            $("#searchForm").attr("action","${ctx}/pqc/breakProblem/breakProblem/breakProblemDetails");
        }




            function goToHistory(){
                var str=document.referrer;//上一个点击进来的页面
                if(str!=null){
                    str = str.split("?")[0].split("/");
                    var actionName = str[(str.length-1)];//获取action名字
                    if(actionName=="MyText.action"){
                        location = "MyText.action";//返回MyText
                    }else if(actionName=="YouText.action"){
                        var url = "YouText.action"//返回YouText
                        location = url;
                    }else{
                        history.go(-1);
                    }
                }else{
                    alert("这个页面是初始页面");
                }
            }




	</script>
</head>
<body>
<ul class="nav nav-tabs">
	<%-- <li class="active"><a href="${ctx}/bizorderqcbill/bizOrderQcBill/report?orderId=${bizQcBill.orderId}">返回</a></li> onclick="history.go(-1)" --%>
	<li class="active"><a href="#" onclick="history.go(-1)">返回</a></li>
</ul>



<div class="breadcrumb form-search">

	<table style="width:100%" align="center" valign="center">
		<tr>
			<td colspan="3" style="width:100%"><label>门店：</label>
				${breakProblemZhu.storeName }
			</td>
		</tr>
		<tr>
			<td colspan="3" style="width:100%"><label>质检项分类：</label>
				${breakProblemZhu.qcCheckKindName }
			</td>
		</tr>
		<tr>
			<td colspan="3" style="width:100%"><label>检查项：</label>
				${breakProblemZhu.qcCheckItemName }
			</td>
		</tr>
		<tr>
			<td colspan="3" style="width:100%"><label>违规形式：</label>
				${breakProblemZhu.breakDescribe }
			</td>
		</tr>

	</table>

</div>
<form:form id="searchForm" modelAttribute="breakProblem" action="${ctx}/pqc/breakProblem/breakProblem/breakProblemDetails" method="post" class="breadcrumb form-search">
	<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
	<input id="qcCheckItemBreakId" name="qcCheckItemBreakId" type="hidden" value="${breakProblem.qcCheckItemBreakId}"/>
	<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
	<li class="btns"><input class="btn btn-primary clearBtn" type="button" value="导出" onclick="exportDetailed()"/></li>
	</ul>
</form:form>

<table id="contentTable" class="table table-striped table-bordered table-condensed">
	<thead>
	<tr>
		<th>序号</th>
		<th>质检员提交报告时间</th>
		<th><span style="text-align: center;">工程模式</span></th>
		<th>区域</th>
		<th>小区</th>
		<th>客户</th>
		<th>项目经理</th>
		<th>质检员</th>
		<th>责任人-项目经理</th>
		<th>责任人-工人</th>
	</tr>
	</thead>
	<tbody>
	<c:forEach items="${page.list}" var="itemDetails" varStatus="status">
		<tr>
			<td>
					${status.index+1}
			</td>
			<td>
				<fmt:formatDate value="${itemDetails.quCreateDate}" pattern="yyyy-MM-dd"/>
			</td>
			<td>
					${itemDetails.projectModeName}
			</td>
			<td>
					${itemDetails.enginDepartName}
			</td>

			<td>
					${itemDetails.customerAddr}
			</td>
			<td>
					${itemDetails.customerName}
			</td>
			<td>
					${itemDetails.itemManager}
			</td>
			<td>
					${itemDetails.orderInspector}
			</td>
			<td>
					${itemDetails.mnagerPerson}
			</td>
			<td>
					${itemDetails.workerGroupName}
			</td>

		</tr>
	</c:forEach>
	</tbody>
</table>

<div class="pagination">${page}</div>
<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/calcRootFontSize.js"></script>
<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/utils/history.js"></script>
<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/lib/swiper-3.3.1.jquery.min.js"></script>
<script type="text/javascript" src="${ctxStatic}/mobile/modules/Manager/js/home/swiper.js"></script>
</body>
</html>