function compresspic(pic,callback){

	var picsize = getImgNaturalDimensions(pic);
	var picWidth = picsize[0];
	var picHeight = picsize[1];

	var square = 600;
  
	var imageWidth ;
	var imageHeight ;
	var offsetX = 0;
	var offsetY = 0; 
	var toCompress = false;

	if(  picWidth > square || picHeight > square ){
	    if (picWidth > picHeight) {
			imageWidth = square;
			imageHeight = picHeight * square / picWidth;
			toCompress = true;
	    } else if(picWidth < picHeight) {
			imageHeight = square;
			imageWidth = picWidth * square / picHeight;
			toCompress = true;
	    } else if(picWidth == picHeight) {
			imageHeight = square;
			imageWidth = square;
			toCompress = true;
 	    }
	}

	if( toCompress ){
		var canvas = document.createElement('canvas');
		var context = canvas.getContext('2d');
		context.clearRect(0, 0, square, square);
		canvas.width = imageWidth;
		canvas.height = imageHeight;

		context.drawImage(pic, 0, 0, imageWidth, imageHeight);
		var data = canvas.toDataURL('image/jpeg');
		pic.onload=null;
		pic.src=data;
		callback(data.split(",")[1],pic);
	}else{
		callback(pic.src.split(",")[1],pic);
	}
	
}

function getImgNaturalDimensions(img, callback) {
    var nWidth, nHeight
    if (img.naturalWidth) { 
        nWidth = img.naturalWidth
        nHeight = img.naturalHeight
    } else { // IE6/7/8
        var imgae = new Image()
        image.src = img.src
        image.onload = function() {
            callback(image.width, image.height)
        }
    }
    return [nWidth, nHeight]
}