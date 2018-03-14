;(function(){
	$('.g_task_query nav.task_nav a.nav_a').each(function(index, val){
		$(val).click(function(){
			if ($(this).hasClass('active')) {
				$(this).removeClass('active')
				$(this).find('img').attr('src','../images/down_btn.png');
				$('.g_task_query nav.task_nav .options').addClass('undis');
			}else{
				$('.g_task_query nav.task_nav a.active').removeClass('active');
				$('.g_task_query nav.task_nav a.nav_a img').attr('src','../images/down_btn.png');
				$(val).addClass('active');
				$('.g_task_query nav.task_nav a.active img').attr('src','../images/down_btn_active.png');
				$('.g_task_query nav.task_nav .options').addClass('undis');
				$('.g_task_query nav.task_nav .options').eq(index).removeClass('undis');
			}
			
		});
	});
	$('.g_task_query nav.task_nav .options a').each(function(index, val){
		$(val).click(function(){
			// console.log($(val).text());
			$('.g_task_query nav.task_nav a.active span').text($(val).text());
			$('.g_task_query nav.task_nav a img').attr('src','../images/down_btn.png');
			$('.g_task_query nav.task_nav .options').addClass('undis');
		});
	});
}());