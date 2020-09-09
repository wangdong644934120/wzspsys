////查询当前时间
//$(function(){
//  var date = new Date();
//          var year = date.getFullYear();
//          var month = date.getMonth()+1;
//          var day = date.getDate();
//          $("#curTime").html(year+"-"+month+"-"+day);
//
//})
//查询客户总人数
$.ajax({
            url : '/dpzs/ze',
            dataType:"json",
            success:function(result) {
            $("#kehu").html(result.rze);
            },
            error:function(error){
             $("#kehu").html('错误');
            }
        });