/**
 * Created by lee on 2016/5/16.
 */
$(function(){
    var um = UM.getEditor('replyText');

    $(".edui-btn-toolbar").append("<a class='submit-btn'>发表回复</a>");

    $(".submit-btn").click(function(){
        var param = {
            tid:$(".reply-box").data("tid"),
            message: um.getContent()
        };

        $.post(DA.ROOT+"/post/save.do", param, function (data) {
            console.log(data);
        });
    });


});