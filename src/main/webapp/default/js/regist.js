/**
 * Created by lee on 2016/5/16.
 */
$(function () {

    $("#email").blur(function () {
        var $this = $(this);
        var val = $this.val();
        if (!val)return;

        $.post(DA.ROOT + "/doCheckEmail.do", {email: val}, function (resp) {
            resp = JSON.parse(resp);
            if (resp.code == "0000") {
                $this.addClass("check-green");
            } else if (resp.code == "2001") {
                $("#errorMsg").text("邮箱已经被注册了，请重新输入！");
                $this.addClass("check-red");
            } else {
                $("#errorMsg").text("验证邮箱错误，请稍后再试！");
            }
        });

    });

    $("#userName").blur(function () {
        var $this = $(this);
        var val = $this.val();
        if (!val)return;

        $.post(DA.ROOT + "/doCheckUserName.do", {userName: val}, function (resp) {
            resp = JSON.parse(resp);
            if (resp.code == "0000") {
                $this.addClass("check-green");
            } else if (resp.code == "2002") {
                $("#errorMsg").text("用户昵称已经被注册了，请重新输入！");
                $this.addClass("check-red");
            } else {
                $("#errorMsg").text("验证邮箱错误，请稍后再试！");
            }
        });

    });

    $("#password").blur(function () {
        var $this = $(this);
        var val = $this.val();
        if (!val)return;
        if(val.length<6){
            $("#errorMsg").text("密码长度最少为6个字符，请重新输入！");
            $this.addClass("check-red");
        }

        $this.addClass("check-green");

    });

    $(".focus-input").focus(function () {
        if($(this).hasClass("check-red")){
            $("#errorMsg").text("");
            $(this).removeClass("check-red");
            $(this).removeClass("check-green");
        }
    });


    $("#registBtn").click(function () {
        if($("#email").hasClass("check-red") || $("#password").hasClass("check-red") || $("#userName").hasClass("check-red") )return;

        var param = {
            email: $("#email").val(),
            userName: $("#userName").val(),
            password: $("#password").val()
        };

        if(!param.email){
            $("#errorMsg").text("注册邮箱不能为空！");
            $("#email").addClass("check-red");
            return;
        }
        if(!param.password){
            $("#errorMsg").text("注册密码不能为空！");
            $("#password").addClass("check-red");
            return;
        }
        if(!param.userName){
            $("#errorMsg").text("注册昵称不能为空！");
            $("#userName").addClass("check-red");
            return;
        }




        $.post(DA.ROOT + "/doRegist.do", param, function (resp) {
            resp = JSON.parse(resp);
            if (resp.code === "0000") {
                $("#mainPanel").removeClass("show");
                $("#resultPanel").addClass("show");
                $("#resultPanel .msg").text("恭喜您注册成功！");
            } else if(resp.code === "1000"){
                $("#errorMsg").text("注册信息不能为空！");
            } else if (resp.code == "2002") {
                $("#errorMsg").text("用户昵称已经被注册了，请重新输入！");
                $("#userName").addClass("check-red");
            } else if (resp.code == "2001") {
                $("#errorMsg").text("邮箱已经被注册了，请重新输入！");
                $("#email").addClass("check-red");
            } else {
                $("#errorMsg").text(resp.msg);
            }
        });
    });


});