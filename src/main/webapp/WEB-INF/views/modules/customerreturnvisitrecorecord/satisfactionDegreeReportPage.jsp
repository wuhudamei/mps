<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html>
<head>
	<title>回访记录满意度汇总</title>
	<meta name="decorator" content="default"/>
	<style>
		.pad_btm40{padding-bottom:20px;}
		.alertMask{position: fixed;top: 0;bottom: 0;left: 0;right: 0;background: rgba(0,0,0,.5);}
		.maskWrapper{width: 30%;margin: 10% auto 0;border-radius: 4px;background: #fff;font-size: 16px;}
		.col_3{color: #333}
		.col_6{color: #666;}
		.col_f{color: #fff;}
		.col_fdfcfa{color: #fdfcfa;}
		.col_0780ec{color: #0780ec;}
		.font28{font-size: 14px;}
		.font33{font-size: 16.5px;}
		.maskTit{height: 50px;line-height: 50px;text-align: center;}
		.maskContent{padding: 25px;border-top: 1px solid #ddd;border-bottom: 1px solid #ddd;line-height: 75px;}
		.maskBtns{width: 83%;margin: 0 auto;padding-bottom: 10px;padding-top: 10px;}
		/* .maskBtn{display: block;width: 60%;} */
		.maskBtn{background: #0780ec;border-radius: 2px;display: block;width: 47.6%;
    		text-align: center;line-height: 38px;font-size:16.5px;margin: 0 auto;}
    	.maskBtnOne{background: #0780ec;border-radius: 2px;display: block;width: 47.6%;
    		text-align: center;line-height: 38px;font-size:16.5px;margin: 0 auto;}
    	.twoBtns .maskBtn:first-child{background: #0780ec;border-radius: 2px;float: left;}
		.twoBtns .maskBtn:last-child{border: 1px solid #0780ec;box-sizing: border-box;border-radius: 2px;float: right;background: #fff;}
		.undis{display:none;}
	</style>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		//清空查询条件
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
		}
		//有提示框的 确定
		function sureTable(){
			$("#warnModel").addClass("undis");
		}
		//查询操作
		function queryDeal(){
			if($("#storeId").val()==null ||$("#storeId").val()==''){
		 		$("#warnModel").removeClass("undis");
		 		return ;
		 	}else{
		 		$("#searchForm").submit();	
		 	}
		}
		
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/customerreturnvisitrecorecord/bizCustomerReturnVisitRecord/satisfactionDegreeReportPage">回访记录满意度汇总</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="bizCustomerReturnVisitRecord" action="${ctx}/customerreturnvisitrecorecord/bizCustomerReturnVisitRecord/satisfactionDegreeQuery" method="post" class="breadcrumb form-search">
		
		<ul class="ul-form">
			<li><label>门店：</label>
				<form:select path="storeId" class="input-medium needClear">
					<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			
			<li><label>回访时间：</label>
				<input name="visitDateBegin" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${bizCustomerReturnVisitRecord.visitDateBegin}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
				<input name="visitDateEnd" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${bizCustomerReturnVisitRecord.visitDateEnd}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="button" onclick="queryDeal()" value="查询"/></li>
			<li class="btns"><input class="btn btn-primary clearBtn" type="button" value="清空" onclick="clearCondition()"/></li>
<!-- 			<li class="btns"><input id="btnExport" class="btn btn-primary" type="button" onclick="exportExcel()" value="导出" /></li> 
 -->			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<c:set value="0" var="success_sum" /> 
		<c:set value="0" var="sum_1"/>
		<c:set value="0" var="sum_2"/>
		<c:set value="0" var="sum_3"/>
		<c:set value="0" var="sum_4"/>
		<c:set value="0" var="sum_5"/>
		
		<thead>
			<tr>
				<th>部门名称</th>
				<c:forEach items="${titleArray}" var="title">
					<th>${title}量 </th>
				</c:forEach>
				<th>回访成功量</th>
				<th>满意度</th>
			</tr>
		</thead>
		
		
		<tbody>
		<c:forEach items="${resultList}" var="map">
			<tr>
				
				<td>
					${map.department_name}
				</td>
				<c:forEach items="${nodeArray}" var="node" varStatus="st">
					<c:set var="node_temp" value="${node}"></c:set>
					<td>${ map[node_temp] != null ? map[node_temp] : 0}</td>
					<c:choose>
						<c:when test="${st.index == 1}">
							<c:set value="${sum_1 + map[node_temp]}" var="sum_1" />
						</c:when>
						<c:when test="${st.index == 2}">
							<c:set value="${sum_2 + map[node_temp]}" var="sum_2" />
						</c:when>
						<c:when test="${st.index == 3}">
							<c:set value="${sum_3 + map[node_temp]}" var="sum_3" />
						</c:when>
						<c:when test="${st.index == 4}">
							<c:set value="${sum_4 + map[node_temp]}" var="sum_4" />
						</c:when>
						<c:otherwise>
							<c:set value="${sum_5 + map[node_temp]}" var="sum_5" />
						</c:otherwise>
					</c:choose>
				</c:forEach>
				
				<td>
					${map.successVisitNum}
					<c:set value="${success_sum + map.successVisitNum}" var="success_sum" /> 
				</td>
				<td>${map.satisfactionDegree}</td>
			</tr>
		</c:forEach>
			<tr>
				<td>总计</td>
				<c:forEach items="${titleArray}" var="node" varStatus="st">
					<c:choose>
						<c:when test="${st.index == 1}">
						  <td>${sum_1}</td>
						</c:when>
						<c:when test="${st.index == 2}">
						  <td>${sum_2}</td>
						</c:when>
						<c:when test="${st.index == 3}">
						  <td>${sum_3}</td>
						</c:when>
						<c:when test="${st.index == 4}">
						  <td>${sum_4}</td>
						</c:when>
						<c:otherwise>
						  <td>${sum_5}</td>
						</c:otherwise>						
					</c:choose>
				</c:forEach>
				<td>${success_sum}</td>
				<td></td>
			</tr>
		</tbody>
	</table>
		
	<div class="alertMask undis" id="warnModel">
		<div class="maskWrapper">
			<p class="col_3 maskTit">提示</p>
			<div class="font28 col_6 maskContent">必须选取具体门店</div>
			<div class="maskBtns clearfix twoBtns">
				<a class="maskBtnOne font33 col_fdfcfa"  onclick="sureTable()" href="javascript:void(0);">确定</a>
			</div>
		</div>
	</div>
</body>
</html>