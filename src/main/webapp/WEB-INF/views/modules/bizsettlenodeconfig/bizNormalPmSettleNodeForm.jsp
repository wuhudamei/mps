<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>结算节点配置管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
			
			var count = 0;
			
			$(":radio[name=projectMode]").each(function(){
				var temp = $(this).attr("checked");
				
				if(temp == 'checked'){
					count++;
				}else{
					$(this).attr("disabled",true);
				}
				
			})
			
			if(count == 0){
				$(":radio[name=projectMode]").each(function(){
					$(this).removeAttr("disabled");
				})
			}
			
			
			var storeId="${node.storeId}";
			var projectMode="${node.projectMode}";
			
		    if(storeId>0){
                var html ="";
                $.ajax({

                    url : "${ctx}/bizsettlenodeconfig/bizNormalPmSettleNode/getCheckNodeDoneByStoreIdAndProjectModel?storeId="
                    + storeId + "&projectMode=" + projectMode,
                    type : "get",
                    success : function(data) {
                        if (null!= data && data.length > 0) {
		
                            for (var v = 0; v < data.length; v++) {
								
                                html +='<option value="'+data[v].value+'">'+data[v].label+'</option>'
                            }

                            $("[name='qcCheckNodeId']").html(html);
                        } else {
                            $("[name='qcCheckNodeId']").html(html);
                        }

                    }

                });



            }


            $(":radio[name='settleType'][value='" + 1 + "']")
                .prop("checked", "checked");
            var content = "<tr><th><select name='evalRoleType' class='input-xlarge required'>";
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

		function tip(){
			
			$(":radio[name=projectMode]").each(function(){
				var temp = $(this).attr("checked");
				if(temp == 'checked'){
					
				}else{
					$(this).attr("disabled",true);
				}
				
			})
			
		}
		
function chooseNodeByStoreId(num){

    var storeId = $("#storeId").val();
    var projectMode = $(":radio[name=projectMode][checked=checked]").val();
    if(storeId == null || storeId =='' || projectMode == null || projectMode == ''){
    	return false;
    }
if(1==num){


}else{


    $.ajax({

        url : "${ctx}/bizsettlenodeconfig/bizNormalPmSettleNode/checkStoreIsExist",
        async:false,
        type : "post",
        data : {storeId : storeId,projectMode : projectMode},
        success : function(data) {

            if (data==2){
                layer.msg("门店已经配置过,请选择其他门店");

               $("#settleId tr").remove();
                $("#settleId").append('<tr> <th><input class="btn btn-primary" type="button" value="增加"  onclick="addSettleConfig(this)" /> </th></tr>')
                $('#saveId').attr("disabled",true);
                $('[type="button"][value="增加"]').attr("disabled",true);
                return;

            }else if (3==data){

                layer.msg("出现了不可预知的错误..");
            }else{

                $('#saveId').attr("disabled",false);
                $('[type="button"][value="增加"]').attr("disabled",false);
            }

        }

    });

}



/* var storeId="${node.storeId}";
var projectMode="${node.projectMode}"; */
var html ="";
    $.ajax({

        url : "${ctx}/bizsettlenodeconfig/bizNormalPmSettleNode/getCheckNodeDoneByStoreIdAndProjectModel?storeId="
        + storeId + "&projectMode=" + projectMode,
        type : "get",
        success : function(data) {

            if (null!= data && data.length > 0) {

                for (var v = 0; v < data.length; v++) {
                    html +='<option value="'+data[v].value+'">'+data[v].label+'</option>'
                }
                $("[name='qcCheckNodeId']").each(function(){

                    $(this).not(':disabled').html(html)
		

				})
				
            } else {
                $("[name='qcCheckNodeId']").each(function(){

                    $(this).not(':disabled').html(html)


                })
            }

        }

    });

}

function addSettleConfig(obj){

    var storeId = $("#storeId").val();
   var projectMode = $(":radio[name=projectMode][checked=checked]").val();
    if(storeId==""){
        layer.msg("请选择门店后再进行增加结算节点配置");
        return;

	}
    if(projectMode=="" || projectMode == null){
        layer.msg("请选择工程模式后再进行增加结算节点配置");
        return;
	}

		if(($(obj).parent().parent().prev().find('a').eq(0).text()=="完成")){

            layer.msg("请按顺序 依次 编辑 结算节点 完成后方可编辑下一节点");
   		 return;

			}





    if	(percentCheck()) {


        $(obj).parent().remove();
        var html = '';

        html = '<tr><th><th><input type="text" name="settleNodeName" htmlEscape="false" maxlength="10" class="input-medium "/> <th> <select name="qcCheckNodeId" class="input-medium"> <option value=""></option> </select> </th><th><input  type="text" name="settleRule" htmlEscape="false" maxlength="11" class="input-medium" onkeyup="checkNum(this)" onafterpaste="checkNum(this)"/> </th><th><input type="text" id="settlePrice" name="settlePrice" htmlEscape="false" maxlength="6" class="input-medium"/></th> <th><select id="receiveMoneyTypeId" name="receiveMoneyType" class="input-medium required"> <option value=""></option> <option value="1">二期款</option> <option value="2">尾款</option> </select></th> <th><select id="settleStage" name="settleStage" class="input-medium required"><option value=""></option> <option value="10">中期结算</option> <option value="20">竣工结算</option></select></th><th><select name="isRequired" class="input-medium required"> <option value="1">选填项</option> <option value="2">必选项</option> </select> </th><th> <a href="#" onclick="changeAndSave(this)">完成</a>  <a href="#" onclick="deleteNode(this)">删除</a>  </th>  </tr>'

        $("#settleId").append(html);

        //注入验收节点
        chooseNodeByStoreId("1");


        var num = 0;
        $("#settleId tr th ").each(function () {
            if ($(this).index() == 0) {
                num++;
                $(this).text(num)
            }

        })

        $("#settleId").append('<tr> <th><input class="btn btn-primary" type="button" value="增加"  onclick="addSettleConfig(this)" /> </th></tr>')


    }

}

        /**
		 * 保存和修改
         */
 function changeAndSave(obj){
			var trObj= $(obj).parent().parent();
			var isRequiredObj=  trObj.find('[name="isRequired"]');
            var   receiveMoneyTypeObj=  trObj.find('[name="receiveMoneyType"]');
            var   settleRuleObj=   trObj.find('[name="settleRule"]');
            var   qcCheckNodeIdObj=    trObj.find('[name="qcCheckNodeId"]');
            var   settleNodeNameObj=   trObj.find('[name="settleNodeName"]');
            var   settlePrice =   trObj.find('[name="settlePrice"]');
            var   settleStage =   trObj.find('[name="settleStage"]');
            var   projectMode =   trObj.find(':radio[name=projectMode][checked=checked]');

if(settleNodeNameObj.val().trim()==""){

    layer.msg("你必须填写结算节点名称才可以保存");
    return;

}


            var param = {
             	storeId: $("#storeId").val(),
                isRequired:isRequiredObj.val(),
                receiveMoneyType:receiveMoneyTypeObj.val(),
                settleRule: settleRuleObj.val(),
                qcCheckNodeId:qcCheckNodeIdObj.val(),
                settleNodeName:settleNodeNameObj.val(),
                settleType:	$(":radio[name='settleType'][checked='checked']").val(),
                settleIndex: $(obj).parent().prev().prev().prev().prev().prev().prev().prev().prev().text(),
                settleStage:settleStage.val(),
                settlePrice:settlePrice.val(),
                projectMode:$(":radio[name=projectMode][checked=checked]").val()

            };


            if($(obj).text()=='修改'){
                $(isRequiredObj).attr("disabled",false);
                $(receiveMoneyTypeObj).attr("disabled",false);
                $(settleRuleObj).attr("readOnly",false);
                $(qcCheckNodeIdObj).attr("disabled",false);
                $(settleNodeNameObj).attr("readOnly",false);
                
                $(settlePrice).attr("readOnly",false);
                $(settleStage).attr("disabled",false);
                
				$(obj).text('完成')


            }else{
                if(percentCheck()){
		
                $.ajax({

                    url : "${ctx}/bizsettlenodeconfig/bizNormalPmSettleNode/saveOrUpdateSettleNode",
                    type: "post",
					data: param,
                    success : function(data) {
                        $('[name="storeId"]').attr("disabled",true);
                        $('[name="settleType"]').attr("disabled",true);
                        if(2==data){

                            layer.msg("修改成功")
							  $(isRequiredObj).attr("disabled",true);
					             $(receiveMoneyTypeObj).attr("disabled",true);
					             $(settleRuleObj).attr("readOnly",true);
					             $(qcCheckNodeIdObj).attr("disabled",true);
					             $(settleNodeNameObj).attr("readOnly",true);
					             $(settlePrice).attr("readOnly",true);
					             $(settleStage).attr("disabled",true);
					             tip();
					             $(obj).text('修改')
                        }else if (1==data){
                            layer.msg("保存成功")
                              $(isRequiredObj).attr("disabled",true);
					             $(receiveMoneyTypeObj).attr("disabled",true);
					             $(settleRuleObj).attr("readOnly",true);
					             $(qcCheckNodeIdObj).attr("disabled",true);
					             $(settleNodeNameObj).attr("readOnly",true);
					             $(settlePrice).attr("readOnly",true);
					             $(settleStage).attr("disabled",true);
					             tip();
					             $(obj).text('修改')
                        }else if(4 == data){
							alertx("准产业结算阶段重复，不可重复添加！")
						}else{
                            layer.msg("操作失败了")
                        }


                    }





                });




            }

            }



		}

function deleteNode(obj){

    //询问框
    layer.confirm('您确定要删除吗?', {
        btn: ['确定','取消'] //按钮
    }, function(){



        $(obj).parent().parent().remove();
        var trObj= $(obj).parent().parent();
        var isRequiredObj=  trObj.find('[name="isRequired"]');
        var   receiveMoneyTypeObj=  trObj.find('[name="receiveMoneyType"]');
        var   settleRuleObj=   trObj.find('[name="settleRule"]');
        var   qcCheckNodeIdObj=    trObj.find('[name="qcCheckNodeId"]');
        var   settleNodeNameObj=   trObj.find('[name="settleNodeName"]');




        var param = {
            storeId: $("#storeId").val(),
            isRequired:isRequiredObj.val(),
            receiveMoneyType:receiveMoneyTypeObj.val(),
            settleRule: settleRuleObj.val(),
            qcCheckNodeId:qcCheckNodeIdObj.val(),
            settleNodeName:settleNodeNameObj.val(),
            settleType:	$(":radio[name='settleType'][checked='checked']").val(),
            settleIndex: $(obj).parent().prev().prev().prev().prev().prev().prev().prev().prev().text()

        };

        $.ajax({

            url : "${ctx}/bizsettlenodeconfig/bizNormalPmSettleNode/deleteSettleNode",
            type: "post",
            data: param,
            success : function(data) {

                if(1==data){

                    layer.msg("删除成功")

                }else{
                    layer.msg("删除失败了")

                }
            }


        });


        var num=0;
        $("#settleId tr th ").each(function(){

            if($(this).index()==0){

                if($(this).text().trim()==""){

                    return;
                }
                num++;

                $(this).text(num)
            }



        })
        if($("#settleId th").length==1){

            $('[name="storeId"]').attr("disabled",false);
            $('[name="settleType"]').attr("disabled",false);

        }



    }, function(){
        layer.msg('您取消了操作');
    });



}

function checkNum(obj)
        {
            if ($('input:radio:checked').val()==1) {
                obj.value = obj.value.replace(/[^\%\d]/g, '');
                if (obj.value.indexOf('%') >= 0) {
                    obj.value = obj.value.split('%')[0] + '%';


                }
            percentCheck();
            }
        }


function save(){

    var changeIndex=0
    var settleTotal=0;

    $('[name="settleRule"]').each(function(){
        if($(this).val().trim()!=""){

            settleTotal+=parseInt($(this).val().split('%')[0]);
		}

    })

var peojectMode = $(":radio[name='projectMode'][checked='checked']").val();
    if(peojectMode != 4){
	    if(settleTotal!=100){
	        changeIndex=1
	        layer.msg("您目前配置的结算比例为 "+settleTotal+"% ,请查改")
	    }
    }




    if($("#settleId a").length>0){


	$("#settleId a").each(function(){



        if($(this).text()=='完成'){

            changeIndex=1

            //询问框
                layer.confirm('您还有没有完成配置的结算节点,确定要保存吗？', {
                    btn: ['确定','取消'] //按钮
                }, function(){

                    if(settleTotal!=100){

                        layer.msg("您目前配置的结算比例为 "+settleTotal+"% ,请查改")
                        return;
                    }

                        layer.msg('正在前往列表页', {icon: 1});
                        window.location.href="${ctx}/bizsettlenodeconfig/bizNormalPmSettleNode/list"

                }, function(){

                    layer.msg('您取消了操作');
                    return;
                });



            }






	})

		if(changeIndex==0){

            window.location.href="${ctx}/bizsettlenodeconfig/bizNormalPmSettleNode/list"

        }

    }else{

        //询问框
        layer.confirm('您什么结算节点还没有添加,确定要保存吗？', {
            btn: ['确定','取消'] //按钮
        }, function(){
            layer.msg('正在前往列表页', {icon: 1});
            window.location.href="${ctx}/bizsettlenodeconfig/bizNormalPmSettleNode/list"

        }, function(){
            layer.msg('您取消了操作');
            return;
        });

    }

}

		function percentCheck(){
           if ($('input:radio:checked').val()==1){
            var settleTotal=0;

            $('[name="settleRule"]').each(function(){
                if($(this).val().trim()!=""){

                    settleTotal+=parseInt($(this).val().split('%')[0]);
                }


            })


            if(settleTotal>100){

                layer.msg("您目前配置的结算比例为 "+settleTotal+"% ,请查改")


                return false;
            }else{

                return true;
			}
           }else{

               return true;
		   }

        }
	</script>
	<script type="text/javascript" src="${ctxStatic}/modules/settleJs/layer/layer.js"></script>
	<script src="${ctxStatic}/modules/settleJs/layer/extend/layer.ext.js"></script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/bizsettlenodeconfig/bizNormalPmSettleNode/">结算节点配置列表</a></li>
		<li class="active"><a href="${ctx}/bizsettlenodeconfig/bizNormalPmSettleNode/form?storeId=${node.storeId}">结算节点配置<shiro:hasPermission name="bizsettlenodeconfig:bizNormalPmSettleNode:edit">${not empty node.storeId?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="bizsettlenodeconfig:bizNormalPmSettleNode:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="bizNormalPmSettleNode" action="${ctx}/bizsettlenodeconfig/bizNormalPmSettleNode/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
		<div class="control-group">
			<label class="control-label">门店：</label>
			<c:if test="${not empty node.storeId}">

				<form:select path="storeId" class="input-medium " disabled="true">
					<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					<span class="help-inline"><font color="red">*</font> </span>
				</form:select>
			</c:if>
			<c:if test="${empty node.storeId}">

				<form:select path="storeId" class="input-medium " onchange="chooseNodeByStoreId()" >
					<form:options items="${fns:getStoreList()}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					<span class="help-inline"><font color="red">*</font> </span>
				</form:select>
			</c:if>

		</div>
		<div class="control-group">
			<label class="control-label">工程模式：</label>
			<label class="control-label"><form:radiobuttons path="projectMode" items="${fns:getDictList('pre_employee_project_mode')}" itemLabel="label" itemValue="value" htmlEscape="false" onchange="chooseNodeByStoreId()"/></label>
            <span class="help-inline"><font color="red">*</font></span>
		</div>
		

		<div class="control-group">
			<label class="control-label">结算类型：</label>

			<c:if test="${node.settleType==1}">

				<label class="control-label">
					<input type="radio" name="settleType" value="1" checked =checked disabled="disabled">百分比</label>


				<label class="control-label">
					<input type="radio" name="settleType" value="2" disabled="disabled" >自定义</label>


			</c:if>

			<c:if test="${node.settleType==2}">

				<label class="control-label">
					<input type="radio" name="settleType" value="1"  disabled="disabled">百分比</label>


				<label class="control-label">
					<input type="radio" name="settleType" value="2" checked =checked disabled="disabled">自定义</label>
			</c:if>

			<c:if test="${empty node}">
				<label class="control-label">
					<input type="radio" name="settleType" value="1">百分比</label>


				<label class="control-label">
					<input type="radio" name="settleType" value="2">自定义</label>


			</c:if>


				<%--<form:radiobuttons path="settleType" items="${fns:getDictList('settle_type')}" itemLabel="label" itemValue="value" htmlEscape="false" class="required"/>--%>
				<span class="help-inline"><font color="red">*</font> </span>

		</div>

		


		<div class="control-group">


				<table id="eval" class="table table-striped table-bordered table-condensed">
					<thead>
					<tr>
						<th>结算批次</th>
						<th>结算节点</th>
						<th>验收阶段</th>
						<th>结算比例</th>
						<th>结算单价</th>
						<th>款项节点</th>
						<th>结算阶段</th>
						<th>设置</th>
						<th>操作</th>
					</tr>
					</thead>
					<tbody id="settleId">

					<c:forEach items="${node.childEntity}" var="entity">
						<tr>


							<th>${entity.settleIndex}</th>

								<th>

									<input type="text"  class="input-medium " name="settleNodeName" value="${entity.settleNodeName}" readonly="readonly">

								</th>
								<th>

									<select id="nodeId" name="qcCheckNodeId" class="input-medium" disabled="disabled">
										<option value="${entity.qcCheckNodeId}">${entity.qcCheckNodeName}</option>

									</select>

								</th>
								<th>
									<input type="text"  class="input-medium " name="settleRule" value="${entity.settleRule}" readonly="readonly" onkeyup="checkNum(this)" onafterpaste="checkNum(this)" >

								</th>
								<th>
									<input type="text"  class="input-medium " name="settlePrice" value="${entity.settlePrice}" readonly="readonly" onkeyup="checkNum(this)" onafterpaste="checkNum(this)" >

								</th>
								<th>
									<select id="receiveMoneyTypeId" name="receiveMoneyType" class="input-medium required" disabled = disabled>
										<c:if test="${entity.receiveMoneyType==1}">
											<option value="" ></option>
											<option value="1" selected="selected">二期款</option>
											<option value="2">尾款</option>
										</c:if>
										<c:if test="${entity.receiveMoneyType==2}">
											<option value="" ></option>
											<option value="1">二期款</option>
											<option value="2" selected="selected">尾款</option>
										</c:if>
										<c:if test="${empty entity.receiveMoneyType}">
											<option value="" selected="selected"></option>
											<option value="1">二期款</option>
											<option value="2" >尾款</option>
										</c:if>



									</select>


								</th>
								<th>
									${fns:getDictLabel(entity.settleStage, 'settlement_stage', '')}
								</th>
								
								
								<th>
									<select name="isRequired" class="input-medium required" disabled =disabled>
									<c:if test="${entity.isRequired==1}">
										<option value="1" selected =selected>选填项</option>
										<option value="2">必选项</option>
									</c:if>
										<c:if test="${entity.isRequired==2}">
											<option value="1" selected =selected>选填项</option>
											<option value="2" selected =selected>必选项</option>

										</c:if>


									</select>
					</th>
							<th>
								<a href="#" onclick="changeAndSave(this)">修改</a>
								<a href="#" onclick="deleteNode(this)">删除</a>

							</th>
							</th>
						</tr>
					</c:forEach>


					<tr>


						<th>
							<input  class="btn btn-primary" type="button" value="增加"  onclick="addSettleConfig(this)" />


						</th>

					</tr>
					</tbody>
				</table>

		</div>







<%--

		<div class="control-group">
			<label class="control-label">结算顺序：</label>
			<div class="controls">
				<form:input path="settleIndex" htmlEscape="false" maxlength="11" class="input-xlarge required digits"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">节点名称：</label>
			<div class="controls">
				<form:input path="settleNodeName" htmlEscape="false" maxlength="100" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">验收节点id：</label>
			<div class="controls">
				<form:input path="qcCheckNodeId" htmlEscape="false" maxlength="11" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">结算类型：</label>
			<div class="controls">
				<form:input path="settleType" htmlEscape="false" maxlength="10" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">具体结算占比：</label>
			<div class="controls">
				<form:input path="settleRule" htmlEscape="false" maxlength="100" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">收款类型：</label>
			<div class="controls">
				<form:input path="receiveMoneyType" htmlEscape="false" maxlength="10" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">是否必选：</label>
			<div class="controls">
				<form:input path="isRequired" htmlEscape="false" maxlength="1" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>--%>
	<div class="form-actions">
			<shiro:hasPermission name="bizsettlenodeconfig:bizNormalPmSettleNode:edit"><input id="saveId" class="btn btn-primary" type="button" value="保 存" onclick="save()"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="window.location.href='${ctx}/bizsettlenodeconfig/bizNormalPmSettleNode/list'"/>
	</div>
	</form:form>
</body>
</html>