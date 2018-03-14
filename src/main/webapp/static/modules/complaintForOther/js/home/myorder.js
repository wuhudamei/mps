;(function(){
	$('.myorder nav.order_nav a.nav_a').each(function(index, val){
		$(val).click(function(){
			if ($(this).hasClass('active')) {
				globalUtil.fn.hideMask();
				$(this).removeClass('active');
				$(this).find('img').attr('src','../images/down_btn.png');
				$('.myorder nav.order_nav .options').addClass('undis');
			}else{
				globalUtil.fn.showMask();
				$('.g-mask').on('click',function(){
					globalUtil.fn.hideMask();
					$(this).removeClass('active');
					$(this).find('img').attr('src','../images/down_btn.png');
					$('.myorder nav.order_nav .options').addClass('undis');
				});
				$('.myorder nav.order_nav a.active').removeClass('active');
				$('.myorder nav.order_nav a.nav_a img').attr('src','../images/down_btn.png');
				$(val).addClass('active');
				$('.myorder nav.order_nav a.active img').attr('src','../images/down_btn_active.png');
				$('.myorder nav.order_nav .options').addClass('undis');
				$('.myorder nav.order_nav .options').eq(index).removeClass('undis');
			}
			
		});
	});
	$('.myorder nav.order_nav .options a').each(function(index, val){
		$(val).click(function(){
			// console.log($(val).text());
			globalUtil.fn.hideMask();
			$('.myorder nav.order_nav a.active span').text($(val).text());
			$('.myorder nav.order_nav a.active').addClass('selected').removeClass('active');
			$('.myorder nav.order_nav a img').attr('src','../images/down_btn.png');
			$('.myorder nav.order_nav .options').addClass('undis');
		});
	});
}());