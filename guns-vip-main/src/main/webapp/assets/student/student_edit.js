/**
 * 详情对话框
 */
var StudentInfoDlg = {
    data: {
        code: "",
        name: "",
        address: "",
        classesname:"",
        classesid:""
    }
};

layui.use(['form', 'admin', 'ax'], function () {
    var $ = layui.jquery;
    var $ax = layui.ax;
    var form = layui.form;
    var admin = layui.admin;

    //让当前iframe弹层高度适应
    //admin.iframeAuto();

// 点击部门时
    $('#classesname').click(function () {
        var formName = encodeURIComponent("parent.StudentInfoDlg.data.classesname");
        var formId = encodeURIComponent("parent.StudentInfoDlg.data.classesid");
        var treeUrl = encodeURIComponent("/dept/tree");

        layer.open({
            type: 2,
            title: '部门选择',
            area: ['300px', '400px'],
            content: Feng.ctxPath + '/system/commonTree?formName=' + formName + "&formId=" + formId + "&treeUrl=" + treeUrl,
            end: function () {
                console.log(StudentInfoDlg.data);
                $("#classesid").val(StudentInfoDlg.data.classesid);
                $("#classesname").val(StudentInfoDlg.data.classesname);
            }
        });
    });

    //获取详情信息，填充表单
    var ajax = new $ax(Feng.ctxPath + "/student/detail?id=" + Feng.getUrlParam("id"));
    var result = ajax.start();
    console.log( result.data);
    form.val('studentForm', result.data);



    //表单提交事件
    form.on('submit(btnSubmit)', function (data) {
        var ajax = new $ax(Feng.ctxPath + "/student/editItem", function (data) {
            Feng.success("更新成功！");
            window.location.href = Feng.ctxPath + '/student'
        }, function (data) {
            Feng.error("更新失败！" + data.responseJSON.message)
        });
        ajax.set(data.field);
        ajax.start();

        return false;
    });

    //返回按钮
    $("#backupPage").click(function () {
        window.location.href = Feng.ctxPath + '/student'
    });

});