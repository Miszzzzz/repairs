function userLoad() {
    $.get("findUser", {}, function (data) {
        //判断用户是否登录
        if (data == null || data == undefined) {
            //没有查询到用户登录信息
            window.location.href = "login.html";
            return;
        } else {
            //头部 和  导航栏
            //查询到用户信息  发生ajax请求 加载主页内容
            $.get("home_user_left_content.html", function (data) {
                $("#left_content").html(data);
            });
            $.get("home_user_header.html", function (data) {
                $("#header").html(data)
            });
        }
    });
}


