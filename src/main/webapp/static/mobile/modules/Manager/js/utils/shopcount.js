$(function(){
	$(document).on('click', '.reduce', function(){
		var num = parseInt($(this).parent().find('input').val());
		if(num > 0){
			num --;
			$(this).parent().find('input').val(num);
		}else{
			console.log('就不改')
		}
	});
	$(document).on('click', '.plus', function(){
		var num = parseInt($(this).parent().find('input').val());
		if(num < 1000){
			num ++;
			$(this).parent().find('input').val(num);
		}else{
			console.log('您最多购买999个')
		}
	});
}());