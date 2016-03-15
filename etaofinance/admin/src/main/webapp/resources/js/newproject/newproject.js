//点击图片移除
function RemoveImg(id,uploader){
	$('#'+id).remove();
	uploader.removeFile(id);
};
//初始化文件上传对象(upload对象,上传按钮ID,图片放置区ID,最大图片数量)
function InitUpload(uploader,buttonId,imgboxId,maxImgCount)
{
	 // 初始化Web Uploader
	 uploader = WebUploader.create({
	    // 选完文件后，是否自动上传。
	    auto: false,
	    // swf文件路径
	    swf: BasePath+'/js/webuploader0.1.5/Uploader.swf',
	    // 文件接收服务端。
	    server:'http://2betop.net/fileupload.php',
	    // 选择文件的按钮。可选。
	    // 内部根据当前运行是创建，可能是input元素，也可能是flash.
	    pick: '#'+buttonId,
	    fileNumLimit: maxImgCount,//最大图片数量 这里设置为1个
	    // 只允许选择图片文件。
	    accept: {
	        title: 'Images',
	        extensions: 'gif,jpg,jpeg,bmp,png',
	        mimeTypes: 'image/*'
	    },
	    //压缩相关配置
	    thumb:{
	    	
	    	    width: 110,
	    	    height: 110,
	    	    // 图片质量，只有type为`image/jpeg`的时候才有效。
	    	    quality: 100,
	    	    // 是否允许放大，如果想要生成小图的时候不失真，此选项应该设置为false.
	    	    allowMagnify: false,
	    	    // 是否允许裁剪。
	    	    crop: false,
	    	    // 为空的话则保留原有图片格式。
	    	    // 否则强制转换成指定的类型。
	    	    type: 'image/jpeg'
	    }
	});
	
	//当有文件添加进来的时候
	uploader.on( 'fileQueued', function( file ) {
		console.log(file);
		//构建图片对象
		var $li = $( '<li id="' + file.id + '">' +
        '<p class="imgWrap">'+
        '<img id="' + file.id + '" style="height: 80px;width: 100px;" onclick=RemoveImg("' + file.id + '")>'+
        '</p></li>' ),
        //图片按钮
        $btns = $('<div class="file-panel">' +
        '<span class="cancel">删除</span>' +
        '<span class="rotateRight">向右旋转</span>' +
        '<span class="rotateLeft">向左旋转</span></div>').appendTo( $li ),
        //图片容器
        $imgbox=$('#'+imgboxId);
		console.log($imgbox);
	
	    // 创建缩略图
	    // 如果为非图片文件，可以不用调用此方法。
	    // thumbnailWidth x thumbnailHeight 为 100 x 100
	    uploader.makeThumb( file, function( error, src ) {
	        if ( error ) {
	            $img.replaceWith('<span>不能预览</span>');
	            return;
	        }
	        $li.find('img').attr( 'src', src );
	    });
	    $li.appendTo($imgbox);//将LI放置在图片容器里面
		
	});
	// 文件删除。
	uploader.on( 'fileDequeued', function(file) {
			console.log(file);
	});

	// 文件上传成功，给item添加成功class, 用样式标记上传成功。
	uploader.on( 'uploadSuccess', function( file ) {
	  //  $( '#'+file.id ).addClass('upload-state-done');
	});

	// 文件上传失败，显示上传出错。
	uploader.on( 'uploadError', function( file ) {
//	    var $li = $( '#'+file.id ),
//	        $error = $li.find('div.error');
//
//	    // 避免重复创建
//	    if ( !$error.length ) {
//	        $error = $('<div class="error"></div>').appendTo( $li );
//	    }
//
//	    $error.text('上传失败');
	});

	// 完成上传完了，成功或者失败，先删除进度条。
	uploader.on( 'uploadComplete', function( file ) {
	   // $( '#'+file.id ).find('.progress').remove();
	});
	
}

$(function(){
   if ( !WebUploader.Uploader.support() ) {
       alert( 'Web Uploader 不支持您的浏览器！如果你使用的是IE浏览器，请尝试升级 flash 播放器');
       throw new Error( 'WebUploader does not support the browser you are using.' );
   }
});
//**************文件上传END*************