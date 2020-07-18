package com.jarn.common.util;

import com.jarn.entity.RepairsImage;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @Author WZY
 **/
public class UploadUtils {


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
}
