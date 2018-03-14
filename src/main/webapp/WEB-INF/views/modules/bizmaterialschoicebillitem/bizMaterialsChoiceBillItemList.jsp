<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>选材单材料表管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			findFirstCode();
			findSecondCode("1");
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
		
		//查询一级类目
		function findFirstCode(){
			
			var categoryLevel = "1";
			var html3 = '<option value=""></option>';
			$.ajax({
				url : "${ctx}/bizmaterialschoicebillitem/bizMaterialsChoiceBillItem/findFirstMaterialsChoiceCategory?categoryLevel=" + categoryLevel,
				type : "get",
				async: false,
				success : function(data) {
					if (null!= data && data.length > 0) {
						for (var v = 0; v < data.length; v++) {
							if('${bizMaterialsChoiceBillItem.firstMaterialsChoiceCategoryCode}' == data[v].categoryCode){
								$("#s2id_firstMaterialsChoiceCategoryCode .select2-chosen").html(data[v].categoryName);
								html3 = html3 + "<option value='"+data[v].categoryCode+"' selected='selected'>"+data[v].categoryName+"</option>";
							}else{
								html3 = html3 + "<option value='"+data[v].categoryCode+"'>"+data[v].categoryName+"</option>";
							}
						}
						$("#firstMaterialsChoiceCategoryCode").html(html3);
					} else {
						$("#firstMaterialsChoiceCategoryCode").html(html3);
					}
				}
			});
		}

		//查询二级类目
		function findSecondCode(type){
			
			var firstCode = $("#firstMaterialsChoiceCategoryCode").val();
			if (firstCode=="" || undefined == firstCode) {
				return false;
			}
			var categoryLevel = "2";
			var html3 = '<option value=""></option>';
			$.ajax({
				url : "${ctx}/bizmaterialschoicebillitem/bizMaterialsChoiceBillItem/findSecondMaterialsChoiceCategory?categoryLevel=" + categoryLevel+"&firstCode="+firstCode,
				type : "get",
				success : function(data) {
					if (null!= data && data.length > 0) {
					    if("1"==type){
                            for (var v = 0; v < data.length; v++) {
                                if('${bizMaterialsChoiceBillItem.materialsChoiceCategoryCode}' == data[v].categoryCode){
                                    $("#s2id_materialsChoiceCategoryCode .select2-chosen").html(data[v].categoryName);
                                    html3 = html3 + "<option value='"+data[v].categoryCode+"' selected='selected'>"+data[v].categoryName+"</option>";
                                }else{
                                    html3 = html3 + "<option value='"+data[v].categoryCode+"'>"+data[v].categoryName+"</option>";
                                }
                            }
                        }else{
                            for (var v = 0; v < data.length; v++) {
                                    html3 = html3 + "<option value='"+data[v].categoryCode+"'>"+data[v].categoryName+"</option>";
                            }
                        }

						$("#materialsChoiceCategoryCode").html(html3);
					} else {
						$("#materialsChoiceCategoryCode").html(html3);
					}
				}
			});
		}
		
		function selectMessage(){
			
			var storeId = $("#storeId").val();
			if (storeId=="" || undefined==storeId ) {
				alertx("请选择门店");
				return false;
			}
			var firstCode = $("#firstMaterialsChoiceCategoryCode").val();
			if (firstCode=="" || firstCode==storeId ) {
				alertx("请选择一级类目");
				return false;
			}
			$("#searchForm").submit();
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/bizmaterialschoicebillitem/bizMaterialsChoiceBillItem/preList">选材类目明细查询</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="bizMaterialsChoiceBillItem" action="${ctx}/bizmaterialschoicebillitem/bizMaterialsChoiceBillItem/list" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>门店：</label>
				<form:select path="storeId" class="input-medium needClear">
					<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>订单编号：</label>
				<form:input path="orderNumber" htmlEscape="false" maxlength="100" class="input-medium needClear"/>
			</li>
			<li><label>客户姓名：</label>
				<form:input path="customerName" htmlEscape="false" maxlength="16" class="input-medium needClear"/>
			</li>
			<li><label>一级类目：</label>
				<form:select path="firstMaterialsChoiceCategoryCode" class="input-medium needClear" onchange="findSecondCode('2')">
				</form:select>
			</li>
			<li><label>二级类目：</label>
				<form:select path="materialsChoiceCategoryCode" class="input-medium needClear">
				</form:select>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="button" value="查询" onclick="selectMessage()"/></li>
			<li class="btns"><input class="btn btn-primary clearBtn" type="button" value="清空"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>门店</th>
				<th>订单编号</th>
				<th>客户姓名</th>
				<th>一级类目</th>
				<th>二级类目</th>
				<th>品牌</th>
				<th>型号</th>
				<th>属性</th>
				<th>供应商</th>
				<th>单位</th>
				<th>规格</th>
				<th>位置</th>
				<th>预算用量1</th>
				<th>预算用量2</th>
				<th>损耗系数</th>
				<th>含损耗用量</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="bizMaterialsChoiceBillItem">
			<tr>
				<td>
					${bizMaterialsChoiceBillItem.storeName}
				</td>
				<td>
					${bizMaterialsChoiceBillItem.orderNumber}
				</td>
				<td>
					${bizMaterialsChoiceBillItem.customerName}
				</td>
				<td>
					${bizMaterialsChoiceBillItem.firstCategoryName}
				</td>
				<td>
					${bizMaterialsChoiceBillItem.categoryName}
				</td>
				<td>
					${bizMaterialsChoiceBillItem.brand}
				</td>
				<td>
					${bizMaterialsChoiceBillItem.model}
				</td>
				<td>
					${bizMaterialsChoiceBillItem.attribute}
				</td>
				<td>
					${bizMaterialsChoiceBillItem.supplierName}
				</td>
				<td>
					${bizMaterialsChoiceBillItem.unit}
				</td>
				<td>
					${bizMaterialsChoiceBillItem.spec}
				</td>
				<td>
					${bizMaterialsChoiceBillItem.position}
				</td>
				<td>
					${bizMaterialsChoiceBillItem.budgetNumber1}
				</td>
				<td>
					${bizMaterialsChoiceBillItem.budgetNumber2}
				</td>
				<td>
					${bizMaterialsChoiceBillItem.lossRatio}
				</td>
				<td>
					${bizMaterialsChoiceBillItem.includeLossNumber}
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>