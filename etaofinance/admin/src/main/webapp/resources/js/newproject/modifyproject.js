//正则 手机号验证
function IsPhone(phone) {
    return (/^1\d{10}$/.test(phone));
}

//大于0正整数
function isInt(n) {
    return n == Math.abs(parseInt(n));
}

//是否为浮点数
function IsDouble(n) {
    return /^(-?\d+)(\.\d{1,2})?$/.test(n);
}

//是否为空串或者白串
function IsEmpty(n) {
    return /^\s*$/.test(n);
}


//保存校验
function SaveChek() {
    if (IsEmpty($('#memberId').val()) || $('#memberId').val() == 0) {
        alert('会员手机号不能为空!');
        $('#memberId').focus();
        return false;
    }
    if (IsEmpty($('#projectName').val())) {
        alert('项目名称不能为空!');
        $('#projectName').focus();
        return false;
    }
    
    var onlinePreheatDate=$("#onlinePreheatDate").val();
    var openFinancingDate=$("#openFinancingDate").val();
    var endFinancingDate=$("#endFinancingDate").val();
    if(onlinePreheatDate=="" ||openFinancingDate=="" ||endFinancingDate==""){
        alert("预热时间、融资时间不能能为空！");
        return false;
    }     
    //收益比例判断
    var typevalue = $('input[name=rProjectType]:checked').val();
    if (typevalue == 1)//稳健性
    {
        var p1a = $('#projectType1A').val();
        if (!IsDouble(p1a)) {
            alert('请输入正确的收益比例!');
            $('#projectType1A').focus();
            return false;
        }
        var p1b = $('#projectType1B').val();
        if (p1b.length != 0 && !IsDouble(p1b)) {
            alert('请输入正确的收益比例!');
            $('#projectType1B').focus();
            return false;
        }
    } else//风险性
    {
        var p2a = $('#projectType2A').val();
        var p2b = $('#projectType2B').val();
        if (!IsDouble(p2a)) {
            alert('请输入正确的收益比例!');
            $('#projectType2A').focus();
            return false;
        }
        var p1b = $('#projectType1B').val();
        if (p1b.length != 0 && !IsDouble(p2b)) {
            alert('请输入正确的收益比例!');
            $('#projectType2B').focus();
            return false;
        }
    }
    
    
    //一句话简介判断
    if (IsEmpty($('#projectDescription').val())) {
        alert('一句话简介不能为空!');
        $('#projectDescription').focus();
        return false;
    }
    
    //融资金额判断
    var a = $('#projectAmount').val();
    var b = $('#projectFenShu').val();
    var c = $('#projectUnitPrice').val();
  
	if (a.length == 0 || b.length == 0 ) {
		   alert('请输入正确的融资金额和份数!');
		   alert(1);
		return false;
	}

	if (!isInt(a) || a == 0) {
		alert('融资金额必须为正整数');
		   alert(2);
		$('#projectAmount').focus();
		return false;
	}

	if ( !isInt(b) || b == 0) {
		alert('份数必须为正整数');
		   alert(3);
		$('#projectFenShu').focus();
		return false;
	}

	  if (parseInt(a) == 0 || parseInt(b) == 0 || parseInt(c) == 0 || parseInt(c) * parseInt(b) != parseInt(a)) {
	        alert('请输入正确的融资金额和份数!');
			   alert(4);
	        return false;
	    }
    
    //最高份数
    var ltzg = $('#projectPreheatMaxFenShu').val();
    //最低限制
    var zdxz = $('#projectLeadMinFenShu').val();
    if (!isInt(ltzg) || parseInt(ltzg) <= 0 || parseInt(ltzg) >= parseInt(b)) {
        alert('请输入正确的领投总额最高限额!');
        $('#projectPreheatMaxFenShu').focus();
        return false;
    }
    if (!isInt(zdxz) || parseInt(zdxz) <= 0 || parseInt(zdxz) > parseInt(ltzg)) {
        alert('请输入正确的领投人最低限制!');
        $('#projectLeadMinFenShu').focus();
        return false;
    }

    //省市区
    var pcode = $('#provinceCode').val();
    var ccode = $('#cityCode').val();
    var acode = $('#regionCode').val();
    if (pcode == -1 || ccode == -1 || acode == -1) {
        alert('请选择正确的省份,城市或区域!');
        return false;
    }
    //详细地址
    if (IsEmpty($('#projectAddress').val())) {
        alert('详细地址不能为空!');
        $('#projectAddress').focus();
        return false;
    }
    //上传图片验证.暂时只验证 项目图片.waptu片
    var xmurl = $('#ProjectImgBox').find('.hideurl');
    var proId=$("#projectId").val();
    var oriUrl = $('#ProjectImgBox').find('.modifyProImg');
    if (xmurl.length == 0 && oriUrl.length ==0) {
        alert('请上传项目图片!');
        return false;
    }
    //项目概况(Wap):
    var gkurl = $('#ProjectDescImgWapBox').find('.hideurl');
    var origkurl = $('#ProjectDescImgWapBox').find('.hideurl');
    
    if (gkurl.length == 0 && origkurl==0) {
        alert('请上传项目概况(Wap)图片!');
        return false;
    }
    //回报说明(Wap):
    var hburl = $('#ProjectHuibaoImgWapBox').find('.hideurl');
    var orihburl = $('#ProjectHuibaoImgWapBox').find('.hideurl');
    if (hburl.length == 0&& orihburl==0) {
        alert('请上传回报说明(Wap)图片!');
        return false;
    }
    //遍历查看是否有未上传的图片
    var flag = true;
    $('.hideurl').each(function(i, el) {
        if ($(el).val() == '') {
            flag = false;
            return false;
        }
    });
    if (!flag) {
        alert('当前有未上传成功的图片,请将图片全部上传至服务器!');
        return false;
    }
    return true;

}

//创建项目对象
function CreateProj() {
    var project = new Object();
    project.id=$("#projectId").val();
    project.projectname = $('#projectName').val();
    project.typeid = $('input[name=rProjectType]:checked').val();
    project.description = $('#projectDescription').val();
    project.amount = $('#projectAmount').val();
    project.fenshu = $('#projectFenShu').val();
    project.leadminfenshu = $('#projectLeadMinFenShu').val();
    project.preheatmaxfenshu = $('#projectPreheatMaxFenShu').val();
    project.unitprice = $('#projectUnitPrice').val();
    project.provincecode = $('#provinceCode').val();
    project.citycode = $('#cityCode').val();
    project.areacode = $('#regionCode').val();
    project.address = $('#projectAddress').val();
    project.projectimage = $('#ProjectImgBox .hideurl').val();
    project.memberid = $('#memberId').val();
    project.preheattime=$("#onlinePreheatDate").val();
    project.starttime=$("#openFinancingDate").val();
    project.endtime=$("#endFinancingDate").val();
    return project;
}

//创建策略对象
function CreateStrategylist() {
    var strategylist = new Array();
    var type = $('input[name=rProjectType]:checked').val();
    if (type == 1)//稳健型
    {
        var item1 = new Object();
        //第一个值
        item1.key = 'SteadyA';
        item1.value = $('#projectType1A').val();
        item1.description = "稳健型第一个值";
        strategylist.push(item1);
        var p1b = $('#projectType1B').val();
        //第二个值
        var item2 = new Object();
        item2.key = 'SteadyB';
        item2.value = 0;
        if (p1b.length != 0 && IsDouble(p1b)) {
            item2.value = p1b;
        }
        item2.description = "稳健型第二个值";
        strategylist.push(item2);
    } else//风险型
    {
        var item1 = new Object();
        //第一个值
        item1.key = 'SteadyA';
        item1.value = $('#projectType2A').val();
        item1.description = "风险共担型第一个值";
        strategylist.push(item1);
        var p2b = $('#projectType2B').val();
        //第二个值
        var item2 = new Object();
        item2.key = 'SteadyB';
        item2.value = 0;
        item2.description = "风险共担型第二个值";
        if (p1b.length != 0 && IsDouble(p2b)) {
            item2.value = p2b;
        }
        strategylist.push(item2);
    }
    return strategylist;
}

//构建图片集合
function CreateImgList() {
    var imglist = new Array();
    /*项目概况图*/
    $('#ProjectDescImgPcBox .hideurl').each(function(i, obj) {
        var tmpObj = new Object();
        tmpObj.typeid = 11;
        tmpObj.url = $(obj).val();
        imglist.push(tmpObj);
    });
    
    
    /*项目概况wap图*/
    $('#ProjectDescImgWapBox .hideurl').each(function(i, obj) {
        var tmpObj = new Object();
        tmpObj.typeid = 12;
        tmpObj.url = $(obj).val();
        imglist.push(tmpObj);
    });
    /*项目概况回报说明pc图*/
    $('#ProjectHuibaoImgPcBox .hideurl').each(function(i, obj) {
        var tmpObj = new Object();
        tmpObj.typeid = 21;
        tmpObj.url = $(obj).val();
        imglist.push(tmpObj);
    });
    /*项目概况回报说明wap图*/
    $('#ProjectHuibaoImgWapBox .hideurl').each(function(i, obj) {
        var tmpObj = new Object();
        tmpObj.typeid = 22;
        tmpObj.url = $(obj).val();
        imglist.push(tmpObj);
    });
    return imglist;
}

//初始化文件上传对象(upload对象,上传按钮ID,图片放置区ID,最大图片数量)
function InitUpload(uploader, buttonId, imgboxId, maxImgCount) {
    // 初始化Web Uploader
    uploader = WebUploader.create({
        // 选完文件后，是否自动上传。
        auto : false,
        // swf文件路径
        swf : BasePath + '/js/webuploader0.1.5/Uploader.swf',
        // 文件接收服务端。
        server : 'http://2betop.net/fileupload.php',
        server : BasePath + '/upload/img',
        // 选择文件的按钮。可选。
        // 内部根据当前运行是创建，可能是input元素，也可能是flash.
        pick : '#' + buttonId,
        fileNumLimit : maxImgCount, //最大图片数量 这里设置为1个
        // 只允许选择图片文件。
        accept : {
            title : 'Images',
            extensions : 'gif,jpg,jpeg,bmp,png',
            mimeTypes : 'image/*'
        },
        //压缩相关配置
        thumb : {
            width : 110,
            height : 110,
            // 图片质量，只有type为`image/jpeg`的时候才有效。
            quality : 100,
            // 是否允许放大，如果想要生成小图的时候不失真，此选项应该设置为false.
            allowMagnify : false,
            // 是否允许裁剪。
            crop : false,
            // 为空的话则保留原有图片格式。
            // 否则强制转换成指定的类型。
            type : 'image/jpeg'
        },
        //单个文件不能超过5M
        fileSingleSizeLimit : 5 * 1024 * 1024
    });

    //当有文件添加进来的时候
    uploader.on('fileQueued', function(file) {
        //构建图片对象
        var $li = $('<li id="' + file.id + '">' + '<p class="imgWrap">' + '<img id="' + file.id + '" style="height: 80px;width: 100px;">' + '<inupt type="hidden" id="' + file.id + 'hideimg" class="hideurl">' + '</p></li>'),
        //图片按钮
            $btns = $('<div class="file-panel">' + '<span class="cancel">删除</span><span id="' + file.id + 'tip">  <font color="red">等待上传</font></span></div>').appendTo($li),

        //图片容器
            $imgbox = $('#' + imgboxId);
        //按钮事件
        $btns.on('click', function() {
            uploader.removeFile(file);
        });
        // 创建缩略图
        // 如果为非图片文件，可以不用调用此方法。
        // thumbnailWidth x thumbnailHeight 为 100 x 100
        uploader.makeThumb(file, function(error, src) {
            if (error) {
                $img.replaceWith('<span>不能预览</span>');
                return;
            }
            $li.find('img').attr('src', src);
        });
        $li.appendTo($imgbox);
        //将LI放置在图片容器里面
    });
    // 文件删除。
    uploader.on('fileDequeued', function(file) {
        var $li = $('#' + file.id);
        $li.remove();
    });

    // 文件上传成功，给item添加成功class, 用样式标记上传成功。
    uploader.on('uploadSuccess', function(file, response) {
        console.log(response);
        if (response.error == 0) {
            $('#' + file.id + 'hideimg').val(response.relativeurl);
            $('#' + file.id + 'tip').html('  <font color="red">上传完成</font>')
        } else {
            alert(response.message);
            $('#' + file.id + 'tip').html('  <font color="red">上传失败</font>')
        }
    });
    // 文件上传失败，显示上传出错。
    uploader.on('uploadError', function(file) {
        //alert('上传失败');
    });

    // 完成上传完了，成功或者失败，先删除进度条。
    uploader.on('uploadComplete', function(file) {
        //alert('上传完成');
    });
    return uploader;
}

$(function() {
    if (!WebUploader.Uploader.support()) {
        alert('Web Uploader 不支持您的浏览器！如果你使用的是IE浏览器，请尝试升级 flash 播放器');
        throw new Error('WebUploader does not support the browser you are using.');
    }
});
//**************文件上传END*************