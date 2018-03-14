var Logo = {
    "init": function () {
        var that = this;
        that.addEven();
    },
    "addEven": function () {
        /*获取验证码*/
        /*$("#info").on("click", function () {
            var i = 6;
            var x = setInterval(function () {
                $('#info').text(i + " s");
                $('#info').addClass("bj")
                i -= 1;
                if (i < 0) {
                    clearTimeout(x)
                    $('#info').text("获取验证码");
                    $('#info').removeClass("bj")
                }
            }, 1000)

        })*/
        /*错误弹层*/
      /*  $(".btn").on("click", function () {
            function show(){
                $(".worry").show()
            }
            show()
            setTimeout(function(){
                $(".worry").hide()
            },3000)
        })*/
    }
}
Logo.init()