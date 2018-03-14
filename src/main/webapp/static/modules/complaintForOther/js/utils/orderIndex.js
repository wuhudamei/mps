var order = {
    "init": function () {
        var self = this;
        self.bindEvent()
    },
    "bindEvent": function () {
        //下拉选项

        $(".select span").on("click", function () {
            var idx = $(this).index();
            /*console.log(idx)*/
            var showEv = $(".sel .sel_up").eq(idx)
            if (showEv.is(":hidden")) {
                showEv.show().siblings(".sel .sel_up").hide();
            } else {
                showEv.hide()
            };
            selectText('.state p', "#state");
            selectText('.sort p', "#sort");
            selectText('.package p', "#package");
        })

        function selectText(even, Target) {
            $(even).on("click", function () {
                var sel_tex = $(this).text(),
                    tex = $(Target);
                tex.text(sel_tex);
                $(this).addClass("sel_bj").siblings().removeClass("sel_bj");
                $(".sel_up").hide()
            })
        }

    }
}
order.init();