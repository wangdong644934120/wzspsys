//$(function(){
//  var date = new Date();
//          var year = date.getFullYear();
//          var month = date.getMonth()+1;
//          $("#time2").html(year+"-"+month);
//
//})
$.ajax({
            url : "/dpzs/rzjs",
            dataType:"json",
            success:function(result) {
//                alert("客户增加数："+result.rzjs);
                $("#xinkehu").html(result.rzjs);

            },
            error:function(result){
                $("#xinkehu").html('错误');
            }
        });