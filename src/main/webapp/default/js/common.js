
DA.pop = function (element,mode) {
    var body = $("body");
    var pop = $('<div class="pop" style="position: fixed; left: 0; top: 0; width: 100%; height: 100%; z-index: 99999;"></div>');
    if(mode){
        pop.append('<div class="pop-bg" style="position: absolute;left: 0;top: 0;width: 100%;height: 100%;background-color: #000;-moz-opacity:0.5;filter:alpha(opacity=50);opacity: 0.5;"></div>');
    }
    var box =$('<div class="pop-box" style="position: absolute;left: 0;top: 0;"></div>');

    element = $(element);
    element.show();
    box.append(element);
    pop.append(box);
    body.append(pop);
    var pw = pop.width(),ph = pop.height();
    box.css("left",(pw-element.width())/2+"px");
    box.css("top",(ph-element.height())/2+"px");
    return pop;
};

DA.alert = function(msg,cb){
    var box = $('<div class="da-box">' +
    '<div class="icon icon-info"></div>' +
    '<div class="text" style="text-align: center;">' +
    msg+
    '</div>' +
    '<div style="text-align: center;padding-top: 20px;">' +
    '<a class="btn close-btn">知道了</a>' +
    '</div>' +
    '</div>');
    var pop = DA.pop(box,true);
    box.find(".close-btn").click(function(){
        pop.remove();
        cb&&cb();
    });
};

DA.confirm = function(msg,okCB,cancelCB){
    var box = $('<div class="da-box">' +
    '<div class="icon icon-question"></div>' +
    '<div class="text" style="text-align: center;">' +
    msg+
    '</div>' +
    '<div style="text-align: center;padding-top: 20px;">' +
    '<a class="btn ok-btn">确定</a><i class="zi"></i><i class="zi"></i><a class="btn cancel-btn" style="background-color:#2c9d7a">取消</a>' +
    '</div>' +
    '</div>');
    var pop = DA.pop(box,true);
    box.find(".ok-btn").click(function(){
        pop.remove();
        okCB&&okCB();
    });
    box.find(".cancel-btn").click(function(){
        pop.remove();
        cancelCB&&cancelCB();
    });
};

DA.error = function(msg,cb){
    var box = $('<div class="da-box">' +
    '<div class="icon icon-no"></div>' +
    '<div class="text" style="text-align: center;">' +
    msg+
    '</div>' +
    '<div style="text-align: center;padding-top: 20px;">' +
    '<a class="btn close-btn">知道了</a>' +
    '</div>' +
    '</div>');
    var pop = DA.pop(box,true);
    box.find(".close-btn").click(function(){
        pop.remove();
        cb&&cb();
    });
};

DA.tip = function(msg,cb,time){
    time = time?time/1000:3;
    var box = $('<div class="da-box">' +
    '<div class="icon icon-yes"></div>' +
    '<div class="text" style="text-align: center;">' +
    msg+
    '(<span class="time">' +time+
    '</span>)</div>' +
    '</div>');
    var pop = DA.pop(box,false);
    setInterval(function(){
        if(time>0){
            time--;
            box.find(".time").text(time);
            return;
        }
        pop.remove();
        cb&&cb();
    },1000);
};