layui.use(['table', 'admin', 'ax'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;

    var branch =  Feng.getUrlParam("branch");
    /**
     * 党支部人员表管理
     */
    var PartyBranchPerson = {
        tableId: "partyBranchPersonTable"
    };

    /**
     * 初始化表格的列
     */
    PartyBranchPerson.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'id', hide: true, title: 'id'},
            {field: 'branch', hide: true,sort: true, title: '党支部id'},
            {field: 'person', hide: true,sort: true, title: '人员id'},
            {field: 'name', sort: true, title: '姓名'},
            {field: 'phone', sort: true, title: '手机号'},
            {align: 'center', toolbar: '#tableBar', title: '操作'}
        ]];
    };

    /**
     * 点击查询按钮
     */
    PartyBranchPerson.search = function () {
        var queryData = {};
        queryData['name'] = $("#condition").val();
        queryData['branch'] = branch;
        table.reload(PartyBranchPerson.tableId, {
            where: queryData, page: {curr: 1}
        });
    };

    /**
     * 弹出添加对话框
     */
    PartyBranchPerson.openAddDlg = function () {
        window.location.href = Feng.ctxPath + '/partyBranchPerson/add?branch='+branch;
    };

    /**
     * 导出excel按钮
     */
    PartyBranchPerson.exportExcel = function () {
        var checkRows = table.checkStatus(PartyBranchPerson.tableId);
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
    PartyBranchPerson.openEditDlg = function (data) {
        window.location.href = Feng.ctxPath + '/partyBranchPerson/edit?id=' + data.id;
    };

    /**
     * 点击删除
     *
     * @param data 点击按钮时候的行数据
     */
    PartyBranchPerson.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/partyBranchPerson/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(PartyBranchPerson.tableId);
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
        elem: '#' + PartyBranchPerson.tableId,
        url: Feng.ctxPath + '/partyBranchPerson/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        where:{"branch": branch},
        cols: PartyBranchPerson.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        PartyBranchPerson.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        PartyBranchPerson.openAddDlg();
    });

    // 导出excel
    $('#btnExp').click(function () {
        PartyBranchPerson.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + PartyBranchPerson.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            PartyBranchPerson.openEditDlg(data);
        } else if (layEvent === 'delete') {
            PartyBranchPerson.onDeleteItem(data);
        }
    });


});
