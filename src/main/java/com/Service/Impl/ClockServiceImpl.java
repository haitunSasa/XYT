package com.Service.Impl;

import com.Dao.BaseDaoI;
import com.Entity.ClockInfo;
import com.Service.ClockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by tan on 2017/3/7.
 */
@Service("clockService")
public class ClockServiceImpl extends BaseServiceImpl<ClockInfo> implements ClockService{
    @Autowired
    private BaseDaoI<ClockInfo> dao;
    private List<ClockInfo> list = new ArrayList<ClockInfo>();

    @Override
    public List<ClockInfo> getClockByDateAndS(int studentId, String date, int dateC) {
        Map<String, Object> params = new HashMap<String,Object>();
        params.put("assignTime", date);
        params.put("studentId", studentId);
        System.out.println(date);
        String dateHql = "";
        switch (dateC){
            case 1:dateHql = "date_format(clockTime,'%Y')";break;
            case 2:dateHql = "date_format(clockTime,'%Y-%m')";break;
            case 3:dateHql = "date_format(clockTime,'%Y-%m-%d')";break;
        }
        String hql = "from ClockInfo t where "+dateHql+" =:clockTime and studentId =:studentId order by clockTime";
        list = this.dao.find(hql, params);
        return list;
    }
}

