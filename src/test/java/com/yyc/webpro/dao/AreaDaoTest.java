package com.yyc.webpro.dao;

import com.yyc.webpro.entity.Area;
import com.yyc.webpro.BasicTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class AreaDaoTest extends BasicTest {
    @Autowired
    private AreaDao areaDao;

    @Test
    public void testBQueryArea() throws Exception {
        List<Area> areaList = areaDao.queryArea();
        assertEquals(2, areaList.size());
    }
}
