layui.use(['table', 'admin', 'ax', 'ztree'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var $ZTree = layui.ztree;

    /**
     * 学生表管理
     */
    var Student = {
        tableId: "studentTable",
        condition: {
            classesid: ""
        }
    };

    /**
     * 初始化表格的列
     */
    Student.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'id', hide: true, title: '主键'},
            {field: 'code', sort: true, title: '学生学号'},
            {field: 'name', sort: true, title: '学生姓名'},
            {field: 'address', sort: true, title: '家庭住址'},
            {field: 'classesname', sort: true, title: '班级'},
            {align: 'center', toolbar: '#tableBar', title: '操作'}
        ]];
    };

    /**
     * 选择部门时
     */
    Student.onClickDept = function (e, treeId, treeNode) {
        Student.condition.classesid = treeNode.id;
        Student.search();
    };


    /**
     * 点击查询按钮
     */
    Student.search = function () {
        var queryData = {};
        queryData['name'] = $("#condition").val();
        queryData['classesid']=Student.condition.classesid;
        table.reload(Student.tableId, {
            where: queryData, page: {curr: 1}
        });
    };

    /**
     * 弹出添加对话框
     */
    Student.openAddDlg = function () {
        window.location.href = Feng.ctxPath + '/student/add';
    };

    /**
     * 导出excel按钮
     */
    Student.exportExcel = function () {
        var checkRows = table.checkStatus(Student.tableId);
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
    Student.openEditDlg = function (data) {
        window.location.href = Feng.ctxPath + '/student/edit?id=' + data.id;
    };

    /**
     * 点击删除
     *
     * @param data 点击按钮时候的行数据
     */
    Student.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/student/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(Student.tableId);
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
        elem: '#' + Student.tableId,
        url: Feng.ctxPath + '/student/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: Student.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        Student.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        Student.openAddDlg();
    });

    // 导出excel
    $('#btnExp').click(function () {
        Student.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + Student.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            Student.openEditDlg(data);
        } else if (layEvent === 'delete') {
            Student.onDeleteItem(data);
        }
    });

    //初始化左侧部门树
    var ztree = new $ZTree("deptTree", "/dept/tree");
    ztree.bindOnClick(Student.onClickDept);
    ztree.init();
});
