package com.Service.Impl;

import com.Dao.BaseDaoI;
import com.Entity.DeviceInfo;
import com.Service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by tan on 2017/3/12.
 */
@Service("deviceService")
public class DeviceServiceImpl extends BaseServiceImpl<DeviceInfo> implements DeviceService {
    @Autowired
    private BaseDaoI<DeviceInfo> dao;
}
