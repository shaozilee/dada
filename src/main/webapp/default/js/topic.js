/**
 * Created by lee on 2016/5/16.
 */
$(function(){

    var um = UE.getEditor('replyText',{autoFloatEnabled:false,autoClearinitialContent:true,enableAutoSave:false,autoSyncData:false});
    var postUM = UE.getEditor('postEditor',{autoFloatEnabled:false,toolbars:[['emotion']],lineheight:"1.5",enableAutoSave:false,autoSyncData:false});

    um.ready(function(){

        $("#replyBox .edui-toolbar").append("<a class='submit-btn'>发表回复</a>");

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
    });



    $("body").delegate(".post-ta","click",function(){
        console.log("11111");
        var container = $(postUM.container);
        container.parents(".post-editor").css("display","none");
        container.parents(".post-editor").find(".post-user-tip").remove();

        if(container.parents(".post-post").find("li").length<=0){
            container.parents(".post-post").removeClass("active");
        }

        console.log("22222");
        var ppid = $(this).data("pid");
        var post = $("#post-"+ppid);
        var puserName = post.data("username");

        if(!post.hasClass("post"))post = post.parents(".post");

        post.find(".post-post").addClass("active");
        post.find(".post-editor").css("display","block");
        post.find(".ppost-btn").data("pid",ppid);
        console.log("33333");
        var ppEditorContainer = post.find(".post-editor-container");
        var tipHtml = $("<div class='post-user-tip'>回复 "+puserName+"：</div>");
        ppEditorContainer.append(tipHtml);
        ppEditorContainer.css("padding-left",tipHtml.width());
        container.appendTo(ppEditorContainer);
        console.log("4444");
        //postUM.execCommand('cleardoc');
        console.log("55555");

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
        //postUM.execCommand('cleardoc');

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

    $("body").delegate(".icon-ppost-face","click",function(){
        postUM.execCommand("emotion");
    });


});

$(".topic-title h1").each(DA.renderTag);