<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>标化辅材管理</title>
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
	var arr = []
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
			//有无限制的初始化
			if($("#limitNumber").val()==1){
				$("input[name='isLimitMaxNumber']:eq('0')").attr("checked",'checked');
				a="1";
				window.onload=restrictedArea();	
				
				
				
				
				
			}else{
				$("input[name='isLimitMaxNumber']:eq('1')").attr("checked",'checked');
				
			}
		});
		
	//0 添加 1 修改
		var a='0';
		//面积table
		function restrictedArea(){
			if(a==1){
				$("#area").attr("hidden",false);
				}else{
				$("#s2id_storeId").attr("readOnly",true);
				$('.control-group input').attr("readOnly",true);
				$("#area").attr("hidden",false);
			}		
		}
		//添加行
		function addTr(){
			var index = $("#evalArea").find('tr').length + 1+$("#updateTr").find('tr').length
			var html="<tr><td>"+ index +"</td>"
			html+="<td><select name='numberRuleCode' class='input-xlarge required' onchange='changeArea(this)'>"
			html+="<option value='1' >71*8/100余数进1(筒灯)</option>"
			html+="<option value='2'>合同面积*8/100余数进1(筒灯)</option>"
			html+="<option value='3'>71*16/100余数进1(灯带)</option>"
			html+="<option value='4'>合同面积*16/100余数进1(灯带)</option>"
			html+="</td>"
			html+="<td><input name='limtArea' value='(0,71)㎡' readOnly='true'/></td>"
			html+="<td><a class='btn-delete' href='javascript:void(0)' onclick='deleteTr(this)'>刪除</a></td></tr>"
			$("#evalArea").append(html);
		}
		//删除行
		function deleteTr(val){
			$(val).parents('tr').remove()
		}
		//不限制
		function noRestrictedArea(){
			$("#area").attr("hidden",true);
			$("#evalArea").html("");
		}
		//多选
		function changeArea(val){
			var htmls='';
			if($(val).val()%2==0){
				htmls="(71,999)㎡"
			}else{
				htmls="(0,71)㎡"
			}
			$(val).parent().next().html("");
			$(val).parent().next().append("<input name='limtArea'  value='"+htmls+"' readOnly='true'/>")
		}
		//保存的校验
		function saveForm(){
			// 1有限制  0 没限制
			var check=$('input:radio[name="isLimitMaxNumber"]:checked').val()
			if(check=='1'){
				//行數
				var index = $("#evalArea").find('tr').length+$("#updateTr").find('tr').length;
				//行数 是 0  弹框
				if(index==0){
					$("#warnModel").removeClass("undis");
				}else{
					$("#inputForm").submit();
				}
			}
			//没选的话 去校验
			if(check=="undefined"){
				$("#inputForm").submit();
			}
			if(check=='0'){
				$("#inputForm").submit();
			}
			
		}
		//有提示框的 確定
		function sureTable(){
			$("#warnModel").addClass("undis");
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/standradmaterials/bizMaterialsLight/">灯具和五金列表</a></li>
		<li class="active"><a href="${ctx}/standradmaterials/bizMaterialsLight/form?id=${bizMaterialsStandard.id}">灯具和五金<shiro:hasPermission name="standradmaterials:bizMaterialsLight:edit">${not empty bizMaterialsStandard.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="standradmaterials:bizMaterialsLight:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="bizMaterialsStandard" action="${ctx}/standradmaterials/bizMaterialsLight/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">门店id：</label>
			<div class="controls">
				<form:select path="storeId" class="input-xlarge required">
					<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">物料类别 ：</label>
			<div class="controls">
				<form:input path="materialsType" htmlEscape="false" maxlength="100" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">物料名称 ：</label>
			<div class="controls">
				<form:input path="materialsName" htmlEscape="false" maxlength="100" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">物料单位 ：</label>
			<div class="controls">
				<form:input path="materialsUnit" htmlEscape="false" maxlength="10" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		
		
		<div class="control-group">
			<label class="control-label">物料单价 ：</label>
			<div class="controls">
				<form:input path="materialsPrice" htmlEscape="false" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注：</label>
			<div class="controls">
				<form:input path="remarks" htmlEscape="false" maxlength="500" class="input-xlarge"/>
				<!-- <span class="help-inline"><font color="red">*</font> </span> -->
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">状态：</label>
			<div class="controls">
				<form:radiobuttons path="isEnabled" items="${fns:getDictList('biz_enable_status')}" itemLabel="label" itemValue="value" htmlEscape="false" class="required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<input value="${bizMaterialsStandard.isLimitMaxNumber}" id="limitNumber" type="hidden">
			<label class="control-label">建议申请数量：</label>
			<div class="controls">
				<input  type="radio" id="radio1" name="isLimitMaxNumber" value="1" onclick="restrictedArea()"  class="required"/> 有限制
				<input  type="radio"  name="isLimitMaxNumber" value="0"  class="required" onclick="noRestrictedArea()"/> 不限制
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
			<br/><br/><br/>
		<div id="area" hidden="true">	
			建议申请数量：根据计算公式余数进1
			
		<table  class="table table-striped table-bordered table-condensed">
					<thead>
						<tr>
							<th>序号</th>
							<th>建议申请数量</th>
							<th>面积限制</th>
							<th>操作</th>
						</tr>
					</thead>
					
					
					<tbody id="updateTr" >
			<c:forEach items="${ bizMaterialsStandard.bizMaterialsStandardNumberSquareList}" var="square" varStatus="status">
			<tr>
			<td>${status.index+1}<input value="${square.id}" name="sId" type="hidden" > </td>
			<td>
			<c:choose >
				<c:when test="${square.numberRuleCode==2 }">
			<select  name='numberRuleCode' class='input-xlarge required' onchange='changeArea(this)'>
			<option value='1' >71*8/100余数进1(筒灯)</option>
			<option value='2' selected="selected" >合同面积*8/100余数进1(筒灯)</option>
			<option value='3'>71*16/100余数进1(灯带)</option>
			<option value='4'>合同面积*16/100余数进1(灯带)</option>
			</td>
				</c:when>
				<c:when test="${square.numberRuleCode==3 }">
			<select  name='numberRuleCode' class='input-xlarge required' onchange='changeArea(this)'>
			<option value='1' >71*8/100余数进1(筒灯)</option>
			<option value='2'  >合同面积*8/100余数进1(筒灯)</option>
			<option value='3' selected="selected">71*16/100余数进1(灯带)</option>
			<option value='4' >合同面积*16/100余数进1(灯带)</option>
			</td>
				</c:when>
				<c:when test="${square.numberRuleCode==4 }">
			<select  name='numberRuleCode' class='input-xlarge required' onchange='changeArea(this)'>
			<option value='1' >71*8/100余数进1(筒灯)</option>
			<option value='2'  >合同面积*8/100余数进1(筒灯)</option>
			<option value='3'>71*16/100余数进1(灯带)</option>
			<option value='4' selected="selected">合同面积*16/100余数进1(灯带)</option>
			</td>
				</c:when>
				<c:otherwise>
			<select  name='numberRuleCode' class='input-xlarge required' onchange='changeArea(this)'>
			<option value='1' >71*8/100余数进1(筒灯)</option>
			<option value='2'  >合同面积*8/100余数进1(筒灯)</option>
			<option value='3'>71*16/100余数进1(灯带)</option>
			<option value='4' >合同面积*16/100余数进1(灯带)</option>
				</c:otherwise>
			</c:choose>
			<c:choose>
			<c:when test="${square.numberRuleCode==1}">
			<td>
			<input name='limtArea' value='(0,71)㎡' readOnly='true'/>
			</td>
			</c:when>
			<c:when test="${square.numberRuleCode==3}">
			<td>
			<input name='limtArea' value='(0,71)㎡' readOnly='true'/>
			</td>
			</c:when>
			<c:when test="${square.numberRuleCode==2}">
			<td>
			<input name='limtArea' value='(71,999)㎡' readOnly='true'/>
			</td>
			</c:when>
			<c:otherwise>
			<td>
			<input name='limtArea' value='(71,999)㎡' readOnly='true'/>
			</td>
			</c:otherwise>
			</c:choose>
			<td><a class='btn-delete' href='javascript:void(0)' onclick='deleteTr(this)'>刪除</a>
			</td></tr>
			</c:forEach>
					</tbody>
					<tbody id="evalArea">
						
					</tbody>
					<tbody>
						<tr id="firstTr">
							<td colspan="4"><a href="javascript:void(0)" onclick="addTr()">增加</a> </td>
						</tr>
					</tbody>
		</table>
		</div>		
		<div class="form-actions">
			<shiro:hasPermission name="standradmaterials:bizMaterialsLight:edit"><input id="btnSubmit" class="btn btn-primary" type="button" onclick="saveForm()" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
		
			
		</div>
	</form:form>
		<div class="alertMask undis" id="warnModel">
			<div class="maskWrapper">
				<p class="col_3 maskTit">提示</p>
				<div class="font28 col_6 maskContent">请至少输入一条建议申请数量限制</div>
				<div class="maskBtns clearfix twoBtns">
					<a class="maskBtnOne font33 col_fdfcfa"  onclick="sureTable()" href="javascript:void(0);">确定</a>
				</div>
			</div>
		</div>
</body>
</html>