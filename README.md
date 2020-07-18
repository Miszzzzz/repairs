# repairs
# 后台报修平台的设计与实现

## 系统介绍

​		该系统使用SpringMVC+Spring+Mybatis三大主流框架作为主要基础。所有工程均采用前后端分离的技术，前端使 用layui框架为主，主要分为用户端和后勤端，后勤端又分为管理员端和维修 人员端，用户进行报修、评价、查看历史报修以及 报修进度，管理员端后台处理、报修事务处理，报修事务的指派与驳回、用户管理、统计查看、修改信息。维修人员端对报修订 单的接受和驳回，信息，报修单的完成，查看个人业绩以及评价。

## 技术栈

**Java**

**Spring**

**SpringMVC**

**Mybatis**

**JavaScript**

**CSS**

**HTML**

**layui**

**MySQL**

## 开发工具

**IDEA**

**Git**

**Maven**

**Navicat**

## 主要界面以及代码

![image](https://github.com/Miszzzzz/repairs/blob/master/images/image-20200718093735559.png)

![image-20200718095008201](C:\Users\jarn\AppData\Roaming\Typora\typora-user-images\image-20200718095008201.png)

![image-20200718095237089](C:\Users\jarn\AppData\Roaming\Typora\typora-user-images\image-20200718095237089.png)

#### 处理文件和平台输入项

```
@Override
    public Object saveMaintenaceAdnImage(HttpServletRequest request) {
        ResultInfo info = new ResultInfo();
        int x = 0, y = 0;
        List<FileItem> items = null;
        HashMap<String,Object> map = null;

        try {
            items = UploadUtils.dea(request);
            //存入表单普通输入项
            map = UploadUtils.ordinaryData(items, request);
            User user = (User)request.getSession().getAttribute(Constant.SESSION_USER);
            map.put("uid",user.getUid());
            //复制用户uId
            Maintenance maintenance = new Maintenance();
            BeanUtils.populate(maintenance, map);
            System.out.println(maintenance);
            //返回主键到Maintenace对象
            x = userDao.saveMaintenace(maintenance);

            //文件操作
            List<RepairsImage> images = UploadUtils.fileData(items, request, maintenance.getmId());
            y = userDao.saveImage(images);
        } catch (FileUploadException | UnsupportedEncodingException fileUploadException) {
            fileUploadException.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        if (x == 0 || y == 0) info.setFlag(false);
        return info;
    }
```

```
/**
     * 获取List<FileItem>集合
     * @param request
     * @return
     * @throws FileUploadException
     */
    public static List<FileItem> dea(HttpServletRequest request) throws FileUploadException {
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setHeaderEncoding("utf-8");
        return upload.parseRequest(request);
    }

    /**
     * 处理普通输入项
     * @param items
     * @param request
     * @return
     * @throws UnsupportedEncodingException
     */
    public static HashMap<String, Object> ordinaryData(List<FileItem> items, HttpServletRequest request) throws UnsupportedEncodingException {
        HashMap<String,Object> map = new HashMap<>();
        for (FileItem item:items
             ) {
            if (item.isFormField()){
                //普通输入项
                String key = item.getFieldName();
                map.put(key, item.getString("UTF-8"));
            }
        }
        return map;
    }

    /**
     * 处理上传文件
     * @param items
     * @param request
     * @param mId 对应保修单主键
     * @return
     */
    public static List<RepairsImage> fileData(List<FileItem> items, HttpServletRequest request, int mId){
        String realPath = request.getServletContext().getRealPath("/assets/images");
        File file = new File(realPath);
        if (!file.exists()){
            file.mkdirs();
        }
        List<RepairsImage> images = new ArrayList<>();
        for (FileItem item:items
             ) {
            if (!item.isFormField()){
                RepairsImage image = new RepairsImage();
                //文件名
                String fileName = item.getName();
                StringBuilder builder = new StringBuilder(realPath);
                builder.append(File.separator).
                        append(RandomUtil.getUuidStr()).
                        append(fileName.substring(fileName.lastIndexOf(".")));
                String path = builder.toString();

                //上传文件
                try (
                        InputStream in = item.getInputStream();
                        FileOutputStream fos = new FileOutputStream(path);
                ){
                    byte[] bytes = new byte[1024 * 1024];
                    int len;
                    while ((len = in.read(bytes)) != -1) {
                        fos.write(bytes, 0, len);
                    }
                    item.delete();
                    image.setmId(mId);
                    image.setUrl(path);
                    images.add(image);
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
        return images;
    }
```

#### 分页操作类

#### JavaBean

```
public class PageBean<T> {
    /**
     *页码  ...  第几页
     */
    private int pageNum;
    /**
     *页面大小 ... 一页多少数据
     */
    private int pageSize;
    /**
     *总数  ...多少条数据
     */
    private int total;
    /**
     *总页数
     */
    private int pages;
    /**
     * 查询数据
     */
    private List<T> totalData;

    public PageBean() {
    }

    public PageBean(int pageNum, int pageSize, int total, int pages, List<T> totalData) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.total = total;
        this.pages = pages;
        this.totalData = totalData;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public List<T> getTotalData() {
        return totalData;
    }

    public void setTotalData(List<T> totalData) {
        this.totalData = totalData;
    }
}
```

#### PaginationUtils

```
public PageBean<T> basePaginationData(BaseService baseService, int pageNum, int pageSize, String keyWords, String status, int uid){

        //数据总数
        int total = baseService.findTotal(keyWords, status, uid);

        if (total == 0) return null;

        //总页数
        int pages = total % pageSize == 0 ? total / pageSize : total / pageSize + 1;
        if (pageNum < 1){
            pageNum = 1;
        }
        if (pageNum > pages){
            pageNum = pages;
        }

        //下表索引值
        int index = (pageNum - 1) * pageSize;

        //查询数据
        List<T> totalData = baseService.findAll(index, pageSize, keyWords, status, uid);

        PageBean<T> pageBean = new PageBean<>(pageNum, pageSize, total,  pages, totalData);

        return pageBean;
    }
```

#### 指派维修

```
管理员指派维修
	*维修单指定维修工人，状态已派工
	*根据订单主键id
	update maintenance set manName = #{name}, manId = #{manId}, status = #{status} where mId = #{mId}
	*维修表，维修工人，状态在忙
	*根据维修工人主键id
	update maintainer set status = #{status} where  manid = #{manId};
	
```

