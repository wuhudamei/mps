<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>大美装饰管理平台</title>
    <meta name="decorator" content="blank"/>
    <meta name="keywords" content=" " />
    <meta name="description" content=" " />

    <meta http-equiv="x-ua-compatible" content="IE=Edge, chrome=1" />
    <meta name="renderer" content="webkit">
    <link href="${ctxStatic}/dameistatic/style.css" rel="stylesheet" type="text/css" />
    <!-- 占位隐藏添加 [hidden]  不占位隐藏添加 [disN]-->
    <style>
        .tab-menu2{
            background: #000000;
            text-align: center;
        }
        .login-content .tab-menu a{
            width: 100%;
        }
    </style>
</head>
<body>

    <div class="wrapper">
        <div class="header-bg">
            <div class="header _login clearfix">
                <a href="javascript:;" class="logo pull-left">
                   <img src="${ctxStatic}/images/logo.png" alt="logo">
                </a>
            </div>

        </div>

        <div class="content content-login login">
            <div class="login-content clearfix">
                <div class="login-box pull-left">
                    <div class="title">认证系统</div>
                    <div class="en-title">Authentication system</div>
                    <div class="tab">
                        <div id="messageBox" class="alert alert-error ${empty message ? 'hide' : ''}"><button data-dismiss="alert" class="close">×</button>
                            <label id="loginError" class="error">${message}</label>
                        </div>
                        <div class="tab-menu clearfix tab-menu2" id="tabMenu">
                            <%--<a href="javascript:void(0)" class="item">二维码登录</a>--%>
                            <a href="javascript:void(0)" class="item _active">用户名登录</a>
                        </div>
                        <!--/.tab-menu-->
                        <form id="loginForm" class="form-signin" action="${ctx}/login" method="post">
                        <div class="tab-content" id="tabContent">
                            <div class="input-form disN">
                                <p><img src="${ctxStatic}/images/qr-code.jpg" alt="img"></p>
                            </div>
                            <div class="input-form">
                                <div class="form-label clearfix posR">
                                    <i class="icon icon-user"></i>
                                    <input type="text" style="height: 50px" id="username" name="username" maxlength="30" class="input-item required" placeholder="请输入员工工号" value="${username}">
                                </div>
                                <div class="form-label clearfix posR">
                                    <i class="icon icon-lock"></i>
                                    <input type="password" style="height: 50px" id="password" name="password" maxlength="30" class="input-item required" placeholder="请输入密码" value="${password}">
                                </div>
                                <div class="form-label clearfix">
                                    <input class="btn" type="submit" value="登 录"/>
                                </div>
                            </div>
                        </div>
                        <!--/.tab-content-->
                        </form>
                    </div>

                </div>
                <div class="login-img pull-right"><img src="${ctxStatic}/images/img01.png" alt="img"></div>
            </div>
            <!--/.login-content-->
        </div>
        <!--/.content-->
        <div class="footer">
            <div class="copyright"><span>Copyright © 2017北京大美新风信息技术有限公司  版权所有</span></div>
        </div>
        <!--/.footer-->
    </div>


    <script>
    $(function() {
            $("#loginForm").validate({
                rules: {
                    username:"required"
                },
                messages: {
                    username: {required: "请填写用户名."},password: {required: "请填写密码."}
                },
                errorLabelContainer: "#messageBox",
                errorPlacement: function(error, element) {
                    error.appendTo($("#loginError").parent());
                }
            });
        // 如果在框架或在对话框中，则弹出提示并跳转到首页
        if(self.frameElement && self.frameElement.tagName == "IFRAME" || $('#left').length > 0 || $('.jbox').length > 0){
            alert('未登录或登录超时。请重新登录，谢谢！');
            top.location = "${ctx}";
        }
        $('#tabMenu a').bind('click', function(event) {
            var $a = $(this);
            var index = $a.index();
            var contents = $('#tabContent').children('div');
            var tabs = $(this).parent().children('a');
            tabs.each(function(index, item) {
                $(item).attr('class', 'item');
            });
            contents.each(function(index, item) {
                $(this).attr('class', 'input-form disN');
            });
            $a.addClass('_active');
            contents.eq(index).removeClass('disN');
        })
    })
    </script>
</body>

</html>