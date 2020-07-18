/**
 * @param pageNum
 * @param keywords
 */
function admin(pageNum,keywords) {

    $.get("findAllUser",{pageNum:pageNum,keywords:keywords},function (data) {
        let pageNum = data.pageNum;
        let pageSize = data.pageSize;
        /*<span id="pages">12</span>页
        <span id="total">132</span>条*/
        let total = data.total;
        let pages = data.pages;
        $("#total").html(total);
        $("#pages").html(pages);

        let lis = "";
        //首页
        let firstPage = '<li onclick="admin(1,\''+keywords+'\')"><a href="javascript:void(0)">首页</a></li>';
        lis += firstPage;
        //上一页
        let previous = pageNum - 1;
        if (previous <= 1){
            previous = 1;
        }
        let previousPage = '<li onclick="admin('+previous+',\''+keywords+'\')"><a href="javascript:void(0)"><<<</a></li>';
        lis += previousPage;

        /*展示10个页码*/
        let begin;
        let end;
        if (pages < 10){
            begin = 1;
            end = pages;
        }else {
            begin = pageNum-5;
            end = pageNum+4;
            //begin小于1时
            if (begin < 1){
                begin = 1;
                end = 10;
            }
            //end大于总页数时
            if (end>pages){
                end = pages;
                begin = end - 9;
            }
        }

        for (let i = begin; i <= end; i++){
            let li;
            if ( pageNum == i){
                li = '<li id="pageN" onclick="admin('+i+',\''+keywords+'\')"><a href="javascript:void(0)">'+i+'</a></li>';
            }else {
                li = '<li onclick="admin('+i+',\''+keywords+'\')"><a href="javascript:void(0)">'+i+'</a></li>';
            }
            lis += li;
        }

        //  1 2 3 4 5 6 7 8 9 10

        //下一页
        let next = pageNum + 1
        if (next >= pages){
            next = pages;
        }
        let nexPage = '<li onclick="admin('+next+',\''+keywords+'\')"><a href="javascript:void(0)">>>></a></li>'
        lis += nexPage;

        //尾页
        let lastPage = '<li onclick="admin('+pages+',\''+keywords+'\')"><a href="javascript:void(0)">尾页</a></li>'
        lis += lastPage;
        $("#navs").html(lis);

        let trs = "<tr>\n" +
            "                <th>编号</th>\n" +
            "                <th>用户名</th>\n" +
            "                <th>密码</th>\n" +
            "                <th>电话号码</th>\n" +
            "                <th>邮箱</th>\n" +
            "                <th>操作</th>\n" +
            "            </tr>";
        for (let i = 0; i < data.totalData.length; i++){
            let u = data.totalData[i];
            let tr = "<tr>\n" +
                "                <td>"+u.uid+"</td>\n" +
                "                <td>"+u.username+"</td>\n" +
                "                <td>"+u.password+"</td>\n" +
                "                <td>"+u.phone+"</td>\n" +
                "                <td>"+u.email+"</td>\n" +
                "                <td>\n" +
                "                    <input type=\"button\" value=\"修改\" class=\"up_btn\" onclick=\"updateUser(this)\">\n" +
                "                    <input type=\"button\" value=\"删除\" class=\"up_btn\" onclick=\"delUser(this)\">\n" +
                "                </td>\n" +
                "            </tr>";
            trs += tr;
        }
        $("#tab_data").html(trs);
       // console.log(trs);


        //定位到页面顶部
        window.scrollTo(0,0);

    });

}