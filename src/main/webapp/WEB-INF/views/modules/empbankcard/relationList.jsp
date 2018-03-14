<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>工人银行卡关联管理</title>
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
	<style>
		.undis{display:none;}
		.g-mask{width:100%;height:100%;position:relative;z-index:99;font-size:0;}
		#g-done_ask{width:400px;height:230px;position:fixed;left:50%;top:50%;transform:translate(-50%,-50%);border:1px solid #333;border-radius:4px;}
		.refuse{height:50px;line-height:50px;font-size:15px;background:#54b4eb;margin:0;}
		/* .content{color:#000;width:400px;height:100px;resize: none;margin:0;} */
		.content{color:#000;width:400px;height:50px;resize: none;margin:0;}
		.second{width:400px;}
		.second a{display:block;width:200px;height:50px;line-height:50px;font-size:24px;text-decoration:none;float:left;text-align:center;background:#54b4eb;color:#fff;}
		.second a:first-child{box-sizing:border-box;border-right:1px solid #ccc;}
	</style>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/empbankcard/bizEmployeeBankcard/">工人银行卡列表</a></li>
		<li class="active"><a href="javascript:void(0)">工人银行卡关联身份证列表</a></li>
	</ul>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>员工姓名</th>
				<th>员工身份证号</th>
				<th>银行卡号</th>
				<th>关联姓名</th>
				<th>关联姓名身份证</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${list}" var="bizEmployeeBankcardRelation">
			<tr>
				<td>
					${bizEmployeeBankcardRelation.employeeName }
				</td>
				<td>
                   ${bizEmployeeBankcardRelation.idCardNo }
				</td>
				<td>
					${bizEmployeeBankcardRelation.bankCardNo }
				</td>
				<td>
					${bizEmployeeBankcardRelation.relationName }
				</td>
				<td>
					${bizEmployeeBankcardRelation.relationIdCardNo }
				</td>
				<td>
					<a href="${ctx}/empbankcard/bizEmployeeBankcard/deleteRelation?relationId=${bizEmployeeBankcardRelation.id}&employeeBankcardId=${bizEmployeeBankcardRelation.employeeBankcardId}" onclick="return confirmx('确认要删除该关联身份证吗？', this.href)">删除</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<input type = "hidden" id="employeeBankcardId" value="${id}">
	<li class="btns"><input class="btn btn-primary" type="button" value="添加关联身份证" onclick="addRelationIdCard()"/></li>
	
	<div class="g-mask undis" id="addRelationIdCard">
		<div id="g-done_ask">
			<p class="refuse">请输入关联姓名：</p>
			<input type="text" class="content" id="relationName" required="required">
			<!-- <textarea class="content" id="reason"></textarea> -->
			<p class="refuse">请输入关联姓名身份证：</p>
			<input type="text" class="content" id="relationCard" required="required">
			<p class="second">
				<a href="javascript:void(0)" onclick="noReject()">取消</a>
				<a href="javascript:void(0)" onclick="yesReject()">确认</a>
			</p>
		</div>
	</div>
	<script type="text/javascript">
	
		var employeeBankcardId ='';
		function addRelationIdCard(){
			employeeBankcardId = $("#employeeBankcardId").val();
			$("#addRelationIdCard").removeClass("undis");
			
		}
		function yesReject(){
			var sameCard = false;
			//alert(employeeBankcardId);
			var regName = /^[\u4e00-\u9fa5 ]{2,20}$/;
			var regCard = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;
			var name = $("#relationName").val();
			var card = $("#relationCard").val();
			if(regName.test(name)===false){
				alertx("请输入正确格式的姓名!");
			//	$("#relationName").val("");
				return;
			}
			if(regCard.test(card) === false){
				alertx("输入的身份证格式有误！");
				//$("#relationCard").val("");
				return;
			}else{
				//校验身份证不许重复,不仅不和自己重复, 要让数据库保持身份证唯一性
				$.ajax({
					url: "${ctx}/empbankcard/bizEmployeeBankcard/compareIdCard" ,
					type: "post",
					async: false,
					data:{
						identifiedCardNumber: card
					},
					success: function(data){
						if(data!=1){
							sameCard=true
							alertx("您输入的身份证已重复,请再次输入")
						}
					}
					
				})
				
				
			}
		if(sameCard){
			return;
		}else{
			$("#relationName").val("");
			$("#relationCard").val("");
			$("#addRelationIdCard").addClass("undis");
			window.location.href = "${ctx}/empbankcard/bizEmployeeBankcard/addRelation?employeeBankcardId="+employeeBankcardId+"&name="+name+"&card="+card;
		
			
		}
			
		
				
		}
		function noReject(){
			$("#relationName").val("");
			$("#relationCard").val("");
			$("#addRelationIdCard").addClass("undis");
		}
	</script>
</body>
</html>