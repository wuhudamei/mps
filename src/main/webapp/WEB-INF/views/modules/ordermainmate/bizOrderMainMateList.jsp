<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>员工信息管理</title>
	<meta name="decorator" content="default"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/hUploadify/css/Huploadify.css"/>
	<script type="text/javascript" src="${ctxStatic}/hUploadify/js/jquery.Huploadify.js"></script>
	
	<style type="text/css">
	 .delfilebtn{display: none;}
	</style>
	
	<script type="text/javascript">

	
	function page(n,s){
		$("#pageNo").val(n);
		$("#pageSize").val(s);
		$("#searchForm").submit();
    	return false;
    }
	
$(document).ready(function() {
	$("#uploadFile").Huploadify({
        //是否自动上传 true or false
        auto:true,
        //超时时间上传成功后，将等待服务器的响应时间。
        //在此时间后，若服务器未响应，则默认为成功(因为已经上传,等待服务器的响应) 单位：秒
        //'successTimeout':99999, 
		multi:true,
        //上传地址
        uploader:"${ctx}/ordermainmate/bizOrderMainMate/uploadFile?orderId=${bizOrderMainMate.orderId}",
        //浏览将要上传文件按钮的背景图片路径
        //'buttonImage':'${pageContext.request.contextPath}/uplodify/background.jpg',

        //浏览按钮的宽度
        width:'100',

        //浏览按钮的高度
        height:'32',

        //在浏览窗口底部的文件类型下拉菜单中显示的文本
        fileTypeDesc:'支持的格式：',

        //允许上传的文件后缀
        fileTypeExts:'*.xls;*.xlsx',
        fileSizeLimit:'10MB',

        //允许上传的文件的最大数量。当达到或超过这个数字，onSelectError事件被触发。
        queueSizeLimit: 1,
        uploadLimit:1,
        multi:false,
        buttonText:'导入墙地砖',
        //上传到服务器，服务器返回相应信息到data里
        onUploadSuccess:function(file, data, response){
            if(data == "success"){
            
            	$("#searchForm").attr("action", "${ctx}/ordermainmate/bizOrderMainMate/list");
            	$("#searchForm").submit();
            }
        },

      //当单个文件上传出错时触发
        onUploadError: function (file, errorCode, errorMsg, errorString) { 
        	alert("导入失败");
        }
    }); 
	
	
	tiaoshu();
	
}); 
function tiaoshu(){
	
	var messageid=$("#messageid").val();
	if(messageid!=null&&messageid!=""){
		
	alert(messageid);
	}
}

  function ajaxedicheck(varthis,wallId,istf){
// 	alert(varthis+":"+wallId+":"+istf)
    $("#searchForm").attr("action", "${ctx}/ordermainmate/bizOrderMainMate/ajaxUpdateistf?id="+wallId+"&iscountsquare="+istf);
    $("#searchForm").submit();

}
  
	function formOrderNum(){
		var v = $("#orderNumberid").val();
// 			alert(v);
		location.href = "${ctx}/bizmaterialschoicechangebill/bizMaterialsChoiceChangeBill/materialsChangeBillList?orderNumber="+v;
	}
	function gotofun(){
		var v = $("#orderId").val();
		var orderNumberid = $("#orderNumberid").val();
// 		alert(v)
		location.href = "${ctx}/projectOrderList/lookWallAndFloorz?&orderId="+v+"&orderNumber="+orderNumberid;
	}
	function exportWallFloor(){
		var orderId = $("#orderId").val();
		var orderNumberid = $("#orderNumberid").val();
		$("#searchForm").attr("action", "${ctx}/ordermainmate/bizOrderMainMate/exportWallFloor");
		   $("#searchForm").submit();
// 		alert(v)
// 		location.href = "${ctx}/ordermainmate/bizOrderMainMate/exportWallFloor?orderId="+orderId+"&orderNumber="+orderNumberid;
	}


</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/ordermainmate/bizOrderMainMate/list?orderId=${bizOrderMainMate.orderId}">墙地砖列表</a></li>
	</ul>
	
	<ul class="breadcrumb ul-form">
		<li >
			订单编号：${bizMaterialsChoiceBill.orderNumber }
		</li>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<li >
			客户姓名：${bizMaterialsChoiceBill.customerName }
		</li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<li >
			客户地址：${bizMaterialsChoiceBill.communityName }-${bizMaterialsChoiceBill.buildNumber }-${bizMaterialsChoiceBill.buildUnit }-${bizMaterialsChoiceBill.buildRoom }
						
		</li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<li >
			客户电话：${bizMaterialsChoiceBill.customerPhone }
		</li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<li >
			项目经理姓名：${bizMaterialsChoiceBill.itemManager }
		</li>
	</ul>
	<ul class="breadcrumb ul-form">
		<li class="btns">
<!-- 		/projectOrderList/preList -->
			<input id="btnCancel" class="btn" type="button" value="返回订单列表" onclick="javascript:window.location.href='${ctx}/order/order/list'"/>
		</li>
<%-- 		<c:if test="${isMaterialsChoiceBill == 0}"> --%>
		<li class="btns">
			<div id="uploadFile"></div>
		</li>
			<li class="btns">
			<input id="btnCancel1" class="uploadify-button" type="button" value="导出模板" onclick="exportWallFloor()"/>
		</li>
<%-- 		</c:if>  --%>
		<!-- 临时注销isMaterialsChoiceBill == 1 -->
		<c:if test="${isMaterialsChoiceBill == 4}">
		<li class="btns">
		<c:if test="${isxin==0}">
			<input id="" class="uploadify-button" type="button" value="墙地砖清单(新)" onclick = "gotofun()"/>
			</c:if>
		<c:if test="${isxin==5}">
			<input id="" class="uploadify-button" type="button" value="墙地砖清单" onclick = "gotofun()"/>
		</c:if>
		</li>
			<li class="btns">
			<input id="" class="uploadify-button" type="button" value="变更单" onclick = "formOrderNum()"/>
		</li>
		</c:if>
	</ul>
	
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>项目名称</th>
				<th>位置</th>
				<th>品牌及套餐</th>
				<th>型号</th>
				<th>属性</th>
				<th>供应商</th>
				<th>规格</th>
				<th>单位</th>
				<th>数量</th>
				<th>损耗系数</th>
				<th>含损耗数量</th>
<!-- 				<th>实发数量</th> -->
				<th>单位面积</th>
				<th>是否计算面积</th>
<!-- 				<th>备注</th> -->
				<th>已申请数量</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="bizOrderMainMate">
			<tr>
				<td>
					${fns:getDictLabel(bizOrderMainMate.mainMateType, 'main_material_type', '')}
				</td>
				<td>
					${bizOrderMainMate.position}
				</td>
				<td>
					${bizOrderMainMate.brandCombo}
				</td>
				<td>
					${bizOrderMainMate.model}
				</td>
				<td>
					${bizOrderMainMate.attribute}
				</td>
				<td>
					${bizOrderMainMate.supplier}
				</td>
				<td>
					${bizOrderMainMate.specification}
				</td>
				<td>
					${bizOrderMainMate.unit}
				</td>
				<td>
					${bizOrderMainMate.count}
				</td>
				<td>
					${bizOrderMainMate.lossxs}
				</td>
				<td>
					${bizOrderMainMate.includLossCount}
				</td>
<!-- 				<td> -->
<%-- 					${bizOrderMainMate.applyCounta} --%>
<!-- 				</td> -->
				<td>
					${bizOrderMainMate.unitsquare}
				</td>
				<td>
				<c:if test="${bizOrderMainMate.iscountsquare==1}">
					<input  onclick="ajaxedicheck(this,${bizOrderMainMate.id},0)" type="checkbox" checked="checked" ></input>
				</c:if>
				<c:if test="${bizOrderMainMate.iscountsquare==0||  bizOrderMainMate.iscountsquare == null || bizOrderMainMate.iscountsquare==''}">
					<input  onclick="ajaxedicheck(this,${bizOrderMainMate.id},1)" type="checkbox" ></input>
				</c:if>
				</td>
<!-- 				<td> -->
<%-- 					${bizOrderMainMate.remarks} --%>
<!-- 				</td> -->
				<td>
					${bizOrderMainMate.purchaseCount}
				</td>
				<td>
					<a href="${ctx}/ordermainmate/bizOrderMainMate/updateUnitsquare?id=${bizOrderMainMate.id}&orderId=${bizOrderMainMate.orderId}">编辑</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<form:form id="searchForm" modelAttribute="bizOrderMainMate" action="${ctx}/ordermainmate/bizOrderMainMate/" method="post">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input  id = "messageid"  name = "message" value="${message}" style="display: none;"/>
		<input  id = "tiaoshuid"  name = "tiaoshu" value="${tiaoshu}" style="display: none;"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<input id="orderId"    name="orderId" type="hidden" value="${bizOrderMainMate.orderId}"/>
		<input id="orderNumberid"     name="orderNumber" type="hidden" value="${bizMaterialsChoiceBill.orderNumber }"/>
<!-- 	<input name="operateDatetime" type="hidden" readonly="readonly" maxlength="20" class="input-medium Wdate " -->
<%-- 					value="<fmt:formatDate value="${bizMaterialsChoiceBill.createDatez}" pattern="yyyy-MM-dd HH:mm:ss"/>"/> --%>
		<div class="pagination">${page}</div>
	</form:form>
</body>
</html>