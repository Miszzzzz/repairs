$(function () {
    $.get("/repairs/include/index/header.html",function (data) {
        $(".header").html(data);
    });
    $.get("/repairs/include/index/sidebar.html",function (data) {
        $(".container-right").html(data);
    });
});