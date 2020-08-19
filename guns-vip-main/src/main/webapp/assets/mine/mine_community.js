layui.use(['table', 'admin', 'ax'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;

    $(function () {
        initInfo();
    })

    function initInfo(){

        $.post(
            Feng.ctxPath + "/mine/get/community_data",
            {r:Math.random(), userId:1},
            function(rs){
                if (rs.code == 200){

                    setUserInfo(rs.data);
                }
            },
            "json"
        )
    }

    function setUserInfo(infoData) {
        $("#user_phone").html(infoData.phone);
        $("#user_coin").html(infoData.coin);
        $("#user_score").html(infoData.score);
        $("#user_goods").html(infoData.goodsCount);
        $("#user_goods_buy").html(infoData.goodsCount);
    }

});
