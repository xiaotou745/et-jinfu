/*
	description:众筹页面
 */

$(function() {

    // DOM节点
    var dom = {
        popup_existCitys: $("#popup_existCitys"), // 现有店铺所在地 弹窗
        popup_expectCity: $("#popup_expectCity"), // 预期众筹所在地 弹窗
        existCitys: $("#existCitys"), // 现有店铺所在地 input
        expectCity: $("#expectCity") // 预期众筹所在地 input

    };

    
    var existCitys = [],
        expectCity = [],	// 保存的选择结果
        existCitysRendered=false,
        expectCityRendered=false;	// 判断弹窗事件是否绑定


    // 现有店铺所在地 点击事件
    dom.existCitys.parent().on("click", function(e) {

    	if(!existCitysRendered){
    		renderMultiCity();
    	}
        dom.popup_existCitys.removeClass("hide");
        $('html,body').addClass("overflow-hidden");

        
    });

    // 预期众筹所在地 点击事件
    dom.expectCity.parent().on("click", function() {
    	if(!expectCityRendered){
    		renderSingleCity();
    	}
        dom.popup_expectCity.removeClass("hide");
        $('html,body').addClass("overflow-hidden");
    });

    // 完成按钮 点击事件
    dom.popup_existCitys.find("#complete")[0].addEventListener("touchstart", function(e){
    	e.preventDefault();
        dom.popup_existCitys.addClass("hide");
    	$('html,body').removeClass("overflow-hidden");
    	dom.existCitys.val(existCitys.toString());
    	$("#hidExistCitys").val(existCitys.toString());
    });
    dom.popup_expectCity.find("#complete")[0].addEventListener("touchstart", function(e){
    	e.preventDefault();
        dom.popup_expectCity.addClass("hide");
    	$('html,body').removeClass("overflow-hidden");
    	dom.expectCity.val(expectCity.toString());
    	$("#hidExpectCity").val(expectCity.toString());
    });

    // 现有店铺所在地 弹窗事件绑定
    function renderMultiCity() {
    	existCitysRendered=true;
        var province = dom.popup_existCitys.find(".popup_con"),
            provinceText = dom.popup_existCitys.find(".popup_con p"),
            area = province.find("ul li");

        // 省份选择
        provinceText.on("click", function() {
            province.not($(this).parent()).removeClass("on");
            $(this).parent().toggleClass("on");
        });

        // 地区选择
        area.on("click", function() {
            var isSelected = $(this).find("i").length > 0,
                slectedAreaText = $(this).text();

            if (isSelected) {
                $(this).find("i").remove();
                for (var i = 0, l = existCitys.length; i < l; i++) {
                    if (slectedAreaText === existCitys[i]) {
                        existCitys.splice(i, 1);
                        return;
                    }
                }
            } else {
                $(this).append("<i></i>");
                existCitys.push(slectedAreaText);
            }
        });
    }

   	// 预期众筹所在地 弹窗事件绑定
    function renderSingleCity() {
    	expectCityRendered=true;
        var province = dom.popup_expectCity.find(".popup_con"),
            provinceText = dom.popup_expectCity.find(".popup_con p"),
            area = province.find("ul li");

        // 省份选择
        provinceText.on("click", function() {
            province.not($(this).parent()).removeClass("on");
            $(this).parent().toggleClass("on");
        });

        // 地区选择
        area.on("click", function() {
            var isSelected = $(this).find("i").length > 0,
                slectedAreaText = $(this).text();
            area.find("i").remove();
            expectCity = [];
            $(this).append("<i></i>");
            expectCity.push(slectedAreaText);
        });
    }

});
