layui.use(['table', 'admin', 'ax'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;

    /**
     * 书籍表管理
     */
    var Books = {
        tableId: "booksTable"
    };

    /**
     * 初始化表格的列
     */
    Books.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'id', hide: true, title: '主键'},
            {field: 'bname', sort: true, title: '书籍名称'},
            {field: 'bauthor', sort: true, title: '作者'},
            {field: 'bcompany', sort: true, title: '出版社'},
            {align: 'center', toolbar: '#tableBar', title: '操作'}
        ]];
    };

    /**
     * 点击查询按钮
     */
    Books.search = function () {
        var queryData = {};
        queryData['condition'] = $("#condition").val();
        table.reload(Books.tableId, {
            where: queryData, page: {curr: 1}
        });
    };

    /**
     * 弹出添加对话框
     */
    Books.openAddDlg = function () {
        window.location.href = Feng.ctxPath + '/books/add';
    };

    /**
     * 导出excel按钮
     */
    Books.exportExcel = function () {
        var checkRows = table.checkStatus(Books.tableId);
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
    Books.openEditDlg = function (data) {
        window.location.href = Feng.ctxPath + '/books/edit?id=' + data.id;
    };

    /**
     * 点击删除
     *
     * @param data 点击按钮时候的行数据
     */
    Books.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/books/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(Books.tableId);
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
        elem: '#' + Books.tableId,
        url: Feng.ctxPath + '/books/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: Books.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        Books.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        Books.openAddDlg();
    });

    // 导出excel
    $('#btnExp').click(function () {
        Books.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + Books.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            Books.openEditDlg(data);
        } else if (layEvent === 'delete') {
            Books.onDeleteItem(data);
        }
    });
});
