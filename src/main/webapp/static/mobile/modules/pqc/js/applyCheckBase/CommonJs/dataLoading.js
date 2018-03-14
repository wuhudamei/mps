function run_waitMe(text) {
    $('body').waitMe({
        effect: 'win8',
        text: text,
        bg: 'rgba(255,255,255,0.7)',
        color: '#000',
        sizeW: '',
        sizeH: '',
        source: 'img.svg'
    });
}


function showAlert(text){
    $(".maskContent").text(text);

    $(".alertMask").show();



}

$(function(){


    $(".maskBtn").click(function(){
        hideAlert();


    })



})
function hideAlert(){
    $(".maskContent").text("");
    $(".alertMask").hide();

}


function globelAlert(text,time){

    globalUtil.fn.alert(text,time);

}

function setTimeOutTurnHref(href,timeOutTime){

 setTimeout('window.location.href='+href,timeOutTime);

}

function  closeWaitMe(element){
    $(element).waitMe('hide');

}