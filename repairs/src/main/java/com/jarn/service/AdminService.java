package com.jarn.service;

import com.jarn.entity.Maintainer;
import com.jarn.entity.User;

import java.util.List;

/**
 * @Author WZY
 **/
public interface AdminService extends BaseService {

    @Override
    int findTotal(String keyWords, String status, int uid);

    @Override
    <T> List<T> findAll(int index, int pageSize, String keyWords, String status, int uid);

    User findUsername(String username);

    int saveUser(User user);

    int updateUser(User user);

    int delUser(int uid);

    List<Maintainer> findMaintainer(String rp, String status);

    Object updateStatus(int mId, int manId, String name, String status);
}
