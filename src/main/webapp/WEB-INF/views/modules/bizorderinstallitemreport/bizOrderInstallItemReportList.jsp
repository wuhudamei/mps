<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>工程部主材工期统计表</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/bizOrderInstallItemReport/bizOrderInstallItemReport/preList">工程部主材工期统计表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="bizOrderInstallItemReport" action="${ctx}/bizOrderInstallItemReport/bizOrderInstallItemReport/list" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>门店：</label> 
				<form:select path="storeId" class="input-medium needClear" >
					<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
			</li>
			<li><label>订单编号：</label>
				<form:input path="orderNumber" htmlEscape="false" maxlength="100" class="input-medium needClear"/>
			</li>
			<li><label>客户姓名：</label>
				<form:input path="customerName" htmlEscape="false" maxlength="16" class="input-medium needClear"/>
			</li>
			<li><label>客户手机号：</label>
				<form:input path="customerPhone" htmlEscape="false" maxlength="11" class="input-medium needClear"/>
			</li>
			<li><label>项目经理：</label>
				<form:input path="pmName" htmlEscape="false" maxlength="11" class="input-medium needClear"/>
			</li>
			<li><label>实际开工日期：</label>
				<input name="beginActualStartDate" id="beginActualStartDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate needClear"
					value="<fmt:formatDate value="${bizOrderInstallItemReport.beginActualStartDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'endActualStartDate\')}',isShowClear:true});"/> 至  
				<input name="endActualStartDate" id="endActualStartDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate needClear"
					value="<fmt:formatDate value="${bizOrderInstallItemReport.endActualStartDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'beginActualStartDate\')}',isShowClear:true});"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="btns"><input class="btn btn-primary clearBtn" type="button" value="清空" /></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th rowspan="2" style="vertical-align:middle">序号</th>
				<th rowspan="2" style="vertical-align:middle">大区</th>
				<th rowspan="2" style="vertical-align:middle">订单编号</th>
				<th rowspan="2" style="vertical-align:middle">客户</th>
				<th rowspan="2" style="vertical-align:middle">手机号</th>
				<th rowspan="2" style="vertical-align:middle">项目经理</th>
				<th rowspan="2" style="vertical-align:middle">实际开工时间</th>
				
				<th colspan="8" style="text-align: center">橱柜</th>
				<th colspan="8" style="text-align: center">房门</th>
				<th colspan="7" style="text-align: center">洁具</th>
				<th colspan="7" style="text-align: center">壁纸</th>
				<th colspan="7" style="text-align: center">木地板</th>
				
				<th colspan="7" style="text-align: center">吊顶</th>
				<th colspan="7" style="text-align: center">灯具、开关、小五金</th>
				<th colspan="8" style="text-align: center">窗帘</th>
				<th colspan="8" style="text-align: center">淋浴房</th>
				<th colspan="8" style="text-align: center">定制衣柜</th>
			</tr>
			<tr>
				<th style="vertical-align: middle">申报核尺时间</th>
				<th style="vertical-align: middle">安装提报</th>
				<th style="vertical-align: middle">期望时间</th>
				<th style="vertical-align: middle">实际进场</th>
				<th style="vertical-align: middle">实际完成</th>
				<th style="vertical-align: middle">是否返工</th>
				<th style="vertical-align: middle">返工内容描述</th>
				<th style="vertical-align: middle">提报返工时间</th>
				
				<th style="vertical-align: middle">申报核尺时间</th>
				<th style="vertical-align: middle">安装提报</th>
				<th style="vertical-align: middle">期望时间</th>
				<th style="vertical-align: middle">实际进场</th>
				<th style="vertical-align: middle">实际完成</th>
				<th style="vertical-align: middle">是否返工</th>
				<th style="vertical-align: middle">返工内容描述</th>
				<th style="vertical-align: middle">提报返工时间</th>
				
				<th style="vertical-align: middle">安装提报</th>
				<th style="vertical-align: middle">期望时间</th>
				<th style="vertical-align: middle">实际进场</th>
				<th style="vertical-align: middle">实际完成</th>
				<th style="vertical-align: middle">是否返工</th>
				<th style="vertical-align: middle">返工内容描述</th>
				<th style="vertical-align: middle">提报返工时间</th>
				
				<th style="vertical-align: middle">安装提报</th>
				<th style="vertical-align: middle">期望时间</th>
				<th style="vertical-align: middle">实际进场</th>
				<th style="vertical-align: middle">实际完成</th>
				<th style="vertical-align: middle">是否返工</th>
				<th style="vertical-align: middle">返工内容描述</th>
				<th style="vertical-align: middle">提报返工时间</th>
				
				<th style="vertical-align: middle">安装提报</th>
				<th style="vertical-align: middle">期望时间</th>
				<th style="vertical-align: middle">实际进场</th>
				<th style="vertical-align: middle">实际完成</th>
				<th style="vertical-align: middle">是否返工</th>
				<th style="vertical-align: middle">返工内容描述</th>
				<th style="vertical-align: middle">提报返工时间</th>
				
				<th style="vertical-align: middle">安装提报</th>
				<th style="vertical-align: middle">期望时间</th>
				<th style="vertical-align: middle">实际进场</th>
				<th style="vertical-align: middle">实际完成</th>
				<th style="vertical-align: middle">是否返工</th>
				<th style="vertical-align: middle">返工内容描述</th>
				<th style="vertical-align: middle">提报返工时间</th>
				
				<th style="vertical-align: middle">安装提报</th>
				<th style="vertical-align: middle">期望时间</th>
				<th style="vertical-align: middle">实际进场</th>
				<th style="vertical-align: middle">实际完成</th>
				<th style="vertical-align: middle">是否返工</th>
				<th style="vertical-align: middle">返工内容描述</th>
				<th style="vertical-align: middle">提报返工时间</th>
				
				<th style="vertical-align: middle">申报核尺时间</th>
				<th style="vertical-align: middle">安装提报</th>
				<th style="vertical-align: middle">期望时间</th>
				<th style="vertical-align: middle">实际进场</th>
				<th style="vertical-align: middle">实际完成</th>
				<th style="vertical-align: middle">是否返工</th>
				<th style="vertical-align: middle">返工内容描述</th>
				<th style="vertical-align: middle">提报返工时间</th>
				
				<th style="vertical-align: middle">申报核尺时间</th>
				<th style="vertical-align: middle">安装提报</th>
				<th style="vertical-align: middle">期望时间</th>
				<th style="vertical-align: middle">实际进场</th>
				<th style="vertical-align: middle">实际完成</th>
				<th style="vertical-align: middle">是否返工</th>
				<th style="vertical-align: middle">返工内容描述</th>
				<th style="vertical-align: middle">提报返工时间</th>
				
				<th style="vertical-align: middle">申报核尺时间</th>
				<th style="vertical-align: middle">安装提报</th>
				<th style="vertical-align: middle">期望时间</th>
				<th style="vertical-align: middle">实际进场</th>
				<th style="vertical-align: middle">实际完成</th>
				<th style="vertical-align: middle">是否返工</th>
				<th style="vertical-align: middle">返工内容描述</th>
				<th style="vertical-align: middle">提报返工时间</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="bizOrderInstallItemReport" varStatus="status">
			<tr>
				<td>${status.index+1 }</td>
				<td>${bizOrderInstallItemReport.enginDepartName }</td>
				<td>${bizOrderInstallItemReport.orderNumber }</td>
				<td>${bizOrderInstallItemReport.customerName }</td>
				<td>${bizOrderInstallItemReport.customerPhone }</td>
				<td>${bizOrderInstallItemReport.pmName }</td>
				<td>
					<fmt:formatDate value="${bizOrderInstallItemReport.actualStartDate}" pattern="yyyy-MM-dd"/>
				</td>
				
				
				
				
				
				
				<!-- 1.橱柜 -->
				<td>
					<fmt:formatDate value="${bizOrderInstallItemReport.checksizeApplyDatetimeChugui}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${bizOrderInstallItemReport.installItemApplyDatetimeChugui}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${bizOrderInstallItemReport.installItemApplyIntoDateChugui}" pattern="yyyy-MM-dd "/>
				</td>
				<td>
					<fmt:formatDate value="${bizOrderInstallItemReport.installItemRealIntoDateChugui}" pattern="yyyy-MM-dd "/>
				</td>
				<td>
					<fmt:formatDate value="${bizOrderInstallItemReport.installItemRealAcceptDateChugui}" pattern="yyyy-MM-dd "/>
				</td>
				<td>
					<c:if test="${bizOrderInstallItemReport.installItemIsReturnedChugui eq 1}">
						是
					</c:if>
					<c:if test="${bizOrderInstallItemReport.installItemIsReturnedChugui ne 1}">
						否
					</c:if>
					
				</td>
				<td>
					${bizOrderInstallItemReport.installItemProblemDescribeChugui}
				</td>
				<td>
					<fmt:formatDate value="${bizOrderInstallItemReport.installItemProblemApplyDatetimeChugui}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				
				
				
				
				
				<!-- 2.房门 -->
				<td>
					<fmt:formatDate value="${bizOrderInstallItemReport.checksizeApplyDatetimeFangmen}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${bizOrderInstallItemReport.installItemApplyDatetimeFangmen}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${bizOrderInstallItemReport.installItemApplyIntoDateFangmen}" pattern="yyyy-MM-dd "/>
				</td>
				<td>
					<fmt:formatDate value="${bizOrderInstallItemReport.installItemRealIntoDateFangmen}" pattern="yyyy-MM-dd "/>
				</td>
				<td>
					<fmt:formatDate value="${bizOrderInstallItemReport.installItemRealAcceptDateFangmen}" pattern="yyyy-MM-dd "/>
				</td>
				<td>
					<c:if test="${bizOrderInstallItemReport.installItemIsReturnedFangmen eq 1}">
						是
					</c:if>
					<c:if test="${bizOrderInstallItemReport.installItemIsReturnedFangmen ne 1}">
						否
					</c:if>
					
				</td>
				<td>
					${bizOrderInstallItemReport.installItemProblemDescribeFangmen}
				</td>
				<td>
					<fmt:formatDate value="${bizOrderInstallItemReport.installItemProblemApplyDatetimeFangmen}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				
				
				
				
				
				<!-- 3.洁具 -->
				<td>
					<fmt:formatDate value="${bizOrderInstallItemReport.installItemApplyDatetimeJieju}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${bizOrderInstallItemReport.installItemApplyIntoDateJieju}" pattern="yyyy-MM-dd "/>
				</td>
				<td>
					<fmt:formatDate value="${bizOrderInstallItemReport.installItemRealIntoDateJieju}" pattern="yyyy-MM-dd "/>
				</td>
				<td>
					<fmt:formatDate value="${bizOrderInstallItemReport.installItemRealAcceptDateJieju}" pattern="yyyy-MM-dd "/>
				</td>
				<td>
					<c:if test="${bizOrderInstallItemReport.installItemIsReturnedJieju eq 1}">
						是
					</c:if>
					<c:if test="${bizOrderInstallItemReport.installItemIsReturnedJieju ne 1}">
						否
					</c:if>
					
				</td>
				<td>
					${bizOrderInstallItemReport.installItemProblemDescribeJieju}
				</td>
				<td>
					<fmt:formatDate value="${bizOrderInstallItemReport.installItemProblemApplyDatetimeJieju}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				
				
				
				
				
				
				<!-- 4.壁纸 -->
				<td>
					<fmt:formatDate value="${bizOrderInstallItemReport.installItemApplyDatetimeBizhi}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${bizOrderInstallItemReport.installItemApplyIntoDateBizhi}" pattern="yyyy-MM-dd "/>
				</td>
				<td>
					<fmt:formatDate value="${bizOrderInstallItemReport.installItemRealIntoDateBizhi}" pattern="yyyy-MM-dd "/>
				</td>
				<td>
					<fmt:formatDate value="${bizOrderInstallItemReport.installItemRealCompleteDateBizhi}" pattern="yyyy-MM-dd "/>
				</td>
				<td>
					<c:if test="${bizOrderInstallItemReport.installItemIsReturnedBizhi eq 1}">
						是
					</c:if>
					<c:if test="${bizOrderInstallItemReport.installItemIsReturnedBizhi ne 1}">
						否
					</c:if>
					
				</td>
				<td>
					${bizOrderInstallItemReport.installItemProblemDescribeBizhi}
				</td>
				<td>
					<fmt:formatDate value="${bizOrderInstallItemReport.installItemProblemApplyDatetimeBizhi}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				
				
				
				
				
				
				<!-- 5.木地板 -->
				<td>
					<fmt:formatDate value="${bizOrderInstallItemReport.installItemApplyDatetimeMudiban}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${bizOrderInstallItemReport.installItemApplyIntoDateMudiban}" pattern="yyyy-MM-dd "/>
				</td>
				<td>
					<fmt:formatDate value="${bizOrderInstallItemReport.installItemRealIntoDateMudiban}" pattern="yyyy-MM-dd "/>
				</td>
				<td>
					<fmt:formatDate value="${bizOrderInstallItemReport.installItemRealAcceptDateMudiban}" pattern="yyyy-MM-dd "/>
				</td>
				<td>
					<c:if test="${bizOrderInstallItemReport.installItemIsReturnedMudiban eq 1}">
						是
					</c:if>
					<c:if test="${bizOrderInstallItemReport.installItemIsReturnedMudiban ne 1}">
						否
					</c:if>
					
				</td>
				<td>
					${bizOrderInstallItemReport.installItemProblemDescribeMudiban}
				</td>
				<td>
					<fmt:formatDate value="${bizOrderInstallItemReport.installItemProblemApplyDatetimeMudiban}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				
				
				
				
				
				
				
				
				<!-- 6.吊顶 -->
				<td>
					<fmt:formatDate value="${bizOrderInstallItemReport.installItemApplyDatetimeDiaoding}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${bizOrderInstallItemReport.installItemApplyIntoDateDiaoding}" pattern="yyyy-MM-dd "/>
				</td>
				<td>
					<fmt:formatDate value="${bizOrderInstallItemReport.installItemRealIntoDateDiaoding}" pattern="yyyy-MM-dd "/>
				</td>
				<td>
					<fmt:formatDate value="${bizOrderInstallItemReport.installItemRealAcceptDateDiaoding}" pattern="yyyy-MM-dd "/>
				</td>
				<td>
					<c:if test="${bizOrderInstallItemReport.installItemIsReturnedDiaoding eq 1}">
						是
					</c:if>
					<c:if test="${bizOrderInstallItemReport.installItemIsReturnedDiaoding ne 1}">
						否
					</c:if>
					
				</td>
				<td>
					${bizOrderInstallItemReport.installItemProblemDescribeDiaoding}
				</td>
				<td>
					<fmt:formatDate value="${bizOrderInstallItemReport.installItemProblemApplyDatetimeDiaoding}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				
				
				
				<!-- 7.灯具、开关、小五金 -->
				<td>
					<fmt:formatDate value="${bizOrderInstallItemReport.installItemApplyDatetimeDengju}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${bizOrderInstallItemReport.installItemApplyIntoDateDengju}" pattern="yyyy-MM-dd "/>
				</td>
				<td>
					<fmt:formatDate value="${bizOrderInstallItemReport.installItemRealIntoDateDengju}" pattern="yyyy-MM-dd "/>
				</td>
				<td>
					<fmt:formatDate value="${bizOrderInstallItemReport.installItemRealAcceptDateDengju}" pattern="yyyy-MM-dd "/>
				</td>
				<td>
					<c:if test="${bizOrderInstallItemReport.installItemIsReturnedDengju eq 1}">
						是
					</c:if>
					<c:if test="${bizOrderInstallItemReport.installItemIsReturnedDengju ne 1}">
						否
					</c:if>
					
				</td>
				<td>
					${bizOrderInstallItemReport.installItemProblemDescribeDengju}
				</td>
				<td>
					<fmt:formatDate value="${bizOrderInstallItemReport.installItemProblemApplyDatetimeDengju}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				
				
				
				
				<!-- 8.窗帘 -->
				<td>
					<fmt:formatDate value="${bizOrderInstallItemReport.checksizeApplyDatetimeChuanglian}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${bizOrderInstallItemReport.installItemApplyDatetimeChuanglian}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${bizOrderInstallItemReport.installItemApplyIntoDateChuanglian}" pattern="yyyy-MM-dd "/>
				</td>
				<td>
					<fmt:formatDate value="${bizOrderInstallItemReport.installItemRealIntoDateChuanglian}" pattern="yyyy-MM-dd "/>
				</td>
				<td>
					<fmt:formatDate value="${bizOrderInstallItemReport.installItemRealAcceptDateChuanglian}" pattern="yyyy-MM-dd "/>
				</td>
				<td>
					<c:if test="${bizOrderInstallItemReport.installItemIsReturnedChuanglian eq 1}">
						是
					</c:if>
					<c:if test="${bizOrderInstallItemReport.installItemIsReturnedChuanglian ne 1}">
						否
					</c:if>
					
				</td>
				<td>
					${bizOrderInstallItemReport.installItemProblemDescribeChuanglian}
				</td>
				<td>
					<fmt:formatDate value="${bizOrderInstallItemReport.installItemProblemApplyDatetimeChuanglian}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				
				

				<!-- 9.淋浴房 -->
				<td>
					<fmt:formatDate value="${bizOrderInstallItemReport.checksizeApplyDatetimeLinyufang}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${bizOrderInstallItemReport.installItemApplyDatetimeLinyufang}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${bizOrderInstallItemReport.installItemApplyIntoDateLinyufang}" pattern="yyyy-MM-dd "/>
				</td>
				<td>
					<fmt:formatDate value="${bizOrderInstallItemReport.installItemRealIntoDateLinyufang}" pattern="yyyy-MM-dd "/>
				</td>
				<td>
					<fmt:formatDate value="${bizOrderInstallItemReport.installItemRealAcceptDateLinyufang}" pattern="yyyy-MM-dd "/>
				</td>
				<td>
					<c:if test="${bizOrderInstallItemReport.installItemIsReturnedLinyufang eq 1}">
						是
					</c:if>
					<c:if test="${bizOrderInstallItemReport.installItemIsReturnedLinyufang ne 1}">
						否
					</c:if>
					
				</td>
				<td>
					${bizOrderInstallItemReport.installItemProblemDescribeLinyufang}
				</td>
				<td>
					<fmt:formatDate value="${bizOrderInstallItemReport.installItemProblemApplyDatetimeLinyufang}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				
				
				
				<!-- 10.定制衣柜 -->
				<td>
					<fmt:formatDate value="${bizOrderInstallItemReport.checksizeApplyDatetimeYigui}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${bizOrderInstallItemReport.installItemApplyDatetimeYigui}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${bizOrderInstallItemReport.installItemApplyIntoDateYigui}" pattern="yyyy-MM-dd "/>
				</td>
				<td>
					<fmt:formatDate value="${bizOrderInstallItemReport.installItemRealIntoDateYigui}" pattern="yyyy-MM-dd "/>
				</td>
				<td>
					<fmt:formatDate value="${bizOrderInstallItemReport.installItemRealAcceptDateYigui}" pattern="yyyy-MM-dd "/>
				</td>
				<td>
					<c:if test="${bizOrderInstallItemReport.installItemIsReturnedYigui eq 1}">
						是
					</c:if>
					<c:if test="${bizOrderInstallItemReport.installItemIsReturnedYigui ne 1}">
						否
					</c:if>
					
				</td>
				<td>
					${bizOrderInstallItemReport.installItemProblemDescribeYigui}
				</td>
				<td>
					<fmt:formatDate value="${bizOrderInstallItemReport.installItemProblemApplyDatetimeYigui}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				
				
				
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>