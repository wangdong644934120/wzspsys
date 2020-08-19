/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var relativePath = "";
(function () {
    var src = $("script[src$='dataui.js']").attr("src");
    src = src.substring(0, src.indexOf("dataui.js"));//获取引用路径
    relativePath = src;
    var require = [
        "widget.js",
        // "defaultdata_2.js",
        "defaultdata.js",
        "widget_abc.js",
        "widget_phc.js",
        // "widget_dazx.js",
        // "widget_zhkf.js",
        "designer.js",
        "countUp.js"
    ];
    for (var i = 0; i < require.length; i++) {//可以避免手动写过多引入，可控制引入顺序
        document.write("<script src=\"" + src + require[i] + "\"></script>");
    }
    Array.prototype.pushAll = (function (arr) {
        for (var i = 0; i < arr.length; i++) {
            this.push(arr[i]);
        }
    });
    //禁止浏览器ctrl+滚轮 缩放
    var scrollFunc = function (e) {
        e = e || window.event;
        if (e.wheelDelta && event.ctrlKey) {//IE/Opera/Chrome
            event.returnValue = false;
        } else if (e.detail) {//Firefox
            event.returnValue = false;
        }
    };
    if (document.addEventListener) {
        document.addEventListener('DOMMouseScroll', scrollFunc, false);
    }
    ;//W3C
    window.onmousewheel = document.onmousewheel = scrollFunc;//IE/Opera/Chrome/Safari
})();

var dataui = {
    container: null, //视图主体组件
    get: function (id) {//根据ID或type获取组件
        return $("#" + id).data("widget");
    },
    getActive: function () {
        var active_model = $(".l_super_widget.active");
        return active_model.data("widget");
    },
    addWidget: function (container, widgetBean) {
        try {
            var widget = new Bswidget.prototype[widgetBean.type](widgetBean);
            container.append(widget.target);
            widget.show();
            return widget;
        } catch (ex) {
            console.log(ex);
        }
        return null;
    },
    init: function (container) {
        this.container = container;//视图body
        this.container.removeClass();
        this.container.addClass("l_container");
        this.container.append($("<div class=\"l_screen_border\"></div>"));
        this.container.bind("mousedown", function (event) {
            removeAllActive(event);
        });
    },
    setBackground: function (backgroud) {
//        if (backgroud == null || backgroud == "") {
//            //设置默认背景图片
//            this.container.css("background-image", "url(" + relativePath + "images/l_background_default.jpg)");
//        } else {
//            this.container.css("background-image", "url(" + relativePath + "images/l_background_" + backgroud + ".jpg)");
//        }
        if ($("#screen_backgroud").length > 0) {
            $("#screen_backgroud").val(backgroud);
        }
    },
    _showView: function (uidata, onedit) {//根据数据进行大屏显示
        //移除原有的
        this.container.find(".l_super_widget").each(function () {
            $(this).data("widget").remove();
        });
        var ratioX = this.container.width() / uidata.pageWidth;//X轴比例
        var ratioY = this.container.height() / uidata.pageHeight;//Y轴比例
        this.setBackground(uidata.backgroud);
        var widgetArray = uidata.widgets;
        if (widgetArray != null) {
            for (var i = 0; i < widgetArray.length; i++) {
                var widgetBean = widgetArray[i];
                widgetBean.onedit = onedit;
                widgetBean.x = widgetBean.x * ratioX;
                widgetBean.y = widgetBean.y * ratioY;
                widgetBean.width = widgetBean.width * ratioX;//按比例修正尺寸
                widgetBean.height = widgetBean.height * ratioY;
                var widgetObj = this.addWidget(this.container, widgetBean);
                if (DefaultData[widgetBean.type] != null) {
                    if (widgetObj != null && (onedit || DefaultData[widgetBean.type].loadShow)) {//处于编辑模式，或需默认加载
                        widgetObj.setData(DefaultData[widgetBean.type].data);
                    }
                }
            }
        }
    },
    openScreen: function (name, onedit) {//打开指定大屏
        onedit = false || onedit;
        // $.ajax({
        //     url: "/screen/read",
        //     async: false,
        //     data:{
        //         name:name + ".json"
        //     },
        //     dataType: "json",
        //     success: function (data) {
        //         data=$.parseJSON(data);
        //         dataui._showView(data, onedit);
        //     }
        // });
        //直接从web路径下获取
        $.ajax({
            url: "data/" + name + ".json?" + Math.random(),
            async: false,
            dataType: "json",
            success: function (data) {
                dataui._showView(data, onedit);
            }
        });
    }
};
