/**
 * Created by lee on 15-12-6.
 */

$(function(){
    $(".login-btn").click(function(){
        var form = $("#j-login");
        var url =form.attr("action");
        $.ajax({
            url:url,
            data:form.serialize(),
            type:"POST",
            dataType:"json",
            success:function(data){
                console.log(data);
                if(data.status === 0){
                    var redirect = ctxPath+"/index.html";
                    var start = window.location.href.indexOf("redirect=");
                    if(start != -1){
                        redirect = window.location.href.substring(start+9);
                    }
                    window.location.href=redirect;
                }
            },
            error:function(err){
                console.log(err);
            }
        });

    });

});