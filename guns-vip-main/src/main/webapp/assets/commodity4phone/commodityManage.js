var Globle = {
    activityId: null,
    pageSize: 5,
    commodityId: "",
    showAll: false
};

/**
 *根据id获取商品
 **/
function getCommodityById(id) {
    var ajax = new layui.ax(Feng.ctxPath + "/commodity/detail?id=" + id);
    var rtn = ajax.start();
    return rtn;
}

//分页获取兑换记录
Globle.getExchangeRecord = function (page, id) {
    var ajax = new layui.ax(Feng.ctxPath + "/exchangeRecord/list?limit=" +
        Globle.pageSize + "&page=" + page + "&commodityId=" + id);
    var rtn = ajax.start();
    return rtn;
}

/**
 * 显示商品的兑换记录
 */
function showDetail(id) {
    // $("#exchangeView").slideInLeft(200);
    $("#listView").hide();
    $(".layui-tab").hide();
    Globle.commodityId = id;
    Globle.preShow = "listView";
    $("#detailView").show();
    $("#exchangeView").show();
    Feng.loadHtml($("#detailView"), "/pages/commodity4phone/commodity_detail.html", function () {
        var rtn = getCommodityById(Globle.commodityId);
        layui.laytpl($("#detailView").html()).render(rtn.data, function (html) {
            $("#detailView").html(html);
            var picArray = rtn.data.pics.split(",");
            $("#pics").html("");
            for (var i = 0; i < picArray.length; i++) {
                $("#pics").append("<img class=\"layadmin-homepage-pad-img\" src=\"/file/" + picArray[i] + "\" >");
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

            $("#exchangeView").html("");
            Feng.loadHtml($("#exchangeRecordTmp"), "/pages/commodity4phone/exchangeRecordTmp4Commodity.html", function () {
                layui.flow.load({
                    elem: '#exchangeView' //流加载容器
                    , scrollElem: '#exchangeView' //滚动条所在元素，一般不用填，此处只是演示需要。
                    , end: ' '
                    , done: function (page, next) { //执行下一页的回调
                        // debugger
                        var id = Globle.commodityId;
                        var ajax_data = Globle.getExchangeRecord(page, id);
                        var exchangeRecord = ajax_data.data;
                        var lis = [];
                        for (var i = 0; i < exchangeRecord.length; i++) {
                            var data = exchangeRecord[i];
                            layui.laytpl($("#exchangeRecordTmp").html()).render(data, function (html) {
                                lis.push(html);
                            });
                        }
                        var total = ajax_data.count;
                        next(lis.join(''), page < (total % Globle.pageSize > 0 ? (parseInt(total / Globle.pageSize) + 1) : (total / Globle.pageSize)));
                    }
                });
            });

        });

    });


}

layui.use(['form', 'table', 'admin', 'ax', "layer", "element", "flow", "laydate", 'laytpl'], function () {
    var $ = layui.$;
    var $ax = layui.ax;
    var element = layui.element;
    var laytpl = layui.laytpl;
    var pageSize = 6;
    console.log("商城管理页面----");
    jQuery.fn.slideInLeft = function (time) {
        var width = this.width();
        this.offset({
            left: width
        }).width(width).show().animate({
            left: 0
        }, time || 300);
    };
    var showAll = IndexGlobal.getUrlParam("showAll") == "true" ? true : false;
    Globle.showAll = showAll;
    if (Globle.showAll) {
        $("#myCommodityTitle").html("商城总览");
    }
    Globle.getCommodity = function (page) {
        if (Globle.showAll == true) {
            var ajax = new $ax(Feng.ctxPath + "/commodity/list4partMember?limit=" + pageSize + "&page=" + page + "&showAll=" + Globle.showAll);
            var rtn = ajax.start();
            return rtn;
        } else {
            var ajax = new $ax(Feng.ctxPath + "/commodity/list4Phone?limit=" + pageSize + "&page=" + page + "&showAll=" + Globle.showAll);
            var rtn = ajax.start();
            return rtn;
        }
    }
    //加载模板
    Feng.loadHtml($("#commodityItemTmpDiv"),"/pages/commodity4phone/commodityItemTemp.html",function(){
        layui.flow.load({
            elem: '#activityList' //流加载容器
            , scrollElem: '#activityList' //滚动条所在元素，一般不用填，此处只是演示需要。
            , end: ' '
            , done: function (page, next) { //执行下一页的回调
                // debugger
                var ajax_data = Globle.getCommodity(page);
                var commodity = ajax_data.data;
                var lis = [];
                for (var i = 0; i < commodity.length; i++) {
                    var data = commodity[i];
                    laytpl($("#commodityItemTmpDiv").html()).render(data, function (html) {
                        lis.push(html);
                    });
                }
                var total = ajax_data.count;
                next(lis.join(''), page < (total % pageSize > 0 ? (parseInt(total / pageSize) + 1) : (total / pageSize)));
                //进度条重绘
                element.render('progress');
            }
        });
    });
    layui.element.on('tab(mainTab)', function (data) {
        $("#detailView").hide();
        $("#exchangeView").hide();
        $("#detailView").html("");
        $("#exchangeView").html("");
        $("#listView").show();
        // $("#publishBtn").show();
        if (data.index == 0) {
        }
    });
});