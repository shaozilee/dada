/**
 * Created by lee on 2016/5/16.
 */
$(function(){
    var um = UM.getEditor('replyText',{autoClearinitialContent:true});
    var postUM = UM.getEditor('postEditor',{isShow:false,toolbars:null,initialStyle:'.edui-editor-body .edui-body-container p{line-height:1.5}'});

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

        if(!post.hasClass("post"))post = post.parents(".post");

        post.find(".post-post").addClass("active");
        post.find(".post-editor").css("display","block");
        post.find(".ppost-btn").data("pid",ppid);

        var ppEditorContainer = post.find(".post-editor-container");
        var tipHtml = $("<div class='post-user-tip'>回复 "+puserName+"：</div>");
        ppEditorContainer.append(tipHtml);
        ppEditorContainer.css("padding-left",tipHtml.width());

        postUM.$container.appendTo(ppEditorContainer);
        postUM.setWidth(ppEditorContainer.width());
        postUM.setShow();
        postUM.execCommand('cleardoc');

    });

    $("body").delegate(".ppost-btn","click",function(){
        var pid = $(this).data("pid");
        var ppost = $("#post-"+pid);
        var post = $(this).parents(".post");
        var param = {
            tid:$("#replyBox").data("tid"),
            message: postUM.getContent(),
            ppid: post.data("pid"),
            puid: ppost.data("uid"),
            puserName: ppost.data("username")
        };
        postUM.execCommand('cleardoc');

        var ppostList = post.find(".post-post ul");
        $.post(DA.ROOT+"/post/save.do", param, function (resp) {
            resp = JSON.parse(resp);
            if(resp.code === "0000"){
                var tmpl = '<li class="clearfix" ';
                tmpl+=('id="post-'+resp.data.pid+'" data-pid="'+resp.data.pid+'" data-username="'+resp.data.userName+'" data-uid="'+resp.data.uid+'"');
                tmpl+='><div class="puser">';
                tmpl+=('<span class="uname">'+resp.data.userName+'</span>');
                if(resp.data.puid != post.data("uid")){
                    tmpl+=('&nbsp;回复&nbsp;<span class="uname">'+resp.data.puserName+'</span>');
                }
                tmpl+='：</div><div class="pmessage">';
                tmpl+=resp.data.message;
                tmpl+='</div>';
                tmpl+='<span class="ppost-date">'+resp.data.dateLine+'</span>';
                tmpl+=('<a class="post-ta icon-ppost ppost-ta" data-pid="'+resp.data.pid+'"></a></li>');

                ppostList.append(tmpl);
            }else{
                DA.error(resp.msg);
            }
        });


    });


});