<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>返单上报详情</title>
<meta name="decorator" content="default" />
<script type="text/javascript"
	src="${ctxStatic}/cusumerValidate/cusumerValidate.js"></script>
<script type="text/javascript">
	$(document).ready({
		
		
	})

	function backBtn(){

     window.location.href="${ctx}/orderReport/bizOrderReport/findByParam";

	}
   
</script>

</head>
<body>
	<form:form id="inputForm" modelAttribute="orderReport"
		class="form-horizontal">
		<table>
			<tr>
				<td>
					<div class="control-group" style="font-size: 20px">基本信息</div>
				</td>
			</tr>
			<tr>
				<td><div class="control-group">
						<label class="control-label">小区：</label>
						<div class="controls">
							<form:input path="communityName" readonly="true"
								htmlEscape="false" maxlength="100" class="input-medium "
								value=" ${orderReport.communityName}" />
						</div>
					</div></td>

				<td><div class="control-group">
						<label class="control-label">客户：</label>
						<div class="controls">
							<form:input path="customerName" readonly="true"
								htmlEscape="false" maxlength="100" class="input-medium "
								value=" ${orderReport.customerName}" />
						</div>
					</div></td>

				<td><div class="control-group">
						<label class="control-label">客户手机号：</label>
						<div class="controls">
							<form:input path="customerPhone" readonly="true"
								htmlEscape="false" maxlength="100" class="input-medium "
								value=" ${orderReport.customerPhone}" />
						</div>
					</div></td>
			</tr>
			<tr>
				<td>
					<div class="control-group">
						<label class="control-label">返单推荐人：</label>
						<div class="controls">
							<form:input path="reporterName" readonly="true"
								htmlEscape="false" maxlength="100" class="input-medium "
								value=" ${orderReport.reporterName}" />
						</div>
					</div>

				</td>

				<td><div class="control-group">
						<label class="control-label">返单推荐人手机号：</label>
						<div class="controls">
							<form:input path="reporterPhone" readonly="true"
								htmlEscape="false" maxlength="100" class="input-medium "
								value=" ${orderReport.reporterPhone}" />
						</div>
					</div></td>

				<td><div class="control-group">
						<label class="control-label">上报日期：</label>
						<div class="controls">
							<input path="reportDatetime" type="text" readonly="true"
								maxlength="20" class="input-medium Wdate "
								value="<fmt:formatDate value="${orderReport.reportDatetime}" pattern="yyyy-MM-dd"/>" />
						</div>
					</div></td>
			</tr>

			<tr>
				<td>
					<div class="control-group" style="font-size: 20px">流程日志</div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="control-group" style="font-size: 15px">
						<span style="padding-left: 30px">返单上报</span>
					</div>
				</td>
			</tr>
			<tr>
				<td><div class="control-group">
						<label class="control-label">上报时间：</label>
						<div class="controls">
							<input path="reportDatetime" type="text" readonly="true"
								maxlength="20" class="input-medium Wdate "
								value="<fmt:formatDate value="${orderReport.reportDatetime}" pattern="yyyy-MM-dd"/>" />
						</div>
					</div></td>
				<td><div class="control-group">
						<label class="control-label">返单推荐人：</label>
						<div class="controls">
							<form:input path="reporterName" readonly="true"
								htmlEscape="false" maxlength="100" class="input-medium "
								value=" ${orderReport.reporterName}" />
						</div>
					</div>
					</div></td>
			</tr>
			<tr>
				<td><div class="control-group">
						<label class="control-label">客户姓名：</label>
						<div class="controls">
							<form:input path="customerName" readonly="true"
								htmlEscape="false" maxlength="100" class="input-medium "
								value=" ${orderReport.customerName}" />
						</div>
					</div></td>
				<td><div class="control-group">
						<label class="control-label">客户手机：</label>
						<div class="controls">
							<form:input path="customerPhone" readonly="true"
								htmlEscape="false" maxlength="100" class="input-medium "
								value=" ${orderReport.customerPhone}" />
						</div>
					</div></td>
			</tr>
			<tr>
				<td><div class="control-group">
						<label class="control-label">小区名称：</label>
						<div class="controls">
							<form:input path="communityName" readonly="true"
								htmlEscape="false" maxlength="100" class="input-medium "
								value=" ${orderReport.communityName}" />
						</div>
					</div></td>
				<td><div class="control-group">
						<label class="control-label">详情地址：</label>
						<div class="controls">
							<form:input path="address" readonly="true" htmlEscape="false"
								maxlength="100" class="input-medium "
								value=" ${orderReport.address}" />
						</div>
					</div></td>
			</tr>
			<tr>
				<td><div class="control-group">
						<label class="control-label">备注：</label>
						<div class="controls">
							<form:input path="reportRemarks" readonly="true"
								htmlEscape="false" maxlength="100" class="input-medium "
								value=" ${orderReport.reportRemarks}" />
						</div>
					</div></td>

			</tr>





		<%--	 <c:if test="${orderReport.reportStatus != '10'}">
			<tr>
				<td>
					<div class="control-group" style="font-size: 15px">
			               <span style="padding-left: 30px">客户进店</span>
			       
					</div>
				</td>
			</tr>
			<c:forEach items="${logList}" var="log" varStatus="status">
              <c:if test="${log.businessStatus=='20'}">
				  <tr>
					  <td>
						  <div class="control-group" style="font-size: 15px">
							  <span style="padding-left: 30px">客户进店</span>

						  </div>
					  </td>
				  </tr>
			   <tr>
				<td><div class="control-group">
						<label class="control-label">处理时间：</label>
						<div class="controls">
						    <input type="text" readonly="true" class="input-medium Wdate "
								value="<fmt:formatDate value="${log.statusDatetime}" pattern="yyyy-MM-dd"/>" />
						</div>
					</div></td>

			</tr>
			</c:if>
            </c:forEach>--%>





		<%--	<tr>
				<td><div class="control-group">
						<label class="control-label">客户进店时间：</label>
						<div class="controls">
							<input path="instoreDatetime" type="text" readonly="true"
								maxlength="20" class="input-medium Wdate "
								value="<fmt:formatDate value="${orderReport.instoreDatetime}" pattern="yyyy-MM-dd"/>" />
						</div>
					</div></td>
			</tr>
			<tr>
				<td><div class="control-group">
						<label class="control-label">备注：</label>
						<div class="controls">
							<form:input path="instoreRemarks" readonly="true"
								htmlEscape="false" maxlength="100" class="input-medium "
								value=" ${orderReport.instoreRemarks}" />
						</div>
					</div></td>
			</tr>--%>
		<%--	</c:if>--%>

            <c:forEach items="${logList}" var="log" varStatus="status">
              <c:if test="${log.businessStatus==20}">

				  <tr>
					  <td>
						  <div class="control-group" style="font-size: 15px">
							  <span style="padding-left: 30px">信息无效</span>
						  </div>
					  </td>
				  </tr>


				  <tr>
				<td><div class="control-group">
						<label class="control-label">处理时间：</label>
						<div class="controls">
						  <input type="text" readonly="true" class="input-medium Wdate "
								value="<fmt:formatDate value="${log.statusDatetime}" pattern="yyyy-MM-dd"/>" />
						</div>
					</div></td>

			</tr>
			</c:if>







				<c:if test="${log.businessStatus==25}">

					<tr>
						<td>
							<div class="control-group" style="font-size: 15px">
								<span style="padding-left: 30px">分派客服</span>
							</div>
						</td>
					</tr>


					<tr>
						<td><div class="control-group">
							<label class="control-label">操作时间：</label>
							<div class="controls">
								<input type="text" readonly="true" class="input-medium Wdate "
									   value="<fmt:formatDate value="${log.statusDatetime}" pattern="yyyy-MM-dd"/>" />
							</div>
						</div></td>

					</tr>
					<tr>
						<td><div class="control-group">
							<label class="control-label">操作人：</label>

								<c:if test="${empty log.businessEmployeeName}">
									<div class="controls">
										<input type="text" readonly="true" class="input-medium"
											   value="系统自动分配" />
									</div>
								</c:if>
								<c:if test="${not empty log.businessEmployeeName}">
									<div class="controls">
										<input type="text" readonly="true" class="input-medium"
											   value="${log.businessEmployeeName}" />
									</div>

								</c:if>


						</div></td>

					</tr>
					<tr>
						<td><div class="control-group">
							<label class="control-label">客服名称：</label>
							<div class="controls">
								<input type="text" readonly="true" class="input-medium"
									   value="${log.businessRemarks}"/>
							</div>
						</div></td>

					</tr>
					<tr>
						<td><div class="control-group">
							<label class="control-label">客服手机：</label>
							<div class="controls">
								<input type="text" readonly="true" class="input-medium"
									   value="${log.remarks}"/>
							</div>
						</div></td>

					</tr>
				</c:if>






				<c:if test="${log.businessStatus==26}">

					<tr>
						<td>
							<div class="control-group" style="font-size: 15px">
								<span style="padding-left: 30px">转派客服</span>
							</div>
						</td>
					</tr>


					<tr>
						<td><div class="control-group">
							<label class="control-label">操作时间：</label>
							<div class="controls">
								<input type="text" readonly="true" class="input-medium Wdate "
									   value="<fmt:formatDate value="${log.statusDatetime}" pattern="yyyy-MM-dd"/>" />
							</div>
						</div></td>

					</tr>
					<tr>
						<td><div class="control-group">
							<label class="control-label">操作人：</label>

							<c:if test="${empty log.businessEmployeeName}">
								<div class="controls">
									<input type="text" readonly="true" class="input-medium"
										   value="系统自动分配" />
								</div>
							</c:if>
							<c:if test="${not empty log.businessEmployeeName}">
								<div class="controls">
									<input type="text" readonly="true" class="input-medium"
										   value="${log.businessEmployeeName}" />
								</div>

							</c:if>
						</div></td>

					</tr>
					<tr>
						<td><div class="control-group">
							<label class="control-label">转派客服名称：</label>
							<div class="controls">
								<input type="text" readonly="true" class="input-medium"
									   value="${log.businessRemarks}"/>
							</div>
						</div></td>

					</tr>
					<tr>
						<td><div class="control-group">
							<label class="control-label">客服手机：</label>
							<div class="controls">
								<input type="text" readonly="true" class="input-medium"
									   value="${log.remarks}"/>
							</div>
						</div></td>

					</tr>

				</c:if>










				<c:if test="${log.businessStatus==30}">

					<tr>
						<td>
							<div class="control-group" style="font-size: 15px">
								<span style="padding-left: 30px">客户进店未签单</span>
							</div>
						</td>
					</tr>


					<tr>
						<td><div class="control-group">
							<label class="control-label">操作时间：</label>
							<div class="controls">
								<input type="text" readonly="true" class="input-medium Wdate "
									   value="<fmt:formatDate value="${log.statusDatetime}" pattern="yyyy-MM-dd"/>" />
							</div>
						</div></td>

					</tr>
					<tr>
						<td><div class="control-group">
							<label class="control-label">操作人：</label>
							<c:if test="${empty log.businessEmployeeName}">
								<div class="controls">
									<input type="text" readonly="true" class="input-medium"
										   value="系统自动分配" />
								</div>
							</c:if>
							<c:if test="${not empty log.businessEmployeeName}">
								<div class="controls">
									<input type="text" readonly="true" class="input-medium"
										   value="${log.businessEmployeeName}" />
								</div>

							</c:if>
						</div></td>

					</tr>
					<tr>
						<td><div class="control-group">
							<label class="control-label">客户进店时间：</label>
							<div class="controls">
								<input type="text" readonly="true" class="input-medium Wdate "
									   value="<fmt:formatDate value="${orderReport.instoreDatetime}" pattern="yyyy-MM-dd"/>" />
							</div>
						</div></td>

					</tr>
					<tr>
						<td><div class="control-group">
							<label class="control-label">备注：</label>
							<div class="controls">
								<input type="text" readonly="true" class="input-medium"
									   value="${orderReport.instoreRemarks}" />
							</div>
						</div></td>

					</tr>
				</c:if>



				<c:if test="${log.businessStatus==40}">

					<tr>
						<td>
							<div class="control-group" style="font-size: 15px">
								<span style="padding-left: 30px">客户进店已签单</span>
							</div>
						</td>
					</tr>


					<tr>
						<td><div class="control-group">
							<label class="control-label">操作时间：</label>
							<div class="controls">
								<input type="text" readonly="true" class="input-medium Wdate "
									   value="<fmt:formatDate value="${log.statusDatetime}" pattern="yyyy-MM-dd"/>" />
							</div>
						</div></td>

					</tr>
					<tr>
						<td><div class="control-group">
							<label class="control-label">操作人：</label>
							<c:if test="${empty log.businessEmployeeName}">
								<div class="controls">
									<input type="text" readonly="true" class="input-medium"
										   value="系统自动分配" />
								</div>
							</c:if>
							<c:if test="${not empty log.businessEmployeeName}">
								<div class="controls">
									<input type="text" readonly="true" class="input-medium"
										   value="${log.businessEmployeeName}" />
								</div>

							</c:if>
						</div></td>

					</tr>
					<tr>
						<td><div class="control-group">
							<label class="control-label">客户签单时间：</label>
							<div class="controls">
								<input type="text" readonly="true" class="input-medium Wdate "
									   value="<fmt:formatDate value="${orderReport.signBillDatetime}" pattern="yyyy-MM-dd"/>" />
							</div>
						</div></td>

					</tr>
					<tr>
						<td><div class="control-group">
							<label class="control-label">备注：</label>
							<div class="controls">
								<input type="text" readonly="true" class="input-medium"
									   value="${orderReport.signBillRemarks}" />
							</div>
						</div></td>

					</tr>
					<tr>
						<td><div class="control-group">
							<label class="control-label">关联订单：</label>
							<div class="controls">

									  ${log.businessOnlyMarkVarchar}
							</div>
						</div></td>

					</tr>
				</c:if>





				<c:if test="${log.businessStatus==50}">

					<tr>
						<td>
							<div class="control-group" style="font-size: 15px">
								<span style="padding-left: 30px">客户已签施工合同</span>
							</div>
						</td>
					</tr>


					<tr>
						<td><div class="control-group">
							<label class="control-label">操作时间：</label>
							<div class="controls">
								<input type="text" readonly="true" class="input-medium Wdate "
									   value="<fmt:formatDate value="${log.statusDatetime}" pattern="yyyy-MM-dd"/>" />
							</div>
						</div></td>

					</tr>
					<tr>
						<td><div class="control-group">
							<label class="control-label">操作人：</label>
							<c:if test="${empty log.businessEmployeeName}">
								<div class="controls">
									<input type="text" readonly="true" class="input-medium"
										   value="系统自动分配" />
								</div>
							</c:if>
							<c:if test="${not empty log.businessEmployeeName}">
								<div class="controls">
									<input type="text" readonly="true" class="input-medium"
										   value="${log.businessEmployeeName}" />
								</div>

							</c:if>
						</div></td>

					</tr>
					<tr>
						<td><div class="control-group">
							<label class="control-label">合同签订日期：</label>
							<div class="controls">
								<input type="text" readonly="true" class="input-medium Wdate "
									   value="<fmt:formatDate value="${log.statusDatetime}" pattern="yyyy-MM-dd"/>" />
							</div>
						</div></td>

					</tr>
					<tr>
						<td><div class="control-group">
							<label class="control-label">关联合同：</label>
							<div class="controls">
								<c:forEach items="${relOrderList}" var="relOrder" varStatus="stat">

									<c:if test="${empty relOrder.remarks}">
										<span style="size: 40px">${relOrder.orderNumber}</span>&nbsp;&nbsp;<c:if test="${!stat.last}"></c:if>

									</c:if>

								</c:forEach>
							</div>
						</div></td>

					</tr>
				</c:if>






				<c:if test="${log.businessStatus==55}">

					<tr>
						<td>
							<div class="control-group" style="font-size: 15px">
								<span style="padding-left: 30px">补签施工合同</span>
							</div>
						</td>
					</tr>


					<tr>
						<td><div class="control-group">
							<label class="control-label">操作时间：</label>
							<div class="controls">
								<input type="text" readonly="true" class="input-medium Wdate "
									   value="<fmt:formatDate value="${log.statusDatetime}" pattern="yyyy-MM-dd"/>" />
							</div>
						</div></td>

					</tr>

					<tr>
						<td><div class="control-group">
							<label class="control-label">操作时间：</label>
							<div class="controls">
								<input type="text" readonly="true" class="input-medium Wdate "
									   value="<fmt:formatDate value="${log.statusDatetime}" pattern="yyyy-MM-dd"/>" />
							</div>
						</div></td>

					</tr>
					<tr>
						<td><div class="control-group">
							<label class="control-label">操作人：</label>
							<c:if test="${empty log.businessEmployeeName}">
								<div class="controls">
									<input type="text" readonly="true" class="input-medium"
										   value="系统自动分配" />
								</div>
							</c:if>
							<c:if test="${not empty log.businessEmployeeName}">
								<div class="controls">
									<input type="text" readonly="true" class="input-medium"
										   value="${log.businessEmployeeName}" />
								</div>

							</c:if>
						</div></td>

					</tr>
					<tr>
						<td><div class="control-group">
							<label class="control-label">合同签订日期：</label>
							<div class="controls">
								<input type="text" readonly="true" class="input-medium Wdate "
									   value="<fmt:formatDate value="${log.statusDatetime}" pattern="yyyy-MM-dd"/>" />
							</div>
						</div></td>

					</tr>
					<tr>
						<td><div class="control-group">
							<label class="control-label">关联合同：</label>
							<div class="controls">
								<c:forEach items="${relOrderList}" var="relOrder" varStatus="stat">

									<c:if test="${not  empty relOrder.remarks}">
									<span style="size: 40px">${relOrder.orderNumber}</span> &nbsp;&nbsp;<c:if test="${!stat.last}"></c:if>
								</c:if>
								</c:forEach>
							</div>
						</div></td>

					</tr>
				</c:if>



				<c:if test="${log.businessStatus==90}">

					<tr>
						<td>
							<div class="control-group" style="font-size: 15px">
								<span style="padding-left: 30px">返单失效</span>
							</div>
						</td>
					</tr>


					<tr>
						<td><div class="control-group">
							<label class="control-label">失效时间：</label>
							<div class="controls">
								<input type="text" readonly="true" class="input-medium Wdate "
									   value="<fmt:formatDate value="${log.statusDatetime}" pattern="yyyy-MM-dd"/>" />
							</div>
						</div></td>

					</tr>

				</c:if>



			</c:forEach>

			<tr>
				<td>
					<div class="control-group"
						style="font-size: 10px; text-align: center;">
						<a href="javascript:" onclick="backBtn()" class="btn"
							style="font-size: 15px">返回上一页</a> &nbsp;
					</div>
				</td>

			</tr>
		</table>
	</form:form>
</body>
</html>