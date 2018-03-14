<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/mobile/modules/pqc/applyCheckBase/pqcBaseJsp.jsp"%>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1, user-scalable=no">
	<meta content="yes" name="apple-mobile-web-app-capable">
	<meta content="black" name="apple-mobile-web-app-status-bar-style">
	<meta content="telephone=no" name="format-detection">
	<meta content="email=no" name="format-detection">
	<title>选择检查项</title>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/pqc/css/reset.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/pqc/css/header.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/pqc/css/check_items.css"/>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/pqc/css/search.css"/>
</head>
<body>
	<div class="g-item">
		<header>
			<a class="back_btn" href="${ctx }/app/pqc/checkList/list"></a>
			<h2 class="title">选择检查项</h2>
		</header><!-- /header -->
			<form  id="itemForm" method="post" action="${ctx }/app/pqc/checkItem/saveItems">
		<section class="item">
			<div class="mar_btm14 font0 search-area">
				<input class="search-box" type="text" placeholder="">
				<a class="search-btn" href="javascript:;" onclick="findText()"></a>
			</div> 
		<input type="text" hidden="hidden" name ="inspectId" value="${inspectId }"/>
			<ul id="container-ul">
			<c:forEach items="${list }" var="checkKind" varStatus="status">
				<li class="clearfix font28" id="li${status.index+1}">
					<p class="font28 borBtm relative hgt86 pad_left30">
						<input type="checkbox" id="build" name="constr" value="build">
						<c:if test="${checkKind.isChoosed=='1'}"> <label data-name="constr" for="build" class="checked" >${checkKind.checkKindName }</label>  </c:if>  
							<c:if test="${checkKind.isChoosed !='1' }"> <label data-name="constr" for="build">${checkKind.checkKindName }</label>             </c:if>
						<a class="showAll" href="javascript:void(0)">展开</a>
					</p>
					<c:forEach items="${checkKind.checkItemList }" var="item">
					<ul class="item_bar undis">
						<li class="hgt86 wid_per90 borBtm mar_left10">
							<input type="checkbox" id="one" value="one">
						<input type="text" hidden="hidden" name ="itemName" value="${item.checkItemName}"/>
							<input type="text" hidden="hidden" name ="itemId" value="${item.checkItemId}"/>
							<!-- 必选检查项 --> 
							<c:if test="${item.isRequired=='1' }">
							<label style="line-height: 1.2em;" data-name="one" for="one" class="checked" name="bixuan" onclick="addCheckClass(this)">${item.checkItemName}</label>
							</c:if>
							
							<!--  不必选检查项-->
						<c:if test="${item.isRequired!='1' }">
						<!-- 不是更改检查项 -->
						<c:if test="${item.isChoosed !='1'}"><label data-name="one" for="one">${item.checkItemName}</label></c:if>	
						<!-- 是更改选择项 -->
						<c:if test="${item.isChoosed=='1' }"><label data-name="one" for="one" class="checked">${item.checkItemName}</label></c:if>
						</c:if>
						</li>
					</ul>
					</c:forEach>
				</li>
				</c:forEach>
			</ul>

			<input type="text" hidden="hidden" name="customerInfo" value="${customerInfo}">
		</section>

				<div class="alertMask undis" id="timeAlert">
					<div class="maskWrapper">
						<p class="col_3 maskTit">质检员您好</p>
						<div class="font28 col_6 maskContent" id="firstText"></div>
						<div class="maskBtns clearfix">
							<a class="maskBtn font33 col_f" href="javascript:;" onclick="sure()">我知道了</a>
						</div>
					</div>
				</div>
			</form>
		<footer>
			<a href="javascript:void(0)" id="submit">确定</a>
		</footer>
	</div>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/pqc/js/lib/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/pqc/js/utils/calcRootFontSize.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/pqc/js/utils/history.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/pqc/js/qcCheck/checkItem.js"></script>
	<style type="text/css">
        .highlight { background-color:yellow; }
    </style>

	<script type="text/javascript">
		var inspectId="${inspectId }";


	</script>

</body>
</html>