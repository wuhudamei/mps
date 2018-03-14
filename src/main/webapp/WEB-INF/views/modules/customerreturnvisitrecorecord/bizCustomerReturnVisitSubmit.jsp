<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<head>
	<!-- <meta name="decorator" content="default"/> -->
	<title>订单回访</title>
	<link rel="stylesheet" href="${ctxStatic}/modules/customerreturnvisit/css/base.css">
	<link rel="stylesheet" href="${ctxStatic}/modules/customerreturnvisit/css/list.css">
	<link rel="stylesheet" href="${ctxStatic}/modules/customerreturnvisit/css/history.css">
	<link href="${ctxStatic}/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css">
	<link href="${ctxStatic}/vendor/bootstrap-table/bootstrap-table.min.css" rel="stylesheet" type="text/css" />
	<link href="${ctxStatic}/modules/popUp/css/style.css" rel="stylesheet" type="text/css" />
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
	<script type="text/javascript" src="${ctxStatic}/vendor/jquery/jquery-2.2.4.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/vendor/bootstrap/js/bootstrap.min.js" charset="UTF-8"></script>
	<script src="${ctxStatic}/jquery/jquery-1.8.3.min.js"></script>
	<script src="${ctxStatic}/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript">
		var baseUrl = '${ctx}';
		
		$(function(){
			$('.quesrg .del').on("click",function(){
				$(this).parent().find('input').val("");
			})
			//当选择二级下拉框时，更改对应的子下拉框内容
			$(".secondSelect").on("change",function(){
				$(this).next().empty();
				var options = "";
				var array = $(this).find("option:selected").attr("childOptions").split(",");
				for( var i = 0 ;i < array.length;i++){
					options = options + "<option value='"+ array[i] +"'>"+ array[i] +"</option>";		
				}
				$(this).next().append(options);
			})
			
			//计数
			$("textarea").keyup(function(){
				var len=$(this).val().length;
				if(len>=100) len=100;
				$(this).parent().find(".count .num").text(len);
			});
			
			//点叉 清空输入框
			$('.quesrg .del').on("click",function(){
				$(this).parent().find('textarea').val(""); 
				$(this).parent().find(".count .num").text(0);
			});
			
			
			/************************历史回访记录begin*************************/
			var flag = 0;
			$(document).on('click','.leftArrow',function(){
				if(flag > 0 ) {
					flag--;
				}
				$('.navA').each(function(index, el) {
					if(flag <= index && index <= flag + 4) {
						$(this).show();
						$(this).css('display','table-cell');
					} else {
						$(this).hide();
					}
				});
			});
			$(document).on('click','.rgtArrow',function(){
				var navA = $('.navA');
				if(navA.length - 5 > flag) {
					flag++;
				}
				navA.each(function(index, el) {
					if(flag <= index && index <= flag + 4) {
						$(this).show();
						$(this).css('display','table-cell');
					} else {
						$(this).hide();
					}
				});
			});
			$(document).on('click','.navA',function(){
				$('.navA').removeClass('active');
				$(this).addClass('active');
				var index = $(this).index();
				$('.historyContent ul').hide();
				$('.historyContent ul').eq(index).show();
			})
		/************************历史回访记录end*************************/
			
		})
		
		function submitFuntion(){
			
			//遍历一级选择项
			$(".firstSelect").each(function(){
				$(this).next().val( $(this).val() );
			})
			//遍历二级选择项
			$(".secondSelect").each(function(){
				$(this).next().next().val( $(this).val() + "  "  + $(this).next().val() );
			})
			
			//文本域答案
			$("textarea[name='questionAnswerArea']").each(function(){
				$(this).parent(".quesrg").find("input[name='questionAnswer']").val( $(this).val() );
			})
			
			//提交答案
			var data = {
				"orderId":$("#orderId").val(),
				"returnVisitNode":$("#dealReturnVisitNode").val(),
				"returnVisitNodeName":$("#returnVisitNodeName").val(),
				"questionContent":getArrayValues('questionContent'),
				"statisticsDepartment":getArrayValues('statisticsDepartment'),
				"statisticsResult":getClassObjArrayValues('secondSelect'),
				"questionAnswer":getArrayValues('questionAnswer')
			};

			$("#baseMask").show();
			$.ajax({
				'url':baseUrl+'/customerreturnvisitrecorecord/bizCustomerReturnVisitRecord/save',
			    'type':'POST', //GET
				'dataType': 'json',
				'data':data,
				'success': function (res) {
					$("#baseMask").hide();
					if( res.code == '200' ){
						$("#subReport").attr("jump","true");
		        		setTimeout("jumpToList()",1500);
					}					
					$("#alertRemarks").text(res.message );
	        		$("#subReport").show();
					
					return false;
				},
				'error': function (data) {
					$("#baseMask").hide();
					$("#alertRemarks").text("请求错误！");
	        		$("#subReport").show();
				}
			});
		}
		
		//无效回访提交
		function invalidSubmitFuntion(){
			
			var data = {
				"orderId":$("#orderId").val(),
				"returnVisitNode":$("#dealReturnVisitNode").val(),
				"returnVisitNodeName":$("#returnVisitNodeName").val(),
				"invalidReason":$("#invalidReason").val()
			};
			$("#baseMask").show();
			$.ajax({
				'url':baseUrl+'/customerreturnvisitrecorecord/bizCustomerReturnVisitRecord/invalidSubmit',
			    'type':'POST', //GET
				'dataType': 'json',
				'data':data,
				'success': function (res) {
					$(".Black").hide();
					if( res.code == '200' ){
						$("#subReport").attr("jump","true");
		        		setTimeout("jumpToList()",1500);
					}else{
						$("#alertRemarks").text("保存失败！");
		        		$("#subReport").show();
					}					
					return false;
				},
				'error': function (data) {
					$(".Black").hide();
					$("#alertRemarks").text("请求错误！");
	        		$("#subReport").show();
	        		return false;
				}
			});
		}
		//无效回访弹窗弹出
		function invalidDivShow(){
			$(".Black").show();
		}
		
		//无效回访弹窗隐藏
		function invalidDivHide(){
			$(".Black").hide();
			$("#invalidReason").val("");
		}

        //提交弹窗隐藏
        function tijiaoDivHide(){
            $("#myModal-manage").hide();
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
	        var a=${projectModeValue};
	        if(a==2){
                window.location.href = baseUrl +"/customerreturnvisitrecorecord/bizCustomerReturnVisitRecord/traditionalorderlist?"
                    +"storeId="+ $("#storeId").val()
                    +"&projectMode="+ $("#projectMode").val()
                    +"&area="+ $("#area").val()
                    +"&orderNumber="+ $("#orderNumber").val()
                    +"&customerPhone="+ $("#customerPhone").val()
                    +"&customerName="+ $("#customerName").val()
                    +"&designerName="+ $("#designerName").val()
                    +"&itemManager="+ $("#itemManager").val()
                    +"&orderInspector="+ $("#orderInspector").val()
                    +"&returnVisitNode="+ $("#returnVisitNode").val()
                    +"&nodeCheckDateBegin="+ $("#nodeCheckDateBegin").val()
                    +"&nodeCheckDateEnd="+ $("#nodeCheckDateEnd").val()
					+"&pageNo"+$("#pageNo").val()
                    +"&pageSize"+$("#pageSize").val();
			}else{
                window.location.href = baseUrl +"/customerreturnvisitrecorecord/bizCustomerReturnVisitRecord/checkingList?"
                    +"storeId="+ $("#storeId").val()
                    +"&projectMode="+ $("#projectMode").val()
                    +"&area="+ $("#area").val()
                    +"&orderNumber="+ $("#orderNumber").val()
                    +"&customerPhone="+ $("#customerPhone").val()
                    +"&customerName="+ $("#customerName").val()
                    +"&designerName="+ $("#designerName").val()
                    +"&itemManager="+ $("#itemManager").val()
                    +"&orderInspector="+ $("#orderInspector").val()
                    +"&returnVisitNode="+ $("#returnVisitNode").val()
                    +"&nodeCheckDateBegin="+ $("#nodeCheckDateBegin").val()
                    +"&nodeCheckDateEnd="+ $("#nodeCheckDateEnd").val()
                    +"&num="+$("#pageNo").val()
                    +"&size="+$("#pageSize").val();
			}
	    }
	  	
	  	//根据输入框名称获取数组信息
	    function getArrayValues(name){
			return $("input[name="+ name +"]").map(function(){
				return $(this).val();
			}).get();
		}
	  	//根据类名称获取数组信息
	    function getClassObjArrayValues(name){
			return $("."+ name).map(function(){
				return $(this).val();
			}).get();
		}
	</script>	
</head>
<body>
	
	<div class="topTit">回访问卷-${nodeName}</div>
	<div class="wrap font0" style="margin-top:10px;">
		<div class="basic">
			<h3>订单基本信息</h3>
			<p class="mt38">
				<span>客户姓名: <i>${order.customerName}</i></span>
				<span>客户电话：<i>${order.customerPhone}</i></span>
			</p>
			<p>
				<span>工程地址: <i>${order.detailAddress}</i></span>
				<span>验收时间：<i><fmt:formatDate value="${order.checkDatetime}" pattern="yyyy-MM-dd HH:mm:ss"/></i></span>
			</p>
			<p>
				<span>合同开工日期：<i><fmt:formatDate value="${order.contractStartDate}" pattern="yyyy-MM-dd"/></i></span>
				<span>合同竣工日期：<i><fmt:formatDate value="${order.contractEndDate}" pattern="yyyy-MM-dd"/></i></span>
			</p>
			<p>
				<span>设计师: <i>${order.designerName}</i></span>
				<span>设计师手机：<i>${order.designerPhone}</i></span>
			</p>
			<p>
				<span>项目经理: <i>${order.managerName}</i></span>
				<span>项目经理手机：<i>${order.managePhone}</i></span>
			</p>
			<p>
				<span>质检员: <i>${order.inspectorName}</i></span>
				<span>质检员手机：<i>${order.inspectorPhone}</i></span>
			</p>
			<p>
				<span>客服姓名: <i>${order.serviceName}</i></span>
				<span>客服手机：<i>${order.servicePhone}</i></span>
			</p>
		</div>
		<c:if test="${ fn:length(historyVisitRecord) > 0 }">
		<div class="question font0">
			<h3><span>历史回访信息</span></h3>
				<div class="navWrapper clearfix">
				<a class="leftArrow" href="javascript:;"></a>
				<nav class="historyNav">
					<c:forEach items="${historyVisitRecord}" var="item" varStatus="status">
						<c:choose>
							<c:when test="${status.index == 0}">
								<a class="font14 navA active" href="javascript:;">${item.return_visit_node_name}</a>
							</c:when>
							<c:when test="${status.index < 5}">
								<a class="font14 navA" href="javascript:;">${item.return_visit_node_name}</a>
							</c:when>
							<c:otherwise>
								<a class="font14 navA undis" href="javascript:;">${item.return_visit_node_name}</a>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</nav>
				<a class="rgtArrow" href="javascript:;"></a>
			</div>
			<section class="historyContent pl30 pr30">
				<c:forEach items="${historyVisitRecord}" var="item" varStatus="status">
					<c:choose>
						<c:when test="${ status.index == 0}">
							<ul class="pb40">
							<c:forEach items="${item.questionList}" var="question" varStatus="status">
								<li class="item clearfix">
									<span class="historyLeft">${status.index+1 }.${question.return_visit_question }</span>
									<span class="historyRgt">${question.question_answer }</span>
								</li>
							</c:forEach>
							</ul>
						</c:when>
						<c:otherwise>
							<ul class="pb40 undis">
							<c:forEach items="${item.questionList}" var="question" varStatus="status">
								<li class="item clearfix">
									<span class="historyLeft">${status.index+1 }.${question.return_visit_question }</span>
									<span class="historyRgt">${question.question_answer }</span>
								</li>
							</c:forEach>
							</ul>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				
			</section>
			
		</div>
		</c:if>
		<div class="question">
			<form name="submitForm" >
			<input type="hidden" id="orderId" name="orderId" value="${bizCustomerReturnVisitRecord.orderId}">
			<input type="hidden" id="dealReturnVisitNode" name="dealReturnVisitNode" value="${dealReturnVisitNode}">
			<input type="hidden" id="returnVisitNodeName" name="returnVisitNodeName" value="${nodeName}">
			
			<!-- 查询条件 -->
			<input type="hidden" id="pageNo" value="${pageNo}">
			<input type="hidden" id="pageSize" value="${pageSize}">
			<input type="hidden" id="storeId" value="${bizCustomerReturnVisitRecord.storeId}">
			<input type="hidden" id="projectMode" value="${bizCustomerReturnVisitRecord.projectMode}">
			<input type="hidden" id="area" value="${bizCustomerReturnVisitRecord.area}">
			<input type="hidden" id="orderNumber" value="${bizCustomerReturnVisitRecord.orderNumber}">
			<input type="hidden" id="customerPhone" value="${bizCustomerReturnVisitRecord.customerPhone}">
			<input type="hidden" id="customerName" value="${bizCustomerReturnVisitRecord.customerName}">
			<input type="hidden" id="designerName" value="${bizCustomerReturnVisitRecord.designerName}">
			<input type="hidden" id="itemManager" value="${bizCustomerReturnVisitRecord.itemManager}">
			<input type="hidden" id="orderInspector" value="${bizCustomerReturnVisitRecord.orderInspector}">
			<input type="hidden" id="returnVisitNode" value="${bizCustomerReturnVisitRecord.returnVisitNode}">
			<input type="hidden" id="nodeCheckDateBegin" value="<fmt:formatDate value="${bizCustomerReturnVisitRecord.nodeCheckDateBegin}" pattern="yyyy-MM-dd"/>">
			<input type="hidden" id="nodeCheckDateEnd" value="<fmt:formatDate value="${bizCustomerReturnVisitRecord.nodeCheckDateEnd}" pattern="yyyy-MM-dd"/>">
			<!-- 查询条件 -->
			
			<h3><span>回访问题</span><span>回访指标</span></h3>
			<c:forEach items="${ questionList}" var="item" varStatus="status">
				
				<c:if test="${item.replyMode != 3}">
					<p>
				</c:if>
				<c:if test="${item.replyMode == 3}">
					<p class="text">
				</c:if>

					<span class="quesle">${status.index + 1}.${item.questionContent}</span>
					<input type="hidden" name="questionContent" value="${item.questionContent}">
					<input type="hidden" name="statisticsDepartment" value="${item.statisticsDepartment}">
					<c:choose>
						<c:when test="${item.replyMode == 1}">
							<select class="firstSelect">
								<c:forEach items="${ item.questionItems}" var="option">
									<option value="${ option.content}">${ option.content}</option>
								</c:forEach>
							</select>
							<input type="hidden" name="questionAnswer" >
							<input type="hidden" class="secondSelect" value="">
						</c:when>
						<c:when test="${item.replyMode == 2}">
							<select class="secondSelect">
								<c:forEach items="${ item.questionItems}" var="option">
									<option value="${ option.content}" childOptions="${option.answer}">${ option.content}</option>
								</c:forEach>
							</select>
							<select class="childSelect">
								<c:forEach items="${fn:split(item.questionItems[0].answer,',')}" var="option">
									<option value="${option}">${ option}</option>
								</c:forEach>
							</select>
							<input type="hidden" name="questionAnswer" >
						</c:when>
						<c:when test="${item.replyMode == 3}">
							<span class="quesrg">
								<textarea name="questionAnswerArea"  maxlength="100"></textarea><i class="del"></i>
								<i class="count"><em class="num">0</em> / 100</i>
								<input type="hidden" name="questionAnswer" value="">
								<input type="hidden" class="secondSelect" value="">
							</span>
						</c:when>
						<c:otherwise>
							<span class="quesrg">
								<input name="questionAnswer"  type="text" readonly="readonly" maxlength="20" class="input-medium Wdate day"
									value=""
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
									<input type="hidden" class="secondSelect" value="">
							</span>
						</c:otherwise>
					</c:choose>
				</p>
			</c:forEach>
			
			</form>
		</div>
		<div class="invalid">
			<h3>无效回访记录</h3>
			<c:forEach items="${invalidRecordList}" var="item">
				<div class="invalist">
					<p>
						<span class="quesle">无效回访时间：<fmt:formatDate value="${item.returnVisitTime}" pattern="yyyy-MM-dd HH:mm:ss"/></span>
						<span class="quesrg">回访人：${item.customServiceName }</span>
					</p>
					<div class="Remarks">
						<span>备注：</span>
						<p>${item.invalidReason }</p>
					</div>
				</div>		
			</c:forEach>
		</div>
		<p class="submit">
			<a href="#"  data-toggle="modal" data-target="#myModal-manage">提交</a>
			<a href="#" onclick="invalidDivShow()">无效回访</a>
			<a href="#" onclick="jumpToList()" style="margin-left: 50px;">返回</a>
		</p>
	</div>
	
	<div style="background: rgba(0, 0, 0, 0.4);z-index: 10000;display: none;width: 100%;height: 100%;position: fixed;top: 0;left: 0;" class="g-mask undis" id ="baseMask" >
	<p style="width: 400px;height: 200px;background: #fff;position: absolute;top: 50%;left: 50%;margin-top: -100px;margin-left: -200px;line-height: 200px;text-align: center;font-size: 20px;border-radius: 5px;">正在提交 . . . </p>
	</div>
	
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
	<!-- 无效回访弹窗  begin -->
	<div class="Black">
		<div class="content">
			<h3>请输入无效回访备注</h3>
			<textarea maxlength="100" id="invalidReason" name="invalidReason" ></textarea>
			<i class="count"><em class="num">0</em> / 100</i>
			<div class="form-btn text-center">
				<button type="button" class="btn btn-primary col-md-3" onclick="invalidSubmitFuntion()">确定</button>
				<button type="button" class="btn btn-default col-md-3" onclick="invalidDivHide()">取消</button>
			</div>
				<%--<p><span onclick="invalidSubmitFuntion()">确定</span><span onclick="invalidDivHide()">取消</span></p>--%>
		</div>
	</div>
	<!-- 无效回访弹窗  end -->

	<!-- 提交弹窗  begin -->
	<div class="modal fade disN" id="myModal-manage" tabindex="-3" role="dialog" aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close"></button>
					<h4 class="modal-title text-center" id="myModalLabel">您确认提交吗？</h4>
				</div>
				<div class="modal-body">
					<form action="" role="form">
							<div class="form-btn text-center">
								<button type="button" class="btn btn-primary col-md-3" onclick="submitFuntion()">确认</button>
								<button type="button" class="btn btn-default col-md-3" onclick="tijiaoDivHide()">取消</button>
							</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<!-- 提交弹窗  end -->
</body>
</html>