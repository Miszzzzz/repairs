/**
 *
 * @param pageNum 页码
 * @param keywords 关键字
 * @param uid 用户uid
 */
function item(pageNum,keywords) {

    $.get("pagination",{pageNum:pageNum,keywords:keywords},function (data) {

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
        let firstPage = '<li onclick="item(1,\''+keywords+'\')"><a href="javascript:void(0)">首页</a></li>';
        lis += firstPage;
        //上一页
        let previous = pageNum - 1;
        if (previous <= 1){
            previous = 1;
        }
        let previousPage = '<li onclick="item('+previous+',\''+keywords+'\')"><a href="javascript:void(0)"><<<</a></li>'
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
                li = '<li id="pageN" onclick="item('+i+',\''+keywords+'\')"><a href="javascript:void(0)">'+i+'</a></li>';
            }else {
                li = '<li onclick="item('+i+',\''+keywords+'\')"><a href="javascript:void(0)">'+i+'</a></li>';
            }
            lis += li;
        }

        //  1 2 3 4 5 6 7 8 9 10

        //下一页
        let next = pageNum + 1
        if (next >= pages){
            next = pages;
        }
        let nexPage = '<li onclick="item('+next+',\''+keywords+'\')"><a href="javascript:void(0)">>>></a></li>'
        lis += nexPage;

        //尾页
        let lastPage = '<li onclick="item('+pages+',\''+keywords+'\')"><a href="javascript:void(0)">尾页</a></li>'
        lis += lastPage;
        $("#navs").html(lis);

        //页面展示数据
        let dataItem = "";
        let items = "";
        for (let i = 0; i < data.totalData.length; i++){
            let mains = data.totalData[i];
            console.log("开始时间="+mains.startdate);
            console.log("完工时间="+mains.enddate);
            console.log("评价="+mains.evaluation);
            console.log("=======================");
            let item = "";
            let repair_start = "<div class=\"repair_start\">\n" +
                "                    <span>报修人:"+mains.name+"</span>\n" +
                "                    <span>报修时间:"+mains.repairsDate+"</span>\n" +
                "                    <span>预约时间:"+mains.appointmentDate+"</span>\n" +
                "                    <span>报修地址:"+mains.address+"-"+mains.detailAddress+"</span>\n" +
                "                    <span>报修内容:"+mains.repairsDetail+"</span>\n" +
                "                </div>";
            item += repair_start;
            let repair_process = "<div class=\"repair_process\"></div>";
            if (mains.startDate == null){
                repair_process = "<div class=\"repair_process\">\n" +
                    "                    <span class='startDate' style='display: none'>派工时间:"+mains.startDate+"</span>\n" +
                    "                    <span class='mainName' style='display: none'>派工对象:"+mains.manName+"</span>\n" +
                    "                </div>";
            }else {
                repair_process = "<div class=\"repair_process\">\n" +
                    "                    <span class='startDate'>派工时间:"+mains.startDate+"</span>\n" +
                    "                    <span class='mainName'>派工对象:"+mains.manName+"</span>\n" +
                    "                </div>";
            }
            item += repair_process;
            let repair_evaluation = "<div class=\"repair_evaluation\"></div>"
            if (mains.endDate == null){
                repair_evaluation = "<div class=\"repair_evaluation\">\n" +
                    "                    <span class='endData' style='display: none'>完工时间:"+mains.endDate+"</span>\n" +
                    "                    <span class='evaluation' style='display: none'>评价:"+mains.evaluation+"</span>\n" +
                    "                </div>";
            }else {
                if (mains.evaluation != null){
                    repair_evaluation = "<div class=\"repair_evaluation\">\n" +
                        "                    <span class='endData'>完工时间:"+mains.endDate+"</span>\n" +
                        "                    <span class='evaluation'>评价:"+mains.evaluation+"</span>\n" +
                        "                </div>";
                }else {
                    repair_evaluation = "<div class=\"repair_evaluation\">\n" +
                        "                    <span class='endData'>完工时间:"+mains.endDate+"</span>\n" +
                        "                    <span class='evaluation'>评价:未评价,点击详情进行评价</span>\n" +
                        "                </div>";
                }
            }
            item += repair_evaluation;
            let repair_end = "<div class=\"repair_end\">\n" +
                "                    <span class='end'>已完成</span>\n" +
                "                    <a href=\"info?mid="+mains.mId+"\">详情</a>\n" +
                "                </div>";
            if (mains.startDate == null && mains.endDate == null){
                repair_end = "<div class=\"repair_end\">\n" +
                    "                    <span class='end'>审核中</span>\n" +
                    "                    <a href=\"info?mid="+mains.mId+"\">详情</a>\n" +
                    "                </div>";
            }
            if (mains.startDate != null && mains.endDate == null){
                repair_end = "<div class=\"repair_end\">\n" +
                    "                    <span class='end'>维修中</span>\n" +
                    "                    <a href=\"info?mId="+mains.mId+"\">详情</a>\n" +
                    "                </div>";
            }
            item += repair_end;
            items += "<div class='item'>"+item+"</div>";

        }
        $(".dataItems").html(items);
        //定位到页面顶部
        window.scrollTo(0,0);

    });

}