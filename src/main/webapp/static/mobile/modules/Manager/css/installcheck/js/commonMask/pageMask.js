'use strict';
var pageMask={
    pageMaskC:function () {
            var pageM='<div class="changeReason_mask" id="maskPage"></div>';
            $('body').append(pageM);

    },
    pageMasknone:function (boxCont) {
        var boxCont=$(boxCont);
        $(boxCont).remove();
    },
    crease:function () {
        $('.comeC').css('display','block');
        $('.comeC .close').on('click',function () {
            $(this).parent().css('display','none');
            pageMask.pageMasknone('.changeReason_mask');
        })

    },
    contentInfo:function (str,saveBtn) {
        var saveBtn=$(saveBtn);
        var textCont=`<div class="tipCont">
	<h5 class="tc">提示</h5>
	<p class="contentInfo"></p>
	<div class="tipBtn">
		<button class="markSure">确认</button>
		<button class="active cancelBtn">取消</button>
	</div>
</div>`;
        saveBtn.on('click',function () {
            $('body').append(textCont);
            pageMask.pageMaskC();
            $('.contentInfo').text(str);
        })
    },
    contentSure:function () {
        // var markSureBtn=$(markSureBtn);
        // var cancelBtn=$(cancelBtn);
        $('body').on('click','.markSure',function (event) {
            pageMask.pageMasknone('.changeReason_mask');
            pageMask.pageMasknone('.tipCont');

        })
        $(document).on('click','.cancelBtn',function (event) {
            pageMask.pageMasknone('.changeReason_mask');
            pageMask.pageMasknone('.tipCont');

        })
    }

}
