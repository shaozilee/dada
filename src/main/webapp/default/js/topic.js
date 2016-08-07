/**
 * Created by lee on 2016/5/16.
 */
$(function(){
    var um = UM.getEditor('replyText');

    $(".edui-btn-toolbar").append("<a class='submit-btn'>发表回复</a>");

    $(".submit-btn").click(function(){
        var replyBox = $("#replyBox");
        var param = {
            tid:replyBox.data("tid"),
            message: um.getContent()
        };
        var ppid = replyBox.data("ppid");
        if(ppid){
            param.ppid = ppid;
        }

        $.post(DA.ROOT+"/post/save.do", param, function (resp) {
            resp = JSON.parse(resp);
            if(resp.code === "0000"){
                if(ppid){
                    console.log("回复应该插入到具体的评论底部");
                }else{
                    var href = window.location.href;
                    href = href.substring(0,href.indexOf(".html"));
                    href = href.substring(0,href.lastIndexOf("-"))+"-1.html#postList";
                    window.location.href = href;
                    window.location.reload(true);
                }
            }else{
                DA.error(resp.msg);
            }
        });
    });


    $("body").delegate(".post-ta","click",function(){
        $("#reply-box").data("ppid",$(this).data("pid"));
    });


});