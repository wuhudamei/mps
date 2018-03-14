$(function(){
	$(document).on('click', '.reduce', function(){
		var num = parseInt($(this).parent().find('span').text());
		if(num > 0){
			num --;
			$(this).parent().find('span').text(num);
		}else{
			console.log('不能再少了哦')
		}
	});
	$(document).on('click', '.plus', function(){
		var num = parseInt($(this).parent().find('span').text());
		if(num < 101){
			num ++;
			$(this).parent().find('span').text(num);
		}else{
			console.log('您最多购买100个')
		}
	});
}());