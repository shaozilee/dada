/**
 * Created by lee on 2016/5/16.
 */
$(function(){
    var um = UM.getEditor('myEditor');

    $(".post-btn").click(function() {
        var param = {
            id:$(this).data("tid"),
            title: $(".title-input").val(),
            content: um.getContent(),
            subject:"大杂烩",
            master:$("#J-master-img").attr("src")
        };

        $.post("postAction.jsp", param, function (data) {
            if(data.state === "0000"){
                Da.tip("恭喜，发布成功！<i class='zi'></i><span style='font-size: 12px;color: #666'>3秒后返回首页</span>",function(){
                    window.location.href="index.jsp";
                });
            }else{
                Da.error(data.msg);
            }
        });
    });


});