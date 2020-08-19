/*
 * 大屏显示框架，基础组件封装
 * lichenfeng
 */
function WidgetBean() {
    this.id = "";
    this.onedit = false;//是否处于编辑
    this.type = "";//类型
    this.typeCN = "";//类型
    this.x = 0;
    this.y = 0;
    this.width = 0;
    this.height = 0;
    this.rotate = 0;//旋转角度
    this.toggle = {
        id: "",
        time: 10
    };
    this.dataSource = {
        type: "static",
        url: "",
        time: 30
    };
    this.title = {//标题
        show: 1,
        text: "",
        backgroud: "default",
        fontsize: "15px",
        fontcolor: "#000",
        position: "left"
    };
    this.backgroud = {//背景
        show: 1,
        backgroud: "default"
    };
    this.border = {//边距
        width: 30
    };
    this.text = {//文本
        text: "",
        size: "20px",
        color: "#FFFFFF",
        position: "center"
    };
}
/**
 * 大屏组件，父类
 * @returns {undefined}
 **/
function Bswidget(widgetBean) {
    this.widgetBean = widgetBean;
    this.target = $("<div id=\"" + widgetBean.id + "\" class=\"l_super_widget\"></div>");
    this.target.css("position", "absolute");
    this.target.data("widget", this);
    this.target.height(widgetBean.height);//高度
    this.target.width(widgetBean.width);//宽度
    this.target.offset({//位置
        top: widgetBean.y,
        left: widgetBean.x
    });
    var self = this;
    if (widgetBean.onedit) {//如果处于编辑状态，则可改变大小、位置
        new Concrete(this.target[0]);
    }
    this.container = $("<div class=\"l_widget_content\"></div>");
    this.target.append(this.container);
    this.appendChild = function (child) {
        this.container.append(child);
    };
    //移除
    this.remove = function () {
        if (this.taskInterval != null) {
            clearInterval(this.taskInterval);
        }
        if (this.toggleTask != null) {
            clearInterval(this.toggleTask);
        }
        this.target.remove();
    };
    this.taskInterval = null;
    this.setDataSource = function (dataSource) {
        if (dataSource.type == "api") {
            if (this.taskInterval != null) {
                clearInterval(this.taskInterval);
            }
            var doTask = function () {
                try {
                    $.ajax({
                        url: dataSource.url,
                        dataType: "json",
                        async: true,
                        success: function (data) {
                            self.setData(data);
                        }
                    });
                } catch (err) {
                    console.log(err);
                }
            };
            doTask();
            this.taskInterval = setInterval(doTask, dataSource.time * 1000);//默认30秒一次
        }
    };
    this.getWidgetBean = function () {
        if (this.target.hasClass("flip")) {//不这么做的话会导致切换的组件，位置错乱
            this.target.removeClass("flip").removeClass("flip_out").removeClass("flip_in");
        }
        this.widgetBean.x = this.target.position().left;
        this.widgetBean.y = this.target.position().top;
        this.widgetBean.width = this.target.outerWidth();
        this.widgetBean.height = this.target.outerHeight();
        return this.widgetBean;
    };
    this.setData = function (data) {//设置数据
        if (typeof (this.onSetData) == "function") {
            try {
                this.onSetData(data);
            } catch (err) {
                console.log("调用onSetData出错" + err);
            }
        }
    };
    this.bindEvent = function (type, callback) {//绑定事件
        this.target.bind(type, callback);
    };
    this.resize = function () {
        if (typeof (this.onResize) == "function") {
            try {
                this.onResize();
            } catch (err) {
                console.log("调用onResize出错" + err);
            }
        }
    };
    this.show = function () {
        if (typeof (this.onShow) == "function") {
            try {
                this.onShow();
            } catch (err) {
                console.log("组件onShow初始化出错" + err);
            }
        }
        this.setDataSource(this.widgetBean.dataSource);
    };
    //切换显示效果
    this.toggleTask = null;
    this.setToggle = function (toggle) {
        if (toggle == null) {
            return;
        }
        if (this.toggleTask != null) {
            clearInterval(this.toggleTask);
        }
        //this.target.removeClass("flip").removeClass("flip_out").removeClass("flip_in");
        if (toggle.id == null || toggle.id == "") {
        } else {
            this.target.addClass("flip").removeClass("flip_in").addClass("flip_out");
            $("#" + toggle.id).addClass("flip").addClass("flip_in").removeClass("flip_out");
            this.toggleTask = setInterval(function () {
                var eleBack = null, eleFront = null;
                if (self.target.hasClass("flip_out")) {
                    eleBack = self.target;
                    eleFront = $("#" + toggle.id);
                } else {
                    eleBack = $("#" + toggle.id);
                    eleFront = self.target;
                }
                eleFront.addClass("flip").addClass("flip_out").removeClass("flip_in");
                setTimeout(function () {
                    eleBack.addClass("flip").addClass("flip_in").removeClass("flip_out");
                }, 225);
            }, toggle.time * 1000);//默认30秒一次
        }
    };
    //显示默认样式
    this.showDefaultUI = function (defaultStyle) {
        for (var key0 in defaultStyle) {
            if (typeof defaultStyle[key0] == "object") {
                for (var key1 in defaultStyle[key0]) {
                    this.widgetBean[key0][key1] = defaultStyle[key0][key1];
                }
            } else {
//                this.widgetBean[key0]=defaultStyle[key0];
            }
        }
        this.refreshUI();
    };
    //1.0设置基本样式
    this.refreshUI = function () {//刷新UI显示
        this.target.attr("id", this.widgetBean.id);//修改ID
        if (this.widgetBean.backgroud.show == 1) {//背景样式
            this.target.addClass("l_super_widget_bg");
        } else if (this.widgetBean.backgroud.show == 2) {
            this.target.addClass("l_super_widget_bg2");
        } else if (this.widgetBean.backgroud.show == 3) {
            this.target.addClass("l_super_widget_bg3");
        } else {
            this.target.removeClass("l_super_widget_bg");
        }
        //设置边框大小
        this.target.css("border-width", this.widgetBean.border.width + "px");
        if (this.widgetBean.title.show == 1) {//标题样式
            var title = this.target.find(".l_widget_title");
            if (title.length == 0) {
                title = $("<a class=\"l_widget_title\"></a>");
                this.container.css("padding-top", "15px");
                this.target.append(title);
            }
            title.html(this.widgetBean.title.text);
            title.get(0).className = "l_widget_title l_title_backgroud_" + this.widgetBean.title.backgroud;
            title.css("top", "-" + (this.widgetBean.border.width / 2 + 5) + "px");//设置标题距离顶部距离
        } else {
            this.container.css("padding-top", "0px");
            this.target.find(".l_widget_title").remove();
        }
        if (this.widgetBean.text.text != "") {//文本样式
            this.container.html(this.widgetBean.text.text);
            this.container.css("font-size", this.widgetBean.text.size);
            this.container.css("color", this.widgetBean.text.color);
            this.container.css("text-align", this.widgetBean.text.position);
        }
        this.setDataSource(this.widgetBean.dataSource);
        this.setToggle(this.widgetBean.toggle);
        this.resize();
    };
    this.refreshUI();//初始界面UI
}
Bswidget.prototype.Label = function (widgetBean) {
    if (widgetBean.text.text == "") {
        widgetBean.text.text = "文本框";
    }
    Bswidget.call(this, widgetBean);
    this.onSetData = function (data) {//设置数据
        if (data != null) {
            this.container.html(data);
        }
    };
};

//滚动动画
function scrollList(obj) {
    //获得当前<li>的高度
    var scrollHeight = $(obj).children(".baseItem").height();
    //滚动出一个<li>的高度
    $(obj).stop().animate({marginTop: -scrollHeight}, 600, function () {
        //动画结束后，将当前<ul>marginTop置为初始值0状态，再将第一个<li>拼接到末尾。
        $(this).css({marginTop: 0}).find(".baseItem:first").appendTo(this);
    });
}

function getSize(contentDiv, num) {
    var offsetHeight = contentDiv.offsetHeight > contentDiv.width ? contentDiv.width : contentDiv.offsetHeight;
    var height = (offsetHeight - 50) / 3;
    var fontSize = height / num;
    if (fontSize < 5) {
        fontSize = 5;
    }
    return fontSize;
}