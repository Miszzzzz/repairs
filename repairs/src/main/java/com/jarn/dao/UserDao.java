package com.jarn.dao;

import com.jarn.entity.Maintenance;
import com.jarn.entity.RepairsImage;
import com.jarn.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author WZY
 **/
@Repository
public interface UserDao {

    /**
     * 用户登录
     * @param userName
     * @param password
     * @return
     */
    User login(String userName, String password);


    User findUserByUid(int uid);

    void updateUser(User user);

    int saveMaintenace(Maintenance maintenance);

    int saveImage(List<RepairsImage> images);

    int findTotal(@Param("keyWords") String keyWords,
                  @Param("status") String status,
                  @Param("uid") int uid);

     List<Maintenance> findAll(@Param("index") int index,
                               @Param("pageSize") int pageSize,
                               @Param("keyWords") String keyWords,
                               @Param("status") String status,
                               @Param("uid") int uid);

    Maintenance findMaintenanceByMid(int mId);

    List<RepairsImage> findRepairsImage(int mId);

    int evaluation(@Param("evaluation") String evaluation,
                   @Param("mId") int mId);
}
