<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1,maximum-scale=1, user-scalable=no">
<meta content="yes" name="apple-mobile-web-app-capable">
<meta content="black" name="apple-mobile-web-app-status-bar-style">
<meta content="telephone=no" name="format-detection">
<meta content="email=no" name="format-detection">
<title>复检</title>
<link rel="stylesheet" type="text/css"
	href="${ctxStatic}/mobile/modules/pqc/css/reset.css" />
<link rel="stylesheet" type="text/css"
	href="${ctxStatic}/mobile/modules/pqc/css/header.css" />
<link rel="stylesheet" type="text/css"
	href="${ctxStatic}/mobile/modules/pqc/css/lib/lCalendar.css" />
<link rel="stylesheet" type="text/css"
	href="${ctxStatic}/mobile/modules/pqc/css/globalUtil2.css" />
<link rel="stylesheet" type="text/css"
	href="${ctxStatic}/mobile/modules/pqc/css/quality_check.css" />
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/pqc/css/photo.css"/>
	<link type="text/css" rel="stylesheet" 	href="${ctxStatic}/mobile/modules/Manager/css/waitMe.css" >
	<style>
	    .tit-pos {
		    line-height: .68rem;
		    text-align: center;
		    margin-bottom: .24rem;
		    color: #0780ec;
		}
		.font30 {font-size: .3rem;}
	</style>
</head>
<body>
	<form id="jvForm">
		<div class="g-qua_check">
			<header>
				<a class="back_btn" onclick="history.go(-1)"></a>
				<h2 class="title">复检</h2>
				<a class="change wtc_btn2">查看整改照片</a>
			</header>
			<!-- /header -->


			<input type="text" id="num" hidden="hidden" name="num" /> <input
				type="text" hidden="hidden" name="recheckId" value="${recheckId }">
			<section class="qua_check">
				<p class="font30 tit-pos">${customerInfo}</p>
				<div class="funs">
					<span class="col3">上传照片</span>
					<div class="up">
						<img src="${ctxStatic}/mobile/modules/pqc/images/pic_load.png"
							alt="上传照片"> <input type="file" accept="image/*"
							 onchange="preview(this)">
					</div>
					<input type="text" hidden="hidden" id="shit" value="1">
					<a class="wtc_btn" href="javascript:void(0)">已拍<span
						id="picLength">0</span>张
					</a>
				</div>
				<ul class="item_lists">

					<c:forEach items="${checkItemList }" var="checkItem"
						varStatus="status">
						<li class="mar_left3 pad_top3 pad_rgt2 bor_btm clearfix"><input
							type="text" hidden="hidden" name="checkItemId"
							value="${checkItem.checkItemId }" /> <c:if
								test="${checkItem.changeWay !='0'}">
								<p class="p_left font28">【线下】${checkItem.checkItemName }</p>
							</c:if> <c:if test="${checkItem.changeWay =='0'}">
								<p class="p_left font28">【线上】${checkItem.checkItemName }</p>
							</c:if>

							<p class="p_right">
								<span class="radio_span marrgt1"> <!-- 合格就默认合格, 不可更改 -->
									<c:if test="${checkItem.isPassed=='1' }">
										<input type="radio" id="yes${status.index+1 }">
										<input type="text" hidden="hidden" name="isPassed" value="1" />
										<label class="font26 checked4"
											data-name="${checkItem.checkItemId }" class="checked">合格</label></span>
								<span class="radio_span"> <input type="radio"
										id="no${status.index+1 }"> <input type="text"
										hidden="hidden" name="isPassed" value="0" /> 
										<label class="font26 checked3" data-name="${checkItem.checkItemId }"
										for="no${status.index+1 }">不合格</label>
									</span>

								</c:if>


								<!-- 原本不合格 , 就默认合格, 但质检员可以修改 -->
								<c:if test="${checkItem.isPassed=='0' }">

									<input type="radio" id="yes${status.index+1 }">
									<input type="text" hidden="hidden" name="isPassed" value="1" />
									<label class="font26 checked"
										data-name="${checkItem.checkItemId }">合格</label>

									</span>
									<span class="radio_span"> <input type="radio"
										id="no${status.index+1 }"> <input type="text"
										hidden="hidden" name="isPassed" value="0" /> <label
										class="font26" data-name="${checkItem.checkItemId }"
										for="no${status.index+1 }">不合格</label>
									</span>

								</c:if>




							</p></li>
					</c:forEach>

				</ul>
			</section>
			<footer>
				<a class="repeat" href="javascript:void(0)" id="submit"><span
					class="btn font28">提交复检</span></a>
			</footer>
			<!-- 照片弹出层 -->
			<div class="mask mask1 undis">
				<div class="pic_container clearfix" id="picNUMS">
					<%-- 
			<c:forEach items="${recheckPicList }" var="picList">
				<div class="pic_wraper clearfix">
					<img src="${baseUrl}${picList.picUrl }" alt="复检照片">
					<a class="del_btn" href="javascript:void(0)" onclick="deletePic('${picList.picId}')">删除</a>
					</div>
				</c:forEach> --%>


				</div>
				<a class="back" href="javascript:void(0)">返回</a>
			</div>
			
			<!-- 查看大图弹出层 -->
			<div class="mask mask undis">
				<div class="big_pic_wraper">
					<img class="big_pic" id="big_pic" alt="质检照片">
				</div>
			</div>
			
			
			<!-- 照片弹出层2 -->
				<div class="mask mask undis" id="picDiv">
				<div class="pic_container clearfix">
				<c:forEach items="${recheckPicList }" var="p">
					<div class="pic_wraper clearfix">
						<img src="${baseUrl}${p.picUrl }" height="1235" width="750" alt="">
					</div>
				</c:forEach>
				<a class="back" href="javascript:void(0)" id="back">返回</a>
			</div>
			
			<!-- 查看大图弹出层 -->
			<div class="mask mask undis" id="datu">
				<div class="big_pic_wraper">
					<img class="big_pic2" id="big_pic2" alt="质检照片">
				</div>
			</div>
		</div>
	</form>
	 <div style="background:rgba(0,0,0,.4);z-index:9;" class="g-mask undis" id ="subReport">
			<div id="g-done_ask">
				<p class="first">尊敬的美得你质检员,确定提交报告吗?</p>
				<p class="second">
					
					<a href="#" onclick="hideSubmitAlert()">取消</a>
					<a href="#" onclick="submitData()" id="formButtonId">确认</a>
				</p>
			</div> 
	</div>
	<script type="text/javascript"
		src="${ctxStatic}/mobile/modules/pqc/js/lib/jquery-2.1.1.min.js"></script>
	<script type="text/javascript"
		src="${ctxStatic}/mobile/modules/pqc/js/utils/calcRootFontSize.js"></script>
	<script type="text/javascript"
		src="${ctxStatic}/mobile/modules/pqc/js/utils/history.js"></script>
	<script type="text/javascript"
		src="${ctxStatic}/mobile/modules/pqc/js/utils/global.js"></script>
	<script type="text/javascript"
		src="${ctxStatic}/mobile/modules/Manager/js/utils/shopcount.js"></script>
	<script type="text/javascript"
		src="${ctxStatic}/mobile/modules/pqc/js/home/compresspic.js"></script>
	<script type="text/javascript"
		src="${ctxStatic}/mobile/modules/Worker/js/lib/jquery.form.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/pqc/js/home/swiper.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/pqc/js/lib/swiper-3.3.1.jquery.min.js"></script>
	<script type="text/javascript"
		src="${ctxStatic}/mobile/modules/Manager/js/utils/waitMe.js"></script>
	<script>
	//蒙层效果
	/* function run_waitMe(effect,text){
		$('body').waitMe({
			effect: effect,
			text: text,
			bg: 'rgba(255,255,255,0.7)',
			color:'#000',
			sizeW:'',
			sizeH:'',
			source: 'img.svg'
		});
	} */
	function hideSubmitAlert(){
		$("#subReport").hide();
	}
		function buhege(obj){
			
			$(obj).removeClass("checked");
			
		}
	
	
		$(function() {
			
			$(document).on('click', '.wtc_btn', function() {

				$('.mask1').removeClass('undis');
			});
			$(document).on('click', '.pic_wraper img', function() {
				var purl = $(this).attr("src");
				$("#big_pic").attr("src", purl);
				$('.mask2').removeClass('undis');
			});
			$(document).on('click', '.mask2', function() {
				$('.mask2').addClass('undis');
			});
			$(document).on('click', '.back', function() {
				$('.mask1').addClass('undis');
			});
			
			
			
			
			$(document).on('click', '.wtc_btn2', function() {

				$('#picDiv').removeClass('undis');
			});
			$(document).on('click', '#picDiv img', function() {
				var purl = $(this).attr("src");
				$("#big_pic2").attr("src", purl);
				$('#datu').removeClass('undis');
			});
			$(document).on('click', '#datu', function() {
				$('#datu').addClass('undis');
			});
			$(document).on('click', '#back', function() {
				$('#picDiv').addClass('undis');
			});

			$('.qua_check label').click(
					function() {
						if($(this).hasClass("checked3") || $(this).hasClass("checked4")){
						
							return;
						}
						var thisName = $(this).attr('data-name');
						$('label[data-name="' + thisName + '"]').removeAttr(
								'class')
								&& $(this).attr('class', 'checked');
					});
			$('.p_right label').click(
					function() {
						if($(this).hasClass("checked3") || $(this).hasClass("checked4")){
							
							return;
						}
						var thisName = $(this).attr('data-name');
						$('label[data-name="' + thisName + '"]').removeAttr(
								'class')
								&& $(this).attr('class', 'checked');
					});
			// 复选框
			$(document).on('click', 'label.checkbox', function() {
				if ($(this).hasClass('checked')) {
					$(this).removeClass('checked');
				} else {
					$(this).addClass('checked');
				}
			});
		});

		//图片上传显示 
		function preview(file) {
			
			var shit = $("#shit").val();
			var prevDiv = $('.camera');
			if (file.files && file.files[0]) {
				var reader = new FileReader();
				reader.onload = function(evt) {
					$(
							'<div class="pic_wraper"><img src="'
									+ evt.target.result
									+ '" onload="compresspic(this,uploadpic)" id="'+shit+'"/><a class="del_btn" href="javascript:void(0)">删除</a></div>')
							.appendTo('#picNUMS');
				}
				reader.readAsDataURL(file.files[0]);
			} else {
				prevDiv.innerHTML = '<div class="img" style="filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src=\'' + file.value + '\'"></div>';
				var pic = '<div class="img" style="filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src=\'' + file.value + '\'"></div>';
				console.log(file, file.value);
			}
			
		}
		$(document).on('click', '.del_btn', function() {
			
			var numReal = $(this).prev().attr("id");
			$(("#num"+numReal)).remove();
			
			var num = $("#num").val();
			num--;
			$("#num").val(num);
			$(this).parent().remove();
			globalUtil.fn.alert('删除图片操作成功', 2.0);
			var count = $("#picNUMS > div ").size();
			$("#picLength").text(count);
		});
		function uploadpic(pic) {
			
			var hiddenForm = document.getElementById("jvForm");
			var input = document.createElement("input");

			var num = $("#num").val();
			var shit = $("#shit").val();

			if (pic) {
				num++;
				input.setAttribute("id", "num" + shit);
				input.setAttribute('hidden', 'hidden');
				input.setAttribute('name', 'photo');
				input.setAttribute("type", "text");
				input.setAttribute("value", pic);
				hiddenForm.appendChild(input);
				$("#num").val(num);
				shit++;
				$("#shit").val(shit);
			}

			var count = $("#picNUMS > div ").size();
			$("#picLength").text(count);

		}
		//提交提示框显示
		function alertReport(){
			$("#subReport").show();
		}
		$(document).ready(function() {
			//绑定onclick事件 弹出提示框
			$("#submit").bind('click', alertReport);

		});
	
		//删除图片
		function deletePic(picId) {
			$.ajax({
				url : "${ctx}/app/pqc/recheck/deletePic?picId=" + picId,
				type : "get",
				async : false,
				success : function(data) {
					if (data == "OK") {
						globalUtil.fn.alert('删除图片操作成功', 2.0);
					} else {

						globalUtil.fn.alert('删除图片失败', 2.0);
					}

				}
			});
		}
	
		//提交方法
		function submitData() {
			//隐藏提示框
			$("#subReport").hide();
			/* run_waitMe("win8","操作中...请稍等"); */
			//没有选择的,就移除name属性, 凡是检查项有的isPassed有的就是1,没有的就是null
			$('.p_right label').each(function() {

				if ($(this).hasClass("checked")) {

				} else {
					var isPassed = $(this).prev();
					$(isPassed).removeAttr("name");
				}
			});
			/* $('body').waitMe('hide'); */
			$("#submit").css("color", "#CCC");
			$("#submit").unbind("click");
			var options = {
				url : "${ctx}/app/pqc/recheck/saveRecheck",
				type : "post",
				success : function(data) {
					if (data == "OK") {
						/* $('body').waitMe('hide'); */
						setInterval(toList())
						window.location.href = "${ctx}/app/pqc/recheck/recheckList";
					} else if (data == "NO") {
						globalUtil.fn.alert('操作失败,请联系开发人员', 3.0);

					}else{

                        globalUtil.fn.alert('您已检查过该复检单,请不要重复提交', 3.0);
                    }
				}
			}
			$("#jvForm").ajaxSubmit(options);
		}

		function toList() {
			
			globalUtil.fn.alert('操作成功', 2.0);
			
		}
	</script>
</body>
</html>