layui.use(['layer', 'ax', 'form', 'laydate', 'element', 'table'], function () {
    var $ = layui.$;
    var $ax = layui.ax;
    var layer = layui.layer;
    var form = layui.form;
    var laydate = layui.laydate;
    var element = layui.element;
    var table = layui.table;

    var Code = {
        tableNames: "",
        dbId: "",
        tables: {}
    };

    $('#code_gen').click(function () {
        window.location.href = Feng.ctxPath + "/gen";
    });

    $('#db_config').click(function () {
        window.location.href = Feng.ctxPath + "/db";
    });

    $('#add_db').click(function () {
        window.location.href = Feng.ctxPath + "/db/add";
    });

    table.render({
        elem: '#dbTableList'
        , url: Feng.ctxPath + '/db/tableList'
        , page: false
        , cols: [[
            {type: 'checkbox'}
            , {field: 'tableName', title: '表的名称'}
            , {field: 'tableComment', title: '表的名称注释'}
            , {toolbar: '#tableBar', title: '操作'}
        ]]
    });

    table.on('checkbox(dbTableList)', function (obj) {
        var checkStatus = table.checkStatus('dbTableList');
        var tableNames = "";
        for (var tableItem in checkStatus.data) {
            tableNames += "CAT" + checkStatus.data[tableItem].tableName;
        }
        Code.tableNames = tableNames;

        //选中行后，显示对应的操作按钮
        if (obj.type === "all") {
            if (obj.checked) {
                $("a[name='con-btn']").removeClass("layui-hide");
            } else {
                $("a[name='con-btn']").addClass("layui-hide")
            }
        } else {
            if (obj.checked) {
                $("#" + obj.data.tableName + "_opt").removeClass("layui-hide");
            } else {
                $("#" + obj.data.tableName + "_opt").addClass("layui-hide");
            }
        }

    });

    table.on('tool(dbTableList)', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'conditionEdit') {
            top.layer.open({
                type: 2,
                title: '选择字段',
                area: ['400px', '600px'],
                content: Feng.ctxPath + '/tableFields?dbId=' + Code.dbId + "&tableName=" + data.tableName
            });
        }
    });

    form.on('select(dataSourceId)', function (data) {
        var dbId = data.value;
        Code.dbId = dbId;
        table.reload("dbTableList", {where: {dbId: dbId}});
    });

    $('#execute').on('click', function () {

        var author = $("#author").val();
        var proPackage = $("#proPackage").val();
        var removePrefix = $("#removePrefix").val();
        var dataSourceId = $("#dataSourceId").val();
        var modularName = $("#modularName").val();

        window.location.href = Feng.ctxPath + "/execute?dataSourceId=" + dataSourceId + "&author="
            + author + "&proPackage=" + proPackage + "&removePrefix=" + removePrefix + "&tables=" + Code.tableNames + "&modularName=" + modularName;
    });

});
