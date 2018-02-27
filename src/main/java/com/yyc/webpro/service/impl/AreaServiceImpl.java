package com.yyc.webpro.service.impl;

import com.yyc.webpro.dao.AreaDao;
import com.yyc.webpro.entity.Area;
import com.yyc.webpro.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AreaServiceImpl implements AreaService {

    @Autowired
    private AreaDao areaDao;

    @Override
    public List<Area> getAreaList() {
        return areaDao.queryArea();
    }
}
