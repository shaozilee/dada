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

        $.post(DA.ROOT+"/topic/save.do", param, function (data) {
            console.log(data);
        });
    });


});