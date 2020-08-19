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
            Feng.ctxPath + "/mine/get/account_data",
            {r:Math.random(), userId:userId},
            function(rs){
                if (rs.code == 200){
                    setUserInfo(rs.data.userinfo);
                }
            },
            "json"
        )
    }

    function setUserInfo(infoData) {
        var userInfo = infoData.userinfo
        $("#user_phone").html(userInfo.name);
        $("#user_coin").html(userInfo.coin);
        $("#user_score").html(userInfo.score);
        $("#user_goods").html(userInfo.goodsCount);
        $("#user_goods_buy").html(userInfo.goodsCount);
    }

});
