/**
 *
 * @param pageNum 页码  1，2，3...
 * @param keyWords 关键字  主要查找姓名
 * @param profession 职业类型  水电维修....
 * @param status 状态 空闲   在忙
 */
function mainEr(pageNum,keyWords,profession,status) {

    $.get("mainEr",{pageNum:pageNum,keyWords:keyWords,profession:profession,status:status},function (data) {

        let pageNum = data.pageNum;
        let pageSize = data.pageSize;
        let total = data.total;
        let pages = data.pages;
        $("#total").html(total);
        $("#pages").html(pages);

        let lis = "";
        //首页
        let firstPage = '<li onclick="mainEr(1,\''+keyWords+'\',\''+profession+'\',\''+status+'\')"><a href="javascript:void(0)">首页</a></li>';
        lis += firstPage;
        //上一页
        let previous = pageNum - 1;
        if (previous <= 1){
            previous = 1;
        }
        let previousPage = '<li onclick="mainEr('+previous+',\''+keyWords+'\',\''+profession+'\',\''+status+'\')"><a href="javascript:void(0)"><<<</a></li>'
        lis += previousPage;

        /*展示5个页码*/
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
                li = '<li id="pageN" onclick="mainEr('+i+',\''+keyWords+'\',\''+profession+'\',\''+status+'\')"><a href="javascript:void(0)">'+i+'</a></li>';
            }else {
                li = '<li onclick="mainEr('+i+',\''+keyWords+'\',\''+profession+'\',\''+status+'\')"><a href="javascript:void(0)">'+i+'</a></li>';
            }
            lis += li;
        }

        //  1 2 3 4 5

        //下一页
        let next = pageNum + 1
        if (next >= pages){
            next = pages;
        }
        let nexPage = '<li onclick="mainEr('+next+',\''+keyWords+'\',\''+profession+'\',\''+status+'\')"><a href="javascript:void(0)">>>></a></li>'
        lis += nexPage;

        //尾页
        let lastPage = '<li onclick="mainEr('+pages+',\''+keyWords+'\',\''+profession+'\',\''+status+'\')"><a href="javascript:void(0)">尾页</a></li>'
        lis += lastPage;
        $("#navs").html(lis);

        let trs = "";
        for (let i = 0; i < data.totalData.length; i++){
            let totalDatum = data.totalData[i];
            let tr = "<tr>\n" +
                "                    <td>"+totalDatum.manid+"</td>\n" +
                "                    <td>"+totalDatum.username+"</td>\n" +
                "                    <td>"+totalDatum.password+"</td>\n" +
                "                    <td>"+totalDatum.phone+"</td>\n" +
                "                    <td>"+totalDatum.name+"</td>\n" +
                "                    <td>"+totalDatum.profession+"</td>\n" +
                "                    <td>"+totalDatum.status+"</td>\n" +
                "                </tr>";
            trs += tr;
        }
        console.log(trs);

        $(".tab-items").html(trs);

        //定位到页面顶部
        window.scrollTo(0, 0);
    });
}