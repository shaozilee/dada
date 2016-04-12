/**
 * Created by lee on 15-1-26.
 */

$(function(){
    var um = UM.getEditor('myEditor');

    $(".post-btn").click(function() {
        $("#j-post>input[name=message]").val(um.getContent());
        $("#j-post").submit();
    });


});