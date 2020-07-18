package com.jarn.test;

import com.jarn.dao.AdminDao;
import com.jarn.dao.UserDao;
import com.jarn.entity.Maintainer;
import com.jarn.entity.RepairsImage;
import com.jarn.entity.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @Author WZY
 **/
public class MybatisTest {


    private InputStream inputStream;
    private SqlSessionFactory factory;
    private SqlSession sqlSession;
    private AdminDao dao;

    @Test
    public void test01(){

    }

    @Before
    public void init() throws IOException {
        inputStream = Resources.getResourceAsStream("mybatis/mybatis-config.xml");
        factory = new SqlSessionFactoryBuilder().build(inputStream);
        sqlSession = factory.openSession();
        dao = sqlSession.getMapper(AdminDao.class);
    }

    @After
    public void destroy() throws IOException {
        sqlSession.commit();
        sqlSession.close();
        inputStream.close();
    }
}
