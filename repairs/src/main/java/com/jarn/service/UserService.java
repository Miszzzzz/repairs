package com.jarn.service;

import com.jarn.dao.UserDao;
import com.jarn.entity.Maintenance;
import com.jarn.entity.RepairsImage;
import com.jarn.entity.User;
import org.apache.commons.fileupload.FileUploadException;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * @Author WZY
 **/
public interface UserService extends BaseService{

    User login(String userName, String password);

    User findUserByUid(int uid);

    void updateUser(User user);

    Object saveMaintenaceAdnImage(HttpServletRequest request);

    @Override
    int findTotal(String keyWords, String status, int uid);

    @Override
    List<Maintenance> findAll(int index, int pageSize, String keyWords, String status, int uid);

    Maintenance findMaintenanceByMid(int mId);

    int evaluation(String evaluation, int mId);
}
