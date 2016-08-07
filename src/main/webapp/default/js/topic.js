/**
 * Created by lee on 2016/5/16.
 */
$(function(){
    var um = UM.getEditor('replyText');

    $(".edui-btn-toolbar").append("<a class='submit-btn'>发表回复</a>");

    $(".submit-btn").click(function(){
        var replyBox = $("#reply-box");
        var param = {
            tid:replyBox.data("tid"),
            message: um.getContent()
        };
        var ppid = replyBox.data("ppid");
        if(ppid){
            param.ppid = ppid;
        }

        $.post(DA.ROOT+"/post/save.do", param, function (data) {
            console.log(data);
        });
    });


    $("body").delegate(".post-ta","click",function(){
        $("#reply-box").data("ppid",$(this).data("pid"));
    });


});