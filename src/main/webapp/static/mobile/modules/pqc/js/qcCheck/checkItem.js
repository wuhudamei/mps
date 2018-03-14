
function findText(){

    var searchText = $('.search-box').val();
    if(null==searchText || searchText==""){
        clearSelection();
        $('.clearfix').show();
        $('.item_bar').hide();
        $('.showSome').text('展开').addClass('showAll').removeClass('showSome');
        return false;
    }

    /* var reg=/[^\u4E00-\u9FA5]/;

     if(reg.test(searchText)){
     $('.search-box').val("");
     return false;
     } */

    $('.clearfix').hide();
    clearSelection();
    var regExp = new RegExp(searchText, 'g');
    /* $('p label').each(function()//遍历文章；
     {
     var html = $(this).html();
     var newHtml = html.replace(regExp, '<span class="highlight">'+searchText+'</span>');//将找到的关键字替换，加上highlight属性；

     $(this).html(newHtml);//更新文章；
     }); */
    $('.item_bar label').each(function()//遍历文章；
    {
        var html = $(this).html();
        var newHtml = html.replace(regExp, '<span class="highlight">'+searchText+'</span>');//将找到的关键字替换，加上highlight属性；

        $(this).html(newHtml);//更新文章；
    });

    $(".highlight").parent().parent().parent().parent().show();
    $(".highlight").parent().parent().parent().show();
    $('.showAll').text('折叠').addClass('showSome').removeClass('showAll');
}

function clearSelection(){
    /*  $('p label').each(function()//遍历
     {
     $(this).find('.highlight').each(function()//找到所有highlight属性的元素；
     {
     $(this).replaceWith($(this).html());//将他们的属性去掉；
     });
     }); */

    $('label').each(function()//遍历
    {
        $(this).find('.highlight').each(function()//找到所有highlight属性的元素；
        {
            $(this).replaceWith($(this).html());//将他们的属性去掉；
        });
    });
}


$(document).ready(function() {
    //绑定onclick事件
    $("#submit").bind('click',saveItem);
});



//如果是必选, 增加点击事件  不停地加check
function addCheckClass(obj){
    $(obj).addClass("class","checked");


}

function sure(){


    $("#timeAlert").hide()
}



function saveItem(){


    $.get(basePqcUrl+"/checkItem/checkTimeIsAllowed", {inspectId: inspectId}, function (data) {

        if (-1 != data && 0 != data) {

            $("#firstText").text("同一个订单两次报告提交操作时间必须间隔5分钟，请过" + data + "分钟后再申请")
            $("#timeAlert").show();
            return;
        } else if (data == 0) {

            $("#firstText").text("该约检单已经提交检查报告， 请勿重复提交")
            $("#timeAlert").show();
            $(".maskBtn").bind("click",toList);
            return;
        } else {

            $("#submit").css("color","#CCC")
            $("#submit").unbind("click");
            var checkItemIds = $(".checked").prev();

            for(var v =0;v<checkItemIds.length;v++){
                if($(checkItemIds[v]).val() =="build"){


                }else{
                    $(checkItemIds[v]).attr("name","checkItemId");
                    var checkItemName =$(checkItemIds[v]).prev();
                    $(checkItemName).attr("name","checkItemName");
                }
            }


            $("#itemForm").submit();


        }

    })










}


var arrObj = {};
var bindClick = function (liId, selArr){
    $(document).on('click', '#'+liId+' ul label', function(){

        if($(this).attr('class') === 'checked'){
            // unselect
            $(this).attr('class', '');
            $(this).parent().removeClass('bg_light');
            $('#'+liId+' p label').attr('class','');
            $('#'+liId+' p').removeClass('bg_light');
        }else{
            // select
            $(this).parent().addClass('bg_light');
            $(this).attr('class', 'checked');
            var isAllTrue = '';
            $.each($(this).parents('#'+liId).find('li label'), function(i, v){
                if ($(v).attr('class') === "checked") {
                    isAllTrue += '1';
                }else{
                    isAllTrue += '0';
                }
            });
            if (isAllTrue.indexOf('0') > -1) {
                $('#'+liId+' p label').attr('class','');
                $('#'+liId+' p').removeClass('bg_light');
            }else{
                $('#'+liId+' p label').attr('class','checked');
                $('#'+liId+' p').addClass('bg_light');
            };
        }
        var theitem = liId;
        selArr.length = 0;
        $.each($('#'+liId+' ul label'), function(i, v){
            if ($(v).attr('class') === 'checked') {
                selArr.push($(v).parent().find('input').val());
            }
        });
        console.log(arrObj);
    });

    $(document).on('click', '#'+liId+' p label', function(){


        var b = $(this).parent().parent().find('label').attr('class');
        if (b === 'checked') {

            $(this).parent().parent().find('label').attr('class', '');
            $('#'+liId+' ul label').attr('class', '');
            selArr.length = 0;
            $(this).parent().removeClass('bg_light');
            $(this).parent().parent().find('li').removeClass('bg_light');

            //必选不让取消
            $("label[name*=bixuan]").removeAttr("class");
            $("label[name*=bixuan]").attr('class','checked');

        }else{

            $(this).parent().addClass('bg_light');
            $(this).parent().parent().find('li').addClass('bg_light');
            $(this).parent().parent().find('label').attr('class', 'checked');
            $('#'+liId+' ul label').attr('class', 'checked');
            $.each($('#'+liId+' ul label'), function(i, v){
                selArr.push($(v).parent().find('input').val());
            });







        }
        console.log(arrObj);
    });
}
$(function(){
    for (var i = 0, len = $('#container-ul > li').size(); i < len; i++) {
        var liId = $('#container-ul > li').eq(i).attr('id');
        arrObj['selArrli'+i]=[];
        bindClick(liId, arrObj['selArrli'+i]);


    }

    //以事件委托的形式，绑定元素点击事件
    $(document).on('click', '.showAll', function(){
        $(this).parent().parent().find('.item_bar').show();
        $(this).text('折叠').addClass('showSome').removeClass('showAll');
    });
    $(document).on('click', '.showSome', function(){
        $(this).parent().parent().find('.item_bar').hide();
        $(this).text('展开').addClass('showAll').removeClass('showSome');
    });
}());
