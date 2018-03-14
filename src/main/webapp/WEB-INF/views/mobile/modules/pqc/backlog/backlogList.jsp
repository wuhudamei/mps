<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/mobile/modules/pqc/applyCheckBase/pqcBaseJsp.jsp"%>
	<html lang="en">
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1, user-scalable=no">
		<meta content="yes" name="apple-mobile-web-app-capable">
		<meta content="black" name="apple-mobile-web-app-status-bar-style">
		<meta content="telephone=no" name="format-detection">
		<meta content="email=no" name="format-detection">
		<title>待办事项</title>
		<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/pqc/css/reset.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/pqc/css/info.css"/>
	
		<link rel="stylesheet" href="${ctxStatic}/mobile/modules/pqc/css/base.css" />
		<link rel="stylesheet" href="${ctxStatic}/mobile/modules/pqc/css/header.css" />
		<link rel="stylesheet" href="${ctxStatic}/mobile/modules/pqc/css/backlog/backlog.css">
		</head>
	<body>
		<header>
			<a class="back_btn New_btns" href="${ctx}/app/pqc/pqcIndex" ></a>
			<h2 class="title">待办事项</h2>
		</header>
		<div class="section" style="margin-bottom: 2rem">
			<h3>今日有<span id="totalBacklogId"></span>个待办事项</h3>
			<div class="list"  id="packRecheckH2Id">
				<%--<h2><i class="img2"></i>任务包复核<span id="packRecheckId"><em class=""></em></span></h2>--%>
				<%--<div class="todo_list">--%>
					<%--<div class="todo_infos">--%>
						<%--<div class="infos">--%>
							<%--<span class="name">顾客：</span>--%>
							<%--<p class="address">合顺家园阿斯蒂芬1503-张丰峰</p>--%>
						<%--</div>--%>

						<%--<a href="javascript:;">去复检</a>--%>
					<%--</div>--%>
				<%--</div>--%>
			</div>




			<div class="list"  id="applyCheckDoneH2Id">
				<%--<h2><i class="img1"></i>约检验收<span id="applyCheckDoneId"><em></em></span></h2>--%>
				<%--<div class="todo_list">--%>
					<%--<div class="todo_infos">--%>
						<%--<div class="infos">--%>
							<%--<span class="name">顾客2：</span>--%>
							<%--<p class="address">合顺家园合家园合顺家家园合顺家园-16-2-1503-张丰峰</p>--%>
						<%--</div>--%>
						<%--<div class="infos">--%>
							<%--<span class="name">约检节点：</span>--%>
							<%--<p class="address">水电隐蔽工程</p>--%>
						<%--</div>--%>
						<%--<a href="javascript:;">去验收</a>--%>
					<%--</div>--%>


				<%--</div>--%>



			<%--</div>--%>




		</div>
		</div>
		<footer>
		<a class="footer_btn home_btn" href="${ctx}/app/pqc/pqcIndex">首页</a>
		<a class="footer_btn todo_btn  active" href="${ctx}/app/pqc/backlog/preList.php">待办</a>
		<a class="footer_btn msg_btn" href="${ctx}/app/pqc/message">消息<div class="msg_countmine">${unreadMessageNum }</div></a>

		<a class="footer_btn mine_btn" href="${ctx}/app/pqc/indexMine">我的</a>
	</footer>
		<script src="${ctxStatic}/mobile/modules/pqc/js/utils/calcRootFontSize.js"></script>

		<script src="${ctxStatic}/mobile/modules/pqc/js/backlog/backlog.js"></script>
		<script src="${ctxStatic}/mobile/modules/pqc/js/backlog/backlogList.js"></script>

		<script type="text/javascript">
//var url = window.location.href;
//var enCodeUrl = base64encode(url);
//alert(enCodeUrl)
//
//var decodeUrl = base64decode(enCodeUrl);
//alert(decodeUrl)
//
//var base64EncodeChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
//var base64DecodeChars = new Array(
//    -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
//    -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
//    -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, -1, -1, 63,
//    52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -1, -1, -1,
//    -1,  0,  1,  2,  3,  4,  5,  6,  7,  8,  9, 10, 11, 12, 13, 14,
//    15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, -1,
//    -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40,
//    41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -1, -1, -1, -1, -1);
////客户端Base64编码
//function base64encode(str) {
//    var out, i, len;
//    var c1, c2, c3;
//
//    len = str.length;
//    i = 0;
//    out = "";
//    while(i < len) {
//        c1 = str.charCodeAt(i++) & 0xff;
//
//        if(i == len)
//        {
//            out += base64EncodeChars.charAt(c1 >> 2);
//            out += base64EncodeChars.charAt((c1 & 0x3) << 4);
//            out += "==";
//            break;
//        }
//        c2 = str.charCodeAt(i++);
//        if(i == len)
//        {
//            out += base64EncodeChars.charAt(c1 >> 2);
//            out += base64EncodeChars.charAt(((c1 & 0x3)<< 4) | ((c2 & 0xF0) >> 4));
//            out += base64EncodeChars.charAt((c2 & 0xF) << 2);
//            out += "=";
//            break;
//        }
//        c3 = str.charCodeAt(i++);
//
//        out += base64EncodeChars.charAt(c1 >> 2);
//        out += base64EncodeChars.charAt(((c1 & 0x3)<< 4) | ((c2 & 0xF0) >> 4));
//        out += base64EncodeChars.charAt(((c2 & 0xF) << 2) | ((c3 & 0xC0) >>6));
//        out += base64EncodeChars.charAt(c3 & 0x3F);
//    }
//    return out;
//}
////客户端Base64解码
//function base64decode(str) {
//    var c1, c2, c3, c4;
//    var i, len, out;
//
//    len = str.length;
//    i = 0;
//    out = "";
//    while(i < len) {
//		/* c1 */
//        do {
//            c1 = base64DecodeChars[str.charCodeAt(i++) & 0xff];
//        } while(i < len && c1 == -1);
//        if(c1 == -1)
//            break;
//
//		/* c2 */
//        do {
//            c2 = base64DecodeChars[str.charCodeAt(i++) & 0xff];
//        } while(i < len && c2 == -1);
//        if(c2 == -1)
//            break;
//
//        out += String.fromCharCode((c1 << 2) | ((c2 & 0x30) >> 4));
//
//		/* c3 */
//        do {
//            c3 = str.charCodeAt(i++) & 0xff;
//            if(c3 == 61)
//                return out;
//            c3 = base64DecodeChars[c3];
//        } while(i < len && c3 == -1);
//        if(c3 == -1)
//            break;
//
//        out += String.fromCharCode(((c2 & 0XF) << 4) | ((c3 & 0x3C) >> 2));
//
//		/* c4 */
//        do {
//            c4 = str.charCodeAt(i++) & 0xff;
//            if(c4 == 61)
//                return out;
//            c4 = base64DecodeChars[c4];
//        } while(i < len && c4 == -1);
//        if(c4 == -1)
//            break;
//        out += String.fromCharCode(((c3 & 0x03) << 6) | c4);
//    }
//    return out;
//}
            orderPackageReCheck();
            applyCheckDone();
		</script>
	</body>
	</html>