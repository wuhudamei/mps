$(function () {
    var BxyChange={
        ChangeCut:function (changeCutBtn,showSelect) {
            var changeCutBtn=$(changeCutBtn);
            var showSelect=$(showSelect);
            changeCutBtn.click(function(){
                showSelect.toggle();
                if($('.g-mask').is(':visible')){
                    globalUtil.fn.hideMask();
                }else {
                    globalUtil.fn.showMask();
                }
                showSelect.find('li').on('click',function () {
                    $(this).css('color','#0780ec')
                })
            });

        }
    }
    BxyChange.ChangeCut('.bxySelect','.bxySelect ul')
})