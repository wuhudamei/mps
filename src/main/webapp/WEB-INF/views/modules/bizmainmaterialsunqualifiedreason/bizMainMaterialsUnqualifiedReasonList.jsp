<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>主材安装项验收不合格原因配置管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
            findInstallName("1");
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }

        function findInstallName(type){
            var html2='<option value=""></option>';
            var html3='<option value=""></option>';
            var storeId = $("#storeId").val();
            var projectModeId = $("#projectMode").val();

            if (storeId=="" ||projectModeId=="" ||undefined==storeId ||undefined==projectModeId) {
                return;
            }
            $.ajax({
                url:"${ctx}/bizengininstall/bizEnginInstall/findInstallItemByStoreIdAndProjectMode?storeId="+storeId+"&projectModeId="+projectModeId,
                success: function(data){

                    if(null!=data&&data.length>0){

                        if("1"== type){
                            for (var v = 0; v < data.length; v++) {

                                if(data[v].isOn == 1){
                                    //启用
                                    if('${bizMainMaterialsUnqualifiedReason.mainMaterialsInstallItemId}' == data[v].id){
                                        $("#s2id_mainMaterialsInstallItemId .select2-chosen").html(data[v].installItemName);
                                        html2 = html2 + "<option value='"+data[v].id+"' selected='selected'>"+data[v].installItemName+"</option>";
                                    }else{
                                        html2 = html2 + "<option value='"+data[v].id+"'>"+data[v].installItemName+"</option>";
                                    }
                                }else{
                                    //停用
                                    if('${bizMainMaterialsUnqualifiedReason.mainMaterialsInstallItemIdStop}' == data[v].id){
                                        $("#s2id_mainMaterialsInstallItemIdStop .select2-chosen").html(data[v].installItemName);
                                        html3 = html3 + "<option value='"+data[v].id+"' selected='selected'>"+data[v].installItemName+"</option>";
                                    }else{
                                        html3 = html3 + "<option value='"+data[v].id+"'>"+data[v].installItemName+"</option>";
                                    }
                                }
                            }
                        }else {
                            for (var v = 0; v < data.length; v++) {
                                if(data[v].isOn == 1){
                                    //启用
                                    html2 = html2 + "<option value='"+data[v].id+"'>"+data[v].installItemName+"</option>";
                                }else{
                                    //停用
                                    html3 = html3 + "<option value='"+data[v].id+"'>"+data[v].installItemName+"</option>";
                                }
                            }
                        }
                        $("#mainMaterialsInstallItemId").html(html2);
                        $("#mainMaterialsInstallItemIdStop").html(html3);
                    } else {
                        $("#mainMaterialsInstallItemId").html(html2);
                        $("#mainMaterialsInstallItemIdStop").html(html3);
                    }
                }
            })
        }


	</script>
	<style>
		a{color:#2fa4e7;}
		.undis{display:none;}
		body {
			background-color: #fff;
			font-size: 16px;
		}
		body {
			width: 100%;
			height: 100%;
			position: relative
		}
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
		.twoBtns .maskBtn:first-child{background: #0780ec;border-radius: .1rem;float: left;}
		.twoBtns .maskBtn:last-child{border: 1px solid #0780ec;box-sizing: border-box;border-radius: .1rem;float: right;background: #fff;}
	</style>

</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/mainmaterialsunqualifiedreason/bizMainMaterialsUnqualifiedReason/preList">主材安装项验收不合格原因配置列表</a></li>
		<shiro:hasPermission name="mainmaterialsunqualifiedreason:bizMainMaterialsUnqualifiedReason:edit"><li><a href="${ctx}/mainmaterialsunqualifiedreason/bizMainMaterialsUnqualifiedReason/form">主材安装项验收不合格原因配置添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="bizMainMaterialsUnqualifiedReason" action="${ctx}/mainmaterialsunqualifiedreason/bizMainMaterialsUnqualifiedReason/list" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>门店：</label>
				<form:select path="storeId" class="input-medium needClear" onchange="findInstallName('2')">
					<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>工程模式：</label>
				<c:if test="${empty gongcheng}">
					<form:select path="projectMode" class="input-medium" disabled="true" onchange="findInstallName('2')">
						<form:option value="" label=""/>
						<form:options items="${fns:getDictList('project_mode')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
				<c:if test="${!empty gongcheng}">
					<form:select path="projectMode" class="input-medium needClear" onchange="findInstallName('2')">
						<form:option value="" label=""/>
						<form:options items="${fns:getDictList('project_mode')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</c:if>
			</li>
			<li><label>安装模式：</label>
				<form:select path="installMode" class="input-medium needClear">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('install_mode')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label style="width:140px;">主材安装项（停用）：</label>
				<form:select path="mainMaterialsInstallItemIdStop" class="input-medium needClear">
				</form:select>
			</li>
			<li><label>主材安装项：</label>
				<form:select path="mainMaterialsInstallItemId" class="input-medium needClear">
				</form:select>
			</li>
			<li><label>不合格原因：</label>
				<form:input path="unqualifiedReason" htmlEscape="false" maxlength="100" class="input-medium needClear"/>
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
				<th>工程模式</th>
				<th>安装模式</th>
				<th>主材安装项</th>
				<th>不合格验收原因</th>
				<th>最后操作人</th>
				<th>最后操作人</th>
				<th>启用状态</th>
				<shiro:hasPermission name="mainmaterialsunqualifiedreason:bizMainMaterialsUnqualifiedReason:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="bizMainMaterialsUnqualifiedReason">
			<tr>
				<td>
					${bizMainMaterialsUnqualifiedReason.storeName}
				</td>
				<td>
					${bizMainMaterialsUnqualifiedReason.projectModeName}
				</td>
				<td>
					${bizMainMaterialsUnqualifiedReason.installModeName}
				</td>
				<td>
					${bizMainMaterialsUnqualifiedReason.mainMaterialsInstallItem}
				</td>
				<td>
					${bizMainMaterialsUnqualifiedReason.unqualifiedReason}
				</td>
				<td>
					${bizMainMaterialsUnqualifiedReason.operatorName}
				</td>
				<td>
					<fmt:formatDate value="${bizMainMaterialsUnqualifiedReason.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<c:if test="${bizMainMaterialsUnqualifiedReason.status eq 1}"><span style="color: green;">启用</span></c:if>
					<c:if test="${bizMainMaterialsUnqualifiedReason.status ne 1}"><span style="color: red;">停用</span></c:if>
				</td>
				<shiro:hasPermission name="mainmaterialsunqualifiedreason:bizMainMaterialsUnqualifiedReason:edit"><td>
    				<a href="#" onclick="reasonEnable('${bizMainMaterialsUnqualifiedReason.id}','${bizMainMaterialsUnqualifiedReason.status}')">
						<c:if test="${bizMainMaterialsUnqualifiedReason.status eq 1}">停用</c:if>
						<c:if test="${bizMainMaterialsUnqualifiedReason.status ne 1}">启用</c:if>
					</a>
					<a href="#" onclick="reasonDelete('${bizMainMaterialsUnqualifiedReason.id}')">删除</a>
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
					<a class="maskBtn font33 col_f" onclick="buttonSure()">我知道了</a>
				</div>
			</div>
		</div>
	</div>
	<div style="background:rgba(0,0,0,.4);z-index:9;" class="g-mask undis" id ="subReportTwo">
		<div class="alertMask">
			<div class="maskWrapper">
				<p class="col_3 maskTit">通知</p>
				<div class="font28 col_6 maskContent" id="alertRemarksTwo"></div>
				<div class="maskBtns clearfix twoBtns">
					<a class="maskBtn font33 col_fdfcfa" onclick="yesTwo()">确定</a>
					<a class="maskBtn font33 col_0780ec" onclick="cancelTwo()">取消</a>
				</div>
			</div>
		</div>
	</div>
	<div style="background:rgba(0,0,0,.4);z-index:9;" class="g-mask undis" id ="subReportThree">
		<div class="alertMask">
			<div class="maskWrapper">
				<p class="col_3 maskTit">通知</p>
				<div class="font28 col_6 maskContent">确认要删除该主材安装项验收不合格原因配置吗？</div>
				<div class="maskBtns clearfix twoBtns">
					<a class="maskBtn font33 col_fdfcfa" onclick="yesThree()">确定</a>
					<a class="maskBtn font33 col_0780ec" onclick="cancelThree()">取消</a>
				</div>
			</div>
		</div>
	</div>


	<div class="pagination">${page}</div>

	<script type="text/javascript">

        var idGlobal;
        var typeNameGlobal;

		function buttonSure(){
            //提交表单
            $("#searchForm").submit();
		}
		function cancelTwo(){
            $("#subReportTwo").hide();
		}
		function cancelThree(){
            $("#subReportThree").hide();
		}

		//-------------------------------------停启用------------------------------------
		//停启用
		function reasonEnable(id,reasonStatus){
            idGlobal = id;
            if("1" == reasonStatus){
                typeNameGlobal = "停用";
			}else {
                typeNameGlobal = "启用";
			}
            $("#alertRemarksTwo").text("确认要"+typeNameGlobal+"该主材安装项验收不合格原因配置吗？");
            $("#subReportTwo").show();
		}
        function yesTwo(){
            $.ajax({
                url: "${ctx}/mainmaterialsunqualifiedreason/bizMainMaterialsUnqualifiedReason/enable",
                type: "post",
                data:{
                    id:idGlobal
                },
                success: function(data) {
                    $("#subReportTwo").hide();
                    if(null!=data && data=="0"){
                        $("#alertRemarks").text("该主材安装项验收不合格原因配置"+typeNameGlobal+"成功");
                        $("#subReport").show();
                    }else {
                        $("#alertRemarks").text("该主材安装项验收不合格原因配置"+typeNameGlobal+"失败");
                        $("#subReport").show();
                    }
                }
            })


        }
        //-------------------------------------停启用------------------------------------
        //-------------------------------------删除------------------------------------
		//删除
		function reasonDelete(id){
            idGlobal = id;
            $("#subReportThree").show();
		}
        function yesThree(){
            $.ajax({
                url: "${ctx}/mainmaterialsunqualifiedreason/bizMainMaterialsUnqualifiedReason/delete",
                type: "post",
                data:{
                    id:idGlobal
                },
                success: function(data) {
                    $("#subReportThree").hide();
                    if(null!=data && data=="0"){
                        $("#alertRemarks").text("该主材安装项验收不合格原因配置删除成功");
                        $("#subReport").show();
                    }else {
                        $("#alertRemarks").text("该主材安装项验收不合格原因配置删除失败");
                        $("#subReport").show();
                    }
                }
            })
        }
        //-------------------------------------删除------------------------------------

	</script>
</body>
</html>