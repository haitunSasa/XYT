package com.Service;

import com.Entity.ClockInfo;

import java.util.List;

/**
 * Created by tan on 2017/3/7.
 */
public interface ClockService extends BaseServiceI<ClockInfo> {
    public List<ClockInfo> getClockByDateAndS(int studentId,String date,int dateC);
}
