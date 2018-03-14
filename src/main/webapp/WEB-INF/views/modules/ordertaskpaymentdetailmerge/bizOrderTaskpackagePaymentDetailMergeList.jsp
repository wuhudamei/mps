<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>付款单明细合并管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#allCheck").click(function () {
	            if ($(this).attr("checked")) { // 全选 
	            	$("input[name='ids']").each(function () {
	            		$(this).attr("checked", true);	
	                });
	            } else { // 取消全选 
	            	$("input[name='ids']").each(function () {
	            		var status = $(this).attr("data-option");
	            		if(status == "0"){
	            			$(this).attr("checked", false);	
	            		}
	                });
	            }
	        });
		});
		
		function confirmPayment(obj){
			var num=0;
			$("input[name='ids']:checked").each(function(){
				var status = $(this).attr("data-option");
				if(status == '0'){
					num = num + 1;	
				}
			});
			if(num == 0){
				alertx("请选择未付款的员工");
				return;
			}
			
			top.$.jBox.confirm("确认付款吗？","系统提示",function(v,h,f){
				if(v=="ok"){
					loading('正在提交，请稍等...');
					$(obj).removeAttr("onclick");
					$("#inputSubmit").submit();
				}
			},{buttonsFocus:1});
			top.$('.jbox-body .jbox-icon').css('top','55px');
		}
				
		function exportTxt(){
			window.location.href="${ctx}/ordertaskpaymentdetailmerge/bizOrderTaskpackagePaymentDetailMerge/exportTxt?summaryId=${summaryId}";
		}
		
		function exportExcel(){
			window.location.href="${ctx}/ordertaskpaymentdetailmerge/bizOrderTaskpackagePaymentDetailMerge/exportExcel?summaryId=${summaryId}";
		}
		function SWIFTExportExcel(){
			window.location.href="${ctx}/ordertaskpaymentdetailmerge/bizOrderTaskpackagePaymentDetailMerge/SWIFTExportExcel?summaryId=${summaryId}";
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/ordertaskpaymentdetailmerge/bizOrderTaskpackagePaymentDetailMerge/list?summaryId=${summaryId}">付款单明细合并列表</a></li>
	</ul>
	<ul class="ul-form breadcrumb">
		<li class="btns"><input class="btn" type="button" value="返回" onclick="javascript:window.location.href='${ctx}/ordertaskpackagepaymentsummary/bizOrderTaskpackagePaymentSummary/summaryList'"/></li>
		<li class="btns"><input class="btn btn-primary" type="button" value="确认已付款" onclick="confirmPayment(this)"/></li>
		<li class="btns"><input class="btn btn-primary" type="button" value="导出徽商银行txt格式文件" onclick="exportTxt()"/></li>
		<li class="btns"><input class="btn btn-primary" type="button" value="导出中国银行excel格式文件" onclick="exportExcel()"/></li>
		<li class="btns"><input class="btn btn-primary" type="button" value="导出中信银行excel格式文件" onclick="SWIFTExportExcel()"/></li>
		<li class="clearfix"></li>
	</ul>
	<sys:message content="${message}"/>
	<form id="inputSubmit" action="${ctx}/ordertaskpaymentdetailmerge/bizOrderTaskpackagePaymentDetailMerge/confirmPayment">
		<input type="hidden" name="summaryId" value="${summaryId}"/>
		<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th><input type="checkbox" id="allCheck"/>全选</th>
				<th>户名</th>
				<th>身份证号</th>
				<th>卡号</th>
				<th>金额</th>
				<th>付款状态</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${list}" var="paymentDetailMergeVo">
			<tr>
				<td><input type="checkbox" data-option="${paymentDetailMergeVo.status}" name="ids" value="${paymentDetailMergeVo.id}" <c:if test="${paymentDetailMergeVo.status =='1'}">checked="checked" disabled="disabled"</c:if>/></td>
				<td>${paymentDetailMergeVo.realName}</td>
				<td>${paymentDetailMergeVo.idCardNo}</td>
				<td>${paymentDetailMergeVo.bankCardNo}</td>
				<td>${paymentDetailMergeVo.amount}</td>
				<td>
    				<c:if test="${paymentDetailMergeVo.status == '0'}">未付款</c:if>
    				<c:if test="${paymentDetailMergeVo.status == '1'}">已付款</c:if>
				</td>
			</tr>
		</c:forEach>
		</tbody>
		</table>
	</form>
</body>
</html>