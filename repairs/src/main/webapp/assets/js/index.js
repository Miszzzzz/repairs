/**W
 *
 * @param pageNum
 * @param keywords
 */
function index(pageNum,keyWords,status) {

    $.get("index",{pageNum:pageNum,keyWords:keyWords,status:status},function (data) {

        let pageNum = data.pageNum;
        let pageSize = data.pageSize;
        let total = data.total;
        let pages = data.pages;
        $("#total").html(total);
        $("#pages").html(pages);

        let lis = "";
        //首页
        let firstPage = '<li onclick="index(1,\''+keyWords+'\',\''+status+'\')"><a href="javascript:void(0)">首页</a></li>';
        lis += firstPage;
        //上一页
        let previous = pageNum - 1;
        if (previous <= 1){
            previous = 1;
        }
        let previousPage = '<li onclick="index('+previous+',\''+keyWords+'\',\''+status+'\')"><a href="javascript:void(0)"><<<</a></li>'
        lis += previousPage;

        /*展示5个页码*/
        let begin;
        let end;
        if (pages < 5){
            begin = 1;
            end = pages;
        }else {
            begin = pageNum-2;
            end = pageNum+2;
            //begin小于1时
            if (begin < 1){
                begin = 1;
                end = 5;
            }
            //end大于总页数时
            if (end>pages){
                end = pages;
                begin = end - 4;
            }
        }

        for (let i = begin; i <= end; i++){
            let li;
            if ( pageNum == i){
                li = '<li id="pageN" onclick="index('+i+',\''+keyWords+'\',\''+status+'\')"><a href="javascript:void(0)">'+i+'</a></li>';
            }else {
                li = '<li onclick="index('+i+',\''+keyWords+'\',\''+status+'\')"><a href="javascript:void(0)">'+i+'</a></li>';
            }
            lis += li;
        }

        //  1 2 3 4 5

        //下一页
        let next = pageNum + 1
        if (next >= pages){
            next = pages;
        }
        let nexPage = '<li onclick="index('+next+',\''+keyWords+'\',\''+status+'\')"><a href="javascript:void(0)">>>></a></li>'
        lis += nexPage;

        //尾页
        let lastPage = '<li onclick="index('+pages+',\''+keyWords+'\',\''+status+'\')"><a href="javascript:void(0)">尾页</a></li>'
        lis += lastPage;
        $("#navs").html(lis);

        let trs = "";
        for (let i = 0; i < data.totalData.length; i++){
            let totalDatum = data.totalData[i];
            let detailin = totalDatum.detailin;
            if (detailin.length >= 16){
                detailin = detailin.substring(0,14)+"...";
            }
            let tr = "<tr>\n" +
                "                        <td>"+totalDatum.repairproject+"</td>\n" +
                "                        <td>"+totalDatum.repairdate+"</td>\n" +
                "                        <td>"+detailin+"</td>\n" +
                "                        <td>"+totalDatum.status+"</td>\n" +
                "                    </tr>";
            trs += tr;
        }

        $(".item-body").html(trs);

        //定位到页面顶部
        window.scrollTo(0, 0);
    });
}