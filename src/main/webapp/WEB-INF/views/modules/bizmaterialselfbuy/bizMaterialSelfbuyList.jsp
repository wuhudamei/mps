<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>材料自采表管理</title>
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
	<style type="text/css">
		.undis{display:none;}
		.alertMask{position: fixed;top: 0;bottom: 0;left: 0;right: 0;background: rgba(0,0,0,.5);}
		.maskWrapper{width: 380px;border-radius: 4px;background: #fff;font-size: 16px;
		    position: fixed;top: 50%;left: 50%;transform: translate(-50%,-50%);}
		.col_3{color: #333}
		.col_6{color: #666;}
		.col_f{color: #fff;}
		.col_fdfcfa{color: #fdfcfa;}
		.col_0780ec{color: #0780ec;}
		.font28{font-size: 14px;}
		.font33{font-size: 16.5px;}
		.maskTit{height: 50px;line-height: 50px;text-align: center;}
		.maskContent{padding: 25px;border-top: 1px solid #ddd;border-bottom: 1px solid #ddd;line-height: 1.5em;}
		.maskBtns{width: 83%;margin: 0 auto;padding-bottom: 10px;padding-top: 10px;}
		.maskBtn{display: block;width: 60%;text-align: center;line-height: 38px;}
		.maskBtn{background: #0780ec;border-radius: 4px;display: block;width: 47.6%;
    		text-align: center;line-height: 38px;font-size:16px;margin: 0 auto;}
    		
	
	</style>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/bizmaterialselfbuy/bizMaterialSelfbuy/preList">材料自采表列表</a></li>
		<shiro:hasPermission name="bizmaterialselfbuy:bizMaterialSelfbuy:edit"><li><a href="${ctx}/bizmaterialselfbuy/bizMaterialSelfbuy/form">材料自采表添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="bizMaterialSelfbuy" action="${ctx}/bizmaterialselfbuy/bizMaterialSelfbuy/list" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>门店：</label>
				<form:select path="storeId" class="input-medium needClear">
					<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>工程模式：</label>
				<c:if test="${empty gongcheng}">
					<form:select path="projectMode" class="input-medium" disabled="true" >
						<form:option value="" label=""/>
						<form:options items="${fns:getDictList('project_mode')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
				<c:if test="${!empty gongcheng}">
					<form:select path="projectMode" class="input-medium needClear" >
						<form:option value="" label=""/>
						<form:options items="${fns:getDictList('project_mode')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
			</li>
			<li><label>材料名称：</label>
				<form:input path="materialName" htmlEscape="false" maxlength="20" class="input-medium needClear"/>
			</li>
			<li><label>所属结算阶段：</label>
				<form:select path="settleStage" class="input-medium needClear">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('settle_stage')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="btns"><input class="btn btn-primary clearBtn" type="button" value="清空" onclick="clearCondition()"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>门店</th>
				<th>工程模式</th>
				<th>自采材料名称</th>
				<th>项目经理结算比例</th>
				<th>所属结算阶段</th>
				<th>状态</th>
				<shiro:hasPermission name="bizmaterialselfbuy:bizMaterialSelfbuy:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="bizMaterialSelfbuy">
			<tr>
				<td>
					${bizMaterialSelfbuy.storeName}
				</td>
				<td>
					${bizMaterialSelfbuy.projectmodeName}
				</td>
				<td>
					${bizMaterialSelfbuy.materialName}
				</td>
				<td>
					${bizMaterialSelfbuy.settleRate}%
				</td>
				<td>
					${bizMaterialSelfbuy.settleStageName}
				</td>
				<td>
					<c:if test="${bizMaterialSelfbuy.isEnabled=='1' }"><span style="color:#00EC00;">启用</span></c:if>
					<c:if test="${bizMaterialSelfbuy.isEnabled=='0' }"><span style="color:red;">停用</span></c:if>
				</td>
				<shiro:hasPermission name="bizmaterialselfbuy:bizMaterialSelfbuy:edit"><td>
				
    				<a href="${ctx}/bizmaterialselfbuy/bizMaterialSelfbuy/form?id=${bizMaterialSelfbuy.id}">修改</a>
					
					<a href="#" onclick="materialIsEnabled('${bizMaterialSelfbuy.id}','${bizMaterialSelfbuy.isEnabled}')">
						<c:if test="${bizMaterialSelfbuy.isEnabled=='0' }"><span style="color:#00EC00;">启用</span></c:if>
						<c:if test="${bizMaterialSelfbuy.isEnabled=='1' }"><span style="color:red;">停用</span></c:if>
					</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	
	<div style="background:rgba(0,0,0,.4);z-index:9;" class="g-mask undis" id ="subReport">
		<div class="alertMask">
			<div class="maskWrapper">
				<p class="col_3 maskTit">通知</p>
				<div class="font28 col_6 maskContent" id="alertRemarks"></div>
				<div class="maskBtns clearfix">
					<a class="maskBtn font33 col_f"  onclick="sendMessage()" href="javascript:;">我知道了</a>
				</div>
			</div>
		</div>
	</div>
	<div style="background:rgba(0,0,0,.4);z-index:9;" class="g-mask undis" id ="subReport2">
		<div class="alertMask">
			<div class="maskWrapper">
				<p class="col_3 maskTit">通知</p>
				<div class="font28 col_6 maskContent" id="alertRemarks2"></div>
				<div class="maskBtns clearfix">
					<a class="maskBtn font33 col_f"  onclick="sendMessage2()" href="javascript:;">我知道了</a>
				</div>
			</div>
		</div>
	</div>
	
	<div class="pagination">${page}</div>
	<script type="text/javascript">
		
		
		function sendMessage(){
			$("#subReport").hide();
		}
		function sendMessage2(){
			//提交表单
	    	$("#searchForm").submit();
		}
		function materialIsEnabled(id,isEnabled){
			$.ajax({
				url: "${ctx}/bizmaterialselfbuy/bizMaterialSelfbuy/delete",
	            type: "post",
	            data:{
	            		id:id,
	            		isEnabled:isEnabled
	            	},
	            success: function(data) {
	            	
	            	if(null!=data && data=="0"){
	            		$("#alertRemarks").text("自选材料状态有误");
	            		$("#subReport").show();
	            	}else if(data=="1"){
	            		$("#alertRemarks").text("自选材料ID为空");
	            		$("#subReport").show();
	            	}else if(data=="2"){
	            		$("#alertRemarks").text("自选材料的状态为空");
	            		$("#subReport").show();
	            	}else if(data=="3"){
	            		$("#alertRemarks2").text("该自选材料停用成功");
	            		$("#subReport2").show();
	            	}else if(data=="4"){
	            		$("#alertRemarks2").text("该自选材料启用成功");
	            		$("#subReport2").show();
	            	}
	            }
			})
			
		}
	
	
	</script>
</body>
</html>