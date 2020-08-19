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
            Feng.ctxPath + "/mine/get/committee_data",
            {r:Math.random(), committeeId: committeeId},
            function(rs){
                if (rs.code == 200){
                    setUserInfo(rs.data.committeeInfo);
                }
            },
            "json"
        )
    }

    function setUserInfo(info) {
        $("#committee_name").html(info.committeeName);

    }

});
