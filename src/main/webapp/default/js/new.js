/**
 * Created by lee on 2016/5/16.
 */
$(function(){
    var um = UE.getEditor('myEditor');

    $(".edui-btn-toolbar").append("<a class='submit-btn'>提交发布</a>");

    $(".submit-btn").click(function() {
        var param = {
            subject: $(".subject-input").val(),
            message: um.getContent()
        };

        if(!param.subject){
            DA.alert("标题不能为空哦！");
            return;
        }
        if(!param.message){
            DA.alert("帖子内容不能为空哦！");
            return;
        }

        $.post(DA.ROOT+"/topic/save.do", param, function (resp) {
            resp = JSON.parse(resp);
            if(resp.code === "0000"){
                DA.tip("发布成功，3秒后回到首页!",function(){
                    window.location.href=DA.ROOT+"/index.html";
                },3000);
            }else if(resp.code === "1000"){
                DA.alert("标题和内容不能为空哦！");
            }else{
                DA.error(resp.msg);
            }

        });
    });


});