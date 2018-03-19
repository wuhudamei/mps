<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>大美装饰管理平台</title>
	<meta name="decorator" content="blank"/><c:set var="tabmode" value="${empty cookie.tabmode.value ? '1' : cookie.tabmode.value}"/>
	<c:if test="${tabmode eq '1'}"><link rel="Stylesheet" href="${ctxStatic}/jerichotab/css/jquery.jerichotab.css" />
		<script type="text/javascript" src="${ctxStatic}/jerichotab/js/jquery.jerichotab.js"></script></c:if>
	<style type="text/css">
		#main {padding:0;margin:0;} #main .container-fluid{padding:0 4px 0 6px;}
		#header {margin:0 0 8px;position:static;} #header li {font-size:14px;_font-size:12px;}
		#header .brand {font-family:Helvetica, Georgia, Arial, sans-serif, 黑体;font-size:26px;padding-left:33px;}
		#footer {margin:8px 0 0 0;padding:3px 0 0 0;font-size:11px;text-align:center;border-top:2px solid #0663A2;}
		#footer, #footer a {color:#999;} #left{overflow-x:hidden;overflow-y:auto;} #left .collapse{position:static;}
		#userControl>li>a{/*color:#fff;*/text-shadow:none;} #userControl>li>a:hover, #user #userControl>li.open>a{background:transparent;}
	</style>
	<style>
		html, body, div, span, applet, object, iframe,
		h1, h2, h3, h4, h5, h6, p, blockquote, pre,
		a, abbr, acronym, address, big, cite, code,
		del, dfn, em, img, ins, kbd, q, s, samp,
		small, strike, strong, sub, sup, tt, var,
		b, u, i, center,
		dl, dt, dd, ol, ul, li,
		fieldset, form, label, legend,
		table, caption, tbody, tfoot, thead, tr, th, td,
		article, aside, canvas, details, embed,
		figure, figcaption, footer, header, hgroup,
		menu, nav, output, ruby, section, summary,
		time, mark, audio, video {
			margin: 0;
			padding: 0;
			border: 0;
			font-size: 100%;
			font: inherit;
			vertical-align: baseline;
		}
		/* HTML5 display-role reset for older browsers */
		article, aside, details, figcaption, figure,
		footer, header, hgroup, menu, nav, section {
			display: block;
		}
		body {
			line-height: 1;
		}
		ol, ul {
			list-style: none;
		}
		blockquote, q {
			quotes: none;
		}
		blockquote:before, blockquote:after,
		q:before, q:after {
			content: '';
			content: none;
		}
		a{
			text-decoration: none;
		}
		table {
			border-collapse: collapse;
			border-spacing: 0;
		}
		body{
			overflow-y: scroll;
		}
		.header-bg {
			background: #000;
			width: 100%
		}

		.header {
			width: 100%;
			padding: 0 20px;
			min-width: 1280px;
			margin: 0 auto;
			padding-top: 10px;
			padding-bottom: 10px;
			height: 50px;
		}

		.header._login {
			width: 1000px
		}

		.header .logo img {
			vertical-align: middle;
			padding-right: 15px;
			border-right: 1px solid #fff;
			margin-right: 10px;
			margin-bottom: 10px
		}

		.header .logo span {
			font-size: 18px;
			color: #fff;
			font-weight: lighter;
			padding-right: 40px
		}

		.header .log-in {
			padding-top: 62px
		}

		.header .log-in .item {
			padding-right: 22px
		}

		.header .log-in .item._last {
			border-right: 0;
			padding-right: 0;
			color: #2eb3f2;
			text-decoration: underline
		}

		.header .log-txt {
			padding-top: 30px;
			color: #fff;
			float: right;
			margin-right: 30px;
		}

		.header .log-txt .btn-exit {
			text-decoration: underline;
			color: #2eb3f2;
			padding: 0 10px 0 10px
		}
		.logo-box{
			float: left;
		}
		.top-nav {
			width: 55%;
			padding-top: 20px;
			float: left;
		}

		.top-nav>li {
			position: relative;
			z-index: 2;
			float: left;
			font-size: 16px;
			color: #fff;
			padding-left: 2%;
			padding-right: 0
		}

		.top-nav li:hover {
			color: #b4cb4f;
			text-decoration: underline
		}

		.top-nav li .nav-sub {
			display: none;
			position: absolute;
			top: -5px;
			left: -15px;
			z-index: 5;
			width: 140px;
			padding-top: 30px
		}

		.nav-sub>li {
			width: 100%;
			padding: 10px;
			background: #323232;
			font-size: 12px;
			text-align: center;
			display: inline-block;
			border-bottom: 1px solid #fff
		}
		.top-nav li a,.nav-sub li a{
			text-decoration: none;
			color: #FFFFFF;
		}
		.top-nav li .nav-sub li:hover {
			color: #fff
		}
		/*shezhisanji*/
		.nav-sub>li{
			position: relative;
		}
		.nav-sub2{
			display: none;
			position: absolute;
			left: 160px;
			top: 0;
			width: 140px;
		}
		.nav-sub2>li{
			width:140px;
			padding:10px;
			background: #4284ED;
			font-size: 12px;
			text-align: center;
			display: inline-block;
			border-bottom: 1px solid #fff;
		}
		.nav-sub2>li{
			position: relative;
		}
		.nav-sub3{
			display: none;
			position: absolute;
			left: 160px;
			top: 0;
			width: 140px;
		}
		.nav-sub3>li{
			width:140px;
			padding: 10px;
			background: #323232;
			font-size: 12px;
			text-align: center;
			display: inline-block;
			border-bottom: 1px solid #fff;
		}
	</style>
	<script type="text/javascript">
        $(document).ready(function() {
            // <c:if test="${tabmode eq '1'}"> 初始化页签
            $.fn.initJerichoTab({
                renderTo: '#right', uniqueId: 'jerichotab',
                contentCss: { 'height': $('#right').height() - tabTitleHeight },
                tabs: [], loadOnce: true, tabWidth: 110, titleHeight: tabTitleHeight
            });//</c:if>
            // 绑定菜单单击事件
            $("#menu a.menu").click(function(){
				// <c:if test="${tabmode eq '1'}"> 打开显示页签
                	return addTab($(this)); // </c:if>
                // 大小宽度调整
                wSizeWidth();
                return false;
            });

            $(" .nav-sub2 li:first a.menuthree").click();
            //$(" .nav-sub2 ul:first li:first a").click();

            // 获取通知数目  <c:set var="oaNotifyRemindInterval" value="${fns:getConfig('oa.notify.remind.interval')}"/>
            function getNotifyNum(){
                $.get("${ctx}/oa/oaNotify/self/count?updateSession=0&t="+new Date().getTime(),function(data){
                    var num = parseFloat(data);
                    if (num > 0){
                        $("#notifyNum,#notifyNum2").show().html("("+num+")");
                    }else{
                        $("#notifyNum,#notifyNum2").hide()
                    }
                });
            }
            getNotifyNum(); //<c:if test="${oaNotifyRemindInterval ne '' && oaNotifyRemindInterval ne '0'}">
            setInterval(getNotifyNum, ${oaNotifyRemindInterval}); //</c:if>
        });
        // <c:if test="${tabmode eq '1'}"> 添加一个页签
        function addTab($this, refresh){
            $(".jericho_tab").show();
            $("#mainFrame").hide();
            $.fn.jerichoTab.addTab({
                tabFirer: $this,
                title: $this.text(),
                closeable: true,
                data: {
                    dataType: 'iframe',
                    dataLink: $this.attr('href')
                }
            }).loadData(refresh);
            return false;
        }// </c:if>
	</script>
</head>
<body>
<div id="main">
	<div id="header" class="navbar navbar-fixed-top">

		<div class="header-bg bg-grey">
			<div class="header _index clearfix">
				<div class="logo-box">
					<a href="javascript:;" class="logo pull-left">
						<img src="${ctxStatic}/images/logo-index.png" alt="logo"> <span>大美装饰管理平台</span>
					</a>
				</div>
				<!--/.logo-->
				<ul class="top-nav pull-left" id="menu">

					<c:set var="menuList" value="${fns:getMenuList()}"/>

					<c:forEach items="${menuList}" var="menu" varStatus="idxStatus">
						<c:if test="${menu.parent.id eq '1'&& menu.isShow eq '1'}">
							<li>
								<a href="javascript:;">
										${menu.name}
								</a>
									<%--//二级菜单--%>
								<ul class="nav-sub">
									<c:forEach items="${menuList}" var="menuTwo" varStatus="idxStatusTwo">
										<c:if test="${menuTwo.parent.id eq (not empty menu.id ? menu.id:1) && menuTwo.isShow eq '1'}">
											<li>
												<a href="javascript:;">
														${menuTwo.name}
												</a>

													<%--//三级菜单--%>
												<ul class="nav-sub2">
													<c:forEach items="${menuList}" var="menuThree" varStatus="idxStatusThree">
														<c:if test="${menuThree.parent.id eq (not empty menuTwo.id ? menuTwo.id:1) && menuThree.isShow eq '1'}">
															<li>
																<a class="menu menuthree" href="${fn:indexOf(menuThree.href, '://') eq -1 ? ctx : ''}${not empty menuThree.href ? menuThree.href : '/404'}" target="${not empty menuThree.target ? menuThree.target : 'mainFrame'}" >
																		${menuThree.name}
																</a>

																	<%--//四级菜单--%>
																<ul  class="nav-sub3">
																	<c:forEach items="${menuList}" var="menuFour" varStatus="idxStatusFour">
																		<c:if test="${menuFour.parent.id eq (not empty menuThree.id ? menuThree.id:1) && menuFour.isShow eq '1'}">
																			<li>
																				<a  class="menu" href="${fn:indexOf(menuFour.href, '://') eq -1 ? ctx : ''}${not empty menuFour.href ? menuFour.href : '/404'}" target="${not empty menuFour.target ? menuFour.target : 'mainFrame'}" >
																						${menuFour.name}
																				</a>

																			</li>

																		</c:if>
																	</c:forEach>
																</ul>

															</li>

														</c:if>
													</c:forEach>
												</ul>

											</li>

										</c:if>
									</c:forEach>
								</ul>
							</li>
						</c:if>
					</c:forEach>

				</ul>
				<div class="log-txt pull-right">
						<span>
							您好, ${fns:getUser().name}
						</span>
					<a href="${ctx}/logout" class="btn-exit">退出</a>
				</div>
				<!--/.log-in-->
			</div>
		</div>
	</div>
	<div class="container-fluid">
		<div id="content" class="row-fluid">
			<div id="right">
				<iframe id="mainFrame" name="mainFrame" src="" style="overflow:visible;" scrolling="yes" frameborder="no" width="100%" height="650"></iframe>
			</div>
		</div>
		<div id="footer" class="row-fluid">
			Copyright &copy; 2012-${fns:getConfig('copyrightYear')} ${fns:getConfig('productName')} - Powered By <a href="http://jeesite.com" target="_blank">JeeSite</a> ${fns:getConfig('version')}
		</div>
	</div>
</div>
<script type="text/javascript">
    var leftWidth = 170; // 左侧窗口大小
    var tabTitleHeight = 33; // 页签的高度
    var htmlObj = $("html"), mainObj = $("#main");
    var headerObj = $("#header"), footerObj = $("#footer");
    var frameObj = $(" #right, #right iframe");
    function wSize(){
        var minHeight = 500, minWidth = 980;
        var strs = getWindowSize().toString().split(",");
        htmlObj.css({"overflow-x":strs[1] < minWidth ? "auto" : "hidden", "overflow-y":strs[0] < minHeight ? "auto" : "hidden"});
        mainObj.css("width",strs[1] < minWidth ? minWidth - 10 : "auto");
        frameObj.height((strs[0] < minHeight ? minHeight : strs[0]) - headerObj.height() - footerObj.height() - (strs[1] < minWidth ? 42 : 28));
        // <c:if test="${tabmode eq '1'}">
        $(".jericho_tab iframe").height($("#right").height() - tabTitleHeight); // </c:if>
        wSizeWidth();
    }
    function wSizeWidth(){
            $("#right").width("100%");
    }// <c:if test="${tabmode eq '1'}">
    function openCloseClickCallBack(b){
        $.fn.jerichoTab.resize();
    } // </c:if>



    $(function() {
        $(".top-nav li").hover(function() {
            $(this).find(".nav-sub").show();
        }, function() {
            $(this).find(".nav-sub").hide();

        });
        $(".nav-sub li").hover(function() {
            $(this).find(".nav-sub2").show();
        }, function() {
            $(this).find(".nav-sub2").hide();

        });
        $(".nav-sub2 li").hover(function() {
            $(this).find(".nav-sub3").show();
        }, function() {
            $(this).find(".nav-sub3").hide();

        });
    });

</script>
<script src="${ctxStatic}/common/wsize.min.js" type="text/javascript"></script>
</body>
</html>