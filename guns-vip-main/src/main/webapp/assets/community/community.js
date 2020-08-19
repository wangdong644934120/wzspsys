layui.use(['table', 'admin', 'ax'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;

    /**
     * 社区表管理
     */
    var Community = {
        tableId: "communityTable"
    };

    /**
     * 初始化表格的列
     */
    Community.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'id', hide: true, title: ''},
            {field: 'code', sort: true, title: '街道'},
            {field: 'name', sort: true, title: '名称'},
            {field: 'phone', hide:true , sort: true, title: '电话'},
            {field: 'createTime', hide:true, sort: true, title: '创建时间'},
            {field: 'createUser', hide:true, sort: true, title: '创建人id'},
            {field: 'updateTime', hide:true, sort: true, title: '修改时间'},
            {field: 'updateUser', hide:true, sort: true, title: '修改人id'},
            {align: 'center', toolbar: '#tableBar', title: '操作'}
        ]];
    };

    /**
     * 点击查询按钮
     */
    Community.search = function () {
        var queryData = {};
        queryData['name'] = $("#condition").val();
        table.reload(Community.tableId, {
            where: queryData, page: {curr: 1}
        });
    };

    /**
     * 弹出添加对话框
     */
    Community.openAddDlg = function () {
        window.location.href = Feng.ctxPath + '/community/add';
    };

    /**
     * 导出excel按钮
     */
    Community.exportExcel = function () {
        var checkRows = table.checkStatus(Community.tableId);
        if (checkRows.data.length === 0) {
            Feng.error("请选择要导出的数据");
        } else {
            table.exportFile(tableResult.config.id, checkRows.data, 'csv');
        }
    };

    /**
     * 点击编辑
     *
     * @param data 点击按钮时候的行数据
     */
    Community.openEditDlg = function (data) {
        window.location.href = Feng.ctxPath + '/community/edit?id=' + data.id;
    };

    /**
     * 点击删除
     *
     * @param data 点击按钮时候的行数据
     */
    Community.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/community/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(Community.tableId);
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("id", data.id);
            ajax.start();
        };
        Feng.confirm("是否删除?", operation);
    };

    // 渲染表格
    var tableResult = table.render({
        elem: '#' + Community.tableId,
        url: Feng.ctxPath + '/community/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: Community.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        Community.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        Community.openAddDlg();
    });

    // 导出excel
    $('#btnExp').click(function () {
        Community.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + Community.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            Community.openEditDlg(data);
        } else if (layEvent === 'delete') {
            Community.onDeleteItem(data);
        }else if (layEvent === 'manager') {
            Community.managerManage(data);
        }

    });

    Community.managerManage = function (data) {
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            title: '负责人管理',
            area: ['90%', '90%'],
            content: Feng.ctxPath + '/userExt?baseID=' + data.id+"&type=community",
            end: function () {
                admin.getTempData('formOk') && table.reload();
            }
        });
    };

});
