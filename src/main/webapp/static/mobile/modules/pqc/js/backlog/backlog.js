var tab={
	"init":function(){
		var self = this;
		self.bindEvent();
	},
	"bindEvent":function(){
		$(".list").on("click","h2",function(){


			var icon=$(this).find("em");
			var showDom=$(this).next(".todo_list")
			if(icon.hasClass("em_sel")){
				icon.removeClass("em_sel");
				showDom.hide()
			}else{

				icon.addClass("em_sel")
				showDom.show()
			}
		})
	}
}
tab.init();