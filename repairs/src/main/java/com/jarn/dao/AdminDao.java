package com.jarn.dao;

import com.jarn.entity.Maintainer;
import com.jarn.entity.Maintenance;
import com.jarn.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author WZY
 **/
public interface AdminDao {

    int findTotal(@Param("keyWords") String keyWords,
                  @Param("status") String status,
                  @Param("uid") int uid);

    List<User> findAll(@Param("index") int index,
                       @Param("pageSize") int pageSize,
                       @Param("keyWords") String keyWords,
                       @Param("status") String status,
                       @Param("uid") int uid);

    User findUsername(String username);

    int saveUser(User user);

    int updateUser(User user);

    int delUser(int uid);

    List<Maintainer> findMaintainer(@Param("rp") String rp,
                                    @Param("status") String status);

    int updateMaintenance(@Param("mId") int mId,
                          @Param("manId") int manId,
                          @Param("name") String name,
                          @Param("status") String status);

    int Maintainer(@Param("manId") int manId,
                   @Param("status") String status);

}
