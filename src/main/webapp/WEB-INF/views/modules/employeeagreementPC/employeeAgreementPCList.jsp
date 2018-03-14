<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>工人管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			findEngineListByStoreIdAndProjectMode();
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }

		function clearButton(){
			$("input[name='noRelateCard']").removeAttr("checked");
			$("input[name='noRelateGroup']").removeAttr("checked");
		}
		
		 function findEngineListByStoreIdAndProjectMode(){
				var html3 = '<option value=""></option>';
			var storeId = $("#storeId").val();
			
			if (storeId=="" || undefined==storeId) {
				return;
			}
			//根据门店个,工程模式    动态加载工程区域
			$.ajax({

						url : "${ctx}/order/order/findEngineDepartmentBystoreIdAndProjectMode?storeId="
								+ storeId,
						type : "get",
						success : function(data) {
							var temp = "${employeeAgreementPC.elactricationId}";
							if (null!= data && data.length > 0) {
								for (var v = 0; v < data.length; v++) {
									if(data[v].engineDepartId == temp){
	                                    $("#s2id_elactricationId").find(".select2-chosen").text(data[v].engineDepartName);
	                                    html3 +='<option selected="selected" value="'+data[v].engineDepartId+'">'+data[v].engineDepartName+'</option>'
	                               }else{
	                                    html3 +='<option value="'+data[v].engineDepartId+'">'+data[v].engineDepartName+'</option>'
	                               }

								}
								$("#elactricationId").html(html3);
							} else {
								$("#elactricationId").html(html3);
							}

						}

					});		
			}
		
		
		
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="javascript:void(0);">工人协议确认查询</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="employeeAgreementPC" action="${ctx}/employeeagreementPC/list?empType=2" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>门店：</label>
				<form:select path="storeId" class="input-medium needClear" onchange="findEngineListByStoreIdAndProjectMode()">
					<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>区域：</label>
				<select id="elactricationId" name="elactricationId" class="input-medium needClear">
				</select>
			</li>
			<li><label>工人姓名：</label>
				<form:input path="realName" htmlEscape="false" maxlength="255" class="input-medium needClear"/>
			</li>
			<li><label>手机号：</label>
				<form:input path="phone" htmlEscape="false" maxlength="11" class="input-medium needClear"/>
			</li>
			<li><label>协议签订状态：</label>
				<form:select path="isSignEmployeeAgreement" class="input-medium needClear">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('is_sign_employee_agreement')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>

			<li class="btns"><input class="btn btn-primary clearBtn" type="button" value="清空" onclick="clearButton()"/></li>

			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>门店</th>
				<th>区域</th>
				<th>工人姓名</th>
				<th>手机号</th>
				<th>工种</th>
				<th>协议签订状态</th>
				<th>同意操作时间</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="employeeAgreement">
			<tr>
				<td>
					${employeeAgreement.storeName}
				</td>
				<td>
					${employeeAgreement.elactricationName}
				</td>
				<td>
					${employeeAgreement.realName}
				</td>
				<td>
					${employeeAgreement.phone}
				</td>
				<td>
                    ${fns:getDictLabel(employeeAgreement.workType, 'emp_work_type', '')}
				</td>
				<td>
                    ${fns:getDictLabel(employeeAgreement.isSignEmployeeAgreement, 'is_sign_employee_agreement', '')}
				</td>
				
				<td>
					<c:if test="${employeeAgreement.isSignEmployeeAgreement == 1}">
						<fmt:formatDate value="${employeeAgreement.employeeAgreementSignDatetime}" pattern="yyyy-MM-dd HH:mm:ss"/>
					</c:if>
					<c:if test="${employeeAgreement.isSignEmployeeAgreement == 0}">
					</c:if>
					
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>