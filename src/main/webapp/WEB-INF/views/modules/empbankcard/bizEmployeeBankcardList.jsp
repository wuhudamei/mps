<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>工人银行卡管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			getEmpByStoreid();
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
		function getEmpByStoreid()
		{
			//alert( $("#storeId").val());
			$("#empId").html('');
			//$("#empId").attr("disabled","true");
			 $.ajax({
			        type : 'POST',
			        dataType : 'json',
			        url : '${ctx}/employee/bizEmployee/employee_list_byStoreId',
			        data : {
			            'storeid' : $("#storeId").val(),
			        },
			        success : function(data) {
			            var html = "";
			            html +="<option></option>";
			           // alert(data.length);
			            for (var i = 0; i < data.length; i++) {
			            	//alert($("#empId").val());
			            	var sec = "";
			            	if($("#empIdStoreId").val() == data[i].value){
			            		//alert("dd");
			            		sec = "selected='selected'";
			            		//alert(data[i].label);
			            	}
			            	html += "<option value='" + data[i].value +"' " + sec + ">" + data[i].label + "</option>"
			            }
			            html+="";
			           	//alert(html);
			           // alert("dd");
			            
			            $("#empId").append(html);
			           // $("#empId").attr("disabled","");
			        }
			    })
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/empbankcard/bizEmployeeBankcard/listPage">工人银行卡列表</a></li>
		<shiro:hasPermission name="empbankcard:bizEmployeeBankcard:edit"><li><a href="${ctx}/empbankcard/bizEmployeeBankcard/form">工人银行卡添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="bizEmployeeBankcard" action="${ctx}/empbankcard/bizEmployeeBankcard/list" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
            <li><label>门店：</label>
                <form:select path="storeId" class="input-medium needClear" onChange="getEmpByStoreid()">
                   <form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
                </form:select>
            </li>
			<li><label>员工：</label>
				<%-- <form:select path="empId" class="input-medium needClear" id="empId" name="empId">
                    <form:option value="" label="" />
                    <form:options items="${fns:getEmployeeList('2')}" itemLabel="label" itemValue="value" htmlEscape="false" />
                </form:select> --%>
                <form:select path="empId" class="input-medium" id="empId"
					name="empId">
					<form:option value="${bizEmployeeBankcard.empId }" label="${bizEmployeeBankcard.empRealName }" />
				</form:select> 
			</li>
			<li><label>身份证号：</label>
				<form:input path="idCardNo" htmlEscape="false" maxlength="18" class="input-medium needClear"/>
			</li>
			<li><label>开户行：</label>
				<form:select path="bankNo" class="input-medium needClear">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('biz_bank_no')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>

            <li class="btns"><input class="btn btn-primary clearBtn" type="button" value="清空"/></li>

			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>门店</th>
				<th>员工</th>
				<th>身份证号</th>
				<th>开户行</th>
				<th>支行地址</th>
				<th>银行卡号</th>
				<shiro:hasPermission name="empbankcard:bizEmployeeBankcard:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="bizEmployeeBankcard">
			<tr>
				<td><a href="${ctx}/empbankcard/bizEmployeeBankcard/form?id=${bizEmployeeBankcard.id}">
                    ${fns:getStoreLabel(bizEmployeeBankcard.storeId, '')}
				</a></td>
				<td>
                   <%--  ${fns:getEmployeeLabel(bizEmployeeBankcard.empId, '')} --%>
                    ${bizEmployeeBankcard.empRealName}
				</td>
				<td>
					${bizEmployeeBankcard.idCardNo}
				</td>
				<td>
					${fns:getDictLabel(bizEmployeeBankcard.bankNo, 'biz_bank_no', '')}
				</td>
				<td>
					${bizEmployeeBankcard.bankName}
				</td>
				<td>
					${bizEmployeeBankcard.bankCardNo}
				</td>
				<shiro:hasPermission name="empbankcard:bizEmployeeBankcard:edit"><td>
    				<a href="${ctx}/empbankcard/bizEmployeeBankcard/form?id=${bizEmployeeBankcard.id}">修改</a>
					<a href="${ctx}/empbankcard/bizEmployeeBankcard/delete?id=${bizEmployeeBankcard.id}" onclick="return confirmx('确认要删除该工人银行卡吗？', this.href)">删除</a>
					<a href="${ctx}/empbankcard/bizEmployeeBankcard/relation?id=${bizEmployeeBankcard.id}">关联身份证</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>