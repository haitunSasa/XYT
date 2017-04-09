package com.Service.Impl;

import com.Dao.BaseDaoI;
import com.Entity.NotificationClass;
import com.Service.NCService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by tan on 2017/3/7.
 */
@Service("ncService")
public class NCServiceImpl extends BaseServiceImpl<NotificationClass> implements NCService {
    @Autowired
    private BaseDaoI<NotificationClass> dao;

}
