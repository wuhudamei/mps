<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
	<title>返单客服规则管理</title>
	<meta name="decorator" content="default" />
	<link rel="stylesheet" href="${ctxStatic}/modules/orderreportsendrule/css/customer.css">
	<script src="${ctxStatic}/modules/orderreportsendrule/js/customer.js"></script>
	<script type="text/javascript">
	var ctxStatic = '${ctxStatic}';
		//提交方法
		function submitFun(){
			
			if ($('#customerBack').find('tr').length <= 0) {
				alertx("客服人员不可为空，请选择客服人员！");
				return;
			}else{
				$.ajax({
				    url:  '${ctx}/orderreportsendrule/OrderReportSendRule/save',
				    type:'POST', //GET
				    data:$("form").serializeArray(),
				    dataType:'json',
				    success:function(res){
				        if( res.code == '200' ){
				        	$("#sendRuleId").val(res.data);
				        	alertx("操作成功！");
				        	$('.icon-clock').attr('src',ctxStatic + '/modules/orderreportsendrule/images/clock.png');
				        	$('.editMask').show();
							$('#editChoice').on('click',function(){
								$('.icon-clock').attr('src',ctxStatic + '/modules/orderreportsendrule/images/unclock.png');
								$('.editMask').hide();
							})
				        }else{
				        	alertx("操作失败！");
				        }
				    },
				    error:function(res){
				        
				    }
				});
			}
		}
		
		//根据规则id查询对应的客服人员
		function viewMembers(obj){
			$.ajax({
			    url:  '${ctx}/orderreportsendrule/OrderReportSendRule/getServiceMembers?sendRuleId='+ obj,
			    type:'GET', //GET
			    dataType:'json',
			    success:function(res){
			        if( res.code == '200' ){
			        	$("#memberList").html('');
			        	$.each(res.data, function(i,item){  
			                $("#memberList").append('<tr><td>'+ (i+1) +'</td><td>'+ item.realName +'</td><td>'+ item.phone +'</td></tr>');
			        	}); 
			        	$('.customerMask').show(500);
						$('.marksure').on('click',function () {
							$('.customerMask').hide(500);
						})
			        }
			    },
			    error:function(res){}
			});
			
		}
	</script>
</head>
<body>
	<p class="cont-title"><span>客服分单规则管理</span><span class="fr" id="editChoice"><img src="${ctxStatic}/modules/orderreportsendrule/images/clock.png" class="icon-clock"></span></p>
	<div class="customer-wrapper">
	<div class="customerContent">
		<div class="editMask"></div>
		<div class="customer-list">
			<table>
				<caption>客服人员列表</caption>
				<thead>
					<tr>
						<th>客服姓名</th>
						<th>手机号码</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody id="customerList">
					<c:forEach items="${serviceMembers}" var="member">
						<tr>
							<td>${member.realName}
								<input type="hidden" name="leftEmployeeId" value="${member.id}" class="hidden">
							</td>
							<td>${member.phone}</td>
							<c:if test="${member.addFlag eq 'Y'}">
								<td class="addBtn colBtn">已添加</td>
							</c:if>
							<c:if test="${member.addFlag eq 'N'}">
								<td class="col_blue addBtn colBtn">添加</td>
							</c:if>
							
						</tr>
					</c:forEach>					
				</tbody>
			</table>
		</div>
		<div class="customer">
			<form name="submitForm" >
			<input type="hidden" name="sendRuleId" id="sendRuleId" value="${sendRuleId}">
			<div class="customer-back">
				<table>
					<caption>返单轮循客服列表</caption>
					<thead>
						<tr>
							<th>编号名</th>
							<th>客服姓名</th>
							<th>手机号码</th>
							<th>操作</th>
						</tr>

					</thead>
					<tbody id="customerBack"></tbody>
				</table>
				
			</div>
			<div class="posCenter">
				<p>
					<span class="setTime">设置生效时间：</span>
					<input name="startDateTime" type="text" maxlength="20" readonly="readonly" class="input-medium needClear Wdate"
					   value="${startDateTime}" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'%y-%M-{%d+1}',isShowClear:false});"/>
				</p>
				<div class="saveBtn">
				<%-- 	<c:if test="${empty sendRuleStatus || sendRuleStatus eq 1}"> --%>
						<input type="button" id="saveBtn" value="保存" onclick="submitFun()">
					<!-- 	<input type="button" id="updateBtn" value="修改" class="cancel"> -->
				<%-- 	</c:if> --%>
					<%-- <c:if test="${sendRuleStatus eq 0}">
						<input type="button" id="saveBtn" value="保存" class="cancel">
					<!-- 	<input type="button" id="updateBtn" value="修改" onclick="submitFun('update')"> -->
					</c:if> --%>
					
				</div>
			</div>
			</form>	
		</div>
	</div>
		<div class="rember">
			<table>
				<caption>返单轮循记录</caption>
				<thead>
					<tr>
						<th>编号</th>
						<th>操作人</th>
						<th>客服轮循生效日期</th>
						<th>客服轮循结束日期</th>
						<th>客服名单</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${ruleExecuteRuleRecord}" var="record" varStatus="status">
						<tr>
							<td>${ status.index + 1}</td>
							<td>${record.createName}</td>
							<td><fmt:formatDate value="${record.startDatetime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
							<td><fmt:formatDate value="${record.endDatetime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
							<td class="Details" onclick="viewMembers(${record.id})">详情</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
	<div class="customerMask">
		<div class="customerMask_content">
			<div class="climitCont">
			<table>
				<thead>
					<tr >
						<th>编号</th>
						<th>客服姓名</th>
						<th class="none">手机号码</th>
					</tr>

				</thead>
				<tbody id="memberList"></tbody>
				
			</table>
			</div>
		 <button class="marksure">确认</button>
			
		</div>
	</div>
</body>
</html>