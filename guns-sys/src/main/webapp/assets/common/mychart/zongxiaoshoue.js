 $(function() {
            $.ajax({
                type: "post",
                url: "/dpzs/allXiaoShouECX",
                data : {},
                dataType: "json",
                success: function(result) {
                var data = result.data[0].xsze;
                    $("#jine").html(data);		//绑定数据
		    $(".jine").val(data);		//给input表单绑定数据
                },
		error: function(error) {
                $("#jine").html('错误');
                }
});
        });
//                       //页面加载开始执行
//                    window.onload = function() {
//                            //获取元素id
//                            var show = document.getElementById("time");
//                            //设置定时器1s执行一次
//                            setInterval(function() {
//                                var time = new Date();   // 程序计时的月从0开始取值后+1
//                                var m = time.getMonth() + 1;
//                                var t = time.getFullYear() + "-" + m + "-"
//                                    + time.getDate() + " " + time.getHours() + ":"
//                                    + time.getMinutes() + ":" + time.getSeconds();
//                                show.innerHTML = t;
//                            }, 1000);
//                        };
