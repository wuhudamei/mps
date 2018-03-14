<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html>
<head>
	<title>客户回访节点管理</title>
	<meta name="decorator" content="default"/>
<%-- 	<link rel="stylesheet" href="${ctxStatic}/modules/customerreturnvisit/css/base.css"> --%>
	<link rel="stylesheet" href="${ctxStatic}/modules/customerreturnvisit/css/index.css">
	<style type="text/css">
		.undis{display:none;}
		.alertMask{z-index:100;position: fixed;top: 0;bottom: 0;left: 0;right: 0;background: rgba(0,0,0,.5);}
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
	<script src="${ctxStatic}/modules/customerreturnvisit/js/manage.js"></script>
	<script type="text/javascript">
		
		//定义统计部门数组
		var statisticsDepartmentOptionArray = $.parseJSON('${fns:getDictListJson("return_visit_statistics_department")}');
		var statisticsDepartmentOptionsStr = '';
		$.each(statisticsDepartmentOptionArray, function(index,obj) {
			statisticsDepartmentOptionsStr += '<option value="'+ obj.value +'">'+ obj.label +'</option>'; 
		});
		
		var baseUrl = '${ctx}';
		var temp = '${badReasonList}';
		var badReasonList = temp.replace("[","").replace("]","").split(",");
		var question_count = 0;//问题总数
		
		//二级选项弹出框已选未选内容过滤
		var returnModelObj = null;
		function secondSelectModel(obj) {
			returnModelObj = $(obj);
			var leftArr = new Array();
			leftArr = leftArr.concat(badReasonList);
			var selectedVal = $(obj).siblings('.selectedItem').val();
			if( selectedVal != null && selectedVal != ''){
				var rightArr = selectedVal.split(",");
				for(var j=0;j<leftArr.length;j++){
					for(var i=0;i<rightArr.length;i++){
						if(leftArr[j]==rightArr[i]){
							leftArr.splice(leftArr.indexOf(leftArr[j]),1);
						}
					}
				}
				//渲染右侧已选择选项
				var rightOptions = '';
				$.each(rightArr,function(name, value) {
					rightOptions = rightOptions + '<span>'+ value +'</span>';
				});
				$(".right_p").html(rightOptions);
			}
			
			//渲染左侧未选择选项
			$(".left_p").html('');
			var leftOptions = '';
			$.each(leftArr,function(name, value) {
				leftOptions = leftOptions + '<span>'+ value +'</span>';
			});
			$(".left_p").html(leftOptions);
			
			$(".two_sel").show();
			$('body,html').css("overflow", "hidden");
		}
		
		function fillProjectNodeOptions(){
			if( $("#storeId").val() == '' || $("#storeId").val() == null ){
				return;
			}
			//根据门店信息获取工程节点，填充工程节点下拉框选项
			$.ajax({
				'url':baseUrl+'/customerreturnvisit/bizCustomerReturnVisit/queryProjectNode?storeId=' + $("#storeId").val()+'&projectMode='+$("#projectMode").val(),
			    'type':'GET', //GET
				'dataType': 'json',
				'success': function (res) {
					$("#projectNode").empty();
					//$("<option value=''></option>").appendTo("#projectNode");
					$("<option value='0'>开工交底</option>").appendTo("#projectNode");
					var array = res.data; 
					if( array != null && array != undefined ){
						for( var i = 0 ;i < array.length;i++){
							 $("<option value='"+ array[i].value +"'>"+ array[i].label +"</option>").appendTo("#projectNode");
						}
					}
				},
				'error': function (data) {
					console.log("请求错误！");
				}
			});
		}
		
		//确认答案选项，拼接选项内容
		function confirmAnswer(obj,indexNum){
			var validateFlag = 1;
			if($("#questionContent" + indexNum ).val() == '' || $("#questionContent" + indexNum ).val() == undefined ||$.trim($("#questionContent" + indexNum ).val()) == ""){
				validateFlag = 0;
				$("#alertRemarks").text("问题描述不可为空！");
        		$("#subReport").show();
				return false;
			}
			//根据下标获取对应的答复方式
			var idx = $(obj).parents(".lines").siblings().find(".only").find(".b_sed").index() + 1;
			$("#replyMode" + indexNum ).val(idx);
			//根据答复方式处理选项内容
			var itemContent = '';
			var temp = '';
			if( idx == 1 ){
				if( $(".firstSelectOptions" + indexNum).length > 0 ){
					
					$(".firstSelectOptions" + indexNum).each(function(){
						if( $(this).val() != '' && $.trim($(this).val()) != ''){
							temp += "{'content': '"+ $(this).val() +"','answer': ''},";	
						}else{
							validateFlag = 0;
							$("#alertRemarks").text("选项内容不可为空！");
			        		$("#subReport").show();
							return false;
						}
					})
					if( validateFlag > 0 ){
						itemContent = "{'replyMode': '1','item': [" + temp.substring(0,temp.length -1 ) +"]}";
					}
				}else{
					$("#alertRemarks").text("请至少添加一个选项！");
	        		$("#subReport").show();
					return false;
				}
			}else if( idx == 2 ){
				console.log($(".seccondSelectOptions" + indexNum).length);
				if( $(".seccondSelectOptions" + indexNum).length > 0 ){
					$(".seccondSelectOptions" + indexNum).each(function(){
						if( $(this).val() == ''|| $.trim($(this).val()) == ''){
							validateFlag = 0;
							$("#alertRemarks").text("选项内容不可为空！");
			        		$("#subReport").show();
							return false;	
						}
						
						temp += "{'content': '"+ $(this).val() +"','answer': '"+ $(this).next().val() +"'},";
					})
					if( validateFlag > 0 ){
						itemContent = "{'replyMode': '2','item': [" + temp.substring(0,temp.length -1 ) +"]}";
					}
				}else{
					$("#alertRemarks").text("请至少添加一个选项！");
	        		$("#subReport").show();
					return false;
				}
			}
			
			if( validateFlag > 0 ){
				$("#itemContent" + indexNum ).val(itemContent);
				$("#validateFlag" + indexNum ).val(validateFlag);
				
				//加锁加蒙板
				var _box = $(obj).parents(".box"),
				whiteboard='<div class="whiteboard"></div>',
				box_w=_box.width(),
				box_h=_box.height();

				_box.append(whiteboard).find(".lock").removeClass('unlock');
				_box.find(".whiteboard").css({
					"width":box_w+'px',
					"height":box_h+30+'px'
				});
			}
		}
		
		function submitFunction(){
			var data = {
					"id":$("#id").val(),
					"storeId":$("#storeId").val(),
					"returnVisitNode":$("#returnVisitNode").val(),
					"projectNode":$("#projectNode").val(),
					"questionContent":getArrayValues('input','questionContent'),
					"statisticsDepartment":getArrayValues('select','statisticsDepartment'),
					"replyMode":getArrayValues('input','replyMode'),
					"itemContent":getArrayValues('input','itemContent'),
                	"projectMode":$("#projectMode").val(),
				};
				
				$.ajax({
					'url':baseUrl+'/customerreturnvisit/bizCustomerReturnVisit/save',
				    'type':'POST', //GET
					'dataType': 'json',
					'data':data,
					'success': function (res) {
						if( res.code == '200' ){
							$("#subReport").attr("jump","true");
			        		setTimeout("jumpToList()",1500);
						}					
						$("#alertRemarks").text(res.message );
		        		$("#subReport").show();
						
						return false;
					},
					'error': function (data) {
						console.log("请求错误！");
					}
				});
		}
		
		
		//根据页面元素类型及名称获取数组
		function getArrayValues(pageElementType,name){
			if( pageElementType == 'input' ){
				return $("input[name="+ name +"]").map(function(){
					return $(this).val();
				}).get();
			}else if( pageElementType == 'select' ){
				return $("select[name="+ name +"]").map(function(){
					return $(this).val();
				}).get();
			}else{
				return null;
			}
			
			
		}
		
		//关闭 
	    function closePromptWindow(){
	    	$("#subReport").hide();
	    	if( $("#subReport").attr("jump") == 'true' ){
	    		jumpToList();
	    	}
	    }
		
	  	//跳转至列表页面
	    function jumpToList(){
	    	window.location.href = baseUrl +"/customerreturnvisit/bizCustomerReturnVisit";
	    }
		
		//当页面加载完毕，为节点问题增加蒙板
		$(document).ready(function(){
			$(".box").each(function(){
				var _box = $(this),
				box_w=_box.width(),
				box_h=_box.height();

				_box.find(".whiteboard").css({
					"width":box_w+'px',
					"height":box_h+30+'px'
				});
			})
			if( $("#id").val() != ''  ){
				$(".top input,.top select").attr("disabled", true);
			}
			
			//同步问题个数
			question_count = $(".box").length;
		})
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/customerreturnvisit/bizCustomerReturnVisit/">客户回访节点列表</a></li>
		<li class="active"><a href="${ctx}/customerreturnvisit/bizCustomerReturnVisit/form?id=${bizCustomerReturnVisit.id}">客户回访节点<shiro:hasPermission name="customerreturnvisit:bizCustomerReturnVisit:edit">${not empty bizCustomerReturnVisit.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="customerreturnvisit:bizCustomerReturnVisit:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form id="inputForm" >
		<input type="hidden" name="id" id="id" value="${bizCustomerReturnVisit.id}">
		<!-- 节点问题区域begin -->
			<div class="wrap">
				<div class="top">
					<p>
						<span><i></i>门　　店<em>：</em></span>
						<select name="storeId" id="storeId" class="tex1" style="width: 412px;" >
							<option value=""></option>
							<c:forEach items="${fns:getStoreList()}" var="item" >
								<c:if test="${bizCustomerReturnVisit.storeId == item.value}">
									<option value="${item.value}" selected="selected">${item.label}</option>
								</c:if>
								<c:if test="${bizCustomerReturnVisit.storeId != item.value}">
									<option value="${item.value}" >${item.label}</option>
								</c:if>
							</c:forEach>
						</select>
					</p>
					<p>
						<span><i></i>工程模式<em>：</em></span>
						<select name="projectMode" id="projectMode" class="tex1" style="width: 412px;" >
							<option value=""></option>
							<c:forEach items="${fns:getDictList('project_mode')}" var="item" >
								<c:if test="${bizCustomerReturnVisit.projectMode == item.value}">
									<option value="${item.value}" selected="selected">${item.label}</option>
								</c:if>
								<c:if test="${bizCustomerReturnVisit.projectMode != item.value}">
									<option value="${item.value}" >${item.label}</option>
								</c:if>
							</c:forEach>
						</select>
					</p>
					<p>
						<span><i></i>工程节点<em>：</em></span>
						<select name="projectNode" id="projectNode" class="tex3" style="width: 412px;">
							
							<option value=""></option>
							<c:if test="${bizCustomerReturnVisit.projectNode == 0}">
								<option value="0" selected="selected">开工交底</option>
							</c:if>
							<c:if test="${bizCustomerReturnVisit.projectNode != 0}">
								<option value="0">开工交底</option>
							</c:if>
							<c:forEach items="${projectNodeList}" var="item" >
								<c:choose>
									<c:when test="${bizCustomerReturnVisit.projectNode == item.value}">
										<option value="${item.value}" selected="selected">${item.label}</option>
									</c:when>
									<c:otherwise>
										<option value="${item.value}">${item.label}</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</select>
					</p>
					<p>
						<span><i></i>回访节点<em>：</em></span>
						<input type="text" class="tex2" style="margin-bottom:0" name="returnVisitNode" id="returnVisitNode" placeholder="输入名称" maxlength="30" value="${bizCustomerReturnVisit.returnVisitNode}" onkeyup="this.value=this.value.replace(/^ +| +$/g,'')">
					</p>
				</div>
				<div class="tab">
					<!-- 修改时显示原有问题 -->
					<c:forEach items="${bizCustomerReturnVisit.questions}" var="customerReturnVisitContent" varStatus="status">
						<div class="box">
						    <div class="lock" ></div>
						    <h3>回访问题<span class="num">${status.index + 1}</span></h3>
						    <div class="whiteboard"></div>
						    <div class="lines">
						        <span><i></i>问题描述<em>：</em></span>
						        <input type="hidden" name="validateFlag" id="validateFlag${status.index + 1}" indexNum="${status.index + 1}" value="1">
						        <input type="text" class="tex2" name="questionContent" id="questionContent${status.index + 1}" maxlength="30" placeholder="输入名称（最多输入30字）" value="${customerReturnVisitContent.questionContent }">
						    </div>
						    <div class="lines"><span><i></i>统计计数<em>：</em></span>
								<select class="count" id="statisticsDepartment${status.index + 1}" name="statisticsDepartment" indexNum="${status.index + 1}" style="width: 363px;">
									<c:forEach items="${fns:getDictList('return_visit_statistics_department')}" var="item" >
										
										<c:if test="${customerReturnVisitContent.statisticsDepartment == item.value}">
											<option value="${item.value}" selected="selected">${item.label}</option>
										</c:if>
										<c:if test="${customerReturnVisitContent.statisticsDepartment != item.value}">
											<option value="${item.value}" >${item.label}</option>
										</c:if>
									 </c:forEach>
								</select>
							</div>
						    <div class="lines">
						        <span><i></i>答复方式<em>：</em></span>
						        <input type="hidden" name="replyMode" id="replyMode${status.index + 1}" value="${customerReturnVisitContent.replyMode }">
						        <input type="hidden" name="itemContent" id="itemContent${status.index + 1}" value="${customerReturnVisitContent.itemContent }">
						        <p class="line_sel only">
						        	<c:choose>
						        		<c:when test="${customerReturnVisitContent.replyMode == 1}">
						        			<span class="b_sed"><b></b>一级选项</span>
								            <span><b></b>二级选项</span>
								            <span><b></b>填空</span>
								            <span><b></b>日历</span>
						        		</c:when>
						        		<c:when test="${customerReturnVisitContent.replyMode == 2}">
						        			<span><b></b>一级选项</span>
								            <span class="b_sed"><b></b>二级选项</span>
								            <span><b></b>填空</span>
								            <span><b></b>日历</span>
						        		</c:when>
						        		<c:when test="${customerReturnVisitContent.replyMode == 3}">
						        			<span><b></b>一级选项</span>
								            <span><b></b>二级选项</span>
								            <span class="b_sed"><b></b>填空</span>
								            <span><b></b>日历</span>
						        		</c:when>
						        		<c:otherwise>
						        			<span><b></b>一级选项</span>
								            <span><b></b>二级选项</span>
								            <span><b></b>填空</span>
								            <span class="b_sed"><b></b>日历</span>
						        		</c:otherwise>
						        	</c:choose>
						        </p>
						    </div>
						    <div class="lines">
						        <span class="flo_left">&nbsp;&nbsp;设置选项<em>：</em></span>
						        <div class="tab_s">
						            <div class="select_box">
						            	<c:choose>
							        		<c:when test="${customerReturnVisitContent.replyMode == 1}">
							        			<c:forEach items="${customerReturnVisitContent.itemContentList}" var="map" >
							        				<p><input type="text" maxlength="10" name="firstItem" class="firstSelectOptions${status.index + 1}" value="${fn:escapeXml(map.content)}"><span class="line_del">删除</span></p>
							        			</c:forEach>
							        		</c:when>
							        		<c:when test="${customerReturnVisitContent.replyMode == 2}">
						        				<c:forEach items="${customerReturnVisitContent.itemContentList}" var="map" >
							        				<p class="two_tex">
								        				<input type="text" name="secondItem" class="seccondSelectOptions${status.index + 1}" maxlength="10" value="${fn:escapeXml(map.content)}" >
								        				<input type="hidden" class="selectedItem" value="${map.answer}">
								        				<button class="two_btn" onclick="secondSelectModel(this)">修改二级选项</button>
								        				<span class="line_del">删除</span>
								        			</p>	
							        			</c:forEach>
							        		</c:when>
							        		<c:otherwise></c:otherwise>
							        	</c:choose>
						                <p class="box_add addline" indexNum="${status.index + 1}">+ 增加</p><p class="revamp" onclick="confirmAnswer(this,${status.index + 1})" >提交</p><p class="lastremove">删除</p>
						            </div>
						        </div>
						    </div>
						</div>
					</c:forEach>
				</div>
				<div class="bot">
					<span class="add">增加回访题</span>
					<span class="store">保存</span>
					<span class="empty">清空</span>
				</div>
			</div>
		
			<div class="black">
				<div class="center">
					<div class="info"></div>
					<div class="cen_btn">
						<span class="cen_y">确认</span>
						<span class="cen_n">取消</span>
					</div>
				</div>
			</div>
		
			<div class="two_sel">
				<div class="sel_cen">
					<h3>添加二级选项</h3>
					<div class="left">
						<h4>未选择</h4>
						<p class="left_p"></p>
					</div>
					<div class="cen">
						<p class="arr_add"></p>
						<p class="arr_del"></p>
					</div>
					<div class="right">
						<h4>已选择</h4>
						<p class="right_p"></p>
					</div>
					<div class="sel_bot">
						<div class="sel_y">确认</div>
						<div class="sel_n">取消</div>
					</div>
				</div>
			</div>	
		</div>
		<!-- 节点问题区域end -->
		<div style="background:rgba(0,0,0,.4);z-index:10000;" class="g-mask undis" id ="subReport" >
			<div class="alertMask">
				<div class="maskWrapper">
					<p class="col_3 maskTit">通知</p>
					<div class="font28 col_6 maskContent" id="alertRemarks"></div>
					<div class="maskBtns clearfix">
						<a class="maskBtn font33 col_f" onclick="closePromptWindow()" href="javascript:;">我知道了</a>
					</div>
				</div>
			</div>
		</div>
	</form>
</body>
</html>