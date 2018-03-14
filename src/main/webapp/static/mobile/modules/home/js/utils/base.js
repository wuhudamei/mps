$(function () {
//    基础的js
//    1.点击文字消失,失去焦点判断,这里主要用于输入框的体验
    var bxyBaseJs = {
        // 声明变量,储存文本提示信息,形参是输入框的父盒子,
        inputTipiInfo: function (TextBox) {
            var defaultText = '';
            var outInfo='';
            var TextBox = $(TextBox);
            TextBox.find('input').on('click', function () {
                defaultText =$(this).attr('placeholder');
                $(this).attr('placeholder','');
                $(this).on('blur',function () {
                    outInfo=$(this).val();
                    if(outInfo==''){
                        $(this).attr('placeholder',defaultText);
                    }else {
                        //这里是用户输入信息之后进行的操作
                    }
                })
            });
        },
        getResh:function () {
            $('.getRefash')
        }
    }
bxyBaseJs.inputTipiInfo('.tab');
})