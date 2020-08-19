var Globle = {
    activityId: null,
    pageSize: 10,
    commodityId: "",
    carousel: null
};

//兑换商品
function exchangeCommodity(id) {
    var ajax = new layui.ax(Feng.ctxPath + "/exchangeRecord/addItem?commodityId=" + id, function () {
        Feng.success("兑换成功!");
        Globle.showListView();
    }, function (data) {
        Feng.error("兑换失败!" + data.responseJSON.message + "!");
    });
    var rtn = ajax.start();
}

/**
 *根据id获取商品
 **/
function getCommodityById(id) {
    var ajax = new layui.ax(Feng.ctxPath + "/commodity/detail?id=" + id);
    var rtn = ajax.start();
    return rtn;
}

/**
 * 显示商品详细
 */
function showDetail(id, type, exchangeTime, exchangeRecordId) {
    $(".commodity").hide();
    $(".layui-tab").hide();

    // $("#exchangeView").hide();
    $("#detailView").show();
    Feng.loadHtml($("#detailView"), "/pages/commodity4phone/commodity_detail.html", function () {
        $("#detailView").slideInLeft(200);
        // $("#listView").hide();
        var data = getCommodityById(id).data;
        var tempType = type;
        data.exchangeRecordId = exchangeRecordId;
        if (tempType == undefined) {
            data.fromPartMember = true;
            data.fromExchanging = false;
            data.fromExchanged = false;
            Globle.preShow = "listView";
        } else if (tempType == 0) {//待兑换
            data.fromExchanging = true;
            data.fromExchanged = false;
            Globle.preShow = "exchangeView";
        } else if (tempType == 1) {//已兑换
            data.fromExchanging = false;
            data.fromExchanged = true;
            data.exchangeTime = exchangeTime;
            Globle.preShow = "exchangeView";
        }
        layui.laytpl($("#detailView").html()).render(data, function (html) {
            $("#detailView").html(html);
            if (data.fromExchanging) {
                var $qrcode = $("#qrcode");
                new QRCode(document.getElementById("qrcode"), {
                    width: $qrcode.width(),
                    height: $qrcode.height()
                }).makeCode("EXCHANGE:" + data.exchangeRecordId);
                //后台注册扫描监听
                new QRCodeUtil().addQRListener(data.exchangeRecordId, "exchangeRecord", function () {
                    //扫码成功后，回调跳转
                    backListView();
                    //打开待兑换商品页
                    $("#exchanging").click();
                });
            }
            var picArray = data.pics.split(",");
            $("#pics").html("");
            for (var i = 0; i < picArray.length; i++) {
                $("#pics").append("<img class=\"layadmin-homepage-pad-img\" src=\"/file/" + picArray[i] + "\" >")
            }
            layui.use('carousel', function () {
                var carousel = layui.carousel;
                //建造实例
                carousel.render({
                    elem: '#test1'
                    , width: '100%' //设置容器宽度
                    // ,height:'200px'
                    , arrow: 'hover' //始终显示箭头
                    , autoplay: true
                    , indicator: 'outside'
                    //,anim: 'updown' //切换动画方式
                });
            });
        });
    });

    // window.location.href = "/pages/commodity4phone/commodity_detail.html";
    // window.top.openTabView('/ref/pages/commodity4phone/commodity_detail.html');
}

/**
 * 显示商品详细
 */
function showExchange(id, type, exchangeTime, exchangeRecordId) {
    $(".commodity").hide();
    $(".layui-tab").hide();
    $("#detailView").show();
    Feng.loadHtml($("#detailView"), "/pages/commodity4phone/commodity_exchange.html", function () {
        $("#detailView").slideInLeft(200);
        // $("#listView").hide();
        var data = getCommodityById(id).data;
        var tempType = type;
        data.exchangeRecordId = exchangeRecordId;
        if (tempType == 0) {//待兑换
            data.fromExchanging = true;
            data.fromExchanged = false;
            Globle.preShow = "exchangeView";
        } else if (tempType == 1) {//已兑换
            data.fromExchanging = false;
            data.fromExchanged = true;
            data.exchangeTime = exchangeTime;
            Globle.preShow = "exchangeView";
        }
        layui.laytpl($("#detailView").html()).render(data, function (html) {
            $("#detailView").html(html);
            if (data.fromExchanging) {
                var $qrcode = $("#qrcode");
                new QRCode(document.getElementById("qrcode"), {
                    width: $qrcode.width(),
                    height: $qrcode.height()
                }).makeCode("EXCHANGE:" + data.exchangeRecordId);
                //后台注册扫描监听
                new QRCodeUtil().addQRListener(data.exchangeRecordId, "exchangeRecord", function () {
                    //扫码成功后，回调跳转
                    backListView();
                    //打开待兑换商品页
                    $("#exchanging").click();
                });
            }
            var picArray = data.pics.split(",");
            $("#pics").html("");
            $("#pics").append("<img class=\"layadmin-homepage-pad-img\" src=\"/file/" + picArray[0] + "\" >");
        });
    });
}

//分页获取兑换记录
Globle.getExchangeRecord = function (page, id) {
    var ajax = new layui.ax(Feng.ctxPath + "/exchangeRecord/list?isExchange=1&limit=" +
        Globle.pageSize + "&page=" + page + "&commodityId=" + id);
    var rtn = ajax.start();
    return rtn;
}

layui.use(['form', 'table', 'admin', 'ax', "layer", "element", "flow", "laydate", 'laytpl', 'carousel'], function () {
    var $ = layui.$;
    var $ax = layui.ax;
    var element = layui.element;
    var laytpl = layui.laytpl;
    var pageSize = 6;
    jQuery.fn.slideInLeft = function (time) {
        var width = this.width();
        this.offset({
            left: width
        }).width(width).show().animate({
            left: 0
        }, time || 300);
    };
    Globle.getCommodity = function (page) {
        var ajax = new $ax(Feng.ctxPath + "/commodity/list4partMember?limit=" + pageSize + "&page=" + page);
        var rtn = ajax.start();
        return rtn;
    }
    Globle.initList = function () {
        layui.flow.load({
            elem: '#activityList' //流加载容器
            , scrollElem: '#activityList' //滚动条所在元素，一般不用填，此处只是演示需要。
            , end: " "
            , isAuto: true
            , done: function (page, next) { //执行下一页的回调
                var ajax_data = Globle.getCommodity(page);
                var commodity = ajax_data.data;
                // debugger;
                Feng.loadHtml($("#commodityItemTmpDiv"), "/pages/commodity4phone/commodityItemTemp.html", function () {
                    var lis = [];
                    for (var i = 0; i < commodity.length; i++) {
                        var data = commodity[i];
                        laytpl($("#commodityItemTmpDiv").html()).render(data, function (html) {
                            lis.push(html);
                        });
                    }
                    var total = ajax_data.count;
                    next(lis.join(''), page < (total % pageSize > 0 ? (parseInt(total / pageSize) + 1) : (total / pageSize)));
                });

            }
        });
    };
    //获取待兑换或已兑换商品
    Globle.getExchangeCommodity = function (page, isExchange) {
        var ajax = new $ax(Feng.ctxPath + "/exchangeRecord/list4PartyMember?isExchange=" + isExchange
            + "&limit=" + pageSize + "&page=" + page);
        var rtn = ajax.start();
        return rtn;
    }
    Globle.ExchangeList = function (isExchange) {
        layui.flow.load({
            elem: '#exchangeView' //流加载容器
            , scrollElem: '#exchangeView' //滚动条所在元素，一般不用填，此处只是演示需要。
            , end: " "
            , done: function (page, next) { //执行下一页的回调
                var ajax_data = Globle.getExchangeCommodity(page, isExchange);
                var commodity = ajax_data.data;
                Feng.loadHtml($("#exchangingRecordTmpDiv"), "/pages/commodity4phone/exchangeRecordTmp4Person.html", function () {
                    var lis = [];
                    for (var i = 0; i < commodity.length; i++) {
                        var data = commodity[i];
                        laytpl($("#exchangingRecordTmpDiv").html()).render(data, function (html) {
                            lis.push(html);
                        });
                    }
                    var total = ajax_data.count;
                    next(lis.join(''), page < (total % pageSize > 0 ? (parseInt(total / pageSize) + 1) : (total / pageSize)));
                });
            }
        });
    };
    //显示商城
    Globle.showListView = function (data) {
        $("#detailView").hide();
        $("#exchangeView").hide();
        $("#detailView").html("");
        $("#exchangeView").html("");
        $("#listView").show();
        $("#activityList").html("");
        if (data == undefined || data.index == 0) {
            Globle.initList();
        }
        // $("#publishBtn").show();
    };
    //待兑换商品
    Globle.showExchange = function (type) {
        $("#detailView").hide();
        $("#exchangeView").show();
        $("#detailView").html("");
        $("#exchangeView").html("");
        $("#listView").hide();
        $("#activityList").html("");
        Globle.ExchangeList(type);
        // $("#publishBtn").show();
    };
    var showAll = IndexGlobal.getUrlParam("showAll") == "false" ? false : true;
    console.log(showAll + "showAll")
    if (showAll) {
        Globle.initList();
    } else {
        $("#shoppingMall").hide();
        $("#exchanging").click();
        Globle.showExchange(0);
    }
    layui.element.on('tab(mainTab)', function (data) {
        var tmpId = $(this)[0].id;
        if (tmpId == "shoppingMall") {
            Globle.showListView(data);
        } else if (tmpId == "exchanging") {
            Globle.showExchange(0);
        } else if (tmpId == "exchanged") {
            Globle.showExchange(1);
        }
    });
});
