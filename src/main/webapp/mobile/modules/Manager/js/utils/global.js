// 防止命名冲突，定义了globalUtil对象
var globalUtil = {};

globalUtil.fn = {
	//弹框Toast
	alert: function(str, time){
		time = time || 1.5;
		var alertDialog = '<div id="g-alertDialog" class="">'+
							'<div class="content">'+
								'<span>'+ str +'</span>'+
							'</div>'+
						'</div>';
		$('body').append(alertDialog);
		setTimeout("globalUtil.fn.hideAlert()",time*1000);
	},
	hideAlert: function(){
		$('#g-alertDialog').remove();
	},
	showMask: function(){
		var mask = '<div class="g-mask"></div>';
		$('body').append(mask);
	},
	hideMask: function(){
		$('.g-mask').remove();
	}
}


