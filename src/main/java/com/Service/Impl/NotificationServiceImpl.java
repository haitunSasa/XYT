package com.Service.Impl;

import com.Dao.BaseDaoI;
import com.Entity.NotificationInfo;
import com.Service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by tan on 2017/3/7.
 */
@Service("notificationService")
public class NotificationServiceImpl extends BaseServiceImpl<NotificationInfo> implements NotificationService {
    @Autowired
    private BaseDaoI<NotificationInfo> dao;
    private List<NotificationInfo> list = new ArrayList<>();

    @Override
    public List<NotificationInfo> getNotyByDateForT(int teacherId, String date, int dateC) {
        Map<String, Object> params = new HashMap<String,Object>();
        params.put("assignTime", date);
        params.put("teacherId", teacherId);
        System.out.println(date);
        String dateHql = "";
        switch (dateC){
            case 1:dateHql = "date_format(assignTime,'%Y')";break;
            case 2:dateHql = "date_format(assignTime,'%Y-%m')";break;
            case 3:dateHql = "date_format(assignTime,'%Y-%m-%d')";break;
        }
        String hql = "from NotificationInfo t where "+dateHql+" =:assignTime and teacherId =:teacherId order by assignTime";
        list = this.dao.find(hql, params);
        return list;
    }
}
