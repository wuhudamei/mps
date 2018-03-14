var urlhistory = window.localStorage.getItem("myhistory") && window.localStorage.getItem("myhistory").split(",") || [];

//如果为首页，清空localStorage
if(window.location.href.indexOf("/index")>-1){
	window.localStorage.removeItem("myhistory");
	urlhistory = [];
	urlhistory.push(window.location.href);
	window.localStorage.setItem("myhistory", urlhistory);
}

//不是刷新,并且不是注册，记录访问历史
if(urlhistory[urlhistory.length-1] != window.location.href){
	
	if(window.location.href.indexOf("im/msgcount") > -1 || window.location.href.indexOf('user/toverifyphone') > -1){
		//如果im未读消息数页面，不记录访问历史
		//如果验证手机号，不记录访问历史
	}else{
		//记录访问历史
		urlhistory.push(window.location.href);
		window.localStorage.setItem("myhistory", urlhistory);
	}
}

var myhistory={
	back:function(){
		var urlhistory = window.localStorage.getItem("myhistory")&&window.localStorage.getItem("myhistory").split(",");
		var varMap = this.getParams(window.location.search);
		var url = urlhistory.pop();//弹出现在的地址
		var flag = false;
		while(varMap && varMap["page"] && varMap["page"] >= 1){
			flag = true;
			url = urlhistory.pop();
			if(url){
				var tempArr = url.split("?");
				varMap = this.getParams("?"+tempArr[1]);
			}else{
				url="/index";
			}
		}
		if(!flag){
			if(!(url.indexOf("system/about-2")>-1)){
				var lastUrl = url;
				url = urlhistory.pop();
				if(!url){
					url="/index";
				}
				//如果注册，直接弹出
				if(url.indexOf("user/reg")>-1){
					url = urlhistory.pop();
					//如果登录
					if(url.indexOf("user/login")>-1){
						url = urlhistory.pop();//直接弹出
						//如果要返回的地址和现在的相同
						if(url==window.location.href){
							url = urlhistory.pop();//直接弹出
						}
					}
				}
				//如果签到，直接弹出
				if(url.indexOf("job/checkininfo")>-1){
					url = urlhistory.pop();
					//如果要返回的地址和现在的相同
					if(url==window.location.href){
						url = urlhistory.pop();//直接弹出
					}
				}
				//TODO
				//如果签到，直接弹出
				if(!(lastUrl.indexOf("user/")>-1)){
					if(url.indexOf("user/editresume")>-1){//改为简历填写界面,
						url = urlhistory.pop();
						//如果要返回的地址和现在的相同
						if(url==window.location.href){
							url = urlhistory.pop();//直接弹出
						}
						if(url.indexOf("job/jobsure")>-1){//改为报名页面
							url = urlhistory.pop();//直接弹出
							//如果要返回的地址和现在的相同
							if(url==window.location.href){
								url = urlhistory.pop();//直接弹出
							}
						}
					}
				}
				if($.cookie("tl_m_token")&&url.indexOf("user/login")!=-1)
				{
					url = urlhistory.pop();
				}
			}else{
				url = urlhistory.pop();
				if(!url){
					url="/index";
				}
			}
		}
		window.localStorage.setItem("myhistory", urlhistory);
		
		window.location.href = url;//直接返回上一个页面
	},
	getParams:function(search){
		var map={};//自定义map
		if(search){
			// 写入数据字典 
			var tmparray = search.substr(1,search.length).split("&"); 
			if(tmparray != null) 
			{ 
				for(var i = 0;i<tmparray.length;i++) { 
					var reg = /[=|^==]/; // 用=进行拆分，但不包括== 
					var set1 = tmparray[i].replace(reg,'&');
					var tmpStr2 = set1.split('&');
					map[tmpStr2[0]] = tmpStr2[1];
				}
			}
		}
		
		return map;// 将参数map进行返回 
	}
};

//页面关闭操作
window.onbeforeunload = function() {
	if (event.clientX <= 0 && event.clientY < 0) {
		//alert("你关闭了浏览器");
		//删除访问历史
		window.localStorage.removeItem("myhistory");
	} else {//刷新页面无操作
		//alert("你正在刷新页面");
	}
};
/*var myhistory={
	back:function(){
		var varMap = this.getParams(window.location.search);
		if(varMap && varMap["page"]){
			if(varMap["page"] > 1){
				var n = varMap["page"];
				history.go(-n);//返回上n个页面
				return;
			}
		}
		
		history.back(-1);//直接返回上一个页面
	},
	getParams:function(search){
		var map={};//自定义map
		if(search){
			// 写入数据字典 
			var tmparray = search.substr(1,search.length).split("&"); 
			if(tmparray != null) 
			{ 
				for(var i = 0;i<tmparray.length;i++) { 
					var reg = /[=|^==]/; // 用=进行拆分，但不包括== 
					var set1 = tmparray[i].replace(reg,'&');
					var tmpStr2 = set1.split('&');
					map[tmpStr2[0]] = tmpStr2[1];
				}
			}
		}
		
		return map;// 将参数map进行返回 
	}
};*/

/*myhistory.back = function(){
	history.back();
}

//自定义的方法，用户返回控制
Function.prototype.myback = function(o){
     //var fn = this;
     return function(){
         //ret = fn.apply(this, arguments);
    	 o.apply(this, arguments);
    	 return;
    };
};

myhistory.back = myhistory.back.myback(function(){
	var vars = window.location.search;
});*/

