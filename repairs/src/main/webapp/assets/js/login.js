/**
 * 登录
 */

function login() {
    const userName = $("input[name='userName']").val();
    const password = $("input[name='password']").val();
    if (userName == 'undefined' || userName == ''){
        $("#error").html("请输入用户名");
        return;
    }
    if (password == 'undefined' || password == ''){
        $("#error").html("请输入密码");
        return;
    }
    $.post("login", $(".layui-form").serialize(), function (data) {
        console.log(data);
        if (data.length != 0) {
            //登录成功
            switch (data.rid) {
                case 1:
                    location.href = "user/home";
                    break;
                case 2:
                    location.href = "admin/home";
                    break;
                default:
                    break;
            }
        } else {
            $("#error").html("用户名或密码错误");
        }
    });
}
