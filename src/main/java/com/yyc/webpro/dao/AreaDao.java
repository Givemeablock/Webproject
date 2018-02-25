package com.yyc.webpro.dao;

import com.yyc.webpro.entity.Area;

import java.util.List;

public interface AreaDao {
    /**
     * 列出区域列表
     * @return
     */
    List<Area> queryArea();
}
