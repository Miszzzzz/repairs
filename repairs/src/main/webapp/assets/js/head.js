function head(){
    $.get("findUser",{},function (data) {
        //查询使用有登录信息
        if (data == null){
            location.href="login.html";
        }else {
            //加载侧边栏和头部
            $.get("home_user_header.html",function (data) {
                $("#header").html(data);
            });
            $.get("home_admin_nav.html",function (data) {
                $("#left_content").html(data);
            });
        }
    });
}



