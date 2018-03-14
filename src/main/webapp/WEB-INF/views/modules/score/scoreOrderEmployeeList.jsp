<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>员工评价统计统计</title>
	<style>
		.undis{display:none;}
		.g-mask{width:100%;height:100%;position:relative;z-index:99;font-size:0;}
		#g-done_ask{width:600px;height:360px;position:fixed;left:50%;top:50%;transform:translate(-50%,-50%);border:1px solid #333;border-radius:4px;}
		.refuse{height:40px;line-height:40px;font-size:20px;background:#54b4eb;margin:0;}
		.content{color:#000;width:600px;height:100px;resize: none;margin:0;}
		.second{width:600px;}
		.second a{display:block;width:300px;height:40px;line-height:40px;font-size:24px;text-decoration:none;float:left;text-align:center;background:#54b4eb;color:#fff;}
		.second a:first-child{box-sizing:border-box;border-right:1px solid #ccc;}
	</style>
	
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
	$(document).ready(function() {
		positionType();
	});
	function page(n,s){
		$("#pageNo").val(n);
		$("#pageSize").val(s);
		$("#searchForm").submit();
    	return false;
    }
	function submitForm(type){
		if (type == 'query'){
			var position=$("#position").val();
			//$("#position1").attr("value",position);
			$("#searchForm").attr("action","${ctx}/score/employee/scoreOrderEmployeelist2");
		}else {
			$("#searchForm").attr("action","${ctx}/score/employee/export");
		}
		$("#searchForm").submit();	
		}
	function positionType(){
		var name=$("#storeId").val();
		//var xx=$("#position1").val();
		$.ajax({
		    url: "${ctx}/score/employee/selectPositionTypeByStoreId",    //请求的url地址
		    dataType: "json",   //返回格式为json
		    async: true, //请求是否异步，默认为异步，这也是ajax重要特性
		    data: { 
		   	storeId:name
		    	},    //参数值
		    type: "POST",   //请求方式
		    success: function(req) {
		    	if(req != undefined && req != null && req != ""){
					var jsonObj = eval(req);
					htmls = "<option></option>";
					
					for (var i = 0; i < jsonObj.length; i++) {
						if('${scorOrderEmployee.employeePost}' == jsonObj[i].employeePost){
							$("#s2id_position .select2-chosen").html(jsonObj[i].employeePost);
							htmls = htmls + "<option value='"+jsonObj[i].employeePost+"' selected='selected'>"+jsonObj[i].employeePost+"</option>";
						}else{
							htmls = htmls + "<option value='"+jsonObj[i].employeePost+"'>"+jsonObj[i].employeePost+"</option>";
						}
					} 
					$('#position').html(htmls);
					/* if(typeof(xx)!= "undefined" && xx!=""){
						$('#position').val(xx);
					} */
				}else{
					$('#position').html("");
				}
		    },
		    error: function() {
		    	 
		    }
		});
		
		
	}
	
	function clearCondition(){
		 document.getElementById("searchForm").reset();
		
		 var inputObjs=jQuery("#searchForm input[type='text']"); 
		 for(var i=0;i<inputObjs.length;i++){ 
		 var inputObj = inputObjs[i]; 
		 inputObj.value=""; 
		 } 
		
		 var selectObjs = jQuery("#searchForm input[class='input-medium']"); 
		 for(var i=0;i<selectObjs.length;i++){ 
		 var selectObj = selectObjs[i]; 
		 selectObj.value=""; 
		 } 
		 
		 var inputObjs=jQuery("#searchForm input[type='number']"); 
		 for(var i=0;i<inputObjs.length;i++){ 
		 var inputObj = inputObjs[i]; 
		 inputObj.value=""; 
		 }
	}
	
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"></li>
	</ul>
		<form:form id="searchForm" modelAttribute="scorOrderEmployee" action="${ctx}/score/employee/scoreOrderEmployeelist2" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>分公司：</label>
				<form:select path="storeId" class="input-medium needClear" onchange="positionType()" >
                    <form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
                </form:select>
			</li>
			<li>
			<label>岗位：</label>
				<form:select  path="employeePost" class="input-medium needClear" id="position">
				
				</form:select>
			</li>
			 <%-- <input id="position1" type="hidden" value="${scorOrderEmployee.employeePost}" name="employeePost" /> --%> 
			<li><label>分值区间：</label>
			<input id="scoreBegin"  name="scoreBegin" type="number" value="${scorOrderEmployee.scoreBegin }" min=0   max=10 />~<input value="${scorOrderEmployee.scoreEnd}" name="scoreEnd" id="numberEnd" type="number"  min=0 max=10 />
			</li>
			<li>
				<form:input type="text"  path="query" name="query" placeholder="员工姓名/员工工号/员工电话"  />
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="button" value="查询" onclick ="submitForm('query')"/></li>
			 <li class="btns"><input id="btnExport" class="btn btn-primary" type="button" value="导出" onclick ="submitForm('export')"/></li> 
			<li class="btns"><input class="btn btn-primary clearBtn" type="button" value="清空" onclick="clearCondition()"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<%-- <sys:message content="${message}"/> --%>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>分公司</th>
				<th>员工排名（好评数）</th>
				<th>岗位</th>
				<th>员工姓名</th>
				<th>员工工号</th>
				<th>员工电话</th>
				<th>好评率</th>
				<th>好评数</th>
				<th>差评数</th>
				<th>综合评分</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="scoreOrder" varStatus="sta">
			<tr>
				<td title="${scoreOrder.name}">
					${scoreOrder.name}
				</td>
				<td title="${sta.index+1}">
					${sta.index+1}
				</td>
				<td title="${scoreOrder.employeePost}">
					${scoreOrder.employeePost}
				</td>
				<td title="${scoreOrder.employeeName}">
					${scoreOrder.employeeName}
				</td>
				<td title="${scoreOrder.no}">
					${scoreOrder.no}
				</td>
				
				<td title="${scoreOrder.employeePhone}">
					${scoreOrder.employeePhone}
				</td>
				<c:if test="${scoreOrder.goodRate ==null}">
					<td title="-">
					-
					</td>
				</c:if>
				<c:if test="${scoreOrder.goodRate !=null}">
					<td title="${scoreOrder.goodRate} ">
					${scoreOrder.goodRate} 
					</td>
				</c:if>
				<td title="${scoreOrder.goodNum} ">
					${scoreOrder.goodNum} 
				</td>
				<td title="${scoreOrder.badNum} ">
					${scoreOrder.badNum} 
				</td>
				<td title="${scoreOrder.avgScore} ">
					${scoreOrder.avgScore} 
				</td>
				<td></td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="g-mask undis" id="approve">
	</div>
	<div class="pagination">${page}</div>
	<script type="text/javascript">
		$(document).ready(function() {
		function submitForm(){
			var a = $("#storeId").val();
		}
		});
	</script>
	<script type="text/javascript">
	</script>
</body>
</html>