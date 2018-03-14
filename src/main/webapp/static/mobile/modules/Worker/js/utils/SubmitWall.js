$(".wall_title span").on("click", function () {
	$(this).addClass("wall_bj").siblings("span").removeClass("wall_bj");
	var idx = $(this).index();
	$(".box_DQ").eq(idx).show().siblings(".box_DQ").hide();
})
$(".pic_big").on("click", function (e) {
	$("#black").show();
	var mo = function (e) {
		e.preventDefault();
	};
	document.body.style.overflow = 'hidden';
	document.addEventListener("touchmove", mo, false); //禁止页面滑动
})
$("#black").on("click", function () {
	$("#black").hide();
	document.body.style.overflow = 'auto';
})