package com.jarn.service.impl;

import com.jarn.common.constant.Constant;
import com.jarn.dao.AdminDao;
import com.jarn.dao.UserDao;
import com.jarn.entity.Maintainer;
import com.jarn.entity.ResultInfo;
import com.jarn.entity.User;
import com.jarn.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author WZY
 **/
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminDao adminDao;

    @Override
    public int findTotal(String keyWords, String status, int uid) {
        return adminDao.findTotal(keyWords, status, uid);
    }

    @Override
    public List<User> findAll(int index, int pageSize, String keyWords, String status, int uid) {
        return adminDao.findAll(index, pageSize, keyWords, status, uid);
    }

    @Override
    public User findUsername(String username) {
        return adminDao.findUsername(username);
    }

    @Override
    public int saveUser(User user) {
        return adminDao.saveUser(user);
    }

    @Override
    public int updateUser(User user) {
        return adminDao.updateUser(user);
    }

    @Override
    public int delUser(int uid) {
        return adminDao.delUser(uid);
    }

    @Override
    public List<Maintainer> findMaintainer(String rp, String status) {
        return adminDao.findMaintainer(rp, status);
    }

    @Override
    public Object updateStatus(int mId, int manId, String name, String status) {
        //维修报修单状态
        int x = adminDao.updateMaintenance(mId, manId, name, status);
        //维修人员状态
        int y = adminDao.Maintainer(manId, Constant.BUSY);

        ResultInfo info = new ResultInfo();
        if (x == 0 || y == 0){
            info.setFlag(false);
        }
        return info;
    }
}
