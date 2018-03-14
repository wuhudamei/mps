<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1,maximum-scale=1, user-scalable=no">
<meta content="yes" name="apple-mobile-web-app-capable">
<meta content="black" name="apple-mobile-web-app-status-bar-style">
<meta content="telephone=no" name="format-detection">
<meta content="spanail=no" name="format-detection">
<title>申请复检</title>
<link rel="stylesheet" type="text/css"
	href="${ctxStatic}/mobile/modules/Manager/css/reset.css" />
<link rel="stylesheet" type="text/css"
	href="${ctxStatic}/mobile/modules/Manager/css/header.css" />
<link rel="stylesheet" type="text/css"
	href="${ctxStatic}/mobile/modules/Manager/css/lib/lCalendar.css" />
<link rel="stylesheet" type="text/css"
	href="${ctxStatic}/mobile/modules/pqc/css/globalUtil2.css" />
<link rel="stylesheet" type="text/css"
	href="${ctxStatic}/mobile/modules/Manager/css/materi2.css" />
</head>

<body>
	<div class="g-materi">
		<header>
			<a class="back_btn" onclick="history.go(-1)"
				href="javascript:void(0)"></a>
			<h2 class="title">申请复检</h2>
		</header>
		<!-- /header -->
		
		<form id="jvForm" >
		<input  type="text" id="num" hidden="hidden" name="num"/>
		<input  type="text"  hidden="hidden" name="recheckId" value="${recheckId }">
		<div class="zone shadow">
			<div class="funs">
				<span class="col">期望验收日期</span>
				<p class="align_rgt">
					<input id="txtBeginDate" class="goods_date" type="text"
						name="input_date" readonly="readonly"
						placeholder="请输入日期" data-lcalendar="2000-01-01,2000-01-01" />
				</p>
			</div>
			<div class="funs">
				<span class="col">上传整改后照片</span>
				<div class="up">
					<img src="${ctxStatic}/mobile/modules/pqc/images/pic_load.png"
						alt="上传照片"> <input type="file" accept="image/*"
						 onchange="preview(this)">
				</div>
				
				
				<a class="wtc_btn" href="javascript:void(0)">已上传<span id="picLength">0</span>张
				</a>
			</div>
		</div>	

		<ul class="show_sec">
			<c:forEach items="${list }" var="checkItem">
				<li class="content"><span> <c:if
							test="${checkItem.changeWay =='1'}">【线下】</c:if> <c:if
							test="${checkItem.changeWay =='0'}">【线上 】</c:if>
				</span>${checkItem.checkItemName } <c:if test="${checkItem.isPassed =='1'}">
						<em class="qual">合格</em>
					</c:if> <c:if test="${checkItem.isPassed =='0'}">
						<em class="qual">不合格</em>
					</c:if></li>

			</c:forEach>
		</ul>
		</form>
		<footer class="sub_content">
			<a class="sub_btn" href="javascript:void(0)" id="submit">提交复检申请</a>
		</footer>
		<!-- 照片弹出层 -->
		<div class="mask mask1 undis">
			<div class="pic_container clearfix" id="picNUMS">
				<%-- <div class="pic_wraper clearfix">
					<img src="${ctxStatic}/mobile/modules/Manager/images/photo.png"
						alt="收货单"> <a class="del_btn" href="javascript:void(0)">删除</a>
				</div> --%>

			</div>
			<a class="back" href="javascript:void(0)">返回</a>
		</div>
		<!-- 查看大图弹出层 -->
		<div class="mask mask2 undis">
			<div class="big_pic_wraper">
				<img class="big_pic"
					src="${ctxStatic}/mobile/modules/Manager/images/photo.png"
					alt="收货单" id="big_pic">
			</div>
		</div>
	</div>

	<div style="background:rgba(0,0,0,.4);z-index:9;" class="g-mask undis" id ="subReport">
		<div id="g-done_ask">
			<p class="first" style="margin-left:0">确认要申请复检吗？</p>
			<p class="second"  style="margin-left:0">
				<a href="#" onclick="hideSubmitAlert()">取消</a>
				<a href="#" onclick="submitData()">确认</a>
			</p>
		</div>
	</div>
</body>
	<script type="text/javascript"
		src="${ctxStatic}/mobile/modules/Manager/js/lib/jquery-2.1.1.min.js"></script>
	<script type="text/javascript"
		src="${ctxStatic}/mobile/modules/Manager/js/utils/calcRootFontSize.js"></script>
	<script type="text/javascript"
		src="${ctxStatic}/mobile/modules/Manager/js/utils/history.js"></script>
	<script type="text/javascript"
		src="${ctxStatic}/mobile/modules/Manager/js/utils/global.js"></script>
	<script type="text/javascript"
		src="${ctxStatic}/mobile/modules/Manager/js/lib/lCalendar.js"></script>
	<script type="text/javascript"
		src="${ctxStatic}/mobile/modules/Manager/js/utils/shopcount.js"></script>
		<script type="text/javascript" src="${ctxStatic}/mobile/modules/pqc/js/home/compresspic.js"></script>
	<script type="text/javascript" src="${ctxStatic}/mobile/modules/Worker/js/lib/jquery.form.js"></script>
		
	<script>


        function alertReport(){

            $("#subReport").show();
        }

        function hideSubmitAlert(){

            $("#subReport").hide();
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
			var now = new Date(),start = new Date(now.setDate(now.getDate()+1)), start = globalUtil.fn.formatDate(now,
					'yyyy-MM-dd'), end = new Date(now.setFullYear(now
					.getFullYear() + 1)), end = globalUtil.fn.formatDate(end,
					'yyyy-MM-dd');
			$("#txtBeginDate").val(start);
			var lcalendar = start + ',' + end;
			// 将时间限制加到input标签上。
			$('#txtBeginDate').attr('data-lcalendar', lcalendar);
			var calendar = new lCalendar();
			calendar.init({
				'trigger' : '#txtBeginDate',//标签id
				'type' : 'date'//date 调出日期选择 datetime 调出日期时间选择 time 调出时间选择
			});
		}());

		//图片上传显示 
		function preview(file) {
			var prevDiv = $('.camera');
			if (file.files && file.files[0]) {
				var reader = new FileReader();
				reader.onload = function(evt) {
					$(
							'<div class="pic_wraper"><img src="'
									+ evt.target.result
									+ '" onload="compresspic(this,uploadpic);"/><a class="del_btn" href="javascript:void(0)">删除</a></div>')
							.appendTo('.pic_container');
				}
				reader.readAsDataURL(file.files[0]);
			} else {
				prevDiv.innerHTML = '<div class="img" style="filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src=\'' + file.value + '\'"></div>';
				var pic = '<div class="img" style="filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src=\'' + file.value + '\'"></div>';
				console.log(file, file.value);
			}
		}
		$(document).on('click', '.del_btn', function() {
			var num = $("#num").val();
			num--;
			$("#num").val(num);
			$(this).parent().remove();
			globalUtil.fn.alert('删除图片操作成功',2.0);
			var count = $("#picNUMS > div ").size();
			$("#picLength").text(count);
		});
		function uploadpic(pic) {

			var hiddenForm = document.getElementById("jvForm");
			var input = document.createElement("input");

			var num = $("#num").val();

			if (pic) {
				num++;
				input.setAttribute("id", "num" + num);
				input.setAttribute('hidden', 'hidden');
				input.setAttribute('name', 'photo');
				input.setAttribute("type", "text");
				input.setAttribute("value", pic);
				hiddenForm.appendChild(input);
				$("#num").val(num);
			}
			
			var count = $("#picNUMS > div ").size();
			$("#picLength").text(count);
			
		}
		
		
		$(document).ready(function() {
			//绑定onclick事件
			$("#submit").bind('click',alertReport);
		});
		function submitData(){
            $("#subReport").hide();

					$("#submit").css("color","#CCC");
					$("#submit").unbind("click");
					var options ={
							url: "${ctx}/app/manager/recheck/applyRecheck",
							type: "post",
								success:function(data){
								if(data=="OK"){
									globalUtil.fn.alert('操作成功',2.0);			
									window.location.href="${ctx}/app/manager/recheck/recheckList";
								}else if(data=='repeat'){

                                    globalUtil.fn.alert('您已经提交过复检了,请不要重复提交',2.0);

                                }
							}
					}
					$("#jvForm").ajaxSubmit(options);
		}
	</script>
</html>