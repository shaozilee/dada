/**
 * Created by lee on 2016/5/16.
 */
$(function () {

    $(".focus-input").focus(function () {
        if($(this).hasClass("check-red")){
            $("#errorMsg").text("");
            $(this).removeClass("check-red");
            $(this).removeClass("check-green");
        }
    });

    $("#loginBtn").click(function(){
        if($("#email").hasClass("check-red") || $("#password").hasClass("check-red") )return;

        var param = {
            email:$("#email").val(),
            password: $("#password").val()
        };

        if(!param.email){
            $("#errorMsg").text("登录账号不能为空！");
            $("#email").addClass("check-red");
            return;
        }
        if(!param.password){
            $("#errorMsg").text("登录密码不能为空！");
            $("#password").addClass("check-red");
            return;
        }

        $.post(DA.ROOT+"/doLogin.do", param, function (resp) {
            resp = JSON.parse(resp);
            if(resp.code === "0000"){
                window.location.replace(redirect);
            }else if(resp.code === "3001"){
                $("#errorMsg").text("登录信息不正确，请重新输入！");
            }else{
                $("#errorMsg").text(resp.msg);
            }
        });
    });

});