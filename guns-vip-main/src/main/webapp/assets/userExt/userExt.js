layui.use(['table', 'admin', 'ax'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;

    var type =  Feng.getUrlParam("type");   //community ,
    var baseID =  Feng.getUrlParam("baseID");

    /**
     * 联系人表管理
     */
    var UserExt = {
        tableId: "userExtTable"
    };

    /**
     * 初始化表格的列
     */
    UserExt.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'userId', hide: true, title: '主键id'},
            {field: 'name', title: '姓名'},
            {field: 'phone', title: '手机号'},
            {field: 'committee', hide: true,sort: true, title: '党委id'},
            {field: 'branch', hide: true,sort: true, title: '党支部id'},
            {field: 'community',hide: true, sort: true, title: '社区id'},
            {field: 'store', hide: true,sort: true, title: '商家id'},
            {align: 'center', toolbar: '#tableBar', title: '操作'}
        ]];
    };

    /**
     * 点击查询按钮
     */
    UserExt.search = function () {
        var queryData = {};
        queryData['name'] = $("#condition").val();
        queryData['type'] = type;
        queryData['baseID'] = baseID;
        table.reload(UserExt.tableId, {
            where: queryData, page: {curr: 1}
        });
    };

    /**
     * 弹出添加对话框
     */
    UserExt.openAddDlg = function () {
        window.location.href = Feng.ctxPath + '/userExt/add?type=' + type+'&baseID='+baseID;
    };

    /**
     * 导出excel按钮
     */
    UserExt.exportExcel = function () {
        var checkRows = table.checkStatus(UserExt.tableId);
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
    UserExt.openEditDlg = function (data) {
        window.location.href = Feng.ctxPath + '/userExt/edit?userId=' + data.userId;
    };

    /**
     * 点击删除
     *
     * @param data 点击按钮时候的行数据
     */
    UserExt.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/userExt/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(UserExt.tableId);
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("userId", data.userId);
            ajax.set("type", type);
            ajax.set("baseID", baseID);
            ajax.start();
        };
        Feng.confirm("是否删除?", operation);
    };

    // 渲染表格
    var tableResult = table.render({
        elem: '#' + UserExt.tableId,
        url: Feng.ctxPath + '/userExt/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        where:{"type": type,"baseID": baseID},
        cols: UserExt.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        UserExt.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        UserExt.openAddDlg();
    });

    // 导出excel
    $('#btnExp').click(function () {
        UserExt.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + UserExt.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            UserExt.openEditDlg(data);
        } else if (layEvent === 'delete') {
            UserExt.onDeleteItem(data);
        }
    });

    // UserExt.search();
});
