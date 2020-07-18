package com.jarn.service.impl;

import com.jarn.common.constant.Constant;
import com.jarn.common.util.UploadUtils;
import com.jarn.dao.UserDao;
import com.jarn.entity.Maintenance;
import com.jarn.entity.RepairsImage;
import com.jarn.entity.ResultInfo;
import com.jarn.entity.User;
import com.jarn.service.UserService;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;

/**
 * @Author WZY
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User login(String userName, String password) {
        return userDao.login(userName, password);
    }

    @Override
    public User findUserByUid(int uid) {
        return userDao.findUserByUid(uid);
    }

    @Override
    public void updateUser(User user) {
        userDao.updateUser(user);
    }

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

    @Override
    public int findTotal(String keyWords, String status, int uid) {
        return userDao.findTotal(keyWords, status, uid);
    }

    @Override
    public List<Maintenance> findAll(int index, int pageSize, String keyWords, String status, int uid) {
        return userDao.findAll(index, pageSize, keyWords, status, uid);
    }

    @Override
    public Maintenance findMaintenanceByMid(int mId) {
        return userDao.findMaintenanceByMid(mId);
    }

    @Override
    public int evaluation(String evaluation, int mId) {
        return userDao.evaluation(evaluation, mId);
    }
}
