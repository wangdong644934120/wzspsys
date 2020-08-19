/**
 * 字典下拉
 *
 */
layui.define(['jquery','form'], function (exports) {
    var $ = layui.jquery;
    var dict={
        init:function (url) {
            $("select[dict]").each(function(){
                var handler = function ($select) {
                    this.$select=$select;
                };
                handler.prototype.create=function(){
                    var container=this.$select;
                    $.ajax({
                        url: url,
                        dataType: "json",
                        async: false,
                        data: {
                            "dictTypeId":container.attr("dict")
                        },
                        success: function (list) {
                            if(list==null||list.length==0){
                                //添加无
                            }else{
                                container.empty();
                                for(var i=0;i<list.length;i++){
                                    container.append("<option value=\""+list[i].dictId+"\">"+list[i].name+"</option>");
                                }
                                layui.form.render('select');
                            }
                        }
                    });
                };
                new handler($(this)).create();
            });
        },
        val:function(id,val){
            $("#"+id).val(val);
            layui.form.render('select');
        }
    };
    //layui.link(layui.cache.base + 'dropdown/dropdown.css');
    dict.init("/dict/getDropdown");
    exports('dict', dict);
});
