/**
 * 添加或者修改页面
 */
var ActivityTypeInfoDlg = {
    data: {
        type1: "",
        type2: "",
        detail: "",
        score: "",
        icon: "",
        note: "",
        createTime: "",
        createUser: "",
        updateTime: "",
        updateUser: ""
    }
};

layui.use(['form', 'admin', 'ax','dict'], function () {
    var $ = layui.jquery;
    var $ax = layui.ax;
    var form = layui.form;
    var admin = layui.admin;

    //让当前iframe弹层高度适应
    //admin.iframeAuto();

    //表单提交事件
    form.on('submit(btnSubmit)', function (data) {
        var ajax = new $ax(Feng.ctxPath + "/activityType/addItem", function (data) {
            Feng.success("添加成功！");
            window.location.href = Feng.ctxPath + '/activityType'
        }, function (data) {
            Feng.error("添加失败！" + data.responseJSON.message)
        });
        ajax.set(data.field);
        ajax.start();

        return false;
    });

    //返回按钮
    $("#backupPage").click(function () {
        window.location.href = Feng.ctxPath + '/activityType'
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
                    , size: 100 //限制文件大小，单位 KB
                    , done: function (res) {
                        var picUUID = res.data.fileUUID;
                        $("#icon").val(picUUID );
                        $("#activityTypeIcon").attr("src", "data:image/jpeg;base64," + res.data.imgBase64);
                    }
                    , error: function () {
                    }
                });
            });
        });
    }

    initUpload();

});


$(function() {
    //获取项目类型
    $request({
        url: "/activityType/getActivityTypeList",
        async: true,
        data: null,
        dataType: "json",
        success: function (response) {
            console.log(response);
            if(response.code==200){
                var data=response.data;
                var groupMap={};
                for(var i=0;i<data.length;i++){
                    if(data[i].parentId==="0"){
                        var group=$("<optgroup label=\""+data[i].name+"\"></optgroup>");
                        groupMap[data[i].dictId]=group;
                    }
                }
                for(var i=0;i<data.length;i++){
                    if(data[i].parentId!=="0"){
                        var group = groupMap[data[i].parentId];
                        if(group !==null){
                            group.append("<option value=\""+data[i].dictId+"\">"+data[i].name+"</option>");
                        }
                    }
                }

                for(var key in groupMap){
                    $("#type2").append(groupMap[key]);
                }
                layui.form.render('select');
            }
        }
    });
});