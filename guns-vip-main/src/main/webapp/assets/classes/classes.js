layui.use(['table', 'admin', 'ax'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;

    /**
     * 班级表管理
     */
    var Classes = {
        tableId: "classesTable"
    };

    /**
     * 初始化表格的列
     */
    Classes.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'id', hide: true, title: '主键'},
            {field: 'classname', sort: true, title: '班级名称'},
            {field: 'teacher', sort: true, title: '班主任'},
            {field: 'createTime', sort: true, title: '创建时间'},
            {align: 'center', toolbar: '#tableBar', title: '操作'}
        ]];
    };

    /**
     * 点击查询按钮
     */
    Classes.search = function () {
        var queryData = {};
        queryData['condition'] = $("#condition").val();
        table.reload(Classes.tableId, {
            where: queryData, page: {curr: 1}
        });
    };

    /**
     * 弹出添加对话框
     */
    Classes.openAddDlg = function () {
        window.location.href = Feng.ctxPath + '/classes/add';
    };

    /**
     * 导出excel按钮
     */
    Classes.exportExcel = function () {
        var checkRows = table.checkStatus(Classes.tableId);
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
    Classes.openEditDlg = function (data) {
        window.location.href = Feng.ctxPath + '/classes/edit?id=' + data.id;
    };

    /**
     * 点击删除
     *
     * @param data 点击按钮时候的行数据
     */
    Classes.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/classes/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(Classes.tableId);
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
        elem: '#' + Classes.tableId,
        url: Feng.ctxPath + '/classes/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: Classes.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        Classes.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        Classes.openAddDlg();
    });

    // 导出excel
    $('#btnExp').click(function () {
        Classes.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + Classes.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            Classes.openEditDlg(data);
        } else if (layEvent === 'delete') {
            Classes.onDeleteItem(data);
        }
    });
});
