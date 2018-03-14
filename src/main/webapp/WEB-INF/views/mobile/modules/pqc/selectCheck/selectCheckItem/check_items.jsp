<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
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
			<a class="back_btn" href="${ctx }/app/pqc/selectCheckList/list"></a>
			<h2 class="title">选择检查项</h2>
		</header><!-- /header -->
			<form  id="itemForm" method="post" action="${ctx }/app/pqc/selectCheckList/saveItems">
		<section class="item">
			<div class="mar_btm14 font0 search-area">
				<input class="search-box" type="text" placeholder="">
				<a class="search-btn" href="javascript:;" onclick="findText()"></a>
			</div>
		<input type="text" hidden="hidden" name ="qcBillId" value="${qcBillId }"/>
		<input type="text" hidden="hidden" name ="orderId" value="${orderId }"/>
			<ul id="container-ul">
			<c:forEach items="${list }" var="checkKind" varStatus="status">
				<li class="clearfix font28" id="li${status.index+1}">
					<p class="font28 borBtm relative hgt86 pad_left30">
						<input type="checkbox" id="build" name="constr" value="build">
							<c:if test="${checkKind.isChoosed=='1'}"> <label data-name="constr" for="build" class="checked" >${checkKind.checkKindName }</label>  </c:if>
							<c:if test="${checkKind.isChoosed !='1' }"> <label data-name="constr" for="build">${checkKind.checkKindName }</label></c:if>
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
								<c:if test="${item.isChoosed !='1'}">
									<label style="line-height: 1.2em;" data-name="one" for="one">${item.checkItemName}</label>
								</c:if>
								<!-- 是更改选择项 -->
								<c:if test="${item.isChoosed=='1' }">
									<label style="line-height: 1.2em;" data-name="one" for="one" class="checked">${item.checkItemName}</label>
								</c:if>
							</c:if>
						</li>
					</ul>
					</c:forEach>
				</li>
				</c:forEach>
			</ul>
			<input type="text" hidden="hidden" name="customerInfo" value="${customerInfo}">
		</section>
			</form>
		<footer>
			<a href="javascript:void(0)" id="submit">确定</a>
		</footer>
	</div>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/pqc/js/lib/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/pqc/js/utils/calcRootFontSize.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/pqc/js/utils/history.js"></script>
	<style type="text/css">
        .highlight { background-color:yellow; }
    </style>
	<script>




	function findText(){

		var searchText = $('.search-box').val();
		if(null==searchText || searchText==""){
			clearSelection();
			$('.clearfix').show();
			$('.item_bar').hide();
			$('.showSome').text('展开').addClass('showAll').removeClass('showSome');
			return false;
		}

		/* var reg=/[^\u4E00-\u9FA5]/;

		if(reg.test(searchText)){
			$('.search-box').val("");
			return false;
		} */

		$('.clearfix').hide();
		$('.item_bar').hide();
		clearSelection();
		var regExp = new RegExp(searchText, 'g');
	/* 	$('p label').each(function()//遍历文章；
            {
                var html = $(this).html();
                var newHtml = html.replace(regExp, '<span class="highlight">'+searchText+'</span>');//将找到的关键字替换，加上highlight属性；

                $(this).html(newHtml);//更新文章；
            }); */
		$('.item_bar label').each(function()//遍历文章；
            {
                var html = $(this).html();
                var newHtml = html.replace(regExp, '<span class="highlight">'+searchText+'</span>');//将找到的关键字替换，加上highlight属性；

                $(this).html(newHtml);//更新文章；
            });

		$(".highlight").parent().parent().parent().parent().show();
		$(".highlight").parent().parent().parent().show();
		$('.showAll').text('折叠').addClass('showSome').removeClass('showAll');

	}

	function clearSelection(){
           /*  $('p label').each(function()//遍历
            {
                $(this).find('.highlight').each(function()//找到所有highlight属性的元素；
                {
                    $(this).replaceWith($(this).html());//将他们的属性去掉；
                });
            }); */

			 $('.item_bar label').each(function()//遍历
            {
                $(this).find('.highlight').each(function()//找到所有highlight属性的元素；
                {
                    $(this).replaceWith($(this).html());//将他们的属性去掉；
                });
            });
        }




	//如果是必选, 增加点击时间  不停地加check
	function addCheckClass(obj){
		$(obj).addClass("class","checked");
	}

	$(document).ready(function() {
		//绑定onclick事件
		$("#submit").bind('click',submitData);
	});

	function submitData(){
		var checkItemIds = $(".checked").prev();

		for(var v =0;v<checkItemIds.length;v++){
			if($(checkItemIds[v]).val() =="build"){
			}else{
				$(checkItemIds[v]).attr("name","checkItemId");
				var checkItemName =$(checkItemIds[v]).prev();
				$(checkItemName).attr("name","checkItemName");
			}
		}

		$("#submit").css("color","#CCC");
		$("#submit").unbind("click");
		$("#itemForm").submit();

	}


		var arrObj = {};
		var bindClick = function (liId, selArr){
			$(document).on('click', '#'+liId+' ul label', function(){

			    if($(this).attr('class') === 'checked'){
					// unselect
					$(this).attr('class', '');
					$(this).parent().removeClass('bg_light');
					$('#'+liId+' p label').attr('class','');
					$('#'+liId+' p').removeClass('bg_light');
				}else{
					// select
					$(this).parent().addClass('bg_light');
					$(this).attr('class', 'checked');
					var isAllTrue = '';
					$.each($(this).parents('#'+liId).find('li label'), function(i, v){
						if ($(v).attr('class') === "checked") {
							isAllTrue += '1';
						}else{
							isAllTrue += '0';
						}
					});
					if (isAllTrue.indexOf('0') > -1) {
						$('#'+liId+' p label').attr('class','');
						$('#'+liId+' p').removeClass('bg_light');
					}else{
						$('#'+liId+' p label').attr('class','checked');
						$('#'+liId+' p').addClass('bg_light');
					};
				}
				var theitem = liId;
				selArr.length = 0;
				$.each($('#'+liId+' ul label'), function(i, v){
					if ($(v).attr('class') === 'checked') {
						selArr.push($(v).parent().find('input').val());
					}
				});
				console.log(arrObj);
			});

			$(document).on('click', '#'+liId+' p label', function(){


				var b = $(this).parent().parent().find('label').attr('class');
				if (b === 'checked') {

					$(this).parent().parent().find('label').attr('class', '');
					$('#'+liId+' ul label').attr('class', '');
					selArr.length = 0;
					$(this).parent().removeClass('bg_light');
					$(this).parent().parent().find('li').removeClass('bg_light');

					//必选不让取消
					$("label[name*=bixuan]").removeAttr("class");
					$("label[name*=bixuan]").attr('class','checked');

				}else{

					$(this).parent().addClass('bg_light');
					$(this).parent().parent().find('li').addClass('bg_light');
					$(this).parent().parent().find('label').attr('class', 'checked');
					$('#'+liId+' ul label').attr('class', 'checked');
					$.each($('#'+liId+' ul label'), function(i, v){
						selArr.push($(v).parent().find('input').val());
					});



				}
				console.log(arrObj);
			});
		}
		$(function(){
			for (var i = 0, len = $('#container-ul > li').size(); i < len; i++) {
				var liId = $('#container-ul > li').eq(i).attr('id');
				arrObj['selArrli'+i]=[];
				bindClick(liId, arrObj['selArrli'+i]);
			}

			//以事件委托的形式，绑定元素点击事件
			$(document).on('click', '.showAll', function(){
				$(this).parent().parent().find('.item_bar').show();
				$(this).text('折叠').addClass('showSome').removeClass('showAll');
			});
			$(document).on('click', '.showSome', function(){
				$(this).parent().parent().find('.item_bar').hide();
				$(this).text('展开').addClass('showAll').removeClass('showSome');
			});
		}());
	</script>
</body>
</html>