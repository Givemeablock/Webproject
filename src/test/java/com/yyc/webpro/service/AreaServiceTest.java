package com.yyc.webpro.service;

import com.yyc.webpro.BasicTest;
import com.yyc.webpro.entity.Area;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class AreaServiceTest extends BasicTest{
    @Autowired
    private AreaService areaService;

    @Test
    public void testGetAreaList(){
        List<Area> areaList = areaService.getAreaList();
    }
}
