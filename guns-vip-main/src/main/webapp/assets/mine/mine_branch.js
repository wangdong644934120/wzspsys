layui.use(['table', 'admin', 'ax'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;

    $(function () {
        initInfo("month");
    })

    function initInfo(time){

        $.post(
            Feng.ctxPath + "/mine/get/branch_data",
            {r:Math.random(), branchId: branchId, time:time},
            function(rs){
                if (rs.code == 200){
                    setUserInfo(rs.data.branchInfo);
                }
            },
            "json"
        )
    }

    function setUserInfo(infoData) {

        $("#branch_name").html(infoData.branchName);
        $("#branch_member_count").html(infoData.personCount);


        $("#user_score").html(infoData.score);
        $("#user_goods").html(infoData.goodsCount);
        $("#user_goods_buy").html(infoData.goodsCount);
    }

});
