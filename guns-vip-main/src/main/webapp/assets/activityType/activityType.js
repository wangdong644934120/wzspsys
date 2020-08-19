layui.use(['table', 'admin', 'ax'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;

    /**
     * 活动类别信息表管理
     */
    var ActivityType = {
        tableId: "activityTypeTable"
    };

    /**
     * 初始化表格的列
     */
    ActivityType.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'id', hide: true, title: 'id'},
            {field: 'type1', hide: true, sort: true, title: '一级类别'},
            {field: 'type2', hide: true, sort: true, title: '二级类别'},
            {field: 'type1Name', sort: true, title: '一级类别'},
            {field: 'type2Name', sort: true, title: '二级类别'},
            {field: 'detail', sort: true, title: '具体事项'},
            {field: 'score', sort: true, title: '积分'},
            {field: 'icon', hide: true,sort: true, title: '图标'},
            {field: 'note', sort: true, title: '备注'},
            {field: 'createTime', hide: true,sort: true, title: '创建时间'},
            {field: 'createUser', hide: true,sort: true, title: '创建人'},
            {field: 'updateTime', hide: true,sort: true, title: '更新时间'},
            {field: 'updateUser', hide: true,sort: true, title: '更新人'},
            {align: 'center', toolbar: '#tableBar', title: '操作'}
        ]];
    };

    /**
     * 点击查询按钮
     */
    ActivityType.search = function () {
        var queryData = {};
        queryData['condition'] = $("#condition").val();
        table.reload(ActivityType.tableId, {
            where: queryData, page: {curr: 1}
        });
    };

    /**
     * 弹出添加对话框
     */
    ActivityType.openAddDlg = function () {
        window.location.href = Feng.ctxPath + '/activityType/add';
    };

    /**
     * 导出excel按钮
     */
    ActivityType.exportExcel = function () {
        var checkRows = table.checkStatus(ActivityType.tableId);
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
    ActivityType.openEditDlg = function (data) {
        window.location.href = Feng.ctxPath + '/activityType/edit?id=' + data.id;
    };

    /**
     * 点击删除
     *
     * @param data 点击按钮时候的行数据
     */
    ActivityType.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/activityType/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(ActivityType.tableId);
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
        elem: '#' + ActivityType.tableId,
        url: Feng.ctxPath + '/activityType/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: ActivityType.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        ActivityType.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        ActivityType.openAddDlg();
    });

    // 导出excel
    $('#btnExp').click(function () {
        ActivityType.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + ActivityType.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            ActivityType.openEditDlg(data);
        } else if (layEvent === 'delete') {
            ActivityType.onDeleteItem(data);
        }
    });


});


