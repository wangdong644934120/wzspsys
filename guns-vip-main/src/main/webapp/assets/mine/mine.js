layui.use(['table', 'admin', 'ax'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;

    $(function () {
        initAccount();
    })

    function initAccount(){

        $.post(
            Feng.ctxPath + "/mine/get/account_data",
            {r:Math.random(), userId:1},
            function(rs){
                if (rs.code == 200){

                    setUserInfo(rs.data.userinfo);
                }
            },
            "json"
        )
    }

    function setUserInfo(infoData) {
        $("#user_phone").html(infoData.name);
        $("#user_coin").html(infoData.coin);
        $("#user_score").html(infoData.score);
        $("#user_goods").html(infoData.goodsCount);
        $("#user_goods_buy").html(infoData.goodsCount);
    }

});
