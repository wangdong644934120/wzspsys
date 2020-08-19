/**
 * 添加或者修改页面
 */
// var UserExtInfoDlg = {
//     data: {
//         committee: "",
//         branch: "",
//         community: "",
//         store: ""
//     }
// };

layui.use(['form', 'admin', 'ax'], function () {
    var $ = layui.jquery;
    var $ax = layui.ax;
    var form = layui.form;
    var admin = layui.admin;

    var type =  Feng.getUrlParam("type");   //community ,
    var baseID =  Feng.getUrlParam("baseID");

    //让当前iframe弹层高度适应
    //admin.iframeAuto();

    //表单提交事件
    form.on('submit(btnSubmit)', function (data) {
        data.field['type']=type ;
        data.field['baseID'] = baseID ;
        var ajax = new $ax(Feng.ctxPath + "/userExt/addItem", function (data) {
            Feng.success("添加成功！");
            window.location.href = Feng.ctxPath + '/userExt?type=' + type+'&baseID='+baseID;
        }, function (data) {
            Feng.error("添加失败！" + data.responseJSON.message)
        });
        ajax.set(data.field);
        ajax.start();

        return false;
    });

    //返回按钮
    $("#backupPage").click(function () {
        window.location.href = Feng.ctxPath + '/userExt'
    });

});