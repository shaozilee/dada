/**
 * Created by lee on 2016/5/16.
 */
$(function(){
    var um = UM.getEditor('myEditor');

    $(".post-btn").click(function() {
        var param = {
            pid:$(this).data("pid"),
            subject: $(".title-input").val(),
            message: um.getContent()
        };

        $.post(DA.ROOT+"/topic/save.do", param, function (data) {
            console.log(data);
        });
    });


});