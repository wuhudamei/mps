;(function(){
	$(document).on('click', '#user_login_btn', function(){
		var username = $('#username').val();
		var password = $('#password').val();
		var url = basePath + '/login';
		/*$.ajax({
			type: "POST",
			url: basePath + "some.php",
			data: { name: username, password: password }
		}).done(function( result ) {
			if (result.status === 0) {
				window.location.href = url; // basePath + '/home';
			}else if(result.status === 1){
				alert(result.msg);
			}
		    alert( "Data Saved: " + msg );
		}).fail(function(err){ console.log(err) });*/
	});
}());

// result
/*{
	status:0, //0.成功， 1帐号密码失败， 2权限失败
	msg:"用户不是项目经理，无法登录", //帐号或密码错误
}*/