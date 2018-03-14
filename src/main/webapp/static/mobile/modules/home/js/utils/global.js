// 防止命名冲突，定义了globalUtil对象
var globalUtil = {};

globalUtil.fn = {
	showMask: function(){
		var mask ='<div class="g-mask"></div>';
		$('body').append(mask);
	},
	hideMask: function(){
		$('.g-mask').remove();
	},
	alert: function(str, time, cb){
		time = time || 1.5;
		var alertDialog = '<div id="g-alertDialog" class="">'+
							'<div class="mask_content">'+
								'<span>'+ str +'</span>'+
							'</div>'+
						'</div>';
		$('body').append(alertDialog);
		setTimeout("globalUtil.fn.hideAlert("+cb+")",time*1000);
	},
	hideAlert: function(cb){
		if (cb) {
			console.log(cb);
			cb();
		}
		$('#g-alertDialog').remove();
	}
}