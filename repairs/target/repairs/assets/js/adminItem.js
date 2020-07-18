/**W
 *
 * @param pageNum
 * @param keywords
 */
function adminItem(pageNum,keyWords,status) {

    $.get("adminMain",{pageNum:pageNum,keyWords:keyWords,status:status},function (data) {

        if (keyWords == null){
            keyWords = "";
        }

        if (status == null){
            status = "";
        }
        let pageNum = data.pageNum;
        let pageSize = data.pageSize;
        let total = data.total;
        let pages = data.pages;
        $("#total").html(total);
        $("#pages").html(pages);

        let lis = "";
        //首页
        let firstPage = '<li onclick="adminItem(1,\''+keyWords+'\',\''+status+'\')"><a href="javascript:void(0)">首页</a></li>';
        lis += firstPage;
        //上一页
        let previous = pageNum - 1;
        if (previous <= 1){
            previous = 1;
        }
        let previousPage = '<li onclick="adminItem('+previous+',\''+keyWords+'\',\''+status+'\')"><a href="javascript:void(0)"><<<</a></li>'
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
                li = '<li id="pageN" onclick="adminItem('+i+',\''+keyWords+'\',\''+status+'\')"><a href="javascript:void(0)">'+i+'</a></li>';
            }else {
                li = '<li onclick="adminItem('+i+',\''+keyWords+'\',\''+status+'\')"><a href="javascript:void(0)">'+i+'</a></li>';
            }
            lis += li;
        }

        //  1 2 3 4 5 6 7 8 9 10

        //下一页
        let next = pageNum + 1
        if (next >= pages){
            next = pages;
        }
        let nexPage = '<li onclick="adminItem('+next+',\''+keyWords+'\',\''+status+'\')"><a href="javascript:void(0)">>>></a></li>'
        lis += nexPage;

        //尾页
        let lastPage = '<li onclick="adminItem('+pages+',\''+keyWords+'\',\''+status+'\')"><a href="javascript:void(0)">尾页</a></li>'
        lis += lastPage;
        $("#navs").html(lis);

        let trs = "<tr>\n" +
            "                    <th>报修人</th>\n" +
            "                    <th>报修地址</th>\n" +
            "                    <th>报修类型</th>\n" +
            "                    <th>报修时间</th>\n" +
            "                    <th>预约时间</th>\n" +
            "                    <th>完成时间</th>\n" +
            "                    <th>状态</th>\n" +
            "                    <th>详情</th>\n" +
            "                </tr>";
        for (let i = 0; i < data.totalData.length; i++){
            let totalDatum = data.totalData[i];
            console.log(totalDatum);
            let tr = "<tr>\n" +
                "                    <td style='display: none'>"+totalDatum.mId+"</td>\n" +
                "                    <td>"+totalDatum.name+"</td>\n" +
                "                    <td>"+totalDatum.address+"-"+totalDatum.detailAddress+"</td>\n" +
                "                    <td>"+totalDatum.repairsType+"</td>\n" +
                "                    <td>"+totalDatum.repairsDate+"</td>\n" +
                "                    <td>"+totalDatum.appointmentDate+"</td>\n" +
                "                    <td>"+totalDatum.endDate+"</td>\n" +
                "                    <th>"+totalDatum.status+"</th>\n" +
                "                    <td><button type=\"button\" class=\"layui-btn\" onclick=\"updateMain(this)\">详情</button></td>\n" +
                "                </tr>";
            trs += tr;
        }

        $(".mainTable").html(trs);

            //定位到页面顶部
        window.scrollTo(0, 0);
        });
}