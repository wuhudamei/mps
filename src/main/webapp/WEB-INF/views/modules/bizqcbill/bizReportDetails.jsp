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
		}
	</script>
</head>
<body>
		<ul class="nav nav-tabs">
			<%-- <li class="active"><a href="${ctx}/bizorderqcbill/bizOrderQcBill/report?orderId=${bizQcBill.orderId}">返回</a></li> --%>
			<li class="active"><a href="#" onclick="history.go(-1)">返回</a></li>
		</ul>
		<form:form id="searchForm" modelAttribute="reportCheckDetails" action="${ctx}/bizorderqcbill/bizOrderQcBill/details" method="post" class="breadcrumb form-search">
		<ul class="ul-form">
			<form:hidden path="qcBillId"/>
			<li><label>检查分类：</label>
				<form:select path="qcCheckKindId" class="input-medium needClear" id="qcCheckKindId">
					<form:option value="" label=""/>
				</form:select>
			</li>
			
			<li><label>检查结果：</label>
				<form:select path="isPassed" class="input-medium needClear">
					<form:option value="" label=""></form:option>
					<form:option value="1" label="">合格</form:option>
					<form:option value="0" label="">不合格</form:option>
				</form:select>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
		</ul>
	</form:form>
		
	
		<div class="breadcrumb form-search">
			
			<table style="width:100%" align="center" valign="center">
				<tr>
					<td colspan="3" style="width:100%"><label>顾客：</label>
						${bizQcBill.communityName }-${bizQcBill.buildNumber }-${bizQcBill.buildUnit }-${bizQcBill.buildRoom }-${bizQcBill.customerName }
					</td>
				</tr>
				<tr>
					<td  style="width:33%"><label>施工地址：</label>${bizQcBill.customerAddress }</td>
					<td style="width:33%"><label>签到地址：</label>${bizQcBill.signAddress }</td>
					<td style="width:33%"><label>签到时间：</label><fmt:formatDate value="${bizQcBill.signCheckDatetime }" pattern="yyyy-MM-dd"/></td>
				</tr>
				<tr>
					<td style="width:33%"><label>报告类型：</label>
						<c:if test="${bizQcBill.qcBillType=='1' }">
							【约检】${bizQcBill.qcCheckNodeName }
						</c:if>
						<c:if test="${bizQcBill.qcBillType=='2' }">
							【抽检】
						</c:if>
						<c:if test="${bizQcBill.isRecheck=='1' }">
							【复检】
						</c:if>
					</td>
					<td style="width:33%"><label>实际开工日期：</label><fmt:formatDate value="${bizQcBill.actualStartDate }" pattern="yyyy-MM-dd"/></td>
					<td style="width:33%"><label>质检员：</label>${bizQcBill.inspectorRealName }</td>
				</tr>
				<tr>
					<td style="width:33%"><label>项目经理：</label>${bizQcBill.managerRealName }</td>
					<td style="width:33%"><label>质检日期：</label><fmt:formatDate value="${bizQcBill.checkDatetime }" pattern="yyyy-MM-dd"/></td>
					<td style="width:33%"><label>实际得分/总分数：</label>${bizQcBill.gotScore }/${bizQcBill.totalScore }</td>
				</tr>
				<tr>
					<td style="width:33%"><label>检查人：</label>${bizQcBill.checkRealName }</td>
					<td style="width:33%"><label>验收时间：</label><fmt:formatDate value="${bizQcBill.acceptCheckDatetime }" pattern="yyyy-MM-dd"/></td>
					<td style="width:33%">
						<c:if test="${picListLength ne 0 }">
							<a href="${ctx}/bizorderqcbill/bizOrderQcBill/detailsPic?qcBillId=${qcBillId}">
								<label>查看图片</label>
							</a>
						</c:if>
					</td>
				</tr>
			</table>
			
		</div>	
	
	
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>检查分类</th>
				<th><span style="text-align: center;">检查项</span></th>
				<th>检查结果</th>
				<th>处理方式</th>
				<th>责任人</th>
				<th>违规形式</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${list}" var="itemDetails" varStatus="status">
			<tr>
				<td>
					${itemDetails.qcCheckKindName}
				</td>
				<td>
					${itemDetails.qcCheckItemName}
				</td>
				<td>
					<c:if test="${itemDetails.isPassed=='1' }">合格</c:if>
					<c:if test="${itemDetails.isPassed=='0' }">不合格</c:if>
				</td>
				<td>
					<c:if test="${itemDetails.isPassed=='0' }">
						<c:if test="${itemDetails.isWarned=='1' }">警告</br></c:if>
						<c:if test="${itemDetails.isLocaleRepaire=='1' }">现场整改</br></c:if>
						<c:if test="${itemDetails.isLimitDateRepaire=='1' }">期限整改 - 
								<c:if test="${itemDetails.limitDateRepaireCheckStyle=='1' }">线下整改</c:if>
								<c:if test="${itemDetails.limitDateRepaireCheckStyle=='0' }">线上整改</c:if>
								 - <fmt:formatDate value="${itemDetails.limitDate }" pattern="yyyy-MM-dd" /></br>
						</c:if>
						<c:if test="${itemDetails.isPunishMoney=='1' }">
								
								扣分/罚款 - 项目经理 ${itemDetails.pmPunishScore }分/${itemDetails.punishMoneyAmountReal }元 工人 ${itemDetails.workerPunishScore }分/${itemDetails.workerPunishAmount }元 质检员 ${itemDetails.qcPunishScore }分/${itemDetails.qcPunishAmount }元</br>
												
								<%-- 罚款 - ${itemDetails.punishMoneyAmountReal }元</br> --%>
						</c:if>
					</c:if>
				</td>
				<td>
					<c:if test="${itemDetails.mnagerPerson!=null ||itemDetails.workGroupPerson!=null}">
						<div class="font28 col6">
							项目经理: ${itemDetails.mnagerPerson  }</br>
							工   人 : ${itemDetails.taskName }- ${itemDetails.workGroupPerson }
								<%-- 罚款 - ${itemDetails.punishMoneyAmountReal }元 --%>
						</div>
					</c:if>
				</td>
				<td>
					<c:if test="${itemDetails.isPassed=='0' }">
						<c:forEach items="${itemDetails.reportCheckBreakSettleBillList }" var="reportBreak">
								${reportBreak.breakDescribe }
								<c:if test="${reportBreak.isRequiredRemarks=='1' }">
								备注：${reportBreak.breakRemarks }
								</c:if></br>
						</c:forEach>
					</c:if>
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