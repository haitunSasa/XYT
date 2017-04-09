package com.Service;

import com.Entity.NotificationInfo;

import java.util.List;

/**
 * Created by tan on 2017/3/7.
 */
public interface NotificationService extends BaseServiceI<NotificationInfo> {
    List<NotificationInfo> getNotyByDateForT(int teacherId, String date, int dateC);
}
