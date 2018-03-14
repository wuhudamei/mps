;(function(){
	$(document).ready(function(){
		var photoAll = $('.g-photo .swiper-slide').length;
	   	$('.g-photo h2.title').text(1 + '/' + photoAll);
		var mySwiper = new Swiper ('.swiper-container',{
	    	loop: false,
	    	nextButton: '.swiper-button-next',
    		prevButton: '.swiper-button-prev',
    		onSlideChangeEnd: function(swiper){
    			console.log(swiper.activeIndex);
	   			$('.g-photo h2.title').text(swiper.activeIndex+1 + '/' + photoAll);
			}
	    })
	})
}());