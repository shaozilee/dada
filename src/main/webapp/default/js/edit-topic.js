/**
 * Created by lee on 2016/5/16.
 */


$(function(){
    var ue = UE.getEditor('myEditor');
    ue.ready(function(){
        $(".edui-toolbar").append("<a class='submit-btn'>提交发布</a>");

        $(".submit-btn").click(function() {
            var param = {
                tid:$(".subject-input").data("tid"),
                subject: $(".subject-input").val(),
                message: ue.getContent()
            };

            if(!param.subject){
                DA.alert("标题不能为空哦！");
                return;
            }
            if(!param.message){
                DA.alert("帖子内容不能为空哦！");
                return;
            }

            $.post(DA.ROOT+"/topic/edit.do", param, function (resp) {
                resp = JSON.parse(resp);
                if(resp.code === "0000"){
                    DA.tip("编辑发布成功，3秒后回到首页!",function(){
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






});