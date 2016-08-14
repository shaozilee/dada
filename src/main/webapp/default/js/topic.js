/**
 * Created by lee on 2016/5/16.
 */
$(function(){
    var um = UM.getEditor('replyText',{autoClearinitialContent:true});
    var postUM = UM.getEditor('postEditor',{isShow:false,toolbars:null});

    $("#replyBox .edui-btn-toolbar").append("<a class='submit-btn'>发表回复</a>");

    $(".submit-btn").click(function(){
        var replyBox = $("#replyBox");
        var param = {
            tid:replyBox.data("tid"),
            message: um.getContent()
        };

        $.post(DA.ROOT+"/post/save.do", param, function (resp) {
            resp = JSON.parse(resp);
            if(resp.code === "0000"){
                var href = window.location.href;
                href = href.substring(0,href.indexOf(".html"));
                href = href.substring(0,href.lastIndexOf("-"))+"-1.html#postList";
                window.location.href = href;
                window.location.reload(true);
            }else{
                DA.error(resp.msg);
            }
        });
    });


    $("body").delegate(".post-ta","click",function(){
        postUM.$container.parents(".post-editor").css("display","none");
        postUM.$container.parents(".post-editor").find(".post-user-tip").remove();

        if(postUM.$container.parents(".post-post").find("li").length<=0){
            postUM.$container.parents(".post-post").removeClass("active");
        }


        var ppid = $(this).data("pid");
        var post = $("#post-"+ppid);
        var puserName = post.data("username");

        post.find(".post-post").addClass("active");
        post.find(".post-editor").css("display","block");
        var ppEditorContainer = post.find(".post-editor-container");
        postUM.$container.appendTo(ppEditorContainer);
        postUM.setWidth(ppEditorContainer.width());
        postUM.setShow();
        var tipHtml = $("<div class='post-user-tip'>回复 "+puserName+"：</div>");
        postUM.$container.find(".edui-editor-body").append(tipHtml);
        postUM.$container.find(".edui-body-container").css("text-indent",tipHtml.width());

        postUM.execCommand('cleardoc');

    });

    $("body").delegate(".ppost-btn","click",function(){
        var post = $(this).parents(".post");
        var param = {
            tid:$("#replyBox").data("tid"),
            message: postUM.getContent(),
            ppid: post.data("pid"),
            puid: post.data("uid"),
            puserName: post.data("username")
        };
        var ppostList = post.find(".post-post ul");
        postUM.execCommand('cleardoc');

        $.post(DA.ROOT+"/post/save.do", param, function (resp) {
            resp = JSON.parse(resp);
            if(resp.code === "0000"){
                ppostList.append("<li>"+resp.data.userName+"："+resp.data.message+"</li>");
            }else{
                DA.error(resp.msg);
            }
        });


    });


});