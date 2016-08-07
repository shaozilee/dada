/**
 * Created by lee on 2016/5/16.
 */
$(function(){
    var um = UM.getEditor('myEditor');

    $(".edui-btn-toolbar").append("<a class='submit-btn'>提交发布</a>");

    $(".submit-btn").click(function() {
        var param = {
            subject: $(".subject-input").val(),
            message: um.getContent()
        };

        $.post(DA.ROOT+"/topic/save.do", param, function (resp) {
            resp = JSON.parse(resp);
            if(resp.code === "0000"){
                DA.tip("发布成功，3秒后回到首页!",function(){
                    window.location.href=DA.ROOT+"/index.html";
                },3000);
            }else{
                DA.error(resp.msg);
            }
        });
    });


});