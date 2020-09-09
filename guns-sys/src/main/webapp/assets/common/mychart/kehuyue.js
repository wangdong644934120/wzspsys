////查询当前时间
//$(function(){
//  var date = new Date();
//          var year = date.getFullYear();
//          var month = date.getMonth()+1;
//          var day = date.getDate();
////          var hours = date.getHours();
////          var minutes = date.getMinutes();
////          var seconds = date.getSeconds();
//          $("#time3").html(year+"-"+month+"-"+day+"&nbsp&nbsp");//+hours+":"+minutes+":"+seconds
//
//})
//查询客户总余额
$.ajax({
            url : '/dpzs/ze',
            dataType:"json",
            success:function(result) {
             $("#kehuyue").html(result.yeze);
            },
            error:function(error){
             $("#kehuyue").html('错误');
            }
        });