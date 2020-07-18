package com.jarn.service;

import java.util.List;

/**
 * @Author WZY
 **/
public interface BaseService{

    int findTotal(String keyWords, String status, int uid);

    <T> List<T> findAll(int index, int pageSize, String keyWords, String status, int uid);
}
