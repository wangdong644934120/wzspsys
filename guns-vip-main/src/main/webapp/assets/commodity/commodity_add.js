/**
 * 添加或者修改页面
 */
var CommodityInfoDlg = {
    data: {
        name: "",
        detail: "",
        pics: "",
        costCoin: "",
        total: "",
        surplus: "",
        enable: "",
        createTime: "",
        createUser: "",
        updateTime: "",
        updateUser: "",
        storeId: ""
    }
};

layui.use(['form', 'admin', 'ax'], function () {
    var $ = layui.jquery;
    var $ax = layui.ax;
    var form = layui.form;
    var admin = layui.admin;

    var storeId =  Feng.getUrlParam("storeId");

    //让当前iframe弹层高度适应
    //admin.iframeAuto();

    //表单提交事件
    form.on('submit(btnSubmit)', function (data) {
        var ajax = new $ax(Feng.ctxPath + "/commodity/addItem", function (data) {
            Feng.success("添加成功！");
            window.location.href = Feng.ctxPath + '/commodity?storeId='+storeId;
        }, function (data) {
            Feng.error("添加失败！" + data.responseJSON.message)
        });
        data.field['storeId'] = storeId;
        ajax.set(data.field);
        ajax.start();

        return false;
    });

    //返回按钮
    $("#backupPage").click(function () {
        window.location.href = Feng.ctxPath + '/commodity?storeId='+storeId;
    });

    /**
     * 上传图片
     */
    function initUpload() {
        layui.use('upload', function () {
            var upload = layui.upload;
            var uploadInst = $(".picBtn").each(function () {
                upload.render({
                    elem: this//指定元素  表示当前的元素
                    , url: '/activityType/uploadPicture'
                    , exts: 'jpg|png'
                    , size: 500 //限制文件大小，单位 KB
                    , done: function (res) {
                        var picUUID = res.data.fileUUID;
                        $("#pics").val(picUUID );
                        $("#picsIcon").attr("src", "data:image/jpeg;base64," + res.data.imgBase64);
                    }
                    , error: function () {
                    }
                });
            });
        });
    }
    initUpload();
});