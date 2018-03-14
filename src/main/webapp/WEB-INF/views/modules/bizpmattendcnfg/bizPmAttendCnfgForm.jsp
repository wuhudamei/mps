<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>项目经理考勤基础设置管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
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
		});
		function addStar(){
			var limitSize =${fn:length(managerStarList)};
			var trSize = $("#contentTable tr").length;
			if(trSize<=limitSize){
				
				var td1 = '<select name="star" class="input-xlarge required"> ';
				td1 +='<c:forEach items="${managerStarList}" var = "managerStar">'	;	
				td1+='<option value="${managerStar.value}" >${managerStar.label}</option>'
				td1+='</c:forEach>';
				td1+='</select>';
				var td2 = '<input type="text" name="starSalaryAllAttend"  onkeyup="value=value.replace(/[^\\d\\	.]/g,&apos;&apos;)"/>';
				var td3 = '<input type="text" name="starSalaryMin"  onkeyup="value=value.replace(/[^\\d\\.]/g,&quot;&quot;)"/>';
				var td4 = '<input  class="btn" type="button" value="删除" onclick="delStar(this)"/>';
				
				var trHTML = '<tr><td>'+td1+'</td><td>'+td2+'</td><td>'+td3+'</td><td>'+td4+'</td></tr>';
				$("#contentTable").append(trHTML);//在table最后面添加一行 
			}
		}
		function deleteStar(obj,id){
			if(confirm("确定要删除吗？")) {
				$.post('${ctx}/bizpmattendcnfg/bizPmAttendCnfg/deleteStarById',{'id':id},function(data){
					$(obj).parent().parent().remove();
				});
			 }
		}
		function delStar(obj){
			$(obj).parent().parent().remove();
		}
		//查询数组中是否有重复，用于校验项目经理星级是否重复
		function isRepeat(arr) {
		   var hash = {};
		   for(var i in arr) {
		       if(hash[arr[i]])
		       {
		           return true;
		       }
		       // 不存在该元素，则赋值为true，可以赋任意值，相应的修改if判断条件即可
		       hash[arr[i]] = true;
		    }
		   return false;
		}
		function save(){
			if($("#storeId")==null||$("#storeId").val()==""){
				alertx("门店不能为空！");
				return ;
			}
			if($("#signCycleDaysBasicwork")==null||$("#signCycleDaysBasicwork").val()==""){
				alertx("基装签到周期天数不能为空！");
				return ;
			}
			if($("#signCycleDaysComplete")==null||$("#signCycleDaysComplete").val()==""){
				alertx("竣工天数不能为空！");
				return ;
			}
			if($("#effectMonth")==null||$("#effectMonth").val()==""){
				alertx("生效月份不能为空！");
				return ;
			}
			if($("#isEnabled")==null||$("#isEnabled").val()==""){
				alertx("启用状态不能为空！");
				return ;
			}
			var myStars=new Array();
			var total=0;
			$("select[name='star_u']").each(function(){
				myStars[total]= $(this).val();
				total =total+1;
			});
			$("select[name='star']").each(function(){
				myStars[total]= $(this).val();
				total =total+1;
			  });
			if(isRepeat(myStars)) {
				alertx("项目经理星级重复，请检查！");
				return ;
			}
			var bool = true;
			$("input[name='starSalaryAllAttend_u']").each(function(){
				if($.trim($(this).val())==''){
					bool = false;
					return false;
				}
			});
			if(!bool){
				alertx("项目经理底薪不能为空，请检查！");
				return ;
			}
			$("input[name='starSalaryAllAttend']").each(function(){
				if($.trim($(this).val())==''){
					bool = false;
					return false;
				}
			});
			if(!bool){
				alertx("项目经理底薪不能为空，请检查！");
				return ;
			}
			$("input[name='starSalaryMin_u']").each(function(){
				if($.trim($(this).val())==''){
					bool = false;
					return false;
				}
			 });
			if(!bool){
				alertx("项目经理最低额度不能为空，请检查！");
				return ;
			}
			$("input[name='starSalaryMin']").each(function(){
				if($.trim($(this).val())==''){
					bool = false;
					return false;
				}
			 });
			if(!bool){
				alertx("项目经理最低额度不能为空，请检查！");
				return ;
			}
			if(${bizPmAttendCnfg!=null}){
				//判断是否有重复门店和生效日期
				var id = '${bizPmAttendCnfg.id}';
				var effectMonth = $("#effectMonth").val();
				var storeId = $("#storeId").val();
                var projectMode = $("#projectMode").val();
				$.post('${ctx}/bizpmattendcnfg/bizPmAttendCnfg/checkRepeateByStorIdAndMonth',{'storeId':storeId,'effectMonth':effectMonth,'id':id,'projectMode':projectMode,},function(data){
					if(data=='true'){
						$("#inputForm").action="${ctx}/bizpmattendcnfg/bizPmAttendCnfg/save";
						$("#inputForm").submit();
					}else{
						alertx("此门店和生效月份已存在，请核实数据！");
						return ;
					}
				});
			}
			
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/bizpmattendcnfg/bizPmAttendCnfg/">项目经理考勤基础设置列表</a></li>
		<li class="active"><a href="${ctx}/bizpmattendcnfg/bizPmAttendCnfg/form?id=${bizPmAttendCnfg.id}">项目经理考勤基础设置<shiro:hasPermission name="bizpmattendcnfg:bizPmAttendCnfg:edit">${not empty bizPmAttendCnfg.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="bizpmattendcnfg:bizPmAttendCnfg:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="bizPmAttendCnfg" action="${ctx}/bizpmattendcnfg/bizPmAttendCnfg/save"  method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label"><span style="color:red;"> *</span>门店：</label>
			<div class="controls">
				<form:select path="storeId"  class="input-medium needClear" >
						<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">工程模式：</label>
			<div class="controls">
				<form:select path="projectMode" class="input-medium needClear">
						<form:option value="" label=""/>
						<form:options items="${fns:getDictList('project_mode')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>	
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><span style="color:red;"> *</span>基装签到周期天数：</label>
			<div class="controls">
				<form:input path="signCycleDaysBasicwork" htmlEscape="false" maxlength="5" class="input-xlarge" onkeyup="this.value=this.value.replace(/\D/g, '')"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><span style="color:red;"> *</span>竣工签到周期天数：</label>
			<div class="controls">
				<form:input path="signCycleDaysComplete" htmlEscape="false" maxlength="5" class="input-xlarge" onkeyup="this.value=this.value.replace(/\D/g, '')"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><span style="color:red;"> *</span>生效月份：</label>
			<div class="controls">
				<c:if test="${empty bizPmAttendCnfg.id}">
				<input name="effectMonth" id="effectMonth" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					   value="${mon}"
					onclick="WdatePicker({dateFmt:'yyyy-MM',maxDate:'#F{$dp.$D(\'effectMonth\')}',isShowClear:false});"/>
				</c:if>
				<c:if test="${not empty bizPmAttendCnfg.id}">
					<input name="effectMonth" id="effectMonth" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
						   value="${bizPmAttendCnfg.effectMonth}"
						   onclick="WdatePicker({dateFmt:'yyyy-MM',maxDate:'#F{$dp.$D(\'effectMonth\')}',isShowClear:false});"/>
				</c:if>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><span style="color:red;"> *</span>是否启用：</label>
			<div class="controls">
				<select name="isEnabled" class="input-xlarge required">
					<option value="1" <c:if test="${bizPmAttendCnfg.isEnabled==1}">selected</c:if>>启用</option>
					<option value="0" <c:if test="${bizPmAttendCnfg.isEnabled==0}">selected</c:if> >停用</option>
				</select>
			</div>
		</div>
		<div class="control-group">
			<input  class="btn btn-primary" type="button" value="新增" onclick="addStar();"/>
		</div>
		<table style=" width:60%;" id="contentTable" class="table table-striped table-bordered table-condensed">
			<tr>
				<td>
					项目经理星级
				</td>
				<td>
					项目经理底薪
				</td>
				<td>
					最低额度
				</td>
				<td>
					操作
				</td>
			</tr>
			<c:forEach items="${bizPmAttendCnfgStarList}" var="bs">
				<tr>
					<input type="hidden" name="id_u"  value="${bs.id }" />
					<td>
						<select name="star_u" class="input-xlarge required">
							<c:forEach items="${managerStarList}" var = "managerStar">'	
								<option value="${managerStar.value}" <c:if test="${bs.star == managerStar.value}">selected = "selected" </c:if>>
									${managerStar.label}
								</option>
							</c:forEach>
						</select>
					</td>
					<td>
						<input type="text" name="starSalaryAllAttend_u"  value="${bs.starSalaryAllAttend }" onkeyup="value=value.replace(/[^\d\.]/g,'')"/>
					</td>
					<td>
						<input type="text" name="starSalaryMin_u"  value="${bs.starSalaryMin }" onkeyup="value=value.replace(/[^\d\.]/g,'')"/>
					</td>
					<td>
						<input  class="btn" type="button" value="删除" onclick="deleteStar(this,${bs.id })"/>
					</td>
				</tr>
			</c:forEach>
		</table>
		<div class="form-actions">
			<shiro:hasPermission name="bizpmattendcnfg:bizPmAttendCnfg:edit"><input id="btnSubmit" class="btn btn-primary" onclick="save();" type="button" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>