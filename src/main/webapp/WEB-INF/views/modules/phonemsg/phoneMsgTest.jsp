<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>短信管理</title>
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

        function isPoneAvailable(str) {
            var myreg=/^[1][3,4,5,7,8][0-9]{9}$/;
            if (!myreg.test(str)) {
                return false;
            } else {
                return true;
            }
        }

        function sendMessageTest() {


            var msgPhone = $("#receivePhone").val().trim();
            var msgContent = $("#msgContent").val().trim();

            alert("手机号："+msgPhone);
            alert("短信内容："+msgContent);

            if(""==msgPhone){
                alert("请输入手机号");
                return false;
			}
            if(""==msgContent){
                alert("请输入短信内容");
                return false;
			}

            if(!isPoneAvailable(msgPhone)){
                alert("手机号错误");
				return false;
			}

            alert("开始发送短信");

			$.ajax({
				url: "${ctx }/phonemsg/phoneMsgTest/sendMessageTest",
				type: "post",
				data:{
					msgPhone:msgPhone,
					msgContent:msgContent
				},
				success: function(data) {

					alert("发送结果："+data);

				}
			});
		}

	</script>
</head>
<body>
<ul class="nav nav-tabs">
	<li class="active"><a href="${ctx}/phonemsg/phoneMsgTest/testList">短信列表</a></li>
</ul>
<form:form id="searchForm" modelAttribute="bizPhoneMsg" action="${ctx}/phonemsg/phoneMsgTest/testList" method="post" class="breadcrumb form-search">
	<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
	<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>

	<ul class="ul-form">

		<li><label>手机号：</label>
			<form:input path="receivePhone" htmlEscape="false" maxlength="11" class="input-medium needClear"/>
		</li>

		<li><label>短信发送内容：</label>
			<form:input path="msgContent" htmlEscape="false" maxlength="300" class="input-xlarge needClear"/>
		</li>

		<li class="btns"><input class="btn btn-primary" type="button" value="发送短信" onclick="sendMessageTest()"/></li>
		<li class="clearfix"></li>
	</ul>
</form:form>
<sys:message content="${message}"/>
<div class="pagination">${page}</div>
</body>
</html>