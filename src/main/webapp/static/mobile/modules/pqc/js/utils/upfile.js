$(".com_pic .img img").on("click", function () {
    var clone_img = $(this).clone();
    $("#black").html(clone_img);
    $("#black").show();
})
$("#black").on("click", function () {
    $(this).hide();
    $("#black").html();
})
document.querySelector('.upfile').onchange = function (evt) {
    var $ImgEven = $(this).parent().next(".img")
    console.log($ImgEven.text())

    var files = evt.target.files;
    for (var i = 0, f; f = files[i]; i++) {
        if (!f.type.match('image.*')) continue;
        var reader = new FileReader();
        reader.onload = (function (theFile) {
            return function (e) {
                var img = document.createElement('img');
                //var img = new Image();
                img.title = theFile.name;
                img.src = e.target.result;
                img.onload = function () {
                    var that = this;
                    //生成比例
                    var w = that.width,
                        h = that.height,
                        scale = w / h;
                    w = 200 || w; //480  你想压缩到多大，改这里
                    h = w / scale;

                    //生成canvas
                    var canvas = document.createElement('canvas');

                    var ctx = canvas.getContext('2d');

                    $(canvas).attr({
                        width: w,
                        height: h
                    });

                    ctx.drawImage(that, 0, 0, w, h);

                    var base64 = canvas.toDataURL('image/jpeg', 1 || 0.8); //1最清晰，越低越模糊。
                    var img = document.createElement('img');
                    img.src = base64; //alt title
                    img.alt = 1;
                    img.title = newGuid();
                    //$ImgEven.append(img); //这里你想插哪插哪

                }
            }
        })(f);
        reader.readAsDataURL(f);
    }
}

function newGuid() {
    var guid = "";
    for (var i = 1; i <= 32; i++) {
        var n = Math.floor(Math.random() * 16.0).toString(16);
        guid += n;
        if ((i == 8) || (i == 12) || (i == 16) || (i == 20))
            guid += "-";
    }
    // alert(guid);
    return guid;
}

function toFixed2(num) {
    return parseFloat(+num.toFixed(2));
}

//@*/**
// * 替换字符串 !{}
// * @param obj
// * @returns {String}
// * @example
// * '我是!{str}'.render({str: '测试'});
// */*@
String.prototype.render = function (obj) {
    var str = this,
        reg;

    Object.keys(obj).forEach(function (v) {
        reg = new RegExp('\\!\\{' + v + '\\}', 'g');
        str = str.replace(reg, obj[v]);
    });
    return str;
};